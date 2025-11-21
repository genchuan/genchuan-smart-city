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
      <el-form-item label="分析名称" prop="resultAnalysisName">
        <el-input
          v-model="queryParams.resultAnalysisName"
          placeholder="请输入分析名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="分析编码" prop="resultAnalysisCode">
        <el-input
          v-model="queryParams.resultAnalysisCode"
          placeholder="请输入分析编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="图表类型" prop="chartType">
        <el-select
          v-model="queryParams.chartType"
          placeholder="请选择图表类型"
          clearable
          class="!w-200px rounded-md"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="生成时间" prop="generateTime">
        <el-date-picker
          v-model="queryParams.generateTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-300px rounded-md"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
    <el-card shadow="hover" class="rounded-lg">
      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        class="table-beautify"
        border
      >
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="分析名称" align="center" prop="resultAnalysisName" width="200" />
        <el-table-column label="分析编码" align="center" prop="resultAnalysisCode" width="150" />
        <el-table-column label="关联任务名称" align="center" prop="evalTaskName" width="180" />
        <el-table-column label="分析维度" align="center" prop="analysisDimension" width="120" />
        <el-table-column label="图表类型" align="center" prop="chartType" width="120">
          <template #default="scope">
            <el-tag
              :type="
                ['BAR', 'PIE', 'LINE', 'RADAR', 'SCATTER'].includes(scope.row.chartType)
                  ? 'success'
                  : 'info'
              "
            >
              {{ scope.row.chartType || '-' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="生成时间"
          align="center"
          prop="generateTime"
          :formatter="dateFormatter"
          width="180px"
        />
        <el-table-column label="生成人" align="center" prop="generateUser" width="120" />
        <el-table-column label="操作" align="center" min-width="180px">
          <template #default="scope">
            <el-button link type="primary" @click="openDetail(scope.row)">
              <Icon icon="ep:detail" class="mr-2" />
              详情
            </el-button>
            <el-button link type="primary" @click="openForm('update', scope.row.id)">
              <Icon icon="ep:edit" class="mr-2" />
              编辑
            </el-button>
            <el-button link type="danger" @click="handleDelete(scope.row.id)">
              <Icon icon="ep:delete" class="mr-2" />
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-4">
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EvalResultAnalysisForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalResultAnalysisDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalResultAnalysisApi,
  EvalResultAnalysisVO
} from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultAna'
import EvalResultAnalysisForm from './EvalResultAnalysisForm.vue'
import EvalResultAnalysisDetailDrawer from './EvalResultAnalysisDetailDrawer.vue'

/** 评价结果分析 列表 */
defineOptions({ name: 'EvalResultAnalysis' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalResultAnalysisVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  resultAnalysisName: undefined,
  resultAnalysisCode: undefined,
  chartType: undefined,
  generateTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 组件Ref */
const formRef = ref()
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalResultAnalysisApi.getEvalResultAnalysisPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情操作 */
const openDetail = (row: EvalResultAnalysisVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalResultAnalysisApi.deleteEvalResultAnalysis(id)
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
    const data = await EvalResultAnalysisApi.exportEvalResultAnalysis(queryParams)
    download.excel(data, '评价结果分析.xls')
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
  --el-table-row-hover-bg-color: #f0f9ff;
  --el-table-header-text-color: #333;
  --el-table-header-bg-color: #f8f9fa;
  --el-table-border-color: #e4e7ed;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-form-item {
  margin-bottom: 15px;
}
</style>
