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
      <el-form-item label="申诉编号" prop="appealCode">
        <el-input
          v-model="queryParams.appealCode"
          placeholder="请输入申诉编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="queryParams.evalObjectName"
          placeholder="请输入评价对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="最终复核结果" prop="finalReviewResult">
        <el-input
          v-model="queryParams.finalReviewResult"
          placeholder="请输入最终复核结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
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
          class="!w-300px rounded-md"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
  <ContentWrap class="mt-4">
    <el-card shadow="hover" class="rounded-lg">
      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        class="table-beautify"
        border
      >
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="申诉编号" align="center" prop="appealCode" width="150" />
        <el-table-column label="评价对象名称" align="center" prop="evalObjectName" width="180" />
        <el-table-column label="原评价等级" align="center" prop="originalEvalLevel" width="120">
          <template #default="scope">
            <el-tag
              :type="
                {
                  A: 'success',
                  B: 'primary',
                  C: 'warning'
                }[scope.row.originalEvalLevel]
              "
            >
              {{ scope.row.originalEvalLevel }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="原评价得分" align="center" prop="originalEvalScore" width="120">
          <template #default="scope">
            <span
              class="font-bold"
              :class="{
                'text-green-600': scope.row.originalEvalLevel === 'A',
                'text-blue-600': scope.row.originalEvalLevel === 'B',
                'text-orange-600': scope.row.originalEvalLevel === 'C'
              }"
              >{{ scope.row.originalEvalScore || 0 }}</span
            >
          </template>
        </el-table-column>
        <el-table-column label="修正后等级" align="center" prop="revisedLevel" width="120">
          <template #default="scope">
            <el-tag
              :type="
                {
                  A: 'success',
                  B: 'primary',
                  C: 'warning'
                }[scope.row.revisedLevel]
              "
            >
              {{ scope.row.revisedLevel }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="修正后得分" align="center" prop="revisedScore" width="120">
          <template #default="scope">
            <span
              class="font-bold"
              :class="{
                'text-green-600': scope.row.revisedLevel === 'A',
                'text-blue-600': scope.row.revisedLevel === 'B',
                'text-orange-600': scope.row.revisedLevel === 'C'
              }"
              >{{ scope.row.revisedScore || 0 }}</span
            >
          </template>
        </el-table-column>
        <el-table-column label="反馈意见" align="center" prop="feedbackOpinion" width="180" />
        <el-table-column
          label="反馈时间"
          align="center"
          prop="feedbackTime"
          :formatter="dateFormatter"
          width="180px"
        />
        <el-table-column label="操作" align="center" min-width="120px">
          <template #default="scope">
            <el-button link type="primary" @click="openDetail(scope.row)">
              <Icon icon="ep:detail" class="mr-2" />
              详情
            </el-button>
            <el-button link type="primary" @click="openForm('update', scope.row.id)">
              <Icon icon="ep:edit" class="mr-2" />
              编辑
            </el-button>
            <el-button link type="danger" @click="handleDelete(scope.row.id)">
              <Icon icon="ep:delete" class="mr-2" />
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-4">
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EvalReviewFeedbackForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalReviewFeedbackDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalReviewFeedbackApi,
  EvalReviewFeedbackVO
} from '@/api/dataHub/comprehensiveEval/evalAppealReview/evalReviewResultFeed'
import EvalReviewFeedbackForm from './EvalReviewFeedbackForm.vue'
import EvalReviewFeedbackDetailDrawer from './EvalReviewFeedbackDetailDrawer.vue'

/** 复核结果反馈 列表 */
defineOptions({ name: 'EvalReviewFeedback' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalReviewFeedbackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  appealCode: undefined,
  evalObjectName: undefined,
  finalReviewResult: undefined,
  feedbackTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 组件Ref */
const formRef = ref()
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalReviewFeedbackApi.getEvalReviewFeedbackPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情操作 */
const openDetail = (row: EvalReviewFeedbackVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalReviewFeedbackApi.deleteEvalReviewFeedback(id)
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
    const data = await EvalReviewFeedbackApi.exportEvalReviewFeedback(queryParams)
    download.excel(data, '复核结果反馈.xls')
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
.table-beautify {
  --el-table-row-hover-bg-color: #f0f9ff;
  --el-table-header-text-color: #333;
  --el-table-header-bg-color: #f8f9fa;
  --el-table-border-color: #e4e7ed;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-form-item {
  margin-bottom: 15px;
}
</style>
