<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
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
      <el-form-item label="行政区划" prop="adminName">
        <el-input
          v-model="queryParams.adminName"
          placeholder="请输入行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input
          v-model="queryParams.domName"
          placeholder="请输入分域名称"
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
        <el-button @click="handleQuery" class="search-btn">
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
  <ContentWrap class="table-wrap">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="报表ID" align="center" prop="rptId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="100" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" width="140" />
      <el-table-column label="行政区划" align="center" width="160">
        <template #default="scope"> {{ scope.row.adminName }}({{ scope.row.adminCode }})</template>
      </el-table-column>
      <el-table-column label="行政区划层级" align="center" prop="adminLevel" width="120" />
      <el-table-column label="分域信息" align="center" width="160">
        <template #default="scope"> {{ scope.row.domName }}({{ scope.row.domId }})</template>
      </el-table-column>
      <el-table-column label="监测点位数量" align="center" prop="monPtCount" width="120" />
      <el-table-column label="设备总数" align="center" prop="deviceTotalCount" width="100" />
      <el-table-column label="设备在线率" align="center" prop="deviceOnlineRate" width="120">
        <template #default="scope">
          <span :class="scope.row.deviceOnlineRate > 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.deviceOnlineRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column label="事件总数" align="center" prop="evtTotalCount" width="100" />
      <el-table-column label="事件办结率" align="center" prop="evtCompleteRate" width="120">
        <template #default="scope">
          <span :class="scope.row.evtCompleteRate > 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.evtCompleteRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="报表生成人" align="center" prop="rptCreateUser" width="120" />
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DomAdminMonRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomAdminMonRptDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomAdminMonRptApi,
  DomAdminMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domadminmonrpt'
import DomAdminMonRptForm from './DomAdminMonRptForm.vue'
import DomAdminMonRptDetail from './DomAdminMonRptDetail.vue'

/** 行政区划分域监测报表 列表 */
defineOptions({ name: 'DomAdminMonRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomAdminMonRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  adminLevel: undefined,
  domId: undefined,
  domName: undefined,
  monPtCount: undefined,
  deviceTotalCount: undefined,
  deviceOnlineRate: undefined,
  evtTotalCount: undefined,
  evtCompleteRate: undefined,
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
    const data = await DomAdminMonRptApi.getDomAdminMonRptPage(queryParams)
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
const detailRef = ref()
const openDetail = (row: DomAdminMonRptVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomAdminMonRptApi.deleteDomAdminMonRpt(id)
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
    const data = await DomAdminMonRptApi.exportDomAdminMonRpt(queryParams)
    download.excel(data, '行政区划分域监测报表.xls')
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

.search-btn {
  margin-right: 8px;
}

.table-wrap {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.custom-table {
  border-radius: 6px;
  overflow: hidden;
}

.pagination {
  margin-top: 15px;
  text-align: right;
}

::v-deep .el-table th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #fafafa !important;
}

.text-green-500 {
  color: #52c41a !important;
}

.text-orange-500 {
  color: #fa8c16 !important;
}
</style>
