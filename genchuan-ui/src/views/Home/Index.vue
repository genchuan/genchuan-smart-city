<template>
  <div>
    <el-card shadow="never">
      <el-skeleton :loading="loading" animated>
        <el-row :gutter="16" justify="space-between">
          <el-col :xl="12" :lg="12" :md="12" :sm="24" :xs="24">
            <div class="flex items-center">
              <el-avatar :src="avatar" :size="70" class="mr-16px">
                <img src="@/assets/imgs/avatar.gif" alt=""/>
              </el-avatar>
              <div>
                <div class="text-20px">
                  {{ t('workplace.welcome') }} {{ username }} {{ t('workplace.happyDay') }}
                </div>
                <div class="mt-10px text-14px text-gray-500">
                  {{ t('workplace.toady') }}，20℃ - 32℃！
                </div>
              </div>
            </div>
          </el-col>
          <el-col :xl="12" :lg="12" :md="12" :sm="24" :xs="24">
            <div class="h-70px flex items-center justify-end lt-sm:mt-10px">
              <div class="px-8px text-right">
                <div class="mb-16px text-14px text-gray-400">{{ t('workplace.project') }}</div>
                <CountTo
                    class="text-20px"
                    :start-val="0"
                    :end-val="totalSate.project"
                    :duration="2600"
                />
              </div>
              <el-divider direction="vertical"/>
              <div class="px-8px text-right">
                <div class="mb-16px text-14px text-gray-400">{{ t('workplace.toDo') }}</div>
                <CountTo
                    class="text-20px"
                    :start-val="0"
                    :end-val="totalSate.todo"
                    :duration="2600"
                />
              </div>
              <!--<el-divider direction="vertical" border-style="dashed" />-->
              <!--<div class="px-8px text-right">-->
              <!--  <div class="mb-16px text-14px text-gray-400">{{ t('workplace.access') }}</div>-->
              <!--  <CountTo-->
              <!--    class="text-20px"-->
              <!--    :start-val="0"-->
              <!--    :end-val="totalSate.access"-->
              <!--    :duration="2600"-->
              <!--  />-->
              <!--</div>-->
            </div>
          </el-col>
        </el-row>
      </el-skeleton>
    </el-card>
  </div>

  <el-row class="mt-8px" :gutter="8" justify="space-between">
    <el-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24" class="mb-8px">
      <el-card shadow="never">
        <template #header>
          <div class="h-3 flex justify-between">
            <span>快捷应用切换入口</span>
            <!--href="#"-->
            <!--            <el-link
                          type="primary"
                          :underline="false"

                          target="_blank"
                        >
                          {{ t('action.more') }}
                        </el-link>-->
          </div>
        </template>
        <el-skeleton :loading="loading" animated>
          <el-row>
            <el-col
                v-for="(item, index) in projects"
                :key="`card-${index}`"
                :xl="12"
                :lg="12"
                :md="12"
                :sm="24"
                :xs="24"
            >
              <!--@click="chartsClick(item)"-->
              <el-card shadow="hover" class="mr-5px mt-5px" style="cursor: pointer">
                <div class="flex items-center">
                  <!--<Icon :icon="item.icon" :size="25" class="mr-8px" />-->
                  <img :src="item.icon" style="width: 25px" class="mr-8px"/>
                  <span class="text-16px">{{ item.name }}</span>
                </div>
                <div class="mt-12px text-9px text-gray-400">
                  <el-button size="mini" v-for="(item3,key3) in item.butList" :key="key3" @click="goToRoute(item3.url)">{{ item3.name }}</el-button>
                </div>
                <!--                <div class="mt-12px text-9px text-gray-400">{{ t(item.message) }}</div>
                                <div class="mt-12px flex justify-between text-12px text-gray-400">
                                  <span>{{ item.personal }}</span>
                                  <span>{{ formatTime(item.time, 'yyyy-MM-dd') }}</span>
                                </div>-->
              </el-card>
            </el-col>
          </el-row>
        </el-skeleton>
      </el-card>
      <!--      <el-card shadow="never" class="mt-8px">
              <el-skeleton :loading="loading" animated>
                <el-row :gutter="20" justify="space-between">
                  <el-col :xl="10" :lg="10" :md="24" :sm="24" :xs="24">
                    <el-card shadow="hover" class="mb-8px">
                      <el-skeleton :loading="loading" animated>
                        <Echart :options="pieOptionsData" :height="280" />
                      </el-skeleton>
                    </el-card>
                  </el-col>
                  <el-col :xl="14" :lg="14" :md="24" :sm="24" :xs="24">
                    <el-card shadow="hover" class="mb-8px">
                      <el-skeleton :loading="loading" animated>
                        <Echart :options="barOptionsData" :height="280" />
                      </el-skeleton>
                    </el-card>
                  </el-col>
                </el-row>
              </el-skeleton>
            </el-card>-->
    </el-col>
    <!--<el-col :xl="8" :lg="8" :md="24" :sm="24" :xs="24">-->
    <!--<el-card shadow="never">-->
    <!--  <template #header>-->
    <!--    <div class="h-3 flex justify-between">-->
    <!--      <span>{{ t('workplace.shortcutOperation') }}</span>-->
    <!--    </div>-->
    <!--  </template>-->
    <!--  <el-skeleton :loading="loading" animated>-->
    <!--    <el-row>-->
    <!--      <el-col v-for="item in shortcut" :key="`team-${item.name}`" :span="8" class="mb-8px">-->
    <!--        <div class="flex items-center">-->
    <!--          <Icon :icon="item.icon" class="mr-8px" />-->
    <!--          <el-link type="default" :underline="false" @click="setWatermark(item.name)">-->
    <!--            {{ item.name }}-->
    <!--          </el-link>-->
    <!--        </div>-->
    <!--      </el-col>-->
    <!--    </el-row>-->
    <!--  </el-skeleton>-->
    <!--</el-card>-->
    <!--      <el-card shadow="never">
            <template #header>
              <div class="h-3 flex justify-between">
                <span>{{ t('workplace.notice') }}</span>
                &lt;!&ndash;<el-link type="primary" :underline="false">{{ t('action.more') }}</el-link>&ndash;&gt;
              </div>
            </template>
            <el-skeleton :loading="loading" animated>
              <div v-for="(item, index) in notice" :key="`dynamics-${index}`">
                <div class="flex items-center">
                  <el-avatar :src="avatar" :size="35" class="mr-16px">
                    <img src="@/assets/imgs/avatar.gif" alt="" />
                  </el-avatar>
                  <div>
                    <div class="text-14px">
                      <Highlight :keys="item.keys.map((v) => t(v))">
                        {{ item.type }} : {{ item.title }}
                      </Highlight>
                    </div>
                    <div class="mt-16px text-12px text-gray-400">
                      {{ formatTime(item.date, 'yyyy-MM-dd') }}
                    </div>
                  </div>
                </div>
                <el-divider />
              </div>
            </el-skeleton>
          </el-card>-->
    <!--</el-col>-->
  </el-row>
