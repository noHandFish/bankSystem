<template>
  <div style="height: 30%;width: 100%;background-color: white;border-radius: 0 0 6px 6px">
    <div style="height: 30px;width: 100%"></div>
    <div style="width:80%;margin: 0 auto">
      <el-checkbox label="支行:" style="vertical-align: middle;"></el-checkbox><select class="listSelect"></select>
      <el-checkbox label="报表日期:" style="vertical-align: middle;"></el-checkbox>
      <select class="listSelect"></select>
    </div>
    <div>
      <button @click="searchDaily">查询</button>
    </div>

    <div v-if="canShow" style="width: 84%;height: 100%;background-color: rgba(0,0,0,0.2);position: fixed;top: 0%;"></div>
    <div v-if="canShow" style="width: 80%;height: 600px;background-color: white;position:fixed;top: 48%;transform: translateY(-50%);border-radius: 10px;margin-left: 20px">
      <el-icon style="position: absolute;top: 4%;right: 6%" @click="cancelShow"><Close /></el-icon>
      <div style="text-align: left;margin-left: 20px;margin-top: 20px">信息查询结果</div>
      <div style="width: 96%;height: 100%;margin: 10px auto">
        <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {getCurrentInstance} from 'vue'
import {Close} from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const canShow=ref(false)

function searchDaily(){

  proxy.$api.post('/approval_form',
  {
    "branch":"0",
      "exact_date": {
    "day":"1",
        "month":"1",
        "year":"1"
  }
  }).then((res)=>{
    console.log(res.data)

  })
}
function cancelShow(){
  canShow.value=false
}
</script>
<style scoped>

button{
  background-color: #4b83da;
  border: none;
  width: 80px;
  height: 30px;
  color: white;
  font-size: 16px;
  border-radius: 2px;
  margin-top: 30px;
}
.listSelect {
  width: 220px;
  height: 26px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-right: 30px;
}
</style>