<template>
  <div id="disA"></div>
</template>

<script setup>
import {getCurrentInstance, onMounted, inject,watch} from 'vue'

const {proxy}= getCurrentInstance()

// const cliRegion=inject('cliRegion')
const dataAll=inject('dataAll')

watch(() => dataAll, () => {

  console.log("inject没有变吗？？？", dataAll[1])

  mychartInit()
}, { deep: true })

setTimeout(()=>{
  mychartInit()
},1000)

function mychartInit() {

  console.log(dataAll[1],'没有数据吗？???')
  // this.mychart = this.$root.$echarts.init(document.getElementById('A'))

  let mychart=proxy.$echarts.init(document.getElementById('disA'))
  let option={
    tooltip:{
      trigger:'item'
    },
    // legend:{
    //   // top:'5%',
    //   left:'center'
    // },
    series:{
      name:'各行政区金额占比',
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
      data:dataAll[1]
    }
  }
  mychart.setOption(option,true)
}
onMounted(()=> {
  // mychartInit()
})
</script>

<style>
#disA{
  width: 100%;
  height: 100%;
  border-radius: 10px;
}


</style>

