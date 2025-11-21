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
      <el-form-item label="数据ID" prop="realTimeDataId">
        <el-input
          v-model="queryParams.realTimeDataId"
          placeholder="请输入数据ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联任务ID" prop="evalTaskId">
        <el-input
          v-model="queryParams.evalTaskId"
          placeholder="请输入关联任务ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="queryParams.evalTaskName"
          placeholder="请输入关联任务名称"
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
      <el-form-item label="数据时间" prop="dataTime">
        <el-date-picker
          v-model="queryParams.dataTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="接入状态" prop="accessStatus">
        <el-select
          v-model="queryParams.accessStatus"
          placeholder="请选择接入状态"
          clearable
          class="!w-240px"
        >
          <el-option label="成功" value="SUCCESS" />
          <el-option label="失败" value="FAIL" />
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
      class="!rounded-lg !shadow-sm"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80px" />
      <el-table-column label="数据ID" align="center" prop="realTimeDataId" width="120px" />
      <el-table-column label="关联任务ID" align="center" prop="evalTaskId" width="120px" />
      <el-table-column label="关联任务名称" align="center" prop="evalTaskName" width="150px" />
      <el-table-column label="关联指标项名称" align="center" prop="idxItemName" width="150px" />
      <el-table-column label="设备名称" align="center" prop="deviceName" width="120px" />
      <el-table-column label="数据值" align="center" prop="dataValue" width="100px" />
      <el-table-column
        label="数据时间"
        align="center"
        prop="dataTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="接入状态" align="center" prop="accessStatus" width="100px">
        <template #default="scope">
          <el-tag :type="scope.row.accessStatus === 'SUCCESS' ? 'success' : 'danger'">
            {{ scope.row.accessStatus === 'SUCCESS' ? '成功' : '失败' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="同步频率" align="center" prop="syncFrequency" width="100px" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
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
      class="mt-10px"
    />
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <EvalRealTimeDataForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalRealTimeDataDetail v-model="detailVisible" :data="detailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalRealTimeDataApi,
  EvalRealTimeDataVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/rtMonDataAccess'
import EvalRealTimeDataForm from './EvalRealTimeDataForm.vue'
import EvalRealTimeDataDetail from './EvalRealTimeDataDetail.vue'

/** 实时监测数据接入 列表 */
defineOptions({ name: 'EvalRealTimeData' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalRealTimeDataVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailVisible = ref(false) // 详情抽屉显示状态
const detailData = ref<EvalRealTimeDataVO>({} as EvalRealTimeDataVO) // 详情数据

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  realTimeDataId: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  deviceId: undefined,
  deviceName: undefined,
  dataValue: undefined,
  dataTime: [],
  accessStatus: undefined,
  failReason: undefined,
  syncFrequency: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalRealTimeDataApi.getEvalRealTimeDataPage(queryParams)
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
const openDetail = (row: EvalRealTimeDataVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalRealTimeDataApi.deleteEvalRealTimeData(id)
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
    const data = await EvalRealTimeDataApi.exportEvalRealTimeData(queryParams)
    download.excel(data, '实时监测数据接入.xls')
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
.el-table {
  border-radius: 8px !important;
  overflow: hidden;
}

.el-table__header-wrapper th {
  background-color: #f8fafc !important;
  font-weight: 500;
}

.el-table__body-wrapper tr:hover td {
  background-color: #fefeff !important;
}
</style>
