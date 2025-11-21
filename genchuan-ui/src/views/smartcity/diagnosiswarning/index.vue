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
      <el-form-item label="故障设备名称" prop="nameFaultyEquipment">
        <el-input
          v-model="queryParams.nameFaultyEquipment"
          placeholder="请输入故障设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="故障设备编号" prop="faultEquipmentNumber">
        <el-input
          v-model="queryParams.faultEquipmentNumber"
          placeholder="请输入故障设备编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:diagnosis-warning:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:diagnosis-warning:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="故障设备名称" align="center" prop="nameFaultyEquipment" />
      <el-table-column label="故障设备编号" align="center" prop="faultEquipmentNumber" />
      <el-table-column label="故障发生时间" align="center" prop="timeFailure" />
      <el-table-column label="故障现象描述" align="center" prop="descriptionSymptoms" />
      <el-table-column label="故障可能原因分析" align="center" prop="analysisPossibleMalfunction" />
      <el-table-column label="诊断方法与依据" align="center" prop="diagnosticMethodsBasis" />
      <el-table-column label="预警级别" align="center" prop="warningLevel" />
      <el-table-column label="预警时间" align="center" prop="warningTime" />
      <el-table-column label="预警信息内容" align="center" prop="warningInformationContent" />
      <el-table-column label="专家评估意见" align="center" prop="expertEvaluationOpinions" />
      <el-table-column label="系统恢复时间" align="center" prop="systemRecoveryTime" />
      <el-table-column label="故障对业务的影响程度" align="center" prop="theDegreeBusiness" />
      <el-table-column label="后续预防措施" align="center" prop="subsequentPreventiveMeasures" />
      <el-table-column label="备用设备投入情况" align="center" prop="backupDeploymentStatus" />
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
            v-hasPermi="['smartcity:diagnosis-warning:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:diagnosis-warning:delete']"
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
  <DiagnosisWarningForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DiagnosisWarningApi, DiagnosisWarningVO } from '@/api/smartcity/diagnosiswarning'
import DiagnosisWarningForm from './DiagnosisWarningForm.vue'

/** 故障诊断和预警 列表 */
defineOptions({ name: 'DiagnosisWarning' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DiagnosisWarningVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  nameFaultyEquipment: undefined,
  faultEquipmentNumber: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DiagnosisWarningApi.getDiagnosisWarningPage(queryParams)
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
    await DiagnosisWarningApi.deleteDiagnosisWarning(id)
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
    const data = await DiagnosisWarningApi.exportDiagnosisWarning(queryParams)
    download.excel(data, '故障诊断和预警.xls')
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