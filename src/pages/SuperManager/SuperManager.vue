<template>
  <div class="bg">
    <div class="outer">
      <el-container>
        <el-header height="60px">
          <div style="display: flex;justify-content: space-between;align-items: center">
            <div class="title">超级管理员</div>
            <div style="width: 230px;display: flex;justify-content: space-between">
              <span class="topBtn" @click="showCreate">增加</span>
              <span class="topBtn" @click="showUpdate">修改</span>
              <span class="topBtn" @click="deleteSelectedUser">删除</span>
            </div>
          </div>
        </el-header>
        <el-main>
          <el-table :data="showTableData" :header-cell-style="{background: '#5484fd', color: '#FFFFFF'}" ref="table"
                    @select="selectChange" @select-all="selectChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="username" label="用户名"/>
            <el-table-column prop="name" label="真实姓名"/>
            <el-table-column prop="perms" label="身份"/>
            <el-table-column prop="phone" label="手机号码"/>
            <el-table-column prop="wxid" label="微信号"/>
          </el-table>
          <div style="display: flex;justify-content: space-between">
            <div
                style="width: 200px;display: flex;justify-content: space-between;align-items: center;margin-top: 22px;margin-left: 8px">
            <span class="bottomBtn">
              <el-icon style="transform: translate(0, 2px)"><Edit/></el-icon>
              批量修改
            </span>
              <span class="bottomBtn" @click="deleteSelectedUser">
              <el-icon style="transform: translate(0, 2px)"><Delete/></el-icon>
              删除选中项
            </span>
            </div>
            <div style="display: flex;margin-top: 16px;align-items: center">
              <span class="pageCount">共{{ tableData.length }}条</span>
              <span>
            <el-pagination
                v-model:currentPage="currentPage"
                layout="prev, pager, next, jumper"
                :total="tableData.length"
                :page-size="10"
                :hide-on-single-page="true"
                @current-change="handleCurrentChange"
            />
            </span>
            </div>
          </div>
        </el-main>
      </el-container>
    </div>

    <el-dialog v-model="dialogTableVisible" :title="dialogTitle">
      <div style="display: flex">
        <div style="flex: 1">
          <div class="input_line">
            <span class="line_text">*用户名</span>
            <el-input v-model="usrName" :placeholder="usrNamePlaceholder" clearable style="width: 290px" :disabled="dialogTitle === '修改用户'"/>
          </div>
          <div class="input_line" style="margin-top: 16px">
            <span class="line_text">*真实姓名</span>
            <el-input v-model="realName" placeholder="真实姓名" clearable style="width: 290px"/>
          </div>
          <div class="input_line" style="margin-top: 16px">
            <span class="line_text">*手机号码</span>
            <el-input v-model="telNum" placeholder="手机号码" clearable style="width: 290px"/>
          </div>
        </div>
        <div style="flex: 1">
          <div class="input_line">
            <span class="line_text">*密码</span>
            <el-input v-model="psw" placeholder="密码" clearable style="width: 290px"/>
          </div>
          <div class="input_line" style="margin-top: 16px">
            <span class="line_text">*身份</span>
            <el-input v-model="perms" placeholder="身份" clearable style="width: 290px"/>
          </div>
          <div class="input_line" style="margin-top: 16px">
            <span class="line_text">微信号</span>
            <el-input v-model="wxId" placeholder="微信号" clearable style="width: 290px"/>
          </div>
        </div>
      </div>
      <!--   两个按钮   -->
      <div style="display: flex;justify-content: space-around;width: 300px;margin: 48px auto 36px">
        <span class="dialogBtn" @click="clickSure">确定</span>
        <span class="dialogBtn" @click="clickReset">重置</span>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, ref} from "vue";
import {Delete, Edit} from "@element-plus/icons-vue"

const {proxy} = getCurrentInstance()
let currentPage = ref(1) // 当前页(从1开始)
let pages = ref(0) // 总页数
let tableData = ref([]) // 表格数据
let showTableData = ref([]) // 展示的表格数据

onMounted(() => {
  proxy.$api.get('/user-role/list').then(res => {
    tableData.value = res.data.data
    pages.value = parseInt(tableData.value.length / 10)
    showTableData.value = tableData.value.slice((currentPage.value - 1) * 10,
        (currentPage.value - 1) * 10 + 10 > tableData.value.length ?
            tableData.value.length : (currentPage.value - 1) * 10 + 10)
  })
})

