<template>
  <div style="height: 100%;width: 100%;background-color: white;border-radius: 0 0 6px 6px;font-size: 14px;text-align: left">
    <div style="height: 24px;width: 100%"></div>
    <div style="margin-left: 20px">贷款额度信息</div>
    <div style="width: 96%;margin: 20px auto;background-color: white;border-radius: 6px;text-align: left" v-if="canShow">
      <!-- <div style="float: right;width: 8%;height: 122px;border-radius: 0 5px 5px 0;border: 1px solid gainsboro;box-sizing: border-box;text-align: center">更多</div> -->
      <table style="width: 92%;border: 1px solid #e5e7ea;border-spacing: 0;">
        <tr >
          <td style="font-size: 14px;text-align: center;width: 25%;">房产权属人及其房产信息</td>
          <td style="font-size: 14px;text-align: center;width: 25%;">房产权属人与借款者关系</td>
          <td style="font-size: 14px;text-align: center;width: 25%;">房产权属人及其房产信息</td>
          <td style="font-size: 14px;text-align: center;width: 25%;">房产权属人与借款者关系</td>
        </tr>
        <tr v-for="item in dataDemo">
          <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px">
            {{item2}}
          </td>
        </tr>
      </table>

    </div>
<!-- 	<div style="position: absolute; right: 10%;" >
		<button class="uploadButton" style="float: right;margin-top: -5px;background-color: #3dba6e;font-size: 12px" @click="downLoad">下载图片</button>
	</div> -->
    <div style="margin-left: 20px;margin-top: 40px">担保人信息</div>
    <div style="width: 96%;margin: 20px auto;background-color: white;border-radius: 6px;text-align: left" v-if="canShow">
<!--      <div style="float: right;width: 8%;height: 162px;border-radius: 0 5px 5px 0;border: 1px solid gainsboro;box-sizing: border-box;text-align: center">更多</div> -->
      <table style="width: 92%;border: 1px solid #e5e7ea;border-spacing: 0;">
        <tr >
          <td style="font-size: 14px;text-align: center">主保证人1姓名</td>
          <td style="font-size: 14px;text-align: center">身份证号码</td>
          <td style="font-size: 14px;text-align: center">与借款人关系</td>
          <td style="font-size: 14px;text-align: center">主担保人1总担保额度</td>
          <td style="font-size: 14px;text-align: center">主保证人2姓名</td>
          <td style="font-size: 14px;text-align: center">身份证号码</td>
        </tr>
        <tr v-for="item in guarantorInf">
          <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px">
            {{item2}}
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script setup>

import {inject, onMounted} from "vue";
import {getCurrentInstance, provide, ref,onActivated} from 'vue'
// import { Avatar,Lock } from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const canShow=ref(false)
let cliId=inject('cliId')
const dataDemo=[{
  'roomAndProperty':'深圳市xx杨梅岗村78号',
  'relationship':'本人',
  'roomAndProperty2':'深圳市xx杨梅岗村79号',
  'relationship2':'本人'
},{
  'roomAndProperty':'深圳市xx杨梅岗村77号',
  'relationship':'本人',
  'roomAndProperty2':'深圳市xx杨梅岗村75号',
  'relationship2':'本人'
},{
  'roomAndProperty':'深圳市xx杨梅岗村73号',
  'relationship':'本人',
  'roomAndProperty2':'深圳市xx杨梅岗村73号',
  'relationship2':'本人'
},

]
// const dataDemo2=[
//   {
//     'name':'李某1',
//     'IDnumber':'440301200002101101',
//     'relationship':'朋友',
//     'value':'2000000',
//     'name2':'钟某2',
//     'IDnumber2':'440301200002101101',
//   },
//   {
//     'name':'李某1',
//     'IDnumber':'440301200002101101',
//     'relationship':'朋友',
//     'value':'2000000',
//     'name2':'钟某2',
//     'IDnumber2':'440301200002101101',
//   },
// ]