</template>
<script lang="ts" setup>
import {set} from 'lodash-es'
import {EChartsOption} from 'echarts'
import {formatTime} from '@/utils'

import {useUserStore} from '@/store/modules/user'
import {useWatermark} from '@/hooks/web/useWatermark'
import type {WorkplaceTotal, Project, Notice, Shortcut} from './types'
import {pieOptions, barOptions} from './echarts-data';

import {useRouter} from 'vue-router';

const router = useRouter();


// 跳转路由的方法
const goToRoute = (url) => {
  // 跳转到路由为 'aa' 的地址
  if (url) {
    router.push(url)
  }
}


defineOptions({name: 'Home'})

const {t} = useI18n()
const userStore = useUserStore()
const {setWatermark} = useWatermark()
const loading = ref(true)
const avatar = userStore.getUser.avatar
const username = userStore.getUser.nickname
const pieOptionsData = reactive<EChartsOption>(pieOptions) as EChartsOption
// 获取统计数
let totalSate = reactive<WorkplaceTotal>({
  project: 0,
  access: 0,
  todo: 0
})

// 快捷动态入口跳转
const chartsClick = (item) => {
  userStore.headerTopActive = item.tag;
  router.push('/ChartAll');
}

const getCount = async () => {
  const data = {
    project: 82,
    access: 2340,
    todo: 160
  }
  totalSate = Object.assign(totalSate, data)
}

// 获取项目数
let projects = reactive<Project[]>([])
const getProject = async () => {
  const data = [
    {
      name: '城管住建',
      icon: new URL('@/assets/imgs/home/1.png', import.meta.url).href,
      message: 'urbanManagementHousingAndConstruction',
      personal: '城管住建',
      time: new Date(),
      /*<el-button size="mini">大屏总览</el-button>
          <el-button size="mini"></el-button>
          <el-button size="mini"></el-button>
          <el-button size="mini">照明监测运维系统</el-button>*/
      butList: [
        {name: '大屏总览', url: 'zhcg/bigScreenOverview'},
        {name: '排水管网运行监测系统', url: 'zhcg/operationMonitoring/shizheng/aa'},
        {name: '照明监测运维系统', url: 'zhcg/operationMonitoring/shizheng/zm'},
        {name: '空气质量监测系统', url: 'zhcg/operationMonitoring/huanwei/a'},
      ],
      tag: '1'
    },
    {
      name: '水利水务',
      icon: new URL('@/assets/imgs/home/2.png', import.meta.url).href,
      message: 'waterConservancyAndWaterAffairs',
      personal: '水利水务',
      time: new Date(),
      butList: [
        {name: '大屏总览', url: ''},
        {name: '排水管网运行监测系统', url: ''},
      ],
      tag: '2'
    },
    {
      name: '生态环保',
      icon: new URL('@/assets/imgs/home/3.png', import.meta.url).href,
      message: 'ecologicalAndEnvironmentalProtection',
      personal: '生态环保',
      time: new Date(),
      tag: '3'
    },
    {
      name: '能源管理',
      icon: new URL('@/assets/imgs/home/4.png', import.meta.url).href,
      message: 'energyManagement',
      personal: '能源管理',
      time: new Date(),
      tag: '5'
    },
    {
      name: '产业园区',
      icon: new URL('@/assets/imgs/home/5.png', import.meta.url).href,
      message: 'industrialPark',
      personal: '产业园区',
      time: new Date(),
      tag: '61'
    },
    {
      name: '市场监督',
      icon: new URL('@/assets/imgs/home/6.png', import.meta.url).href,
      message: 'marketSurveillance',
      personal: '市场监督',
      time: new Date(),
      tag: '62'
    },
    {
      name: '交通运输',
      icon: new URL('@/assets/imgs/home/7.png', import.meta.url).href,
      message: 'transportation',
      personal: '交通运输',
      time: new Date(),
      tag: '62'
    },
    {
      name: '应急安全',
      icon: new URL('@/assets/imgs/home/8.png', import.meta.url).href,
      message: 'emergencySafety',
      personal: '应急安全',
      time: new Date(),
      tag: '62'
    },
    {
      name: '文体旅游',
      icon: new URL('@/assets/imgs/home/10.png', import.meta.url).href,
      message: 'culturalAndSportsTourism',
      personal: '文体旅游',
      time: new Date(),
      tag: '62'
    },
    {
      name: '社区乡村',
      icon: new URL('@/assets/imgs/home/9.png', import.meta.url).href,
      message: 'communityVillage',
      personal: '社区乡村',
      time: new Date(),
      tag: '62'
    },
  ]
  projects = Object.assign(projects, data)
}

