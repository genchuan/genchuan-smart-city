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
      <el-form-item label="评估ID" prop="effectEvalId">
        <el-input
          v-model="queryParams.effectEvalId"
          placeholder="请输入评估ID"
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
      <el-form-item label="实施周期" prop="implementationCycle">
        <el-input
          v-model="queryParams.implementationCycle"
          placeholder="请输入实施周期"
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
      <el-form-item label="实际效果" prop="actualEffect">
        <el-input
          v-model="queryParams.actualEffect"
          placeholder="请输入实际效果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="效果达成率" prop="effectAchievementRate">
        <el-input
          v-model="queryParams.effectAchievementRate"
          placeholder="请输入效果达成率"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资源总投入" prop="resourceTotalInput">
        <el-input
          v-model="queryParams.resourceTotalInput"
          placeholder="请输入资源总投入"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="投入回报率" prop="inputReturnRate">
        <el-input
          v-model="queryParams.inputReturnRate"
          placeholder="请输入投入回报率"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="用户满意度" prop="userSatisfy">
        <el-input
          v-model="queryParams.userSatisfy"
          placeholder="请输入用户满意度"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估等级" prop="evalGrade">
        <el-input
          v-model="queryParams.evalGrade"
          placeholder="请输入评估等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="成功经验" prop="successExp">
        <el-input
          v-model="queryParams.successExp"
          placeholder="请输入成功经验"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="改进点" prop="improvePts">
        <el-input
          v-model="queryParams.improvePts"
          placeholder="请输入改进点"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估人" prop="evalUser">
        <el-input
          v-model="queryParams.evalUser"
          placeholder="请输入评估人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="queryParams.evalTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="评估报告附件" prop="evalRptAttach">
        <el-input
          v-model="queryParams.evalRptAttach"
          placeholder="请输入评估报告附件"
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
          v-hasPermi="['datacenter:decision-effect-eval:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:decision-effect-eval:export']"
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
      <el-table-column label="评估ID" align="center" prop="effectEvalId" />
      <el-table-column label="关联方案ID" align="center" prop="relSchemeId" />
      <el-table-column label="关联方案名称" align="center" prop="relSchemeName" />
      <el-table-column label="实施周期" align="center" prop="implementationCycle" />
      <el-table-column label="预期效果" align="center" prop="expectedEffect" />
      <el-table-column label="实际效果" align="center" prop="actualEffect" />
      <el-table-column label="效果达成率" align="center" prop="effectAchievementRate" />
      <el-table-column label="资源总投入" align="center" prop="resourceTotalInput" />
      <el-table-column label="投入回报率" align="center" prop="inputReturnRate" />
      <el-table-column label="用户满意度" align="center" prop="userSatisfy" />
      <el-table-column label="评估等级" align="center" prop="evalGrade" />
      <el-table-column label="成功经验" align="center" prop="successExp" />
      <el-table-column label="改进点" align="center" prop="improvePts" />
      <el-table-column label="评估人" align="center" prop="evalUser" />
      <el-table-column
        label="评估时间"
        align="center"
        prop="evalTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="评估报告附件" align="center" prop="evalRptAttach" />
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
            v-hasPermi="['datacenter:decision-effect-eval:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:decision-effect-eval:delete']"
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
  <DecisionEffectEvalForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DecisionEffectEvalApi, DecisionEffectEvalVO } from '@/api/dataHub/analysisDecision/decisioneffecteval'
import DecisionEffectEvalForm from './DecisionEffectEvalForm.vue'

/** 决策效果评估 列表 */
defineOptions({ name: 'DecisionEffectEval' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DecisionEffectEvalVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  effectEvalId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  implementationCycle: undefined,
  expectedEffect: undefined,
  actualEffect: undefined,
  effectAchievementRate: undefined,
  resourceTotalInput: undefined,
  inputReturnRate: undefined,
  userSatisfy: undefined,
  evalGrade: undefined,
  successExp: undefined,
  improvePts: undefined,
  evalUser: undefined,
  evalTime: [],
  evalRptAttach: undefined,
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
    const data = await DecisionEffectEvalApi.getDecisionEffectEvalPage(queryParams)
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
    await DecisionEffectEvalApi.deleteDecisionEffectEval(id)
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
    const data = await DecisionEffectEvalApi.exportDecisionEffectEval(queryParams)
    download.excel(data, '决策效果评估.xls')
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
