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
      <el-form-item label="关联ID" prop="deviceRelDeviceId">
        <el-input
          v-model="queryParams.deviceRelDeviceId"
          placeholder="请输入关联ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主设备名称" prop="mainDeviceName">
        <el-input
          v-model="queryParams.mainDeviceName"
          placeholder="请输入主设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="从设备名称" prop="slaveDeviceName">
        <el-input
          v-model="queryParams.slaveDeviceName"
          placeholder="请输入从设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联类型" prop="relType">
        <el-input
          v-model="queryParams.relType"
          placeholder="请输入关联类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联时间" prop="relTime">
        <el-date-picker
          v-model="queryParams.relTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="关联状态" prop="relStatus">
        <el-select
          v-model="queryParams.relStatus"
          placeholder="请选择关联状态"
          clearable
          class="!w-240px"
        >
          <el-option label="已关联" value="1" />
          <el-option label="未关联" value="0" />
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
      <el-table-column label="关联ID" align="center" prop="deviceRelDeviceId" width="120" />
      <el-table-column label="主设备信息" align="center" width="250">
        <template #default="scope">
          <div>
            <div>名称: {{ scope.row.mainDeviceName }}</div>
            <div>编码: {{ scope.row.mainDeviceCode }}</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="从设备信息" align="center" width="250">
        <template #default="scope">
          <div>
            <div>名称: {{ scope.row.slaveDeviceName }}</div>
            <div>编码: {{ scope.row.slaveDeviceCode }}</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="关联类型" align="center" prop="relType" />
      <el-table-column
        label="关联时间"
        align="center"
        prop="relTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="关联人" align="center" prop="relUser" />
      <el-table-column label="关联状态" align="center" prop="relStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.relStatus === '1' ? 'success' : 'danger'">
            {{ scope.row.relStatus === '1' ? '已关联' : '未关联' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="160px" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-2">
            查看
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DeviceDeviceForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceDeviceDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceDeviceApi,
  DeviceDeviceVO
} from '@/api/dataHub/deviceManage/deviceOpManage/devicedevice'
import DeviceDeviceForm from './DeviceDeviceForm.vue'
import DeviceDeviceDetailDrawer from './DeviceDeviceDetailDrawer.vue'

/** 设备关联设备 列表 */
defineOptions({ name: 'DeviceDevice' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceDeviceVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  deviceRelDeviceId: undefined,
  mainDeviceId: undefined,
  mainDeviceName: undefined,
  mainDeviceCode: undefined,
  slaveDeviceId: undefined,
  slaveDeviceName: undefined,
  slaveDeviceCode: undefined,
  relType: undefined,
  relTime: [],
  relUser: undefined,
  relStatus: undefined,
  adjustTime: [],
  adjustUser: undefined,
  adjustReason: undefined,
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
    const data = await DeviceDeviceApi.getDeviceDevicePage(queryParams)
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

/** 查看详情 */

/** 详情操作 */
const detailRef = ref()
const openDetail = (row: DeviceDeviceVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceDeviceApi.deleteDeviceDevice(id)
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
    const data = await DeviceDeviceApi.exportDeviceDevice(queryParams)
    download.excel(data, '设备关联设备.xls')
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
