<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">
            <Icon icon="ep:search" class="title-icon" />
            查询条件
          </span>
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
        label-width="100px"
      >
        <!-- 基础配置信息 -->
        <div class="form-section">
          <div class="section-title">
            <Icon icon="ep:setting" class="section-icon" />
            基础配置信息
          </div>
          <div class="form-row">
            <el-form-item label="配置ID" prop="flowCfgId">
              <el-input
                v-model="queryParams.flowCfgId"
                placeholder="请输入配置ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="配置名称" prop="flowCfgName">
              <el-input
                v-model="queryParams.flowCfgName"
                placeholder="请输入配置名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="流程步骤" prop="flowSteps">
              <el-input
                v-model="queryParams.flowSteps"
                placeholder="请输入流程步骤"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 系统信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">
            <Icon icon="ep:user" class="section-icon" />
            系统信息
          </div>
          <div class="form-row">
            <el-form-item label="创建人账号" prop="createUser">
              <el-input
                v-model="queryParams.createUser"
                placeholder="请输入创建人账号"
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
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="更新人账号" prop="updateUser">
              <el-input
                v-model="queryParams.updateUser"
                placeholder="请输入更新人账号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <div class="form-actions">
          <el-button type="primary" @click="handleQuery" class="action-btn">
            <Icon icon="ep:search" class="btn-icon" />
            搜索
          </el-button>
          <el-button @click="resetQuery" class="action-btn">
            <Icon icon="ep:refresh" class="btn-icon" />
            重置
          </el-button>
          <el-button
            type="success"
            @click="openForm('create')"
            v-hasPermi="['datacenter:coop-flow-cfg:create']"
            class="action-btn"
          >
            <Icon icon="ep:plus" class="btn-icon" />
            新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:coop-flow-cfg:export']"
            class="action-btn"
          >
            <Icon icon="ep:download" class="btn-icon" />
            导出
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
          <span class="card-title">
            <Icon icon="ep:list" class="title-icon" />
            联动流程配置列表
          </span>
          <div class="table-info">
            <Icon icon="ep:document" class="info-icon" />
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
          background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
          color: '#fff',
          fontWeight: '600',
          fontSize: '14px'
        }"
        class="custom-table"
      >
        <el-table-column label="配置信息" align="center" min-width="220">
          <template #default="scope">
            <div class="config-info">
              <div class="config-id">
                <Icon icon="ep:key" class="id-icon" />
                ID: {{ scope.row.flowCfgId }}
              </div>
              <div class="config-name">{{ scope.row.flowCfgName }}</div>
              <div v-if="scope.row.id" class="primary-id">
                主键: {{ scope.row.id }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="流程步骤" align="center" min-width="200">
          <template #default="scope">
            <div class="flow-steps">
              <div v-if="scope.row.flowSteps" class="steps-content">
                <el-tooltip
                  :content="scope.row.flowSteps"
                  placement="top"
                  effect="light"
                >
                  <div class="steps-text">
                    <Icon icon="ep:guide" class="steps-icon" />
                    {{ formatSteps(scope.row.flowSteps) }}
                  </div>
                </el-tooltip>
                <div class="steps-count">
                  {{ getStepsCount(scope.row.flowSteps) }} 个步骤
                </div>
              </div>
              <div v-else class="no-steps">
                <Icon icon="ep:warning" class="warning-icon" />
                未配置步骤
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="创建信息" align="center" width="180">
          <template #default="scope">
            <div class="create-info">
              <div v-if="scope.row.createUser" class="create-user">
                <Icon icon="ep:user" class="create-icon" />
                {{ scope.row.createUser }}
              </div>
              <div v-if="scope.row.createTime" class="create-time">
                <Icon icon="ep:clock" class="time-icon" />
                {{ dateFormatter(scope.row.createTime) }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="更新信息" align="center" width="160">
          <template #default="scope">
            <div class="update-info">
              <div v-if="scope.row.updateUser" class="update-user">
                <Icon icon="ep:edit" class="update-icon" />
                {{ scope.row.updateUser }}
              </div>
              <div v-else class="no-update">
                <Icon icon="ep:info" class="info-icon" />
                暂无更新
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <div class="action-buttons">
              <el-button
                size="small"
                type="primary"
                @click="openForm('update', scope.row.id)"
                v-hasPermi="['datacenter:coop-flow-cfg:update']"
                class="action-button"
              >
                <Icon icon="ep:edit" class="button-icon" />
                编辑
              </el-button>
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.row.id)"
                v-hasPermi="['datacenter:coop-flow-cfg:delete']"
                class="action-button"
              >
                <Icon icon="ep:delete" class="button-icon" />
                删除
              </el-button>
            </div>
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
  <CoopFlowCfgForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { CoopFlowCfgApi, CoopFlowCfgVO } from '@/api/dataHub/commandAndCoordination/coopflowcfg'
import CoopFlowCfgForm from './CoopFlowCfgForm.vue'

/** 联动流程配置 列表 */
defineOptions({ name: 'CoopFlowCfg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CoopFlowCfgVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  flowCfgId: undefined,
  flowCfgName: undefined,
  flowSteps: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 格式化流程步骤显示 */
const formatSteps = (steps: string) => {
  if (!steps) return ''
  if (steps.length > 30) {
    return steps.substring(0, 30) + '...'
  }
  return steps
}

/** 获取步骤数量 */
const getStepsCount = (steps: string) => {
  if (!steps) return 0
  // 假设步骤用逗号分隔
  return steps.split(',').length
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CoopFlowCfgApi.getCoopFlowCfgPage(queryParams)
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
    await CoopFlowCfgApi.deleteCoopFlowCfg(id)
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
    const data = await CoopFlowCfgApi.exportCoopFlowCfg(queryParams)
    download.excel(data, '联动流程配置.xls')
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
  margin-bottom: 20px;
  border-radius: 12px;
  border: 1px solid #e1e8ff;
  background: linear-gradient(135deg, #f8faff 0%, #f0f4ff 100%);
}

.table-card {
  border-radius: 12px;
  border: 1px solid #e1e8ff;
  background: #fff;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
}

.card-title {
  font-size: 16px;
  font-weight: 700;
  color: #2c3e50;
  display: flex;
  align-items: center;
  gap: 8px;
}

.title-icon {
  color: #409eff;
  font-size: 18px;
}

.toggle-btn {
  color: #409eff;
  font-size: 13px;
  font-weight: 500;
  padding: 4px 8px;
  border-radius: 6px;
  transition: all 0.3s ease;
}

.toggle-btn:hover {
  background: #ecf5ff;
  transform: translateY(-1px);
}

.table-info {
  font-size: 14px;
  color: #606266;
  display: flex;
  align-items: center;
  gap: 6px;
}

.info-icon {
  color: #909399;
  font-size: 16px;
}

.info-highlight {
  color: #409eff;
  font-weight: 700;
  font-size: 16px;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-section {
  border: 1px solid #e1e8ff;
  border-radius: 10px;
  padding: 20px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.08);
  transition: all 0.3s ease;
}

.form-section:hover {
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.12);
  transform: translateY(-1px);
}

.section-title {
  font-size: 15px;
  font-weight: 700;
  color: #409eff;
  margin-bottom: 16px;
  padding-bottom: 10px;
  border-bottom: 2px solid #e1e8ff;
  display: flex;
  align-items: center;
  gap: 8px;
}

.section-icon {
  font-size: 16px;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  align-items: flex-start;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 16px;
  padding-top: 20px;
  border-top: 1px solid #e1e8ff;
  margin-top: 10px;
}

.action-btn {
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 6px;
}

.action-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.btn-icon {
  font-size: 14px;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid #e1e8ff;
}

/* 表格内容样式 */
.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

.config-info {
  line-height: 1.5;
  text-align: left;
  padding: 8px 0;
}

.config-id {
  font-family: 'Monaco', 'Consolas', monospace;
  color: #e6a23c;
  background: linear-gradient(135deg, #fdf6ec 0%, #faecd8 100%);
  padding: 4px 8px;
  border-radius: 6px;
  display: inline-flex;
  align-items: center;
  gap: 4px;
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 600;
  border: 1px solid #f5dab1;
}

.id-icon {
  font-size: 12px;
}

.config-name {
  font-weight: 700;
  color: #2c3e50;
  font-size: 14px;
  margin-bottom: 4px;
}

.primary-id {
  color: #909399;
  font-size: 11px;
  font-weight: 500;
  background: #f5f7fa;
  padding: 2px 6px;
  border-radius: 4px;
  display: inline-block;
}

.flow-steps {
  line-height: 1.5;
  text-align: left;
}

.steps-content {
  padding: 8px;
  background: linear-gradient(135deg, #f0f9ff 0%, #e6f7ff 100%);
  border-radius: 8px;
  border: 1px solid #d1e9ff;
}

.steps-text {
  font-size: 13px;
  color: #1890ff;
  font-weight: 500;
  margin-bottom: 6px;
  display: flex;
  align-items: center;
  gap: 6px;
  cursor: pointer;
  transition: color 0.3s ease;
}

.steps-text:hover {
  color: #096dd9;
}

.steps-icon {
  font-size: 14px;
  color: #1890ff;
}

.steps-count {
  font-size: 11px;
  color: #52c41a;
  background: #f6ffed;
  padding: 2px 6px;
  border-radius: 4px;
  display: inline-block;
  border: 1px solid #b7eb8f;
  font-weight: 600;
}

.no-steps {
  color: #c0c4cc;
  font-style: italic;
  font-size: 13px;
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 8px;
  background: #fafafa;
  border-radius: 6px;
  border: 1px dashed #d9d9d9;
}

.warning-icon {
  font-size: 14px;
  color: #faad14;
}

.create-info {
  line-height: 1.5;
  text-align: left;
  font-size: 12px;
}

.create-user {
  color: #606266;
  margin-bottom: 6px;
  display: flex;
  align-items: center;
  gap: 6px;
  font-weight: 500;
}

.create-icon {
  color: #67c23a;
  font-size: 12px;
}

.create-time {
  color: #909399;
  font-size: 11px;
  display: flex;
  align-items: center;
  gap: 6px;
  background: #f5f7fa;
  padding: 4px 8px;
  border-radius: 4px;
}

.time-icon {
  font-size: 11px;
}

.update-info {
  line-height: 1.5;
  text-align: left;
  font-size: 12px;
}

.update-user {
  color: #606266;
  display: flex;
  align-items: center;
  gap: 6px;
  font-weight: 500;
  background: #f0f9ff;
  padding: 6px 8px;
  border-radius: 6px;
  border: 1px solid #d1e9ff;
}

.update-icon {
  color: #1890ff;
  font-size: 12px;
}

.no-update {
  color: #c0c4cc;
  font-style: italic;
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 6px;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  gap: 6px;
  align-items: center;
}

.action-button {
  padding: 6px 12px;
  border-radius: 6px;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 4px;
  font-weight: 500;
  min-width: 60px;
  justify-content: center;
}

.action-button:hover {
  background: #f5f7fa;
  transform: translateX(2px);
}

.button-icon {
  font-size: 12px;
}

:deep(.el-card__header) {
  padding: 16px 24px;
  border-bottom: 1px solid #e1e8ff;
  background: linear-gradient(135deg, #f8faff 0%, #f0f4ff 100%);
}

:deep(.el-table .cell) {
  padding: 12px 16px;
  line-height: 1.5;
}

:deep(.el-table th) {
  font-weight: 700;
  font-size: 14px;
}

:deep(.el-table .el-table__row) {
  transition: all 0.3s ease;
}

:deep(.el-table .el-table__row:hover) {
  background-color: #f8faff !important;
  transform: scale(1.01);
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.15);
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped td) {
  background: #fafbfc;
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped:hover td) {
  background-color: #f0f4ff !important;
}

.ml-2 {
  margin-left: 8px;
}

.mr-5px {
  margin-right: 5px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .form-row {
    gap: 12px;
  }

  .form-actions {
    flex-wrap: wrap;
    gap: 12px;
  }

  .action-btn {
    flex: 1;
    min-width: 120px;
    justify-content: center;
  }

  .action-buttons {
    flex-direction: row;
    justify-content: center;
  }
}
</style>
