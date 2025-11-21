<template>
  <ContentWrap>
    <!-- 搜索工作栏：隐藏扩展字段，保留核心搜索项 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="统计ID" prop="efficacyStatId">
        <el-input
          v-model="queryParams.efficacyStatId"
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
      <el-form-item label="处置部门" prop="handleDeptName">
        <el-input
          v-model="queryParams.handleDeptName"
          placeholder="请输入处置部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处置预警总数" prop="totalHandleCount">
        <el-input
          v-model="queryParams.totalHandleCount"
          placeholder="请输入处置预警总数"
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

  <!-- 列表：美化样式，添加详情按钮 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="统计ID" align="center" prop="efficacyStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="处置部门ID" align="center" prop="handleDeptId" />
      <el-table-column label="处置部门名称" align="center" prop="handleDeptName" />
      <el-table-column label="处置预警总数" align="center" prop="totalHandleCount" />
      <el-table-column label="已办结数" align="center" prop="completedCount" />
      <el-table-column label="处置率(%)" align="center" prop="handleRate">
        <template #default="scope">
          <span :class="scope.row.handleRate > 80 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.handleRate ? scope.row.handleRate.toFixed(2) : 0 }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="平均处置时长" align="center" prop="avgHandleEndure" />
      <el-table-column label="主要处置类型" align="center" prop="mainHandleType" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
      />
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
  <EarlyWarnHandleEffForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EarlyWarnHandleEffDetail
    :visible="detailVisible"
    :detail-data="currentDetail"
    @close="detailVisible = false"
    @update:visible="(val) => (detailVisible = val)"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnHandleEffApi,
  EarlyWarnHandleEffVO
} from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarnhandleeff'
import EarlyWarnHandleEffForm from './EarlyWarnHandleEffForm.vue'
import EarlyWarnHandleEffDetail from './EarlyWarnHandleEffDetail.vue'

/** 预警告警处置效率统计 列表 */
defineOptions({ name: 'EarlyWarnHandleEff' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnHandleEffVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  efficacyStatId: undefined,
  statCycle: undefined,
  handleDeptId: undefined,
  handleDeptName: undefined,
  totalHandleCount: undefined,
  completedCount: undefined,
  handleRate: undefined,
  avgHandleEndure: undefined,
  criticalAvgEndure: undefined,
  mainHandleType: undefined,
  mainTypeProportion: undefined,
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

// 详情抽屉相关：规避prop变异错误，通过父组件状态控制
const detailVisible = ref(false)
const currentDetail = ref<EarlyWarnHandleEffVO | null>(null)

/** 打开详情抽屉 */
const openDetail = (row: EarlyWarnHandleEffVO) => {
  currentDetail.value = { ...row }
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnHandleEffApi.getEarlyWarnHandleEffPage(queryParams)
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
    await EarlyWarnHandleEffApi.deleteEarlyWarnHandleEff(id)
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
    const data = await EarlyWarnHandleEffApi.exportEarlyWarnHandleEff(queryParams)
    download.excel(data, '预警告警处置效率统计.xls')
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
  margin-bottom: 16px;
}

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #333;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}

::v-deep .el-form-item {
  margin-bottom: 12px;
}

.text-green-500 {
  color: #52c41a;
  font-weight: 500;
}

.text-orange-500 {
  color: #fa8c16;
  font-weight: 500;
}
</style>
