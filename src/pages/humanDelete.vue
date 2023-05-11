<template>

	<div style="height: 100%;width: 100%;border-radius: 0 0 6px 6px;font-size: 14px;padding-bottom: 20px">
	    <div style="height: 20px;width: 96%"></div>
		<div style="width: 96%; margin: 0 auto; background-color: white; height: 100px; border-radius: 5px;">
			<div style="display: flex; justify-content: space-around; align-items: center; width: 60%; height: 100%;">
				<div style="margin-left: 10px;">
					<span>身份证号/港澳通行证号</span>
					<input type="search" placeholder="请输入客户的身份证号或港澳通行证号" style="width: 280px;height: 30px; margin-left: 10px; padding-left: 10px;"/>
				</div>
				<div>
					<el-button class="btn" @click="searchDelete">查询</el-button>
					<el-button class="btn" type="primary">重置</el-button>
				</div>
				
			</div>
			
			<div class="search_box" style="width: 100%; margin-top: 20px;">
						<div>
					<div id="">
						<h4>信息查询结果</h4>
					</div>
							<!-- 列表 -->
							<div id="inf_list">
								<!-- <listForm :data = "tempData"></listForm> -->
								<div class="form_box" style="width: 96%; overflow-y: scroll; ">
									<table style="width:100%;border: 1px solid #e5e7ea;border-spacing: 0; position: relative;min-height: 300px; ">
									  <tr style="position: sticky; top: 0; height: 40px; line-height: 40px;">
									    <td style="font-size: 16px;text-align: center;width: 14%;">录入时间</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">客户名称</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">客户所在村</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">身份证号</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">拒接原因</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">预警等级</td>
									    <td style="font-size: 16px;text-align: center;width: 14%">操作</td>
									  </tr>
									  <tr v-for="(item,index) in newData">
			
									    <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 14%;">
									      {{item2}}
									    </td>
										<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 14%;"><a href="javascript:" style="color: #1673FF; display:inline-block; margin-right: 5px;">修改</a><a href="javascript:" @click="deleteInf(index)" style="color: red">删除</a></td>
									  </tr>
									</table>
								</div>
								
								<div class="form_bottom">
									<!-- checkbox -->
									<div style="display: flex; justify-content: space-between; align-items: center; ">
										<div class="icon_list">
											<span>全选</span>
											<input type="checkbox"  />
										</div>
										<div class="icon_list">
											<img src="../imag/modify.png" style="width: 15px; height: 17px;">
											<span>批量修改</span>
										</div>
										<div class="icon_list" @click="deleteLot">
											<img src="../imag/delete.png" style="width: 15px; height: 17px;">
											<span>批量删除</span>
										</div>
										
									</div>
									<!-- 分页 -->
									<div id="page">
									
										<el-pagination
											  background
										      v-model:currentPage="page"
										      :page-size="10"
										      :small="small"
										      :disabled="disabled"
										      :background="background"
											  :total="totallpage"
										      layout="total,prev, pager, next,jumper"
											  :page-count="totallpage"
											@click = "getpagedata(page)"
										    />
									</div>
								</div>
							</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
	
	import { ref,watch,onMounted,getCurrentInstance } from 'vue'
	const {proxy}= getCurrentInstance()
	const num = ref(1)
	const tempData=ref([])
	const newData=ref([])
	const totallpage = ref(1)
	const pagenumber = ref(10)	
	const page = ref(1)
	const pageList = ref([1])
	const time = ref(new Date())
	
	onMounted(()=>{
		document.getElementsByClassName(
		      "el-pagination__jump"
		    )[0].childNodes[0].nodeValue = "跳至";
		document.getElementsByClassName(
		      "el-pagination__total"
		    )[0].childNodes[0].nodeValue = "总共";	
	})
	
	function searchDelete(){
		// proxy.$api.post('/DataEntry/alertPerson/manual_delete_info',{
		// 	"id": "440307****1917"//客户身份证号
		// }).then((res)=>{
		// 	console.log(res)
		// })
		proxy.$api.post('/DataEntry/alertPerson/manual_delete_info',{
			"id": "440307****1917"//客户身份证号
		}).then((res)=>{
		console.log(res)
		tempData.value = res.data.data.alertPersonInfo
		
		 console.log(tempData.value)
		 getpage()
		 getpagedata(page.value)
		 })
	}
	
	watch(() => page.value, (e) => {
	  getpagedata(e)
	}, { deep: true })
	
	function getpage() {
		totallpage.value = parseInt((tempData.value.length-1)/pagenumber.value)+1
		}
	
		
	function getpagedata(index){
			page.value =index;//获取当前的页数是多少
			newData.value = tempData.value.slice((page.value-1)*pagenumber.value,
											(page.value)*pagenumber.value);
			console.log(newData.value)
		    }
	
	function deleteInf(i){
		console.log(page.value)
		console.log(8*page.value+i)
		tempData.value.splice(pagenumber.value*(page.value-1)+i,1)
		getpage()
		getpagedata(totallpage.value)
		console.log(tempData.value[pagenumber.value*(page.value-1)+i])
		proxy.$api.post("/DataEntry/alertPerson/manual_delete",{
			"id": "440307****1917"//客户身份证号
		}).then(res=>{
			console.log(res)
		})
	}
</script>

<style>
	.form_box{
		display: flex;
		height: 100%;
		background-color: white;
		overflow-y: scroll;
		overflow-x: scroll;
		margin: 0 auto
	}
	tr:nth-child(odd) {background-color: #f6f9fd;}
	tr{
	  display: flex;
	  height: 26px;
	  line-height: 26px;
	  text-align:left;
	  font-size: 16px;
	}
	td{
		white-space:nowrap;
	}
	.form_bottom{
		display: flex;
		align-items: center;
		justify-content: space-between;
		margin-top: 10px;
	}
	.icon_list{
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 0 5px;
		cursor: pointer;
	}
</style>
