<template>

 <div class="bg" >

    <div class="line">
      <div style="border-radius: 5px;width: 35%;height: 100%;font-size: 14px">
        <div style="width: 100%;height: 47%;display: flex;">
          <div style="width: 50%;height: 100%;border-right:1px solid #eff0f4">
            <div style="height: 42%;width: 100%;background-color: #f7faff;line-height: 300%">账户余额（元）</div>

            <div style="height: 58%;width: 100%;background-color: white;line-height: 300%">￥ {{ data.loanBalance }}
            </div>
          </div>
          <div style="width: 50%;height: 100%">
            <div style="height: 42%;width: 100%;background-color: #f7faff;line-height: 300%">户数（户）</div>
            <div style="height: 58%;width: 100%;background-color: white;line-height: 300%">{{ data.clientCounts }}</div>

          </div>

        </div>
        <div style="width: 100%;height: 47%;margin-top: 14px">
          <div style="height: 42%;width: 100%;background-color: #f7faff;line-height: 300%">利息收入（元）</div>

          <div style="height: 58%;width: 100%;background-color: white;line-height: 300%">￥ {{ data.rateIncome }}</div>

        </div>
      </div>
      <div style="border-radius: 5px;width: 35%;height: 100%;display: flex;font-size: 14px">
        <div style="width: 50%;height: 100%;border-right: 1px solid #eff0f4">
          <div style="height: 20%;width: 100%;background-color: #f7faff;line-height: 300%">户均贷款（元）</div>

          <div style="height: 80%;width: 100%;background-color: white;line-height: 1000%">￥ {{ data.averBalance }}</div>
        </div>
        <div style="width: 50%">
          <div style="height: 20%;width: 100%;background-color: #f7faff;line-height: 300%">平均利率</div>
          <div style="height: 80%;width: 100%;background-color: white;line-height: 1000%">{{ data.averRate }}%</div>

        </div>
      </div>
      <div style="border-radius: 5px;width: 28%;height: 100%;font-size: 14px;">
        <div style="height: 20%;width: 100%;background-color: #f7faff;line-height: 300%">ARPU值</div>

        <div style="height: 80%;width: 100%;background-color: white;line-height: 1000%">{{ data.ARPU }}%</div>

      </div>
    </div>
    <div class="line">
      <div style="width: 71%;border-radius: 5px;height: 100%;font-size: 14px;overflow-y: auto;overflow-x: auto">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;background-color: white;height: 100%">
          <tr style="height: 18%">
            <td style="width:80px;font-size: 14px;text-align: center">客户名称</td>
            <td style="width:80px;font-size: 14px;text-align: center">贷款余额</td>
            <td style="width:80px;font-size: 14px;text-align: center">还款金额</td>
            <td style="width:80px;font-size: 14px;text-align: center">贷款金额</td>
            <td style="width:80px;font-size: 14px;text-align: center">所属机构</td>
            <td style="width:80px;font-size: 14px;text-align: center">还款时间</td>
            <td style="width:80px;font-size: 14px;text-align: center">贷款账号</td>
            <td style="width:80px;font-size: 14px;text-align: center">账户存款</td>
            <td style="width:80px;font-size: 14px;text-align: center">欠款金额</td>
            <td style="width:80px;font-size: 14px;text-align: center">逾期天数</td>
            <td style="width:80px;font-size: 14px;text-align: center">欠息天数</td>
            <td style="width:80px;font-size: 14px;text-align: center">欠息金额</td>
            <td style="width:80px;font-size: 14px;text-align: center">贷款利率</td>
            <td style="width:80px;font-size: 14px;text-align: center">贷款期限</td>

          </tr>
          <tr v-for="item in dataDemo" style="height: 23.4%">
            <td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px">
              {{ item.cliName }}

            </td>
          </tr>
        </table>
      </div>
      <div style="width: 28%;border-radius: 5px;height: 100%;font-size: 14px">
        <div style="width: 100%;height: 47%">
          <div style="height: 42%;width: 100%;background-color: #f7faff;line-height: 300%">逾期贷款额度</div>

          <div style="height: 58%;width: 100%;background-color: white;line-height: 300%">￥ {{ data.loanAmount }}</div>
        </div>
        <div style="width: 100%;height: 47%;margin-top: 14px">
          <div style="height: 42%;width: 100%;background-color: #f7faff;line-height: 300%">欠息合计</div>
          <div style="height: 58%;width: 100%;background-color: white;line-height: 300%">￥ {{ data.loanAmount }}</div>
        </div>
      </div>
    </div>
    <div style="background-color: white;font-size: 14px;width: 100%;height: 40%;">
      <!--     存放图像信息-->
      <div style="height: 2%"></div>
      <div style="width: 100%;height: 10%;text-align: left;font-size: 14px;border-bottom: 1px solid #eff0f4">
        <span style="padding-left: 10px;margin-left: 20px;border-left: 4px solid #4b83da;font-size: 18px">A区</span>
        <select style="width: 78px;height: 24px;float: right;line-height: 300%;margin-right: 20px;margin-top: 2px"
                v-model="subbranch" @change="changeSubbranch">
          <option value="全行">全行</option>
          <option value="南山支行">南山支行</option>
          <option value="龙华支行">龙华支行</option>
          <option value="龙城支行">龙城支行</option>
          <option value="福田支行">福田支行</option>
          <option value="龙岗支行">龙岗支行</option>
        </select>

        <div
            style="float: right;margin-right: 20px;display: flex;justify-content: space-between;width: 12%;cursor: pointer">
          <div id='按年' style="line-height: 220%" :class="interval == '按年' ? 'selectIt' : ''"
               @click="changeInterval">按年
          </div>
          <div id='按月' style="line-height: 220%" :class="interval == '按月' ? 'selectIt' : ''"
               @click="changeInterval">按月
          </div>
          <div id='按天' style="line-height: 220%" :class="interval == '按天' ? 'selectIt' : ''"
               @click="changeInterval">按天
          </div>
        </div>

        <div
            style="float: right;margin-right: 20px;display: flex;justify-content: space-between;width: 12%;cursor: pointer">
          <div id='本年' style="line-height: 220%" :class="time == '本年' ? 'selectIt' : ''" @click="changeTime">本年
          </div>
          <div id='本月' style="line-height: 220%" :class="time == '本月' ? 'selectIt' : ''" @click="changeTime">本月
          </div>
          <div id='本周' style="line-height: 220%" :class="time == '本周' ? 'selectIt' : ''" @click="changeTime">本周
          </div>
        </div>
      </div>

      <div style="width: 100%;height: 88%">
        <allA></allA>
        <!--       <allAC v-if="interval=='按年'?false:true"></allAC>-->
      </div>
    </div>
    <div style="background-color: white;font-size: 14px;width: 100%;height: 40%;">
      <div style="width: 100%;height: 88%;">
        <allB></allB>
      </div>
    </div>
    <div style="background-color: white;font-size: 14px;width: 100%;height: 40%;">
      <div style="width: 100%;height: 88%">
        <allC></allC>
      </div>
    </div>
  </div>


