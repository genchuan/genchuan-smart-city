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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="记录ID" prop="specExecId">
        <el-input
          v-model="queryParams.specExecId"
          placeholder="请输入记录ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="记录编码" prop="specExecCode">
        <el-input
          v-model="queryParams.specExecCode"
          placeholder="请输入记录编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联专项计划" prop="specPlanName">
        <el-input
          v-model="queryParams.specPlanName"
          placeholder="请输入关联专项计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执行人员" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入执行人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执行时间" prop="execStartTime">
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
      <el-table-column label="记录ID" align="center" prop="specExecId" />
      <el-table-column label="记录编码" align="center" prop="specExecCode" />
      <el-table-column label="关联专项计划" align="center" prop="specPlanName" />
      <el-table-column label="关联任务" align="center" prop="taskCode" />
      <el-table-column label="执行人员" align="center" prop="personName" />
      <el-table-column label="关联标准" align="center" prop="stdName" />
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
      <el-table-column label="不合格项数量" align="center" prop="unqualifiedCount" />
      <el-table-column label="执行评分" align="center" prop="execScore" />
      <el-table-column label="执行设备" align="center" prop="deviceCode" />
      <el-table-column label="创建人" align="center" prop="bizCreateUser" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="bizCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
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
  <InspectSpecExecRecForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <InspectSpecExecRecDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectSpecExecRecApi,
  InspectSpecExecRecVO
} from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/specialPatrolExecutionRecord'
import InspectSpecExecRecForm from './InspectSpecExecRecForm.vue'
import InspectSpecExecRecDetail from './InspectSpecExecRecDetail.vue'

/** 专项巡查执行记录 列表 */
defineOptions({ name: 'InspectSpecExecRec' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectSpecExecRecVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  specExecId: undefined,
  specExecCode: undefined,
  specPlanId: undefined,
  specPlanName: undefined,
  taskId: undefined,
  taskCode: undefined,
  personId: undefined,
  personName: undefined,
  stdId: undefined,
  stdName: undefined,
  execStartTime: [],
  execCompleteTime: [],
  ckItemDetail: undefined,
  unqualifiedCount: undefined,
  unqualifiedDetail: undefined,
  execScore: undefined,
  specExecRemark: undefined,
  deviceCode: undefined,
  execLocX: undefined,
  execLocY: undefined,
  bizCreateUser: undefined,
  bizCreateTime: [],
  bizUpdateUser: undefined,
  bizUpdateTime: [],
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
    const data = await InspectSpecExecRecApi.getInspectSpecExecRecPage(queryParams)
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
const openDetail = (row: InspectSpecExecRecVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectSpecExecRecApi.deleteInspectSpecExecRec(id)
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
    const data = await InspectSpecExecRecApi.exportInspectSpecExecRec(queryParams)
    download.excel(data, '专项巡查执行记录.xls')
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
</style>
