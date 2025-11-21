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
      <el-form-item label="对比ID" prop="simCompareId">
        <el-input
          v-model="queryParams.simCompareId"
          placeholder="请输入对比ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="对比名称" prop="compareName">
        <el-input
          v-model="queryParams.compareName"
          placeholder="请输入对比名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="参与方案IDs" prop="participateSchemeIds">
        <el-input
          v-model="queryParams.participateSchemeIds"
          placeholder="请输入参与方案IDs"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="参与方案名称" prop="participateSchemeNames">
        <el-input
          v-model="queryParams.participateSchemeNames"
          placeholder="请输入参与方案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资源投入权重" prop="resInputWeight">
        <el-input
          v-model="queryParams.resInputWeight"
          placeholder="请输入资源投入权重"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预期效果权重" prop="expectedEffectWeight">
        <el-input
          v-model="queryParams.expectedEffectWeight"
          placeholder="请输入预期效果权重"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="实施周期权重" prop="implementationCycleWeight">
        <el-input
          v-model="queryParams.implementationCycleWeight"
          placeholder="请输入实施周期权重"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案1_ID" prop="scheme1Id">
        <el-input
          v-model="queryParams.scheme1Id"
          placeholder="请输入方案1_ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案1_资源投入得分" prop="scheme1ResScore">
        <el-input
          v-model="queryParams.scheme1ResScore"
          placeholder="请输入方案1_资源投入得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案1_预期效果得分" prop="scheme1EffectScore">
        <el-input
          v-model="queryParams.scheme1EffectScore"
          placeholder="请输入方案1_预期效果得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案1_周期得分" prop="scheme1CycleScore">
        <el-input
          v-model="queryParams.scheme1CycleScore"
          placeholder="请输入方案1_周期得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案1_综合得分" prop="scheme1CompositeScore">
        <el-input
          v-model="queryParams.scheme1CompositeScore"
          placeholder="请输入方案1_综合得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案2_ID" prop="scheme2Id">
        <el-input
          v-model="queryParams.scheme2Id"
          placeholder="请输入方案2_ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案2_资源投入得分" prop="scheme2ResScore">
        <el-input
          v-model="queryParams.scheme2ResScore"
          placeholder="请输入方案2_资源投入得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案2_预期效果得分" prop="scheme2EffectScore">
        <el-input
          v-model="queryParams.scheme2EffectScore"
          placeholder="请输入方案2_预期效果得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案2_周期得分" prop="scheme2CycleScore">
        <el-input
          v-model="queryParams.scheme2CycleScore"
          placeholder="请输入方案2_周期得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="方案2_综合得分" prop="scheme2CompositeScore">
        <el-input
          v-model="queryParams.scheme2CompositeScore"
          placeholder="请输入方案2_综合得分"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="对比结论" prop="compareConclusion">
        <el-input
          v-model="queryParams.compareConclusion"
          placeholder="请输入对比结论"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="对比人" prop="compareUser">
        <el-input
          v-model="queryParams.compareUser"
          placeholder="请输入对比人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="对比时间" prop="compareTime">
        <el-date-picker
          v-model="queryParams.compareTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
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
          v-hasPermi="['datacenter:scheme-sim-compare:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:scheme-sim-compare:export']"
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
      <el-table-column label="对比ID" align="center" prop="simCompareId" />
      <el-table-column label="对比名称" align="center" prop="compareName" />
      <el-table-column label="参与方案IDs" align="center" prop="participateSchemeIds" />
      <el-table-column label="参与方案名称" align="center" prop="participateSchemeNames" />
      <el-table-column label="资源投入权重" align="center" prop="resInputWeight" />
      <el-table-column label="预期效果权重" align="center" prop="expectedEffectWeight" />
      <el-table-column label="实施周期权重" align="center" prop="implementationCycleWeight" />
      <el-table-column label="方案1_ID" align="center" prop="scheme1Id" />
      <el-table-column label="方案1_资源投入得分" align="center" prop="scheme1ResScore" />
      <el-table-column label="方案1_预期效果得分" align="center" prop="scheme1EffectScore" />
      <el-table-column label="方案1_周期得分" align="center" prop="scheme1CycleScore" />
      <el-table-column label="方案1_综合得分" align="center" prop="scheme1CompositeScore" />
      <el-table-column label="方案2_ID" align="center" prop="scheme2Id" />
      <el-table-column label="方案2_资源投入得分" align="center" prop="scheme2ResScore" />
      <el-table-column label="方案2_预期效果得分" align="center" prop="scheme2EffectScore" />
      <el-table-column label="方案2_周期得分" align="center" prop="scheme2CycleScore" />
      <el-table-column label="方案2_综合得分" align="center" prop="scheme2CompositeScore" />
      <el-table-column label="对比结论" align="center" prop="compareConclusion" />
      <el-table-column label="对比人" align="center" prop="compareUser" />
      <el-table-column
        label="对比时间"
        align="center"
        prop="compareTime"
        :formatter="dateFormatter"
        width="180px"
      />
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
            v-hasPermi="['datacenter:scheme-sim-compare:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:scheme-sim-compare:delete']"
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
  <SchemeSimCompareForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { SchemeSimCompareApi, SchemeSimCompareVO } from '@/api/dataHub/analysisDecision/schemesimcompare'
import SchemeSimCompareForm from './SchemeSimCompareForm.vue'

/** 方案模拟对比统计 列表 */
defineOptions({ name: 'SchemeSimCompare' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<SchemeSimCompareVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  simCompareId: undefined,
  compareName: undefined,
  participateSchemeIds: undefined,
  participateSchemeNames: undefined,
  resInputWeight: undefined,
  expectedEffectWeight: undefined,
  implementationCycleWeight: undefined,
  scheme1Id: undefined,
  scheme1ResScore: undefined,
  scheme1EffectScore: undefined,
  scheme1CycleScore: undefined,
  scheme1CompositeScore: undefined,
  scheme2Id: undefined,
  scheme2ResScore: undefined,
  scheme2EffectScore: undefined,
  scheme2CycleScore: undefined,
  scheme2CompositeScore: undefined,
  compareConclusion: undefined,
  compareUser: undefined,
  compareTime: [],
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
    const data = await SchemeSimCompareApi.getSchemeSimComparePage(queryParams)
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
    await SchemeSimCompareApi.deleteSchemeSimCompare(id)
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
    const data = await SchemeSimCompareApi.exportSchemeSimCompare(queryParams)
    download.excel(data, '方案模拟对比统计.xls')
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
