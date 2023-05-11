<template>
	<div style="width: 98%;height: 10%;background-color: white;border-radius: 6px;margin:20px auto">
	  <div style="height: 20px"></div>
	<search1></search1>
	</div>
	<div style="width: 98%;height: 80%;background-color: white;border-radius: 6px;margin: 20px auto; display: flex;">
		<div style="width: 100%; height: 80%; display: flex; justify-content:center; align-items: center;position: relative;">
			<div style="position: absolute; top: 3%; left: 2%;">
<!--				<img src="../imag/pre.png" @click="prePage"  style="width: 20px; height: 20px;cursor: pointer;">-->
			</div>
			<div  style="width: 300px;height: 160px; margin-top: 20px; background-color: #FD6362
;border-radius: 10px;margin-right:60px; ">
				<a  href="javascript:none;" @click="jump">
					<div style="height: 100%; display: flex; justify-content: center; align-items: center;">
							<div style="width: 30px; height: 30px; border-radius: 100%; background-color: #fff; display: flex; justify-content: center; align-items: center;">
								<img src="../imag/error.png" style="width: 15px;height: 15px; ">
							</div>

							<span style="color: #fff;">未结清业务</span>
					</div>

				</a>

			</div>

			<div class="img_entry" style="width: 300px;height: 160px; margin-top: 20px;background-color: rgba(84,132,253,0.1);border-radius: 10px;">
				<a href="javascript:none;"  @click="jump1">
					<div style="height: 100%; display: flex; justify-content: center; align-items: center;">
							<div style="width: 30px; height: 30px; border-radius: 100%; background-color: #5484fd; display: flex; justify-content: center; align-items: center; opacity: 1;">
								<img src="../imag/check.png" style="width: 15px;height: 15px; ">
							</div>
							<span style="color:#5484fd; ">已结清业务</span>
					</div>

				</a>


			</div>
		</div>
	</div>
</template>

<script setup>
	import search1 from '/src/components/imageSearch.vue'
	import {getCurrentInstance} from 'vue'
	const {proxy}= getCurrentInstance()
	function jump(){
		proxy.$router.push({
			    name:'onGoingImage',
		})
	}
	function jump1(){
		proxy.$router.push({
			    name:'doneImage'
		})
	}
	function prePage(){
		proxy.$router.back();
	}
</script>

<style>
	a{
		text-decoration: none;
	}

</style>
