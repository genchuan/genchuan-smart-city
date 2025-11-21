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
      <el-form-item label="路径ID" prop="tracePathId">
        <el-input
          v-model="queryParams.tracePathId"
          placeholder="请输入路径ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警ID" prop="earlyWarnId">
        <el-input
          v-model="queryParams.earlyWarnId"
          placeholder="请输入预警ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警名称" prop="earlyWarnName">
        <el-input
          v-model="queryParams.earlyWarnName"
          placeholder="请输入预警名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="溯源时间" prop="traceTime">
        <el-date-picker
          v-model="queryParams.traceTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="分析人" prop="analystUserName">
        <el-input
          v-model="queryParams.analystUserName"
          placeholder="请输入分析人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
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
      <el-table-column label="路径ID" align="center" prop="tracePathId" />
      <el-table-column label="预警ID" align="center" prop="earlyWarnId" />
      <el-table-column label="预警名称" align="center" prop="earlyWarnName" min-width="150" />
      <el-table-column
        label="溯源时间"
        align="center"
        prop="traceTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="溯源结论" align="center" prop="traceConclusion" min-width="150" />
      <el-table-column label="分析人" align="center" prop="analystUserName" width="120" />
      <el-table-column label="是否自动分析" align="center" prop="isAutoAnalysis" width="120">
        <template #default="scope">
          <span :class="scope.row.isAutoAnalysis === '1' ? 'text-green-500' : 'text-red-500'">
            {{ scope.row.isAutoAnalysis === '1' ? '是' : '否' }}
          </span>
        </template>
      </el-table-column>
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
  <RiskTracePathForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <RiskTracePathDetail
    ref="detailRef"
    :visible="detailVisible"
    :data="detailData"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  RiskTracePathApi,
  RiskTracePathVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risktracepath'
import RiskTracePathForm from './RiskTracePathForm.vue'
import RiskTracePathDetail from './RiskTracePathDetail.vue'

/** 风险溯源路径 列表 */
defineOptions({ name: 'RiskTracePath' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<RiskTracePathVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  tracePathId: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  traceTime: [],
  analystUserName: undefined
  // 隐藏部分搜索字段
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const detailData = ref<RiskTracePathVO | null>(null)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await RiskTracePathApi.getRiskTracePathPage(queryParams)
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

/** 查看详情 */
const openDetail = (row: RiskTracePathVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await RiskTracePathApi.deleteRiskTracePath(id)
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
    const data = await RiskTracePathApi.exportRiskTracePath(queryParams)
    download.excel(data, '风险溯源路径.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
