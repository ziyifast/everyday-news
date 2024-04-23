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