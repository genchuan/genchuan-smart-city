<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="80px"
    >
      <el-form-item label="计划名称" prop="dailyPlanName">
        <el-input
          v-model="queryParams.dailyPlanName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划编码" prop="dailyPlanCode">
        <el-input
          v-model="queryParams.dailyPlanCode"
          placeholder="请输入计划编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属区域" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入所属区域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入负责人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划状态" prop="planStatus">
        <el-select
          v-model="queryParams.planStatus"
          placeholder="请选择计划状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="true" />
          <el-option label="禁用" value="false" />
        </el-select>
      </el-form-item>
      <el-form-item label="执行时间" prop="executeStartTime">
        <el-date-picker
          v-model="queryParams.executeStartTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="计划名称" align="center" prop="dailyPlanName" min-width="150" />
      <el-table-column label="计划编码" align="center" prop="dailyPlanCode" min-width="120" />
      <el-table-column label="所属区域" align="center" prop="areaName" min-width="120" />
      <el-table-column label="关联路线" align="center" prop="routeName" min-width="120" />
      <el-table-column label="负责人" align="center" prop="personName" min-width="100" />
      <el-table-column label="计划周期" align="center" prop="planCycle" min-width="100" />
      <el-table-column
        label="开始日期"
        align="center"
        prop="startDate"
        min-width="200"
        :formatter="dateFormatter"
      />
      <el-table-column
        label="结束日期"
        align="center"
        prop="endDate"
        min-width="200"
        :formatter="dateFormatter"
      />
      <el-table-column label="计划状态" align="center" prop="planStatus" min-width="100">
        <template #default="scope">
          <el-tag :type="scope.row.planStatus ? 'success' : 'danger'">
            {{ scope.row.planStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="bizCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
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
  <InspectDailyPlanForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectDailyPlanDetail
    v-model:visible="detailVisible"
    :data="detailData"
    @close="detailData = {}"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectDailyPlanApi,
  InspectDailyPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/dailyPatrolPlan'
import InspectDailyPlanForm from './InspectDailyPlanForm.vue'
import InspectDailyPlanDetail from './InspectDailyPlanDetail.vue'

/** 日常巡查计划信息 列表 */
defineOptions({ name: 'InspectDailyPlan' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectDailyPlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  dailyPlanId: undefined,
  dailyPlanName: undefined,
  dailyPlanCode: undefined,
  areaCode: undefined,
  areaName: undefined,
  routeId: undefined,
  routeName: undefined,
  stdId: undefined,
  stdName: undefined,
  personId: undefined,
  personName: undefined,
  planCycle: undefined,
  executeStartTime: [],
  executeEndTime: [],
  planStatus: undefined,
  startDate: [],
  endDate: [],
  createUser: undefined,
  bizCreateTime: [],
  updateUser: undefined,
  bizUpdateTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<InspectDailyPlanVO>({})

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectDailyPlanApi.getInspectDailyPlanPage(queryParams)
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

/** 详情操作 */
const openDetail = (row: InspectDailyPlanVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectDailyPlanApi.deleteInspectDailyPlan(id)
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
    const data = await InspectDailyPlanApi.exportInspectDailyPlan(queryParams)
    download.excel(data, '日常巡查计划信息.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 格式化日期 */
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-table th {
  background-color: #f7f8fa;
  font-weight: 500;
}

.el-table tr:hover > td {
  background-color: #f7faff !important;
}
</style>
