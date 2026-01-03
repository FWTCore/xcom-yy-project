import request from '@/utils/request'

// 查询PA平台分析配置;PA平台分析配置列表
export function listConfig(query) {
  return request({
    url: '/business/config/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台分析配置;PA平台分析配置详细
export function getConfig(id) {
  return request({
    url: '/business/config/' + id,
    method: 'get'
  })
}

// 新增PA平台分析配置;PA平台分析配置
export function addConfig(data) {
  return request({
    url: '/business/config',
    method: 'post',
    data: data
  })
}

// 修改PA平台分析配置;PA平台分析配置
export function updateConfig(data) {
  return request({
    url: '/business/config',
    method: 'put',
    data: data
  })
}

// 删除PA平台分析配置;PA平台分析配置
export function delConfig(id) {
  return request({
    url: '/business/config/' + id,
    method: 'delete'
  })
}
