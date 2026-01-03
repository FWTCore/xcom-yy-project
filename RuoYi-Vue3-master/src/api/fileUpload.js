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

// 或者创建新的专用接口
export function uploadFiles(data) {
  console.log(data)
  return request({
    url: 'file/upload',
    method: 'post',
    headers: { "Content-Type": "multipart/form-data" },
    data: data
  })
}

// 或者创建新的专用接口
export function uploadFile_com(data) {
  return request({
    url: 'common/uploads',
    method: 'post',
    headers: { "Content-Type": "multipart/form-data" },
    data: data
  })
}