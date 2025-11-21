<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="告警编号" prop="alertCode">
        <el-input
          v-model="queryParams.alertCode"
          placeholder="请输入告警编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联对象名称" prop="relatedObjectName">
        <el-input
          v-model="queryParams.relatedObjectName"
          placeholder="请输入关联对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警领域" prop="warningField">
        <el-select
          v-model="queryParams.warningField"
          placeholder="请选择预警领域"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="item in warningFieldOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="预警类型" prop="warningType">
        <el-select
          v-model="queryParams.warningType"
          placeholder="请选择预警类型"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="item in warningTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="预警等级" prop="warningLevel">
        <el-select
          v-model="queryParams.warningLevel"
          placeholder="请选择预警等级"
          clearable
          class="!w-240px"
        >
          <el-option label="紧急" value="emergency" />
          <el-option label="重要" value="important" />
          <el-option label="一般" value="general" />
        </el-select>
      </el-form-item>
      <el-form-item label="预警状态" prop="warningStatus">
        <el-select
          v-model="queryParams.warningStatus"
          placeholder="请选择预警状态"
          clearable
          class="!w-240px"
        >
          <el-option label="待派单" value="pending_dispatch" />
          <el-option label="待接单" value="pending_receive" />
          <el-option label="处置中" value="handling" />
          <el-option label="待审核" value="pending_review" />
          <el-option label="已结案" value="completed" />
          <el-option label="已驳回" value="rejected" />
        </el-select>
      </el-form-item>
      <el-form-item label="派发部门" prop="dispatchDepartment">
        <el-select
          v-model="queryParams.dispatchDepartment"
          placeholder="请选择派发部门"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dept in departmentOptions"
            :key="dept.id"
            :label="dept.name"
            :value="dept.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="handleImport"
          v-hasPermi="['datacenter:warning-alert-list-table:import']"
        >
          <Icon icon="ep:upload" class="mr-5px" /> 导入
        </el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:warning-alert-list-table:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="openExportOptions"
          :loading="exportLoading"
          v-hasPermi="['datacenter:warning-alert-list-table:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 - 绑定行点击事件 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      @sort-change="handleSortChange"
      @row-click="(row) => handleOpenDetail(row.id)"
      :row-class-name="tableRowClassName"
    >
      <el-table-column label="预警ID" align="center" prop="id" />
      <el-table-column
        label="告警编号"
        align="center"
      >
        <template #default="scope">
          <span
            :class="isOverdue(scope.row) ? 'text-red-500' : ''"
            class="text-primary cursor-pointer"
          >
            {{ scope.row.alertCode }}
            <template v-if="isOverdue(scope.row)">
              <span class="ml-2 text-red-500">超时{{ getOverdueHours(scope.row) }}小时</span>
            </template>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="关联对象类型" align="center" prop="relatedObjectType" />
      <el-table-column label="关联对象名称" align="center" prop="relatedObjectName" />
      <el-table-column label="预警领域" align="center" prop="warningField" />
      <el-table-column label="预警类型" align="center" prop="warningType" />

      <!-- 预警等级列 -->
      <el-table-column
        label="预警等级"
        align="center"
        prop="warningLevel"
        sortable="custom"
      >
        <template #default="scope">
          <el-tag
            :type="scope.row.warningLevel === 'emergency' ? 'danger' :
                   scope.row.warningLevel === 'important' ? 'warning' : 'info'"
          >
            {{ scope.row.warningLevel === 'emergency' ? '紧急' :
            scope.row.warningLevel === 'important' ? '重要' : '一般' }}
          </el-tag>
        </template>
      </el-table-column>

      <!-- 预警状态列 -->
      <el-table-column
        label="预警状态"
        align="center"
        prop="warningStatus"
      >
        <template #default="scope">
          <el-tag
            :type="scope.row.warningStatus === 'pending_dispatch' ? 'info' :
                   scope.row.warningStatus === 'pending_receive' ? 'primary' :
                   scope.row.warningStatus === 'handling' ? 'warning' :
                   scope.row.warningStatus === 'pending_review' ? 'purple' :
                   scope.row.warningStatus === 'completed' ? 'success' : 'danger'"
          >
            {{ scope.row.warningStatus === 'pending_dispatch' ? '待派单' :
            scope.row.warningStatus === 'pending_receive' ? '待接单' :
              scope.row.warningStatus === 'handling' ? '处置中' :
                scope.row.warningStatus === 'pending_review' ? '待审核' :
                  scope.row.warningStatus === 'completed' ? '已结案' : '已驳回' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="触发原因" align="center" prop="triggerReason" />
      <el-table-column label="派发部门" align="center" prop="dispatchDepartment" />
      <el-table-column label="责任人" align="center" prop="responsiblePerson" />
      <el-table-column
        label="触发时间"
        align="center"
        prop="triggerTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
      />
      <el-table-column
        label="要求完成时间"
        align="center"
        prop="requiredCompleteTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
      />
      <el-table-column label="处置附件" align="center" prop="disposalAttachmentPath">
        <template #default="scope">
          <template v-if="['completed', 'rejected'].includes(scope.row.warningStatus) && scope.row.disposalAttachmentPath">
            <!--            <el-button-->
            <!--              link-->
            <!--              type="primary"-->
            <!--              @click.stop="handleDownloadAttachment(scope.row.disposalAttachmentPath)"-->
            <!--            >-->
            <el-button
              link
              type="primary"
              @click.stop
            >
              下载附件
            </el-button>
          </template>
          <template v-else>
            -
          </template>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="scope">
          <!-- 待派单状态操作 -->
          <template v-if="scope.row.warningStatus === 'pending_dispatch'">
            <el-button
              link
              type="primary"
              @click.stop="handleDispatch(scope.row)"
              v-hasPermi="['datacenter:warning-alert-list-table:dispatch']"
            >
              派单
            </el-button>
            <el-button
              link
              type="primary"
              @click.stop="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:warning-alert-list-table:update']"
            >
              修改
            </el-button>
            <el-button
              link
              type="danger"
              @click.stop="handleDelete(scope.row)"
              v-hasPermi="['datacenter:warning-alert-list-table:delete']"
            >
              删除
            </el-button>
          </template>

          <!-- 待接单状态操作 -->
          <template v-if="scope.row.warningStatus === 'pending_receive'">
            <el-button
              link
              type="success"
              @click.stop="handleReceiveOrder(scope.row)"
              v-hasPermi="['datacenter:warning-alert-list-table:receive']"
            >
              接单
            </el-button>
          </template>

          <!-- 处置中状态操作 -->
          <template v-if="scope.row.warningStatus === 'handling'">
            <el-button
              link
              type="primary"
              @click.stop="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:warning-alert-list-table:update']"
            >
              补充进展
            </el-button>
            <el-button
              link
              type="success"
              @click.stop="handleSubmitReview(scope.row)"
              v-hasPermi="['datacenter:warning-alert-list-table:submitReview']"
            >
              提交审核
            </el-button>
          </template>

          <!-- 待审核状态操作 -->
          <template v-if="scope.row.warningStatus === 'pending_review'">
            <el-button
              link
              type="primary"
              @click.stop="handleReview(scope.row)"
              v-hasPermi="['datacenter:warning-alert-list-table:review']"
            >
              审核
            </el-button>
          </template>

          <!-- 通用操作：查看详情 -->
          <el-button
            link
            type="primary"
            @click.stop="handleOpenDetail(scope.row.id)"
            v-hasPermi="['datacenter:warning-alert-list-table:detail']"
          >
            详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 表单弹窗和其他组件 -->
  <WarningAlertListTableForm ref="formRef" @success="getList" />
  <WarningDetailDrawer
    ref="detailDrawerRef"
    @close="handleDrawerClose"
    :is-fullscreen="isDrawerFullscreen"
    @update:is-fullscreen="isDrawerFullscreen = $event"
  />
  <DispatchOrderForm ref="dispatchFormRef" @success="getList" />
  <ReceiveOrderForm ref="receiveFormRef" @success="getList" />
  <SubmitReviewForm ref="submitReviewFormRef" @success="getList" />
  <!--  <DeleteReasonForm ref="deleteReasonFormRef" @confirm="confirmDelete" />-->
  <ReviewForm ref="reviewFormRef" @success="getList" />
  <ExportOptionsForm ref="exportOptionsFormRef" @confirm="confirmExport" />
  <ImportForm ref="importFormRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { WarningAlertListTableApi, WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import WarningAlertListTableForm from './WarningAlertListTableForm.vue'
import WarningDetailDrawer from './components/WarningDetailDrawer.vue'
import DispatchOrderForm from './components/DispatchOrderForm.vue'
import ReceiveOrderForm from './components/ReceiveOrderForm.vue'
import SubmitReviewForm from './components/SubmitReviewForm.vue'
import ReviewForm from './components/ReviewForm.vue'
import ExportOptionsForm from './components/ExportOptionsForm.vue'
import ImportForm from './components/ImportForm.vue'
// import DeleteReasonForm from './components/DeleteReasonForm.vue'
defineOptions({ name: 'WarningAlertListTable' })

const message = useMessage()
// const { t } = useI18n()

// 组件引用
const formRef = ref()
const detailDrawerRef = ref()
const dispatchFormRef = ref()
const receiveFormRef = ref()
const submitReviewFormRef = ref()
const reviewFormRef = ref()
const exportOptionsFormRef = ref()
const importFormRef = ref()

// 状态管理
const loading = ref(true)
const list = ref<WarningAlertListTableVO[]>([])
const total = ref(0)
const exportLoading = ref(false)
const isDrawerFullscreen = ref(false)
const currentRowId = ref<number | null>(null)  // 仅用于行高亮

// 选项数据
const warningFieldOptions = ref([
  { label: '网络监控', value: 'field1' },
  { label: '系统监控', value: 'field2' },
  { label: '视频监控', value: 'field3' }
])

const warningTypeOptions = ref([
  { label: '端口异常', value: 'type1' },
  { label: 'CPU过载', value: 'type2' },
  { label: '设备离线', value: 'type3' }
])

const departmentOptions = ref([
  { id: 'dept1', name: '环境监测部' },
  { id: 'dept2', name: '安全监察部' },
  { id: 'dept3', name: 'IT运维部' }
])

const queryFormRef = ref()

// 查询参数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  alertCode: undefined,
  relatedObjectName: undefined,
  warningField: undefined,
  warningType: undefined,
  warningLevel: undefined,
  warningStatus: undefined,
  dispatchDepartment: undefined,
  orderByColumn: '', // 初始无排序
  isAsc: '' // 初始无排序
})

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await WarningAlertListTableApi.getWarningAlertListTablePage(queryParams)
    list.value = data.list
    total.value = data.total
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
  queryFormRef.value.resetFields()
  queryParams.orderByColumn = '' // 重置为无排序
  queryParams.isAsc = '' // 重置为无排序
  handleQuery()
}

