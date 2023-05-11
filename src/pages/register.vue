<template>
  <div class="main" style="background-color: #579bde">
    <div style="width: 400px;height: 400px;background-color: white;border-radius: 10px;position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%)">
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
                />
              </el-form-item >
            </div >
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

            <div class="inputBorder" style="margin-top: 30px;position: relative">
              <el-form-item prop="perms">
              <el-select v-model="value" clearable placeholder="Select" style="width: 100%" size="large">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    size="large"
                />
              </el-select>
              </el-form-item>
              <div class="inputBorder"
                   style="margin-top: 10px;text-align: right;cursor: pointer;color: #4b83da;font-size: 14px"
                   @click="turnToLogin">返回登录
              </div>
            </div>
            <el-button style="width: 100%;height:40px;background-color: #409eff;color: aliceblue;border: none;border-radius: 5px;margin-top: 20px" @click="register">注册</el-button>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import {HomeFilled,User,SwitchButton,DocumentRemove,Folder,Refresh,DocumentChecked,Download,PartlyCloudy} from '@element-plus/icons-vue'
import {ref} from 'vue'
import {getCurrentInstance} from 'vue'
import { Avatar,Lock,Menu,Postcard } from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const formData=ref({})
const value = ref('')
const userToken=ref('')
const dataRule={
  username:[
    {
      required:true,
      message:"用户名不能为空",
      trigger:"blur"
    }
  ],
  password:[
    {
      required:true,
      message:"密码不能为空",
      trigger:"blur"
    }
  ],
  // perms:[
  //   {
  //     required:true,
  //     message:"身份不能为空",
  //   }
  // ]
}
import { useStore } from 'vuex'
const store = useStore()
//回车登录功能，后续需要补充
// function enterSent(e){
//   if((e.keyCode==13))
//   {
//     login()
//   }
// }
// {
//   value: 'Option1',
//       label: 'Option1',
// },
let options=[
  {
    value:'ROLE_SeniorManager',
    label:'高层管理人员',
  },
  {
    value:'ROLE_MiddleManager',
    label:'中层管理人员',
  },
  {
    value:'ROLE_Marketer',
    label:'支行营销人员',
  },
  {
    value:'ROLE_ExtensionWorker',
    label:'辅助推广人员',
  }
]
function register() {
  if (formData.value.username === '' ||formData.value.password === ''||value.value === '') {
    alert('账号或密码或身份信息不能为空');
  }
  else {
    proxy.$api.post('/user-role/saveRegister',{
      // "username":formData.value.username,
      // "password":formData.value.password,
      //用户名、密码、用户级别权限
      username:formData.value.username,
      password:formData.value.password,
      perms:value.value
    }).then(()=> {
      alert('注册成功，正在跳转登录页面……')
      proxy.$router.replace('/login')
    }).catch(error => {
      alert('注册失败');
      console.log(error);
    })
  }
}
function turnToLogin() {
  proxy.$router.back();
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

.main{
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: space-between;
  background-color: #eff0f4;
  height: 100%;
}

el-menu-item{
  text-align: center;
}
el-sub-menu{
  text-align: center;
}
html,
body{
  height: 100%;
}


</style>
