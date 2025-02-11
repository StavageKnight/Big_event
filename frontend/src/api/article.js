import request from '@/utils/request.js'
// import { useTokenStore } from '../stores/token'

//文章分类列表查询
export const articleList = () => {
    // const token = useTokenStore()
    return request.get('/category');
}

//文章分类添加
export const articleAdd = (data) => {
    return request.post('/category', data)
}

//修改文章分类
export const articleEdit = (data) => {
    return request.put('/category', data)
}

//文章列表查询
export const articleGetList = (params) => {
    return request.get('/article', { params: params })
}

//添加文章
export const addArticle = (data) => {
    return request.post('/article', data)
}