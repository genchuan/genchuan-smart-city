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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-select
          v-model="queryParams.gridType"
          placeholder="请选择网格类型"
          clearable
          class="!w-240px"
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
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属街道" prop="streetName">
        <el-input
          v-model="queryParams.streetName"
          placeholder="请输入所属街道名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主要预警类型" prop="mainWarnType">
        <el-select
          v-model="queryParams.mainWarnType"
          placeholder="请选择主要预警类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120px" />
      <el-table-column label="网格类型" align="center" prop="gridType" width="100px" />
      <el-table-column label="网格名称" align="center" prop="gridName" min-width="150px" />
      <el-table-column label="所属街道" align="center" prop="streetName" min-width="150px" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" width="100px" />
      <el-table-column label="已办结数" align="center" prop="completedCount" width="100px" />
      <el-table-column label="待处置数" align="center" prop="pendCount" width="100px">
        <template #default="scope">
          <span class="text-red-500 font-medium">{{ scope.row.pendCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主要预警类型" align="center" prop="mainWarnType" width="140px" />
      <el-table-column label="处置率" align="center" prop="handleRate" width="100px">
        <template #default="scope">
          <span :class="scope.row.handleRate >= 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.handleRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnGridForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnGridDetail
    ref="detailRef"
    :visible="detailVisible"
    :detail-data="currentDetail"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnGridApi,
  EarlyWarnGridVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/gridDomainWarnAlarm'
import EarlyWarnGridForm from './EarlyWarnGridForm.vue'
import EarlyWarnGridDetail from './EarlyWarnGridDetail.vue'

/** 按网格分域预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnGrid' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnGridVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statCycle: undefined,
  gridType: undefined,
  gridName: undefined,
  streetName: undefined,
  mainWarnType: undefined,
  statTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const currentDetail = ref<EarlyWarnGridVO | null>(null)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnGridApi.getEarlyWarnGridPage(queryParams)
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
const openDetail = (row: EarlyWarnGridVO) => {
  currentDetail.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnGridApi.deleteEarlyWarnGrid(id)
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
    const data = await EarlyWarnGridApi.exportEarlyWarnGrid(queryParams)
    download.excel(data, '按网格分域预警告警统计.xls')
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

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
