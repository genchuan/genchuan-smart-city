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
      <el-form-item label="统计ID" prop="warnAssetStatId">
        <el-input
          v-model="queryParams.warnAssetStatId"
          placeholder="请输入统计ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资产大类" prop="assetMajorName">
        <el-input
          v-model="queryParams.assetMajorName"
          placeholder="请输入资产大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资产小类" prop="assetMinorName">
        <el-input
          v-model="queryParams.assetMinorName"
          placeholder="请输入资产小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警总数" prop="totalWarnCount">
        <el-input
          v-model="queryParams.totalWarnCount"
          placeholder="请输入预警总数"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="queryParams.statTime"
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
      <el-table-column label="统计ID" align="center" prop="warnAssetStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="资产大类" align="center" prop="assetMajorName" />
      <el-table-column label="资产小类" align="center" prop="assetMinorName" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" />
      <el-table-column label="主要故障类型" align="center" prop="mainFaultType" />
      <el-table-column label="涉及资产数" align="center" prop="assetCount" />
      <el-table-column label="修复率" align="center" prop="repairRate" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
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
  <EarlyWarnAssetForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnAssetDetail
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
  EarlyWarnAssetApi,
  EarlyWarnAssetVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnasset'
import EarlyWarnAssetForm from './EarlyWarnAssetForm.vue'
import EarlyWarnAssetDetail from './EarlyWarnAssetDetail.vue'

/** 按资产分域预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnAsset' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnAssetVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  warnAssetStatId: undefined,
  statCycle: undefined,
  assetMajorId: undefined,
  assetMajorName: undefined,
  assetMinorId: undefined,
  assetMinorName: undefined,
  totalWarnCount: undefined,
  mainFaultType: undefined,
  assetCount: undefined,
  mntCost: undefined,
  repairedAssetCount: undefined,
  repairRate: undefined,
  statUserId: undefined,
  statTime: [],
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
const detailData = ref<EarlyWarnAssetVO | null>(null)

const openDetail = (row: EarlyWarnAssetVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnAssetApi.getEarlyWarnAssetPage(queryParams)
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
    await EarlyWarnAssetApi.deleteEarlyWarnAsset(id)
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
    const data = await EarlyWarnAssetApi.exportEarlyWarnAsset(queryParams)
    download.excel(data, '按资产分域预警告警统计.xls')
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
