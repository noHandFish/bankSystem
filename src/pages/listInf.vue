<template>

  <div style="width: 96%;margin: 20px auto;background-color: white;border-radius: 6px; padding-bottom: 24px">
    <div style="height: 20px"></div>
    <div style="font-size: 14px;display: flex;justify-content: space-around;width: 100%;">
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div>
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_balances">放贷余额日：
          <input type="text" style="width: 100px" class="inputText" v-model="con_balances_left">
          <span>至</span>
          <input type="text" class="inputText" v-model="con_balances_right">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_end">贷款到期日：
          <!--          <input type="text" class="inputText" style="margin-left: -4px">-->
          <el-date-picker
              v-model="con_end_left"
              type="date"
              placeholder="开始"
              style="width: 110px;margin:0 10px 0 10px;"
              :size="size"
          />
          <span>至</span>
          <!--          <input type="text" class="inputText">-->
          <el-date-picker
              v-model="con_end_right"
              type="date"
              placeholder="结束"
              style="width: 108px;margin-left: 10px;"
              :size="size"
          />
        </div>

        <div style="margin: 10px 0 0 30px;">
          所属支行：
          <select class="listSelect" v-model="cli_subbranch" style="margin-left: 20px; width: 254px">
            <!--            //有个bug-->
            <option>龙岗支行</option>
            <option>新安支行</option>
            <option>龙城支行</option>
            <option>宝龙支行</option>
            <option>龙华支行</option>
            <option>坪地支行</option>
            <option>总行营业部</option>
            <option>全部</option>
          </select>
        </div>
        <div style="margin: 10px 0 0 30px;">
          夫妻贷款：
          <select class="listSelect" v-model="coupleType" style="margin-left: 20px; width: 254px">
            <option>空</option>
            <option>是</option>
            <option>否</option>
          </select>
        </div>
      </div>
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div>
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_amount">贷款余额：
          <input type="text" class="inputText" v-model="con_amount_left">
          <span>至</span>
          <input type="text" class="inputText" v-model="con_amount_right">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_rate">贷款利率：
          <input type="text" class="inputText" v-model="con_rate_left">
          <span>至</span>
          <input type="text" class="inputText" v-model="con_rate_right">
        </div>
        <div style="margin: 10px 0 0 29px;">
          客户经理：
          <select class="listSelect" v-model="cli_manager" style="width: 254px">
            <option>全部</option>
          </select>
        </div>
      </div>
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div>
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_start">放贷日期：
          <el-date-picker
              v-model="con_start_left"
              type="date"
              placeholder="开始"
              style="width: 108px;margin:0 10px;"
              :size="size"
          />
          <span>至</span>
          <el-date-picker
              v-model="con_start_right"
              type="date"
              placeholder="结束"
              style="width: 108px;margin-left: 10px;"
              :size="size"
          />
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;" v-model="con_duration">贷款期限：
          <!-- <el-date-picker
              v-model="con_duration_left"
              type="date"
              placeholder="开始"
              style="width: 100px;margin-right: 10px;"
              :size="size"
          />
          <span>至</span>
          <el-date-picker
              v-model="con_duration_right"
              type="date"
              placeholder="结束"
              style="width: 100px;margin-left: 10px;"
              :size="size"
          /> -->
          <input type="text" class="inputText" v-model="con_duration_left">
          <span>至</span>
          <input type="text" class="inputText" v-model="con_duration_right">
        </div>
        <div style="margin: 10px 0 0 10px;">
          AB互保贷款：
          <select class="listSelect" v-model="abType" style="width: 252px">
            <option>空</option>
            <option>是</option>
            <option>否</option>
          </select>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <button class="searchButton" @click="searchClientByList">查询</button>
      <button class="resetButton" @click="resetInf">重置</button>
    </div>
  </div>
  <div style="height: 60%;width: 96%;background-color: white;margin: 20px auto;border-radius: 6px;text-align: center">
    <div class="tb_box" style="">
      <table style="width:100%;border: 1px solid #e5e7ea;border-spacing: 0;margin-bottom: 20px; position: relative;">
        <tr style="position: sticky; top: 0;">
          <td style="font-size: 14px;text-align: center;width: 120px">客户号</td>
          <td style="font-size: 14px;text-align: center;width: 120px">客户名称</td>
          <td style="font-size: 14px;text-align: center;width: 120px">证件号码</td>
          <td style="font-size: 14px;text-align: center;width: 120px">证配偶姓</td>
          <td style="font-size: 14px;text-align: center;width: 120px">借款人账号</td>
          <td style="font-size: 14px;text-align: center;width: 120px">账户存款</td>
          <td style="font-size: 14px;text-align: center;width: 120px">所属支行</td>
          <td style="font-size: 14px;text-align: center;width: 120px">客户经理</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款合同号</td>
          <td style="font-size: 14px;text-align: center;width: 120px">授信额度</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款余额</td>
          <td style="font-size: 14px;text-align: center;width: 120px">放贷日期</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款到期日</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款期限</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款利率</td>
          <td style="font-size: 14px;text-align: center;width: 120px">贷款类型</td>
          <td style="font-size: 14px;text-align: center;width: 120px">担保人1</td>
          <td style="font-size: 14px;text-align: center;width: 120px">担保人2</td>
          <td style="font-size: 14px;text-align: center;width: 120px">辅助担保人1</td>
          <td style="font-size: 14px;text-align: center;width: 120px">辅助担保人2</td>
          <td style="font-size: 14px;text-align: center;width: 120px">辅助担保人3</td>
        </tr>

        <tr v-for="item in dataDemo">
          <td v-for="item2 in item"
              style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 12px; width:120px;">
            {{ item2 }}
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script setup>
import {ref} from 'vue'
import {getCurrentInstance} from 'vue'

