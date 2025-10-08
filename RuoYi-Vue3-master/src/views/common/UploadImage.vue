<template>
    <div class="image-uploader">
        <!-- 图片展示区域 -->
        <div class="image-preview" @click="handleClick">
            <img v-if="imageUrl && imageUrl !== uploadImg" :src="imageUrl" class="avatar" alt="图片预览" />
            <div v-else class="uploader-icon">
                <el-icon>
                    <Plus />
                </el-icon>
            </div>
            <!-- 删除按钮 -->
            <div v-if="imageUrl && imageUrl !== uploadImg" class="delete-btn" @click.stop="handleDelete">
                <el-icon>
                    <Close />
                </el-icon>
            </div>
            <!-- 隐藏的文件输入 -->
            <input ref="fileInput" type="file" accept="image/*" style="display: none" @change="handleFileChange" />
        </div>
    </div>
</template>

<script setup name="UploadImage">
import { ref, watch, getCurrentInstance, onUnmounted } from 'vue'
import { Plus, Close } from '@element-plus/icons-vue'
import { uploadImage } from '@/api/fileUpload'
import uploadImg from '@/assets/images/upload.png'

const { proxy } = getCurrentInstance()

const props = defineProps({
    modelValue: {
        type: String,
        default: ''
    },
    defaultImage: {
        type: String,
        default: ''
    }
})

const emit = defineEmits(['update:modelValue'])

// 优先使用 modelValue，其次使用 defaultImage
const imageUrl = ref(
    normalizeImageUrl(props.modelValue) ||
    normalizeImageUrl(props.defaultImage) ||
    uploadImg
)
const fileInput = ref(null)
const uploading = ref(false)

// 监听 modelValue 变化
watch(() => props.modelValue, (newVal) => {
    if (newVal !== undefined) {
        imageUrl.value = normalizeImageUrl(newVal) || uploadImg
    }
})

/**
 * 标准化图片URL
 * @param url 原始URL
 * @returns 处理后的URL
 */
function normalizeImageUrl(url) {
    console.log(url)
    if (!url) return undefined
    // 如果已经是完整URL或base64数据，直接返回
    if (url.startsWith('http') || url.startsWith('data:')) {
        return url
    }
    // 处理相对路径
    if (!url.startsWith('/api')) {
        return import.meta.env.VITE_APP_BASE_API + url
    }
    return url
}

// 点击图片或+号
const handleClick = () => {
    fileInput.value.click()
}

// 文件选择变化
const handleFileChange = async (event) => {
    const file = event.target.files[0]
    if (!file) return

    // 检查文件类型
    if (!file.type.startsWith('image/')) {
        proxy.$modal.msgError("请上传图片文件（JPG/PNG等）")
        return
    }

    // 检查文件大小（5MB限制）
    const maxSize = 5 * 1024 * 1024
    if (file.size > maxSize) {
        proxy.$modal.msgError("图片大小不能超过5MB")
        return
    }

    // 创建预览URL
    if (imageUrl.value.startsWith('blob:')) {
        URL.revokeObjectURL(imageUrl.value)
    }
    imageUrl.value = URL.createObjectURL(file)

    // 直接上传
    await submitUpload(file)
}

// 提交上传
const submitUpload = async (file) => {
    uploading.value = true
    try {
        const formData = new FormData()
        formData.append('file', file)

        const res = await uploadImage(formData)
        if (res.code === 200) {
            // 上传成功后使用服务器返回的URL替换预览URL
            if (imageUrl.value.startsWith('blob:')) {
                URL.revokeObjectURL(imageUrl.value)
            }
            const imageFileUrl = res.data.fileUrl.startsWith('http')
                ? res.data.fileUrl
                : import.meta.env.VITE_APP_BASE_API + res.data.fileUrl
            imageUrl.value = imageFileUrl
            emit('update:modelValue', res.data.fileUrl)
            proxy.$modal.msgSuccess("上传成功")
        } else {
            proxy.$modal.msgError(res.message || '上传失败')
        }
    } catch (error) {
        proxy.$modal.msgError('上传出错：' + error.message)
    } finally {
        uploading.value = false
    }
}

// 删除图片
const handleDelete = (e) => {
    e.stopPropagation() // 阻止冒泡到父元素
    if (imageUrl.value.startsWith('blob:')) {
        URL.revokeObjectURL(imageUrl.value)
    }
    imageUrl.value = uploadImg
    emit('update:modelValue', '')
    proxy.$modal.msgSuccess("图片已删除")
}

// 组件卸载时清理
onUnmounted(() => {
    if (imageUrl.value.startsWith('blob:')) {
        URL.revokeObjectURL(imageUrl.value)
    }
})
</script>

<style scoped>
.image-uploader {
    display: inline-block;
    position: relative;
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

.image-preview img {
    width: 100%;
    height: 100%;
    object-fit: contain;
    display: block;
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

.delete-btn {
    position: absolute;
    top: 0;
    right: 0;
    width: 20px;
    height: 20px;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 0 0 0 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    cursor: pointer;
    z-index: 1;
}

.delete-btn:hover {
    background-color: rgba(255, 0, 0, 0.7);
}
</style>