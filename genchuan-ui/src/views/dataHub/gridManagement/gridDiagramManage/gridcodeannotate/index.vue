<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
    >
      <el-form-item label="注记ID" prop="annotateId">
        <el-input
          v-model="queryParams.annotateId"
          placeholder="请输入注记ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="比例尺" prop="scale">
        <el-select v-model="queryParams.scale" clearable placeholder="全部" class="!w-240px">
          <el-option label="1:5000" value="1:5000" />
          <el-option label="1:1000" value="1:1000" />
          <el-option label="1:2000" value="1:2000" />
          <el-option label="自定义" value="custom" />
        </el-select>
      </el-form-item>

      <el-form-item label="网格类型" prop="gridType">
        <el-select v-model="queryParams.gridType" clearable placeholder="全部" class="!w-240px">
          <el-option label="单元网格" value="UNIT_GRID" />
          <el-option label="管理网格" value="MANAGE_GRID" />
          <el-option label="评价网格" value="EVAL_GRID" />
        </el-select>
      </el-form-item>

      <el-form-item label="字高(mm)" prop="fontHeight">
        <el-input
          v-model="queryParams.fontHeight"
          placeholder="请输入字高(mm)"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" stripe :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="注记ID" align="center" prop="annotateId" />
      <el-table-column label="比例尺" align="center" prop="scale" />
      <el-table-column label="网格类型" align="center" prop="gridType" />
      <el-table-column label="字高(mm)" align="center" prop="fontHeight" />
      <el-table-column label="字型" align="center" prop="fontType" />
      <el-table-column label="颜色(CMYK)" align="center" :formatter="formatCMYK" />
      <el-table-column label="创建人" align="center" prop="createUserId" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />

      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="{ row }">
          <el-button link type="primary" @click="openForm('update', row.id)">编辑</el-button>
          <el-button link type="success" @click="openDetailDrawer(row)">预览注记</el-button>
          <!--          <el-button link type="success" @click="previewAnnotate(row)">预览注记</el-button>-->
          <el-button link type="danger" @click="handleDelete(row.id)">删除</el-button>
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
  <GridCodeAnnotateForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <GridCodeAnnotateDetailDrawer ref="detailDrawerRef" @restart-gis="onRestartGis" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, nextTick } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { ElMessage } from 'element-plus'
import {
  GridCodeAnnotateApi,
  GridCodeAnnotateVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridcodeannotate'
import GridCodeAnnotateForm from './GridCodeAnnotateForm.vue'
import GridCodeAnnotateDetailDrawer from './GridCodeAnnotateDetailDrawer.vue'

const message = useMessage()
const loading = ref(true)
const list = ref<GridCodeAnnotateVO[]>([])
const total = ref(0)
const exportLoading = ref(false)

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  annotateId: '',
  scale: '',
  gridType: '',
  fontHeight: '',
  fontType: '',
  colorC: undefined,
  colorM: undefined,
  colorY: undefined,
  colorK: undefined,
  createUserId: '',
  remark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: '',
  createTime: []
})

const queryFormRef = ref()

// ✅ 显式声明子组件暴露方法类型
type GridFormExpose = { open: (type: string, id?: number) => void }
type GridDrawerExpose = { open: (row: GridCodeAnnotateVO) => void }

const formRef = ref<GridFormExpose | null>(null)
const detailDrawerRef = ref<GridDrawerExpose | null>(null)

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await GridCodeAnnotateApi.getGridCodeAnnotatePage(queryParams)
    list.value = (data.list || []).slice()
    // 按比例尺数字排序（1:500 -> 500）
    list.value.sort((a, b) => parseScaleNumber(a.scale) - parseScaleNumber(b.scale))
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

const openForm = async (type: string, id?: number) => {
  await nextTick()
  formRef.value?.open(type, id)
}

const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    await GridCodeAnnotateApi.deleteGridCodeAnnotate(id)
    ElMessage.success('删除成功')
    await getList()
  } catch {}
}

const handleExport = async () => {
  exportLoading.value = true
  try {
    const data = await GridCodeAnnotateApi.exportGridCodeAnnotate(queryParams)
    download.excel(data, '网格标识码注记配置.xls')
  } finally {
    exportLoading.value = false
  }
}

const openDetailDrawer = async (row: GridCodeAnnotateVO) => {
  console.log('打开详情抽屉', row)
  await nextTick()
  detailDrawerRef.value?.open(row)
}

// const previewAnnotate = async (row: GridCodeAnnotateVO) => {
//   await nextTick()
//   detailDrawerRef.value?.open(row)
// }

const onRestartGis = () => {
  ElMessage.info('GIS 重启请求已发送（演示）')
}

const formatCMYK = (row: any) => `C${row.colorC}% M${row.colorM}% Y${row.colorY}% K${row.colorK}%`

const parseScaleNumber = (scale?: string) => {
  if (!scale) return 0
  const m = scale.match(/(\d+)/)
  return m ? Number(m[0]) : 0
}

onMounted(() => getList())
</script>
