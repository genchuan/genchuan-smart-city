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
      <!-- 保留常用搜索字段，隐藏次要字段 -->
      <el-form-item label="督办任务ID" prop="supvTaskId">
        <el-input
          v-model="queryParams.supvTaskId"
          placeholder="请输入督办任务ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="督办任务编码" prop="supvTaskCode">
        <el-input
          v-model="queryParams.supvTaskCode"
          placeholder="请输入督办任务编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联问题名称" prop="problemName">
        <el-input
          v-model="queryParams.problemName"
          placeholder="请输入关联问题名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="督办人姓名" prop="supvUserName">
        <el-input
          v-model="queryParams.supvUserName"
          placeholder="请输入督办人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="督办任务状态" prop="supvStatus">
        <el-select
          v-model="queryParams.supvStatus"
          placeholder="请选择督办任务状态"
          clearable
          class="!w-240px"
        >
          <el-option label="未开始" value="1" />
          <el-option label="进行中" value="2" />
          <el-option label="已完成" value="3" />
          <el-option label="已逾期" value="4" />
        </el-select>
      </el-form-item>
      <el-form-item label="督办截止时间" prop="supvDeadlineTime">
        <el-date-picker
          v-model="queryParams.supvDeadlineTime"
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
      <el-table-column label="督办任务ID" align="center" prop="supvTaskId" />
      <el-table-column label="督办任务编码" align="center" prop="supvTaskCode" />
      <el-table-column label="关联整改任务编码" align="center" prop="rectifyTaskCode" />
      <el-table-column label="关联问题名称" align="center" prop="problemName" min-width="150" />
      <el-table-column label="督办人姓名" align="center" prop="supvUserName" />
      <el-table-column label="督办频次" align="center" prop="supvFrequency" />
      <el-table-column
        label="督办截止时间"
        align="center"
        prop="supvDeadlineTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="督办任务状态" align="center" prop="supvStatus">
        <template #default="scope">
          <el-tag :type="getSupvStatusTagType(scope.row.supvStatus)">
            {{ scope.row.supvStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectSupvTaskForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectSupvTaskDetail ref="detailRef" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectSupvTaskApi,
  InspectSupvTaskVO
} from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightCreation'
import InspectSupvTaskForm from './InspectSupvTaskForm.vue'
import InspectSupvTaskDetail from './InspectSupvTaskDetail.vue'

/** 巡查巡检重点督办任务 列表 */
defineOptions({ name: 'InspectSupvTask' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectSupvTaskVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  supvTaskId: undefined,
  supvTaskCode: undefined,
  problemName: undefined,
  supvUserName: undefined,
  supvStatus: undefined,
  supvDeadlineTime: [],
  // 隐藏其他查询字段
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemId: undefined,
  supvUserId: undefined,
  supvFrequency: undefined,
  supvRequirement: undefined,
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
const openDetail = (row: InspectSupvTaskVO) => {
  detailRef.value.open(row)
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectSupvTaskApi.getInspectSupvTaskPage(queryParams)
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
    await InspectSupvTaskApi.deleteInspectSupvTask(id)
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
    const data = await InspectSupvTaskApi.exportInspectSupvTask(queryParams)
    download.excel(data, '巡查巡检重点督办任务.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 获取督办状态文本 */
// const getSupvStatusText = (status: string) => {
//   switch (status) {
//     case '1':
//       return '未开始'
//     case '2':
//       return '进行中'
//     case '3':
//       return '已完成'
//     case '4':
//       return '已逾期'
//     default:
//       return status || '-'
//   }
// }

/** 获取督办状态标签类型 */
const getSupvStatusTagType = (status: string) => {
  switch (status) {
    case '督办中':
      return 'primary'
    case '已完成':
      return 'success'
    case '待处理':
      return 'warning'
    case '4':
      return 'danger'
    default:
      return 'info'
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
  color: #4e5969;
}

::v-deep .el-table td {
  padding: 12px 0;
}
</style>
