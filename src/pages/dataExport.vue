<template>
<!--  需要设置二级页面-->
  <div style="width: 98%;height: 84%;margin: 20px auto;font-size: 14px;">
    <div style="width: 100%;height: 7%;background-color: white;border-radius: 6px 6px 0 0;text-align: left">
      <div style="display: flex;justify-content: space-around;width: 54%;margin-left: 2px;height: 100%">
          <router-link @click="changeType" to="/home/dataExport/customerInfExp" style="height: 100%;line-height: 300%" :style="exportType=='客户信息'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="客户信息">客户信息</router-link>
          <router-link @click="changeType" to="/home/dataExport/videoInfExp" style="height: 100%;line-height: 300%" :style="exportType=='影像信息'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="影像信息">影像信息</router-link>
          <router-link @click="changeType" to="/home/dataExport/loanLedger" style="height: 100%;line-height: 300%" :style="exportType=='信贷台账'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="信贷台账">信贷台账</router-link>
          <router-link @click="changeType" to="/home/dataExport/regulationReport" style="height: 100%;line-height: 300%" :style="exportType=='监管报表'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="监管报表">监管报表</router-link>
          <router-link @click="changeType" to="/home/dataExport/termsExport" style="height: 100%;line-height: 300%" :style="exportType=='条件导出'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="条件导出">条件导出</router-link>
          <router-link @click="changeType" to="/home/dataExport/loanDaily" style="height: 100%;line-height: 300%" :style="exportType=='村民贷日报'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="村民贷日报">村民贷日报</router-link>
          <router-link @click="changeType" to="/home/dataExport/approvalForm" style="height: 100%;line-height: 300%" :style="exportType=='审批表'?'border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black':''" id="审批表">审批表</router-link>
      </div>
    </div>
    <div style="height: 93%;width: 100%">
		<router-view v-slot="{ Component }">
		  <transition>
		    <keep-alive>
		      <component :is="Component" />
		    </keep-alive>
		  </transition>
		</router-view>
      
    </div>
  </div>

</template>
<script setup>
import {ref} from "vue";

const exportType=ref('客户信息')
function changeType(e){
  exportType.value=e.currentTarget.id
}
</script>
<style scoped>
input{
  width: 18%;
  height: 11%;
  margin-right: 50px;
}
button{
  background-color: #4b83da;
  border: none;
  width: 80px;
  height: 30px;
  color: white;
  font-size: 16px;
  border-radius: 2px;
  margin-right: 30px;
}
a,router-link{
  text-decoration: none;
  color: #2a2525;
}
</style>