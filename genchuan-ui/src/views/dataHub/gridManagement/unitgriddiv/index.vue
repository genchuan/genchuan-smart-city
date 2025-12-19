<template>
  <ContentWrap class="grid-page">
    <!-- 查询条件区 -->
    <el-form :inline="true" :model="queryParams" class="mb-3 flex-wrap">
      <el-form-item label="单元网格名称">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入单元网格名称"
          clearable
          class="!w-220px"
        />
      </el-form-item>

      <!--      <el-form-item label="社区ID">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.commId"-->
      <!--          placeholder="请输入社区ID"-->
      <!--          clearable-->
      <!--          class="!w-200px"-->
      <!--        />-->
      <!--      </el-form-item>-->

      <el-form-item label="所属社区">
        <el-tree-select
          v-model="queryParams.commId"
          :data="commList"
          node-key="fullCode"
          :props="treeProps"
          placeholder="请选择所属社区"
          check-strictly
          filterable
          clearable
          class="!w-240px"
          @change="handleCommChange"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" plain @click="handleQuery">查询</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button type="primary" plain @click="handleAdd">新增网格</el-button>
        <el-button type="info" plain @click="handleImport">批量导入</el-button>
        <el-button type="warning" plain @click="handleCheckOverlap">检查边界重叠</el-button>
        <el-button type="success" plain :loading="exportLoading" @click="handleExport">
          导出
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮区 -->
    <!--    <div class="mb-4 flex justify-end space-x-2">-->
    <!--      <el-button type="primary" plain @click="handleAdd">新增网格</el-button>-->
    <!--      <el-button type="info" plain @click="handleImport">批量导入</el-button>-->
    <!--      <el-button type="warning" plain @click="handleCheckOverlap">检查边界重叠</el-button>-->
    <!--      <el-button type="success" plain :loading="exportLoading" @click="handleExport">-->
    <!--        导出-->
    <!--      </el-button>-->
    <!--    </div>-->

    <!-- 数据表格 -->
    <div class="table-container">
      <GridTable
        :loading="loading"
        :data="tableData"
        @edit="handleEdit"
        @view="handleView"
        @delete="handleDelete"
        @import="handleImport"
      />
    </div>

    <!-- 分页 -->
    <Pagination
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      :total="total"
      @pagination="loadData"
    />

    <!-- 弹窗/抽屉组件 -->
    <GridFormDialog ref="formDialogRef" @success="loadData" />
    <BoundaryDrawer ref="boundaryDrawerRef" />
    <BoundaryCheckPanel ref="checkPanelRef" />
    <CoordinateImportDialog ref="importDialogRef" @success="loadData" />
  </ContentWrap>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import download from '@/utils/download'
import {
  UnitGridDivApi,
  type UnitGridDivVO
} from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'

import GridTable from './components/GridTable.vue'
import GridFormDialog from './components/GridFormDialog.vue'
import BoundaryDrawer from './components/BoundaryDrawer.vue'
import BoundaryCheckPanel from './components/BoundaryCheckPanel.vue'
import CoordinateImportDialog from './components/CoordinateImportDialog.vue'

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  name: '',
  commId: ''
})

const tableData = ref<UnitGridDivVO[]>([])
const total = ref(0)
const loading = ref(false)
const exportLoading = ref(false)

const formDialogRef = ref()
const boundaryDrawerRef = ref()
const checkPanelRef = ref()
const importDialogRef = ref()

const loadData = async () => {
  loading.value = true
  try {
    const res = await UnitGridDivApi.getUnitGridDivPage({
      pageNo: queryParams.pageNo,
      pageSize: queryParams.pageSize,
      unitGridName: queryParams.name,
      commId: queryParams.commId
    })
    tableData.value = res.list || []
    total.value = res.total || tableData.value.length
  } catch (e) {
    ElMessage.error('加载数据失败')
  } finally {
    loading.value = false
  }
}

//社区下拉查询
const commList = ref<any[]>([])
const treeProps = {
  label: 'name',
  children: 'children',
  value: 'fullCode'
}

/** 加载社区树结构 */
const loadCommList = async () => {
  try {
    const res = await AreaApi.getTakeEffect()
    commList.value =
      res?.data?.communityList ?? res?.data?.townList ?? res?.communityList ?? res?.townList ?? []
  } catch (err) {
    console.error(err)
    ElMessage.error('社区数据加载失败')
  }
}

/** 社区选择变更 */
const handleCommChange = (val: string) => {
  queryParams.commId = val
}

const handleQuery = () => {
  queryParams.pageNo = 1
  loadData()
}

const resetQuery = () => {
  queryParams.name = ''
  queryParams.commId = ''
  queryParams.pageNo = 1
  loadData()
}

const handleAdd = () => {
  formDialogRef.value.open('add')
}

const handleEdit = (row: UnitGridDivVO) => {
  formDialogRef.value.open('edit', row)
}

const handleDelete = async (row: UnitGridDivVO) => {
  try {
    await ElMessageBox.confirm(`确定删除【${row.unitGridName}】吗？`, '提示', { type: 'warning' })
    await UnitGridDivApi.deleteUnitGridDiv(row.id)
    ElMessage.success('删除成功')
    loadData()
  } catch {}
}

const handleView = (row: UnitGridDivVO) => {
  boundaryDrawerRef.value.open(row)
}

const handleCheckOverlap = () => {
  checkPanelRef.value.open()
}

const handleImport = (row?: any) => {
  if (row) {
    importDialogRef.value.open({ commId: row.commId })
  } else {
    importDialogRef.value.open()
  }
}

const handleExport = async () => {
  try {
    exportLoading.value = true
    const data = await UnitGridDivApi.exportUnitGridDiv({
      unitGridName: queryParams.name,
      commId: queryParams.commId
    })
    download.excel(data, '单元网格划分数据.xls')
    ElMessage.success('导出成功')
  } catch {
    ElMessage.error('导出失败')
  } finally {
    exportLoading.value = false
  }
}

onMounted(() => {
  loadCommList()
  loadData()
})
</script>

<style scoped>
.grid-page {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.table-container {
  flex: 1;
  overflow: auto;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.05);
}

.el-table {
  width: 100%;
  font-size: 14px;
  word-break: break-all;
  table-layout: fixed !important;
}

.el-table th,
.el-table td {
  text-align: center;
  white-space: normal !important;
  word-wrap: break-word;
  padding: 10px 6px;
}

.mb-3 {
  margin-bottom: 0;
}

.mb-4 {
  margin-bottom: 16px;
}
</style>
