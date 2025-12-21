import request from '@/utils/request'

// 查询核实资产列表
export function listAsset(query) {
  return request({
    url: '/verifyAsset/list',
    method: 'get',
    params: query
  })
}

// 查询核实资产详细
export function getAsset(id) {
  return request({
    url: '/verifyAsset/' + id,
    method: 'get'
  })
}

// 新增核实资产
export function addAsset(data) {
  return request({
    url: '/verifyAsset',
    method: 'post',
    data: data
  })
}

// 修改核实资产
export function updateAsset(data) {
  return request({
    url: '/verifyAsset',
    method: 'put',
    data: data
  })
}

// 删除核实资产
export function delAsset(id) {
  return request({
    url: '/verifyAsset/' + id,
    method: 'delete'
  })
}

// 同步核实资产
export function syncAsset(data) {
  return request({
    url: '/verifyAsset/sync',
    method: 'post',
    data: data
  })
}
