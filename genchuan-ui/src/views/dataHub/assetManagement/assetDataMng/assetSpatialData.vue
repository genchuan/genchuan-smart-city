<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">

      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="queryParams.relAssetId" placeholder="请输入关联资产ID" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="关联资产名称" prop="relAssetName">
        <el-select v-model="queryParams.relAssetName" placeholder="请选择关联资产名称" clearable class="!w-240px">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <el-form-item label="空间数据来源" prop="spatialDataSource">
        <el-select v-model="queryParams.spatialDataSource" placeholder="请选择空间数据来源" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.spatialDataSourceOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" /> 搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" /> 重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')"
          v-hasPermi="['assetDataMng:assetSpatialData:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetDataMng:assetSpatialData:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>

        <!-- 导入按钮 -->
        <el-button type="warning" plain @click="handleImport" v-hasPermi="['assetDataMng:assetSpatialData:import']">
          <Icon icon="ep:upload" class="mr-5px" /> 导入
        </el-button>

      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true" @sort-change="handleSortChange">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="空间数据ID" align="center" prop="assetSpatialId" />
      <el-table-column label="关联资产ID" align="center" prop="relAssetId" />
      <el-table-column label="关联资产名称" align="center" prop="relAssetName" />
      <el-table-column label="坐标系类型" align="center" prop="coordSystemType" />
      <el-table-column label="坐标X" align="center" prop="coordX" />
      <el-table-column label="坐标Y" align="center" prop="coordY" />
      <el-table-column label="高程" align="center" prop="elevation" />
      <el-table-column label="边界坐标" align="center" prop="boundaryCoords" />
      <el-table-column label="空间数据来源" align="center" prop="spatialDataSource" />
      <el-table-column label="录入时间" align="center" prop="inputTime" :formatter="dateFormatter" width="180px"
      sortable="custom" :sort-orders="['ascending', 'descending']"  />
      <el-table-column label="操作人" align="center" prop="operUser" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px"
      sortable="custom" :sort-orders="['ascending', 'descending']"  />

      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
            <Icon icon="ep:view" class="mr-2px" /> 详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetDataMng:assetSpatialData:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetDataMng:assetSpatialData:delete']">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination :total="total" v-model:page="queryParams.pageNo" v-model:limit="queryParams.pageSize"
      @pagination="getList" />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px" v-loading="formLoading">

      <!-- 关联资产 ID （可手动输入，也可回显） -->
      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="formData.relAssetId" placeholder="系统自动带出或手动输入" clearable @change="onDataIdInput" />
      </el-form-item>
      <!-- 关联资产名称（带双向映射） -->
      <el-form-item label="关联资产名称" prop="relAssetName">
        <el-select v-model="formData.relAssetName" placeholder="请选择关联资产名称" clearable @change="onDataNameChange">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <el-form-item label="坐标系类型" prop="coordSystemType">
        <el-select v-model="formData.coordSystemType" placeholder="请选择坐标系类型">
          <el-option v-for="item in OptionsAll.coordSystemTypeOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="坐标X" prop="coordX">
        <el-input v-model="formData.coordX" placeholder="请输入坐标X" />
      </el-form-item>
      <el-form-item label="坐标Y" prop="coordY">
        <el-input v-model="formData.coordY" placeholder="请输入坐标Y" />
      </el-form-item>
      <el-form-item label="高程" prop="elevation">
        <el-input v-model="formData.elevation" placeholder="请输入高程" />
      </el-form-item>
      <el-form-item label="边界坐标" prop="boundaryCoords">
        <el-input v-model="formData.boundaryCoords" placeholder="请输入边界坐标" />
      </el-form-item>
      <el-form-item label="空间数据来源" prop="spatialDataSource">
        <el-select v-model="formData.spatialDataSource" placeholder="请选择空间数据来源">
          <el-option v-for="item in OptionsAll.spatialDataSourceOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="录入时间" prop="inputTime">
        <el-date-picker v-model="formData.inputTime" type="date" value-format="x" placeholder="选择录入时间" />
      </el-form-item>
      <el-form-item label="操作人" prop="operUser">
        <el-input v-model="formData.operUser" placeholder="请输入操作人" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker v-model="formData.updatedTime" type="date" value-format="x" placeholder="选择更新时间" />
      </el-form-item>
     
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>

  <!-- 导入组件 -->
  <AssetSpatialDataImport ref="importRef" @success="handleImportSuccess" />
  <!-- 抽屉组件 -->
  <assetSpatialDataDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />

</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetSpatialDataApi, AssetSpatialDataVO } from '@/api/dataHub/assetManagement/assetDataMng/assetSpatialData'
import AssetSpatialDataImport from './components/import/assetSpatialDataImport.vue'
import assetSpatialDataDrawer from './components/drawer/assetSpatialDataDrawer.vue'


