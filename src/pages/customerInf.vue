<template>
  <div style="width: 100%;height: 100%;margin: 0 auto">
    <div style="width: 100%;height: 10%;background-color: white;margin-top: 20px;border-radius: 6px">
        <div style="height: 32%;width: 100%"></div>
        <div style="font-size: 14px;text-align: left;margin-left: 20px">
          <span>证件号码 / 客户名称</span>
          <input placeholder="请输入证件号码/客户名称" class="searchInput" v-model="searchString">
          <button class="searchButton" @click="beginSearch">查询</button>
          <button class="resetButton" @click="resetSearch">重置</button>
        </div>
    </div>
    <div style="width: 100%;height:80%;background-color: white;border-radius: 6px;margin-top: 20px">
      <div style="border-bottom: 2px solid ghostwhite;height: 8%;width: 100%;">

        <div style="width: 50%;display: flex;justify-content: space-between;margin-left: 20px;font-size: 14px;line-height: 400%;height: 100%">
          <router-link @click="changeInfType" id="基本信息" :class="infType=='基本信息'?'changeInfType':''" to="/home/customerInf/basicInf">基本信息</router-link>
          <router-link @click="changeInfType" id="业务信息" :class="infType=='业务信息'?'changeInfType':''" to="/home/customerInf/businessInf">业务信息</router-link>
          <router-link @click="changeInfType" id="额度信息" :class="infType=='额度信息'?'changeInfType':''" to="/home/customerInf/quotaInf">额度依据</router-link>
		  <router-link @click="changeInfType" id="关联业务" :class="infType=='关联业务'?'changeInfType':''" to="/home/customerInf/relatedBusiness">关联业务</router-link>
		  <router-link @click="changeInfType" id="担保信息" :class="infType=='担保信息'?'changeInfType':''" to="/home/customerInf/guaranteeInf">对外担保</router-link>
		  <router-link @click="changeInfType" id="担保圈" :class="infType=='担保圈'?'changeInfType':''" to="/home/customerInf/guaranteecirle">担保圈</router-link>
          <router-link @click="changeInfType" id="附加信息" :class="infType=='附加信息'?'changeInfType':''" to="/home/customerInf/additionalInf">附加信息</router-link>
        </div>
      </div>
      <div style="width: 100%;height: 85%;color: grey;overflow-y: auto;margin: 0 auto;">
        <router-view></router-view>
      </div>
	  
	 <!-- 检查重复名字 -->
	  <div id="checkNameBox" v-if="canShow">
		  <div id="">
		  	<h4>本次查询的客户姓名出现重复，在下列选项中选择您想查询的用户</h4>
		  </div>
	  	<div style="width: 100%;">
	  		<table style="border: 1px solid #e5e7ea;border-spacing: 0; width: 100%; margin-top: 20px;">
	  			<tr v-for="(item,index) in sameNameList" >
	  				<input type="checkbox" style="zoom:140%;margin: 8px 0 0 4px" v-model="sameNameCheck" @change="changeId(item.cliIdnum)">
	  				<td>{{index+1}} : </td>
	  				<td>客户姓名:{{item.cliName}}</td>
	  				<td>身份证:{{item.cliIdnum}}</td>
	  			</tr>
	  		</table>
	  	</div>
		<div id="" style="margin: 20px 0 20px 0;">
			<button class="searchButton" @click="checkName">确认</button>
			</div>
		</div>
    </div>




  </div>
</template>
<script  setup>
import {getCurrentInstance, provide, ref,watch,} from 'vue'
import { onBeforeRouteLeave, onBeforeRouteUpdate } from 'vue-router'




import qs from "qs"	

const {proxy}= getCurrentInstance()
const searchString=ref('')
const canShow = ref(false)
const cliIdNum = ref()
let sameNameList = ref([])
let sameNameCheck = ref(false)
let checkId = ref()
let basicInf=ref({
  "客户名称": "",
  "证件号码": "",
  "性别": "",
  "行政村": "",
  "街道办": "",
  "行政区": "",
  "联系地址": "",
  "工作单位": "",
  "联系电话": "",
  "婚姻状况": "",
  "所属网点": "",
  "所属网点客户经理": "",
})

