<template>
  <div id="D"></div>
</template>

<script>
import {getCurrentInstance} from 'vue'
let that = null
export default {
  name: "myEchartsD",
  data: () => {
    return {
      myChart: {},
      myProxy: null
    }
  },
  props: {
    dataset: {
      type: Array,
      default: null
    }
  },
  methods: {
    myChartInit() {
      this.myChart = this.myProxy.$echarts.init(document.getElementById('D'))
      let option = {
        legend: {
          left: '8%'
        },
        tooltip: {},
        dataset: {
          source: this.dataset
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{type: 'bar'}, {type: 'bar'}]
      }
      this.myChart.setOption(option, true)
    },
    myChartInitByArgs(set) {
      let option = {
        legend: {
          left: '8%'
        },
        tooltip: {},
        dataset: {
          source: set
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{type: 'bar'}, {type: 'bar'}]
      }
      this.myChart.setOption(option, true)
    },
    resize() {
      let option = {
        legend: {
          left: '8%'
        },
        tooltip: {},
        dataset: {
          source: this.dataset
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{type: 'bar'}, {type: 'bar'}]
      }
      this.myChart.setOption(option, true)
    }
  },
  watch: {
    dataset: (newData) => {
      that.myChartInitByArgs(newData)
    }
  },
  mounted() {
    const {proxy} = getCurrentInstance()
    this.myProxy = proxy
    that = this
    this.myChartInit()
  }
}
</script>

<style scoped>
#D {
  width: 100%;
  height: 84%;
  border-radius: 10px;
  margin: 20px auto;
}
</style>

