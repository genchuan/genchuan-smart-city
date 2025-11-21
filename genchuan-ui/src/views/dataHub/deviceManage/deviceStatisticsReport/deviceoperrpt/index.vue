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
      <!-- 保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="统计ID" prop="operStatId">
        <el-input
          v-model="queryParams.operStatId"
          placeholder="请输入统计ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
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
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input
          v-model="queryParams.statCycleName"
          placeholder="请输入统计周期名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="行政区划" prop="regionName">
        <el-input
          v-model="queryParams.regionName"
          placeholder="请输入行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="设备大类" prop="deviceMajorName">
        <el-input
          v-model="queryParams.deviceMajorName"
          placeholder="请输入设备大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="queryParams.statTime"
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
      <el-table-column label="统计ID" align="center" prop="operStatId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="100" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" width="140" />
      <el-table-column label="行政区划" align="center" prop="regionName" width="140" />
      <el-table-column label="设备大类" align="center" prop="deviceMajorName" width="140" />
      <el-table-column label="设备总数(台)" align="center" prop="totalDeviceCount" width="110" />
      <el-table-column label="平均在线数(台)" align="center" prop="avgOnlineCount" width="120" />
      <el-table-column label="设备在线率(%)" align="center" prop="deviceOnlineRate" width="120" />
      <el-table-column label="总预警数(条)" align="center" width="110">
        <template #default="scope">
          {{
            scope.row.generalAlertCount + scope.row.importantAlertCount + scope.row.urgentAlertCount
          }}
        </template>
      </el-table-column>
      <el-table-column label="已处理预警(条)" align="center" prop="handledAlertCount" width="120" />
      <el-table-column label="预警处置率(%)" align="center" prop="alertHandleRate" width="120" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="统计人" align="center" prop="statUser" width="100" />
      <el-table-column label="操作" align="center" min-width="160px">
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
  <DeviceOperRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceOperRptDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceOperRptApi,
  DeviceOperRptVO
} from '@/api/dataHub/deviceManage/deviceStatisticsReport/deviceoperrpt'
import DeviceOperRptForm from './DeviceOperRptForm.vue'
import DeviceOperRptDetail from './DeviceOperRptDetail.vue'

/** 设备运行状态统计 列表 */
defineOptions({ name: 'DeviceOperRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceOperRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  operStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  totalDeviceCount: undefined,
  avgOnlineCount: undefined,
  deviceOnlineRate: undefined,
  totalOfflineEndure: undefined,
  avgOfflineEndure: undefined,
  generalAlertCount: undefined,
  importantAlertCount: undefined,
  urgentAlertCount: undefined,
  handledAlertCount: undefined,
  alertHandleRate: undefined,
  statTime: [],
  statUser: undefined,
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
    const data = await DeviceOperRptApi.getDeviceOperRptPage(queryParams)
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
const openDetail = (row: DeviceOperRptVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceOperRptApi.deleteDeviceOperRpt(id)
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
    const data = await DeviceOperRptApi.exportDeviceOperRpt(queryParams)
    download.excel(data, '设备运行状态统计.xls')
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
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
