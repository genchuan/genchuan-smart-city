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
      <el-form-item label="关联任务名称" prop="evalTaskName">
        <el-input
          v-model="queryParams.evalTaskName"
          placeholder="请输入关联任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="评价对象名称" prop="evalObjectName">
        <el-input
          v-model="queryParams.evalObjectName"
          placeholder="请输入评价对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatus">
        <el-select
          v-model="queryParams.auditStatus"
          placeholder="请选择审核状态"
          clearable
          class="!w-200px rounded-md"
        >
          <el-option label="通过" value="APPROVED" />
          <el-option label="待定" value="PENDING" />
          <el-option label="拒绝" value="REJECTED" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker
          v-model="queryParams.auditTime"
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
        <el-table-column label="关联任务名称" align="center" prop="evalTaskName" width="180" />
        <el-table-column label="评价对象名称" align="center" prop="evalObjectName" width="180" />
        <el-table-column label="自动计算结果" align="center" prop="autoCalcResult" width="150" />
        <el-table-column label="自动计算得分" align="center" prop="autoCalcScore" width="120">
          <template #default="scope">
            <span
              class="font-bold"
              :class="{
                'text-green-600': scope.row.auditStatus === 'APPROVED',
                'text-red-600': scope.row.auditStatus === 'REJECTED',
                'text-orange-600': scope.row.auditStatus === 'PENDING'
              }"
              >{{ scope.row.autoCalcScore || 0 }}</span
            >
          </template>
        </el-table-column>
        <el-table-column label="审核状态" align="center" prop="auditStatus" width="120">
          <template #default="scope">
            <el-tag
              :type="
                scope.row.auditStatus === 'APPROVED'
                  ? 'success'
                  : scope.row.auditStatus === 'REJECTED'
                    ? 'danger'
                    : 'warning'
              "
            >
              {{
                scope.row.auditStatus === 'APPROVED'
                  ? '通过'
                  : scope.row.auditStatus === 'REJECTED'
                    ? '拒绝'
                    : '待定'
              }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="审核时间"
          align="center"
          prop="auditTime"
          :formatter="dateFormatter"
          width="180px"
        />
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
  <EvalResultAuditForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalResultAuditDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalResultAuditApi,
  EvalResultAuditVO
} from '@/api/dataHub/comprehensiveEval/evalResultMgmt/evalResultAudit'
import EvalResultAuditForm from './EvalResultAuditForm.vue'
import EvalResultAuditDetailDrawer from './EvalResultAuditDetailDrawer.vue'

/** 结果审核 列表 */
defineOptions({ name: 'EvalResultAudit' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalResultAuditVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evalTaskName: undefined,
  evalObjectName: undefined,
  auditStatus: undefined,
  auditTime: []
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
    const data = await EvalResultAuditApi.getEvalResultAuditPage(queryParams)
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
const openDetail = (row: EvalResultAuditVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalResultAuditApi.deleteEvalResultAudit(id)
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
    const data = await EvalResultAuditApi.exportEvalResultAudit(queryParams)
    download.excel(data, '结果审核.xls')
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