const {proxy} = getCurrentInstance()
// const value1 = ref('')
//需要增加一个未选中禁用输入框的功能
const con_balances = ref(true) //放贷余额
const con_balances_left = ref(0)
const con_balances_right = ref(0)

const con_amount = ref(false) //贷款余额
const con_amount_left = ref(0)
const con_amount_right = ref(0)

const con_start = ref(false)//放贷日期
const con_start_left = ref('')
const con_start_right = ref('')

const con_end = ref(false)//货款到期日
const con_end_left = ref('')
const con_end_right = ref('')

const con_rate = ref(false)//贷款利率
const con_rate_left = ref(0)
const con_rate_right = ref(0)

const con_duration = ref(false)
const con_duration_left = ref('')
const con_duration_right = ref('')

const cli_subbranch = ref('总行营业部')
const cli_manager = ref('全部')
const abType = ref('空')
const coupleType = ref('空')
let dataDemo = ref([])

function resetInf() {
  con_balances.value = false
  con_balances_left.value = 0
  con_balances_right.value = 0

  con_amount.value = false
  con_amount_left.value = 0
  con_amount_right.value = 0

  con_start.value = false
  con_start_left.value = ''
  con_start_right.value = ''

  con_end.value = false
  con_end_left.value = ''
  con_end_right.value = ''

  con_rate.value = false
  con_rate_left.value = 0
  con_rate_right.value = 0

  con_duration.value = false
  con_duration_left.value = ''
  con_duration_right.value = ''

  cli_subbranch.value = '本行'
  cli_manager.value = '全部'
  abType.value = ''
  coupleType.value = ''
  dataDemo.value = []
}

