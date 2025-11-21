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
            <el-form-item label="配置ID" prop="monEvtDataId">
              <el-input
                v-model="queryParams.monEvtDataId"
                placeholder="请输入配置ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件分类ID" prop="evtCatId">
              <el-input
                v-model="queryParams.evtCatId"
                placeholder="请输入事件分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件分类名称" prop="evtCatName">
              <el-input
                v-model="queryParams.evtCatName"
                placeholder="请输入事件分类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 字段配置 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">字段配置</div>
          <div class="form-row">
            <el-form-item label="字段名称" prop="fieldName">
              <el-input
                v-model="queryParams.fieldName"
                placeholder="请输入字段名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="字段代码" prop="fieldCode">
              <el-input
                v-model="queryParams.fieldCode"
                placeholder="请输入字段代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="字段类型" prop="fieldType">
              <el-select
                v-model="queryParams.fieldType"
                placeholder="请选择字段类型"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="字符串" value="string" />
                <el-option label="数字" value="number" />
                <el-option label="日期" value="date" />
                <el-option label="布尔" value="boolean" />
              </el-select>
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="字段长度" prop="fieldLength">
              <el-input-number
                v-model="queryParams.fieldLength"
                placeholder="字段长度"
                :min="0"
                :max="9999"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="约束条件" prop="constraintType">
              <el-select
                v-model="queryParams.constraintType"
                placeholder="请选择约束条件"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="必填" value="required" />
                <el-option label="可选" value="optional" />
                <el-option label="唯一" value="unique" />
              </el-select>
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="值域范围" prop="valueRange">
              <el-input
                v-model="queryParams.valueRange"
                placeholder="请输入值域范围"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="字段说明" prop="fieldDesc">
              <el-input
                v-model="queryParams.fieldDesc"
                placeholder="请输入字段说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 扩展字段 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">扩展字段</div>
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
            <el-form-item label="通用扩展字段1" prop="extCommon1">
              <el-input
                v-model="queryParams.extCommon1"
                placeholder="请输入通用扩展字段1"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="通用扩展字段2" prop="extCommon2">
              <el-input
                v-model="queryParams.extCommon2"
                placeholder="请输入通用扩展字段2"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 操作记录 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">操作记录</div>
          <div class="form-row">
            <el-form-item label="创建人" prop="createUser">
              <el-input
                v-model="queryParams.createUser"
                placeholder="请输入创建人"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="创建时间" prop="createTime">
              <el-date-picker
                v-model="queryParams.createTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="更新人" prop="updateUser">
              <el-input
                v-model="queryParams.updateUser"
                placeholder="请输入更新人"
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
              v-hasPermi="['datacenter:mon-evt-data-cfg:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-evt-data-cfg:export']"
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
          <span class="card-title">监测事件数据配置列表</span>
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
        <el-table-column label="配置ID" align="center" prop="monEvtDataId" width="100" sortable="custom" />
        <el-table-column label="事件分类" align="center" width="150">
          <template #default="scope">
            <div class="category-info">
              <div class="category-id">ID: {{ scope.row.evtCatId }}</div>
              <div class="category-name">{{ scope.row.evtCatName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="字段配置" align="center" min-width="200">
          <template #default="scope">
            <el-popover
              placement="left"
              title="字段配置详情"
              :width="320"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看详情</el-button>
              </template>
              <div class="field-config-detail">
                <div class="config-item">
                  <span class="config-label">字段名称:</span>
                  <span class="config-value">{{ scope.row.fieldName }}</span>
                </div>
                <div class="config-item">
                  <span class="config-label">字段代码:</span>
                  <span class="config-value code">{{ scope.row.fieldCode }}</span>
                </div>
                <div class="config-item">
                  <span class="config-label">字段类型:</span>
                  <el-tag
                    :type="getFieldTypeTagType(scope.row.fieldType)"
                    size="small"
                  >
                    {{ scope.row.fieldType }}
                  </el-tag>
                </div>
                <div class="config-item">
                  <span class="config-label">字段长度:</span>
                  <span class="config-value">{{ scope.row.fieldLength || '无限制' }}</span>
                </div>
                <div class="config-item">
                  <span class="config-label">约束条件:</span>
                  <el-tag
                    :type="getConstraintTagType(scope.row.constraintType)"
                    size="small"
                  >
                    {{ scope.row.constraintType }}
                  </el-tag>
                </div>
                <div v-if="scope.row.valueRange" class="config-item full-width">
                  <span class="config-label">值域范围:</span>
                  <span class="config-value range">{{ scope.row.valueRange }}</span>
                </div>
                <div v-if="scope.row.fieldDesc" class="config-item full-width">
                  <span class="config-label">字段说明:</span>
                  <span class="config-value desc">{{ scope.row.fieldDesc }}</span>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="创建信息" align="center" width="160">
          <template #default="scope">
            <div class="user-info">
              <div class="user-name">{{ scope.row.createUser || '-' }}</div>
              <div class="create-time">{{ dateFormatter(scope.row, scope.column, scope.row.createTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="更新人" align="center" prop="updateUser" width="100" />
        <el-table-column label="扩展字段" align="center" width="100">
          <template #default="scope">
            <el-popover
              placement="left"
              title="扩展字段信息"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看</el-button>
              </template>
              <div class="ext-fields">
                <div v-if="scope.row.extCat1" class="ext-field">
                  <span class="field-label">分类扩展1:</span>
                  <span class="field-value">{{ scope.row.extCat1 }}</span>
                </div>
                <div v-if="scope.row.extCat2" class="ext-field">
                  <span class="field-label">分类扩展2:</span>
                  <span class="field-value">{{ scope.row.extCat2 }}</span>
                </div>
                <div v-if="scope.row.extCommon1" class="ext-field">
                  <span class="field-label">通用扩展1:</span>
                  <span class="field-value">{{ scope.row.extCommon1 }}</span>
                </div>
                <div v-if="scope.row.extCommon2" class="ext-field">
                  <span class="field-label">通用扩展2:</span>
                  <span class="field-value">{{ scope.row.extCommon2 }}</span>
                </div>
                <div v-if="!scope.row.extCat1 && !scope.row.extCat2 && !scope.row.extCommon1 && !scope.row.extCommon2" class="no-ext">
                  无扩展字段数据
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
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-data-cfg:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-data-cfg:delete']"
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
  <MonEvtDataCfgForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MonEvtDataCfgApi, MonEvtDataCfgVO } from '@/api/dataHub/managedComponent/monevtdatacfg'
import MonEvtDataCfgForm from './MonEvtDataCfgForm.vue'

/** 监测事件数据配置 列表 */
defineOptions({ name: 'MonEvtDataCfg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonEvtDataCfgVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  monEvtDataId: undefined,
  evtCatId: undefined,
  evtCatName: undefined,
  fieldName: undefined,
  fieldCode: undefined,
  fieldType: undefined,
  fieldLength: undefined,
  constraintType: undefined,
  valueRange: undefined,
  fieldDesc: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: [],
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取字段类型标签样式 */
const getFieldTypeTagType = (type: string) => {
  const typeMap: Record<string, string> = {
    string: '',
    number: 'success',
    date: 'warning',
    boolean: 'danger'
  }
  return typeMap[type] || 'info'
}

/** 获取约束条件标签样式 */
const getConstraintTagType = (constraint: string) => {
  const constraintMap: Record<string, string> = {
    required: 'danger',
    optional: 'success',
    unique: 'warning'
  }
  return constraintMap[constraint] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonEvtDataCfgApi.getMonEvtDataCfgPage(queryParams)
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
    await MonEvtDataCfgApi.deleteMonEvtDataCfg(id)
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
    const data = await MonEvtDataCfgApi.exportMonEvtDataCfg(queryParams)
    download.excel(data, '监测事件数据配置.xls')
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
.category-info {
  line-height: 1.4;
  font-size: 12px;
}

.category-id {
  color: #909399;
  font-size: 11px;
}

.category-name {
  font-weight: 500;
  color: #303133;
  margin-top: 2px;
}

.user-info {
  line-height: 1.4;
  font-size: 12px;
}

.user-name {
  font-weight: 500;
  color: #303133;
}

.create-time {
  color: #909399;
  margin-top: 2px;
}

.field-config-detail {
  font-size: 13px;
  line-height: 1.6;
}

.config-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
  padding-bottom: 6px;
  border-bottom: 1px dashed #f0f0f0;
}

.config-item.full-width {
  flex-direction: column;
  align-items: flex-start;
}

.config-label {
  font-weight: 500;
  color: #606266;
  min-width: 80px;
  text-align: right;
  margin-right: 12px;
}

.config-value {
  color: #303133;
  font-weight: 500;
  text-align: left;
}

.config-value.code {
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  font-family: monospace;
}

.config-value.range {
  color: #67c23a;
  background: #f0f9eb;
  padding: 4px 8px;
  border-radius: 4px;
  margin-top: 4px;
  word-break: break-all;
}

.config-value.desc {
  color: #606266;
  background: #f4f4f5;
  padding: 6px 8px;
  border-radius: 4px;
  margin-top: 4px;
  font-style: italic;
  word-break: break-all;
}

.ext-fields {
  font-size: 13px;
  line-height: 1.6;
}

.ext-field {
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

.no-ext {
  color: #c0c4cc;
  font-style: italic;
  text-align: center;
  padding: 8px 0;
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
