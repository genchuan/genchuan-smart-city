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
      <el-form-item label="分域ID" prop="domId">
        <el-input
          v-model="queryParams.domId"
          placeholder="请输入分域ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input
          v-model="queryParams.domName"
          placeholder="请输入分域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域编码" prop="domCode">
        <el-input
          v-model="queryParams.domCode"
          placeholder="请输入分域编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-select
          v-model="queryParams.gridType"
          placeholder="请选择网格类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="网格名称" prop="gridName">
        <el-input
          v-model="queryParams.gridName"
          placeholder="请输入网格名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域状态" prop="domStatus">
        <el-select
          v-model="queryParams.domStatus"
          placeholder="请选择分域状态"
          clearable
          class="!w-200px"
        >
          <el-option label="启用" value="1" />
          <el-option label="停用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
      <el-table-column label="分域ID" align="center" prop="domId" width="120" />
      <el-table-column label="分域名称" align="center" prop="domName" width="150" />
      <el-table-column label="分域编码" align="center" prop="domCode" width="150" />
      <el-table-column label="网格类型" align="center" prop="gridType" width="120" />
      <el-table-column label="网格名称" align="center" prop="gridName" width="150" />
      <el-table-column label="所属行政区域" align="center" prop="adminName" width="150" />
      <el-table-column label="关联点位数量(个)" align="center" prop="relPtCount" width="130" />
      <el-table-column label="关联设备数量(个)" align="center" prop="relDeviceCount" width="130" />
      <el-table-column label="网格员" align="center" prop="gridUserName" width="120" />
      <el-table-column label="分域状态" align="center" prop="domStatus" width="120">
        <template #default="scope">
          <el-tag :type="scope.row.domStatus ? 'success' : 'danger'">
            {{ scope.row.domStatus ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-2">
            查看
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)" class="mr-2">
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
  <DomDivGridForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomDivGridDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDivGridApi,
  DomDivGridVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivgrid'
import DomDivGridForm from './DomDivGridForm.vue'
import DomDivGridDetail from './DomDivGridDetail.vue'

/** 网格分域 列表 */
defineOptions({ name: 'DomDivGrid' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDivGridVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  gridType: undefined,
  gridId: undefined,
  gridCode: undefined,
  gridName: undefined,
  adminCode: undefined,
  adminName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  gridUserId: undefined,
  gridUserName: undefined,
  domStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
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
    const data = await DomDivGridApi.getDomDivGridPage(queryParams)
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
const openDetail = (row: DomDivGridVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomDivGridApi.deleteDomDivGrid(id)
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
    const data = await DomDivGridApi.exportDomDivGrid(queryParams)
    download.excel(data, '网格分域.xls')
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

.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
