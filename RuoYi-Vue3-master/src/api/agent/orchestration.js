import request from '@/utils/request'

// 查询PA平台业务编排;PA平台业务编排列表
export function listOrchestration(query) {
  return request({
    url: '/business/orchestration/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台业务编排;PA平台业务编排详细
export function getOrchestration(id) {
  return request({
    url: '/business/orchestration/' + id,
    method: 'get'
  })
}

// 新增PA平台业务编排;PA平台业务编排
export function addOrchestration(data) {
  return request({
    url: '/business/orchestration',
    method: 'post',
    data: data
  })
}

// 修改PA平台业务编排;PA平台业务编排
export function updateOrchestration(data) {
  return request({
    url: '/business/orchestration',
    method: 'put',
    data: data
  })
}

// 删除PA平台业务编排;PA平台业务编排
export function delOrchestration(id) {
  return request({
    url: '/business/orchestration/' + id,
    method: 'delete'
  })
}
