//导入vue-router
import { createRouter, createWebHistory } from 'vue-router'
//导入组件
import Login from "@/views/Login.vue";
import Home from "@/views/Home.vue";
import Manage from "@/views/article/ArticleManage.vue";
import category from "@/views/article/ArticleCategory.vue";
import Avatar from "@/views/user/UserAvatar.vue";
import Info from "@/views/user/UserInfo.vue";
import ResetPassword from "@/views/user/UserResetPassword.vue";

//定义路由关系
const routes = [
    {
        path: '/login',
        component: Login
    },
    {
        path: '/',
        component: Home,
        redirect: '/article/manage',
        children: [
            {
                path: '/article/category',
                component: category
            },
            {
                path: '/article/manage',
                component: Manage
            },
            {
                path: '/user/avatar',
                component: Avatar
            },
            {
                path: '/user/info',
                component: Info
            },
            {
                path: '/user/resetPassword',
                component: ResetPassword
            }
        ]

    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router