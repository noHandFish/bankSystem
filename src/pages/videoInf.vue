<template>
  <div style="width: 98%;height: 10%;background-color: white;border-radius: 6px;margin:20px auto">
    <div style="height: 20px"></div>
    <search></search>
  </div>
  <div style="width: 98%;height: 80%;background-color: white;border-radius: 6px;margin: 20px auto; display: flex;">
	<div style="width: 100%; height: 80%; display: flex; justify-content:center; align-items: center;">
		
		<div class="img_entry" >
			<a   href="javascript:none;" @click="change1">
				<div style="height: 80%; display: flex; justify-content: center; align-items: center;">
					<img src="../imag/manageImage.png" style="height: 83px;" width="83px">
				</div>
				<div style="height: 20%; background-color: #edf1fc; display: flex; justify-content: space-between; align-items: center; padding: 0 10px;">
					<div style="color: #6789f5;">
						管理类图像
					</div>
					<div>
						<a href="javascript:;" style="color: #b3b6bf;">查看></a>
					</div>
				</div>
			</a>
			 
		</div>
		
		<div class="img_entry" >
			<a href="javascript:none;" @click="change">
				<div style="height: 80%;width: 100%; display: flex; justify-content: center; align-items: center;">
					<img src="../imag/businessImage.png" style="height: 83px; width: 75px;" >
				</div>
				<div style="height: 20%; background-color: #edf1fc; display: flex; justify-content: space-between; align-items: center; padding: 0 10px;">
					<div style="color: #6789f5;">
						要件类图像
					</div>
					<div>
						<a href="javascript:;" style="color: #b3b6bf;">查看></a>
					</div>
				</div>
			</a>
			
		</div>
	</div>
	 

  </div>
  
</template>
<script setup>
import {getCurrentInstance, ref,watch} from "vue";
import search from '/src/components/imageSearch.vue'
const {proxy}= getCurrentInstance()
const imageSrc=ref('')
const marrige_image=ref('')
const equity_image=ref('')
const con_image=ref('')
const house_image=ref('')
const household_image=ref('')
const id_image=ref('')
const job_image=ref('')
const villager_image=ref('')
const license_image=ref('')
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

const isCheck = ref(false)
const checkList = ref([])
let count = 0

function change(){
	proxy.$router.push({
		    name:'businessStatus',
	})
}

function change1(){
	proxy.$router.push({
		    name:'manageImage',
	})
}

function searchVideoById(){
	if(id_num.value){
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
	let data = res.data.data
	if (data){
		if(data.id_image){
			id_image.value = 'http://120.24.235.36'+ data.id_image
		}
		if(data.villager_image){
			villager_image.value = 'http://120.24.235.36'+ data.villager_image
		}
		if(data.marrige_image){
			marrige_image.value = 'http://120.24.235.36'+ data.marrige_image
		}
		if(data.con_image){
			con_image.value = 'http://120.24.235.36'+ data.con_image
		}
		if(data.household_image){
			household_image.value = 'http://120.24.235.36'+ data.household_image
		}
		if(data.job_image){
			job_image.value = 'http://120.24.235.36'+ data.job_image
		}
		if( data.equity_image){
			equity_image.value = 'http://120.24.235.36'+ data.equity_image
		}
		if(data.house_image){
			house_image.value = 'http://120.24.235.36'+ data.house_image
		}
		if(data.license_image){
			license_image.value = 'http://120.24.235.36'+ data.license_image
		}
	}
		
  })	
	}
	else{
		alert('输入为空')
	}
 
}
function selectAll(e){
	isCheck.value = !isCheck.value
}
function downLoadPic(){
	let url = {
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
	}
	proxy.$api.post('/information-query/image-information/downloadImage',{
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
		console.log(url)
	})
}

// function checkChange(e){
// 	if(checkList.value=="on"){
// 		count +=1
// 		console.log(count)
// 	}
// 	else{
// 		count -=1
// 		console.log(count)
// 	}
// 	if(count==6){
// 		isCheck.value = true
// 	}
// }
</script>
<style scoped>
	a{
		text-decoration: none;
	}
.searchInput{
  width: 260px;
  height: 30px;
  border:1px solid gainsboro;
  border-radius: 4px;
  margin: 0 20px 0 20px;
  padding-left:10px ;
}
.uploadButton{
  width: 90px;
  height: 32px;
  border: none;
  border-radius: 2px;
  font-size: 12px;
  background-color: #3dba6c;
  color: white;
  margin-left: 10px;
}
.img_entry{
	width: 350px;
	height: 250px;
	margin-top: 20px;
	border: 1px solid #dee5fb;
	margin-right:60px;
	border-radius: 5px;
}
</style>