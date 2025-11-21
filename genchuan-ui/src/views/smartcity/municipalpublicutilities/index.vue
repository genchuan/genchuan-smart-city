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
      <el-form-item label="市政设施名称" prop="nameMunicipalFacilities">
        <el-input
          v-model="queryParams.nameMunicipalFacilities"
          placeholder="请输入市政设施名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设施类型" prop="facilityType">
        <el-select
          v-model="queryParams.facilityType"
          placeholder="请选择设施类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="设施位置" prop="facilityLocation">
        <el-input
          v-model="queryParams.facilityLocation"
          placeholder="请输入设施位置"
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
          v-hasPermi="['smartcity:municipal-public-utilities:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:municipal-public-utilities:export']"
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
      <el-table-column label="市政设施名称" align="center" prop="nameMunicipalFacilities" />
      <el-table-column label="设施类型" align="center" prop="facilityType" />
      <el-table-column label="设施位置" align="center" prop="facilityLocation" />
      <el-table-column
        label="建设时间"
        align="center"
        prop="constructionTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="使用年限" align="center" prop="serviceLife" />
      <el-table-column label="承载能力" align="center" prop="bearingCapacity" />
      <el-table-column label="服务范围" align="center" prop="serviceScope" />
      <el-table-column label="服务人口" align="center" prop="servicePopulation" />
      <el-table-column label="资源配置" align="center" prop="resourceAllocation" />
      <el-table-column label="资金投入" align="center" prop="capitalInvestment" />
      <el-table-column label="预算安排" align="center" prop="budgetArrangement" />
      <el-table-column label="效益评估" align="center" prop="benefitsAssessment" />
      <el-table-column label="政策法规依据" align="center" prop="policyRegulatoryBasis" />
      <el-table-column label="行业标准规范" align="center" prop="industryStandardSpecifications" />
      <el-table-column label="安全隐患" align="center" prop="potentialSafetyHazard" />
      <el-table-column label="风险评估" align="center" prop="riskAssessment" />
      <el-table-column label="应急处置预案" align="center" prop="emergencyResponsePlan" />
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
            v-hasPermi="['smartcity:municipal-public-utilities:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:municipal-public-utilities:delete']"
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
  <MunicipalPublicUtilitiesForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MunicipalPublicUtilitiesApi, MunicipalPublicUtilitiesVO } from '@/api/smartcity/municipalpublicutilities'
import MunicipalPublicUtilitiesForm from './MunicipalPublicUtilitiesForm.vue'

/** 市政公用 列表 */
defineOptions({ name: 'MunicipalPublicUtilities' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MunicipalPublicUtilitiesVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  nameMunicipalFacilities: undefined,
  facilityType: undefined,
  facilityLocation: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MunicipalPublicUtilitiesApi.getMunicipalPublicUtilitiesPage(queryParams)
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
    await MunicipalPublicUtilitiesApi.deleteMunicipalPublicUtilities(id)
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
    const data = await MunicipalPublicUtilitiesApi.exportMunicipalPublicUtilities(queryParams)
    download.excel(data, '市政公用.xls')
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