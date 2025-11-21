<template>
  <!-- 模板部分保持不变 -->
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
        :inline="true"
        label-width="120px"
      >
        <div class="form-row">
          <el-form-item label="关联ID" prop="mngMatterMajorMinorId">
            <el-input
              v-model="queryParams.mngMatterMajorMinorId"
              placeholder="请输入关联ID"
              clearable
              @keyup.enter="handleQuery"
              class="!w-200px"
            />
          </el-form-item>
          <el-form-item label="关联管理事项大类ID" prop="majorId">
            <el-input
              v-model="queryParams.majorId"
              placeholder="请输入关联管理事项大类ID"
              clearable
              @keyup.enter="handleQuery"
              class="!w-200px"
            />
          </el-form-item>
          <el-form-item label="关联管理事项大类名称" prop="majorName">
            <el-input
              v-model="queryParams.majorName"
              placeholder="请输入关联管理事项大类名称"
              clearable
              @keyup.enter="handleQuery"
              class="!w-200px"
            />
          </el-form-item>
        </div>

        <div class="form-row">
          <el-form-item label="关联管理事项小类ID" prop="minorId">
            <el-input
              v-model="queryParams.minorId"
              placeholder="请输入关联管理事项小类ID"
              clearable
              @keyup.enter="handleQuery"
              class="!w-200px"
            />
          </el-form-item>
          <el-form-item label="关联管理事项小类名称" prop="minorName">
            <el-input
              v-model="queryParams.minorName"
              placeholder="请输入关联管理事项小类名称"
              clearable
              @keyup.enter="handleQuery"
              class="!w-200px"
            />
          </el-form-item>
          <el-form-item label="关联状态" prop="relStatus">
            <el-select
              v-model="queryParams.relStatus"
              placeholder="请选择关联状态"
              clearable
              class="!w-200px"
            >
              <el-option label="全部" value="" />
              <el-option label="有效" value="1" />
              <el-option label="无效" value="0" />
            </el-select>
          </el-form-item>
        </div>

        <div class="form-row">
          <el-form-item label="系统创建时间" prop="createTimeSys">
            <el-date-picker
              v-model="queryParams.createTimeSys"
              value-format="YYYY-MM-DD"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              clearable
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item label="系统更新时间" prop="updateTimeSys">
            <el-date-picker
              v-model="queryParams.updateTimeSys"
              value-format="YYYY-MM-DD"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              clearable
              class="!w-240px"
            />
          </el-form-item>
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
            v-hasPermi="['datacenter:rel-mng-matter-maj-min:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:rel-mng-matter-maj-min:export']"
          >
            <Icon icon="ep:download" class="mr-5px" /> 导出
          </el-button>
        </div>
      </el-form>
    </el-card>
  </ContentWrap>

  <!-- 列表和弹窗部分保持不变 -->
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

      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        style="width: 100%"
        :header-cell-style="{ background: '#f5f7fa', color: '#606266' }"
      >
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="关联ID" align="center" prop="mngMatterMajorMinorId" width="120" />
        <el-table-column label="关联管理事项大类ID" align="center" prop="majorId" width="150" />
        <el-table-column label="关联管理事项大类名称" align="center" prop="majorName" min-width="180" />
        <el-table-column label="关联管理事项小类ID" align="center" prop="minorId" width="150" />
        <el-table-column label="关联管理事项小类名称" align="center" prop="minorName" min-width="180" />
        <el-table-column label="关联状态" align="center" prop="relStatus" width="100">
          <template #default="scope">
            <el-tag
              :type="scope.row.relStatus === '1' ? 'success' : 'danger'"
              size="small"
            >
              {{ scope.row.relStatus === '1' ? '有效' : '无效' }}
            </el-tag>
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
        <el-table-column label="操作" align="center" width="150" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:rel-mng-matter-maj-min:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:rel-mng-matter-maj-min:delete']"
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

  <RelMngMatterMajMinForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
// 脚本部分保持不变
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { RelMngMatterMajMinApi, RelMngMatterMajMinVO } from '@/api/dataHub/managedComponent/relmngmattermajmin'
import RelMngMatterMajMinForm from './RelMngMatterMajMinForm.vue'

defineOptions({ name: 'RelMngMatterMajMin' })

const message = useMessage()
const { t } = useI18n()

const loading = ref(true)
const list = ref<RelMngMatterMajMinVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mngMatterMajorMinorId: undefined,
  majorId: undefined,
  majorName: undefined,
  minorId: undefined,
  minorName: undefined,
  relStatus: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref()
const exportLoading = ref(false)

const getList = async () => {
  loading.value = true
  try {
    const data = await RelMngMatterMajMinApi.getRelMngMatterMajMinPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

const resetQuery = () => {
  queryFormRef.value.resetFields()
  handleQuery()
}

const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await RelMngMatterMajMinApi.deleteRelMngMatterMajMin(id)
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await RelMngMatterMajMinApi.exportRelMngMatterMajMin(queryParams)
    download.excel(data, '管理事项大小类关联.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

onMounted(() => {
  getList()
})
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
  border-radius: 8px;
  overflow: hidden; /* 避免内容溢出卡片圆角 */
}

.table-card {
  border-radius: 8px;
  overflow: hidden;
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
  display: flex;
  flex-direction: column;
  gap: 18px; /* 行与行之间的间距 */
  padding: 15px 20px; /* 表单内边距，避免内容贴边 */
}

/* 核心优化：调整查询项间距，确保紧凑且对齐 */
.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 30px; /* 大幅减小原160px的间距，避免松散 */
  align-items: center; /* 确保标签和输入框垂直居中，防止错位 */
  padding: 5px 0; /* 行内上下内边距，增加呼吸感 */
}

.form-actions {
  display: flex;
  gap: 12px;
  padding-top: 16px;
  margin-top: 8px; /* 与上方查询项保持距离 */
  border-top: 1px solid #ebeef5;
  flex-wrap: wrap; /* 小屏幕下按钮自动换行 */
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

/* 调整Element组件样式，确保对齐 */
:deep(.el-card__header) {
  padding: 12px 20px;
  border-bottom: 1px solid #ebeef5;
  background-color: #fafafa; /* 标题区轻微背景色，区分内容区 */
}

:deep(.el-form-item) {
  margin-bottom: 0; /* 清除默认底部间距，避免行高过大 */
}

:deep(.el-form-item__label) {
  padding-right: 10px; /* 标签与输入框的间距 */
  white-space: nowrap; /* 标签文字不换行 */
}

:deep(.el-table .cell) {
  padding: 8px 12px;
}

:deep(.el-table th) {
  font-weight: 600;
}

/* 响应式适配：不同屏幕尺寸自动调整 */
@media (max-width: 1200px) {
  .form-row {
    gap: 20px; /* 中等屏幕减小间距 */
  }
}

@media (max-width: 992px) {
  .form-row {
    gap: 15px;
    flex-wrap: wrap;
  }
  /* 小屏幕下每行显示2个查询项 */
  :deep(.el-form-item) {
    width: calc(50% - 15px);
  }
}

@media (max-width: 768px) {
  /* 超小屏幕下每行显示1个查询项 */
  :deep(.el-form-item) {
    width: 100%;
  }
  .form-actions {
    justify-content: flex-start; /* 按钮左对齐，更符合小屏操作习惯 */
  }
}
</style>
