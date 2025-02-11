import request from '@/utils/request.js'

//注册
export const userRegisterService = (registerData) => {
    return request.post('/user/register', registerData);
}

//登录
export const LoginService = (registerData) => {
    return request.post('/user/login', registerData);
}
