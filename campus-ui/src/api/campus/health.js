import request from '@/utils/request'

// 查询健康打卡列表
export function listHealth(query) {
  return request({
    url: '/campus/health/list',
    method: 'get',
    params: query
  })
}

// 查询健康打卡详细
export function getHealth(studentId) {
  return request({
    url: '/campus/health/' + studentId,
    method: 'get'
  })
}

// 新增健康打卡
export function addHealth(data) {
  return request({
    url: '/campus/health',
    method: 'post',
    data: data
  })
}

// 修改健康打卡
export function updateHealth(data) {
  return request({
    url: '/campus/health',
    method: 'put',
    data: data
  })
}

// 删除健康打卡
export function delHealth(studentId) {
  return request({
    url: '/campus/health/' + studentId,
    method: 'delete'
  })
}
