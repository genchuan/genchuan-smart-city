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
      <el-form-item label="分级ID" prop="problemLevelId">
        <el-input
          v-model="queryParams.problemLevelId"
          placeholder="请输入分级ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="关联问题ID" prop="problemId">
        <el-input
          v-model="queryParams.problemId"
          placeholder="请输入关联问题ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="关联问题名称" prop="problemName">
        <el-input
          v-model="queryParams.problemName"
          placeholder="请输入关联问题名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="问题级别" prop="problemLevel">
        <el-input
          v-model="queryParams.problemLevel"
          placeholder="请输入问题级别"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分级时间" prop="levelTime">
        <el-date-picker
          v-model="queryParams.levelTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="是否推送提醒" prop="isPushRemind">
        <el-select
          v-model="queryParams.isPushRemind"
          placeholder="请选择是否推送提醒"
          clearable
          class="!w-200px"
        >
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
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
      <el-table-column label="分级ID" align="center" prop="problemLevelId" />
      <el-table-column label="关联问题ID" align="center" prop="problemId" />
      <el-table-column label="关联问题编码" align="center" prop="problemCode" />
      <el-table-column label="关联问题名称" align="center" prop="problemName" min-width="150" />
      <el-table-column label="问题级别" align="center" prop="problemLevel" />
      <el-table-column label="处置时限" align="center" prop="handleDeadline" />
      <el-table-column label="分级人" align="center" prop="levelUser" />
      <el-table-column
        label="分级时间"
        align="center"
        prop="levelTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="是否推送提醒" align="center" prop="isPushRemind">
        <template #default="scope">
          <el-tag :type="scope.row.isPushRemind ? 'success' : 'info'">
            {{ scope.row.isPushRemind ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="bizCreateUser" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="bizCreateTime"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <InspectProblemLevelRecForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <InspectProblemLevelRecDetail
    ref="detailRef"
    :visible="detailVisible"
    :data="detailData"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectProblemLevelRecApi,
  InspectProblemLevelRecVO
} from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueClassificationManagement'
import InspectProblemLevelRecForm from './InspectProblemLevelRecForm.vue'
import InspectProblemLevelRecDetail from './InspectProblemLevelRecDetail.vue'

/** 巡查巡检问题分级记录 列表 */
defineOptions({ name: 'InspectProblemLevelRec' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectProblemLevelRecVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  problemLevelId: undefined,
  problemId: undefined,
  problemCode: undefined,
  problemName: undefined,
  problemLevel: undefined,
  levelDesc: undefined,
  handleDeadline: undefined,
  levelBasis: undefined,
  levelUser: undefined,
  levelTime: [],
  isPushRemind: undefined,
  pushRemindTime: [],
  problemStatusChange: undefined,
  bizCreateUser: undefined,
  bizCreateTime: [],
  bizUpdateUser: undefined,
  bizUpdateTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<InspectProblemLevelRecVO | null>(null)
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectProblemLevelRecApi.getInspectProblemLevelRecPage(queryParams)
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
const openDetail = (row: InspectProblemLevelRecVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectProblemLevelRecApi.deleteInspectProblemLevelRec(id)
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
    const data = await InspectProblemLevelRecApi.exportInspectProblemLevelRec(queryParams)
    download.excel(data, '巡查巡检问题分级记录.xls')
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
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
