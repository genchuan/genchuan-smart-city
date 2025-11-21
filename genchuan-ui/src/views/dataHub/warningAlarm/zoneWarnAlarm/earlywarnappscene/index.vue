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
      <el-form-item label="统计周期" prop="statCycle">
        <el-input
          v-model="queryParams.statCycle"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="场景大类" prop="sceneMajorName">
        <el-input
          v-model="queryParams.sceneMajorName"
          placeholder="请输入场景大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="场景小类" prop="sceneMinorName">
        <el-input
          v-model="queryParams.sceneMinorName"
          placeholder="请输入场景小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="主要预警类型" prop="mainWarnType">
        <el-select
          v-model="queryParams.mainWarnType"
          placeholder="请选择主要预警类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
        <el-button @click="handleQuery" class="search-btn">
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
  <ContentWrap class="table-content">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="统计周期" align="center" prop="statCycle" width="120px" />
      <el-table-column label="场景大类" align="center" prop="sceneMajorName" width="150px" />
      <el-table-column label="场景小类" align="center" prop="sceneMinorName" width="150px" />
      <el-table-column label="预警总数" align="center" prop="totalWarnCount" width="100px" />
      <el-table-column label="主要预警类型" align="center" prop="mainWarnType" width="140px" />
      <el-table-column label="已办结数" align="center" prop="completedCount" width="100px" />
      <el-table-column label="处置成功率" align="center" prop="successRate" width="120px">
        <template #default="scope">
          <span :class="scope.row.successRate >= 90 ? 'success-rate' : 'warning-rate'">
            {{ scope.row.successRate }}%
          </span>
        </template>
      </el-table-column>
      <el-table-column label="涉及网格数" align="center" prop="gridCount" width="120px" />
      <el-table-column
        label="统计时间"
        align="center"
        prop="statTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-5px">
            查看
          </el-button>
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
      class="pagination"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EarlyWarnAppSceneForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <EarlyWarnAppSceneDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EarlyWarnAppSceneApi,
  EarlyWarnAppSceneVO
} from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnappscene'
import EarlyWarnAppSceneForm from './EarlyWarnAppSceneForm.vue'
import EarlyWarnAppSceneDetail from './EarlyWarnAppSceneDetail.vue'

/** 按应用场景预警告警统计 列表 */
defineOptions({ name: 'EarlyWarnAppScene' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EarlyWarnAppSceneVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  warnAppSceneStatId: undefined,
  statCycle: undefined,
  sceneMajorId: undefined,
  sceneMajorName: undefined,
  sceneMinorId: undefined,
  sceneMinorName: undefined,
  totalWarnCount: undefined,
  mainWarnType: undefined,
  completedCount: undefined,
  successRate: undefined,
  gridCount: undefined,
  statUserId: undefined,
  statTime: [],
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
    const data = await EarlyWarnAppSceneApi.getEarlyWarnAppScenePage(queryParams)
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
const openDetail = (row: EarlyWarnAppSceneVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EarlyWarnAppSceneApi.deleteEarlyWarnAppScene(id)
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
    const data = await EarlyWarnAppSceneApi.exportEarlyWarnAppScene(queryParams)
    download.excel(data, '按应用场景预警告警统计.xls')
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
.search-form {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
  margin-bottom: 15px !important;
}

.search-btn {
  margin-right: 8px;
}

.table-content {
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.custom-table {
  border-radius: 6px;
  overflow: hidden;
}

.pagination {
  margin-top: 15px;
  text-align: right;
}

.success-rate {
  color: #52c41a;
  font-weight: 500;
}

.warning-rate {
  color: #faad14;
  font-weight: 500;
}
</style>