// let relatedBusiness={
// 	"业务种类":'',
// 	"合同金额":'',
// 	"贷款余额":'',
// 	"贷款起始日期":'',
// 	"贷款到期日期":'',
// 	"贷款":''
// }
let guaranteeGener = {
	"主担保限额":'',
	"主担保合计":'',
	"辅助担保合计":'',
}
let outGuarantee={
	"被担保人名称":'',
	"业务种类":'',
	"合同金额":'',
	"贷款余额":'',
	"贷款起始日期":'',
	"贷款到期日期":'',
	"贷款":''
}
// let businessInf={
// 	"业务种类":'',
// 	"合同编号":'',
// 	"合同金额":'',
// }
let businessInf=[]
let businessInf1={
	"授信截止日期日期":'',
	"借款人授信内容":'',
	"合同编号":'',
	"合同金额":'',
	"贷款金额":'',

}
let businessInf2={
	"融资/票面金额":'',
	"已还金额":'',
	"还款计划":''
}


let LoanInf={
  '身份证':'',
  '借款人身份':'',
  '是否有分红或工作单位':'',
  '房产数量':'',
  '房产权属人及其房产信息1':'',
  '房产1权属人与借款人关系':'',
  '房产权属人及其房产信息2':'',
  '房产2权属人与借款人关系':'',
  '房产权属人及其房产信息3':'',
  '房产3权属人与借款人关系':'',
  '房产权属人及其房产信息4':'',
  '房产4权属人与借款人关系':'',
  '房产权属人及其房产信息5':'',
  '房产5权属人与借款人关系':'',

}

let guaranteeInf= {
  '合同编号': '',
  '主保证人1姓名': '',
  '主保证人1身份证号码': '',
  '主保证人1与借款人关系': '',
  '主保证人2姓名': '',
  '主保证人2身份证号码': '',
  '主保证人2与借款人关系': '',
  '辅助担保人1 ': '',
  '辅助担保人1身份证号码': '',
  '辅助担保人1是否直系亲属关系': '',
  '辅助担保人2': '',
  '辅助担保人2身份证号码': '',
  '辅助担保人2是否直系亲属关系': '',
  '辅助担保人3': '',
  '辅助担保人3身份证号码': '',
  '辅助担保人3是否直系亲属关系': '',
}
let additionalInf={
  '经营主体名称':'',
  '营业执照号':'',
  '借款人身份':'',
  '企业性质':'',
  '行政区':'',
  '街道办':'',
  '行政村':'',
  '家庭年收入':'',
}
 let relatedBusiness = [
		{
			"业务种类":'',
			"合同金额":'',
			"贷款余额":'',
			"贷款起始日期":'',
			"贷款到期日期":'',
			"贷款":''
		},
		{
			"业务种类":'',
			"合同金额":'',
			"贷款余额":'',
			"贷款起始日期":'',
			"贷款到期日期":'',
			"贷款":''
		},
 ]
let guarateeCircleSubInfos= [{
				type: 'AB互保',
				groupNum: 0,
				memberNum: 0,
				price: 0
			},
			{
				type: 'ABC担保圈',
				groupNum: 0,
				memberNum: 0,
				price: 0
			},
			{
				type: 'ABCD担保圈',
				groupNum: 0,
				memberNum: 0,
				price: 0
			},
			{
				type: '5P及以上担保圈',
				groupNum: 0,
				memberNum: 0,
				price: 0
			}]
let	guarateeCircleInfos = [
				[
					// []
				],
				
				[
					// []
				],
				
				[
					// []
				],
				
				[
					// []
				],
			]

const infType=ref('')



