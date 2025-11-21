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
            <el-form-item label="预案ID" prop="planId">
              <el-input
                v-model="queryParams.planId"
                placeholder="请输入预案ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="预案编号" prop="planNo">
              <el-input
                v-model="queryParams.planNo"
                placeholder="请输入预案编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="预案标准名称" prop="planName">
              <el-input
                v-model="queryParams.planName"
                placeholder="请输入预案标准名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 分类信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">分类信息</div>
          <div class="form-row">
            <el-form-item label="分类ID" prop="catId">
              <el-input
                v-model="queryParams.catId"
                placeholder="请输入分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分类名称" prop="catName">
              <el-input
                v-model="queryParams.catName"
                placeholder="请输入分类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 区域信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">区域信息</div>
          <div class="form-row">
            <el-form-item label="适用区域代码" prop="applyRegionCode">
              <el-input
                v-model="queryParams.applyRegionCode"
                placeholder="请输入适用区域代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="适用区域名称" prop="applyRegionName">
              <el-input
                v-model="queryParams.applyRegionName"
                placeholder="请输入适用区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 版本信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">版本信息</div>
          <div class="form-row">
            <el-form-item label="预案版本" prop="planVersion">
              <el-input
                v-model="queryParams.planVersion"
                placeholder="请输入预案版本"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="生效时间" prop="effectiveTime">
              <el-date-picker
                v-model="queryParams.effectiveTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-220px"
              />
            </el-form-item>
            <el-form-item label="失效时间" prop="expireTime">
              <el-date-picker
                v-model="queryParams.expireTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-220px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 其他信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">其他信息</div>
          <div class="form-row">
            <el-form-item label="预案文档路径" prop="planDocPath">
              <el-input
                v-model="queryParams.planDocPath"
                placeholder="请输入预案文档路径"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
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
            v-hasPermi="['datacenter:emer-plan-lib:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:emer-plan-lib:export']"
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
          <span class="card-title">预案库列表</span>
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
        <el-table-column label="预案信息" align="center" min-width="220">
          <template #default="scope">
            <div class="plan-info">
              <div class="plan-no">{{ scope.row.planNo }}</div>
              <div class="plan-name">{{ scope.row.planName }}</div>
              <div class="plan-id">ID: {{ scope.row.planId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分类信息" align="center" width="150">
          <template #default="scope">
            <div class="category-info">
              <div class="cat-name">{{ scope.row.catName }}</div>
              <div class="cat-id">ID: {{ scope.row.catId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="区域信息" align="center" width="160">
          <template #default="scope">
            <div class="region-info">
              <div v-if="scope.row.applyRegionName" class="region-name">
                {{ scope.row.applyRegionName }}
              </div>
              <div v-if="scope.row.applyRegionCode" class="region-code">
                代码: {{ scope.row.applyRegionCode }}
              </div>
              <div v-else class="no-region">
                未指定区域
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="版本信息" align="center" width="120">
          <template #default="scope">
            <div class="version-info">
              <div class="plan-version">
                {{ scope.row.planVersion || '未指定' }}
              </div>
              <div v-if="scope.row.effectiveTime" class="effective-time">
                生效: {{ formatDate(scope.row.effectiveTime) }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="时间信息" align="center" width="180">
          <template #default="scope">
            <div class="time-info">
              <div v-if="scope.row.effectiveTime" class="time-item">
                <span class="time-label">生效:</span>
                <span class="time-value">{{ formatDate(scope.row.effectiveTime) }}</span>
              </div>
              <div v-if="scope.row.expireTime" class="time-item">
                <span class="time-label">失效:</span>
                <span class="time-value">{{ formatDate(scope.row.expireTime) }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="主键ID" align="center" width="100">
          <template #default="scope">
            <div class="primary-id">
              {{ scope.row.id }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:emer-plan-lib:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:emer-plan-lib:delete']"
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
  <EmerPlanLibForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import download from '@/utils/download'
import { EmerPlanLibApi, EmerPlanLibVO } from '@/api/dataHub/commandAndCoordination/emerplanlib'
import EmerPlanLibForm from './EmerPlanLibForm.vue'

/** 预案库 列表 */
defineOptions({ name: 'EmerPlanLib' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EmerPlanLibVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  planId: undefined,
  planNo: undefined,
  planName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  planVersion: undefined,
  effectiveTime: [],
  expireTime: [],
  planDocPath: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 格式化日期 */
const formatDate = (dateString: string) => {
  if (!dateString) return '-'
  return dateString.split(' ')[0] // 只显示日期部分
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EmerPlanLibApi.getEmerPlanLibPage(queryParams)
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
    await EmerPlanLibApi.deleteEmerPlanLib(id)
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
    const data = await EmerPlanLibApi.exportEmerPlanLib(queryParams)
    download.excel(data, '预案库.xls')
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
.plan-info {
  line-height: 1.4;
  text-align: left;
}

.plan-no {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.plan-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.plan-id {
  font-size: 12px;
  color: #909399;
}

.category-info {
  line-height: 1.4;
  text-align: center;
}

.cat-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.cat-id {
  font-size: 11px;
  color: #909399;
}

.region-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.region-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.region-code {
  color: #606266;
}

.no-region {
  color: #c0c4cc;
  font-style: italic;
}

.version-info {
  line-height: 1.4;
  text-align: center;
}

.plan-version {
  font-weight: 500;
  color: #409eff;
  margin-bottom: 4px;
}

.effective-time {
  font-size: 11px;
  color: #909399;
}

.time-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.time-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 2px;
}

.time-label {
  color: #909399;
}

.time-value {
  color: #303133;
  font-weight: 500;
}

.primary-id {
  font-family: monospace;
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
