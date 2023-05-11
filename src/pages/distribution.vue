<template>
  <div style="width: 96%;height: 100%;font-size: 14px;overflow-y: auto">
    <div style="text-align: left;margin-left: 20px;margin-top: 20px">

      <button
          style="background-color: white;border: 1px solid #4b83da;width: 80px;height: 30px;border-radius: 5px;color: #4b83da;margin-right: 20px"
          :style="searchType==='户数'?'background-color:#4b83da;boder:1px solid #4b83da;color:white':''"
          @click="changeType" id="户数">户数
      </button>
      <button
          style="background-color: white;border: 1px solid #4b83da;width: 80px;height: 30px;border-radius: 5px;color: #4b83da"
          :style="searchType==='金额'?'background-color:#4b83da;boder:1px solid #4b83da;color:white':''"
          @click="changeType" id="金额">金额
      </button>
    </div>
    <div style="display: flex;justify-content: space-around;height: 42%;margin-top: 20px">
      <div style="width: 60%;height: 92%;margin: 0px auto;border-radius: 6px;background-color: white">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
          <tr>

            <td style="font-size: 12px;text-align: center">排名</td>
            <td style="font-size: 12px;text-align: center">行政区名称</td>
            <td style="font-size: 12px;text-align: center">占比</td>
          </tr>

          <tr v-for="(item,index) in cliRegion">
            <td style="text-align:center;font-size: 12px">{{ index + 1 }}</td>
            <td style="text-align:center;font-size: 12px">
              {{ item.name }}
            </td>
            <td style="text-align:center;font-size: 12px">
              {{ item.value }}

            </td>
          </tr>
        </table>
      </div>
      <div style="width: 38%;border-radius: 6px;height: 92%;background-color: white">
        <div style="text-align: left;padding-left: 20px;border-bottom: 1px solid #eff0f4;height: 10%;">
          <span style="line-height: 200%">行政区类型占比</span>
        </div>
        <div style="width: 100%;height: 90%">
          <distributionA></distributionA>
        </div>
      </div>
    </div>
    <div style="display: flex;justify-content: space-around;height: 42%;margin-top: 20px">
      <div style="width: 60%;height: 92%;margin: 0px auto;border-radius: 6px;background-color: white">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
          <tr>

            <td style="font-size: 12px;text-align: center">排名</td>
            <td style="font-size: 12px;text-align: center">客户类型</td>
            <td style="font-size: 12px;text-align: center">占比</td>
          </tr>

          <tr v-for="(item,index) in typeMap">
            <td style="text-align:center;font-size: 12px">{{ index + 1 }}</td>
            <td style="text-align:center;font-size: 12px">
              {{ item.name }}
            </td>
            <td style="text-align:center;font-size: 12px">
              {{ item.value }}

            </td>
          </tr>
        </table>
      </div>
      <div style="width: 38%;border-radius: 6px;height: 92%;background-color: white">
        <div style="text-align: left;padding-left: 20px;border-bottom: 1px solid #eff0f4;height: 10%;">
          <span style="line-height: 200%">客户类型占比</span>
        </div>
        <div style="width: 100%;height: 90%">
          <distributionC></distributionC>
        </div>
      </div>
    </div>
    <div style="display: flex;justify-content: space-around;height: 42%;margin-top: 20px">
      <div style="width: 60%;height: 92%;margin: 0px auto;border-radius: 6px;background-color: white">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
          <tr>

            <td style="font-size: 12px;text-align: center">排名</td>
            <td style="font-size: 12px;text-align: center">年龄阶段</td>
            <td style="font-size: 12px;text-align: center">占比</td>
          </tr>

          <tr v-for="(item,index) in cliAge">
            <td style="text-align:center;font-size: 12px">{{ index + 1 }}</td>
            <td style="text-align:center;font-size: 12px">
              {{ item.name }}
            </td>
            <td style="text-align:center;font-size: 12px">
              {{ item.value }}

            </td>
          </tr>
        </table>
      </div>
      <div style="width: 38%;border-radius: 6px;height: 92%;background-color: white">
        <div style="text-align: left;padding-left: 20px;border-bottom: 1px solid #eff0f4;height: 10%;">
          <span style="line-height: 200%">年龄阶段占比</span>
        </div>
        <div style="width: 100%;height: 90%">
          <distributionB></distributionB>
        </div>
      </div>
    </div>
    <div style="display: flex;justify-content: space-around;height: 42%;margin-top: 20px">
      <div style="width: 60%;height: 92%;margin: 0px auto;border-radius: 6px;background-color: white">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
          <tr>

            <td style="font-size: 12px;text-align: center">排名</td>
            <td style="font-size: 12px;text-align: center">性别类型</td>
            <td style="font-size: 12px;text-align: center">占比</td>
          </tr>

          <tr v-for="(item,index) in genderMap">
            <td style="text-align:center;font-size: 12px">{{ index + 1 }}</td>
            <td style="text-align:center;font-size: 12px">
              {{ item.name }}
            </td>
            <td style="text-align:center;font-size: 12px">
              {{ item.value }}

            </td>
          </tr>
        </table>
      </div>
      <div style="width: 38%;border-radius: 6px;height: 92%;background-color: white">
        <div style="text-align: left;padding-left: 20px;border-bottom: 1px solid #eff0f4;height: 10%;">
          <span style="·line-height: 200%">性别比例占比</span>
        </div>
        <div style="width: 100%;height: 90%">
          <distributionD />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import distributionA from "@/components/distributionA";
