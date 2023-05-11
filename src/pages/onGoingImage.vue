<template>
<div style="width: 98%;height: 10%;background-color: white;border-radius: 6px;margin:20px auto">
	  <div style="height: 20px"></div>
	<search1 @send="handlerOnGoing"></search1>
	</div>
	<div style="width: 98%;margin: 20px auto;height:80%;display: flex;flex-wrap: wrap; overflow-y: scroll; background-color: white;padding: 20px 0 0 20px; box-sizing: border-box;position: relative;">
		<div style="position: absolute; top: 3%; left: 2%;">
<!--			<img src="../imag/pre.png" @click="prePage"  style="width: 20px; height: 20px;cursor: pointer;">-->
		</div>
		<div style="font-size: 18px;font-family: Source Han Sans CN, Source Han Sans CN-Bold;font-weight: 700;text-align: left;color: #32323a; margin-top: 2%;">未结清业务</div>
		<div style="width: 100%; display: flex;flex-wrap: wrap;margin: 0 auto;">

		  <div v-for="item in img" style="width: 30%; height:200px; border: 1px solid gray;margin: 10px;" >
		  	<img :src="item" style="width: 100%; height: 100%;">
		  </div>
		</div>
	</div>

</template>

<script setup>
	import {ref,onMounted} from "vue"
	import search1 from '/src/components/imageSearch.vue'
	import {getCurrentInstance} from 'vue'
	const {proxy} = getCurrentInstance()
	const img = ref([])
	onMounted(()=>{
		if(sessionStorage.getItem("OnGoingImg")){
			img.value = JSON.parse(sessionStorage.getItem("OnGoingImg"))
			console.log(img.value)
			delete img.value.conId
			let temp = Object.values(img.value)
			let i = 0
			temp.forEach(e=>{
				temp[i] = 'http://120.24.235.36:8089/images'+e
				i++
			})
			img.value = temp
		}
	})
	function handlerOnGoing(data){
		// sessionStorage.setItem("OnGoingImg",JSON.stringify(data.data.data[0]))
		img.value = JSON.parse(sessionStorage.getItem("OnGoingImg"))

		delete img.value.conId
		let temp = Object.values(img.value)
		console.log(temp)
		let i = 0
		temp.forEach(e=>{
			temp[i] = 'http://120.24.235.36:8089/images'+e
			i++
		})
		img.value = temp
	}
	function prePage(){
		proxy.$router.back();
	}
</script>

<style>
</style>
