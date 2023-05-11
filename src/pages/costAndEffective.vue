<template>
  <div style="width: 100%;height: 86%;background-color: white;margin-top: -20px">
    <div style="height: 20px"></div>
    <div style="width: 95%;height: 90%;margin: 0 auto;display: flex;justify-content: space-between ">
      <div style="height: 100%;width: 80%;text-align: left">
        <div style="margin:0px 0 0 20%; ">
          <button @click="changeCostType" id='0' :class="costType=='0'?'selectButton':'basicButton'"  style="margin: 0 10px 0 10px;">贷款金额</button>
          <button @click="changeCostType" id='1' :class="costType=='1'?'selectButton':'basicButton'"  style="margin: 0 10px 0 10px;">贷款户数</button>
          <button @click="changeCostType" id='2' :class="costType=='2'?'selectButton':'basicButton'"  style="margin: 0 10px 0 10px;">利率</button>
          <button @click="changeCostType" id='3' :class="costType=='3'?'selectButton':'basicButton'"  style="margin: 0 10px 0 10px;">APRU值</button>
        </div>

        <div style="height: 78%;width: 100%; margin-top: -30px; overflow-x: auto;">

         <loanAmountAn v-if="chartWhich === 'loanAmountAn'" :bank ='chart_bank' :time='chart_time' :type='chart_type'></loanAmountAn>
         <clientCountsAn v-if="chartWhich === 'clientCountsAn'" :bank ='chart_bank' :time='chart_time' :type='chart_type'></clientCountsAn>
         <averRateAn v-if="chartWhich === 'averRateAn'" :bank ='chart_bank' :time='chart_time' :type='chart_type'></averRateAn>
         <ARPUAn v-if="chartWhich === 'ARPUAn'" :bank ='chart_bank' :time='chart_time' :type='chart_type'></ARPUAn>
        </div>
      </div>
      <div style="height: 100%;width: 44%;text-align: left ">
        <div>
          <button id='0' :class="effType=='0'?'selectButton':'basicButton'" @click="changeEffType">资金成本</button>
          <button id='1' :class="effType=='1'?'selectButton':'basicButton'" @click="changeEffType">营销费用</button>
          <button id='2' :class="effType=='2'?'selectButton':'basicButton'" @click="changeEffType">考核奖励</button>
          <button id='3' :class="effType=='3'?'selectButton':'basicButton'" @click="changeEffType">管理成本</button>
        </div>
        <div style="height: 78%;width: 100%;margin-top: -30px;margin-left: 10px">
		 <!-- <keep-alive> -->
		    <!-- <component :is="chartWhich1"></component> -->
			<eff  :xdata = 'xdata' :ydata = 'ydata' :type='chart_type'></eff>
<!-- 			<market v-if="chartWhich1 === 'market'"></market>
			<award v-if="chartWhich1 === 'award'"></award>
			<manage v-if="chartWhich1 === 'manage'"></manage> -->
		<!--  </keep-alive> -->
        </div>
      </div>
    </div>

    <div style="margin-top: -6%;">
      选择支行：<select v-model="chart_bank" @change="changeBank">
      <option>全行</option>
	  <option>龙岗支行</option>
    </select>
      时间区间：<select v-model="chart_time">
      <option>按年</option>
	  <option>按月</option>
	  <option>按日</option>
    </select>
	图表：<select v-model="chart_type">
	  <option>曲线图</option>
	  <option>柱状图</option>
	</select>
    </div>
  </div>
