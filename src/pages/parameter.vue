<template>
  <div style="height: 100%;width: 100%;background-color: white;margin: -20px auto;border-radius: 0 0 6px 6px;font-size: 14px;padding-bottom: 20px">
    <div style="height: 20px;width: 100%"></div>
    <div style="width: 76%;margin: 0 auto;text-align: left">
      <span>成本参数：</span>
      <el-checkbox label="录入" style="vertical-align: middle;margin-left: 12px" v-model="checkEnter"></el-checkbox>
      <el-checkbox label="修改" style="vertical-align: middle;margin-left: 20px"></el-checkbox>
    </div>
    <div style="width: 76%;margin: 20px auto;text-align: left">
      <span>开始时间：</span>
      <el-date-picker
          v-model="timeStart"
          type="date"
          placeholder="开始"
          style="width: 200px;margin-left: 10px;"
          :size="size"
      />
      <span style="margin-left: 27px">结束时间：</span>
      <el-date-picker
          v-model="timeEnd"
          type="date"
          placeholder="结束"
          style="width: 200px;margin-left: 10px;"
          :size="size"
      />
    </div>
    <div style="width: 76%;margin: 20px auto;text-align: left">
      <span style="">营销成本：</span>
      <input class="inputText" type="text" v-model="fundCost">
      <span style="margin-left: 37px">营销费用：</span>
      <input class="inputText" type="text" v-model="marketCost">
    </div>

    <div style="width: 76%;margin: 20px auto;text-align: left">
      <span >考核奖励：</span>
      <input class="inputText" type="text" v-model="award">
      <span style="margin-left: 22px">管理成本：</span>
      <input class="inputText" type="text" v-model="manageCost">
    </div>
    <div style="margin-top: 30px">
      <button @click="inputParameter">确定</button>
      <button>取消</button>
    </div>
	 <!-- 本次录入记录 -->
	<div style="margin-top: 20px;  height: 45%;">
		<table style="width: 92%; 1px solid #e5e7ea;border-spacing: 0;">
		  <tr>
			<td style="font-size: 13px;text-align: center; width:14%;">序号</td>
		    <td style="font-size: 13px;text-align: center; width:14%;">开始时间</td>
		    <td style="font-size: 13px;text-align: center; width:14%;">结束时间</td>
		    <td style="font-size: 13px;text-align: center; width:14%;">营销成本</td>
		    <td style="font-size: 13px;text-align: center; width:14%;">营销费用</td>
			<td style="font-size: 13px;text-align: center; width:14%;">考核奖励</td>
			<td style="font-size: 13px;text-align: center; width:14%;">管理成本</td>
		  </tr>
		  <tr v-for="item in newData">
		    <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 13%;">
		      {{item2}}
		    </td>
		  </tr>
		</table>
	</div>
	<div id="page">

		<el-pagination
		      v-model:currentPage="page"
		      :page-size="10"
		      :small="small"
		      :disabled="disabled"
		      :background="background"
		      layout="prev, pager, next,jumper"
			  :page-count="totallpage"
			@click = "getpagedata(page)"
		    />
	</div>
  </div>
 
  
</template>

<script setup>
import {ref,onMounted,watch} from "vue";
import {getCurrentInstance} from 'vue'
const {proxy}= getCurrentInstance()
const timeStart=ref('')
const timeEnd=ref('')
const fundCost=ref('')
const marketCost=ref('')
const award=ref('')
const manageCost=ref('')
const checkEnter = ref(false)
var num = ref(1)
var tempData=ref([])
var newData=ref([])
var totallpage = ref(1)
var pagenumber = ref(8)	
var page = ref(0)
var pageList = ref([1])
var fundCostList=[]
var marketCostList=[]
var awardList=[]
var manageCostList=[]
var numList=[]
var timeList = []

watch(() => page.value, (e) => {
  getpagedata(e)
}, { deep: true })

onMounted(()=>{
	// document.getElementsByClassName(
	//       "el-pagination__jump"
	//     )[0].childNodes[0].nodeValue = "跳至";
})
function inputParameter() {
  let url='/finance-analysis/insertBenefitInfo/'+changeDateToSubmit(timeStart.value)+'/'+changeDateToSubmit(timeEnd.value)+'/'+fundCost.value+'/'+marketCost.value+'/'+award.value+'/'+manageCost.value
  proxy.$api.post(url,{}).then((res)=>{
    //提示插入成功
	// console.log(url)
 //    console.log(res.data.data)
	// console.log(timeStart.value)
  })



	let startChanged=changeDate(timeStart.value)
	let endChanged=changeDate(timeEnd.value)
  if(timeStart.value&&timeEnd.value&&fundCost.value&&manageCost.value&&award.value&&manageCost.value){
	  var unitData=[num.value,startChanged,endChanged,fundCost.value,marketCost.value,award.value,manageCost.value]
	  if(!compareTime(timeStart.value,timeEnd.value)){
		  return
	  }
  }
  else{
	  alert("输入为空或填写不完整，请重新填写")
	  return
  }
	fundCostList.push(fundCost.value)
	marketCostList.push(marketCost.value)
	awardList.push(award.value)
	manageCostList.push(manageCost.value)
	numList.push(num.value)
	timeList.push(num.value)
	num.value++
	sessionData()
	tempData.value.push(unitData)
	getpage()
	// let thisPage = parseInt((tempData.value.length-1)/pagenumber.value)+1
	// console.log(thisPage)
	getpagedata(totallpage.value)
	// console.log(totallpage.value)
	// console.log(tempData.value)
	// console.log(num.value)
  }
  
