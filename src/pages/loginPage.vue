<template>

  <div class="main" style="background-color: #579bde">
    <div
        style="width: 400px;height: 400px;background-color: white;border-radius: 10px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%)">
      <div style="width: 90%;margin: 10px auto">
        <div style="display: flex;font-size: 16px;justify-content: space-around">
          <div style="color: #4b83da;border-bottom: 2px solid #4b83da;line-height: 300%">账号密码登录</div>
          <div style="line-height: 300%">短信验证码登录</div>
        </div>
        <div style="width: 86%;margin: 40px auto">
          <el-form :model="formData" :rules="dataRule" ref="form">
            <div class="inputBorder">
              <el-form-item prop="username">
                <el-input
                    v-model="formData.username"
                    placeholder="请输入账号"
                    :prefix-icon="Avatar"
                    size="large"
                    @blur="listenID"
                />
              </el-form-item>
            </div>
            <div class="inputBorder" style="margin-top: 30px">
              <el-form-item prop="password">
                <el-input
                    v-model="formData.password"
                    type="password"
                    placeholder="请输入密码"
                    show-password
                    :prefix-icon="Lock"
                    size="large"
                    @keyup="enterSent"
                />
              </el-form-item>
            </div>
            <div class="inputBorder" style="margin-top: 30px">
              <el-input
                  :prefix-icon="Menu"
                  size="large"
                  v-model="parmsData"
                  disabled
              />
            </div>
            <div class="inputBorder"
                 style="margin-top: 10px;text-align: right;cursor: pointer;color: #4b83da;font-size: 14px"
                 @click="turnToregister">注册账号
            </div>
            <el-button
                style="width: 100%;height:40px;background-color: #4b83da;color: aliceblue;border: none;border-radius: 5px;margin-top: 20px;"
                @click="login">登录
            </el-button>
          </el-form>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
import {ref} from 'vue'
import {getCurrentInstance} from 'vue'
import axios from "axios"
import {Avatar, Lock, Menu} from '@element-plus/icons-vue'

const {proxy} = getCurrentInstance()
const formData = ref({})
const userToken = ref('')
const parmsData = ref('')

function listenID() {
  let url = '/user-role/getRole?username=' + formData.value.username
  proxy.$api.get(url).then((res) => {
    console.log(res.data.data)
    let parms = res.data.data
    if (parms === 'ROLE_SeniorManager') {
      parmsData.value = '高层管理人员'
    }
    if (parms === 'ROLE_MiddleManager') {
      parmsData.value = '中层管理人员'
    }
    if (parms === 'ROLE_Marketer') {
      parmsData.value = '支行营销人员'
    }
    if (parms === 'ROLE_ExtensionWorker') {
      parmsData.value = '辅助推广人员'
    }
  })
}

const dataRule = {
  username: [
    {
      required: true,
      message: "用户名不能为空",
      trigger: "blur"
    }
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: "blur"
    }
  ]
}
import {useStore} from 'vuex'


const store = useStore()

//回车登录功能，后续需要补充
function enterSent(e) {
  // if((e.keyCode==13))
  // {
  //   login()
  // }
}

function turnToregister() {
  proxy.$router.push('/register')
}

function jsonToFormData(config) {
  const formData = new FormData()
  //循环传入的值转换formData
  Object.keys(config).forEach((key) => {
    formData.append(key, config[key])
  })
  return formData
}

function login() {
  if (!formData.value.username || !formData.value.password || formData.value.username === '' || formData.value.password === '') {
    alert('账号或密码不能为空')
  } else {
    axios.post('http://120.24.235.36:8089/login', jsonToFormData({
      username: formData.value.username,
      password: formData.value.password
    }), {
      headers: {
        'Content-Type': 'multipart/form-data; boundary=--------------------------881790554299985758608590'
      }
    }).then(res => {
      if (res.data === '登录失败!')
        alert('账号或密码错误');
      else {
        if (res.headers.authorization) {
          localStorage.setItem('Authorization', res.headers.authorization)
          proxy.$store.state.Authorization = res.headers.authorization
        }
        proxy.$api.post('/user-role/getRole', {
          username: formData.value.username,
        }).then(res => {
          store.state.privilege = res.data.data
          alert('登录成功');
          proxy.$router.push('/home')
        })
      }
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
  height: 100%;
}

.main {
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: space-between;
  background-color: #eff0f4;
  height: 100%;
}


el-menu-item {
  text-align: center;
}

el-sub-menu {
  text-align: center;
}

html,
body {
  height: 100%;
}
</style>

