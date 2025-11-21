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
      <!-- 保留常用搜索字段，隐藏次要字段 -->
      <el-form-item label="验收ID" prop="acceptId">
        <el-input
          v-model="queryParams.acceptId"
          placeholder="请输入验收ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="验收编码" prop="acceptCode">
        <el-input
          v-model="queryParams.acceptCode"
          placeholder="请输入验收编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联整改任务编码" prop="rectifyTaskCode">
        <el-input
          v-model="queryParams.rectifyTaskCode"
          placeholder="请输入关联整改任务编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="验收结果" prop="acceptResult">
        <el-select
          v-model="queryParams.acceptResult"
          placeholder="请选择验收结果"
          clearable
          class="!w-240px"
        >
          <el-option label="通过" value="通过" />
          <el-option label="不通过" value="不通过" />
          <el-option label="待验收" value="待验收" />
        </el-select>
      </el-form-item>
      <el-form-item label="验收人" prop="acceptUser">
        <el-input
          v-model="queryParams.acceptUser"
          placeholder="请输入验收人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="验收时间" prop="acceptTime">
        <el-date-picker
          v-model="queryParams.acceptTime"
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
      <el-table-column label="验收ID" align="center" prop="acceptId" />
      <el-table-column label="验收编码" align="center" prop="acceptCode" />
      <el-table-column label="关联整改任务编码" align="center" prop="rectifyTaskCode" />
      <el-table-column label="关联问题ID" align="center" prop="problemId" />
      <el-table-column
        label="整改结果描述"
        align="center"
        prop="rectifyResultDesc"
        min-width="150"
      />
      <el-table-column label="验收结果" align="center" prop="acceptResult">
        <template #default="scope">
          <el-tag :type="getAcceptResultTagType(scope.row.acceptResult)">
            {{ scope.row.acceptResult || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="验收人" align="center" prop="acceptUser" />
      <el-table-column
        label="验收时间"
        align="center"
        prop="acceptTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="整改提交人" align="center" prop="rectifySubmitUser" />
      <el-table-column
        label="整改提交时间"
        align="center"
        prop="rectifySubmitTime"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectRectifyAcceptForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectRectifyAcceptDetail ref="detailRef" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectRectifyAcceptApi,
  InspectRectifyAcceptVO
} from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionResultAcceptance'
import InspectRectifyAcceptForm from './InspectRectifyAcceptForm.vue'
import InspectRectifyAcceptDetail from './InspectRectifyAcceptDetail.vue'

/** 巡查巡检整改结果验收 列表 */
defineOptions({ name: 'InspectRectifyAccept' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectRectifyAcceptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  acceptId: undefined,
  acceptCode: undefined,
  rectifyTaskCode: undefined,
  acceptResult: undefined,
  acceptUser: undefined,
  acceptTime: [],
  // 隐藏其他查询字段
  rectifyTaskId: undefined,
  problemId: undefined,
  rectifyResultDesc: undefined,
  rectifyAfterPhotoUrls: undefined,
  rectifyBeforePhotoUrls: undefined,
  acceptOpinion: undefined,
  rectifySubmitUser: undefined,
  rectifySubmitTime: [],
  taskStatusChange: undefined,
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
const openDetail = (row: InspectRectifyAcceptVO) => {
  detailRef.value.open(row)
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectRectifyAcceptApi.getInspectRectifyAcceptPage(queryParams)
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
    await InspectRectifyAcceptApi.deleteInspectRectifyAccept(id)
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
    const data = await InspectRectifyAcceptApi.exportInspectRectifyAccept(queryParams)
    download.excel(data, '巡查巡检整改结果验收.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 获取验收结果标签类型 */
const getAcceptResultTagType = (result: string) => {
  switch (result) {
    case '合格':
      return 'success'
    case '待改进':
      return 'danger'
    case '待定':
      return 'warning'
    default:
      return 'info'
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

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-table td {
  padding: 12px 0;
}
</style>
