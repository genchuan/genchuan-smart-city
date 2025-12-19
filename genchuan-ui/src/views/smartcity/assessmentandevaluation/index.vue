<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="养护人员" prop="maintenancePersonnelNumber" label-width="100px">
        <el-input
          v-model="queryParams.maintenancePersonnelNumber"
          placeholder="请输入养护人员编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="养护任务" prop="maintenanceTaskNumber">
        <el-input
          v-model="queryParams.maintenanceTaskNumber"
          placeholder="请输入养护任务编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="考核周期" prop="assessmentCycle">
        <el-input
          v-model="queryParams.assessmentCycle"
          placeholder="请输入考核周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:assessment-and-evaluation:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:assessment-and-evaluation:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="50px" />
      <el-table-column label="养护人员编号" align="center" prop="maintenancePersonnelNumber" />
      <el-table-column label="养护任务编号" align="center" prop="maintenanceTaskNumber" />
      <el-table-column label="考核周期" align="center" prop="assessmentCycle" />
      <el-table-column label="考核得分" align="center" prop="assessmentScore" />
      <el-table-column label="考核等级" align="center" prop="assessmentLevel" />
      <el-table-column label="评价意见" align="center" prop="evaluationOpinion" />
      <el-table-column label="改进建议" align="center" prop="improvementSuggestions" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:assessment-and-evaluation:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:assessment-and-evaluation:delete']"
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
  <AssessmentAndEvaluationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssessmentAndEvaluationApi, AssessmentAndEvaluationVO } from '@/api/smartcity/assessmentandevaluation'
import AssessmentAndEvaluationForm from './AssessmentAndEvaluationForm.vue'

/** 养护考核评价 列表 */
defineOptions({ name: 'AssessmentAndEvaluation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssessmentAndEvaluationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  maintenancePersonnelNumber: undefined,
  maintenanceTaskNumber: undefined,
  assessmentCycle: undefined,
  assessmentScore: undefined,
  assessmentLevel: undefined,
  evaluationOpinion: undefined,
  improvementSuggestions: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssessmentAndEvaluationApi.getAssessmentAndEvaluationPage(queryParams)
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
    await AssessmentAndEvaluationApi.deleteAssessmentAndEvaluation(id)
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
    const data = await AssessmentAndEvaluationApi.exportAssessmentAndEvaluation(queryParams)
    download.excel(data, '养护考核评价.xls')
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
