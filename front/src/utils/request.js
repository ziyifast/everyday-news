//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({ baseURL })


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
        //异步的状态转化成失败的状态
        ElMessage.error('服务异常')
        return Promise.reject(err);
    }
)

export default instance;