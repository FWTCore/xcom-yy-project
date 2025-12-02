import request from '@/utils/request'

// 查询资产列表
export function listAsset(query) {
  return request({
    url: '/asset/list',
    method: 'get',
    params: query
  })
}

// 查询资产详细
export function getAsset(id) {
  return request({
    url: '/asset/' + id,
    method: 'get'
  })
}

// 新增资产
export function addAsset(data) {
  return request({
    url: '/asset',
    method: 'post',
    data: data
  })
}

// 修改资产
export function updateAsset(data) {
  return request({
    url: '/asset',
    method: 'put',
    data: data
  })
}

// 删除资产
export function delAsset(id) {
  return request({
    url: '/asset/' + id,
    method: 'delete'
  })
}

// 批量修改资产
export function updateAssetBatch(data) {
  return request({
    url: '/asset/batch',
    method: 'put',
    data: data
  })
}

export function disassociateAsset(ids) {
  return request({
    url: '/asset/disassociate/ids',
    method: 'put',
    data: ids
  })
}
