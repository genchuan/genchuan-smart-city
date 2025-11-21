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
      <el-form-item label="属性名称" prop="attrName">
        <el-input
          v-model="queryParams.attrName"
          placeholder="请输入属性名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="属性代码" prop="attrCode">
        <el-input
          v-model="queryParams.attrCode"
          placeholder="请输入属性代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
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
      <el-table-column label="设备名称" align="center" prop="deviceName" min-width="120px" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" min-width="120px" />
      <el-table-column label="属性名称" align="center" prop="attrName" min-width="120px" />
      <el-table-column label="属性代码" align="center" prop="attrCode" min-width="120px" />
      <el-table-column label="属性值" align="center" prop="attrValue" min-width="100px" />
      <el-table-column label="数据类型" align="center" prop="dataType" min-width="100px" />
      <el-table-column label="是否必选" align="center" min-width="100px">
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
        min-width="160px"
      />
      <el-table-column label="配置人" align="center" prop="cfgUser" min-width="100px" />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        min-width="160px"
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
  <DeviceServerAttrForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceServerAttrDetail
    ref="detailRef"
    :visible="detailVisible"
    :detail-data="currentDetail"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceServerAttrApi,
  DeviceServerAttrVO
} from '@/api/dataHub/deviceManage/deviceDataManage/deviceserverattr'
import DeviceServerAttrForm from './DeviceServerAttrForm.vue'
import DeviceServerAttrDetail from './DeviceServerAttrDetail.vue'

/** 设备服务器属性配置 列表 */
defineOptions({ name: 'DeviceServerAttr' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceServerAttrVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  serverAttrCfgId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  attrName: undefined,
  attrCode: undefined,
  attrValue: undefined,
  dataType: undefined,
  isRequired: undefined,
  attrDesc: undefined,
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

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const currentDetail = ref<DeviceServerAttrVO | null>(null)

const openDetail = (row: DeviceServerAttrVO) => {
  currentDetail.value = row
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceServerAttrApi.getDeviceServerAttrPage(queryParams)
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
    await DeviceServerAttrApi.deleteDeviceServerAttr(id)
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
    const data = await DeviceServerAttrApi.exportDeviceServerAttr(queryParams)
    download.excel(data, '设备服务器属性配置.xls')
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
