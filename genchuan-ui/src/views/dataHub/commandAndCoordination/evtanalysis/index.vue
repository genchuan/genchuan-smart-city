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
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">基础信息</div>
          <div class="form-row">
            <el-form-item label="研判ID" prop="analysisId">
              <el-input
                v-model="queryParams.analysisId"
                placeholder="请输入研判ID"
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
            <el-form-item label="事件名称" prop="evtName">
              <el-input
                v-model="queryParams.evtName"
                placeholder="请输入事件名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 研判信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">研判信息</div>
          <div class="form-row">
            <el-form-item label="研判人" prop="analystName">
              <el-input
                v-model="queryParams.analystName"
                placeholder="请输入研判人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="研判时间" prop="analysisTime">
              <el-date-picker
                v-model="queryParams.analysisTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="紧急程度" prop="urgencyLevel">
              <el-input
                v-model="queryParams.urgencyLevel"
                placeholder="请输入紧急程度"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 分析详情 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">分析详情</div>
          <div class="form-row">
            <el-form-item label="事件原因" prop="evtReason">
              <el-input
                v-model="queryParams.evtReason"
                placeholder="请输入事件原因"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="影响范围" prop="impactRange">
              <el-input
                v-model="queryParams.impactRange"
                placeholder="请输入影响范围"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="研判结论" prop="conclusion">
              <el-input
                v-model="queryParams.conclusion"
                placeholder="请输入研判结论"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 监测数据 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">监测数据</div>
          <div class="form-row">
            <el-form-item label="监测数据ID" prop="relMonDataId">
              <el-input
                v-model="queryParams.relMonDataId"
                placeholder="请输入关联监测数据ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="数据摘要" prop="monDataAbstract">
              <el-input
                v-model="queryParams.monDataAbstract"
                placeholder="请输入监测数据摘要"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="是否需要分拨" prop="needAllocate">
              <el-select
                v-model="queryParams.needAllocate"
                placeholder="请选择是否需要分拨"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 详细信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">详细信息</div>
          <div class="form-row">
            <el-form-item label="研判人ID" prop="analystId">
              <el-input
                v-model="queryParams.analystId"
                placeholder="请输入研判人ID"
                clearable
                @keyup.enter="handleQuery"
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
            v-hasPermi="['datacenter:evt-analysis:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:evt-analysis:export']"
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
          <span class="card-title">事件研判分析列表</span>
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
        <el-table-column label="事件信息" align="center" min-width="200">
          <template #default="scope">
            <div class="event-info">
              <div class="event-code">{{ scope.row.evtCode }}</div>
              <div class="analysis-id">研判ID: {{ scope.row.analysisId }}</div>
              <div class="event-name">{{ scope.row.evtName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="研判信息" align="center" width="160">
          <template #default="scope">
            <div class="analysis-info">
              <div class="analyst-name">{{ scope.row.analystName }}</div>
              <div class="analysis-time">{{ dateFormatter(scope.row, {}, scope.row.analysisTime) }}</div>
              <el-tag
                :type="getUrgencyLevelType(scope.row.urgencyLevel)"
                size="small"
                class="mt-5px"
              >
                {{ scope.row.urgencyLevel || '未知' }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分析结论" align="center" width="180">
          <template #default="scope">
            <div class="analysis-detail">
              <div class="evt-reason">
                <span class="label">原因:</span>
                {{ scope.row.evtReason || '无' }}
              </div>
              <div class="impact-range">
                <span class="label">影响:</span>
                {{ scope.row.impactRange || '无' }}
              </div>
              <div class="conclusion">
                <span class="label">结论:</span>
                {{ scope.row.conclusion || '无' }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="监测数据" align="center" width="120">
          <template #default="scope">
            <div class="monitor-info">
              <div v-if="scope.row.relMonDataId" class="data-id">
                ID: {{ scope.row.relMonDataId }}
              </div>
              <div v-if="scope.row.monDataAbstract" class="data-abstract">
                {{ scope.row.monDataAbstract }}
              </div>
              <el-tag
                :type="scope.row.needAllocate === '1' ? 'warning' : 'success'"
                size="small"
                class="mt-5px"
              >
                {{ scope.row.needAllocate === '1' ? '需分拨' : '不分拨' }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="详细信息" align="center" width="100">
          <template #default="scope">
            <el-popover
              placement="left"
              title="详细信息"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看</el-button>
              </template>
              <div class="detail-fields">
                <div class="detail-field">
                  <span class="field-label">研判人ID:</span>
                  <span class="field-value">{{ scope.row.analystId || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">事件原因:</span>
                  <span class="field-value">{{ scope.row.evtReason || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">影响范围:</span>
                  <span class="field-value">{{ scope.row.impactRange || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">研判结论:</span>
                  <span class="field-value">{{ scope.row.conclusion || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">监测数据摘要:</span>
                  <span class="field-value">{{ scope.row.monDataAbstract || '无' }}</span>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:evt-analysis:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:evt-analysis:delete']"
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
  <EvtAnalysisForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EvtAnalysisApi, EvtAnalysisVO } from '@/api/dataHub/commandAndCoordination/evtanalysis'
import EvtAnalysisForm from './EvtAnalysisForm.vue'

/** 事件研判分析 列表 */
defineOptions({ name: 'EvtAnalysis' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EvtAnalysisVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  analysisId: undefined,
  evtCode: undefined,
  evtName: undefined,
  analystId: undefined,
  analystName: undefined,
  analysisTime: [],
  evtReason: undefined,
  impactRange: undefined,
  urgencyLevel: undefined,
  conclusion: undefined,
  relMonDataId: undefined,
  monDataAbstract: undefined,
  needAllocate: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取紧急程度标签类型 */
const getUrgencyLevelType = (level: string) => {
  const levelMap: Record<string, string> = {
    '紧急': 'danger',
    '重要': 'warning',
    '一般': 'info',
    '低': 'success'
  }
  return levelMap[level] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvtAnalysisApi.getEvtAnalysisPage(queryParams)
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
    await EvtAnalysisApi.deleteEvtAnalysis(id)
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
    const data = await EvtAnalysisApi.exportEvtAnalysis(queryParams)
    download.excel(data, '事件研判分析.xls')
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
.event-info {
  line-height: 1.4;
  text-align: left;
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

.analysis-id {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.event-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.analysis-info {
  line-height: 1.4;
  text-align: center;
}

.analyst-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.analysis-time {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.analysis-detail {
  line-height: 1.4;
  font-size: 12px;
  text-align: left;
}

.evt-reason, .impact-range, .conclusion {
  margin-bottom: 4px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.label {
  font-weight: 500;
  color: #606266;
  margin-right: 4px;
}

.monitor-info {
  line-height: 1.4;
  font-size: 12px;
  text-align: center;
}

.data-id {
  color: #909399;
  margin-bottom: 4px;
}

.data-abstract {
  color: #606266;
  margin-bottom: 4px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.detail-fields {
  font-size: 13px;
  line-height: 1.6;
}

.detail-field {
  display: flex;
  margin-bottom: 6px;
}

.field-label {
  font-weight: 500;
  color: #606266;
  min-width: 100px;
  text-align: right;
  margin-right: 8px;
}

.field-value {
  color: #303133;
  word-break: break-all;
  flex: 1;
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

.mt-5px {
  margin-top: 5px;
}
</style>
