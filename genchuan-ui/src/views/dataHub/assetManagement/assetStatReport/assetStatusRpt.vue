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
      <el-form-item label="统计周期名称" prop="statCycleName">
        <el-input
          v-model="queryParams.statCycleName"
          placeholder="请输入统计周期名称"
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
      <el-form-item label="资产类型" prop="assetType">
        <el-select
          v-model="queryParams.assetType"
          placeholder="请选择资产类型"
          clearable
          class="!w-240px"
        >
          <el-option 
            v-for="item in OptionsAll.assetTypeOptions" 
            :key="item.value" 
            :label="item.label" 
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select
          v-model="queryParams.assetStatus"
          placeholder="请选择资产状态"
          clearable
          class="!w-240px"
        >
          <el-option 
            v-for="item in OptionsAll.assetStatusOptions" 
            :key="item.value" 
            :label="item.label" 
            :value="item.value"
          />
        </el-select>
      </el-form-item>
     
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['assetStatReport:assetStatusRpt:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['assetStatReport:assetStatusRpt:export']"
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
      <el-table-column label="统计ID" align="center" prop="assetStatusStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划代码" align="center" prop="regionCode" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" />
      <el-table-column label="资产类型" align="center" prop="assetType">
        <template #default="scope">
          {{ getAssetTypeLabel(scope.row.assetType) }}
        </template>
      </el-table-column>
      <el-table-column label="资产状态" align="center" prop="assetStatus">
        <template #default="scope">
          {{ getAssetStatusLabel(scope.row.assetStatus) }}
        </template>
      </el-table-column>
      <el-table-column label="资产数量" align="center" prop="assetCount" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="占比" align="center" prop="assetRatio" sortable="custom" :sort-orders="['ascending', 'descending']" />
      <el-table-column label="故障资产主要原因" align="center" prop="faultReason" />
      <el-table-column label="维护优先级" align="center" prop="mntPriority" sortable="custom" :sort-orders="['ascending', 'descending']" />
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
            v-hasPermi="['assetStatReport:assetStatusRpt:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetStatReport:assetStatusRpt:delete']"
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
      <el-form-item label="资产类型" prop="assetType">
        <el-select v-model="formData.assetType" placeholder="请选择资产类型">
          <el-option 
            v-for="item in OptionsAll.assetTypeOptions" 
            :key="item.value" 
            :label="item.label" 
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select v-model="formData.assetStatus" placeholder="请选择资产状态">
          <el-option 
            v-for="item in OptionsAll.assetStatusOptions" 
            :key="item.value" 
            :label="item.label" 
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="资产数量" prop="assetCount">
        <el-input v-model="formData.assetCount" placeholder="请输入资产数量" />
      </el-form-item>
      <el-form-item label="占比" prop="assetRatio">
        <el-input v-model="formData.assetRatio" placeholder="请输入占比" />
      </el-form-item>
      <el-form-item label="故障资产主要原因" prop="faultReason">
        <el-input v-model="formData.faultReason" placeholder="请输入故障资产主要原因" />
      </el-form-item>
      <el-form-item label="维护优先级" prop="mntPriority">
        <el-select v-model="formData.mntPriority" placeholder="请选择维护优先级">
           <el-option
            v-for="item in OptionsAll.mntPriorityOptions" 
            :key="item.value" 
            :label="item.label"
            :value="item.value" />
        </el-select>
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
import { AssetStatusRptApi, AssetStatusRptVO } from '@/api/dataHub/assetManagement/assetStatReport/assetStatusRpt'

/** 资产状态统计 列表 */
defineOptions({ name: 'AssetStatusRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetStatusRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetStatusStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  assetType: undefined,
  assetStatus: undefined,
  assetCount: undefined,
  assetRatio: undefined,
  faultReason: undefined,
  mntPriority: undefined,
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
  isAsc: undefined as 'asc' | 'desc' | undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetStatusRptApi.getAssetStatusRptPage(queryParams)
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

/** 表单弹窗相关 */
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  assetStatusStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  assetType: undefined,
  assetStatus: undefined,
  assetCount: undefined,
  assetRatio: undefined,
  faultReason: undefined,
  mntPriority: undefined,
  statTime: undefined,
  statUser: undefined,
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})
const formRules = reactive({
  assetStatusStatId: [{ required: true, message: '统计ID不能为空', trigger: 'blur' }],
  statCycle: [{ required: true, message: '统计周期不能为空', trigger: 'blur' }],
  statCycleName: [{ required: true, message: '统计周期名称不能为空', trigger: 'blur' }],
  regionCode: [{ required: true, message: '行政区划代码不能为空', trigger: 'blur' }],
  regionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'blur' }],
  assetType: [{ required: true, message: '资产类型不能为空', trigger: 'change' }],
  assetStatus: [{ required: true, message: '资产状态不能为空', trigger: 'change' }],
  assetCount: [{ required: true, message: '资产数量不能为空', trigger: 'blur' }],
  assetRatio: [{ required: true, message: '占比不能为空', trigger: 'blur' }],
  statTime: [{ required: true, message: '统计时间不能为空', trigger: 'change' }],
  statUser: [{ required: true, message: '统计人不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

// 所有选项集合
const OptionsAll = ref({
  // 资产类型
  assetTypeOptions: [
    {label: '市政', value: 'municipal'},
    {label: '交通', value: 'traf'}
  ],
  // 资产状态
  assetStatusOptions: [
    {label: '启用', value: 'enabled'},
    {label: '禁用', value: 'disabled'},
    {label: '故障', value: 'fault'}
  ],
  // 维护优先级
  mntPriorityOptions: [
    {label: '高', value: 'high'},
    {label: '中', value: 'middle'},
    {label: '低', value: 'low'}
  ]
})

/** 获取资产类型标签 */
const getAssetTypeLabel = (value: string) => {
  const option = OptionsAll.value.assetTypeOptions.find(item => item.value === value)
  return option ? option.label : value
}

/** 获取资产状态标签 */
const getAssetStatusLabel = (value: string) => {
  const option = OptionsAll.value.assetStatusOptions.find(item => item.value === value)
  return option ? option.label : value
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
      const data = await AssetStatusRptApi.getAssetStatusRpt(id)
      formData.value = {
        ...data,
        // 确保日期格式正确
        statTime: data.statTime ? Number(data.statTime) : undefined
      }
    } finally {
      formLoading.value = false
    }
  }
}

/** 提交表单 */
const submitForm = async () => {
  // 校验表单
  if (!formRef.value) return
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = {
      ...formData.value,
      // 确保数据类型正确
      assetCount: formData.value.assetCount ? Number(formData.value.assetCount) : 0,
      assetRatio: formData.value.assetRatio ? Number(formData.value.assetRatio) : 0,
      // mntPriority: formData.value.mntPriority ? Number(formData.value.mntPriority) : 0
    } as unknown as AssetStatusRptVO
    
    if (formType.value === 'create') {
      await AssetStatusRptApi.createAssetStatusRpt(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetStatusRptApi.updateAssetStatusRpt(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 刷新列表
    await getList()
  } catch (error) {
    console.error('提交表单错误:', error)
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetStatusStatId: undefined,
    statCycle: undefined,
    statCycleName: undefined,
    regionCode: undefined,
    regionName: undefined,
    assetType: undefined,
    assetStatus: undefined,
    assetCount: undefined,
    assetRatio: undefined,
    faultReason: undefined,
    mntPriority: undefined,
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
    await AssetStatusRptApi.deleteAssetStatusRpt(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch (error) {
    console.error('删除错误:', error)
  }
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await AssetStatusRptApi.exportAssetStatusRpt(queryParams)
    download.excel(data, '资产状态统计.xls')
  } catch (error) {
    console.error('导出错误:', error)
  } finally {
    exportLoading.value = false
  }
}

/** 排序变化事件 */
const handleSortChange = (sort: { prop: string; order: string }) => {
  if (sort.prop === 'assetCount') {
    // 资产数量排序
    queryParams.orderByColumn = 'assetCount'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  if (sort.prop === 'mntPriority') {
    // 维护优先级排序
    queryParams.orderByColumn = 'mntPriority'
    queryParams.isAsc = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  if (sort.prop === 'assetRatio') {
    // 资产占比排序
    queryParams.orderByColumn = 'assetRatio'
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