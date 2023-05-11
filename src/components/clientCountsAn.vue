<template>
  <div id="clientCountsAn"></div>
</template>


<script setup>
import {getCurrentInstance,onMounted,inject,watch,ref} from 'vue'
const {proxy}= getCurrentInstance()
let clientCounts=[]
let xCostData=ref([])
const props = defineProps({
  bank: {
    type: Array,
    default: () => [],
  },
  time: {
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
  getData(e)
}, { deep: true })
function getData(e){

	let url='/finance-analysis/getCostEffectiveness/'
		proxy.$api.post(url,{
			"timeStart":"2022-01-17",
			"timeEnd":"2029-01-17",
			"subbranch":e.bank,
			"interval":e.time//时间间隔
		}).then((res)=>{

			  console.log(res.data.data)
			  console.log(res.data.data.Effective)
		  xCostData.value=Object.keys(res.data.data.Effective)
		  console.log(xCostData.value,'坐标轴')
		  let data=res.data.data.Effective
		
		  for(let i=0;i<xCostData.value.length;i++)
		  {
		    clientCounts[i]=data[xCostData.value[i]].clientCounts
		  }
		  console.log(clientCounts)
		  mychartInit(e.type)
	})
}
onMounted(()=>{
	console.log(props)
	getData(props)
})

function mychartInit(type) {
	if(type=='曲线图'){
		type = 'line'
	}
	else if(type=='柱状图'){
		type = 'bar'
	}
  let mychart = proxy.$echarts.init(document.getElementById('clientCountsAn'))
  let option = {
    xAxis: {
      type: 'category',
      data: xCostData.value
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: clientCounts,
        type: type
      }
    ]
  }
  mychart.setOption(option)
}


</script>

<style>
#clientCountsAn{
  width: 100%;
  height: 100%;
  border-radius: 10px;
  margin:20px auto;
}

</style>