// 获取通知公告
let notice = reactive<Notice[]>([])
const getNotice = async () => {
  const data = [
    {
      title: '东郊河河段-07:20-处理中',
      type: '通知',
      keys: ['通知'],
      date: new Date()
    },
    {
      title: '城北街道-市政设施损坏-09:30-处理中',
      type: '通知',
      keys: ['通知'],
      date: new Date()
    },
    {
      title: '城西水闸 水闸开合异常-10:10-已解决',
      type: '通知',
      keys: ['通知'],
      date: new Date()
    },

    {
      title: '城西公园-垃圾堆积-10:11-处理中',
      type: '通知',
      keys: ['通知'],
      date: new Date()
    },
    {
      title: '城中商业区-道路清扫质量差-10:35-处理中',
      type: '通知',
      keys: ['通知'],
      date: new Date()
    },
    {
      title: `维护通知： ${formatTime(new Date(), 'yyyy-MM-dd')} 系统凌晨维护`,
      type: '公告',
      keys: ['公告', '最广泛'],
      date: new Date()
    }
  ]
  notice = Object.assign(notice, data)
}

// 获取快捷入口
let shortcut = reactive<Shortcut[]>([])

const getShortcut = async () => {
  const data = [
    {
      name: 'Github',
      icon: 'akar-icons:github-fill',
      url: 'github.io'
    },
    {
      name: 'Vue',
      icon: 'logos:vue',
      url: 'vuejs.org'
    },
    {
      name: 'Vite',
      icon: 'vscode-icons:file-type-vite',
      url: 'https://vitejs.dev/'
    },
    {
      name: 'Angular',
      icon: 'logos:angular-icon',
      url: 'github.io'
    },
    {
      name: 'React',
      icon: 'logos:react',
      url: 'github.io'
    },
    {
      name: 'Webpack',
      icon: 'logos:webpack',
      url: 'github.io'
    }
  ]
  shortcut = Object.assign(shortcut, data)
}

// 用户来源
const getUserAccessSource = async () => {
  const data = [
    {value: 335, name: '管网排口'},
    {value: 310, name: '市政公用'},
    {value: 234, name: '市容环卫'},
    {value: 135, name: '园林绿化'},
    {value: 1548, name: '智能井盖'}
  ]
  set(
      pieOptionsData,
      'legend.data',
      data.map((v) => t(v.name))
  )
  pieOptionsData!.series![0].data = data.map((v) => {
    return {
      name: t(v.name),
      value: v.value
    }
  })
}
const barOptionsData = reactive<EChartsOption>(barOptions) as EChartsOption

// 周活跃量
const getWeeklyUserActivity = async () => {
  const data = [
    {value: 13253, name: 'analysis.monday'},
    {value: 34235, name: 'analysis.tuesday'},
    {value: 26321, name: 'analysis.wednesday'},
    {value: 12340, name: 'analysis.thursday'},
    {value: 24643, name: 'analysis.friday'},
    {value: 1322, name: 'analysis.saturday'},
    {value: 1324, name: 'analysis.sunday'}
  ]
  set(
      barOptionsData,
      'xAxis.data',
      data.map((v) => t(v.name))
  )
  set(barOptionsData, 'series', [
    {
      name: t('analysis.activeQuantity'),
      data: data.map((v) => v.value),
      type: 'bar'
    }
  ])
}

const getAllApi = async () => {
  await Promise.all([
    getCount(),
    getProject(),
    getNotice(),
    getShortcut(),
    getUserAccessSource(),
    getWeeklyUserActivity()
  ])
  loading.value = false
}

getAllApi()
</script>
