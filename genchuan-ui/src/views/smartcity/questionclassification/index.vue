<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="所属领域" prop="isArea" label-width="100px">
        <el-input
          v-model="queryParams.isArea"
          placeholder="请输入所属领域"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="问题类型" prop="questionType">
        <el-select
          v-model="queryParams.questionType"
          placeholder="请选择问题类型"
          clearable
          class="!w-240px"
          @change="handleQuery"
        >
        <el-option label="系统操作类" value="systemOperation" />
        <el-option label="现场实操类" value="onSiteOperation" />
        <el-option label="流程管理类" value="processManagement" />
        <el-option label="资源需求类" value="resourceRequirement" />
        <el-option label="政策咨询类" value="policyConsultation" />
        <el-option label="其他问题" value="other" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:question-classification:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:question-classification:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="所属领域" align="center" prop="isArea" />
      <el-table-column label="问题类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.questionType === 'systemOperation'">系统操作类</template>
          <template v-else-if="scope.row.questionType === 'onSiteOperation'">现场实操类</template>
          <template v-else-if="scope.row.questionType === 'processManagement'">流程管理类</template>
          <template v-else-if="scope.row.questionType === 'resourceRequirement'">资源需求类</template>
          <template v-else-if="scope.row.questionType === 'policyConsultation'">政策咨询类</template>
          <template v-else-if="scope.row.questionType === 'other'">其他问题</template>
          <template v-else>未分类</template>
        </template>
      </el-table-column>
      <el-table-column label="紧急程度" align="center" prop="urgency" />
      <el-table-column label="涉及主体" align="center" prop="involvingTheSubject" />
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
            v-hasPermi="['smartcity:question-classification:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:question-classification:delete']"
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
  <QuestionClassificationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { QuestionClassificationApi, QuestionClassificationVO } from '@/api/smartcity/questionclassification'
import QuestionClassificationForm from './QuestionClassificationForm.vue'

/** 问题录入 列表 */
defineOptions({ name: 'QuestionClassification' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<QuestionClassificationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  isArea: undefined,
  questionType: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await QuestionClassificationApi.getQuestionClassificationPage(queryParams)
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
    await QuestionClassificationApi.deleteQuestionClassification(id)
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
    const data = await QuestionClassificationApi.exportQuestionClassification(queryParams)
    download.excel(data, '问题录入.xls')
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
