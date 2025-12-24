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
            <!-- 常用系统入口区域 - 新增自定义逻辑 -->
            <div class="mb-4">
              <div class="flex justify-between items-center mb-2">
                <h4 class="text-14px font-medium text-gray-800">{{ t('workplace.commonSystemEntry') }}</h4>
                <div class="flex gap-2">
                  <!-- 编辑模式切换按钮 -->
                  <el-button
                    type="text"
                    size="small"
                    @click="handleEditToggle('app')"
                  >
                    <Icon :icon="editMode.app ? 'ep:close' : 'ep:edit'" />
                    {{ editMode.app ? t('action.cancel') : t('action.edit') }}
                  </el-button>
                  <!-- 新增按钮 - 触发树形下拉框 -->
                  <el-dropdown
                    :teleported="true"
                    popper-class="quick-dropdown-popper"
                    @command="() => {}"
                    :visible="showTreeDropdown && currentType === 'app'"
                    @visible-change="val => { if (!val) showTreeDropdown = false }"
                  >
                    <el-button type="text" size="small" class="text-primary" @click="handleShowTreeDropdown('app')">
                      <Icon icon="ep:plus" />
                      {{ t('action.add') }}
                    </el-button>
                    <template #dropdown>
                      <div class="quick-dropdown-container">
                        <!-- 菜单搜索框 -->
                        <el-input
                          v-model="searchMenuText"
                          placeholder="搜索菜单名称"
                          size="small"
                          class="mb-2"
                          clearable
                          @input="handleSearchMenu"
                        />
                        <!-- 树形菜单列表 -->
                        <div class="tree-container">
                          <el-tree
                            ref="menuTreeRef"
                            :data="filteredMenus"
                            :props="treeProps"
                            :filter-node-method="filterMenuNode"
                            @node-click="(node) => handleTreeNodeClick(node, 'app')"
                            node-key="id"
                            :highlight-current="true"
                            :default-expanded-keys="[]"
                            :expand-on-click-node="false"
                          >
                            <template #default="{ node, data }">
                              <span class="tree-node" :class="{ 'cursor-pointer': isLeafNode(data) }">
                                <Icon :icon="data.icon || 'ep:menu'" class="mr-1 text-12px" />
                                {{ node.label }}
                              </span>
                            </template>
                          </el-tree>
                        </div>
                      </div>
                    </template>
                  </el-dropdown>
                </div>
              </div>
              <!-- 常用系统展示区域 - 马卡龙色图标+名称 -->
              <div class="h-24 bg-gray-50 rounded-md p-2 flex flex-wrap items-center gap-3 overflow-x-auto">
                <div
                  v-for="(item, index) in appList"
                  :key="`app-${item.id}`"
                  class="flex flex-col items-center cursor-pointer group"
                  @click="handleRouteJump(item.path)"
                >
                  <div
                    :style="{ backgroundColor: macaronColors[index % macaronColors.length], borderRadius: '8px', padding: '8px' }"
                    class="mb-1"
                  >
                    <Icon
                      :icon="item.icon || 'fa-solid:apple-alt'"
                      :size="20"
                      class="text-white"
                    />
                  </div>
                  <span class="text-12px text-gray-800 truncate w-20 text-center">{{ item.name }}</span>
                  <!-- 编辑模式下显示删除按钮 -->
                  <Icon
                    :icon="'ep:delete'"
                    v-if="editMode.app"
                    class="text-red-500 text-14px mt-1 opacity-0 group-hover:opacity-100 transition-opacity"
                    @click.stop="handleDeleteQuickMenu(item.id, 'app')"
                  />
                </div>
                <!-- 空状态提示 -->
                <div v-if="appList.length === 0" class="text-gray-400 text-12px">
                  {{ t('workplace.noCommonSystem') }}
                </div>
              </div>
            </div>
            <!-- 快捷功能直达区域 - 逻辑与常用系统一致 -->
            <div>
              <div class="flex justify-between items-center mb-2">
                <h4 class="text-14px font-medium text-gray-800">{{ t('workplace.quickFunctionAccess') }}</h4>
                <div class="flex gap-2">
                  <el-button
                    type="text"
                    size="small"
                    @click="handleEditToggle('data')"
                  >
                    <Icon :icon="editMode.data ? 'ep:close' : 'ep:edit'" />
                    {{ editMode.data ? t('action.cancel') : t('action.edit') }}
                  </el-button>
                  <el-dropdown
                    :teleported="true"
                    popper-class="quick-dropdown-popper"
                    @command="() => {}"
                    :visible="showTreeDropdown && currentType === 'data'"
                    @visible-change="val => { if (!val) showTreeDropdown = false }"
                  >
                    <el-button type="text" size="small" class="text-primary" @click="handleShowTreeDropdown('data')">
                      <Icon icon="ep:plus" />
                      {{ t('action.add') }}
                    </el-button>
                    <template #dropdown>
                      <div class="quick-dropdown-container">
                        <el-input
                          v-model="searchMenuText"
                          placeholder="搜索菜单名称"
                          size="small"
                          class="mb-2"
                          clearable
                          @input="handleSearchMenu"
                        />
                        <div class="tree-container">
                          <el-tree
                            ref="menuTreeRef"
                            :data="filteredMenus"
                            :props="treeProps"
                            :filter-node-method="filterMenuNode"
                            @node-click="(node) => handleTreeNodeClick(node, 'data')"
                            node-key="id"
                            :highlight-current="true"
                            :default-expanded-keys="[]"
                            :expand-on-click-node="false"
                          >
                            <template #default="{ node, data }">
                              <span class="tree-node" :class="{ 'cursor-pointer': isLeafNode(data) }">
                                <Icon :icon="data.icon || 'ep:menu'" class="mr-1 text-12px" />
                                {{ node.label }}
                              </span>
                            </template>
                          </el-tree>
                        </div>
                      </div>
                    </template>
                  </el-dropdown>
                </div>
              </div>
              <div class="h-24 bg-gray-50 rounded-md p-2 flex flex-wrap items-center gap-3 overflow-x-auto">
                <div
                  v-for="(item, index) in dataList"
                  :key="`data-${item.id}`"
                  class="flex flex-col items-center cursor-pointer group"
                  @click="handleRouteJump(item.path)"
                >
                  <div
                    :style="{ backgroundColor: macaronColors[index % macaronColors.length], borderRadius: '8px', padding: '8px' }"
                    class="mb-1"
                  >
                    <Icon
                      :icon="item.icon || 'fa:database'"
                      :size="20"
                      class="text-white"
                    />
                  </div>
                  <span class="text-12px text-gray-800 truncate w-20 text-center">{{ item.name }}</span>
                  <Icon
                    :icon="'ep:delete'"
                    v-if="editMode.data"
                    class="text-red-500 text-14px mt-1 opacity-0 group-hover:opacity-100 transition-opacity"
                    @click.stop="handleDeleteQuickMenu(item.id, 'data')"
                  />
                </div>
                <div v-if="dataList.length === 0" class="text-gray-400 text-12px">
                  {{ t('workplace.noQuickFunction') }}
                </div>
              </div>
            </div>
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
      <!-- 右侧边栏：待办任务列表 + 我的告警预警列表 -->
      <el-col :xl="8" :lg="8" :md="24" :sm="24" :xs="24" class="mb-8px">
        <!-- 待办任务列表 -->
        <el-card shadow="never" class="mb-8px">
          <template #header>
            <div class="h-3 flex justify-between">
              <span>{{ t('workplace.listTitle') }}</span>
              <el-link type="primary" :underline="false" size="small">{{ t('action.more') }}</el-link>
            </div>
          </template>
          <el-skeleton :loading="loading" animated>
            <div class="space-y-1 max-h-300 overflow-y-auto pr-2">
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
        <!-- 我的告警预警列表 -->
        <el-card shadow="never">
          <template #header>
            <div class="h-3 flex justify-between">
              <span>{{ t('workplace.myAlarmWarning') }}</span>
              <el-link type="primary" :underline="false" size="small" @click="handleRouteJump('/my-warning/index')">
                {{ t('action.more') }}
              </el-link>
            </div>
          </template>
          <el-skeleton :loading="loading" animated>
            <div class="space-y-1 max-h-300 overflow-y-auto pr-2">
              <div
                v-for="(item, index) in alarmTasks"
                :key="`alarm-task-${index}`"
                class="p-3 hover:bg-gray-50 rounded-md"
              >
                <div class="flex items-center">
                  <!-- 调整告警图标背景色，与待办任务头像配色和谐 -->
                  <el-avatar :size="30" class="mr-12px bg-primary/10">
                    <Icon :icon="`ep:alarm-clock`" :size="18" class="text-primary" />
                  </el-avatar>
                  <div class="flex-1 min-w-0">
                    <div class="text-13px flex items-center gap-2">
                      <el-tag :type="item.warningStatus === '0' ? 'info' : 'success'" size="mini">
                        {{ item.warningStatus === '0' ? '待派单' : '已派单' }}
                      </el-tag>
                      <span class="truncate">{{ item.triggerReason }}</span>
                    </div>
                    <div class="mt-2px text-11px text-gray-400">
                      触发时间：{{ formatTime(item.triggerTime, 'yyyy-MM-dd HH:mm') }}
                    </div>
                  </div>
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
import { WarningAlertListTableApi, type WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import { useRouter } from 'vue-router'
import { ArrowRight } from '@element-plus/icons-vue'
// 新增导入
import { getInfo } from '@/api/login'
import { HomeQuickMenuApi, type HomeQuickMenuVO } from '@/api/dataHub/homequickmenu'
import { ElMessage, ElTree } from 'element-plus'
import type { TreeNode } from 'element-plus/es/components/tree/src/tree.type'

defineOptions({ name: 'Home' })
const { t } = useI18n()
const userStore = useUserStore()
// const { setWatermark } = useWatermark()
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

// 新增：快捷入口自定义相关响应式数据
const menus = ref<any[]>([]) // 用户权限下的树形菜单数据
const showTreeDropdown = ref(false) // 控制树形下拉框显示
const searchMenuText = ref('') // 菜单搜索文本
const currentType = ref('') // 当前操作的类型（app/data）
const appList = ref<HomeQuickMenuVO[]>([]) // 常用系统（type=app）列表
const dataList = ref<HomeQuickMenuVO[]>([]) // 快捷入口（type=data）列表
const editMode = ref({ app: false, data: false }) // 编辑模式状态
// 马卡龙配色方案
const macaronColors = ref([
  '#FFC2D1', '#B5EAD7', '#C7CEEA',
  '#FFD6A5', '#FDFFB6', '#CAFFBF',
  '#9BF6FF', '#BDB2FF', '#FFADAD'
])
// 树形组件配置
const treeProps = ref({
  label: 'name',
  children: 'children',
  disabled: (data: any) => {
    // 修改：只有非叶子节点才禁用
    return data.children && data.children.length > 0
  }
})

// 图表数据
const pieOptionsData = reactive<EChartsOption>(pieOptions) as EChartsOption
const barOptionsData = reactive<EChartsOption>(barOptions) as EChartsOption
// 统计数据
let totalSate = reactive<WorkplaceTotal>({
  project: 0,
  access: 0,
  todo: 0,
  task: 0,
  alarm: 0
})
// 待办任务数据
let todoTasks = reactive<TodoTaskInfo[]>([])
// 告警预警数据
let alarmTasks = reactive<WarningAlertListTableVO[]>([])

// 新增：判断是否为叶子节点的方法
const isLeafNode = (data: any): boolean => {
  return !data.children || data.children.length === 0
}

// 新增：加载用户权限菜单数据
const loadMenus = async () => {
  try {
    const permissionInfo = await getInfo()
    menus.value = permissionInfo?.menus || []
  } catch (error) {
    ElMessage.error(t('workplace.loadMenuFailed'))
    console.error('加载菜单失败：', error)
  }
}

// 新增：加载快捷菜单初始数据（按type分类）
const loadQuickMenuData = async () => {
  try {
    const res = await HomeQuickMenuApi.getHomeQuickMenuPage({
      pageNo: 1,
      pageSize: 20, // 最多各10个，共20个
      userId: userStore.getUser.id
    })
    const list = res.list || []
    // 按type分类
    appList.value = list.filter(item => item.type === 'app')
    dataList.value = list.filter(item => item.type === 'data')
  } catch (error) {
    ElMessage.error(t('workplace.loadQuickMenuFailed'))
    console.error('加载快捷菜单失败：', error)
  }
}

// 修复：拼接完整路径（递归追溯至parentId=0的根节点，避免路径重复和顺序错乱）
const getFullPath = (node: any, allMenus: any[]): string => {
  // 步骤1：先将树形菜单扁平化为所有节点的集合（方便通过id快速查找父节点）
  const flattenMenus = (menus: any[]): any[] => {
    let result: any[] = []
    menus.forEach(menu => {
      result.push(menu) // 存入当前节点
      if (menu.children && menu.children.length > 0) {
        result = result.concat(flattenMenus(menu.children)) // 递归存入子节点
      }
    })
    return result
  }
  const flatMenus = flattenMenus(allMenus) // 所有层级节点的扁平数组

  // 步骤2：递归收集路径（先获取父节点完整路径，再拼接当前节点path，避免重复）
  const collectPaths = (currentNode: any): string[] => {
    const paths: string[] = []
    // 找到当前节点的父节点
    const parentNode = flatMenus.find(menu => menu.id === currentNode.parentId)
    // 递归获取父节点的完整路径（根节点为止）
    if (parentNode) {
      paths.push(...collectPaths(parentNode))
    }
    // 最后添加当前节点path（保证顺序：根→子）
    if (currentNode.path) {
      paths.push(currentNode.path)
    }
    return paths
  }

  // 步骤3：过滤空path，拼接为完整路由（处理根节点path带/的情况，避免重复斜杠）
  const paths = collectPaths(node).filter(Boolean)
  // 拼接时确保根节点路径正确（若首路径不带/，则添加/）
  const fullPath = paths.join('/').replace(/^([^/])/, '/$1').replace(/\/+/g, '/')
  return fullPath
}

// 新增：用于搜索展示的树形数据
const filteredMenus = computed(() => {
  if (!searchMenuText.value) return menus.value

  const keyword = searchMenuText.value.toLowerCase()
  const hitIds = new Set<number>()

  // 扁平化菜单
  const flatten = (list: any[], parent?: any) => {
    list.forEach(item => {
      if (item.name?.toLowerCase().includes(keyword)) {
        let cur = item
        // 命中节点 + 所有父节点
        while (cur) {
          hitIds.add(cur.id)
          cur = parentMap.get(cur.parentId)
        }
      }
      if (item.children) flatten(item.children, item)
    })
  }

  // parentId -> node 映射
  const parentMap = new Map<number, any>()
  const buildMap = (list: any[]) => {
    list.forEach(item => {
      parentMap.set(item.id, item)
      if (item.children) buildMap(item.children)
    })
  }

  buildMap(menus.value)
  flatten(menus.value)

  // 根据命中 id 重建树
  const rebuild = (list: any[]): any[] =>
    list
      .filter(item => hitIds.has(item.id))
      .map(item => ({
        ...item,
        children: item.children ? rebuild(item.children) : []
      }))

  return rebuild(menus.value)
})

// 新增：显示树形下拉框
const handleShowTreeDropdown = (type: 'app' | 'data') => {
  currentType.value = type
  showTreeDropdown.value = true
  searchMenuText.value = '' // 清空搜索框
}

// 新增：搜索菜单处理
const handleSearchMenu = () => {
  // 调用树组件的过滤方法
  if (menuTreeRef.value) {
    menuTreeRef.value.filter(searchMenuText.value)
  }
}

// 修改：树形节点搜索过滤方法
const filterMenuNode = (value: string, data: any) => {
  if (!value) return true
  return data.name && data.name.toLowerCase().includes(value.toLowerCase())
}

// 添加树组件引用
const menuTreeRef = ref<InstanceType<typeof ElTree>>()

// 新增：树形节点点击（选中菜单）
const handleTreeNodeClick = async (node: any, type: 'app' | 'data') => {
  // 修改：添加叶子节点校验
  if (!isLeafNode(node)) {
    // 非叶子节点不执行选择操作，只展开/收起
    return
  }

  // 校验数量限制
  const targetList = type === 'app' ? appList.value : dataList.value
  if (targetList.length >= 10) {
    ElMessage.warning(`${type === 'app' ? t('workplace.commonSystem') : t('workplace.quickFunction')}最多只能添加10个`)
    showTreeDropdown.value = false
    return
  }

  try {
    // 拼接完整路径
    const fullPath = getFullPath(node, menus.value)
    console.log("fullPath")
    console.log(fullPath)
    // 构建新增参数
    const params: HomeQuickMenuVO = {
      id: 0,
      name: node.name,
      path: fullPath,
      type: type,
      icon: node.icon || (type === 'app' ? 'fa-solid:apple-alt' : 'fa-solid:database'),
      userId: userStore.getUser.id,
      sortNum: targetList.length + 1, // 排序序号
      enableStatus: '启用',
      menuDesc: '',
      extCommon1: '',
      extCommon2: ''
    }
    // 调用新增接口
    await HomeQuickMenuApi.createHomeQuickMenu(params)
    ElMessage.success(t('action.addSuccess'))
    // 刷新数据并关闭下拉框
    await loadQuickMenuData()
    showTreeDropdown.value = false
    searchMenuText.value = '' // 清空搜索框
  } catch (error) {
    ElMessage.error(t('action.addFailed'))
    console.error('新增快捷菜单失败：', error)
  }
}

// 新增：切换编辑模式
const handleEditToggle = (type: 'app' | 'data') => {
  editMode.value[type] = !editMode.value[type]
  // 关闭另一个区域的编辑模式
  const otherType = type === 'app' ? 'data' : 'app'
  editMode.value[otherType] = false
}

// 新增：删除快捷菜单
const handleDeleteQuickMenu = async (id: number, type: 'app' | 'data') => {
  try {
    await HomeQuickMenuApi.deleteHomeQuickMenu(id)
    ElMessage.success(t('action.deleteSuccess'))
    // 刷新数据
    await loadQuickMenuData()
  } catch (error) {
    ElMessage.error(t('action.deleteFailed'))
    console.error('删除快捷菜单失败：', error)
  }
}

// 获取统计及列表数据
const getCount = async () => {
  const doneTaskData = await TaskApi.getTaskManagerPage({ pageNo: 1, pageSize: 100 })
  const todoTaskData = await TaskApi.getTaskTodoPage({ pageNo: 1, pageSize: 100 })
  const alarmDataOfUser = await WarningAlertListTableApi.getWarningAlertListTablePage({
    pageNo: 1,
    pageSize: 100,
    responsiblePerson: userStore.getUser.id
  })
  // 统计数据赋值
  totalSate.task = doneTaskData.total
  totalSate.todo = todoTaskData.total
  totalSate.alarm = alarmDataOfUser.total
  // 待办任务数据赋值
  todoTasks = todoTaskData.list.map(task => ({
    taskId: task.id,
    taskName: task.name,
    processName: task.processInstance.name,
    processInstanceId: task.processInstance.id,
    createTime: task.createTime,
    startUserName: task.processInstance.startUser.nickname
  }))
  // 告警预警数据赋值
  alarmTasks = alarmDataOfUser.list || []
}
// 获取饼图数据
const getUserAccessSource = async () => {
  const res = await TaskApi.taskStatistics()
  const data = res.pieData
  set(pieOptionsData, 'legend.data', data.map((v) => t(v.name)))
  pieOptionsData!.series![0].data = data.map((v) => ({ name: t(v.name), value: v.value }))
}
// 获取柱状图数据
const getWeeklyUserActivity = async () => {
  const data = await AlarmStatisticsApi.getLevelAlarmsOfResponsible({ responsiblePerson: userStore.getUser.id })
  set(barOptionsData, 'xAxis.data', data.map((v) => t(v.name)))
  set(barOptionsData, 'series', [{ name: t('analysis.alarmNum'), data: data.map((v) => v.value), type: 'bar' }])
}
// 路由跳转方法
const handleRouteJump = (path: string) => router.push(path)
// 任务跳转方法（带参数校验）
const handleTaskJump = (item: TodoTaskInfo) => {
  if (!item.processInstanceId) {
    ElMessage.error('流程实例编号不存在，无法查看详情')
    return
  }
  router.push({
    path: '/process-detail/index',
    query: {
      id: item.processInstanceId,
      taskId: item.taskId,
      activityId: ''
    }
  })
}
// 初始化加载所有数据
const getAllApi = async () => {
  await Promise.all([
    getCount(),
    loadMenus(), // 加载权限菜单
    loadQuickMenuData(), // 加载快捷菜单初始数据
    getUserAccessSource(),
    getWeeklyUserActivity()
  ])
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
/* 列表滚动样式（统一高度确保对齐） */
.max-h-300 {
  max-height: 300px;
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

/* 新增样式 */
/* 树形下拉框宽度 */
.w-400 { width: 400px; }
/* 菜单项横向排列，超出滚动 */
.overflow-x-auto { overflow-x: auto; }
.overflow-x-auto::-webkit-scrollbar { height: 4px; }
.overflow-x-auto::-webkit-scrollbar-thumb { background-color: #e5e7eb; border-radius: 2px; }
/* 菜单项 hover 效果 */
.group:hover .text-gray-800 { color: var(--el-color-primary); }
/* 修复卡片高度不变 */
.el-card .h-24 { height: 96px !important; }
/* 搜索框与树形菜单间距 */
.mb-2 { margin-bottom: 8px; }
/* 图标容器样式优化 */
.flex-col items-center { align-items: center; }
.cursor-pointer { cursor: pointer; }
.truncate { white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
.w-20 { width: 80px; }
.text-center { text-align: center; }

/* 下拉框容器样式 */
.quick-dropdown-container {
  width: 360px;
  max-height: 420px;
  display: flex;
  flex-direction: column;
  background: #ffffff;
  border-radius: 8px;
  overflow: hidden;
}

/* 树容器 - 确保滚动条正常工作 */
.tree-container {
  flex: 1;
  min-height: 0;
  overflow-y: auto;
}

.tree-container .el-tree {
  min-height: 100%;
}

/* 树节点样式 */
.tree-node {
  display: flex;
  align-items: center;
  font-size: 13px;
  line-height: 20px;
  padding: 2px 0;
}

/* 新增：叶子节点可点击样式 */
.tree-node.cursor-pointer {
  cursor: pointer;
}

.tree-node.cursor-pointer:hover {
  color: var(--el-color-primary);
}

/* 下拉框popper样式 */
.quick-dropdown-popper {
  z-index: 9999 !important; /* 确保高于其他元素 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 8px;
  overflow: hidden;
}

.quick-dropdown-popper .el-popper__arrow {
  display: none;
}

/* 确保树形组件可以滚动到最后 */
.tree-container::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.tree-container::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

.tree-container::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

.tree-container::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* 空状态提示 */
.el-tree__empty-block {
  padding: 20px 0;
  text-align: center;
  color: #999;
  font-size: 12px;
}
</style>
