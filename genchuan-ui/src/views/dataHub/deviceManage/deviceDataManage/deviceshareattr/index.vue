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
      <el-form-item label="属性配置ID" prop="shareAttrCfgId">
        <el-input
          v-model="queryParams.shareAttrCfgId"
          placeholder="请输入属性配置ID"
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
      <el-form-item label="属性名称" prop="attrName">
        <el-input
          v-model="queryParams.attrName"
          placeholder="请输入属性名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="配置时间" prop="cfgTime">
        <el-date-picker
          v-model="queryParams.cfgTime"
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
      <el-table-column label="属性配置ID" align="center" prop="shareAttrCfgId" />
      <el-table-column label="设备ID" align="center" prop="deviceId" />
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="120" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="属性名称" align="center" prop="attrName" />
      <el-table-column label="属性代码" align="center" prop="attrCode" />
      <el-table-column label="属性值" align="center" prop="attrValue" />
      <el-table-column label="数据类型" align="center" prop="dataType" />
      <el-table-column label="是否必选" align="center" prop="isRequired">
        <template #default="scope">
          <span :class="scope.row.isRequired !== '1' ? 'text-green-500' : 'text-red-500'">
            {{ scope.row.isRequired === '1' ? '是' : '否' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="配置时间"
        align="center"
        prop="cfgTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="配置人" align="center" prop="cfgUser" />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
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
  <DeviceShareAttrForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceShareAttrDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceShareAttrApi,
  DeviceShareAttrVO
} from '@/api/dataHub/deviceManage/deviceDataManage/deviceshareattr'
import DeviceShareAttrForm from './DeviceShareAttrForm.vue'
import DeviceShareAttrDetail from './DeviceShareAttrDetail.vue'

/** 设备共享属性配置 列表 */
defineOptions({ name: 'DeviceShareAttr' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceShareAttrVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailVisible = ref(false) // 详情抽屉显示状态
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  shareAttrCfgId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  attrName: undefined,
  attrCode: undefined,
  attrValue: undefined,
  dataType: undefined,
  valueRange: undefined,
  isRequired: undefined,
  cfgTime: [],
  cfgUser: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const formRef = ref() // 表单Ref
const detailRef = ref() // 详情抽屉Ref

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceShareAttrApi.getDeviceShareAttrPage(queryParams)
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

/** 详情操作 */
const openDetail = (row: DeviceShareAttrVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceShareAttrApi.deleteDeviceShareAttr(id)
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
    const data = await DeviceShareAttrApi.exportDeviceShareAttr(queryParams)
    download.excel(data, '设备共享属性配置.xls')
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

::v-deep .el-table__header-wrapper th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
