<template>
  <div class="bg">
    <!--  两个下拉框  -->
    <select class="selector" v-model="subbranch" @change="selectSubbranchOrInterval" v-show="!showCharts">
      <option disabled selected value>选择支行</option>
      <option value="全行">全行</option>
      <option value="龙华支行">龙华支行</option>
      <option value="石岩支行">石岩支行</option>
      <option value="新安支行">新安支行</option>
      <option value="坪地支行">坪地支行</option>
      <option value="宝龙支行">宝龙支行</option>
      <option value="龙城支行">龙城支行</option>
    </select>
    <select class="selector" style="left: 227px;" v-model="interval" @change="selectSubbranchOrInterval"
            v-show="!showCharts">
      <option disabled selected value>时间频率</option>
      <option value="本年">本年</option>
      <option value="本月">本月</option>
      <option value="本周">本周</option>
      <option value="本日">本日</option>
    </select>

    <!--  小背景  -->
    <div class="bg-inner">
      <!--   左上角蓝色框   -->
      <div class="box box1" v-show="!showCharts" @click="clickCard('A')">
        <div style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;">
          <div>
            <div class="number">{{ homePageData.loanBalance }}</div>
            <div class="text-under-number">账户余额(元)</div>
          </div>
        </div>
        <div
            style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;border-left: solid 1px #e5e9ed;border-right: solid 1px #e5e9ed;">
          <div>
            <div class="number">{{ homePageData.clientCount }}</div>
            <div class="text-under-number">户数(户)</div>
          </div>
        </div>
        <div style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;">
          <div>
            <div class="number">{{ homePageData.rateIncome }}</div>
            <div class="text-under-number">利息收入(元)</div>
          </div>
        </div>
      </div>
      <!--   右上角红色框   -->
      <div class="box box2" v-show="!showCharts" @click="clickCard('B')">
        <div
            style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;border-right: solid 1px #e5e9ed;">
          <div>
            <div class="number">{{ homePageData.averLoanAmount }}</div>
            <div class="text-under-number">户均贷款(元)</div>
          </div>
        </div>
        <div style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;">
          <div>
            <div class="number">{{ homePageData.averRate }}%</div>
            <div class="text-under-number">平均利率</div>
          </div>
        </div>
      </div>
      <!--   左下角青色框   -->
      <div class="box box3" style="justify-content: center" v-show="!showCharts" @click="clickCard('C')">
        <span class="text-under-number" style="margin-top: 0;">APRU值:</span>
        <span class="number" style="margin-left: 12px;">{{ homePageData.arpu }}%</span>
      </div>
      <!--   右下角灰色框   -->
      <div class="box box4" v-show="!showCharts" @click="clickCard('D')">
        <div
            style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;border-right: solid 1px #e5e9ed;">
          <div>
            <div class="number">{{ homePageData.overdueLoanAmount }}</div>
            <div class="text-under-number">逾期贷款额度(元)</div>
          </div>
        </div>
        <div style="flex: 1;display: flex;justify-content: center;align-items: center;height: 45%;">
          <div>
            <div class="number">{{ homePageData.totalInterestOwed }}</div>
            <div class="text-under-number">欠息合计(元)</div>
          </div>
        </div>
      </div>

      <div v-if="showCharts" style="width: 100%; height: 100%;position: relative">
        <el-header width="100%" height="30px">
          <p style="text-align: left">详情</p>
        </el-header>
        <img
            src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAkACQAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAxADEDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9DPiH8WLfwLMyzyIig4+bFdV4T8XWPizTYri0mEpZdzY7VzHxY+FVn4+0iVBEv2o5O9sV82eF/FmtfBXxSdP1FpXs5ZtibUOAv1/CgD7XorD8J+LLPxVpsd1bSKQwHy7gTWtdXUdnC8sjqiqMnccUAF1dxWcJllbag715Hd/H7T4/Ei6XFPGzmUR44rzv9oD41XMvn6JoMkjXTD5WhBcfpWp8DfgKfLi1/wAQxrcz3AEqZwCre9AHvH/CQJ6iirf9i2v/ADzFFAF+vOvit8K7HxxpUjbFjuI0+QouCTXotQ3V1HZwPLIyqqjJycUAfHPgnXtZ+CvidbHUlkXSo+TNM2e/vXTfFr49SeI7G107wyy3st0PLl8s4K5zWZ+0h4y0jxpNP4d0+B5dUbkSp8y+nYV5j8N9LX4T61aXGu28kguJAYyqkbfrx7UAe7/AH4EtYiLXdd3yX27Pk3GWGDz3r6NiiSGMJGoRF6KowBWL4T8VWXijS47i0dduANu4E9K3aACiiigCG6uo7OFpJW2KBnJr5j+OPx0mu7hdB0DF1LOfJcxnlTXS/tEeKtfsbOWz0d3SQtjIUkfpWZ8Cf2fxZyNr3iKFZru4ImjYcEGgBP2f/gS+nmHxBrheS+zzDP8AN716V8U/hPYeNNHZUjSCaFD5excHNejKoVQo6AYpaAPirwn4q1n4JeJ00+/WRrBSSZJTx1r638I+LrLxZpkNzbTLIzLuIXtXLfFr4S2HxB0qZTAGvGGAzY9K+ePh7feKfh34ybSi7iy84RqqocYoA+zqK5r+1rn1P5UUAc58QP8Aj6b/AHv613Ohf8ge1/3KKKAL9FFFACV5BqH/ACNH/bWiigD0WiiigD//2Q=="
            class="close-ico" @click="clickClose">
        <hr color="#e5e9ed">
        <div style="height: 100%;width: 100%;position: relative">
          <div style="position: absolute;top: -10px;right: 2%;">
            <span class="interval-charts" :style="intervalForCharts === 1 ? 'color: #4e8bff;' : ''"
                  @click="changIntervalForCharts(1)">按年</span>
            <span class="interval-charts" :style="intervalForCharts === 2 ? 'color: #4e8bff;' : ''"
                  @click="changIntervalForCharts(2)">按月</span>
            <span class="interval-charts" style="margin-right: 32px"
                  :style="intervalForCharts === 3 ? 'color: #4e8bff;' : ''"
                  @click="changIntervalForCharts(3)">按天</span>
            <el-date-picker v-model="startDate" type="date" placeholder="开始日期" style="width: 133px"
                            value-format="YYYY-MM-DD" :clearable="false" @change="changeChartSelect"/>
            <span style="margin-left: 8px;margin-right: 8px;">至</span>
            <el-date-picker v-model="endDate" type="date" placeholder="结束日期" style="width: 133px"
                            value-format="YYYY-MM-DD" :clearable="false" @change="changeChartSelect"/>
            <el-select v-model="subbranchForCharts" style="width: 133px;margin-left: 27px" placeholder="全行"
                       @change="changeChartSelect">
              <el-option value="全行">全行</el-option>
              <el-option value="龙华支行">龙华支行</el-option>
              <el-option value="石岩支行">石岩支行</el-option>
              <el-option value="新安支行">新安支行</el-option>
              <el-option value="坪地支行">坪地支行</el-option>
              <el-option value="宝龙支行">宝龙支行</el-option>
              <el-option value="龙城支行">龙城支行</el-option>
            </el-select>
          </div>
          <barchartA :dataset="dataA" v-if="showType === 'A'" ref="barchartA" style="position: relative;top: 16px"/>
          <barchartB :dataset="dataB" v-if="showType === 'B'" ref="barchartB" style="position: relative;top: 16px"/>
          <barchartC :dataset="dataC" v-if="showType === 'C'" ref="barchartB" style="position: relative;top: 16px"/>
          <barchartD :dataset="dataD" v-if="showType === 'D'" ref="barchartB" style="position: relative;top: 16px"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance} from "vue";
