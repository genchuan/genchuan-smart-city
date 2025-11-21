<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">

      <el-form-item label="关联资产分类ID" prop="relAssetCatId">
        <el-input v-model="queryParams.relAssetCatId" placeholder="请输入关联资产分类ID" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="关联资产分类名称" prop="relAssetCatName">
        <el-select v-model="queryParams.relAssetCatName" placeholder="请选择关联资产分类名称" clearable class="!w-240px">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>
      <el-form-item label="资产编码" prop="assetCode">
        <el-input v-model="queryParams.assetCode" placeholder="请输入资产编码" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input v-model="queryParams.assetName" placeholder="请输入资产名称" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select v-model="queryParams.assetStatus" placeholder="请选择资产状态" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.assetStatusOptions" :key="item.value" :label="item.label"
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
          v-hasPermi="['assetDataMng:assetDataMngList:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetDataMng:assetDataMngList:export']">
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
      <el-table-column label="资产数据ID" align="center" prop="assetDataId" />
      <el-table-column label="关联资产分类ID" align="center" prop="relAssetCatId" />
      <el-table-column label="关联资产分类名称" align="center" prop="relAssetCatName" />
      <el-table-column label="资产编码" align="center" prop="assetCode" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="资产状态" align="center" prop="assetStatus" />
      <el-table-column label="安装时间" align="center" prop="installTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="使用年限" align="center" prop="serviceLife" />
      <el-table-column label="资产负责人" align="center" prop="assetManager" />
      <el-table-column label="资产负责人电话" align="center" prop="assetManagerTel" />
      <el-table-column label="资产描述" align="center" prop="assetDesc" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column label="创建时间" align="center" prop="createdTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />

      <el-table-column label="操作" align="center" min-width="200px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)">
            <Icon icon="ep:view" class="mr-5px" /> 详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetDataMng:assetDataMngList:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetDataMng:assetDataMngList:delete']">
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

      <!-- 关联资产分类 ID （可手动输入，也可回显） -->
      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="formData.relAssetCatId" placeholder="系统自动带出或手动输入" clearable @change="handleDataIdInput" />
      </el-form-item>
      <!-- 关联资产分类名称（带双向映射） -->
      <el-form-item label="关联资产分类名称" prop="relAssetName">
        <el-select v-model="formData.relAssetCatName" placeholder="请选择关联资产名称" clearable @change="handleDataNameChange">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <el-form-item label="资产编码" prop="assetCode">
        <el-input v-model="formData.assetCode" placeholder="请输入资产编码" />
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input v-model="formData.assetName" placeholder="请输入资产名称" />
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select v-model="formData.assetStatus" placeholder="请选择资产状态">
          <el-option v-for="item in OptionsAll.assetStatusOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="安装时间" prop="installTime">
        <el-date-picker v-model="formData.installTime" type="date" value-format="x" placeholder="选择安装时间" />
      </el-form-item>
      <el-form-item label="使用年限" prop="serviceLife">
        <el-input v-model="formData.serviceLife" placeholder="请输入使用年限" />
      </el-form-item>
      <el-form-item label="资产负责人" prop="assetManager">
        <el-input v-model="formData.assetManager" placeholder="请输入资产负责人" />
      </el-form-item>
      <el-form-item label="资产负责人电话" prop="assetManagerTel">
        <el-input v-model="formData.assetManagerTel" placeholder="请输入资产负责人电话" />
      </el-form-item>
      <el-form-item label="资产描述" prop="assetDesc">
        <el-input v-model="formData.assetDesc" type="textarea" placeholder="请输入资产描述" />
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input v-model="formData.createUser" placeholder="请输入创建人" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker v-model="formData.createdTime" type="date" value-format="x" placeholder="选择创建时间" />
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

  <!-- 详情抽屉 -->
  <assetDataMngListDrawer v-model="detailVisible" :id="currentId" />

</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetDataMngApi, AssetDataMngVO } from '@/api/dataHub/assetManagement/assetDataMng/assetDataMngList'

import assetDataMngListDrawer from './components/drawer/assetDataMngListDrawer.vue'


