<template>
  <div style="height: 84%;width: 98%;margin: 10px auto;background-color: white;font-size: 13px;border-radius: 6px">
    <div style="width: 100%;height: 6%;background-color: white;border-radius: 6px 6px 0 0;text-align: left">
      <div style="display: flex;justify-content: space-around;width: 24%;margin-left: 2px;height: 100%">
        <router-link to="/home/financialAssistant/LoanAmountC" style="border-bottom: 2px solid #2986fb;height: 100%;line-height: 300%;color: black">贷款额度计算</router-link>
        <router-link to="/home/financialAssistant/LoanProgram" style="height: 100%;line-height: 300%">贷款利息及还款计划</router-link>
      </div>
    </div>
    <div style="height: 94%;width: 100%">
      <router-view></router-view>
    </div>
  </div>
</template>
<style>
a,router-link{
  text-decoration: none;
  color: #2a2525;
}
</style>