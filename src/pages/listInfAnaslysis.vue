<template>
  <div style="background-color: white;margin: 5px auto;width: 98%;height: 20%">
    <form >
      <div style="display: flex;justify-content: space-around">
        <div style="width: 50%;">
          <ul style="text-align: left;">
            <li>
              <input type="checkbox" name="way" value="loanBalance">担保余额: <input type="text" name="loanBalanceB"> - <input type="text" name="loanBalanceE">
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">担保时间: <input type="text" name="loanBalanceB"> - <input type="text" name="loanBalanceE">
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">
              <span>客户经理: </span>
              <select style="height: 28px;width: 100px">
                <option>全部</option>
                <option>张三</option>
                <option>李四</option>
              </select>
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">
              <span>AB互保贷款: </span>
              <select style="height: 28px;width: 100px">
                <option>无</option>
                <option>是</option>
                <option>否</option>
              </select>
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">
              <span>ABCD互保贷款: </span>
              <select style="height: 28px;width: 100px">
                <option>无</option>
                <option>是</option>
                <option>否</option>
              </select>
            </li>
          </ul>
        </div>
        <div style="width: 50%">
          <ul style="text-align: left;">
            <li>
              <input type="checkbox" name="way" value="loanBalance">担保金额: <input type="text" name="loanBalanceB"> - <input type="text" name="loanBalanceE">
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">担保到期日: <input type="text" name="loanBalanceB"> - <input type="text" name="loanBalanceE">
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">
              <span>所属支行: </span>
              <select style="height: 28px;width: 100px">
                <option>全部</option>
                <option>xxxx</option>
                <option>xxxx</option>
              </select>
            </li>
            <li>
              <input type="checkbox" name="way" value="loanBalance">
              <span>ABC担保圈: </span>
              <select style="height: 28px;width: 100px">
                <option>空</option>
                <option>是</option>
                <option>否</option>
              </select>
            </li>
          </ul>
        </div>
      </div>
      <button type="submit" style="width: 10%;margin-right: 20px;border: none">查询</button>
      <button type="reset" style="width: 10%;background-color: #579bde;border: none">重置</button>
    </form>
  </div>
  <!--  □贷款余额 [     ] — [     ]           □贷款金额   [     ] — [     ]-->
  <!--  □放款日期 [     ] — [     ]           □贷款到期日 [     ] — [     ]-->
  <!--  □贷款利率 [     ] — [     ]           □贷款期限   [     ] — [     ]-->
  <!--  所属支行    [&#45;&#45;&#45;&#45;&#45;&#45;P&#45;&#45;&#45;&#45;&#45;&#45;]             客户经理[&#45;&#45;&#45;&#45;&#45;&#45;P&#45;&#45;&#45;&#45;&#45;&#45;]-->
  <!--  AB互保贷款[&#45;&#45;&#45;&#45;&#45;&#45;P&#45;&#45;&#45;&#45;&#45;&#45;]              夫妻贷款[&#45;&#45;&#45;&#45;&#45;&#45;P&#45;&#45;&#45;&#45;&#45;&#45;]-->
  <div style="background-color: white;margin: 5px auto;width: 98%;height: 70%">
    <!--      客户号	客户名称	证件号码	贷款合同号	贷款金额	贷款余额	贷款期限	贷款利率	贷款类型	所属支行	客户经理-->
    <div style="height: 5px"></div>
    <table>
      <tr>
        <th>序号</th>
        <th>担保人名称</th>
        <th>证件号码</th>
        <th>担保方式</th>
        <th>担保金额</th>
        <th>担保时间</th>
        <th>担保到期日</th>
        <th>贷款合同号</th>
        <th>与借贷人关系</th>
        <th>所属支行</th>
      </tr>
      <tr>
        <td>010</td>
        <td>张三</td>
        <td>440201199204039876</td>
        <td>000000000000</td>
        <td>78660</td>
        <td>20221209</td>
        <td>20230104</td>
        <td>01</td>
        <td>姐妹</td>
        <td>龙岗分行</td>
      </tr>
      <tr>
        <td>011</td>
        <td>黄刘</td>
        <td>440201199204039876</td>
        <td>000000000000</td>
        <td>78660</td>
        <td>20221209</td>
        <td>20230103</td>
        <td>01</td>
        <td>姐妹</td>
        <td>龙岗分行</td>
      </tr>
    </table>


  </div>
</template>

<style scoped>
input{
  width: 22%;
  height: 20px;
}
ul{
  list-style: none;
}
.listsearch{
  text-align: center;
  margin: 20px auto;
}
tr:hover {background-color: #f5f5f5;}
th {
  background-color: #6f706f;
  color: white;
}
th, td {
  border-bottom: 1px solid #ddd;
}
table{
  margin:50px auto;
}

</style>