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
      <el-form-item label="风险源编号" prop="riskSourceNo">
        <el-input
          v-model="queryParams.riskSourceNo"
          placeholder="请输入风险源编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="风险源名称" prop="riskSourceName">
        <el-input
          v-model="queryParams.riskSourceName"
          placeholder="请输入风险源名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="风险源类型" prop="riskSourceType">
        <el-select
          v-model="queryParams.riskSourceType"
          placeholder="请选择风险源类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-input
          v-model="queryParams.riskLevel"
          placeholder="请输入风险等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所在区域" prop="regionName">
        <el-input
          v-model="queryParams.regionName"
          placeholder="请输入所在行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="建档时间" prop="createTime">
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
  <ContentWrap class="table-container">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="风险源编号" align="center" prop="riskSourceNo" width="140" />
      <el-table-column label="风险源名称" align="center" prop="riskSourceName" width="200" />
      <el-table-column label="风险源类型" align="center" prop="riskSourceType" width="120" />
      <el-table-column label="风险等级" align="center" prop="riskLevel" width="100">
        <template #default="scope">
          <span :class="['risk-level', `level-${scope.row.riskLevel}`]">
            {{ scope.row.riskLevel }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="所在区域" align="center" prop="regionName" width="180" />
      <el-table-column label="责任部门" align="center" prop="deptName" width="160" />
      <el-table-column label="历史预警次数" align="center" prop="historyWarnCount" width="120" />
      <el-table-column
        label="建档时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="160px">
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
      class="pagination-container"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <RiskSourceArchForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <RiskSourceArchDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  RiskSourceArchApi,
  RiskSourceArchVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risksourcearch'
import RiskSourceArchForm from './RiskSourceArchForm.vue'
import RiskSourceArchDetail from './RiskSourceArchDetail.vue'

/** 风险源档案 列表 */
defineOptions({ name: 'RiskSourceArch' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<RiskSourceArchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  riskSourceId: undefined,
  riskSourceNo: undefined,
  riskSourceName: undefined,
  riskSourceType: undefined,
  riskLevel: undefined,
  regionCode: undefined,
  regionName: undefined,
  locationDesc: undefined,
  assetId: undefined,
  assetName: undefined,
  deptId: undefined,
  deptName: undefined,
  historyWarnCount: undefined,
  createTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 详情抽屉 */
const detailRef = ref()
const openDetail = (row: RiskSourceArchVO) => {
  detailRef.value.open(row)
}
const handleDetailClose = () => {
  // 关闭抽屉后的处理
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await RiskSourceArchApi.getRiskSourceArchPage(queryParams)
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
    await RiskSourceArchApi.deleteRiskSourceArch(id)
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
    const data = await RiskSourceArchApi.exportRiskSourceArch(queryParams)
    download.excel(data, '风险源档案.xls')
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

.table-container {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.custom-table {
  border-radius: 6px;
  overflow: hidden;
}

.pagination-container {
  margin-top: 15px;
  text-align: right;
}

.risk-level {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.level-高 {
  background-color: #fff1f0;
  color: #cf1322;
}

.level-中 {
  background-color: #fffbe6;
  color: #faad14;
}

.level-低 {
  background-color: #f6ffed;
  color: #52c41a;
}
</style>
