<template>
  <div class="page-container">
    <!-- 左右布局 -->
    <div class="layout-container">
      <!-- 左侧资产树 -->
      <div class="left-panel">
        <AssetClientAttrCfgTree 
          ref="assetTreeRef"
          @asset-select="handleAssetSelect"
        />
      </div>
      
      <!-- 右侧内容 -->
      <div class="right-panel">
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
            <el-form-item label="属性名称" prop="attrName">
              <el-input v-model="queryParams.attrName" placeholder="请输入属性名称" clearable @keyup.enter="handleQuery"
                class="!w-240px" />
            </el-form-item>
            <el-form-item label="属性代码" prop="attrCode">
              <el-input v-model="queryParams.attrCode" placeholder="请输入属性代码" clearable @keyup.enter="handleQuery"
                class="!w-240px" />
            </el-form-item>

            <el-form-item>
              <el-button @click="handleQuery">
                <Icon icon="ep:search" class="mr-5px" /> 搜索
              </el-button>
              <el-button @click="resetQuery">
                <Icon icon="ep:refresh" class="mr-5px" /> 重置
              </el-button>
              <el-button type="primary" plain @click="addClick"
                v-hasPermi="['assetDataMng:assetClientAttrCfg:create']">
                <Icon icon="ep:plus" class="mr-5px" /> 新增
              </el-button>
              <el-button type="success" plain @click="handleExport" :loading="exportLoading"
                v-hasPermi="['assetDataMng:assetClientAttrCfg:export']">
                <Icon icon="ep:download" class="mr-5px" /> 导出
              </el-button>

              <!-- 导入 -->
              <el-button type="warning" plain @click="handleImport" v-hasPermi="['assetDataMng:assetClientAttrCfg:import']">
                <Icon icon="ep:upload" class="mr-5px" /> 导入
              </el-button>
            </el-form-item>
          </el-form>
        </ContentWrap>

        <!-- 列表 -->
        <ContentWrap>
          <div class="table-header" v-if="selectedAsset.assetName">
            <el-tag type="primary" size="large">
              当前筛选资产：{{ selectedAsset.assetName }}（{{ selectedAsset.assetId }}）
            </el-tag>
            <el-button 
              link 
              type="danger" 
              @click="clearAssetFilter"
              class="clear-filter-btn"
            >
              清除筛选
            </el-button>
          </div>
          
          <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
            @sort-change="handleSortChange">
            <el-table-column label="客户端属性ID" align="center" prop="assetClientAttrId" />
            <el-table-column label="属性名称" align="center" prop="attrName" />
            <el-table-column label="属性代码" align="center" prop="attrCode" />
            <el-table-column label="属性数据类型" align="center" prop="attrDataType" />
            <el-table-column label="属性值" align="center" prop="attrValue" />
            <el-table-column label="属性用途" align="center" prop="attrPurpose" />
            <el-table-column label="客户端IP" align="center" prop="clientIp" />
            <el-table-column label="配置时间" align="center" prop="cfgTime" :formatter="dateFormatter" width="180px"
              sortable="custom" :sort-orders="['ascending', 'descending']" />
            <el-table-column label="操作人" align="center" prop="operUser" />
            <el-table-column label="更新人" align="center" prop="updateUser" />
            <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px"
            sortable="custom" :sort-orders="['ascending', 'descending']" />

            <el-table-column label="操作" align="center" min-width="150px">
              <template #default="scope">
                <el-button link type="primary" @click="queryClick(scope.row)"
                  v-hasPermi="['assetDataMng:assetClientAttrCfg:query']">
                  详细
                </el-button>
                <el-button link type="primary" @click="editClick(scope.row)"
                  v-hasPermi="['assetDataMng:assetClientAttrCfg:update']">
                  编辑
                </el-button>
                <el-button link type="danger" @click="handleDelete(scope.row.id)"
                  v-hasPermi="['assetDataMng:assetClientAttrCfg:delete']">
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

    <!-- 导入组件 -->
    <AssetClientAttrCfgImport ref="importRef" @success="handleImportSuccess" />

    <!-- 抽屉组件 -->
    <AssetClientAttrCfgDrawer
      :drawer-config="drawerConfig"
      :form-data="formData"
      :query-data="queryData"
      :data-maps="dataMaps"
      @update:drawer-config="handleDrawerConfigUpdate"
      @update:form-data="handleFormDataUpdate"
      @update:query-data="handleQueryDataUpdate"
      @submit-form="handleDrawerSubmit"
      @tab-click="handleTabClick"
      @data-name-change="handleDataNameChange"
      @data-id-input="handleDataIdInput"
      @cancel="handleDrawerCancel"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetClientAttrCfgApi, AssetClientAttrCfgVO } from '@/api/dataHub/assetManagement/assetDataMng/assetClientAttrCfg'
