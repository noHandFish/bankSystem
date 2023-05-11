import {createRouter, createWebHistory} from 'vue-router'
//使用@/和../的区别是什么？
const routes = [
  {
    path: '/',
    redirect: '/login',
  },

  {
    path: '/home',

    component: () => import('@/pages/home'),
    children: [
      {

        path: "potentialCustomer",
        name: "potentialCustomer",
        component: () => import('@/pages/potentialCustomerPages/potentialCustomer'),
        children: []
      },
      {

        path: "overView1",
        name: "overView1",
        component: () => import('@/pages/overView1')
      },
      {

        path: "newOverview",
        name: "newOverview",
        component: () => import('@/pages/new_overview/NewOverview')
      },
      {

        path: "distribution",
        name: "distribution",
        component: () => import('@/pages/distribution')
      },
      {
        path: "ranking",
        name: "ranking",
        component: () => import('@/pages/ranking')
      },
      {

        path: "newRanking",
        name: "newRanking",
        component: () => import('@/pages/new_ranking/NewRanking')
      },
      {

        path: "customerInf",
        name: "customerInf",
        component: () => import('@/pages/customerInf'),
        children: [
          {
            path: 'businessInf',
            name: 'businessInf',
            component: () => import('@/pages/customerInfPages/businessInf')
          },

          {
            path: 'businessInf_jump',
            name: 'businessInf_jump',
            component: () => import('@/pages/customerInfPages/businessInf_jump')
          },

          {
            path: 'quotaInf',
            name: 'quotaInf',
            component: () => import('@/pages/customerInfPages/quotaInf')
          },
          {
            path: 'guaranteeInf',
            name: 'guaranteeInf',
            component: () => import('@/pages/customerInfPages/guaranteeInf'),
            children: [
              {
                path: 'generalSituation',
                name: 'generalSituation',
                component: () => import('@/components/generalSituation')
              },
              {
                path: 'inGuarantee',
                name: 'inGuarantee',
                component: () => import('@/components/inGuarantee')
              },
              {
                path: 'outGuarantee',
                name: 'outGuarantee',
                component: () => import('@/components/outGuarantee')
              }]
          },
          {
            path: 'relatedBusiness',
            name: 'relatedBusiness',
            component: () => import('@/pages/customerInfPages/relatedBusiness')
          },
          {
            path: 'guaranteecirle',
            name: 'guaranteecirle',
            component: () => import('@/pages/customerInfPages/guaranteecirle')
          },
          {
            path: 'circleJump1',
            name: 'circleJump1',
            component: () => import('@/pages/customerInfPages/circleJump1')
          },
           {
		    path: 'circleDetail',
		    name: 'circleDetail',
		    component: () => import('@/pages/customerInfPages/circleDetail')
		  },

          {
            path: 'additionalInf',
            name: 'additionalInf',
            component: () => import('@/pages/customerInfPages/additionalInf')
          },
          {
            path: 'basicInf',
            name: 'basicInf',
            component: () => import('@/pages/customerInfPages/basicInf')

          },

        ]
      },
      {
        path: "listInf",
        name: "listInf",
        component: () => import('@/pages/listInf')
      },
      {
        path: "videoInf",
        name: "videoInf",
        component: () => import('@/pages/videoInf'),

      },
      {
        path: "businessStatus",
        name: "businessStatus",
        component: () => import('@/pages/businessStatus'),
      },
      {
        path: "doneImage",
        name: "doneImage",
        component: () => import('@/pages/doneImage'),
      },
      {
        path: "onGoingImage",
        name: "onGoingImage",
        component: () => import('@/pages/onGoingImage'),
      },
      {
        path: "manageImage",
        name: "manageImage",
        component: () => import('@/pages/manageImage'),
      },

      {
        path: "guaranteeAnalysis",
        name: "guaranteeAnalysis",
        component: () => import('@/pages/guaranteeAnalysis')
      },
      {
        path: "trendAnalysis",
        name: "trendAnalysis",
        component: () => import('@/pages/trendAnalysis')
      },
      {
        path: "tendencyAnalysis",
        name: "tendencyAnalysis",
        component: () => import('@/pages/tendencyAnalysis')
      },
      {
        path: "financialAnalysis",
        name: "financialAnalysis",
        component: () => import('@/pages/financialAnalysis'),
        children: [
          {
            path: 'parameter',
            name: 'parameter',
            component: () => import('@/pages/parameter'),
            meta: {
              keepAlive: true, // 组件需要缓存
            }
          },
          {
            path: 'costAndEffective',
            name: 'costAndEffective',
            component: () => import('@/pages/costAndEffective'),
            keepAlive: true
          },
          {
            path: 'loanPerformance',
            name: 'loanPerformance',
            component: () => import('@/pages/loanPerformance'),
            keepAlive: true
          },
        ]
      },
      {
        path: "retainCustomerInf",
        name: "retainCustomerInf",
        component: () => import('@/pages/retainCustomerInf')
      },
      {
        path: "humanEntry",
        name: "humanEntry",
        component: () => import('@/pages/humanEntry')
      },
      {
        path: "humanDelete",
        name: "humanDelete",
        component: () => import('@/pages/humanDelete')
      },
      {
        path: "AIentry",
        name: "AIentry",
        component: () => import('@/pages/AiEntry')
      },
      {
        path: "retainVideoInf",
        name: "retainVideoInf",
        component: () => import('@/pages/retainVideoInf')
      },
      {
        path: "accountAdjustment",
        name: "accountAdjustment",
        component: () => import('@/pages/accountAdjustment')
      },
      {
        path: "listInfAnaslysis",
        name: "listInfAnaslysis",
        component: () => import('@/pages/listInfAnaslysis')
      },
      {
        path: 'dataExport',
        name: 'dataExport',
        component: () => import('@/pages/dataExport'),
        children: [
          {
            path: 'approvalForm',
            name: 'approvalForm',
            component: () => import('@/pages/dataExportPages/approvalForm')
          },
          {
            path: 'customerInfExp',
            name: 'customerInfExp',
            component: () => import('@/pages/dataExportPages/customerInfExp')
          },
          {
            path: 'loanDaily',
            name: 'loanDaily',
            keepAlive: true,
            component: () => import('@/pages/dataExportPages/loanDaily')
          },
          {
            path: 'loanLedger',
            name: 'loanLedger',
            component: () => import('@/pages/dataExportPages/loanLedger')
          },
          {
            path: 'regulationReport',
            name: 'regulationReport',
            keepAlive: true,
            component: () => import('@/pages/dataExportPages/regulationReport')
          },
          {
            path: 'videoInfExp',
            name: 'videoInfExp',
            component: () => import('@/pages/dataExportPages/videoInfExp')
          },
          {
            path: 'termsExport',
            name: 'termsExport',
            keepAlive: true,
            component: () => import('@/pages/dataExportPages/termsExport')
          },
        ]
      },
      {
        path: 'myManager',
        name: 'myManager',
        component: () => import('@/pages/myManager')
      },
      {
        path: 'financialAssistant',
        name: 'financialAssistant',
        component: () => import('@/pages/financialAssistant'),
        children: [
          {
            path: 'LoanAmountC',
            name: 'LoanAmountC',
            component: () => import('@/pages/financialAssistantPages/LoanAmountC')
          },
          {
            path: 'LoanProgram',
            name: 'LoanProgram',
            component: () => import('@/pages/financialAssistantPages/LoanProgram')
          }
        ]
      },
      {
        name: 'enterpriseCustomer',
        path: 'enterpriseCustomer',
        component: () => import('@/pages/EnterpriseCustomer/EnterpriseCustomer')
      }
    ],

    redirect: '/home/newOverview'

  },
  {
    path: "/login",
    name: "login",
    component: () => import('@/pages/loginPage')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/pages/register')
  },
  {
    path: '/superManager',
    name: 'superManager',
    component: () => import('@/pages/SuperManager/SuperManager')
  }
]
//在router4中使用createRouter替代了new VueRouter;
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
  //在此处引入所有页面的路由配置数组routes;
})
// router.beforeEach((to, from, next) => {
//   if (to.path === '/login' || to.path === '/register') {
//     next();
//   } else {
//     let token = localStorage.getItem('Authorization');
//     if (token === null || token === '') {
//       alert('登录过期或未登录，请重新登录')
//       next('/login');
//     } else {
//       next();
//     }
//   }
// })
export default router
//导出包含着所有路由配置的router对象,
//在main.js中注入后供全局使用;

