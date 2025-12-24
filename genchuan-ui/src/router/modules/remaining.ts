import { Layout } from '@/utils/routerHelper'

const { t } = useI18n()
/**
 * redirect: noredirect        当设置 noredirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'          设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
 hidden: true              当设置 true 的时候该路由不会再侧边栏出现 如404，login等页面(默认 false)

 alwaysShow: true          当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式，
 只有一个时，会将那个子路由当做根路由显示在侧边栏，
 若你想不管路由下面的 children 声明的个数都显示你的根路由，
 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，
 一直显示根路由(默认 false)

 title: 'title'            设置该路由在侧边栏和面包屑中展示的名字

 icon: 'svg-name'          设置该路由的图标

 noCache: true             如果设置为true，则不会被 <keep-alive> 缓存(默认 false)

 breadcrumb: false         如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)

 affix: true               如果设置为true，则会一直固定在tag项中(默认 false)

 noTagsView: true          如果设置为true，则不会出现在tag中(默认 false)

 activeMenu: '/dashboard'  显示高亮的路由路径

 followAuth: '/dashboard'  跟随哪个路由进行权限过滤

 canTo: true               设置为true即使hidden为true，也依然可以进行路由跳转(默认 false)
 }
 **/
const remainingRouter: AppRouteRecordRaw[] = [
  {
    path: '/redirect',
    component: Layout,
    name: 'Redirect',
    children: [
      {
        path: '/redirect/:path(.*)',
        name: 'Redirect',
        component: () => import('@/views/Redirect/Redirect.vue'),
        meta: {}
      }
    ],
    meta: {
      hidden: true,
      noTagsView: true
    }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    name: 'Home',
    meta: {},
    children: [
      {
        path: 'index',
        component: () => import('@/views/Home/indexNew1.vue'),
        name: 'Index',
        meta: {
          title: t('router.home'),
          icon: 'ep:home-filled',
          noCache: false,
          affix: true
        }
      }
    ]
  },
  {
    path: '/ChartAll',
    name: 'ChartAll',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/ChartAll/index.vue'),
/*    children: [
      {
        path: 'index',

        name: 'Index',
        meta: {
          title: t('router.home'),
          icon: 'ep:home-filled',
          noCache: false,
          affix: true
        }
      }
    ]*/
  },
  {
    path: '/overview/dpzl',
    name: 'dpzl',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/dpzl/index.vue'),
  },
  {
    path: '/overview/urbanmanagement/dpzl',
    name: 'UrbanManagement',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/urbanmanagement/dpzl/index.vue'),
  },
  {
    path: '/overview/municipalfacility/dpzl',
    name: 'MunicipalFacility',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/urbanmanagement/dpzl/components/MunicipalFacility.vue')
  },
  {
    path: '/overview/cityscapeorder/dpzl',
    name: 'CityscapeOrder',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/urbanmanagement/dpzl/components/CityscapeOrder.vue')
  },
  {
    path: '/overview/environsanitation/dpzl',
    name: 'EnvironSanitation',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/urbanmanagement/dpzl/components/EnvironSanitation.vue')
  },
  {
    path: '/overview/gardengreening/dpzl',
    name: 'GardenGreening',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/urbanmanagement/dpzl/components/GardenGreening.vue')
  },

  {
    path: '/overview/waterresourceswateraffairs/dpzl',
    name: 'WaterResourcesWaterAffairs',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/waterresourceswateraffairs/dpzl/index.vue')
  },
  {
    path: '/overview/ecologicalprotection/dpzl',
    name: 'EcologicalProtection',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/ecologicalprotection/dpzl/index.vue')
  },
  {
    path: '/overview/airquality/dpzl',
    name: 'AirQuality',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/ecologicalprotection/dpzl/components/AirQuality.vue')
  },
  {
    path: '/overview/drinkingwater/dpzl',
    name: 'DrinkingWater',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/ecologicalprotection/dpzl/components/DrinkingWater.vue')
  },
  {
    path: '/overview/keypollutantsource/dpzl',
    name: 'KeyPollutantSource',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/ecologicalprotection/dpzl/components/KeyPollutantSource.vue')
  },
  {
    path: '/overview/solidwastedisposal/dpzl',
    name: 'SolidWasteDisposal',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/ecologicalprotection/dpzl/components/SolidWasteDisposal.vue')
  },
  {
    path: '/overview/industrialpark/dpzl',
    name: 'IndustrialPark',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/industrialpark/dpzl/index.vue')
  },
  {
    path: '/overview/transportation/dpzl',
    name: 'Transportation',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/transportation/dpzl/index.vue')
  },
  {
    path: '/overview/trafficefficiency/dpzl',
    name: 'TrafficEfficiency',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/transportation/dpzl/components/TrafficEfficiency.vue')
  },
  {
    path: '/overview/accidentwarning/dpzl',
    name: 'AccidentWarning',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/transportation/dpzl/components/AccidentWarning.vue')
  },
  {
    path: '/overview/servicequality/dpzl',
    name: 'ServiceQuality',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/transportation/dpzl/components/ServiceQuality.vue')
  },
  {
    path: '/overview/disposalefficiency/dpzl',
    name: 'DisposalEfficiency',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/transportation/dpzl/components/DisposalEfficiency.vue')
  },
  {
    path: '/overview/emergencysafety/dpzl',
    name: 'EmergencySafety',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/emergencysafety/dpzl/index.vue')
  },
  {
    path: '/overview/eventdisposaltracking/dpzl',
    name: 'EventDisposalTracking',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/emergencysafety/dpzl/components/EventDisposalTracking.vue')
  },
  {
    path: '/overview/emergencydisposalprogress/dpzl',
    name: 'EmergencyDisposalProgress',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/emergencysafety/dpzl/components/EmergencyDisposalProgress.vue')
  },
  {
    path: '/overview/resourcedistribution/dpzl',
    name: 'ResourceDistribution',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/emergencysafety/dpzl/components/ResourceDistribution.vue')
  },
  {
    path: '/overview/accidenthazardwarning/dpzl',
    name: 'AccidentHazardWarning',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/emergencysafety/dpzl/components/AccidentHazardWarning.vue')
  },

  {
    path: '/overview/hygienehealth/dpzl',
    name: 'HygieneHealth',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/hygienehealth/dpzl/index.vue')
  },
  {
    path: '/overview/businessservices/dpzl',
    name: 'BusinessServices',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/businessservices/dpzl/index.vue')
  },
  {
    path: '/overview/culturesportstourism/dpzl',
    name: 'CultureSportsTourism',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/culturesportstourism/dpzl/index.vue'),
  },
  {
    path: '/overview/tourismscenicspot/dpzl',
    name: 'TourismScenicSpot',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/culturesportstourism/dpzl/components/TourismScenicSpot.vue')
  },
  {
    path: '/overview/tourismactivity/dpzl',
    name: 'TourismActivity',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/culturesportstourism/dpzl/components/TourismActivity.vue')
  },
  {
    path: '/overview/tourismvenue/dpzl',
    name: 'TourismVenue',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/culturesportstourism/dpzl/components/TourismVenue.vue')
  },
  {
    path: '/overview/tourismresource/dpzl',
    name: 'TourismResource',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/culturesportstourism/dpzl/components/TourismResource.vue')
  },

  {
    path: '/overview/smartcommunity/dpzl',
    name: 'SmartCommunity',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/smartcommunity/dpzl/index.vue')
  },
  {
    path: '/overview/comprehensivelaw/dpzl',
    name: 'ComprehensiveLaw',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/comprehensivelaw/dpzl/index.vue')
  },
  {
    path: '/overview/educationmanagement/dpzl',
    name: 'EducationManagement',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/educationmanagement/dpzl/index.vue')
  },
  {
    path: '/overview/marketregulation/dpzl',
    name: ' MarketRegulation',
    meta: {
      hidden: true,
    },
    component: () => import('@/views/overview/marketregulation/dpzl/index.vue')
  },
  // {
  //   path: '/overview/digitalvillage/dpzl',
  //   name: 'DigitalVillage',
  //   meta: {
  //     hidden: true,
  //   },
  //   component: () => import('@/views/overview/digitalvillage/dpzl/index.vue')
  // },
  // {
  //   path: '/overview/marketsupervision/dpzl',
  //   name: 'MarketSupervision',
  //   meta: {
  //     hidden: true,
  //   },
  //   component: () => import('@/views/overview/marketsupervision/dpzl/index.vue')
  // },
  // 在remainingRouter数组中添加以下路由配置（可插入到合适位置，如/user路由附近）
  //todo 首页工作台快速跳转路由配置
  {
    path: '/my-todo',
    component: Layout,
    name: 'MyTodo',
    meta: {
      hidden: true,
      icon: 'fa:tasks',
      title: '我的待办'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/bpm/task/todo/index.vue'),
        name: 'MyTodoIndex',
        meta: {
          hidden: true,
          canTo: true,
          icon: 'fa:tasks',
          title: '我的待办'
        }
      }
    ]
  },
  {
    path: '/my-warning',
    component: Layout,
    name: 'MyWarning',
    meta: {
      hidden: true,
      icon: 'ep:alarm-clock',
      title: '我的预警'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/dataHub/warningAlarm/list/index.vue'),
        name: 'MyWarningIndex',
        meta: {
          hidden: true,
          canTo: true,
          icon: 'ep:alarm-clock',
          title: '我的预警'
        }
      }
    ]
  },
  {
    path: '/my-task',
    component: Layout,
    name: 'MyTask',
    meta: {
      hidden: true,
      icon: 'fa-solid:tasks',
      title: '我的任务'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/bpm/task/manager/index.vue'),
        name: 'MyTaskIndex',
        meta: {
          hidden: true,
          canTo: true,
          icon: 'fa-solid:tasks',
          title: '我的任务'
        }
      }
    ]
  },
  {
    path: '/message-center',
    component: Layout,
    name: 'MessageCenter',
    meta: {
      hidden: true,
      icon: 'ep:message',
      title: '消息中心'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/genchuan/messageCenter/index.vue'),
        name: 'MessageCenterIndex',
        meta: {
          hidden: true,
          canTo: true,
          icon: 'ep:message',
          title: '消息中心'
        }
      }
    ]
  },
  {
    path: '/process-detail',
    component: Layout,
    name: 'ProcessDetail',
    meta: {
      hidden: true,
      title: '流程详情'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/bpm/processInstance/detail/index.vue'),
        name: 'ProcessDetailIndex',
        props: (route) => ({
          id: route.query.id as string,
          taskId: route.query.taskId as string,
          activityId: route.query.activityId as string
        }),
        meta: {
          hidden: true,
          canTo: true,
          title: '流程详情'
        }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    name: 'UserInfo',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'profile',
        component: () => import('@/views/Profile/Index.vue'),
        name: 'Profile',
        meta: {
          canTo: true,
          hidden: true,
          noTagsView: false,
          icon: 'ep:user',
          title: t('common.profile')
        }
      },
      {
        path: 'notify-message',
        component: () => import('@/views/system/notify/my/index.vue'),
        name: 'MyNotifyMessage',
        meta: {
          canTo: true,
          hidden: true,
          noTagsView: false,
          icon: 'ep:message',
          title: '我的站内信'
        }
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    name: 'dict',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'type/data/:dictType',
        component: () => import('@/views/system/dict/data/index.vue'),
        name: 'SystemDictData',
        meta: {
          title: '字典数据',
          noCache: true,
          hidden: true,
          canTo: true,
          icon: '',
          activeMenu: '/system/dict'
        }
      }
    ]
  },

  {
    path: '/codegen',
    component: Layout,
    name: 'CodegenEdit',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'edit',
        component: () => import('@/views/infra/codegen/EditTable.vue'),
        name: 'InfraCodegenEditTable',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '修改生成配置',
          activeMenu: 'infra/codegen/index'
        }
      }
    ]
  },
  {
    path: '/job',
    component: Layout,
    name: 'JobL',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'job-log',
        component: () => import('@/views/infra/job/logger/index.vue'),
        name: 'InfraJobLog',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '调度日志',
          activeMenu: 'infra/job/index'
        }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login/Login.vue'),
    name: 'Login',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/ChartAll',
    component: () => import('@/views/ChartAll/index.vue'),
    name: 'ChartAll',
    meta: {
      hidden: true,
      title: '态势感知',
      noTagsView: true
    }
  },
  {
    path: '/sso',
    component: () => import('@/views/Login/Login.vue'),
    name: 'SSOLogin',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/social-login',
    component: () => import('@/views/Login/SocialLogin.vue'),
    name: 'SocialLogin',
    meta: {
      hidden: true,
      title: t('router.socialLogin'),
      noTagsView: true
    }
  },
  {
    path: '/403',
    component: () => import('@/views/Error/403.vue'),
    name: 'NoAccess',
    meta: {
      hidden: true,
      title: '403',
      noTagsView: true
    }
  },
  {
    path: '/404',
    component: () => import('@/views/Error/404.vue'),
    name: 'NoFound',
    meta: {
      hidden: true,
      title: '404',
      noTagsView: true
    }
  },
  {
    path: '/500',
    component: () => import('@/views/Error/500.vue'),
    name: 'Error',
    meta: {
      hidden: true,
      title: '500',
      noTagsView: true
    }
  },
  {
    path: '/bpm',
    component: Layout,
    name: 'bpm',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'manager/form/edit',
        component: () => import('@/views/bpm/form/editor/index.vue'),
        name: 'BpmFormEditor',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '设计流程表单',
          activeMenu: '/bpm/manager/form'
        }
      },
      {
        path: 'manager/definition',
        component: () => import('@/views/bpm/model/definition/index.vue'),
        name: 'BpmProcessDefinition',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '流程定义',
          activeMenu: '/bpm/manager/model'
        }
      },
      {
        path: 'process-instance/detail',
        component: () => import('@/views/bpm/processInstance/detail/index.vue'),
        name: 'BpmProcessInstanceDetail',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '流程详情',
          activeMenu: '/bpm/task/my'
        },
        props: (route) => ({
          id: route.query.id,
          taskId: route.query.taskId,
          activityId: route.query.activityId
        })
      },

      //thingsBoard 关联使用
      {
        path: 'process-instance/detail1',
        component: () => import('@/views/bpm/processInstance/detail/index1.vue'),
        name: 'BpmProcessInstanceDetail1',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '流程详情',
          activeMenu: '/bpm/task/my' ///tB/a/test66  这个是菜单要哪个active高亮
        },
        props: (route) => ({
          id: route.query.id,
          taskId: route.query.taskId,
          activityId: route.query.activityId,
          thingsBoardId:route.query.thingsBoardId
        })
      },


      //警告列表事件 关联使用
      {
        path: 'process-instance/detail3',
        component: () => import('@/views/bpm/processInstance/detail/index3.vue'),
        name: 'BpmProcessInstanceDetail3',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '流程详情',
          activeMenu: '/bpm/task/my' ///tB/a/test66  这个是菜单要哪个active高亮
        },
        props: (route) => ({
          id: route.query.id,
          taskId: route.query.taskId,
          activityId: route.query.activityId,
          thingsBoardId:route.query.thingsBoardId
        })
      },

      //thingsBoard 单个设备遥测数据详细
      {
        path: 'device-telemetry/detail',
        component: () => import('@/views/thingsBoard/profiles/devicesDetailed.vue'),
        name: 'DeviceTelemetryDetail',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '遥测数据详细',
          activeMenu: '/bpm/task/my' ///tB/a/test66  这个是菜单要哪个active高亮
        },
        props: (route) => ({
          id: route.query.id,
        })
      },



      {
        path: 'process-instance/report',
        component: () => import('@/views/bpm/processInstance/report/index.vue'),
        name: 'BpmProcessInstanceReport',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '数据报表',
          activeMenu: '/bpm/manager/model'
        }
      },
      {
        path: 'oa/leave/create',
        component: () => import('@/views/bpm/oa/leave/create.vue'),
        name: 'OALeaveCreate',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '发起 OA 请假',
          activeMenu: '/bpm/oa/leave'
        }
      },
      {
        path: 'oa/leave/detail',
        component: () => import('@/views/bpm/oa/leave/detail.vue'),
        name: 'OALeaveDetail',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '查看 OA 请假',
          activeMenu: '/bpm/oa/leave'
        }
      },
      //todo配置业务流程表单路由 此配置暂不影响流程
      {
        path: 'alarmWarn/dispatchOrder/create',
        component: () => import('@/views/dataHub/warningAlarm/list/components/DispatchOrderForm.vue'),
        name: 'AlarmWarnDispatchOrder',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '派单',
          activeMenu: '/dataHub/riskWarning/dataHub/warningAlarm/list/index'
        }
      },
      {
        path: 'alarmWarn/dispatchOrder/detail',
        component: () => import('@/views/bpm/processInstance/create/ProcessDefinitionDetail.vue'),
        name: 'AlarmWarnDispatchOrderDetail',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '查看告警预警流程详情',
          activeMenu: '/dataHub/riskWarning/dataHub/warningAlarm/list/index'
        }
      },

      {
        path: 'manager/model/create',
        component: () => import('@/views/bpm/model/form/index.vue'),
        name: 'BpmModelCreate',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '创建流程',
          activeMenu: '/bpm/manager/model'
        }
      },
      {
        path: 'manager/model/:type/:id',
        component: () => import('@/views/bpm/model/form/index.vue'),
        name: 'BpmModelUpdate',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '修改流程',
          activeMenu: '/bpm/manager/model'
        }
      }
    ]
  },
  {
    path: '/mall/product', // 商品中心
    component: Layout,
    name: 'ProductCenter',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'spu/add',
        component: () => import('@/views/mall/product/spu/form/index.vue'),
        name: 'ProductSpuAdd',
        meta: {
          noCache: false, // 需要缓存
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '商品添加',
          activeMenu: '/mall/product/spu'
        }
      },
      {
        path: 'spu/edit/:id(\\d+)',
        component: () => import('@/views/mall/product/spu/form/index.vue'),
        name: 'ProductSpuEdit',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '商品编辑',
          activeMenu: '/mall/product/spu'
        }
      },
      {
        path: 'spu/detail/:id(\\d+)',
        component: () => import('@/views/mall/product/spu/form/index.vue'),
        name: 'ProductSpuDetail',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:view',
          title: '商品详情',
          activeMenu: '/mall/product/spu'
        }
      },
      {
        path: 'property/value/:propertyId(\\d+)',
        component: () => import('@/views/mall/product/property/value/index.vue'),
        name: 'ProductPropertyValue',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:view',
          title: '商品属性值',
          activeMenu: '/product/property'
        }
      }
    ]
  },
  {
    path: '/mall/trade', // 交易中心
    component: Layout,
    name: 'TradeCenter',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'order/detail/:id(\\d+)',
        component: () => import('@/views/mall/trade/order/detail/index.vue'),
        name: 'TradeOrderDetail',
        meta: { title: '订单详情', icon: 'ep:view', activeMenu: '/mall/trade/order' }
      },
      {
        path: 'after-sale/detail/:id(\\d+)',
        component: () => import('@/views/mall/trade/afterSale/detail/index.vue'),
        name: 'TradeAfterSaleDetail',
        meta: { title: '退款详情', icon: 'ep:view', activeMenu: '/mall/trade/after-sale' }
      }
    ]
  },
  {
    path: '/member',
    component: Layout,
    name: 'MemberCenter',
    meta: { hidden: true },
    children: [
      {
        path: 'user/detail/:id',
        name: 'MemberUserDetail',
        meta: {
          title: '会员详情',
          noCache: true,
          hidden: true
        },
        component: () => import('@/views/member/user/detail/index.vue')
      }
    ]
  },
  {
    path: '/pay',
    component: Layout,
    name: 'pay',
    meta: { hidden: true },
    children: [
      {
        path: 'cashier',
        name: 'PayCashier',
        meta: {
          title: '收银台',
          noCache: true,
          hidden: true
        },
        component: () => import('@/views/pay/cashier/index.vue')
      }
    ]
  },
  {
    path: '/diy',
    name: 'DiyCenter',
    meta: { hidden: true },
    component: Layout,
    children: [
      {
        path: 'template/decorate/:id',
        name: 'DiyTemplateDecorate',
        meta: {
          title: '模板装修',
          noCache: true,
          hidden: true,
          activeMenu: '/mall/promotion/diy/template'
        },
        component: () => import('@/views/mall/promotion/diy/template/decorate.vue')
      },
      {
        path: 'page/decorate/:id',
        name: 'DiyPageDecorate',
        meta: {
          title: '页面装修',
          noCache: true,
          hidden: true,
          activeMenu: '/mall/promotion/diy/page'
        },
        component: () => import('@/views/mall/promotion/diy/page/decorate.vue')
      }
    ]
  },
  {
    path: '/crm',
    component: Layout,
    name: 'CrmCenter',
    meta: { hidden: true },
    children: [
      {
        path: 'clue/detail/:id',
        name: 'CrmClueDetail',
        meta: {
          title: '线索详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/clue'
        },
        component: () => import('@/views/crm/clue/detail/index.vue')
      },
      {
        path: 'customer/detail/:id',
        name: 'CrmCustomerDetail',
        meta: {
          title: '客户详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/customer'
        },
        component: () => import('@/views/crm/customer/detail/index.vue')
      },
      {
        path: 'business/detail/:id',
        name: 'CrmBusinessDetail',
        meta: {
          title: '商机详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/business'
        },
        component: () => import('@/views/crm/business/detail/index.vue')
      },
      {
        path: 'contract/detail/:id',
        name: 'CrmContractDetail',
        meta: {
          title: '合同详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/contract'
        },
        component: () => import('@/views/crm/contract/detail/index.vue')
      },
      {
        path: 'receivable-plan/detail/:id',
        name: 'CrmReceivablePlanDetail',
        meta: {
          title: '回款计划详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/receivable-plan'
        },
        component: () => import('@/views/crm/receivable/plan/detail/index.vue')
      },
      {
        path: 'receivable/detail/:id',
        name: 'CrmReceivableDetail',
        meta: {
          title: '回款详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/receivable'
        },
        component: () => import('@/views/crm/receivable/detail/index.vue')
      },
      {
        path: 'contact/detail/:id',
        name: 'CrmContactDetail',
        meta: {
          title: '联系人详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/contact'
        },
        component: () => import('@/views/crm/contact/detail/index.vue')
      },
      {
        path: 'product/detail/:id',
        name: 'CrmProductDetail',
        meta: {
          title: '产品详情',
          noCache: true,
          hidden: true,
          activeMenu: '/crm/product'
        },
        component: () => import('@/views/crm/product/detail/index.vue')
      }
    ]
  },
  {
    path: '/ai',
    component: Layout,
    name: 'Ai',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'image/square',
        component: () => import('@/views/ai/image/square/index.vue'),
        name: 'AiImageSquare',
        meta: {
          title: '绘图作品',
          icon: 'ep:home-filled',
          noCache: false
        }
      },
      {
        path: 'knowledge/document',
        component: () => import('@/views/ai/knowledge/document/index.vue'),
        name: 'AiKnowledgeDocument',
        meta: {
          title: '知识库文档',
          icon: 'ep:document',
          noCache: false,
          activeMenu: '/ai/knowledge'
        }
      },
      {
        path: 'knowledge/document/create',
        component: () => import('@/views/ai/knowledge/document/form/index.vue'),
        name: 'AiKnowledgeDocumentCreate',
        meta: {
          title: '创建文档',
          icon: 'ep:plus',
          noCache: true,
          hidden: true,
          activeMenu: '/ai/knowledge'
        }
      },
      {
        path: 'knowledge/document/update',
        component: () => import('@/views/ai/knowledge/document/form/index.vue'),
        name: 'AiKnowledgeDocumentUpdate',
        meta: {
          title: '修改文档',
          icon: 'ep:edit',
          noCache: true,
          hidden: true,
          activeMenu: '/ai/knowledge'
        }
      },
      {
        path: 'knowledge/retrieval',
        component: () => import('@/views/ai/knowledge/knowledge/retrieval/index.vue'),
        name: 'AiKnowledgeRetrieval',
        meta: {
          title: '文档召回测试',
          icon: 'ep:search',
          noCache: true,
          hidden: true,
          activeMenu: '/ai/knowledge'
        }
      },
      {
        path: 'knowledge/segment',
        component: () => import('@/views/ai/knowledge/segment/index.vue'),
        name: 'AiKnowledgeSegment',
        meta: {
          title: '知识库分段',
          icon: 'ep:tickets',
          noCache: true,
          hidden: true,
          activeMenu: '/ai/knowledge'
        }
      },
      {
        path: 'console/workflow/create',
        component: () => import('@/views/ai/workflow/form/index.vue'),
        name: 'AiWorkflowCreate',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '设计 AI 工作流',
          activeMenu: '/ai/console/workflow'
        }
      },
      {
        path: 'console/workflow/:type/:id',
        component: () => import('@/views/ai/workflow/form/index.vue'),
        name: 'AiWorkflowUpdate',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          title: '设计 AI 工作流',
          activeMenu: '/ai/console/workflow'
        }
      }
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/Error/404.vue'),
    name: '',
    meta: {
      title: '404',
      hidden: true,
      breadcrumb: false
    }
  },
  {
    path: '/iot',
    component: Layout,
    name: 'IOT',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'product/product/detail/:id',
        name: 'IoTProductDetail',
        meta: {
          title: '产品详情',
          noCache: true,
          hidden: true,
          activeMenu: '/iot/device/product'
        },
        component: () => import('@/views/iot/product/product/detail/index.vue')
      },
      {
        path: 'device/detail/:id',
        name: 'IoTDeviceDetail',
        meta: {
          title: '设备详情',
          noCache: true,
          hidden: true,
          activeMenu: '/iot/device/device'
        },
        component: () => import('@/views/iot/device/device/detail/index.vue')
      },
      {
        path: 'plugin/detail/:id',
        name: 'IoTPluginDetail',
        meta: {
          title: '插件详情',
          noCache: true,
          hidden: true,
          activeMenu: '/iot/plugin'
        },
        component: () => import('@/views/iot/plugin/detail/index.vue')
      },
      //todo配置预警告警列表路由 便于其他页面设置跳转到此页面


    ]
  }
]

export default remainingRouter
