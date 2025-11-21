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
      <el-form-item label="姓名" prop="fullName">
        <el-input
          v-model="queryParams.fullName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input
          v-model="queryParams.idNumber"
          placeholder="请输入身份证号"
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
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:personnel-information:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:personnel-information:export']"
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
      <el-table-column label="姓名" align="center" prop="fullName" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column
        label="出生日期"
        align="center"
        prop="dateOfBirth"
        :formatter="dateFormatter2"
      />
      <el-table-column label="身份证号" align="center" prop="idNumber" />
      <el-table-column label="民族" align="center" prop="nationality" />
      <el-table-column label="户籍地址" align="center" prop="registeredResidenceAddress" />
      <el-table-column label="现居住地址" align="center" prop="currentResidentialAddress" />
      <el-table-column label="联系电话" align="center" prop="contactNumber" />
      <el-table-column label="电子邮箱" align="center" prop="eMail" />
      <!--<el-table-column label="婚姻状况" align="center" prop="maritalStatus" />-->
      <el-table-column label="学历" align="center" prop="educationalBackground" />
      <el-table-column label="毕业院校" align="center" prop="almaMater" />
      <el-table-column label="专业" align="center" prop="major" />
      <el-table-column label="工作单位" align="center" prop="workUnit" />
      <el-table-column label="职业" align="center" prop="career" />
      <el-table-column
        label="入职时间"
        align="center"
        prop="entryTime"
        :formatter="dateFormatter2"
      />
      <el-table-column
        label="离职时间（若已离职）"
        align="center"
        prop="resignationTime"
        :formatter="dateFormatter2"
      />
      <el-table-column label="紧急联系人姓名" align="center" prop="emergencyContactName" />
      <el-table-column label="紧急联系人电话" align="center" prop="emergencyContactPhone" />
      <el-table-column label="紧急联系人关系" align="center" prop="emergencyContactRelationship" />
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
            v-hasPermi="['smartcity:personnel-information:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:personnel-information:delete']"
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
  <PersonnelInformationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { PersonnelInformationApi, PersonnelInformationVO } from '@/api/smartcity/personnelinformation'
import PersonnelInformationForm from './PersonnelInformationForm.vue'

/** 人员信息 列表 */
defineOptions({ name: 'PersonnelInformation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PersonnelInformationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  fullName: undefined,
  gender: undefined,
  idNumber: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PersonnelInformationApi.getPersonnelInformationPage(queryParams)
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
    await PersonnelInformationApi.deletePersonnelInformation(id)
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
    const data = await PersonnelInformationApi.exportPersonnelInformation(queryParams)
    download.excel(data, '人员信息.xls')
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
