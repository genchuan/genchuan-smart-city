<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      
      <el-form-item label="从资产ID" prop="slaveAssetId">
        <el-input
          v-model="queryParams.slaveAssetId"
          placeholder="请输入从资产ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="从资产名称" prop="slaveAssetName">
        <el-select
          v-model="queryParams.slaveAssetName"
          placeholder="请选择从资产名称"
          clearable
          class="!w-240px"
        >
          <el-option v-for="item in OptionsAll.slaveAssetNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="主资产ID" prop="masterAssetId">
        <el-input
          v-model="queryParams.masterAssetId"
          placeholder="请输入主资产ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主资产名称" prop="masterAssetName">
        <el-select
          v-model="queryParams.masterAssetName"
          placeholder="请选择主资产名称"
          clearable
          class="!w-240px"
        >
          <el-option v-for="item in OptionsAll.masterAssetNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['assetOperationManagement:assetAppScene:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['assetOperationManagement:assetAppScene:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true" @sort-change="handleSortChange">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="关联ID" align="center" prop="assetRelAssetId" />
      <el-table-column label="从资产ID" align="center" prop="slaveAssetId" />
      <el-table-column label="从资产名称" align="center" prop="slaveAssetName" />
      <el-table-column label="主资产ID" align="center" prop="masterAssetId" />
      <el-table-column label="主资产名称" align="center" prop="masterAssetName" />
      <el-table-column label="关联类型" align="center" prop="relType" />
      <el-table-column
        label="关联时间"
        align="center"
        prop="relTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
        :sort-orders="['ascending', 'descending']"
      />
      <el-table-column label="操作人" align="center" prop="operUser" />
      <el-table-column label="关联说明" align="center" prop="relDesc" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updatedTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
        :sort-orders="['ascending', 'descending']"
      />
      
      <el-table-column label="操作" align="center" min-width="220px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
            <Icon icon="ep:view" class="mr-2px" /> 详情
          </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetOperationManagement:assetAppScene:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetOperationManagement:assetAppScene:delete']"
          >
            删除
          </el-button>
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

  <!-- 表单弹窗：添加/修改 -->
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >

      <el-form-item label="从资产ID" prop="slaveAssetId">
        <el-input v-model="formData.slaveAssetId" placeholder="请输入从资产ID" />
      </el-form-item>
      <el-form-item label="从资产名称" prop="slaveAssetName">
        <el-select v-model="formData.slaveAssetName" placeholder="请选择从资产名称">
          <el-option v-for="item in OptionsAll.slaveAssetNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="主资产ID" prop="masterAssetId">
        <el-input v-model="formData.masterAssetId" placeholder="请输入主资产ID" />
      </el-form-item>
      <el-form-item label="主资产名称" prop="masterAssetName">
        <el-select v-model="formData.masterAssetName" placeholder="请选择主资产名称">
          <el-option v-for="item in OptionsAll.masterAssetNameOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="关联类型" prop="relType">
        <el-select v-model="formData.relType" placeholder="请选择关联类型">
          <el-option v-for="item in OptionsAll.relTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="关联时间" prop="relTime">
        <el-date-picker
          v-model="formData.relTime"
          type="date"
          value-format="x"
          placeholder="选择关联时间"
        />
      </el-form-item>
      <el-form-item label="操作人" prop="operUser">
        <el-input v-model="formData.operUser" placeholder="请输入操作人" />
      </el-form-item>
      <el-form-item label="关联说明" prop="relDesc">
        <el-input v-model="formData.relDesc" type="textarea" placeholder="请输入关联说明" />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input v-model="formData.updateUser" placeholder="请输入更新人" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker
          v-model="formData.updatedTime"
          type="date"
          value-format="x"
          placeholder="选择更新时间"
        />
      </el-form-item>
      
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>

  <!-- 抽屉组件 -->
    <assetAssetDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetAssetApi, AssetAssetVO } from '@/api/dataHub/assetManagement/assetOperationManagement/assetAsset'

import assetAssetDrawer from './components/drawer/assetAssetDrawer.vue'

/** 资产关联资产 列表 */
defineOptions({ name: 'AssetAsset' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetAssetVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 15,
  assetRelAssetId: undefined,
  slaveAssetId: undefined,
  slaveAssetName: undefined,
  masterAssetId: undefined,
  masterAssetName: undefined,
  relType: undefined,
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
  assetRelAssetId: undefined,
  slaveAssetId: undefined,
  slaveAssetName: undefined,
  masterAssetId: undefined,
  masterAssetName: undefined,
  relType: undefined,
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
  assetRelAssetId: [{ required: true, message: '关联ID不能为空', trigger: 'blur' }],
  slaveAssetId: [{ required: true, message: '从资产ID不能为空', trigger: 'blur' }],
  slaveAssetName: [{ required: true, message: '从资产名称不能为空', trigger: 'change' }],
  masterAssetId: [{ required: true, message: '主资产ID不能为空', trigger: 'blur' }],
  masterAssetName: [{ required: true, message: '主资产名称不能为空', trigger: 'change' }],
  relType: [{ required: true, message: '关联类型不能为空', trigger: 'change' }],
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

/** 所有选项集合 */
const OptionsAll = ref({

  // 主资产名称
  masterAssetNameOptions: [
    { label: '资产A', value: '资产A' },
    { label: '资产B', value: '资产B' }
  ],

  // 从资产名称
  slaveAssetNameOptions: [
    { label: '资产C', value: '资产C' },
    { label: '资产D', value: '资产D' }
  ],
  // 关联类型
  relTypeOptions: [
    { label: '类型A', value: '类型A' },
    { label: '类型B', value: '类型B' }
  ]

})

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetAssetApi.getAssetAssetPage(queryParams)
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
  queryParams.orderByColumn = '' // 重置为无排序
  queryParams.isAsc = '' // 重置为无排序
  handleQuery()
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
      formData.value = await AssetAssetApi.getAssetAsset(id)
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
    const data = formData.value as unknown as AssetAssetVO
    if (formType.value === 'create') {
      await AssetAssetApi.createAssetAsset(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetAssetApi.updateAssetAsset(data)
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
    assetRelAssetId: undefined,
    slaveAssetId: undefined,
    slaveAssetName: undefined,
    masterAssetId: undefined,
    masterAssetName: undefined,
    relType: undefined,
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
    await AssetAssetApi.deleteAssetAsset(id)
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
    const data = await AssetAssetApi.exportAssetAsset(queryParams)
    download.excel(data, '资产关联资产.xls')
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
  getList()
})
</script>