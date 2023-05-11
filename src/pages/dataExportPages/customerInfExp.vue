<template>
  <div style="padding-bottom: 20px;width: 100%;background-color: white;border-radius: 0 0 6px 6px">
    <div style="height: 30px;width: 100%"></div>
    <div style="width:80%;margin: 0 auto">
      <span>客户名称：</span><input type="text" class="searchInput" v-model="searchName">
      <span>证件号码：</span><input type="text" class="searchInput" v-model="searchString">
    </div>
    <div style="margin-top: 40px">
      <button @click="searchCustomer">查询</button>
    </div>

    <div style="width: 96%;height: 100%;margin: 10px auto" v-if="canShow">
      <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
        <tr v-for="(item,index) in basicInf" style="width: 100%">
          <td style="width: 14%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index}}</td>
          <td style="width: 80%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{item}}</td>
        </tr>
      </table>
    </div>

<!--    <div v-if="canShow" style="width: 84%;height: 100%;background-color: rgba(0,0,0,0.2);position: fixed;top: 0%;"></div>-->
<!--    <div v-if="canShow" style="width: 80%;height: 600px;background-color: white;position:fixed;top: 48%;transform: translateY(-50%);border-radius: 10px;margin-left: 20px">-->
<!--      <el-icon style="position: absolute;top: 4%;right: 6%" @click="cancelShow"><Close /></el-icon>-->
<!--      <div style="text-align: left;margin-left: 20px;margin-top: 20px">信息查询结果</div>-->
<!--      -->
<!--    </div>-->
  </div>
</template>
<script setup>
import {getCurrentInstance, provide, ref} from 'vue'
import {Close} from '@element-plus/icons-vue'
// import { Avatar,Lock } from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const canShow=ref(false)
const searchString=ref('')
const searchName=ref('')
let basicInf={
  "客户名称": "李某1",
  "证件号码": "440301200002101101",
  "性别": "男",
  "行政村": "桂庙村",
  "街道办": "粤海街道",
  "行政区": "南山区",
  "联系地址": "深圳市xxx",
  "工作单位": "xxx股份有限公司1",
  "联系电话": "13530xxxxxx",
  "婚姻状况": "未婚",
  "所属网点": "龙华支行",
  "所属网点客户经理": "岳飞",
}
function cancelShow(){
  canShow.value=false
}
function searchCustomer(){
	console.log(searchString.value)
	sessionStorage.setItem("cliId",searchString.value)
// 	proxy.$api.get('/information-query/customer-information/client-info-byCliIdnum',{
// 	   "cli_idnum":searchString.value
// 	 }).then(res=>{
// console.log(res.data)
//     let data=res.data.data
//     basicInf['客户名称']=data.cliName
//     basicInf['证件号码']=data.cliIdnum
//     basicInf['性别']=data.cliGender
//     basicInf['行政村']=data.cliVillage
//     basicInf['街道办']=data.cliStreet
//     basicInf['行政区']=data.cliRegion
//     basicInf['联系地址']=data.cliAddress
//     basicInf['工作单位']=data.cliWorplace
//     basicInf['联系电话']=data.cliPhone
//     basicInf['婚姻状况']=data.cliMarriage
//     basicInf['所属网点']=data.cliSubbranch
//     basicInf['所属网点客户经理']=data.cliManager
//     canShow.value=true
// 	sessionStorage.setItem("cliId",searchString.value)
// 	 })
if(searchName.value){
	  //名字转为身份证
	  let url='/information-query/customer-information/findCliIdnumBycCliName/'+'?cli_name='+searchName.value
	  proxy.$api.get(url).then(res=>{
		  //成功获取到了信息
		  console.log(res)
		if(res.data.code==200){
			let data=res.data.data
			//查询该名字后发现系统中只有一个结果 即确定唯一 即可输出
			if(data.length==1){
				//身份证号
				var cliId= data[0].cliIdnum
				searchInfById(cliId)
			}
			//重复名字
			else{

			}
		}
		//没有获取到信息
		else{
			alert("没有查询到当前用户信息")
		}
	    
		})
}
  
}

function searchInfById(id){
	let url='/information-query/customer-information/client-info-byCliIdnum'+'?cli_idnum='+id
	proxy.$api.get(url).then((res)=>{
	  console.log(res.data)
	  let data=res.data.data
	  basicInf['客户名称']=data.cliName
	  basicInf['证件号码']=data.cliIdnum
	  basicInf['性别']=data.cliGender
	  basicInf['行政村']=data.cliVillage
	  basicInf['街道办']=data.cliStreet
	  basicInf['行政区']=data.cliRegion
	  basicInf['联系地址']=data.cliAddress
	  basicInf['工作单位']=data.cliWorplace
	  basicInf['联系电话']=data.cliPhone
	  basicInf['婚姻状况']=data.cliMarriage
	  basicInf['所属网点']=data.cliSubbranch
	  basicInf['所属网点客户经理']=data.cliManager
	  canShow.value=true
		sessionStorage.setItem("cliId",id)
	})
}

</script>
<style scoped>
tr:nth-child(odd) {background-color: #f6f9fd;}
tr{
  width: 100%;
  height: 40px;
  text-align:left;
  font-size: 16px;
}
.searchInput{
  width: 240px;
  height: 26px;
  border:1px solid gainsboro;
  border-radius: 4px;
  margin-left: 10px;
  margin-right: 30px;
}
</style>