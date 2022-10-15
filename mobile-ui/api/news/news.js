import request from '@/config/request.js';

// 获取动态列表
export const getFeeds = (params) => request.get('/campus/news/list', params)

// 获取资讯列表信息
export const getNews = (params) => request.get('/campus/news/list', params)