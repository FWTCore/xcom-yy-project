<template>
  <div class="upload-demo-container">
    <h2>文件上传组件调用示例</h2>
    
    <!-- 示例1: 基本使用 -->
    <div class="demo-section">
      <h3>1. 基本使用（无绑定）</h3>
      <FileUpload 
        :file-types="['.jpg', '.jpeg', '.png']" 
        :max-file-size="5"
        :max-files="3"
      />
    </div>
    
    <!-- 示例2: 使用v-model绑定单个文件URL -->
    <div class="demo-section">
      <h3>2. 使用v-model绑定单个文件URL（推荐）</h3>
      <el-form :model="formData" label-width="120px">
        <el-form-item label="头像上传">
          <FileUpload 
            v-model="formData.avatar"
            :file-types="['.jpg', '.jpeg', '.png']" 
            :max-file-size="2"
            :max-files="1"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item label="简历上传">
          <FileUpload 
            v-model="formData.resume"
            :file-types="['.pdf', '.doc', '.docx']" 
            :max-file-size="10"
            :max-files="1"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item label="当前头像URL">
          <el-input v-model="formData.avatar" readonly placeholder="上传后自动显示URL" />
        </el-form-item>
        
        <el-form-item label="当前简历URL">
          <el-input v-model="formData.resume" readonly placeholder="上传后自动显示URL" />
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 示例3: 使用v-model绑定多个文件URL -->
    <div class="demo-section">
      <h3>3. 使用v-model绑定多个文件URL（数组）</h3>
      <el-form :model="formData" label-width="120px">
        <el-form-item label="作品集">
          <FileUpload 
            v-model="formData.portfolio"
            :file-types="['.jpg', '.jpeg', '.png', '.pdf']" 
            :max-file-size="5"
            :max-files="5"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item label="作品集URLs">
          <el-tag 
            v-for="(url, index) in (Array.isArray(formData.portfolio) ? formData.portfolio : [])" 
            :key="index"
            type="info"
            style="margin-right: 5px; margin-bottom: 5px;"
          >
            {{ url.substring(url.lastIndexOf('/') + 1) }}
          </el-tag>
          <div v-if="!formData.portfolio || formData.portfolio.length === 0" style="color: #909399;">
            暂无上传文件
          </div>
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 示例4: 通过ref获取 -->
    <div class="demo-section">
      <h3>4. 通过ref调用组件方法</h3>
      <el-form label-width="120px">
        <el-form-item label="附件上传">
          <FileUpload 
            ref="attachmentUploadRef"
            :file-types="['.pdf', '.doc', '.docx', '.xls', '.xlsx', '.zip', '.rar']" 
            :max-file-size="20"
            :max-files="10"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button @click="handleGetUploadedFiles" type="primary">
            获取已上传文件
          </el-button>
          <el-button @click="handleGetUploadedUrls" type="success">
            获取已上传URL
          </el-button>
          <el-button @click="handleClearAll" type="danger">
            清空所有文件
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 示例5: 通过事件监听 -->
    <div class="demo-section">
      <h3>5. 通过事件监听</h3>
      <FileUpload 
        ref="eventUploadRef"
        :file-types="['.jpg', '.jpeg', '.png']" 
        :max-file-size="5"
        :max-files="3"
        class="demo-upload"
        @upload-success="handleUploadSuccess"
        @upload-error="handleUploadError"
        @upload-start="handleUploadStart"
        @upload-complete="handleUploadComplete"
        @file-list-change="handleFileListChange"
        @file-added="handleFileAdded"
        @file-removed="handleFileRemoved"
      />
      
      <div class="event-log" v-if="eventLogs.length > 0">
        <h4>事件日志</h4>
        <div class="log-container">
          <div 
            v-for="(log, index) in eventLogs.slice().reverse()" 
            :key="index"
            class="log-item"
            :class="log.type"
          >
            <span class="log-time">{{ log.time }}</span>
            <span class="log-message">{{ log.message }}</span>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 示例6: 回显已上传文件 -->
    <div class="demo-section">
      <h3>6. 回显已上传文件</h3>
      <el-form label-width="120px">
        <el-form-item label="已有文件回显">
          <FileUpload 
            v-model="formData.existingFile"
            :uploaded-files="[
              { 
                name: 'document.pdf', 
                url: 'https://example.com/files/document.pdf',
                size: 2048000
              },
              { 
                name: 'image.jpg', 
                url: 'https://example.com/files/image.jpg',
                size: 1024000
              }
            ]"
            :file-types="['.jpg', '.jpeg', '.png', '.pdf']" 
            :max-file-size="10"
            :max-files="5"
            class="demo-upload"
          />
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 示例7: 表单提交示例 -->
    <div class="demo-section">
      <h3>7. 完整表单提交示例</h3>
      <el-form 
        :model="formData" 
        :rules="rules" 
        ref="formRef" 
        label-width="120px"
        class="demo-form"
      >
        <el-form-item label="用户姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名" style="width: 300px;" />
        </el-form-item>
        
        <el-form-item label="用户邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="请输入邮箱" style="width: 300px;" />
        </el-form-item>
        
        <el-form-item label="头像" prop="avatar">
          <FileUpload 
            v-model="formData.avatar"
            :file-types="['.jpg', '.jpeg', '.png']" 
            :max-file-size="2"
            :max-files="1"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item label="简历" prop="resume">
          <FileUpload 
            v-model="formData.resume"
            :file-types="['.pdf', '.doc', '.docx']" 
            :max-file-size="10"
            :max-files="1"
            class="demo-upload"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button @click="handleResetForm" style="margin-right: 10px;">
            重置
          </el-button>
          <el-button @click="handleSubmitForm" type="primary">
            提交表单
          </el-button>
        </el-form-item>
      </el-form>
      
      <div class="form-data-preview">
        <h4>表单数据预览</h4>
        <pre>{{ JSON.stringify(formData, null, 2) }}</pre>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import FileUpload from './FileUpload.vue'