function searchClientByList() {
  let url = {
    "con_balanced": con_balances.value,
    "con_balanced_left": con_balances_left.value,
    "con_balanced_right": con_balances_right.value,
    "con_amount": con_amount.value,
    "con_amount_left": con_amount_left.value,
    "con_amount_right": con_amount_right.value,
    "con_start": con_start.value,
    "con_start_left": con_start_left.value,
    "con_start_right": con_start_right.value,
    "con_end": con_end.value,
    "con_end_left": con_end_left.value,
    "con_end_right": con_end_right.value,
    "con_rate": con_rate.value,
    "con_rate_left": con_rate_left.value,
    "con_rate_right": con_rate_right.value,
    "con_duration": con_duration.value,
    "con_duration_left": con_duration_left.value,
    "con_duration_right": con_duration_right.value,
    "cli_subbranch": cli_subbranch.value,
    "cli_manager": cli_manager.value,
    "abType": abType.value,
    "coupleType": coupleType.value
  }
  console.log(url)
  proxy.$api.post('/information-query/list-information/getListInformation', {
    "con_balanced": con_balances.value,
    "con_balanced_left": con_balances_left.value,
    "con_balanced_right": con_balances_right.value,
    "con_amount": con_amount.value,
    "con_amount_left": con_amount_left.value,
    "con_amount_right": con_amount_right.value,
    "con_start": con_start.value,
    "con_start_left": con_start_left.value,
    "con_start_right": con_start_right.value,
    "con_end": con_end.value,
    "con_end_left": con_end_left.value,
    "con_end_right": con_end_right.value,
    "con_rate": con_rate.value,
    "con_rate_left": con_rate_left.value,
    "con_rate_right": con_rate_right.value,
    "con_duration": con_duration.value,
    "con_duration_left": con_duration_left.value,
    "con_duration_right": con_duration_right.value,
    "cli_subbranch": cli_subbranch.value,
    "cli_manager": cli_manager.value,
    "abType": abType.value,
    "coupleType": coupleType.value
    // "con_balanced":true,
    //        "con_balanced_left":1000000,
    //        "con_balanced_right":2000000,
    //        "con_amount":true,
    //        "con_amount_left":1000000,
    //        "con_amount_right":2000000,
    //        "con_start":true,
    //        "con_start_left":"2022-01-15",
    //        "con_start_right":"2022-01-19",
    //        "con_end":true,
    //        "con_end_left":"2025-01-15",
    //        "con_end_right":"2025-01-19",
    //        "con_rate":true,
    //        "con_rate_left":6,
    //        "con_rate_right":7.34,
    //        "con_duration":true,
    //        "con_duration_left":34,
    //        "con_duration_right":40,
    //        "cli_subbranch":"总行营业部",
    //        "cli_manager":"全部",
    //        "abType":"空",
    //        "coupleType":"空"
  }).then(res => {
    console.log(res)
    // 获取数据
    if (res.data.code == 200) {
      dataDemo.value = res.data.data
      for (let i = 0; i < dataDemo.value.length; i++) {
        dataDemo.value[i].conStart = dataDemo.value[i].conStart[0] + '-' + dataDemo.value[i].conStart[1] + '-' + dataDemo.value[i].conStart[2]
        dataDemo.value[i].conEnd = dataDemo.value[i].conEnd[0] + '-' + dataDemo.value[i].conEnd[1] + '-' + dataDemo.value[i].conEnd[2]
      }
    } else {
      alert("没有查询到相关信息")
      dataDemo.value = []
    }

  })
}

// const dataDemo=[
//   {
//     "客户号":'001',
//     "客户名称":'张三',
//     "证件号码":'441892300004638237',
//     "贷款合同":'000000000000001',
//     "贷款金额":'289090',
//     "贷款期限":'2022-10-09',
//     "贷款利率":'3.24%',
//     "贷款类型":'01',
//     "所属支行":'龙岗支行',
//     "客户经理":'张柳',
//   },
//   {
//     "客户号":'001',
//     "客户名称":'张三',
//     "证件号码":'447283499902736273',
//     "贷款合同":'000000000000002',
//     "贷款金额":'289090',
//     "贷款期限":'2022-11-03',
//     "贷款利率":'3.24%',
//     "贷款类型":'01',
//     "所属支行":'龙岗支行',
//     "客户经理":'张柳',
//   }
// ]
</script>
<style scoped>
.inputText {
  width: 100px;
  height: 25px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
  margin-right: 10px;
  padding-left: 5px;

}

.listSelect {
  width: 246px;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
}

.searchButton {
  width: 90px;
  height: 32px;
  background-color: #4b83da;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  color: white;
  margin-left: 30px;
}

.resetButton {
  width: 90px;
  height: 32px;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  background-color: #e54e4e;
  color: white;
  margin-left: 10px;
}

.tb_box {
  width: 100%;
  height: 100%;
  background-color: white;
  overflow-y: scroll;
  overflow-x: scroll;
  margin: 0 auto
}

tr:nth-child(odd) {
  background-color: #f6f9fd;
}

tr {
  display: flex;
  height: 26px;
  line-height: 26px;
  text-align: left;
  font-size: 16px;
}

</style>



