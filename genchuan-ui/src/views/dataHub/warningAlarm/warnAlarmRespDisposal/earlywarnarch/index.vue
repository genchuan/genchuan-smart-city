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
      <el-form-item label="归档ID" prop="archId">
        <el-input
          v-model="queryParams.archId"
          placeholder="请输入归档ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="归档编号" prop="archNo">
        <el-input
          v-model="queryParams.archNo"
          placeholder="请输入归档编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="预警名称" prop="earlyWarnName">
        <el-input
          v-model="queryParams.earlyWarnName"
          placeholder="请输入预警名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="预警类型" prop="warnType">
        <el-select
          v-model="queryParams.warnType"
          placeholder="请选择预警类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="解除状态" prop="releaseStatus">
        <el-select
          v-model="queryParams.releaseStatus"
          placeholder="请选择解除状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="归档时间" prop="archTime">
        <el-date-picker
          v-model="queryParams.archTime"
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
      <el-table-column label="归档ID" align="center" prop="archId" />
      <el-table-column label="归档编号" align="center" prop="archNo" />
      <el-table-column label="预警ID" align="center" prop="earlyWarnId" />
      <el-table-column label="预警名称" align="center" prop="earlyWarnName" min-width="150" />
      <el-table-column label="预警类型" align="center" prop="warnType" />
      <el-table-column label="预警等级" align="center" prop="warnLevel" />
      <el-table-column label="所在区域" align="center" prop="regionName" />
      <el-table-column label="处置人" align="center" prop="handleUserName" />
      <el-table-column label="综合评估得分" align="center" prop="comprehensiveScore" />
      <el-table-column label="解除状态" align="center" prop="releaseStatus">
        <template #default="scope">
          <span
            :class="scope.row.releaseStatus === 'RELEASED' ? 'status-success' : 'status-danger'"
          >
            {{ scope.row.releaseStatus === 'RELEASED' ? '已解除' : '未解除' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="归档时间"
        align="center"
        prop="archTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="归档人" align="center" prop="archUserName" />
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
  <EarlyWarnArchForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnArchDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnArchApi,
  EarlyWarnArchVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnarch'
import EarlyWarnArchForm from './EarlyWarnArchForm.vue'
import EarlyWarnArchDetail from './EarlyWarnArchDetail.vue'

/** 预警告警处置归档 列表 */
defineOptions({ name: 'EarlyWarnArch' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnArchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  archId: undefined,
  archNo: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  warnType: undefined,
  warnLevel: undefined,
  regionName: undefined,
  handleUserName: undefined,
  comprehensiveScore: undefined,
  releaseStatus: undefined,
  archTime: [],
  archUserId: undefined,
  archUserName: undefined,
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
    const data = await EarlyWarnArchApi.getEarlyWarnArchPage(queryParams)
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
const openDetail = (row: EarlyWarnArchVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnArchApi.deleteEarlyWarnArch(id)
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
    const data = await EarlyWarnArchApi.exportEarlyWarnArch(queryParams)
    download.excel(data, '预警告警处置归档.xls')
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
}

.status-success {
  color: #4cd964;
  font-weight: 500;
}

.status-danger {
  color: #ff3b30;
  font-weight: 500;
}
</style>
