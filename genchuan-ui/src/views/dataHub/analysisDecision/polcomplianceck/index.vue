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
      <el-form-item label="校验ID" prop="complianceCkId">
        <el-input
          v-model="queryParams.complianceCkId"
          placeholder="请输入校验ID"
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
      <el-form-item label="校验政策IDs" prop="ckPolIds">
        <el-input
          v-model="queryParams.ckPolIds"
          placeholder="请输入校验政策IDs"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="校验政策名称" prop="ckPolNames">
        <el-input
          v-model="queryParams.ckPolNames"
          placeholder="请输入校验政策名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="校验状态" prop="ckStatus">
        <el-select
          v-model="queryParams.ckStatus"
          placeholder="请选择校验状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-input
          v-model="queryParams.riskLevel"
          placeholder="请输入风险等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="风险点数量" prop="riskPtCount">
        <el-input
          v-model="queryParams.riskPtCount"
          placeholder="请输入风险点数量"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="合规率" prop="complianceRate">
        <el-input
          v-model="queryParams.complianceRate"
          placeholder="请输入合规率"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="校验结论" prop="ckConclusion">
        <el-input
          v-model="queryParams.ckConclusion"
          placeholder="请输入校验结论"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="校验人" prop="ckUser">
        <el-input
          v-model="queryParams.ckUser"
          placeholder="请输入校验人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="校验时间" prop="ckTime">
        <el-date-picker
          v-model="queryParams.ckTime"
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
      <el-form-item label="复核状态" prop="reviewStatus">
        <el-select
          v-model="queryParams.reviewStatus"
          placeholder="请选择复核状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="复核人" prop="reviewUser">
        <el-input
          v-model="queryParams.reviewUser"
          placeholder="请输入复核人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="复核时间" prop="reviewTime">
        <el-date-picker
          v-model="queryParams.reviewTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
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
          v-hasPermi="['datacenter:pol-compliance-ck:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:pol-compliance-ck:export']"
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
      <el-table-column label="校验ID" align="center" prop="complianceCkId" />
      <el-table-column label="关联方案ID" align="center" prop="relSchemeId" />
      <el-table-column label="关联方案名称" align="center" prop="relSchemeName" />
      <el-table-column label="校验政策IDs" align="center" prop="ckPolIds" />
      <el-table-column label="校验政策名称" align="center" prop="ckPolNames" />
      <el-table-column label="校验状态" align="center" prop="ckStatus" />
      <el-table-column label="风险等级" align="center" prop="riskLevel" />
      <el-table-column label="风险点数量" align="center" prop="riskPtCount" />
      <el-table-column label="合规率" align="center" prop="complianceRate" />
      <el-table-column label="校验结论" align="center" prop="ckConclusion" />
      <el-table-column label="校验人" align="center" prop="ckUser" />
      <el-table-column
        label="校验时间"
        align="center"
        prop="ckTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="整改建议" align="center" prop="rectificationSugg" />
      <el-table-column label="复核状态" align="center" prop="reviewStatus" />
      <el-table-column label="复核人" align="center" prop="reviewUser" />
      <el-table-column
        label="复核时间"
        align="center"
        prop="reviewTime"
        :formatter="dateFormatter"
        width="180px"
      />
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
            v-hasPermi="['datacenter:pol-compliance-ck:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:pol-compliance-ck:delete']"
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
  <PolComplianceCkForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { PolComplianceCkApi, PolComplianceCkVO } from '@/api/dataHub/analysisDecision/polcomplianceck'
import PolComplianceCkForm from './PolComplianceCkForm.vue'

/** 政策合规校验 列表 */
defineOptions({ name: 'PolComplianceCk' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PolComplianceCkVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  complianceCkId: undefined,
  relSchemeId: undefined,
  relSchemeName: undefined,
  ckPolIds: undefined,
  ckPolNames: undefined,
  ckStatus: undefined,
  riskLevel: undefined,
  riskPtCount: undefined,
  complianceRate: undefined,
  ckConclusion: undefined,
  ckUser: undefined,
  ckTime: [],
  rectificationSugg: undefined,
  reviewStatus: undefined,
  reviewUser: undefined,
  reviewTime: [],
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
    const data = await PolComplianceCkApi.getPolComplianceCkPage(queryParams)
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
    await PolComplianceCkApi.deletePolComplianceCk(id)
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
    const data = await PolComplianceCkApi.exportPolComplianceCk(queryParams)
    download.excel(data, '政策合规校验.xls')
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
