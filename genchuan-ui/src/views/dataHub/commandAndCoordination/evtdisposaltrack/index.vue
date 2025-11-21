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
            <el-form-item label="跟踪ID" prop="trackId">
              <el-input
                v-model="queryParams.trackId"
                placeholder="请输入跟踪ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="工单ID" prop="woId">
              <el-input
                v-model="queryParams.woId"
                placeholder="请输入工单ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="处置状态" prop="handleStatus">
              <el-select
                v-model="queryParams.handleStatus"
                placeholder="请选择处置状态"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="未开始" value="未开始" />
                <el-option label="进行中" value="进行中" />
                <el-option label="已完成" value="已完成" />
                <el-option label="已暂停" value="已暂停" />
                <el-option label="已取消" value="已取消" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 处置信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">处置信息</div>
          <div class="form-row">
            <el-form-item label="当前节点" prop="currentNode">
              <el-input
                v-model="queryParams.currentNode"
                placeholder="请输入当前处置节点"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="处置人员" prop="handlerName">
              <el-input
                v-model="queryParams.handlerName"
                placeholder="请输入处置人员"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 时间信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">时间信息</div>
          <div class="form-row">
            <el-form-item label="处置开始时间" prop="startTime">
              <el-date-picker
                v-model="queryParams.startTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="预计完成时间" prop="estCompleteTime">
              <el-date-picker
                v-model="queryParams.estCompleteTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
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
            v-hasPermi="['datacenter:evt-disposal-track:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:evt-disposal-track:export']"
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
          <span class="card-title">事件处置跟踪列表</span>
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
        <el-table-column label="跟踪信息" align="center" min-width="200">
          <template #default="scope">
            <div class="track-info">
              <div class="track-id">{{ scope.row.trackId }}</div>
              <div class="wo-id">工单ID: {{ scope.row.woId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="处置状态" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getHandleStatusType(scope.row.handleStatus)"
              size="small"
            >
              {{ scope.row.handleStatus || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="处置节点" align="center" width="150">
          <template #default="scope">
            <div class="node-info">
              <div class="current-node">{{ scope.row.currentNode }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="处置人员" align="center" width="120">
          <template #default="scope">
            <div class="handler-info">
              <div class="handler-name">{{ scope.row.handlerName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="时间信息" align="center" width="200">
          <template #default="scope">
            <div class="time-info">
              <div class="start-time">
                开始: {{ dateFormatter(scope.row, {}, scope.row.startTime) }}
              </div>
              <div class="est-complete-time">
                预计完成: {{ dateFormatter(scope.row, {}, scope.row.estCompleteTime) }}
              </div>
              <div class="time-progress" v-if="scope.row.startTime && scope.row.estCompleteTime">
                <el-progress
                  :percentage="calculateProgress(scope.row.startTime, scope.row.estCompleteTime)"
                  :status="getProgressStatus(scope.row.handleStatus)"
                  :show-text="false"
                  class="mt-5px"
                />
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
              v-hasPermi="['datacenter:evt-disposal-track:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:evt-disposal-track:delete']"
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
  <EvtDisposalTrackForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EvtDisposalTrackApi, EvtDisposalTrackVO } from '@/api/dataHub/commandAndCoordination/evtdisposaltrack'
import EvtDisposalTrackForm from './EvtDisposalTrackForm.vue'

/** 事件处置跟踪 列表 */
defineOptions({ name: 'EvtDisposalTrack' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EvtDisposalTrackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  trackId: undefined,
  woId: undefined,
  handleStatus: undefined,
  currentNode: undefined,
  handlerName: undefined,
  startTime: [],
  estCompleteTime: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取处置状态标签类型 */
const getHandleStatusType = (status: string) => {
  const statusMap: Record<string, string> = {
    '未开始': 'info',
    '进行中': 'primary',
    '已完成': 'success',
    '已暂停': 'warning',
    '已取消': 'danger'
  }
  return statusMap[status] || 'info'
}

/** 获取进度条状态 */
const getProgressStatus = (status: string) => {
  const statusMap: Record<string, any> = {
    '已完成': 'success',
    '已取消': 'exception',
    '已暂停': 'warning'
  }
  return statusMap[status] || undefined
}

/** 计算进度百分比 */
const calculateProgress = (startTime: string, estCompleteTime: string) => {
  if (!startTime || !estCompleteTime) return 0

  const start = new Date(startTime).getTime()
  const end = new Date(estCompleteTime).getTime()
  const now = new Date().getTime()

  if (now >= end) return 100
  if (now <= start) return 0

  const total = end - start
  const elapsed = now - start
  return Math.min(Math.round((elapsed / total) * 100), 100)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvtDisposalTrackApi.getEvtDisposalTrackPage(queryParams)
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
    await EvtDisposalTrackApi.deleteEvtDisposalTrack(id)
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
    const data = await EvtDisposalTrackApi.exportEvtDisposalTrack(queryParams)
    download.excel(data, '事件处置跟踪.xls')
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
.track-info {
  line-height: 1.4;
  text-align: left;
}

.track-id {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.wo-id {
  font-size: 12px;
  color: #909399;
}

.node-info {
  line-height: 1.4;
  text-align: center;
}

.current-node {
  font-weight: 500;
  color: #303133;
  font-size: 14px;
}

.handler-info {
  line-height: 1.4;
  text-align: center;
}

.handler-name {
  font-weight: 500;
  color: #303133;
  font-size: 14px;
}

.time-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.start-time {
  color: #606266;
  margin-bottom: 4px;
}

.est-complete-time {
  color: #e6a23c;
  margin-bottom: 4px;
}

.time-progress {
  margin-top: 8px;
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

:deep(.el-progress) {
  width: 100%;
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
