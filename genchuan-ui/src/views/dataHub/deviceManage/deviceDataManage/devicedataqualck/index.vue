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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="质量检查ID" prop="qualCkId">
        <el-input
          v-model="queryParams.qualCkId"
          placeholder="请输入质量检查ID"
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
      <el-form-item label="检查批次" prop="ckBatch">
        <el-input
          v-model="queryParams.ckBatch"
          placeholder="请输入检查批次"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="检查时间" prop="ckTime">
        <el-date-picker
          v-model="queryParams.ckTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="检查结果" prop="ckResult">
        <el-input
          v-model="queryParams.ckResult"
          placeholder="请输入检查结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="整改状态" prop="rectifyStatus">
        <el-select
          v-model="queryParams.rectifyStatus"
          placeholder="请选择整改状态"
          clearable
          class="!w-240px"
        >
          <el-option label="未整改" value="0" />
          <el-option label="已整改" value="1" />
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
      <el-table-column label="质量检查ID" align="center" prop="qualCkId" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="检查批次" align="center" prop="ckBatch" />
      <el-table-column
        label="检查时间"
        align="center"
        prop="ckTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="检查项" align="center" prop="ckItem" />
      <el-table-column label="检查结果" align="center" prop="ckResult" />
      <!-- 整改状态：采用插槽方式设置el-tag -->
      <el-table-column label="整改状态" align="center">
        <template #default="scope">
          <el-tag :type="scope.row.rectifyStatus === '已完成' ? 'success' : 'danger'" size="small">
            {{ scope.row.rectifyStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="整改时间"
        align="center"
        prop="rectifyTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="检查人" align="center" prop="ckUser" />
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
  <DeviceDataQualCkForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceDataQualCkDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceDataQualCkApi,
  DeviceDataQualCkVO
} from '@/api/dataHub/deviceManage/deviceDataManage/devicedataqualck'
import DeviceDataQualCkForm from './DeviceDataQualCkForm.vue'
import DeviceDataQualCkDetail from './DeviceDataQualCkDetail.vue'

/** 设备数据质量检查 列表 */
defineOptions({ name: 'DeviceDataQualCk' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceDataQualCkVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  qualCkId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  ckBatch: undefined,
  ckTime: [],
  ckItem: undefined,
  ckResult: undefined,
  rectifyStatus: undefined,
  rectifyTime: []
  // 隐藏不常用的搜索字段
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const detailVisible = ref(false) // 详情抽屉显示状态
const formRef = ref() // 表单Ref
const detailRef = ref() // 详情抽屉Ref

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceDataQualCkApi.getDeviceDataQualCkPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 查看详情 */
const openDetail = (row: DeviceDataQualCkVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceDataQualCkApi.deleteDeviceDataQualCk(id)
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
    const data = await DeviceDataQualCkApi.exportDeviceDataQualCk(queryParams)
    download.excel(data, '设备数据质量检查.xls')
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

:deep(.el-table__header-wrapper th) {
  background-color: #f9fafb;
  font-weight: 600;
  color: #333;
}

:deep(.el-table__body tr:hover > td) {
  background-color: #f5f7fa !important;
}

/* 优化tag样式 */
:deep(.el-tag--success) {
  background-color: #f0f9eb;
  color: #10b981;
  border-color: #d1fae5;
}

:deep(.el-tag--danger) {
  background-color: #fee2e2;
  color: #ef4444;
  border-color: #fca5a5;
}
</style>
