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
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事项大类" prop="matterMajorName">
        <el-input
          v-model="queryParams.matterMajorName"
          placeholder="请输入事项大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事项小类" prop="matterMinorName">
        <el-input
          v-model="queryParams.matterMinorName"
          placeholder="请输入事项小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主要处置部门" prop="mainDeptName">
        <el-input
          v-model="queryParams.mainDeptName"
          placeholder="请输入主要处置部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="statTime">
        <el-date-picker
          v-model="queryParams.statTime"
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
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120" />
      <el-table-column label="事项大类" align="center" prop="matterMajorName" width="150" />
      <el-table-column label="事项小类" align="center" prop="matterMinorName" width="150" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" width="100" />
      <el-table-column label="已办结数" align="center" prop="completedCount" width="100" />
      <el-table-column label="超时未处置数" align="center" prop="overtimeCount" width="120">
        <template #default="scope">
          <span class="text-red-500">{{ scope.row.overtimeCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处置率" align="center" prop="handleRate" width="100">
        <template #default="scope">
          <span :class="scope.row.handleRate > 80 ? 'text-green-500' : 'text-orange-500'">
            {{ scope.row.handleRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column label="主要处置部门" align="center" prop="mainDeptName" width="180" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180"
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
  <EarlyWarnMatterForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnMatterDetail ref="detailRef" v-model="detailVisible" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnMatterApi,
  EarlyWarnMatterVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmatter'
import EarlyWarnMatterForm from './EarlyWarnMatterForm.vue'
import EarlyWarnMatterDetail from './EarlyWarnMatterDetail.vue'

/** 按管理事项预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnMatter' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnMatterVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statCycle: undefined,
  matterMajorName: undefined,
  matterMinorName: undefined,
  mainDeptName: undefined,
  statTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const detailVisible = ref(false)
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EarlyWarnMatterApi.getEarlyWarnMatterPage(queryParams)
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
const openDetail = (row: EarlyWarnMatterVO) => {
  console.log('点击了详情按钮，行数据：', row) // 新增打印
  detailVisible.value = true
  console.log('detailVisible设置后的值：', detailVisible.value) // 新增打印
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnMatterApi.deleteEarlyWarnMatter(id)
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
    const data = await EarlyWarnMatterApi.exportEarlyWarnMatter(queryParams)
    download.excel(data, '按管理事项预警告警统计.xls')
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

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #333;
}

::v-deep .el-table__body tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