</template>
<script setup>
import {getCurrentInstance, onMounted, ref, provide, nextTick,onActivated,onBeforeMount} from 'vue'
const {proxy}= getCurrentInstance()
const costType=ref(0)
const effType=ref(0)
const xdata = ref([])
const ydata = ref([])
console.log(2)
const effTypeobj=ref({})
const chart_bank = ref('全行')
const chart_time = ref('按年')
const chart_type = ref('曲线图')
let xCostData=ref([])
let yLoanAmountData=ref([])
let yClientCountsData=ref([])
let yAverRateData=ref([])
let yARPUData=ref([])
let loanAmount=[]
let clientCounts=[]
let averRate=[]
let ARPU=[]
let chartWhich = ref("loanAmountAn")
let chartWhich1 = ref("fundCost")
// let tryDta={
//   xCostData:xCostData,
//   yCostData:yCostData,
//   effType:effType.value
// }
// provide('tryData',tryDta)
//[[],[],[],[]]
//默认按月
function changeBank()
{
	console.log(chart_bank.value)
	provide('whichBank',chart_bank.value)
}
onActivated(()=>{
	provide('isact',1)
})
onBeforeMount(()=>{
	
})
onMounted(()=>{
	console.log(1)
	getdata()
// setTimeout(()=>{
// 	getdata()
// },1000)
 //  let url='/finance-analysis/getCostEffectiveness/全行/2021-01-01/2022-03-20/按月'
 //  proxy.$api.post(url,{}).then((res)=>{
	//   console.log(res.data.data)
	//   console.log(res.data.data.Effective)
 //    xCostData.value=Object.keys(res.data.data.Effective)
 //    console.log(xCostData.value,'坐标轴')
 //    let data=res.data.data.Effective

 //    for(let i=0;i<xCostData.value.length;i++)
 //    {
 //      loanAmount[i]=data[xCostData.value[i]].loanAmount
 //      clientCounts[i]=data[xCostData.value[i]].clientCounts
 //      averRate[i]=data[xCostData.value[i]].averRate
 //      ARPU[i]=data[xCostData.value[i]].ARPU
 //    }
 //    // console.log('资金成本',fundCost)
	// provide('yCostData',loanAmount)
 //    yLoanAmountData.value.push(loanAmount)
 //    yClientCountsData.value.push(clientCounts)
 //    yAverRateData.value.push(averRate)
 //    yARPUData.value.push(ARPU)
 //  })
})
function changeCostType(e)
{
  costType.value=e.currentTarget.id
  console.log(costType.value)
  if(costType.value==0){
	chartWhich.value = "loanAmountAn"
	 }
  
  else if(costType.value==1){
	chartWhich.value = "clientCountsAn"
  }
  else if(costType.value==2){
	chartWhich.value = "averRateAn"
  }
  else if(costType.value==3){
	chartWhich.value = "ARPUAn"
  }
  nextTick()
}
function changeEffType(e)
{
  effType.value=parseInt(e.currentTarget.id)
  effTypeobj.value.num=e.currentTarget.id
  // console.log(effTypeobj.value.num,'efffff')
  if(effTypeobj.value.num==0){
	chartWhich1.value = "fundCost"
	getdata()
  }
  else if(effTypeobj.value.num==1){
	chartWhich1.value = "marketCost"
	getdata()
  }
  else if(effTypeobj.value.num==2){
  	chartWhich1.value = "award"  
	getdata()
  }
  else if(effTypeobj.value.num==3){
  	chartWhich1.value = "manageCost"
	getdata()
  }
  
  // console.log(chartWhich1.value)
}

function getdata(){
	console.log(chartWhich1.value)
	if(sessionStorage.getItem(chartWhich1.value)){
		xdata.value = sessionStorage.getItem("numList")
		ydata.value = sessionStorage.getItem(chartWhich1.value)
		xdata.value= xdata.value.split(",")
		ydata.value = ydata.value.split(",")
		// sessionStorage.clear()
		}
}
// provide chartname过去 然后判断 获取数据
// 	provide('effData',yCostData.value)
provide('xCostData',xCostData.value)
provide('effType',effTypeobj.value)

// provide('chartWhich',chartWhich.value)
import costAn from '@/components/costAn'
import eff from '@/components/effectAn'
import loanAmountAn from'@/components/loanAmountAn'
import clientCountsAn from'@/components/clientCountsAn'
import averRateAn from'@/components/averRateAn'
import ARPUAn from'@/components/ARPUAn'
</script>
<style scoped>
.selectButton{
  width: 64px;
  height: 32px;
  background-color: #4b83da;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  color: white;
  margin-left: 10px;
}
.basicButton{
  width: 64px;
  height: 32px;
  background-color: white;
  border: 1px solid gray;
  border-radius: 2px;
  font-size: 12px;
  color: gray;
  margin-left: 10px;
}
</style>