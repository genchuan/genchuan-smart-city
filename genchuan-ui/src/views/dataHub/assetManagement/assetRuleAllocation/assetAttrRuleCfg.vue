<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">

      <el-form-item label="关联资产分类名称" prop="relAssetCatName">
        <el-select v-model="queryParams.relAssetCatName" placeholder="请选择关联资产分类名称" clearable filterable
          class="!w-240px">
          <el-option v-for="op in catMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>
      <el-form-item label="属性名称" prop="assetAttrName">
        <el-input v-model="queryParams.assetAttrName" placeholder="请输入属性名称" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>

      <el-form-item label="属性数据类型" prop="attrDataType">
        <el-select v-model="queryParams.attrDataType" placeholder="请选择属性数据类型" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.attrDataTypeOptions" :key="item.value" :label="item.label"
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
          v-hasPermi="['assetRuleAllocation:assetAttrRuleCfg:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetRuleAllocation:assetAttrRuleCfg:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>

        <el-button type="warning" plain :disabled="batchIds.length === 0" @click="handleBatchSetRequired(1)">
          批量设为必选
        </el-button>
        <el-button type="warning" plain :disabled="batchIds.length === 0" @click="handleBatchSetRequired(0)">
          批量设为可选
        </el-button>

      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
      @selection-change="handleSelectionChange" @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" /><!-- 选择框 -->
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="属性规则ID" align="center" prop="assetAttrRuleId" />
      <el-table-column label="关联资产分类ID" align="center" prop="relAssetCatId" />
      <el-table-column label="关联资产分类名称" align="center" prop="relAssetCatName" />
      <el-table-column label="属性名称" align="center" prop="assetAttrName" />
      <el-table-column label="属性代码" align="center" prop="assetAttrCode" />
      <el-table-column label="属性数据类型" align="center" prop="attrDataType" />
      <el-table-column label="属性长度" align="center" prop="attrLength" />
      <el-table-column label="属性值域" align="center" prop="attrValueRange" />
      <el-table-column label="是否必选" align="center" prop="isRequired" />
      <el-table-column label="属性说明" align="center" prop="attrDesc" />
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
            v-hasPermi="['assetRuleAllocation:assetAttrRuleCfg:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetRuleAllocation:assetAttrRuleCfg:delete']">
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
        <el-select v-model="formData.relAssetCatName" placeholder="请选择关联资产名称" clearable @change="onCatNameChange">
          <el-option v-for="op in catMaps.options" :key="op.value" :label="op.label" :value="op.label" />
        </el-select>
      </el-form-item>

      <!-- 关联资产 ID （可手动输入，也可回显） -->
      <el-form-item label="关联资产ID" prop="relAssetId">
        <el-input v-model="formData.relAssetCatId" placeholder="系统自动带出或手动输入" clearable @change="onCatIdInput" />
      </el-form-item>

      <el-form-item label="属性名称" prop="assetAttrName">
        <el-input v-model="formData.assetAttrName" placeholder="请输入属性名称" />
      </el-form-item>
      <el-form-item label="属性代码" prop="assetAttrCode">
        <el-input v-model="formData.assetAttrCode" placeholder="请输入属性代码" />
      </el-form-item>
      <el-form-item label="属性数据类型" prop="attrDataType">
        <el-select v-model="formData.attrDataType" placeholder="请选择属性数据类型">
          <el-option v-for="item in OptionsAll.attrDataTypeOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="属性长度" prop="attrLength">
        <el-input v-model="formData.attrLength" placeholder="请输入属性长度" />
      </el-form-item>
      <el-form-item label="属性值域" prop="attrValueRange">
        <el-input v-model="formData.attrValueRange" placeholder="请输入属性值域" />
      </el-form-item>
      <el-form-item label="是否必选" prop="isRequired">
        <el-select v-model="formData.isRequired" placeholder="请选择是否必选">
          <el-option v-for="item in OptionsAll.isRequiredOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="属性说明" prop="attrDesc">
        <el-input v-model="formData.attrDesc" placeholder="请输入属性说明" />
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

<!-- 抽屉组件 -->
 <assetAttrRuleCfgDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetAttrRuleCfgApi, AssetAttrRuleCfgVO } from '@/api/dataHub/assetManagement/assetRuleAllocation/assetAttrRuleCfg'
import assetAttrRuleCfgDrawer from './components/drawer/assetAttrRuleCfgDrawer.vue'

