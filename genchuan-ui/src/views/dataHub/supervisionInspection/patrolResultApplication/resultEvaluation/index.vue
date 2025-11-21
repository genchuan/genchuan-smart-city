<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px mb-4"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="评估编码" prop="evalCode">
        <el-input
          v-model="queryParams.evalCode"
          placeholder="请输入评估编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="评估周期" prop="evalCycleName">
        <el-input
          v-model="queryParams.evalCycleName"
          placeholder="请输入评估周期名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="所属区域" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入所属区域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
        />
      </el-form-item>
      <el-form-item label="主要问题类型" prop="mainProblemType">
        <el-select
          v-model="queryParams.mainProblemType"
          placeholder="请选择主要问题类型"
          clearable
          class="!w-280px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="评估人" prop="evalUser">
        <el-input
          v-model="queryParams.evalUser"
          placeholder="请输入评估人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-280px"
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
          class="!w-320px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" type="primary" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')" class="ml-2">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading" class="ml-2">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>
  <!-- 列表 -->
  <ContentWrap class="rounded-lg shadow-sm border border-gray-100">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
      :header-cell-style="{
        'background-color': '#f8f9fa',
        color: '#333',
        'font-weight': '600',
        'border-bottom': '2px solid #e9ecef'
      }"
      :row-style="{ height: '50px' }"
      :cell-style="{ 'vertical-align': 'middle' }"
    >
      <!-- 隐藏主键列：v-if="false" 完全移除不占位 -->
      <el-table-column label="主键ID" align="center" prop="id" v-if="false" />
      <el-table-column label="评估编码" align="center" prop="evalCode" min-width="140px" />
      <el-table-column label="评估周期" align="center" prop="evalCycleName" min-width="160px" />
      <el-table-column label="所属区域" align="center" prop="areaName" min-width="140px" />
      <el-table-column
        label="日常巡查合格率"
        align="center"
        prop="dailyQualifiedRate"
        min-width="140px"
      >
        <template #default="scope">
          <span
            class="font-medium"
            :class="{
              'text-green-600': Number(scope.row.dailyQualifiedRate) >= 90,
              'text-orange-600':
                Number(scope.row.dailyQualifiedRate) < 90 &&
                scope.row.dailyQualifiedRate !== undefined
            }"
          >
            {{ scope.row.dailyQualifiedRate || '0' }}%
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="专项巡查整改率"
        align="center"
        prop="specRectifyRate"
        min-width="140px"
      >
        <template #default="scope">
          <span
            class="font-medium"
            :class="{
              'text-green-600': Number(scope.row.specRectifyRate) >= 90,
              'text-orange-600':
                Number(scope.row.specRectifyRate) < 90 && scope.row.specRectifyRate !== undefined
            }"
          >
            {{ scope.row.specRectifyRate || '0' }}%
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="一级问题处置及时率"
        align="center"
        prop="level1TimelyRate"
        min-width="160px"
      >
        <template #default="scope">
          <span
            class="font-medium"
            :class="{
              'text-green-600': Number(scope.row.level1TimelyRate) >= 90,
              'text-orange-600':
                Number(scope.row.level1TimelyRate) < 90 && scope.row.level1TimelyRate !== undefined
            }"
          >
            {{ scope.row.level1TimelyRate || '0' }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="主要问题类型"
        align="center"
        prop="mainProblemType"
        min-width="140px"
      />
      <el-table-column label="评估人" align="center" prop="evalUser" min-width="120px" />
      <el-table-column
        label="评估时间"
        align="center"
        prop="evalTime"
        :formatter="dateFormatter"
        min-width="180px"
      />
      <!-- 隐藏扩展字段列：v-if="false" 完全移除不占位 -->
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" v-if="false" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" v-if="false" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" v-if="false" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" v-if="false" />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetailDrawer(scope.row)" class="mr-2">
            详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination-container pt-4 pb-2 px-4">
      <Pagination
        :total="total"
        v-model:page="queryParams.pageNo"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
        background
        layout="prev, pager, next, jumper, ->, total"
      />
    </div>
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <InspectResultEvalForm
    ref="formRef"
    :model-value="formDialogVisible"
    @update:model-value="formDialogVisible = $event"
    @success="getList"
  />
  <!-- 详情抽屉 -->
  <InspectResultEvalDetail v-model="detailDrawerVisible" :data="currentDetailData" />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectResultEvalApi,
  InspectResultEvalVO
} from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultEvaluation'
import InspectResultEvalForm from './InspectResultEvalForm.vue'
import InspectResultEvalDetail from './InspectResultEvalDetail.vue'

/** 巡查巡检结果评估 列表 */
defineOptions({ name: 'InspectResultEval' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<InspectResultEvalVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailDrawerVisible = ref(false) // 详情抽屉显示状态
const currentDetailData = ref<InspectResultEvalVO | null>(null) // 当前详情数据
const formDialogVisible = ref(false) // 表单弹窗显示状态

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evalId: undefined,
  evalCode: undefined,
  evalCycle: undefined,
  evalCycleName: undefined,
  areaCode: undefined,
  areaName: undefined,
  dailyQualifiedRate: undefined,
  specRectifyRate: undefined,
  level1TimelyRate: undefined,
  problemConcentratedArea: undefined,
  mainProblemType: undefined,
  evalOpinion: undefined,
  evalUser: undefined,
  evalTime: [],
  evalRptUrl: undefined,
  createUser: undefined,
  bizCreateTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const formRef = ref() // 表单组件引用

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectResultEvalApi.getInspectResultEvalPage(queryParams)
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
  queryFormRef.value?.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const openForm = async (type: string, id?: number) => {
  formDialogVisible.value = true
  // 确保子组件渲染完成后再调用方法
  formRef.value?.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectResultEvalApi.deleteInspectResultEval(id)
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
    const data = await InspectResultEvalApi.exportInspectResultEval(queryParams)
    download.excel(data, '巡查巡检结果评估.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情抽屉 */
const openDetailDrawer = (row: InspectResultEvalVO) => {
  currentDetailData.value = row
  detailDrawerVisible.value = true
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
<style scoped>
.custom-table {
  --el-table-border-color: #f0f2f5;
  border-radius: 8px;
  overflow: hidden;
}

.custom-table ::v-deep(.el-table__row) {
  transition: background-color 0.2s ease;
}

.custom-table ::v-deep(.el-table__row):hover {
  background-color: #fafafa !important;
}

.pagination-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

/* 表格数值列样式优化 */
::v-deep(.el-table-column--align-center .cell) {
  font-family: 'Arial', sans-serif;
}
</style>
