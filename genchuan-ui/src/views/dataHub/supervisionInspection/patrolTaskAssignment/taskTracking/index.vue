<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="80px"
    >
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
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskType">
        <el-select
          v-model="queryParams.taskType"
          placeholder="请选择任务类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="负责人员" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入负责人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select
          v-model="queryParams.taskStatus"
          placeholder="请选择任务状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="queryParams.startTime"
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
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="任务编码" align="center" prop="taskCode" />
      <el-table-column label="任务名称" align="center" prop="taskName" min-width="150" />
      <el-table-column label="任务类型" align="center" prop="taskType" />
      <el-table-column label="负责人员" align="center" prop="personName" />
      <el-table-column label="所属区域" align="center" prop="areaName" />
      <el-table-column label="任务状态" align="center" prop="taskStatus">
        <template #default="scope">
          <span
            :class="
              scope.row.taskStatus === '已完成'
                ? 'text-green-500'
                : scope.row.taskStatus === '待开始'
                  ? 'text-[#4494efff]'
                  : 'text-[#ef8644ff]'
            "
          >
            {{ scope.row.taskStatus }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="截止时间"
        align="center"
        prop="deadlineTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="完成时间"
        align="center"
        prop="completeTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="检查项完成率" align="center" min-width="120">
        <template #default="scope">
          <span>
            {{
              scope.row.totalCkItem
                ? Math.round((scope.row.completedCkItem / scope.row.totalCkItem) * 100) + '%'
                : '0%'
            }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="是否超时" align="center" prop="isOvertime">
        <template #default="scope">
          <span :class="scope.row.isOvertime === 1 ? 'text-red-500' : 'text-green-500'">
            {{ scope.row.isOvertime === 1 ? '是' : '否' }}
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectTaskTrackForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectTaskTrackDetail
    v-model:visible="detailVisible"
    :detail-data="detailData"
    @close="handleDetailClose"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectTaskTrackApi,
  InspectTaskTrackVO
} from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskTracking'
import InspectTaskTrackForm from './InspectTaskTrackForm.vue'
import InspectTaskTrackDetail from './InspectTaskTrackDetail.vue'

/** 巡查巡检任务跟踪 列表 */
defineOptions({ name: 'InspectTaskTrack' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectTaskTrackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  taskId: undefined,
  taskCode: undefined,
  taskName: undefined,
  taskType: undefined,
  personId: undefined,
  personName: undefined,
  areaCode: undefined,
  areaName: undefined,
  taskStatus: undefined,
  startTime: [],
  completeTime: [],
  deadlineTime: [],
  totalCkItem: undefined,
  completedCkItem: undefined,
  uploadPhotoCount: undefined,
  isOvertime: [],
  remindCount: undefined,
  lastRemindTime: [],
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<InspectTaskTrackVO>({} as InspectTaskTrackVO)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectTaskTrackApi.getInspectTaskTrackPage(queryParams)
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
const openDetail = (row: InspectTaskTrackVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 关闭详情 */
const handleDetailClose = () => {
  detailData.value = {} as InspectTaskTrackVO
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectTaskTrackApi.deleteInspectTaskTrack(id)
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
    const data = await InspectTaskTrackApi.exportInspectTaskTrack(queryParams)
    download.excel(data, '巡查巡检任务跟踪.xls')
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
.search-form {
  padding: 16px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  margin-bottom: 16px !important;
}

.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

.pagination {
  margin-top: 16px;
  text-align: right;
}

::v-deep .el-table th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #fafafa !important;
}

::v-deep .el-table--striped .el-table__body tr.el-table__row--striped td {
  background-color: #fcfcfc;
}
</style>
