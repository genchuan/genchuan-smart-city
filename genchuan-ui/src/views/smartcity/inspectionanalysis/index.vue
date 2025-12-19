<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="巡查人员" prop="inspector" label-width="120px">
        <el-input
          v-model="queryParams.inspector"
          placeholder="请输入巡查人员"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="巡查开始时间" prop="cycleStartTime">
        <el-date-picker
          v-model="queryParams.cycleStartTime"
          type="datetime"
          placeholder="请选择巡查开始时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="巡查结束时间" prop="cycleEndTime">
        <el-date-picker
          v-model="queryParams.cycleEndTime"
          type="datetime"
          placeholder="请选择巡查结束时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:inspection-analysis:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:inspection-analysis:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" align="center" prop="id" min-width="50px" />
      <el-table-column label="巡查人员" align="center" prop="inspector" width="150px" />
      <el-table-column
        label="巡查开始时间"
        align="center"
        prop="cycleStartTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="巡查结束时间"
        align="center"
        prop="cycleEndTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="完成巡查任务数" align="center" prop="completedTaskCount" width="150px" />
      <el-table-column label="巡查总里程（km）" align="center" prop="totalMileageKm" width="150px" />
      <el-table-column label="巡查总时长（h）" align="center" prop="totalDurationH" width="150px" />
      <el-table-column label="发现问题总数" align="center" prop="foundProblemCount" width="150px" />
      <el-table-column label="创建时间" align="center" prop="createTime" :formatter="dateFormatter" width="180px" />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:inspection-analysis:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:inspection-analysis:delete']"
          >
            删除
          </el-button>
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

  <!-- 表单弹窗 -->
  <InspectionAnalysisForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { InspectionAnalysisApi, InspectionAnalysisVO } from '@/api/smartcity/inspectionanalysis'
import InspectionAnalysisForm from './InspectionAnalysisForm.vue'

/** 巡查分析 列表 */
defineOptions({ name: 'InspectionAnalysis' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectionAnalysisVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  inspector: undefined,
  cycleStartTime: undefined,
  cycleEndTime: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectionAnalysisApi.getInspectionAnalysisPage(queryParams)
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

/** 添加/修改操作：关联巡查分析表单 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除：调用巡查分析删除API
    await InspectionAnalysisApi.deleteInspectionAnalysis(id)
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
    // 发起导出：调用巡查分析导出API
    exportLoading.value = true
    const data = await InspectionAnalysisApi.exportInspectionAnalysis(queryParams)
    download.excel(data, '巡查分析统计.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>
