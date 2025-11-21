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
      <el-form-item label="统计ID" prop="statAnalysisId">
        <el-input
          v-model="queryParams.statAnalysisId"
          placeholder="请输入统计ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input
          v-model="queryParams.statCycleName"
          placeholder="请输入统计周期名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件大类ID" prop="compMajorId">
        <el-input
          v-model="queryParams.compMajorId"
          placeholder="请输入部件大类ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件大类名称" prop="compMajorName">
        <el-input
          v-model="queryParams.compMajorName"
          placeholder="请输入部件大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件小类ID" prop="compMinorId">
        <el-input
          v-model="queryParams.compMinorId"
          placeholder="请输入部件小类ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件小类名称" prop="compMinorName">
        <el-input
          v-model="queryParams.compMinorName"
          placeholder="请输入部件小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件总存量" prop="totalCompStock">
        <el-input
          v-model="queryParams.totalCompStock"
          placeholder="请输入部件总存量"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="损坏部件数" prop="damagedCompCount">
        <el-input
          v-model="queryParams.damagedCompCount"
          placeholder="请输入损坏部件数"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件损坏率" prop="compDamageRate">
        <el-input
          v-model="queryParams.compDamageRate"
          placeholder="请输入部件损坏率"
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
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:analysis-mng-comp:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:analysis-mng-comp:export']"
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
      <el-table-column label="统计ID" align="center" prop="statAnalysisId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="部件大类ID" align="center" prop="compMajorId" />
      <el-table-column label="部件大类名称" align="center" prop="compMajorName" />
      <el-table-column label="部件小类ID" align="center" prop="compMinorId" />
      <el-table-column label="部件小类名称" align="center" prop="compMinorName" />
      <el-table-column label="部件总存量" align="center" prop="totalCompStock" />
      <el-table-column label="损坏部件数" align="center" prop="damagedCompCount" />
      <el-table-column label="部件损坏率" align="center" prop="compDamageRate" />
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
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:analysis-mng-comp:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:analysis-mng-comp:delete']"
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
  <AnalysisMngCompForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AnalysisMngCompApi, AnalysisMngCompVO } from '@/api/dataHub/analysisDecision/analysismngcomp'
import AnalysisMngCompForm from './AnalysisMngCompForm.vue'

/** 按管理部件分析研判统计 列表 */
defineOptions({ name: 'AnalysisMngComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AnalysisMngCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statAnalysisId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalCompStock: undefined,
  damagedCompCount: undefined,
  compDamageRate: undefined,
  createTimeSys: [],
  updateTimeSys: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AnalysisMngCompApi.getAnalysisMngCompPage(queryParams)
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
    await AnalysisMngCompApi.deleteAnalysisMngComp(id)
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
    const data = await AnalysisMngCompApi.exportAnalysisMngComp(queryParams)
    download.excel(data, '按管理部件分析研判统计.xls')
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
