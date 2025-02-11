import './assets/main.scss'
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router'
import App from './App.vue'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'

// 正确导入 Element Plus 的语言包
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const pinia = createPinia()
const persist = createPersistedState()
const app = createApp(App)

// 使用 Element Plus 并传入中文语言包
app.use(ElementPlus, { locale: zhCn })

// 使用路由和 Pinia
app.use(router)
pinia.use(persist)
app.use(pinia)

// 挂载 Vue 应用
app.mount('#app')
