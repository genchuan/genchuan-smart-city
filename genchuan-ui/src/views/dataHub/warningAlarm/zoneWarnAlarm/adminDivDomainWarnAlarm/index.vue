<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="98px"
    >
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input
          v-model="queryParams.regionCode"
          placeholder="请输入行政区划代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input
          v-model="queryParams.regionName"
          placeholder="请输入行政区划名称"
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
        <el-button @click="handleQuery" class="search-btn">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="reset-btn">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')" class="add-btn">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          class="export-btn"
        >
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap class="table-content">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
      border
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="统计ID" align="center" prop="warnRegionStatId" width="140" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
      <el-table-column label="行政区划代码" align="center" prop="regionCode" width="140" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" width="140" />
      <el-table-column label="行政区划级别" align="center" prop="regionLevel" width="120" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" width="100" />
      <el-table-column label="已办结数" align="center" prop="completedCount" width="100" />
      <el-table-column label="待处置数" align="center" prop="pendCount" width="100" />
      <el-table-column label="处置率(%)" align="center" prop="handleRate" width="100" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-5px">
            查看
          </el-button>
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnRegionForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnRegionDetail
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
  EarlyWarnRegionApi,
  EarlyWarnRegionVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/adminDivDomainWarnAlarm'
import EarlyWarnRegionForm from './EarlyWarnRegionForm.vue'
import EarlyWarnRegionDetail from './EarlyWarnRegionDetail.vue'

/** 按行政区划预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnRegion' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnRegionVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  warnRegionStatId: undefined,
  statCycle: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  totalWarnCount: undefined,
  normalLevelCount: undefined,
  heavyLevelCount: undefined,
  seriousLevelCount: undefined,
  criticalLevelCount: undefined,
  completedCount: undefined,
  pendCount: undefined,
  handleRate: undefined,
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
const detailData = ref<EarlyWarnRegionVO | null>(null)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnRegionApi.getEarlyWarnRegionPage(queryParams)
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
const openDetail = (row: EarlyWarnRegionVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnRegionApi.deleteEarlyWarnRegion(id)
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
    const data = await EarlyWarnRegionApi.exportEarlyWarnRegion(queryParams)
    download.excel(data, '按行政区划预警告警统计.xls')
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

.search-btn {
  margin-right: 8px;
}

.reset-btn {
  margin-right: 8px;
}

.table-content {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.custom-table {
  width: 100%;
  margin-bottom: 15px;
}

.pagination {
  margin-top: 15px;
  text-align: right;
}
</style>
