<template>
  <div style="width: 100%;background-color: white;margin: -20px auto;border-radius: 0 0 6px 6px;font-size: 14px;padding-bottom: 20px">
    <div style="height: 40px;width: 100%"></div>
    <div style="margin: 0 auto;text-align: center;width: 99%">
      支行名称：<select class="listSelect">
		  <option value ="龙城">龙城</option>
	  </select>
      绩效类型：<select class="listSelect" v-model="PerformanceType">
		  <option>increPerformance</option>
		  <option>stockPerformance</option>
	  </select>
      起始日期：
      <el-date-picker
	  v-model="start_time"
        type="date"
        placeholder="开始"
        class="listSelect"
        :size="size"/>
      终止日期：
      <el-date-picker
	  v-model="end_time"
        type="date"
        placeholder="结束"
        class="listSelect"
        :size="size"/>
    </div>
      <div>
        <button  class="selectButton">计算</button>
        <button  class="selectButton" @click="performanceShow">输出结果</button>
      </div>
    <div style="width: 96%;height: 100%;margin: 10px auto" v-if="canShow">
      <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
        <tr >
          <td style="font-size: 14px;text-align: center;width: 10%; ">支行</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">客户号</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">余额</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">证件号码</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">贷款合同号</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">贷款金额</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">贷款日期</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">每日xx</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">利率</td>
          <td style="font-size: 14px;text-align: center;width: 10%;">绩效</td>
        </tr>
        <tr v-for="item in dataDemo1">
          <td style="text-align:center;font-size: 12px" v-for="item2 in item">
            {{item2}}
          </td>
        </tr>
      </table>
      <button  class="selectButton" style="background-color: #42b983">下载</button>
    </div>
<!--    <div v-if="canShow" style="width: 84%;height: 100%;background-color: rgba(0,0,0,0.2);position: fixed;top: 0%;"></div>-->
<!--    <div v-if="canShow" style="width: 80%;height: 400px;background-color: white;position:fixed;top: 48%;transform: translateY(-50%);border-radius: 10px;margin-left: 20px">-->
<!--      <el-icon style="position: absolute;top: 4%;right: 6%" @click="cancelShow"><Close /></el-icon>-->
<!--      <div style="text-align: left;margin-left: 20px;margin-top: 20px">贷款绩效计算明细</div>-->
<!--    </div>-->
  </div>


</template>

<script setup>
import {nextTick, ref} from 'vue'
import {getCurrentInstance} from 'vue'
const {proxy}= getCurrentInstance()
const subbranch=ref('龙城支行')
const start_time=ref('')
const end_time=ref('')
const canShow=ref(false)
const PerformanceType = ref('increPerformance')
let dataDemo1=ref([])

function performanceShow(){
	// proxy.$api.post('/finance-analysis/getPerformance/',{
	// 	"subbranch":subbranch.value,
	// 	"start_time":changeDate(start_time.value),
	// 	"end_time":changeDate(end_time.value),
	// }).then((res)=>{
	// console.log(res)
 //    canShow.value=true
 //    dataDemo1.value=res.data.data[PerformanceType.value]
	// for(let i=0;i<dataDemo1.value.length;i++)
	// {
	//   dataDemo1.value[i].conStart=dataDemo1.value[i].conStart[0]+'-'+dataDemo1.value[i].conStart[1]+'-'+dataDemo1.value[i].conStart[2]
	// }
 //    console.log(dataDemo1.value)
 //    nextTick()		
	// })

  let url='/finance-analysis/getPerformance/'
  // +subbranch.value+'/'+changeDate(start_time.value)+'/'+changeDate(end_time.value)
  proxy.$api.post(url,{
	      "timeStart":changeDate(start_time.value),
	      "timeEnd":changeDate(end_time.value),
	      "subbranch":subbranch.value//支行名称
  }).then((res)=>{

    console.log(res.data.data)
    canShow.value=true
    dataDemo1.value=res.data.data[PerformanceType.value]
	for(let i=0;i<dataDemo1.value.length;i++)
	{
	  dataDemo1.value[i].conStart=dataDemo1.value[i].conStart[0]+'-'+dataDemo1.value[i].conStart[1]+'-'+dataDemo1.value[i].conStart[2]
	}
    console.log(dataDemo1.value)
    nextTick()
  })
}
function changeDate(dictTime){
	let Datechange=dictTime.getFullYear() 
	+ '-' 
	+ (dictTime.getMonth() + 1) 
	+ '-' 
	+ dictTime.getDate() 
	return Datechange
}

</script>
<style scoped>
.listSelect {
  width: 140px;
  height: 28px;
  border-radius: 3px;
  border: 1px solid #c4c0c0;
  margin-right: 40px;
}
.selectButton{
  width: 80px;
  height: 30px;
  background-color: #4b83da;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  color: white;
  margin-left: 20px;
  margin-top: 40px;
}
tr:nth-child(odd) {background-color: #f6f9fd;}
</style>