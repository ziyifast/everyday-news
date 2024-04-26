import request from '@/utils/request.js'

//用户注册接口
export const userRegisterService = (registerData) => {
    //URL param
    let params = new URLSearchParams();
    for( let key in registerData) {
        params.append(key, registerData[key]);
    }
    return request.post('/user/register', params);
}

//用户登录接口
export const userLoginService = (loginData) => {
    //URL param
    let params = new URLSearchParams();
    for( let key in loginData) {
        params.append(key, loginData[key]);
    }
    return request.post('/user/login', params);
}

//获取用户信息
export const getUserInfoService = () => {
    return request.get('/user/userInfo');
}

//修改用户信息
export const updateUserInfoService = (userInfo) => {
    return request.put('/user/update', userInfo);
}

//更新用户头像
export const updateUserAvatarService = (avatarUrl) => {
    let params = new URLSearchParams();
    params.append('avatorUrl', avatarUrl);
    return request.put('/user/updateAvator', params);
}