let LoanInf=
  // '身份证':'',
  // '借款人身份':'',
  // '是否有分红或工作单位':'',
  // '房产数量':'',
  // '房产权属人及其房产信息1':'',
  // '房产1权属人与借款人关系':'',
  // '房产权属人及其房产信息2':'',
  // '房产2权属人与借款人关系':'',
  // '房产权属人及其房产信息3':'',
  // '房产3权属人与借款人关系':'',
  // '房产权属人及其房产信息4':'',
  // '房产4权属人与借款人关系':'',
  // '房产权属人及其房产信息5':'',
  // '房产5权属人与借款人关系':'',
  [{
    'roomAndProperty':'',
    'relationship':'',
    'roomAndProperty2':'',
    'relationship2':''
  },{
    'roomAndProperty':'',
    'relationship':'',
    'roomAndProperty2':'',
    'relationship2':''
  },{
    'roomAndProperty':'',
    'relationship':'',
  }
  ]
let guarantorInf = [
  {
    'name':'张三',
    'IDnumber':'44212044244',
    'relationship':'兄弟',
    'value':'1000000',
    'name2':'李四',
    'IDnumber2':'4424029485',
  },
]
onMounted(()=>{
	getLoanData()
  // let url='/information-query/customer-information/loan-info-byCliIdnum'+'?loan_idnum='+cliId
  // console.log(cliId)
  // proxy.$api.get(url).then(res=>{
  //   console.log(res.data.data)
  //   let data=res.data.data
  //   // LoanInf['身份证']=data.loanIdnum
  //   // LoanInf['借款人身份']=data.loanIdentify
  //   // LoanInf['是否有分红或工作单位']=data.loanIsBonus
  //   // LoanInf['房产数量']=data.loanProperty
  //   LoanInf['房产权属人及其房产信息1']=data.loanRela1Info
  //   LoanInf['房产1权属人与借款人关系']=data.loanRela1
  //   LoanInf['房产权属人及其房产信息2']=data.loanRela2Info
  //   LoanInf['房产2权属人与借款人关系']=data.loanRela2
  //   LoanInf['房产权属人及其房产信息3']=data.loanRela3Info
  //   LoanInf['房产3权属人与借款人关系']=data.loanRela3
  //   LoanInf['房产权属人及其房产信息4']=data.loanRela4Info
  //   LoanInf['房产4权属人与借款人关系']=data.loanRela4
  //   LoanInf['房产权属人及其房产信息5']=data.loanRela5Info
  //   LoanInf['房产5权属人与借款人关系']=data.loanRela5
  // })
})
// setTimeout(getLoanData(),100)
onActivated(()=>{
	getLoanData()
})

function downLoad(){
	// proxy.$api.get('/data-export/credit-account/downloadByCliIdnum',{
	// 	"cli_idnum":440301200002101101,
	// }).then(res=>{
	// 	console.log(res)
	// })
	
	let url='/data-export/credit-account/downloadByCliIdnum'+'?cli_idnum=440301200002101101'
	proxy.$api.get(url,{}).then((res)=>{
		console.log(res)
	})
}

