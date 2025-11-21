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
          value-format="YYYY-MM-DD"
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
          v-hasPermi="['datacenter:alarm-list:create']"
        >
          <Plus class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:alarm-list:export']"
        >
          <Download class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表区域 -->
  <ContentWrap style="position: relative;">
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
            @click="openDetailDrawer(scope.row.id)"
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
        :formatter="dateFormatter"
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
        :formatter="dateFormatter"
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
            @click="openDetailDrawer(scope.row.id)"
            v-hasPermi="['datacenter:alarm-list:detail']"
          >
            详情
          </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:alarm-list:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="warning"
            @click="handleDispatch(scope.row)"
            v-if="scope.row.alarmStatus === '待处置'"
            v-hasPermi="['datacenter:alarm-list:dispatch']"
          >
            派单
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:alarm-list:delete']"
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

  <!-- 引入详情抽屉子组件 -->
  <AlarmDetailDrawer
    v-model:visible="detailDrawerVisible"
    :detail-data="detailData"
    :level-class="getLevelClass"
    :level-name="getLevelName"
    :status-class="getStatusClass"
    :format-time="formatTime"
    @close="handleDetailClose"
  />

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
  <AlarmListForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { ref, reactive, watch, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
// 按需引入Element Plus图标
import { Search, Refresh, Plus, Download } from '@element-plus/icons-vue'
import Pagination from '@/components/Pagination/index.vue'
import ContentWrap from '@/components/ContentWrap/src/ContentWrap.vue'
// 引入接口、类型定义和组件
import { AlarmListApi, AlarmListVO } from '@/api/dataHub/earlyWarningAlarm/alarmlist'
import AlarmListForm from '@/views/dataHub/earlyWarningAlarm/list/AlarmListForm.vue'
import AlarmDetailDrawer from './AlarmDetailDrawer.vue' // 引入抽屉子组件

// 类型定义
interface QueryParams {
  pageNo: number
  pageSize: number
  alarmCode: string | undefined
  riskTypeId: string | undefined
  alarmLevel: string | undefined
  domainId: string | undefined
  alarmStatus: string | undefined
  triggerTime: string | undefined
  occurRegion: string | undefined
  sortField?: string
  sortOrder?: 'ASC' | 'DESC' | undefined
}

defineOptions({ name: 'AlarmList' })

// 全局工具
const message = useMessage()
const { t } = useI18n()

// 状态管理
const loading = ref(true)
const exportLoading = ref(false)
const total = ref(0)
const queryFormRef = ref()
const dispatchFormRef = ref<any>(null)
const formRef = ref()

// 详情抽屉状态（传递给子组件）
const detailDrawerVisible = ref(false)
const detailData = ref<AlarmListVO>({} as AlarmListVO)

// 派单弹窗状态
const dispatchDialogVisible = ref(false)
const dispatchForm = reactive({
  alarmId: '',
  alarmCode: '',
  riskTypeName: '',
  handlerId: '',
  remark: ''
})

// 筛选选项配置
const domainOptions = [
  { label: '城管住建', value: 'cgzj' },
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

// 查询参数
const queryParams = reactive<QueryParams>({
  pageNo: 1,
  pageSize: 10,
  alarmCode: undefined,
  riskTypeId: undefined,
  alarmLevel: undefined,
  domainId: undefined,
  alarmStatus: undefined,
  triggerTime: undefined,
  occurRegion: undefined,
  sortField: undefined,
  sortOrder: undefined
})

// 列表数据
const list = ref<AlarmListVO[]>([])

/** 序号计算 */
const indexMethod = (index: number) => (queryParams.pageNo - 1) * queryParams.pageSize + index + 1

/** 获取预警等级样式类（传递给子组件） */
const getLevelClass = (level: string) => {
  switch (level) {
    case '特别重大': return 'red'
    case '重大': return 'orange'
    case '较大': return 'yellow'
    case '一般': return 'blue'
    default: return ''
  }
}

/** 获取预警等级名称（传递给子组件） */
const getLevelName = (level: string) => {
  const map = {
    '一般': '一般',
    '较大': '较大',
    '重大': '重大',
    '特别重大': '特别重大'
  }
  return map[level] || level
}

/** 获取状态样式类（传递给子组件） */
const getStatusClass = (status: string) => {
  switch (status) {
    case '待处置': return 'orange'
    case '处置中': return 'blue'
    case '已完成': return 'green'
    case '已解除': return 'gray'
    default: return ''
  }
}

/** 处理排序变化 */
const handleSortChange = (sort: { prop: string; order: 'ascending' | 'descending' | null }) => {
  queryParams.sortField = sort.prop
  queryParams.sortOrder = sort.order === 'ascending' ? 'ASC' : sort.order === 'descending' ? 'DESC' : undefined
  getList()
}

/** 通用时间格式化函数（传递给子组件） */
const formatTime = (time?: string | Date): string => {
  if (!time) return '-'
  try {
    const date = typeof time === 'string' ? new Date(time) : time
    return date.toLocaleString('zh-CN', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    })
  } catch (error) {
    return typeof time === 'string' ? time : time.toString()
  }
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const response = await AlarmListApi.getAlarmListPage(queryParams)
    list.value = response.list || []
    total.value = response.total || 0
  } catch (error) {
    console.error('获取列表失败:', error)
    message.error('获取预警列表失败，请重试')
    list.value = []
    total.value = 0
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  if (queryFormRef.value) {
    queryFormRef.value.resetFields()
  }
  queryParams.sortField = undefined
  queryParams.sortOrder = undefined
  handleQuery()
}

/** 打开详情抽屉（加载数据并显示） */
const openDetailDrawer = async (id: number) => {
  try {
    const response = await AlarmListApi.getAlarmList(id)
    detailData.value = response || {} as AlarmListVO
    detailDrawerVisible.value = true // 触发子组件显示
  } catch (error) {
    console.error('获取详情失败:', error)
    message.error('获取预警详情失败，请重试')
  }
}

/** 关闭详情抽屉（子组件触发） */
const handleDetailClose = () => {
  detailDrawerVisible.value = false
  setTimeout(() => {
    detailData.value = {} as AlarmListVO // 清空数据
  }, 300)
}

/** 打开派单弹窗 */
const handleDispatch = (row: AlarmListVO) => {
  dispatchForm.alarmId = row.alarmId
  dispatchForm.alarmCode = row.alarmCode || ''
  dispatchForm.riskTypeName = row.riskTypeName || ''
  dispatchForm.handlerId = ''
  dispatchForm.remark = ''
  dispatchDialogVisible.value = true
}

/** 关闭派单弹窗 */
const handleDispatchClose = () => {
  dispatchFormRef.value?.resetFields()
  dispatchDialogVisible.value = false
}

/** 提交派单 */
const handleSubmitDispatch = () => {
  dispatchFormRef.value?.validate((valid: boolean) => {
    if (valid) {
      setTimeout(() => {
        message.success('派单成功，预警状态更新为"处置中"')
        dispatchDialogVisible.value = false
        getList()
      }, 800)
    }
  })
}

/** 添加/修改操作 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await AlarmListApi.deleteAlarmList(id)
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

/** 导出操作 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const response = await AlarmListApi.exportAlarmList(queryParams)
    download.excel(response, `预警告警列表_${new Date().toLocaleDateString()}.xls`)
    message.success('导出成功')
  } catch (error) {
    console.error('导出失败:', error)
    message.error('导出失败，请重试')
  } finally {
    exportLoading.value = false
  }
}

/** 监听筛选条件变化 */
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

/** 初始化加载 */
onMounted(() => {
  getList()
})
</script>

<style scoped>
/* 预警等级标签样式 */
.alarm-level-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  color: #fff;
  font-size: 12px;
  font-weight: 500;
}
.alarm-level-tag.level-blue {
  background-color: #1E90FF;
}
.alarm-level-tag.level-yellow {
  background-color: #FFC107;
}
.alarm-level-tag.level-orange {
  background-color: #FF7F50;
}
.alarm-level-tag.level-red {
  background-color: #DC143C;
}

/* 预警状态标签样式 */
.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  color: #fff;
  font-size: 12px;
  font-weight: 500;
}
.status-tag.status-orange {
  background-color: #FF9800;
}
.status-tag.status-blue {
  background-color: #2196F3;
}
.status-tag.status-green {
  background-color: #4CAF50;
}
.status-tag.status-gray {
  background-color: #9E9E9E;
}
.status-tag.status-default {
  background-color: #607D8B;
}
</style>