// 表单数据
const formData = reactive({
  name: '',
  email: '',
  avatar: '',
  resume: '',
  portfolio: [],
  existingFile: null
})

// 表单验证规则
const rules = {
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  avatar: [
    { required: true, message: '请上传头像', trigger: 'change' }
  ],
  resume: [
    { required: true, message: '请上传简历', trigger: 'change' }
  ]
}

// ref引用
const formRef = ref()
const attachmentUploadRef = ref()
const eventUploadRef = ref()

// 事件日志
const eventLogs = ref([])

// 添加日志
const addLog = (type, message) => {
  const now = new Date()
  const time = `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')}`
  
  eventLogs.value.push({
    time,
    type,
    message
  })
  
  // 只保留最近20条日志
  if (eventLogs.value.length > 20) {
    eventLogs.value.shift()
  }
}

// 示例4: 通过ref获取
const handleGetUploadedFiles = () => {
  if (attachmentUploadRef.value) {
    const fileList = attachmentUploadRef.value.getFileList()
    console.log('文件列表:', fileList)
    
    const fileNames = fileList.map(file => file.name).join(', ')
    ElMessage.info(`文件列表: ${fileList.length}个文件 (${fileNames})`)
  } else {
    ElMessage.warning('请先选择文件')
  }
}

const handleGetUploadedUrls = () => {
  if (attachmentUploadRef.value) {
    const urls = attachmentUploadRef.value.getUploadedUrls()
    const firstUrl = attachmentUploadRef.value.getFirstUploadedUrl()
    
    console.log('所有URL:', urls)
    console.log('第一个URL:', firstUrl)
    
    if (urls.length > 0) {
      ElMessage.success(`获取到 ${urls.length} 个文件URL`)
    } else {
      ElMessage.warning('暂无已上传的文件')
    }
  } else {
    ElMessage.warning('上传组件未初始化')
  }
}

const handleClearAll = () => {
  if (attachmentUploadRef.value) {
    attachmentUploadRef.value.clearFiles()
  }
}

// 示例5: 事件监听
const handleUploadSuccess = ({ file, response }) => {
  console.log('上传成功事件:', file.name, response)
  addLog('success', `文件上传成功: ${file.name}`)
  ElMessage.success(`${file.name} 上传成功`)
}

