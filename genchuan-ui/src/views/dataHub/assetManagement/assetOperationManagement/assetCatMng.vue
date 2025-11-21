<template>
  <div class="asset-cat-mng-container">
    <!-- 左侧树形组件 -->
    <div class="tree-sidebar">
      <AssetCatMngTree ref="treeRef" />
    </div>

    <!-- 右侧内容区域 -->
    <div class="content-main">
      <!-- 搜索工作栏 -->
      <ContentWrap>
        <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="资产分类编码" prop="assetCatCode">
            <el-input v-model="queryParams.assetCatCode" placeholder="请输入资产分类编码" clearable @keyup.enter="handleQuery"
              class="!w-240px" />
          </el-form-item>
          <el-form-item label="资产分类名称" prop="assetCatName">
            <el-input v-model="queryParams.assetCatName" placeholder="请输入资产分类名称" clearable @keyup.enter="handleQuery"
              class="!w-240px" />
          </el-form-item>
          <el-form-item label="分类层级" prop="catLevel">
            <el-select v-model="queryParams.catLevel" placeholder="请选择分类层级" clearable class="!w-240px">
              <el-option v-for="item in OptionsAll.catLevelOptions" :key="item.value" :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>

          <el-form-item label="启用状态" prop="enableStatus">
            <el-select v-model="queryParams.enableStatus" placeholder="请选择启用状态" clearable class="!w-240px">
              <el-option v-for="item in OptionsAll.enableStatusOptions" :key="item.value" :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="创建人" prop="createUser">
            <el-input v-model="queryParams.createUser" placeholder="请输入创建人" clearable @keyup.enter="handleQuery"
              class="!w-240px" />
          </el-form-item>

          <el-form-item>
            <el-button @click="handleQuery">
              <Icon icon="ep:search" class="mr-5px" /> 搜索
            </el-button>
            <el-button @click="resetQuery">
              <Icon icon="ep:refresh" class="mr-5px" /> 重置
            </el-button>
            <el-button type="primary" plain @click="openForm('create')"
              v-hasPermi="['assetOperationManagement:assetCatMng:create']">
              <Icon icon="ep:plus" class="mr-5px" /> 新增
            </el-button>
            <el-button type="success" plain @click="handleExport" :loading="exportLoading"
              v-hasPermi="['assetOperationManagement:assetCatMng:export']">
              <Icon icon="ep:download" class="mr-5px" /> 导出
            </el-button>

            <!-- 添加导入按钮 -->
            <el-button type="warning" plain @click="handleImport"
              v-hasPermi="['assetOperationManagement:assetCatMng:import']">
              <Icon icon="ep:upload" class="mr-5px" /> 导入
            </el-button>
          </el-form-item>
        </el-form>
      </ContentWrap>

      <!-- 列表 -->
      <ContentWrap>
        <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
          @sort-change="handleSortChange">
          <el-table-column label="主键ID" align="center" prop="id" />
          <el-table-column label="资产分类ID" align="center" prop="assetCatId" />
          <el-table-column label="关联分类规则ID" align="center" prop="relCatRuleId" />
          <el-table-column label="资产分类编码" align="center" prop="assetCatCode" />
          <el-table-column label="资产分类名称" align="center" prop="assetCatName" />
          <el-table-column label="分类层级" align="center" prop="catLevel" />
          <el-table-column label="上级分类ID" align="center" prop="parentCatId" />
          <el-table-column label="上级分类名称" align="center" prop="parentCatName" />
          <el-table-column label="分类说明" align="center" prop="catDesc" />
          <el-table-column label="启用状态" align="center" prop="enableStatus" />
          <el-table-column label="创建人" align="center" prop="createUser" />
          <el-table-column label="创建时间" align="center" prop="createdTime" :formatter="dateFormatter" width="180px"
            sortable="custom" :sort-orders="['ascending', 'descending']" />
          <el-table-column label="更新人" align="center" prop="updateUser" />
          <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px"
            sortable="custom" :sort-orders="['ascending', 'descending']" />

          <el-table-column label="操作" align="center" min-width="220px">
            <template #default="scope">
              <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
                <Icon icon="ep:view" class="mr-2px" /> 详情
              </el-button>
              <el-button link type="primary" @click="openForm('update', scope.row.id)"
                v-hasPermi="['assetOperationManagement:assetCatMng:update']">
                编辑
              </el-button>
              <el-button link type="danger" @click="handleDelete(scope.row.id)"
                v-hasPermi="['assetOperationManagement:assetCatMng:delete']">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <Pagination :total="total" v-model:page="queryParams.pageNo" v-model:limit="queryParams.pageSize"
          @pagination="getList" />
      </ContentWrap>
    </div>
  </div>

  <!-- 表单弹窗：添加/修改 -->
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px" v-loading="formLoading">

      <el-form-item label="关联分类规则ID" prop="relCatRuleId">
        <el-input v-model="formData.relCatRuleId" placeholder="请输入关联分类规则ID" />
      </el-form-item>
      <el-form-item label="资产分类编码" prop="assetCatCode">
        <el-input v-model="formData.assetCatCode" placeholder="请输入资产分类编码" :disabled="formType === 'update'" />
      </el-form-item>
      <el-form-item label="资产分类名称" prop="assetCatName">
        <el-input v-model="formData.assetCatName" placeholder="请输入资产分类名称" />
      </el-form-item>

      <el-form-item label="分类层级" prop="catLevel">
        <el-select v-model="formData.catLevel" placeholder="请选择分类层级" :disabled="formType === 'update'"
          @change="onCatLevelChange">
          <el-option v-for="item in OptionsAll.catLevelOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>

      <!-- 上级分类ID -->
      <el-form-item label="上级分类ID" prop="relAssetId">
        <el-input v-model="formData.parentCatId" placeholder="系统自动带出" clearable @change="handleDataIdInput"
          :disabled="formData.catLevel == '1' || formType === 'update'" />
      </el-form-item>

      <!-- 上级资产分类名称 -->
      <el-form-item label="上级资产分类名称" prop="relAssetName">
        <el-select v-model="formData.parentCatName" placeholder="请选择上级分类名称" clearable @change="handleDataNameChange"
          :disabled="formData.catLevel == '1' || formType === 'update'">
          <el-option v-for="op in dataMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <el-form-item label="分类说明" prop="catDesc">
        <el-input v-model="formData.catDesc" placeholder="请输入分类说明" />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select v-model="formData.enableStatus" placeholder="请选择启用状态">
          <el-option v-for="item in OptionsAll.enableStatusOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
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

  <!-- 导入组件 -->
  <AssetCatMngImport ref="importRef" @success="handleImportSuccess" />
  <!-- 抽屉组件 -->
  <assetCatMngDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetCatMngApi, AssetCatMngVO,getCatMaps,buildCatMaps } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'

