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
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="车辆品牌" prop="vehicleBrand">
        <el-input
          v-model="queryParams.vehicleBrand"
          placeholder="请输入车辆品牌"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="车辆型号" prop="model">
        <el-input
          v-model="queryParams.model"
          placeholder="请输入车辆型号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="车辆颜色" prop="vehicleColor">
        <el-input
          v-model="queryParams.vehicleColor"
          placeholder="请输入车辆颜色"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 30px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:law-enforcement-vehicle:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:law-enforcement-vehicle:export']"
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
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
      <el-table-column label="车架号" align="center" prop="vin" />
      <el-table-column label="发动机号" align="center" prop="engineNo" />
      <el-table-column label="购置时间" align="center" prop="purchaseTime" />
      <el-table-column
        label="登记注册日期"
        align="center"
        prop="registrationDate"
        :formatter="dateFormatter2"
        width="180px"
      />
      <el-table-column label="所属执法部门" min-width="110" align="center" prop="belongingDepartment" />
      <el-table-column label="使用性质" align="center" prop="natureOfUse" />
      <el-table-column label="车辆用途" align="center" prop="vehicleUsage" />
      <el-table-column label="车辆状态" align="center" width="150px">
        <template #default="scope">
          <!-- 与执法车辆状态单选框保持一致，值与显示名称对应 -->
          <template v-if="scope.row.vehicleStatus === 'standby'">执法待命</template>
          <template v-else-if="scope.row.vehicleStatus === 'onDuty'">执行任务中</template>
          <template v-else-if="scope.row.vehicleStatus === 'refueling'">加油/充电中</template>
          <template v-else-if="scope.row.vehicleStatus === 'maintenance'">维修中</template>
          <template v-else-if="scope.row.vehicleStatus === 'service'">定期保养</template>
          <template v-else-if="scope.row.vehicleStatus === 'impounded'">暂扣/封存</template>
          <template v-else-if="scope.row.vehicleStatus === 'outOfService'">停用报废</template>
          <!-- 兜底：若出现未定义状态，显示原始值避免空白 -->
          <template v-else>{{ scope.row.vehicleStatus }}</template>
        </template>
      </el-table-column>
      <el-table-column
        label="年检到期日期"
        align="center"
        prop="annualInspectionDate"
        :formatter="dateFormatter2"
        width="180px"
      />
      <el-table-column
        label="保险截止日期"
        align="center"
        prop="insuranceDeadline"
        :formatter="dateFormatter2"
        width="180px"
      />
      <el-table-column label="保险类型" align="center" prop="typesOfInsurance" />
      <el-table-column label="驾驶员姓名" min-width="110" align="center" prop="driverName" />
      <el-table-column label="驾驶员联系方式" min-width="130" align="center" prop="driverContactInformation" />
      <el-table-column label="行驶里程" align="center" prop="mileage" />
      <el-table-column label="维修记录" align="center" prop="maintenanceRecord" />
      <el-table-column label="保养记录" align="center" prop="maintenanceRecords" />
      <el-table-column label="加油记录" align="center" prop="refuelingRecord" />
      <el-table-column label="违章记录" align="center" prop="violationRecords" />
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
            v-hasPermi="['smartcity:law-enforcement-vehicle:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:law-enforcement-vehicle:delete']"
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
  <LawEnforcementVehicleForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { LawEnforcementVehicleApi, LawEnforcementVehicleVO } from '@/api/smartcity/lawenforcementvehicle'
import LawEnforcementVehicleForm from './LawEnforcementVehicleForm.vue'

/** 执法车辆管理 列表 */
defineOptions({ name: 'LawEnforcementVehicle' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<LawEnforcementVehicleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  licensePlateNumber: undefined,
  vehicleBrand: undefined,
  model: undefined,
  vehicleColor: undefined,
  vin: undefined,
  engineNo: undefined,
  purchaseTime: [],
  registrationDate: [],
  belongingDepartment: undefined,
  natureOfUse: undefined,
  vehicleUsage: undefined,
  vehicleStatus: undefined,
  annualInspectionDate: [],
  insuranceDeadline: undefined,
  insuranceDeadline: [],
  typesOfInsurance: undefined,
  driverName: undefined,
  driverContactInformation: undefined,
  mileage: undefined,
  maintenanceRecord: undefined,
  maintenanceRecords: undefined,
  refuelingRecord: undefined,
  violationRecords: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await LawEnforcementVehicleApi.getLawEnforcementVehiclePage(queryParams)
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
    await LawEnforcementVehicleApi.deleteLawEnforcementVehicle(id)
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
    const data = await LawEnforcementVehicleApi.exportLawEnforcementVehicle(queryParams)
    download.excel(data, '执法车辆管理.xls')
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
