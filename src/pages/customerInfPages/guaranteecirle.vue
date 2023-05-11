<template>
	<div style="width: 96%; height: 33%; margin: 20px 0 0 20px; display: flex; justify-content: space-between; align-items: center;">
		<div v-for="item in data2" class="circle_box" @click="jump(item.type)">
			<div style="height: 28%; width: 100%;background: rgba(132,150,171,0.1);color: #32323A;">
				<div style="display: flex; justify-content: space-between; align-items: center;padding: 5px 10px 0 10px;">
					<span style="font-size: 16px; font-family: Source Han Sans CN, Source Han Sans CN-Bold;font-weight: 700;color: #32323a;">{{item.type}}</span>
					<a href="javascript:;" style="color: #000000;">></a>
				</div>
			</div>
			
			<div style="padding:10px 0 0 10px;">
				<div style="width: 98%; height: 90%; margin: 0 auto;">
					<table style="font-size: 14px;color: #32323a;font-family: Source Han Sans CN, Source Han Sans CN-Regular; text-align: left;">
						<tr>
							<td>组数: {{item.groupNum}}</td>
						</tr>
						<tr>
							<td>人数: {{item.memberNum}}</td>
						</tr>
						<tr>
							<td >金额: <span style="color: red;">{{item.price}}</span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
<!-- 	<div style="width: 100%; height: 40%;">
		<table style="border: 1px solid #e5e7ea;border-spacing: 0; width: 100%; margin-top: 20px;" class="circle_detail">
			<tr v-for="(item,index) in circleData" style="width: 100%">
			  <td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index}}</td>
			  <td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{item}}</td>
			</tr>
		</table>
	</div> -->
</template>

<script setup>
	import {ref,getCurrentInstance,inject} from "vue";
	const {proxy} = getCurrentInstance()
	const data = ["AB互保","ABC担保圈","ABCD担保圈","5P及以上担保圈"]
	 const data1 = inject("relatedBusiness")
	const tableData = ref([])
	let data2 =  [
			{
				"type": "ab互保",
				"groupNum": '',
				"memberNum": '',
				"price": ''
			},
			{
				"type": "abc担保",
				"groupNum":'' ,
				"memberNum":'' ,
				"price": ''
			},
			{
				"type": "abcd担保",
				"groupNum": '',
				"memberNum": '',
				"price": ''
			},
			{
				"type": "p5担保",
				"groupNum":'' ,
				"memberNum": '',
				"price":'' 
			}
		]
	if(sessionStorage.getItem("guarateeCircleSubInfos")){
		 data2 = JSON.parse(sessionStorage.getItem("guarateeCircleSubInfos"))
	}
	

	let circleData = {
		"所属网点":'',
		"客户经理":'',
		"业务种类":'',
		"合同编号":'',
		"客户名称":'',
		"合同金额":'',
		"贷款余额":'',
		"贷款起始日期":'',
		"贷款到期日期":'',
		"贷款期限":'',
		"贷款利率":'',
	}
	function jump(type){
		// let url = "/information-query/customer-information/guarantorCircletype-info-byGuaranteeType"+"?guaranteeType="+type
		// // proxy.$api.get("/information-query/customer-information/guarantorCircletype-info-byGuaranteeType",{
		// // 	"guaranteeType":type
		// // }).then((res)=>{
		// // 	console.log(res)
		// // })
		// proxy.$api.get(url).then((res)=>{
		// 	console.log(res)
		// 	console.log(1)
		// 	sessionStorage.setItem("circleTypeInfo",JSON.stringify(res.data.data))
		// 	// sessionStorage.setItem("circleTypeInfo",test)
			
		// })
		sessionStorage.setItem("circleType",JSON.stringify(type))
		setTimeout(()=>{
			proxy.$router.push({
				name:"circleJump1"
			})
		},100)
		
	}
</script>

<style scoped>
	.circle_box{
		width: 20%;
		height: 90%;
		background: rgba(253,253,253,0.4);
		border: 1px solid #8496ab;
		border: 1px solid #e5e7e9;
		border-radius: 10px;
		cursor: pointer;
	}
	a{
		text-decoration: none;
	}
	tr{
	  width: 100%;
	  height: 28px;
	  text-align:left;
	  font-size: 16px;
	}
</style>
