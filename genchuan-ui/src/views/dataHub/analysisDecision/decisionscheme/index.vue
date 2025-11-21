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
      <el-form-item label="方案ID" prop="decisionSchemeId">
        <el-input
          v-model="queryParams.decisionSchemeId"
          placeholder="请输入方案ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案名称" prop="schemeName">
        <el-input
          v-model="queryParams.schemeName"
          placeholder="请输入方案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联分析ID" prop="relAnalysisId">
        <el-input
          v-model="queryParams.relAnalysisId"
          placeholder="请输入关联分析ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联分析类型" prop="relAnalysisType">
        <el-select
          v-model="queryParams.relAnalysisType"
          placeholder="请选择关联分析类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="方案目标" prop="schemeGoal">
        <el-input
          v-model="queryParams.schemeGoal"
          placeholder="请输入方案目标"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="实施措施" prop="implementationMeasures">
        <el-input
          v-model="queryParams.implementationMeasures"
          placeholder="请输入实施措施"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资源需求" prop="resDemand">
        <el-input
          v-model="queryParams.resDemand"
          placeholder="请输入资源需求"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预期效果" prop="expectedEffect">
        <el-input
          v-model="queryParams.expectedEffect"
          placeholder="请输入预期效果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案状态" prop="schemeStatus">
        <el-select
          v-model="queryParams.schemeStatus"
          placeholder="请选择方案状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请人ID" prop="applyUserId">
        <el-input
          v-model="queryParams.applyUserId"
          placeholder="请输入申请人ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="申请人姓名" prop="applyUserName">
        <el-input
          v-model="queryParams.applyUserName"
          placeholder="请输入申请人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker
          v-model="queryParams.applyTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input
          v-model="queryParams.auditUserId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="审核人姓名" prop="auditUserName">
        <el-input
          v-model="queryParams.auditUserName"
          placeholder="请输入审核人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker
          v-model="queryParams.auditTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="审核意见" prop="auditOpinion">
        <el-input
          v-model="queryParams.auditOpinion"
          placeholder="请输入审核意见"
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
          v-hasPermi="['datacenter:decision-scheme:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:decision-scheme:export']"
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
      <el-table-column label="方案ID" align="center" prop="decisionSchemeId" />
      <el-table-column label="方案名称" align="center" prop="schemeName" />
      <el-table-column label="关联分析ID" align="center" prop="relAnalysisId" />
      <el-table-column label="关联分析类型" align="center" prop="relAnalysisType" />
      <el-table-column label="方案目标" align="center" prop="schemeGoal" />
      <el-table-column label="实施措施" align="center" prop="implementationMeasures" />
      <el-table-column label="资源需求" align="center" prop="resDemand" />
      <el-table-column label="预期效果" align="center" prop="expectedEffect" />
      <el-table-column label="方案状态" align="center" prop="schemeStatus" />
      <el-table-column label="申请人ID" align="center" prop="applyUserId" />
      <el-table-column label="申请人姓名" align="center" prop="applyUserName" />
      <el-table-column
        label="申请时间"
        align="center"
        prop="applyTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="审核人ID" align="center" prop="auditUserId" />
      <el-table-column label="审核人姓名" align="center" prop="auditUserName" />
      <el-table-column
        label="审核时间"
        align="center"
        prop="auditTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="审核意见" align="center" prop="auditOpinion" />
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
            v-hasPermi="['datacenter:decision-scheme:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:decision-scheme:delete']"
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
  <DecisionSchemeForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DecisionSchemeApi, DecisionSchemeVO } from '@/api/dataHub/analysisDecision/decisionscheme'
import DecisionSchemeForm from './DecisionSchemeForm.vue'

/** 决策方案生成 列表 */
defineOptions({ name: 'DecisionScheme' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DecisionSchemeVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  decisionSchemeId: undefined,
  schemeName: undefined,
  relAnalysisId: undefined,
  relAnalysisType: undefined,
  schemeGoal: undefined,
  implementationMeasures: undefined,
  resDemand: undefined,
  expectedEffect: undefined,
  schemeStatus: undefined,
  applyUserId: undefined,
  applyUserName: undefined,
  applyTime: [],
  auditUserId: undefined,
  auditUserName: undefined,
  auditTime: [],
  auditOpinion: undefined,
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
    const data = await DecisionSchemeApi.getDecisionSchemePage(queryParams)
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
    await DecisionSchemeApi.deleteDecisionScheme(id)
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
    const data = await DecisionSchemeApi.exportDecisionScheme(queryParams)
    download.excel(data, '决策方案生成.xls')
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
