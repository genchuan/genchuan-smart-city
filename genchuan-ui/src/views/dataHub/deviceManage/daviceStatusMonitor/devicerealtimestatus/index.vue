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
      <el-form-item label="状态ID" prop="realTimeStatusId">
        <el-input
          v-model="queryParams.realTimeStatusId"
          placeholder="请输入状态ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备ID" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="运行状态" prop="runStatus">
        <el-input
          v-model="queryParams.runStatus"
          placeholder="请输入运行状态"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="最近上报时间" prop="lastRptTime">
        <el-date-picker
          v-model="queryParams.lastRptTime"
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
      <el-table-column label="状态ID" align="center" prop="realTimeStatusId" />
      <el-table-column label="设备ID" align="center" prop="deviceId" />
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="120" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="运行状态" align="center" prop="runStatus">
        <template #default="scope">
          <span :class="scope.row.runStatus === 'online' ? 'status-normal' : 'status-abnormal'">
            {{ scope.row.runStatus }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="最近上报时间"
        align="center"
        prop="lastRptTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="实时参数1" align="center" prop="realTimeParam1" />
      <el-table-column label="实时参数2" align="center" prop="realTimeParam2" />
      <el-table-column
        label="状态更新时间"
        align="center"
        prop="statusUpdateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="所属区域" align="center" prop="regionName" />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
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
  <DeviceRealTimeStatusForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceRealTimeStatusDetail
    ref="detailRef"
    :visible="detailVisible"
    :data="detailData"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceRealTimeStatusApi,
  DeviceRealTimeStatusVO
} from '@/api/dataHub/deviceManage/daviceStatusMonitor/devicerealtimestatus'
import DeviceRealTimeStatusForm from './DeviceRealTimeStatusForm.vue'
import DeviceRealTimeStatusDetail from './DeviceRealTimeStatusDetail.vue'

/** 设备实时状态 列表 */
defineOptions({ name: 'DeviceRealTimeStatus' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceRealTimeStatusVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  realTimeStatusId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  runStatus: undefined,
  lastRptTime: [],
  realTimeParam1: undefined,
  realTimeParam2: undefined,
  statusUpdateTime: [],
  regionCode: undefined,
  regionName: undefined,
  gridId: undefined,
  gridName: undefined,
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
const detailData = ref<DeviceRealTimeStatusVO | null>(null)

const openDetail = (row: DeviceRealTimeStatusVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceRealTimeStatusApi.getDeviceRealTimeStatusPage(queryParams)
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
    await DeviceRealTimeStatusApi.deleteDeviceRealTimeStatus(id)
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
    const data = await DeviceRealTimeStatusApi.exportDeviceRealTimeStatus(queryParams)
    download.excel(data, '设备实时状态.xls')
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

.status-normal {
  color: #4caf50;
  font-weight: 500;
}

.status-abnormal {
  color: #f44336;
  font-weight: 500;
}
</style>
