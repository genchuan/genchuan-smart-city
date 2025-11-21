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
      <el-form-item label="问卷ID" prop="questionnaireId">
        <el-input
          v-model="queryParams.questionnaireId"
          placeholder="请输入问卷ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="问卷名称" prop="questionnaireName">
        <el-input
          v-model="queryParams.questionnaireName"
          placeholder="请输入问卷名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="queryParams.evalTaskName"
          placeholder="请输入关联任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="调查对象范围" prop="targetScope">
        <el-input
          v-model="queryParams.targetScope"
          placeholder="请输入调查对象范围"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
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
      <el-form-item label="问卷状态" prop="questionnaireStatus">
        <el-select
          v-model="queryParams.questionnaireStatus"
          placeholder="请选择问卷状态"
          clearable
          class="!w-240px"
        >
          <el-option label="已完成" value="COMPLETED" />
          <el-option label="进行中" value="RUNNING" />
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
      class="!rounded-lg !shadow-sm"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80px" />
      <el-table-column label="问卷ID" align="center" prop="questionnaireId" width="120px" />
      <el-table-column label="问卷名称" align="center" prop="questionnaireName" width="180px" />
      <el-table-column label="问卷编码" align="center" prop="questionnaireCode" width="120px" />
      <el-table-column label="关联任务名称" align="center" prop="evalTaskName" width="150px" />
      <el-table-column label="调查对象范围" align="center" prop="targetScope" width="150px" />
      <el-table-column label="发放方式" align="center" prop="releaseMethod" width="100px" />
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="问卷状态" align="center" prop="questionnaireStatus" width="100px">
        <template #default="scope">
          <el-tag :type="scope.row.questionnaireStatus === 'COMPLETED' ? 'success' : 'warning'">
            {{ scope.row.questionnaireStatus === 'COMPLETED' ? '已完成' : '进行中' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="填写人数" align="center" prop="fillCount" width="80px" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      class="mt-10px"
    />
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <EvalQuestionnaireForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalQuestionnaireDetail v-model="detailVisible" :data="detailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalQuestionnaireApi,
  EvalQuestionnaireVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/qstnrMgmt'
import EvalQuestionnaireForm from './EvalQuestionnaireForm.vue'
import EvalQuestionnaireDetail from './EvalQuestionnaireDetail.vue'

/** 问卷调查管理 列表 */
defineOptions({ name: 'EvalQuestionnaire' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalQuestionnaireVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailVisible = ref(false) // 详情抽屉显示状态
const detailData = ref<EvalQuestionnaireVO>({} as EvalQuestionnaireVO) // 详情数据

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  questionnaireId: undefined,
  questionnaireName: undefined,
  questionnaireCode: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  targetScope: undefined,
  releaseMethod: undefined,
  questionnaireUrl: undefined,
  questionnaireQrcode: undefined,
  startTime: [],
  endTime: [],
  questionnaireStatus: undefined,
  fillCount: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalQuestionnaireApi.getEvalQuestionnairePage(queryParams)
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
const openDetail = (row: EvalQuestionnaireVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalQuestionnaireApi.deleteEvalQuestionnaire(id)
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
    const data = await EvalQuestionnaireApi.exportEvalQuestionnaire(queryParams)
    download.excel(data, '问卷调查管理.xls')
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
.el-table {
  border-radius: 8px !important;
  overflow: hidden;
}

.el-table__header-wrapper th {
  background-color: #f8fafc !important;
  font-weight: 500;
}

.el-table__body-wrapper tr:hover td {
  background-color: #fefeff !important;
}
</style>
