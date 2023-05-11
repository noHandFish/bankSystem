<template>
  <div id="A"></div>
</template>


<script setup>
import {getCurrentInstance, onMounted, inject, watch, nextTick} from 'vue'
const {proxy}= getCurrentInstance()

let dataA=inject('dataA')

// const data=computed(()=>dataA.dataA)

watch(() => dataA, () => {
  console.log("inject", dataA)
  mychartInit()
}, { deep: true })

setTimeout(()=>{
  mychartInit()
},1000)
function mychartInit() {
  // this.mychart = this.$root.$echarts.init(document.getElementById('A'))
  console.log('正在调用A组件',proxy.$echarts)
  let mychart=proxy.$echarts.init(document.getElementById('A'))
  let option={
              legend:{},
              tooltip:{},
              dataset:{
                source:dataA
              },
              xAxis:{type:'category'},
              yAxis:{},
              series:[{type:'bar'},{type:'bar'},{type:'bar'}]
            }
            mychart.setOption(option,true)
}
onMounted(()=>{
})


  // export default {
  //   name:"allA",
  //   data()
  //   {
  //     return{}
  //   },
  //   methods:{
  //     mychartAInit()
  //       {
  //       //   console.log(this.$root.$echarts)
  //       // }
  //         console.log(this.$root.$data,"A")
  //         this.mychart = this.$root.$echarts.init(document.getElementById('A'))
          // let option={
          //   title: {
          //     text: 'A区',
          //     subtext: '户数、贷款余额、利息收入'
          //   },
          //   tooltip: {
          //     trigger: 'axis'
          //   },
          //   legend: {
          //     data: ['户数', '贷款余额','利息收入']
          //   },
          //   toolbox: {
          //     show: true,
          //     feature: {
          //       // dataView: { show: true, readOnly: false },
          //       // magicType: { show: true, type: ['line', 'bar'] },
          //       // restore: { show: true },
          //       // saveAsImage: { show: true }
          //     }
          //   },
          //   calculable: true,
          //   xAxis: [
          //     {
          //       type: 'category',
          //       // prettier-ignore
          //       data: ['2017', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
          //     }
          //   ],
          //   yAxis: [
          //     {
          //       type: 'value'
          //     }
          //   ],
          //   series: [
          //     {
          //       name: '户数',
          //       type: 'bar',
          //       data: [
          //         2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
          //       ],
          //       markLine: {
          //         data: [{ type: 'average', name: 'Avg' }]
          //       }
          //     },
          //     {
          //       name: '贷款余额',
          //       type: 'bar',
          //       data: [
          //         2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
          //       ],
          //       markLine: {
          //         data: [{ type: 'average', name: 'Avg' }]
          //       }
          //     },
          //     {
          //       name: '利息收入',
          //       type: 'bar',
          //       data: [
          //         2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
          //       ],
          //       markLine: {
          //         data: [{ type: 'average', name: 'Avg' }]
          //       }
          //     }
          //   ]
          // }
  //         let option={
  //           legend:{},
  //           tooltip:{},
  //           dataset:{
  //             source:[
  //                 ['type','户数','贷款余额','利息收入'],
  //                 ['2019',414,809,827],
  //                 ['2020',654,987,98],
  //                 ['2020',789,765,213],
  //             ]
  //           },
  //           xAxis:{type:'year'},
  //           yAxis:{},
  //           series:[{type:'bar'},{type:'bar'},{type:'bar'}]
  //         }
  //         this.mychart.setOption(option)
  //       }
  //   },
  //   mounted() {
  //     this.mychartAInit();
  //   }
  // }

</script>

<style>
#A{
  width: 100%;
  height: 250px;
  border-radius: 10px;
  margin:20px auto;
}


</style>

