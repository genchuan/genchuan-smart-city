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
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="人员ID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入人员ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="人员姓名" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属部门" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入所属部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="personPhone">
        <el-input
          v-model="queryParams.personPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="人员状态" prop="personStatus">
        <el-select
          v-model="queryParams.personStatus"
          placeholder="请选择人员状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="true" />
          <el-option label="禁用" value="false" />
        </el-select>
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
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
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
      <el-table-column label="主键" align="center" prop="id" width="80" />
      <el-table-column label="人员姓名" align="center" prop="personName" width="120" />
      <el-table-column label="人员账号" align="center" prop="personAccount" width="140" />
      <el-table-column label="所属部门" align="center" prop="deptName" width="160" />
      <el-table-column label="联系电话" align="center" prop="personPhone" width="140" />
      <el-table-column label="负责网格" align="center" prop="gridName" width="160" />
      <el-table-column label="人员角色" align="center" prop="personRole" width="120" />
      <el-table-column label="人员状态" align="center" prop="personStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.personStatus ? 'success' : 'danger'">
            {{ scope.row.personStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="入职日期"
        align="center"
        prop="entryDate"
        :formatter="dateFormatter"
        width="140"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectPersonInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectPersonInfoDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectPersonInfoApi,
  InspectPersonInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/staffManage'
import InspectPersonInfoForm from './InspectPersonInfoForm.vue'
import InspectPersonInfoDetail from './InspectPersonInfoDetail.vue'

/** 巡查巡检人员信息 列表 */
defineOptions({ name: 'InspectPersonInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectPersonInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  personId: undefined,
  personName: undefined,
  personAccount: undefined,
  deptCode: undefined,
  deptName: undefined,
  personPhone: undefined,
  gridId: undefined,
  gridName: undefined,
  personRole: undefined,
  deviceCode: undefined,
  personStatus: undefined,
  entryDate: [],
  createUser: undefined,
  bizCreateTime: [],
  updateUser: undefined,
  bizUpdateTime: [],
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
    const data = await InspectPersonInfoApi.getInspectPersonInfoPage(queryParams)
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
const openDetail = (row: InspectPersonInfoVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectPersonInfoApi.deleteInspectPersonInfo(id)
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
    const data = await InspectPersonInfoApi.exportInspectPersonInfo(queryParams)
    download.excel(data, '巡查巡检人员信息.xls')
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #333;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
