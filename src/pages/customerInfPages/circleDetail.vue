<template>
	<div style="position: absolute; top: 3%; left: 2%;">
<!--		<img src="../../imag/pre.png" @click="prePage"  style="width: 20px; height: 20px;cursor: pointer;">-->
	</div>
	<div style="height: 20px;margin-top: 2%;"></div>
	<table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;" class="tb_box4">
	 <!-- <tr v-for="(item,index) in businessInf1" style="width: 100%">
	    <td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index}}</td>
	    <td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{item}}</td>
	  </tr> -->
	  <tr style="position: sticky; top: 0; display: flex;">
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">所属网点</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">客户经理</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">业务种类</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">合同编号</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">客户名称</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">合同金额</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">贷款余额</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">贷款起始日期</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">贷款到期日期</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">贷款期限</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">贷款利率</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">主保证人名称</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">辅助保证人1姓名</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">辅助保证人2姓名</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">辅助保证人3姓名</td>
	  	<td style="font-size: 14px;text-align: center;width: 140px; ">辅助保证人4姓名</td>
	  </tr>
	  <tr style="display: flex;" v-for="(item,index) in tableData">
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.cliSubbranch}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.cliManager}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conBussType}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conId}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.loanName}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conAmount}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.cliBalance}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conStart}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conEnd}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conDuration}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.conRate}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.guaName}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.sub1Name}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.sub2Name}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.sub3Name}}</td>
		  <td style="font-size: 14px;text-align: center;width: 140px; ">{{item.sub4Name}}</td>
	  </tr>
	</table>
</template>

<script setup>
	import {getCurrentInstance} from 'vue'
	const {proxy} = getCurrentInstance()
	const data = JSON.parse(sessionStorage.getItem("circleDetailInfo"))
	let tableData = []
	console.log(data)
	if(data){
		if(tableData[0]){
			tableData.splice(0,tableData.length)
		}
		for(let i = 0;i<data.length;i++){
			for(let j = 0;j<data[i].length;j++){
				data[i][j].conStart = data[i][j].conStart[0]+'-'+data[i][j].conStart[1]+'-'+data[i][j].conStart[2]
				data[i][j].conEnd = data[i][j].conEnd[0]+'-'+data[i][j].conEnd[1]+'-'+data[i][j].conEnd[2]
				// tableData[(i*data.length)+j]=data[i][j]
				tableData.push(data[i][j])
			}
		}
	}
	function prePage(){
		proxy.$router.back();
	}


	let businessInf1={
		"所属网点":'龙华支行',
		"客户经理":'张三',
		"业务种类":'经营贷',
		"合同编号":'DYC01010102',
		"客户名称":'李四',
		"合同金额":'12000',
		"贷款余额":'10000',
		"贷款起始日期":'2022-1-6',
		"贷款到期日期":'2022-2-6',
		"贷款期限":'30',
		"贷款利率":'6.00',
	}
</script>

<style scoped>
	.tb_box4>tr:nth-child(odd) {background-color: #f6f9fd;}
	.tb_box4>tr{
	  width: 100%;
	  height: 40px;
	  text-align:left;
	  font-size: 16px;
	}
	.tb_box4>tr>td{
		line-height: 40px;
	}
</style>