import distributionB from "@/components/distributionB";
import distributionC from "@/components/distributionC";
import distributionD from "@/components/distributionD";

import {getCurrentInstance, nextTick, onMounted, provide, ref} from 'vue'
import {Close} from '@element-plus/icons-vue'

const {proxy} = getCurrentInstance()
const searchType = ref('户数')
const canShow = ref(false)
const showTitle = ref('')
const dataDetail = ref([])
let cliAge = ref([])
let cliRegion = ref([])

let typeMap = ref([])

let genderMap = ref([])

onMounted(() => {
  distribuByhouseholds()
  nextTick()
})
provide("cliAge", cliAge.value)
provide("cliRegion", cliRegion.value)
provide("typeMap", typeMap.value)
provide("genderMap", genderMap.value)

const dataAll = ref([])
provide('dataAll', dataAll.value)

function distribuByhouseholds() {
  proxy.$api.post('/portal/chardisplay/Byhouseholds', {}).then(res => {
    // let sortData=res.data.data
    // sortData.sort((a,b)=>{ return a.value-b.value})
    let typeMapData = res.data.data.typeMap
    let cliRegionData = res.data.data.cliRegion
    let cliAgeData = res.data.data.cliAge
    let genderMapData = res.data.data.genderMap
    typeMap.value = []
    for (let i = 0; i < typeMapData.length; i++) {
      let key = Object.keys(typeMapData[i])[0]
      let name = key
      let value = typeMapData[i][key]
      typeMap.value.push({name, value})
    }
    cliRegion.value = []
    for (let i = 0; i < cliRegionData.length; i++) {
      let key = Object.keys(cliRegionData[i])[0]
      let name = key
      let value = cliRegionData[i][key]
      cliRegion.value.push({name, value})
    }
    cliAge.value = []
    for (let i = 0; i < cliAgeData.length; i++) {
      let key = Object.keys(cliAgeData[i])[0]
      let name = key
      let value = cliAgeData[i][key]
      cliAge.value.push({name, value})
    }
    genderMap.value = []
    for (let i = 0; i < genderMapData.length; i++) {
      let key = Object.keys(genderMapData[i])[0]
      let name = key
      let value = genderMapData[i][key]
      genderMap.value.push({name, value})
    }
    typeMap.value.sort((a, b) => {
      return b.value - a.value
    })
    cliRegion.value.sort((a, b) => {
      return b.value - a.value
    })
    cliAge.value.sort((a, b) => {
      return b.value - a.value
    })
    genderMap.value.sort((a, b) => {
      return b.value - a.value
    })
    dataAll.value[0] = typeMap.value
    dataAll.value[1] = cliRegion.value
    dataAll.value[2] = cliAge.value
    dataAll.value[3] = genderMap.value
  })
}

function changeType(e) {
  if (searchType.value != e.currentTarget.id) {
    if (e.currentTarget.id == '户数') {
      distribuByhouseholds()
    } else {
      distribuByamount()
    }
  }
  searchType.value = e.currentTarget.id
}

function distribuByamount() {
  proxy.$api.post('/portal/chardisplay/ByFinance', {}).then(res => {
    console.log(res.data)
    let typeMapData = res.data.data.Type_Finacial
    let cliRegionData = res.data.data.regionByFinacial
    let cliAgeData = res.data.data.ageByFinacial
    let genderMapData = res.data.data.genderListByFinacial
    typeMap.value = []
    for (let i = 0; i < typeMapData.length; i++) {
      let key = Object.keys(typeMapData[i])[0]
      let name = key
      let value = typeMapData[i][key]
      typeMap.value.push({name, value})
    }
    cliRegion.value = []
    for (let i = 0; i < cliRegionData.length; i++) {
      let key = Object.keys(cliRegionData[i])[0]
      let name = key
      let value = cliRegionData[i][key]
      cliRegion.value.push({name, value})
    }
    cliAge.value = []
    for (let i = 0; i < cliAgeData.length; i++) {
      let key = Object.keys(cliAgeData[i])[0]
      let name = key
      let value = cliAgeData[i][key]
      cliAge.value.push({name, value})
    }
    genderMap.value = []
    for (let i = 0; i < genderMapData.length; i++) {
      let key = Object.keys(genderMapData[i])[0]
      let name = key
      let value = genderMapData[i][key]
      genderMap.value.push({name, value})
    }
    typeMap.value.sort((a, b) => {
      return b.value - a.value
    })
    cliRegion.value.sort((a, b) => {
      return b.value - a.value
    })
    cliAge.value.sort((a, b) => {
      return b.value - a.value
    })
    genderMap.value.sort((a, b) => {
      return b.value - a.value
    })
    dataAll.value[0] = typeMap.value
    dataAll.value[1] = cliRegion.value
    dataAll.value[2] = cliAge.value
    dataAll.value[3] = genderMap.value
  })
}

</script>

<style>

.disleft {

  width: 97%;
  height: 300px;
  margin: 10px auto;
  display: flex;
  justify-content: center;
}

tr:nth-child(odd) {
  background-color: #f6f9fd;
}

tr {
  width: 100%;
  height: 40px;
  text-align: left;
  font-size: 16px;
}
</style>