onBeforeRouteUpdate((from,to)=>{

	if(to.path==='/home/customerInf/relatedBusiness'&&from.path=='/home/customerInf'){
		const newId = sessionStorage.getItem("id")
		searchString.value = newId
		beginSearch(newId)
		infType.value = '基本信息'
	}
	
	if(to.path==='/home/customerInf/guaranteeInf/inGuarantee'&&from.path=='/home/customerInf'){
		const newId = sessionStorage.getItem("idGuarantee")
		searchString.value = newId
		beginSearch(newId)
		infType.value = '基本信息'
	}
})

function changeId(id){
	 checkId.value = id
}
function changeInfType(e){
  infType.value=e.currentTarget.id
}
function resetSearch()
{
  searchString.value=''
}

function  beginSearch()
{
  // 18位为身份证号 直接开始查询 应该用正则检验！！！
  if(searchString.value.length==18){
	  writeInfo(searchString.value)
  }
  else{
	  //名字转为身份证
	  let url='/information-query/customer-information/findCliIdnumBycCliName/'+'?cli_name='+searchString.value
	  proxy.$api.get(url).then(res=>{
		  //成功获取到了信息
		if(res.data.code==200){
			let data=res.data.data
			//查询该名字后发现系统中只有一个结果 即确定唯一 即可输出

			if(data.length==1){

				//身份证号
				var cliId= data[0].cliIdnum
				cliIdNum.value = data[0].cliIdnum
				writeInfo(cliId)
			}
			//重复名字
			else{
				sameNameList.value = data
				canShow.value = true
			}
		}
		//没有获取到信息 
		else{
			alert("没有查询到当前用户信息")
		}
	    
		})
  }

  

}
	
