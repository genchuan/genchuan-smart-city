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
            <el-form-item label="事件ID" prop="monEvtId">
              <el-input
                v-model="queryParams.monEvtId"
                placeholder="请输入事件ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件标识码" prop="evtCode">
              <el-input
                v-model="queryParams.evtCode"
                placeholder="请输入事件标识码"
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
            <el-form-item label="事件分类ID" prop="evtCatId">
              <el-input
                v-model="queryParams.evtCatId"
                placeholder="请输入事件分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件等级" prop="evtLevel">
              <el-select
                v-model="queryParams.evtLevel"
                placeholder="请选择事件等级"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="低" value="1" />
                <el-option label="中" value="2" />
                <el-option label="高" value="3" />
                <el-option label="紧急" value="4" />
              </el-select>
            </el-form-item>
            <el-form-item label="处置状态" prop="handleStatus">
              <el-select
                v-model="queryParams.handleStatus"
                placeholder="请选择处置状态"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="待处理" value="1" />
                <el-option label="处理中" value="2" />
                <el-option label="已处理" value="3" />
                <el-option label="已关闭" value="4" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 关联部件信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">关联部件信息</div>
          <div class="form-row">
            <el-form-item label="关联部件ID" prop="relCompId">
              <el-input
                v-model="queryParams.relCompId"
                placeholder="请输入关联部件ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联部件名称" prop="relCompName">
              <el-input
                v-model="queryParams.relCompName"
                placeholder="请输入关联部件名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 位置信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">位置信息</div>
          <div class="form-row">
            <el-form-item label="事发位置" prop="incidentPos">
              <el-input
                v-model="queryParams.incidentPos"
                placeholder="请输入事发位置"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="事发坐标X" prop="incidentX">
              <el-input
                v-model="queryParams.incidentX"
                placeholder="请输入事发坐标X"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事发坐标Y" prop="incidentY">
              <el-input
                v-model="queryParams.incidentY"
                placeholder="请输入事发坐标Y"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 系统时间 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">系统时间</div>
          <div class="form-row">
            <el-form-item label="系统创建时间" prop="createTimeSys">
              <el-date-picker
                v-model="queryParams.createTimeSys"
                value-format="YYYY-MM-DD"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                clearable
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="系统更新时间" prop="updateTimeSys">
              <el-date-picker
                v-model="queryParams.updateTimeSys"
                value-format="YYYY-MM-DD"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                clearable
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
            v-hasPermi="['datacenter:mon-evt-info:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-evt-info:export']"
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
          <span class="card-title">监测事件信息列表</span>
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
        <el-table-column label="事件ID" align="center" prop="monEvtId" width="100" sortable="custom" />
        <el-table-column label="事件标识码" align="center" prop="evtCode" width="130" />
        <el-table-column label="事件名称" align="center" prop="evtName" min-width="150" show-overflow-tooltip />
        <el-table-column label="事件分类ID" align="center" prop="evtCatId" width="110" />
        <el-table-column label="状态与等级" align="center" width="140">
          <template #default="scope">
            <div class="status-level">
              <el-tag
                :type="getLevelTagType(scope.row.evtLevel)"
                size="small"
                class="level-tag"
              >
                {{ getLevelText(scope.row.evtLevel) }}
              </el-tag>
              <el-tag
                :type="getStatusTagType(scope.row.handleStatus)"
                size="small"
                class="status-tag"
              >
                {{ getStatusText(scope.row.handleStatus) }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="关联部件" align="center" width="160">
          <template #default="scope">
            <div class="component-info">
              <div class="component-id">ID: {{ scope.row.relCompId }}</div>
              <div class="component-name">{{ scope.row.relCompName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="位置信息" align="center" width="180">
          <template #default="scope">
            <el-popover
              placement="left"
              title="位置信息详情"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看位置</el-button>
              </template>
              <div class="location-detail">
                <div class="location-item">
                  <span class="location-label">事发位置:</span>
                  <span class="location-value">{{ scope.row.incidentPos || '未知位置' }}</span>
                </div>
                <div v-if="scope.row.incidentX && scope.row.incidentY" class="location-item">
                  <span class="location-label">坐标位置:</span>
                  <div class="coord-values">
                    <span class="coord-x">X: {{ scope.row.incidentX }}</span>
                    <span class="coord-y">Y: {{ scope.row.incidentY }}</span>
                  </div>
                </div>
                <div v-else class="location-item">
                  <span class="location-label">坐标位置:</span>
                  <span class="no-coord">无坐标信息</span>
                </div>
                <div class="map-preview" v-if="scope.row.incidentX && scope.row.incidentY">
                  <div class="map-placeholder">
                    <Icon icon="ep:location" class="map-icon" />
                    <span>地图预览</span>
                  </div>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          label="系统创建时间"
          align="center"
          prop="createTimeSys"
          :formatter="dateFormatter"
          width="160"
          sortable="custom"
        />
        <el-table-column
          label="系统更新时间"
          align="center"
          prop="updateTimeSys"
          :formatter="dateFormatter"
          width="160"
          sortable="custom"
        />
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-info:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-info:delete']"
            >
              删除
            </el-button>
            <el-button
              size="small"
              type="success"
              link
              @click="handleViewDetail(scope.row)"
              v-hasPermi="['datacenter:mon-evt-info:query']"
            >
              详情
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
  <MonEvtInfoForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MonEvtInfoApi, MonEvtInfoVO } from '@/api/dataHub/managedComponent/monevtinfo'
import MonEvtInfoForm from './MonEvtInfoForm.vue'

/** 监测事件信息 列表 */
defineOptions({ name: 'MonEvtInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonEvtInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  monEvtId: undefined,
  evtCode: undefined,
  evtName: undefined,
  evtCatId: undefined,
  relCompId: undefined,
  relCompName: undefined,
  incidentPos: undefined,
  incidentX: undefined,
  incidentY: undefined,
  evtLevel: undefined,
  handleStatus: undefined,
  createTimeSys: [],
  updateTimeSys: [],
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取事件等级标签样式 */
const getLevelTagType = (level: string) => {
  const levelMap: Record<string, string> = {
    '1': '',      // 低 - 默认
    '2': 'info',  // 中 - 信息
    '3': 'warning', // 高 - 警告
    '4': 'danger'   // 紧急 - 危险
  }
  return levelMap[level] || ''
}

/** 获取事件等级文本 */
const getLevelText = (level: string) => {
  const levelTextMap: Record<string, string> = {
    '1': '低',
    '2': '中',
    '3': '高',
    '4': '紧急'
  }
  return levelTextMap[level] || level || '未知'
}

/** 获取处置状态标签样式 */
const getStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    '1': 'warning',  // 待处理 - 警告
    '2': 'primary',  // 处理中 - 主要
    '3': 'success',  // 已处理 - 成功
    '4': 'info'      // 已关闭 - 信息
  }
  return statusMap[status] || ''
}

/** 获取处置状态文本 */
const getStatusText = (status: string) => {
  const statusTextMap: Record<string, string> = {
    '1': '待处理',
    '2': '处理中',
    '3': '已处理',
    '4': '已关闭'
  }
  return statusTextMap[status] || status || '未知'
}

/** 查看详情 */
const handleViewDetail = (row: any) => {
  // 这里可以打开详情弹窗或跳转到详情页面
  message.success(`查看事件详情: ${row.evtName}`)
  // 实际项目中可以调用详情弹窗组件
  // detailRef.value.open(row.id)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonEvtInfoApi.getMonEvtInfoPage(queryParams)
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
    await MonEvtInfoApi.deleteMonEvtInfo(id)
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
    const data = await MonEvtInfoApi.exportMonEvtInfo(queryParams)
    download.excel(data, '监测事件信息.xls')
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
.status-level {
  display: flex;
  flex-direction: column;
  gap: 4px;
  align-items: center;
}

.level-tag,
.status-tag {
  width: 60px;
  justify-content: center;
}

.component-info {
  line-height: 1.4;
  font-size: 12px;
}

.component-id {
  color: #909399;
  font-size: 11px;
}

.component-name {
  font-weight: 500;
  color: #303133;
  margin-top: 2px;
}

.location-detail {
  font-size: 13px;
  line-height: 1.6;
}

.location-item {
  margin-bottom: 10px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #f0f0f0;
}

.location-label {
  font-weight: 500;
  color: #606266;
  display: block;
  margin-bottom: 4px;
}

.location-value {
  color: #303133;
  word-break: break-all;
}

.coord-values {
  display: flex;
  gap: 12px;
}

.coord-x,
.coord-y {
  color: #409eff;
  background: #ecf5ff;
  padding: 4px 8px;
  border-radius: 4px;
  font-family: monospace;
}

.no-coord {
  color: #c0c4cc;
  font-style: italic;
}

.map-preview {
  margin-top: 8px;
  border: 1px solid #e1e4e8;
  border-radius: 4px;
  overflow: hidden;
}

.map-placeholder {
  height: 80px;
  background: #f5f7fa;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #909399;
  font-size: 12px;
}

.map-icon {
  font-size: 20px;
  margin-bottom: 4px;
  color: #409eff;
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
</style>
