import request from '@/config/request.js'

export const AddLevSchool = (params) => request.post('/campus/levscl',params)

export const QueryLevSchool = (params) => request.get('/campus/levscl/listByName',params)
