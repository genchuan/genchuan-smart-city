<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="80px"
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
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="指标名称" prop="idxName">
        <el-input
          v-model="queryParams.idxName"
          placeholder="请输入指标名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="行政区划" prop="adminName">
        <el-input
          v-model="queryParams.adminName"
          placeholder="请输入行政区划名称"
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
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划" align="center" prop="adminName" />
      <el-table-column label="指标名称" align="center" prop="idxName" />
      <el-table-column label="指标单位" align="center" prop="idxUnit" width="90" />
      <el-table-column label="指标平均值" align="center" prop="idxAvg" />
      <el-table-column label="超标次数" align="center" prop="exceedCount">
        <template #default="scope">
          <span
            :class="
              scope.row.exceedCount && scope.row.exceedCount > 0 ? 'text-red-500 font-medium' : ''
            "
          >
            {{ scope.row.exceedCount || '-' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="环比改善率" align="center" prop="momImproveRate">
        <template #default="scope">
          <span
            :class="
              scope.row.momImproveRate && scope.row.momImproveRate > 0
                ? 'text-green-500 font-medium'
                : scope.row.momImproveRate && scope.row.momImproveRate < 0
                  ? 'text-red-500 font-medium'
                  : ''
            "
          >
            {{ scope.row.momImproveRate !== undefined ? scope.row.momImproveRate + '%' : '-' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDrawer(scope.row.id)"> 查看</el-button>
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <KeyIdxSummaryRptForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <KeyIdxSummaryRptDrawer ref="drawerRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  KeyIdxSummaryRptApi,
  KeyIdxSummaryRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/keyidxsummaryrpt'
import KeyIdxSummaryRptForm from './KeyIdxSummaryRptForm.vue'
import KeyIdxSummaryRptDrawer from './KeyIdxSummaryRptDrawer.vue'

/** 关键指标汇总报表 列表 */
defineOptions({ name: 'KeyIdxSummaryRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<KeyIdxSummaryRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  idxId: undefined,
  idxName: undefined,
  idxUnit: undefined,
  idxLevel: undefined,
  idxAvg: undefined,
  idxMax: undefined,
  exceedCount: undefined,
  momImproveRate: undefined,
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
    const data = await KeyIdxSummaryRptApi.getKeyIdxSummaryRptPage(queryParams)
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

/** 查看详情操作 */
const drawerRef = ref()
const openDrawer = (id: number) => {
  drawerRef.value.open(id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await KeyIdxSummaryRptApi.deleteKeyIdxSummaryRpt(id)
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
    const data = await KeyIdxSummaryRptApi.exportKeyIdxSummaryRpt(queryParams)
    download.excel(data, '关键指标汇总报表.xls')
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
.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

.el-table th {
  background-color: #f9fafb;
  font-weight: 500;
}

.text-red-500 {
  color: #ef4444;
}

.text-green-500 {
  color: #22c55e;
}

.font-medium {
  font-weight: 500;
}
</style>
