import request from '@/utils/request'

// 查询PA平台业务办理步骤;PA平台业务办理步骤列表
export function listSteps(query) {
  return request({
    url: '/agent/steps/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台业务办理步骤;PA平台业务办理步骤详细
export function getSteps(id) {
  return request({
    url: '/agent/steps/' + id,
    method: 'get'
  })
}

// 新增PA平台业务办理步骤;PA平台业务办理步骤
export function addSteps(data) {
  return request({
    url: '/agent/steps',
    method: 'post',
    data: data
  })
}

// 修改PA平台业务办理步骤;PA平台业务办理步骤
export function updateSteps(data) {
  return request({
    url: '/agent/steps',
    method: 'put',
    data: data
  })
}

// 删除PA平台业务办理步骤;PA平台业务办理步骤
export function delSteps(id) {
  return request({
    url: '/agent/steps/' + id,
    method: 'delete'
  })
}
