import request from '@/utils/request'

// 查询PA平台账号;PA平台账号列表
export function listAccount(query) {
  return request({
    url: '/agent/account/list',
    method: 'get',
    params: query
  })
}

// 查询PA平台账号;PA平台账号详细
export function getAccount(id) {
  return request({
    url: '/agent/account/' + id,
    method: 'get'
  })
}

// 新增PA平台账号;PA平台账号
export function addAccount(data) {
  return request({
    url: '/agent/account',
    method: 'post',
    data: data
  })
}

// 修改PA平台账号;PA平台账号
export function updateAccount(data) {
  return request({
    url: '/agent/account',
    method: 'put',
    data: data
  })
}

// 删除PA平台账号;PA平台账号
export function delAccount(id) {
  return request({
    url: '/agent/account/' + id,
    method: 'delete'
  })
}

// 查询列表（所有）
export function listAllAccount() {
  return request({
    url: '/agent/account/listAll',
    method: 'get'
  })
}