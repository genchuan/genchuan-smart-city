<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="130px"
    >
      <!-- 只保留常用搜索字段，隐藏部分扩展字段 -->
      <el-form-item label="分域ID" prop="domId">
        <el-input
          v-model="queryParams.domId"
          placeholder="请输入分域ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input
          v-model="queryParams.domName"
          placeholder="请输入分域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分域编码" prop="domCode">
        <el-input
          v-model="queryParams.domCode"
          placeholder="请输入分域编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="管理事项大类名称" prop="matterMajorName">
        <el-input
          v-model="queryParams.matterMajorName"
          placeholder="请输入管理事项大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="管理事项小类名称" prop="matterMinorName">
        <el-input
          v-model="queryParams.matterMinorName"
          placeholder="请输入管理事项小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处置部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入处置部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分域状态" prop="domStatus">
        <el-select
          v-model="queryParams.domStatus"
          placeholder="请选择分域状态"
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
          class="!w-320px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
      <el-table-column label="分域名称" align="center" prop="domName" min-width="120" />
      <el-table-column label="分域编码" align="center" prop="domCode" min-width="120" />
      <el-table-column label="管理事项大类" align="center" min-width="150">
        <template #default="scope">{{ scope.row.matterMajorName }}</template>
      </el-table-column>
      <el-table-column label="管理事项小类" align="center" min-width="150">
        <template #default="scope">{{ scope.row.matterMinorName }}</template>
      </el-table-column>
      <el-table-column label="关联点位数量" align="center" prop="relPtCount" width="120" />
      <el-table-column label="关联事件数量" align="center" prop="relEvtCount" width="120" />
      <el-table-column label="处置部门" align="center" prop="deptName" min-width="120" />
      <el-table-column label="分域状态" align="center" width="100">
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
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-2">
            查看
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)" class="mr-2">
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DomDivMngMatterForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomDivMngMatterDetail ref="detailRef" @close="handleDetailClose" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDivMngMatterApi,
  DomDivMngMatterVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmngmatter'
import DomDivMngMatterForm from './DomDivMngMatterForm.vue'
import DomDivMngMatterDetail from './DomDivMngMatterDetail.vue'

/** 管理事项分域 列表 */
defineOptions({ name: 'DomDivMngMatter' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDivMngMatterVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  matterMajorId: undefined,
  matterMajorName: undefined,
  matterMinorId: undefined,
  matterMinorName: undefined,
  relPtCount: undefined,
  relEvtCount: undefined,
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

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DomDivMngMatterApi.getDomDivMngMatterPage(queryParams)
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

/** 查看详情 */
const detailRef = ref()
const openDetail = (row: DomDivMngMatterVO) => {
  detailRef.value.open(row)
}

const handleDetailClose = () => {
  // 详情关闭后的操作
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomDivMngMatterApi.deleteDomDivMngMatter(id)
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
    const data = await DomDivMngMatterApi.exportDomDivMngMatter(queryParams)
    download.excel(data, '管理事项分域.xls')
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
