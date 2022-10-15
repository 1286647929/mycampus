import request from '@/config/request.js'

export const GetNovList = () => request.get('/campus/novdata/list')

// 中国总体疫情数据
export const getNovChinaTotalList = () => request.get('/campus/novchinatotal/list')

// 中国总体疫情当天数据
export const getNovChinaTotalTodayList = () => request.get('/campus/novchinatotaltoday/list')

// 当地疫情数据
export const getlocaltotal = () => request.get('/campus/novlocaltotal/list')
