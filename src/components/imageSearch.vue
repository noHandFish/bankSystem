<template>
	<div style="text-align: left;margin-left: 20px;font-size: 14px">
	  <span>身份证</span>
	  <input type="text" placeholder="请输入身份证号码" class="searchInput" v-model="id_num"/>
	  <button class="searchButton" @click="searchVideoById">查询</button>
	</div>
</template>

<script setup>
	import {getCurrentInstance,ref} from "vue";
	const {proxy}= getCurrentInstance()
	const OnGoingImage = ref([])
	const doneImage = ref([])
	const manageImage = ref([])
	const id_num = ref()
	let sendInf = defineEmits(["send"])

	defineExpose({
	  OnGoingImage,
	  doneImage,
	  manageImage
	})
	function searchVideoById(){
		proxy.$api.post("/information-query/image-information/get-done-business-image",{
			 "id":"440100xxx3"
		}).then((res)=>{
			// console.log(res)
			// doneImage.value = res.data
			// sendInf("send",res)
			console.log(res.data.data[0])
			sessionStorage.setItem("doneImg",JSON.stringify(res.data.data[0]))
			sendInf("send",res)
		})
		
		proxy.$api.post("/information-query/image-information/get-ongoing-business-image",{
			 "id":"440100xxx3"
		}).then((res)=>{
			// console.log(res)
			// OnGoingImage.value = 1
			console.log(res.data.data[0])
			sessionStorage.setItem("OnGoingImg",JSON.stringify(res.data.data[0]))
			sendInf("send",res)
		})
		
		proxy.$api.post("/information-query/image-information/get-manage-image",{
			 "id":"440301xxxxxxxx1101"
		}).then((res)=>{
			console.log(res.data.data)
			// console.log(res)
			// manageImage.value = res.data
			// sendInf("send",res)	
			sessionStorage.setItem("manageImg",JSON.stringify(res.data.data))
			sendInf("send",res)
		})
	}
</script>

<style>
	.searchInput{
	  width: 260px;
	  height: 30px;
	  border:1px solid gainsboro;
	  border-radius: 4px;
	  margin: 0 20px 0 20px;
	  padding-left:10px ;
	}
</style>
