// 封装请求
import axios from 'axios';
import store from '@/store/index';
import router from '@/router/index';
// import {getCurrentInstance} from "vue";
// const {proxy} = getCurrentInstance()
// import { message } from '@element-plus/icons-vue'
const config = {
  // baseURL: process.env.baseURL
  baseURL: 'http://120.24.235.36:8089',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
    "Authentication": localStorage.Authorization
  },
};
const api = axios.create(config);

// 默认 post 请求，使用 application/json 形式
api.defaults.headers.post['Content-Type'] = 'application/json';


//封装下post
api.post = function (url, params) {
  return new Promise((resolve, reject) => {
    axios({
      method: 'post',
      url: config.baseURL + url,
      data: JSON.stringify(params),
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
        "Authorization": localStorage.getItem('Authorization')
      },
    }).then(response => {
      if (response.status == 200) {
        //根据实际情况进行更改
        resolve(response)
      } else {
        reject(response)
      }
    })
  })

}

// 封装get请求
api.get = function (url) {
  return new Promise((resolve, reject) => {
    axios({
      method: 'get',
      url: config.baseURL + url,
      headers: {
//          'Content-Type': 'application/json; charset=utf-8',
        'Content-Type': 'multipart/form-data; charset=utf-8',
        "Authorization": localStorage.getItem('Authorization')
      },
    }).then(response => {
      if (response.status == 200) {
        //根据实际情况进行更改
        resolve(response)
      } else {
        reject(response)
      }
    })
  })
}

// http response 拦截器
axios.interceptors.response.use(response => {
    //拦截响应，做统一处理
    if (response.data.code) {
      // console.log(response.status);
      switch (response.status) {
        case 301: {
          console.log('登录过期');
          break
        }
        case 102: {
          console.log('登录过期');
          // proxy.$message.error('您的账号已在其它地方登陆，若不是本人操作，请注意账号安全！')
          localStorage.removeItem('token');
          store.commit("changeLogin", {Authorization: null})
          // store.commit('loginSuccess', null);
          // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
          setTimeout(() => {
            router.replace({
              path: '/login',
              query: {
                redirect: router.currentRoute.value.fullPath
              },
            });
          }, 1000);
          break
        }

      }
    }
    return response
  },
  //接口错误状态处理，也就是说无响应时的处理
  error => {
    return Promise.reject(error.response.status) // 返回接口返回的错误信息
  })

export default api;
