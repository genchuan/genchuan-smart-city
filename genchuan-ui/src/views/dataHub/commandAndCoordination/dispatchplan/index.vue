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
        <!-- 方案基础信息 -->
        <div class="form-section">
          <div class="section-title">方案基础信息</div>
          <div class="form-row">
            <el-form-item label="方案ID" prop="planId">
              <el-input
                v-model="queryParams.planId"
                placeholder="请输入方案ID"
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
            <el-form-item label="方案名称" prop="planName">
              <el-input
                v-model="queryParams.planName"
                placeholder="请输入方案名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="调度需求描述" prop="demandDesc">
              <el-input
                v-model="queryParams.demandDesc"
                placeholder="请输入调度需求描述"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 响应关联信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">响应关联信息</div>
          <div class="form-row">
            <el-form-item label="关联响应ID" prop="respId">
              <el-input
                v-model="queryParams.respId"
                placeholder="请输入关联响应ID"
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
          </div>
        </div>

        <!-- 资源调度信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">资源调度信息</div>
          <div class="form-row">
            <el-form-item label="资源类型" prop="resType">
              <el-select
                v-model="queryParams.resType"
                placeholder="请选择资源类型"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
            <el-form-item label="资源ID" prop="resId">
              <el-input
                v-model="queryParams.resId"
                placeholder="请输入资源ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="资源名称" prop="resName">
              <el-input
                v-model="queryParams.resName"
                placeholder="请输入资源名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="调度数量" prop="dispatchQuantity">
              <el-input
                v-model="queryParams.dispatchQuantity"
                placeholder="请输入调度数量"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 目的地信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">目的地信息</div>
          <div class="form-row">
            <el-form-item label="调配目的地" prop="dispatchDest">
              <el-input
                v-model="queryParams.dispatchDest"
                placeholder="请输入调配目的地"
                clearable
                @keyup.enter="handleQuery"
                class="!w-320px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="目的地坐标X" prop="destCoordX">
              <el-input
                v-model="queryParams.destCoordX"
                placeholder="请输入目的地坐标X"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="目的地坐标Y" prop="destCoordY">
              <el-input
                v-model="queryParams.destCoordY"
                placeholder="请输入目的地坐标Y"
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
            <el-form-item label="负责人ID" prop="chargerId">
              <el-input
                v-model="queryParams.chargerId"
                placeholder="请输入负责人ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="负责人姓名" prop="chargerName">
              <el-input
                v-model="queryParams.chargerName"
                placeholder="请输入负责人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="要求完成时间" prop="requireCompleteTime">
              <el-date-picker
                v-model="queryParams.requireCompleteTime"
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

        <!-- 状态信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">状态信息</div>
          <div class="form-row">
            <el-form-item label="方案状态" prop="planStatus">
              <el-select
                v-model="queryParams.planStatus"
                placeholder="请选择方案状态"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
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
            v-hasPermi="['datacenter:dispatch-plan:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:dispatch-plan:export']"
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
          <span class="card-title">调度方案列表</span>
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
        <el-table-column label="方案信息" align="center" min-width="180">
          <template #default="scope">
            <div class="plan-info">
              <div class="plan-name">{{ scope.row.planName }}</div>
              <div class="plan-details">
                <span class="plan-id">ID: {{ scope.row.planId }}</span>
                <span class="plan-no">编号: {{ scope.row.planNo }}</span>
              </div>
              <div v-if="scope.row.demandDesc" class="demand-desc">
                {{ scope.row.demandDesc }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="响应关联" align="center" width="140">
          <template #default="scope">
            <div class="response-info">
              <div class="resp-id">响应ID: {{ scope.row.respId }}</div>
              <div class="resp-no">{{ scope.row.respNo }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="资源信息" align="center" min-width="160">
          <template #default="scope">
            <div class="resource-info">
              <div class="res-name">{{ scope.row.resName }}</div>
              <div class="res-details">
                <span class="res-type">{{ scope.row.resType }}</span>
                <span class="res-id">ID: {{ scope.row.resId }}</span>
              </div>
              <div class="quantity">
                数量: <span class="quantity-number">{{ scope.row.dispatchQuantity }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="目的地" align="center" width="140">
          <template #default="scope">
            <div class="destination-info">
              <div class="dispatch-dest">{{ scope.row.dispatchDest }}</div>
              <div v-if="scope.row.destCoordX || scope.row.destCoordY" class="coordinates">
                ({{ scope.row.destCoordX }}, {{ scope.row.destCoordY }})
              </div>
              <div v-else class="no-coordinates">
                无坐标信息
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="负责人" align="center" width="120">
          <template #default="scope">
            <div class="charger-info">
              <div class="charger-name">{{ scope.row.chargerName }}</div>
              <div class="charger-id">ID: {{ scope.row.chargerId }}</div>
              <div v-if="scope.row.requireCompleteTime" class="complete-time">
                {{ dateFormatter(scope.row.requireCompleteTime) }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" width="100">
          <template #default="scope">
            <el-tag
              :type="getPlanStatusTagType(scope.row.planStatus)"
              size="small"
            >
              {{ scope.row.planStatus || '未知' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建信息" align="center" width="140">
          <template #default="scope">
            <div class="create-info">
              <div class="create-user">{{ scope.row.createUser }}</div>
              <div class="create-time">{{ dateFormatter(scope.row.createTime) }}</div>
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
              v-hasPermi="['datacenter:dispatch-plan:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:dispatch-plan:delete']"
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
  <DispatchPlanForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DispatchPlanApi, DispatchPlanVO } from '@/api/dataHub/commandAndCoordination/dispatchplan'
import DispatchPlanForm from './DispatchPlanForm.vue'

/** 调度方案 列表 */
defineOptions({ name: 'DispatchPlan' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DispatchPlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  planId: undefined,
  planNo: undefined,
  respId: undefined,
  respNo: undefined,
  planName: undefined,
  demandDesc: undefined,
  resType: undefined,
  resId: undefined,
  resName: undefined,
  dispatchQuantity: undefined,
  dispatchDest: undefined,
  destCoordX: undefined,
  destCoordY: undefined,
  chargerId: undefined,
  chargerName: undefined,
  requireCompleteTime: [],
  planStatus: undefined,
  createUser: undefined,
  createTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取方案状态标签样式 */
const getPlanStatusTagType = (status: string) => {
  const statusMap: Record<string, string> = {
    'draft': 'info',        // 草稿
    'active': 'success',    // 激活
    'executing': 'warning', // 执行中
    'completed': '',        // 已完成
    'cancelled': 'danger'   // 已取消
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DispatchPlanApi.getDispatchPlanPage(queryParams)
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
    await DispatchPlanApi.deleteDispatchPlan(id)
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
    const data = await DispatchPlanApi.exportDispatchPlan(queryParams)
    download.excel(data, '调度方案.xls')
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

.plan-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.plan-details {
  display: flex;
  flex-direction: column;
  gap: 2px;
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.plan-id, .plan-no {
  font-family: monospace;
}

.demand-desc {
  font-size: 12px;
  color: #606266;
  font-style: italic;
  background: #f5f7fa;
  padding: 4px 8px;
  border-radius: 3px;
  margin-top: 4px;
}

.response-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.resp-id {
  color: #909399;
  margin-bottom: 4px;
}

.resp-no {
  font-weight: 500;
  color: #303133;
  font-family: monospace;
}

.resource-info {
  line-height: 1.4;
  text-align: left;
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

.res-id {
  font-family: monospace;
}

.quantity {
  font-size: 12px;
  color: #606266;
}

.quantity-number {
  color: #409eff;
  font-weight: 600;
  font-family: monospace;
}

.destination-info {
  line-height: 1.4;
  text-align: center;
}

.dispatch-dest {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.coordinates {
  font-size: 11px;
  color: #909399;
  font-family: monospace;
  background: #f5f7fa;
  padding: 2px 4px;
  border-radius: 3px;
  display: inline-block;
}

.no-coordinates {
  font-size: 11px;
  color: #c0c4cc;
  font-style: italic;
}

.charger-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.charger-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 2px;
}

.charger-id {
  color: #909399;
  margin-bottom: 4px;
}

.complete-time {
  color: #e6a23c;
  font-weight: 500;
}

.create-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.create-user {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.create-time {
  color: #909399;
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
