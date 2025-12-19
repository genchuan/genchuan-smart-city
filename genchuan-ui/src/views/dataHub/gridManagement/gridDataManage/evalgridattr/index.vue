<template>
  <ContentWrap>
    <!-- 搜索表单保持原样 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <!-- 搜索条件（网格类型+指标名称） -->
      <el-form-item label="评价网格类型" prop="gridType">
        <el-select
          v-model="queryParams.gridType"
          placeholder="请选择类型"
          clearable
          class="!w-240px"
        >
          <el-option v-for="type in gridTypes" :key="type" :label="type" :value="type" />
        </el-select>
      </el-form-item>

      <el-form-item label="指标名称" prop="idxName">
        <el-input
          v-model="queryParams.idxName"
          placeholder="请输入指标名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          >导出</el-button
        >
        <el-button type="warning" plain @click="openBatchAssign">批量关联指标</el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      @selection-change="handleSelectionChange"
      ref="tableRef"
    >
      <el-table-column type="selection" width="50" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="评价网格编码" align="center" prop="evalGridCode" />
      <el-table-column label="面积(m²)" align="center" prop="area" />
      <el-table-column label="评价网格类型" prop="gridType" align="center">
        <template #default="{ row }">
          <el-tag
            :type="row.gridType === 'A' ? 'danger' : row.gridType === 'B' ? 'primary' : 'success'"
          >
            {{ row.gridType }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="关联评价指标名称" align="center" prop="idxName" />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
          <el-button link type="primary" @click="openForm(scope.row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 编辑表单 -->
  <EvalGridAttrForm ref="formRef" @success="getList" />

  <!-- 批量关联指标 -->
  <el-dialog title="批量关联指标" v-model="batchAssignVisible">
    <el-form :model="batchForm">
      <el-form-item label="选择指标">
        <el-select v-model="batchForm.idxId" placeholder="请选择指标">
          <el-option
            v-for="item in filteredIndicators"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="batchAssignVisible = false">取消</el-button>
      <el-button type="primary" @click="handleBatchAssign">确认</el-button>
    </template>
  </el-dialog>

  <!-- 详情抽屉 -->
  <EvalGridAttrDetailDrawer v-model="detailDrawerVisible" :record="detailRecord" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import {
  EvalGridAttrApi,
  EvalGridAttrVO
} from '@/api/dataHub/gridManagement/gridDataManage/evalgridattr'
import EvalGridAttrForm from './EvalGridAttrForm.vue'
import EvalGridAttrDetailDrawer from './EvalGridAttrDetailDrawer.vue'
import { useMessage } from '@/hooks/web/useMessage'
import download from '@/utils/download'

const message = useMessage()
const loading = ref(true)
const exportLoading = ref(false)
const list = ref<EvalGridAttrVO[]>([])
const total = ref(0)
const queryFormRef = ref()
const formRef = ref<any>()

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  gridType: undefined,
  idxName: undefined
})

const gridTypes = ['A', 'B', 'C']
const tableRef = ref()
const selectedRows = ref<EvalGridAttrVO[]>([])

// 模拟指标数据
const indicators = [
  { id: 1, name: '指标A1', type: 'A' },
  { id: 2, name: '指标A2', type: 'A' },
  { id: 3, name: '指标B1', type: 'B' },
  { id: 4, name: '指标C1', type: 'C' }
]

const batchAssignVisible = ref(false)
const batchForm = reactive<{ gridType: string; idxId?: number }>({
  gridType: '',
  idxId: undefined
})

const filteredIndicators = computed(() => {
  const type = batchForm.gridType || ''
  return indicators.filter((i) => i.type === type)
})

// 详情抽屉
const detailDrawerVisible = ref(false)
const detailRecord = reactive<EvalGridAttrVO>({} as EvalGridAttrVO)
const openDetail = (record: EvalGridAttrVO) => {
  Object.assign(detailRecord, record)
  detailDrawerVisible.value = true
}

// 列表
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalGridAttrApi.getEvalGridAttrPage(queryParams)
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
  queryFormRef.value?.resetFields()
  handleQuery()
}

// 编辑表单，仅用于编辑
const openForm = (id: number) => {
  formRef.value.open(id)
}

const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await EvalGridAttrApi.deleteEvalGridAttr(id)
    message.success('删除成功')
    getList()
  } catch {}
}

const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await EvalGridAttrApi.exportEvalGridAttr(queryParams)
    download.excel(data, '评价网格属性.xls')
  } finally {
    exportLoading.value = false
  }
}

const handleSelectionChange = (rows: EvalGridAttrVO[]) => {
  selectedRows.value = rows
}

const openBatchAssign = () => {
  if (selectedRows.value.length === 0) {
    message.warning('请先选择至少一个网格')
    return
  }
  batchForm.gridType = selectedRows.value[0].gridType
  batchForm.idxId = undefined
  batchAssignVisible.value = true
}

const handleBatchAssign = () => {
  if (!batchForm.idxId) {
    message.warning('请选择指标')
    return
  }
  selectedRows.value.forEach((row) => {
    row.idxId = batchForm.idxId
    const indicator = indicators.find((i) => i.id === batchForm.idxId)
    row.idxName = indicator?.name || ''
  })
  message.success('批量关联成功')
  batchAssignVisible.value = false
}

onMounted(() => getList())
</script>
