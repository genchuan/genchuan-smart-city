<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
      <el-form-item label="网格类型" prop="gridType">
        <el-select v-model="queryParams.gridType" placeholder="全部" clearable class="!w-240px">
          <el-option v-for="item in gridTypes" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>

      <el-form-item label="数据格式" prop="dataFormat">
        <el-select v-model="queryParams.dataFormat" placeholder="全部" clearable class="!w-240px">
          <el-option label="GeoJSON" value="GeoJSON" />
          <el-option label="SHP" value="SHP" />
          <el-option label="文本" value="文本" />
        </el-select>
      </el-form-item>

      <el-form-item label="关键字" prop="keyword">
        <el-input
          v-model="queryParams.keyword"
          placeholder="空间ID/关联网格ID/备注"
          clearable
          class="!w-360px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>

      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker
          v-model="queryParams.updateTime"
          type="daterange"
          value-format="YYYY-MM-DD HH:mm:ss"
          class="!w-220px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>

        <el-button
          type="primary"
          plain
          @click="openForm('create')"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>

        <el-button
          type="info"
          plain
          @click="openImport"
        >
          <Icon icon="ep:upload" class="mr-5px" /> 导入
        </el-button>

        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
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
      @sort-change="onSortChange"
    >
      <el-table-column prop="id" label="主键ID" align="center" width="100" />
      <el-table-column prop="spatialId" label="空间数据ID" align="center" />
      <el-table-column prop="gridId" label="关联网格ID" align="center" />
      <el-table-column prop="gridType" label="网格类型" align="center" />
      <el-table-column prop="boundaryCoords" label="边界坐标" align="center" width="260">
        <template #default="{ row }">
          <el-tooltip effect="dark" :content="shortBoundary(row.boundaryCoords)" placement="top">
            <span>{{ shortBoundary(row.boundaryCoords) }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="topoRelation" label="拓扑关系" align="center" />
      <el-table-column prop="coordSystem" label="坐标系" align="center" />
      <el-table-column prop="dataFormat" label="数据格式" align="center" />
      <el-table-column prop="remark" label="备注" align="center" />
      <el-table-column
        prop="updateTime"
        label="更新时间"
        align="center"
        width="180"
        :formatter="dateFormatter"
        sortable="custom"
      />
      <el-table-column label="操作" align="center" min-width="200">
        <template #default="scope">
          <el-button link @click="openDetail(scope.row)" type="primary">详情</el-button>
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

  <!-- 弹窗/抽屉/组件 -->
  <GridSpatialDataForm ref="formRef" @success="getList" />
  <GridSpatialDataDetailDrawer ref="detailRef" />

  <!-- 导入文件对话框 -->
  <el-dialog title="导入空间文件" v-model="importVisible" width="600px">
    <div>
      <el-upload
        ref="uploadRef"
        action=""
        :auto-upload="false"
        :before-upload="beforeUpload"
        :on-change="handleFileChange"
        :file-list="fileList"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip">支持 GeoJSON (.geojson/.json) 、SHP (.zip 打包) 文件</div>
      </el-upload>
    </div>
    <template #footer>
      <el-button @click="importVisible = false">取消</el-button>
      <el-button type="primary" @click="doImport" :loading="importLoading">开始导入</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
/**
 * GridSpatialDataTable.vue（修复 el-date-picker 类型相关报错）
 * 说明：把 updateTime 的类型严格化为 string[]（或 undefined），不包含 null，避免与 ElementPlus 的 Prop 类型冲突
 */

import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import GridSpatialDataForm from './GridSpatialDataForm.vue'
import GridSpatialDataDetailDrawer from './GridSpatialDataDetailDrawer.vue'
import GridSpatialDataApi, {
  GridSpatialDataVO,
  GridSpatialDataPageQuery as _GridSpatialDataPageQuery,
} from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata'
import {
  mockGridSpatialDataList,
  mockGridTypes,
} from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata/mockGridSpatialData'

const message = useMessage()
const { t } = useI18n()

// 扩展父接口类型，注意 updateTime 的类型为 string[] （不包含 null）
interface GridSpatialDataPageQuery extends _GridSpatialDataPageQuery {
  keyword?: string
  updateTime?: string[] // <-- 关键修复：不允许 null 联合
}

const loading = ref(false)
const list = ref<GridSpatialDataVO[]>([])
const total = ref(0)
const gridTypes = ref<string[]>([])

const queryParams = reactive<GridSpatialDataPageQuery>({
  pageNo: 1,
  pageSize: 10,
  sortField: '',
  sortOrder: '',
  spatialId: undefined,
  gridId: undefined,
  gridType: undefined,
  dataFormat: undefined,
  keyword: '',
  updateTime: [], // 初始化为空数组，符合 el-date-picker 的 daterange v-model 类型
})

const queryFormRef = ref()
const exportLoading = ref(false)
const formRef = ref()
const detailRef = ref()

/** 导入相关 */
const importVisible = ref(false)
const importLoading = ref(false)
const uploadRef = ref()
const fileList = ref<any[]>([])

onMounted(async () => {
  await initGridTypes()
  await getList()
})

/** 获取网格类型 */
const initGridTypes = async () => {
  // try {
  //   const res = await GridSpatialDataApi.getGridTypes()
  //   gridTypes.value = res && Array.isArray(res) ? res : mockGridTypes
  // } catch {
    gridTypes.value = mockGridTypes
  // }
}

/** 查询列表（含排序） */
const getList = async () => {
  loading.value = true
  try {
    const params = { ...queryParams }
    const data: any = await GridSpatialDataApi.getGridSpatialDataPage(params)
    if (data && Array.isArray(data.list)) {
      list.value = data.list
      total.value = data.total ?? data.list.length
    } else if (Array.isArray(data)) {
      list.value = data
      total.value = data.length
    } else {
      list.value = mockGridSpatialDataList
      total.value = mockGridSpatialDataList.length
    }
  } catch {
    list.value = mockGridSpatialDataList
    total.value = mockGridSpatialDataList.length
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

const resetQuery = () => {
  queryFormRef.value?.resetFields?.()
  Object.assign(queryParams, {
    pageNo: 1,
    pageSize: 15,
    sortField: '',
    sortOrder: '',
    spatialId: undefined,
    gridId: undefined,
    gridType: undefined,
    dataFormat: undefined,
    keyword: '',
    updateTime: [],
  })
  getList()
}

/** 打开表单 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 打开详情抽屉 */
const openDetail = async (row: GridSpatialDataVO) => {
  detailRef.value?.open(row)
}

/** 删除 */
const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await GridSpatialDataApi.deleteGridSpatialData(id)
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

/** 导出 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await GridSpatialDataApi.exportGridSpatialData(queryParams)
    download.excel(data, '网格空间数据导出.xls')
  } catch {
    // ignore
  } finally {
    exportLoading.value = false
  }
}

/** 排序变更回调 */
const onSortChange = (sort: any) => {
  if (sort && sort.prop) {
    queryParams.sortField = sort.prop
    queryParams.sortOrder = sort.order === 'ascending' ? 'asc' : 'desc'
  } else {
    queryParams.sortField = ''
    queryParams.sortOrder = ''
  }
  getList()
}

/** 边界简短展示 */
const shortBoundary = (b: string | undefined) => {
  if (!b) return ''
  return b.length > 40 ? b.slice(0, 40) + '...' : b
}

/** 导入流程 */
const openImport = () => {
  importVisible.value = true
}
const beforeUpload = () => false
const handleFileChange = (_file: any, fileListArg: any[]) => {
  fileList.value = fileListArg
}
const doImport = async () => {
  if (!fileList.value.length) {
    message.warning('请先选择文件')
    return
  }
  try {
    importLoading.value = true
    const form = new FormData()
    fileList.value.forEach((f: any) => form.append('file', f.raw ?? f))
    try {
      await GridSpatialDataApi.importSpatialFile(form)
      message.success('导入成功（若后端实现）')
    } catch {
      message.info('后端导入接口不可用，使用本地预览（mock）')
    }
    importVisible.value = false
    await getList()
  } finally {
    importLoading.value = false
  }
}
</script>
