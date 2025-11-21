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
      <el-form-item label="车牌号" prop="licensePlateNumber">
        <el-input
          v-model="queryParams.licensePlateNumber"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="车辆品牌" prop="vehicleBrand">
        <el-input
          v-model="queryParams.vehicleBrand"
          placeholder="请输入车辆品牌"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <!--<el-form-item label="车辆型号" prop="model">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.model"-->
      <!--    placeholder="请输入车辆型号"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="车主联系电话" prop="carOwnerPhoneNumber">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.carOwnerPhoneNumber"-->
      <!--    placeholder="请输入车主联系电话"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="车辆颜色" prop="vehicleColor">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.vehicleColor"-->
      <!--    placeholder="请输入车辆颜色"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="座位数" prop="seats">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.seats"-->
      <!--    placeholder="请输入座位数"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="出厂日期" prop="dateOfProduction">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.dateOfProduction"-->
      <!--    placeholder="请输入出厂日期"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="购置价格" prop="purchasePrice">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.purchasePrice"-->
      <!--    placeholder="请输入购置价格"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="使用性质" prop="natureOfUse">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.natureOfUse"-->
      <!--    placeholder="请输入使用性质"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="年检有效期" prop="annualInspectionPeriod">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.annualInspectionPeriod"-->
      <!--    placeholder="请输入年检有效期"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="保险有效期" prop="periodOfInsurance">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.periodOfInsurance"-->
      <!--    placeholder="请输入保险有效期"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="保险类型" prop="typesOfInsurance">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.typesOfInsurance"-->
      <!--    placeholder="请输入保险类型"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="环保排放标准" prop="environmentalStandards">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.environmentalStandards"-->
      <!--    placeholder="请输入环保排放标准"-->
      <!--    clearable-->
      <!--    @keyup.enter="handleQuery"-->
      <!--    class="!w-240px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <!--<el-form-item label="车辆用途" prop="vehicleUsage">-->
      <!--  <el-input-->
      <!--    v-model="queryParams.vehicleUsage"-->
      <!--    placeholder="请输入车辆用途"-->
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
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:vehicle-information:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:vehicle-information:export']"
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
      <el-table-column label="车牌号" align="center" prop="licensePlateNumber" />
      <el-table-column label="车辆品牌" align="center" prop="vehicleBrand" />
      <el-table-column label="车辆型号" align="center" prop="model" />
      <el-table-column label="车辆类型" align="center" prop="vehicleType" />
      <el-table-column label="车架号" align="center" prop="vin" />
      <el-table-column label="发动机号" align="center" prop="engineNo" />
      <el-table-column
        label="注册日期"
        align="center"
        prop="registrationDate"
        :formatter="dateFormatter2"
        width="180px"
      />
      <el-table-column label="登记车主姓名" align="center" prop="registerTheName" />
      <el-table-column label="车主身份证号" align="center" prop="ownerIdNumberNumber" />
      <el-table-column label="车主联系电话" align="center" prop="carOwnerPhoneNumber" />
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
      <el-table-column label="座位数" align="center" prop="seats" />
      <el-table-column label="出厂日期" align="center" prop="dateOfProduction" />
      <el-table-column label="购置价格" align="center" prop="purchasePrice" />
      <el-table-column label="使用性质" align="center" prop="natureOfUse" />
      <el-table-column label="年检有效期" align="center" prop="annualInspectionPeriod" />
      <el-table-column label="保险有效期" align="center" prop="periodOfInsurance" />
      <el-table-column label="保险类型" align="center" prop="typesOfInsurance" />
      <el-table-column label="环保排放标准" align="center" prop="environmentalStandards" />
      <el-table-column label="车辆用途" align="center" prop="vehicleUsage" />
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
            v-hasPermi="['smartcity:vehicle-information:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:vehicle-information:delete']"
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
  <VehicleInformationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { VehicleInformationApi, VehicleInformationVO } from '@/api/smartcity/vehicleinformation'
import VehicleInformationForm from './VehicleInformationForm.vue'

/** 车辆信息 列表 */
defineOptions({ name: 'VehicleInformation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<VehicleInformationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  licensePlateNumber: undefined,
  vehicleBrand: undefined,
  model: undefined,
  carOwnerPhoneNumber: undefined,
  vehicleColor: undefined,
  seats: undefined,
  dateOfProduction: undefined,
  purchasePrice: undefined,
  natureOfUse: undefined,
  annualInspectionPeriod: undefined,
  periodOfInsurance: undefined,
  typesOfInsurance: undefined,
  environmentalStandards: undefined,
  vehicleUsage: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await VehicleInformationApi.getVehicleInformationPage(queryParams)
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
    await VehicleInformationApi.deleteVehicleInformation(id)
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
    const data = await VehicleInformationApi.exportVehicleInformation(queryParams)
    download.excel(data, '车辆信息.xls')
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
