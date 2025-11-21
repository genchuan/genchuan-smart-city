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
      <el-form-item label="统计ID" prop="warnMonCompStatId">
        <el-input
          v-model="queryParams.warnMonCompStatId"
          placeholder="请输入统计ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件大类" prop="monMajorName">
        <el-input
          v-model="queryParams.monMajorName"
          placeholder="请输入部件大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件小类" prop="monMinorName">
        <el-input
          v-model="queryParams.monMinorName"
          placeholder="请输入部件小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预警总数" prop="totalWarnCount">
        <el-input
          v-model="queryParams.totalWarnCount"
          placeholder="请输入预警总数"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="queryParams.statTime"
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
      <el-table-column label="统计ID" align="center" prop="warnMonCompStatId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="部件大类" align="center" prop="monMajorName" />
      <el-table-column label="部件小类" align="center" prop="monMinorName" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" />
      <el-table-column label="主要触发原因" align="center" prop="mainTriggerReason" />
      <el-table-column label="涉及设备数" align="center" prop="deviceCount" />
      <el-table-column label="已恢复数" align="center" prop="recoveredCount" />
      <el-table-column label="恢复率" align="center" prop="recoveryRate" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="160px"
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
  <EarlyWarnMonCompForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnMonCompDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnMonCompApi,
  EarlyWarnMonCompVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmoncomp'
import EarlyWarnMonCompForm from './EarlyWarnMonCompForm.vue'
import EarlyWarnMonCompDetail from './EarlyWarnMonCompDetail.vue'

/** 按监测部件预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnMonComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnMonCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  warnMonCompStatId: undefined,
  statCycle: undefined,
  monMajorId: undefined,
  monMajorName: undefined,
  monMinorId: undefined,
  monMinorName: undefined,
  totalWarnCount: undefined,
  mainTriggerReason: undefined,
  deviceCount: undefined,
  recoveredCount: undefined,
  recoveryRate: undefined,
  statUserId: undefined,
  statTime: [],
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
    const data = await EarlyWarnMonCompApi.getEarlyWarnMonCompPage(queryParams)
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
const openDetail = (row: EarlyWarnMonCompVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnMonCompApi.deleteEarlyWarnMonComp(id)
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
    const data = await EarlyWarnMonCompApi.exportEarlyWarnMonComp(queryParams)
    download.excel(data, '按监测部件预警告警统计.xls')
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

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
