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
      <!--<el-form-item label="所属部门" prop="department">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.department"-->
      <!--    placeholder="请输入所属部门"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="岗位名称" prop="position">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.position"-->
      <!--    placeholder="请输入岗位名称"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
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
          v-hasPermi="['smartcity:management-of-patrol-personnel:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:management-of-patrol-personnel:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <!--<el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="姓名" align="center" prop="fullName" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column
        label="出生日期"
        align="center"
        prop="dateOfBirth"
        :formatter="dateFormatter2"
        width="180px"
      />
      <el-table-column label="联系方式" align="center" prop="contactInformation" />
      <!--<el-table-column label="身份证号" align="center" prop="idNumber" />-->
      <!--<el-table-column label="所属部门" align="center" prop="department" />-->
      <!--<el-table-column label="岗位名称" align="center" prop="position" />-->
      <!--<el-table-column-->
      <!--  label="入职时间"-->
      <!--  align="center"-->
      <!--  prop="entryTime"-->
      <!--  :formatter="dateFormatter"-->
      <!--  width="180px"-->
      <!--/>-->
      <el-table-column label="巡查区域" align="center" prop="patrolArea" />
      <el-table-column label="巡查路线" align="center" prop="patrolRoute" />
      <el-table-column label="巡查班次" align="center" prop="inspectionShift" />
      <!--<el-table-column label="资质证书编号" align="center" prop="qualificationCertificateNumber" />-->
      <!--<el-table-column label="证书有效期" align="center" prop="certificateValidityPeriod" />-->
      <!--<el-table-column label="培训记录" align="center" prop="trainingRecords" />-->
      <el-table-column label="巡查设备编号" align="center" prop="inspectionEquipmentNumber" min-width="110px" />
      <!--<el-table-column label="违规记录" align="center" prop="violationRecord" />-->
      <!--<el-table-column label="奖惩情况" align="center" prop="rewardAndPunishmentSituation" />-->
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
            v-hasPermi="['smartcity:management-of-patrol-personnel:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:management-of-patrol-personnel:delete']"
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
  <ManagementOfPatrolPersonnelForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import {dateFormatter, dateFormatter2} from '@/utils/formatTime'
import download from '@/utils/download'
import { ManagementOfPatrolPersonnelApi, ManagementOfPatrolPersonnelVO } from '@/api/smartcity/managementofpatrolpersonnel'
import ManagementOfPatrolPersonnelForm from './ManagementOfPatrolPersonnelForm.vue'

/** 巡査人员管理 列表 */
defineOptions({ name: 'ManagementOfPatrolPersonnel' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ManagementOfPatrolPersonnelVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  fullName: undefined,
  department: undefined,
  position: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ManagementOfPatrolPersonnelApi.getManagementOfPatrolPersonnelPage(queryParams)
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
    await ManagementOfPatrolPersonnelApi.deleteManagementOfPatrolPersonnel(id)
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
    const data = await ManagementOfPatrolPersonnelApi.exportManagementOfPatrolPersonnel(queryParams)
    download.excel(data, '巡査人员管理.xls')
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
