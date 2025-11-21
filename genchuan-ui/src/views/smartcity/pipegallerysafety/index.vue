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
      <el-form-item label="管廊名称" prop="pipeGalleryName">
        <el-input
          v-model="queryParams.pipeGalleryName"
          placeholder="请输入管廊名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="管廊位置" prop="locationGallery">
        <el-input
          v-model="queryParams.locationGallery"
          placeholder="请输入管廊位置"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="管廊编号" prop="pipeGalleryNumber">
        <el-input
          v-model="queryParams.pipeGalleryNumber"
          placeholder="请输入管廊编号"
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
          v-hasPermi="['smartcity:pipe-gallery-safety:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:pipe-gallery-safety:export']"
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
      <el-table-column label="管廊名称" align="center" prop="pipeGalleryName" />
      <el-table-column label="管廊位置" align="center" prop="locationGallery" />
      <el-table-column label="管廊编号" align="center" prop="pipeGalleryNumber" />
      <el-table-column label="入廊管线类型" align="center" prop="typePipeline" />
      <el-table-column label="入廊管线数量" align="center" prop="numberOfEntrancePipelines" />
      <el-table-column label="管廊结构安全状况" align="center" prop="safetyStatusStructure" />
      <el-table-column label="管廊渗漏水情况" align="center" prop="leakagePipeGallery" />
      <el-table-column label="温湿度" align="center" prop="temperatureAndHumidity" />
      <el-table-column label="有害气体浓度" align="center" prop="harmfulGasConcentration" />
      <el-table-column label="氧气含量" align="center" prop="oxygenContent" />
      <el-table-column label="通风设备运行状态" align="center" prop="operationVentilationEquipment" />
      <el-table-column label="预警级别" align="center" prop="warningLevel" />
      <el-table-column label="维修记录" align="center" prop="maintenanceRecord" />
      <el-table-column
        label="维修时间"
        align="center"
        prop="maintenanceTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="应急物资储备情况" align="center" prop="emergencyMaterialSituation" />
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
            v-hasPermi="['smartcity:pipe-gallery-safety:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:pipe-gallery-safety:delete']"
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
  <PipeGallerySafetyForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { PipeGallerySafetyApi, PipeGallerySafetyVO } from '@/api/smartcity/pipegallerysafety'
import PipeGallerySafetyForm from './PipeGallerySafetyForm.vue'

/** 管廊安全 列表 */
defineOptions({ name: 'PipeGallerySafety' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PipeGallerySafetyVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  pipeGalleryName: undefined,
  locationGallery: undefined,
  pipeGalleryNumber: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PipeGallerySafetyApi.getPipeGallerySafetyPage(queryParams)
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
    await PipeGallerySafetyApi.deletePipeGallerySafety(id)
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
    const data = await PipeGallerySafetyApi.exportPipeGallerySafety(queryParams)
    download.excel(data, '管廊安全.xls')
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