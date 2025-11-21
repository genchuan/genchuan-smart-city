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
      <el-form-item label="上报数据ID" prop="platformRptDataId">
        <el-input
          v-model="queryParams.platformRptDataId"
          placeholder="请输入上报数据ID"
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
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="queryParams.evalObjectName"
          placeholder="请输入评价对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联指标项名称" prop="idxItemName">
        <el-input
          v-model="queryParams.idxItemName"
          placeholder="请输入关联指标项名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="上报时间" prop="rptTime">
        <el-date-picker
          v-model="queryParams.rptTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="数据状态" prop="dataStatus">
        <el-select
          v-model="queryParams.dataStatus"
          placeholder="请选择数据状态"
          clearable
          class="!w-240px"
        >
          <el-option label="已验证" value="VERIFIED" />
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
      <el-table-column label="上报数据ID" align="center" prop="platformRptDataId" width="140px" />
      <el-table-column label="关联任务名称" align="center" prop="evalTaskName" width="150px" />
      <el-table-column label="评价对象名称" align="center" prop="evalObjectName" width="150px" />
      <el-table-column label="关联指标项名称" align="center" prop="idxItemName" width="150px" />
      <el-table-column label="数据值" align="center" prop="dataValue" width="100px" />
      <el-table-column label="数据单位" align="center" prop="dataUnit" width="80px" />
      <el-table-column
        label="上报时间"
        align="center"
        prop="rptTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="上报人姓名" align="center" prop="rptUserName" width="120px" />
      <el-table-column label="数据状态" align="center" prop="dataStatus" width="100px">
        <template #default="scope">
          <el-tag :type="scope.row.dataStatus === 'VERIFIED' ? 'success' : 'danger'">
            {{ scope.row.dataStatus === 'VERIFIED' ? '已验证' : '未验证' }}
          </el-tag>
        </template>
      </el-table-column>
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
          <el-button link type="primary" @click="openForm('update', scope.row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
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
  <EvalPlatformRptDataForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalPlatformRptDataDetail v-model="detailVisible" :data="detailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalPlatformRptDataApi,
  EvalPlatformRptDataVO
} from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/platReportDataUpload'
import EvalPlatformRptDataForm from './EvalPlatformRptDataForm.vue'
import EvalPlatformRptDataDetail from './EvalPlatformRptDataDetail.vue'

/** 平台上报数据 列表 */
defineOptions({ name: 'EvalPlatformRptData' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalPlatformRptDataVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailVisible = ref(false) // 详情抽屉显示状态
const detailData = ref<EvalPlatformRptDataVO>({} as EvalPlatformRptDataVO) // 详情数据

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  platformRptDataId: undefined,
  evalTaskId: undefined,
  evalTaskName: undefined,
  evalObjectId: undefined,
  evalObjectName: undefined,
  idxItemId: undefined,
  idxItemName: undefined,
  dataValue: undefined,
  dataUnit: undefined,
  rptTime: [],
  rptUserId: undefined,
  rptUserName: undefined,
  dataStatus: undefined,
  verifyFailReason: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalPlatformRptDataApi.getEvalPlatformRptDataPage(queryParams)
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
const openDetail = (row: EvalPlatformRptDataVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalPlatformRptDataApi.deleteEvalPlatformRptData(id)
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
    const data = await EvalPlatformRptDataApi.exportEvalPlatformRptData(queryParams)
    download.excel(data, '平台上报数据.xls')
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
