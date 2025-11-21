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
            <el-form-item label="事项ID" prop="mngMatterId">
              <el-input
                v-model="queryParams.mngMatterId"
                placeholder="请输入事项ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项标识码" prop="matterCode">
              <el-input
                v-model="queryParams.matterCode"
                placeholder="请输入事项标识码"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项名称" prop="matterName">
              <el-input
                v-model="queryParams.matterName"
                placeholder="请输入事项名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="关联管理事项小类ID" prop="minorId">
              <el-input
                v-model="queryParams.minorId"
                placeholder="请输入关联管理事项小类ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第二行 -->
          <div class="form-row">
            <el-form-item label="关联管理事项小类名称" prop="minorName">
              <el-input
                v-model="queryParams.minorName"
                placeholder="请输入关联管理事项小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="关联单元网格ID" prop="gridId">
              <el-input
                v-model="queryParams.gridId"
                placeholder="请输入关联单元网格ID"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="关联单元网格名称" prop="gridName">
              <el-input
                v-model="queryParams.gridName"
                placeholder="请输入关联单元网格名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事项状态" prop="matterStatus">
              <el-select
                v-model="queryParams.matterStatus"
                placeholder="请选择事项状态"
                clearable
                class="form-input"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </div>

          <!-- 第三行 -->
          <div class="form-row">
            <el-form-item label="事项等级" prop="matterLevel">
              <el-input
                v-model="queryParams.matterLevel"
                placeholder="请输入事项等级"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="主管部门代码" prop="deptCode">
              <el-input
                v-model="queryParams.deptCode"
                placeholder="请输入主管部门代码"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="主管部门名称" prop="deptName">
              <el-input
                v-model="queryParams.deptName"
                placeholder="请输入主管部门名称"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
            <el-form-item label="事发位置" prop="incidentLocation">
              <el-input
                v-model="queryParams.incidentLocation"
                placeholder="请输入事发位置"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
              />
            </el-form-item>
          </div>

          <!-- 第四行 -->
          <div class="form-row">
            <el-form-item label="录入人账号" prop="createUser">
              <el-input
                v-model="queryParams.createUser"
                placeholder="请输入录入人账号"
                clearable
                @keyup.enter="handleQuery"
                class="form-input"
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
                class="date-range-input"
              />
            </el-form-item>
            <el-form-item label="修改人账号" prop="updateUser">
              <el-input
                v-model="queryParams.updateUser"
                placeholder="请输入修改人账号"
                clearable
                @keyup.enter="handleQuery"
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
            <el-form-item class="form-actions">
              <el-button
                type="primary"
                plain
                @click="openForm('create')"
                v-hasPermi="['datacenter:biz-mng-matter:create']"
              >
                <Icon icon="ep:plus" class="mr-5px" /> 新增
              </el-button>
              <el-button
                type="success"
                plain
                @click="handleExport"
                :loading="exportLoading"
                v-hasPermi="['datacenter:biz-mng-matter:export']"
              >
                <Icon icon="ep:download" class="mr-5px" /> 导出
              </el-button>
            </el-form-item>
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
        <el-table-column label="事项ID" align="center" prop="mngMatterId" width="120" />
        <el-table-column label="事项标识码" align="center" prop="matterCode" width="120" />
        <el-table-column label="事项名称" align="center" prop="matterName" width="150" />
        <el-table-column label="关联管理事项小类ID" align="center" prop="minorId" width="150" />
        <el-table-column label="关联管理事项小类名称" align="center" prop="minorName" width="180" />
        <el-table-column label="关联单元网格ID" align="center" prop="gridId" width="120" />
        <el-table-column label="关联单元网格名称" align="center" prop="gridName" width="150" />
        <el-table-column label="事项状态" align="center" prop="matterStatus" width="100" />
        <el-table-column label="事项等级" align="center" prop="matterLevel" width="100" />
        <el-table-column label="主管部门代码" align="center" prop="deptCode" width="120" />
        <el-table-column label="主管部门名称" align="center" prop="deptName" width="150" />
        <el-table-column label="事发位置" align="center" prop="incidentLocation" width="150" />
        <el-table-column label="录入人账号" align="center" prop="createUser" width="120" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          :formatter="dateFormatter"
          width="180"
        />
        <el-table-column label="修改人账号" align="center" prop="updateUser" width="120" />
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
              v-hasPermi="['datacenter:biz-mng-matter:update']"
            >
              编辑
            </el-button>
            <el-button
              link
              type="danger"
              size="small"
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:biz-mng-matter:delete']"
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
  <BizMngMatterForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { BizMngMatterApi, BizMngMatterVO } from '@/api/dataHub/managedComponent/bizmngmatter'
import BizMngMatterForm from './BizMngMatterForm.vue'

/** 管理事项信息 列表 */
defineOptions({ name: 'BizMngMatter' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BizMngMatterVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mngMatterId: undefined,
  matterCode: undefined,
  matterName: undefined,
  minorId: undefined,
  minorName: undefined,
  gridId: undefined,
  gridName: undefined,
  matterStatus: undefined,
  matterLevel: undefined,
  deptCode: undefined,
  deptName: undefined,
  incidentLocation: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BizMngMatterApi.getBizMngMatterPage(queryParams)
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
    await BizMngMatterApi.deleteBizMngMatter(id)
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
    const data = await BizMngMatterApi.exportBizMngMatter(queryParams)
    download.excel(data, '管理事项信息.xls')
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

.date-range-input {
  width: 220px !important;
}

.search-actions {
  display: flex;
  gap: 8px;
  margin-left: auto;
}

.form-actions {
  display: flex;
  gap: 8px;
  margin-left: auto;
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
</style>
