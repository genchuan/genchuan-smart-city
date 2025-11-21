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
      <el-form-item label="部件编号" prop="partNumber" label-width="120px">
        <el-input
          v-model="queryParams.partNumber"
          placeholder="请输入部件编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件名称" prop="componentName" style="margin-left: 50px;">
        <el-input
          v-model="queryParams.componentName"
          placeholder="请输入部件名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件类型" prop="partType" style="margin-left: 50px;">
        <el-select
          v-model="queryParams.partType"
          placeholder="请选择部件类型"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_PART_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属区域" prop="belongingRegion" style="margin-left: 50px;">
        <el-input
          v-model="queryParams.belongingRegion"
          placeholder="请输入所属区域"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="部件状态" prop="componentStatus" label-width="120px">
        <el-select
          v-model="queryParams.componentStatus"
          placeholder="请选择部件状态"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getStrDictOptions(DICT_TYPE.SM_STATE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item style="margin-left: 220px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:component-information:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:component-information:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="60px" />
      <el-table-column label="部件编号" align="center" prop="partNumber" width="160px" />
      <el-table-column label="部件名称" align="center" prop="componentName" />
      <el-table-column label="部件类型" align="center" prop="partType">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.SM_PART_TYPE" :value="scope.row.partType" />
        </template>
      </el-table-column>
      <el-table-column label="所属区域" align="center" prop="belongingRegion" />
      <el-table-column label="安装位置" align="center" prop="installationPosition" />
      <el-table-column label="经纬度坐标" align="center" prop="latitudeLongitude" min-width="90px"/>
      <el-table-column
        label="建设日期"
        align="center"
        prop="constructionDate"
        :formatter="dateFormatter2"
        width="110px"
      />
      <el-table-column label="管理部门" align="center" prop="administrativeDepartment" />
      <el-table-column label="维护单位" align="center" prop="maintenanceUnit" />
      <el-table-column label="联系电话" align="center" prop="contactNumber" />
      <el-table-column label="部件状态" align="center" prop="componentStatus">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.SM_STATE" :value="scope.row.componentStatus" />
        </template>
      </el-table-column>
      <!-- <el-table-column label="设备图片" align="center" prop="specificationAndModel" /> -->
      <el-table-column label="使用寿命" align="center" prop="serviceLife" />
      <el-table-column label="关联事件记录" align="center" prop="relatedEventRecords" min-width="100px" />
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
            v-hasPermi="['smartcity:component-information:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:component-information:delete']"
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
  <ComponentInformationForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { dateFormatter, dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  ComponentInformationApi,
  ComponentInformationVO
} from '@/api/smartcity/componentinformation'
import ComponentInformationForm from './ComponentInformationForm.vue'

/** 部件信息 列表 */
defineOptions({ name: 'ComponentInformation' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ComponentInformationVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  partNumber: undefined,
  componentName: undefined,
  partType: undefined,
  belongingRegion: undefined,
  componentStatus: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ComponentInformationApi.getComponentInformationPage(queryParams)
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
    await ComponentInformationApi.deleteComponentInformation(id)
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
    const data = await ComponentInformationApi.exportComponentInformation(queryParams)
    download.excel(data, '部件信息.xls')
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
