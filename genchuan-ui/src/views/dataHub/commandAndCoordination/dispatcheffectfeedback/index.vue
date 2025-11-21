<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">查询条件</span>
          <el-button
            type="text"
            @click="toggleSearchForm"
            class="toggle-btn"
          >
            {{ showFullSearch ? '简化搜索' : '展开搜索' }}
            <Icon :icon="showFullSearch ? 'ep:arrow-up' : 'ep:arrow-down'" class="ml-2" />
          </el-button>
        </div>
      </template>
      <el-form
        class="search-form"
        :model="queryParams"
        ref="queryFormRef"
        :inline="true"
        label-width="120px"
      >
        <!-- 反馈基础信息 -->
        <div class="form-section">
          <div class="section-title">反馈基础信息</div>
          <div class="form-row">
            <el-form-item label="反馈ID" prop="feedbackId">
              <el-input
                v-model="queryParams.feedbackId"
                placeholder="请输入反馈ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="指令编号" prop="commandNo">
              <el-input
                v-model="queryParams.commandNo"
                placeholder="请输入指令编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联指令ID" prop="commandId">
              <el-input
                v-model="queryParams.commandId"
                placeholder="请输入关联指令ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 执行单位信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">执行单位信息</div>
          <div class="form-row">
            <el-form-item label="执行单位ID" prop="execUnitId">
              <el-input
                v-model="queryParams.execUnitId"
                placeholder="请输入执行单位ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="执行单位名称" prop="execUnitName">
              <el-input
                v-model="queryParams.execUnitName"
                placeholder="请输入执行单位名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 反馈内容信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">反馈内容信息</div>
          <div class="form-row">
            <el-form-item label="执行效果描述" prop="effectDesc">
              <el-input
                v-model="queryParams.effectDesc"
                placeholder="请输入执行效果描述"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="反馈附件路径" prop="feedbackAttachPath">
              <el-input
                v-model="queryParams.feedbackAttachPath"
                placeholder="请输入反馈附件路径"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 反馈人信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">反馈人信息</div>
          <div class="form-row">
            <el-form-item label="反馈人ID" prop="feedbackUserId">
              <el-input
                v-model="queryParams.feedbackUserId"
                placeholder="请输入反馈人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="反馈人姓名" prop="feedbackUserName">
              <el-input
                v-model="queryParams.feedbackUserName"
                placeholder="请输入反馈人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
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
          </div>
        </div>

        <!-- 评估信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">评估信息</div>
          <div class="form-row">
            <el-form-item label="评估结果" prop="evalResult">
              <el-input
                v-model="queryParams.evalResult"
                placeholder="请输入评估结果"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="评估人ID" prop="evalUserId">
              <el-input
                v-model="queryParams.evalUserId"
                placeholder="请输入评估人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="评估时间" prop="evalTime">
              <el-date-picker
                v-model="queryParams.evalTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-320px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="评估说明" prop="evalDesc">
              <el-input
                v-model="queryParams.evalDesc"
                placeholder="请输入评估说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 扩展信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">扩展信息</div>
          <div class="form-row">
            <el-form-item label="分类扩展字段1" prop="extCat1">
              <el-input
                v-model="queryParams.extCat1"
                placeholder="请输入分类扩展字段1"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分类扩展字段2" prop="extCat2">
              <el-input
                v-model="queryParams.extCat2"
                placeholder="请输入分类扩展字段2"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="通用扩展字段1" prop="extCommon1">
              <el-input
                v-model="queryParams.extCommon1"
                placeholder="请输入通用扩展字段1"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
            <el-form-item label="通用扩展字段2" prop="extCommon2">
              <el-input
                v-model="queryParams.extCommon2"
                placeholder="请输入通用扩展字段2"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
          </div>
        </div>

        <div class="form-actions">
          <el-button type="primary" @click="handleQuery">
            <Icon icon="ep:search" class="mr-5px" /> 搜索
          </el-button>
          <el-button @click="resetQuery">
            <Icon icon="ep:refresh" class="mr-5px" /> 重置
          </el-button>
          <el-button
            type="success"
            @click="openForm('create')"
            v-hasPermi="['datacenter:dispatch-effect-feedback:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:dispatch-effect-feedback:export']"
          >
            <Icon icon="ep:download" class="mr-5px" /> 导出
          </el-button>
        </div>
      </el-form>
    </el-card>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">调度效果反馈列表</span>
          <div class="table-info">
            共 <span class="info-highlight">{{ total }}</span> 条记录
          </div>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        style="width: 100%"
        :header-cell-style="{
          background: '#f5f7fa',
          color: '#606266',
          fontWeight: '600'
        }"
      >
        <el-table-column label="反馈信息" align="center" min-width="180">
          <template #default="scope">
            <div class="feedback-info">
              <div class="feedback-id">反馈ID: {{ scope.row.feedbackId }}</div>
              <div class="command-info">
                指令: {{ scope.row.commandNo }}
                <span class="command-id">(ID: {{ scope.row.commandId }})</span>
              </div>
              <div v-if="scope.row.effectDesc" class="effect-desc">
                {{ scope.row.effectDesc }}
              </div>
              <div v-if="scope.row.feedbackAttachPath" class="attach-path">
                <Icon icon="ep:paperclip" class="mr-1" />
                附件
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="执行单位" align="center" width="140">
          <template #default="scope">
            <div class="exec-unit-info">
              <div class="exec-unit-name">{{ scope.row.execUnitName }}</div>
              <div class="exec-unit-id">ID: {{ scope.row.execUnitId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="反馈人" align="center" width="120">
          <template #default="scope">
            <div class="feedback-user-info">
              <div class="feedback-user-name">{{ scope.row.feedbackUserName }}</div>
              <div class="feedback-user-id">ID: {{ scope.row.feedbackUserId }}</div>
              <div class="feedback-time">{{ dateFormatter(scope.row.feedbackTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评估信息" align="center" min-width="200">
          <template #default="scope">
            <div class="eval-info">
              <div class="eval-result">
                <el-tag
                  :type="getEvalResultTagType(scope.row.evalResult)"
                  size="small"
                >
                  {{ scope.row.evalResult || '待评估' }}
                </el-tag>
              </div>
              <div v-if="scope.row.evalDesc" class="eval-desc">
                {{ scope.row.evalDesc }}
              </div>
              <div v-if="scope.row.evalUserId" class="eval-user">
                评估人ID: {{ scope.row.evalUserId }}
              </div>
              <div v-if="scope.row.evalTime" class="eval-time">
                {{ dateFormatter(scope.row.evalTime) }}
              </div>
              <div v-else class="no-eval">
                未评估
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:dispatch-effect-feedback:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:dispatch-effect-feedback:delete']"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination-wrapper">
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
  <DispatchEffectFeedbackForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DispatchEffectFeedbackApi, DispatchEffectFeedbackVO } from '@/api/dataHub/commandAndCoordination/dispatcheffectfeedback'
import DispatchEffectFeedbackForm from './DispatchEffectFeedbackForm.vue'

/** 调度效果反馈 列表 */
defineOptions({ name: 'DispatchEffectFeedback' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DispatchEffectFeedbackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  feedbackId: undefined,
  commandId: undefined,
  commandNo: undefined,
  execUnitId: undefined,
  execUnitName: undefined,
  effectDesc: undefined,
  feedbackAttachPath: undefined,
  feedbackUserId: undefined,
  feedbackUserName: undefined,
  feedbackTime: [],
  evalResult: undefined,
  evalDesc: undefined,
  evalUserId: undefined,
  evalTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取评估结果标签样式 */
const getEvalResultTagType = (result: string) => {
  const resultMap: Record<string, string> = {
    '优秀': 'success',
    '良好': 'primary',
    '合格': 'warning',
    '不合格': 'danger',
    '待评估': 'info'
  }
  return resultMap[result] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DispatchEffectFeedbackApi.getDispatchEffectFeedbackPage(queryParams)
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
    await DispatchEffectFeedbackApi.deleteDispatchEffectFeedback(id)
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
    const data = await DispatchEffectFeedbackApi.exportDispatchEffectFeedback(queryParams)
    download.excel(data, '调度效果反馈.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 切换搜索表单显示 */
const toggleSearchForm = () => {
  showFullSearch.value = !showFullSearch.value
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
  border-radius: 8px;
}

.table-card {
  border-radius: 8px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.toggle-btn {
  color: #409eff;
  font-size: 13px;
}

.table-info {
  font-size: 14px;
  color: #606266;
}

.info-highlight {
  color: #409eff;
  font-weight: 600;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-section {
  border: 1px solid #ebeef5;
  border-radius: 6px;
  padding: 16px;
  background: #fafbfc;
}

.section-title {
  font-size: 14px;
  font-weight: 600;
  color: #409eff;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #e1e4e8;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

/* 表格内容样式 */
.feedback-info {
  line-height: 1.4;
  text-align: left;
}

.feedback-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.command-info {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.command-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.effect-desc {
  font-size: 13px;
  color: #606266;
  background: #f5f7fa;
  padding: 6px 8px;
  border-radius: 4px;
  margin: 4px 0;
  border-left: 3px solid #409eff;
}

.attach-path {
  display: inline-flex;
  align-items: center;
  font-size: 12px;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  margin-top: 4px;
}

.exec-unit-info {
  line-height: 1.4;
  text-align: center;
}

.exec-unit-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.exec-unit-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.feedback-user-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.feedback-user-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.feedback-user-id {
  color: #909399;
  margin-bottom: 4px;
}

.feedback-time {
  color: #67c23a;
  font-weight: 500;
}

.eval-info {
  line-height: 1.4;
  text-align: center;
}

.eval-result {
  margin-bottom: 6px;
}

.eval-desc {
  font-size: 12px;
  color: #606266;
  background: #f0f9ff;
  padding: 6px 8px;
  border-radius: 4px;
  margin: 4px 0;
  border-left: 3px solid #e6a23c;
}

.eval-user {
  font-size: 11px;
  color: #909399;
  margin-bottom: 2px;
}

.eval-time {
  font-size: 11px;
  color: #67c23a;
  font-weight: 500;
}

.no-eval {
  font-size: 12px;
  color: #c0c4cc;
  font-style: italic;
}

:deep(.el-card__header) {
  padding: 12px 20px;
  border-bottom: 1px solid #ebeef5;
}

:deep(.el-table .cell) {
  padding: 8px 12px;
}

:deep(.el-table th) {
  font-weight: 600;
}

:deep(.el-table .el-table__row:hover) {
  background-color: #f5f7fa;
}

.ml-2 {
  margin-left: 8px;
}

.mr-5px {
  margin-right: 5px;
}

.mr-1 {
  margin-right: 4px;
}
</style>
