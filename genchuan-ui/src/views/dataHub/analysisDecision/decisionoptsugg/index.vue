<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="建议ID" prop="optSuggId">
        <el-input
          v-model="queryParams.optSuggId"
          placeholder="请输入建议ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联评估ID" prop="relEvalId">
        <el-input
          v-model="queryParams.relEvalId"
          placeholder="请输入关联评估ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input
          v-model="queryParams.relSchemeId"
          placeholder="请输入关联方案ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input
          v-model="queryParams.relSchemeName"
          placeholder="请输入关联方案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="建议类型" prop="suggType">
        <el-select
          v-model="queryParams.suggType"
          placeholder="请选择建议类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="参考案例ID" prop="refCaseId">
        <el-input
          v-model="queryParams.refCaseId"
          placeholder="请输入参考案例ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="参考案例名称" prop="refCaseName">
        <el-input
          v-model="queryParams.refCaseName"
          placeholder="请输入参考案例名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="建议优先级" prop="suggPriority">
        <el-input
          v-model="queryParams.suggPriority"
          placeholder="请输入建议优先级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="建议状态" prop="suggStatus">
        <el-select
          v-model="queryParams.suggStatus"
          placeholder="请选择建议状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="提出人" prop="proposer">
        <el-input
          v-model="queryParams.proposer"
          placeholder="请输入提出人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="提出人姓名" prop="proposerName">
        <el-input
          v-model="queryParams.proposerName"
          placeholder="请输入提出人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="提出时间" prop="proposeTime">
        <el-date-picker
          v-model="queryParams.proposeTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="处理人" prop="handler">
        <el-input
          v-model="queryParams.handler"
          placeholder="请输入处理人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker
          v-model="queryParams.handleTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="处理意见" prop="handleOpinion">
        <el-input
          v-model="queryParams.handleOpinion"
          placeholder="请输入处理意见"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input
          v-model="queryParams.extCat1"
          placeholder="请输入分类扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input
          v-model="queryParams.extCat2"
          placeholder="请输入分类扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input
          v-model="queryParams.extCommon1"
          placeholder="请输入通用扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input
          v-model="queryParams.extCommon2"
          placeholder="请输入通用扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="queryParams.createTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统创建时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="queryParams.updateTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统更新时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:decision-opt-sugg:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:decision-opt-sugg:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="建议ID" align="center" prop="optSuggId" />
      <el-table-column label="关联评估ID" align="center" prop="relEvalId" />
      <el-table-column label="关联方案ID" align="center" prop="relSchemeId" />
      <el-table-column label="关联方案名称" align="center" prop="relSchemeName" />
      <el-table-column label="建议类型" align="center" prop="suggType" />
      <el-table-column label="建议内容" align="center" prop="suggContent" />
      <el-table-column label="参考案例ID" align="center" prop="refCaseId" />
      <el-table-column label="参考案例名称" align="center" prop="refCaseName" />
      <el-table-column label="建议优先级" align="center" prop="suggPriority" />
      <el-table-column label="建议状态" align="center" prop="suggStatus" />
      <el-table-column label="提出人" align="center" prop="proposer" />
      <el-table-column label="提出人姓名" align="center" prop="proposerName" />
      <el-table-column
        label="提出时间"
        align="center"
        prop="proposeTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="处理人" align="center" prop="handler" />
      <el-table-column
        label="处理时间"
        align="center"
        prop="handleTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="处理意见" align="center" prop="handleOpinion" />
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:decision-opt-sugg:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:decision-opt-sugg:delete']"
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
  <DecisionOptSuggForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DecisionOptSuggApi, DecisionOptSuggVO } from '@/api/dataHub/analysisDecision/decisionoptsugg'
import DecisionOptSuggForm from './DecisionOptSuggForm.vue'

/** 决策优化建议 列表 */
defineOptions({ name: 'DecisionOptSugg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DecisionOptSuggVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  optSuggId: undefined,
  relEvalId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  suggType: undefined,
  suggContent: undefined,
  refCaseId: undefined,
  refCaseName: undefined,
  suggPriority: undefined,
  suggStatus: undefined,
  proposer: undefined,
  proposerName: undefined,
  proposeTime: [],
  handler: undefined,
  handleTime: [],
  handleOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DecisionOptSuggApi.getDecisionOptSuggPage(queryParams)
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
    await DecisionOptSuggApi.deleteDecisionOptSugg(id)
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
    const data = await DecisionOptSuggApi.exportDecisionOptSugg(queryParams)
    download.excel(data, '决策优化建议.xls')
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
