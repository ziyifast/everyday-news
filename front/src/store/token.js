//定义store
import { defineStore } from 'pinia'
import { ref } from 'vue'
/*
    第一个参数：名字，保证唯一性
    第二个参数：函数，函数内部可以定义状态的所有内容
*/
export const useTokenStore = defineStore('token', () => {
    //定义状态(响应式变量)
    const token = ref('')

    //定义操作状态的方法
    const setToken = (newToken) => {
        token.value = newToken
    }
    const removeToken = () =>{
        token.value = ''
    }
    return {
        token,
        setToken,
        removeToken
    }
})