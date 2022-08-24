import request from '@/utils/request'

// 查询离校管理列表
export function listLevscl(query) {
  return request({
    url: '/campus/levscl/list',
    method: 'get',
    params: query
  })
}

// 查询离校管理详细
export function getLevscl(studentId) {
  return request({
    url: '/campus/levscl/' + studentId,
    method: 'get'
  })
}

// 新增离校管理
export function addLevscl(data) {
  return request({
    url: '/campus/levscl',
    method: 'post',
    data: data
  })
}

// 修改离校管理
export function updateLevscl(data) {
  return request({
    url: '/campus/levscl',
    method: 'put',
    data: data
  })
}

// 删除离校管理
export function delLevscl(studentId) {
  return request({
    url: '/campus/levscl/' + studentId,
    method: 'delete'
  })
}
