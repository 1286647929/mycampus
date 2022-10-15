import request from '@/config/request.js'

// 查询打卡记录
export const QueryPunchByName = (params) => request.get('/campus/health/listByName',params)

// 添加打卡
export const AddPunch = (params) => request.post('/campus/health', params)
