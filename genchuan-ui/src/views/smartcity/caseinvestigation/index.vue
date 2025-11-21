<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="案件ID" prop="caseId" label-width="100px">
        <el-input
          v-model="queryParams.caseId"
          placeholder="请输入案件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="调查结果" prop="investigationResult">
        <el-input
          v-model="queryParams.investigationResult"
          placeholder="请输入调查结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-600px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:case-investigation:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:case-investigation:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" align="center" prop="id" min-width="30px" />
      <el-table-column label="案件ID" align="center" prop="caseId" />
      <el-table-column label="调查负责人" align="center" prop="investigationLeader" />
      <el-table-column label="调查组成员" align="center" prop="investigationTeam" />
      <el-table-column
        label="调查开始时间"
        align="center"
        prop="investigationStartTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column
        label="调查结束时间"
        align="center"
        prop="investigationEndTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column label="现场勘查情况" align="center" prop="investigationDesc" />
      <el-table-column label="证据情况描述" align="center" prop="evidenceDesc" />
      <el-table-column label="证人证言描述" align="center" prop="testimonyDesc" />
      <el-table-column label="调查结果" align="center" prop="investigationResult" />
      <el-table-column label="处理建议" align="center" prop="treatmentSuggestion" />
      <!--<el-table-column-->
      <!--  label="创建时间"-->
      <!--  align="center"-->
      <!--  prop="createTime"-->
      <!--  :formatter="dateFormatter2"-->
      <!--  width="180px"-->
      <!--/>-->
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:case-investigation:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:case-investigation:delete']"
          >
            删除
          </el-button>
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
  <CaseInvestigationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { CaseInvestigationApi, CaseInvestigationVO } from '@/api/smartcity/caseinvestigation'
import CaseInvestigationForm from './CaseInvestigationForm.vue'

/** 案件调查 列表 */
defineOptions({ name: 'CaseInvestigation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CaseInvestigationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  caseId: undefined,
  investigationResult: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CaseInvestigationApi.getCaseInvestigationPage(queryParams)
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
    await CaseInvestigationApi.deleteCaseInvestigation(id)
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
    const data = await CaseInvestigationApi.exportCaseInvestigation(queryParams)
    download.excel(data, '案件调查.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>
