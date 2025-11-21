<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
    >
      <el-form-item label="跟踪ID" prop="handleTraceId">
        <el-input
          v-model="queryParams.handleTraceId"
          placeholder="请输入跟踪ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="派单ID" prop="dispatchId">
        <el-input
          v-model="queryParams.dispatchId"
          placeholder="请输入派单ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="派单编号" prop="dispatchNo">
        <el-input
          v-model="queryParams.dispatchNo"
          placeholder="请输入派单编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警ID" prop="earlyWarnId">
        <el-input
          v-model="queryParams.earlyWarnId"
          placeholder="请输入预警ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处置进度" prop="handleProgress">
        <el-input
          v-model="queryParams.handleProgress"
          placeholder="请输入处置进度"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="操作时间" prop="operTime">
        <el-date-picker
          v-model="queryParams.operTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
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
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="跟踪ID" align="center" prop="handleTraceId" />
      <el-table-column label="派单ID" align="center" prop="dispatchId" />
      <el-table-column label="派单编号" align="center" prop="dispatchNo" />
      <el-table-column label="预警ID" align="center" prop="earlyWarnId" />
      <el-table-column label="处置进度" align="center" prop="handleProgress" />
      <el-table-column label="操作人" align="center" prop="operUserName" />
      <el-table-column
        label="操作时间"
        align="center"
        prop="operTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="是否关键节点" align="center" prop="isKeyNode">
        <template #default="scope">
          <span :class="scope.row.isKeyNode === '1' ? 'text-green-500' : 'text-gray-500'">
            {{ scope.row.isKeyNode === '1' ? '是' : '否' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
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

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnHandleTraceForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnHandleTraceDetail
    ref="detailRef"
    :visible="detailVisible"
    :data="currentDetailData"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnHandleTraceApi,
  EarlyWarnHandleTraceVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnhandletrace'
import EarlyWarnHandleTraceForm from './EarlyWarnHandleTraceForm.vue'
import EarlyWarnHandleTraceDetail from './EarlyWarnHandleTraceDetail.vue'

/** 预警告警处置跟踪 列表 */
defineOptions({ name: 'EarlyWarnHandleTrace' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnHandleTraceVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  handleTraceId: undefined,
  dispatchId: undefined,
  dispatchNo: undefined,
  earlyWarnId: undefined,
  handleProgress: undefined,
  progressDesc: undefined,
  operUserId: undefined,
  operUserName: undefined,
  operTime: [],
  attachUrl: undefined,
  isKeyNode: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const currentDetailData = ref<EarlyWarnHandleTraceVO | null>(null)

const openDetail = (row: EarlyWarnHandleTraceVO) => {
  currentDetailData.value = row
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnHandleTraceApi.getEarlyWarnHandleTracePage(queryParams)
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
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnHandleTraceApi.deleteEarlyWarnHandleTrace(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await EarlyWarnHandleTraceApi.exportEarlyWarnHandleTrace(queryParams)
    download.excel(data, '预警告警处置跟踪.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  font-weight: 500;
  color: #4b5563;
}

.text-green-500 {
  color: #10b981;
  font-weight: 500;
}

.text-gray-500 {
  color: #6b7280;
}
</style>
