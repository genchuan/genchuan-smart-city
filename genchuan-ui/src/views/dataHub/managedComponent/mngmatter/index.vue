<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">查询条件</span>
        </div>
      </template>
      <el-form
        class="search-form"
        :model="queryParams"
        ref="queryFormRef"
        label-width="100px"
      >
        <div class="form-rows">
          <!-- 第一行 -->
          <div class="form-row">
            <el-form-item label="统计ID" prop="statMngMatterId">
              <el-input
                v-model="queryParams.statMngMatterId"
                placeholder="请输入统计ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="统计周期" prop="statCycle">
              <el-input
                v-model="queryParams.statCycle"
                placeholder="请输入统计周期"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="统计周期名称" prop="statCycleName">
              <el-input
                v-model="queryParams.statCycleName"
                placeholder="请输入统计周期名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="行政区划代码" prop="areaCode">
              <el-input
                v-model="queryParams.areaCode"
                placeholder="请输入行政区划代码"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第二行 -->
          <div class="form-row">
            <el-form-item label="行政区划名称" prop="areaName">
              <el-input
                v-model="queryParams.areaName"
                placeholder="请输入行政区划名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项大类ID" prop="matterMajorId">
              <el-input
                v-model="queryParams.matterMajorId"
                placeholder="请输入事项大类ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项大类名称" prop="matterMajorName">
              <el-input
                v-model="queryParams.matterMajorName"
                placeholder="请输入事项大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项小类ID" prop="matterMinorId">
              <el-input
                v-model="queryParams.matterMinorId"
                placeholder="请输入事项小类ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第三行 -->
          <div class="form-row">
            <el-form-item label="事项小类名称" prop="matterMinorName">
              <el-input
                v-model="queryParams.matterMinorName"
                placeholder="请输入事项小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="处置部门代码" prop="deptCode">
              <el-input
                v-model="queryParams.deptCode"
                placeholder="请输入处置部门代码"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="处置部门名称" prop="deptName">
              <el-input
                v-model="queryParams.deptName"
                placeholder="请输入处置部门名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="上报总数" prop="totalRptCount">
              <el-input
                v-model="queryParams.totalRptCount"
                placeholder="请输入上报总数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第四行 -->
          <div class="form-row">
            <el-form-item label="待处置数" prop="pendCount">
              <el-input
                v-model="queryParams.pendCount"
                placeholder="请输入待处置数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="处置中数" prop="handlCount">
              <el-input
                v-model="queryParams.handlCount"
                placeholder="请输入处置中数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="已办结数" prop="completedCount">
              <el-input
                v-model="queryParams.completedCount"
                placeholder="请输入已办结数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="已驳回数" prop="rejectedCount">
              <el-input
                v-model="queryParams.rejectedCount"
                placeholder="请输入已驳回数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第五行 -->
          <div class="form-row">
            <el-form-item label="办结率" prop="completeRate">
              <el-input
                v-model="queryParams.completeRate"
                placeholder="请输入办结率"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="平均处置时长" prop="avgHandleEndure">
              <el-input
                v-model="queryParams.avgHandleEndure"
                placeholder="请输入平均处置时长"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="系统创建时间" prop="createTimeSys">
              <el-date-picker
                v-model="queryParams.createTimeSys"
                value-format="YYYY-MM-DD"
                type="date"
                placeholder="选择系统创建时间"
                clearable
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="系统更新时间" prop="updateTimeSys">
              <el-date-picker
                v-model="queryParams.updateTimeSys"
                value-format="YYYY-MM-DD"
                type="date"
                placeholder="选择系统更新时间"
                clearable
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 操作按钮行 -->
          <div class="form-actions">
            <el-button type="primary" @click="handleQuery">
              <Icon icon="ep:search" class="mr-5px" /> 搜索
            </el-button>
            <el-button @click="resetQuery">
              <Icon icon="ep:refresh" class="mr-5px" /> 重置
            </el-button>
            <el-button
              type="primary"
              plain
              @click="openForm('create')"
              v-hasPermi="['datacenter:mng-matter:create']"
            >
              <Icon icon="ep:plus" class="mr-5px" /> 新增
            </el-button>
            <el-button
              type="success"
              plain
              @click="handleExport"
              :loading="exportLoading"
              v-hasPermi="['datacenter:mng-matter:export']"
            >
              <Icon icon="ep:download" class="mr-5px" /> 导出
            </el-button>
          </div>
        </div>
      </el-form>
    </el-card>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">数据列表</span>
          <div class="table-info">
            共 <span class="info-highlight">{{ total }}</span> 条记录
          </div>
        </div>
      </template>

      <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="统计ID" align="center" prop="statMngMatterId" width="120" />
        <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
        <el-table-column label="统计周期名称" align="center" prop="statCycleName" width="150" />
        <el-table-column label="行政区划代码" align="center" prop="areaCode" width="120" />
        <el-table-column label="行政区划名称" align="center" prop="areaName" width="150" />
        <el-table-column label="事项大类ID" align="center" prop="matterMajorId" width="120" />
        <el-table-column label="事项大类名称" align="center" prop="matterMajorName" width="150" />
        <el-table-column label="事项小类ID" align="center" prop="matterMinorId" width="120" />
        <el-table-column label="事项小类名称" align="center" prop="matterMinorName" width="150" />
        <el-table-column label="处置部门代码" align="center" prop="deptCode" width="120" />
        <el-table-column label="处置部门名称" align="center" prop="deptName" width="150" />
        <el-table-column label="上报总数" align="center" prop="totalRptCount" width="100" />
        <el-table-column label="待处置数" align="center" prop="pendCount" width="100" />
        <el-table-column label="处置中数" align="center" prop="handlCount" width="100" />
        <el-table-column label="已办结数" align="center" prop="completedCount" width="100" />
        <el-table-column label="已驳回数" align="center" prop="rejectedCount" width="100" />
        <el-table-column label="办结率" align="center" prop="completeRate" width="100">
          <template #default="scope">
            <span class="rate-cell">{{ scope.row.completeRate }}%</span>
          </template>
        </el-table-column>
        <el-table-column label="平均处置时长" align="center" prop="avgHandleEndure" width="120">
          <template #default="scope">
            <span class="duration-cell">{{ scope.row.avgHandleEndure }}天</span>
          </template>
        </el-table-column>
        <el-table-column
          label="系统创建时间"
          align="center"
          prop="createTimeSys"
          :formatter="dateFormatter"
          width="180"
        />
        <el-table-column
          label="系统更新时间"
          align="center"
          prop="updateTimeSys"
          :formatter="dateFormatter"
          width="180"
        />
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              link
              type="primary"
              size="small"
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mng-matter:update']"
            >
              编辑
            </el-button>
            <el-button
              link
              type="danger"
              size="small"
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mng-matter:delete']"
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
  <MngMatterForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MngMatterApi, MngMatterVO } from '@/api/dataHub/managedComponent/mngmatter'
