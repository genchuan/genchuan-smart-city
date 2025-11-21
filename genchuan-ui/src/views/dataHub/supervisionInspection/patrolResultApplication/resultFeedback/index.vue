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
      <el-form-item label="反馈编码" prop="feedbackCode">
        <el-input
          v-model="queryParams.feedbackCode"
          placeholder="请输入反馈编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="关联公示标题" prop="publicTitle">
        <el-input
          v-model="queryParams.publicTitle"
          placeholder="请输入关联公示标题"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="反馈人姓名" prop="feedbackUserName">
        <el-input
          v-model="queryParams.feedbackUserName"
          placeholder="请输入反馈人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="反馈状态" prop="feedbackStatus">
        <el-select
          v-model="queryParams.feedbackStatus"
          placeholder="请选择反馈状态"
          clearable
          class="!w-280px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="核实结果" prop="verifyResult">
        <el-input
          v-model="queryParams.verifyResult"
          placeholder="请输入核实结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="反馈时间" prop="feedbackTime">
        <el-date-picker
          v-model="queryParams.feedbackTime"
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
      <!-- 隐藏主键列：v-if="false" 完全移除不占位 -->
      <el-table-column label="主键ID" align="center" prop="id" v-if="false" />
      <el-table-column label="反馈编码" align="center" prop="feedbackCode" min-width="140px" />
      <el-table-column label="关联公示标题" align="center" prop="publicTitle" min-width="180px" />
      <el-table-column
        label="反馈人姓名"
        align="center"
        prop="feedbackUserName"
        min-width="120px"
      />
      <el-table-column
        label="反馈人手机号"
        align="center"
        prop="feedbackUserPhone"
        min-width="140px"
      />
      <el-table-column
        label="反馈时间"
        align="center"
        prop="feedbackTime"
        :formatter="dateFormatter"
        min-width="180px"
      />
      <el-table-column label="反馈状态" align="center" prop="feedbackStatus" min-width="120px">
        <template #default="scope">
          <el-tag
            :type="
              scope.row.feedbackStatus === '已处理'
                ? 'success'
                : scope.row.feedbackStatus === '处理中'
                  ? 'primary'
                  : 'warning'
            "
          >
            {{ scope.row.feedbackStatus || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="核实人员" align="center" prop="verifyUserName" min-width="120px" />
      <el-table-column label="核实结果" align="center" prop="verifyResult" min-width="120px">
        <template #default="scope">
          <span
            class="font-medium"
            :class="scope.row.verifyResult === '属实' ? 'text-green-600' : 'text-orange-600'"
          >
            {{ scope.row.verifyResult || '-' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="处理人" align="center" prop="handleUser" min-width="120px" />
      <!-- 隐藏扩展字段列：v-if="false" 完全移除不占位 -->
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" v-if="false" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" v-if="false" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" v-if="false" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" v-if="false" />
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
  <InspectResultFeedbackForm
    ref="formRef"
    :model-value="formDialogVisible"
    @update:model-value="formDialogVisible = $event"
    @success="getList"
  />
  <!-- 详情抽屉 -->
  <InspectResultFeedbackDetail v-model="detailDrawerVisible" :data="currentDetailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectResultFeedbackApi,
  InspectResultFeedbackVO
} from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultFeedback'
import InspectResultFeedbackForm from './InspectResultFeedbackForm.vue'
import InspectResultFeedbackDetail from './InspectResultFeedbackDetail.vue'

/** 巡查巡检结果反馈 列表 */
defineOptions({ name: 'InspectResultFeedback' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<InspectResultFeedbackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailDrawerVisible = ref(false) // 详情抽屉显示状态
const currentDetailData = ref<InspectResultFeedbackVO | null>(null) // 当前详情数据
const formDialogVisible = ref(false) // 表单弹窗显示状态

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  feedbackId: undefined,
  feedbackCode: undefined,
  publicId: undefined,
  publicTitle: undefined,
  feedbackUserName: undefined,
  feedbackUserPhone: undefined,
  feedbackContent: undefined,
  feedbackPhotoUrls: undefined,
  feedbackTime: [],
  feedbackStatus: undefined,
  verifyUserId: undefined,
  verifyUserName: undefined,
  verifyResult: undefined,
  verifyOpinion: undefined,
  problemId: undefined,
  handleResult: undefined,
  handleUser: undefined,
  handleTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const formRef = ref() // 表单组件引用

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectResultFeedbackApi.getInspectResultFeedbackPage(queryParams)
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
  queryFormRef.value?.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const openForm = async (type: string, id?: number) => {
  formDialogVisible.value = true
  // 确保子组件渲染完成后再调用方法
  // await nextTick()
  formRef.value?.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectResultFeedbackApi.deleteInspectResultFeedback(id)
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
    const data = await InspectResultFeedbackApi.exportInspectResultFeedback(queryParams)
    download.excel(data, '巡查巡检结果反馈.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情抽屉 */
const openDetailDrawer = (row: InspectResultFeedbackVO) => {
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

/* 表格状态列样式优化 */
::v-deep(.el-table-column--align-center .cell) {
  font-family: 'Arial', sans-serif;
}
</style>
