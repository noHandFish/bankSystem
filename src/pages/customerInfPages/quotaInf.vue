<template>
  <div style="height: 20px"></div>

  <div class="mask" v-show="show"></div>
  <div class="img_alert" v-show="show">
  	<div style="border-bottom: 1px solid  black; text-align: left;padding: 10px 0 40px 10px; width: 100%;height: 5%; box-sizing: border-box;">
  		<!-- {{img_titile}}:{{index}} -->
		房产权属人及其房产信息{{index}}
  	</div>
	<div style="position: absolute; right: 10px; top: 10px;">
		<img src="../../imag/errorgray.png" style="width: 20px; height: 20px;" @click="show = false" >
	</div>
	<div style="width: 80%;height: 60%; margin: 20px auto; border: 1px solid black;display: flex; justify-content: center; align-items: center;">
		<div>
			<img :src="currentImg" style="width: 100%; height: 100%;">
		</div>
		
	</div>
	<div style="margin: 10px auto;">
		<el-button @click="preImg">上一页</el-button>
		 <el-button type="primary" @click="nextImg">下一页</el-button>
	</div>
  </div>
  <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;" class="tb_box5">
   <!-- <tr v-for="(item,index) in tableData" style="width: 100%">
      <td :class="index=='房产权属人及其房产信息1'||index=='房产权属人及其房产信息2'||index=='房产权属人及其房产信息3'||index=='房产权属人及其房产信息4'||index=='房产权属人及其房产信息5'?
	  'img_box':''" 
	  @click="handleImg(index,i)"
	  style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index}}</td>
      <td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px" >{{item}}</td>

    </tr> -->
	<tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">身份证</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{tableData.身份证}}</td>
	</tr>
	<tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">借款人身份</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{tableData.借款人身份}}</td>
	</tr>
	<tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">是否有分红或工作单位</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{tableData.是否有分红或工作单位}}</td>
	</tr>
	<tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产数量</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{tableData.房产数量}}</td>
	</tr>
	<tr>
		<td class="img_box" @click="handleImg(1)" style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产权属人及其房产信息1</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr>
	<!-- <tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产1权属人与借款人关系</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr> -->
	<tr>
		<td class="img_box" @click="handleImg(2)" style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产权属人及其房产信息2</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr>
<!-- 	<tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产2权属人与借款人关系</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr> -->
	<tr>
		<td class="img_box" @click="handleImg(3)" style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产权属人及其房产信息3</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr>
	<!-- <tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产3权属人与借款人关系</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr> -->
	<tr>
		<td class="img_box" @click="handleImg(4)" style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产权属人及其房产信息4</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr>
	<!-- <tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产4权属人与借款人关系</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr> -->
	<tr>
		<td class="img_box" @click="handleImg(5)" style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产权属人及其房产信息5</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr>
	<!-- <tr>
		<td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">房产5权属人与借款人关系</td>
		<td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px"></td>
	</tr> -->
  </table>
</template>

<script setup>


import {inject,ref} from "vue";
let tableData=inject('LoanInf')
console.log(tableData)
let imgList = []
const index = ref(1)
const currentImg = ref()
if(sessionStorage.getItem("quotaInfImg")){
	 imgList=JSON.parse(sessionStorage.getItem("quotaInfImg"))
	delete imgList.cliIdnum
	
	console.log(imgList)
}


const show = ref(false)
const img_titile = ref("房产权属人及其房产信息1")
var str = ["房产权属人及其房产信息1","房产权属人及其房产信息2","房产权属人及其房产信息3","房产权属人及其房产信息4","房产权属人及其房产信息5"]
function handleImg(x){
	// index.value =  Number(x.replace(/[^\d]/g, ""))
	index.value = Number(x)
	let currentImgName = "loanRela"+index.value+"Info" 
	console.log(currentImgName)
	currentImg.value = 'http://120.24.235.36:8089/images'+imgList[currentImgName]
	console.log(imgList[currentImgName])
	console.log(currentImg.value)
	if(x){
		img_titile.value = "房产权属人及其房产信息"+x
		show.value = true
	}
}
function preImg(){
	if(index.value>1&&index.value<=5){
		index.value -=1
		let currentImgName = "loanRela"+index.value+"Info"
		currentImg.value = 'http://120.24.235.36:8089/images'+imgList[currentImgName]
	}
	else if(index.value==1){
		index.value = 5
		let currentImgName = "loanRela"+index.value+"Info"
		currentImg.value = 'http://120.24.235.36:8089/images'+imgList[currentImgName]
	}
	
}
function nextImg(){
	if(index.value>=1&&index.value<5){
		index.value +=1
		let currentImgName = "loanRela"+index.value+"Info"
		currentImg.value = 'http://120.24.235.36:8089/images'+imgList[currentImgName]
	}
	else if(index.value==5){
		index.value = 1
		let currentImgName = "loanRela"+index.value+"Info"
		currentImg.value = 'http://120.24.235.36:8089/images'+imgList[currentImgName]
	}
	
}
</script>

<style type="text/css" scoped>
	tr:nth-child(odd) {background-color: #f6f9fd;}
	tr{
	  width: 100%;
	  height: 40px;
	  text-align:left;
	  font-size: 16px;
	}
	.img_box:hover{
		background-color: #5f83f5;
		color: white;
		cursor:pointer;
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
	.img_alert{
		position: absolute;
		width: 40%;
		height: 50%;
		background-color: white;
		z-index: 10;
		left:50%;
		top:50%;
		transform:translate(-50%,-50%);
	}
</style>

