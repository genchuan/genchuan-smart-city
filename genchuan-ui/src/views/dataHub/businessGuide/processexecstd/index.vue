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
      <el-form-item label="规范ID" prop="processExecStdId">
        <el-input
          v-model="queryParams.processExecStdId"
          placeholder="请输入规范ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="规范名称" prop="stdName">
        <el-input
          v-model="queryParams.stdName"
          placeholder="请输入规范名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联流程ID" prop="relatedProcessId">
        <el-input
          v-model="queryParams.relatedProcessId"
          placeholder="请输入关联流程ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联流程名称" prop="relatedProcessName">
        <el-input
          v-model="queryParams.relatedProcessName"
          placeholder="请输入关联流程名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="流程步骤" prop="processSteps">
        <el-input
          v-model="queryParams.processSteps"
          placeholder="请输入流程步骤"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="责任部门代码" prop="deptCode">
        <el-input
          v-model="queryParams.deptCode"
          placeholder="请输入责任部门代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="责任部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入责任部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="时限要求" prop="timeLimit">
        <el-input
          v-model="queryParams.timeLimit"
          placeholder="请输入时限要求"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执行要求" prop="execRequirement">
        <el-input
          v-model="queryParams.execRequirement"
          placeholder="请输入执行要求"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="规范文件ID" prop="stdFileId">
        <el-input
          v-model="queryParams.stdFileId"
          placeholder="请输入规范文件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="规范文件路径" prop="stdFilePath">
        <el-input
          v-model="queryParams.stdFilePath"
          placeholder="请输入规范文件路径"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="规范状态" prop="stdStatus">
        <el-select
          v-model="queryParams.stdStatus"
          placeholder="请选择规范状态"
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
          v-hasPermi="['datacenter:process-exec-std:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:process-exec-std:export']"
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
      <el-table-column label="规范ID" align="center" prop="processExecStdId" />
      <el-table-column label="规范名称" align="center" prop="stdName" />
      <el-table-column label="关联流程ID" align="center" prop="relatedProcessId" />
      <el-table-column label="关联流程名称" align="center" prop="relatedProcessName" />
      <el-table-column label="流程步骤" align="center" prop="processSteps" />
      <el-table-column label="责任部门代码" align="center" prop="deptCode" />
      <el-table-column label="责任部门名称" align="center" prop="deptName" />
      <el-table-column label="时限要求" align="center" prop="timeLimit" />
      <el-table-column label="执行要求" align="center" prop="execRequirement" />
      <el-table-column label="规范文件ID" align="center" prop="stdFileId" />
      <el-table-column label="规范文件路径" align="center" prop="stdFilePath" />
      <el-table-column label="规范状态" align="center" prop="stdStatus" />
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
            v-hasPermi="['datacenter:process-exec-std:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:process-exec-std:delete']"
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
  <ProcessExecStdForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { ProcessExecStdApi, ProcessExecStdVO } from '@/api/dataHub/businessGuide/processexecstd'
import ProcessExecStdForm from './ProcessExecStdForm.vue'

/** 流程执行规范 列表 */
defineOptions({ name: 'ProcessExecStd' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ProcessExecStdVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  processExecStdId: undefined,
  stdName: undefined,
  relatedProcessId: undefined,
  relatedProcessName: undefined,
  processSteps: undefined,
  deptCode: undefined,
  deptName: undefined,
  timeLimit: undefined,
  execRequirement: undefined,
  stdFileId: undefined,
  stdFilePath: undefined,
  stdStatus: undefined,
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
    const data = await ProcessExecStdApi.getProcessExecStdPage(queryParams)
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
    await ProcessExecStdApi.deleteProcessExecStd(id)
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
    const data = await ProcessExecStdApi.exportProcessExecStd(queryParams)
    download.excel(data, '流程执行规范.xls')
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
