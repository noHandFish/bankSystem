<template>
	<div style="width: 100%; display: flex; flex-wrap: wrap;">
		<div style="margin: 20px 0 0 20px; width: 22%;" v-for="(item,index) in business" @click="beginSearch(index)">
			<div style="text-align: left;">
				<input type="checkbox" /><span>属于集团用户</span>
			
			</div>
			<table style="width: 100%;border-spacing: 0;padding: 10px;background-color: #5384fd; border-radius: 5px;" class="td_box">
				<tr>
					<td style="width: 70%;border-bottom: 1px solid white;padding-bottom: 8px;">客户名称：{{cliNameList[index]}}</td>
					<td style="width: 30%;border-bottom: 1px solid white;"> <a href="">详情></a></td>
				</tr>
				<tr v-for="(item2,index2) in item">
					<td style="width: 90%;font-size: 13px;padding-top: 12px;" colspan="2">{{index2}}: {{item2}} </td>
				</tr>
			</table>
		</div>
		
	</div>
</template>
	
<script setup>
import {ref,getCurrentInstance,inject,watch,onMounted} from "vue"
const {proxy} = getCurrentInstance()
let cliNameList = []
 let business = [
		{
			"业务种类":'',
			"合同金额":'',
			"贷款余额":'',
			"贷款起始日期":'',
			"贷款到期日期":'',
			"贷款":''
		},

 ]

 if(sessionStorage.getItem("relatedBusiness")){
	 business = JSON.parse(sessionStorage.getItem("relatedBusiness")) 
	 console.log(business)
	 for(let i = 0;i<business.length;i++){
		 cliNameList.push(business[i].cliName)
		 delete business[i].cliName
		  delete business[i].cliIdnum
	 }
	 //换成中文
	 business = JSON.parse(JSON.stringify(business).replace(/bussType/g,"业务种类").replace(/cliBalance/g,"贷款余额")
												   .replace(/conAmount/g,"合同金额").replace(/conEnd/g,"贷款到期日期")
												   .replace(/conRate/g,"贷款").replace(/conStart/g,"贷款起始日期"))


	 console.log(cliNameList)
 }

const cliName = inject("basicInf")['客户名称']
// onMounted(()=>{
// 	const business = inject("relatedBusiness")
// 	console.log(business)
// })
function beginSearch(i){
	proxy.$router.push("/home/customerInf")
	sessionStorage.setItem('id',cliNameList[i])
}

// watch(() => business, (e) => {
//   console.log(e)
// }, { deep: true })




</script>

<style scoped>
.td_box>tr>td{
		color: white;
		text-align: left;
	}
	a{
		font-size: 12px;
	  text-align: center;
	  text-decoration: none;
	  color: #2a2525;
	}
</style>
