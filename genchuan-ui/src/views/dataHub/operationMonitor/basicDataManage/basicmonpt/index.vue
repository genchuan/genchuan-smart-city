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
      <el-form-item label="点位ID" prop="ptId">
        <el-input
          v-model="queryParams.ptId"
          placeholder="请输入点位ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="点位名称" prop="ptName">
        <el-input
          v-model="queryParams.ptName"
          placeholder="请输入点位名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="点位编码" prop="ptCode">
        <el-input
          v-model="queryParams.ptCode"
          placeholder="请输入点位编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="点位类型" prop="ptType">
        <el-select
          v-model="queryParams.ptType"
          placeholder="请选择点位类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="点位状态" prop="ptStatus">
        <el-select
          v-model="queryParams.ptStatus"
          placeholder="请选择点位状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
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
      <el-table-column label="点位ID" align="center" prop="ptId" width="100" />
      <el-table-column label="点位名称" align="center" prop="ptName" min-width="120" />
      <el-table-column label="点位编码" align="center" prop="ptCode" width="120" />
      <el-table-column label="点位类型" align="center" prop="ptType" width="100" />
      <el-table-column label="所属区域" align="center" min-width="120">
        <template #default="scope">{{ scope.row.areaName }}</template>
      </el-table-column>
      <el-table-column label="关联设备" align="center" min-width="120">
        <template #default="scope">{{ scope.row.deviceName }}</template>
      </el-table-column>
      <el-table-column label="负责人" align="center" min-width="100">
        <template #default="scope">{{ scope.row.mngrName }}</template>
      </el-table-column>
      <el-table-column label="点位状态" align="center" prop="ptStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.ptStatus ? 'success' : 'danger'">
            {{ scope.row.ptStatus ? '正常' : '异常' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="160px"
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <BasicMonPtForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <BasicMonPtDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  BasicMonPtApi,
  BasicMonPtVO
} from '@/api/dataHub/operationMonitor/basicDataManage/basicmonpt'
import BasicMonPtForm from './BasicMonPtForm.vue'
import BasicMonPtDetail from './BasicMonPtDetail.vue'

/** 基础监测点位 列表 */
defineOptions({ name: 'BasicMonPt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BasicMonPtVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  ptId: undefined,
  ptName: undefined,
  ptCode: undefined,
  ptType: undefined,
  areaCode: undefined,
  areaName: undefined,
  deviceCode: undefined,
  deviceName: undefined,
  ptCoordX: undefined,
  ptCoordY: undefined,
  mngrId: undefined,
  mngrName: undefined,
  mngrPhone: undefined,
  ptStatus: undefined,
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
    const data = await BasicMonPtApi.getBasicMonPtPage(queryParams)
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
const openDetail = (row: BasicMonPtVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await BasicMonPtApi.deleteBasicMonPt(id)
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
    const data = await BasicMonPtApi.exportBasicMonPt(queryParams)
    download.excel(data, '基础监测点位.xls')
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

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #374151;
}

::v-deep .el-table tr:hover > td {
  background-color: #f8fafc !important;
}

::v-deep .el-table--enable-row-hover .el-table__body tr:hover > td {
  background-color: #f8fafc;
}
</style>
