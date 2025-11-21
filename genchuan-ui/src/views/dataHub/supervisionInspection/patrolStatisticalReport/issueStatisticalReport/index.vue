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
      <!-- 保留常用搜索字段，隐藏冗余字段 -->
      <el-form-item label="统计ID" prop="problemStatId">
        <el-input
          v-model="queryParams.problemStatId"
          placeholder="请输入统计ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
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
      <el-form-item label="问题类型" prop="problemTypeName">
        <el-input
          v-model="queryParams.problemTypeName"
          placeholder="请输入问题类型名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
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
      <el-table-column label="统计ID" align="center" prop="problemStatId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
      <el-table-column label="周期名称" align="center" prop="statCycleName" width="150" />
      <el-table-column label="所属区域" align="center" prop="areaName" width="150" />
      <el-table-column label="问题类型" align="center" prop="problemTypeName" width="150" />
      <el-table-column label="上报问题数" align="center" prop="rptProblemCount" width="120" />
      <el-table-column label="一级问题数" align="center" prop="level1ProblemCount" width="120" />
      <el-table-column label="整改完成数" align="center" prop="rectifyCompleteCount" width="120" />
      <el-table-column label="整改率" align="center" prop="rectifyRate" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.rectifyRate >= 90 ? 'success' : 'warning'">
            {{ scope.row.rectifyRate ? scope.row.rectifyRate + '%' : '0%' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
      />
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
  <InspectProblemRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectProblemRptDetail v-model="detailVisible" :data="detailData" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectProblemRptApi,
  InspectProblemRptVO
} from '@/api/dataHub/supervisionInspection/patrolStatisticalReport/issueStatisticalReport'
import InspectProblemRptForm from './InspectProblemRptForm.vue'
import InspectProblemRptDetail from './InspectProblemRptDetail.vue'

/** 巡查巡检问题统计 列表 */
defineOptions({ name: 'InspectProblemRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectProblemRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  problemStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  problemTypeId: undefined,
  problemTypeName: undefined,
  statTime: []
  // 隐藏不常用搜索字段
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<InspectProblemRptVO | null>(null)

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectProblemRptApi.getInspectProblemRptPage(queryParams)
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
const openDetail = (row: InspectProblemRptVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 关闭详情回调 */
const handleDetailClose = () => {
  detailData.value = null
  detailVisible.value = false
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectProblemRptApi.deleteInspectProblemRpt(id)
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
    const data = await InspectProblemRptApi.exportInspectProblemRpt(queryParams)
    download.excel(data, '巡查巡检问题统计.xls')
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

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
