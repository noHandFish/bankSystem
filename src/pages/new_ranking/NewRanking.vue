<template>
  <div class="bg">
    <div style="text-align: left">
      <button style="cursor: pointer" :class="regionOrBranchCount==='0'?'':'unclickButton'" id="0" @click="changeRorB">
        地区
      </button>
      <button style="cursor: pointer" :class="regionOrBranchCount==='1'?'':'unclickButton'" id="1" @click="changeRorB">
        支行
      </button>
      <select class="rankSelect" v-model="area" v-show="regionOrBranchCount === '0'" @change="getChartData">
        <option>全市</option>
        <option>南山区</option>
        <option>福田区</option>
      </select>
      <select class="rankSelect" style="margin-left: 16px" v-model="interval" @change="getChartData">
        <option>按年</option>
        <option>按月</option>
        <option>按周</option>
        <option>按天</option>
      </select>
    </div>
    <div style="width: 101%;background-color: white;border-radius: 5px;margin-top: 20px">
      <div style="width: 100%;border-bottom: 1px solid #eff0f3;display: flex;justify-content: space-between">
        <div style="display: flex;justify-content: space-around;height: 100%;margin-left: 10px;width: 32%;
                    font-size: 16px;color: #6f706f">
          <div :class="dataType==='贷款额'?'clickType':'unclickType'" @click="changeType" id="贷款额">贷款额
          </div>
          <div :class="dataType==='利润贡献度'?'clickType':'unclickType'" @click="changeType"
               id="利润贡献度">利润贡献度
          </div>
          <div :class="dataType==='贷款人数'?'clickType':'unclickType'" @click="changeType" id="贷款人数">
            贷款人数
          </div>
          <div :class="dataType==='夫妻数'?'clickType':'unclickType'" @click="changeType" id="夫妻数">
            夫妻数
          </div>
        </div>
        <div style="float: right;margin-right: 20px;font-size: 16px;color: #6f706f;height: 100%">
          <span style="line-height: 300%;cursor: pointer" @click="searchMore">{{showMore ? '返回' : '更多'}}</span>
        </div>
      </div>

      <div style="width: 66vw;height: 32vh;transform: translate(-16px, 0)" v-if="!showMore">
        <NewRankingAll :keyD="keyD" :valueD="valueD"/>
      </div>
      <div style="height: 24vh" v-if="!showMore" />

      <!--   显示更多   -->
      <div v-if="showMore">
        <div v-for="(item, index) in moreDetail" :key="index" class="moreCard">
          <el-row>
            <el-col :span="6" class="tableCell" style="background-color: #5383FD;color: #FDFDFD">
              <div>
                {{regionOrBranchCount === '0' ? '地区' : '支行'}}
              </div>
            </el-col>
            <el-col :span="18" class="tableCell" style="background: #f3f4fa">
              <div>
                {{item.address}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="tableCell">
              <div>
                贷款额
              </div>
            </el-col>
            <el-col :span="18" class="tableCell">
              <div>
                {{item.balancesum}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="tableCell" style="background: #f3f4fa">
              <div>
                利润贡献度
              </div>
            </el-col>
            <el-col :span="18" class="tableCell" style="background: #f3f4fa">
              <div>
                {{item.contributesum ? item.contributesum : 0}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="tableCell">
              <div>
                夫妻数
              </div>
            </el-col>
            <el-col :span="18" class="tableCell">
              <div>
                {{item.spousesum ? item.spousesum : 0}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="tableCell" style="background: #f3f4fa">
              <div>
                贷款人数
              </div>
            </el-col>
            <el-col :span="18" class="tableCell" style="background: #f3f4fa">
              <div>
                {{item.quantitysum ? item.quantitysum : 0}}
              </div>
            </el-col>
          </el-row>
        </div>
        <div style="height: 16px" />
      </div>
    </div>
  </div>
</template>

<script>
import { getCurrentInstance, provide } from "vue";
import NewRankingAll from '@/pages/new_ranking/NewRankingAll'

let that = null
export default {
  name: "NewRanking",
  components: {
    NewRankingAll
  },
  data: () => {
    return {
      area: '全市',
      dataType: '贷款额',
      regionOrBranchCount: '0',
      proxy: null,
      keyD: [],
      valueD: [],
      interval: '按年',
      showMore: false,
      moreDetail: []
    }
  },
  methods: {
    async requestChartData() {
      if (this.regionOrBranchCount === '0') {
        return this.proxy.$api.post('/loan_rank/region', {
          datapart: this.dataType,
          region: this.area,
          interval: this.interval
        })
      }
      else {
        return this.proxy.$api.post('/loan_rank/subbranch', {
          datapart: this.dataType,
          interval: this.interval
        })
      }
    },
    getChartData() {
      this.requestChartData().then(res => {
        let arr = res.data.data.rankingdata
        arr.sort((a, b) => { return a.sum - b.sum })
        this.keyD = []
        this.valueD = []
        for (let i = 0; i < arr.length; i += 1) {
          this.keyD.push(arr[i].address)
          this.valueD.push(arr[i].sum)
        }
      }).catch(err => { console.log(err) })
      this.getMoreDetail()
    },
    changeType(e){
      this.dataType = e.currentTarget.id
      this.getChartData()
      this.getMoreDetail()
    },
    changeRorB(e) {
      this.regionOrBranchCount = e.currentTarget.id
      this.getChartData()
      this.getMoreDetail()
    },
    searchMore() {
      this.showMore = !this.showMore
      if (this.showMore)
        this.getMoreDetail()
      else
        this.getChartData()
    },
    getMoreDetail() {
      if (this.regionOrBranchCount === '0') {
        this.$api.post('/loan_rank/region/detail', {
          datapart: this.dataType,
          region: this.area
        }).then(res => {
          this.moreDetail = res.data.data.rankingdata
        })
      }
      else {
        this.$api.post('/loan_rank/subbranch/detail', {
          datapart: this.dataType
        }).then(res => {
          this.moreDetail = res.data.data.rankingdata
        })
      }
    }
  },
  mounted() {
    that = this
    this.proxy = getCurrentInstance().proxy
    this.getChartData()
  }
}
</script>

<style scoped>
.clickType {
  color: black;
  border-bottom: 4px solid #2986fb;
  cursor: pointer;
  width: 20%;
  height: 100%;
  line-height: 300%;
  font-size: 14px;
}

.unclickType {
  cursor: pointer;
  width: 20%;
  height: 100%;
  line-height: 300%;
  font-size: 14px;
}

.bg {
  background-color: #eff0f4;
  height: 95%;
  width: 96%;
  margin: 20px 20px;
}

button {
  background-color: #2986fb;
  border: none;
  width: 100px;
  height: 40px;
  color: white;
  font-size: 16px;
  border-radius: 5px;
  margin-right: 30px;
}

.unclickButton {
  background-color: white;
  border: 1px solid #2986fb;
  color: #2986fb;
}

.rankSelect {
  width: 240px;
  height: 38px;
  border: 1px solid #6f706f;
  border-radius: 5px;
  padding-left: 8px;
  outline: none;
}

.moreCard {
  width: 75%;
  margin: 16px auto;
}

.tableCell {
  text-align: left;
  border: 1px solid #e5e7e9;
  padding: 4px;
}
</style>
