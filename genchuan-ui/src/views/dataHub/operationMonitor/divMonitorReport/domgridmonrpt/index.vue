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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
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
      <el-form-item label="网格类型" prop="gridType">
        <el-select
          v-model="queryParams.gridType"
          placeholder="请选择网格类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="报表ID" align="center" prop="rptId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划" align="center" prop="adminName" />
      <el-table-column label="网格类型" align="center" prop="gridType" />
      <el-table-column label="分域名称" align="center" prop="domName" />
      <el-table-column label="网格编码" align="center" prop="gridCode" />
      <el-table-column label="网格员" align="center" prop="gridUserName" />
      <el-table-column label="监测点位数量" align="center" prop="monPtCount" />
      <el-table-column label="设备在线率" align="center" prop="deviceOnlineRate" />
      <el-table-column label="事件总数" align="center" prop="evtTotalCount" />
      <el-table-column label="事件办结率" align="center" prop="evtCompleteRate" />
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
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
  <DomGridMonRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomGridMonRptDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomGridMonRptApi,
  DomGridMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domgridmonrpt'
import DomGridMonRptForm from './DomGridMonRptForm.vue'
import DomGridMonRptDetail from './DomGridMonRptDetail.vue'

/** 网格分域监测报表 列表 */
defineOptions({ name: 'DomGridMonRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomGridMonRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  gridType: undefined,
  domId: undefined,
  domName: undefined,
  gridCode: undefined,
  gridUserName: undefined,
  monPtCount: undefined,
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

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const openDetail = (row: DomGridMonRptVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DomGridMonRptApi.getDomGridMonRptPage(queryParams)
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
    await DomGridMonRptApi.deleteDomGridMonRpt(id)
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
    const data = await DomGridMonRptApi.exportDomGridMonRpt(queryParams)
    download.excel(data, '网格分域监测报表.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

::v-deep .el-table__header-wrapper {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  background-color: #f9fafb !important;
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-table tr:hover > td {
  background-color: #f7fafc !important;
}
</style>
