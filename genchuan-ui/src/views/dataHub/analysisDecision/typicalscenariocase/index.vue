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
      <el-form-item label="案例ID" prop="typicalCaseId">
        <el-input
          v-model="queryParams.typicalCaseId"
          placeholder="请输入案例ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="案例名称" prop="caseName">
        <el-input
          v-model="queryParams.caseName"
          placeholder="请输入案例名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联方案ID" prop="relSchemeId">
        <el-input
          v-model="queryParams.relSchemeId"
          placeholder="请输入关联方案ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联评估ID" prop="relEvalId">
        <el-input
          v-model="queryParams.relEvalId"
          placeholder="请输入关联评估ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="案例标签" prop="caseTags">
        <el-input
          v-model="queryParams.caseTags"
          placeholder="请输入案例标签"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="适用场景" prop="applicableScenario">
        <el-input
          v-model="queryParams.applicableScenario"
          placeholder="请输入适用场景"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="核心措施" prop="coreMeasures">
        <el-input
          v-model="queryParams.coreMeasures"
          placeholder="请输入核心措施"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资源投入" prop="resourceInput">
        <el-input
          v-model="queryParams.resourceInput"
          placeholder="请输入资源投入"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="实施效果" prop="implementationEffect">
        <el-input
          v-model="queryParams.implementationEffect"
          placeholder="请输入实施效果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="评估等级" prop="evalGrade">
        <el-input
          v-model="queryParams.evalGrade"
          placeholder="请输入评估等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="成功经验" prop="successExp">
        <el-input
          v-model="queryParams.successExp"
          placeholder="请输入成功经验"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="改进建议" prop="improveSuggs">
        <el-input
          v-model="queryParams.improveSuggs"
          placeholder="请输入改进建议"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="归档人" prop="archUser">
        <el-input
          v-model="queryParams.archUser"
          placeholder="请输入归档人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="归档时间" prop="archTime">
        <el-date-picker
          v-model="queryParams.archTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="案例状态" prop="caseStatus">
        <el-select
          v-model="queryParams.caseStatus"
          placeholder="请选择案例状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input
          v-model="queryParams.extCat1"
          placeholder="请输入分类扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input
          v-model="queryParams.extCat2"
          placeholder="请输入分类扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input
          v-model="queryParams.extCommon1"
          placeholder="请输入通用扩展字段1"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input
          v-model="queryParams.extCommon2"
          placeholder="请输入通用扩展字段2"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="queryParams.createTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统创建时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="queryParams.updateTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统更新时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:typical-scenario-case:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:typical-scenario-case:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="案例ID" align="center" prop="typicalCaseId" />
      <el-table-column label="案例名称" align="center" prop="caseName" />
      <el-table-column label="关联方案ID" align="center" prop="relSchemeId" />
      <el-table-column label="关联评估ID" align="center" prop="relEvalId" />
      <el-table-column label="案例标签" align="center" prop="caseTags" />
      <el-table-column label="适用场景" align="center" prop="applicableScenario" />
      <el-table-column label="核心措施" align="center" prop="coreMeasures" />
      <el-table-column label="资源投入" align="center" prop="resourceInput" />
      <el-table-column label="实施效果" align="center" prop="implementationEffect" />
      <el-table-column label="评估等级" align="center" prop="evalGrade" />
      <el-table-column label="成功经验" align="center" prop="successExp" />
      <el-table-column label="改进建议" align="center" prop="improveSuggs" />
      <el-table-column label="归档人" align="center" prop="archUser" />
      <el-table-column
        label="归档时间"
        align="center"
        prop="archTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="案例状态" align="center" prop="caseStatus" />
      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />
      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />
      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" />
      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:typical-scenario-case:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:typical-scenario-case:delete']"
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
  <TypicalScenarioCaseForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { TypicalScenarioCaseApi, TypicalScenarioCaseVO } from '@/api/dataHub/analysisDecision/typicalscenariocase'
import TypicalScenarioCaseForm from './TypicalScenarioCaseForm.vue'

/** 典型场景案例库 列表 */
defineOptions({ name: 'TypicalScenarioCase' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<TypicalScenarioCaseVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  typicalCaseId: undefined,
  caseName: undefined,
  relSchemeId: undefined,
  relEvalId: undefined,
  caseTags: undefined,
  applicableScenario: undefined,
  coreMeasures: undefined,
  resourceInput: undefined,
  implementationEffect: undefined,
  evalGrade: undefined,
  successExp: undefined,
  improveSuggs: undefined,
  archUser: undefined,
  archTime: [],
  caseStatus: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await TypicalScenarioCaseApi.getTypicalScenarioCasePage(queryParams)
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
    await TypicalScenarioCaseApi.deleteTypicalScenarioCase(id)
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
    const data = await TypicalScenarioCaseApi.exportTypicalScenarioCase(queryParams)
    download.excel(data, '典型场景案例库.xls')
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
