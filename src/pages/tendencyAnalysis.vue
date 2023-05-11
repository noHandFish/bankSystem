<template>

  <div style="width: 98%;margin: 20px auto;background-color: white;border-radius: 6px">
    <div style="height: 20px"></div>
    <div style="font-size: 14px;display: flex;justify-content: space-around;width: 100%;">
      <div style="width: 38%;padding-left: 10px;text-align: left">
        <div style="display: flex; justify-content: center; align-items: center;">
          <!-- <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="range_value"> -->分析范围：
          <div class="example-block">
              <span class="example-demonstration"></span>
			  <!-- :show-all-levels="false" -->
              <el-cascader v-model="rangeAnalysis" :options="range"  @change="handleChange" />
            </div>
        </div>
        <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
         <!-- <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="time_value"> -->时间频率：
          <div class="example-block">
              <span class="example-demonstration"></span>
              <el-cascader v-model="timeCount" :options="timeRate" @change="handleChange" />
            </div>
			</div>
			<div style="margin-top: 20px;">
				<div v-if="timeCount=='按月'" style="display: flex; justify-content: center;align-items: center;">
					<div class="block" >
					        <el-date-picker
					          v-model="month1"
					          type="month"
					          placeholder="选择开始月份"
					        />
					      </div>
						  <span style="margin: 10px;">至</span>
						  <div class="block" >
						          <el-date-picker
						            v-model="month2"
						            type="month"
						            placeholder="选择结束月份"
						          />
						        </div>
			</div>
			<div v-if="timeCount=='按年'" style="display: flex; justify-content: center;align-items: center;margin-left: 180px">
				<div class="block">
				        <el-date-picker
				          v-model="year1"
				          type="year"
				          placeholder="选择开始年份"
				        />
				      </div>
					  <span style="margin: 10px;">至</span>
					  <div class="block">
					          <el-date-picker
					            v-model="year2"
					            type="year"
					            placeholder="选择结束年份"
					          />
					        </div>
			</div>

			<div v-if="timeCount=='按日'" style="display: flex; justify-content: center;align-items: center;">
				<div class="block">
				        <el-date-picker
				          v-model="day1"
				          type="date"
				          placeholder="选择开始日期"
				        />
				      </div>
					  <span style="margin: 10px;">至</span>
					  <div class="block">
					          <el-date-picker
					            v-model="day2"
					            type="date"
					            placeholder="选择结束日期"
					          />
					        </div>
			</div>

        </div>
      </div>
      <div style="width: 38%;padding-left: 10px;text-align: left">
        <div style="display: flex; justify-content: center; align-items: center;">
          <!-- <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="chart_value"> -->图表形式：
          <div class="example-block">
              <span class="example-demonstration"></span>
              <el-cascader v-model="chartType" :options="chart" @change="handleChange" />
            </div>
        </div>
  		<div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;" >
  		  <!-- <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="analysis_value"> -->分析项目：
  		  <div class="example-block">
  		      <span class="example-demonstration"></span>
  		      <el-cascader v-model="analysisItem" :options="analyItem" @change="handleChange" />
  		    </div>
  		</div>
			</div>
				</div>
		<div style="margin-top: 20px;">
			<button class="searchButton" @click="searchTrend" style="margin-bottom: 20px">查询</button>
		</div>


      </div>
	  <div style="height: 76%;width: 100%;margin-top: -60px;margin-left: 10px" v-if="canShow">
		  <div v-if="chartType=='list'">
		  	<div style="width: 100%; height: 100%; margin-top: 60px;" >
		  		<table style="border: 1px solid #e5e7ea;border-spacing: 0; width: 100%;">
		  			<tr v-for="(item,index) in yData" >
						<td style="width: 3%;padding-left: 1%;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index+1}}</td>
						<td style="width: 97%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{analysisItemShow}}:  {{item}}</td>
		  			</tr>
		  		</table>
		  	</div>
		  </div>
		  <div style="width: 100%; height: 100%;" v-else>
		  	<tendencyAn  :yData = 'yData' :type = 'chartType' :xData = 'xData'></tendencyAn>
		  </div>

	  </div>
</template>


