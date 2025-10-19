import request from '@/utils/request'

// 查询原始资产列表
export function listOriginalAsset(query) {
  return request({
    url: '/originalAsset/list',
    method: 'get',
    params: query
  })
}

// 查询原始资产详细
export function getOriginalAsset(id) {
  return request({
    url: '/originalAsset/' + id,
    method: 'get'
  })
}

// 新增原始资产
export function addOriginalAsset(data) {
  return request({
    url: '/originalAsset',
    method: 'post',
    data: data
  })
}

// 修改原始资产
export function updateOriginalAsset(data) {
  return request({
    url: '/originalAsset',
    method: 'put',
    data: data
  })
}

// 删除原始资产
export function delOriginalAsset(id) {
  return request({
    url: '/originalAsset/' + id,
    method: 'delete'
  })
}
