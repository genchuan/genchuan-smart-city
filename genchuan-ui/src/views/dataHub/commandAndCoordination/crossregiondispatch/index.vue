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
        <!-- 协调基础信息 -->
        <div class="form-section">
          <div class="section-title">协调基础信息</div>
          <div class="form-row">
            <el-form-item label="协调ID" prop="coordinateId">
              <el-input
                v-model="queryParams.coordinateId"
                placeholder="请输入协调ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="协调编号" prop="coordinateNo">
              <el-input
                v-model="queryParams.coordinateNo"
                placeholder="请输入协调编号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="申请状态" prop="applyStatus">
              <el-select
                v-model="queryParams.applyStatus"
                placeholder="请选择申请状态"
                clearable
                class="!w-200px"
              >
                <el-option label="待审核" value="1" />
                <el-option label="已通过" value="2" />
                <el-option label="已驳回" value="3" />

              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 单位信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">单位信息</div>
          <div class="form-row">
            <el-form-item label="申请单位ID" prop="applyUnitId">
              <el-input
                v-model="queryParams.applyUnitId"
                placeholder="请输入申请单位ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="申请单位名称" prop="applyUnitName">
              <el-input
                v-model="queryParams.applyUnitName"
                placeholder="请输入申请单位名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="协作单位ID" prop="cooperUnitId">
              <el-input
                v-model="queryParams.cooperUnitId"
                placeholder="请输入协作单位ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="协作单位名称" prop="cooperUnitName">
              <el-input
                v-model="queryParams.cooperUnitName"
                placeholder="请输入协作单位名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 资源需求信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">资源需求信息</div>
          <div class="form-row">
            <el-form-item label="所需资源类型" prop="resType">
              <el-select
                v-model="queryParams.resType"
                placeholder="请选择所需资源类型"
                clearable
                class="!w-200px"
              >
                <el-option label="应急物资" value="1" />
                <el-option label="救援队伍" value="2" />
                <el-option label="救援车辆" value="3" />
                <el-option label="救援设备" value="4" />

              </el-select>
            </el-form-item>
            <el-form-item label="所需资源名称" prop="resName">
              <el-input
                v-model="queryParams.resName"
                placeholder="请输入所需资源名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="所需数量" prop="requireQuantity">
              <el-input
                v-model="queryParams.requireQuantity"
                placeholder="请输入所需数量"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="协作区域" prop="cooperRegion">
              <el-input
                v-model="queryParams.cooperRegion"
                placeholder="请输入协作区域"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 申请信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">申请信息</div>
          <div class="form-row">
            <el-form-item label="申请人ID" prop="applyUserId">
              <el-input
                v-model="queryParams.applyUserId"
                placeholder="请输入申请人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="申请人姓名" prop="applyUserName">
              <el-input
                v-model="queryParams.applyUserName"
                placeholder="请输入申请人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="申请时间" prop="applyTime">
              <el-date-picker
                v-model="queryParams.applyTime"
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
            <el-form-item label="申请理由" prop="applyReason">
              <el-input
                v-model="queryParams.applyReason"
                placeholder="请输入申请理由"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 审核信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">审核信息</div>
          <div class="form-row">
            <el-form-item label="审核人ID" prop="auditUserId">
              <el-input
                v-model="queryParams.auditUserId"
                placeholder="请输入审核人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="审核人姓名" prop="auditUserName">
              <el-input
                v-model="queryParams.auditUserName"
                placeholder="请输入审核人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="审核时间" prop="auditTime">
              <el-date-picker
                v-model="queryParams.auditTime"
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
            <el-form-item label="审核意见" prop="auditOpinion">
              <el-input
                v-model="queryParams.auditOpinion"
                placeholder="请输入审核意见"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
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
            v-hasPermi="['datacenter:cross-region-dispatch:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:cross-region-dispatch:export']"
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
          <span class="card-title">跨域调度协调列表</span>
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
        <el-table-column label="协调信息" align="center" min-width="180">
          <template #default="scope">
            <div class="coordinate-info">
              <div class="coordinate-no">{{ scope.row.coordinateNo }}</div>
              <div class="coordinate-id">ID: {{ scope.row.coordinateId }}</div>
              <div v-if="scope.row.applyReason" class="apply-reason">
                {{ scope.row.applyReason }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="单位信息" align="center" min-width="200">
          <template #default="scope">
            <div class="unit-info">
              <div class="apply-unit">
                <span class="unit-label">申请:</span>
                <span class="unit-name">{{ scope.row.applyUnitName }}</span>
                <span class="unit-id">(ID: {{ scope.row.applyUnitId }})</span>
              </div>
              <div class="cooper-unit">
                <span class="unit-label">协作:</span>
                <span class="unit-name">{{ scope.row.cooperUnitName }}</span>
                <span class="unit-id">(ID: {{ scope.row.cooperUnitId }})</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="资源需求" align="center" min-width="160">
          <template #default="scope">
            <div class="resource-info">
              <div class="res-name">{{ scope.row.resName }}</div>
              <div class="res-details">
                <span class="res-type">{{ scope.row.resType }}</span>
                <span class="quantity">数量: {{ scope.row.requireQuantity }}</span>
              </div>
              <div v-if="scope.row.cooperRegion" class="cooper-region">
                区域: {{ scope.row.cooperRegion }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="申请状态" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getApplyStatusTagType(scope.row.applyStatus)"
              size="small"
            >
              {{ scope.row.applyStatus || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="申请人" align="center" width="120">
          <template #default="scope">
            <div class="apply-user-info">
              <div class="apply-user-name">{{ scope.row.applyUserName }}</div>
              <div class="apply-user-id">ID: {{ scope.row.applyUserId }}</div>
              <div class="apply-time">{{ dateFormatter(scope.row.applyTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="审核信息" align="center" min-width="180">
          <template #default="scope">
            <div class="audit-info">
              <div v-if="scope.row.auditUserName" class="audit-user">
                <div class="audit-user-name">{{ scope.row.auditUserName }}</div>
                <div class="audit-user-id">ID: {{ scope.row.auditUserId }}</div>
              </div>
              <div v-else class="no-audit-user">
                未审核
              </div>
              <div v-if="scope.row.auditTime" class="audit-time">
                {{ dateFormatter(scope.row.auditTime) }}
              </div>
              <div v-if="scope.row.auditOpinion" class="audit-opinion">
                {{ scope.row.auditOpinion }}
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
              v-hasPermi="['datacenter:cross-region-dispatch:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:cross-region-dispatch:delete']"
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
  <CrossRegionDispatchForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { CrossRegionDispatchApi, CrossRegionDispatchVO } from '@/api/dataHub/commandAndCoordination/crossregiondispatch'
import CrossRegionDispatchForm from './CrossRegionDispatchForm.vue'

/** 跨域调度协调 列表 */
defineOptions({ name: 'CrossRegionDispatch' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CrossRegionDispatchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  coordinateId: undefined,
  coordinateNo: undefined,
  applyUnitId: undefined,
  applyUnitName: undefined,
  cooperUnitId: undefined,
  cooperUnitName: undefined,
  applyReason: undefined,
  resType: undefined,
  resName: undefined,
  requireQuantity: undefined,
  cooperRegion: undefined,
  applyStatus: undefined,
  applyUserId: undefined,
  applyUserName: undefined,
  applyTime: [],
  auditUserId: undefined,
  auditUserName: undefined,
  auditTime: [],
  auditOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取申请状态标签样式 */
const getApplyStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    'pending': 'warning',    // 待审核
    'approved': 'success',   // 已通过
    'rejected': 'danger',    // 已拒绝
    'processing': 'primary', // 处理中
    'completed': ''          // 已完成
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CrossRegionDispatchApi.getCrossRegionDispatchPage(queryParams)
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
    await CrossRegionDispatchApi.deleteCrossRegionDispatch(id)
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
    const data = await CrossRegionDispatchApi.exportCrossRegionDispatch(queryParams)
    download.excel(data, '跨域调度协调.xls')
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
.coordinate-info {
  line-height: 1.4;
  text-align: left;
}

.coordinate-no {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.coordinate-id {
  font-size: 12px;
  color: #909399;
  font-family: monospace;
  margin-bottom: 4px;
}

.apply-reason {
  font-size: 13px;
  color: #606266;
  background: #f5f7fa;
  padding: 6px 8px;
  border-radius: 4px;
  border-left: 3px solid #409eff;
}

.unit-info {
  line-height: 1.4;
  text-align: left;
  font-size: 13px;
}

.apply-unit, .cooper-unit {
  margin-bottom: 6px;
}

.unit-label {
  color: #909399;
  font-weight: 500;
  margin-right: 4px;
}

.unit-name {
  color: #303133;
  font-weight: 500;
}

.unit-id {
  color: #909399;
  font-family: monospace;
  font-size: 12px;
}

.resource-info {
  line-height: 1.4;
  text-align: center;
}

.res-name {
  font-weight: 600;
  color: #303133;
  margin-bottom: 4px;
}

.res-details {
  display: flex;
  flex-direction: column;
  gap: 2px;
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.res-type {
  color: #e6a23c;
  font-weight: 500;
}

.quantity {
  color: #409eff;
  font-weight: 500;
}

.cooper-region {
  font-size: 12px;
  color: #67c23a;
  background: #f0f9eb;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
}

.apply-user-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.apply-user-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.apply-user-id {
  color: #909399;
  margin-bottom: 4px;
}

.apply-time {
  color: #67c23a;
  font-weight: 500;
}

.audit-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.audit-user {
  margin-bottom: 4px;
}

.audit-user-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.audit-user-id {
  color: #909399;
  margin-bottom: 4px;
}

.no-audit-user {
  color: #c0c4cc;
  font-style: italic;
  margin-bottom: 4px;
}

.audit-time {
  color: #e6a23c;
  font-weight: 500;
  margin-bottom: 4px;
}

.audit-opinion {
  color: #606266;
  background: #f0f9ff;
  padding: 6px 8px;
  border-radius: 4px;
  border-left: 3px solid #e6a23c;
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