/** 排序变化处理 */
const handleSortChange = (sort: { column: any, prop: string, order: string }) => {
  if (sort.prop && sort.order) {
    queryParams.orderByColumn = sort.prop
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } else {
    queryParams.orderByColumn = '' // 取消排序时重置
    queryParams.isAsc = '' // 取消排序时重置
  }
  getList()
}

/** 添加/修改操作 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 行点击打开详情（核心方法） */
const handleOpenDetail = async (id: number) => {
  try {
    // 直接加载新数据并打开抽屉，不判断抽屉是否已打开
    const detailData = await WarningAlertListTableApi.getWarningAlertListTable(id)
    detailDrawerRef.value.open(detailData)
    currentRowId.value = id  // 记录当前行ID用于高亮
  } catch (error) {
    message.error('获取预警详情失败，请重试')
  }
}

/** 抽屉关闭时重置状态 */
const handleDrawerClose = () => {
  currentRowId.value = null
  isDrawerFullscreen.value = false
}

/** 行高亮样式 */
const tableRowClassName = ({ row }: { row: WarningAlertListTableVO }) => {
  return row.id === currentRowId.value ? 'bg-primary/5' : ''
}

/** 派单操作 */
const handleDispatch = (row: WarningAlertListTableVO) => {
  dispatchFormRef.value.open(row)
}

