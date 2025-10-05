import request from '@/utils/request'

// 查询项目成员列表
export function listMember(query) {
  return request({
    url: '/project-member/list',
    method: 'get',
    params: query
  })
}

// 查询项目成员详细
export function getMember(id) {
  return request({
    url: '/project-member/' + id,
    method: 'get'
  })
}

// 新增项目成员
export function addMember(data) {
  return request({
    url: '/project-member',
    method: 'post',
    data: data
  })
}

// 修改项目成员
export function updateMember(data) {
  return request({
    url: '/project-member',
    method: 'put',
    data: data
  })
}

// 删除项目成员
export function delMember(id) {
  return request({
    url: '/project-member/' + id,
    method: 'delete'
  })
}
// 查询角色未授权用户列表
export function unallocatedUserList(query) {
  return request({
    url: '/project-member/unallocatedList',
    method: 'get',
    params: query
  })
}

// 授权用户选择
export function authUserSelectAll(data) {
  return request({
    url: '/project-member/authUser',
    method: 'put',
    params: data
  })
}

// 设置项目负责人
export function setProjectLeader(id,projectId) {
  const data = {
    id,
    projectId
  }
  return request({
    url: '/project-member/setProjectLeader',
    method: 'put',
    data: data
  })
}