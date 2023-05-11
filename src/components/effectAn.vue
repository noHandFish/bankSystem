<template>
  <div id="eff"></div>
</template>
<script setup>
import {getCurrentInstance,onMounted,inject,watch,onUnmounted,onActivated,ref} from 'vue'
// import {watch} from "vue/dist/vue";
const {proxy}= getCurrentInstance()
// const xdata = ref([])
// const ydata = ref([])
// const type = ref([])
const props = defineProps({
  xdata: {
    type: Array,
    default: () => [],
  },
  ydata: {
    type: Array,
    default: () => [],
  },
  type: {
    type: Array,
    default: () => [],
  },
})
watch(() => props, (e) => {
  mychartInit(e.xdata,e.ydata,e.type)
}, { deep: true })
onMounted(()=>{
	// if(sessionStorage.getItem("fundCost")){
	// 	xdata.value = sessionStorage.getItem("numList")
	// 	ydata.value = sessionStorage.getItem("fundCost")
	// 	xdata.value= xdata.value.split(",")
	// 	ydata.value = ydata.value.split(",")
	// 	// sessionStorage.clear()
	// 	}
})

// watch(() => xdata.value, (e) => {
//   mychartInit()
//   console.log(1)
// }, { deep: true })
// watch(() => ydata.value, (e) => {
//   mychartInit()
// }, { deep: true })



function mychartInit(xdata,ydata,type) {
	if(type=='曲线图'){
		type = 'line'
	}
	else if(type=='柱状图'){
		type = 'bar'
	}
  let mychart = proxy.$echarts.init(document.getElementById('eff'))
  // console.log(yData,xData,indexT,'thisData')
  let option = {
    xAxis: {
      type: 'category',
      data: xdata,
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data:ydata,
        type: type
      }
    ]
  }
  mychart.setOption(option)
}


</script>

<style>
#eff{
  width: 100%;
  height: 100%;
  border-radius: 10px;
  margin:20px auto;
}

</style>