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
      <el-form-item label="报表ID" prop="rptId">
        <el-input
          v-model="queryParams.rptId"
          placeholder="请输入报表ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分析维度类型" prop="trendDimType">
        <el-select
          v-model="queryParams.trendDimType"
          placeholder="请选择分析维度类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="分析维度名称" prop="trendDimName">
        <el-input
          v-model="queryParams.trendDimName"
          placeholder="请输入分析维度名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="时间粒度" prop="timeGranularity">
        <el-input
          v-model="queryParams.timeGranularity"
          placeholder="请输入时间粒度"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="报表生成时间" prop="rptCreateTime">
        <el-date-picker
          v-model="queryParams.rptCreateTime"
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
      <el-table-column label="报表ID" align="center" prop="rptId" />
      <el-table-column label="分析维度类型" align="center" prop="trendDimType" />
      <el-table-column label="分析维度名称" align="center" prop="trendDimName" />
      <el-table-column label="时间粒度" align="center" prop="timeGranularity" />
      <el-table-column
        label="起始时间"
        align="center"
        prop="startTime"
        :formatter="dateFormatter"
        width="160"
      />
      <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        :formatter="dateFormatter"
        width="160"
      />
      <el-table-column label="整体变化率" align="center" prop="overallChangeRate" />
      <el-table-column label="趋势类型" align="center" prop="trendType" />
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="报表生成人" align="center" prop="rptCreateUser" />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看</el-button>
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
  <TrendAnalysisRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <TrendAnalysisRptDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  TrendAnalysisRptApi,
  TrendAnalysisRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/trendanalysisrpt'
import TrendAnalysisRptForm from './TrendAnalysisRptForm.vue'
import TrendAnalysisRptDetail from './TrendAnalysisRptDetail.vue'

/** 综合趋势分析报表 列表 */
defineOptions({ name: 'TrendAnalysisRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<TrendAnalysisRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  trendDimType: undefined,
  trendDimId: undefined,
  trendDimName: undefined,
  timeGranularity: undefined,
  startTime: [],
  endTime: [],
  cycle1Label: undefined,
  cycle1Value: undefined,
  cycle2Label: undefined,
  cycle2Value: undefined,
  overallChangeRate: undefined,
  trendType: undefined,
  rptCreateTime: [],
  rptCreateUser: undefined,
  rptRemark: undefined,
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
const openDetail = (row: TrendAnalysisRptVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await TrendAnalysisRptApi.getTrendAnalysisRptPage(queryParams)
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
    await TrendAnalysisRptApi.deleteTrendAnalysisRpt(id)
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
    const data = await TrendAnalysisRptApi.exportTrendAnalysisRpt(queryParams)
    download.excel(data, '综合趋势分析报表.xls')
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
.search-form {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
  margin-bottom: 15px !important;
}

.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table th {
  background-color: #f5f7fa;
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
