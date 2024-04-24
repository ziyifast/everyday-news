import request from '@/utils/request.js'
import { useTokenStore } from '@/store/token.js'

// 获取文章分类
export const articleCategoryListService = () => {
    //请求头携带token
    const tokenStore = useTokenStore()
    // //pinia创建的响应式对象不用.value，因此直接使用tokenStore.token=》改为请求拦截器添加token
    // console.log('token=',tokenStore.token)
    return request.get('/category/list')
}