import AssetClientAttrCfgImport from './components/import/assetClientAttrCfgImport.vue'
import AssetClientAttrCfgDrawer from './components/drawer/assetClientAttrCfgDrawer.vue'
import AssetClientAttrCfgTree from './components/tree/assetClientAttrCfgTree.vue'

/** 资产客户端属性配置 列表 */
defineOptions({ name: 'AssetClientAttrCfg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

// 资产树组件引用
const assetTreeRef = ref()

// 当前选中的资产
const selectedAsset = ref({
  assetId: '',
  assetName: ''
})

const loading = ref(true) // 列表的加载中
const list = ref<AssetClientAttrCfgVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 15,
  assetClientAttrId: undefined,
  relAssetId: undefined as string | undefined,
  relAssetName: undefined as string | undefined,
  attrName: undefined,
  attrCode: undefined,
  attrDataType: undefined,
  attrValue: undefined,
  attrPurpose: undefined,
  clientIp: undefined,
  cfgTime: [],
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

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetClientAttrCfgApi.getAssetClientAttrCfgPage(queryParams)
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

/**
 * 处理资产选择
 */
const handleAssetSelect = (asset: { assetId: string; assetName: string }) => {
  selectedAsset.value = asset
  // 设置查询参数
  queryParams.relAssetId = asset.assetId
  queryParams.relAssetName = asset.assetName
  queryParams.pageNo = 1
  // 执行查询
  getList()
}

/**
 * 清除资产筛选
 */
const clearAssetFilter = () => {
  selectedAsset.value = {
    assetId: '',
    assetName: ''
  }
  // 清除查询参数
  queryParams.relAssetId = undefined
  queryParams.relAssetName = undefined
  queryParams.pageNo = 1
  // 清除树形组件的选中状态
  assetTreeRef.value?.clearSelection()
  // 重新加载列表
  getList()
}

// ========== 抽屉相关 ==========
const drawerConfig = ref({
  isDrawer: false,
  loading: false,
  title: '资产客户端属性配置',
  type: 'add', // add新增 edit编辑 query详细
  active: '1',
  fullscreenType: false,
  tabsList: [
    {label: '基础信息', name: '1'},
    {label: '预留', name: '2'}
  ]
})

// 表单数据
const formData = ref({
  id: undefined,
  assetClientAttrId: undefined,
  relAssetId: undefined as string | undefined,
  relAssetName: undefined as string | undefined,
  attrName: undefined,
  attrCode: undefined,
  attrDataType: undefined,
  attrValue: undefined,
  attrPurpose: undefined,
  clientIp: undefined,
  cfgTime: undefined,
  operUser: undefined,
  updateUser: undefined,
  updatedTime: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})

// 查看数据
const queryData = ref({})

/** 处理抽屉配置更新 */
const handleDrawerConfigUpdate = (newConfig: any) => {
  drawerConfig.value = newConfig
}

/** 处理表单数据更新 */
const handleFormDataUpdate = (newData: any) => {
  formData.value = newData
}

/** 处理查询数据更新 */
const handleQueryDataUpdate = (newData: any) => {
  queryData.value = newData
}

/** 点击详细按钮 */
const queryClick = async (row: any) => {
  drawerConfig.value = {
    ...drawerConfig.value,
    active: '1',
    isDrawer: true,
    loading: false,
    type: 'query',
    title: '资产客户端属性配置-详细信息'
  }
  queryData.value = { ...row }
}

/** 点击编辑按钮 */
const editClick = async (row: any) => {
  drawerConfig.value = {
    ...drawerConfig.value,
    active: '1',
    isDrawer: true,
    loading: false,
    type: 'edit',
    title: '编辑资产客户端属性配置'
  }
  formData.value = { ...row }
}

/** 点击新增按钮 */
const addClick = async () => {
  formData.value = {
    id: undefined,
    assetClientAttrId: undefined,
    relAssetId: undefined,
    relAssetName: undefined,
    attrName: undefined,
    attrCode: undefined,
    attrDataType: undefined,
    attrValue: undefined,
    attrPurpose: undefined,
    clientIp: undefined,
    cfgTime: undefined,
    operUser: undefined,
    updateUser: undefined,
    updatedTime: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  }
  drawerConfig.value = {
    ...drawerConfig.value,
    isDrawer: true,
    loading: false,
    type: 'add',
    title: '新增资产客户端属性配置',
    active: '1'
  }
}

/** 抽屉表单提交 */
const handleDrawerSubmit = async (formEl: any) => {
  try {
    const data = formData.value as unknown as AssetClientAttrCfgVO
    if (drawerConfig.value.type === 'add') {
      await AssetClientAttrCfgApi.createAssetClientAttrCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetClientAttrCfgApi.updateAssetClientAttrCfg(data)
      message.success(t('common.updateSuccess'))
    }
    drawerConfig.value = {
      ...drawerConfig.value,
      isDrawer: false
    }
    await getList()
  } catch (error) {
    console.log('表单提交出错', error)
  }
}

/** 处理抽屉取消 */
const handleDrawerCancel = () => {
  drawerConfig.value = {
    ...drawerConfig.value,
    isDrawer: false
  }
}

/** 处理Tab切换 */
const handleTabClick = (tab: any) => {
  console.log('tabs点击切换', tab.props.name)
}

/** 双向赋值函数 */
const handleDataNameChange = (name: string) => {
  formData.value.relAssetId = dataMaps.name2Id[name] ?? undefined
}

const handleDataIdInput = (id: string) => {
  formData.value.relAssetName = dataMaps.id2Name[id] ?? undefined
}

/** 所有选项集合 */
const OptionsAll = ref({
  // 关联资产名称
  relAssetNameOptions: [] as Array<{ label: string; value: string }>,
  // 属性数据类型
  attrDataTypeOptions: [
    {label: '字符串', value: "字符串"},
    {label: '整数', value: "整数"},
    {label: '浮点数', value: "浮点数"},
    {label: '布尔值', value: "布尔值"},
    {label: '日期时间', value: "日期时间"}
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

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await AssetClientAttrCfgApi.deleteAssetClientAttrCfg(id)
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
    const data = await AssetClientAttrCfgApi.exportAssetClientAttrCfg(queryParams)
    download.excel(data, '资产客户端属性配置.xls')
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
  if (sort.prop === 'cfgTime') {
    queryParams.orderByColumn = 'cfgTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'updatedTime') {
    queryParams.orderByColumn = 'updatedTime'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  
  handleQuery()
}

/** 初始化 **/
onMounted(() => {
  loadDataMaps()
  getList()
})
</script>

<style lang="scss" scoped>
.page-container {
  height: 100%;
  
  .layout-container {
    display: flex;
    height: 100%;
    gap: 16px;
    
    .left-panel {
      width: 320px;
      min-width: 320px;
      height: 100%;
    }
    
    .right-panel {
      flex: 1;
      min-width: 0;
    }
  }
  
  .table-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 16px;
    padding: 12px 16px;
    background: #f8f9fa;
    border-radius: 4px;
    
    .clear-filter-btn {
      margin-left: 12px;
    }
  }
}
</style>