/** 接单操作 */
const handleReceiveOrder = (row: WarningAlertListTableVO) => {
  receiveFormRef.value.open(row)
}

/** 提交审核操作 */
const handleSubmitReview = (row: WarningAlertListTableVO) => {
  submitReviewFormRef.value.open(row)
}

/** 审核操作 */
const handleReview = (row: WarningAlertListTableVO) => {
  reviewFormRef.value.open(row)
}

/** 删除操作 */
const handleDelete = (row: WarningAlertListTableVO) => {
  if (row.warningStatus !== 'pending_dispatch') {
    message.warning('只有待派单状态的预警可以删除')
    return
  }
  // 实际项目中这里应该打开删除确认弹窗
  message.success('删除逻辑已触发')
}

/** 确认删除 */
// const confirmDelete = async (reason: string) => {
//   if (!deleteRowId.value) return
//
//   try {
//     await WarningAlertListTableApi.deleteWarningAlertListTable(deleteRowId.value)
//     message.success(t('common.delSuccess'))
//     await getList()
//   } catch {} finally {
//     deleteRowId.value = null
//   }
// }

/** 导入操作 */
const handleImport = () => {
  importFormRef.value.open()
}

/** 打开导出选项 */
const openExportOptions = () => {
  exportOptionsFormRef.value.open()
}

/** 确认导出 */
const confirmExport = async (fields: string[], format: string) => {
  try {
    exportLoading.value = true
    const data = await WarningAlertListTableApi.exportWarningAlertListTable({
      ...queryParams,
      exportFields: fields,
      format
    })

    const fileName = `预警记录.${format === 'excel' ? 'xls' : 'csv'}`
    download.excel(data, fileName)
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 下载附件 */
// const handleDownloadAttachment = async (filePath: string) => {
//   try {
//     const data = await WarningAlertListTableApi.downloadAttachment(filePath)
//     download.file(data, filePath.split('/').pop() || 'attachment')
//   } catch (error) {
//     message.error('下载失败')
//   }
// }

/** 判断是否超时 */
const isOverdue = (row: WarningAlertListTableVO) => {
  if (!row.requiredCompleteTime || !row.warningStatus ||
    ['completed', 'rejected'].includes(row.warningStatus)) {
    return false
  }

  const requiredTime = new Date(row.requiredCompleteTime).getTime()
  const now = new Date().getTime()

  return now > requiredTime
}

/** 获取超时小时数 */
const getOverdueHours = (row: WarningAlertListTableVO) => {
  if (!row.requiredCompleteTime) return 0
  const requiredTime = new Date(row.requiredCompleteTime).getTime()
  const now = new Date().getTime()
  const hours = Math.ceil((now - requiredTime) / (1000 * 60 * 60))

  return hours > 0 ? hours : 0
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>
