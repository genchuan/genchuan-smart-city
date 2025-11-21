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
      <el-form-item label="统计ID" prop="catStatId">
        <el-input
          v-model="queryParams.catStatId"
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
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input
          v-model="queryParams.regionName"
          placeholder="请输入行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="设备大类名称" prop="deviceMajorName">
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
  <ContentWrap class="table-container">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
      border
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="统计ID" align="center" prop="catStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划" align="center" width="200">
        <template #default="scope">
          {{ scope.row.regionName }}({{ scope.row.regionCode }})
        </template>
      </el-table-column>
      <el-table-column label="设备分类" align="center" width="200">
        <template #default="scope">
          {{ scope.row.deviceMajorName }}-{{ scope.row.deviceMidName || '' }}
        </template>
      </el-table-column>
      <el-table-column label="设备总数(台)" align="center" prop="totalDeviceCount" />
      <el-table-column label="在线设备数(台)" align="center" prop="onlineDeviceCount">
        <template #default="scope">
          <span class="text-green-500">{{ scope.row.onlineDeviceCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="故障设备数(台)" align="center" prop="faultDeviceCount">
        <template #default="scope">
          <span class="text-red-500">{{ scope.row.faultDeviceCount }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="统计人" align="center" prop="statUser" />
      <el-table-column label="操作" align="center" min-width="150px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-5px">
            详情
          </el-button>
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
  <DeviceCatRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceCatRptDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceCatRptApi,
  DeviceCatRptVO
} from '@/api/dataHub/deviceManage/deviceStatisticsReport/devicecatrpt'
import DeviceCatRptForm from './DeviceCatRptForm.vue'
import DeviceCatRptDetail from './DeviceCatRptDetail.vue'

/** 设备分类统计 列表 */
defineOptions({ name: 'DeviceCatRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceCatRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  catStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMidId: undefined,
  deviceMidName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  totalDeviceCount: undefined,
  onlineDeviceCount: undefined,
  offlineDeviceCount: undefined,
  faultDeviceCount: undefined,
  newDeviceCount: undefined,
  scrappedDeviceCount: undefined,
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

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const openDetail = (row: DeviceCatRptVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceCatRptApi.getDeviceCatRptPage(queryParams)
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
    await DeviceCatRptApi.deleteDeviceCatRpt(id)
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
    const data = await DeviceCatRptApi.exportDeviceCatRpt(queryParams)
    download.excel(data, '设备分类统计.xls')
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

.table-container {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.custom-table {
  margin-bottom: 15px;
}

.pagination {
  text-align: right;
  margin-top: 15px;
}

::v-deep .el-table th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
