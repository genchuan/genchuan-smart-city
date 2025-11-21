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
        <!-- 评估基础信息 -->
        <div class="form-section">
          <div class="section-title">评估基础信息</div>
          <div class="form-row">
            <el-form-item label="评估ID" prop="evalId">
              <el-input
                v-model="queryParams.evalId"
                placeholder="请输入评估ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联回溯ID" prop="traceId">
              <el-input
                v-model="queryParams.traceId"
                placeholder="请输入关联回溯ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件编码" prop="evtCode">
              <el-input
                v-model="queryParams.evtCode"
                placeholder="请输入事件编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="事件名称" prop="evtName">
              <el-input
                v-model="queryParams.evtName"
                placeholder="请输入事件名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 评估得分信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">评估得分信息</div>
          <div class="form-row">
            <el-form-item label="处置效率得分" prop="efficacyScore">
              <el-input
                v-model="queryParams.efficacyScore"
                placeholder="请输入处置效率得分"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="资源利用得分" prop="resScore">
              <el-input
                v-model="queryParams.resScore"
                placeholder="请输入资源利用得分"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="效果达成得分" prop="effectScore">
              <el-input
                v-model="queryParams.effectScore"
                placeholder="请输入效果达成得分"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="综合得分" prop="comprehensiveScore">
              <el-input
                v-model="queryParams.comprehensiveScore"
                placeholder="请输入综合得分"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 评估说明信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">评估说明信息</div>
          <div class="form-row">
            <el-form-item label="处置效率说明" prop="efficacyDesc">
              <el-input
                v-model="queryParams.efficacyDesc"
                placeholder="请输入处置效率说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="资源利用说明" prop="resDesc">
              <el-input
                v-model="queryParams.resDesc"
                placeholder="请输入资源利用说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="效果达成说明" prop="effectDesc">
              <el-input
                v-model="queryParams.effectDesc"
                placeholder="请输入效果达成说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="改进建议" prop="improveSugg">
              <el-input
                v-model="queryParams.improveSugg"
                placeholder="请输入改进建议"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 评估人信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">评估人信息</div>
          <div class="form-row">
            <el-form-item label="评估人ID" prop="evalUserId">
              <el-input
                v-model="queryParams.evalUserId"
                placeholder="请输入评估人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="评估人姓名" prop="evalUserName">
              <el-input
                v-model="queryParams.evalUserName"
                placeholder="请输入评估人姓名"
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
            <el-form-item label="评估报告路径" prop="rptPath">
              <el-input
                v-model="queryParams.rptPath"
                placeholder="请输入评估报告路径"
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

        <!-- 系统信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">系统信息</div>
          <div class="form-row">
            <el-form-item label="系统创建时间" prop="createTimeSys">
              <el-date-picker
                v-model="queryParams.createTimeSys"
                value-format="YYYY-MM-DD"
                type="date"
                placeholder="选择系统创建时间"
                clearable
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="系统更新时间" prop="updateTimeSys">
              <el-date-picker
                v-model="queryParams.updateTimeSys"
                value-format="YYYY-MM-DD"
                type="date"
                placeholder="选择系统更新时间"
                clearable
                class="!w-200px"
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
            v-hasPermi="['datacenter:disposal-result-eval:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:disposal-result-eval:export']"
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
          <span class="card-title">处置结果评估列表</span>
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
        <el-table-column label="评估信息" align="center" min-width="180">
          <template #default="scope">
            <div class="eval-info">
              <div class="eval-id">评估ID: {{ scope.row.evalId }}</div>
              <div class="trace-id">回溯ID: {{ scope.row.traceId }}</div>
              <div class="event-info">
                <div class="event-code">{{ scope.row.evtCode }}</div>
                <div class="event-name">{{ scope.row.evtName }}</div>
              </div>
              <div v-if="scope.row.rptPath" class="rpt-path">
                <Icon icon="ep:document" class="mr-1" />
                评估报告
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="得分情况" align="center" min-width="200">
          <template #default="scope">
            <div class="score-info">
              <div class="comprehensive-score">
                <div class="score-label">综合得分</div>
                <div class="score-value" :class="getScoreClass(scope.row.comprehensiveScore)">
                  {{ scope.row.comprehensiveScore }}
                </div>
              </div>
              <div class="detail-scores">
                <div class="score-item">
                  <span class="score-type">处置效率:</span>
                  <span class="score-detail" :class="getScoreClass(scope.row.efficacyScore)">
                    {{ scope.row.efficacyScore }}
                  </span>
                </div>
                <div class="score-item">
                  <span class="score-type">资源利用:</span>
                  <span class="score-detail" :class="getScoreClass(scope.row.resScore)">
                    {{ scope.row.resScore }}
                  </span>
                </div>
                <div class="score-item">
                  <span class="score-type">效果达成:</span>
                  <span class="score-detail" :class="getScoreClass(scope.row.effectScore)">
                    {{ scope.row.effectScore }}
                  </span>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评估说明" align="center" min-width="220">
          <template #default="scope">
            <div class="eval-desc-info">
              <div v-if="scope.row.efficacyDesc" class="desc-item">
                <div class="desc-label">处置效率:</div>
                <div class="desc-content">{{ scope.row.efficacyDesc }}</div>
              </div>
              <div v-if="scope.row.resDesc" class="desc-item">
                <div class="desc-label">资源利用:</div>
                <div class="desc-content">{{ scope.row.resDesc }}</div>
              </div>
              <div v-if="scope.row.effectDesc" class="desc-item">
                <div class="desc-label">效果达成:</div>
                <div class="desc-content">{{ scope.row.effectDesc }}</div>
              </div>
              <div v-if="scope.row.improveSugg" class="improve-sugg">
                <div class="sugg-label">改进建议:</div>
                <div class="sugg-content">{{ scope.row.improveSugg }}</div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评估人" align="center" width="140">
          <template #default="scope">
            <div class="eval-user-info">
              <div class="eval-user-name">{{ scope.row.evalUserName }}</div>
              <div class="eval-user-id">ID: {{ scope.row.evalUserId }}</div>
              <div class="eval-time">{{ dateFormatter(scope.row.evalTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="系统信息" align="center" width="140">
          <template #default="scope">
            <div class="system-info">
              <div class="create-time">
                创建: {{ dateFormatter(scope.row.createTimeSys) }}
              </div>
              <div class="update-time">
                更新: {{ dateFormatter(scope.row.updateTimeSys) }}
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
              v-hasPermi="['datacenter:disposal-result-eval:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:disposal-result-eval:delete']"
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
  <DisposalResultEvalForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DisposalResultEvalApi, DisposalResultEvalVO } from '@/api/dataHub/commandAndCoordination/disposalresulteval'
import DisposalResultEvalForm from './DisposalResultEvalForm.vue'

/** 处置结果评估 列表 */
defineOptions({ name: 'DisposalResultEval' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DisposalResultEvalVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evalId: undefined,
  traceId: undefined,
  evtCode: undefined,
  evtName: undefined,
  efficacyScore: undefined,
  efficacyDesc: undefined,
  resScore: undefined,
  resDesc: undefined,
  effectScore: undefined,
  effectDesc: undefined,
  comprehensiveScore: undefined,
  improveSugg: undefined,
  evalUserId: undefined,
  evalUserName: undefined,
  evalTime: [],
  rptPath: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 根据得分获取样式类名 */
const getScoreClass = (score: number) => {
  if (!score) return 'score-default'
  if (score >= 90) return 'score-excellent'
  if (score >= 80) return 'score-good'
  if (score >= 60) return 'score-average'
  return 'score-poor'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DisposalResultEvalApi.getDisposalResultEvalPage(queryParams)
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
    await DisposalResultEvalApi.deleteDisposalResultEval(id)
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
    const data = await DisposalResultEvalApi.exportDisposalResultEval(queryParams)
    download.excel(data, '处置结果评估.xls')
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
.eval-info {
  line-height: 1.4;
  text-align: left;
}

.eval-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.trace-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 6px;
}

.event-info {
  margin-bottom: 6px;
}

.event-code {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.event-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.rpt-path {
  display: inline-flex;
  align-items: center;
  font-size: 12px;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
}

.score-info {
  line-height: 1.4;
  text-align: center;
}

.comprehensive-score {
  margin-bottom: 8px;
  padding: 8px;
  border-radius: 6px;
  background: #f5f7fa;
}

.score-label {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.score-value {
  font-size: 24px;
  font-weight: 700;
}

.detail-scores {
  display: flex;
  flex-direction: column;
  gap: 4px;
  font-size: 12px;
}

.score-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 4px 8px;
  border-radius: 4px;
  background: #f8f9fa;
}

.score-type {
  color: #606266;
}

.score-detail {
  font-weight: 600;
  padding: 2px 6px;
  border-radius: 3px;
}

/* 得分样式 */
.score-excellent {
  color: #67c23a;
  background: #f0f9eb;
}

.score-good {
  color: #409eff;
  background: #ecf5ff;
}

.score-average {
  color: #e6a23c;
  background: #fdf6ec;
}

.score-poor {
  color: #f56c6c;
  background: #fef0f0;
}

.score-default {
  color: #909399;
  background: #f4f4f5;
}

.eval-desc-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.desc-item, .improve-sugg {
  margin-bottom: 8px;
  padding: 6px 8px;
  border-radius: 4px;
  background: #f8f9fa;
}

.desc-item:last-child {
  margin-bottom: 0;
}

.desc-label, .sugg-label {
  color: #409eff;
  font-weight: 500;
  margin-bottom: 2px;
}

.desc-content, .sugg-content {
  color: #606266;
}

.improve-sugg {
  background: #f0f9ff;
  border-left: 3px solid #409eff;
}

.eval-user-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.eval-user-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.eval-user-id {
  color: #909399;
  margin-bottom: 4px;
}

.eval-time {
  color: #67c23a;
  font-weight: 500;
}

.system-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.create-time, .update-time {
  color: #909399;
  margin-bottom: 4px;
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
