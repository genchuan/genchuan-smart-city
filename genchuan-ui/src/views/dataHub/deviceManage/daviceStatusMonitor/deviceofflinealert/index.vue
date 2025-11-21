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
      <el-form-item label="预警ID" prop="offlineAlertId">
        <el-input
          v-model="queryParams.offlineAlertId"
          placeholder="请输入预警ID"
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
      <el-form-item label="离线开始时间" prop="offlineStartTime">
        <el-date-picker
          v-model="queryParams.offlineStartTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="预警状态" prop="alertStatus">
        <el-select
          v-model="queryParams.alertStatus"
          placeholder="请选择预警状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
      <el-table-column label="预警ID" align="center" prop="offlineAlertId" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column
        label="离线开始时间"
        align="center"
        prop="offlineStartTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="离线时长(分钟)" align="center" prop="offlineEndure" />
      <el-table-column label="预警级别" align="center" prop="alertLevel" />
      <el-table-column label="预警接收人" align="center" prop="alertUserName" />
      <el-table-column
        label="预警推送时间"
        align="center"
        prop="alertPushTime"
        :formatter="dateFormatter"
        width="180px"
      />

      <el-table-column label="预警状态" align="center" prop="alertStatus">
        <template #default="scope">
          <el-tag
            :type="scope.row.alertStatus === '已处理' ? 'success' : 'danger'"
            :disable-transitions="false"
          >
            {{ scope.row.alertStatus || '未设置' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="处理时间"
        align="center"
        prop="handleTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="处理结果" align="center" prop="handleResult" />
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DeviceOfflineAlertForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceOfflineAlertDetail
    ref="detailRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceOfflineAlertApi,
  DeviceOfflineAlertVO
} from '@/api/dataHub/deviceManage/daviceStatusMonitor/deviceofflinealert'
import DeviceOfflineAlertForm from './DeviceOfflineAlertForm.vue'
import DeviceOfflineAlertDetail from './DeviceOfflineAlertDetail.vue'

/** 设备离线预警 列表 */
defineOptions({ name: 'DeviceOfflineAlert' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceOfflineAlertVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  offlineAlertId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  offlineStartTime: [],
  offlineEndure: undefined,
  alertLevel: undefined,
  alertUserId: undefined,
  alertUserName: undefined,
  alertPushTime: [],
  alertStatus: undefined,
  handleUserId: undefined,
  handleUserName: undefined,
  handleTime: [],
  handleResult: undefined,
  regionCode: undefined,
  regionName: undefined,
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
const openDetail = (row: DeviceOfflineAlertVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceOfflineAlertApi.getDeviceOfflineAlertPage(queryParams)
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
    await DeviceOfflineAlertApi.deleteDeviceOfflineAlert(id)
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
    const data = await DeviceOfflineAlertApi.exportDeviceOfflineAlert(queryParams)
    download.excel(data, '设备离线预警.xls')
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
