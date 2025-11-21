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
      <el-form-item label="关联分析编号" prop="analysisNumber" label-width="150px">
        <el-input
          v-model="queryParams.analysisNumber"
          placeholder="请输入关联分析编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主事件编号" prop="mainEventNumber" label-width="150px">
        <el-input
          v-model="queryParams.mainEventNumber"
          placeholder="请输入主事件编号"
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
      <el-form-item style="margin-left: 120px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:event-analysis:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:event-analysis:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="60px" />
      <el-table-column label="关联分析编号" align="center" prop="analysisNumber" width="160px" />
      <el-table-column label="主事件编号" align="center" prop="mainEventNumber" width="160px" />
      <el-table-column label="主事件名称" align="center" prop="mainEventName" />
      <el-table-column label="关联事件编号" align="center" prop="relatedEventNumber" width="160px" />
      <el-table-column label="关联事件名称" align="center" prop="relatedEventName" />
      <el-table-column label="关联类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.associationType === 'direct_causal'">直接因果</template>
          <template v-else-if="scope.row.associationType === 'indirect_causal'">间接因果</template>
          <template v-else-if="scope.row.associationType === 'same_period'">同期发生</template>
          <template v-else-if="scope.row.associationType === 'time_dependent'">时序依赖</template>
          <template v-else-if="scope.row.associationType === 'same_component'">同部件关联</template>
          <template v-else-if="scope.row.associationType === 'upstream_downstream'"
            >上下游关联</template
          >
          <template v-else-if="scope.row.associationType === 'same_system'">同系统关联</template>
          <template v-else-if="scope.row.associationType === 'derivative'">衍生关联</template>
          <template v-else-if="scope.row.associationType === 'coupling'">耦合关联</template>
          <template v-else>{{ scope.row.associationType }}</template>
        </template>
      </el-table-column>
      <el-table-column label="关联强度" align="center" prop="associationStrength" />
      <el-table-column
        label="分析时间"
        align="center"
        prop="analysisTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="分析人员" align="center" prop="analysts" />
      <el-table-column label="分析结论" align="center" prop="conclusion" />
      <el-table-column label="关联证据" align="center" prop="relatedEvidence" />
      <el-table-column label="建议措施" align="center" prop="recommendedMeasure" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:event-analysis:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:event-analysis:delete']"
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
  <EventAnalysisForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EventAnalysisApi, EventAnalysisVO } from '@/api/smartcity/eventanalysis'
import EventAnalysisForm from './EventAnalysisForm.vue'

/** 事件关联分析 列表 */
defineOptions({ name: 'EventAnalysis' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EventAnalysisVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  analysisNumber: undefined,
  mainEventNumber: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EventAnalysisApi.getEventAnalysisPage(queryParams)
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
    await EventAnalysisApi.deleteEventAnalysis(id)
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
    const data = await EventAnalysisApi.exportEventAnalysis(queryParams)
    download.excel(data, '事件关联分析.xls')
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
