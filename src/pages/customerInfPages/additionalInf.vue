<template>
  <div style="height: 20px"></div>
  <table style="width: 100%;border: 1px solid #e5e7ea;border-spacing: 0;">
    <tr v-for="(item,index) in tableData" style="width: 100%">
      <td style="width: 16%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;border-right: 1px solid #e5e7ea;font-size: 14px">{{index}}</td>
      <td style="width: 78%;padding-left: 20px;border-bottom: 1px solid #e5e7ea;font-size: 14px">{{item}}</td>
    </tr>
  </table>
</template>

<script setup>

import {inject} from "vue";
let tableData=inject('additionalInf')



</script>

<style type="text/css" scoped>
	tr:nth-child(odd) {background-color: #f6f9fd;}
	tr{
	  width: 100%;
	  height: 40px;
	  text-align:left;
	  font-size: 16px;
	}
</style>

