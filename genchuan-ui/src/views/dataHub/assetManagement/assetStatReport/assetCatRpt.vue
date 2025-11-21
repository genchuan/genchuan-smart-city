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
      
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input
          v-model="queryParams.regionCode"
          placeholder="请输入行政区划代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input
          v-model="queryParams.regionName"
          placeholder="请输入行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
     
      <el-form-item label="资产大类名称" prop="assetMajorName">
        <el-input
          v-model="queryParams.assetMajorName"
          placeholder="请输入资产大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      
      <el-form-item label="资产中类名称" prop="assetMidName">
        <el-input
          v-model="queryParams.assetMidName"
          placeholder="请输入资产中类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
     
      <el-form-item label="资产小类名称" prop="assetMinorName">
        <el-input
          v-model="queryParams.assetMinorName"
          placeholder="请输入资产小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['assetStatReport:assetCatRpt:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['assetStatReport:assetCatRpt:export']"
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
      <el-table-column label="统计ID" align="center" prop="assetCatStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划代码" align="center" prop="regionCode" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" />
      <el-table-column label="资产大类ID" align="center" prop="assetMajorId" />
      <el-table-column label="资产大类名称" align="center" prop="assetMajorName" />
      <el-table-column label="资产中类ID" align="center" prop="assetMidId" />
      <el-table-column label="资产中类名称" align="center" prop="assetMidName" />
      <el-table-column label="资产小类ID" align="center" prop="assetMinorId" />
      <el-table-column label="资产小类名称" align="center" prop="assetMinorName" />
      <el-table-column label="资产总数" align="center" prop="totalAssetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="启用资产数" align="center" prop="enabledAssetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="禁用资产数" align="center" prop="disabledAssetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="新增资产数" align="center" prop="newAssetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="更新资产数" align="center" prop="updateAssetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom" :sort-orders="['ascending', 'descending']"
      />
      <el-table-column label="统计人" align="center" prop="statUser" />
      <el-table-column label="报表备注" align="center" prop="rptRemark" />
   
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetStatReport:assetCatRpt:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetStatReport:assetCatRpt:delete']"
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

      <el-form-item label="统计周期" prop="statCycle">
        <el-input v-model="formData.statCycle" placeholder="请输入统计周期" />
      </el-form-item>
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input v-model="formData.statCycleName" placeholder="请输入统计周期名称" />
      </el-form-item>
      <el-form-item label="行政区划代码" prop="regionCode">
        <el-input v-model="formData.regionCode" placeholder="请输入行政区划代码" />
      </el-form-item>
      <el-form-item label="行政区划名称" prop="regionName">
        <el-input v-model="formData.regionName" placeholder="请输入行政区划名称" />
      </el-form-item>
      <el-form-item label="资产大类ID" prop="assetMajorId">
        <el-input v-model="formData.assetMajorId" placeholder="请输入资产大类ID" />
      </el-form-item>
      <el-form-item label="资产大类名称" prop="assetMajorName">
        <el-input v-model="formData.assetMajorName" placeholder="请输入资产大类名称" />
      </el-form-item>
      <el-form-item label="资产中类ID" prop="assetMidId">
        <el-input v-model="formData.assetMidId" placeholder="请输入资产中类ID" />
      </el-form-item>
      <el-form-item label="资产中类名称" prop="assetMidName">
        <el-input v-model="formData.assetMidName" placeholder="请输入资产中类名称" />
      </el-form-item>
      <el-form-item label="资产小类ID" prop="assetMinorId">
        <el-input v-model="formData.assetMinorId" placeholder="请输入资产小类ID" />
      </el-form-item>
      <el-form-item label="资产小类名称" prop="assetMinorName">
        <el-input v-model="formData.assetMinorName" placeholder="请输入资产小类名称" />
      </el-form-item>
      <el-form-item label="资产总数" prop="totalAssetCount">
        <el-input v-model="formData.totalAssetCount" placeholder="请输入资产总数" />
      </el-form-item>
      <el-form-item label="启用资产数" prop="enabledAssetCount">
        <el-input v-model="formData.enabledAssetCount" placeholder="请输入启用资产数" />
      </el-form-item>
      <el-form-item label="禁用资产数" prop="disabledAssetCount">
        <el-input v-model="formData.disabledAssetCount" placeholder="请输入禁用资产数" />
      </el-form-item>
      <el-form-item label="新增资产数" prop="newAssetCount">
        <el-input v-model="formData.newAssetCount" placeholder="请输入新增资产数" />
      </el-form-item>
      <el-form-item label="更新资产数" prop="updateAssetCount">
        <el-input v-model="formData.updateAssetCount" placeholder="请输入更新资产数" />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="formData.statTime"
          type="date"
          value-format="x"
          placeholder="选择统计时间"
        />
      </el-form-item>
      <el-form-item label="统计人" prop="statUser">
        <el-input v-model="formData.statUser" placeholder="请输入统计人" />
      </el-form-item>
      <el-form-item label="报表备注" prop="rptRemark">
        <el-input v-model="formData.rptRemark" placeholder="请输入报表备注" />
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
import { AssetCatRptApi, AssetCatRptVO } from '@/api/dataHub/assetManagement/assetStatReport/assetCatRpt'

