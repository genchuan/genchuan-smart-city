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
            <el-form-item label="接报ID" prop="rptId">
              <el-input
                v-model="queryParams.rptId"
                placeholder="请输入接报ID"
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

        <!-- 位置信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">位置信息</div>
          <div class="form-row">
            <el-form-item label="事发区域" prop="incidentRegionName">
              <el-input
                v-model="queryParams.incidentRegionName"
                placeholder="请输入事发区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事发位置" prop="incidentLocation">
              <el-input
                v-model="queryParams.incidentLocation"
                placeholder="请输入事发位置"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="坐标X" prop="incidentCoordX">
              <el-input
                v-model="queryParams.incidentCoordX"
                placeholder="请输入事发坐标X"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="坐标Y" prop="incidentCoordY">
              <el-input
                v-model="queryParams.incidentCoordY"
                placeholder="请输入事发坐标Y"
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
            <el-form-item label="事件描述" prop="evtDesc">
              <el-input
                v-model="queryParams.evtDesc"
                placeholder="请输入事件描述"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
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
            <el-form-item label="区域代码" prop="incidentRegionCode">
              <el-input
                v-model="queryParams.incidentRegionCode"
                placeholder="请输入事发区域代码"
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
            v-hasPermi="['datacenter:evt-rpt-reg:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:evt-rpt-reg:export']"
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
          <span class="card-title">事件接报登记列表</span>
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
              <div class="rpt-id">接报ID: {{ scope.row.rptId }}</div>
              <div class="event-type">{{ scope.row.evtTypeName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="位置信息" align="center" width="180">
          <template #default="scope">
            <div class="location-info">
              <div class="region-name">{{ scope.row.incidentRegionName }}</div>
              <div class="incident-location">{{ scope.row.incidentLocation }}</div>
              <div v-if="scope.row.incidentCoordX || scope.row.incidentCoordY" class="coordinates">
                坐标: {{ scope.row.incidentCoordX }}, {{ scope.row.incidentCoordY }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="事件描述" align="center" width="200">
          <template #default="scope">
            <div class="event-desc">
              {{ scope.row.evtDesc || '无描述信息' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="详细信息" align="center" width="120">
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
                  <span class="field-label">区域代码:</span>
                  <span class="field-value">{{ scope.row.incidentRegionCode || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">坐标X:</span>
                  <span class="field-value">{{ scope.row.incidentCoordX || '无' }}</span>
                </div>
                <div class="detail-field">
                  <span class="field-label">坐标Y:</span>
                  <span class="field-value">{{ scope.row.incidentCoordY || '无' }}</span>
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
              v-hasPermi="['datacenter:evt-rpt-reg:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:evt-rpt-reg:delete']"
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
  <EvtRptRegForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import download from '@/utils/download'
import { EvtRptRegApi, EvtRptRegVO } from '@/api/dataHub/commandAndCoordination/evtrptreg'
import EvtRptRegForm from './EvtRptRegForm.vue'

/** 事件接报登记 列表 */
defineOptions({ name: 'EvtRptReg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EvtRptRegVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  evtCode: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  incidentRegionCode: undefined,
  incidentRegionName: undefined,
  incidentLocation: undefined,
  incidentCoordX: undefined,
  incidentCoordY: undefined,
  evtDesc: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvtRptRegApi.getEvtRptRegPage(queryParams)
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
    await EvtRptRegApi.deleteEvtRptReg(id)
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
    const data = await EvtRptRegApi.exportEvtRptReg(queryParams)
    download.excel(data, '事件接报登记.xls')
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

.rpt-id {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.event-type {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.location-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.region-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.incident-location {
  color: #606266;
  margin-bottom: 4px;
}

.coordinates {
  color: #909399;
  font-style: italic;
}

.event-desc {
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
