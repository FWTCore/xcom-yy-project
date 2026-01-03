import request from '@/utils/request'

// 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件列表
export function listFile(query) {
  return request({
    url: '/agent/file/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件详细
export function getFile(id) {
  return request({
    url: '/agent/file/' + id,
    method: 'get'
  })
}

// 新增PA平台业务办理步骤文件;PA平台业务办理步骤文件
export function addFile(data) {
  return request({
    url: '/agent/file',
    method: 'post',
    data: data
  })
}

// 修改PA平台业务办理步骤文件;PA平台业务办理步骤文件
export function updateFile(data) {
  return request({
    url: '/agent/file',
    method: 'put',
    data: data
  })
}

// 删除PA平台业务办理步骤文件;PA平台业务办理步骤文件
export function delFile(id) {
  return request({
    url: '/agent/file/' + id,
    method: 'delete'
  })
}
