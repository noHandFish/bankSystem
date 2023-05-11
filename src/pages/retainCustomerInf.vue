<template>
  <div style="height: 95%;background-color: white;border-top: 1px solid gainsboro;font-size: 14px;border-bottom: none">
    <div style="height: 30%;width: 98%;border: 1px solid gainsboro;margin: 20px auto;margin-top: 20px;border-radius: 5px">
      <div style="height: 16%;margin: 0 auto;width: 98%;border-bottom: 1px solid #eff0f3;text-align: left;font-size: 14px;">
        <span style="line-height: 300%;color: black">客户信息维护</span>
        <div style="float: right;margin-right: 20px;line-height: 300%"><span>更多查询条件</span></div>
      </div>
      <div style="height: 84%;width: 100%;text-align: center">
        <div style="width: 70%;height: 40%;margin: 40px auto;">
          <div style="width: 100%;height: 48%;font-size: 14px;text-align: left">
            支行名称：<select class="retainSelect" v-model="Subbranch">
              <option value="全行">全行</option>
              <option value="龙城支行">龙城支行</option>
              <option value="龙华支行">龙华支行</option>
              <option value="南山支行">南山支行</option>
              <option value="龙岗支行">龙岗支行</option>
            </select>
            数据状态：<select class="retainSelect" v-model="Condition">
              <option value="已补录">已补录</option>
              <option value="待补录">待补录</option>
            </select>
          </div>
          <div style="width: 100%;height: 48%;text-align: left">
            起始日期：<el-date-picker
              v-model="StartDay"
              type="date"
              placeholder="开始"
              style="width: 26%;height: 70%;margin-right: 80px"
              :size="size"
          />
            终止日期：<el-date-picker
              v-model="EndDay"
              type="date"
              placeholder="结束"
              style="width: 26%;height: 70%;"
              :size="size"
          />
          </div>
        </div>
        <div style="margin-top: -20px">
          <button @click="searchInf" style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white">查询</button>
        </div>
      </div>
      <div v-if="canShow" style="height: 500px;overflow-y: auto;margin-left: 40px;text-align: left">
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white" @click="dataEntry">补录</button>
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #3dba6e;border: none;border-radius: 5px;color: white">修改</button>
        <div style="width: 96%;height: 72%;overflow-y: auto;text-align: left">
          <el-table
              ref="multipleTableRef"
              :data="tableData"
              style="width: 100%;"
              @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" />
            <el-table-column label="客户号" width="120">
              <template #default="scope">{{ scope.row.cliId }}</template>
            </el-table-column>
            <el-table-column property="cliName" label="客户名称" width="120" />
            <el-table-column property="conId" label="贷款合同号" width="180" show-overflow-tooltip/>
            <el-table-column property="cliBalance" label="贷款金额" width="120" />
            <el-table-column property="conDate" label="放款日期" width="120" />
            <el-table-column property="supplementaryRecordStatue" label="数据状态" width="120" />
          </el-table>
        </div>
      </div>
    </div>

    <div v-if="canShowB" style="width: 84%;height: 100%;background-color: rgba(0,0,0,0.2);position: fixed;top: 0%;"></div>
    <div v-if="canShowB" style="text-align:left;width: 80%;height: 300px;background-color: white;position:fixed;top: 48%;transform: translateY(-50%);border-radius: 10px;margin-left: 20px">
      <el-icon style="position: absolute;top: 4%;right: 6%" @click="cancelShow"><Close /></el-icon>
      <div style="text-align: left;margin-left: 20px;margin-top: 20px">客户信息补录</div>
      <div>
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white" @click="dataEntry">基本信息</button>
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white" @click="dataEntry">业务信息</button>
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white" @click="dataEntry">担保人信息</button>
        <button style="margin-left: 10px;margin-right: 10px;margin-top: 20px;margin-bottom: 20px;width: 80px;height: 30px;background-color: #4b83da;border: none;border-radius: 5px;color: white" @click="dataEntry">附加信息</button>
      </div>
      <div style="display: flex;margin-top: 30px;width: 80%;font-size: 14px;margin: 0 auto">
        <div style="width: 33%">
          <div style="margin-bottom: 20px">客户名称：<input type="text" style="height: 30px;width: 180px;margin-left: 16px"></div>
          <div style="margin-bottom: 20px">联系电话：<input type="text" style="height: 30px;width: 180px;margin-left: 16px"></div>
        </div>
        <div style="width: 33%">
          <div style="margin-bottom: 20px">证件号码：<input type="text" style="height: 30px;width: 180px;margin-left: 16px"></div>
          <div style="margin-bottom: 20px">工作单位：<input type="text" style="height: 30px;width: 180px;margin-left: 16px"></div>
        </div>
        <div style="width: 33%">
          <div style="margin-bottom: 20px">性别<input type="text" style="height: 30px;width: 180px;margin-left: 16px"></div>
          <div></div>
        </div>

      </div>
    </div>
  </div>
</template>
<script setup>
import {getCurrentInstance, ref} from 'vue'
import { ElTable } from 'element-plus'
import { Avatar,Lock,Close} from '@element-plus/icons-vue'
const {proxy}= getCurrentInstance()
const Condition=ref('待补录')
const Subbranch=ref('全行')
const StartDay=ref('')
const EndDay=ref('')
const canShow=ref(false)
const canShowB=ref(false)
const tableData=ref([])
function dataEntry(){
  canShowB.value=true
  canShow.value=false
}
function  cancelShow(){
  canShowB.value=false
  canShow.value=true
}
// const Condition=ref('')
// const Subbranch=ref('')
// const StartDay=ref('')
// const EndDay=ref('')

function searchInf() {
  let url = '/DataEntry/cilent/info' + '?Condition=' + Condition.value + '&Subbranch=' + Subbranch.value + '&StartDay=' + StartDay.value + '&EndDay=' + EndDay.value
  proxy.$api.post(url, {}).then((res) => {
    console.log(res.data.data.ClientList)
    tableData.value = res.data.data.ClientList
    console.log(tableData.value)
    canShow.value=true
  })
}
</script>
<style>
.retainSelect{
  width: 26%;
  height: 70%;
  border: 1px solid darkgray;
  border-radius: 5px;
  margin-right: 80px;
}
</style>