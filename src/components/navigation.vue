<template>
  <div class="titleclass">
    <h2>村民贷管理系统</h2>
  </div>
    <div id="navigation" class="nav">
      <ul>
<!--        <template v-for="item in items" :key="item">-->
<!--          <li><a >{{item.txt}}</a>-->
<!--&lt;!&ndash;          <secnav v-if="item.secnav" :items="item.secnav" :class="secnav"></secnav>&ndash;&gt;-->
<!--            <div v-if="hassecnav" id="hassecnav" v-bind="showSec">-->
<!--              <template v-for="item2 in item.secnav" :key="item2">-->
<!--                <li >-->
<!--                  <a :href="item2.url">{{ item2.txt }}</a>-->
<!--                </li>-->
<!--              </template>-->
<!--            </div>-->
<!--          </li>-->
          <li id="one" >
            <router-link to="/dataDetail">业务概括</router-link>
<!--            <a>业务概括</a>-->
          </li>
          <li><a>信息查询</a></li>
          <li><a>数据分析</a></li>
          <li><a>数据导出</a></li>
          <li><a>数据导入</a></li>
          <li><a>我的管户</a></li>
          <li><a>个人信息</a></li>
      </ul>
    </div>
<!--  <div id="navigation" class="nav">-->
<!--    <ul>-->
<!--      <template v-for="item in items" :key="item">-->
<!--        <li >-->
<!--          <a :href="item.url">{{ item.txt }}</a>-->
<!--          <secnav v-if="item.secnav" :items="item.secnav" :class="secnav"></secnav>-->
<!--        </li>-->
<!--      </template>-->
<!--    </ul>-->
<!--  </div>-->

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'navigation',
  data() {
    return {
      showone: false,
    }
  },
  methods:{

  }
}
  // let showli=document.getElementById("one")
  // showli.addEventListener("mouseenter",()=>
  //     this.$data.showone=true)
  // showli.addEventListener("mouseleave",()=>
  //     this.$data.showone=false)
</script>

<style>
/*a{*/
/*  text-align: center;*/
/*}*/
/*.nav {*/
/*  width: 80%;*/
/*  max-width: 440px;*/
/*  margin: 0 auto;*/
/*}*/
/*.nav>ul {*/
/*  display: flex;*/
/*  list-style-image:url("../imag/favicon.ico");*/
/*  list-style-type: square;*/
/*  !*margin: 0;*!*/
/*  !*justify-content: left;*!*/
/*  flex-wrap: wrap;*/
/*  height: 100%;*/
/*}*/

/*.nav>ul li {*/
/*  flex-grow: 1;*/
/*  padding: 15px 30px;*/
/*  position: relative;*/
/*  height: 100%;*/
/*  font-size: 20px;*/
/*  text-align: center;*/
/*}*/
/*.nav>ul li:hover {*/
/*  background-color: #171414;*/
/*  cursor: pointer;*/
/*}*/
/*.nav>ul li:hover>a{*/
/*  color: white;*/
/*}*/
/*.nav>ul li:hover .secnav {*/
/*  display: inline-block;*/
/*  z-index: 2;*/
/*}*/
/*.nav>ul li a {*/
/*  display: inline-block;*/
/*  width: 100%;*/
/*}*/
/*.titleclass{*/
/*  text-align: center;*/
/*  color: white;*/
/*}*/
</style>