import AssetCatMngImport from './components/import/assetCatMngImport.vue'
import AssetCatMngTree from './components/tree/assetCatMngTree.vue'
import assetCatMngDrawer from './components/drawer/assetCatMngDrawer.vue'

/** 资产分类管理 列表 */
defineOptions({ name: 'AssetCatMng' })

// 在组件中定义 treeRef
const treeRef = ref()
// 在适当的地方（如表单提交成功后）刷新树形数据
const refreshTreeData = () => {
  treeRef.value?.refresh?.()
}

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetCatMngVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetCatId: undefined,
  relCatRuleId: undefined as string | undefined,
  assetCatCode: undefined,
  assetCatName: undefined,
  catLevel: undefined,
  parentCatId: undefined,
  parentCatName: undefined,
  catDesc: undefined,
  enableStatus: undefined,
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

// 表单相关
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetCatId: undefined,
  relCatRuleId: undefined,
  assetCatCode: undefined,
  assetCatName: undefined,
  catLevel: undefined,
  parentCatId: undefined as string | undefined,
  parentCatName: undefined as string | undefined,
  catDesc: undefined,
  enableStatus: undefined,
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
  assetCatId: [{ required: true, message: '资产分类ID不能为空', trigger: 'blur' }],
  relCatRuleId: [{ required: true, message: '关联分类规则ID不能为空', trigger: 'blur' }],
  assetCatCode: [{ required: true, message: '资产分类编码不能为空', trigger: 'blur' }],
  assetCatName: [{ required: true, message: '资产分类名称不能为空', trigger: 'blur' }],
  catLevel: [{ required: true, message: '分类层级不能为空', trigger: 'change' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
  createdTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }],
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
  // 启用状态
  enableStatusOptions: [
    { label: '启用', value: 1 },
    { label: '禁用', value: 0 }
  ],
  // 分类层级
  catLevelOptions: [
    { label: '大类', value: 1 },
    { label: '中类', value: 2 },
    { label: '小类', value: 3 },
  ],
  isRequiredOptions: [
    { label: '是', value: 1 },
    { label: '否', value: 0 }
  ],
})

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
  formData.value.parentCatId = dataMaps.name2Id[name] ?? undefined
}
const handleDataIdInput = (id: string) => {
  formData.value.parentCatName = dataMaps.id2Name[id] ?? undefined
}

