<template>
	<div style="position: absolute; top: 3%; left: 2%;">
<!--		<img src="../../imag/pre.png" @click="prePage"  style="width: 20px; height: 20px; cursor: pointer;">-->
	</div>
	<div style="margin: 0 auto;">
		<h3>{{type}}</h3>
	</div>
<div style="width: 100%; overflow-y: auto; display:flex; flex-wrap: wrap;">
	<div v-if="data.length==0" style="margin: 0 auto;color: red;">
		<h1 >无数据</h1>
	</div>
	<div class="circle_box" v-for="item in data" >
		<a href="javascript:;" @click="jumpToNext(item.loanName)">
			<div class="top_box">
				<div style="font-size: 18px; font-family: Source Han Sans CN, Source Han Sans CN-Regular;">
					<div v-for="(item1,index1) in item">
						<p>借款人{{index1+1}}名称:{{item1.loanName}}</p>
						<p>贷款余额:{{item1.cliBalance}}</p>
					</div>
				</div>
			</div>
		</a>
	</div>
</div>
</template>

<script setup>
	import {getCurrentInstance} from 'vue'
	let typeNum = 0
	const {proxy} = getCurrentInstance()
	const type = JSON.parse(sessionStorage.getItem("circleType"))
	if(type =="AB互保") {
		typeNum = 0
	}
	else if(type =="ABC担保圈")
	 {
		typeNum = 1
	 }
	else if(type =="ABCD担保圈")
	{
		typeNum = 2
	}
	else if(type =="5P及以上担保圈")
	{
		typeNum = 3
	}

		const data = JSON.parse(sessionStorage.getItem("guarateeCircleInfos"))[typeNum]





	// const data = sessionStorage.getItem("circleTypeInfo")


	function jumpToNext(name){
		// let url = "/information-query/customer-information/guarantor-circle-byCliName?"+"cli_name="+name
		// proxy.$api.get(url).then((res)=>{
		// 	console.log(res)

		// 	// sessionStorage.setItem("circleDetailInfo",test)
		// })
		sessionStorage.setItem("circleDetailInfo",JSON.stringify(data))
		setTimeout(()=>{
			proxy.$router.push({
				    name:'circleDetail',
			})
		},200)

	}
	function prePage(){
		proxy.$router.back();
	}


</script>

<style scoped>

	.circle_box{
		width: 23%;
		height: auto;
		margin: 20px 0 0 20px;
		padding-bottom: 10px;
		border: 1px solid #E5E7E9;
		border-radius: 6px;
		padding-top: 10px;
		box-sizing: border-box;
	}
	.top_box{
		width: 90%;
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color:  #4b83da;
		margin: 0 auto;
		border-radius: 3px;
		text-align: left;

		padding: 10px 0 0 10px ;
	}
	.top_box div p{
		color: white;
		line-height: 25px;
		font-size: 15px;
	}
	a{
		text-decoration: none;
	}
</style>
