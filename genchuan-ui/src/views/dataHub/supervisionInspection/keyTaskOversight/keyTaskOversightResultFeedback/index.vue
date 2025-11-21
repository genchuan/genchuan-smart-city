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
      <!-- 保留常用搜索字段，隐藏次要字段 -->
      <el-form-item label="反馈ID" prop="feedbackId">
        <el-input
          v-model="queryParams.feedbackId"
          placeholder="请输入反馈ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="反馈编码" prop="feedbackCode">
        <el-input
          v-model="queryParams.feedbackCode"
          placeholder="请输入反馈编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联督办任务编码" prop="supvTaskCode">
        <el-input
          v-model="queryParams.supvTaskCode"
          placeholder="请输入关联督办任务编码"
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
      <el-form-item label="督办结果" prop="supvResult">
        <el-select
          v-model="queryParams.supvResult"
          placeholder="请选择督办结果"
          clearable
          class="!w-240px"
        >
          <el-option label="已完成" value="已完成" />
          <el-option label="部分完成" value="部分完成" />
          <el-option label="进行中" value="进行中" />
          <!--          <el-option label="进行中" value="进行中" />-->
          <!--          <el-option label="未通过" value="未通过" />-->
          <!--          <el-option label="已逾期" value="已逾期" />-->
        </el-select>
      </el-form-item>
      <el-form-item label="反馈人" prop="feedbackUser">
        <el-input
          v-model="queryParams.feedbackUser"
          placeholder="请输入反馈人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="反馈时间" prop="feedbackTime">
        <el-date-picker
          v-model="queryParams.feedbackTime"
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
      <el-table-column label="反馈ID" align="center" prop="feedbackId" />
      <el-table-column label="反馈编码" align="center" prop="feedbackCode" />
      <el-table-column label="关联督办任务编码" align="center" prop="supvTaskCode" />
      <el-table-column label="关联问题名称" align="center" prop="problemName" min-width="150" />
      <el-table-column label="督办结果" align="center" prop="supvResult">
        <template #default="scope">
          <el-tag :type="getSupvResultTagType(scope.row.supvResult)">
            {{ scope.row.supvResult || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="反馈人" align="center" prop="feedbackUser" />
      <el-table-column
        label="反馈时间"
        align="center"
        prop="feedbackTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="关联整改验收结果" align="center" prop="rectifyAcceptResult">
        <template #default="scope">
          <el-tag :type="getAcceptResultTagType(scope.row.rectifyAcceptResult)">
            {{ scope.row.rectifyAcceptResult || '-' }}
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
  <InspectSupvFeedbackForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectSupvFeedbackDetail ref="detailRef" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectSupvFeedbackApi,
  InspectSupvFeedbackVO
} from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightResultFeedback'
import InspectSupvFeedbackForm from './InspectSupvFeedbackForm.vue'
import InspectSupvFeedbackDetail from './InspectSupvFeedbackDetail.vue'

/** 巡查巡检督办结果反馈 列表 */
defineOptions({ name: 'InspectSupvFeedback' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectSupvFeedbackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  feedbackId: undefined,
  feedbackCode: undefined,
  supvTaskCode: undefined,
  problemName: undefined,
  supvResult: undefined,
  feedbackUser: undefined,
  feedbackTime: [],
  // 隐藏其他查询字段
  supvTaskId: undefined,
  supvResultDesc: undefined,
  supvFeedbackOpinion: undefined,
  rectifyAcceptResult: undefined,
  supvStatusChange: undefined,
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
const openDetail = (row: InspectSupvFeedbackVO) => {
  detailRef.value.open(row)
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectSupvFeedbackApi.getInspectSupvFeedbackPage(queryParams)
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
    await InspectSupvFeedbackApi.deleteInspectSupvFeedback(id)
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
    const data = await InspectSupvFeedbackApi.exportInspectSupvFeedback(queryParams)
    download.excel(data, '巡查巡检督办结果反馈.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 获取督办结果标签类型 */
const getSupvResultTagType = (result: string) => {
  switch (result) {
    case '已完成':
      return 'success'
    case '进行中':
      return 'warning'
    // case '未通过':
    //   return 'danger'
    // case '已逾期':
    //   return 'danger'
    case '部分完成':
      return 'danger'
    default:
      return 'info'
  }
}

/** 获取验收结果标签类型 */
const getAcceptResultTagType = (result: string) => {
  switch (result) {
    case '合格':
      return 'success'
    case '待改进':
      return 'danger'
    case '待定':
      return 'warning'
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
