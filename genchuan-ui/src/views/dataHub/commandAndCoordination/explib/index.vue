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
        <!-- 经验基础信息 -->
        <div class="form-section">
          <div class="section-title">经验基础信息</div>
          <div class="form-row">
            <el-form-item label="经验ID" prop="expId">
              <el-input
                v-model="queryParams.expId"
                placeholder="请输入经验ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="经验编号" prop="expNo">
              <el-input
                v-model="queryParams.expNo"
                placeholder="请输入经验编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="经验类型" prop="expType">
              <el-select
                v-model="queryParams.expType"
                placeholder="请选择经验类型"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="1" />
              </el-select>
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="经验标题" prop="expTitle">
              <el-input
                v-model="queryParams.expTitle"
                placeholder="请输入经验标题"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 关联信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">关联信息</div>
          <div class="form-row">
            <el-form-item label="事件类型ID" prop="evtTypeId">
              <el-input
                v-model="queryParams.evtTypeId"
                placeholder="请输入关联事件类型ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="事件类型名称" prop="evtTypeName">
              <el-input
                v-model="queryParams.evtTypeName"
                placeholder="请输入关联事件类型名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="关联评估ID" prop="evalId">
              <el-input
                v-model="queryParams.evalId"
                placeholder="请输入关联评估ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 系统信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">系统信息</div>
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
            v-hasPermi="['datacenter:exp-lib:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:exp-lib:export']"
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
          <span class="card-title">经验库列表</span>
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
        <el-table-column label="经验信息" align="center" min-width="220">
          <template #default="scope">
            <div class="exp-info">
              <div class="exp-no">{{ scope.row.expNo }}</div>
              <div class="exp-title">{{ scope.row.expTitle }}</div>
              <div class="exp-id">ID: {{ scope.row.expId }}</div>
              <div v-if="scope.row.expContent" class="exp-content">
                {{ scope.row.expContent }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="关联信息" align="center" width="140">
          <template #default="scope">
            <div class="relation-info">
              <div v-if="scope.row.evtTypeName" class="event-type">
                <div class="type-name">{{ scope.row.evtTypeName }}</div>
                <div class="type-id">ID: {{ scope.row.evtTypeId }}</div>
              </div>
              <div v-else class="no-event-type">
                未关联事件类型
              </div>
              <div v-if="scope.row.evalId" class="eval-id">
                评估ID: {{ scope.row.evalId }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="经验类型" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getExpTypeTagType(scope.row.expType)"
              size="small"
            >
              {{ scope.row.expType || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="系统信息" align="center" width="140">
          <template #default="scope">
            <div class="system-info">
              <div class="create-time">
                创建: {{ dateFormatter(scope.row.createTimeSys) }}
              </div>
              <div class="update-time">
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
              v-hasPermi="['datacenter:exp-lib:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:exp-lib:delete']"
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
  <ExpLibForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { ExpLibApi, ExpLibVO } from '@/api/dataHub/commandAndCoordination/explib'
import ExpLibForm from './ExpLibForm.vue'

/** 经验库 列表 */
defineOptions({ name: 'ExpLib' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ExpLibVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  expId: undefined,
  expNo: undefined,
  expTitle: undefined,
  evtTypeId: undefined,
  evtTypeName: undefined,
  expType: undefined,
  expContent: undefined,
  evalId: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取经验类型标签样式 */
const getExpTypeTagType = (type: string) => {
  const typeMap: Record<string, string> = {
    'success': 'success',    // 成功经验
    'failure': 'danger',     // 失败教训
    'warning': 'warning',    // 注意事项
    'improvement': 'primary' // 改进建议
  }
  return typeMap[type] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ExpLibApi.getExpLibPage(queryParams)
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
    await ExpLibApi.deleteExpLib(id)
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
    const data = await ExpLibApi.exportExpLib(queryParams)
    download.excel(data, '经验库.xls')
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
.exp-info {
  line-height: 1.4;
  text-align: left;
}

.exp-no {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.exp-title {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.exp-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.exp-content {
  font-size: 13px;
  color: #606266;
  background: #f5f7fa;
  padding: 6px 8px;
  border-radius: 4px;
  border-left: 3px solid #409eff;
}

.relation-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.event-type {
  margin-bottom: 6px;
}

.type-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.type-id {
  color: #909399;
  font-family: monospace;
}

.no-event-type {
  color: #c0c4cc;
  font-style: italic;
  margin-bottom: 6px;
}

.eval-id {
  color: #409eff;
  font-weight: 500;
  background: #ecf5ff;
  padding: 4px 6px;
  border-radius: 3px;
  display: inline-block;
}

.system-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.create-time, .update-time {
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