import MngMatterForm from './MngMatterForm.vue'

/** 管理事项统计 列表 */
defineOptions({ name: 'MngMatter' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MngMatterVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statMngMatterId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  matterMajorId: undefined,
  matterMajorName: undefined,
  matterMinorId: undefined,
  matterMinorName: undefined,
  deptCode: undefined,
  deptName: undefined,
  totalRptCount: undefined,
  pendCount: undefined,
  handlCount: undefined,
  completedCount: undefined,
  rejectedCount: undefined,
  completeRate: undefined,
  avgHandleEndure: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MngMatterApi.getMngMatterPage(queryParams)
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
    await MngMatterApi.deleteMngMatter(id)
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
    const data = await MngMatterApi.exportMngMatter(queryParams)
    download.excel(data, '管理事项统计.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
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
  border: 1px solid #e8e8e8;
}

.table-card {
  border-radius: 8px;
  border: 1px solid #e8e8e8;
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

.table-info {
  font-size: 14px;
  color: #606266;
}

.info-highlight {
  color: #409eff;
  font-weight: 600;
}

.search-form {
  padding: 16px 8px 8px;
}

.form-rows {
  display: flex;
  flex-direction: column;
  gap: 0;
}

.form-row {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin-bottom: 16px;
  padding-bottom: 16px;
  border-bottom: 1px dashed #f0f0f0;
}

.form-row:last-of-type {
  border-bottom: none;
  margin-bottom: 0;
  padding-bottom: 0;
}

.form-input {
  width: 200px !important;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding-top: 16px;
  margin-top: 8px;
  border-top: 1px solid #ebeef5;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

.rate-cell {
  color: #67c23a;
  font-weight: 600;
}

.duration-cell {
  color: #e6a23c;
  font-weight: 500;
}

:deep(.el-card__header) {
  padding: 12px 20px;
  border-bottom: 1px solid #ebeef5;
  background-color: #fafafa;
}

:deep(.el-table .cell) {
  padding: 8px 12px;
}

:deep(.el-table th) {
  font-weight: 600;
}

:deep(.el-form-item) {
  margin-bottom: 0;
  display: flex;
  align-items: center;
  min-height: 32px;
}

:deep(.el-form-item__content) {
  display: flex;
  align-items: center;
  flex: 1;
}

:deep(.el-form-item__label) {
  font-weight: 500;
  color: #606266;
  text-align: right;
  padding-right: 12px;
  line-height: 32px;
  flex: 0 0 100px;
}

:deep(.el-button) {
  border-radius: 4px;
}

/* 修复日期选择器对齐问题 */
:deep(.el-date-editor) {
  line-height: 32px;
}

:deep(.el-date-editor .el-range-input) {
  vertical-align: middle;
}

:deep(.el-date-editor .el-range-separator) {
  line-height: 24px;
}

/* 表格样式优化 */
:deep(.el-table) {
  border: 1px solid #e8e8e8;
  border-radius: 4px;
}

:deep(.el-table__header) {
  background-color: #f5f7fa;
}

:deep(.el-table th) {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: 600;
}

:deep(.el-table td) {
  border-bottom: 1px solid #e8e8e8;
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped td) {
  background-color: #fafafa;
}

/* 统计数字列样式优化 */
:deep(.el-table .number-cell) {
  font-weight: 500;
}

/* 办结率列样式 */
:deep(.el-table .rate-cell) {
  color: #67c23a;
  font-weight: 600;
}

/* 处置时长列样式 */
:deep(.el-table .duration-cell) {
  color: #e6a23c;
  font-weight: 500;
}
</style>
