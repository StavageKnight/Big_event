    import axios from 'axios';//导入axios  npm install axios
    import { ElMessage } from 'element-plus'
    import { useTokenStore } from '@/stores/token.js';
    //定义一个变量,记录公共的前缀  ,  baseURL
    const baseURL = '/api';//解决跨域
    const instance = axios.create({ baseURL })

    instance.interceptors.request.use(
        (config) => {
            let tokenStore = useTokenStore();
            if (tokenStore.token) {
                config.headers['token'] = tokenStore.token;
            }
            return config;
        },
        (err) => {
            ElMessage.error('服务异常');
            return Promise.reject(err);
        }
    )

    import router from '@/router';
    //添加响应拦截器
    instance.interceptors.response.use(
        result => {
            if (result.data.code == 200) {
                return result.data;
            }
            // console.log("22222");
            ElMessage.error('请先登录！');
            router.push('/login');
            // alert('服务异常');
            return Promise.reject(result.data);//异步的状态转化成失败的状态
        },
        err => {
            ElMessage.error('服务异常');
            return Promise.reject(err);//异步的状态转化成失败的状态
        }
    )

    export default instance;