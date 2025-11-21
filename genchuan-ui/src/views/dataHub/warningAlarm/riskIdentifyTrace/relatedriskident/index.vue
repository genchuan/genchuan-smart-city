<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="识别ID" prop="identId">
        <el-input
          v-model="queryParams.identId"
          placeholder="请输入识别ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="触发预警ID" prop="triggerWarnId">
        <el-input
          v-model="queryParams.triggerWarnId"
          placeholder="请输入触发预警ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="触发预警名称" prop="triggerWarnName">
        <el-input
          v-model="queryParams.triggerWarnName"
          placeholder="请输入触发预警名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="触发预警类型" prop="triggerWarnType">
        <el-select
          v-model="queryParams.triggerWarnType"
          placeholder="请选择触发预警类型"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联风险名称" prop="relatedRiskName">
        <el-input
          v-model="queryParams.relatedRiskName"
          placeholder="请输入关联风险名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-input
          v-model="queryParams.riskLevel"
          placeholder="请输入风险等级"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="识别时间" prop="identTime">
        <el-date-picker
          v-model="queryParams.identTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="是否已处置" prop="isHandled">
        <el-input
          v-model="queryParams.isHandled"
          placeholder="请输入是否已处置"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
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
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="识别ID" align="center" prop="identId" />
      <el-table-column label="触发预警ID" align="center" prop="triggerWarnId" />
      <el-table-column label="触发预警名称" align="center" prop="triggerWarnName" />
      <el-table-column label="触发预警类型" align="center" prop="triggerWarnType" />
      <el-table-column label="关联风险名称" align="center" prop="relatedRiskName" />
      <el-table-column label="关联风险类型" align="center" prop="relatedRiskType" />
      <el-table-column label="风险等级" align="center" prop="riskLevel">
        <template #default="scope">
          <span :class="getRiskLevelClass(scope.row.riskLevel)">{{ scope.row.riskLevel }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="识别时间"
        align="center"
        prop="identTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="识别用户姓名" align="center" prop="identifyUserName" />
      <el-table-column label="是否已处置" align="center" prop="isHandled">
        <template #default="scope">
          <el-tag :type="scope.row.isHandled === '1' ? 'success' : 'danger'" size="small">
            {{ scope.row.isHandled === '1' ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <RelatedRiskIdentForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <RelatedRiskIdentDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  RelatedRiskIdentApi,
  RelatedRiskIdentVO
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/relatedriskident'
import RelatedRiskIdentForm from './RelatedRiskIdentForm.vue'
import RelatedRiskIdentDetail from './RelatedRiskIdentDetail.vue'

/** 关联风险识别 列表 */
defineOptions({ name: 'RelatedRiskIdent' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<RelatedRiskIdentVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  identId: undefined,
  triggerWarnId: undefined,
  triggerWarnName: undefined,
  triggerWarnType: undefined,
  relatedRiskName: undefined,
  relatedRiskType: undefined,
  relatedBasis: undefined,
  riskLevel: undefined,
  identTime: [],
  identMethod: undefined,
  identifyUserId: undefined,
  identifyUserName: undefined,
  isHandled: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
/** 详情抽屉 */
const detailRef = ref()
const openDetail = (row: RiskSourceArchVO) => {
  detailRef.value.open(row)
}
const handleDetailClose = () => {
  // 关闭抽屉后的处理
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await RelatedRiskIdentApi.getRelatedRiskIdentPage(queryParams)
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
    await RelatedRiskIdentApi.deleteRelatedRiskIdent(id)
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
    const data = await RelatedRiskIdentApi.exportRelatedRiskIdent(queryParams)
    download.excel(data, '关联风险识别.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 获取风险等级样式 */
const getRiskLevelClass = (level: string) => {
  switch (level) {
    case '高':
      return 'text-red-500 font-medium'
    case '中':
      return 'text-orange-500 font-medium'
    case '低':
      return 'text-green-500 font-medium'
    default:
      return ''
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.search-form {
  padding: 16px;
  background-color: #f9fafb;
  border-radius: 8px;
  margin-bottom: 16px !important;
}

.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

::v-deep .el-table__header-wrapper th {
  background-color: #f5f7fa;
  font-weight: 500;
}

::v-deep .el-table__body tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
