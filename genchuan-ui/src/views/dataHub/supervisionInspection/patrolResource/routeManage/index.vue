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
      <!-- 只保留常用搜索字段，隐藏部分不常用字段 -->
      <el-form-item label="路线ID" prop="routeId">
        <el-input
          v-model="queryParams.routeId"
          placeholder="请输入路线ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="路线名称" prop="routeName">
        <el-input
          v-model="queryParams.routeName"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="路线编码" prop="routeCode">
        <el-input
          v-model="queryParams.routeCode"
          placeholder="请输入路线编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="所属行政区划" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入所属行政区划名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="负责人员" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入负责人员姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="路线状态" prop="routeStatus">
        <el-select
          v-model="queryParams.routeStatus"
          placeholder="请选择路线状态"
          clearable
          class="!w-200px"
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
      <el-table-column label="路线ID" align="center" prop="routeId" width="120" />
      <el-table-column label="路线名称" align="center" prop="routeName" width="180" />
      <el-table-column label="路线编码" align="center" prop="routeCode" width="120" />
      <el-table-column label="所属行政区划" align="center" prop="areaName" width="150" />
      <el-table-column label="起点名称" align="center" prop="startPtName" width="120" />
      <el-table-column label="终点名称" align="center" prop="endPtName" width="120" />
      <el-table-column label="预计时长" align="center" prop="estimateEndure" width="100" />
      <el-table-column label="巡查频次" align="center" prop="inspectFrequency" width="120" />
      <el-table-column label="负责人员" align="center" prop="personName" width="120" />
      <el-table-column label="路线状态" align="center" prop="routeStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.routeStatus ? 'success' : 'danger'">
            {{ scope.row.routeStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看 </el-button>
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectRouteInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectRouteInfoDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectRouteInfoApi,
  InspectRouteInfoVO
} from '@/api/dataHub/supervisionInspection/patrolResource/routeManage'
import InspectRouteInfoForm from './InspectRouteInfoForm.vue'
import InspectRouteInfoDetail from './InspectRouteInfoDetail.vue'

/** 巡查巡检路线信息 列表 */
defineOptions({ name: 'InspectRouteInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectRouteInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  routeId: undefined,
  routeName: undefined,
  routeCode: undefined,
  areaCode: undefined,
  areaName: undefined,
  personName: undefined,
  routeStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const openDetail = (row: InspectRouteInfoVO) => {
  detailRef.value.open(row)
}
const handleDetailClose = () => {
  // 可以添加关闭后的逻辑
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectRouteInfoApi.getInspectRouteInfoPage(queryParams)
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
    await InspectRouteInfoApi.deleteInspectRouteInfo(id)
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
    const data = await InspectRouteInfoApi.exportInspectRouteInfo(queryParams)
    download.excel(data, '巡查巡检路线信息.xls')
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

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #333;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
