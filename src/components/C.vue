<template>
  <div id="CC"></div>
</template>

<script setup>
import {getCurrentInstance,onMounted,inject,watch} from 'vue'
// import {watch} from "vue/dist/vue";
const {proxy}= getCurrentInstance()
// let dataSource=inject('dataSource')
let dataC=inject('dataC')
 
watch(() => dataC, () => {
  console.log("inject", dataC)
  mychartInit1()
}, { deep: true })

setTimeout(()=>{
  mychartInit1()
},1000)
function mychartInit1() {

  // this.mychart = this.$root.$echarts.init(document.getElementById('A'))
  console.log('正在调用AA组件',proxy.$echarts)
  let mycharts=proxy.$echarts.init(document.getElementById('CC'))
  let option={
    legend:{},
    tooltip:{},
    dataset:{
      source:dataC
    },
    xAxis:{type:'category'},
    yAxis:{},
    series:[{type:'line'},{type:'line'},{type:'line'}]
  }
  mycharts.setOption(option,true)
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
//           series:[{type:''},{type:'bar'},{type:'bar'}]
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
#CC{
  width: 100%;
  height: 250px;
  border-radius: 10px;
  margin:20px auto;
}

</style>