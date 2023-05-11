<template>
  <div style="width: 98%;margin: 20px auto;background-color: white;border-radius: 6px">
    <div style="height: 20px"></div>
    <div style="font-size: 14px;display: flex;justify-content: space-around;width: 100%;">
      <div style="width: 38%;padding-left: 10px;text-align: left">
        <div >
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="gua_total_balance">担保余额：
          <input type="text" class="inputText" v-model="gua_total_balance_left" style="margin-left: 12px">
          <span>至</span>
          <input type="text" class="inputText" v-model="gua_total_balance_right">
        </div>
        <div style="margin-top: 20px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_end">担保到期日：
          <el-date-picker
              v-model="con_end_left"
              type="date"
              placeholder="开始"
              style="width: 105px;margin-right: 10px;"
              :size="size"
          />
          <span>至</span>
          <el-date-picker
              v-model="con_end_right"
              type="date"
              placeholder="结束"
              style="width: 105px;margin-left: 10px;"
              :size="size"
          />
        </div>

        <div style="margin-top: 20px;margin-left: 43px">
          所属支行：
          <select class="listSelect" v-model="cli_subbranch" style="margin-left: -1px">
            <option>总行营业部</option>
          </select>
        </div>

      </div>
      <div style="width: 38%;padding-left: 10px;text-align: left">
        <div>
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="gua_total_amount">担保金额：
          <input type="text" class="inputText" v-model="gua_total_amount_left">
          <span>至</span>
          <input type="text" class="inputText" v-model="gua_total_amount_right">
        </div>
		<div style="margin-top: 20px;">
		  <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_start">担保时间：
		  <el-date-picker
		      v-model="con_start_left"
		      type="date"
		      placeholder="开始"
		      style="width: 106px;margin-right: 10px;margin-left: 10px"
		      :size="size"
		  />
		  <span>至</span>
		  <el-date-picker
		      v-model="con_start_right"
		      type="date"
		      placeholder="结束"
		      style="width: 106px;margin-left: 10px;"
		      :size="size"
		  />
		</div>
        <div style="margin-top: 20px;margin-left: 30px">
          客户经理：
          <input type="text" class="inputText" style="width: 64%;padding-left: 5px;" v-model="cli_manager">
        </div>

      </div>
      <div style="width: 26%;padding-left: 10px;text-align: left">


        <div style="">
          AB互保贷款：
          <select class="listSelect" style="margin-left: -8px" v-model="abType">
            <option>空</option>
			<option>是</option>
			<option>否</option>
          </select>
        </div>
		<div style="margin-top: 20px;">
		  ABC担保圈：
		  <select class="listSelect" style="margin-left: -4px" v-model="abcType">
		    <option>空</option>
			<option>是</option>
			<option>否</option>
		  </select>
		</div>
		<div style="margin-top: 20px; ">
		 ABCD担保圈：
		  <select class="listSelect" style="margin-left: -8px" v-model="abcdType">
		    <option>空</option>
			<option>是</option>
			<option>否</option>
		  </select>
		</div>
      </div>
    </div>
    <div style="margin-top: 20px;padding-bottom: 20px;height: 490px;">
      <button class="searchButton" @click="searchGuarantee" style="margin-bottom: 20px">查询</button>
