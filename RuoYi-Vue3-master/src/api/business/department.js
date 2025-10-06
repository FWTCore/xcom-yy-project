import request from '@/utils/request'

// 查询部门列表
export function listDepartment(query) {
  return request({
    url: '/department/list',
    method: 'get',
    params: query
  })
}

// 查询部门详细
export function getDepartment(id) {
  return request({
    url: '/department/' + id,
    method: 'get'
  })
}

// 新增部门
export function addDepartment(data) {
  return request({
    url: '/department',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDepartment(data) {
  return request({
    url: '/department',
    method: 'put',
    data: data
  })
}

// 删除部门
export function delDepartment(id) {
  return request({
    url: '/department/' + id,
    method: 'delete'
  })
}

// 查询列表（所有）
export function listAllDepartment(deptId) {
  return request({
    url: '/department/listAll/' + deptId,
    method: 'get'
  })
}