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
      <!-- 只保留常用搜索字段，隐藏部分不常用字段 -->
      <el-form-item label="设备ID" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="设备编码" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="坐标系类型" prop="coordSystem">
        <el-input
          v-model="queryParams.coordSystem"
          placeholder="请输入坐标系类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="录入时间" prop="inputTime">
        <el-date-picker
          v-model="queryParams.inputTime"
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
      <!-- 只保留常用显示字段 -->
      <el-table-column label="设备ID" align="center" prop="deviceId" min-width="120px" />
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="150px" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" min-width="120px" />
      <el-table-column label="坐标系类型" align="center" prop="coordSystem" min-width="120px" />
      <el-table-column label="坐标X(度)" align="center" prop="coordX" min-width="100px" />
      <el-table-column label="坐标Y(度)" align="center" prop="coordY" min-width="100px" />
      <el-table-column
        label="定位精度(米)"
        align="center"
        prop="positionAccuracy"
        min-width="120px"
      />
      <el-table-column
        label="录入时间"
        align="center"
        prop="inputTime"
        :formatter="dateFormatter"
        min-width="160px"
      />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        min-width="160px"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DeviceSpatialDataForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceSpatialDataDetail
    ref="detailRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceSpatialDataApi,
  DeviceSpatialDataVO
} from '@/api/dataHub/deviceManage/deviceDataManage/devicespatialdata'
import DeviceSpatialDataForm from './DeviceSpatialDataForm.vue'
import DeviceSpatialDataDetail from './DeviceSpatialDataDetail.vue'

/** 设备空间数据 列表 */
defineOptions({ name: 'DeviceSpatialData' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceSpatialDataVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  deviceSpatialId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  coordSystem: undefined,
  coordX: undefined,
  coordY: undefined,
  elevation: undefined,
  positionAccuracy: undefined,
  spatialDataFormat: undefined,
  dataSource: undefined,
  inputTime: [],
  inputUser: undefined,
  updateTimeSys: undefined,
  updateUser: undefined,
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
const openDetail = (row: DeviceSpatialDataVO) => {
  detailVisible.value = true
  setTimeout(() => {
    detailRef.value.setData(row)
  }, 100)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceSpatialDataApi.getDeviceSpatialDataPage(queryParams)
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
    await DeviceSpatialDataApi.deleteDeviceSpatialData(id)
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
    const data = await DeviceSpatialDataApi.exportDeviceSpatialData(queryParams)
    download.excel(data, '设备空间数据.xls')
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
  color: #333;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
