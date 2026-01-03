import request from '@/utils/request'

// 查询PA平台分析配置详情;PA平台分析配置详情列表
export function listDetail(query) {
  return request({
    url: '/business/detail/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台分析配置详情;PA平台分析配置详情详细
export function getDetail(id) {
  return request({
    url: '/business/detail/' + id,
    method: 'get'
  })
}

// 新增PA平台分析配置详情;PA平台分析配置详情
export function addDetail(data) {
  return request({
    url: '/business/detail',
    method: 'post',
    data: data
  })
}

// 修改PA平台分析配置详情;PA平台分析配置详情
export function updateDetail(data) {
  return request({
    url: '/business/detail',
    method: 'put',
    data: data
  })
}

// 删除PA平台分析配置详情;PA平台分析配置详情
export function delDetail(id) {
  return request({
    url: '/business/detail/' + id,
    method: 'delete'
  })
}
