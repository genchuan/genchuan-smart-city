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
      <el-form-item label="报表ID" prop="rptId">
        <el-input
          v-model="queryParams.rptId"
          placeholder="请输入报表ID"
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
      <el-form-item label="行政区划" prop="adminName">
        <el-input
          v-model="queryParams.adminName"
          placeholder="请输入行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事件类型" prop="evtType">
        <el-input
          v-model="queryParams.evtType"
          placeholder="请输入事件类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="报表生成时间" prop="rptCreateTime">
        <el-date-picker
          v-model="queryParams.rptCreateTime"
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
      <el-table-column label="报表ID" align="center" prop="rptId" />
      <el-table-column label="统计周期" align="center" prop="statCycle" />
      <el-table-column label="统计周期名称" align="center" prop="statCycleName" />
      <el-table-column label="行政区划" align="center" prop="adminName" />
      <el-table-column label="事件类型" align="center" prop="evtType" />
      <el-table-column label="事件总数" align="center" prop="totalEvtCount" />
      <el-table-column label="已办结事件数" align="center" prop="completedEvtCount" />
      <el-table-column label="事件办结率(%)" align="center" prop="evtCompleteRate" />
      <el-table-column
        label="报表生成时间"
        align="center"
        prop="rptCreateTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
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
  <BasicMonEvtRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <BasicMonEvtRptDetailDrawer
    ref="detailDrawerRef"
    :visible="detailVisible"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  BasicMonEvtRptApi,
  BasicMonEvtRptVO
} from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonevtrpt'
import BasicMonEvtRptForm from './BasicMonEvtRptForm.vue'
import BasicMonEvtRptDetailDrawer from './BasicMonEvtRptDetailDrawer.vue'

/** 基础监测事件报 列表 */
defineOptions({ name: 'BasicMonEvtRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BasicMonEvtRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  rptId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  adminCode: undefined,
  adminName: undefined,
  evtType: undefined,
  rptCreateTime: []
  // 隐藏部分搜索字段，只保留常用的
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailDrawerRef = ref()
const openDetail = (row: BasicMonEvtRptVO) => {
  detailVisible.value = true
  detailDrawerRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BasicMonEvtRptApi.getBasicMonEvtRptPage(queryParams)
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
    await BasicMonEvtRptApi.deleteBasicMonEvtRpt(id)
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
    const data = await BasicMonEvtRptApi.exportBasicMonEvtRpt(queryParams)
    download.excel(data, '基础监测事件报表.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
}
</style>