<!--      <button class="resetButton">重置</button>-->
      <div class="tb_box"  v-if="canShow">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;margin-bottom: 20px" class="trend_box" id="trend_table">
          <tr>
            <td style="font-size: 14px;text-align: center; width:180px;">担保人名称</td>
            <td style="font-size: 14px;text-align: center; width:180px;">证件号码</td>
            <td style="font-size: 14px;text-align: center; width:180px;">担保方式</td>
            <td style="font-size: 14px;text-align: center; width:180px;">担保余额（元）</td>
            <td style="font-size: 14px;text-align: center; width:180px;">担保金额（元）</td>
            <td style="font-size: 14px;text-align: center; width:180px;">担保时间</td>
            <td style="font-size: 14px;text-align: center; width:180px;">担保到期日</td>
            <td style="font-size: 14px;text-align: center; width:180px;">贷款合同号</td>
            <td style="font-size: 14px;text-align: center; width:180px;">与借款人关系</td>
          </tr>
          <tr v-for="item in dataDemo">
            <td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.cliName}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.gua1Idnum}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.bussType}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.conBalance}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.conAmount}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.conDuration}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.conEnd}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.conId}}</td>
			<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px; width:180px;">{{item.gua1Ship}}</td>
          </tr>
        </table>
        <button class="searchButton" @click="downloadGuarantee" style="margin-bottom: 20px;background-color: #3dba6e">下载</button>
      </div>

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
import {ref} from 'vue'
import {getCurrentInstance} from 'vue'
import * as XLSX from 'xlsx'
import {Close} from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const dataDemo=ref(true)
const gua_total_balance=ref(false)
const gua_total_balance_left=ref('')
const gua_total_balance_right=ref('')
const gua_total_amount=ref(false)
const gua_total_amount_left=ref('')
const gua_total_amount_right=ref('')
const con_start=ref(false)
const con_start_left=ref('')
const con_start_right=ref('')
const con_end=ref(false)
const con_end_left=ref('')
const con_end_right=ref('')
const con_duration=ref(false)
const con_duration_left=ref('')
const con_duration_right=ref('')
const cli_subbranch=ref('总行营业部')
const cli_manager=ref('全部')
const abType=ref('空')
const abcType=ref('空')
const abcdType=ref('空')
const canShow=ref(false)

function cancelShow(){
  canShow.value=false
}
function searchGuarantee(){
	let data ={
    "gua_total_balance_left":gua_total_balance_left.value,
    "gua_total_balance_right":gua_total_balance_right.value,
    "gua_total_amount_left":gua_total_amount_left.value,
    "gua_total_amount_right":gua_total_amount_right.value,
    "con_start_left":con_start_left.value,
    "con_start_right":con_start_right.value,
    "con_end_left":con_end_left.value,
    "con_end_right":con_end_right.value,
        "bussType":"全部",
        "cli_subbranch":"全行",
        "cli_manager":"全部",
        "abType":abType.value,
        "abcType":abcType.value,
        "abcdType":abcdType.value,
        "p5Type":"空",
        "coupleType":"空",
        "isLoaning": true
	}
  proxy.$api.post('/data-analysis/guarantee-analysis/getListInformation',data).then((res)=>{
	if(res.data.code==200){
		let data = res.data.data
		console.log(data)
		dataDemo.value=data
		canShow.value=true
	}

  })
}
function downloadGuarantee() {
  let excelData = dataDemo.value.map((item) => {
    return [item.gua1Name, item.gua1Idnum, item.bussType, item.conBalance, item.conAmount, item.conDuration, item.conEnd, item.conId, item.gua1Ship]
  })
  let title = ["担保人名称", "证件号码", "担保方式", "担保余额（元）", "担保金额（元）", "担保时间", "担保到期日", "贷款合同号", "与借款人关系"]
  excelData.unshift(title)
  // 创建一个工作簿
  const workbook = XLSX.utils.book_new()
  // 创建一个工作表
  const worksheet = XLSX.utils.aoa_to_sheet(excelData)
  // 将工作表添加到工作簿中
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1')
  //转换成二进制流
  const excelBlob = XLSX.write(workbook, { bookType: "xlsx", type: "array" })
  //转成blob对象
  const blob = new Blob([excelBlob], { type: "application/octet-stream"})
  //创造链接
  const link = document.createElement('a')
  link.href = URL.createObjectURL(blob)
  link.download = '担保分析.xlsx'
  link.click()
}
</script>

<style scoped>

.inputText{
  width: 100px;
  height: 25px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
  margin-right: 10px;
}
.listSelect {
  width: 140px;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
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
.tb_box{
	width: 100%;
	height: 100%;
	background-color: white;
	overflow-y: scroll;
	overflow-x: scroll;
	margin: 0 auto
}
.trend_box>tr:nth-child(odd) {background-color: #f6f9fd;}
.trend_box>tr{
  width: 100%;
  height: 40px;
  text-align:left;
  font-size: 16px;
}
</style>
