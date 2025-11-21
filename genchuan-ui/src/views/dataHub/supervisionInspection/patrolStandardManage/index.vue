<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
    >
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="标准ID" prop="stdId">
        <el-input
          v-model="queryParams.stdId"
          placeholder="请输入标准ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="标准名称" prop="stdName">
        <el-input
          v-model="queryParams.stdName"
          placeholder="请输入标准名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="标准编码" prop="stdCode">
        <el-input
          v-model="queryParams.stdCode"
          placeholder="请输入标准编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="标准类型" prop="stdType">
        <el-select
          v-model="queryParams.stdType"
          placeholder="请选择标准类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="标准状态" prop="stdStatus">
        <el-select
          v-model="queryParams.stdStatus"
          placeholder="请选择标准状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="生效时间" prop="effectiveTime">
        <el-date-picker
          v-model="queryParams.effectiveTime"
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
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="标准ID" align="center" prop="stdId" width="120" />
      <el-table-column label="标准名称" align="center" prop="stdName" min-width="150" />
      <el-table-column label="标准编码" align="center" prop="stdCode" width="120" />
      <el-table-column label="标准类型" align="center" prop="stdType" width="120" />
      <el-table-column label="适用区域" align="center" min-width="150">
        <template #default="scope">{{ scope.row.areaName }}({{ scope.row.areaCode }})</template>
      </el-table-column>
      <el-table-column label="合格阈值" align="center" prop="qualifiedThreshold" width="100" />
      <el-table-column label="标准状态" align="center" prop="stdStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.stdStatus ? 'success' : 'danger'">
            {{ scope.row.stdStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="生效时间"
        align="center"
        prop="effectiveTime"
        :formatter="dateFormatter"
        width="160"
      />
      <el-table-column
        label="创建时间"
        align="center"
        prop="bizCreateTime"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)"> 查看 </el-button>
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
  <InspectStdInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectStdInfoDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectStdInfoApi,
  InspectStdInfoVO
} from '@/api/dataHub/supervisionInspection/patrolStandardManage'
import InspectStdInfoForm from './InspectStdInfoForm.vue'
import InspectStdInfoDetail from './InspectStdInfoDetail.vue'

/** 巡查巡检标准信息 列表 */
defineOptions({ name: 'InspectStdInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectStdInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  stdId: undefined,
  stdName: undefined,
  stdCode: undefined,
  stdType: undefined,
  stdStatus: undefined,
  effectiveTime: []
  // 隐藏不常用的搜索字段
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectStdInfoApi.getInspectStdInfoPage(queryParams)
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

/** 查看详情操作 */
const detailRef = ref()
const openDetail = (id: number) => {
  detailRef.value.open(id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectStdInfoApi.deleteInspectStdInfo(id)
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
    const data = await InspectStdInfoApi.exportInspectStdInfo(queryParams)
    download.excel(data, '巡查巡检标准信息.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
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
