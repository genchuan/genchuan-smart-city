<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px mb-4"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="复核编码" prop="dailyReviewCode">
        <el-input
          v-model="queryParams.dailyReviewCode"
          placeholder="请输入复核编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="关联记录编码" prop="dailyExecCode">
        <el-input
          v-model="queryParams.dailyExecCode"
          placeholder="请输入关联日常记录编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="巡查人员" prop="execUserName">
        <el-input
          v-model="queryParams.execUserName"
          placeholder="请输入巡查人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="巡查区域" prop="execAreaName">
        <el-input
          v-model="queryParams.execAreaName"
          placeholder="请输入巡查区域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="复核结果" prop="reviewResult">
        <el-input
          v-model="queryParams.reviewResult"
          placeholder="请输入复核结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="复核时间" prop="reviewTime">
        <el-date-picker
          v-model="queryParams.reviewTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-320px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" type="primary" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')" class="ml-2">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading" class="ml-2">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>
  <!-- 列表 -->
  <ContentWrap class="rounded-lg shadow-sm border border-gray-100">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
      :header-cell-style="{
        'background-color': '#f8f9fa',
        color: '#333',
        'font-weight': '600',
        'border-bottom': '2px solid #e9ecef'
      }"
      :row-style="{ height: '50px' }"
      :cell-style="{ 'vertical-align': 'middle' }"
    >
      <el-table-column label="复核编码" align="center" prop="dailyReviewCode" min-width="140px" />
      <el-table-column label="关联记录编码" align="center" prop="dailyExecCode" min-width="140px" />
      <el-table-column label="巡查人员" align="center" prop="execUserName" min-width="120px" />
      <el-table-column label="巡查区域" align="center" prop="execAreaName" min-width="140px" />
      <el-table-column label="复核结果" align="center" prop="reviewResult" min-width="120px">
        <template #default="scope">
          <el-tag :type="scope.row.reviewResult === '合格' ? 'success' : 'danger'">
            {{ scope.row.reviewResult || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="复核人" align="center" prop="reviewUser" min-width="120px" />
      <el-table-column
        label="复核时间"
        align="center"
        prop="reviewTime"
        :formatter="dateFormatter"
        min-width="180px"
      />
      <el-table-column
        label="整改要求"
        align="center"
        prop="rectifyRequirement"
        min-width="160px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row)" class="mr-2">
            详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination-container pt-4 pb-2 px-4">
      <Pagination
        :total="total"
        v-model:page="queryParams.pageNo"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
        background
        layout="prev, pager, next, jumper, ->, total"
      />
    </div>
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <InspectDailyReviewForm
    ref="formRef"
    :model-value="formDialogVisible"
    @update:model-value="formDialogVisible = $event"
    @success="getList"
  />
  <!-- 详情抽屉 -->
  <InspectDailyReviewDetail v-model="detailDrawerVisible" :data="currentDetailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectDailyReviewApi,
  InspectDailyReviewVO
} from '@/api/dataHub/supervisionInspection/patrolResultReview/dailyPatrolResultReview'
import InspectDailyReviewForm from './InspectDailyReviewForm.vue'
import InspectDailyReviewDetail from './InspectDailyReviewDetail.vue'

/** 巡查巡检日常结果复核 列表 */
defineOptions({ name: 'InspectDailyReview' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<InspectDailyReviewVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailDrawerVisible = ref(false) // 详情抽屉显示状态
const currentDetailData = ref<InspectDailyReviewVO | null>(null) // 当前详情数据
const formDialogVisible = ref(false) // 表单弹窗显示状态

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  dailyReviewId: undefined,
  dailyReviewCode: undefined,
  dailyExecId: undefined,
  dailyExecCode: undefined,
  execUserId: undefined,
  execUserName: undefined,
  execAreaName: undefined,
  reviewContent: undefined,
  reviewResult: undefined,
  reviewOpinion: undefined,
  reviewBasis: undefined,
  reviewUser: undefined,
  reviewTime: [],
  rectifyRequirement: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectDailyReviewApi.getInspectDailyReviewPage(queryParams)
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
  formDialogVisible.value = true
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectDailyReviewApi.deleteInspectDailyReview(id)
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
    const data = await InspectDailyReviewApi.exportInspectDailyReview(queryParams)
    download.excel(data, '巡查巡检日常结果复核.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情抽屉 */
const openDetailDrawer = (row: InspectDailyReviewVO) => {
  currentDetailData.value = row
  detailDrawerVisible.value = true
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
<style scoped>
.custom-table {
  --el-table-border-color: #f0f2f5;
  border-radius: 8px;
  overflow: hidden;
}

.custom-table ::v-deep(.el-table__row) {
  transition: background-color 0.2s ease;
}

.custom-table ::v-deep(.el-table__row):hover {
  background-color: #fafafa !important;
}

.pagination-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
</style>