const handleUploadError = ({ file, error }) => {
  console.log('上传失败事件:', file.name, error)
  addLog('error', `文件上传失败: ${file.name}`)
  ElMessage.error(`${file.name} 上传失败`)
}

const handleUploadStart = (files) => {
  console.log('上传开始事件:', files)
  addLog('info', `开始上传 ${files.length} 个文件`)
  ElMessage.info('开始上传文件...')
}

const handleUploadComplete = (files) => {
  console.log('上传完成事件:', files)
  addLog('info', '上传完成')
  ElMessage.success('文件上传完成')
}

const handleFileListChange = (fileList) => {
  console.log('文件列表变化事件:', fileList)
  addLog('info', `文件列表变化: ${fileList.length} 个文件`)
}

const handleFileAdded = (file) => {
  console.log('文件添加事件:', file)
  addLog('info', `文件添加: ${file.name}`)
}

const handleFileRemoved = (file) => {
  console.log('文件删除事件:', file)
  addLog('warning', `文件删除: ${file.name}`)
}

// 示例7: 表单提交
const handleSubmitForm = async () => {
  try {
    // 验证表单
    await formRef.value.validate()
    
    // 构建提交数据
    const submitData = {
      name: formData.name,
      email: formData.email,
      avatar: formData.avatar,
      resume: formData.resume,
      portfolio: Array.isArray(formData.portfolio) ? formData.portfolio : []
    }
    
    console.log('提交数据:', submitData)
    
    // 这里可以调用API提交数据
    // await api.submitForm(submitData)
    
    ElMessage.success('表单提交成功！')
    
  } catch (error) {
    console.error('表单验证失败:', error)
    if (error && error.fields) {
      ElMessage.error('请完善表单信息')
    }
  }
}

const handleResetForm = () => {
  formRef.value.resetFields()
  formData.avatar = ''
  formData.resume = ''
  formData.portfolio = []
  ElMessage.success('表单已重置')
}

// 页面加载时初始化示例数据
onMounted(() => {
  // 设置一些示例数据
  setTimeout(() => {
    formData.name = '张三'
    formData.email = 'zhangsan@example.com'
  }, 1000)
})
</script>

<style scoped>
.upload-demo-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
}

.demo-section {
  margin-bottom: 40px;
  padding: 20px;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.demo-section h3 {
  margin-top: 0;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #e4e7ed;
  color: #303133;
}

.demo-upload {
  width: 100%;
}

.event-log {
  margin-top: 20px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 15px;
  background-color: #f8f9fa;
}

.event-log h4 {
  margin-top: 0;
  margin-bottom: 10px;
  color: #606266;
}

.log-container {
  max-height: 200px;
  overflow-y: auto;
  font-family: 'Monaco', 'Menlo', 'Ubuntu Mono', monospace;
  font-size: 12px;
}

.log-item {
  padding: 5px 8px;
  margin-bottom: 4px;
  border-radius: 3px;
  border-left: 4px solid #ddd;
}

.log-item.info {
  border-left-color: #409EFF;
  background-color: #ecf5ff;
}

.log-item.success {
  border-left-color: #67C23A;
  background-color: #f0f9eb;
}

.log-item.error {
  border-left-color: #F56C6C;
  background-color: #fef0f0;
}

.log-item.warning {
  border-left-color: #E6A23C;
  background-color: #fdf6ec;
}

.log-time {
  display: inline-block;
  width: 70px;
  color: #909399;
  font-weight: bold;
}

.log-message {
  color: #303133;
}

.form-data-preview {
  margin-top: 20px;
  padding: 15px;
  background-color: #f6f8fa;
  border-radius: 6px;
  border: 1px solid #e1e4e8;
}

.form-data-preview h4 {
  margin-top: 0;
  margin-bottom: 10px;
  color: #24292e;
}

.form-data-preview pre {
  margin: 0;
  padding: 10px;
  background-color: #fff;
  border-radius: 3px;
  overflow-x: auto;
  font-size: 12px;
  line-height: 1.5;
  white-space: pre-wrap;
  word-wrap: break-word;
}

.demo-form {
  max-width: 600px;
}
</style>
