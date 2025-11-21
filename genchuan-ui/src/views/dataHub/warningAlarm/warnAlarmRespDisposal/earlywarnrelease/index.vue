<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
    >
      <el-form-item label="解除ID" prop="releaseId">
        <el-input
          v-model="queryParams.releaseId"
          placeholder="请输入解除ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警ID" prop="earlyWarnId">
        <el-input
          v-model="queryParams.earlyWarnId"
          placeholder="请输入预警ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警名称" prop="earlyWarnName">
        <el-input
          v-model="queryParams.earlyWarnName"
          placeholder="请输入预警名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="解除状态" prop="releaseStatus">
        <el-select
          v-model="queryParams.releaseStatus"
          placeholder="请选择解除状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker
          v-model="queryParams.applyTime"
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
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="解除ID" align="center" prop="releaseId" />
      <el-table-column label="预警ID" align="center" prop="earlyWarnId" />
      <el-table-column label="预警名称" align="center" prop="earlyWarnName" min-width="150" />
      <el-table-column label="申请人" align="center" min-width="120">
        <template #default="scope">
          {{ scope.row.applyUserName }}({{ scope.row.applyUserId }})
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" min-width="120">
        <template #default="scope">
          {{ scope.row.auditUserName }}({{ scope.row.auditUserId }})
        </template>
      </el-table-column>
      <el-table-column label="解除状态" align="center" prop="releaseStatus">
        <template #default="scope">
          <span
            class="status-tag"
            :class="scope.row.releaseStatus === 'APPROVED' ? 'status-success' : 'status-danger'"
          >
            {{ scope.row.releaseStatus || '-' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="申请时间"
        align="center"
        prop="applyTime"
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
      class="pagination-container"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnReleaseForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnReleaseDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnReleaseApi,
  EarlyWarnReleaseVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnrelease'
import EarlyWarnReleaseForm from './EarlyWarnReleaseForm.vue'
import EarlyWarnReleaseDetail from './EarlyWarnReleaseDetail.vue'

/** 预警告警解除 列表 */
defineOptions({ name: 'EarlyWarnRelease' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnReleaseVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  releaseId: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  releaseStatus: undefined,
  applyTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnReleaseApi.getEarlyWarnReleasePage(queryParams)
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

/** 查看详情操作 */
const detailRef = ref()
const openDetail = (row: EarlyWarnReleaseVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnReleaseApi.deleteEarlyWarnRelease(id)
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
    const data = await EarlyWarnReleaseApi.exportEarlyWarnRelease(queryParams)
    download.excel(data, '预警告警解除.xls')
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
  border: 1px solid #f0f2f5;
}

.status-tag {
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-success {
  background-color: #f0f9eb;
  color: #52c41a;
}

.status-danger {
  background-color: #fff1f0;
  color: #f5222d;
}

.pagination-container {
  margin-top: 16px;
  text-align: right;
}
</style>
