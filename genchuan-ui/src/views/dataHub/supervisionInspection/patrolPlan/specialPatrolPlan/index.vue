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
      <!-- 保留常用搜索字段 -->
      <el-form-item label="计划ID" prop="specPlanId">
        <el-input
          v-model="queryParams.specPlanId"
          placeholder="请输入计划ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划名称" prop="specPlanName">
        <el-input
          v-model="queryParams.specPlanName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划编码" prop="specPlanCode">
        <el-input
          v-model="queryParams.specPlanCode"
          placeholder="请输入计划编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="专项主题" prop="specTopic">
        <el-input
          v-model="queryParams.specTopic"
          placeholder="请输入专项主题"
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
      <el-form-item label="计划状态" prop="planStatus">
        <el-select
          v-model="queryParams.planStatus"
          placeholder="请选择计划状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="执行日期" prop="executeStartDate">
        <el-date-picker
          v-model="queryParams.executeStartDate"
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
      <el-table-column label="计划ID" align="center" prop="specPlanId" width="120" />
      <el-table-column label="计划名称" align="center" prop="specPlanName" min-width="150" />
      <el-table-column label="计划编码" align="center" prop="specPlanCode" width="150" />
      <el-table-column label="专项主题" align="center" prop="specTopic" width="120" />
      <el-table-column label="所属区域" align="center" prop="areaName" width="120" />
      <el-table-column label="关联标准" align="center" prop="stdName" width="150" />
      <el-table-column label="负责人" align="center" prop="personNames" width="150" />
      <el-table-column label="执行开始日期" align="center" prop="executeStartDate" width="160" />
      <el-table-column label="执行结束日期" align="center" prop="executeEndDate" width="160" />
      <el-table-column label="计划状态" align="center" prop="planStatus" width="100">
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
  <InspectSpecPlanForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectSpecPlanDetail v-model:visible="detailVisible" :data="detailData" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectSpecPlanApi,
  InspectSpecPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/specialPatrolPlan'
import InspectSpecPlanForm from './InspectSpecPlanForm.vue'
import InspectSpecPlanDetail from './InspectSpecPlanDetail.vue'

/** 专项巡查计划信息 列表 */
defineOptions({ name: 'InspectSpecPlan' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectSpecPlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  specPlanId: undefined,
  specPlanName: undefined,
  specPlanCode: undefined,
  specTopic: undefined,
  areaCode: undefined,
  areaName: undefined,
  stdId: undefined,
  stdName: undefined,
  personIds: undefined,
  personNames: undefined,
  executeStartDate: [],
  executeEndDate: [],
  dailyExecuteTime: [],
  planStatus: undefined,
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
const detailData = ref<InspectSpecPlanVO>({} as InspectSpecPlanVO)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectSpecPlanApi.getInspectSpecPlanPage(queryParams)
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
const openDetail = (row: InspectSpecPlanVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectSpecPlanApi.deleteInspectSpecPlan(id)
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
    const data = await InspectSpecPlanApi.exportInspectSpecPlan(queryParams)
    download.excel(data, '专项巡查计划信息.xls')
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

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  margin-bottom: 15px;
}

.el-table th {
  background-color: #fafafa;
  font-weight: 500;
}

.el-table tr:hover > td {
  background-color: #f7f8fa !important;
}
</style>
