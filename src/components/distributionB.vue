<template>
  <div id="disB"></div>
</template>

<script setup>
import {getCurrentInstance,onMounted,inject,watch} from 'vue'
// import {watch} from "vue/dist/vue";
const {proxy}= getCurrentInstance()

// let cliAge=inject("cliAge")
const dataAll=inject('dataAll')

watch(() => dataAll, () => {

  console.log("inject没有变吗？？？", dataAll[0])

  mychartInit()
}, { deep: true })

setTimeout(()=>{
  mychartInit()
},1000)
function mychartInit() {

  // this.mychart = this.$root.$echarts.init(document.getElementById('A'))
  console.log('正在调用B组件',proxy.$echarts)


  let mychart=proxy.$echarts.init(document.getElementById('disB'))
  let option={
    tooltip:{
      trigger:'item'
    },
    // legend:{
    //   // top:'5%',
    //   left:'center'
    // },
    series:{
      name:'各年龄金额占比',
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
      data:dataAll[0]
    }
  }
  mychart.setOption(option,true)
}
onMounted(()=> {
  // mychartInit()
})
</script>

<style>
#disB{
  width:100%;
  height: 100%;
  border-radius: 10px;
}


</style>

