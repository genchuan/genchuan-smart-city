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
      <el-form-item label="计划名称" prop="inspectPlanName">
        <el-input
          v-model="queryParams.inspectPlanName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="计划编码" prop="inspectPlanCode">
        <el-input
          v-model="queryParams.inspectPlanCode"
          placeholder="请输入计划编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="关联任务" prop="evalTaskName">
        <el-input
          v-model="queryParams.evalTaskName"
          placeholder="请输入关联任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="考察对象" prop="evalObjectName">
        <el-input
          v-model="queryParams.evalObjectName"
          placeholder="请输入考察对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="计划状态" prop="planStatus">
        <el-select
          v-model="queryParams.planStatus"
          placeholder="请选择计划状态"
          clearable
          class="!w-200px rounded-md"
        >
          <el-option label="计划中" value="PLANNED" />
          <el-option label="已完成" value="COMPLETED" />
          <el-option label="进行中" value="RUNNING" />
        </el-select>
      </el-form-item>
      <el-form-item label="考察时间" prop="inspectTime">
        <el-date-picker
          v-model="queryParams.inspectTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-300px rounded-md"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
  <ContentWrap class="mt-4">
    <el-card shadow="hover" class="rounded-lg">
      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        class="table-beautify"
        border
      >
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="计划名称" align="center" prop="inspectPlanName" width="180" />
        <el-table-column label="计划编码" align="center" prop="inspectPlanCode" width="150" />
        <el-table-column label="关联任务" align="center" prop="evalTaskName" width="180" />
        <el-table-column label="考察对象" align="center" prop="evalObjectName" width="180" />
        <el-table-column label="考察人员" align="center" prop="inspectorName" width="120" />
        <el-table-column
          label="考察时间"
          align="center"
          prop="inspectTime"
          :formatter="dateFormatter"
          width="180px"
        />
        <el-table-column label="计划状态" align="center" prop="planStatus" width="120">
          <template #default="scope">
            <el-tag
              :type="
                scope.row.planStatus === 'COMPLETED'
                  ? 'success'
                  : scope.row.planStatus === 'RUNNING'
                    ? 'warning'
                    : 'primary'
              "
            >
              {{
                scope.row.planStatus === 'COMPLETED'
                  ? '已完成'
                  : scope.row.planStatus === 'RUNNING'
                    ? '进行中'
                    : '计划中'
              }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="180px">
          <template #default="scope">
            <el-button link type="primary" @click="openDetail(scope.row)">
              <Icon icon="ep:detail" class="mr-2" />
              详情
            </el-button>
            <el-button link type="primary" @click="openForm('update', scope.row.id)">
              <Icon icon="ep:edit" class="mr-2" />
              编辑
            </el-button>
            <el-button link type="danger" @click="handleDelete(scope.row.id)">
              <Icon icon="ep:delete" class="mr-2" />
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-4">
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EvalInspectPlanForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalInspectPlanDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalInspectPlanApi,
  EvalInspectPlanVO
} from '@/api/dataHub/comprehensiveEval/onSiteEval/onSitePlanMgmt'
import EvalInspectPlanForm from './EvalInspectPlanForm.vue'
import EvalInspectPlanDetailDrawer from './EvalInspectPlanDetailDrawer.vue'

/** 考察计划管理 列表 */
defineOptions({ name: 'EvalInspectPlan' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalInspectPlanVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  inspectPlanName: undefined,
  inspectPlanCode: undefined,
  evalTaskName: undefined,
  evalObjectName: undefined,
  planStatus: undefined,
  inspectTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 组件Ref */
const formRef = ref()
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalInspectPlanApi.getEvalInspectPlanPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情操作 */
const openDetail = (row: EvalInspectPlanVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalInspectPlanApi.deleteEvalInspectPlan(id)
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
    const data = await EvalInspectPlanApi.exportEvalInspectPlan(queryParams)
    download.excel(data, '考察计划管理.xls')
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
.table-beautify {
  --el-table-row-hover-bg-color: #f0f9ff;
  --el-table-header-text-color: #333;
  --el-table-header-bg-color: #f8f9fa;
  --el-table-border-color: #e4e7ed;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-form-item {
  margin-bottom: 15px;
}
</style>