// 响应分类层级变化
const handleCatLevelChange = async (level: string) => {
  // 根据层级锁定或解锁上级分类字段
  if (level === '1') {
    // 层级为1时，锁定上级分类字段并清空值
    formData.value.parentCatId = undefined
    formData.value.parentCatName = undefined
    dataMaps.options = [] // 清空选项
  } else {
    // 层级为2或3时，根据层级获取对应的上级分类选项
    await loadParentOptions(level)
  }
}

// 根据层级加载上级分类选项
const loadParentOptions = async (currentLevel: string) => {
  try {
    // 获取所有启用的资产分类
    const allCategories = await AssetCatMngApi.getEnabledAssetCatMngList()
    
    // 根据当前层级计算上级层级
    const parentLevelMap = {
      '2': '1', // 层级2的上级是层级1
      '3': '2'  // 层级3的上级是层级2
    }
    
    const parentLevel = parentLevelMap[currentLevel as keyof typeof parentLevelMap]
    
    if (parentLevel) {
      // 筛选出对应层级的分类作为上级选项（修正：使用 === 而不是 !==）
      const parentCategories = allCategories.filter((cat: AssetCatMngVO) => 
        String(cat.catLevel) === String(parentLevel)  // 确保类型一致
      )
      
      console.log(`层级 ${currentLevel} 的上级层级 ${parentLevel} 的分类:`, parentCategories)
      
      // 构建映射数据
      const { name2Id, id2Name, options } = buildCatMaps(parentCategories)
      dataMaps.name2Id = name2Id
      dataMaps.id2Name = id2Name
      dataMaps.options = options
      
      // 清空已选值，让用户重新选择
      formData.value.parentCatId = undefined
      formData.value.parentCatName = undefined
    }
  } catch (error) {
    console.error('加载上级分类选项失败:', error)
    message.error('加载上级分类选项失败')
  }
}

// 监听分类层级变化（在表单的 el-select 上添加）
const onCatLevelChange = (value: string) => {
  handleCatLevelChange(value)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetCatMngApi.getAssetCatMngPage(queryParams)
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
      const data = await AssetCatMngApi.getAssetCatMng(id)
      formData.value = data
      // 如果修改时有分类层级，触发层级变化处理
      if (data.catLevel) {
        await handleCatLevelChange(String(data.catLevel))
      }
    } finally {
      formLoading.value = false
    }
  } else {
    // 新增时，默认清空上级分类选项
    dataMaps.options = []
  }
}
/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as AssetCatMngVO
    if (formType.value === 'create') {
      await AssetCatMngApi.createAssetCatMng(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetCatMngApi.updateAssetCatMng(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 刷新列表
    getList()
    // 刷新树形数据
    refreshTreeData()
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetCatId: undefined,
    relCatRuleId: undefined,
    assetCatCode: undefined,
    assetCatName: undefined,
    catLevel: undefined,
    parentCatId: undefined,
    parentCatName: undefined,
    catDesc: undefined,
    enableStatus: undefined,
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
    await AssetCatMngApi.deleteAssetCatMng(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
    // 刷新树形数据
    refreshTreeData()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await AssetCatMngApi.exportAssetCatMng(queryParams)
    download.excel(data, '资产分类管理.xls')
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
  // 刷新树形数据
  refreshTreeData()
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'createdTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'createdTime'
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

import { useRoute } from 'vue-router'

const route = useRoute()

/** 初始化 **/
onMounted(() => {
  // 初始化启用资产分类数据
  loadDataMaps()
  // 刷新树形数据
  refreshTreeData()
  // 如果路由参数中包含 relCatRuleId，则设置为查询条件
  const ruleId = route.query.relCatRuleId
  if (ruleId) {
    queryParams.relCatRuleId = ruleId as string
  }
  getList()
})
</script>

<style scoped>
.asset-cat-mng-container {
  display: flex;
  height: 100%;
  gap: 16px;
}

.tree-sidebar {
  width: 15%;
  min-width: 280px;
  flex-shrink: 0;
}

.content-main {
  flex: 1;
  min-width: 0; /* 防止内容溢出 */
}

/* 响应式布局 */
@media (max-width: 1200px) {
  .tree-sidebar {
    width: 20%;
    min-width: 250px;
  }
}

@media (max-width: 992px) {
  .asset-cat-mng-container {
    flex-direction: column;
  }
  
  .tree-sidebar {
    width: 100%;
    min-width: auto;
  }
}
</style>