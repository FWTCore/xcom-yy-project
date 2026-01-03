import request from '@/utils/request'

// 查询PA平台智能体;PA平台智能体列表
export function listAgent(query) {
  return request({
    url: '/business/agent/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台智能体;PA平台智能体详细
export function getAgent(id) {
  return request({
    url: '/business/agent/' + id,
    method: 'get'
  })
}

// 新增PA平台智能体;PA平台智能体
export function addAgent(data) {
  return request({
    url: '/business/agent',
    method: 'post',
    data: data
  })
}

// 修改PA平台智能体;PA平台智能体
export function updateAgent(data) {
  return request({
    url: '/business/agent',
    method: 'put',
    data: data
  })
}

// 删除PA平台智能体;PA平台智能体
export function delAgent(id) {
  return request({
    url: '/business/agent/' + id,
    method: 'delete'
  })
}