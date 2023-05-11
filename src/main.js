// createApp(App).use(store).use(router).mount('#app').$e
import Vue from 'vue'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import store from './store'
import api from './assets/config/config'

// import "babel-polyfill"
// import VueWechatTitle from 'vue-wechat-title';
// import "babel-polyfill"
import * as echarts from 'echarts'

import Axios from "axios"					//axios接口引用
const app = createApp(App)
app.config.globalProperties.$axios = Axios	

import qs from "qs"							//qs引用
app.config.globalProperties.$qs = qs

app.use(router).use(ElementPlus).use(store).use(echarts).use(router)
app.config.globalProperties.$api=api
app.config.globalProperties.$echarts=echarts
app.mount('#app')