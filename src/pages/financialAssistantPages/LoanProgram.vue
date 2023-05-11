<template>

  <div style="width: 80%;height: 100%;margin: 26px auto;">

    <div >
      <span>贷款金额：</span><input type="text" class="inputText" id="loan" v-model="amount">
      <span style="margin-left: 50px">贷款利率：</span><input type="text" class="inputText" id="rate" v-model="rate">
    </div>
    <div style="margin-top: 20px">
      <span>贷款期限：</span><input type="text" class="inputText" id='duration' v-model="duration">
      <span style="margin-left: 50px">贷款日期：</span>
      <el-date-picker
        v-model="start"
        type="date"
        placeholder="请选择日期"
        style="width: 18%;height: 20px;margin-left: 10px;margin-right: 50px;"
    />
    </div>
    <div style="margin-top: 20px;display: flex;">
      <div style="text-align: right;width: 22%">还款方式：</div>
      <div style="width: 70%;text-align: left">
        <ul>
          <li>
            <input type="checkbox" class="checkedBox" name="way">任意本金，按频率付息，自第<input type="text" style="width: 20px;height: 20px;margin-left: 10px;margin-right: 10px">月起还本金的<input type="text" style="margin-left:10px;margin-right:10px;width: 40px;height: 20px">%
          </li>
          <li style="margin-top: 14px"><input type="checkbox" class="checkedBox" name="way">一次性到期还本付息</li>
          <li style="margin-top: 14px"><input type="checkbox" class="checkedBox" name="way">等额本息</li>
          <li style="margin-top: 14px"><input type="checkbox" class="checkedBox" name="way">按月付息，到期还本</li>
        </ul>

      </div>
    </div>
    <div style="margin-top: 40px;">
      <button >计算利息</button>
      <button style="background-color: #e24f4f;margin-left: 20px" @click="returnPlan">生成还款计划</button>
      <button style="background-color: #14bd4a;margin-left: 20px">导出word</button>
    </div>
    <div style="font-size: 14px;margin-top: 40px">
      贷款利息总额（元）：<span style="color: #e24f4f;font-size: 16px">{{balanceValue}}</span>
    </div>
    <div style="width: 96%;height: 100%;margin: 10px auto" v-if="canShow">
      <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
        <tr>
          <td style="text-align: center">还款时间</td>
          <td style="text-align: center">利息</td>
          <td style="text-align: center">本金</td>
          <td style="text-align: center">还款金额</td>
        </tr>
        <tr v-for="item in returnPlanData" style="width: 100%">
          <td style="text-align: center" v-for="item2 in item">{{item2}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";
import {getCurrentInstance} from 'vue'
const {proxy}= getCurrentInstance()
let canShow=ref(false)
const balanceValue=ref('')
const amount=ref('')
const rate=ref('')
const duration=ref('')
const start=ref('')
const methodOfRepayment=ref('')
const returnPlanData=ref([])
function returnPlan(){
  let way=document.getElementsByName('way')
  for(let i=0;i<way.length;i++)
  {
    if(way[i].checked)
    {
      methodOfRepayment.value=i+1
      break
    }
  }
  let url='/finance-helper/getInterestPlan/'+amount.value+'/'+rate.value+'/'+duration.value+'/'+'2022-01-01'+'/'+methodOfRepayment.value+''
  console.log(url)
  proxy.$api.post(url,{}).then(res=>{
    console.log(res.data.data)
    canShow.value=true
    balanceValue.value=res.data.data['贷款利息总额']
    returnPlanData.value=res.data.data['还款计划']
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
.inputText{
  width: 18%;
  height: 20px;
  margin-left: 10px;
  margin-right: 50px;
}
button{
  width: 90px;
  height: 32px;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  background-color: #4b83da;
  color: white;
}
ul,li{
  text-decoration: none;
  list-style:none;
  padding:0;margin:0;
}
.checkedBox:checked{
  border: 1px solid #1673ff;
}
.checkedBoxS:checked{
  background: #4b83da;
}

.checkedBoxS{
  width:20px;
  height:20px;
  margin:0;
  padding:0;
  border-radius: 4px;
  border:solid 1px #dddddd;
  background-color:#ffffff;
  /*border: none;*/
  position:relative;
  display:inline-block;
  vertical-align:top;
  cursor:default;
  -webkit-appearance:none;
  -webkit-user-select:none;
  user-select:none;
  -webkit-transition:background-color ease 0.1s;
  transition:background-color ease 0.1s;
  margin-right: 14px;
}
.checkedBox {
  width:20px;
  height:20px;
  background-color:#ffffff;
  border:solid 1px #dddddd;
  -webkit-border-radius:50%;
  border-radius:50%;
  font-size:0.8rem;
  margin:0;
  padding:0;
  position:relative;
  display:inline-block;
  vertical-align:top;
  cursor:default;
  -webkit-appearance:none;
  -webkit-user-select:none;
  user-select:none;
  -webkit-transition:background-color ease 0.1s;
  transition:background-color ease 0.1s;
  margin-right: 10px;
}

.checkedBox:checked::after {
  content:'';
  top:50%;
  left:50%;
  transform: translate(-50%,-50%);
  position:absolute;
  /*background:transparent;*/
  /*border:#1673ff solid 2px;*/
  border-top:none;
  border-right:none;
  height:10px;
  width:10px;
  border-radius: 5px;
  background-color: #1673ff;
  /*-moz-transform:rotate(-45deg);*/
  /*-ms-transform:rotate(-45deg);*/
  /*-webkit-transform:rotate(-45deg);*/
  /*transform:rotate(-45deg);*/
}
.checkedBoxS:checked::after {
  content:'';
  top:3px;
  left:3px;
  position:absolute;
  background:transparent;
  border:#fff solid 2px;
  border-top:none;
  border-right:none;
  height:6px;
  width:10px;
  -moz-transform:rotate(-45deg);
  -ms-transform:rotate(-45deg);
  -webkit-transform:rotate(-45deg);
  transform:rotate(-45deg);
}
</style>