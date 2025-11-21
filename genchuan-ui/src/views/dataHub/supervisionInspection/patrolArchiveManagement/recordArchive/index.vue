<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="80px"
    >
      <!-- 保留常用搜索字段，隐藏扩展字段和冗余字段 -->
      <el-form-item label="档案ID" prop="archId">
        <el-input
          v-model="queryParams.archId"
          placeholder="请输入档案ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="档案编码" prop="archCode">
        <el-input
          v-model="queryParams.archCode"
          placeholder="请输入档案编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="档案名称" prop="archName">
        <el-input
          v-model="queryParams.archName"
          placeholder="请输入档案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="档案类型" prop="archType">
        <el-select
          v-model="queryParams.archType"
          placeholder="请选择档案类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="所属区域" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入所属区域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="档案状态" prop="archStatus">
        <el-select
          v-model="queryParams.archStatus"
          placeholder="请选择档案状态"
          clearable
          class="!w-240px"
        >
          <el-option label="已归档" value="已归档" />
          <el-option label="待归档" value="待归档" />
        </el-select>
      </el-form-item>
      <el-form-item label="归档时间" prop="archTime">
        <el-date-picker
          v-model="queryParams.archTime"
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
  <ContentWrap class="mt-15px">
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      border
      class="!rounded-lg !shadow-sm"
      :header-cell-style="{ 'background-color': '#f5fafe', 'font-weight': 600 }"
      :cell-style="{ padding: '12px 0' }"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80px" />
      <el-table-column label="档案ID" align="center" prop="archId" width="120px" />
      <el-table-column label="档案编码" align="center" prop="archCode" width="140px" />
      <el-table-column label="档案名称" align="center" prop="archName" min-width="180px" />
      <el-table-column label="档案类型" align="center" prop="archType" width="120px" />
      <el-table-column label="所属区域" align="center" prop="areaName" width="140px" />
      <el-table-column label="归档周期" align="center" prop="archCycle" width="120px" />
      <el-table-column label="包含记录数" align="center" prop="includeRecCount" width="120px" />
      <el-table-column label="归档人" align="center" prop="archUser" width="120px" />
      <el-table-column
        label="归档时间"
        align="center"
        prop="archTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="档案状态" align="center" prop="archStatus" width="120px">
        <template #default="scope">
          <el-tag :type="scope.row.archStatus === '已归档' ? 'success' : 'warning'">
            {{ scope.row.archStatus || '-' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="销毁时间"
        align="center"
        prop="destroyTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)" class="mr-5px">
            详情
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
      class="mt-15px"
    />
  </ContentWrap>
  <!-- 表单弹窗：添加/修改 -->
  <InspectRecArchForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <InspectRecArchDetailDrawer
    v-model="detailVisible"
    :data="currentRow"
    @toggle-fullscreen="handleFullscreen"
  />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectRecArchApi,
  InspectRecArchVO
} from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/recordArchive'
import InspectRecArchForm from './InspectRecArchForm.vue'
import InspectRecArchDetailDrawer from './InspectRecArchDetailDrawer.vue'

/** 巡查巡检记录档案 列表 */
defineOptions({ name: 'InspectRecArch' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<InspectRecArchVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const detailVisible = ref(false) // 详情抽屉显示状态
const currentRow = ref<InspectRecArchVO | null>(null) // 当前选中行数据
const isFullscreen = ref(false) // 全屏状态

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  archId: undefined,
  archCode: undefined,
  archName: undefined,
  archType: undefined,
  areaCode: undefined,
  areaName: undefined,
  archCycle: undefined,
  includeRecCount: undefined,
  archAttachUrls: undefined,
  archUser: undefined,
  archTime: [],
  archStatus: undefined,
  destroyTime: [],
  archDesc: undefined
  // 隐藏扩展字段搜索
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectRecArchApi.getInspectRecArchPage(queryParams)
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
    await InspectRecArchApi.deleteInspectRecArch(id)
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
    const data = await InspectRecArchApi.exportInspectRecArch(queryParams)
    download.excel(data, '巡查巡检记录档案.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情抽屉 */
const openDetail = (row: InspectRecArchVO) => {
  currentRow.value = row
  detailVisible.value = true
  isFullscreen.value = false
}

/** 全屏切换处理 */
const handleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
<style scoped>
.mt-15px {
  margin-top: 15px;
}

.el-table {
  border-radius: 8px !important;
  overflow: hidden;
}

.el-table__header th {
  border-bottom: 1px solid #eaf6fa !important;
}

.el-table__body tr:hover {
  background-color: #f8fafc !important;
}
</style>
