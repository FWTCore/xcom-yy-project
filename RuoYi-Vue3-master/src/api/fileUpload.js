import request from '@/utils/request'

// 图片上传
export function uploadImage(data) {
  return request({
    url: 'file/uploadImage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data
  })
}