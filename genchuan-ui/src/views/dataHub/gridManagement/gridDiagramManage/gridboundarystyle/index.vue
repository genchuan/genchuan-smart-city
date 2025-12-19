<template>
  <ContentWrap>
    <!-- 搜索表单 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="样式ID" prop="styleId">
        <el-input
          v-model="queryParams.styleId"
          placeholder="请输入样式ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="比例尺" prop="scale">
        <el-input
          v-model="queryParams.scale"
          placeholder="请输入比例尺"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="网格类型" prop="gridType">
        <el-input
          v-model="queryParams.gridType"
          placeholder="请输入网格类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
        <el-button type="primary" plain @click="openForm('create')">新增</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="样式ID" align="center" prop="styleId" />
      <el-table-column label="比例尺" align="center" prop="scale" />
      <el-table-column label="网格类型" align="center" prop="gridType" />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row)">详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 表单弹窗 -->
  <GridBoundaryStyleForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <GridBoundaryStyleDetailDrawer ref="detailDrawerRef" @restart-gis="onRestartGis" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, nextTick } from 'vue'
import {
  GridBoundaryStyleApi,
  GridBoundaryStyleVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridboundarystyle'
import GridBoundaryStyleForm from './GridBoundaryStyleForm.vue'
import GridBoundaryStyleDetailDrawer from './GridBoundaryStyleDetailDrawer.vue'
import download from '@/utils/download'

const loading = ref(true)
const list = ref<GridBoundaryStyleVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  styleId: '',
  scale: '',
  gridType: ''
})

const queryFormRef = ref()
const formRef = ref<InstanceType<typeof GridBoundaryStyleForm> | null>(null)
const detailDrawerRef = ref<InstanceType<typeof GridBoundaryStyleDetailDrawer> | null>(null)
const exportLoading = ref(false)

// 获取列表
const getList = async () => {
  loading.value = true
  try {
    const data = await GridBoundaryStyleApi.getGridBoundaryStylePage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

// 查询 / 重置
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  handleQuery()
}

// 打开表单
const openForm = (type: 'create' | 'update', id?: number) => {
  formRef.value?.open(type, id)
}

// 删除
const handleDelete = async (id: number) => {
  try {
    await GridBoundaryStyleApi.deleteGridBoundaryStyle(id)
    ElMessage.success('删除成功')
    getList()
  } catch {}
}

// 打开详情抽屉
const openDetailDrawer = async (row: GridBoundaryStyleVO) => {
  await nextTick()
  detailDrawerRef.value?.open(row)
}

// 导出
const handleExport = async () => {
  exportLoading.value = true
  try {
    const data = await GridBoundaryStyleApi.exportGridBoundaryStyle(queryParams)
    download.excel(data, '网格边界线样式配置.xls')
  } finally {
    exportLoading.value = false
  }
}

// GIS 重启事件
const onRestartGis = (row: any) => {
  ElMessage.info(`GIS 重启事件触发: ${row.id}`)
}

// 初始化
onMounted(() => {
  getList()
})
</script>
