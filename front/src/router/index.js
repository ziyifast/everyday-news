import { createRouter, createWebHistory } from "vue-router";


//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import ArticleCategoryVue from '@/views/article/ArticleCategory.vue'
import ArticleManageVue from '@/views/article/ArticleManage.vue'
import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'


const routes = [
    {
        path: '/login',
        component: LoginVue,
    },
    {
        path: '/',
        component: LayoutVue,
        redirect: '/article/category',
        children: [
            {
                path: '/article/category',
                component: ArticleCategoryVue
            },
            {
                path: '/article/manage',
                component: ArticleManageVue
            },
            {
                path: '/user/avatar',
                component: UserAvatarVue
            },
            {
                path: '/user/userinfo',
                component: UserInfoVue
            },
            {
                path: '/user/resetPassword',
                component: UserResetPasswordVue
            }
        ]
    }
]

const router = createRouter({
    routes: routes,
    history: createWebHistory()
});


export default router;