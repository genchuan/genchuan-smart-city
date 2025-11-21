<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="110px"
    >
      <!-- 保留常用搜索字段，隐藏次要字段 -->
      <el-form-item label="整改任务ID" prop="rectifyTaskId">
        <el-input
          v-model="queryParams.rectifyTaskId"
          placeholder="请输入整改任务ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="整改任务编码" prop="rectifyTaskCode">
        <el-input
          v-model="queryParams.rectifyTaskCode"
          placeholder="请输入整改任务编码"
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
      <el-form-item label="责任部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入责任部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="整改负责人姓名" prop="leaderUserName">
        <el-input
          v-model="queryParams.leaderUserName"
          placeholder="请输入整改负责人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="整改任务状态" prop="rectifyStatus">
        <el-select
          v-model="queryParams.rectifyStatus"
          placeholder="请选择整改任务状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否超期" prop="isOverdue">
        <el-select
          v-model="queryParams.isOverdue"
          placeholder="请选择是否超期"
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
      <el-table-column label="整改任务ID" align="center" prop="rectifyTaskId" />
      <el-table-column label="整改任务编码" align="center" prop="rectifyTaskCode" />
      <el-table-column label="关联问题名称" align="center" prop="problemName" min-width="150" />
      <el-table-column label="责任部门名称" align="center" prop="deptName" />
      <el-table-column label="整改负责人姓名" align="center" prop="leaderUserName" />
      <el-table-column label="整改任务状态" align="center" prop="rectifyStatus">
        <template #default="scope">
          <el-tag :type="scope.row.rectifyStatus === '已完成' ? 'success' : 'primary'">
            {{ scope.row.rectifyStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="整改时限（小时）" align="center" prop="rectifyDeadlineHour" />
      <el-table-column
        label="整改截止时间"
        align="center"
        prop="rectifyDeadlineTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="是否超期" align="center" prop="isOverdue">
        <template #default="scope">
          <el-tag :type="scope.row.isOverdue ? 'danger' : 'success'">
            {{ scope.row.isOverdue ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="催办次数" align="center" prop="remindCount" />
      <el-table-column
        label="最新进展时间"
        align="center"
        prop="latestProgressTime"
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
  <InspectRectifyTrackForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectRectifyTrackDetail ref="detailRef" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectRectifyTrackApi,
  InspectRectifyTrackVO
} from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionProgressTracking'
import InspectRectifyTrackForm from './InspectRectifyTrackForm.vue'
import InspectRectifyTrackDetail from './InspectRectifyTrackDetail.vue'

/** 巡查巡检整改进度跟踪视图 列表 */
defineOptions({ name: 'InspectRectifyTrack' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectRectifyTrackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rectifyTaskId: undefined,
  rectifyTaskCode: undefined,
  problemName: undefined,
  deptName: undefined,
  leaderUserName: undefined,
  rectifyStatus: undefined,
  isOverdue: undefined,
  // 隐藏其他查询字段
  rectifyDeadlineHour: undefined,
  rectifyDeadlineTime: [],
  remainTimeMin: undefined,
  latestProgressTime: [],
  latestProgressDesc: undefined,
  latestProgressPhoto: undefined,
  remindCount: undefined,
  lastRemindTime: [],
  dispatchTime: [],
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const openDetail = (row: InspectRectifyTrackVO) => {
  detailRef.value.open(row)
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectRectifyTrackApi.getInspectRectifyTrackPage(queryParams)
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
    await InspectRectifyTrackApi.deleteInspectRectifyTrack(id)
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
    const data = await InspectRectifyTrackApi.exportInspectRectifyTrack(queryParams)
    download.excel(data, '巡查巡检整改进度跟踪视图.xls')
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
  color: #4e5969;
}

::v-deep .el-table td {
  padding: 12px 0;
}
</style>
