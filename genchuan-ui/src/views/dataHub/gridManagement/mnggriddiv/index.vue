<template>
  <ContentWrap class="grid-page">
    <!-- 查询条件区 -->
    <el-form :inline="true" :model="queryParams" class="mb-3 flex-wrap">
      <el-form-item label="网格名称">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入网格名称"
          clearable
          class="!w-240px"
        />
      </el-form-item>

      <!--      <el-form-item label="所属乡镇ID">-->
      <!--        <el-input-->
      <!--            v-model="queryParams.townStreetId"-->
      <!--            placeholder="请输入乡镇ID"-->
      <!--            clearable-->
      <!--            class="!w-160px"-->
      <!--        />-->
      <!--      </el-form-item>-->

      <el-form-item label="所属乡镇">
        <el-tree-select
          v-model="queryParams.townStreetId"
          :data="townList"
          node-key="fullCode"
          :props="treeProps"
          placeholder="请选择到社区"
          check-strictly
          filterable
          clearable
          class="!w-240px"
          @change="handleTownChange"
        />
      </el-form-item>

      <el-form-item label="所含单元数">
        <el-select v-model="queryParams.unitRange" placeholder="请选择" class="!w-160px">
          <el-option label="全部" value="" />
          <el-option label="1-3个" value="1-3" />
          <el-option label="4-6个" value="4-6" />
          <el-option label="7个及以上" value="7+" />
        </el-select>
      </el-form-item>

      <el-form-item label="划分时间">
        <el-date-picker
          v-model="queryParams.divTime"
          type="datetime"
          placeholder="请选择划分时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" plain @click="handleQuery">查询</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button type="primary" plain @click="openFormDialog()">新增网格</el-button>
        <el-button type="info" plain @click="handleImport">
          <Icon icon="ep:document-add" class="mr-5px" />
          批量导入单元网格
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮区 -->
    <!--    <div class="mb-4 flex justify-end space-x-2">-->
    <!--      <el-button type="primary" plain @click="openFormDialog()">新增网格</el-button>-->
    <!--      <el-button type="info" plain @click="handleImport">-->
    <!--        <Icon icon="ep:document-add" class="mr-5px" />-->
    <!--        批量导入单元网格-->
    <!--      </el-button>-->
    <!--      <el-button type="success" plain @click="handleExport" :loading="exportLoading">-->
    <!--        <Icon icon="ep:download" class="mr-5px" />-->
    <!--        导出-->
    <!--      </el-button>-->
    <!--    </div>-->

    <!-- 数据表格 -->
    <div class="table-container">
      <el-table
        v-loading="loading"
        :data="tableData"
        stripe
        border
        height="calc(100vh - 350px)"
        style="width: 100%; table-layout: fixed"
        @sort-change="handleSortChange"
      >
        <el-table-column prop="mngGridName" label="管理网格名称" min-width="150" />
        <el-table-column prop="townStreetId" label="所属乡镇" width="160" />
        <el-table-column prop="area" label="面积(m²)" width="150" sortable="custom" />
        <el-table-column prop="divTime" label="划分时间" width="200" :formatter="dateFormatter" />
        <el-table-column prop="extCommon2" label="网格员" width="180" />
        <el-table-column fixed="right" label="操作" width="240">
          <template #default="{ row }">
            <el-button link type="primary" @click="openDetail(row)">详情</el-button>
            <el-button link type="primary" @click="openFormDialog(row)">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页 -->
    <Pagination
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      :total="total"
      @pagination="loadData"
    />

    <!-- 弹窗 -->
    <MngGridDivForm ref="formDialogRef" @refresh="loadData" />
    <MngGridDivImportDialog ref="importDialogRef" @refresh="loadData" />
    <MngGridDivDetail ref="detailRef" />
  </ContentWrap>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import { MngGridDivApi, MngGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/mnggriddiv'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'
import MngGridDivForm from './MngGridDivForm.vue'
import MngGridDivDetail from './MngGridDivDetail.vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import MngGridDivImportDialog from './MngGridDivImportDialog.vue'

const loading = ref(false)
const tableData = ref<MngGridDivVO[]>([])
const total = ref(0)
const exportLoading = ref(false)

const queryParams = ref({
  name: '',
  townStreetId: '',
  unitRange: '',
  divTime: '',
  sortField: '',
  sortOrder: '',
  pageNo: 1,
  pageSize: 10
})

const formDialogRef = ref()
const detailRef = ref()
const importDialogRef = ref()

const parseUnitRange = (range: string) => {
  if (!range) return { minUnits: undefined, maxUnits: undefined }
  if (range === '7+') return { minUnits: 7, maxUnits: undefined }
  const [min, max] = range.split('-').map(Number)
  return { minUnits: min, maxUnits: max }
}

const loadData = async () => {
  loading.value = true
  try {
    if (queryParams.value.unitRange) {
      const { minUnits, maxUnits } = parseUnitRange(queryParams.value.unitRange)
      const townStreetIds = [...new Set(tableData.value.map((d) => d.townStreetId))]
      if (townStreetIds.length === 0) {
        tableData.value = []
        total.value = 0
      } else {
        const data: MngGridDivVO[] = []
        for (const id of townStreetIds) {
          const res = await MngGridDivApi.listByUnitCount({ townStreetId: id, minUnits, maxUnits })
          data.push(...res)
        }
        tableData.value = data
        total.value = data.length
      }
    } else {
      const params: any = { ...queryParams.value, mngGridName: queryParams.value.name }
      if (queryParams.value.divTime) params.divTime = [queryParams.value.divTime]
      else delete params.divTime

      if (queryParams.value.sortField) params.sortField = queryParams.value.sortField
      if (queryParams.value.sortOrder) params.sortOrder = queryParams.value.sortOrder

      const res = await MngGridDivApi.getMngGridDivPage(params)
      tableData.value = res.list || []
      total.value = res.total || 0
    }
  } finally {
    loading.value = false
  }
}

//社区下拉查询
const townList = ref<any[]>([])
const treeProps = {
  label: 'name',
  children: 'children',
  value: 'fullCode'
}

/** 加载社区树结构 */
const loadTownList = async () => {
  try {
    const res = await AreaApi.getTakeEffect()
    townList.value =
      res?.data?.communityList ?? res?.data?.townList ?? res?.communityList ?? res?.townList ?? []
  } catch (err) {
    console.error(err)
    ElMessage.error('乡镇数据加载失败')
  }
}

/** 社区选择变更 */
const handleTownChange = (val: string) => {
  queryParams.value.townStreetId = val
}

const handleQuery = () => {
  queryParams.value.pageNo = 1
  loadData()
}

const resetQuery = () => {
  queryParams.value = {
    name: '',
    townStreetId: '',
    unitRange: '',
    divTime: '',
    sortField: '',
    sortOrder: '',
    pageNo: 1,
    pageSize: 10
  }
  loadData()
}

const handleSortChange = ({ prop, order }: any) => {
  if (prop === 'area') {
    queryParams.value.sortField = prop
    queryParams.value.sortOrder =
      order === 'ascending' ? 'asc' : order === 'descending' ? 'desc' : ''
    loadData()
  }
}

const openFormDialog = (row?: MngGridDivVO) => {
  if (row) formDialogRef.value.open('edit', row)
  else formDialogRef.value.open('create')
}

const handleDelete = (row: MngGridDivVO) => {
  ElMessageBox.confirm('确定要删除该管理网格吗？', '提示', { type: 'warning' })
    .then(async () => {
      await MngGridDivApi.deleteMngGridDiv(row.id)
      ElMessage.success('删除成功')
      await loadData()
    })
    .catch(() => {})
}

const openDetail = (row: MngGridDivVO) => {
  detailRef.value.open(row.id)
}

const handleImport = () => {
  importDialogRef.value.open()
}

const handleExport = async () => {
  try {
    exportLoading.value = true
    const exportParams: any = { ...queryParams.value, mngGridName: queryParams.value.name }
    if (queryParams.value.divTime) exportParams.divTime = [queryParams.value.divTime]
    else delete exportParams.divTime

    const data = await MngGridDivApi.exportMngGridDiv(exportParams)
    download.excel(data, '管理网格划分.xls')
    ElMessage.success('导出成功')
  } catch (error) {
    console.error(error)
  } finally {
    exportLoading.value = false
  }
}

onMounted(() => {
  loadTownList()
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