import barchartA from '@/pages/new_overview/myEchartsA'
import barchartB from '@/pages/new_overview/myEchartsB'
import barchartC from '@/pages/new_overview/myEchartsC'
import barchartD from '@/pages/new_overview/myEchartsD'
export default {
  name: "NewOverview",
  components: {
    barchartA,
    barchartB,
    barchartC,
    barchartD
  },
  data: () => {
    return {
      subbranch: '',
      interval: '',
      homePageData: {},
      showCharts: false,
      showType: '',
      dataA: [],
      dataB: [],
      dataC: [],
      dataD: [],
      myProxy: null,
      startDate: '',
      endDate: '',
      subbranchForCharts: '全行',
      intervalForCharts: 2
    }
  },
  methods: {
    selectSubbranchOrInterval() {
      this.requestData()
    },
    clickCard(type) {
      this.showCharts = true;
      this.showType = type;
      this.requestChartData().then(res => {
        this.transformChartData(res.data.data.macroInfo)
        this.$nextTick(() => {
          if(type === 'A')
            this.$refs.barchartA.resize()
          else if(type === 'B')
            this.$refs.barchartB.resize()
          else if(type === 'C')
            this.$refs.barchartC.resize()
          else
            this.$refs.barchartD.resize()
        })
      })
    },
    clickClose() {
      this.showCharts = false;
    },
    requestData() {
      let realSubbranch = this.subbranch === '' ? '全行' : this.subbranch
      let realInterval = this.interval === '' ? '本年' : this.interval
      this.myProxy.$api.post('/finance-analysis/overview/time', {
        "subbranch": realSubbranch,
        "time": realInterval,
        "interval": '按' + realInterval[1],
        "manager": "全部"
      }).then(res => {
        if (res.data.data.macroInfo.length === 0)
          alert('暂无数据')
        else {
          this.homePageData = res.data.data.macroInfo[0]
          this.homePageData.averRate = this.homePageData.averRate.toFixed(2)
          this.homePageData.arpu = this.homePageData.arpu.toFixed(2)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    dateFormat(date) {
      let year = date.getFullYear(), month = date.getMonth() + 1, day = date.getDate()
      month = month < 10 ? '0' + month : '' + month
      day = day < 10 ? '0' + day : '' + day
      return year + '-' + month + '-' + day
    },
    changIntervalForCharts(arg) {
      this.intervalForCharts = arg
      this.requestChartData().then(res => {
        this.transformChartData(res.data.data.macroInfo)
        this.$nextTick(() => {
          if(this.showType === 'A')
          this.$refs.barchartA.resize()
          else if(this.showType === 'B')
            this.$refs.barchartB.resize()
          else if(this.showType === 'C')
            this.$refs.barchartC.resize()
          else
            this.$refs.barchartD.resize()
        })
      })
    },
    async requestChartData() {
      let tempList = ['按年', '按月', '按天']
      return this.myProxy.$api.post('/finance-analysis/overview/startAndEndTime', {
        "subbranch": this.subbranchForCharts,
        "start": this.startDate,
        "end": this.endDate,
        "interval": tempList[this.intervalForCharts - 1],
        "manager": "全部"
      })
    },
    changeChartSelect() {
      this.requestChartData().then(res => {
        this.transformChartData(res.data.data.macroInfo)
        this.$nextTick(() => {
          if(this.showType === 'A')
            this.$refs.barchartA.resize()
          else if(this.showType === 'B')
            this.$refs.barchartB.resize()
          else if(this.showType === 'C')
            this.$refs.barchartC.resize()
          else
            this.$refs.barchartD.resize()
        })
      })
    },
    transformChartData(arr) {
      let tempList = []
      if(this.showType === 'A') {
        console.log(111)
        tempList = [['type', '户数', '贷款余额', '利息收入']]
        for (let i = 0; i < arr.length; i += 1) {
          let item = [arr[i].date, arr[i].clientCount, arr[i].loanBalance, arr[i].rateIncome]
          tempList.push(item)
        }
        this.dataA = tempList
      } else if(this.showType === 'B') {
        console.log(222)
        tempList = [['type', '户均贷款', '户均利率']]
        for (let i = 0; i < arr.length; i += 1) {
          let item = [arr[i].date, arr[i].averLoanAmount, arr[i].averRate]
          tempList.push(item)
        }
        this.dataB = tempList
      } else if(this.showType === 'C') {
        console.log(333)
        tempList = [['type', 'ARPU值']]
        for (let i = 0; i < arr.length; i += 1) {
          let item = [arr[i].date, arr[i].arpu]
          tempList.push(item)
        }
        this.dataC = tempList
      } else if(this.showType === 'D') {
        console.log(444)
        tempList = [['type', '与其贷款额度', '欠息合计']]
        for (let i = 0; i < arr.length; i += 1) {
          let item = [arr[i].date, arr[i].overdueLoanAmount, arr[i].totalInterestOwed]
          tempList.push(item)
        }
        this.dataD = tempList
      }
    },
    toThousands(num) {
      return num.toString().replace(/\d+/, function(n) {
        return n.replace(/(\d)(?=(?:\d{3})+$)/g, '$1,')
      })
    }
  },
  mounted() {
    this.myProxy = getCurrentInstance().proxy
    this.requestData()
    let now = new Date(), start = new Date()
    start.setFullYear(now.getFullYear() - 1)
    this.startDate = this.dateFormat(start)
    this.endDate = this.dateFormat(now)
  }
}
</script>

<style scoped>
.bg {
  position: relative;
  background-color: #eff0f4;
  height: 95%;
  width: 96%;
  margin: 20px 20px;
  overflow-y: auto;
}

/* 两个下拉选择框样式 */
.selector {
  position: absolute;
  top: 0;
  left: 0;
  width: 209px;
  height: 39px;
  background: #ffffff;
  border: 1px solid #adadad;
  border-radius: 5px;
  outline: none;
  padding: 0 8px;
}

.bg-inner {
  position: absolute;
  top: 55px;
  width: 90%;
  height: 72%;
  background: #ffffff;
  border: 1px solid #e5e7e9;
  border-radius: 10px;
}

.box {
  position: absolute;
  display: flex;
  width: 37%;
  height: 30%;
  border-radius: 6px;
  align-items: center;
  transition: all 100ms;
}

.box:hover {
  width: 40%;
  height: 32%;
  box-shadow: 6.69px 7.43px 19.2px 0.4px rgba(0, 0, 0, 0.23);
}

.box1, .box2, .box3 {
  left: calc(50% - 37% - 12px);
  top: calc(50% - 30% - 12px);
  background: linear-gradient(-60deg, #678efc 5%, #3576fd 94%);
}

.box1:hover, .box3:hover {
  left: calc(50% - 40% - 12px);
  top: calc(50% - 32% - 12px);
}

.box2 {
  left: calc(50% + 12px);
  background: linear-gradient(-60deg, #f89186 5%, #fd6362 94%);;
}

.box2:hover {
  top: calc(50% - 32% - 12px);
}

.box3 {
  top: calc(50% + 12px);
  background: linear-gradient(-60deg, #31b3db 5%, #308cda 94%);;
}

.box3:hover {
  top: calc(50% + 12px);
}

.box4 {
  left: calc(50% + 12px);
  top: calc(50% + 12px);
  background: linear-gradient(-60deg, #809dcc 5%, #697fa5 94%);
}

.number, .text-under-number {
  font-size: 25px;
  font-family: Source Han Sans CN, Source Han Sans CN-Regular, serif;
  font-weight: 400;
  color: #ffffff;
}

.text-under-number {
  font-size: 14px;
  margin-top: 16px;
}

.close-ico {
  position: absolute;
  height: 14px;
  width: 14px;
  top: 2px;
  right: 28px;
  cursor: pointer;
}

.interval-charts {
  font-size: 14px;
  font-family: Source Han Sans CN, Source Han Sans CN-Bold;
  margin-right: 16px;
  cursor: pointer;
}
</style>
