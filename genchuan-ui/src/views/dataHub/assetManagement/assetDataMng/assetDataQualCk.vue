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

      <el-form-item label="检查结果" prop="ckResult">
        <el-select v-model="queryParams.ckResult" placeholder="请选择检查结果" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.ckResultOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>

      <el-form-item label="检查方式" prop="ckMethod">
        <el-select v-model="queryParams.ckMethod" placeholder="请选择检查方式" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.ckMethodOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="handleStatus">
        <el-select v-model="queryParams.handleStatus" placeholder="请选择处理状态" clearable class="!w-240px">
          <el-option v-for="item in OptionsAll.handleStatusOptions" :key="item.value" :label="item.label"
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
          v-hasPermi="['assetDataMng:assetDataQualCk:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetDataMng:assetDataQualCk:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 主要内容区域 -->
  <div class="main-content">
    <!-- 资产列表 -->
    <ContentWrap class="asset-list-container">
      <el-table 
        v-loading="treeLoading" 
        :data="assetListData" 
        :stripe="true" 
        :show-overflow-tooltip="true"
        @row-click="handleAssetRowClick"
        highlight-current-row
        class="asset-list-table"
      >
        <el-table-column label="资产名称（ID）" min-width="200">
          <template #default="scope">
            <span>{{ scope.row.name }}（{{ scope.row.id }}）</span>
          </template>
        </el-table-column>
      </el-table>
    </ContentWrap>

    <!-- 质量检查列表 -->
    <ContentWrap class="quality-list-container">
      <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
        @sort-change="handleSortChange">

        <el-table-column label="质量检查ID" align="center" prop="assetDataQualId" />

        <el-table-column label="检查数据类型" align="center" prop="ckDataType" />
        <el-table-column label="检查项名称" align="center" prop="ckItemName" />
        <el-table-column label="检查项代码" align="center" prop="ckItemCode" />
        <el-table-column label="检查结果" align="center" prop="ckResult" />
        <el-table-column label="错误描述" align="center" prop="errorDesc" />
        <el-table-column label="检查时间" align="center" prop="ckTime" :formatter="dateFormatter" width="180px"
          sortable="custom" :sort-orders="['ascending', 'descending']" />
        <el-table-column label="检查方式" align="center" prop="ckMethod" />
        <el-table-column label="处理状态" align="center" prop="handleStatus" />
        <el-table-column label="处理人" align="center" prop="handleUser" />
        <el-table-column label="处理时间" align="center" prop="handleTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />
 
        <el-table-column label="操作" align="center" min-width="220px">
          <template #default="scope">
            <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
            <Icon icon="ep:view" class="mr-2px" /> 详情
          </el-button>
            <el-button link type="primary" @click="openForm('update', scope.row.id)"
              v-hasPermi="['assetDataMng:assetDataQualCk:update']">
              编辑
            </el-button>
            <el-button link type="danger" @click="handleDelete(scope.row.id)"
              v-hasPermi="['assetDataMng:assetDataQualCk:delete']">
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

  <!-- 抽屉：添加/修改 -->
  <el-drawer
    v-model="drawerVisible"
    :title="drawerTitle"
    direction="rtl"
    size="70%"
    class="form-drawer"
  >
    <template #header>
      <h4>{{ drawerTitle }}</h4>
    </template>
    
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

      <el-form-item label="检查数据类型" prop="ckDataType">
        <el-select v-model="formData.ckDataType" placeholder="请选择检查数据类型">
          <el-option v-for="item in OptionsAll.ckDataTypeOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="检查项名称" prop="ckItemName">
        <el-input v-model="formData.ckItemName" placeholder="请输入检查项名称" />
      </el-form-item>
      <el-form-item label="检查项代码" prop="ckItemCode">
        <el-input v-model="formData.ckItemCode" placeholder="请输入检查项代码" />
      </el-form-item>
      <el-form-item label="检查结果" prop="ckResult">
        <el-select v-model="formData.ckResult" placeholder="请选择检查结果">
          <el-option v-for="item in OptionsAll.ckResultOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="错误描述" prop="errorDesc">
        <el-input v-model="formData.errorDesc" placeholder="请输入错误描述" />
      </el-form-item>
      <el-form-item label="检查时间" prop="ckTime">
        <el-date-picker v-model="formData.ckTime" type="date" value-format="x" placeholder="选择检查时间" />
      </el-form-item>
      <el-form-item label="检查方式" prop="ckMethod">
        <el-select v-model="formData.ckMethod" placeholder="请选择检查方式">
          <el-option v-for="item in OptionsAll.ckMethodOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="handleStatus">
        <el-select v-model="formData.handleStatus" placeholder="请选择处理状态">
          <el-option v-for="item in OptionsAll.handleStatusOptions" :key="item.value" :label="item.label"
            :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理人" prop="handleUser">
        <el-input v-model="formData.handleUser" placeholder="请输入处理人" />
      </el-form-item>
      <el-form-item label="处理时间" prop="handleTime">
        <el-date-picker v-model="formData.handleTime" type="date" value-format="x" placeholder="选择处理时间" />
      </el-form-item>
      
    </el-form>
    
    <template #footer>
      <div style="flex: auto">
        <el-button @click="drawerVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm" :disabled="formLoading">确定</el-button>
      </div>
    </template>
  </el-drawer>

  <!-- 抽屉组件 -->
  <assetDataQualCkDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetDataQualCkApi, AssetDataQualCkVO } from '@/api/dataHub/assetManagement/assetDataMng/assetDataQualCk'