</template>

<script setup>
import {getCurrentInstance, ref, onMounted, provide, nextTick} from 'vue'

const {proxy} = getCurrentInstance()

import allA from '@/components/A.vue'
import allAC from '@/components/Asenc.vue'
import allB from '@/components/B.vue'
import allC from '@/components/C.vue'


const interval = ref('按年')
const time = ref('本年')
const subbranch = ref('全行')
let dataA = ref([])
let dataB = ref([])
let dataC = ref([])

// 更换时间频率，通过绑定的name获取当前值
function progressData(data) {
  let arr = Object.keys(data)
  // dataA.length=0
  // dataB.value.length=0
  // dataC.value.length=0
  dataA.value[0] = ['type', '户数', '贷款余额', '利息收入']
  dataB.value[0] = ['type', '平均利率', '户均贷款余额']
  dataC.value[0] = ['type', 'ARPU值']
  // let oneData=[]
  // let obj=oneData
  for (let i = 0; i < arr.length; i++) {
    let oneData = []

    oneData.push(arr[i])
    oneData.push(data[arr[i]].clientCounts)
    oneData.push(data[arr[i]].loanBalance)
    oneData.push(data[arr[i]].rateIncome)

    console.log(oneData, 'datadata')
    dataA.value[i + 1] = oneData
  }
  for (let i = 0; i < arr.length; i++) {
    let oneData = []
    oneData.push(arr[i])
    oneData.push(data[arr[i]].averRate)
    oneData.push(data[arr[i]].averBalance)
    dataB.value[i + 1] = oneData
  }
  for (let i = 0; i < arr.length; i++) {
    let oneData = []
    oneData.push(arr[i])
    oneData.push(data[arr[i]].ARPU)
    dataC.value[i + 1] = oneData
  }
  console.log('alll', dataA.value, dataC.value, dataB.value)
}

