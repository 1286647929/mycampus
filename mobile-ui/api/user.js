import request from '@/config/request.js';

// 获取用户信息
export const getInfo = () => request.get('/system/user/profile')

// 修改用户信息
export const updateProfile = (params) => request.put('/system/user/profile',params)

// 上传头像
export const avatar = (params) => request.post('/system/user/profile/avatar',params)