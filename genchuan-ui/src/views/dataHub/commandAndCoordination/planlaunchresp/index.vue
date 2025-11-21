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
            <el-form-item label="响应ID" prop="respId">
              <el-input
                v-model="queryParams.respId"
                placeholder="请输入响应ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="响应编号" prop="respNo">
              <el-input
                v-model="queryParams.respNo"
                placeholder="请输入响应编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联预案ID" prop="planId">
              <el-input
                v-model="queryParams.planId"
                placeholder="请输入关联预案ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="预案名称" prop="planName">
              <el-input
                v-model="queryParams.planName"
                placeholder="请输入预案名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="预案版本" prop="planVersion">
              <el-input
                v-model="queryParams.planVersion"
                placeholder="请输入预案版本"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联事件编码" prop="evtCode">
              <el-input
                v-model="queryParams.evtCode"
                placeholder="请输入关联事件编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="事件名称" prop="evtName">
              <el-input
                v-model="queryParams.evtName"
                placeholder="请输入事件名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="启动原因" prop="launchReason">
              <el-input
                v-model="queryParams.launchReason"
                placeholder="请输入启动原因"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="响应等级" prop="respLevel">
              <el-input
                v-model="queryParams.respLevel"
                placeholder="请输入响应等级"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 人员信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">人员信息</div>
          <div class="form-row">
            <el-form-item label="启动人ID" prop="launchUserId">
              <el-input
                v-model="queryParams.launchUserId"
                placeholder="请输入启动人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="启动人姓名" prop="launchUserName">
              <el-input
                v-model="queryParams.launchUserName"
                placeholder="请输入启动人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="响应状态" prop="respStatus">
              <el-select
                v-model="queryParams.respStatus"
                placeholder="请选择响应状态"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 时间信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">时间信息</div>
          <div class="form-row">
            <el-form-item label="启动时间" prop="launchTime">
              <el-date-picker
                v-model="queryParams.launchTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-320px"
              />
            </el-form-item>
            <el-form-item label="终止时间" prop="terminateTime">
              <el-date-picker
                v-model="queryParams.terminateTime"
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
            <el-form-item label="终止原因" prop="terminateReason">
              <el-input
                v-model="queryParams.terminateReason"
                placeholder="请输入终止原因"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 同步信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">同步信息</div>
          <div class="form-row">
            <el-form-item label="联动单位ID" prop="syncUnitIds">
              <el-input
                v-model="queryParams.syncUnitIds"
                placeholder="请输入需同步的联动单位ID，多个用逗号分隔"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
            <el-form-item label="联动单位名称" prop="syncUnitNames">
              <el-input
                v-model="queryParams.syncUnitNames"
                placeholder="请输入与单位IDs同步"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 扩展信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">扩展信息</div>
          <div class="form-row">
            <el-form-item label="扩展字段1" prop="extCat1">
              <el-input
                v-model="queryParams.extCat1"
                placeholder="请输入预留，如“响应指挥长”"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="扩展字段2" prop="extCat2">
              <el-input
                v-model="queryParams.extCat2"
                placeholder="请输入预留，如“响应指挥长”"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="部署方案路径1" prop="extCommon1">
              <el-input
                v-model="queryParams.extCommon1"
                placeholder="请输入预留，存储响应部署方案路径"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
            <el-form-item label="部署方案路径2" prop="extCommon2">
              <el-input
                v-model="queryParams.extCommon2"
                placeholder="请输入预留，存储响应部署方案路径"
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
            v-hasPermi="['datacenter:plan-launch-resp:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:plan-launch-resp:export']"
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
          <span class="card-title">预案启动响应列表</span>
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
        <el-table-column label="响应信息" align="center" min-width="180">
          <template #default="scope">
            <div class="response-info">
              <div class="resp-id">{{ scope.row.respId }}</div>
              <div class="resp-no">{{ scope.row.respNo }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="预案信息" align="center" min-width="200">
          <template #default="scope">
            <div class="plan-info">
              <div class="plan-name">{{ scope.row.planName }}</div>
              <div class="plan-details">
                <span class="plan-id">ID: {{ scope.row.planId }}</span>
                <span class="plan-version">版本: {{ scope.row.planVersion }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="事件信息" align="center" min-width="160">
          <template #default="scope">
            <div class="event-info">
              <div class="event-name">{{ scope.row.evtName }}</div>
              <div class="event-code">编码: {{ scope.row.evtCode }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="启动信息" align="center" width="140">
          <template #default="scope">
            <div class="launch-info">
              <div class="launch-user">{{ scope.row.launchUserName }}</div>
              <div class="launch-time">{{ dateFormatter(scope.row.launchTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="响应状态" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getStatusTagType(scope.row.respStatus)"
              size="small"
            >
              {{ scope.row.respStatus || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="响应等级" align="center" width="100">
          <template #default="scope">
            <div class="resp-level">
              {{ scope.row.respLevel }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="终止信息" align="center" width="140">
          <template #default="scope">
            <div class="terminate-info">
              <div v-if="scope.row.terminateTime" class="terminate-time">
                {{ dateFormatter(scope.row.terminateTime) }}
              </div>
              <div v-else class="no-terminate">
                未终止
              </div>
              <div v-if="scope.row.terminateReason" class="terminate-reason">
                {{ scope.row.terminateReason }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="联动单位" align="center" width="120">
          <template #default="scope">
            <div class="sync-info">
              <div v-if="scope.row.syncUnitNames" class="sync-names">
                {{ scope.row.syncUnitNames }}
              </div>
              <div v-else class="no-sync">
                无联动
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
              v-hasPermi="['datacenter:plan-launch-resp:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:plan-launch-resp:delete']"
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
  <PlanLaunchRespForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { PlanLaunchRespApi, PlanLaunchRespVO } from '@/api/dataHub/commandAndCoordination/planlaunchresp'
import PlanLaunchRespForm from './PlanLaunchRespForm.vue'

/** 预案启动响应 列表 */
defineOptions({ name: 'PlanLaunchResp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PlanLaunchRespVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  respId: undefined,
  respNo: undefined,
  planId: undefined,
  planName: undefined,
  planVersion: undefined,
  evtCode: undefined,
  evtName: undefined,
  launchReason: undefined,
  respLevel: undefined,
  launchUserId: undefined,
  launchUserName: undefined,
  launchTime: [],
  respStatus: undefined,
  terminateReason: undefined,
  terminateTime: [],
  syncUnitIds: undefined,
  syncUnitNames: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取状态标签样式 */
const getStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    'active': 'success',
    'inactive': 'info',
    'completed': '',
    'terminated': 'warning',
    'cancelled': 'danger'
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PlanLaunchRespApi.getPlanLaunchRespPage(queryParams)
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
    await PlanLaunchRespApi.deletePlanLaunchResp(id)
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
    const data = await PlanLaunchRespApi.exportPlanLaunchResp(queryParams)
    download.excel(data, '预案启动响应.xls')
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
.response-info {
  line-height: 1.4;
  text-align: left;
}

.resp-id {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.resp-no {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.plan-info {
  line-height: 1.4;
  text-align: left;
}

.plan-name {
  font-weight: 600;
  color: #303133;
  margin-bottom: 4px;
}

.plan-details {
  display: flex;
  flex-direction: column;
  gap: 2px;
  font-size: 12px;
  color: #909399;
}

.plan-id, .plan-version {
  font-family: monospace;
}

.event-info {
  line-height: 1.4;
  text-align: left;
}

.event-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.event-code {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
}

.launch-info {
  line-height: 1.4;
  text-align: center;
}

.launch-user {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.launch-time {
  font-size: 12px;
  color: #909399;
}

.resp-level {
  font-weight: 600;
  color: #e6a23c;
}

.terminate-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.terminate-time {
  color: #f56c6c;
  font-weight: 500;
  margin-bottom: 4px;
}

.terminate-reason {
  color: #909399;
  font-style: italic;
}

.no-terminate {
  color: #67c23a;
  font-weight: 500;
}

.sync-info {
  line-height: 1.4;
  text-align: center;
}

.sync-names {
  font-size: 12px;
  color: #606266;
}

.no-sync {
  font-size: 12px;
  color: #c0c4cc;
  font-style: italic;
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
