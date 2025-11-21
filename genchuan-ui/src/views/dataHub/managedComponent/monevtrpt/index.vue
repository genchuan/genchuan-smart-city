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
        label-width="160px"
      >
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">基础信息</div>
          <div class="form-row">
            <el-form-item label="唯一编码" prop="statId">
              <el-input
                v-model="queryParams.statId"
                placeholder="请输入唯一编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="统计周期类型" prop="statCycle">
              <el-select
                v-model="queryParams.statCycle"
                placeholder="请选择统计周期类型"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="日" value="day" />
                <el-option label="周" value="week" />
                <el-option label="月" value="month" />
                <el-option label="季" value="quarter" />
                <el-option label="年" value="year" />
              </el-select>
            </el-form-item>
            <el-form-item label="统计周期" prop="statCycleName">
              <el-input
                v-model="queryParams.statCycleName"
                placeholder="如：2025年09月"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 区域维度 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">区域维度</div>
          <div class="form-row">
            <el-form-item label="行政区划代码" prop="regionCode">
              <el-input
                v-model="queryParams.regionCode"
                placeholder="请输入行政区划代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="区域名称" prop="regionName">
              <el-input
                v-model="queryParams.regionName"
                placeholder="请输入区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 事件分类维度 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">事件分类维度</div>
          <div class="form-row">
            <el-form-item label="事件大类ID" prop="evtMajorId">
              <el-input
                v-model="queryParams.evtMajorId"
                placeholder="请输入事件大类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件大类名称" prop="evtMajorName">
              <el-input
                v-model="queryParams.evtMajorName"
                placeholder="请输入事件大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件小类ID" prop="evtMinorId">
              <el-input
                v-model="queryParams.evtMinorId"
                placeholder="请输入事件小类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件小类名称" prop="evtMinorName">
              <el-input
                v-model="queryParams.evtMinorName"
                placeholder="请输入事件小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 处置部门 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">处置部门</div>
          <div class="form-row">
            <el-form-item label="部门代码" prop="deptCode">
              <el-input
                v-model="queryParams.deptCode"
                placeholder="请输入部门代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部门名称" prop="deptName">
              <el-input
                v-model="queryParams.deptName"
                placeholder="请输入部门名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 事件数量统计 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">事件数量统计</div>
          <div class="form-row">
            <el-form-item label="总上报数" prop="totalRptCount">
              <el-input-number
                v-model="queryParams.totalRptCount"
                placeholder="总上报数"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="待处置数" prop="pendCount">
              <el-input-number
                v-model="queryParams.pendCount"
                placeholder="待处置数"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="处置中数" prop="handlCount">
              <el-input-number
                v-model="queryParams.handlCount"
                placeholder="处置中数"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="已办结数" prop="completedCount">
              <el-input-number
                v-model="queryParams.completedCount"
                placeholder="已办结数"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="已驳回数" prop="rejectedCount">
              <el-input-number
                v-model="queryParams.rejectedCount"
                placeholder="已驳回数"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="一级事件数" prop="level1Count">
              <el-input-number
                v-model="queryParams.level1Count"
                placeholder="一级事件数"
                :min="0"
                controls-position="right"
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
            v-hasPermi="['datacenter:mon-evt-rpt:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-evt-rpt:export']"
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
          <span class="card-title">监测事件统计报表列表</span>
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
        @sort-change="handleSortChange"
      >
        <el-table-column label="ID" align="center" prop="id" width="70" sortable="custom" />
        <el-table-column label="唯一编码" align="center" prop="statId" width="120" sortable="custom" />
        <el-table-column label="统计周期" align="center" width="140">
          <template #default="scope">
            <div class="stat-cycle">
              <div class="cycle-type">{{ scope.row.statCycle }}</div>
              <div class="cycle-name">{{ scope.row.statCycleName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="区域维度" align="center" width="160">
          <template #default="scope">
            <div class="region-info">
              <div class="region-code">{{ scope.row.regionCode }}</div>
              <div class="region-name">{{ scope.row.regionName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="事件分类" align="center" width="180">
          <template #default="scope">
            <el-popover
              placement="left"
              title="事件分类信息"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看分类</el-button>
              </template>
              <div class="event-category">
                <div class="category-item">
                  <span class="category-label">事件大类:</span>
                  <div class="category-detail">
                    <span class="major-id">ID: {{ scope.row.evtMajorId }}</span>
                    <span class="major-name">{{ scope.row.evtMajorName }}</span>
                  </div>
                </div>
                <div v-if="scope.row.evtMinorId" class="category-item">
                  <span class="category-label">事件小类:</span>
                  <div class="category-detail">
                    <span class="minor-id">ID: {{ scope.row.evtMinorId }}</span>
                    <span class="minor-name">{{ scope.row.evtMinorName }}</span>
                  </div>
                </div>
                <div v-else class="category-item">
                  <span class="category-label">事件小类:</span>
                  <span class="no-minor">无小类信息</span>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="处置部门" align="center" width="160">
          <template #default="scope">
            <div class="department-info">
              <div class="dept-code">{{ scope.row.deptCode }}</div>
              <div class="dept-name">{{ scope.row.deptName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="事件统计" align="center" width="220">
          <template #default="scope">
            <el-popover
              placement="left"
              title="详细事件统计"
              :width="320"
              trigger="click"
            >
              <template #reference>
                <div class="event-summary">
                  <div class="total-count">总计: {{ scope.row.totalRptCount || 0 }}</div>
                  <div class="status-counts">
                    <el-tag size="small" type="warning">{{ scope.row.pendCount || 0 }}待处置</el-tag>
                    <el-tag size="small" type="success">{{ scope.row.completedCount || 0 }}已办结</el-tag>
                  </div>
                </div>
              </template>
              <div class="event-detail">
                <div class="event-item">
                  <span class="event-label">总上报数:</span>
                  <span class="event-value total">{{ scope.row.totalRptCount || 0 }}</span>
                </div>
                <div class="event-row">
                  <div class="event-cell">
                    <span class="event-label">待处置:</span>
                    <span class="event-value pending">{{ scope.row.pendCount || 0 }}</span>
                  </div>
                  <div class="event-cell">
                    <span class="event-label">处置中:</span>
                    <span class="event-value handling">{{ scope.row.handlCount || 0 }}</span>
                  </div>
                </div>
                <div class="event-row">
                  <div class="event-cell">
                    <span class="event-label">已办结:</span>
                    <span class="event-value completed">{{ scope.row.completedCount || 0 }}</span>
                  </div>
                  <div class="event-cell">
                    <span class="event-label">已驳回:</span>
                    <span class="event-value rejected">{{ scope.row.rejectedCount || 0 }}</span>
                  </div>
                </div>
                <div class="event-row">
                  <div class="event-cell">
                    <span class="event-label">一级事件:</span>
                    <span class="event-value level1">{{ scope.row.level1Count || 0 }}</span>
                  </div>
                </div>
                <div class="event-chart" v-if="scope.row.totalRptCount > 0">
                  <div class="chart-title">处置状态分布</div>
                  <div class="chart-bar">
                    <div
                      class="bar-segment pending"
                      :style="{ width: calculatePercentage(scope.row.pendCount, scope.row.totalRptCount) }"
                      :title="`待处置: ${scope.row.pendCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment handling"
                      :style="{ width: calculatePercentage(scope.row.handlCount, scope.row.totalRptCount) }"
                      :title="`处置中: ${scope.row.handlCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment completed"
                      :style="{ width: calculatePercentage(scope.row.completedCount, scope.row.totalRptCount) }"
                      :title="`已办结: ${scope.row.completedCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment rejected"
                      :style="{ width: calculatePercentage(scope.row.rejectedCount, scope.row.totalRptCount) }"
                      :title="`已驳回: ${scope.row.rejectedCount || 0}`"
                    ></div>
                  </div>
                  <div class="chart-legend">
                    <div class="legend-item">
                      <span class="legend-color pending"></span>
                      <span class="legend-text">待处置</span>
                    </div>
                    <div class="legend-item">
                      <span class="legend-color handling"></span>
                      <span class="legend-text">处置中</span>
                    </div>
                    <div class="legend-item">
                      <span class="legend-color completed"></span>
                      <span class="legend-text">已办结</span>
                    </div>
                    <div class="legend-item">
                      <span class="legend-color rejected"></span>
                      <span class="legend-text">已驳回</span>
                    </div>
                  </div>
                </div>
                <div v-else class="no-data-chart">无事件数据</div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-rpt:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-rpt:delete']"
            >
              删除
            </el-button>
            <el-button
              size="small"
              type="success"
              link
              @click="handleViewAnalysis(scope.row)"
              v-hasPermi="['datacenter:mon-evt-rpt:query']"
            >
              分析
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
  <MonEvtRptForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import download from '@/utils/download'
import { MonEvtRptApi, MonEvtRptVO } from '@/api/dataHub/managedComponent/monevtrpt'
import MonEvtRptForm from './MonEvtRptForm.vue'

/** 监测事件统计报 列表 */
defineOptions({ name: 'MonEvtRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonEvtRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  evtMajorId: undefined,
  evtMajorName: undefined,
  evtMinorId: undefined,
  evtMinorName: undefined,
  deptCode: undefined,
  deptName: undefined,
  totalRptCount: undefined,
  pendCount: undefined,
  handlCount: undefined,
  completedCount: undefined,
  rejectedCount: undefined,
  level1Count: undefined,
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 计算百分比 */
const calculatePercentage = (part: number, total: number) => {
  if (!total) return '0%'
  return `${((part || 0) / total * 100).toFixed(1)}%`
}

/** 查看分析 */
const handleViewAnalysis = (row: any) => {
  // 这里可以打开分析弹窗或跳转到分析页面
  message.success(`查看事件分析: ${row.statCycleName}`)
  // 实际项目中可以调用分析弹窗组件
  // analysisRef.value.open(row.id)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonEvtRptApi.getMonEvtRptPage(queryParams)
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
    await MonEvtRptApi.deleteMonEvtRpt(id)
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
    const data = await MonEvtRptApi.exportMonEvtRpt(queryParams)
    download.excel(data, '监测事件统计报.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 切换搜索表单显示 */
const toggleSearchForm = () => {
  showFullSearch.value = !showFullSearch.value
}

/** 排序处理 */
const handleSortChange = (column: any) => {
  if (column.prop) {
    queryParams.sortField = column.prop
    queryParams.sortOrder = column.order === 'ascending' ? 'asc' :
      column.order === 'descending' ? 'desc' : undefined
  } else {
    queryParams.sortField = undefined
    queryParams.sortOrder = undefined
  }
  getList()
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
.stat-cycle {
  line-height: 1.4;
  font-size: 12px;
}

.cycle-type {
  font-weight: 500;
  color: #303133;
  text-transform: capitalize;
}

.cycle-name {
  color: #909399;
  margin-top: 2px;
}

.region-info {
  line-height: 1.4;
  font-size: 12px;
}

.region-code {
  color: #909399;
  font-size: 11px;
}

.region-name {
  font-weight: 500;
  color: #303133;
  margin-top: 2px;
}

.event-category {
  font-size: 13px;
  line-height: 1.6;
}

.category-item {
  margin-bottom: 10px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #f0f0f0;
}

.category-label {
  font-weight: 500;
  color: #606266;
  display: block;
  margin-bottom: 4px;
}

.category-detail {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.major-id,
.minor-id {
  color: #909399;
  font-size: 11px;
}

.major-name,
.minor-name {
  color: #303133;
  font-weight: 500;
}

.no-minor {
  color: #c0c4cc;
  font-style: italic;
}

.department-info {
  line-height: 1.4;
  font-size: 12px;
}

.dept-code {
  color: #909399;
  font-size: 11px;
}

.dept-name {
  font-weight: 500;
  color: #303133;
  margin-top: 2px;
}

.event-summary {
  line-height: 1.4;
  text-align: center;
}

.total-count {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.status-counts {
  display: flex;
  justify-content: center;
  gap: 4px;
  margin-top: 4px;
}

.event-detail {
  font-size: 13px;
}

.event-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
  padding-bottom: 6px;
  border-bottom: 1px solid #e1e4e8;
}

.event-row {
  display: flex;
  gap: 16px;
  margin-bottom: 8px;
}

.event-cell {
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.event-label {
  font-weight: 500;
  color: #606266;
}

.event-value {
  font-weight: 600;
  padding: 2px 6px;
  border-radius: 3px;
}

.event-value.total {
  color: #409eff;
  background: #ecf5ff;
}

.event-value.pending {
  color: #e6a23c;
  background: #fdf6ec;
}

.event-value.handling {
  color: #409eff;
  background: #ecf5ff;
}

.event-value.completed {
  color: #67c23a;
  background: #f0f9eb;
}

.event-value.rejected {
  color: #f56c6c;
  background: #fef0f0;
}

.event-value.level1 {
  color: #f56c6c;
  background: #fef0f0;
  font-weight: 700;
}

.event-chart {
  margin-top: 12px;
  padding-top: 8px;
  border-top: 1px dashed #e1e4e8;
}

.chart-title {
  font-size: 12px;
  font-weight: 600;
  color: #606266;
  margin-bottom: 8px;
  text-align: center;
}

.chart-bar {
  height: 20px;
  background: #f5f7fa;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
  margin-bottom: 8px;
}

.bar-segment {
  height: 100%;
  transition: all 0.3s ease;
}

.bar-segment.pending {
  background: #e6a23c;
}

.bar-segment.handling {
  background: #409eff;
}

.bar-segment.completed {
  background: #67c23a;
}

.bar-segment.rejected {
  background: #f56c6c;
}

.chart-legend {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  justify-content: center;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 4px;
}

.legend-color {
  width: 12px;
  height: 12px;
  border-radius: 2px;
}

.legend-color.pending {
  background: #e6a23c;
}

.legend-color.handling {
  background: #409eff;
}

.legend-color.completed {
  background: #67c23a;
}

.legend-color.rejected {
  background: #f56c6c;
}

.legend-text {
  font-size: 11px;
  color: #606266;
}

.no-data-chart {
  text-align: center;
  color: #c0c4cc;
  font-style: italic;
  font-size: 12px;
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

:deep(.el-input-number) {
  width: 100%;
}
</style>
