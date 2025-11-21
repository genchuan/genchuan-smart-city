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
      <el-form-item label="分域ID" prop="domId">
        <el-input
          v-model="queryParams.domId"
          placeholder="请输入分域ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input
          v-model="queryParams.domName"
          placeholder="请输入分域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分极编码" prop="domCode">
        <el-input
          v-model="queryParams.domCode"
          placeholder="请输入分极编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="监测部件大类" prop="monCompMajorName">
        <el-input
          v-model="queryParams.monCompMajorName"
          placeholder="请输入监测部件大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域状态" prop="domStatus">
        <el-select
          v-model="queryParams.domStatus"
          placeholder="请选择分域状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
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
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="分域ID" align="center" prop="domId" width="120" />
      <el-table-column label="分域名称" align="center" prop="domName" width="160" />
      <el-table-column label="分极编码" align="center" prop="domCode" width="120" />
      <el-table-column label="监测部件大类" align="center" prop="monCompMajorName" width="160" />
      <el-table-column label="监测部件小类" align="center" prop="monCompMinorName" width="160" />
      <el-table-column label="关联设备数量(个)" align="center" prop="relDeviceCount" width="140" />
      <el-table-column label="设备在线率(%)" align="center" prop="deviceOnlineRate" width="130" />
      <el-table-column label="维护部门" align="center" prop="deptName" width="140" />
      <el-table-column label="分域状态" align="center" prop="domStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.domStatus ? 'success' : 'danger'">
            {{ scope.row.domStatus ? '启用' : '停用' }}
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
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
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
  <DomDivMonCompForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomDivMonCompDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDivMonCompApi,
  DomDivMonCompVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmoncomp'
import DomDivMonCompForm from './DomDivMonCompForm.vue'
import DomDivMonCompDetail from './DomDivMonCompDetail.vue'

/** 监测部件分域 列表 */
defineOptions({ name: 'DomDivMonComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDivMonCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  monCompMajorId: undefined,
  monCompMajorName: undefined,
  monCompMinorId: undefined,
  monCompMinorName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  deviceOnlineRate: undefined,
  deptCode: undefined,
  deptName: undefined,
  domStatus: undefined,
  createTimeBiz: undefined,
  updateTimeBiz: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const openDetail = (row: DomDivMonCompVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DomDivMonCompApi.getDomDivMonCompPage(queryParams)
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
    await DomDivMonCompApi.deleteDomDivMonComp(id)
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
    const data = await DomDivMonCompApi.exportDomDivMonComp(queryParams)
    download.excel(data, '监测部件分域.xls')
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

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #4b5563;
}

::v-deep .el-table tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
