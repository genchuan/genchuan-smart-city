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
      <!-- 保留常用搜索字段 -->
      <el-form-item label="统计ID" prop="unitStatId">
        <el-input
          v-model="queryParams.unitStatId"
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
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input
          v-model="queryParams.statCycleName"
          placeholder="请输入统计周期名称"
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
      <!-- 精简表格列 -->
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="统计ID" align="center" prop="unitStatId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" />
      <el-table-column label="行政区划级别" align="center" prop="regionLevel" width="100" />
      <el-table-column label="网格总数(个)" align="center" prop="totalCount" width="100" />
      <el-table-column label="在用网格数(个)" align="center" prop="inUseCount" width="100">
        <template #default="scope">
          <span class="status-success">{{ scope.row.inUseCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="停用网格数(个)" align="center" prop="stoppedCount" width="100">
        <template #default="scope">
          <span class="status-danger">{{ scope.row.stoppedCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总面积(m²)" align="center" prop="totalArea" />
      <el-table-column label="统计人" align="center" prop="statUserId" width="100" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情 </el-button>
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
      class="pagination-container"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <UnitGridRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <UnitGridRptDetail
    :visible="detailVisible"
    :detail-data="currentDetailData"
    @update:visible="(visible) => (detailVisible = visible)"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  UnitGridRptApi,
  UnitGridRptVO
} from '@/api/dataHub/gridManagement/gridStatReport/unitgridrpt'
import UnitGridRptForm from './UnitGridRptForm.vue'
import UnitGridRptDetail from './UnitGridRptDetail.vue'

/** 单元网格统计 列表 */
defineOptions({ name: 'UnitGridRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<UnitGridRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  unitStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  scale: undefined,
  totalCount: undefined,
  inUseCount: undefined,
  stoppedCount: undefined,
  totalArea: undefined,
  avgArea: undefined,
  newCount: undefined,
  updateCount: undefined,
  statUserId: undefined,
  statTime: [],
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const currentDetailData = ref<UnitGridRptVO>()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await UnitGridRptApi.getUnitGridRptPage(queryParams)
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

/** 打开详情抽屉 */
const openDetail = (row: UnitGridRptVO) => {
  currentDetailData.value = { ...row }
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await UnitGridRptApi.deleteUnitGridRpt(id)
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
    const data = await UnitGridRptApi.exportUnitGridRpt(queryParams)
    download.excel(data, '单元网格统计.xls')
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
  border-radius: 4px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.pagination-container {
  margin-top: 15px;
  text-align: right;
}

.status-success {
  color: #67c23a;
  font-weight: 500;
}

.status-danger {
  color: #f56c6c;
  font-weight: 500;
}
</style>
