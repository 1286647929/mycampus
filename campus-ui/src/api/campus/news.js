import request from '@/utils/request'

// 查询新闻列表
export function listNews(query) {
  return request({
    url: '/campus/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻详细
export function getNews(newId) {
  return request({
    url: '/campus/news/' + newId,
    method: 'get'
  })
}

// 新增新闻
export function addNews(data) {
  return request({
    url: '/campus/news',
    method: 'post',
    data: data
  })
}

// 修改新闻
export function updateNews(data) {
  return request({
    url: '/campus/news',
    method: 'put',
    data: data
  })
}

// 删除新闻
export function delNews(newId) {
  return request({
    url: '/campus/news/' + newId,
    method: 'delete'
  })
}
