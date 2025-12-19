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
      <el-form-item label="文书类型" prop="documentType">
        <el-input
          v-model="queryParams.documentType"
          placeholder="请输入文书类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
<!--      <el-form-item label="审批时间" prop="approvalTime">
        <el-date-picker
          v-model="queryParams.approvalTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>-->
      <el-form-item label="审批状态" prop="approvalStatus">
        <el-input
          v-model="queryParams.approvalStatus"
          placeholder="请输入审批状态"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:law-document:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:law-document:export']"
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
      <el-table-column label="文书类型" align="center" prop="documentType" />
      <el-table-column label="文书编号" align="center" prop="documentCode" />
      <el-table-column label="文书标题" align="center" prop="documentTitle" />
      <el-table-column label="文书内容" align="center" prop="documentContent" />
      <el-table-column label="创建人" align="center" prop="documentCreator" />
      <el-table-column label="审批人" align="center" prop="approver" />
      <el-table-column
        label="审批时间"
        align="center"
        prop="approvalTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column label="审批状态" align="center" prop="approvalStatus" />
      <el-table-column label="签署人" align="center" prop="signatory" />
      <el-table-column
        label="签署时间"
        align="center"
        prop="signTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column label="盖章状态" align="center" prop="sealStatus" />
      <el-table-column
        label="盖章时间"
        align="center"
        prop="sealTime"
        :formatter="dateFormatter2"
        width="120px"
      />
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
            v-hasPermi="['smartcity:law-document:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:law-document:delete']"
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
  <LawDocumentForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { LawDocumentApi, LawDocumentVO } from '@/api/smartcity/lawdocument'
import LawDocumentForm from './LawDocumentForm.vue'

/** 执法文书 列表 */
defineOptions({ name: 'LawDocument' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<LawDocumentVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  caseId: undefined,
  documentType: undefined,
  approvalTime: [],
  approvalStatus: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await LawDocumentApi.getLawDocumentPage(queryParams)
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
    await LawDocumentApi.deleteLawDocument(id)
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
    const data = await LawDocumentApi.exportLawDocument(queryParams)
    download.excel(data, '执法文书.xls')
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
