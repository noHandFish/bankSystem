<template>
  <div style="background-color: white;margin-top: 5px;width: 100%;height: 100%">
    <div >
      <form>
        <div>
          <span style="margin-right: 20px;margin-top: 30px">查询方式</span>
          <input  type="checkbox" name="searchway" value="idcard">证件查询
          <input  style="margin-left: 40px;margin-top: 30px" type="checkbox" name="searchway" value="name">客户名查询
        </div>
        <span style="margin-right: 20px">证件号码/客户名称</span><input type="text" class="searchinpt" name="searchByIdCard" v-model="inputId">
        <button class="search" type="submit" @click="searchGuaranteeById">查询</button>
        <button class="reset"  type="reset">重置</button>
      </form>
    </div>

    <div class="context">
      <el-tabs type="border-card" id="selectWillShow">
        <el-tab-pane label="担保人基本信息">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="name"  width="180" />
            <el-table-column prop="data"  width="800" />
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="担保情况">担保情况</el-tab-pane>
      </el-tabs>
    </div>
    <button>更多</button> <button>下载</button>
  </div>
</template>
<script scope setup>
import {getCurrentInstance,ref} from 'vue'
const {proxy}= getCurrentInstance()

const inputId=ref('')
function searchGuaranteeById(){
  proxy.$api.get('/data-analysis/guarantee-analysis/customerInformationQuery',{
    cli_idnum:inputId.value
  }).then(res=>{
    console.log(res.data)
  })
}
const tableData = [
  {
    name: '担保人名称',
    data: '张三',
  },
  {
    name: '证件号码',
    data: '3333333333333333',
  },
  {
    name: '性别',
    data: '男',
  },
  {
    name: '联系电话',
    data: 'xxx',
  },
  {
    name: '工作单位',
    data: 'xxx',
  },
  {
    name: '联系地址',
    data: 'xxx',
  },
  {
    name: '行政区',
    data: 'xxx',
  },
  {
    name: '街道办',
    data: 'xxx',
  },
  {
    name: '行政村',
    data: 'xxx',
  },
  {
    name: '自然村',
    data: 'xxx',
  },
  {
    name: '婚姻状况',
    data: 'xxx'
  },
  {
    name: '所属网点客户经理',
    data: 'xxx',
  },
]
</script>
<style>
.father{
  text-align: center;
}
.search{
  width: 370px;
  height: 40px;
}
.searchinpt{
  width: 30%;
  margin: 20px 20px;
  height: 30px;
}
#selectWillShow{
  margin: 40px auto;
}
.context{
  width: 90%;
  box-shadow: 2px -2px 1px gainsboro;
  margin: 50px auto;
}
.showMsg{
  background: gainsboro;
}
.inf{
  background-color: #ffffff;
  width: 500px;
}
.search{
  width: 8%;
  height: 10%;
  padding: 5px;
  border-radius: 8px;
  background-color: #579bde;
  border-width: 0;
  color: white;
  margin: 20px 20px;
}
.reset{
  width: 8%;
  height: 10%;
  padding: 5px;
  margin: 20px 20px;
  border-radius: 8px;
  background-color: #ffffff;
  color: #101110;
  border: 1px solid black;
}
</style>
