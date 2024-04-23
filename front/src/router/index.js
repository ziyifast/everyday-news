import { createRouter, createWebHistory } from "vue-router";


//导入组件
import  LoginVue  from '@/views/Login.vue'
import  LayoutVue  from '@/views/Layout.vue'


const routes = [
    {
        path: '/login',
        component: LoginVue,
    },
    {
        path: '/',
        component: LayoutVue,
    }
]

const router = createRouter({
    routes:routes,
    history:createWebHistory()
});


export default router;