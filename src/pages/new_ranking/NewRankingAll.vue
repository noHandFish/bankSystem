<template>
  <div id="ranking"/>
</template>

<script>
import { getCurrentInstance } from 'vue'

export default {
  name: "NewRankingAll",
  props: {
    keyD: {
      type: Array,
      default: []
    },
    valueD: {
      type: Array,
      default: []
    }
  },
  data: () => {
    return {
      myChart: {},
      proxy: {}
    }
  },
  methods: {
    myChartInit() {
      let option = {
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
          data: this.keyD
        },
        series: [
          {
            name: '2022',
            type: 'bar',
            data: this.valueD
          },
        ]
      }
      this.myChart.setOption(option)
    },
    myChartInitByParam(newKey, newValue) {
      let option = {
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
          data: newKey
        },
        series: [
          {
            name: new Date().getFullYear(),
            type: 'bar',
            data: newValue
          },
        ]
      }
      this.myChart.setOption(option)
    }
  },
  watch: {
    keyD(newKey) {
      this.myChartInitByParam(newKey, this.valueD)
    },
    valueD(newValue) {
      this.myChartInitByParam(this.keyD, newValue)
    }
  },
  mounted() {
    this.proxy = getCurrentInstance().proxy
    this.myChart = this.proxy.$echarts.init(document.getElementById('ranking'))
    this.myChartInit()
  }
}
</script>

<style scoped>
#ranking{
  width: 90%;
  height: 100%;
  border-radius: 10px;
  margin:30px auto;
}
</style>
