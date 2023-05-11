<template>
  <div style="height: 20px"></div>


  <div style="width: 96%; display: flex; justify-content: space-between;" >
  	<div style="width: 15%;height: 150px; display: flex;justify-content: space-between;font-size: 14px;line-height: 400%; flex-direction: column;">
  		<router-link @click="changeInfType"  id="概况" :class="infType=='概况'?'changeInfType1':''" to="/home/customerInf/guaranteeInf/generalSituation">概况</router-link>
  		<router-link @click="changeInfType" id="正在担保" :class="infType=='正在担保'?'changeInfType1':''" to="/home/customerInf/guaranteeInf/inGuarantee">正在担保</router-link>
  		<router-link @click="changeInfType" id="结束担保" :class="infType=='结束担保'?'changeInfType1':''" to="/home/customerInf/guaranteeInf/outGuarantee">结束担保</router-link>
  	</div>
  	<div style="width: 85%;color: grey;overflow-y: auto; margin:0 0 0 30px;">
  	        <router-view></router-view>
  	      </div>
  </div>

</template>


<script setup>

import {ref,provide} from "vue"
const infType=ref('概况')
function changeInfType(e){
  infType.value=e.currentTarget.id
}

// import {inject} from "vue";
// let tableData=inject('guaranteeInf')

</script>

<style type="text/css">
	.changeInfType1{
	  height: 100%;
	  color: #7c99f6;
	  border-right: 4px solid #5f83f5;
	  background-color: #edf1fc;
	  font-size: 16px;
	  font-weight: 800;
	}
	a,router-link{
	  text-decoration: none;
	  color: #2a2525;
	}
</style>

