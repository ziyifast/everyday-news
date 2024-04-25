import { defineStore } from "pinia";
import {ref} from 'vue';

const useUserInfoStore = defineStore('userInfo', () => {
    //定义用户信息
    const info = ref({})
    //设置用户信息
    const setUserInfo = (newInfo) => {
        info.value = newInfo
    }
    //清空用户信息
    const clearUserInfo = () => {
        info.value = {}
    }
    return {
        info,
        setUserInfo,
        clearUserInfo
    }
},{
    persist: true,
})


export default useUserInfoStore;