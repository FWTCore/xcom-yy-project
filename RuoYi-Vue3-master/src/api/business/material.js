import request from '@/utils/request'

// 查询物资列表
export function listMaterial(query) {
  return request({
    url: '/material/list',
    method: 'get',
    params: query
  })
}

// 查询物资详细
export function getMaterial(id) {
  return request({
    url: '/material/' + id,
    method: 'get'
  })
}

// 新增物资
export function addMaterial(data) {
  return request({
    url: '/material',
    method: 'post',
    data: data
  })
}

// 修改物资
export function updateMaterial(data) {
  return request({
    url: '/material',
    method: 'put',
    data: data
  })
}

// 删除物资
export function delMaterial(id) {
  return request({
    url: '/material/' + id,
    method: 'delete'
  })
}
