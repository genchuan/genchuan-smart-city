<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="计划编号" prop="planNumber" label-width="100px">
        <el-input
          v-model="queryParams.planNumber"
          placeholder="请输入计划编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划名称" prop="planName">
        <el-input
          v-model="queryParams.planName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="养护地块" prop="maintainParcel">
        <el-input
          v-model="queryParams.maintainParcel"
          placeholder="请输入养护地块"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
<!--      <el-form-item label="创建时间" prop="createTime">-->
<!--        <el-date-picker-->
<!--          v-model="queryParams.createTime"-->
<!--          value-format="YYYY-MM-DD HH:mm:ss"-->
<!--          type="daterange"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"-->
<!--          class="!w-220px"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:maintenance-plan:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:maintenance-plan:export']"
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
      <el-table-column label="计划编号" align="center" prop="planNumber" />
      <el-table-column label="计划名称" align="center" prop="planName" />
      <el-table-column
        label="计划制定日期"
        align="center"
        prop="planFormulationDate"
        :formatter="dateFormatter2"
      />
      <el-table-column
        label="计划开始日期"
        align="center"
        prop="startDate"
        :formatter="dateFormatter2"
      />
      <el-table-column
        label="计划结束日期"
        align="center"
        prop="plannedEndDate"
        :formatter="dateFormatter2"
      />
      <el-table-column label="负责养护人员" align="center" prop="maintenancePersonnel" />
      <el-table-column label="养护地块" align="center" prop="maintainParcel" />
      <el-table-column label="养护内容" align="center" prop="maintenanceContent" />
      <el-table-column label="养护频率" align="center" prop="maintenanceFrequency" />
      <el-table-column label="养护资源需求" align="center" prop="maintenanceDemand" />
      <!--<el-table-column label="计划状态" align="center" prop="planStatus" />-->
      <el-table-column label="计划预算" align="center" prop="plannedBudget" />
      <!--<el-table-column label="审批人" align="center" prop="approver" />-->
      <!--<el-table-column label="审批意见" align="center" prop="approvalOpinion" />-->
      <!--<el-table-column-->
      <!--  label="审批日期"-->
      <!--  align="center"-->
      <!--  prop="approvalDate"-->
      <!--  :formatter="dateFormatter"-->
      <!--  width="180px"-->
      <!--/>-->
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
            v-hasPermi="['smartcity:maintenance-plan:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:maintenance-plan:delete']"
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
  <MaintenancePlanForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { MaintenancePlanApi, MaintenancePlanVO } from '@/api/smartcity/maintenanceplan'
import MaintenancePlanForm from './MaintenancePlanForm.vue'

/** 养护计划 列表 */
defineOptions({ name: 'MaintenancePlan' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MaintenancePlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  planNumber: undefined,
  planName: undefined,
  maintainParcel: undefined,
  maintenanceContent: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MaintenancePlanApi.getMaintenancePlanPage(queryParams)
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
    await MaintenancePlanApi.deleteMaintenancePlan(id)
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
    const data = await MaintenancePlanApi.exportMaintenancePlan(queryParams)
    download.excel(data, '养护计划.xls')
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
