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
      <el-form-item label="派单ID" prop="dispatchId">
        <el-input
          v-model="queryParams.dispatchId"
          placeholder="请输入派单ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="派单编号" prop="dispatchNo">
        <el-input
          v-model="queryParams.dispatchNo"
          placeholder="请输入派单编号"
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
      <el-form-item label="派单类型" prop="dispatchType">
        <el-select
          v-model="queryParams.dispatchType"
          placeholder="请选择派单类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="派单状态" prop="dispatchStatus">
        <el-select
          v-model="queryParams.dispatchStatus"
          placeholder="请选择派单状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="派单时间" prop="dispatchTime">
        <el-date-picker
          v-model="queryParams.dispatchTime"
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
      class="table-container"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="派单ID" align="center" prop="dispatchId" />
      <el-table-column label="派单编号" align="center" prop="dispatchNo" />
      <el-table-column label="预警名称" align="center" prop="earlyWarnName" min-width="150" />
      <el-table-column label="派单类型" align="center" prop="dispatchType" />
      <el-table-column label="接收部门" align="center" prop="receiveDeptName" />
      <el-table-column label="处置人" align="center" prop="handleUserName" />
      <!-- 修改派单状态列 -->
      <el-table-column label="派单状态" align="center" prop="dispatchStatus">
        <template #default="scope">
          <el-tag
            :type="
              scope.row.dispatchStatus === 'COMPLETED'
                ? 'success'
                : scope.row.dispatchStatus === 'HANDLING'
                  ? 'warning'
                  : 'info'
            "
          >
            {{
              scope.row.dispatchStatus === 'COMPLETED'
                ? '已完成'
                : scope.row.dispatchStatus === 'HANDLING'
                  ? '处理中'
                  : '已接收'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="处置时限" align="center" prop="handleLimit" />
      <el-table-column
        label="派单时间"
        align="center"
        prop="dispatchTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="派单人" align="center" prop="dispatchUserName" />
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnDispatchForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnDispatchDetail
    ref="detailRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnDispatchApi,
  EarlyWarnDispatchVO
} from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarndispatch'
import EarlyWarnDispatchForm from './EarlyWarnDispatchForm.vue'
import EarlyWarnDispatchDetail from './EarlyWarnDispatchDetail.vue'

/** 预警告警派单 列表 */
defineOptions({ name: 'EarlyWarnDispatch' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnDispatchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  dispatchId: undefined,
  dispatchNo: undefined,
  earlyWarnId: undefined,
  earlyWarnName: undefined,
  dispatchType: undefined,
  receiveDeptId: undefined,
  receiveDeptName: undefined,
  handleUserId: undefined,
  handleUserName: undefined,
  dispatchStatus: undefined,
  handleLimit: undefined,
  dispatchTime: [],
  receiveTime: [],
  rejectReason: undefined,
  dispatchUserId: undefined,
  dispatchUserName: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const openDetail = (row: EarlyWarnDispatchVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnDispatchApi.getEarlyWarnDispatchPage(queryParams)
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
    await EarlyWarnDispatchApi.deleteEarlyWarnDispatch(id)
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
    const data = await EarlyWarnDispatchApi.exportEarlyWarnDispatch(queryParams)
    download.excel(data, '预警告警派单.xls')
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
.table-container {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.status-success {
  color: #52c41a;
  background: rgba(82, 196, 26, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-danger {
  color: #f5222d;
  background: rgba(245, 34, 45, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}
</style>
