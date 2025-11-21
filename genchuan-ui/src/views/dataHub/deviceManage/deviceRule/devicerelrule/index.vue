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
      <el-form-item label="关联规则ID" prop="relRuleId">
        <el-input
          v-model="queryParams.relRuleId"
          placeholder="请输入关联规则ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备分类ID" prop="deviceCatId">
        <el-input
          v-model="queryParams.deviceCatId"
          placeholder="请输入设备分类ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备分类名称" prop="deviceCatName">
        <el-input
          v-model="queryParams.deviceCatName"
          placeholder="请输入设备分类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联对象类型" prop="relObjType">
        <el-input
          v-model="queryParams.relObjType"
          placeholder="请输入关联对象类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联对象名称" prop="relObjName">
        <el-input
          v-model="queryParams.relObjName"
          placeholder="请输入关联对象名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="queryParams.enableStatus"
          placeholder="请选择启用状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
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
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:device-rel-rule:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:device-rel-rule:export']"
        >
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
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="关联规则ID" align="center" prop="relRuleId" width="140" />
      <el-table-column label="设备分类ID" align="center" prop="deviceCatId" width="120" />
      <el-table-column label="设备分类名称" align="center" prop="deviceCatName" width="140" />
      <el-table-column label="关联对象类型" align="center" prop="relObjType" width="140" />
      <el-table-column label="关联对象名称" align="center" prop="relObjName" width="140" />
      <el-table-column label="关联必选标识" align="center" prop="isRequired" width="120">
        <template #default="scope">
          <span :class="scope.row.isRequired === '1' ? 'text-[#eb2553ff]' : 'text-gray-600'">
            {{ scope.row.isRequired === '1' ? '必填' : '可选' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="enableStatus" width="100">
        <template #default="scope">
          <span :class="scope.row.enableStatus === '1' ? 'status-enabled' : 'status-disabled'">
            {{ scope.row.enableStatus === '1' ? '启用' : '禁用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:device-rel-rule:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:device-rel-rule:delete']"
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
  <DeviceRelRuleForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceRelRuleDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceRelRuleApi,
  DeviceRelRuleVO
} from '@/api/dataHub/deviceManage/deviceRule/devicerelrule'
import DeviceRelRuleForm from './DeviceRelRuleForm.vue'
import DeviceRelRuleDetail from './DeviceRelRuleDetail.vue'

/** 设备关联规则配置 列表 */
defineOptions({ name: 'DeviceRelRule' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceRelRuleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  relRuleId: undefined,
  deviceCatId: undefined,
  deviceCatName: undefined,
  relObjType: undefined,
  relObjId: undefined,
  relObjName: undefined,
  isRequired: undefined,
  relCkRule: undefined,
  enableStatus: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceRelRuleApi.getDeviceRelRulePage(queryParams)
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

/** 详情操作 */
const detailRef = ref()
const openDetail = (row: DeviceRelRuleVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceRelRuleApi.deleteDeviceRelRule(id)
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
    const data = await DeviceRelRuleApi.exportDeviceRelRule(queryParams)
    download.excel(data, '设备关联规则配置.xls')
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

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.status-enabled {
  color: #10b981;
  background-color: rgba(16, 185, 129, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-disabled {
  color: #ef4444;
  background-color: rgba(239, 68, 68, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  font-weight: 600;
  color: #4b5563;
}

::v-deep .el-table td,
::v-deep .el-table th {
  padding: 12px 0;
}
</style>
