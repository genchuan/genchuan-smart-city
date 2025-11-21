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
      <el-form-item label="跟踪ID" prop="execTrackId">
        <el-input
          v-model="queryParams.execTrackId"
          placeholder="请输入跟踪ID"
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
      <el-form-item label="关联方案名称" prop="relSchemeName">
        <el-input
          v-model="queryParams.relSchemeName"
          placeholder="请输入关联方案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="计划实施周期" prop="plannedExecCycle">
        <el-input
          v-model="queryParams.plannedExecCycle"
          placeholder="请输入计划实施周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="已实施天数" prop="executedDays">
        <el-input
          v-model="queryParams.executedDays"
          placeholder="请输入已实施天数"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="实施进度" prop="execProgress">
        <el-input
          v-model="queryParams.execProgress"
          placeholder="请输入实施进度"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="预期效果指标" prop="expectedEffectIdx">
        <el-input
          v-model="queryParams.expectedEffectIdx"
          placeholder="请输入预期效果指标"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="实际效果指标" prop="actualEffectIdx">
        <el-input
          v-model="queryParams.actualEffectIdx"
          placeholder="请输入实际效果指标"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="效果达成率" prop="effectAchievementRate">
        <el-input
          v-model="queryParams.effectAchievementRate"
          placeholder="请输入效果达成率"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="未达标项" prop="unmetItems">
        <el-input
          v-model="queryParams.unmetItems"
          placeholder="请输入未达标项"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="跟踪状态" prop="trackStatus">
        <el-select
          v-model="queryParams.trackStatus"
          placeholder="请选择跟踪状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="跟踪人" prop="trackUser">
        <el-input
          v-model="queryParams.trackUser"
          placeholder="请输入跟踪人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="跟踪时间" prop="trackTime">
        <el-date-picker
          v-model="queryParams.trackTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="上次跟踪时间" prop="lastTrackTime">
        <el-date-picker
          v-model="queryParams.lastTrackTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="整改建议" prop="rectificationSugg">
        <el-input
          v-model="queryParams.rectificationSugg"
          placeholder="请输入整改建议"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
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
          v-hasPermi="['datacenter:decision-exec-track:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:decision-exec-track:export']"
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
      <el-table-column label="跟踪ID" align="center" prop="execTrackId" />
      <el-table-column label="关联方案ID" align="center" prop="relSchemeId" />
      <el-table-column label="关联方案名称" align="center" prop="relSchemeName" />
      <el-table-column label="计划实施周期" align="center" prop="plannedExecCycle" />
      <el-table-column label="已实施天数" align="center" prop="executedDays" />
      <el-table-column label="实施进度" align="center" prop="execProgress" />
      <el-table-column label="预期效果指标" align="center" prop="expectedEffectIdx" />
      <el-table-column label="实际效果指标" align="center" prop="actualEffectIdx" />
      <el-table-column label="效果达成率" align="center" prop="effectAchievementRate" />
      <el-table-column label="未达标项" align="center" prop="unmetItems" />
      <el-table-column label="跟踪状态" align="center" prop="trackStatus" />
      <el-table-column label="跟踪人" align="center" prop="trackUser" />
      <el-table-column
        label="跟踪时间"
        align="center"
        prop="trackTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="上次跟踪时间"
        align="center"
        prop="lastTrackTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="整改建议" align="center" prop="rectificationSugg" />
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
            v-hasPermi="['datacenter:decision-exec-track:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:decision-exec-track:delete']"
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
  <DecisionExecTrackForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DecisionExecTrackApi, DecisionExecTrackVO } from '@/api/dataHub/analysisDecision/decisionexectrack'
import DecisionExecTrackForm from './DecisionExecTrackForm.vue'

/** 决策执行跟踪 列表 */
defineOptions({ name: 'DecisionExecTrack' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DecisionExecTrackVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  execTrackId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  plannedExecCycle: undefined,
  executedDays: undefined,
  execProgress: undefined,
  expectedEffectIdx: undefined,
  actualEffectIdx: undefined,
  effectAchievementRate: undefined,
  unmetItems: undefined,
  trackStatus: undefined,
  trackUser: undefined,
  trackTime: [],
  lastTrackTime: [],
  rectificationSugg: undefined,
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
    const data = await DecisionExecTrackApi.getDecisionExecTrackPage(queryParams)
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
    await DecisionExecTrackApi.deleteDecisionExecTrack(id)
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
    const data = await DecisionExecTrackApi.exportDecisionExecTrack(queryParams)
    download.excel(data, '决策执行跟踪.xls')
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
