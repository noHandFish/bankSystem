<template>
  <div id="disD"></div>
</template>

<script setup>
import {getCurrentInstance, onMounted,inject,watch} from 'vue'
// import {watch} from "vue/dist/vue";

const {proxy}= getCurrentInstance()

const dataAll=inject('dataAll')

watch(() => dataAll, () => {

  console.log("inject没有变吗？？？", dataAll[3])

  mychartInit()
}, { deep: true })

setTimeout(()=>{
  mychartInit()
},1000)
// const data=[
//   {value:1048,name:'18-25'},
//   {value:48,name:'30-40'},
//   {value:148,name:'50-60'},
//   {value:1448,name:'60-65'},
//   {value:1448,name:'25-30'},
//   {value:1448,name:'40-50'},
// ]
// "cliAge": {
//   "18-25": 1672866143075652.2,
//       "30-40": 8805297082.55201,
//       "50-60": 311834377.2395684,
//       "60-65": 28.125564931478014,
//       "25-30": 844.1574883885241,
//       "40-50": 1007124250532.1442
// },
function mychartInit() {

  // this.mychart = this.$root.$echarts.init(document.getElementById('A'))
  console.log('正在调用D组件',proxy.$echarts)
  let mychart=proxy.$echarts.init(document.getElementById('disD'))
  let option={
    tooltip:{
      trigger:'item'
    },
    // legend:{
    //   // top:'5%',
    //   left:'center'
    // },
    series:{
      name:'性别',
      type:'pie',
      radius:['55%','76%'],
      avoidLabelOverlap:false,
      label:{
        show:true,
        // position:'center'
      },
      labelLine:{
        show:true
      },
      data:dataAll[3]
    }
  }
  mychart.setOption(option,true)
}
onMounted(()=> {
  // mychartInit()
})
</script>

<style>
#disD{
  width: 100%;
  height: 100%;
  border-radius: 10px;
}


</style>

