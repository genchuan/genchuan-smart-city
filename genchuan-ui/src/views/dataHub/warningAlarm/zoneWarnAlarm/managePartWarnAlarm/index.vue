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
      <el-form-item label="统计ID" prop="warnMngCompStatId">
        <el-input
          v-model="queryParams.warnMngCompStatId"
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
      <el-form-item label="部件大类名称" prop="compMajorName">
        <el-input
          v-model="queryParams.compMajorName"
          placeholder="请输入部件大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件小类名称" prop="compMinorName">
        <el-input
          v-model="queryParams.compMinorName"
          placeholder="请输入部件小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主要故障类型" prop="mainFaultType">
        <el-select
          v-model="queryParams.mainFaultType"
          placeholder="请选择主要故障类型"
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
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="统计ID" align="center" prop="warnMngCompStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="部件大类" align="center">
        <template #default="scope">{{ scope.row.compMajorName }}</template>
      </el-table-column>
      <el-table-column label="部件小类" align="center">
        <template #default="scope">{{ scope.row.compMinorName }}</template>
      </el-table-column>
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" />
      <el-table-column label="主要故障类型" align="center" prop="mainFaultType" />
      <el-table-column label="主要分布区域" align="center">
        <template #default="scope">{{ scope.row.mainRegionName }}</template>
      </el-table-column>
      <el-table-column label="已办结数" align="center" prop="completedCount" />
      <el-table-column label="处置率" align="center" prop="handleRate" />
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
  <EarlyWarnMngCompForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnMngCompDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnMngCompApi,
  EarlyWarnMngCompVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/managePartWarnAlarm'
import EarlyWarnMngCompForm from './EarlyWarnMngCompForm.vue'
import EarlyWarnMngCompDetail from './EarlyWarnMngCompDetail.vue'

/** 按管理部件预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnMngComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnMngCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  warnMngCompStatId: undefined,
  statCycle: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalWarnCount: undefined,
  mainFaultType: undefined,
  mainRegionCode: undefined,
  mainRegionName: undefined,
  completedCount: undefined,
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
const detailVisible = ref(false)
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnMngCompApi.getEarlyWarnMngCompPage(queryParams)
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
const openDetail = (row: EarlyWarnMngCompVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnMngCompApi.deleteEarlyWarnMngComp(id)
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
    const data = await EarlyWarnMngCompApi.exportEarlyWarnMngComp(queryParams)
    download.excel(data, '按管理部件预警告警统计.xls')
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
