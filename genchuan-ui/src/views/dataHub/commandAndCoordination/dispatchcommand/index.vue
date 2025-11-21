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
        <!-- 指令基础信息 -->
        <div class="form-section">
          <div class="section-title">指令基础信息</div>
          <div class="form-row">
            <el-form-item label="指令ID" prop="commandId">
              <el-input
                v-model="queryParams.commandId"
                placeholder="请输入指令ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="指令编号" prop="commandNo">
              <el-input
                v-model="queryParams.commandNo"
                placeholder="请输入指令编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="指令状态" prop="commandStatus">
              <el-select
                v-model="queryParams.commandStatus"
                placeholder="请选择指令状态"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 方案关联信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">方案关联信息</div>
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
            <el-form-item label="方案编号" prop="planNo">
              <el-input
                v-model="queryParams.planNo"
                placeholder="请输入方案编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 执行单位信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">执行单位信息</div>
          <div class="form-row">
            <el-form-item label="执行单位ID" prop="execUnitId">
              <el-input
                v-model="queryParams.execUnitId"
                placeholder="请输入执行单位ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="执行单位名称" prop="execUnitName">
              <el-input
                v-model="queryParams.execUnitName"
                placeholder="请输入执行单位名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="执行要求" prop="execRequirement">
              <el-input
                v-model="queryParams.execRequirement"
                placeholder="请输入执行要求"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 下发信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">下发信息</div>
          <div class="form-row">
            <el-form-item label="下发人ID" prop="issueUserId">
              <el-input
                v-model="queryParams.issueUserId"
                placeholder="请输入下发人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="下发人姓名" prop="issueUserName">
              <el-input
                v-model="queryParams.issueUserName"
                placeholder="请输入下发人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="下发时间" prop="issueTime">
              <el-date-picker
                v-model="queryParams.issueTime"
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

        <!-- 接收信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">接收信息</div>
          <div class="form-row">
            <el-form-item label="接收人ID" prop="receiveUserId">
              <el-input
                v-model="queryParams.receiveUserId"
                placeholder="请输入接收人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="接收人姓名" prop="receiveUserName">
              <el-input
                v-model="queryParams.receiveUserName"
                placeholder="请输入接收人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="接收时间" prop="receiveTime">
              <el-date-picker
                v-model="queryParams.receiveTime"
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

        <!-- 执行反馈信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">执行反馈信息</div>
          <div class="form-row">
            <el-form-item label="执行反馈" prop="execFeedback">
              <el-input
                v-model="queryParams.execFeedback"
                placeholder="请输入执行反馈"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="完成时间" prop="completeTime">
              <el-date-picker
                v-model="queryParams.completeTime"
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
            v-hasPermi="['datacenter:dispatch-command:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:dispatch-command:export']"
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
          <span class="card-title">调度指令列表</span>
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
        <el-table-column label="指令信息" align="center" min-width="180">
          <template #default="scope">
            <div class="command-info">
              <div class="command-no">{{ scope.row.commandNo }}</div>
              <div class="command-id">ID: {{ scope.row.commandId }}</div>
              <div v-if="scope.row.taskContent" class="task-content">
                {{ scope.row.taskContent }}
              </div>
              <div v-if="scope.row.execRequirement" class="exec-requirement">
                {{ scope.row.execRequirement }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="方案关联" align="center" width="120">
          <template #default="scope">
            <div class="plan-info">
              <div class="plan-no">{{ scope.row.planNo }}</div>
              <div class="plan-id">ID: {{ scope.row.planId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="执行单位" align="center" width="140">
          <template #default="scope">
            <div class="exec-unit-info">
              <div class="exec-unit-name">{{ scope.row.execUnitName }}</div>
              <div class="exec-unit-id">ID: {{ scope.row.execUnitId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getCommandStatusTagType(scope.row.commandStatus)"
              size="small"
            >
              {{ scope.row.commandStatus || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="下发信息" align="center" width="140">
          <template #default="scope">
            <div class="issue-info">
              <div class="issue-user">{{ scope.row.issueUserName }}</div>
              <div class="issue-time">{{ dateFormatter(scope.row.issueTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="接收信息" align="center" width="140">
          <template #default="scope">
            <div class="receive-info">
              <div class="receive-user">{{ scope.row.receiveUserName }}</div>
              <div class="receive-time">{{ dateFormatter(scope.row.receiveTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="执行反馈" align="center" width="160">
          <template #default="scope">
            <div class="feedback-info">
              <div v-if="scope.row.execFeedback" class="exec-feedback">
                {{ scope.row.execFeedback }}
              </div>
              <div v-else class="no-feedback">
                暂无反馈
              </div>
              <div v-if="scope.row.completeTime" class="complete-time">
                {{ dateFormatter(scope.row.completeTime) }}
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
              v-hasPermi="['datacenter:dispatch-command:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:dispatch-command:delete']"
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
  <DispatchCommandForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DispatchCommandApi, DispatchCommandVO } from '@/api/dataHub/commandAndCoordination/dispatchcommand'
import DispatchCommandForm from './DispatchCommandForm.vue'

/** 调度指令 列表 */
defineOptions({ name: 'DispatchCommand' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DispatchCommandVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  commandId: undefined,
  commandNo: undefined,
  planId: undefined,
  planNo: undefined,
  execUnitId: undefined,
  execUnitName: undefined,
  taskContent: undefined,
  execRequirement: undefined,
  commandStatus: undefined,
  issueUserId: undefined,
  issueUserName: undefined,
  issueTime: [],
  receiveUserId: undefined,
  receiveUserName: undefined,
  receiveTime: [],
  execFeedback: undefined,
  completeTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取指令状态标签样式 */
const getCommandStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    'draft': 'info',        // 草稿
    'issued': 'warning',    // 已下发
    'received': 'primary',  // 已接收
    'executing': 'success', // 执行中
    'completed': '',        // 已完成
    'cancelled': 'danger'   // 已取消
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DispatchCommandApi.getDispatchCommandPage(queryParams)
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
    await DispatchCommandApi.deleteDispatchCommand(id)
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
    const data = await DispatchCommandApi.exportDispatchCommand(queryParams)
    download.excel(data, '调度指令.xls')
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
.command-info {
  line-height: 1.4;
  text-align: left;
}

.command-no {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.command-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.task-content {
  font-size: 13px;
  color: #606266;
  margin-bottom: 4px;
  background: #f5f7fa;
  padding: 4px 8px;
  border-radius: 3px;
}

.exec-requirement {
  font-size: 12px;
  color: #e6a23c;
  font-style: italic;
}

.plan-info {
  line-height: 1.4;
  text-align: center;
}

.plan-no {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.plan-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.exec-unit-info {
  line-height: 1.4;
  text-align: center;
}

.exec-unit-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.exec-unit-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.issue-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.issue-user {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.issue-time {
  color: #909399;
}

.receive-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.receive-user {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.receive-time {
  color: #909399;
}

.feedback-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.exec-feedback {
  color: #606266;
  background: #f0f9ff;
  padding: 6px 8px;
  border-radius: 4px;
  margin-bottom: 4px;
  border-left: 3px solid #409eff;
}

.no-feedback {
  color: #c0c4cc;
  font-style: italic;
  margin-bottom: 4px;
}

.complete-time {
  color: #67c23a;
  font-weight: 500;
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
