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
      <el-form-item label="评估ID" prop="evalId">
        <el-input
          v-model="queryParams.evalId"
          placeholder="请输入评估ID"
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
      <el-form-item label="派单ID" prop="dispatchId">
        <el-input
          v-model="queryParams.dispatchId"
          placeholder="请输入派单ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估人" prop="evalUserName">
        <el-input
          v-model="queryParams.evalUserName"
          placeholder="请输入评估人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估等级" prop="evalLevel">
        <el-input
          v-model="queryParams.evalLevel"
          placeholder="请输入评估等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估时间" prop="evalTime">
        <el-date-picker
          v-model="queryParams.evalTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" class="search-btn">
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
  <ContentWrap class="table-content">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="评估ID" align="center" prop="evalId" />
      <el-table-column label="预警ID" align="center" prop="earlyWarnId" />
      <el-table-column label="派单ID" align="center" prop="dispatchId" />
      <el-table-column label="评估人" align="center" prop="evalUserName" />
      <el-table-column label="问题解决度得分" align="center" prop="problemSolveScore" />
      <el-table-column label="响应及时性得分" align="center" prop="timelinessScore" />
      <el-table-column label="现场规范性得分" align="center" prop="stdizationScore" />
      <el-table-column label="综合得分" align="center" prop="comprehensiveScore" />
      <el-table-column label="评估等级" align="center" prop="evalLevel" />
      <el-table-column
        label="评估时间"
        align="center"
        prop="evalTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="是否二次处置" align="center" prop="isSecondHandle">
        <template #default="scope">
          <span :class="scope.row.isSecondHandle === '1' ? 'status-success' : 'status-danger'">
            {{ scope.row.isSecondHandle === '1' ? '是' : '否' }}
          </span>
        </template>
      </el-table-column>
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DisposalResultEvalForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DisposalResultEvalDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnHandleEvalApi,
  EarlyWarnHandleEvalVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/disposalresulteval'
import DisposalResultEvalForm from './DisposalResultEvalForm.vue'
import DisposalResultEvalDetail from './DisposalResultEvalDetail.vue'

/** 预警告警处置评估 列表 */
defineOptions({ name: 'EarlyWarnHandleEval' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnHandleEvalVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evalId: undefined,
  earlyWarnId: undefined,
  dispatchId: undefined,
  evalUserId: undefined,
  evalUserName: undefined,
  problemSolveScore: undefined,
  timelinessScore: undefined,
  stdizationScore: undefined,
  comprehensiveScore: undefined,
  evalLevel: undefined,
  evalTime: [],
  evalRemark: undefined,
  isSecondHandle: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnHandleEvalApi.getEarlyWarnHandleEvalPage(queryParams)
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
const detailRef = ref()
const openDetail = (row: EarlyWarnHandleEvalVO) => {
  detailRef.value.open(row)
}

const handleDetailClose = () => {
  // 详情关闭后的处理
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnHandleEvalApi.deleteEarlyWarnHandleEval(id)
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
    const data = await EarlyWarnHandleEvalApi.exportEarlyWarnHandleEval(queryParams)
    download.excel(data, '预警告警处置评估.xls')
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
.search-form {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
  margin-bottom: 15px !important;
}

.search-btn {
  margin-right: 8px;
}

.table-content {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.custom-table {
  border-radius: 6px;
  overflow: hidden;
}

.pagination {
  margin-top: 15px;
  text-align: right;
}

.status-success {
  color: #52c41a;
  background-color: #f6ffed;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-danger {
  color: #f5222d;
  background-color: #fff2f0;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}
</style>
