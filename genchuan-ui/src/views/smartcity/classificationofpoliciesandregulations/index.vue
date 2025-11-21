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
      <el-form-item label="法规类别" prop="regulatoryCategory" label-width="200px">
        <el-input
          v-model="queryParams.regulatoryCategory"
          placeholder="请输入法规类别"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属领域" prop="isArea" style="margin-left: 50px;">
        <el-input
          v-model="queryParams.isArea"
          placeholder="请输入所属领域"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <!--<el-form-item label="主题" prop="theme">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.theme"-->
      <!--    placeholder="请输入主题"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
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
      <el-form-item style="margin-left: 100px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:classification-of-policies-and-regulations:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:classification-of-policies-and-regulations:export']"
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
      <el-table-column label="法规类别" align="center" prop="regulatoryCategory" />
      <el-table-column label="所属领域" align="center" prop="isArea" />
      <!--<el-table-column label="主题" align="center" prop="theme" />-->
      <!--<el-table-column label="适用对象" align="center" prop="applicableObjects" />-->
      <!--<el-table-column label="效力层级" align="center" prop="levelOfEffectiveness" />-->
      <!--<el-table-column label="发布部门" align="center" prop="departmentOfPublication" />-->
      <!--<el-table-column label="实施时间" align="center" prop="implementationTime" :formatter="dateFormatter2"/>-->
      <!--<el-table-column label="废止时间 " align="center" prop="revocatoryDate" :formatter="dateFormatter2"/>-->
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
            v-hasPermi="['smartcity:classification-of-policies-and-regulations:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:classification-of-policies-and-regulations:delete']"
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
  <ClassificationOfPoliciesAndRegulationsForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter, dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  ClassificationOfPoliciesAndRegulationsApi,
  ClassificationOfPoliciesAndRegulationsVO
} from '@/api/smartcity/classificationofpoliciesandregulations'
import ClassificationOfPoliciesAndRegulationsForm from './ClassificationOfPoliciesAndRegulationsForm.vue'

/** 政策法规分类 列表 */
defineOptions({ name: 'ClassificationOfPoliciesAndRegulations' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ClassificationOfPoliciesAndRegulationsVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  regulatoryCategory: undefined,
  isArea: undefined,
  theme: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data =
      await ClassificationOfPoliciesAndRegulationsApi.getClassificationOfPoliciesAndRegulationsPage(
        queryParams
      )
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
    await ClassificationOfPoliciesAndRegulationsApi.deleteClassificationOfPoliciesAndRegulations(id)
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
    const data =
      await ClassificationOfPoliciesAndRegulationsApi.exportClassificationOfPoliciesAndRegulations(
        queryParams
      )
    download.excel(data, '政策法规分类.xls')
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
