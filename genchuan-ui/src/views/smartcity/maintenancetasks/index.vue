<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="任务编号" prop="taskNumber" label-width="100px">
        <el-input
          v-model="queryParams.taskNumber"
          placeholder="请输入任务编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="任务名称" prop="task">
        <el-input
          v-model="queryParams.task"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="养护地块" prop="maintainTheLandParcel">
        <el-input
          v-model="queryParams.maintainTheLandParcel"
          placeholder="请输入养护地块"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="养护人员" prop="maintenancePersonnel">
        <el-input
          v-model="queryParams.maintenancePersonnel"
          placeholder="请输入养护人员"
          clearable
          @keyup.enter="handleQuery"
          class="!w-140px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:maintenance-tasks:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:maintenance-tasks:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <!--<el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="任务编号" align="center" prop="taskNumber" width="180px" />
      <el-table-column label="任务名称" align="center" prop="task" />
      <el-table-column label="养护地块" align="center" prop="maintainTheLandParcel" />
      <el-table-column label="养护人员" align="center" prop="maintenancePersonnel" />
      <el-table-column
        label="任务开始时间"
        align="center"
        prop="taskStartTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="任务结束时间"
        align="center"
        prop="taskEndTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="任务内容" align="center" prop="taskContent" />
      <el-table-column label="所需工具" align="center" prop="requiredTools" />
      <!--<el-table-column label="所需材料" align="center" prop="requiredMaterials" />-->
      <el-table-column label="任务优先级" align="center" prop="taskPriority" />
      <!--<el-table-column label="任务状态" align="center" prop="status" />-->
      <el-table-column label="完成情况说明" align="center" prop="completionStatusDescription" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:maintenance-tasks:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:maintenance-tasks:delete']"
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
  <MaintenanceTasksForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MaintenanceTasksApi, MaintenanceTasksVO } from '@/api/smartcity/maintenancetasks'
import MaintenanceTasksForm from './MaintenanceTasksForm.vue'

/** 养护任务 列表 */
defineOptions({ name: 'MaintenanceTasks' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MaintenanceTasksVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  taskNumber: undefined,
  task: undefined,
  maintainTheLandParcel: undefined,
  maintenancePersonnel: undefined,
  taskStartTime: [],
  taskEndTime: [],
  taskContent: undefined,
  requiredTools: undefined,
  requiredMaterials: undefined,
  taskPriority: undefined,
  status: undefined,
  completionStatusDescription: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MaintenanceTasksApi.getMaintenanceTasksPage(queryParams)
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
    await MaintenanceTasksApi.deleteMaintenanceTasks(id)
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
    const data = await MaintenanceTasksApi.exportMaintenanceTasks(queryParams)
    download.excel(data, '养护任务.xls')
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
