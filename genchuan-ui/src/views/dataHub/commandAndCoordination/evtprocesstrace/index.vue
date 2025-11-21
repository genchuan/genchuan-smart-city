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
        <!-- 回溯基础信息 -->
        <div class="form-section">
          <div class="section-title">回溯基础信息</div>
          <div class="form-row">
            <el-form-item label="回溯ID" prop="traceId">
              <el-input
                v-model="queryParams.traceId"
                placeholder="请输入回溯ID"
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
          <div class="form-row">
            <el-form-item label="事件类型名称" prop="evtTypeName">
              <el-input
                v-model="queryParams.evtTypeName"
                placeholder="请输入事件类型名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 处理环节信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">处理环节信息</div>
          <div class="form-row">
            <el-form-item label="接报环节" prop="rptLink">
              <el-input
                v-model="queryParams.rptLink"
                placeholder="请输入接报环节"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="研判环节" prop="analysisLink">
              <el-input
                v-model="queryParams.analysisLink"
                placeholder="请输入研判环节"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分拨环节" prop="allocateLink">
              <el-input
                v-model="queryParams.allocateLink"
                placeholder="请输入分拨环节"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="工单环节" prop="woLink">
              <el-input
                v-model="queryParams.woLink"
                placeholder="请输入工单环节"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="调度环节" prop="dispatchLink">
              <el-input
                v-model="queryParams.dispatchLink"
                placeholder="请输入调度环节"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 回溯人信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">回溯人信息</div>
          <div class="form-row">
            <el-form-item label="回溯人ID" prop="traceUserId">
              <el-input
                v-model="queryParams.traceUserId"
                placeholder="请输入回溯人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="回溯人姓名" prop="traceUserName">
              <el-input
                v-model="queryParams.traceUserName"
                placeholder="请输入回溯人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="回溯时间" prop="traceTime">
              <el-date-picker
                v-model="queryParams.traceTime"
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
            <el-form-item label="回溯报告路径" prop="rptPath">
              <el-input
                v-model="queryParams.rptPath"
                placeholder="请输入回溯报告路径"
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
            v-hasPermi="['datacenter:evt-process-trace:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:evt-process-trace:export']"
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
          <span class="card-title">事件过程回溯列表</span>
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
        <el-table-column label="回溯信息" align="center" min-width="180">
          <template #default="scope">
            <div class="trace-info">
              <div class="trace-id">回溯ID: {{ scope.row.traceId }}</div>
              <div class="trace-time">{{ dateFormatter(scope.row.traceTime) }}</div>
              <div v-if="scope.row.rptPath" class="rpt-path">
                <Icon icon="ep:document" class="mr-1" />
                报告路径
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="事件信息" align="center" min-width="200">
          <template #default="scope">
            <div class="event-info">
              <div class="event-code">{{ scope.row.evtCode }}</div>
              <div class="event-name">{{ scope.row.evtName }}</div>
              <div class="event-type">{{ scope.row.evtTypeName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="处理环节" align="center" min-width="220">
          <template #default="scope">
            <div class="process-links">
              <div class="link-row">
                <div class="link-item" :class="{ 'has-content': scope.row.rptLink }">
                  <span class="link-label">接报:</span>
                  <span class="link-content">{{ scope.row.rptLink || '无' }}</span>
                </div>
                <div class="link-item" :class="{ 'has-content': scope.row.analysisLink }">
                  <span class="link-label">研判:</span>
                  <span class="link-content">{{ scope.row.analysisLink || '无' }}</span>
                </div>
              </div>
              <div class="link-row">
                <div class="link-item" :class="{ 'has-content': scope.row.allocateLink }">
                  <span class="link-label">分拨:</span>
                  <span class="link-content">{{ scope.row.allocateLink || '无' }}</span>
                </div>
                <div class="link-item" :class="{ 'has-content': scope.row.woLink }">
                  <span class="link-label">工单:</span>
                  <span class="link-content">{{ scope.row.woLink || '无' }}</span>
                </div>
              </div>
              <div class="link-row">
                <div class="link-item" :class="{ 'has-content': scope.row.dispatchLink }">
                  <span class="link-label">调度:</span>
                  <span class="link-content">{{ scope.row.dispatchLink || '无' }}</span>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="回溯人" align="center" width="140">
          <template #default="scope">
            <div class="trace-user-info">
              <div class="trace-user-name">{{ scope.row.traceUserName }}</div>
              <div class="trace-user-id">ID: {{ scope.row.traceUserId }}</div>
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
              v-hasPermi="['datacenter:evt-process-trace:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:evt-process-trace:delete']"
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
  <EvtProcessTraceForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EvtProcessTraceApi, EvtProcessTraceVO } from '@/api/dataHub/commandAndCoordination/evtprocesstrace'
import EvtProcessTraceForm from './EvtProcessTraceForm.vue'

/** 事件过程回溯 列表 */
defineOptions({ name: 'EvtProcessTrace' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EvtProcessTraceVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  traceId: undefined,
  evtCode: undefined,
  evtName: undefined,
  evtTypeName: undefined,
  rptLink: undefined,
  analysisLink: undefined,
  allocateLink: undefined,
  woLink: undefined,
  dispatchLink: undefined,
  traceUserId: undefined,
  traceUserName: undefined,
  traceTime: [],
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

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvtProcessTraceApi.getEvtProcessTracePage(queryParams)
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
    await EvtProcessTraceApi.deleteEvtProcessTrace(id)
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
    const data = await EvtProcessTraceApi.exportEvtProcessTrace(queryParams)
    download.excel(data, '事件过程回溯.xls')
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
.trace-info {
  line-height: 1.4;
  text-align: left;
}

.trace-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.trace-time {
  font-weight: 500;
  color: #303133;
  font-size: 13px;
  margin-bottom: 4px;
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

.event-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.event-type {
  font-size: 12px;
  color: #409eff;
  background: #ecf5ff;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
}

.process-links {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.link-row {
  display: flex;
  margin-bottom: 6px;
}

.link-item {
  flex: 1;
  display: flex;
  align-items: center;
  padding: 4px 8px;
  border-radius: 4px;
  background: #f5f7fa;
  margin-right: 8px;
}

.link-item:last-child {
  margin-right: 0;
}

.link-item.has-content {
  background: #f0f9ff;
  border-left: 3px solid #409eff;
}

.link-label {
  color: #909399;
  min-width: 32px;
  margin-right: 4px;
  font-weight: 500;
}

.link-content {
  color: #303133;
  flex: 1;
}

.trace-user-info {
  line-height: 1.4;
  text-align: center;
}

.trace-user-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.trace-user-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
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
