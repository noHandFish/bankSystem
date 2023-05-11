<template>

  <div style="width: 70%;margin: 55px auto">

    <div style="display: flex;justify-content: center;margin-bottom: 14px">
      <div style="width: 40%;text-align: right">身份额度：</div>
      <ul style="text-align: left;width: 60%">
        <li><input type="checkbox" class="checkedBox" name="role" value="0">普通村民、科员、职员</li>
        <li style="margin-top: 14px"><input type="checkbox" class="checkedBox" name="role" value="1">村干部、（副）科长
        </li>
        <li style="margin-top: 14px;"><input type="checkbox" class="checkedBox" name="role" value="=2">（副）村长、处长
        </li>
      </ul>
    </div>
    <div style="display: flex;justify-content: center;margin-bottom: 14px">
      <div style="width: 40%;text-align: right;">经济收入额度：</div>
      <ul style="text-align: left;width: 60%">
        <li><input type="checkbox" class="checkedBoxS" name="income">分红权</li>
        <li style="margin-top: 14px"><input type="checkbox" class="checkedBoxS" name="income">工作单位
          <input type="checkbox" class="checkedBox" style="margin-left: 20px" name="incomeValue">10万
          <input type="checkbox" class="checkedBox" style="margin-left: 20px" name="incomeValue">20万
        </li>
        <li style="margin-top: 14px"><input type="checkbox" class="checkedBoxS" name="income">配偶人及配偶房产
          <select style="width: 50px;margin-left: 20px;margin-right: 8px" v-model="numOfTypeAProperties">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
          </select>套20万
          <select style="width: 50px;margin-left: 20px;margin-right: 8px" v-model="numOfTypeBProperties">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
          </select>套30万
        </li>
        <li style="margin-top: 14px"><input type="checkbox" class="checkedBoxS" name="income">家庭成员房产
          <select style="width: 50px;margin-left: 46px;margin-right: 8px" v-model="numOfFamilyProperties">
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
          </select>套
        </li>
      </ul>
    </div>
    <div style="display: flex;justify-content: center;">
      <div style="width: 40%;text-align: right">附加额度：</div>
      <ul style="text-align: left;width: 60%">
        <li><input type="checkbox" class="checkedBox" name="guarantee">村干部以上担保</li>
        <li style="margin-top: 14px"><input type="checkbox" class="checkedBox" name="guarantee">有房产的家庭成员担保
        </li>
      </ul>
    </div>
    <div style="display: flex;justify-content: center;">
      <div style="width: 40%;text-align: right"></div>
      <div style="margin-top: 20px;width: 60%;text-align: left">
        <button style="width: 84px;height: 36px;background-color: #4b83da;color: white;border: none;border-radius: 5px"
                @click="culAmount">确定
        </button>
      </div>
    </div>
    <div style="display: flex;justify-content: center;">
      <div style="width: 40%;text-align: right"></div>
      <div style="margin-top: 20px;width: 60%;text-align: left">
        <span>核定村民贷款额度为（元）：</span>
        <span style="font-size: 16px;color: red">{{ resultAmount }}</span>
      </div>
    </div>

  </div>


</template>

<script setup>
import {getCurrentInstance, onMounted, inject, watch, nextTick, ref} from 'vue'

const {proxy} = getCurrentInstance()
let identityQuota = ref(0) //身份额度
let hasDividend = ref(0)//是否有分红权
let hasWork = ref(0) //是否有工作单位
let workQuota = ref(0)//工资额度
let hasProperty = ref(0)//借款人及配偶房产
let numOfTypeAProperties = ref(0)//类型一房产有多少套
let numOfTypeBProperties = ref(0) //类型二房产有多少套
let hasFamilyProperty = ref(0)//家庭成员房产，0 无，1 有
let numOfFamilyProperties = ref(0)//家庭成员房产有多少套
let additionalQuota = ref('0')//附加额度：村干部以上担保/有房产的家庭成员担保，0 两项都没选，1 选了其中一项
let resultAmount = ref('')

