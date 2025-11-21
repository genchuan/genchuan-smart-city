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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="分配ID" prop="allocateId">
        <el-input
          v-model="queryParams.allocateId"
          placeholder="请输入分配ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="任务编码" prop="taskCode">
        <el-input
          v-model="queryParams.taskCode"
          placeholder="请输入任务编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="原负责人" prop="oldPersonName">
        <el-input
          v-model="queryParams.oldPersonName"
          placeholder="请输入原负责人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="新负责人" prop="newPersonName">
        <el-input
          v-model="queryParams.newPersonName"
          placeholder="请输入新负责人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分配时间" prop="allocateTime">
        <el-date-picker
          v-model="queryParams.allocateTime"
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
      <!-- 只保留常用显示字段 -->
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="分配ID" align="center" prop="allocateId" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="任务编码" align="center" prop="taskCode" />
      <el-table-column label="原负责人ID" align="center" prop="oldPersonId" />
      <el-table-column label="原负责人姓名" align="center" prop="oldPersonName" />
      <el-table-column label="新负责人ID" align="center" prop="newPersonId" />
      <el-table-column label="新负责人姓名" align="center" prop="newPersonName" />
      <el-table-column label="分配原因" align="center" prop="allocateReason" />
      <el-table-column
        label="分配时间"
        align="center"
        prop="allocateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="分配人" align="center" prop="allocateUser" />
      <el-table-column label="任务状态变更" align="center" prop="taskStatusChange" />
      <el-table-column label="任务类型" align="center" prop="taskType" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
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
  <InspectTaskAllocateRecForm ref="formRef" @success="getList" />

  <!-- 详情抽屉组件 -->
  <InspectTaskAllocateRecDetail
    ref="detailRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectTaskAllocateRecApi,
  InspectTaskAllocateRecVO
} from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskAllocation'
import InspectTaskAllocateRecForm from './InspectTaskAllocateRecForm.vue'
import InspectTaskAllocateRecDetail from './InspectTaskAllocateRecDetail.vue'

/** 巡查巡检任务分配记录 列表 */
defineOptions({ name: 'InspectTaskAllocateRec' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectTaskAllocateRecVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  allocateId: undefined,
  taskId: undefined,
  taskCode: undefined,
  oldPersonId: undefined,
  oldPersonName: undefined,
  newPersonId: undefined,
  newPersonName: undefined,
  allocateReason: undefined,
  allocateTime: [],
  allocateUser: undefined,
  taskStatusChange: undefined,
  taskType: undefined,
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
const openDetail = (row: InspectTaskAllocateRecVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectTaskAllocateRecApi.getInspectTaskAllocateRecPage(queryParams)
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
    await InspectTaskAllocateRecApi.deleteInspectTaskAllocateRec(id)
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
    const data = await InspectTaskAllocateRecApi.exportInspectTaskAllocateRec(queryParams)
    download.excel(data, '巡查巡检任务分配记录.xls')
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

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-form-item {
  margin-right: 15px;
  margin-bottom: 15px;
}
</style>
