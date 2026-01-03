<template>
  <div class="file-upload-container">
    <!-- 上传区域 -->
    <el-upload ref="uploadRef" class="upload-demo" action="#" :multiple="true" :limit="maxFiles"
      v-model:file-list="fileList" :http-request="handleHttpRequest" :before-upload="beforeUpload"
      :on-change="handleChange" :on-remove="handleComponentRemove" :on-exceed="handleExceed" :on-success="handleSuccess"
      :on-error="handleError" :auto-upload="false" :show-file-list="false" :accept="acceptFileTypes">
      <template #trigger>
        <el-button type="primary">选择文件</el-button>
      </template>

      <el-button type="success" @click="submitUpload" :disabled="fileList.length === 0 || uploading || isAllUploaded"
        style="margin-left: 10px;">
        {{ uploadButtonText }}
      </el-button>

      <template #tip>
        <div class="el-upload__tip">
          支持上传 {{ acceptFileTypes }} 格式的文件，单个文件不超过 {{ maxFileSize }}MB
        </div>
      </template>
    </el-upload>

    <!-- 自定义文件列表 -->
    <div class="file-list-container" v-if="fileList.length > 0">
      <div class="file-list-header">
        <span>已选择文件 ({{ fileList.length }})</span>
        <el-button type="danger" text @click="clearAll" :disabled="uploading">
          清空全部
        </el-button>
      </div>

      <el-table :data="fileList" style="width: 100%" empty-text="暂无文件" v-loading="uploading">
        <el-table-column prop="name" label="文件名" min-width="200">
          <template #default="{ row }">
            <div class="file-name">
              <el-icon>
                <Document />
              </el-icon>
              <span class="name-text">{{ row.name }}</span>
            </div>
          </template>
        </el-table-column>

        <el-table-column prop="size" label="大小" width="120">
          <template #default="{ row }">
            {{ formatFileSize(row.size) }}
          </template>
        </el-table-column>

        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="getStatusType(row.status)" size="small">
              {{ getStatusText(row.status) }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="120">
          <template #default="{ row }">
            <el-button v-if="row.status === 'success' && row.url" type="primary" link @click="handleDownload(row)">
              下载
            </el-button>

            <el-button type="danger" link @click="handleCustomRemove(row)"
              :disabled="uploading && row.status === 'uploading'">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Document } from '@element-plus/icons-vue'
import { uploadFiles } from '@/api/fileUpload'

// 组件属性
const props = defineProps({
  fileTypes: {
    type: Array,
    default: () => ['.jpg', '.jpeg', '.png', '.pdf', '.doc', '.docx', '.xls', '.xlsx']
  },
  maxFileSize: {
    type: Number,
    default: 10
  },
  maxFiles: {
    type: Number,
    default: 10
  },
  uploadedFiles: {
    type: Array,
    default: () => []
  },
  // 支持v-model绑定文件URL
  modelValue: {
    type: [String, Array],
    default: null
  }
})

// 定义emit事件
const emit = defineEmits([
  'update:modelValue',
  'upload-success',
  'upload-error',
  'file-list-change',
  'upload-start',
  'upload-complete',
  'file-added',
  'file-removed'
])

// 响应式数据
const uploadRef = ref()
const fileList = ref([])
const uploading = ref(false)
const internalChange = ref(false) // 防止循环更新的标志

// 计算属性
const acceptFileTypes = computed(() => props.fileTypes.join(','))
const isAllUploaded = computed(() => {
  if (fileList.value.length === 0) return false
  return fileList.value.every(file => file.status === 'success')
})
const uploadButtonText = computed(() => {
  if (uploading.value) return '上传中...'
  if (isAllUploaded.value) return '全部已上传'
  return '开始上传'
})

// 关键改造：监听父组件的modelValue变化
watch(() => props.modelValue, (newValue) => {
  // 如果是内部变化触发的，不处理
  if (internalChange.value) {
    internalChange.value = false
    return
  }

  // 父组件的modelValue是唯一数据源，同步到内部fileList
  syncFileListFromModelValue(newValue)
}, { deep: true })

// 关键改造：从modelValue同步到fileList
const syncFileListFromModelValue = (modelValue) => {
  if (!modelValue) {
    fileList.value = []
    return
  }

  try {
    const urls = Array.isArray(modelValue) ? modelValue : [modelValue]
    fileList.value = urls.filter(url => url && url.trim() !== '').map(url => ({
      name: url.split('/').pop() || '已上传文件',
      url: url,
      uid: generateUID(),
      status: 'success',
      size: 0
    }))
  } catch (error) {
    console.error('同步文件列表失败:', error)
    fileList.value = []
  }
}

