<template>
<!--  二级路由-->
  <div style="height: 6%;width: 98%;margin:20px auto;background-color: white;border-radius: 6px 6px 0 0;font-size: 14px;">
    <div style="height: 100%;width: 98%;border-bottom: 1px solid #eff0f4 ;margin: 0 auto;">
      <div style="display: flex;width: 30%;justify-content: space-between;height: 100%;">
        <router-link @click="changeType" id="参数录入" style="line-height: 300%;" :style="analysisType=='参数录入'?'color: #4b83da;border-bottom: 2px solid #4b83da;':''" to="/home/financialAnalysis/parameter">参数录入</router-link>
        <router-link @click="changeType" id="成本效益分析" style="line-height: 300%;" :style="analysisType=='成本效益分析'?'color: #4b83da;border-bottom: 2px solid #4b83da;':''" to="/home/financialAnalysis/costAndEffective">成本效益分析</router-link>
        <router-link @click="changeType" id="贷款绩效计算" style="line-height: 300%;" :style="analysisType=='贷款绩效计算'?'color: #4b83da;border-bottom: 2px solid #4b83da;':''" to="/home/financialAnalysis/loanPerformance">贷款绩效计算</router-link>
      </div>
    </div>
  </div>
  <div style="width: 98%;margin: 0 auto;border-radius: 0 0 6px 6px;font-size: 14px;height: 100%">
    <router-view v-slot="{ Component }">
      <transition>
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </transition>
    </router-view>

  </div>

</template>
<script setup>
import {ref} from "vue";

const analysisType=ref('参数录入')
function changeType(e){
  analysisType.value=e.currentTarget.id
}
</script>
<style>
a,router-link{
  text-decoration: none;
  color: #2a2525;
}
</style>