/** 资产空间数据 列表 */
defineOptions({ name: 'AssetSpatialData' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

// 列表相关
const loading = ref(true) // 列表的加载中
const list = ref<AssetSpatialDataVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 15,
  assetSpatialId: undefined,
  relAssetId: undefined,
  relAssetName: undefined,
  coordSystemType: undefined,
  coordX: undefined,
  coordY: undefined,
  elevation: undefined,
  boundaryCoords: undefined,
  spatialDataSource: undefined,
  inputTime: [],
  operUser: undefined,
  updateUser: undefined,
  updatedTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: [],
   // 排序字段
  orderByColumn: undefined as undefined | string,
  isAsc: undefined as undefined | 'asc' | 'desc',
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 表单相关
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetSpatialId: undefined,
  relAssetId: undefined as string | undefined,
  relAssetName: undefined as string | undefined,
  coordSystemType: undefined,
  coordX: undefined,
  coordY: undefined,
  elevation: undefined,
  boundaryCoords: undefined,
  spatialDataSource: undefined,
  inputTime: undefined,
  operUser: undefined,
  updateUser: undefined,
  updatedTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  // 排序字段
  orderByColumn: undefined as undefined | string,
  isAsc: undefined as undefined | 'asc' | 'desc',
})
const formRules = reactive({
  assetSpatialId: [{ required: true, message: '空间数据ID不能为空', trigger: 'blur' }],
  relAssetId: [{ required: true, message: '关联资产ID不能为空', trigger: 'blur' }],
  relAssetName: [{ required: true, message: '关联资产名称不能为空', trigger: 'change' }],
  coordSystemType: [{ required: true, message: '坐标系类型不能为空', trigger: 'change' }],
  coordX: [{ required: true, message: '坐标X不能为空', trigger: 'blur' }],
  coordY: [{ required: true, message: '坐标Y不能为空', trigger: 'blur' }],
  spatialDataSource: [{ required: true, message: '空间数据来源不能为空', trigger: 'change' }],
  inputTime: [{ required: true, message: '录入时间不能为空', trigger: 'blur' }],
  operUser: [{ required: true, message: '操作人不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

// 抽屉
const detailDrawerVisible = ref(false)
const selectedDetailId = ref<number>()
// 打开详情抽屉方法
const openDetailDrawer = (id: number) => {
  selectedDetailId.value = id
  detailDrawerVisible.value = true
}

/** 所有选项集合 */
const OptionsAll = ref({

  //关联资产名称
  relAssetNameOptions: [] as Array<{label: string, value: string}>,
  //数据来源
  spatialDataSourceOptions: [
   {label: '地图拾取', value: "map_pick"},
   {label: '手动导入', value: "manual"},
   {label: '文件导入', value: "import"},
  ],
  //坐标系类型
  coordSystemTypeOptions: [
   {label: 'WGS84', value: "WGS84"},
   {label: 'GCJ02', value: "GCJ02"}
  ],
 
})

import { getDataMaps } from '@/api/dataHub/assetManagement/assetDataMng/assetDataMngList'
/* 资产分类映射 & 下拉选项 */
const dataMaps = reactive({
    name2Id: {} as Record<string, string>,
    id2Name: {} as Record<string, string>,
    options: [] as { label: string; value: string }[],
})

/* 初始化映射表（mounted 阶段调用） */
const loadDataMaps = async () => {
    const maps = await getDataMaps()
    Object.assign(dataMaps, maps)
}

/* 双向赋值函数 */
const onDataNameChange = (name: string) => {
    formData.value.relAssetId = dataMaps.name2Id[name] ?? undefined
}
const onDataIdInput = (id: string) => {
    formData.value.relAssetName = dataMaps.id2Name[id] ?? undefined
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetSpatialDataApi.getAssetSpatialDataPage(queryParams)
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

/** 打开弹窗 */
const openForm = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetSpatialDataApi.getAssetSpatialData(id)
    } finally {
      formLoading.value = false
    }
  }
}

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as AssetSpatialDataVO
    if (formType.value === 'create') {
      await AssetSpatialDataApi.createAssetSpatialData(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetSpatialDataApi.updateAssetSpatialData(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 刷新列表
    await getList()
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetSpatialId: undefined,
    relAssetId: undefined,
    relAssetName: undefined,
    coordSystemType: undefined,
    coordX: undefined,
    coordY: undefined,
    elevation: undefined,
    boundaryCoords: undefined,
    spatialDataSource: undefined,
    inputTime: undefined,
    operUser: undefined,
    updateUser: undefined,
    updatedTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  formRef.value?.resetFields()
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await AssetSpatialDataApi.deleteAssetSpatialData(id)
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
    const data = await AssetSpatialDataApi.exportAssetSpatialData(queryParams)
    download.excel(data, '资产空间数据.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

// 导入组件 Ref
const importRef = ref()
/** 导入按钮操作 */
const handleImport = () => {
  importRef.value?.openDialog()
}

/** 导入成功处理 */
const handleImportSuccess = () => {
  // 刷新列表
  getList()
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'inputTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'inputTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  if (sort.prop === 'updatedTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'updatedTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  
  // 重新加载数据
  handleQuery()
}

/** 初始化 **/
onMounted(() => {
  loadDataMaps()
  getList()
})
</script>