/** 资产分类统计 列表 */
defineOptions({ name: 'AssetCatRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetCatRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetCatStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  assetMajorId: undefined,
  assetMajorName: undefined,
  assetMidId: undefined,
  assetMidName: undefined,
  assetMinorId: undefined,
  assetMinorName: undefined,
  totalAssetCount: undefined,
  enabledAssetCount: undefined,
  disabledAssetCount: undefined,
  newAssetCount: undefined,
  updateAssetCount: undefined,
  statTime: [],
  statUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: [],
  // 排序参数
  orderByColumn: undefined as string | undefined,
  isAsc: undefined as 'asc' | 'desc' | undefined,

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
  assetCatStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  assetMajorId: undefined,
  assetMajorName: undefined,
  assetMidId: undefined,
  assetMidName: undefined,
  assetMinorId: undefined,
  assetMinorName: undefined,
  totalAssetCount: undefined,
  enabledAssetCount: undefined,
  disabledAssetCount: undefined,
  newAssetCount: undefined,
  updateAssetCount: undefined,
  statTime: undefined,
  statUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  assetCatStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  assetMajorId: [{ required: true, message: '资产大类ID不能为空', trigger: 'blur' }],
  assetMajorName: [{ required: true, message: '资产大类名称不能为空', trigger: 'blur' }],
  totalAssetCount: [{ required: true, message: '资产总数不能为空', trigger: 'blur' }],
  enabledAssetCount: [{ required: true, message: '启用资产数不能为空', trigger: 'blur' }],
  disabledAssetCount: [{ required: true, message: '禁用资产数不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'blur' }],
  statUser: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetCatRptApi.getAssetCatRptPage(queryParams)
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
const openForm = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetCatRptApi.getAssetCatRpt(id)
    } finally {
      formLoading.value = false
    }
  }
}

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as AssetCatRptVO
    if (formType.value === 'create') {
      await AssetCatRptApi.createAssetCatRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetCatRptApi.updateAssetCatRpt(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
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
    assetCatStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    assetMajorId: undefined,
    assetMajorName: undefined,
    assetMidId: undefined,
    assetMidName: undefined,
    assetMinorId: undefined,
    assetMinorName: undefined,
    totalAssetCount: undefined,
    enabledAssetCount: undefined,
    disabledAssetCount: undefined,
    newAssetCount: undefined,
    updateAssetCount: undefined,
    statTime: undefined,
    statUser: undefined,
    rptRemark: undefined,
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
    await AssetCatRptApi.deleteAssetCatRpt(id)
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
    const data = await AssetCatRptApi.exportAssetCatRpt(queryParams)
    download.excel(data, '资产分类统计.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'totalAssetCount') {
    // 资产总数排序
    queryParams.orderByColumn = 'totalAssetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'enabledAssetCount') {
    // 启用资产总数排序
    queryParams.orderByColumn = 'enabledAssetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'disabledAssetCount') {
    // 禁用资产总数排序
    queryParams.orderByColumn = 'disabledAssetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'newAssetCount') {
    // 新增资产总数排序
    queryParams.orderByColumn = 'newAssetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'updateAssetCount') {
    // 更新资产总数排序
    queryParams.orderByColumn = 'updateAssetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  } 
  if (sort.prop === 'statTime') {
    // 统计时间排序
    queryParams.orderByColumn = 'statTime'
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