function getLoanData(){
	console.log(1)
	let cliId = sessionStorage.getItem("cliId")
	if(cliId){
// 		proxy.$api.get('/data-export/credit-account/getDataByCliIdnum',{
// 			   "cli_idnum":cliId
// 			 }).then(res=>{
// console.log(res.data.data)
// 	  let data=res.data.data
// 	  // LoanInf['身份证']=data.loanIdnum
// 	  // LoanInf['借款人身份']=data.loanIdentify
// 	  // LoanInf['是否有分红或工作单位']=data.loanIsBonus
// 	  // LoanInf['房产数量']=data.loanProperty
// 	  LoanInf[0].roomAndProperty=data[1]['房产权属人及其房产信息1']
// 	  LoanInf[0].relationship=data[1]['房产权属人与借款人关系1']
// 	  LoanInf[0].roomAndProperty2=data[1]['房产权属人及其房产信息2']
// 	  LoanInf[0].relationship2=data[1]['房产权属人与借款人关系2']
// 	  LoanInf[1].roomAndProperty=data[1]['房产权属人及其房产信息3']
// 	  LoanInf[1].relationship=data[1]['房产权属人与借款人关系3']
// 	  LoanInf[1].roomAndProperty2=data[1]['房产权属人及其房产信息4']
// 	  LoanInf[1].relationship2=data[1]['房产权属人与借款人关系4']
// 	  LoanInf[2].roomAndProperty=data[1]['房产权属人及其房产信息5']
// 	  LoanInf[2].relationship=data[1]['房产权属人与借款人关系5']
// 	  guarantorInf[0].name = data[2]['合同DYC202201001:担保人信息']['主保证人1姓名']
// 	  guarantorInf[0].IDnumber = data[2]['合同DYC202201001:担保人信息']['身份证号码1']
// 	  guarantorInf[0].relationship = data[2]['合同DYC202201001:担保人信息']['与借款人关系1']
// 	  guarantorInf[0].value= data[2]['合同DYC202201001:担保人信息']['主担保人1总担保额度']
// 	  guarantorInf[0].name2 = data[2]['合同DYC202201001:担保人信息']['主保证人2姓名']
// 	  guarantorInf[0].IDnumber2 = data[2]['合同DYC202201001:担保人信息']['身份证号码2']		
// 			 })		
let url='/data-export/credit-account/getDataByCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(url).then(res=>{
	  console.log(res.data.data)
	  let data=res.data.data
	  // LoanInf['身份证']=data.loanIdnum
	  // LoanInf['借款人身份']=data.loanIdentify
	  // LoanInf['是否有分红或工作单位']=data.loanIsBonus
	  // LoanInf['房产数量']=data.loanProperty
	  LoanInf[0].roomAndProperty=data[1]['房产权属人及其房产信息1']
	  LoanInf[0].relationship=data[1]['房产权属人与借款人关系1']
	  LoanInf[0].roomAndProperty2=data[1]['房产权属人及其房产信息2']
	  LoanInf[0].relationship2=data[1]['房产权属人与借款人关系2']
	  LoanInf[1].roomAndProperty=data[1]['房产权属人及其房产信息3']
	  LoanInf[1].relationship=data[1]['房产权属人与借款人关系3']
	  LoanInf[1].roomAndProperty2=data[1]['房产权属人及其房产信息4']
	  LoanInf[1].relationship2=data[1]['房产权属人与借款人关系4']
	  LoanInf[2].roomAndProperty=data[1]['房产权属人及其房产信息5']
	  LoanInf[2].relationship=data[1]['房产权属人与借款人关系5']
	  guarantorInf[0].name = data[2]['合同DYC202201001:担保人信息']['主保证人1姓名']
	  guarantorInf[0].IDnumber = data[2]['合同DYC202201001:担保人信息']['身份证号码1']
	  guarantorInf[0].relationship = data[2]['合同DYC202201001:担保人信息']['与借款人关系1']
	  guarantorInf[0].value= data[2]['合同DYC202201001:担保人信息']['主担保人1总担保额度']
	  guarantorInf[0].name2 = data[2]['合同DYC202201001:担保人信息']['主保证人2姓名']
	  guarantorInf[0].IDnumber2 = data[2]['合同DYC202201001:担保人信息']['身份证号码2']
	})
	setTimeout(()=>{
		canShow.value=true
	},100)
	}
	sessionStorage.clear()
}
</script>

<style>
tr:nth-child(odd) {background-color: #f6f9fd;}
tr{
  width: 100%;
  height: 40px;
  text-align:left;
  font-size: 16px;
}
</style>

		
