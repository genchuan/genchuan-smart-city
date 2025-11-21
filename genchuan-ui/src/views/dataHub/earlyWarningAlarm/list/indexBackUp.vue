<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="120px"
      size="small"
    >
      <!-- 预警编号 -->
      <el-form-item label="预警编号" prop="alarmCode">
        <el-input
          v-model="queryParams.alarmCode"
          placeholder="例：AL_cgjz_20240001"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>

      <!-- 风险类型 -->
      <el-form-item label="风险类型" prop="riskTypeId">
        <el-select
          v-model="queryParams.riskTypeId"
          placeholder="全部"
          clearable
          class="!w-200px"
        >
          <el-option label="全部" value="" />
          <el-option
            v-for="item in riskTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>

      <!-- 预警等级 -->
      <el-form-item label="预警等级" prop="alarmLevel">
        <el-select
          v-model="queryParams.alarmLevel"
          placeholder="全部"
          clearable
          class="!w-200px"
        >
          <el-option label="全部" value="" />
          <el-option
            v-for="item in alarmLevelOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :style="{ color: item.color }"
          />
        </el-select>
      </el-form-item>

      <!-- 所属分域 -->
      <el-form-item label="所属分域" prop="domainId">
        <el-select
          v-model="queryParams.domainId"
          placeholder="全部"
          clearable
          class="!w-200px"
        >
          <el-option label="全部" value="" />
          <el-option
            v-for="item in domainOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>

      <!-- 预警状态 -->
      <el-form-item label="预警状态" prop="alarmStatus">
        <el-select
          v-model="queryParams.alarmStatus"
          placeholder="全部"
          clearable
          class="!w-200px"
        >
          <el-option label="全部" value="" />
          <el-option
            v-for="item in alarmStatusOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>

      <!-- 触发时间范围 -->
      <el-form-item label="触发时间" prop="triggerTime">
        <el-date-picker
          v-model="queryParams.triggerTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="date"
          class="!w-240px"
        />
      </el-form-item>

      <!-- 发生区域 -->
      <el-form-item label="发生区域" prop="occurRegion">
        <el-input
          v-model="queryParams.occurRegion"
          placeholder="例：天河区天河路"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>

      <!-- 操作按钮组 -->
      <el-form-item>
        <el-button @click="handleQuery"><Search class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Refresh class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['alarm:list:create']"
        >
          <Plus class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['alarm:list:export']"
        >
          <Download class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表区域 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      @sort-change="handleSortChange"
      border
    >
      <!-- 序号列 -->
      <el-table-column
        label="序号"
        align="center"
        type="index"
        :index="indexMethod"
        width="60"
      />

      <!-- 预警ID -->
      <el-table-column
        label="预警ID"
        align="center"
        prop="alarmId"
        width="180"
        sortable
      />

      <!-- 预警编号（可点击查看详情） -->
      <el-table-column
        label="预警编号"
        align="center"
        prop="alarmCode"
        width="180"
        sortable
      >
        <template #default="scope">
          <el-link
            type="primary"
            :underline="false"
            @click="openDetailDrawer(scope.row.alarmId)"
          >
            {{ scope.row.alarmCode }}
          </el-link>
        </template>
      </el-table-column>

      <!-- 风险类型 -->
      <el-table-column
        label="风险类型"
        align="center"
        prop="riskTypeName"
        width="140"
      />

      <!-- 预警等级（颜色标识） -->
      <el-table-column
        label="预警等级"
        align="center"
        prop="alarmLevel"
        width="120"
        sortable
      >
        <template #default="scope">
          <span
            class="alarm-level-tag"
            :class="`level-${getLevelClass(scope.row.alarmLevel)}`"
          >
            {{ getLevelName(scope.row.alarmLevel) }}
          </span>
        </template>
      </el-table-column>

      <!-- 所属分域 -->
      <el-table-column
        label="所属分域"
        align="center"
        prop="domainName"
        width="120"
      />

      <!-- 发生区域 -->
      <el-table-column
        label="发生区域"
        align="center"
        prop="occurRegion"
        width="150"
      />

      <!-- GPS坐标 -->
      <el-table-column
        label="GPS坐标"
        align="center"
        prop="gpsCoordinate"
        width="150"
      />

      <!-- 触发时间 -->
      <el-table-column
        label="触发时间"
        align="center"
        prop="triggerTime"
        width="180"
        sortable

      />

      <!-- 预警状态（样式标识） -->
      <el-table-column
        label="预警状态"
        align="center"
        prop="alarmStatus"
        width="120"
        sortable
      >
        <template #default="scope">
          <span
            class="status-tag"
            :class="`status-${getStatusClass(scope.row.alarmStatus)}`"
          >
            {{ scope.row.alarmStatus }}
          </span>
        </template>
      </el-table-column>

      <!-- 触发原因 -->
      <el-table-column
        label="触发原因"
        align="center"
        prop="triggerReason"
        min-width="180"
      />

      <!-- 处置责任人 -->
      <el-table-column
        label="处置责任人"
        align="center"
        prop="handlerName"
        width="120"
      />

      <!-- 最后更新时间 -->
      <el-table-column
        label="最后更新时间"
        align="center"
        prop="lastUpdateTime"
        width="180"
        sortable

      />

      <!-- 操作列 -->
      <el-table-column
        label="操作"
        align="center"
        min-width="200"
        fixed="right"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openDetailDrawer(scope.row.alarmId)"
            v-hasPermi="['alarm:list:detail']"
          >
            详情
          </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.alarmId)"
            v-hasPermi="['alarm:list:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="warning"
            @click="handleDispatch(scope.row)"
            v-if="scope.row.alarmStatus === '待处置'"
            v-hasPermi="['alarm:list:dispatch']"
          >
            派单
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.alarmId)"
            v-hasPermi="['alarm:list:delete']"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      class="mt-4"
    />
  </ContentWrap>

  <!-- 详情抽屉 -->
  <el-drawer
    v-model="detailDrawerVisible"
    title="预警详情"
    size="50%"
    :before-close="handleDetailClose"
  >
    <el-descriptions  border class="mt-4" size="small">
      <el-descriptions-item label="预警ID">{{ detailData.alarmId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="预警编号">{{ detailData.alarmCode || '-' }}</el-descriptions-item>
      <el-descriptions-item label="风险类型ID">{{ detailData.riskTypeId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="风险类型名称">{{ detailData.riskTypeName || '-' }}</el-descriptions-item>
      <el-descriptions-item label="预警等级">
        <span
          class="alarm-level-tag"
          :class="`level-${getLevelClass(detailData.alarmLevel)}`"
          v-if="detailData.alarmLevel"
        >
          {{ getLevelName(detailData.alarmLevel) }}
        </span>
        <span v-else>-</span>
      </el-descriptions-item>
      <el-descriptions-item label="所属分域ID">{{ detailData.domainId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="所属分域名称">{{ detailData.domainName || '-' }}</el-descriptions-item>
      <el-descriptions-item label="发生区域">{{ detailData.occurRegion || '-' }}</el-descriptions-item>
      <el-descriptions-item label="GPS坐标">{{ detailData.gpsCoordinate || '-' }}</el-descriptions-item>
      <el-descriptions-item label="触发时间">{{ formatTime(detailData.triggerTime) }}</el-descriptions-item>
      <el-descriptions-item label="预警状态">
        <span
          class="status-tag"
          :class="`status-${getStatusClass(detailData.alarmStatus)}`"
          v-if="detailData.alarmStatus"
        >
          {{ detailData.alarmStatus }}
        </span>
        <span v-else>-</span>
      </el-descriptions-item>
      <el-descriptions-item label="触发原因">{{ detailData.triggerReason || '-' }}</el-descriptions-item>
      <el-descriptions-item label="关联指标ID">{{ detailData.indicatorId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="处置责任人ID">{{ detailData.handlerId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="处置责任人姓名">{{ detailData.handlerName || '未分配' }}</el-descriptions-item>
      <el-descriptions-item label="最后更新时间">{{ formatTime(detailData.lastUpdateTime) }}</el-descriptions-item>
    </el-descriptions>
  </el-drawer>

  <!-- 派单弹窗 -->
  <el-dialog
    v-model="dispatchDialogVisible"
    title="预警派单"
    width="400px"
    :before-close="handleDispatchClose"
  >
    <el-form
      :model="dispatchForm"
      ref="dispatchFormRef"
      label-width="100px"
      size="small"
      class="mt-2"
    >
      <el-form-item label="预警编号" prop="alarmCode">
        <el-input v-model="dispatchForm.alarmCode" disabled />
      </el-form-item>
      <el-form-item label="风险类型" prop="riskTypeName">
        <el-input v-model="dispatchForm.riskTypeName" disabled />
      </el-form-item>
      <el-form-item label="处置责任人" prop="handlerId" required>
        <el-select
          v-model="dispatchForm.handlerId"
          placeholder="请选择责任人"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="item in handlerOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="派单备注" prop="remark">
        <el-input
          v-model="dispatchForm.remark"
          type="textarea"
          placeholder="请输入派单说明"
          class="!w-240px"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dispatchDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmitDispatch">确认派单</el-button>
    </template>
  </el-dialog>

  <!-- 表单弹窗：添加/修改 -->
  <!-- 表单弹窗：添加/修改（核心修改） -->
  <ListForm
    ref="formRef"
    v-model="formVisible"
    :operation-type="formOperationType"
    :form-data="formData"
    :risk-type-options="riskTypeOptions"
    :domain-options="domainOptions"
    :alarm-level-options="alarmLevelOptions"
    @success="handleFormSuccess"
    @cancel="handleFormCancel"
  />
</template>

<script setup lang="ts">
import { ref, reactive, watch, onMounted } from 'vue'
import download from '@/utils/download'
// 按需引入Element Plus图标
import { Search, Refresh, Plus, Download } from '@element-plus/icons-vue'
import ListForm from '@/views/dataHub/earlyWarningAlarm/list/AlarmListForm.vue'
import Pagination from '@/components/Pagination/index.vue'
import ContentWrap from '@/components/ContentWrap/src/ContentWrap.vue'


// 类型定义（对齐数据库表结构）
interface AlarmVO {
  alarmId: string // 预警ID，UUID
  alarmCode: string // 预警编号
  riskTypeId: string // 风险类型ID
  riskTypeName: string // 风险类型名称
  alarmLevel: '一般' | '较大' | '重大' | '特别重大' // 预警等级
  domainId: string // 所属分域ID
  domainName: string // 所属分域名称
  occurRegion: string // 发生区域
  gpsCoordinate?: string // GPS坐标
  triggerTime: string // 触发时间
  alarmStatus: '待处置' | '处置中' | '已完成' | '已解除' // 预警状态
  triggerReason: string // 触发原因
  indicatorId?: string // 关联指标ID
  handlerId?: string // 处置责任人ID
  handlerName?: string // 处置责任人姓名
  lastUpdateTime: string // 最后更新时间
}

interface QueryParams {
  pageNo: number
  pageSize: number
  alarmCode: string | undefined
  riskTypeId: string | undefined
  alarmLevel: string | undefined
  domainId: string | undefined
  alarmStatus: string | undefined
  triggerTime: [string, string] | []
  occurRegion: string | undefined
  sortField?: string
  sortOrder?: 'ASC' | 'DESC' | undefined
}

// 新增：表单相关类型定义（与ListForm对齐）
type FormOperationType = 'create' | 'update'

defineOptions({ name: 'AlarmList' })

// 全局工具
const message = useMessage()
const { t } = useI18n()

// 状态管理
const loading = ref(true)
const exportLoading = ref(false)
const total = ref(0)
const queryFormRef = ref()
const formRef = ref<any>(null)
const dispatchFormRef = ref<any>(null)

// 详情抽屉状态（不变）
const detailDrawerVisible = ref(false)
const detailData = ref<AlarmVO>({} as AlarmVO)

// 派单弹窗状态（不变）
const dispatchDialogVisible = ref(false)
const dispatchForm = reactive({
  alarmId: '',
  alarmCode: '',
  riskTypeName: '',
  handlerId: '',
  remark: ''
})

// 新增：表单相关状态
const formVisible = ref(false) // 控制表单显示/隐藏
const formOperationType = ref<FormOperationType>('create') // 操作类型
const formData = ref<AlarmVO>({} as AlarmVO) // 待编辑的表单数据

// 筛选选项配置（不变）
const domainOptions = [
  { label: '城管住建', value: 'cgjz' },
  { label: '水利水务', value: 'slsw' },
  { label: '市政公用', value: 'szgy' },
  { label: '市容环卫', value: 'srhw' },
  { label: '园林绿化', value: 'ylhh' }
]

const riskTypeOptions = [
  { label: '燃气管道泄漏', value: 'gas_leak' },
  { label: '供水管网破裂', value: 'water_pipe_break' },
  { label: '道路积水超标', value: 'road_ponding' },
  { label: '垃圾堆积超标', value: 'garbage_pile' },
  { label: '绿化枯萎异常', value: 'green_wither' }
]

const alarmLevelOptions = [
  { label: '一般（蓝）', value: '一般', color: '#1E90FF' },
  { label: '较大（黄）', value: '较大', color: '#FFC107' },
  { label: '重大（橙）', value: '重大', color: '#FF7F50' },
  { label: '特别重大（红）', value: '特别重大', color: '#DC143C' }
]

const alarmStatusOptions = [
  { label: '待处置', value: '待处置' },
  { label: '处置中', value: '处置中' },
  { label: '已完成', value: '已完成' },
  { label: '已解除', value: '已解除' }
]

const handlerOptions = [
  { label: '张三（管道维修）', value: 'user1' },
  { label: '李四（水务抢修）', value: 'user2' },
  { label: '王五（环卫处置）', value: 'user3' },
  { label: '赵六（绿化维护）', value: 'user4' }
]

// 查询参数（不变）
const queryParams = reactive<QueryParams>({
  pageNo: 1,
  pageSize: 10,
  alarmCode: undefined,
  riskTypeId: undefined,
  alarmLevel: undefined,
  domainId: undefined,
  alarmStatus: undefined,
  triggerTime: [],
  occurRegion: undefined,
  sortField: undefined,
  sortOrder: undefined
})

// 列表数据（模拟，不变）
const list = ref<AlarmVO[]>([])

// 生成UUID（模拟预警ID，不变）
const generateUUID = () => {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    const r = Math.random() * 16 | 0
    const v = c === 'x' ? r : (r & 0x3 | 0x8)
    return v.toString(16)
  })
}

// 生成模拟数据（不变）
const generateMockData = (): AlarmVO[] => {
  const baseDate = new Date()
  const getDateStr = (days: number, hours: number = 0) => {
    const date = new Date(baseDate)
    date.setDate(baseDate.getDate() - days)
    date.setHours(hours, Math.floor(Math.random() * 60), Math.floor(Math.random() * 60))
    return date.toISOString().replace('T', ' ').slice(0, 19)
  }

  const levels = ['一般', '较大', '重大', '特别重大'] as const
  const statuses = ['待处置', '处置中', '已完成', '已解除'] as const
  const regions = ['天河区天河路', '海珠区江南大道', '越秀区北京路', '白云区机场路', '番禺区市桥街']
  const reasons = [
    '燃气管道压力值低于阈值0.2MPa',
    '供水管网压力突降30%',
    '道路积水深度超过15cm',
    '垃圾堆积高度超过1.2m',
    '绿化区域枯萎面积超过20%'
  ]

  return Array.from({ length: 36 }, (_, i) => {
    const domain = domainOptions[Math.floor(Math.random() * domainOptions.length)]
    const riskType = riskTypeOptions[Math.floor(Math.random() * riskTypeOptions.length)]
    const level = levels[Math.floor(Math.random() * levels.length)]
    const status = statuses[Math.floor(Math.random() * statuses.length)]
    const daysAgo = Math.floor(Math.random() * 15)
    const handler = Math.random() > 0.4 ? handlerOptions[Math.floor(Math.random() * handlerOptions.length)] : null
    const year = new Date().getFullYear()
    const serial = (i + 1).toString().padStart(8, '0')

    return {
      alarmId: generateUUID(),
      alarmCode: `AL_${domain.value}_${year}${serial}`,
      riskTypeId: riskType.value,
      riskTypeName: riskType.label,
      alarmLevel: level,
      domainId: domain.value,
      domainName: domain.label,
      occurRegion: `${regions[Math.floor(Math.random() * regions.length)]}${Math.floor(Math.random() * 100)}号`,
      gpsCoordinate: `${113.2 + Math.random() * 0.5},${23.0 + Math.random() * 0.5}`,
      triggerTime: getDateStr(daysAgo, 8 + Math.floor(Math.random() * 10)),
      alarmStatus: status,
      triggerReason: reasons[Math.floor(Math.random() * reasons.length)],
      indicatorId: `indicator_${Math.floor(Math.random() * 100)}`,
      handlerId: handler?.value,
      handlerName: handler?.label.split('（')[0],
      lastUpdateTime: getDateStr(daysAgo, 10 + Math.floor(Math.random() * 8))
    }
  })
}

// 模拟数据源（不变）
const mockData = generateMockData()

/** 序号计算（不变） */
const indexMethod = (index: number) => (queryParams.pageNo - 1) * queryParams.pageSize + index + 1

/** 获取预警等级样式类（不变） */
const getLevelClass = (level: string) => {
  switch (level) {
    case '特别重大': return 'red'
    case '重大': return 'orange'
    case '较大': return 'yellow'
    case '一般': return 'blue'
    default: return ''
  }
}

/** 获取预警等级名称（不变） */
const getLevelName = (level: string) => {
  const map = {
    '一般': '一般',
    '较大': '较大',
    '重大': '重大',
    '特别重大': '特别重大'
  }
  return map[level] || level
}

/** 获取状态样式类（不变） */
const getStatusClass = (status: string) => {
  switch (status) {
    case '待处置': return 'orange'
    case '处置中': return 'blue'
    case '已完成': return 'green'
    case '已解除': return 'gray'
    default: return ''
  }
}

/** 处理排序变化（不变） */
const handleSortChange = (sort: { prop: string; order: 'ascending' | 'descending' | null }) => {
  queryParams.sortField = sort.prop
  queryParams.sortOrder = sort.order === 'ascending' ? 'ASC' : sort.order === 'descending' ? 'DESC' : undefined
  getList()
}

/** 通用时间格式化函数（不变） */
const formatTime = (timeStr?: string): string => {
  if (!timeStr) return '-'
  try {
    const date = new Date(timeStr)
    return date.toLocaleString('zh-CN', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    })
  } catch (error) {
    return timeStr
  }
}

/** 查询列表（模拟，不变） */
const getList = async () => {
  loading.value = true
  try {
    // 1. 筛选逻辑
    let filteredData = [...mockData]

    // 预警编号筛选（修复注释）
    if (queryParams.alarmCode) {
      filteredData = filteredData.filter(item => item.alarmCode.includes(queryParams.alarmCode))
    }

    // 风险类型筛选
    if (queryParams.riskTypeId) {
      filteredData = filteredData.filter(item => item.riskTypeId === queryParams.riskTypeId)
    }

    // 预警等级筛选
    if (queryParams.alarmLevel) {
      filteredData = filteredData.filter(item => item.alarmLevel === queryParams.alarmLevel)
    }

    // 所属分域筛选
    if (queryParams.domainId) {
      filteredData = filteredData.filter(item => item.domainId === queryParams.domainId)
    }

    // 预警状态筛选
    if (queryParams.alarmStatus) {
      filteredData = filteredData.filter(item => item.alarmStatus === queryParams.alarmStatus)
    }

    // 触发时间筛选
    if (queryParams.triggerTime) {
      //const [start, end] = queryParams.triggerTime
      //filteredData = filteredData.filter(item => item.triggerTime >= start && item.triggerTime <= end)
      filteredData = filteredData.filter(item => item.triggerTime.includes(queryParams.triggerTime))
    }

    // 发生区域筛选（修复注释）
    if (queryParams.occurRegion) {
      filteredData = filteredData.filter(item => item.occurRegion.includes(queryParams.occurRegion))
    }

    // 2. 排序逻辑
    if (queryParams.sortField) {
      filteredData.sort((a, b) => {
        const valA = a[queryParams.sortField as keyof AlarmVO]
        const valB = b[queryParams.sortField as keyof AlarmVO]

        // 时间类型排序
        if (typeof valA === 'string' && typeof valB === 'string' && !isNaN(Date.parse(valA))) {
          return queryParams.sortOrder === 'ASC'
            ? new Date(valA).getTime() - new Date(valB).getTime()
            : new Date(valB).getTime() - new Date(valA).getTime()
        }

        // 字符串类型排序
        if (typeof valA === 'string' && typeof valB === 'string') {
          return queryParams.sortOrder === 'ASC' ? valA.localeCompare(valB) : valB.localeCompare(valA)
        }

        return 0
      })
    }

    // 3. 分页处理
    total.value = filteredData.length
    const startIndex = (queryParams.pageNo - 1) * queryParams.pageSize
    const endIndex = startIndex + queryParams.pageSize
    list.value = filteredData.slice(startIndex, endIndex)

  } catch (error) {
    console.error('获取列表失败:', error)
    message.error('获取预警列表失败，请重试')
    list.value = []
    total.value = 0
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作（不变） */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作（不变） */
const resetQuery = () => {
  if (queryFormRef.value) {
    queryFormRef.value.resetFields()
  }
  queryParams.sortField = undefined
  queryParams.sortOrder = undefined
  handleQuery()
}

/** 打开详情抽屉（不变） */
const openDetailDrawer = (alarmId: string) => {
  const data = mockData.find(item => item.alarmId === alarmId)
  if (data) detailData.value = { ...data }
  detailDrawerVisible.value = true
}

/** 关闭详情抽屉（不变） */
const handleDetailClose = () => {
  detailDrawerVisible.value = false
  setTimeout(() => {
    detailData.value = {} as AlarmVO
  }, 300)
}

/** 打开派单弹窗（不变） */
const handleDispatch = (row: AlarmVO) => {
  dispatchForm.alarmId = row.alarmId
  dispatchForm.alarmCode = row.alarmCode
  dispatchForm.riskTypeName = row.riskTypeName
  dispatchForm.handlerId = ''
  dispatchForm.remark = ''
  dispatchDialogVisible.value = true
}

/** 关闭派单弹窗（不变） */
const handleDispatchClose = () => {
  dispatchFormRef.value?.resetFields()
  dispatchDialogVisible.value = false
}

/** 提交派单（不变） */
const handleSubmitDispatch = () => {
  dispatchFormRef.value?.validate((valid: boolean) => {
    if (valid) {
      // 模拟派单接口延迟
      setTimeout(() => {
        // 更新模拟数据中的处置责任人
        const index = mockData.findIndex(item => item.alarmId === dispatchForm.alarmId)
        if (index !== -1) {
          const handler = handlerOptions.find(item => item.value === dispatchForm.handlerId)
          mockData[index].handlerId = dispatchForm.handlerId
          mockData[index].handlerName = handler?.label.split('（')[0]
          mockData[index].alarmStatus = '处置中'
          mockData[index].lastUpdateTime = new Date().toISOString().replace('T', ' ').slice(0, 19)
        }
        message.success('派单成功，预警状态更新为"处置中"')
        dispatchDialogVisible.value = false
        getList()
      }, 800)
    }
  })
}

/** 新增/修改：打开表单（核心修改） */
const openForm = (type: FormOperationType, id?: string) => {
  formOperationType.value = type // 设置操作类型
  if (type === 'update' && id) {
    // 编辑场景：查询待编辑数据并赋值
    const editData = mockData.find(item => item.alarmId === id)
    formData.value = editData ? { ...editData } : {} as AlarmVO
  } else {
    // 新增场景：清空表单数据
    formData.value = {
      alarmId: generateUUID(), // 预生成ID（或由后端生成）
      alarmStatus: '待处置', // 新增默认状态
      triggerTime: new Date().toISOString().replace('T', ' ').slice(0, 19), // 默认为当前时间
      lastUpdateTime: new Date().toISOString().replace('T', ' ').slice(0, 19)
    } as AlarmVO
  }
  formVisible.value = true // 显示表单
}

/** 表单提交成功：刷新列表（新增） */
const handleFormSuccess = (formResult: AlarmVO) => {
  formVisible.value = false // 关闭表单
  if (formOperationType.value === 'create') {
    // 新增：添加到模拟数据
    mockData.unshift(formResult)
    message.success('新增预警成功')
  } else {
    // 编辑：更新模拟数据
    const index = mockData.findIndex(item => item.alarmId === formResult.alarmId)
    if (index !== -1) {
      mockData[index] = { ...formResult, lastUpdateTime: new Date().toISOString().replace('T', ' ').slice(0, 19) }
      message.success('编辑预警成功')
    }
  }
  getList() // 刷新列表
}

/** 表单取消：关闭表单（新增） */
const handleFormCancel = () => {
  formVisible.value = false
  // 可选：清空表单数据
  formData.value = {} as AlarmVO
}

/** 删除操作（不变） */
const handleDelete = async (alarmId: string) => {
  try {
    await message.delConfirm()
    // 模拟删除逻辑
    const index = mockData.findIndex(item => item.alarmId === alarmId)
    if (index !== -1) mockData.splice(index, 1)
    message.success(t('common.delSuccess'))
    getList()
  } catch {}
}

/** 导出操作（不变） */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    // 模拟导出文件流
    const csvContent = `预警ID,预警编号,风险类型,预警等级,所属分域,发生区域,触发时间,预警状态\n${
      list.value.map(item => `${item.alarmId},${item.alarmCode},${item.riskTypeName},${item.alarmLevel},${item.domainName},${item.occurRegion},${item.triggerTime},${item.alarmStatus}`).join('\n')
    }`
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' })
    download.excel(blob, `预警告警列表_${new Date().toLocaleDateString()}.xls`)
    message.success('导出成功')
  } catch {
    message.error('导出失败，请重试')
  } finally {
    exportLoading.value = false
  }
}

/** 监听筛选条件变化，实时刷新（不变） */
watch(
  () => [
    queryParams.alarmCode,
    queryParams.riskTypeId,
    queryParams.alarmLevel,
    queryParams.domainId,
    queryParams.alarmStatus,
    queryParams.triggerTime,
    queryParams.occurRegion
  ],
  () => {
    queryParams.pageNo = 1
    getList()
  },
  { deep: true }
)

/** 初始化加载（不变） */
onMounted(() => {
  getList()
})
</script>


<style scoped lang="scss">
// 预警等级标签样式
.alarm-level-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  color: #fff;
  font-size: 12px;

  &.level-red {
    background-color: #DC143C;
  }
  &.level-orange {
    background-color: #FF7F50;
  }
  &.level-yellow {
    background-color: #FFC107;
    color: #333;
  }
  &.level-blue {
    background-color: #1E90FF;
  }
}

// 状态标签样式
.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  border: 1px solid transparent;

  &.status-orange {
    background-color: #fff7e6;
    color: #ff7d00;
    border-color: #ffd591;
  }
  &.status-blue {
    background-color: #e6f7ff;
    color: #1890ff;
    border-color: #91d5ff;
  }
  &.status-green {
    background-color: #f0fff4;
    color: #52c41a;
    border-color: #b7eb8f;
  }
  &.status-gray {
    background-color: #f5f5f5;
    color: #8c8c8c;
    border-color: #d9d9d9;
  }
}
</style>
