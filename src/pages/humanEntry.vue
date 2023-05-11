<template>
	<div style="height: 100%;width: 100%;border-radius: 0 0 6px 6px;font-size: 14px;padding-bottom: 20px">
	    <div style="height: 20px;width: 96%"></div>
	    <div style="width: 98%;margin: 0 auto;text-align: left">

			
	      <div id="import_box" >
			  <form action="" method="">
			  	<div id="top_box">
			  		<div class="inf_box">
			  			<span>客户名称:</span>
			  			<input type="text" placeholder="请输入客户名称" v-model="cliName" autofocus required pattern="[\u4e00-\u9fa5]{2,5}"/>
			  		</div>
			  		<div class="inf_box">
			  			<span>客户所在村:</span>
			  			<input type="text" placeholder="请输入客户所在村" v-model="cliVillage"/>
			  		</div>
			  		<div class="inf_box">
			  			<span>身份证号码:</span>
			  			<input type="text" placeholder="请输入身份证号码" v-model="cliIdnum" required 
						pattern="^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$"
						maxlength="18"
						/>
			  		</div>
			  		<div class="inf_box">
			  			<span>预警等级:</span>
			  			<select v-model="warnLevel">
			  				<option value ="1">1</option>
			  				<option value ="2">2</option>
			  			</select>
			  		</div>
			  		<div id="reason_box">
			  			<span>拒接原因:</span>
			  			<textarea placeholder="请输入原因" v-model="reason" style="padding: 3px 0 0 5px;"></textarea>
			  		</div>
			  	</div>
			  	<div id="btn_box">
			  		<div >
			  			<el-button class="btn">重置</el-button>
			  			<el-button class="btn" type="primary" @click="entryInfo">录入</el-button>
			  		</div>
			  			
			  	</div>
			  </form>
	      	
	      </div>
		  <div class="search_box">
			<div>

		  		<div id="">
		  			<h4>信息查询结果</h4>
		  		</div>
				<!-- 列表 -->
				<div id="inf_list">

					<!-- <listForm :data = "tempData"></listForm> -->
					<div class="form_box" style="width: 96%; overflow-y: scroll; ">
						<table style="width:100%;border: 1px solid #e5e7ea;border-spacing: 0; position: relative; ">
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
							  <td  style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 14%;">
							    <input type="checkbox"    @click="checkbox" :checked="isCheck"/> {{timeList[(page-1)*pagenumber+index]}} 
							  </td>
						    <td v-for="item2 in item" style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 14%;">
						      {{item2}}
						    </td>
							<td style="text-align:center;border-bottom: 1px solid #e5e7ea;font-size: 14px;width: 14%;"><a href="javascript:" style="color: #1673FF; display:inline-block; margin-right: 5px;">修改</a>
							<a href="javascript:" @click="deleteInf(index)" style="color: red">删除</a></td>

						  </tr>
						</table>
					</div>
					

					<div id="form_bottom">
						<!-- checkbox -->
						<div style="display: flex; justify-content: space-between; align-items: center; ">
							<div class="icon_list">
								<span>全选</span>
								<input type="checkbox"  @click="checkChange"/>
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
	import { ref,watch,onMounted } from 'vue'
	import {getCurrentInstance} from 'vue'
	import listForm from "@/components/listForm"
	const {proxy}= getCurrentInstance()
	const isCheck = ref(false)
	const cliName = ref()
	const cliVillage = ref()
	const cliIdnum = ref()
	const warnLevel = ref(1)
	const reason = ref()
	const checkboxList = ref([])
	const num = ref(1)
	const tempData=ref([])
	const newData=ref([])
	const totallpage = ref(1)
	const pagenumber = ref(10)	
	const page = ref(1)
	const pageList = ref([1])
	const time = ref(new Date())
	const timeList = ref([])
	onMounted(()=>{
		document.getElementsByClassName(
		      "el-pagination__jump"
		    )[0].childNodes[0].nodeValue = "跳至";
		document.getElementsByClassName(
		      "el-pagination__total"
		    )[0].childNodes[0].nodeValue = "总共";	
	})
	
	function checkChange(){
		isCheck.value = !isCheck.value 
	}
	
	function isCardNo(card) 
		{ 
		    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
		    //\d 匹配数字
		    // |将两个匹配条件进行逻辑“或”（or）运算。
		    //^匹配输入字符串的开始位置。
		    //$匹配输入字符串的结束位置
		    //()是为了提取匹配字符串的，表达式中有几个()就有几个相应的匹配字符串
		    //{}一般是用来匹配的长度。比如\s{3}表示匹配三个空格，\s[1,3]表示匹配1到3个空格
		    // var reg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/; 
		    // if(reg.test(card) === false) { 
		    //     alert("身份证输入不合法"); 
		    //     return false; 
		    // }else{
		    // 	alert("输入正确");
		    	return true;
		    // }
		}

	function checkbox(e){
		if(e.currentTarget.checked){
			checkboxList.value.push(e.currentTarget.class)
		}
		else{
			for(let i = 0; i < checkboxList.value.length; i++){
			    if(checkboxList.value[i] == e.currentTarget.class){
			       checkboxList.value.splice(i,1);
			    }
			}
		}
		console.log(checkboxList.value)
		
	}
	
	function deleteLot(){
		for(let i = 0; i < checkboxList.value.length; i++){
		    deleteInf(checkboxList.value[i])
		}
		checkboxList.value=[]
	}
	
	function deleteInf(i){
		console.log(page.value)
		i = Number(i)
		console.log(8*page.value+Number(i))
		tempData.value.splice(pagenumber.value*(page.value-1)+i,1)
		timeList.value.splice(pagenumber.value*(page.value-1)+i,1)
		getpage()
		getpagedata(totallpage.value)
	}
	
	function entryInfo(){
		if(isCardNo(cliIdnum.value)){
			proxy.$api.post('/DataEntry/alertPerson/manual_entry',{
					"cliName": cliName.value,
					"cliVillage": cliVillage.value,
					"cliIdnum": cliIdnum.value,
					"warnLevel": warnLevel.value,
					"reason": reason.value
			}).then(res=>{
				if(res.data.code==200){
					// alert("录入成功")
					time.value = new Date()
					timeList.value.push(format())
					let uniData = [cliName.value,cliVillage.value,cliIdnum.value,warnLevel.value,reason.value]
					tempData.value.push(uniData)
					getpage()
					getpagedata(totallpage.value)
				}
			})
		}

		
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
		    }
	

	
	function format()
		{
			//dataString是整数，否则要parseInt转换
			var time = new Date();
			var year = time.getFullYear();
			var month = time.getMonth()+1;
			var day = time.getDate();
			var hour = time.getHours();
			var minute = time.getMinutes();
			var second = time.getSeconds();
			return year+'-'+(month<10?'0'+month:month)+'-'+(day<10?'0'+day:day)+' '+(hour<10?'0'+hour:hour)+':'+(minute<10?'0'+minute:minute)+':'+(second<10?'0'+second:second)
		}
		
		

</script>

<style>
	a{
		text-decoration: none;
	}
	#import_box{
		box-sizing: border-box;
		width: 98%;
		background-color: white;
		border-radius: 6px;
		padding: 30px 20px;
		margin: 0 auto;

		
	}
	#top_box{
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between
	}
	#top_box input,select {
		outline-color: rgb(153, 153, 255);
		margin-left: 10px;
		padding-left: 5px;
		width: 180px;
		height: 26px;
	}
	#reason_box{
		margin-top: 30px;
		display: flex;
	}
	#reason_box textarea{
		margin-left: 10px;
		width: 400px;
		height: 90px;
	}
	#btn_box{
		display: flex;
		justify-content: center;
	}
	#btn_box div{
		margin: 20px 0 auto;
	}
	.btn{
		width: 90px;
	}


	.form_box{
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
	.search_box{
		box-sizing: border-box;
		width: 98%;
		margin: 0 auto;
		background-color: white;
		padding: 10px 10px 10px;
		margin-top: 5px;
		border-radius: 6px;
	}
	.icon_list{
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 0 5px;
		cursor: pointer;
	}
</style>

