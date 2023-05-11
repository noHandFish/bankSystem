<template>
	<div id="inf_list">
		<div class="form_box" style="width: 100%; overflow-y: scroll; ">
			<table style="width:100%;border: 1px solid #e5e7ea;border-spacing: 0; position: relative; min-height: 300px;">
			  <tr style="position: sticky; top: 0; height: 40px; line-height: 40px;">
			    <td style="font-size: 16px;text-align: center;width: 16%;">录入时间</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">客户名称</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">客户所在村</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">身份证号</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">拒接原因</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">预警等级</td>
			    <td style="font-size: 16px;text-align: center;width: 16%">操作</td>
			  </tr>
			  <tr v-for="item in newData">
			    <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 13%;">
			      {{item2}}
			    </td>
			  </tr>
			</table>
		</div>
		
		<div id="form_bottom">
			<!-- checkbox -->
			<div id="">123
				<input type="checkbox" name="" id="" value="" />
			</div>
			<!-- 分页 -->
			<div id="page">
			
				<el-pagination
				      v-model:currentPage="page"
				      :page-size="10"
				      :small="small"
				      :disabled="disabled"
				      :background="background"
				      layout="prev, pager, next,jumper"
					  :page-count="totallpage"
					@click = "getpagedata(page)"
				    />
			</div>
		</div>
	</div>
</template>

<script setup>
	import {ref,onMounted,watch} from "vue";
	import {getCurrentInstance} from 'vue'
	const {proxy}= getCurrentInstance()
	const num = ref(1)
	const tempData=ref([])
	const newData=ref([])
	const totallpage = ref(1)
	const pagenumber = ref(8)	
	const page = ref(0)
	const pageList = ref([1])
	const props = defineProps({
		data:{
			type: Array,
			default: () => [],
		}
	})
	
	watch(() => props.data, (e) => {
		console.log(e)
		tempData.value = e
		console.log(tempData.value)
		getpagedata(page)
		
	}, { deep: true })
	
	// watch(() => page.value, (e) => {
	//   getpagedata(e)
	// }, { deep: true })
	
	function getpage() {
		totallpage.value = parseInt((tempData.value.length-1)/pagenumber.value)+1
		}
	
		
	function getpagedata(index){
			page.value =index;//获取当前的页数是多少
			newData.value = tempData.value.slice((page.value-1)*pagenumber.value,
											(page.value)*pagenumber.value);
		 
		    }
</script>

<style>
	.inf_list{
		background-color: blue;
		border: 3px solid black;
	}
	#form_bottom{
		display: flex;
		justify-content: space-between;
		margin:20px 10px 0px;
	}
</style>