function writeInfo(cliId){
	//基本信息
	let data = ""
	let urlBasic='/information-query/customer-information/client-info-byCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(urlBasic).then(res=>{
		if(res.data.code==200){
			data = res.data.data
			changeBasicInfo(data)
		}
	})
	//业务信息
	let urlBusiness='/information-query/customer-information/bussiness-info-byCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(urlBusiness).then(res=>{
		console.log(res)
		if(res.data.code==200){
			let temptData = []
		    data = res.data.data
			if(data["企业贷业务列表"].length>0){
			  temptData = data["企业贷业务列表"]
			  
			 for(let i = 0 ;i <temptData.length;i++){
				 console.log()
			 	temptData[i].busType = "企业贷"
			 }
			}
			else if(data["个人贷业务列表"].length>0){
				 temptData = data["个人贷业务列表"]
				 console.log(temptData)
				for(let i = 0 ;i <temptData.length;i++){
					
					temptData[i].busType = "个人贷"
				}
			}
			else if(data["社区贷业务列表"].length>0){
				 temptData = data["社区贷业务列表"]
				for(let i = 0 ;i <temptData.length;i++){
					temptData[i].busType = "社区贷"
				}
			}
				
			//重复查询 会有bug
			for(let i = 0 ;i <temptData.length;i++){
					if(businessInf[0]){
						if(temptData[i].conId==businessInf[0].conId){
							break
						}
					}
					//清空上次查询的数据
					if(businessInf[0]){
						businessInf.splice(0,businessInf.length)
					}
					businessInf.push(temptData[i])
					console.log(businessInf,11)
			}
			console.log(businessInf)
			changeBusinessInfo(temptData)
		}

		})
	
	//担保圈
		let urlcircle='/information-query/customer-information/guarantor-circle-byCliIdnum'+'?cli_idnum='+cliId
		proxy.$api.get(urlcircle).then(res=>{
			if(res.data.code==200){
				data = res.data.data
				console.log(data)
				for(let i = 0; i < data.length; i++) {
					if(data[i][0].guaranteeType === 'ab互保') {
						guarateeCircleSubInfos[0].groupNum += 1
						guarateeCircleSubInfos[0].memberNum += data[i].length
						guarateeCircleInfos[0].push(data[i])
						for(let j = 0; j < data[i].length; j++) {
							guarateeCircleSubInfos[0].price += data[i][j].cliBalance
						}
					}
					else if(data[i][0].guaranteeType === 'abc担保') {
						guarateeCircleSubInfos[1].groupNum += 1
						guarateeCircleSubInfos[1].memberNum += data[i].length
						guarateeCircleInfos[1].push(data[i])
						for(let j = 0; j < data[i].length; j++) {
							guarateeCircleSubInfos[1].price += data[i][j].cliBalance
						}
					}
					else if(data[i][0].guaranteeType === 'abcd担保') {
						guarateeCircleSubInfos[2].groupNum += 1
						guarateeCircleSubInfos[2].memberNum += data[i].length
						guarateeCircleInfos[2].push(data[i])
						for(let j = 0; j < data[i].length; j++) {
							guarateeCircleSubInfos[2].price += data[i][j].cliBalance
						}
					}
					else if(data[i][0].guaranteeType === 'p5担保') {
						guarateeCircleSubInfos[3].groupNum += 1
						guarateeCircleSubInfos[3].memberNum += data[i].length
						guarateeCircleInfos[3].push(data[i])
						for(let j = 0; j < data[i].length; j++) {
							guarateeCircleSubInfos[3].price += data[i][j].cliBalance
						}
					}
				}
				console.log(guarateeCircleSubInfos)
				console.log(guarateeCircleInfos)
				sessionStorage.setItem("guarateeCircleSubInfos",JSON.stringify(guarateeCircleSubInfos))
				sessionStorage.setItem("guarateeCircleInfos",JSON.stringify(guarateeCircleInfos))
			}
		
			})
	//关联业务
	let urlRelatedBusiness='/information-query/customer-information/associatebusiness-info-byCliName'+'?'+'cli_name=李某0'
	proxy.$api.get(urlRelatedBusiness).then(res=>{
		if(res.data.code==200){
			data = res.data.data
			changeRelatedInf(data)
			sessionStorage.setItem("relatedBusiness",JSON.stringify(data))
			}
			
		})
	//额度信息
	let urlLoan='/information-query/customer-information/loan-info-byCliIdnum'+'?loan_idnum='+cliId
	proxy.$api.get(urlLoan).then(res=>{
		if(res.data.code==200){
			data = res.data.data
			changeLoanInfo(data)
		}

		})
	//额度房产信息
	let urlLoanImg='/information-query/image-information/get-Loan-image'
	proxy.$api.post(urlLoanImg,{
    "id":"440301xxxxxxxx1101",
    "choose":[]
}).then(res=>{
		if(res.data.code==200){
		let	data = res.data.data
			sessionStorage.setItem("quotaInfImg",JSON.stringify(data))
		}
	
		})
	//担保信息
	let urlGur='/information-query/customer-information/guaranted-info-byCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(urlGur).then(res=>{
		if(res.data.code==200){
			data = res.data.data[0]
			console.log(data)
			changeguaranteeInfo(data)
		}

		})
	//附加信息
	let urlAdd='/information-query/customer-information/client-info-more-byCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(urlAdd).then(res=>{
		if(res.data.code==200){
			data = res.data.data
			changeadditionalInfo(data)
		}

		})
	//对外担保-正在担保
	let urlin='/information-query/customer-information/guarantedpluson-info-byCliIdnum'+'?cli_idnum='+cliId
	proxy.$api.get(urlin).then(res=>{
		if(res.data.code==200){
			data = res.data.data
			sessionStorage.setItem("inGuarantee",JSON.stringify(data))
		}
	
		})
	//对外担保-结束担保
	let urlout='/information-query/customer-information/guarantedplusoff-info-byCliIdnum'+'?cli_idnum='+'440301xxxxxxxx1102'
	proxy.$api.get(urlout).then(res=>{
		if(res.data.code==200){
			console.log(res)
			data = res.data.data
			sessionStorage.setItem("outGuarantee",JSON.stringify(data))
		}
	
		})
}
function checkName(){
	canShow.value = false
	writeInfo(checkId.value)
}

 function changeBasicInfo(data){
	 basicInf.value['客户名称']=data.cliName
	 basicInf.value['证件号码']=data.cliIdnum
	 basicInf.value['性别']=data.cliGender
	 basicInf.value['行政村']=data.cliVillage
	 basicInf.value['街道办']=data.cliStreet
	 basicInf.value['行政区']=data.cliRegion
	 basicInf.value['联系地址']=data.cliAddress
	 basicInf.value['工作单位']=data.cliWorplace
	 basicInf.value['联系电话']=data.cliPhone
	 basicInf.value['婚姻状况']=data.cliMarriage
	 basicInf.value['所属网点']=data.cliSubbranch
	 basicInf.value['所属网点客户经理']=data.cliManager
	 console.log(basicInf)
		// 数据改变后使用了切换页面进行渲染
	 setTimeout(()=>{
		 proxy.$router.push({
		 	    name:'basicInf',
		 })
		 infType.value = '基本信息'
		 console.log(1)
	 },100)
	
 }

 function changeRelatedInf(data){
	 // console.log( relatedBusiness[0]["业务种类"])
	 // relatedBusiness[count]["业务种类"]=data.bussType
	 // relatedBusiness[count]["合同金额"]=data.conAmount
	 // relatedBusiness[count]["合同编号"]=data.conId
	 // relatedBusiness[count]["贷款余额"]=data.cliBalance
	 // relatedBusiness[count]["贷款起始日期"]=data.conStart
	 // relatedBusiness[count]["贷款截止日期"]=data.conEnd
	 // relatedBusiness[count]["贷款"]=data.conRate
	 
	 // 需要按照不同页面进行分开
	 
	 // outGuarantee["业务种类"]=data.conType
	 // outGuarantee["合同金额"]=data.conAmount
	 // outGuarantee["被担保人名称"]=data.cliName
	 // outGuarantee["贷款余额"]=data.cliBalance
	 // outGuarantee["贷款起始日期"]=data.conStart
	 // outGuarantee["贷款截止日期"]=data.conEnd
	 // outGuarantee["贷款"]=data.conRate
 }

 function changeBusinessInfo(data){
	 // console.log(data)
	 // 某些字段不明
	// businessInf["业务种类"]=data.conType
	// businessInf["合同金额"]=data.conAmount
	// businessInf["合同编号"]=data.conId
	
	// businessInf1["合同金额"]=data.conAmount //重复 
	// businessInf1["合同编号"]=data.conId
	// businessInf1["授信截止日期日期"]=data.conEnd
	// businessInf1["借款人授信内容"]=data.con_creditcon
	// businessInf1["贷款金额"]=""
	// businessInf2["融资/票面金额"]=data.conAmount//重复 
	// businessInf2["已还金额"]=data.conReturn
	// businessInf2["还款计划"]=data.conReturnPlan

 }

 function changeLoanInfo(data){
    LoanInf['身份证']=cliIdNum.value
    LoanInf['借款人身份']=data.loanIdentify
    LoanInf['是否有分红或工作单位']=data.loanIsBonus
    LoanInf['房产数量']=data.loanProperty
    LoanInf['房产权属人及其房产信息1']=data.loanRela1Info
    LoanInf['房产1权属人与借款人关系']=data.loanRela1
    LoanInf['房产权属人及其房产信息2']=data.loanRela2Info
    LoanInf['房产2权属人与借款人关系']=data.loanRela2
    LoanInf['房产权属人及其房产信息3']=data.loanRela3Info
    LoanInf['房产3权属人与借款人关系']=data.loanRela3
    LoanInf['房产权属人及其房产信息4']=data.loanRela4Info
    LoanInf['房产4权属人与借款人关系']=data.loanRela4
    LoanInf['房产权属人及其房产信息5']=data.loanRela5Info
    LoanInf['房产5权属人与借款人关系']=data.loanRela5	 
 }
 function changeadditionalInfo(data){
	additionalInf['经营主体名称']=data.enName
    additionalInf['营业执照号']=data.enIdnum
    additionalInf['借款人身份']=data.enIdentify
    additionalInf['企业性质']=data.enStatus
    additionalInf['行政区']=data.enRegion
    additionalInf['街道办']=data.enStreet
    additionalInf['行政村']=data.enVillage
    additionalInf['家庭年收入']=data.enAnnualIncome
 }
 function changeguaranteeInfo(data){
    // guaranteeInf['合同编号']=data.guanrantorInfo.conId
    // guaranteeInf['主保证人1姓名']=data.guanrantorInfo.gua1Name
    // guaranteeInf['主保证人1身份证号码']=data.guanrantorInfo.gua1Idnum
    // guaranteeInf['主保证人1与借款人关系']=data.guanrantorInfo.gua1Ship
    // guaranteeInf['主保证人2姓名']=data.guanrantorInfo.gua2Name
    // guaranteeInf['主保证人2身份证号码']=data.guanrantorInfo.gua2Idnum
    // guaranteeInf['主保证人2与借款人关系']=data.guanrantorInfo.gau2Ship
    // guaranteeInf['辅助担保人1 ']=data.guanrantorInfo.sub1Name
    // guaranteeInf['辅助担保人1身份证号码']=data.guanrantorInfo.sub1Idnum
    // guaranteeInf['辅助担保人1是否直系亲属关系']=data.guanrantorInfo.sub1IsRela
    // guaranteeInf['辅助担保人2']=data.guanrantorInfo.sub2Name
    // guaranteeInf['辅助担保人2身份证号码']=data.guanrantorInfo.sub2Idnum
    // guaranteeInf['辅助担保人2是否直系亲属关系']=data.guanrantorInfo.sub2IsRela
    // guaranteeInf['辅助担保人3']=data.guanrantorInfo.sub3Name
    // guaranteeInf['辅助担保人3身份证号码']=data.guanrantorInfo.sub3Idnum
    // guaranteeInf['辅助担保人3是否直系亲属关系']=data.guanrantorInfo.sub3IsRela 
	// guaranteeInf["业务种类"]=
	// guaranteeInf["合同金额"]=data.conAmount
	// guaranteeInf["被担保人名称"]=""
	// guaranteeInf["贷款余额"]=""
	// guaranteeInf["贷款起始日期"]=data.gua1Start
	// guaranteeInf["贷款到期日期"]=data.gua1End
	guaranteeGener["主担保限额"] = data.guaTotalAmount
	guaranteeGener["主担保合计"] = data.sumGuaAmount
	guaranteeGener["辅助担保合计"] = data.sumSubAmount
	console.log(guaranteeGener)
 }


provide('basicInf',basicInf)
provide('guaranteeGener',guaranteeGener)
provide('relatedBusiness',relatedBusiness)
provide('businessInf',businessInf)
provide('businessInf1',businessInf1)
provide('businessInf2',businessInf2)
provide('LoanInf',LoanInf)
provide('guaranteeInf',guaranteeInf)
provide('additionalInf',additionalInf)
provide('outGuarantee',outGuarantee)
</script>

<style scoped>

.changeInfType{
  height: 100%;
  color: #4b83da;
  border-bottom: 2px solid #4b83da
}
a,router-link{
  text-decoration: none;
  color: #2a2525;
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
.searchInput{
  padding-left: 10px ;
  width: 260px;
  height: 30px;
  border:1px solid gainsboro;
  border-radius: 4px;
  margin-left: 20px;
}
#checkNameBox{
	background-color: white;
	border: 1px solid black;
	border-radius: 10px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	position: fixed;
	top: 30%;
	left: 25%;
}
</style>

