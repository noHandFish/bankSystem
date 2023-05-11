<template>
  <div id="loanAmountAn"></div>
</template>


<script setup>
import {getCurrentInstance,onMounted,inject,watch,ref} from 'vue'
const {proxy}= getCurrentInstance()
const props = defineProps({
  xData: {
    type: Array,
    default: () => [],
  },
  yData: {
    type: Array,
    default: () => [],
  },
  type: {
    type: Array,
    default: () => [],
  },

})

watch(() => props, (e) => {
  console.log(e)
  console.log(props.xData)
  console.log(props.yData,props.type)
  mychartInit(props.xData,props.yData,props.type)
}, { deep: true })

onMounted(()=>{
	mychartInit(props.xData,props.yData,props.type)
})
function mychartInit(x,y,type) {
console.log(type)
  let mychart = proxy.$echarts.init(document.getElementById('loanAmountAn'))
  let option = {
    xAxis: {
      type: 'category',
      data: x
    },
    yAxis: {
      type: 'value'
    },  
    series: [
      {
        data: y,
        type: type[0]? type[0]:type
      }
    ]
  }
  mychart.setOption(option)
}


</script>

<style>
#loanAmountAn{
  width: 100%;
  height: 100%;
  border-radius: 10px;
  margin:20px auto;
}

</style>