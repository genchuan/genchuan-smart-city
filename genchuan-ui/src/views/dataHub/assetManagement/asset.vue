<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
      <el-form-item label="资产名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入资产名称" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="资产类型" prop="type">
        <el-input v-model="queryParams.type" placeholder="请输入资产类型" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="标签" prop="label">
        <el-input v-model="queryParams.label" placeholder="请输入标签" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="租户ID" prop="tenantId">
        <el-input v-model="queryParams.tenantId" placeholder="请输入租户ID" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" /> 搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" /> 重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')" v-hasPermi="['datacenter:asset:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['datacenter:asset:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
        <el-button type="danger" plain @click="handleBatchDelete" v-hasPermi="['datacenter:asset:delete']">
          <Icon icon="ep:delete" class="mr-5px" /> 批量删除
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true"
      @selection-change="handleSelectionChange" @sort-change="handleSortChange">
      <el-table-column type="selection" width="50" align="center" /> <!-- 多选框 -->
      <el-table-column label="资产ID" align="center" prop="id" />
      <el-table-column label="租户ID" align="center" prop="tenantId" />
      <el-table-column label="客户ID" align="center" prop="customerId" />
      <el-table-column label="资产名称" align="center" prop="name" />
      <el-table-column label="资产类型" align="center" prop="type" />
      <el-table-column label="标签" align="center" prop="label" />
      <el-table-column label="资产实体ID" align="center" prop="assetProfileId" />
      <el-table-column label="附加信息" align="center" prop="additionalInfo" />
      <el-table-column label="外部ID" align="center" prop="externalId" />
      <el-table-column label="版本" align="center" prop="version" />
      <el-table-column label="创建时间" align="center" prop="createdTime" :formatter="dateFormatter" width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']" />

      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button link type="primary" @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:asset:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)" v-hasPermi="['datacenter:asset:delete']">
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
      <el-form-item label="资产ID" prop="id">
        <el-input v-model="formData.id" placeholder="请输入资产ID" clearable />
      </el-form-item>
      <el-form-item label="租户ID" prop="tenantId">
        <el-input v-model="formData.tenantId" placeholder="请输入租户ID" />
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input v-model="formData.customerId" placeholder="请输入客户ID" />
      </el-form-item>
      <el-form-item label="资产名称" prop="name">
        <el-input v-model="formData.name" placeholder="请输入资产名称" />
      </el-form-item>
      <el-form-item label="资产类型" prop="type">
        <el-input v-model="formData.type" placeholder="请输入资产类型" />
      </el-form-item>
      <el-form-item label="标签" prop="label">
        <el-input v-model="formData.label" placeholder="请输入标签" />
      </el-form-item>
      <el-form-item label="资产实体ID" prop="assetProfileId">
        <el-input v-model="formData.assetProfileId" placeholder="请输入资产实体ID" />
      </el-form-item>
      <el-form-item label="附加信息" prop="additionalInfo">
        <el-input v-model="formData.additionalInfo" type="textarea" placeholder="请输入附加信息" />
      </el-form-item>
      <el-form-item label="外部ID" prop="externalId">
        <el-input v-model="formData.externalId" placeholder="请输入外部ID" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetApi, AssetVO } from '@/api/dataHub/thingsboard/asset.ts'

/** 资产 列表 */
defineOptions({ name: 'Asset' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  id: undefined,
  tenantId: undefined,
  customerId: undefined,
  name: undefined,
  type: undefined,
  label: undefined,
  assetProfileId: undefined,
  additionalInfo: undefined,
  externalId: undefined,
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
  tenantId: undefined,
  customerId: undefined,
  name: undefined,
  type: undefined,
  label: undefined,
  assetProfileId: undefined,
  additionalInfo: undefined,
  externalId: undefined,
  version: undefined,
  createTime: undefined,
})
const formRules = reactive({
  id: [{ required: true, message: '资产ID不能为空', trigger: 'blur' }],
  tenantId: [{ required: true, message: '租户ID不能为空', trigger: 'blur' }],
  name: [{ required: true, message: '资产名称不能为空', trigger: 'blur' }],
  assetProfileId: [{ required: true, message: '资产实体ID不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetApi.getAssetPage(queryParams)
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

/** 添加/修改操作 */
const openForm = async (type: string, id?: string) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetApi.getAsset(id)
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
    const data = formData.value as unknown as AssetVO
    if (formType.value === 'create') {
      await AssetApi.createAsset(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetApi.updateAsset(data)
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
    tenantId: undefined,
    customerId: undefined,
    name: undefined,
    type: undefined,
    label: undefined,
    assetProfileId: undefined,
    additionalInfo: undefined,
    externalId: undefined,
    version: undefined,
    createTime: undefined,
  }
  formRef.value?.resetFields()
}

/** 删除按钮操作 */
const handleDelete = async (id: string) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await AssetApi.deleteAsset(id)
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
    const data = await AssetApi.exportAsset(queryParams)
    download.excel(data, '资产.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 多选框选择的ID集合 **/
const selectedIds = ref<string[]>([])

/** 多选框选择变化时的处理 **/
const handleSelectionChange = (selection: AssetVO[]) => {
  selectedIds.value = selection.map(item => item.id)
}

/** 批量删除按钮操作 **/
const handleBatchDelete = async () => {
  if (selectedIds.value.length === 0) {
    message.warning('请选择要删除的数据')
    return
  }
  try {
    await message.delConfirm(`确认删除选中的 ${selectedIds.value.length} 条数据吗？`)
    await AssetApi.deleteAssetList(selectedIds.value)
    message.success(t('common.delSuccess'))
    await getList()
    selectedIds.value = [] // 清空选中状态
  } catch {
    // 用户取消操作
  }
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'createTime') {
    // 设置排序参数
    queryParams.orderByColumn = 'createTime'
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