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
            <el-form-item label="分拨ID" prop="allocateId">
              <el-input
                v-model="queryParams.allocateId"
                placeholder="请输入分拨ID"
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
            <el-form-item label="事件类型" prop="evtTypeName">
              <el-input
                v-model="queryParams.evtTypeName"
                placeholder="请输入事件类型名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 分拨信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">分拨信息</div>
          <div class="form-row">
            <el-form-item label="分拨部门" prop="allocateDeptName">
              <el-input
                v-model="queryParams.allocateDeptName"
                placeholder="请输入分拨部门名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分拨人" prop="allocatorName">
              <el-input
                v-model="queryParams.allocatorName"
                placeholder="请输入分拨人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="紧急程度" prop="urgencyLevel">
              <el-select
                v-model="queryParams.urgencyLevel"
                placeholder="请选择紧急程度"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="紧急" value="紧急" />
                <el-option label="重要" value="重要" />
                <el-option label="一般" value="一般" />
                <el-option label="低" value="低" />
              </el-select>
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="分拨时间" prop="allocateTime">
              <el-date-picker
                v-model="queryParams.allocateTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="分拨说明" prop="allocateDesc">
              <el-input
                v-model="queryParams.allocateDesc"
                placeholder="请输入分拨说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 详细信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">详细信息</div>
          <div class="form-row">
            <el-form-item label="事件类型ID" prop="evtTypeId">
              <el-input
                v-model="queryParams.evtTypeId"
                placeholder="请输入事件类型ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部门代码" prop="allocateDeptCode">
              <el-input
                v-model="queryParams.allocateDeptCode"
                placeholder="请输入分拨部门代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分拨人ID" prop="allocatorId">
              <el-input
                v-model="queryParams.allocatorId"
                placeholder="请输入分拨人ID"
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
            v-hasPermi="['datacenter:evt-classify-allocate:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:evt-classify-allocate:export']"
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
          <span class="card-title">事件分级分拨列表</span>
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
              <div class="allocate-id">分拨ID: {{ scope.row.allocateId }}</div>
              <div class="event-type">{{ scope.row.evtTypeName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分拨信息" align="center" width="180">
          <template #default="scope">
            <div class="allocate-info">
              <div class="dept-name">{{ scope.row.allocateDeptName }}</div>
              <div class="allocator-name">分拨人: {{ scope.row.allocatorName }}</div>
              <div class="allocate-time">{{ dateFormatter(scope.row, {}, scope.row.allocateTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" width="100">
          <template #default="scope">
            <div class="status-info">
              <el-tag
                :type="getUrgencyLevelType(scope.row.urgencyLevel)"
                size="small"
              >
                {{ scope.row.urgencyLevel || '未知' }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分拨说明" align="center" width="200">
          <template #default="scope">
            <div class="allocate-desc">
              {{ scope.row.allocateDesc || '无说明信息' }}
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
                  <span class="field-label">事件类型ID:</span>
                  <span class="field-value">{{ scope.row.evtTypeId || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">部门代码:</span>
                  <span class="field-value">{{ scope.row.allocateDeptCode || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">分拨人ID:</span>
                  <span class="field-value">{{ scope.row.allocatorId || '无' }}</span>
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
              v-hasPermi="['datacenter:evt-classify-allocate:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:evt-classify-allocate:delete']"
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
  <EvtClassifyAllocateForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EvtClassifyAllocateApi, EvtClassifyAllocateVO } from '@/api/dataHub/commandAndCoordination/evtclassifyallocate'
import EvtClassifyAllocateForm from './EvtClassifyAllocateForm.vue'

/** 事件分级分拨 列表 */
defineOptions({ name: 'EvtClassifyAllocate' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EvtClassifyAllocateVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  allocateId: undefined,
  evtCode: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  urgencyLevel: undefined,
  allocateDeptCode: undefined,
  allocateDeptName: undefined,
  allocatorId: undefined,
  allocatorName: undefined,
  allocateTime: [],
  allocateDesc: undefined,
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
    const data = await EvtClassifyAllocateApi.getEvtClassifyAllocatePage(queryParams)
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
    await EvtClassifyAllocateApi.deleteEvtClassifyAllocate(id)
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
    const data = await EvtClassifyAllocateApi.exportEvtClassifyAllocate(queryParams)
    download.excel(data, '事件分级分拨.xls')
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

.allocate-id {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.event-type {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.allocate-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.dept-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.allocator-name {
  color: #606266;
  margin-bottom: 4px;
}

.allocate-time {
  color: #909399;
}

.status-info {
  display: flex;
  justify-content: center;
}

.allocate-desc {
  line-height: 1.4;
  font-size: 13px;
  color: #606266;
  text-align: left;
  display: -webkit-box;
  -webkit-line-clamp: 3;
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
  min-width: 80px;
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
</style>