/** 详情抽屉 */
const detailVisible = ref(false)
const currentId = ref<number>()

/** 打开详情 */
const openDetail = (id: number) => {
  currentId.value = id
  detailVisible.value = true
}

/** 资产数据管理 列表 */
defineOptions({ name: 'AssetDataMng' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetDataMngVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetDataId: undefined,
  relAssetCatId: undefined,
  relAssetCatName: undefined,
  assetCode: undefined,
  assetName: undefined,
  assetStatus: undefined,
  installTime: [],
  serviceLife: undefined,
  assetManager: undefined,
  assetManagerTel: undefined,
  assetDesc: undefined,
  createUser: undefined,
  createdTime: [],
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

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetDataMngApi.getAssetDataMngPage(queryParams)
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

// ========== 表单相关 ==========
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetDataId: undefined,
  relAssetCatId: undefined as string | undefined,
  relAssetCatName: undefined as string | undefined,
  assetCode: undefined,
  assetName: undefined,
  assetStatus: undefined,
  installTime: undefined,
  serviceLife: undefined,
  assetManager: undefined,
  assetManagerTel: undefined,
  assetDesc: undefined,
  createUser: undefined,
  createdTime: undefined,
  updateUser: undefined,
  updatedTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  assetDataId: [{ required: true, message: '资产数据ID不能为空', trigger: 'blur' }],
  relAssetCatId: [{ required: true, message: '关联资产分类ID不能为空', trigger: 'blur' }],
  relAssetCatName: [{ required: true, message: '关联资产分类名称不能为空', trigger: 'change' }],
  assetCode: [{ required: true, message: '资产编码不能为空', trigger: 'blur' }],
  assetName: [{ required: true, message: '资产名称不能为空', trigger: 'blur' }],
  assetStatus: [{ required: true, message: '资产状态不能为空', trigger: 'change' }],
  installTime: [{ required: true, message: '安装时间不能为空', trigger: 'blur' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
  createdTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

/** 所有选项集合 */
const OptionsAll = ref({

  // 关联资产分类名称
  relAssetCatNameOptions: [] as Array<{ label: string; value: string }>,
  // 资产状态
  assetStatusOptions: [
    { label: '正常', value: '1' },
    { label: '故障', value: '2' },
    { label: '待维护', value: '3' },
  ],
 
})

import { getCatMaps } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'
/* 资产分类映射 & 下拉选项 */
const dataMaps = reactive({
  name2Id: {} as Record<string, string>,
  id2Name: {} as Record<string, string>,
  options: [] as { label: string; value: string }[],
})
/* 初始化映射表（mounted 阶段调用） */
const loadDataMaps = async () => {
  const maps = await getCatMaps()
  Object.assign(dataMaps, maps)
}
/** 双向赋值函数 */
const handleDataNameChange = (name: string) => {
  formData.value.relAssetCatId = dataMaps.name2Id[name] ?? undefined
}
const handleDataIdInput = (id: string) => {
  formData.value.relAssetCatName = dataMaps.id2Name[id] ?? undefined
}

/** 添加/修改操作 */
const openForm = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetDataMngApi.getAssetDataMng(id)
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
    const data = formData.value as unknown as AssetDataMngVO
    if (formType.value === 'create') {
      await AssetDataMngApi.createAssetDataMng(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetDataMngApi.updateAssetDataMng(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 刷新列表
    getList()
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetDataId: undefined,
    relAssetCatId: undefined,
    relAssetCatName: undefined,
    assetCode: undefined,
    assetName: undefined,
    assetStatus: undefined,
    installTime: undefined,
    serviceLife: undefined,
    assetManager: undefined,
    assetManagerTel: undefined,
    assetDesc: undefined,
    createUser: undefined,
    createdTime: undefined,
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
    await AssetDataMngApi.deleteAssetDataMng(id)
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
    const data = await AssetDataMngApi.exportAssetDataMng(queryParams)
    download.excel(data, '资产数据管理.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'cfgTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'cfgTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'updatedTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'updatedTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  if (sort.prop === 'createdTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'createdTime'
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