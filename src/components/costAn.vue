<template>
  <div id="cost"></div>
</template>


<script setup>
import {getCurrentInstance,onMounted,inject,watch} from 'vue'
const {proxy}= getCurrentInstance()
let yData=inject('yCostData')
console.log(yData)
watch(() => yData, (e) => {
  console.log(e)
  mychartInit()
}, { deep: true })
function mychartInit() {
  let mychart = proxy.$echarts.init(document.getElementById('cost'))
  let option = {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: yData,
        type: 'line'
      }
    ]
  }
  mychart.setOption(option)
}
onMounted(()=>{
  mychartInit()
})

</script>

<style>
#cost{
  width: 100%;
  height: 100%;
  border-radius: 10px;
  margin:20px auto;
}

</style>