<template>
  <div class="main">
    <div class="leftside">
      <div style="height: 7vh;background-color: #2760b9;color: white;font-size: 22px;">
        <span style="line-height: 7vh">村民贷管理系统</span>
      </div>
      <el-menu background-color="#32323a"
               text-color="#ece9e9"
               active-text-color="#fff"
               style="height: 93%;text-align: center"
               router
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <DocumentRemove/>
            </el-icon>
            业务概括
          </template>

          <el-menu-item index="/home/newOverview">宏观信息</el-menu-item>
          <el-menu-item index="/home/distribution">分布情况</el-menu-item>
          <el-menu-item index="/home/newRanking">排行榜</el-menu-item>

        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon>
              <Folder/>
            </el-icon>
            信息查询
          </template>
          <el-menu-item index="/home/customerInf">客户信息</el-menu-item>
          <el-menu-item index="/home/listInf">列表情况</el-menu-item>
          <el-menu-item index="/home/videoInf">影像信息</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <el-icon>
              <Refresh/>
            </el-icon>
            数据分析
          </template>
          <el-menu-item index="/home/trendAnalysis">担保分析</el-menu-item>
          <el-menu-item index="/home/tendencyAnalysis">趋势分析</el-menu-item>
          <el-menu-item index="/home/financialAnalysis">财务分析</el-menu-item>
        </el-sub-menu>
        <!--        <el-sub-menu index="/dataExport">-->
        <!--          <template #title>-->
        <!--            <el-icon><Download /></el-icon>数据导出-->
        <!--          </template>-->
        <!--        </el-sub-menu>-->
        <el-menu-item index="/home/dataExport">
          <template #title>
            <el-icon>
              <Download/>
            </el-icon>
            数据导出
          </template>
        </el-menu-item>
        <el-sub-menu index="6">
          <template #title>
            <el-icon>
              <PartlyCloudy/>
            </el-icon>
            数据录入
          </template>
          <el-menu-item index="/home/retainCustomerInf">客户信息维护</el-menu-item>
          <el-menu-item index="/home/enterpriseCustomer">集团客户</el-menu-item>

          <el-sub-menu index="7">
            <template #title>
              预警名单
            </template>
            <el-menu-item index="/home/humanEntry">人工录入</el-menu-item>
            <el-menu-item index="/home/humanDelete">人工删除</el-menu-item>
            <el-menu-item index="/home/AiEntry">AI录入规则设置</el-menu-item>
          </el-sub-menu>

          <!--          <el-menu-item index="/retainVideoInf">影像信息维护</el-menu-item>-->
          <!--          <el-menu-item index="/accountAdjustment">挂户调整</el-menu-item>-->
        </el-sub-menu>
        <el-sub-menu index="8">
          <template #title>
            <el-icon>
              <DocumentChecked/>
            </el-icon>
            我的管户
          </template>
          <el-menu-item index="/home/myManager">我的管户</el-menu-item>
          <el-menu-item index="/home/financialAssistant">金融助手</el-menu-item>
          <el-menu-item index="/home/potentialCustomer">潜在客户</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
    <div class="mainside">
      <div style="background-color: white;height: 7%">
        <div style="height: 45%"></div>
        <div style="text-align: center;height: 10%;margin-left: 20px">
          <el-icon style="float: left;line-height: 100%">
            <HomeFilled/>
          </el-icon>
          <el-breadcrumb separator="/" style="float: left">
            <el-breadcrumb-item style="margin-left: 10px">业务概括</el-breadcrumb-item>
            <el-breadcrumb-item>宏观信息</el-breadcrumb-item>
          </el-breadcrumb>
          <div style="float: right;font-size: 14px;margin-right: 20px">
            <el-icon style="margin-right: 6px">
              <SwitchButton/>
            </el-icon>
            <span>退出</span>
          </div>
          <div style="float:right;font-size: 14px;margin-right: 20px">
            <el-icon style="margin-right: 6px">
              <User/>
            </el-icon>
            <span>个人中心</span>
          </div>
          <div style="float:right;margin-right: 20px" v-if="store.state.privilege === '超级管理员' || store.state.privilege === 'ROLE_SeniorManager'">
            <span class="superBtn" @click="navigateToSuper">超级管理面板</span>
          </div>
        </div>
      </div>
      <div style="height: 93%;">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  HomeFilled,
  User,
  SwitchButton,
  DocumentRemove,
  Folder,
  Refresh,
  DocumentChecked,
  Download,
  PartlyCloudy
} from '@element-plus/icons-vue'
import {useRouter} from 'vue-router'
import {useStore} from "vuex"

const router = useRouter()
const store = useStore()

function navigateToSuper() {
  router.push('/superManager')
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

.superBtn {
  font-size: 14px;
  padding: 4px 6px;
  border-radius: 6px;
  border: solid 1px #5b8ff9;
  color: #5b8ff9;
  cursor: pointer;
}

.main {
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: space-between;
  background-color: #eff0f4;
  height: 100%;
}

.leftside {
  width: 14%;
  background-color: #32323a;
  height: 100%;
}

.mainside {
  width: 86%;
  height: 100%;
  background-color: #eff0f4;
}

.el-menu-item.is-active {
  background-color: #2760b9 !important;
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

<style scoped>
.el-menu {
  overflow: hidden !important;
}
</style>
