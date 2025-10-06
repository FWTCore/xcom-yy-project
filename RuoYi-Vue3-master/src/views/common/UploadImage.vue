<template>
    <div class="image-uploader">
        <!-- 图片展示区域 -->
        <div class="image-preview" @click="handleClick">
            <img v-if="imageUrl" :src="imageUrl" class="avatar" alt="图片预览" />
            <div v-else class="uploader-icon">
                <el-icon>
                    <Plus />
                </el-icon>
            </div>
        </div>

        <!-- 文件上传对话框 -->
        <el-dialog v-model="dialogVisible" title="上传图片" width="30%" :before-close="handleClose">
            <el-upload class="upload-demo" drag :auto-upload="false" :show-file-list="false" :on-change="handleChange"
                accept="image/*">
                <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                <div class="el-upload__text">
                    拖拽图片到此处或 <em>点击选择</em>
                </div>
            </el-upload>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitUpload" :loading="uploading">
                        确认上传
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { Plus, UploadFilled } from '@element-plus/icons-vue'
import { uploadImage } from '@/api/fileUpload'

const props = defineProps({
    defaultImage: {
        type: String,
        default: ''
    }
})

const emit = defineEmits(['update:modelValue'])

const imageUrl = ref(props.defaultImage || '')
const dialogVisible = ref(false)
const uploading = ref(false)
const currentFile = ref(null)

// 点击图片或+号
const handleClick = () => {
    dialogVisible.value = true
}

// 关闭对话框
const handleClose = () => {
    dialogVisible.value = false
    currentFile.value = null
}

// 文件选择变化
const handleChange = (file) => {
    // 检查文件类型
    if (!file.raw.type.startsWith('image/')) {
        proxy.$modal.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。")
        return false
    }
    currentFile.value = file.raw
}

// 提交上传
const submitUpload = async () => {
    if (!currentFile.value) {
        proxy.$modal.msgError("请先选择图片")
        return
    }

    uploading.value = true
    try {
        const formData = new FormData()
        formData.append('file', currentFile.value)

        const res = await uploadImage(formData)
        if (res.code === 200) {
            imageUrl.value = res.data.fileUrl
            emit('update:modelValue', res.data.fileUrl)
            proxy.$modal.msgSuccess("上传成功")
            dialogVisible.value = false
        } else {
            proxy.$modal.msgError(res.message || '上传失败')
        }
    } catch (error) {
        proxy.$modal.msgError('上传出错：' + error.message)
    } finally {
        uploading.value = false
    }
}
</script>

<style scoped>
.image-uploader {
    display: inline-block;
}

.image-preview {
    width: 150px;
    height: 150px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f5f7fa;
}

.image-preview:hover {
    border-color: #409eff;
}

.avatar {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.uploader-icon {
    font-size: 28px;
    color: #8c939d;
}

.uploader-icon:hover {
    color: #409eff;
}

:deep(.el-upload) {
    width: 100%;
}

:deep(.el-upload-dragger) {
    width: 100%;
    padding: 20px;
}
</style>