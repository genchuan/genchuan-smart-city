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
      <!-- 只保留常用搜索字段，隐藏部分不常用字段 -->
      <el-form-item label="问题ID" prop="problemId">
        <el-input
          v-model="queryParams.problemId"
          placeholder="请输入问题ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="问题编码" prop="problemCode">
        <el-input
          v-model="queryParams.problemCode"
          placeholder="请输入问题编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="问题名称" prop="problemName">
        <el-input
          v-model="queryParams.problemName"
          placeholder="请输入问题名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="问题类型" prop="matterTypeName">
        <el-input
          v-model="queryParams.matterTypeName"
          placeholder="请输入问题类型名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="上报时间" prop="rptTime">
        <el-date-picker
          v-model="queryParams.rptTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="问题状态" prop="problemStatus">
        <el-select
          v-model="queryParams.problemStatus"
          placeholder="请选择问题状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
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
      <el-table-column label="问题ID" align="center" prop="problemId" width="120" />
      <el-table-column label="问题编码" align="center" prop="problemCode" width="120" />
      <el-table-column label="问题名称" align="center" prop="problemName" min-width="150" />
      <el-table-column label="问题类型" align="center" prop="matterTypeName" width="120" />
      <el-table-column label="关联任务" align="center" prop="taskCode" width="120" />
      <el-table-column label="上报人员" align="center" prop="rptUserName" width="100" />
      <el-table-column label="所属网格" align="center" prop="gridName" width="120" />
      <el-table-column
        label="上报时间"
        align="center"
        prop="rptTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="问题状态" align="center" prop="problemStatus" width="100">
        <template #default="scope">
          <el-tag
            :type="
              scope.row.problemStatus === '已处理'
                ? 'success'
                : scope.row.problemStatus === '处理中'
                  ? 'warning'
                  : 'primary'
            "
          >
            {{ scope.row.problemStatus || '-' }}
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectProblemRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectProblemRptDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectProblemRptApi,
  InspectProblemRptVO
} from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueInfoReporting'
import InspectProblemRptForm from './InspectProblemRptForm.vue'
import InspectProblemRptDetail from './InspectProblemRptDetail.vue'

/** 巡查巡检问题上报记录 列表 */
defineOptions({ name: 'InspectProblemRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectProblemRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  problemId: undefined,
  problemCode: undefined,
  problemName: undefined,
  matterTypeId: undefined,
  matterTypeName: undefined,
  taskId: undefined,
  taskCode: undefined,
  rptUserId: undefined,
  rptUserName: undefined,
  gridId: undefined,
  gridName: undefined,
  problemLocX: undefined,
  problemLocY: undefined,
  problemLocDesc: undefined,
  problemDesc: undefined,
  problemPhotoUrls: undefined,
  rptTime: [],
  problemStatus: undefined,
  rejectReason: undefined,
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

// 详情抽屉引用
const detailRef = ref()
const openDetail = (row: InspectProblemRptVO) => {
  detailRef.value.open(row)
}
const handleDetailClose = () => {
  // 关闭抽屉后的处理
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectProblemRptApi.getInspectProblemRptPage(queryParams)
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
    await InspectProblemRptApi.deleteInspectProblemRpt(id)
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
    const data = await InspectProblemRptApi.exportInspectProblemRpt(queryParams)
    download.excel(data, '巡查巡检问题上报记录.xls')
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
}

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
}
</style>
