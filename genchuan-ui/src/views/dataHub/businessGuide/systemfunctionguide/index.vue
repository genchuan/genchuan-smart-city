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
      <el-form-item label="指南ID" prop="systemFunctionGuideId">
        <el-input
          v-model="queryParams.systemFunctionGuideId"
          placeholder="请输入指南ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="指南标题" prop="guideTitle">
        <el-input
          v-model="queryParams.guideTitle"
          placeholder="请输入指南标题"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联功能ID" prop="relatedFunctionId">
        <el-input
          v-model="queryParams.relatedFunctionId"
          placeholder="请输入关联功能ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联功能名称" prop="relatedFunctionName">
        <el-input
          v-model="queryParams.relatedFunctionName"
          placeholder="请输入关联功能名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="功能用途说明" prop="functionPurpose">
        <el-input
          v-model="queryParams.functionPurpose"
          placeholder="请输入功能用途说明"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="操作步骤" prop="operSteps">
        <el-input
          v-model="queryParams.operSteps"
          placeholder="请输入操作步骤"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="常见问题及解决方案" prop="commonProblems">
        <el-input
          v-model="queryParams.commonProblems"
          placeholder="请输入常见问题及解决方案"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="适用角色" prop="applicableRole">
        <el-input
          v-model="queryParams.applicableRole"
          placeholder="请输入适用角色"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="指南附件ID" prop="guideAttachId">
        <el-input
          v-model="queryParams.guideAttachId"
          placeholder="请输入指南附件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="指南附件路径" prop="guideAttachPath">
        <el-input
          v-model="queryParams.guideAttachPath"
          placeholder="请输入指南附件路径"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="指南状态" prop="guideStatus">
        <el-select
          v-model="queryParams.guideStatus"
          placeholder="请选择指南状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入创建人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input
          v-model="queryParams.updateUser"
          placeholder="请输入更新人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input
          v-model="queryParams.extCat1"
          placeholder="请输入分类扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input
          v-model="queryParams.extCat2"
          placeholder="请输入分类扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input
          v-model="queryParams.extCommon1"
          placeholder="请输入通用扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input
          v-model="queryParams.extCommon2"
          placeholder="请输入通用扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="queryParams.createTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统创建时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="queryParams.updateTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统更新时间"
          clearable
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
          v-hasPermi="['datacenter:system-function-guide:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:system-function-guide:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="指南ID" align="center" prop="systemFunctionGuideId" />
      <el-table-column label="指南标题" align="center" prop="guideTitle" />
      <el-table-column label="关联功能ID" align="center" prop="relatedFunctionId" />
      <el-table-column label="关联功能名称" align="center" prop="relatedFunctionName" />
      <el-table-column label="功能用途说明" align="center" prop="functionPurpose" />
      <el-table-column label="操作步骤" align="center" prop="operSteps" />
      <el-table-column label="常见问题及解决方案" align="center" prop="commonProblems" />
      <el-table-column label="适用角色" align="center" prop="applicableRole" />
      <el-table-column label="指南附件ID" align="center" prop="guideAttachId" />
      <el-table-column label="指南附件路径" align="center" prop="guideAttachPath" />
      <el-table-column label="指南状态" align="center" prop="guideStatus" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:system-function-guide:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:system-function-guide:delete']"
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
  <SystemFunctionGuideForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { SystemFunctionGuideApi, SystemFunctionGuideVO } from '@/api/dataHub/businessGuide/systemfunctionguide'
import SystemFunctionGuideForm from './SystemFunctionGuideForm.vue'

/** 系统功能指南 列表 */
defineOptions({ name: 'SystemFunctionGuide' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<SystemFunctionGuideVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  systemFunctionGuideId: undefined,
  guideTitle: undefined,
  relatedFunctionId: undefined,
  relatedFunctionName: undefined,
  functionPurpose: undefined,
  operSteps: undefined,
  commonProblems: undefined,
  applicableRole: undefined,
  guideAttachId: undefined,
  guideAttachPath: undefined,
  guideStatus: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await SystemFunctionGuideApi.getSystemFunctionGuidePage(queryParams)
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
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await SystemFunctionGuideApi.deleteSystemFunctionGuide(id)
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
    const data = await SystemFunctionGuideApi.exportSystemFunctionGuide(queryParams)
    download.excel(data, '系统功能指南.xls')
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
