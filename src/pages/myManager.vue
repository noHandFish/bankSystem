<template>
  <div style="width: 98%;height: 94%;background-color:white;margin: 10px auto">
    <div style="height: 80px;width: 100%"></div>
    <div style="width: 60%;height: 100%;margin: 0 auto;font-size: 14px">
      <div>
        <div style="height: 10%;width: 100%;">
          <div style="text-align: left;height: 100%">
            <span style="line-height: 300%">业务概括</span>
            <div style="float: right;line-height: 300%">查看明细</div>
          </div>
        </div>
        <div style="margin-top: 4px;">
          <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
            <tr >
              <td style="font-size: 12px;text-align: center">村民贷户数</td>
              <td style="font-size: 12px;text-align: center">贷款余额</td>
              <td style="font-size: 12px;text-align: center">存款余额</td>
            </tr>
            <tr>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo1.householdNum}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo1.LoanBalance}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo1.depositBalance}}
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div style="margin-top: 40px">
        <div style="height: 10%;width: 100%;">
          <div style="text-align: left;height: 100%">
            <span style="line-height: 300%">贷款业绩</span>
            <div style="float: right;line-height: 300%">查看明细</div>
          </div>
        </div>
        <div style="margin-top: 4px;">
          <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
            <tr >
              <td style="font-size: 12px;text-align: center">本月业绩</td>
              <td style="font-size: 12px;text-align: center">较上月</td>
              <td style="font-size: 12px;text-align: center">本年业绩</td>
              <td style="font-size: 12px;text-align: center">较年初</td>
            </tr>
            <tr>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo2.month}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo2.compareLastM}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo2.year}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo2.compareY_Begin}}
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div style="margin-top: 40px">
        <div style="height: 10%;width: 100%;">
          <div style="text-align: left;height: 100%">
            <span style="line-height: 300%">欠息预期情况</span>
            <div style="float: right;line-height: 300%">查看明细</div>
          </div>
        </div>
        <div style="margin-top: 4px;">
          <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
            <tr >
              <td style="font-size: 12px;text-align: center">欠息户数</td>
              <td style="font-size: 12px;text-align: center">欠息余额</td>
              <td style="font-size: 12px;text-align: center">逾期户数</td>
              <td style="font-size: 12px;text-align: center">逾期余额</td>
            </tr>
            <tr>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo3.oweNum}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo3.oweBalance}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo3.oweHouseholdNum}}
              </td>
              <td style="text-align:center;font-size: 12px">
                {{dataDemo3.oweBalance2}}
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>

let dataDemo1={
  householdNum:300,
  LoanBalance:3400000,
  depositBalance:354365
}
let dataDemo2={
  month:"2356278元",
  compareLastM:"+23456元",
  year:"2356278元",
  compareY_Begin:"+354365元"
}
let dataDemo3={
  oweNum:"30户",
  oweBalance:"34000元",
  oweHouseholdNum:"200户",
  oweBalance2:"300万元"
}

</script>

<style>
tr:nth-child(odd) {background-color: #f6f9fd;}
tr{
  width: 100%;
  height: 40px;
  text-align:left;
  font-size: 16px;
}
</style>