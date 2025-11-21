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
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="对比维度类型" prop="compareDimType">
        <el-select
          v-model="queryParams.compareDimType"
          placeholder="请选择对比维度类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="统计指标" prop="statIdx">
        <el-input
          v-model="queryParams.statIdx"
          placeholder="请输入统计指标"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
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
  <ContentWrap class="table-content">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="报表ID" align="center" prop="rptId" />
      <el-table-column label="对比维度类型" align="center" prop="compareDimType" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="统计指标" align="center" prop="statIdx" />
      <el-table-column label="对比对象1" align="center">
        <template #default="scope">{{ scope.row.compareObj1Name }}</template>
      </el-table-column>
      <el-table-column label="对比对象2" align="center">
        <template #default="scope">{{ scope.row.compareObj2Name }}</template>
      </el-table-column>
      <el-table-column label="平均值" align="center" prop="avgValue" />
      <el-table-column label="最大差异率" align="center" prop="maxDiffRate" />
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)"> 查看</el-button>
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <CompareAnalysisRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <CompareAnalysisRptDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  CompareAnalysisRptApi,
  CompareAnalysisRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/compareanalysisrpt'
import CompareAnalysisRptForm from './CompareAnalysisRptForm.vue'
import CompareAnalysisRptDetail from './CompareAnalysisRptDetail.vue'

/** 综合对比分析报表 列表 */
defineOptions({ name: 'CompareAnalysisRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CompareAnalysisRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  compareDimType: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  statIdx: undefined,
  compareObj1Id: undefined,
  compareObj1Name: undefined,
  compareObj1Value: undefined,
  compareObj2Id: undefined,
  compareObj2Name: undefined,
  compareObj2Value: undefined,
  avgValue: undefined,
  maxObjId: undefined,
  maxObjName: undefined,
  maxValue: undefined,
  minObjId: undefined,
  minObjName: undefined,
  minValue: undefined,
  maxDiffValue: undefined,
  maxDiffRate: undefined,
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

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CompareAnalysisRptApi.getCompareAnalysisRptPage(queryParams)
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
const detailRef = ref()
const openDetail = async (id: number) => {
  detailRef.value.open(id)
}

const handleDetailClose = () => {
  // 可以在这里处理详情关闭后的逻辑
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await CompareAnalysisRptApi.deleteCompareAnalysisRpt(id)
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
    const data = await CompareAnalysisRptApi.exportCompareAnalysisRpt(queryParams)
    download.excel(data, '综合对比分析报表.xls')
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
  background-color: #f9fafb;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 15px !important;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.table-content {
  background-color: #fff;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.custom-table {
  border-radius: 6px;
  overflow: hidden;
}

.pagination {
  margin-top: 15px;
  text-align: right;
}
</style>
