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
      <el-form-item label="统计ID" prop="areaStatId">
        <el-input
          v-model="queryParams.areaStatId"
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
      <el-form-item label="行政区划" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入行政区划名称"
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
      <el-form-item label="统计人" prop="statUser">
        <el-input
          v-model="queryParams.statUser"
          placeholder="请输入统计人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
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
      <el-table-column label="统计ID" align="center" prop="areaStatId" width="120" />
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
      <el-table-column label="周期名称" align="center" prop="statCycleName" width="150" />
      <el-table-column label="行政区划" align="center" prop="areaName" width="150" />
      <el-table-column label="上级区域" align="center" prop="parentAreaName" width="150" />
      <el-table-column label="日常巡查" align="center" prop="dailyInspectCount" width="100" />
      <el-table-column label="专项巡查" align="center" prop="specInspectCount" width="100" />
      <el-table-column label="应急巡查" align="center" prop="emerInspectCount" width="100" />
      <el-table-column label="上报问题" align="center" prop="totalProblemCount" width="100" />
      <el-table-column label="整改完成" align="center" prop="rectifyCompleteCount" width="100" />
      <el-table-column label="整改率" align="center" prop="rectifyRate" width="100">
        <template #default="scope">
          <span :class="scope.row.rectifyRate >= 90 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.rectifyRate ? scope.row.rectifyRate + '%' : '0%' }}
          </span>
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
  <InspectAreaRptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectAreaRptDetail v-model="detailVisible" :data="detailData" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectAreaRptApi,
  InspectAreaRptVO
} from '@/api/dataHub/supervisionInspection/patrolStatisticalReport/areaStatisticalReport'
import InspectAreaRptForm from './InspectAreaRptForm.vue'
import InspectAreaRptDetail from './InspectAreaRptDetail.vue'

/** 巡查巡检区域统计 列表 */
defineOptions({ name: 'InspectAreaRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectAreaRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  areaStatId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  parentAreaName: undefined,
  statTime: [],
  statUser: undefined
  // 隐藏不常用搜索字段
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<InspectAreaRptVO | null>(null)
const detailRef = ref()

// 打开详情
const openDetail = (row: InspectAreaRptVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

// 关闭详情回调
const handleDetailClose = () => {
  detailData.value = null
  detailVisible.value = false
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectAreaRptApi.getInspectAreaRptPage(queryParams)
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
    await InspectAreaRptApi.deleteInspectAreaRpt(id)
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
    const data = await InspectAreaRptApi.exportInspectAreaRpt(queryParams)
    download.excel(data, '巡查巡检区域统计.xls')
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
