<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="统计周期" prop="statisticalCycle" label-width="120px">
        <el-input
          v-model="queryParams.statisticalCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事件类型" prop="eventType">
        <el-select
          v-model="queryParams.eventType"
          placeholder="请选择事件类型"
          clearable
          class="!w-240px"
        >
          <el-option label="故障事件" value="fault" />
          <el-option label="维护事件" value="maintenance" />
          <el-option label="异常运行事件" value="abnormal_operation" />
          <el-option label="预警事件" value="early_warning" />
          <el-option label="更换事件" value="replacement" />
          <el-option label="巡检发现事件" value="inspection_found" />
          <el-option label="报废事件" value="scrap" />
          <el-option label="其他事件" value="other" />
        </el-select>
      </el-form-item>
      <el-form-item label="发生地点" prop="place">
        <el-input
          v-model="queryParams.place"
          placeholder="请输入发生地点"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <!--<el-form-item label="创建时间" prop="createTime">-->
      <!--  <el-date-picker-->
      <!--    v-model="queryParams.createTime"-->
      <!--    value-format="YYYY-MM-DD HH:mm:ss"-->
      <!--    type="daterange"-->
      <!--    start-placeholder="开始日期"-->
      <!--    end-placeholder="结束日期"-->
      <!--    :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"-->
      <!--    class="!w-220px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item style="margin-left: 50px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:event-statistical-analysis:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:event-statistical-analysis:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="50px" />
      <el-table-column label="统计周期" align="center" prop="statisticalCycle" />
      <el-table-column label="事件类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.eventType === 'fault'">故障事件</template>
          <template v-else-if="scope.row.eventType === 'maintenance'">维护事件</template>
          <template v-else-if="scope.row.eventType === 'abnormal_operation'">异常运行事件</template>
          <template v-else-if="scope.row.eventType === 'early_warning'">预警事件</template>
          <template v-else-if="scope.row.eventType === 'replacement'">更换事件</template>
          <template v-else-if="scope.row.eventType === 'inspection_found'">巡检发现事件</template>
          <template v-else-if="scope.row.eventType === 'scrap'">报废事件</template>
          <template v-else>其他事件</template>
        </template>
      </el-table-column>
      <el-table-column label="事件数量" align="center" prop="numberOfEvents" />
      <el-table-column label="发生地点" align="center" prop="place" />
      <el-table-column label="高发时段" align="center" prop="highIncidencePeriod" />
      <el-table-column label="平均处理时长" align="center" prop="aht" />
      <el-table-column label="处理成功率" align="center" prop="processingSuccessRate" />
      <el-table-column label="严重程度分布" align="center" prop="severityDistribution" />
      <el-table-column label="涉及部门" align="center" prop="involvedDepartments" />
      <el-table-column label="责任主体" align="center" prop="responsibleParty" />
      <el-table-column label="处理方式占比" align="center" prop="proportionOfProcessing" />
      <el-table-column label="资源消耗统计" align="center" prop="resourceConsumption" />
      <el-table-column label="不同渠道上报占比" align="center" prop="proportionOfReporting" />
      <el-table-column label="重复事件数量" align="center" prop="numberOfRepeatedEvents" />
      <el-table-column label="时间序列趋势" align="center" prop="timeSeriesTrend" />
      <el-table-column label="关联部件故障次数" align="center" prop="numberOfRelated" />
      <el-table-column
        label="公众反馈满意度评分"
        align="center"
        prop="publicFeedbackSatisfaction"
      />
      <!-- <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      /> -->
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:event-statistical-analysis:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:event-statistical-analysis:delete']"
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

  <!-- 表单弹窗：添加/修改 -->
  <EventStatisticalAnalysisForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EventStatisticalAnalysisApi,
  EventStatisticalAnalysisVO
} from '@/api/smartcity/eventstatisticalanalysis'
import EventStatisticalAnalysisForm from './EventStatisticalAnalysisForm.vue'

/** 事件统计分析 列表 */
defineOptions({ name: 'EventStatisticalAnalysis' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EventStatisticalAnalysisVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statisticalCycle: undefined,
  eventType: undefined,
  place: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EventStatisticalAnalysisApi.getEventStatisticalAnalysisPage(queryParams)
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
    await EventStatisticalAnalysisApi.deleteEventStatisticalAnalysis(id)
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
    const data = await EventStatisticalAnalysisApi.exportEventStatisticalAnalysis(queryParams)
    download.excel(data, '事件统计分析.xls')
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
