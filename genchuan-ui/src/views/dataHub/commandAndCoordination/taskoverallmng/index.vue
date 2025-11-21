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
        <!-- 任务基础信息 -->
        <div class="form-section">
          <div class="section-title">任务基础信息</div>
          <div class="form-row">
            <el-form-item label="统筹ID" prop="overallId">
              <el-input
                v-model="queryParams.overallId"
                placeholder="请输入统筹ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="任务编号" prop="taskNo">
              <el-input
                v-model="queryParams.taskNo"
                placeholder="请输入任务编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="任务名称" prop="taskName">
              <el-input
                v-model="queryParams.taskName"
                placeholder="请输入任务名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="任务类型" prop="taskType">
              <el-select
                v-model="queryParams.taskType"
                placeholder="请选择任务类型"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
            <el-form-item label="任务状态" prop="taskStatus">
              <el-select
                v-model="queryParams.taskStatus"
                placeholder="请选择任务状态"
                clearable
                class="!w-200px"
              >
                <el-option label="待执行" value="1" />
                <el-option label="执行中" value="2" />
                <el-option label="已完成" value="3" />
                <el-option label="已取消" value="4" />
                <el-option label="超时" value="5" />
             
              </el-select>
            </el-form-item>
            <el-form-item label="优先级" prop="priority">
              <el-input
                v-model="queryParams.priority"
                placeholder="请输入优先级"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 关联信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">关联信息</div>
          <div class="form-row">
            <el-form-item label="关联方案ID" prop="planId">
              <el-input
                v-model="queryParams.planId"
                placeholder="请输入关联方案ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联指令ID" prop="commandId">
              <el-input
                v-model="queryParams.commandId"
                placeholder="请输入关联指令ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联跨域ID" prop="crossId">
              <el-input
                v-model="queryParams.crossId"
                placeholder="请输入关联跨域ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 负责人信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">负责人信息</div>
          <div class="form-row">
            <el-form-item label="负责人账号" prop="chargerId">
              <el-input
                v-model="queryParams.chargerId"
                placeholder="请输入任务负责人账号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="负责人姓名" prop="chargerName">
              <el-input
                v-model="queryParams.chargerName"
                placeholder="请输入任务负责人姓名"
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
            <el-form-item label="计划完成时间" prop="planCompleteTime">
              <el-date-picker
                v-model="queryParams.planCompleteTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-320px"
              />
            </el-form-item>
            <el-form-item label="实际完成时间" prop="actualCompleteTime">
              <el-date-picker
                v-model="queryParams.actualCompleteTime"
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
            <el-form-item label="延迟时长" prop="delayEndure">
              <el-input
                v-model="queryParams.delayEndure"
                placeholder="请输入延迟时长"
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
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-320px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 系统时间信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">系统时间信息</div>
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
            v-hasPermi="['datacenter:task-overall-mng:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:task-overall-mng:export']"
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
          <span class="card-title">任务统筹管理列表</span>
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
        <el-table-column label="任务信息" align="center" min-width="200">
          <template #default="scope">
            <div class="task-info">
              <div class="task-no">{{ scope.row.taskNo }}</div>
              <div class="task-name">{{ scope.row.taskName }}</div>
              <div class="task-details">
                <span class="overall-id">统筹ID: {{ scope.row.overallId }}</span>
                <span class="task-type">{{ scope.row.taskType }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="关联信息" align="center" width="140">
          <template #default="scope">
            <div class="relation-info">
              <div v-if="scope.row.planId" class="relation-item">
                <span class="relation-label">方案:</span>
                <span class="relation-id">{{ scope.row.planId }}</span>
              </div>
              <div v-if="scope.row.commandId" class="relation-item">
                <span class="relation-label">指令:</span>
                <span class="relation-id">{{ scope.row.commandId }}</span>
              </div>
              <div v-if="scope.row.crossId" class="relation-item">
                <span class="relation-label">跨域:</span>
                <span class="relation-id">{{ scope.row.crossId }}</span>
              </div>
              <div v-if="!scope.row.planId && !scope.row.commandId && !scope.row.crossId" class="no-relation">
                无关联信息
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" width="100">
          <template #default="scope">
            <div class="status-info">
              <el-tag
                :type="getTaskStatusTagType(scope.row.taskStatus)"
                size="small"
              >
                {{ scope.row.taskStatus || '未知' }}
              </el-tag>
              <div class="priority">
                优先级: <span class="priority-level">{{ scope.row.priority }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="负责人" align="center" width="140">
          <template #default="scope">
            <div class="charger-info">
              <div class="charger-name">{{ scope.row.chargerName }}</div>
              <div class="charger-id">账号: {{ scope.row.chargerId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="时间信息" align="center" min-width="180">
          <template #default="scope">
            <div class="time-info">
              <div class="plan-time">
                <span class="time-label">计划:</span>
                <span class="time-value">{{ dateFormatter(scope.row.planCompleteTime) }}</span>
              </div>
              <div class="actual-time">
                <span class="time-label">实际:</span>
                <span class="time-value">{{ dateFormatter(scope.row.actualCompleteTime) || '未完成' }}</span>
              </div>
              <div v-if="scope.row.delayEndure" class="delay-info">
                <span class="delay-label">延迟:</span>
                <span class="delay-value">{{ scope.row.delayEndure }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="系统信息" align="center" width="140">
          <template #default="scope">
            <div class="system-info">
              <div class="create-time-sys">
                创建: {{ dateFormatter(scope.row.createTimeSys) }}
              </div>
              <div class="update-time-sys">
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
              v-hasPermi="['datacenter:task-overall-mng:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:task-overall-mng:delete']"
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
  <TaskOverallMngForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { TaskOverallMngApi, TaskOverallMngVO } from '@/api/dataHub/commandAndCoordination/taskoverallmng'
import TaskOverallMngForm from './TaskOverallMngForm.vue'

/** 任务统筹管理 列表 */
defineOptions({ name: 'TaskOverallMng' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<TaskOverallMngVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  overallId: undefined,
  taskNo: undefined,
  taskName: undefined,
  taskType: undefined,
  planId: undefined,
  commandId: undefined,
  crossId: undefined,
  taskStatus: undefined,
  priority: undefined,
  chargerId: undefined,
  chargerName: undefined,
  planCompleteTime: [],
  actualCompleteTime: [],
  delayEndure: undefined,
  createTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取任务状态标签样式 */
const getTaskStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    'pending': 'info',        // 待处理
    'processing': 'primary',  // 处理中
    'completed': 'success',   // 已完成
    'delayed': 'warning',     // 已延迟
    'cancelled': 'danger'     // 已取消
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await TaskOverallMngApi.getTaskOverallMngPage(queryParams)
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
    await TaskOverallMngApi.deleteTaskOverallMng(id)
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
    const data = await TaskOverallMngApi.exportTaskOverallMng(queryParams)
    download.excel(data, '任务统筹管理.xls')
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
.task-info {
  line-height: 1.4;
  text-align: left;
}

.task-no {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.task-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.task-details {
  display: flex;
  flex-direction: column;
  gap: 2px;
  font-size: 12px;
  color: #909399;
}

.overall-id {
  font-family: monospace;
}

.task-type {
  color: #409eff;
  font-weight: 500;
}

.relation-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.relation-item {
  margin-bottom: 4px;
}

.relation-label {
  color: #909399;
  margin-right: 4px;
}

.relation-id {
  color: #303133;
  font-family: monospace;
  font-weight: 500;
}

.no-relation {
  color: #c0c4cc;
  font-style: italic;
  text-align: center;
}

.status-info {
  line-height: 1.4;
  text-align: center;
}

.priority {
  font-size: 12px;
  color: #606266;
  margin-top: 4px;
}

.priority-level {
  color: #e6a23c;
  font-weight: 600;
}

.charger-info {
  line-height: 1.4;
  text-align: center;
}

.charger-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.charger-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.time-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.plan-time, .actual-time, .delay-info {
  display: flex;
  margin-bottom: 4px;
}

.time-label, .delay-label {
  color: #909399;
  min-width: 32px;
  margin-right: 4px;
}

.time-value {
  color: #303133;
  font-weight: 500;
}

.delay-value {
  color: #f56c6c;
  font-weight: 600;
}

.system-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.create-time-sys, .update-time-sys {
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
</style>
