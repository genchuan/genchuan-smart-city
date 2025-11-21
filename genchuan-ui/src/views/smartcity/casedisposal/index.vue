<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="案件ID" prop="caseId" label-width="100px">
        <el-input
          v-model="queryParams.caseId"
          placeholder="请输入案件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处理类型" prop="disposalType" style="margin-left: 60px">
        <el-select
          v-model="queryParams.disposalType"
          placeholder="请选择处理类型"
          clearable
          @change="handleQuery"
          class="!w-240px"
        >
          <el-option value="行政处罚" label="行政处罚" />
          <el-option value="整改通知" label="整改通知" />
          <el-option value="不予处罚" label="不予处罚" />
          <el-option value="其他处理" label="其他处理" />
        </el-select>
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
      <el-form-item style="margin-left: 100px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:case-disposal:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:case-disposal:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" align="center" prop="id" min-width="30px" />
      <el-table-column label="案件ID" align="center" prop="caseId" />
      <el-table-column label="处理类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.disposalType === 'administrative_penalty'">行政处罚</template>
          <template v-else-if="scope.row.disposalType === 'rectification_notice'">整改通知</template>
          <template v-else-if="scope.row.disposalType === 'no_penalty'">不予处罚</template>
          <template v-else-if="scope.row.disposalType === 'other_disposal'">其他处理</template>
          <template v-else>{{ scope.row.disposalType }}</template>
        </template>
      </el-table-column>
      <el-table-column label="处理部门" align="center" prop="disposalDepartment" />
      <el-table-column label="处理人" align="center" prop="disposalPerson" />
      <el-table-column
        label="处理开始时间"
        align="center"
        prop="disposalStartTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column
        label="处理结束时间"
        align="center"
        prop="disposalEndTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column label="处理依据" align="center" prop="disposalBasis" />
      <el-table-column label="处理内容" align="center" prop="disposalContent" />
      <el-table-column label="处理结果" align="center" prop="disposalResult" />
      <el-table-column label="处罚金额" align="center" prop="penaltyAmount" />
      <el-table-column label="处罚类型" align="center" prop="penaltyType" />
      <!--<el-table-column-->
      <!--  label="创建时间"-->
      <!--  align="center"-->
      <!--  prop="createTime"-->
      <!--  :formatter="dateFormatter"-->
      <!--  width="180px"-->
      <!--/>-->
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:case-disposal:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:case-disposal:delete']"
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
  <CaseDisposalForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { CaseDisposalApi, CaseDisposalVO } from '@/api/smartcity/casedisposal'
import CaseDisposalForm from './CaseDisposalForm.vue'

/** 案件处理 列表 */
defineOptions({ name: 'CaseDisposal' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CaseDisposalVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  caseId: undefined,
  disposalType: undefined,
  createTime: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CaseDisposalApi.getCaseDisposalPage(queryParams)
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
    await CaseDisposalApi.deleteCaseDisposal(id)
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
    const data = await CaseDisposalApi.exportCaseDisposal(queryParams)
    download.excel(data, '案件处理.xls')
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
