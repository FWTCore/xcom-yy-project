import request from '@/utils/request'

// 查询项目列表
export function listProject(query) {
  return request({
    url: '/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目详细
export function getProject(id) {
  return request({
    url: '/project/' + id,
    method: 'get'
  })
}

// 新增项目
export function addProject(data) {
  return request({
    url: '/project',
    method: 'post',
    data: data
  })
}

// 修改项目
export function updateProject(data) {
  return request({
    url: '/project',
    method: 'put',
    data: data
  })
}

// 删除项目
export function delProject(id) {
  return request({
    url: '/project/' + id,
    method: 'delete'
  })
}
// 查询分类列表（所有）
export function listAllProject(deptId) {
  return request({
    url: '/project/listAll/' + deptId,
    method: 'get'
  })
}