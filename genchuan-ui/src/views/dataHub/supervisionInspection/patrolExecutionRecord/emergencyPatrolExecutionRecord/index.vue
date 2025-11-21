<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="140px"
    >
      <!-- 保留常用搜索字段，隐藏部分次要字段 -->
      <el-form-item label="记录ID" prop="emerExecId">
        <el-input
          v-model="queryParams.emerExecId"
          placeholder="请输入记录ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="记录编码" prop="emerExecCode">
        <el-input
          v-model="queryParams.emerExecCode"
          placeholder="请输入记录编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联应急计划名称" prop="emerPlanName">
        <el-input
          v-model="queryParams.emerPlanName"
          placeholder="请输入关联应急计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联应急事件名称" prop="emerEvtName">
        <el-input
          v-model="queryParams.emerEvtName"
          placeholder="请输入关联应急事件名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执行人员姓名" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入执行人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执行开始时间" prop="execStartTime">
        <el-date-picker
          v-model="queryParams.execStartTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="是否已推送指挥中心" prop="isPushedCmd">
        <el-select
          v-model="queryParams.isPushedCmd"
          placeholder="请选择是否已推送指挥中心"
          clearable
          class="!w-240px"
        >
          <el-option label="是" value="true" />
          <el-option label="否" value="false" />
        </el-select>
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
      <el-table-column label="记录ID" align="center" prop="emerExecId" />
      <el-table-column label="记录编码" align="center" prop="emerExecCode" />
      <el-table-column label="关联应急计划名称" align="center" prop="emerPlanName" />
      <el-table-column label="关联应急事件名称" align="center" prop="emerEvtName" />
      <el-table-column label="执行人员姓名" align="center" prop="personName" />
      <el-table-column
        label="执行开始时间"
        align="center"
        prop="execStartTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="执行完成时间"
        align="center"
        prop="execCompleteTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="紧急级别" align="center" prop="urgencyLevel" />
      <el-table-column label="是否已推送指挥中心" align="center" prop="isPushedCmd" width="140">
        <template #default="scope">
          <el-tag :type="scope.row.isPushedCmd ? 'success' : 'danger'">
            {{ scope.row.isPushedCmd ? '是' : '否' }}
          </el-tag>
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectEmerExecRecForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectEmerExecRecDetail ref="detailRef" @close="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectEmerExecRecApi,
  InspectEmerExecRecVO
} from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/emergencyPatrolExecutionRecord'
import InspectEmerExecRecForm from './InspectEmerExecRecForm.vue'
import InspectEmerExecRecDetail from './InspectEmerExecRecDetail.vue'

/** 应急巡查执行记录 列表 */
defineOptions({ name: 'InspectEmerExecRec' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectEmerExecRecVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  emerExecId: undefined,
  emerExecCode: undefined,
  emerPlanName: undefined,
  emerEvtName: undefined,
  personName: undefined,
  execStartTime: [],
  isPushedCmd: undefined,
  // 保留其他字段但不在搜索栏显示
  emerPlanId: undefined,
  emerEvtId: undefined,
  taskId: undefined,
  taskCode: undefined,
  personId: undefined,
  execCompleteTime: [],
  emerCkDetail: undefined,
  urgencyLevel: undefined,
  emerProblemDesc: undefined,
  disposalSugg: undefined,
  deviceCode: undefined,
  emerLocX: undefined,
  emerLocY: undefined,
  pushTime: [],
  bizCreateUser: undefined,
  bizCreateTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectEmerExecRecApi.getInspectEmerExecRecPage(queryParams)
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

/** 详情操作 */
const detailRef = ref()
const openDetail = (row: InspectEmerExecRecVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectEmerExecRecApi.deleteInspectEmerExecRec(id)
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
    const data = await InspectEmerExecRecApi.exportInspectEmerExecRec(queryParams)
    download.excel(data, '应急巡查执行记录.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