<script setup>
	import {ref} from 'vue'
	import {getCurrentInstance} from 'vue'
	const {proxy}= getCurrentInstance()
	const rangeAnalysis = ref(["全行"])
	const timeCount = ref(["按年"])
	const chartType = ref(["line"])
	const analysisItem = ref(["loanBalance"])
	const analysisItemShow = ref("贷款金额")
	const month1 = ref()
	const month2 = ref()
	const year1 = ref()
	const year2 = ref()
	const day1 = ref()
	const day2 = ref()
	const xData = ref([])
	const yData = ref([])
	const range_value = ref()
	const tendency_data = ref([])
	const canShow=ref(false)
	const props = {
	  expandTrigger: 'hover',
	}
	function searchTrend(){
		let url = {
			"subbranch": rangeAnalysis.value[rangeAnalysis.value.length-1], //全行,龙华支行,等支行
			"time": "本年", //本年,本月,本周
			"interval":  timeCount.value[0], //按年,按月,按天
			"manager": "廖惠娣" //可不填
		}
		console.log(url)
		proxy.$api.post('/finance-analysis/trendAnalysis',{
				"subbranch": rangeAnalysis.value[rangeAnalysis.value.length-1], //全行,龙华支行,等支行
				"time": "本年", //本年,本月,本周
				"interval":  timeCount.value[0], //按年,按月,按天
				"manager": "廖惠娣" //可不填
		}).then((res)=>{
			console.log(res)
			let data = res.data.data.macroInfo
			console.log(data)
			xData.value = Object.keys(data)
			tendency_data.value = data
			for(let i = 0;i<xData.value.length;i++){
				console.log(data[xData.value[i]])
				yData.value[i] = data[xData.value[i]][analysisItem.value]
			}
			canShow.value = true
			console.log(yData.value,'y')
			console.log(xData.value,'x')
			console.log(tendency_data.value)
			})
	}

	const range = [
	  {
	    value: '全行',
	    label: '全行',

	  },
	  {
	    value: '各支行',
	    label: '各支行',
	    children: [
			{
				value: '龙华支行',
				label: '龙华支行',
			},
			{
				value: 'xx支行',
				label: 'xx支行',
			},
			{
				value: 'xx支行',
				label: 'xx支行',
			},
	    ],
	  },
	  {
	    value: '客户经理',
	    label: '客户经理',
	    children: [
	  			{
	  				value: '李四',
	  				label: '李四',
	  			},
	  			{
	  				value: '张三',
	  				label: '张三',
	  			},
	  			{
	  				value: '王五',
	  				label: '王五',
	  			},
	    ],
	  },

	]
	const chart = [
		{
			value: 'bar',
			label: '柱状图',
		},
		{
			value: 'line',
			label: '曲线图',
		},
		{
			value: 'list',
			label: '列表',
		}
	]
	const analyItem = [
		{
			value: 'loanBalance',
			label: '贷款金额',
		},
		{
			value: 'clientCount',
			label: '贷款户数',
		},
		{
			value: 'averLoanAmount',
			label: '平均利率（加权）',
		},
		{
			value: 'arpu',
			label: 'ARPU值',
		}
	]
	const timeRate = [
		{
			value: '按年',
			label: '按年',
		},
		{
			value: '按月',
			label: '按月',
		},
		{
			value: '按日',
			label: '按日',
		},
	]

function handleChange(){
	searchTrend()
	if(analysisItem.value[0]=="loanBalance"){
		analysisItemShow.value = "贷款余额"
	}
	else if(analysisItem.value[0]=="clientCount"){
		analysisItemShow.value = "贷款户数"
	}
	else if(analysisItem.value[0]=="averLoanAmount"){
		analysisItemShow.value = "平均利率（加权）"
	}
	else if(analysisItem.value[0]=="arpu"){
		analysisItemShow.value = "ARPU值"
	}
	}
import tendencyAn from '@/components/tendencyAn'
</script>

<style>
	.inputText{
	  width: 100px;
	  height: 25px;
	  border-radius: 5px;
	  border: 1px solid #c4c0c0;
	  margin-left: 10px;
	  margin-right: 10px;
	}
	.listSelect {
	  width: 140px;
	  height: 30px;
	  border-radius: 5px;
	  border: 1px solid #c4c0c0;
	  margin-left: 10px;
	}
	.searchButton{
	  width: 90px;
	  height: 32px;
	  background-color: #4b83da;
	  border: none;
	  border-radius: 2px;
	  font-size: 12px;
	  color: white;
	  margin-left: 30px;
	}
	.resetButton{
	  width: 90px;
	  height: 32px;
	  border: none;
	  border-radius: 2px;
	  font-size: 12px;
	  background-color: #e54e4e;
	  color: white;
	  margin-left: 10px;
	}
	.tb_box{
		width: 100%;
		height: 100%;
		background-color: white;
		overflow-y: scroll;
		overflow-x: scroll;
		margin: 0 auto
	}
	tr:nth-child(odd) {background-color: #f6f9fd;}
	tr{
	  width: 100%;
	  height: 40px;
	  text-align: left;
	  font-size: 16px;
	}
</style>
