//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'
import { useTokenStore } from '@/store/token.js';
import router from '@/router'
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({ baseURL })

//添加请求拦截器
instance.interceptors.request.use(
    config => {
        const token = useTokenStore().token
        if (token) {
            //添加token
            config.headers['Authorization'] = token;
        }
        return config
    },
    err => {
        return Promise.reject(err);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断状态码
        if (result.data.code === 0) {
            return result.data;
        }
        //异步的状态转化成失败的状态
        ElMessage.error(result.data.message ? result.data.message : '服务异常');
        return Promise.reject(result.data);
    },
    err => {
        if (err.response.status === 401) {
            ElMessage.error('未登录');
            //跳转到登录页面
            router.push('/login');
        } else {
            //异步的状态转化成失败的状态
            ElMessage.error('服务异常')
            return Promise.reject(err);
        }
    }
)

export default instance;