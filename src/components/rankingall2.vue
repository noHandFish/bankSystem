<template>
  <div id="ranking2"></div>
</template>

<script>
export default {
  name:"rankingall2",
  data()
  {
    return{}
  },
  methods:{
    mychartAInit()
    {
      //   console.log(this.$root.$echarts)
      // }
      console.log(this.$root.$data,"A")
      this.mychart = this.$root.$echarts.init(document.getElementById('ranking2'))
      let option={
        title: {
          text: '村名贷排行榜'
        },
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
          data: ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World']
        },
        series: [
          {
            name: '2011',
            type: 'bar',
            data: [18203, 23489, 29034, 104970, 131744, 630230]
          },
          {
            name: '2012',
            type: 'bar',
            data: [19325, 23438, 31000, 121594, 134141, 681807]
          }
        ]
      }
      this.mychart.setOption(option)
    }
  },
  mounted() {
    this.mychartAInit();
  }
}

</script>

<style>
#ranking2{
  width: 90%;
  height: 300px;
  border-radius: 10px;
  margin:30px auto;
}

</style>