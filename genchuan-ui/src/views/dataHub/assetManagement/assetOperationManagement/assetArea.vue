<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
      <el-form-item label="关联资产名称" prop="relAssetName">
        <el-select v-model="queryParams.relAssetName" placeholder="请选择关联资产名称" clearable class="!w-240px" filterable>
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="queryParams.relAssetId" placeholder="请输入关联资产ID" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>

      <el-form-item label="行政区划级别" prop="regionLevel">
        <el-select v-model="queryParams.regionLevel" placeholder="请选择行政区划级别" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.regionLevelOptions" :key="item.value" :label="item.label"
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
          v-hasPermi="['assetOperationManagement:assetArea:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetOperationManagement:assetArea:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
      @sort-change="handleSortChange">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="关联ID" align="center" prop="assetRelRegionId" />
      <el-table-column label="关联资产ID" align="center" prop="relAssetId" />
      <el-table-column label="关联资产名称" align="center" prop="relAssetName" />
      <el-table-column label="行政区划代码" align="center" prop="regionCode" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" />
      <el-table-column label="行政区划级别" align="center" prop="regionLevel" />
      <el-table-column label="关联时间" align="center" prop="relTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="操作人" align="center" prop="operUser" />
      <el-table-column label="关联说明" align="center" prop="relDesc" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />

      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
            <Icon icon="ep:view" class="mr-2px" /> 详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetOperationManagement:assetArea:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetOperationManagement:assetArea:delete']">
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

      <!-- 关联资产名称（带双向映射） -->
      <el-form-item label="关联资产名称" prop="relAssetName">
        <el-select v-model="formData.relAssetName" placeholder="请选择关联资产名称" clearable @change="onDataNameChange">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <!-- 关联资产 ID （可手动输入，也可回显） -->
      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="formData.relAssetId" placeholder="系统自动带出或手动输入" clearable @change="onDataIdInput" />
      </el-form-item>

      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="行政区划级别" prop="regionLevel">
        <el-select v-model="formData.regionLevel" placeholder="请选择行政区划级别">
          <el-option v-for="item in OptionsAll.regionLevelOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联时间" prop="relTime">
        <el-date-picker v-model="formData.relTime" type="date" value-format="x" placeholder="选择关联时间" />
      </el-form-item>
      <el-form-item label="操作人" prop="operUser">
        <el-input v-model="formData.operUser" placeholder="请输入操作人" />
      </el-form-item>
      <el-form-item label="关联说明" prop="relDesc">
        <el-input v-model="formData.relDesc" placeholder="请输入关联说明" />
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

  <!-- 抽屉组件 -->
    <assetAreaDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetAreaApi, AssetAreaVO } from '@/api/dataHub/assetManagement/assetOperationManagement/assetArea'

import assetAreaDrawer from './components/drawer/assetAreaDrawer.vue'

/** 资产关联行政区划 列表 */
defineOptions({ name: 'AssetArea' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetAreaVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 15,
  assetRelRegionId: undefined,
  relAssetId: undefined,
  relAssetName: undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  relTime: [],
  operUser: undefined,
  relDesc: undefined,
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

// 表单相关变量
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetRelRegionId: undefined,
  relAssetId: undefined as string | undefined,
  relAssetName: undefined as string | undefined,
  regionCode: undefined,
  regionName: undefined,
  regionLevel: undefined,
  relTime: undefined,
  operUser: undefined,
  relDesc: undefined,
  updateUser: undefined,
  updatedTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  assetRelRegionId: [{ required: true, message: '关联ID不能为空', trigger: 'blur' }],
  relAssetId: [{ required: true, message: '关联资产ID不能为空', trigger: 'blur' }],
  relAssetName: [{ required: true, message: '关联资产名称不能为空', trigger: 'change' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  regionLevel: [{ required: true, message: '行政区划级别不能为空', trigger: 'change' }],
  relTime: [{ required: true, message: '关联时间不能为空', trigger: 'blur' }],
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

// 所有选项集合
const OptionsAll = ref({

  // 关联资产名称
  relAssetNameOptions: [] as Array<{ label: string; value: string }>,
  // 行政区划级别
  regionLevelOptions: [
    { label: '省级', value: '省级' },
    { label: '市级', value: '市级' },
    { label: '县级', value: '县级' },
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
    const data = await AssetAreaApi.getAssetAreaPage(queryParams)
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
      formData.value = await AssetAreaApi.getAssetArea(id)
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
    const data = formData.value as unknown as AssetAreaVO
    if (formType.value === 'create') {
      await AssetAreaApi.createAssetArea(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetAreaApi.updateAssetArea(data)
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
    assetRelRegionId: undefined,
    relAssetId: undefined,
    relAssetName: undefined,
    regionCode: undefined,
    regionName: undefined,
    regionLevel: undefined,
    relTime: undefined,
    operUser: undefined,
    relDesc: undefined,
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
    await AssetAreaApi.deleteAssetArea(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch { }
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await AssetAreaApi.exportAssetArea(queryParams)
    download.excel(data, '资产关联行政区划.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}
/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'relTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'relTime'
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