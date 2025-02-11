import request from '@/utils/request.js'

//获取用户详细信息
export const getUserInfoService = () => {
    return request.get('/user/userinfo');
}


//修改个人信息
export const updateUserInfoService = (data) => {
    return request.put('/user/update', data);
}

//修改头像
export const updateAvatarService = (data) => {
    const params = new URLSearchParams()
    params.append('avatar', data)
    return request.patch('/user/updateAvatar', params);
}

//修改密码
export const updatePasswordService = (data) => {
    return request.patch('/user/updatePwd', data);
}