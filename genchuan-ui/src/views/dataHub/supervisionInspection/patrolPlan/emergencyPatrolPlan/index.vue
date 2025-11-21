<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <!-- 只保留常用搜索字段，隐藏部分不常用字段 -->
      <el-form-item label="计划名称" prop="emerPlanName">
        <el-input
          v-model="queryParams.emerPlanName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划编码" prop="emerPlanCode">
        <el-input
          v-model="queryParams.emerPlanCode"
          placeholder="请输入计划编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="应急主题" prop="emerTopic">
        <el-input
          v-model="queryParams.emerTopic"
          placeholder="请输入应急主题"
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
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="执行时间" prop="executeTime">
        <el-date-picker
          v-model="queryParams.executeTime"
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
      <el-table-column label="计划名称" align="center" prop="emerPlanName" min-width="150" />
      <el-table-column label="计划编码" align="center" prop="emerPlanCode" min-width="120" />
      <el-table-column label="应急主题" align="center" prop="emerTopic" min-width="120" />
      <el-table-column label="所属区域" align="center" min-width="120">
        <template #default="scope">{{ scope.row.areaName || '-' }}</template>
      </el-table-column>
      <el-table-column
        label="执行时间"
        align="center"
        prop="executeTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="计划状态" align="center" prop="planStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.planStatus ? 'success' : 'danger'">
            {{ scope.row.planStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createUser" width="100" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="bizCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看</el-button>
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
  <InspectEmerPlanForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <InspectEmerPlanDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectEmerPlanApi,
  InspectEmerPlanVO
} from '@/api/dataHub/supervisionInspection/patrolPlan/emergencyPatrolPlan'
import InspectEmerPlanForm from './InspectEmerPlanForm.vue'
import InspectEmerPlanDetail from './InspectEmerPlanDetail.vue'

/** 应急巡查计划信息 列表 */
defineOptions({ name: 'InspectEmerPlan' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectEmerPlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  emerPlanId: undefined,
  emerPlanName: undefined,
  emerPlanCode: undefined,
  emerTopic: undefined,
  planStatus: undefined,
  executeTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectEmerPlanApi.getInspectEmerPlanPage(queryParams)
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

/** 查看详情 */
const detailRef = ref()
const openDetail = (row: InspectEmerPlanVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectEmerPlanApi.deleteInspectEmerPlan(id)
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
    const data = await InspectEmerPlanApi.exportInspectEmerPlan(queryParams)
    download.excel(data, '应急巡查计划信息.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  background-color: #f9fafb !important;
  font-weight: 500;
}
</style>