import assetDataQualCkDrawer from './components/drawer/assetDataQualCkDrawer.vue'

/** 资产数据质量检查 列表 */
defineOptions({ name: 'AssetDataQualCk' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetDataQualCkVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  assetDataQualId: undefined,
  relAssetId: undefined,
  relAssetName: undefined,
  ckDataType: undefined,
  ckItemName: undefined,
  ckItemCode: undefined,
  ckResult: undefined,
  errorDesc: undefined,
  ckTime: [],
  ckMethod: undefined,
  handleStatus: undefined,
  handleUser: undefined,
  handleTime: [],
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
    const data = await AssetDataQualCkApi.getAssetDataQualCkPage(queryParams)
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

// ========== 抽屉相关 ==========
const drawerVisible = ref(false) // 抽屉的是否展示
const drawerTitle = ref('') // 抽屉的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetDataQualId: undefined,
  relAssetId: undefined as string | undefined,
  relAssetName: undefined as string | undefined,
  ckDataType: undefined,
  ckItemName: undefined,
  ckItemCode: undefined,
  ckResult: undefined,
  errorDesc: undefined,
  ckTime: undefined,
  ckMethod: undefined,
  handleStatus: undefined,
  handleUser: undefined,
  handleTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  assetDataQualId: [{ required: true, message: '质量检查ID不能为空', trigger: 'blur' }],
  relAssetId: [{ required: true, message: '关联资产ID不能为空', trigger: 'blur' }],
  relAssetName: [{ required: true, message: '关联资产名称不能为空', trigger: 'change' }],
  ckDataType: [{ required: true, message: '检查数据类型不能为空', trigger: 'change' }],
  ckItemName: [{ required: true, message: '检查项名称不能为空', trigger: 'blur' }],
  ckItemCode: [{ required: true, message: '检查项代码不能为空', trigger: 'blur' }],
  ckResult: [{ required: true, message: '检查结果不能为空', trigger: 'change' }],
  ckTime: [{ required: true, message: '检查时间不能为空', trigger: 'blur' }],
  ckMethod: [{ required: true, message: '检查方式不能为空', trigger: 'change' }],
  handleStatus: [{ required: true, message: '处理状态不能为空', trigger: 'change' }],
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
  relAssetNameOptions: [] as Array<{label: string; value: string}>,
  //检查数据类型
  ckDataTypeOptions: [
   {label: '空间数据', value: "spatial"},
   {label: '服务端属性', value: "server_attr"},
   {label: '客户端属性', value: "client_attr"}
  ],
  //检查项名称
  ckItemNameOptions: [
   {label: '项A', value: "项A"},
   {label: '项B', value: "项B"}
  ],
  //检查结果
  ckResultOptions: [
   {label: '合格', value: "qualified"},
   {label: '不合格', value: "unqualified"}
  ],
  //检查方式
  ckMethodOptions: [
   {label: '自动定期检查', value: "auto"},
   {label: '手动触发检查', value: "manual"}
  ],
  //处理状态
  handleStatusOptions: [
   {label: '待处理', value: "pend"},
   {label: '已处理', value: "handled"}
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
    
    // 同时将映射数据转换为资产列表数据
    loadAssetListData(maps)
}

/* 双向赋值函数 */
const onDataNameChange = (name: string) => {
    formData.value.relAssetId = dataMaps.name2Id[name] ?? undefined
}
const onDataIdInput = (id: string) => {
    formData.value.relAssetName = dataMaps.id2Name[id] ?? undefined
}

// ========== 资产列表相关 ==========
const treeLoading = ref(false)
const assetListData = ref<any[]>([]) // 资产列表数据

/** 加载资产列表数据 - 基于已有的映射数据 */
const loadAssetListData = (maps: any) => {
  treeLoading.value = true
  try {
    // 从映射数据中提取资产列表
    assetListData.value = maps.options.map((option: any) => {
      const assetId = maps.name2Id[option.label]
      return {
        id: assetId,
        name: option.label,
      }
    })
    
    // 如果没有从映射数据中获取到足够的资产数据，添加一些示例数据

  } finally {
    treeLoading.value = false
  }
}

/** 点击资产行事件 */
const handleAssetRowClick = (row: any) => {
  queryParams.relAssetId = row.id
  queryParams.relAssetName = row.name
  handleQuery()
}

/** 打开抽屉 */
const openForm = async (type: string, id?: number) => {
  drawerVisible.value = true
  drawerTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetDataQualCkApi.getAssetDataQualCk(id)
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
    const data = formData.value as unknown as AssetDataQualCkVO
    if (formType.value === 'create') {
      await AssetDataQualCkApi.createAssetDataQualCk(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetDataQualCkApi.updateAssetDataQualCk(data)
      message.success(t('common.updateSuccess'))
    }
    drawerVisible.value = false
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
    assetDataQualId: undefined,
    relAssetId: undefined,
    relAssetName: undefined,
    ckDataType: undefined,
    ckItemName: undefined,
    ckItemCode: undefined,
    ckResult: undefined,
    errorDesc: undefined,
    ckTime: undefined,
    ckMethod: undefined,
    handleStatus: undefined,
    handleUser: undefined,
    handleTime: undefined,
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
    await AssetDataQualCkApi.deleteAssetDataQualCk(id)
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
    const data = await AssetDataQualCkApi.exportAssetDataQualCk(queryParams)
    download.excel(data, '资产数据质量检查.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'ckTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'ckTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  if (sort.prop === 'handleTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'handleTime'
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

<style scoped>
.main-content {
  display: flex;
  gap: 16px;
  height: calc(100vh - 200px);
}

.asset-list-container {
  flex: 0 0 300px;
  height: 100%;
  overflow: auto;
}

.quality-list-container {
  flex: 1;
  height: 100%;
  overflow: auto;
}

.asset-list-table {
  height: 100%;
}

.asset-list-table :deep(.el-table__body) {
  cursor: pointer;
}

.form-drawer :deep(.el-drawer__body) {
  padding: 20px;
}

.form-drawer :deep(.el-form) {
  padding-right: 20px;
}
</style>