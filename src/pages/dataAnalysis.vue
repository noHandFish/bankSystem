<template>
  <div id="topbar">
    <div class="top">
      <router-link to="/dataAnalysis/financialAnalysis">担保分析</router-link>
    </div >
    <div class="top">
      <router-link to="/dataAnalysis/trendAnalysis">趋势分析</router-link>
    </div>
    <div class="top">
      <!--      如果不使用路由嵌套的话，整个一级路由界面都会消失-->
      <router-link to="/dataAnalysis/financialAnalysis">财务分析</router-link>
    </div>
  </div>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name:'dataAnalysis'
}
</script>

<style>
#topbar{
  display: flex;
  justify-content: space-around;
}
.top{
  width: 34%;
  height: 60px;
  text-align: center;
  border-bottom-color: #a9a0a0;
  border-bottom-style: solid;
  border-bottom-width: 1px;
}
.top>a{
  display: block;
  font-size: 22px;
  height: 100%;
}
</style>