// 关键改造：获取当前已上传的URLs
const getCurrentUploadedUrls = () => {
  const uploadedUrls = fileList.value
    .filter(file => file.status === 'success' && file.url)
    .map(file => file.url)

  if (uploadedUrls.length === 0) return null
  return uploadedUrls.length === 1 ? uploadedUrls[0] : uploadedUrls
}

// 初始化：从modelValue同步文件列表
onMounted(() => {
  syncFileListFromModelValue(props.modelValue)
})

// 生成唯一UID
const generateUID = () => {
  return Date.now().toString(36) + Math.random().toString(36).substr(2)
}

// 文件大小格式化
const formatFileSize = (bytes) => {
  if (bytes === 0) return '0 B'
  const k = 1024
  const sizes = ['B', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
}

// 状态显示
const getStatusType = (status) => {
  const types = {
    'ready': 'info',
    'uploading': 'warning',
    'success': 'success',
    'fail': 'danger'
  }
  return types[status] || 'info'
}

const getStatusText = (status) => {
  const texts = {
    'ready': '等待上传',
    'uploading': '上传中',
    'success': '上传成功',
    'fail': '上传失败'
  }
  return texts[status] || '未知'
}

// 上传前验证
const beforeUpload = (file) => {
  const fileExtension = '.' + file.name.split('.').pop().toLowerCase()
  const isValidType = props.fileTypes.some(type =>
    type.toLowerCase() === fileExtension
  )

  if (!isValidType) {
    ElMessage.error(`文件格式不支持，请上传 ${props.fileTypes.join(', ')} 格式的文件`)
    return false
  }

  const isLtSize = file.size / 1024 / 1024 < props.maxFileSize
  if (!isLtSize) {
    ElMessage.error(`文件大小不能超过 ${props.maxFileSize}MB`)
    return false
  }

  return true
}

// 文件选择变化处理
const handleChange = (file, files) => {
  // 检查重复文件
  const existingFileIndex = fileList.value.findIndex(f => f.name === file.name && f.uid !== file.uid)
  if (existingFileIndex > -1) {
    if (uploadRef.value && uploadRef.value.handleRemove) {
      uploadRef.value.handleRemove(fileList.value[existingFileIndex])
    }
    ElMessage.warning(`文件 ${file.name} 已替换`)
  }

  emit('file-list-change', fileList.value)
  emit('file-added', file)
}

// 组件内部删除事件
const handleComponentRemove = (file, files) => {
  ElMessage.success(`文件 ${file.name} 已删除`)

  // 关键改造：删除后同步更新父组件的modelValue
  internalChange.value = true
  nextTick(() => {
    const currentUrls = getCurrentUploadedUrls()
    emit('update:modelValue', currentUrls)
    emit('file-list-change', fileList.value)
    emit('file-removed', file)
  })
}

// 自定义文件列表中的删除操作
const handleCustomRemove = async (file) => {
  try {
    await ElMessageBox.confirm(`确定要删除文件 ${file.name} 吗？`, '提示', {
      type: 'warning'
    })

    if (uploadRef.value && uploadRef.value.handleRemove) {
      uploadRef.value.handleRemove(file)
    }

    // 关键改造：删除后同步更新父组件的modelValue
    internalChange.value = true
    nextTick(() => {
      const currentUrls = getCurrentUploadedUrls()
      emit('update:modelValue', currentUrls)
      emit('file-list-change', fileList.value)
      emit('file-removed', file)
    })
  } catch {
    // 用户取消操作
  }
}

// 文件超出限制
const handleExceed = () => {
  ElMessage.warning(`最多只能上传 ${props.maxFiles} 个文件`)
}

// 关键改造：上传成功处理 - 适配新的API响应结构
const handleSuccess = (response, file) => {
  file.status = 'success'

  // 改造点：适配新的API响应结构
  if (response && Array.isArray(response) && response.length > 0) {
    const fileData = response[0]
    file.url = fileData.fileUrl
    file.name = fileData.fileName || file.name
  } else if (response && response.data && Array.isArray(response.data)) {
    const fileData = response.data[0]
    file.url = fileData.fileUrl
    file.name = fileData.fileName || file.name
  } else {
    file.url = response?.data?.url || response?.url
  }

  ElMessage.success(`${file.name} 上传成功`)
  emit('upload-success', { file, response })

  // 关键改造：上传成功后同步更新父组件的modelValue
  internalChange.value = true
  const currentUrls = getCurrentUploadedUrls()
  emit('update:modelValue', currentUrls)
  emit('file-list-change', fileList.value)
}

// 上传失败处理
const handleError = (error, file) => {
  file.status = 'fail'
  const errorMessage = error?.message || error?.response?.data?.message || '未知错误'
  ElMessage.error(`${file.name} 上传失败: ${errorMessage}`)
  emit('upload-error', { file, error })
  emit('file-list-change', fileList.value)
}

// 自定义HTTP请求处理
const handleHttpRequest = async (options) => {
  const { file, onProgress, onSuccess, onError } = options

  try {
    const formData = new FormData()
    formData.append('files', file.raw || file)

    const response = await uploadFiles(formData)

    onSuccess(response)
    return response
  } catch (error) {
    onError(error)
    throw error
  }
}

// 关键改造：开始上传 - 适配新的API响应结构
const submitUpload = async () => {
  if (fileList.value.length === 0) {
    ElMessage.warning('请先选择要上传的文件')
    return
  }

  const filesToUpload = fileList.value.filter(file => file.status !== 'success')
  if (filesToUpload.length === 0) {
    ElMessage.warning('没有需要上传的新文件')
    return
  }

  uploading.value = true
  emit('upload-start', filesToUpload)

  try {
    for (const file of filesToUpload) {
      if (file.status !== 'success') {
        file.status = 'uploading'

        try {
          const formData = new FormData()
          formData.append('files', file.raw || file)

          const response = await uploadFiles(formData)

          file.status = 'success'

          // 关键改造：统一响应处理逻辑
          if (response && Array.isArray(response) && response.length > 0) {
            const fileData = response[0]
            file.url = fileData.fileUrl
            file.name = fileData.fileName || file.name
          } else if (response && response.data && Array.isArray(response.data)) {
            const fileData = response.data[0]
            file.url = fileData.fileUrl
            file.name = fileData.fileName || file.name
          } else {
            file.url = response?.data?.url || response?.url
          }

          ElMessage.success(`${file.name} 上传成功`)
          emit('upload-success', { file, response })
        } catch (error) {
          file.status = 'fail'
          const errorMessage = error?.message || error?.response?.data?.message || '未知错误'
          ElMessage.error(`${file.name} 上传失败: ${errorMessage}`)
          emit('upload-error', { file, error })
        }
      }
    }

    const failedFiles = fileList.value.filter(file => file.status === 'fail')
    const successfulFiles = fileList.value.filter(file => file.status === 'success')

    if (failedFiles.length === 0) {
      ElMessage.success(`所有文件上传完成 (${successfulFiles.length}个文件)`)
    } else if (successfulFiles.length === 0) {
      ElMessage.error('所有文件上传失败')
    } else {
      ElMessage.warning(`${successfulFiles.length}个文件上传成功，${failedFiles.length}个文件上传失败`)
    }

    // 关键改造：上传完成后同步更新父组件的modelValue
    internalChange.value = true
    const currentUrls = getCurrentUploadedUrls()
    emit('update:modelValue', currentUrls)

  } catch (error) {
    console.error('上传过程中出错:', error)
    ElMessage.error('上传过程中出现错误')
  } finally {
    uploading.value = false
    emit('upload-complete', fileList.value)
    emit('file-list-change', fileList.value)
  }
}

// 下载文件
const handleDownload = (file) => {
  if (file.url) {
    window.open(file.url, '_blank')
  } else {
    ElMessage.warning('文件链接不存在')
  }
}

// 清空所有文件
const clearAll = async () => {
  if (fileList.value.length === 0) return

  try {
    await ElMessageBox.confirm('确定要清空所有文件吗？', '提示', {
      type: 'warning'
    })

    if (uploadRef.value && uploadRef.value.clearFiles) {
      uploadRef.value.clearFiles()
    } else {
      fileList.value = []
    }
    ElMessage.success('已清空所有文件')

    // 关键改造：清空后同步更新父组件的modelValue
    internalChange.value = true
    emit('update:modelValue', null)
    emit('file-list-change', fileList.value)
  } catch {
    // 用户取消操作
  }
}

// 获取已上传文件URL
const getUploadedUrls = () => {
  return fileList.value
    .filter(file => file.status === 'success' && file.url)
    .map(file => file.url)
}

// 获取第一个成功上传的URL
const getFirstUploadedUrl = () => {
  const uploadedFile = fileList.value.find(file => file.status === 'success' && file.url)
  return uploadedFile ? uploadedFile.url : null
}

// 暴露方法给父组件
defineExpose({
  getFileList: () => fileList.value,
  getUploadedUrls,
  getFirstUploadedUrl,
  clearFiles: clearAll,
  submitFiles: submitUpload,
  getUploadingStatus: () => uploading.value,
  // 新增：重置到初始状态的方法
  resetToInitial: () => syncFileListFromModelValue(props.modelValue)
})
</script>

<style scoped>
.file-upload-container {
  width: 100%;
}

.upload-demo {
  margin-bottom: 20px;
}

.file-list-container {
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  padding: 20px;
}

.file-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #f0f0f0;
}

.file-name {
  display: flex;
  align-items: center;
  gap: 8px;
}

.name-text {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.el-upload__tip {
  margin-top: 10px;
  color: #909399;
  font-size: 12px;
}
</style>