// http://120.24.235.36:8089/finance-helper/calLoanQuota/0/1/1/1/1/0/1/1/2/1
function culAmount() {
  let role = document.getElementsByName('role')
  let income = document.getElementsByName('income')
  let incomeValue = document.getElementsByName('incomeValue')
  let guarantee = document.getElementsByName('guarantee')
  //多选
  for (let i = 0; i < guarantee.length; i++) {
    if (guarantee[i].checked) {
      additionalQuota.value++
    }
  }
  //单选
  for (let i = 0; i < role.length; i++) {
    if (role[i].checked) {
      identityQuota.value = role[i].value
      break
    }
  }
  //多选
  if (income[0].checked) {
    hasDividend.value = 1
  }
  if (income[1].checked) {
    hasWork.value = 1
  }
  if (income[2].checked) {
    hasProperty.value = 1
  }
  if (income[3].checked) {
    hasFamilyProperty.value = 1
  }
  //单选
  for (let i = 0; i < incomeValue.length; i++) {
    if (incomeValue[i].checked) {
      workQuota.value = i
      break
    }
  }


  // let restfulString=identityQuota.value+'/'+hasDividend.value+'/'+hasWork.value+'/'+workQuota.value+'/'+hasProperty.value+'/'+numOfTypeAProperties.value+'/'+numOfTypeBProperties.value+'/'+hasFamilyProperty.value+'/'+numOfFamilyProperties.value+'/'+additionalQuota.value
  let url = '/finance-helper/calLoanQuota/' + identityQuota.value + '/' + hasDividend.value + '/' + hasWork.value + '/' + workQuota.value + '/' + hasProperty.value + '/' + numOfTypeAProperties.value + '/' + numOfTypeBProperties.value + '/' + hasFamilyProperty.value + '/' + numOfFamilyProperties.value + '/' + additionalQuota.value
  proxy.$api.post(url, {}).then((res) => {
    console.log(res.data)
    resultAmount.value = res.data.data
  })
}


</script>

<style scoped>
ul, li {
  text-decoration: none;
  list-style: none;
  padding: 0;
  margin: 0;
}

.checkedBox:checked {
  border: 1px solid #1673ff;
}

.checkedBoxS:checked {
  background: #4b83da;
}

.checkedBoxS {
  width: 20px;
  height: 20px;
  margin: 0;
  padding: 0;
  border-radius: 4px;
  border: solid 1px #dddddd;
  background-color: #ffffff;
  /*border: none;*/
  position: relative;
  display: inline-block;
  vertical-align: top;
  cursor: default;
  -webkit-appearance: none;
  -webkit-user-select: none;
  user-select: none;
  -webkit-transition: background-color ease 0.1s;
  transition: background-color ease 0.1s;
  margin-right: 14px;
}

.checkedBox {
  width: 20px;
  height: 20px;
  background-color: #ffffff;
  border: solid 1px #dddddd;
  -webkit-border-radius: 50%;
  border-radius: 50%;
  font-size: 0.8rem;
  margin: 0;
  padding: 0;
  position: relative;
  display: inline-block;
  vertical-align: top;
  cursor: default;
  -webkit-appearance: none;
  -webkit-user-select: none;
  user-select: none;
  -webkit-transition: background-color ease 0.1s;
  transition: background-color ease 0.1s;
  margin-right: 10px;
}

.checkedBox:checked::after {
  content: '';
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  /*background:transparent;*/
  /*border:#1673ff solid 2px;*/
  border-top: none;
  border-right: none;
  height: 10px;
  width: 10px;
  border-radius: 5px;
  background-color: #1673ff;
  /*-moz-transform:rotate(-45deg);*/
  /*-ms-transform:rotate(-45deg);*/
  /*-webkit-transform:rotate(-45deg);*/
  /*transform:rotate(-45deg);*/
}

.checkedBoxS:checked::after {
  content: '';
  top: 3px;
  left: 3px;
  position: absolute;
  background: transparent;
  border: #fff solid 2px;
  border-top: none;
  border-right: none;
  height: 6px;
  width: 10px;
  -moz-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
}
</style>
