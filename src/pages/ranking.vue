<template>
  <div class="bg">
    <div style="text-align: left">
      <button style="cursor: pointer" :class="regionOrBranchCount=='0'?'':'unclickButton'" id="0" @click="changeRorB">地区</button>
      <button style="cursor: pointer" :class="regionOrBranchCount=='1'?'':'unclickButton'" id="1" @click="changeRorB">支行</button>
      <select class="rankSelect">
        <option>全市</option>
        <option>南山区</option>
        <option>福田区</option>
      </select>
    </div>
    <div style="height: 50%;width: 101%;background-color: white;border-radius: 5px;margin-top: 20px">
      <div style="width: 100%;height: 12%;border-bottom: 1px solid #eff0f3">
        <div style="display: flex;float: left;justify-content: space-around;height: 100%;margin-left: 10px;width: 30%;
                    font-size: 16px;color: #6f706f">
          <div  :class="type=='loan_balance'?'clickType':'unclickType'" @click="changeType" id="loan_balance">利润额</div>
          <div  :class="type=='profit_contribution'?'clickType':'unclickType'" @click="changeType" id="profit_contribution">利润贡献度</div>
          <div  :class="type=='borrowers_number'?'clickType':'unclickType'" @click="changeType" id="borrowers_number">贷款人数</div>
          <div  :class="type=='couples_number'?'clickType':'unclickType'" @click="changeType" id="couples_number">夫妻数</div>
        </div>
        <div style="float: right;margin-right: 20px;font-size: 16px;color: #6f706f;height: 100%">
          <span style="line-height: 300%" @click="searchMore">更多</span>
        </div>
      </div>
      <div style="width: 100%;height: 80%;background-color: white">
        <rankingAll></rankingAll>
      </div>
    </div>
  </div>

</template>
<script setup>
import {getCurrentInstance, onMounted, ref, provide} from 'vue'
import rankingAll from '@/components/rankingall'
const {proxy}= getCurrentInstance()

const regionOrBranchCount=ref('0')
const regionOrBranch=ref('南山区')
const type=ref('loan_balance')

let keyD=ref([])
let valueD=ref([])
onMounted(()=>{
  changeSort()
})

function changeRorB(e){
  regionOrBranchCount.value=e.currentTarget.id
  changeSort()
}
function changeType(e){
  type.value=e.currentTarget.id
  changeSort()
}
function changeSort(){
  proxy.$api.post('/loan_rank',{
    "regionOrBranchCount":regionOrBranchCount.value,
    "regionOrBranch": regionOrBranch.value,
    "type": type.value,
  }).then(res=>{
    keyD.value.length=0
    valueD.value.length=0
    let sortData=res.data.data
    sortData.sort((a,b)=>{ return a.value-b.value})
    for(let i=0;i<sortData.length;i++)
    {
      keyD.value[i]=sortData[i].key
      valueD.value[i]=sortData[i].value
    }

  })
}
provide('keyD',keyD.value)
provide('valueD',valueD.value)
</script>


<style scoped>

.clickType{
  color: black;
  border-bottom: 4px solid #2986fb;
  cursor:pointer;
  width: 20%;
  height: 100%;
  line-height: 300%;
  font-size: 14px;
}
.unclickType{
  cursor:pointer;
  width: 20%;
  height: 100%;
  line-height: 300%;
  font-size: 14px;
}
.clickButton{

}
.bg{
  background-color: #eff0f4;
  height: 95%;
  width: 96%;
  margin: 20px 20px;
}
button{
  background-color: #2986fb;
  border: none;
  width: 100px;
  height: 40px;
  color: white;
  font-size: 16px;
  border-radius: 5px;
  margin-right: 30px;
}
.unclickButton{
  background-color:white;
  border: 1px solid #2986fb;
  color: #2986fb;
}
.rankSelect{
  width: 240px;
  height: 38px;
  border: 1px solid #6f706f;
  border-radius: 5px;
}

</style>

