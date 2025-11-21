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
      <el-form-item label="监测事件大类" prop="monEvtMajorName">
        <el-input
          v-model="queryParams.monEvtMajorName"
          placeholder="请输入监测事件大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="管控部门" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入管控部门名称"
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
      <el-table-column label="分域名称" align="center" prop="domName" min-width="120" />
      <el-table-column label="分域编码" align="center" prop="domCode" min-width="100" />
      <el-table-column label="监测事件大类" align="center" prop="monEvtMajorName" min-width="140" />
      <el-table-column label="监测事件小类" align="center" prop="monEvtMinorName" min-width="140" />
      <el-table-column label="关联点位数量" align="center" prop="relPtCount" min-width="100">
        <template #default="scope">{{ scope.row.relPtCount || 0 }} 个</template>
      </el-table-column>
      <el-table-column label="关联事件数量" align="center" prop="relEvtCount" min-width="100">
        <template #default="scope">{{ scope.row.relEvtCount || 0 }} 件</template>
      </el-table-column>
      <el-table-column label="事件办结率" align="center" prop="evtCompleteRate" min-width="100">
        <template #default="scope">{{ scope.row.evtCompleteRate || 0 }}%</template>
      </el-table-column>
      <el-table-column label="管控部门" align="center" prop="deptName" min-width="120" />
      <el-table-column label="分域状态" align="center" prop="domStatus" min-width="100">
        <template #default="scope">
          <el-tag :type="scope.row.domStatus ? 'success' : 'danger'">
            {{ scope.row.domStatus ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeBiz"
        :formatter="dateFormatter"
        width="160px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)" class="mr-5px">
            详情
          </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)" class="mr-5px">
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
      class="mt-15px"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DomDivMonEvtForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DomDivMonEvtDetail
    ref="detailRef"
    :visible="detailVisible"
    @update:visible="(val) => (detailVisible = val)"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDivMonEvtApi,
  DomDivMonEvtVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmonevt'
import DomDivMonEvtForm from './DomDivMonEvtForm.vue'
import DomDivMonEvtDetail from './DomDivMonEvtDetail.vue'

/** 监测事件分域 列表 */
defineOptions({ name: 'DomDivMonEvt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDivMonEvtVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  monEvtMajorId: undefined,
  monEvtMajorName: undefined,
  monEvtMinorId: undefined,
  monEvtMinorName: undefined,
  relPtCount: undefined,
  relEvtCount: undefined,
  evtCompleteRate: undefined,
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
const detailVisible = ref(false) // 详情抽屉显示状态

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DomDivMonEvtApi.getDomDivMonEvtPage(queryParams)
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
const openDetail = async (id: number) => {
  detailVisible.value = true
  await detailRef.value.loadData(id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomDivMonEvtApi.deleteDomDivMonEvt(id)
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
    const data = await DomDivMonEvtApi.exportDomDivMonEvt(queryParams)
    download.excel(data, '监测事件分域.xls')
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

.mt-15px {
  margin-top: 15px;
}
</style>