function changeInterval(e) {
  let changeVlaue = e.currentTarget.id
  console.log('更换了时间频率为：', changeVlaue)
  interval.value = changeVlaue
  let url = '/finance-analysis/overview/' + subbranch.value + '/' + '2019-01-17/2025-01-17/' + interval.value + '/廖惠娣'
  proxy.$api.post(url, {}).then(res => {

    console.log(res.data.data)
    progressData(res.data.data)
  })
}


// 更换时间
function changeTime(e) {
  let changeVlaue = e.currentTarget.id
  console.log('更换了时间为：', changeVlaue)
  time.value = changeVlaue
  // http://120.24.235.36:8089/finance-analysis/overview/龙城支行/2021-01-17/2025-01-17/按年/廖惠娣
  let url = '/finance-analysis/overview/' + subbranch.value + '/' + '2019-01-17/2025-01-17' + interval.value + '/廖惠娣'
  proxy.$api.post(url, {}).then(res => {

    console.log(res.data.data)
    progressData(res.data.data)
  })
}

// 更换区域

function changeSubbranch() {
  // let changeVlaue=e.currentTarget.id
  console.log('更换了区域为：', subbranch.value)
  // subbranch.value=changeVlaue
  let url = '/finance-analysis/overview/' + subbranch.value + '/' + '2021-01-17/2025-01-17/' + interval.value + '/廖惠娣'
  proxy.$api.post(url, {}).then(res => {

    console.log(res.data.data['1'])
    progressData(res.data.data)
  })
}

// let obj={
//   dataA:dataA,
//   dataB:dataB.value,
//   dataC:dataC.value
// }

provide('dataA', dataA.value)
provide('dataB', dataB.value)
provide('dataC', dataC.value)
const data = ref({})
const dataDemo = ref([])
setTimeout(() => {

}, 2000)
onMounted(() => {
  let url1 = '/finance-analysis/overview/' + subbranch.value + '/' + '2021-01-17/2025-01-17/' + interval.value + '/廖惠娣'
  let url2 = '/finance-analysis/overview/startAndEndTime'
  let url3 = '/portal/getOverduePersonnel'
  // http://120.24.235.36:8089/portal/getOverduePersonnel/宝龙支行
  proxy.$api.post(url2, {
    "subbranch": "全行",
    "start": "2022-01-17",
    "end":"2025-01-17",
    "interval": "按年",
    "manager": ""
  }).then(res => {
    data.value = res.data.data['2022']
  })
  proxy.$api.post(url1, {}).then(res => {
    console.log(res.data.data, 'yyyyy')
    progressData(res.data.data)
    nextTick()
  })
  proxy.$api.post(url3, {
    "subbranch": "全行"
  }).then(res => {
    console.log(res.data.data.overduePeople)
    // dataDemo.value = res.data.data.overduePeople
    let tempList = res.data.data.overduePeople
    for (let i = 0; i < tempList.length; i += 1) {
      let dataJson = tempList[i].conEnd
      tempList[i].conEnd = dataJson[0] + '-' + dataJson[1] + '-' + dataJson[2]
    }
    dataDemo.value = tempList

  })
})

// onMounted(()=>{
//   progressData(dataAll)
// },3000)
// const inf=ref()

// proxy.$api.post()
//伪造假数据



</script>

<style>
.selectIt {
  color: #4b83da;
}

tr:nth-child(odd) {
  background-color: #f6f9fd;
}

tr {
  width: 100%;
  text-align: left;
  font-size: 16px;
}

.bg {

  background-color: #eff0f4;
  height: 95%;
  width: 96%;
  margin: 20px 20px;
  overflow-y: auto;
}


.line {
  width: 100%;
  height: 26%;

  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

</style>
