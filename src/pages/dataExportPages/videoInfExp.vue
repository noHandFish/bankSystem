<template>
  <div style="height: 100%;width: 100%;background-color: white;border-radius: 0 0 6px 6px">
    <div style="height: 20px"></div>
    <div style="width: 98%;margin: 0 auto;height:4%;text-align: left">
      <input type="checkbox" style="zoom:130%;vertical-align: middle;-webkit-appearance: checkbox" name="allSelct"/>
      <span style="margin-left: 10px;vertical-align: middle;">全选</span>
      <button class="uploadButton" style="float: right;margin-top: -5px;background-color: #3dba6e;font-size: 12px">下载图片</button>
    </div>
    <div style="width: 98%;margin: 20px auto;height:80%;display: flex;justify-content: space-around; overflow-y: scroll;">
      <div style="width: 33%;height: 98%;text-align: left">
        <div style="width: 100%;height: 48%">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;" v-model="id_check">
            <span style="margin-left: 10px;vertical-align: middle;" >身份证缩略图</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
        <div style="width: 100%;height: 48%;margin-top: 20px">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;" v-model="villager_check">
            <span style="margin-left: 10px;vertical-align: middle;">村民证明/分红证明</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
		<div style="width: 100%;height: 48%;margin-top: 20px">
		  <div>
		    <input type="checkbox" style="zoom:130%; vertical-align: middle;" :checked="isCheck" v-model="house_check" >
		    <span style="margin-left: 10px;vertical-align: middle;">房产资料</span>
		  </div>
		  <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
			  <img :src="house_image">
		  </div>
		</div>
      </div>
      <div style="width: 33%;height: 98%;text-align: left">
        <div style="width: 100%;height: 48%">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;">
            <span style="margin-left: 10px;vertical-align: middle;">结婚证缩略图</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
        <div style="width: 100%;height: 48%;margin-top: 20px">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;">
            <span style="margin-left: 10px;vertical-align: middle;">股权证</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
		<div style="width: 100%;height: 48%;margin-top: 20px">
		  <div>
		    <input type="checkbox" style="zoom:130%; vertical-align: middle;" :checked="isCheck" v-model="license_check" >
		    <span style="margin-left: 10px;vertical-align: middle;">营业执照</span>
		  </div>
		  <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
			  <img :src="license_image">
		  </div>
		</div>
      </div>
      <div style="width: 33%;height: 98%;text-align: left">
        <div style="width: 100%;height: 48%">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;">
            <span style="margin-left: 10px;vertical-align: middle;">户口篇缩略图</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
        <div style="width: 100%;height: 48%;margin-top: 20px">
          <div>
            <input type="checkbox" style="zoom:130%; vertical-align: middle;">
            <span style="margin-left: 10px;vertical-align: middle;">工作证明</span>
          </div>
          <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
          </div>
        </div>
		<div style="width: 100%;height: 48%;margin-top: 20px">
		  <div>
		    <input type="checkbox" style="zoom:130%; vertical-align: middle;" :checked="isCheck" v-model="con_check" >
		    <span style="margin-left: 10px;vertical-align: middle;">购销合同</span>
		  </div>
		  <div style="width: 90%;height: 84%;background-color: #c4c0c0;margin-top: 8px">
			  <img :src="con_image">
		  </div>
		</div>
      </div>
	  
    </div>
  </div>
</template>
<script setup>
import {getCurrentInstance, ref,watch} from "vue";
const {proxy}= getCurrentInstance()
const id_num = ref()
const marrige_check=ref(false)
const equity_check=ref(false)
const con_check=ref(false)
const house_check=ref(false)
const household_check=ref(false)
const id_check=ref(false)
const job_check=ref(false)
const villager_check=ref(false)
const license_check=ref(false)

	function donwLoadImage(){
		proxy.$api.post('/information-query/image-information/getImage',{
		   "cli_idnum":id_num.value,
		   "identityCard":id_check.value,
		   "householdRegister":household_check.value,
		   "marriageCertificate":marrige_check.value,
		   "villagersCertificate":villager_check.value,
		   "shareWarrant":equity_check.value,
		   "proofOfWork":job_check.value,
		   "realEstateInformation":house_check.value,
		   "businessLicense":license_check.value,
		   "tradingContract":con_check.value
		 }).then(res=>{
			 console.log(res) 
		 })
	}
</script>
<style scoped>
input{
  height: auto;
  margin: 0;
  width: auto;
}
input[type='checkbox']{
  -webkit-appearance: checkbox;
  margin: 0;
}
</style>