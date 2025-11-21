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
      <el-form-item label="法规ID" prop="localRegulationId">
        <el-input
          v-model="queryParams.localRegulationId"
          placeholder="请输入法规ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="法规标题" prop="regulationTitle">
        <el-input
          v-model="queryParams.regulationTitle"
          placeholder="请输入法规标题"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="法规文号" prop="regulationDocNo">
        <el-input
          v-model="queryParams.regulationDocNo"
          placeholder="请输入法规文号"
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
      <el-form-item label="发布部门代码" prop="issueDeptCode">
        <el-input
          v-model="queryParams.issueDeptCode"
          placeholder="请输入发布部门代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="发布部门名称" prop="issueDeptName">
        <el-input
          v-model="queryParams.issueDeptName"
          placeholder="请输入发布部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="issueTime">
        <el-date-picker
          v-model="queryParams.issueTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="实施时间" prop="implementTime">
        <el-date-picker
          v-model="queryParams.implementTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="法规文件ID" prop="regulationFileId">
        <el-input
          v-model="queryParams.regulationFileId"
          placeholder="请输入法规文件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="法规文件路径" prop="regulationFilePath">
        <el-input
          v-model="queryParams.regulationFilePath"
          placeholder="请输入法规文件路径"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="法规状态" prop="regulationStatus">
        <el-select
          v-model="queryParams.regulationStatus"
          placeholder="请选择法规状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="法规摘要" prop="regulationSummary">
        <el-input
          v-model="queryParams.regulationSummary"
          placeholder="请输入法规摘要"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
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
          v-hasPermi="['datacenter:local-regulation:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:local-regulation:export']"
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
      <el-table-column label="法规ID" align="center" prop="localRegulationId" />
      <el-table-column label="法规标题" align="center" prop="regulationTitle" />
      <el-table-column label="法规文号" align="center" prop="regulationDocNo" />
      <el-table-column label="行政区划代码" align="center" prop="regionCode" />
      <el-table-column label="行政区划名称" align="center" prop="regionName" />
      <el-table-column label="发布部门代码" align="center" prop="issueDeptCode" />
      <el-table-column label="发布部门名称" align="center" prop="issueDeptName" />
      <el-table-column label="发布时间" align="center" prop="issueTime" />
      <el-table-column label="实施时间" align="center" prop="implementTime" />
      <el-table-column label="法规文件ID" align="center" prop="regulationFileId" />
      <el-table-column label="法规文件路径" align="center" prop="regulationFilePath" />
      <el-table-column label="法规状态" align="center" prop="regulationStatus" />
      <el-table-column label="法规摘要" align="center" prop="regulationSummary" />
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
            v-hasPermi="['datacenter:local-regulation:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:local-regulation:delete']"
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
  <LocalRegulationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { LocalRegulationApi, LocalRegulationVO } from '@/api/dataHub/businessGuide/localregulation'
import LocalRegulationForm from './LocalRegulationForm.vue'

/** 地方法规信息 列表 */
defineOptions({ name: 'LocalRegulation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<LocalRegulationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  localRegulationId: undefined,
  regulationTitle: undefined,
  regulationDocNo: undefined,
  regionCode: undefined,
  regionName: undefined,
  issueDeptCode: undefined,
  issueDeptName: undefined,
  issueTime: [],
  implementTime: [],
  regulationFileId: undefined,
  regulationFilePath: undefined,
  regulationStatus: undefined,
  regulationSummary: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await LocalRegulationApi.getLocalRegulationPage(queryParams)
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
    await LocalRegulationApi.deleteLocalRegulation(id)
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
    const data = await LocalRegulationApi.exportLocalRegulation(queryParams)
    download.excel(data, '地方法规信息.xls')
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
