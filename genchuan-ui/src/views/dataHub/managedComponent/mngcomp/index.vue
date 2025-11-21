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
            <el-form-item label="统计ID" prop="statMngCompId">
              <el-input
                v-model="queryParams.statMngCompId"
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
            <el-form-item label="部件大类ID" prop="compMajorId">
              <el-input
                v-model="queryParams.compMajorId"
                placeholder="请输入部件大类ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="部件大类名称" prop="compMajorName">
              <el-input
                v-model="queryParams.compMajorName"
                placeholder="请输入部件大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="部件小类ID" prop="compMinorId">
              <el-input
                v-model="queryParams.compMinorId"
                placeholder="请输入部件小类ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第三行 -->
          <div class="form-row">
            <el-form-item label="部件小类名称" prop="compMinorName">
              <el-input
                v-model="queryParams.compMinorName"
                placeholder="请输入部件小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="部件总数" prop="totalCompCount">
              <el-input
                v-model="queryParams.totalCompCount"
                placeholder="请输入部件总数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="完好部件数" prop="normalCompCount">
              <el-input
                v-model="queryParams.normalCompCount"
                placeholder="请输入完好部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="破损部件数" prop="damagedCompCount">
              <el-input
                v-model="queryParams.damagedCompCount"
                placeholder="请输入破损部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第四行 -->
          <div class="form-row">
            <el-form-item label="丢失部件数" prop="lostCompCount">
              <el-input
                v-model="queryParams.lostCompCount"
                placeholder="请输入丢失部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="废弃部件数" prop="discardedCompCount">
              <el-input
                v-model="queryParams.discardedCompCount"
                placeholder="请输入废弃部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="新增部件数" prop="newCompCount">
              <el-input
                v-model="queryParams.newCompCount"
                placeholder="请输入新增部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="更新部件数" prop="updateCompCount">
              <el-input
                v-model="queryParams.updateCompCount"
                placeholder="请输入更新部件数"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第五行 -->
          <div class="form-row">
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
            <el-form-item class="search-actions">
              <el-button type="primary" @click="handleQuery">
                <Icon icon="ep:search" class="mr-5px" /> 搜索
              </el-button>
              <el-button @click="resetQuery">
                <Icon icon="ep:refresh" class="mr-5px" /> 重置
              </el-button>
            </el-form-item>
          </div>

          <!-- 操作按钮行 -->
          <div class="form-actions">
            <el-button
              type="primary"
              plain
              @click="openForm('create')"
              v-hasPermi="['datacenter:mng-comp:create']"
            >
              <Icon icon="ep:plus" class="mr-5px" /> 新增
            </el-button>
            <el-button
              type="success"
              plain
              @click="handleExport"
              :loading="exportLoading"
              v-hasPermi="['datacenter:mng-comp:export']"
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
        <el-table-column label="统计ID" align="center" prop="statMngCompId" width="120" />
        <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
        <el-table-column label="统计周期名称" align="center" prop="statCycleName" width="150" />
        <el-table-column label="行政区划代码" align="center" prop="areaCode" width="120" />
        <el-table-column label="行政区划名称" align="center" prop="areaName" width="150" />
        <el-table-column label="部件大类ID" align="center" prop="compMajorId" width="120" />
        <el-table-column label="部件大类名称" align="center" prop="compMajorName" width="150" />
        <el-table-column label="部件小类ID" align="center" prop="compMinorId" width="120" />
        <el-table-column label="部件小类名称" align="center" prop="compMinorName" width="150" />
        <el-table-column label="部件总数" align="center" prop="totalCompCount" width="100" />
        <el-table-column label="完好部件数" align="center" prop="normalCompCount" width="100" />
        <el-table-column label="破损部件数" align="center" prop="damagedCompCount" width="100" />
        <el-table-column label="丢失部件数" align="center" prop="lostCompCount" width="100" />
        <el-table-column label="废弃部件数" align="center" prop="discardedCompCount" width="100" />
        <el-table-column label="新增部件数" align="center" prop="newCompCount" width="100" />
        <el-table-column label="更新部件数" align="center" prop="updateCompCount" width="100" />
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
              v-hasPermi="['datacenter:mng-comp:update']"
            >
              编辑
            </el-button>
            <el-button
              link
              type="danger"
              size="small"
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mng-comp:delete']"
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
  <MngCompForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MngCompApi, MngCompVO } from '@/api/dataHub/managedComponent/mngcomp'
import MngCompForm from './MngCompForm.vue'

/** 管理部件统计 列表 */
defineOptions({ name: 'MngComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MngCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statMngCompId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalCompCount: undefined,
  normalCompCount: undefined,
  damagedCompCount: undefined,
  lostCompCount: undefined,
  discardedCompCount: undefined,
  newCompCount: undefined,
  updateCompCount: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MngCompApi.getMngCompPage(queryParams)
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
    await MngCompApi.deleteMngComp(id)
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
    const data = await MngCompApi.exportMngComp(queryParams)
    download.excel(data, '管理部件统计.xls')
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

.search-actions {
  display: flex;
  gap: 8px;
  margin-left: auto;
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

/* 数字统计列样式优化 */
:deep(.el-table .number-cell) {
  font-weight: 500;
  color: #409eff;
}
</style>
