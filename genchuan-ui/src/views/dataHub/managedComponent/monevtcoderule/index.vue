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
            <el-form-item label="规则ID" prop="monEvtRuleId">
              <el-input
                v-model="queryParams.monEvtRuleId"
                placeholder="请输入规则ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="规则名称" prop="ruleName">
              <el-input
                v-model="queryParams.ruleName"
                placeholder="请输入规则名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="启用状态" prop="enableStatus">
              <el-select
                v-model="queryParams.enableStatus"
                placeholder="请选择启用状态"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="启用" value="1" />
                <el-option label="禁用" value="0" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 代码位数配置 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">代码位数配置</div>
          <div class="code-length-config">
            <div class="form-row">
              <el-form-item label="行政代码位数" prop="adminCodeLen">
                <el-input-number
                  v-model="queryParams.adminCodeLen"
                  placeholder="行政代码位数"
                  :min="0"
                  :max="10"
                  controls-position="right"
                  class="!w-200px"
                />
              </el-form-item>
              <el-form-item label="大类代码位数" prop="majorCodeLen">
                <el-input-number
                  v-model="queryParams.majorCodeLen"
                  placeholder="大类代码位数"
                  :min="0"
                  :max="10"
                  controls-position="right"
                  class="!w-200px"
                />
              </el-form-item>
              <el-form-item label="中类代码位数" prop="midCodeLen">
                <el-input-number
                  v-model="queryParams.midCodeLen"
                  placeholder="中类代码位数"
                  :min="0"
                  :max="10"
                  controls-position="right"
                  class="!w-200px"
                />
              </el-form-item>
            </div>
            <div class="form-row">
              <el-form-item label="小类代码位数" prop="minorCodeLen">
                <el-input-number
                  v-model="queryParams.minorCodeLen"
                  placeholder="小类代码位数"
                  :min="0"
                  :max="10"
                  controls-position="right"
                  class="!w-200px"
                />
              </el-form-item>
              <el-form-item label="顺序码位数" prop="seqCodeLen">
                <el-input-number
                  v-model="queryParams.seqCodeLen"
                  placeholder="顺序码位数"
                  :min="0"
                  :max="10"
                  controls-position="right"
                  class="!w-200px"
                />
              </el-form-item>
            </div>
            <div class="form-row">
              <el-form-item label="顺序码生成规则" prop="seqGenRule">
                <el-input
                  v-model="queryParams.seqGenRule"
                  placeholder="请输入顺序码生成规则"
                  clearable
                  @keyup.enter="handleQuery"
                  class="!w-480px"
                />
              </el-form-item>
            </div>
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
            v-hasPermi="['datacenter:mon-evt-code-rule:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-evt-code-rule:export']"
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
          <span class="card-title">监测事件标识码规则列表</span>
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
        <el-table-column label="规则ID" align="center" prop="monEvtRuleId" width="100" sortable="custom" />
        <el-table-column label="规则名称" align="center" prop="ruleName" min-width="150" show-overflow-tooltip />
        <el-table-column label="代码位数配置" align="center" width="180">
          <template #default="scope">
            <el-popover
              placement="left"
              title="代码位数配置详情"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看配置</el-button>
              </template>
              <div class="code-config-detail">
                <div class="config-item">
                  <span class="config-label">行政代码:</span>
                  <span class="config-value">{{ scope.row.adminCodeLen || 0 }} 位</span>
                </div>
                <div class="config-item">
                  <span class="config-label">大类代码:</span>
                  <span class="config-value">{{ scope.row.majorCodeLen || 0 }} 位</span>
                </div>
                <div class="config-item">
                  <span class="config-label">中类代码:</span>
                  <span class="config-value">{{ scope.row.midCodeLen || 0 }} 位</span>
                </div>
                <div class="config-item">
                  <span class="config-label">小类代码:</span>
                  <span class="config-value">{{ scope.row.minorCodeLen || 0 }} 位</span>
                </div>
                <div class="config-item">
                  <span class="config-label">顺序码:</span>
                  <span class="config-value">{{ scope.row.seqCodeLen || 0 }} 位</span>
                </div>
                <div v-if="scope.row.seqGenRule" class="config-item full-width">
                  <span class="config-label">生成规则:</span>
                  <span class="config-value rule-desc">{{ scope.row.seqGenRule }}</span>
                </div>
                <div class="total-length">
                  总长度: <span class="total-number">{{ calculateTotalLength(scope.row) }}</span> 位
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="启用状态" align="center" width="90">
          <template #default="scope">
            <el-tag
              :type="scope.row.enableStatus === '1' ? 'success' : 'danger'"
              size="small"
            >
              {{ scope.row.enableStatus === '1' ? '启用' : '禁用' }}
            </el-tag>
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
              v-hasPermi="['datacenter:mon-evt-code-rule:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-evt-code-rule:delete']"
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
  <MonEvtCodeRuleForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MonEvtCodeRuleApi, MonEvtCodeRuleVO } from '@/api/dataHub/managedComponent/monevtcoderule'
import MonEvtCodeRuleForm from './MonEvtCodeRuleForm.vue'

/** 监测事件标识码规则 列表 */
defineOptions({ name: 'MonEvtCodeRule' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonEvtCodeRuleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  monEvtRuleId: undefined,
  ruleName: undefined,
  adminCodeLen: undefined,
  majorCodeLen: undefined,
  midCodeLen: undefined,
  minorCodeLen: undefined,
  seqCodeLen: undefined,
  seqGenRule: undefined,
  enableStatus: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  createTimeSys: [],
  updateTimeSys: [],
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 计算代码总长度 */
const calculateTotalLength = (row: any) => {
  const admin = Number(row.adminCodeLen) || 0
  const major = Number(row.majorCodeLen) || 0
  const mid = Number(row.midCodeLen) || 0
  const minor = Number(row.minorCodeLen) || 0
  const seq = Number(row.seqCodeLen) || 0
  return admin + major + mid + minor + seq
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonEvtCodeRuleApi.getMonEvtCodeRulePage(queryParams)
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
    await MonEvtCodeRuleApi.deleteMonEvtCodeRule(id)
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
    const data = await MonEvtCodeRuleApi.exportMonEvtCodeRule(queryParams)
    download.excel(data, '监测事件标识码规则.xls')
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

.code-length-config {
  display: flex;
  flex-direction: column;
  gap: 12px;
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

.code-config-detail {
  font-size: 13px;
  line-height: 1.6;
}

.config-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  padding-bottom: 4px;
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
}

.config-value {
  color: #303133;
  font-weight: 500;
}

.rule-desc {
  color: #e6a23c;
  background: #fdf6ec;
  padding: 4px 8px;
  border-radius: 4px;
  margin-top: 4px;
  font-size: 12px;
  word-break: break-all;
}

.total-length {
  text-align: center;
  margin-top: 8px;
  padding-top: 8px;
  border-top: 2px solid #409eff;
  font-weight: 600;
  color: #409eff;
}

.total-number {
  font-size: 16px;
  color: #f56c6c;
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
