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
      <el-form-item label="设备ID" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备ID"
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
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select
          v-model="queryParams.deviceType"
          placeholder="请选择设备类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select
          v-model="queryParams.deviceStatus"
          placeholder="请选择设备状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="安装时间" prop="installTime">
        <el-date-picker
          v-model="queryParams.installTime"
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
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="150" />
      <el-table-column label="设备类型" align="center" prop="deviceType" />
      <el-table-column label="所属区域" align="center" prop="areaName" />
      <el-table-column label="关联人员" align="center" prop="personName" />
      <el-table-column label="设备状态" align="center" prop="deviceStatus">
        <template #default="scope">
          <span :class="scope.row.deviceStatus === '正常' ? 'status-normal' : 'status-abnormal'">
            {{ scope.row.deviceStatus }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="安装时间"
        align="center"
        prop="installTime"
        :formatter="dateFormatter"
        width="160"
      />
      <el-table-column
        label="更新时间"
        align="center"
        prop="bizUpdateTime"
        :formatter="dateFormatter"
        width="160"
      />
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectDeviceInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectDeviceInfoDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectDeviceInfoApi,
  InspectDeviceInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/deviceManage'
import InspectDeviceInfoForm from './InspectDeviceInfoForm.vue'
import InspectDeviceInfoDetail from './InspectDeviceInfoDetail.vue'

/** 巡查巡检设备信息 列表 */
defineOptions({ name: 'InspectDeviceInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectDeviceInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  deviceId: undefined,
  deviceCode: undefined,
  deviceName: undefined,
  deviceType: undefined,
  areaCode: undefined,
  areaName: undefined,
  gridId: undefined,
  gridName: undefined,
  personId: undefined,
  personName: undefined,
  deviceStatus: undefined,
  installTime: [],
  warrantyEndTime: [],
  createUser: undefined,
  bizCreateTime: [],
  updateUser: undefined,
  bizUpdateTime: [],
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
    const data = await InspectDeviceInfoApi.getInspectDeviceInfoPage(queryParams)
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
const openDetail = (row: InspectDeviceInfoVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectDeviceInfoApi.deleteInspectDeviceInfo(id)
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
    const data = await InspectDeviceInfoApi.exportInspectDeviceInfo(queryParams)
    download.excel(data, '巡查巡检设备信息.xls')
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

.status-normal {
  color: #52c41a;
  background-color: rgba(82, 196, 26, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-abnormal {
  color: #f5222d;
  background-color: rgba(245, 34, 45, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}
</style>
