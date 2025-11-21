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
        <el-tree-select
          v-model="queryParams.warningType"
          :data="warningTypeTree"
          filterable
          clearable
          placeholder="请选择预警类型"
          :loading="warningTypeLoading"
          style="width: 240px"
          :props="{ value: 'value', label: 'label', children: 'children' }"
          @change="handleWarningTypeChange"
        />
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
      <el-form-item label="预警状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择预警状态"
          clearable
          class="!w-240px"
        >
          <el-option label="待派单" value="0" />
          <el-option label="已派单" value="1" />
        </el-select>
      </el-form-item>

      <el-form-item label="派发部门" prop="dispatchDepartment">
        <el-tree-select
          v-model="queryParams.dispatchDepartment"
          :data="deptTree"
          filterable
          clearable
          placeholder="请选择派发部门"
          :loading="deptLoading"
          style="width: 240px"
          :props="{ value: 'value', label: 'label', children: 'children' }"
          @change="handleDeptChange"
        />
      </el-form-item>

      <el-form-item label="责任人">
        <el-select
          v-model="queryParams.responsiblePerson"
          placeholder="请选择责任人"
          filterable
          clearable
          style="width: 180px"
        >
          <el-option
            v-for="user in userList"
            :key="user.id"
            :label="user.nickname"
            :value="user.id"
          />
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <!--        <el-button-->
        <!--          type="primary"-->
        <!--          plain-->
        <!--          @click="handleImport"-->
        <!--          v-hasPermi="['datacenter:warning-alert-list-table:import']"-->
        <!--        >-->
        <!--          <Icon icon="ep:upload" class="mr-5px" /> 导入-->
        <!--        </el-button>-->
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
        prop="status"
      >
        <template #default="scope">
          <el-tag
            :type="scope.row.status === 0 ? 'info' :'success' "
          >
            {{ scope.row.status === 0 ? '待派单' : '已派单'}}
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

      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="scope">
          <!-- 待派单状态操作 -->
          <template v-if="scope.row.status === 0 ">
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
              type="danger"
              @click.stop="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:warning-alert-list-table:delete']"
            >
              删除
            </el-button>
          </template>

          <!-- 通用操作：查看详情 -->
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
  <!--  <DispatchOrderForm ref="dispatchFormRef" @success="getList" />-->
  <ReceiveOrderForm ref="receiveFormRef" @success="getList" />
  <SubmitReviewForm ref="submitReviewFormRef" @success="getList" />
  <!--  <DeleteReasonForm ref="deleteReasonFormRef" @confirm="confirmDelete" />-->
  <ReviewForm ref="reviewFormRef" @success="getList" />
  <ExportOptionsForm ref="exportOptionsFormRef" @confirm="confirmExport" />
  <!--  <ImportForm ref="importFormRef" @success="getList" />-->
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { WarningAlertListTableApi, WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import WarningAlertListTableForm from './WarningAlertListTableForm.vue'
import WarningDetailDrawer from './components/WarningDetailDrawer.vue'
// import DispatchOrderForm from './components/DispatchOrderForm.vue'
import ReceiveOrderForm from './components/ReceiveOrderForm.vue'
import SubmitReviewForm from './components/SubmitReviewForm.vue'
import ReviewForm from './components/ReviewForm.vue'
import ExportOptionsForm from './components/ExportOptionsForm.vue'
// import ImportForm from './components/ImportForm.vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
// import DeleteReasonForm from './components/DeleteReasonForm.vue'
defineOptions({ name: 'WarningAlertListTable' })

const message = useMessage()
const { t } = useI18n()

// 组件引用
const formRef = ref()
const detailDrawerRef = ref()
// const dispatchFormRef = ref()
const receiveFormRef = ref()
const submitReviewFormRef = ref()
const reviewFormRef = ref()
const exportOptionsFormRef = ref()
// const importFormRef = ref()

// 状态管理
const loading = ref(true)
const list = ref<WarningAlertListTableVO[]>([])
const total = ref(0)
const exportLoading = ref(false)
const isDrawerFullscreen = ref(false)
const currentRowId = ref<number | null>(null)  // 仅用于行高亮
/** 用户数据接口类型 */
interface UserItem {
  id: number
  nickname: string
  deptId: number | null
  deptName: string | null
}

/** 用户列表：用于责任人下拉框与表格昵称回显 */
const userList = ref<UserItem[]>([])
/** 获取用户列表 */
const getUserList = async () => {
  const res = await WarningAlertListTableApi.getSimpleUserList()
  // if (res.code === 0 && Array.isArray(res.data)) {
  //   userList.value = res
  // } else {
  //   userList.value = []
  // }
  userList.value = res
}

// 选项数据
const warningFieldOptions = ref([
  { label: '网络监控', value: 'field1' },
  { label: '系统监控', value: 'field2' },
  { label: '视频监控', value: 'field3' }
])

/** ====================== 预警类型树选择 ====================== */
const warningTypeTree = ref<any[]>([])
const warningTypeLoading = ref(false)

/** 构建树形数据结构 */
function buildTreeSelectData(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach(item => map.set(item.id, { value: item.id, label: item.matterName, children: [] }))
  list.forEach(item => {
    const node = map.get(item.id)
    if (item.parentId && item.parentId !== '0') {
      const parent = map.get(Number(item.parentId))
      if (parent) parent.children.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

/** 获取预警类型树 */
const loadWarningTypeTree = async () => {
  warningTypeLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getWarningTypeTree()
    warningTypeTree.value = buildTreeSelectData(res)
  } finally {
    warningTypeLoading.value = false
  }
}

/** 选择预警类型后自动触发搜索 */
const handleWarningTypeChange = (val: number) => {
  const label = findLabelById(warningTypeTree.value, val)
  queryParams.warningType = label
  handleQuery() // 触发表格查询
}

/** 递归查找 label */
function findLabelById(nodes: any[], id: number): string | undefined {
  for (const n of nodes) {
    if (n.value === id) return n.label
    if (n.children) {
      const label = findLabelById(n.children, id)
      if (label) return label
    }
  }
}


/** ====================== 派发部门树 ====================== */
const deptTree = ref<any[]>([])
const deptLoading = ref(false)

function buildDeptTree(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach(item => map.set(item.id, { value: item.id, label: item.name, children: [] }))
  list.forEach(item => {
    const node = map.get(item.id)
    if (item.parentId && item.parentId !== 0) {
      const parent = map.get(item.parentId)
      if (parent) parent.children.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

const loadDeptTree = async () => {
  deptLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getDeptTree()
    deptTree.value = buildDeptTree(res)
  } finally {
    deptLoading.value = false
  }
}

const handleDeptChange = (val: number) => {
  const label = findLabelById(deptTree.value, val)
  queryParams.dispatchDepartment = label
  handleQuery()
}

/** 根据用户ID查找用户昵称（用于列表与回显） */
function findUserNicknameById(id: string | number | undefined): string | undefined {
  if (!id) return undefined
  const user = userList.value.find(u => String(u.id) === String(id))
  return user ? user.nickname : undefined
}



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
  isAsc: '' ,// 初始无排序
  status:undefined,
  responsiblePerson:undefined
})


/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await WarningAlertListTableApi.getWarningAlertListTablePage(queryParams)
    list.value = data.list.map(item => {
      //  部门名称映射
      const deptNode = findLabelById(deptTree.value, Number(item.dispatchDepartment))

      //  责任人昵称映射（id → name）
      const userNode = findUserNicknameById(item.responsiblePerson)

      return {
        ...item,
        dispatchDepartment: deptNode || item.dispatchDepartment,
        responsiblePerson: userNode || item.responsiblePerson // 若未匹配则显示原值
      }
    })
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

/** 派单操作,触发流程 */
const handleDispatch = async (row: WarningAlertListTableVO) => {
  try {
    await WarningAlertListTableApi.dispatchWarningAlertListTable(row.id);
    ElMessage.success('派单成功');
    getList()
  } catch (error) {
    ElMessage.error('派单失败，请重试');
  }
}

/** 接单操作 */
// const handleReceiveOrder = (row: WarningAlertListTableVO) => {
//   receiveFormRef.value.open(row)
// }

/** 提交审核操作 */
// const handleSubmitReview = (row: WarningAlertListTableVO) => {
//   submitReviewFormRef.value.open(row)
// }

/** 审核操作 */
// const handleReview = (row: WarningAlertListTableVO) => {
//   reviewFormRef.value.open(row)
// }


/** 确认删除 */
const handleDelete = async (id: number) => {
  try {
    await WarningAlertListTableApi.deleteWarningAlertListTable(id)
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

/** 导入操作 */
// const handleImport = () => {
//   importFormRef.value.open()
// }

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
  loadWarningTypeTree()
  loadDeptTree()
  getUserList()
  getList()
})
</script>
