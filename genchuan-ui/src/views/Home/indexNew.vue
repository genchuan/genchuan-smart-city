<template>
  <div>
    <el-card shadow="never">
      <el-skeleton :loading="loading" animated>
        <el-row :gutter="16" justify="space-between" align="middle">
          <!-- 欢迎区域 - 紧凑布局 -->
          <el-col :xl="8" :lg="10" :md="12" :sm="24" :xs="24">
            <div class="flex items-center">
              <el-avatar :src="avatar" :size="56" class="mr-12px">
                <img src="@/assets/imgs/avatar.gif" alt="" />
              </el-avatar>
              <div>
                <div class="text-18px font-medium">
                  {{ t('workplace.welcome') }} {{ username }} {{ t('workplace.happyDay') }}
                </div>
                <div class="mt-6px text-13px text-gray-500">
                  {{ t('workplace.toady') }}，20℃ - 32℃！
                </div>
              </div>
            </div>
          </el-col>
          <!-- 统计数字区域 - 横向紧凑排列 -->
          <el-col :xl="16" :lg="14" :md="12" :sm="24" :xs="24">
            <div class="flex items-center justify-end h-full gap-4 lt-sm:justify-center">
              <div class="stat-item">
                <div class="stat-label">{{ t('workplace.task') }}</div>
                <div class="stat-value">{{ totalSate.task }}</div>
              </div>
              <el-divider direction="vertical" />
              <div class="stat-item">
                <div class="stat-label">{{ t('workplace.toDo') }}</div>
                <div class="stat-value">{{ totalSate.todo }}</div>
              </div>
              <el-divider direction="vertical" />
              <div class="stat-item">
                <div class="stat-label">{{ t('workplace.alarm') }}</div>
                <div class="stat-value">{{ totalSate.alarm }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-skeleton>
    </el-card>
    <el-row class="mt-8px" :gutter="8" justify="space-between">
      <!-- 左侧主内容区：工作台核心内容 + 快捷入口（迁移后） + 图表 -->
      <el-col :xl="16" :lg="16" :md="24" :sm="24" :xs="24" class="mb-8px">
        <!-- 工作台核心卡片（原快捷入口卡片位置） -->
        <el-card shadow="never">
          <template #header>
            <div class="h-3">
              <span>{{ t('workplace.title') }}</span>
            </div>
          </template>
          <el-skeleton :loading="loading" animated>
            <el-row :gutter="12">
              <el-col
                v-for="(item, index) in shortcutRoutes"
                :key="`shortcut-${index}`"
                :xl="6" :lg="6" :md="12" :sm="12" :xs="24"
              >
                <el-card
                  shadow="hover"
                  class="cursor-pointer shortcut-card"
                  @click="handleRouteJump(item.path)"
                >
                  <div class="flex-col-center py-4px">
                    <Icon :icon="item.icon" :size="28" class="text-primary mb-4px" />
                    <span class="text-14px font-medium">{{ item.name }}</span>
                    <span class="mt-2px text-10px text-gray-400">{{ item.desc }}</span>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-skeleton>
        </el-card>
        <!-- 快捷入口卡片（迁移至工作台和统计区中间） -->
        <el-card shadow="never" class="mt-8px">
          <template #header>
            <div class="h-3 flex justify-between">
              <span>{{ t('workplace.shortcutOperation') }}</span>
            </div>
          </template>
          <el-skeleton :loading="loading" animated>
            <el-row :gutter="16" class="py-4px">
              <!-- 响应式调整：大屏span=4，小屏span=8 -->
              <el-col
                v-for="item in shortcut"
                :key="`team-${item.name}`"
                :xl="4" :lg="4" :md="6" :sm="8" :xs="8"
                class="mb-4px"
              >
                <div class="flex-col-center p-3 rounded-md hover:bg-gray-50 cursor-pointer">
                  <Icon :icon="item.icon" class="mr-2 mb-2" />
                  <el-link type="default" :underline="false" @click="setWatermark(item.name)">
                    <span class="text-12px">{{ item.name }}</span>
                  </el-link>
                </div>
              </el-col>
            </el-row>
          </el-skeleton>
        </el-card>
        <!-- 图表区域 -->
        <el-card shadow="never" class="mt-8px">
          <el-skeleton :loading="loading" animated>
            <el-row :gutter="20" justify="space-between">
              <el-col :xl="10" :lg="10" :md="24" :sm="24" :xs="24">
                <el-card shadow="hover" class="mb-8px">
                  <el-skeleton :loading="loading" animated>
                    <Echart :options="pieOptionsData" :height="240" />
                  </el-skeleton>
                </el-card>
              </el-col>
              <el-col :xl="14" :lg="14" :md="24" :sm="24" :xs="24">
                <el-card shadow="hover" class="mb-8px">
                  <el-skeleton :loading="loading" animated>
                    <Echart :options="barOptionsData" :height="240" />
                  </el-skeleton>
                </el-card>
              </el-col>
            </el-row>
          </el-skeleton>
        </el-card>
      </el-col>
      <!-- 右侧边栏：待办任务列表（上移后） -->
      <el-col :xl="8" :lg="8" :md="24" :sm="24" :xs="24" class="mb-8px">
        <!-- 待办任务列表（替代原快捷入口位置） -->
        <el-card shadow="never">
          <template #header>
            <div class="h-3 flex justify-between">
              <span>{{ t('workplace.listTitle') }}</span>
              <el-link type="primary" :underline="false" size="small">{{ t('action.more') }}</el-link>
            </div>
          </template>
          <el-skeleton :loading="loading" animated>
            <div class="space-y-1 max-h-500 overflow-y-auto pr-2">
              <div
                v-for="(item, index) in todoTasks"
                :key="`todo-task-${index}`"
                class="cursor-pointer p-3 hover:bg-gray-50 rounded-md"
                @click="handleTaskJump(item)"
              >
                <div class="flex items-center">
                  <el-avatar :src="avatar" :size="30" class="mr-12px">
                    <img src="@/assets/imgs/avatar.gif" alt="" />
                  </el-avatar>
                  <div class="flex-1 min-w-0">
                    <div class="text-13px truncate">
                      <Highlight :keys="['燃气管道事件流程', item.taskName]">
                        {{ item.processName }} : {{ item.taskName }}
                      </Highlight>
                    </div>
                    <div class="mt-2px text-11px text-gray-400">
                      发起时间：{{ formatTime(item.createTime, 'yyyy-MM-dd HH:mm') }}
                    </div>
                  </div>
                  <el-icon class="text-gray-400 text-14px ml-2">
                    <ArrowRight />
                  </el-icon>
                </div>
              </div>
            </div>
          </el-skeleton>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script lang="ts" setup>
import { set } from 'lodash-es'
import { EChartsOption } from 'echarts'
import { formatTime } from '@/utils'
import * as TaskApi from '@/api/bpm/task'
import { AlarmStatisticsApi } from '@/api/dataHub/warningAlarm/statistics'
import { useUserStore } from '@/store/modules/user'
import { useWatermark } from '@/hooks/web/useWatermark'
import { usePermissionStore } from '@/store/modules/permission'
import type { WorkplaceTotal, Shortcut, TodoTaskInfo } from './types'
import { pieOptions, barOptions } from './echarts-data'
import { WarningAlertListTableApi } from '@/api/dataHub/warningAlarm/list'
import { useRouter } from 'vue-router'
import { ArrowRight } from '@element-plus/icons-vue'
defineOptions({ name: 'Home' })
const { t } = useI18n()
const userStore = useUserStore()
const { setWatermark } = useWatermark()
const router = useRouter()
const loading = ref(true)
const avatar = userStore.getUser.avatar
const username = userStore.getUser.nickname
const permissionStore = usePermissionStore()
// 快捷入口配置（工作台核心入口）
const shortcutRoutes = reactive([
  {
    name: t('workplace.myTodo'),
    icon: 'fa:tasks',
    path: '/my-todo/index',
    desc: t('workplace.todoDesc')
  },
  {
    name: t('workplace.myWarning'),
    icon: 'ep:alarm-clock',
    path: '/my-warning/index',
    desc: t('workplace.warningDesc')
  },
  {
    name: t('workplace.myTask'),
    icon: 'fa-solid:tasks',
    path: '/my-task/index',
    desc: t('workplace.taskDesc')
  },
  {
    name: t('workplace.messageCenter'),
    icon: 'ep:message',
    path: '/message-center/index',
    desc: t('workplace.messageDesc')
  }
])
// 工具类快捷入口（迁移后的横向布局）
let shortcut = reactive<Shortcut[]>([])
const getShortcut = async () => {
  const data = [
    { name: 'Github', icon: 'akar-icons:github-fill', url: 'github.io' },
    { name: 'Vue', icon: 'logos:vue', url: 'vuejs.org' },
    { name: 'Vite', icon: 'vscode-icons:file-type-vite', url: 'https://vitejs.dev/' },
    { name: 'Angular', icon: 'logos:angular-icon', url: 'github.io' },
    { name: 'React', icon: 'logos:react', url: 'github.io' },
    { name: 'Webpack', icon: 'logos:webpack', url: 'github.io' }
  ]
  shortcut = Object.assign(shortcut, data)
}
const pieOptionsData = reactive<EChartsOption>(pieOptions) as EChartsOption
let totalSate = reactive<WorkplaceTotal>({
  project: 0,
  access: 0,
  todo: 0,
  task: 0,
  alarm: 0
})
let todoTasks = reactive<TodoTaskInfo[]>([])
// 获取数据逻辑
const getCount = async () => {
  const doneTaskData = await TaskApi.getTaskManagerPage({ pageNo: 1, pageSize: 100 })
  const todoTaskData = await TaskApi.getTaskTodoPage({ pageNo: 1, pageSize: 100 })
  const alarmDataOfUser = await WarningAlertListTableApi.getWarningAlertListTablePage({
    pageNo: 1,
    pageSize: 100,
    responsiblePerson: userStore.getUser.id
  })
  totalSate.task = doneTaskData.total
  totalSate.todo = todoTaskData.total
  totalSate.alarm = alarmDataOfUser.total
  // 关键修改：processInstanceId 从 task.processInstance.id 获取（确保流程实例编号有效）
  todoTasks = todoTaskData.list.map(task => ({
    taskId: task.id,
    taskName: task.name,
    processName: task.processInstance.name,
    processInstanceId: task.processInstance.id, // 修复：从 processInstance.id 取值，而非 task.processInstanceId
    createTime: task.createTime,
    startUserName: task.processInstance.startUser.nickname
  }))
}
const getUserAccessSource = async () => {
  const res = await TaskApi.taskStatistics()
  const data = res.pieData
  set(pieOptionsData, 'legend.data', data.map((v) => t(v.name)))
  pieOptionsData!.series![0].data = data.map((v) => ({ name: t(v.name), value: v.value }))
}
const barOptionsData = reactive<EChartsOption>(barOptions) as EChartsOption
const getWeeklyUserActivity = async () => {
  const data = await AlarmStatisticsApi.getLevelAlarmsOfResponsible({ responsiblePerson: userStore.getUser.id })
  set(barOptionsData, 'xAxis.data', data.map((v) => t(v.name)))
  set(barOptionsData, 'series', [{ name: t('analysis.alarmNum'), data: data.map((v) => v.value), type: 'bar' }])
}
// 路由跳转方法
const handleRouteJump = (path: string) => router.push(path)
// 关键优化：跳转时增加参数校验，确保必填参数非空
const handleTaskJump = (item: TodoTaskInfo) => {
  if (!item.processInstanceId) {
    ElMessage.error('流程实例编号不存在，无法查看详情')
    return
  }

  router.push({
    path: '/process-detail/index',
    query: {
      id: item.processInstanceId, // 传递有效的流程实例编号（修复后的值）
      taskId: item.taskId,
      activityId: '' // 可选参数，空值不影响
    }
  })
}
// 初始化加载所有数据
const getAllApi = async () => {
  await Promise.all([getCount(), getShortcut(), getUserAccessSource(), getWeeklyUserActivity()])
  loading.value = false
}
getAllApi()
</script>
<style scoped>
/* 基础样式 */
.cursor-pointer { cursor: pointer; }
.space-y-1 > div:not(:last-child) { margin-bottom: 8px; }
/* 统计数字样式 */
.stat-item {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 0 8px;
}
.stat-label {
  font-size: 12px;
  color: var(--el-text-color-placeholder);
  margin-bottom: 4px;
}
.stat-value {
  font-size: 18px;
  font-weight: 500;
  color: var(--el-text-color-primary);
}
/* 卡片hover效果 */
.el-card:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08) !important;
  transition: box-shadow 0.2s ease;
}
/* 工作台核心入口卡片样式（自适应高度） */
.shortcut-card {
  height: auto !important;
  min-height: 70px;
  box-sizing: border-box;
}
/* 居中布局工具类 */
.flex-col-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
/* 迁移后的工具类快捷入口样式 */
.el-col[v-for="item in shortcut"] .flex-col-center {
  text-align: center;
}
.el-col[v-for="item in shortcut"] .el-link {
  display: inline-block;
  width: 100%;
}
/* 待办任务列表滚动样式 */
.max-h-500 {
  max-height: 500px;
}
.overflow-y-auto::-webkit-scrollbar {
  width: 4px;
}
.overflow-y-auto::-webkit-scrollbar-thumb {
  background-color: #e5e7eb;
  border-radius: 2px;
}
/* 响应式调整 */
@media (max-width: 768px) {
  .stat-item { align-items: center; }
  .el-row { flex-direction: column; gap: 12px !important; }
}
</style>
