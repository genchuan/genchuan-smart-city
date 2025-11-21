<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="制定机关" prop="developingAgencies" label-width="120px">
        <el-input
          v-model="queryParams.developingAgencies"
          placeholder="请输入制定机关"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="政策法规名称" prop="nameOfPolicyAndRegulation">
        <el-input
          v-model="queryParams.nameOfPolicyAndRegulation"
          placeholder="请输入政策法规名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="文号" prop="documentNumber">
        <el-input
          v-model="queryParams.documentNumber"
          placeholder="请输入文号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
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
      <el-form-item style="margin-left: 50px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:policy-and-regulation-input:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:policy-and-regulation-input:export']"
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
      <el-table-column label="制定机关" align="center" prop="developingAgencies" />
      <el-table-column label="政策法规名称" min-width="150" align="center" prop="nameOfPolicyAndRegulation" />
      <el-table-column label="文号" align="center" prop="documentNumber" />
      <el-table-column
        label="发布日期"
        align="center"
        prop="releaseDate"
        :formatter="dateFormatter2"
      />
      <el-table-column
        label="生效日期"
        align="center"
        prop="effectiveDate"
        :formatter="dateFormatter2"
      />
      <el-table-column label="失效日期" align="center" prop="expiringDate"  :formatter="dateFormatter2"/>
      <el-table-column label="法规类别" align="center" prop="regulatoryCategory">
        <template #default="scope">
          <span v-for="(item,key) in regulatoryCategoryOptions" :key="key">
            <span v-if="scope.row.regulatoryCategory==item.value">
              {{item.label}}
            </span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="所属领域" align="center" prop="isArea" />
      <el-table-column label="适用范围" align="center" prop="scopeOfApplication" />
      <el-table-column label="正文内容" align="center" prop="mainContent" />
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
            v-hasPermi="['smartcity:policy-and-regulation-input:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:policy-and-regulation-input:delete']"
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
  <PolicyAndRegulationInputForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { PolicyAndRegulationInputApi, PolicyAndRegulationInputVO } from '@/api/smartcity/policyandregulationinput'
import PolicyAndRegulationInputForm from './PolicyAndRegulationInputForm.vue'
import {
  ClassificationOfPoliciesAndRegulationsApi
} from '@/api/smartcity/classificationofpoliciesandregulations'

/** 政策法规录入 列表 */
defineOptions({ name: 'PolicyAndRegulationInput' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PolicyAndRegulationInputVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  developingAgencies: undefined,
  nameOfPolicyAndRegulation: undefined,
  documentNumber: undefined,
  mainContent: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PolicyAndRegulationInputApi.getPolicyAndRegulationInputPage(queryParams)
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
    await PolicyAndRegulationInputApi.deletePolicyAndRegulationInput(id)
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
    const data = await PolicyAndRegulationInputApi.exportPolicyAndRegulationInput(queryParams)
    download.excel(data, '政策法规录入.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

let regulatoryCategoryOptions = ref([]);//法规分类

const initData = async () => {
  let queryParams={
    pageNo:1,
    pageSize:100
  }
  const data = await ClassificationOfPoliciesAndRegulationsApi.getClassificationOfPoliciesAndRegulationsPage(queryParams);
  regulatoryCategoryOptions.value = data.list.map(item => ({
    label: item.regulatoryCategory,
    value:item.id
  }));
}

/** 初始化 */
onMounted(() => {
  getList();
  initData();
})
</script>
