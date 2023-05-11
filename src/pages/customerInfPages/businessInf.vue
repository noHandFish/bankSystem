<template>
  <div style="height: 20px"></div>

<div style="width: 100%; overflow-y: auto; height: 100%; display:flex; flex-wrap: wrap;">
	<div class="business_box" v-for="item in tableData">
		<a href="javascript:;" @click="jumpToNext(conId)">
			<div class="top_box">
				<div style="font-size: 16px; font-family: Source Han Sans CN, Source Han Sans CN-Regular;">
					<p>业务种类:{{item.busType }}</p>
					<p>合同编号:{{item.conId}}</p>
					<p>合同金额{{item.conAmount}}</p>
				</div>
				
			</div>
			<div class="bottom_box">
				<div style="display: flex; justify-content: center; align-items: center; margin: 0 auto;">
					<div style="width: 22px; height: 22px; border-radius: 100%; background-color: #FD6362; display: flex; justify-content: center; align-items: center;">
						<img src="../../imag/errorWhite.png" style="width: 12px;height: 12px; ">
					</div>
					<div style="color: #FD6362;font-size: 18px;font-family: Source Han Sans CN, Source Han Sans CN-Regular;font-weight: 400;margin-left: 5px;">未结清</div>
				</div>
			</div>
		</a>
	</div>
</div>

</template>

<script setup>
import {inject,getCurrentInstance} from "vue";
const {proxy}= getCurrentInstance()
let tableData=inject('businessInf')

function jumpToNext(conId){
	let url = "/information-query/customer-information/Contractinfo-info-byConID"+"?con_id="+conId
	// proxy.$api.get("/information-query/customer-information/Contractinfo-info-byConID",{
	// 	"con_id":"DYC202201013"
	// }).then(res=>{
	// 	console.log(res)
	// })
	console.log(url)
	proxy.$api.get(url).then(res=>{
		if(res.data.code==200){
			sessionStorage.setItem("businessInfJump",JSON.stringify(res.data.data))
		}
		
	})
	proxy.$router.push({
		    name:'businessInf_jump',
	})
}



</script>


<style type="text/css" scoped>
	*{
		padding: 0;
		margin: 0;
	}
	.business_box{
		width: 23%;
		height: 220px;
		margin: 20px 0 0 20px;
		border: 1px solid #E5E7E9;
		border-radius: 6px;
		padding-top: 10px;
		box-sizing: border-box;
	}
	.top_box{
		width: 90%;
		height: 70%;
		box-sizing: border-box;
		background-color: #FD6362;
		margin: 0 auto;
		border-radius: 3px;
		text-align: left;
		
		padding: 10px 0 0 10px ;
	}
	.top_box div p{
		color: white;
		line-height: 40px;
		font-size: 13px;
	}
	.bottom_box{
		width: 90%;
		height: 20%;
		margin: 20px auto;
	}
	a{
		text-decoration: none;
	}
</style>

