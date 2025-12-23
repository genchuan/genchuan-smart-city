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

      <!-- 新增：应用场景搜索 -->
      <el-form-item label="应用场景">
        <!-- 绑定ref用于调用子组件方法 -->
        <AppSceneTree
          ref="appSceneTreeRef"
          v-model="sceneSelectedCodes"
          @change="handleSceneChange"
          style="width: 220px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button
          type="success"
          plain
          @click="openExportOptions"
          :loading="exportLoading"
          v-hasPermi="['datacenter:warning-alert-list-table:export']"
        >
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
        <!--表格字段是否显示-->
        <el-dropdown trigger="click" :hide-on-click="false" style="padding-left: 12px">
          <el-button circle :icon="Menu" />
          <template #dropdown>
            <el-dropdown-menu>
              <template v-for="item in tableColumns" :key="item.visible">
                <el-dropdown-item>
                  <el-checkbox v-model="item.visible" :label="item.label" />
                </el-dropdown-item>
              </template>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
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
      <el-table-column
        label="预警ID"
        align="center"
        prop="id"
        v-if="tableColumnShow('预警ID')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="告警编号"
        align="center"
        v-if="tableColumnShow('告警编号')"
        :show-overflow-tooltip="true"
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
      <el-table-column
        label="关联对象类型"
        align="center"
        prop="relatedObjectType"
        v-if="tableColumnShow('关联对象类型')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="关联对象名称"
        align="center"
        prop="relatedObjectName"
        v-if="tableColumnShow('关联对象名称')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="预警类型"
        align="center"
        prop="warningType"
        v-if="tableColumnShow('预警类型')"
        :show-overflow-tooltip="true"
      />

      <!-- 新增：应用场景列 -->
      <el-table-column
        label="应用场景"
        align="center"
        prop="appScene"
        v-if="tableColumnShow('应用场景')"
        :show-overflow-tooltip="true"
      />

      <!-- 预警等级列 -->
      <el-table-column
        label="预警等级"
        align="center"
        prop="warningLevel"
        sortable="custom"
        v-if="tableColumnShow('预警等级')"
        :show-overflow-tooltip="true"
      >
        <template #default="scope">
          <el-tag
            :type="
              scope.row.warningLevel === 'emergency'
                ? 'danger'
                : scope.row.warningLevel === 'important'
                  ? 'warning'
                  : 'info'
            "
          >
            {{
              scope.row.warningLevel === 'emergency'
                ? '紧急'
                : scope.row.warningLevel === 'important'
                  ? '重要'
                  : '一般'
            }}
          </el-tag>
        </template>
      </el-table-column>

      <!-- 预警状态列 -->
      <el-table-column
        label="预警状态"
        align="center"
        prop="status"
        v-if="tableColumnShow('预警状态')"
        :show-overflow-tooltip="true"
      >
        <template #default="scope">
          <el-tag :type="scope.row.status === 0 ? 'info' : 'success'">
            {{ scope.row.status === 0 ? '待派单' : '已派单' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="触发原因"
        align="center"
        prop="triggerReason"
        v-if="tableColumnShow('触发原因')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="派发部门"
        align="center"
        prop="dispatchDepartment"
        v-if="tableColumnShow('派发部门')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="责任人"
        align="center"
        prop="responsiblePerson"
        v-if="tableColumnShow('责任人')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="行政区划编码"
        align="center"
        prop="regionCode"
        v-if="tableColumnShow('行政区划编码')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="所在行政区划名称"
        align="center"
        prop="regionName"
        v-if="tableColumnShow('所在行政区划名称')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="所在网格ID"
        align="center"
        prop="gridId"
        v-if="tableColumnShow('所在网格ID')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="所在网格名称"
        align="center"
        prop="gridName"
        v-if="tableColumnShow('所在网格名称')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="事件发生地址"
        align="center"
        prop="address"
        v-if="tableColumnShow('事件发生地址')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="经度"
        align="center"
        prop="longitude"
        v-if="tableColumnShow('经度')"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="纬度" align="center" prop="latitude" v-if="tableColumnShow('纬度')" />
      <el-table-column
        label="触发时间"
        align="center"
        prop="triggerTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
        v-if="tableColumnShow('触发时间')"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="要求完成时间"
        align="center"
        prop="requiredCompleteTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
        v-if="tableColumnShow('要求完成时间')"
        :show-overflow-tooltip="true"
      />

      <el-table-column label="操作" align="center" min-width="220px" fixed="right">
        <template #default="scope">
          <!-- 待派单状态操作 -->
          <template v-if="scope.row.status === 0">
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
  <ReceiveOrderForm ref="receiveFormRef" @success="getList" />
  <SubmitReviewForm ref="submitReviewFormRef" @success="getList" />
  <ReviewForm ref="reviewFormRef" @success="getList" />
  <ExportOptionsForm ref="exportOptionsFormRef" @confirm="confirmExport" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { WarningAlertListTableApi, WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import { MonEvtCatApi } from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'
import WarningAlertListTableForm from './WarningAlertListTableForm.vue'
import WarningDetailDrawer from './components/WarningDetailDrawer.vue'
import ReceiveOrderForm from './components/ReceiveOrderForm.vue'
import SubmitReviewForm from './components/SubmitReviewForm.vue'
import ReviewForm from './components/ReviewForm.vue'
import ExportOptionsForm from './components/ExportOptionsForm.vue'
import AppSceneTree from '@/views/dataHub/common/AppSceneTree.vue' // 新增：引入应用场景树组件
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { Menu } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router' // 新增：引入路由
defineOptions({ name: 'WarningAlertListTable' })

const route = useRoute() // 新增：路由实例
const message = useMessage()
const { t } = useI18n()

// 组件引用
const formRef = ref()
const detailDrawerRef = ref()
const receiveFormRef = ref()
const submitReviewFormRef = ref()
const reviewFormRef = ref()
const exportOptionsFormRef = ref()
const appSceneTreeRef = ref<any>(null) // 新增：应用场景树组件引用

// 状态管理
const loading = ref(true)
const list = ref<WarningAlertListTableVO[]>([])
const total = ref(0)
const exportLoading = ref(false)
const isDrawerFullscreen = ref(false)
const currentRowId = ref<number | null>(null) // 仅用于行高亮
const fromIndexPage = ref(false) // 新增：标记是否从其他页面跳转
const sceneSelectedCodes = ref<string[]>([]) // 新增：应用场景选中的编码

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
  userList.value = res
}

// 选项数据
const warningFieldOptions = ref([
  { label: '网络监控', value: 'field1' },
  { label: '系统监控', value: 'field2' },
  { label: '视频监控', value: 'field3' }
])

//表格字段太多,控制表格列显隐
//table 列显隐信息
const tableColumns = ref([
  { label: '预警ID', visible: true },
  { label: '告警编号', visible: true },
  { label: '关联对象类型', visible: true },
  { label: '关联对象名称', visible: true },
  { label: '预警类型', visible: true },
  { label: '应用场景', visible: true }, // 新增：应用场景列
  { label: '预警状态', visible: true },
  { label: '触发原因', visible: true },
  { label: '派发部门', visible: true },
  { label: '责任人', visible: true },
  { label: '行政区划编码', visible: false },
  { label: '所在行政区划名称', visible: false },
  { label: '所在网格ID', visible: false },
  { label: '所在网格名称', visible: false },
  { label: '事件发生地址', visible: false },
  { label: '经度', visible: false },
  { label: '纬度', visible: false },
  { label: '触发时间', visible: true },
  { label: '要求完成时间', visible: true }
])
//table 列是否显示
const tableColumnShow = (label) => {
  for (let i = 0; i < tableColumns.value.length; i++) {
    if (tableColumns.value[i].label === label) {
      return tableColumns.value[i].visible
    }
  }
}

/** ====================== 预警类型树选择 ====================== */
const warningTypeTree = ref<any[]>([])
const warningTypeLoading = ref(false)

/** 构建树形数据结构 */
function buildTreeSelectData(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach((item) =>
    map.set(item.id, { value: item.evtCatCode, label: item.evtCatName, children: [] })
  )
  list.forEach((item) => {
    const node = map.get(item.id)
    if (item.parentEvtId && item.parentEvtId !== '0') {
      const parent = map.get(Number(item.parentEvtId))
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
    const res = await MonEvtCatApi.getMonEvtCatPage({ pageNo: 1, pageSize: 100 })
    warningTypeTree.value = buildTreeSelectData(res.list)
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
  list.forEach((item) => map.set(item.id, { value: item.id, label: item.name, children: [] }))
  list.forEach((item) => {
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
  const user = userList.value.find((u) => String(u.id) === String(id))
  return user ? user.nickname : undefined
}

/** 新增：处理应用场景选择变化 */
const handleSceneChange = (data: { values: string[]; labels: string[] }, isInit = false) => {
  queryParams.extendCategory1 = data.values.length > 0 ? data.values[0] : undefined
  queryParams.extendCategory2 = data.labels.length > 0 ? data.labels[0] : undefined
  if (fromIndexPage.value && !isInit) {
    fromIndexPage.value = false
  }
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
  isAsc: '', // 初始无排序
  status: undefined,
  responsiblePerson: undefined,
  // 新增：应用场景相关参数
  extendCategory1: undefined,
  extendCategory2: undefined,
  code: undefined
})

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const params = {
      ...queryParams,
      // 从index跳转且未手动选择场景时，用code筛选
      extendCategory1: fromIndexPage.value && !queryParams.extendCategory1 && queryParams.code
        ? queryParams.code
        : queryParams.extendCategory1
    }

    const data = await WarningAlertListTableApi.getWarningAlertListTablePage(params)
    list.value = data.list.map((item) => {
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
  sceneSelectedCodes.value = [] // 新增：重置应用场景选择
  queryParams.orderByColumn = '' // 重置为无排序
  queryParams.isAsc = '' // 重置为无排序
  // 新增：重置应用场景相关参数
  Object.assign(queryParams, {
    extendCategory1: undefined,
    extendCategory2: undefined
  })
  handleQuery()
}

/** 排序变化处理 */
const handleSortChange = (sort: { column: any; prop: string; order: string }) => {
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

/** 行点击打开详情 */
const handleOpenDetail = async (id: number) => {
  try {
    // 直接加载新数据并打开抽屉，不判断抽屉是否已打开
    const detailData = await WarningAlertListTableApi.getWarningAlertListTable(id)
    //对抽屉数据赋值前将责任人映射传入
    detailData.responsiblePerson = findUserNicknameById(detailData.responsiblePerson)
    detailDrawerRef.value.open(detailData)
    currentRowId.value = id // 记录当前行ID用于高亮
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
    await WarningAlertListTableApi.dispatchWarningAlertListTable(row.id)
    ElMessage.success('派单成功')
    await getList()
  } catch (error) {
    ElMessage.error('派单失败，请重试')
  }
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await WarningAlertListTableApi.deleteWarningAlertListTable(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 打开导出选项 */
const openExportOptions = () => {
  exportOptionsFormRef.value.open()
}

/** 确认导出 */
const confirmExport = async (fields: string[], format: string) => {
  try {
    exportLoading.value = true
    // 新增：导出时包含应用场景参数
    const params = {
      ...queryParams,
      exportFields: fields,
      format,
      // 从index跳转且未手动选择场景时，用code筛选
      extendCategory1: fromIndexPage.value && !queryParams.extendCategory1 && queryParams.code
        ? queryParams.code
        : queryParams.extendCategory1
    }

    const data = await WarningAlertListTableApi.exportWarningAlertListTable(params)

    const fileName = `预警记录.${format === 'excel' ? 'xls' : 'csv'}`
    download.excel(data, fileName)
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 判断是否超时 */
const isOverdue = (row: WarningAlertListTableVO) => {
  if (
    !row.requiredCompleteTime ||
    !row.warningStatus ||
    ['completed', 'rejected'].includes(row.warningStatus)
  ) {
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
onMounted(async () => {
  loadWarningTypeTree()
  loadDeptTree()
  getUserList()

  // 新增：处理路由传值
  const code = route.query.code as string
  if (code && /^\d+$/.test(code)) {
    fromIndexPage.value = true
    queryParams.code = code

    // 等待子组件加载数据完成
    if (appSceneTreeRef.value) {
      await appSceneTreeRef.value.loadSceneTreeData()
    }

    // 查找code对应的label
    const sceneLabels = []
    if (appSceneTreeRef.value?.flatSceneList) {
      const matchItem = appSceneTreeRef.value.flatSceneList.find(
        (item: any) => item.sceneCatCode === code
      )
      if (matchItem) sceneLabels.push(matchItem.sceneCatName)
    }
    // 赋值选中值并触发change事件 初始化调用：传递isInit=true，避免重置fromIndexPage
    sceneSelectedCodes.value = [code]
    handleSceneChange({ values: [code], labels: sceneLabels }, true)
  }

  await getList()
})
</script>