/** 资产属性规则配置 列表 */
defineOptions({ name: 'AssetAttrRuleCfg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetAttrRuleCfgVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetAttrRuleId: undefined,
  relAssetCatId: undefined,
  relAssetCatName: undefined,
  assetAttrName: undefined,
  assetAttrCode: undefined,
  attrDataType: undefined,
  attrLength: undefined,
  attrValueRange: undefined,
  isRequired: undefined,
  attrDesc: undefined,
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
  assetAttrRuleId: undefined,
  relAssetCatId: undefined as string | undefined,
  relAssetCatName: undefined as string | undefined,
  assetAttrName: undefined,
  assetAttrCode: undefined,
  attrDataType: undefined,
  attrLength: undefined,
  attrValueRange: undefined,
  isRequired: undefined,
  attrDesc: undefined,
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
  assetAttrRuleId: [{ required: true, message: '属性规则ID不能为空', trigger: 'blur' }],
  relAssetCatId: [{ required: true, message: '关联资产分类ID不能为空', trigger: 'blur' }],
  relAssetCatName: [{ required: true, message: '关联资产分类名称不能为空', trigger: 'change' }],
  assetAttrName: [{ required: true, message: '属性名称不能为空', trigger: 'blur' }],
  assetAttrCode: [{ required: true, message: '属性代码不能为空', trigger: 'blur' }],
  attrDataType: [{ required: true, message: '属性数据类型不能为空', trigger: 'change' }],
  isRequired: [{ required: true, message: '是否必选不能为空', trigger: 'change' }],
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
  // 关联资产分类名称
  relAssetCatNameOptions: [] as Array<{ label: string; value: string }>,
  // 属性数据类型
  attrDataTypeOptions: [
    { label: '字符串', value: 'varchar' },
    { label: '整数', value: 'int' },
    { label: '浮点数', value: 'decimal' },
    { label: '枚举', value: 'enum' },
  ],
  // 是否必选
  isRequiredOptions: [
    { label: '是', value: 1 },
    { label: '否', value: 0 }
  ],
})

import { getCatMaps } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'
/* 资产分类映射 & 下拉选项 */
const catMaps = reactive({
  name2Id: {} as Record<string, string>,
  id2Name: {} as Record<string, string>,
  options: [] as { label: string; value: string }[],
})

/* 初始化映射表（mounted 阶段调用） */
const loadCatMaps = async () => {
  const maps = await getCatMaps()
  Object.assign(catMaps, maps)
}

/* 双向赋值函数 */
const onCatNameChange = (name: string) => {
  formData.value.relAssetCatId= catMaps.name2Id[name] ?? undefined
}
const onCatIdInput = (id: string) => {
  formData.value.relAssetCatName = catMaps.id2Name[id] ?? undefined
}


/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetAttrRuleCfgApi.getAssetAttrRuleCfgPage(queryParams)
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
      formData.value = await AssetAttrRuleCfgApi.getAssetAttrRuleCfg(id)
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
    const data = formData.value as unknown as AssetAttrRuleCfgVO
    if (formType.value === 'create') {
      await AssetAttrRuleCfgApi.createAssetAttrRuleCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetAttrRuleCfgApi.updateAssetAttrRuleCfg(data)
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
    assetAttrRuleId: undefined,
    relAssetCatId: undefined,
    relAssetCatName: undefined,
    assetAttrName: undefined,
    assetAttrCode: undefined,
    attrDataType: undefined,
    attrLength: undefined,
    attrValueRange: undefined,
    isRequired: undefined,
    attrDesc: undefined,
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
    await AssetAttrRuleCfgApi.deleteAssetAttrRuleCfg(id)
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
    const data = await AssetAttrRuleCfgApi.exportAssetAttrRuleCfg(queryParams)
    download.excel(data, '资产属性规则配置.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

// 批量选择的 ID 列表
/* ----- 批量相关 ----- */
const batchIds = ref<number[]>([])

// 复选框变化
const handleSelectionChange = (rows: AssetAttrRuleCfgVO[]) => {
  batchIds.value = rows.map(r => r.id)
}

// 真正批量请求
const handleBatchSetRequired = async (flag: 0 | 1) => {
  try {
    await message.confirm(
      `确认将选中的 ${batchIds.value.length} 条数据设为${flag ? '必选' : '可选'}？`
    )
    await AssetAttrRuleCfgApi.batchUpdateIsRequired({
      ids: batchIds.value,
      isRequired: String(flag)
    })
    message.success('批量设置成功')
    await getList()
    batchIds.value = []      // 清空选中
  } catch { /* 用户取消 */ }
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

/** 初始化 **/
onMounted(() => {
  getList()
  loadCatMaps()   // 加载关联资产分类名称字典
})
</script>