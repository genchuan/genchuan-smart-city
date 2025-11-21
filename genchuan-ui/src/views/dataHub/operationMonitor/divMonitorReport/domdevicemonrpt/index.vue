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
      <el-form-item label="设备大类" prop="deviceMajorName">
        <el-input
          v-model="queryParams.deviceMajorName"
          placeholder="请输入设备大类名称"
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
      <el-table-column label="报表ID" align="center" prop="rptId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="100" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" width="140" />
      <el-table-column label="行政区划" align="center" prop="adminName" width="140" />
      <el-table-column label="设备大类" align="center" prop="deviceMajorName" width="140" />
      <el-table-column label="设备小类" align="center" prop="deviceMinorName" width="140" />
      <el-table-column label="分域名称" align="center" prop="domName" width="140" />
      <el-table-column label="设备总数" align="center" prop="totalDeviceCount" width="100" />
      <el-table-column label="在线率" align="center" prop="deviceOnlineRate" width="100">
        <template #default="scope">
          <span :class="scope.row.deviceOnlineRate >= 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.deviceOnlineRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column label="数据达标率" align="center" prop="uploadQualifiedRate" width="120">
        <template #default="scope">
          <span :class="scope.row.uploadQualifiedRate >= 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.uploadQualifiedRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column label="异常次数" align="center" prop="deviceAbnCount" width="100">
        <template #default="scope">
          <span :class="scope.row.deviceAbnCount > 0 ? 'text-red-500' : 'text-green-500'">
            {{ scope.row.deviceAbnCount }}
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
  <DomDeviceMonRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomDeviceMonRptDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDeviceMonRptApi,
  DomDeviceMonRptVO
} from '@/api/dataHub/operationMonitor/divMonitorReport/domdevicemonrpt'
import DomDeviceMonRptForm from './DomDeviceMonRptForm.vue'
import DomDeviceMonRptDetail from './DomDeviceMonRptDetail.vue'

/** 设备分域监测报表 列表 */
defineOptions({ name: 'DomDeviceMonRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDeviceMonRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  domId: undefined,
  domName: undefined,
  totalDeviceCount: undefined,
  deviceOnlineRate: undefined,
  totalUploadFreq: undefined,
  uploadQualifiedRate: undefined,
  deviceAbnCount: undefined,
  firmwareUpdatedCount: undefined,
  firmwareUpdateRate: undefined,
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
    const data = await DomDeviceMonRptApi.getDomDeviceMonRptPage(queryParams)
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
const openDetail = (row: DomDeviceMonRptVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomDeviceMonRptApi.deleteDomDeviceMonRpt(id)
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
    const data = await DomDeviceMonRptApi.exportDomDeviceMonRpt(queryParams)
    download.excel(data, '设备分域监测报表.xls')
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

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 600;
  color: #333;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}

::v-deep .el-table--striped .el-table__row--striped td {
  background-color: #fafafa;
}
</style>
