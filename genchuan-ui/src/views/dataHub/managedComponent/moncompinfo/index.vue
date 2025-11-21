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
            <el-form-item label="部件ID" prop="monCompId">
              <el-input
                v-model="queryParams.monCompId"
                placeholder="请输入部件ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件标识码" prop="compCode">
              <el-input
                v-model="queryParams.compCode"
                placeholder="请输入部件标识码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件名称" prop="compName">
              <el-input
                v-model="queryParams.compName"
                placeholder="请输入部件名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 管理部门信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">管理部门信息</div>
          <div class="form-row">
            <el-form-item label="主管部门代码" prop="deptCode">
              <el-input
                v-model="queryParams.deptCode"
                placeholder="请输入主管部门代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="主管部门名称" prop="deptName">
              <el-input
                v-model="queryParams.deptName"
                placeholder="请输入主管部门名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件分类ID" prop="compCatId">
              <el-input
                v-model="queryParams.compCatId"
                placeholder="请输入部件分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 位置信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">位置信息</div>
          <div class="form-row">
            <el-form-item label="行政区域代码" prop="regionCode">
              <el-input
                v-model="queryParams.regionCode"
                placeholder="请输入行政区域代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="行政区域名称" prop="regionName">
              <el-input
                v-model="queryParams.regionName"
                placeholder="请输入行政区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="所在网格ID" prop="gridId">
              <el-input
                v-model="queryParams.gridId"
                placeholder="请输入所在网格ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="所在网格名称" prop="gridName">
              <el-input
                v-model="queryParams.gridName"
                placeholder="请输入所在网格名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="坐标X(经度)" prop="coordX">
              <el-input
                v-model="queryParams.coordX"
                placeholder="请输入坐标X，经度"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="坐标Y(纬度)" prop="coordY">
              <el-input
                v-model="queryParams.coordY"
                placeholder="请输入坐标Y，纬度"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 时间范围 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">时间范围</div>
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
            v-hasPermi="['datacenter:mon-comp-info:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-comp-info:export']"
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
          <span class="card-title">监测部件信息列表</span>
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
        @sort-change="handleSortChange"
      >
        <el-table-column label="ID" align="center" prop="id" width="70" sortable="custom" />
        <el-table-column label="部件ID" align="center" prop="monCompId" width="100" sortable="custom" />
        <el-table-column label="部件标识码" align="center" prop="compCode" width="120" />
        <el-table-column label="部件名称" align="center" prop="compName" min-width="150" show-overflow-tooltip />
        <el-table-column label="部件分类ID" align="center" prop="compCatId" width="110" />
        <el-table-column label="主管部门" align="center" min-width="140" show-overflow-tooltip>
          <template #default="scope">
            <div class="dept-info">
              <div class="dept-code">{{ scope.row.deptCode }}</div>
              <div class="dept-name">{{ scope.row.deptName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="行政区域" align="center" min-width="140" show-overflow-tooltip>
          <template #default="scope">
            <div class="region-info">
              <div class="region-code">{{ scope.row.regionCode }}</div>
              <div class="region-name">{{ scope.row.regionName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="所在网格" align="center" min-width="140" show-overflow-tooltip>
          <template #default="scope">
            <div class="grid-info">
              <div class="grid-id">ID: {{ scope.row.gridId }}</div>
              <div class="grid-name">{{ scope.row.gridName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="坐标位置" align="center" width="140">
          <template #default="scope">
            <div class="coord-info" v-if="scope.row.coordX && scope.row.coordY">
              <div>X: {{ scope.row.coordX }}</div>
              <div>Y: {{ scope.row.coordY }}</div>
            </div>
            <span v-else class="no-coord">-</span>
          </template>
        </el-table-column>
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTimeSys"
          :formatter="dateFormatter"
          width="160"
          sortable="custom"
        />
        <el-table-column
          label="更新时间"
          align="center"
          prop="updateTimeSys"
          :formatter="dateFormatter"
          width="160"
          sortable="custom"
        />
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mon-comp-info:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-comp-info:delete']"
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
  <MonCompInfoForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MonCompInfoApi, MonCompInfoVO } from '@/api/dataHub/managedComponent/moncompinfo'
import MonCompInfoForm from './MonCompInfoForm.vue'

/** 监测部件信息 列表 */
defineOptions({ name: 'MonCompInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonCompInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  monCompId: undefined,
  compCode: undefined,
  compName: undefined,
  compCatId: undefined,
  deptCode: undefined,
  deptName: undefined,
  regionCode: undefined,
  regionName: undefined,
  gridId: undefined,
  gridName: undefined,
  coordX: undefined,
  coordY: undefined,
  createTimeSys: [],
  updateTimeSys: [],
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonCompInfoApi.getMonCompInfoPage(queryParams)
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
    await MonCompInfoApi.deleteMonCompInfo(id)
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
    const data = await MonCompInfoApi.exportMonCompInfo(queryParams)
    download.excel(data, '监测部件信息.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 切换搜索表单显示 */
const toggleSearchForm = () => {
  showFullSearch.value = !showFullSearch.value
}

/** 排序处理 */
const handleSortChange = (column: any) => {
  if (column.prop) {
    queryParams.sortField = column.prop
    queryParams.sortOrder = column.order === 'ascending' ? 'asc' :
      column.order === 'descending' ? 'desc' : undefined
  } else {
    queryParams.sortField = undefined
    queryParams.sortOrder = undefined
  }
  getList()
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
.dept-info,
.region-info,
.grid-info {
  line-height: 1.4;
}

.dept-code,
.region-code,
.grid-id {
  font-size: 12px;
  color: #909399;
}

.dept-name,
.region-name,
.grid-name {
  font-size: 13px;
  color: #606266;
}

.coord-info {
  font-size: 12px;
  line-height: 1.4;
  color: #606266;
}

.no-coord {
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
</style>