// 获取哪些用户被选中
function inSelectedList(item, list) {
  for (let i = 0; i < list.length; i++) {
    let keys = Object.keys(item)
    let flag = true
    for (let j = 0; j < keys.length; j++) {
      if (item[keys[j]] !== list[i][keys[j]]) {
        flag = false
        break
      }
    }
    if (flag)
      return true
  }
  return false
}
// 选择的回调
function selectChange(list) {
  for (let i = 0; i < showTableData.value.length; i++) {
    tableData.value[(currentPage.value - 1) * 10 + i].selected = inSelectedList(showTableData.value[i], list)
  }
}
// 翻页的时候记录这一页选中的用户
function ensureSelect() {
  let table = proxy.$refs.table
  for (let i = 0; i < showTableData.value.length; i++) {
    if (showTableData.value[i].selected) {
      table.toggleRowSelection(showTableData.value[i], true)
    }
  }
}
// 切换不同页
function handleCurrentChange() {
  showTableData.value = tableData.value.slice((currentPage.value - 1) * 10,
      (currentPage.value - 1) * 10 + 10 > tableData.value.length ?
          tableData.value.length : (currentPage.value - 1) * 10 + 10)
  proxy.$nextTick(() => {
    ensureSelect()
  })
}

// 删除用户
function deleteSelectedUser() {
  deleteUser().then(() => {
    alert('删除成功')
    location.reload()
  }).catch(err => {
    console.log(err)
  })
}
// 被上面调用，await循环调用异步请求
function deleteUser() {
  let promiseList = []
  for (let i = 0; i < tableData.value.length; i++) {
    if (!tableData.value[i].selected)
      continue
    promiseList.push(proxy.$api.post('/user-role/delete', {
      username: tableData.value[i].username
    }))
  }
  return Promise.all(promiseList)
}

// 下面都是弹窗内容
let dialogTableVisible = ref(false)
let dialogTitle = ref('')
let usrName = ref('')
let realName = ref('')
let telNum = ref('')
let psw = ref('')
let perms = ref('')
let wxId = ref('')
let usrNamePlaceholder = ref('用户名')

function showCreate() {
  dialogTitle.value = '新增用户'
  dialogTableVisible.value = true
}
function showUpdate() {
  let target = {}
  let flag = false
  for (let i = 0; i < tableData.value.length; i++) {
    if (tableData.value[i].selected) {
      if (flag) {
        alert('不可以同时修改多个用户噢')
        return
      }
      target = tableData.value[i]
      flag = true
    }
  }
  if (!flag) {
    alert('请选择需要修改的用户')
    return
  }
  dialogTitle.value = '修改用户'
  dialogTableVisible.value = true
  usrNamePlaceholder.value = target.username
}
function clickReset() {
  if (dialogTitle.value === '新增用户')
    usrName.value = ''
  realName.value = ''
  telNum.value = ''
  psw.value = ''
  perms.value = ''
  wxId.value = ''
}
function clickSure() {
  if (dialogTitle.value === '新增用户') {
    if (!usrName.value || !psw.value || !perms.value || !realName.value || !telNum.value) {
      alert('带星号为必填项噢')
      return
    }
    proxy.$api.post('/user-role/saveRegister', {
      username: usrName.value,
      password: psw.value,
      perms: perms.value,
      name: realName.value,
      phone: telNum.value,
      wxid: wxId.value
    }).then(res => {
      console.log(res)
      if (!res.data.code || res.data.code !== 200)
        alert(res.data.message)
      else {
        alert('添加成功')
        dialogTableVisible.value = false
        location.reload()
      }
    })
  }
  else {
    alert('暂时没有修改用户的接口')
    dialogTableVisible.value = false
  }
}
</script>

<style scoped>
.bg {
  margin: 0;
  padding: 0;
  background-color: #579bde;
  height: 800px;
}

.outer {
  width: 1200px;
  border-radius: 6px;
  position: relative;
  left: 50%;
  transform: translate(-50%, 80px);
  padding: 16px;
  background-color: #FFFFFF;
}

.title {
  font-size: 18px;
  font-weight: 900;
  color: #666666;
}

.topBtn {
  display: inline-block;
  width: 64px;
  height: 36px;
  border: 1px solid #5b8ff9;
  border-radius: 3px;
  line-height: 36px;
  text-align: center;
  font-size: 16px;
  color: #5b8ff9;
  cursor: pointer;
}

.topBtn:hover {
  background-color: #5b8ff9;
  color: #FFFFFF;
}

.el-main {
  padding-top: 0 !important;
}

.pageCount {
  transform: translate(0, -1px);
  font-size: 14px;
  font-weight: 400;
  color: #8496ab;
}

.bottomBtn {
  font-size: 14px;
  font-weight: 400;
  color: #666666;
  cursor: pointer;
}
.input_line {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.line_text {
  display: inline-block;
  width: 68px;
  font-size: 14px;
  font-weight: 400;
  text-align: right;
  color: #32323A;
}
.dialogBtn {
  display: inline-block;
  font-size: 16px;
  font-weight: 400;
  text-align: center;
  color: #5484fd;
  border-radius: 5px;
  padding: 8px 12px;
  border: solid 1px #5b8ff9;
  cursor: pointer;
}
.dialogBtn:hover {
  background-color: #5b8ff9;
  color: #FFFFFF;
}
</style>
