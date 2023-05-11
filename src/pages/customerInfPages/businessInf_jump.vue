<template>
	<div class="mask" v-show="show"></div>
	
	<div style="margin: 20px 0 0 20px;">
		<div style="float: right; margin-right: 5%; margin-bottom: 10px;">
		<el-button type="primary"  @click="show = true">贷款发放信息</el-button>
		</div>
		<table style="width: 100%; height: 80%; border: 1px solid #e8ebef" class="tb_box3">
			<tr v-for="(item,index) in tableData">
				<td style="width: 30%; padding-left: 10px;">{{index}}</td>
				<td style="width: 70%;">{{item}}</td>
			</tr>
<!-- 			<tr>
				<td style="width: 30%; padding-left: 10px;"><a href="javascript:;" @click="show = true">...</a></td>
				<td style="width: 70%;"></td>
			</tr> -->
		</table>
	</div>
	<div class="alert_box" v-show="show">
		<div style="display: flex; justify-content: space-between; align-items: center; padding: 0 20px; border-bottom: 1px solid #e8ebef;">
			<div>
				<h4>贷款发放信息</h4>
			</div>
			<div>
				<img src="../../imag/errorgray.png" style="width: 15px; height: 15px;" @click="show = false">
			</div>
		</div>
		<div>
			<div style="width: 96%; margin: 30px auto; overflow-x: scroll; overflow-y: scroll;">
				<table style="width:100%;border: 1px solid #e5e7ea;border-spacing: 0;margin-bottom: 20px; position: relative; min-height:200px;" class="tb_box3">
					<tr style="position: sticky; top: 0; display: flex;">
						<td style="font-size: 14px;text-align: center;width: 140px; ">发放号</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">融资/票面金额</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">已还金额</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">还款计划</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">短期/长期</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">放款/开票日</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">贷款/票据到期日</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">贷款账号</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">欠款合计</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">逾期天数</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">自主支付金额</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">受托支付金额</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">受托支付收款人名称</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">受托支付收款人开户行</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">受托支付收款人账号</td>
						<td style="font-size: 14px;text-align: center;width: 140px; ">五级分类</td>
					</tr>
					<tr v-for="(item,index) in tableData2" style="display: flex;">
						<td style="font-size: 14px;text-align: center;width: 140px;">{{index+1}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["融资/票面金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["已还金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["还款计划"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["融资/票面金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["已还金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["还款计划"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["融资/票面金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["已还金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["还款计划"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["融资/票面金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["已还金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["还款计划"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["融资/票面金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["已还金额"]}}</td>
						<td style="font-size: 14px;text-align: center;width: 140px;">{{item["还款计划"]}}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {ref,inject,onMounted} from "vue"
	// const tableData=inject('businessInf1')
	// const tableData2=inject('businessInf2')
	const businessInfJump= JSON.parse(sessionStorage.getItem("businessInfJump"))
	console.log(businessInfJump)
	const show = ref(false)
	const tableData=
	{
	    "授信截止日期日期":'',
	    "借款人授信内容":'',
	    "合同编号":'',
	    "合同金额": '',
	    "贷款金额": ''
	}
	const tableData2=
	[{
	    "融资/票面金额": '',
	    "已还金额": '',
	    "还款计划": '',
	}]

		if(sessionStorage.getItem("businessInfJump")){
			let ddl = businessInfJump.conDDL[0]+'-'+businessInfJump.conDDL[1]+'-'+businessInfJump.conDDL[2]
			tableData["授信截止日期日期"] = ddl
			tableData["借款人授信内容"] = businessInfJump.conCreditCon
			tableData["合同编号"] = businessInfJump.conId
			tableData["合同金额"] = businessInfJump.conAmount
			tableData["贷款金额"] = businessInfJump.loanamount
			
			tableData2[0]["融资/票面金额"] = businessInfJump.conAmount
			tableData2[0]["已还金额"] = businessInfJump.conReturn
			tableData2[0]["还款计划"] = businessInfJump.conReturnPlan
			let tableData3 = Object.values(tableData2)
		}

</script>

<style scoped>
	a{
		text-decoration: none;
	}
	.tb_box3>tr:nth-child(odd) {background-color: #f6f9fd;}
	.tb_box3>tr{
	  width: 100%;
	  height: 36px;
	  line-height: 36px;
	  text-align:left;
	  font-size: 16px;
	}
	.mask {
	  position: fixed;
	  top: 0;
	  height: 100%;
	  width: 100%;
	  background-color: rgba(167, 165, 165, 0.486);
	  opacity: 0.5;
	  z-index: 9;
	}
	.alert_box{
		position: absolute;
		right: 50%;
		margin-right: -35%;
		top: 50%;
		margin-top: -20%;
		width: 60%;
		height: 50%;
		background-color: white;
		z-index: 10;
		padding-top: 10px;
	}

</style>