function getpage() {
	totallpage.value = parseInt((tempData.value.length-1)/pagenumber.value)+1
	}
	
// function addpage() {
// 	    for (var i = 0; i < totallpage.value; i++) {
// 		    pageList.value[i] = i + 1;
// 	        }
// 		    return pageList.value;
//         }
	
function getpagedata(index){
		page.value =index;//获取当前的页数是多少
		newData.value = tempData.value.slice((page.value-1)*pagenumber.value,
										(page.value)*pagenumber.value);
	 
	    }
function nextPage(index){
	if(page.value+1<=totallpage.value){
		getpagedata(page.value+1)
	}
}	
function prePage(index){
	if(page.value-1>=1){
		getpagedata(page.value-1)
	}
}

function changeDate(dictTime){
	let Datechange=dictTime.getFullYear() 
	+ '-' 
	+ (dictTime.getMonth() + 1) 
	+ '-' 
	+ dictTime.getDate() 
	// +' ' 
	// + dictTime.getHours() 
	// + ':' 
	// + dictTime.getMinutes() 
	// + ':' 
	// + dictTime.getSeconds();
	return Datechange
}
function changeDateToSubmit(dictTime){
	// console.log(dictTime)
	let month = (dictTime.getMonth()+1)
		if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	let day = dictTime.getDate()
		if (day >= 1 && day <= 9) {
		    day = "0" + day;
			
		}
	let hour = dictTime.getHours()
		if (hour >= 0 && hour <= 9) {
			hour = "0" + hour;
		}
	let minute = dictTime.getMinutes()
		if (minute >= 0 && minute <= 9) {
			minute = "0" + minute;
		}
		let sec = dictTime.getSeconds()
			if (sec >= 0 && sec <= 9) {
				sec = "0" + sec;
			}
	let Datechange=dictTime.getFullYear() 
	+ '-' 
	+ month
	+ '-' 
	+ day
	+' ' 
	+ hour 
	+ ':' 
	+ minute 
	+ ':' 
	+ sec
	return Datechange
}

function compareTime(start,end){
let	startYear = start.getFullYear()
let	endYear = end.getFullYear()
let	startMonth = start.getMonth()
let	endMonth = end.getMonth()
let	startDay = start.getDate() 
let	endDay = end.getDate() 
	if(endYear<startYear){
		alert("结束时间必须大于开始时间！")
		return false
	}
	else if(endYear==startYear){
		if(endMonth<startMonth){
			alert("结束时间必须大于开始时间！")
			return false
		}
		else if(endMonth==startMonth){
			if(endDay<startDay){
				alert("结束时间必须大于开始时间！")
				return false
			}
		}
		return true
	}
}
function sessionData(){
	sessionStorage.setItem("fundCost",fundCostList)
	sessionStorage.setItem("marketCost",marketCostList)
	sessionStorage.setItem("award",awardList)
	sessionStorage.setItem("manageCost",manageCostList)
	sessionStorage.setItem("numList",numList)
	
}

// beforeRouteLeave(to, from, next){
//     if (to.name ==='costAndEffective'){
// 		sessionData()
// 	}
// 	next()
// }


</script>

<style scoped>
.inputText{
  width: 180px;
  height: 22px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
  margin-right: 10px;
}
.listSelect {
  width: 180px;
  height: 24px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
}
Button{
  width: 90px;
  height: 32px;
  background-color: #4b83da;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  color: white;
  margin-left: 30px;
}
tr:nth-child(odd) {background-color: #f6f9fd;}
tr{
  width: 100%;
  height: 40px;
  text-align:left;
  font-size: 16px;
}
#page{
	z-index: 100;
	margin: 30px 0 auto;
	display: flex;
	justify-content: center;	
}
.pagination{
	list-style: none;
	display: flex;
	justify-content: center;
}
.pagination li {
	font-size: 18px;
	margin: 8px;
	text-align: center;
	line-height: 18px;
}
.numList:hover{
	color: #fc0007;
}

</style>