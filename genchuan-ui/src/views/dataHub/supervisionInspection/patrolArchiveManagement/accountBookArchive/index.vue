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
      <el-form-item label="档案编码" prop="ledgerArchCode">
        <el-input
          v-model="queryParams.ledgerArchCode"
          placeholder="请输入档案编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="档案名称" prop="ledgerArchName">
        <el-input
          v-model="queryParams.ledgerArchName"
          placeholder="请输入档案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="所属区域" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入所属区域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="台账周期" prop="ledgerCycle">
        <el-input
          v-model="queryParams.ledgerCycle"
          placeholder="请输入台账周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="归档时间" prop="ledgerArchTime">
        <el-date-picker
          v-model="queryParams.ledgerArchTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-300px"
        />
      </el-form-item>
      <el-form-item label="档案状态" prop="ledgerArchStatus">
        <el-select
          v-model="queryParams.ledgerArchStatus"
          placeholder="请选择档案状态"
          clearable
          class="!w-200px"
        >
          <el-option label="已归档" value="已归档" />
          <el-option label="待归档" value="待归档" />
        </el-select>
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
  <ContentWrap class="mt-4">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="table-beautify"
      :cell-style="{ 'font-size': '14px' }"
      :header-cell-style="{ 'background-color': '#f5fafe', 'font-weight': 600 }"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="档案编码" align="center" prop="ledgerArchCode" width="120" />
      <el-table-column label="档案名称" align="center" prop="ledgerArchName" width="180" />
      <el-table-column label="所属区域" align="center" prop="areaName" width="150" />
      <el-table-column label="台账周期" align="center" prop="ledgerCycle" width="120" />
      <el-table-column label="归档人" align="center" prop="ledgerArchUser" width="100" />
      <el-table-column
        label="归档时间"
        align="center"
        prop="ledgerArchTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="档案状态" align="center" prop="ledgerArchStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.ledgerArchStatus === '已归档' ? 'success' : 'warning'">
            {{ scope.row.ledgerArchStatus || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="销毁时间"
        align="center"
        prop="ledgerDestroyTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="150px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row)"> 详情</el-button>
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
      class="mt-4"
    />
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <InspectLedgerArchForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <InspectLedgerArchDetailDrawer v-model:visible="detailDrawerVisible" :detail="currentDetail" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectLedgerArchApi,
  InspectLedgerArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/accountBookArchive'
import InspectLedgerArchForm from './InspectLedgerArchForm.vue'
import InspectLedgerArchDetailDrawer from './InspectLedgerArchDetailDrawer.vue'

/** 巡查巡检台账档案 列表 */
defineOptions({ name: 'InspectLedgerArch' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<InspectLedgerArchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailDrawerVisible = ref(false) // 详情抽屉显示状态
const currentDetail = ref<InspectLedgerArchVO | null>(null) // 当前详情数据

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  ledgerArchId: undefined,
  ledgerArchCode: undefined,
  ledgerArchName: undefined,
  areaCode: undefined,
  areaName: undefined,
  ledgerCycle: undefined,
  coreIdxJson: undefined,
  includeArchTypes: undefined,
  ledgerAttachUrl: undefined,
  ledgerArchUser: undefined,
  ledgerArchTime: [],
  ledgerArchStatus: undefined,
  ledgerDestroyTime: [],
  ledgerArchDesc: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取状态标签类型 */
const getStatusTagType = (status?: string) => {
  switch (status) {
    case '1':
      return 'success'
    case '2':
      return 'danger'
    case '3':
      return 'warning'
    default:
      return 'info'
  }
}

/** 打开详情抽屉 */
const openDetailDrawer = (row: InspectLedgerArchVO) => {
  currentDetail.value = { ...row }
  detailDrawerVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectLedgerArchApi.getInspectLedgerArchPage(queryParams)
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
    await InspectLedgerArchApi.deleteInspectLedgerArch(id)
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
    const data = await InspectLedgerArchApi.exportInspectLedgerArch(queryParams)
    download.excel(data, '巡查巡检台账档案.xls')
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
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.table-beautify ::v-deep .el-table__body tr:hover {
  background-color: #fafafa;
}
</style>
