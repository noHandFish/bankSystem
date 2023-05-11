<template>
  <div style="height: 90%;width: 100%;background-color: white;border-radius: 0 0 6px 6px">
    <div style="height: 20px"></div>
    <div style="font-size: 14px;display: flex;justify-content: space-around;width: 100%;margin-left: 20px">
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div >
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">放贷余额：
          <input type="text" class="inputText">
          <span>至</span>
          <input type="text" class="inputText">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">贷款到期日：
          <input type="text" class="inputText" style="margin-left: -4px">
          <span>至</span>
          <input type="text" class="inputText">
        </div>

        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">所属支行：
          <select class="listSelect">
            <option>xxx</option>
          </select>
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">夫妻贷款：
          <select class="listSelect">
            <option>xxx</option>
          </select>
        </div>
      </div>
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div>
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">贷款余额：
          <input type="text" class="inputText">
          <span>至</span>
          <input type="text" class="inputText">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">贷款利率：
          <input type="text" class="inputText">
          <span>至</span>
          <input type="text" class="inputText">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">客户经理：
          <select class="listSelect">
            <option>xxx</option>
          </select>
        </div>
      </div>
      <div style="width: 33%;padding-left: 10px;text-align: left">
        <div >
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">放贷日期：
          <input type="text" class="inputText">
          <span>至</span>
          <input type="text" class="inputText">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">贷款期限：
          <input type="text" class="inputText">
          <span>至</span>
          <input type="text" class="inputText">
        </div>
        <div style="margin-top: 10px;">
          <input type="checkbox" style="zoom:140%; vertical-align: middle;">AB互保贷款：
          <select class="listSelect" style="margin-left: -8px">
            <option>xxx</option>
          </select>
        </div>
      </div>
    </div>
    <div style="margin-top: 10px">
      <button class="searchButton">查询</button>
      <button class="resetButton">重置</button>
    </div>
  </div>
</template>

<style>
.inputText{
  width: 100px;
  height: 25px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
  margin-right: 10px;
}
.listSelect {
  width: 246px;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #c4c0c0;
  margin-left: 10px;
}
.searchButton{
  width: 90px;
  height: 32px;
  background-color: #4b83da;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  color: white;
  margin-left: 30px;
}
.resetButton{
  width: 90px;
  height: 32px;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  background-color: #e54e4e;
  color: white;
  margin-left: 10px;
}
input{
  height: auto;
  margin: 0;
  width: auto;
}
input[type='checkbox']{
  -webkit-appearance: checkbox;
  margin: 0;
  margin-right: 5px;
}
</style>