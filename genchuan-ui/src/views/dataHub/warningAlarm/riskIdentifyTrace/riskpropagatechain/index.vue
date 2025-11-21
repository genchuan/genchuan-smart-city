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
      <el-form-item label="传播链ID" prop="propagateChainId">
        <el-input
          v-model="queryParams.propagateChainId"
          placeholder="请输入传播链ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="传播链编号" prop="propagateChainNo">
        <el-input
          v-model="queryParams.propagateChainNo"
          placeholder="请输入传播链编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="源头预警名称" prop="sourceWarnName">
        <el-input
          v-model="queryParams.sourceWarnName"
          placeholder="请输入源头预警名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="节点类型" prop="nodeType">
        <el-select
          v-model="queryParams.nodeType"
          placeholder="请选择节点类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="发生时间" prop="occurTime">
        <el-date-picker
          v-model="queryParams.occurTime"
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
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="传播链ID" align="center" prop="propagateChainId" />
      <el-table-column label="传播链编号" align="center" prop="propagateChainNo" />
      <el-table-column label="源头预警名称" align="center" prop="sourceWarnName" />
      <el-table-column label="传播节点序号" align="center" prop="nodeSeq" width="100" />
      <el-table-column label="节点类型" align="center" prop="nodeType" />
      <el-table-column
        label="发生时间"
        align="center"
        prop="occurTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="关联设备名称" align="center" prop="deviceName" />
      <el-table-column label="记录人姓名" align="center" prop="recUserName" width="100" />
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
  <RiskPropagateChainForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <RiskPropagateChainDetail
    ref="detailRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  RiskPropagateChainApi,
  RiskPropagateChainVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/riskpropagatechain'
import RiskPropagateChainForm from './RiskPropagateChainForm.vue'
import RiskPropagateChainDetail from './RiskPropagateChainDetail.vue'

/** 风险传播链 列表 */
defineOptions({ name: 'RiskPropagateChain' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<RiskPropagateChainVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  propagateChainId: undefined,
  propagateChainNo: undefined,
  sourceWarnId: undefined,
  sourceWarnName: undefined,
  nodeSeq: undefined,
  nodeContent: undefined,
  nodeType: undefined,
  occurTime: [],
  impactRange: undefined,
  deviceId: undefined,
  deviceName: undefined,
  recUserId: undefined,
  recUserName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const detailVisible = ref(false) // 详情抽屉显示状态
const detailRef = ref() // 详情抽屉引用

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await RiskPropagateChainApi.getRiskPropagateChainPage(queryParams)
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
const openDetail = (row: RiskPropagateChainVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await RiskPropagateChainApi.deleteRiskPropagateChain(id)
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
    const data = await RiskPropagateChainApi.exportRiskPropagateChain(queryParams)
    download.excel(data, '风险传播链.xls')
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
  color: #4e5969;
}

::v-deep .el-table tr:hover > td {
  background-color: #f0f7ff !important;
}
</style>
