<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form class="-mb-15px" :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
      <el-form-item label="规则名称" prop="ruleName">
        <el-input v-model="queryParams.ruleName" placeholder="请输入规则名称" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>

      <el-form-item label="编码生成逻辑" prop="codeGenLogic">
        <el-input v-model="queryParams.codeGenLogic" placeholder="请输入编码生成逻辑" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
      </el-form-item>
      <el-form-item label="适用资产领域" prop="assetDom">
        <el-input v-model="queryParams.assetDom" placeholder="请输入适用资产领域" clearable @keyup.enter="handleQuery"
          class="!w-240px" />
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
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker v-model="queryParams.createdTime" value-format="YYYY-MM-DD HH:mm:ss" type="daterange"
          start-placeholder="开始日期" end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]" class="!w-220px" />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" /> 搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" /> 重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')"
          v-hasPermi="['assetRuleAllocation:assetCatRuleCfg:create']">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          v-hasPermi="['assetRuleAllocation:assetCatRuleCfg:export']">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="分类规则ID" align="center" prop="assetCatRuleId" />
      <el-table-column label="规则名称" align="center" prop="ruleName" />
      <el-table-column label="大类编码位数" align="center" prop="majorCodeLength" />
      <el-table-column label="中类编码位数" align="center" prop="midCodeLength" />
      <el-table-column label="小类编码位数" align="center" prop="minorCodeLength" />
      <el-table-column label="编码生成逻辑" align="center" prop="codeGenLogic" />
      <el-table-column label="适用资产领域" align="center" prop="assetDom" />
      <el-table-column label="启用状态" align="center" prop="enableStatus" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column label="创建时间" align="center" prop="createdTime" :formatter="dateFormatter" width="180px" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" :formatter="dateFormatter" width="180px" />

      <el-table-column label="操作" align="center" min-width="250px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row.id)">
            <Icon icon="ep:view" class="mr-2px" /> 详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)"
            v-hasPermi="['assetRuleAllocation:assetCatRuleCfg:update']">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"
            v-hasPermi="['assetRuleAllocation:assetCatRuleCfg:delete']">
            删除
          </el-button>

          <el-button link type="primary" @click="viewRelatedCategories(scope.row.assetCatRuleId)">
            查看关联分类
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
      <el-form-item label="规则名称" prop="ruleName">
        <el-input v-model="formData.ruleName" placeholder="请输入规则名称" />
      </el-form-item>
      <el-form-item label="大类编码位数" prop="majorCodeLength">
        <el-input v-model="formData.majorCodeLength" placeholder="请输入大类编码位数" />
      </el-form-item>
      <el-form-item label="中类编码位数" prop="midCodeLength">
        <el-input v-model="formData.midCodeLength" placeholder="请输入中类编码位数" />
      </el-form-item>
      <el-form-item label="小类编码位数" prop="minorCodeLength">
        <el-input v-model="formData.minorCodeLength" placeholder="请输入小类编码位数" />
      </el-form-item>
      <el-form-item label="编码生成逻辑" prop="codeGenLogic">
        <el-input v-model="formData.codeGenLogic" placeholder="请输入编码生成逻辑" />
      </el-form-item>
      <el-form-item label="适用资产领域" prop="assetDom">
        <el-input v-model="formData.assetDom" placeholder="请输入适用资产领域" />
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

  <!-- 详情抽屉组件 -->
  <assetCatRuleCfgDrawer v-model="detailDrawerVisible" :id="selectedDetailId" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetCatRuleCfgApi, AssetCatRuleCfgVO } from '@/api/dataHub/assetManagement/assetRuleAllocation/assetCatRuleCfg'

import assetCatRuleCfgDrawer from './components/drawer/assetCatRuleCfgDrawer.vue'

/** 资产分类规则配置 列表 */
defineOptions({ name: 'AssetCatRuleCfg' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetCatRuleCfgVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetCatRuleId: undefined,
  ruleName: undefined,
  majorCodeLength: undefined,
  midCodeLength: undefined,
  minorCodeLength: undefined,
  codeGenLogic: undefined,
  assetDom: undefined,
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
  assetCatRuleId: undefined,
  ruleName: undefined,
  majorCodeLength: undefined,
  midCodeLength: undefined,
  minorCodeLength: undefined,
  codeGenLogic: undefined,
  assetDom: undefined,
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
  assetCatRuleId: [{ required: true, message: '分类规则ID不能为空', trigger: 'blur' }],
  ruleName: [{ required: true, message: '规则名称不能为空', trigger: 'blur' }],
  majorCodeLength: [{ required: true, message: '大类编码位数不能为空', trigger: 'blur' }],
  midCodeLength: [{ required: true, message: '中类编码位数不能为空', trigger: 'blur' }],
  minorCodeLength: [{ required: true, message: '小类编码位数不能为空', trigger: 'blur' }],
  codeGenLogic: [{ required: true, message: '编码生成逻辑不能为空', trigger: 'blur' }],
  assetDom: [{ required: true, message: '适用资产领域不能为空', trigger: 'blur' }],
  enableStatus: [{ required: true, message: '启用状态不能为空', trigger: 'change' }],
  createUser: [{ required: true, message: '创建人不能为空', trigger: 'blur' }],
  createdTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }],
})
const formRef = ref() // 表单 Ref

// 详情抽屉相关变量
const detailDrawerVisible = ref(false) // 详情抽屉的显示状态
const selectedDetailId = ref<number>() // 选中的详情ID

/** 所有选项集合 */
const OptionsAll = ref({

  //启用状态
  enableStatusOptions: [
    { label: '启用', value: "1" },
    { label: '禁用', value: "0" }
  ],

})

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetCatRuleCfgApi.getAssetCatRuleCfgPage(queryParams)
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

/** 打开详情抽屉 */
const openDetailDrawer = (id: number) => {
  selectedDetailId.value = id
  detailDrawerVisible.value = true
}

/** 打开表单弹窗 */
const openForm = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await AssetCatRuleCfgApi.getAssetCatRuleCfg(id)
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
    const data = formData.value as unknown as AssetCatRuleCfgVO
    if (formType.value === 'create') {
      await AssetCatRuleCfgApi.createAssetCatRuleCfg(data)
      message.success(t('common.createSuccess'))
    } else {
      await AssetCatRuleCfgApi.updateAssetCatRuleCfg(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    getList()
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    assetCatRuleId: undefined,
    ruleName: undefined,
    majorCodeLength: undefined,
    midCodeLength: undefined,
    minorCodeLength: undefined,
    codeGenLogic: undefined,
    assetDom: undefined,
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
    await AssetCatRuleCfgApi.deleteAssetCatRuleCfg(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch { }
}

/**查看关联分类*/
import { useRouter } from 'vue-router'

const router = useRouter()

const viewRelatedCategories = (ruleId: string) => {
  router.push({
    name: 'AssetCatMng',
    query: {
      relCatRuleId: ruleId
    }
  })
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await AssetCatRuleCfgApi.exportAssetCatRuleCfg(queryParams)
    download.excel(data, '资产分类规则配置.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>