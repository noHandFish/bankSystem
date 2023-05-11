<template>
  <div id="ranking"></div>
</template>


<script setup>
import {getCurrentInstance, onMounted, inject,watch} from 'vue'
// import {watch} from "vue/dist/vue";
const {proxy}= getCurrentInstance()
let keyD=[]
let valueD=[]

keyD=inject('keyD')
valueD=inject('valueD')
watch(() => keyD, () => {
  console.log("inject", keyD)
  mychartInit()
}, { deep: true })

watch(() => valueD, () => {
  console.log("inject", valueD)
  mychartInit()
}, { deep: true })


setTimeout(()=>{
  mychartInit()
},1000)

function mychartInit() {

  let mychart=proxy.$echarts.init(document.getElementById('ranking'))
  let dataX=[]
  let dataY=[]
  for(let i=0;i<keyD.length;i++)
  {
    dataY[i]=keyD[i]
    dataX[i]=valueD[i]
  }
  console.log(keyD.length,'xxxxx')
  let option= {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {},
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'value',
      boundaryGap: [0, 0.01]
    },
    yAxis: {
      type: 'category',
      data: dataY
    },
    series: [
      {
        name: '2022',
        type: 'bar',
        data:dataX
      },
    ]
  }
  mychart.setOption(option)
}
onMounted(()=> {
})
</script>

<style>
#ranking{
  width: 90%;
  height: 100%;
  border-radius: 10px;
  margin:30px auto;
}

</style>