<template>
  <div class="header">
    <span style="font-size: 16px;font-family: Source Han Sans CN,Source Han Sans CN-Regular,serif;user-select: none">证件号码/客户名称</span>
    <span style="margin-left: 16px">
      <el-input v-model="queryString" placeholder="请输入证件号码或客户名称"/>
    </span>
    <span class="ecBtn" style="margin-left: 16px" @click="search">查询</span>
    <span class="ecBtn" style="margin-left: 16px" @click="queryString = ''">重置</span>
  </div>
  <div class="content">
    <div style="display: flex;padding: 4px 12px;">
      <span class="nav" :class="selected === '1' ? 'nav_selected' : ''" @click="select('1')">借贷主体1</span>
      <span class="nav" :class="selected === '2' ? 'nav_selected' : ''" @click="select('2')">借贷主体2</span>
      <span class="nav" :class="selected === '3' ? 'nav_selected' : ''" @click="select('3')">借贷主体3</span>
    </div>
    <div style="display: flex; width: 95%; padding: 16px;flex-wrap: wrap;justify-content: flex-start;max-height: 64vh;overflow: auto">
      <el-card v-for="(item, index) in dataList" style="margin: 0 2% 8px" :key="index">
        <template #header>
          <div class="card-header"
               style="display: flex;width: 300px;justify-content: space-between;align-items: center;">
            <span class="usrName">客户名称: {{item.cliName}}</span>
<!--            <span-->
<!--                style="display: flex;align-items: center;font-size: 16px;color: #5484FD;user-select: none;cursor: pointer">-->
<!--              详情-->
<!--              <el-icon style="transform: translate(0, 10%)">-->
<!--                <ArrowRight/>-->
<!--              </el-icon>-->
<!--            </span>-->
          </div>
        </template>

        <div style="text-align: left">
          <div style="display: flex;justify-content: space-between;margin-top:-8px">
            <span>业务种类:</span>
            <span>{{ item.bussType }}</span>
          </div>
<!--          <div style="display: flex;justify-content: space-between;margin-top: 8px">-->
<!--            <span>合同金额:</span>-->
<!--            <span>{{ item.conAmount }}元</span>-->
<!--          </div>-->
          <div style="display: flex;justify-content: space-between;margin-top: 8px">
            <span>贷款余额:</span>
            <span>{{ item.conBalance }}元</span>
          </div>
          <div style="display: flex;justify-content: space-between;margin-top: 8px">
            <span>贷款起始日期:</span>
            <span>{{ item.conStart }}</span>
          </div>
          <div style="display: flex;justify-content: space-between;margin-top: 8px">
            <span>贷款到期日期:</span>
            <span>{{ item.conEnd }}</span>
          </div>
          <div style="display: flex;justify-content: space-between;margin-top: 8px">
            <span>贷款:</span>
            <span>{{ item.conAmount }}元</span>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, ref, watch} from 'vue'
import {ArrowRight} from '@element-plus/icons-vue'

const {proxy} = getCurrentInstance()

let queryString = ref('')
let selected = ref('1')
let dataList = ref([])

function search() {
  const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
  if (reg.test(queryString.value)) {
    proxy.$api.post('/information-query/groupcustomers/get_groupcustomersById', {
      cliIdnum: queryString.value
    }).then(res => {
      dataList.value = res.data.data.GroupBusinessListRelatedEnterprises
      dataList.value = dataList.value.concat(res.data.data.GroupBusinessListRelatedSpouseOrCommunitys)
    })
  }
  else {
    proxy.$api.post('/information-query/groupcustomers/get_groupcustomers', {
      cliName: queryString.value
    }).then(res => {
      dataList.value = res.data.data.GroupBusinessListRelatedEnterprises
      dataList.value = dataList.value.concat(res.data.data.GroupBusinessListRelatedSpouseOrCommunitys)
    })
  }
}

function select(s) {
  selected.value = s
}
</script>

<style scoped>
.header {
  width: 95%;
  height: 10vh;
  box-sizing: border-box;
  margin: 16px auto;
  padding: 16px;
  background: #fdfdfd;
  border: 1px solid #e5e7e9;
  border-radius: 6px;
  display: flex;
  align-items: center;
}

.ecBtn {
  user-select: none;
  cursor: pointer;
  border: 1px solid #5484fd;
  border-radius: 3px;
  padding: 4px 12px;
  color: #5484fd;
}

.ecBtn:hover {
  color: #FFFFFF;
  background-color: #5484fd;
}

.content {
  width: 95%;
  margin: 16px auto;
  background: #fdfdfd;
  border-radius: 6px;
  border: 1px solid #e5e7e9;
}

.nav {
  display: block;
  padding: 6px 10px;
  font-size: 16px;
  font-family: Source Han Sans CN, Source Han Sans CN-Regular, serif;
  font-weight: 400;
  color: #8496ab;
  cursor: pointer;
  user-select: none;
}

.nav_selected {
  font-size: 16px;
  font-family: Source Han Sans CN, Source Han Sans CN-Bold, serif;
  font-weight: 700;
  color: #32323a;
}

.usrName {
  font-size: 20px;
  font-family: Source Han Sans CN, Source Han Sans CN-Bold, serif;
  font-weight: 700;
  color: #32323a;
}

</style>
