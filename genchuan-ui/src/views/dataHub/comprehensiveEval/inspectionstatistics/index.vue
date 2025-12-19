<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="120px"
    >
      <el-form-item label="考核日期" prop="inspectionDate">
        <el-date-picker
          v-model="queryParams.inspectionDate"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="场所类型" prop="areaType">
        <el-input
          v-model="queryParams.areaType"
          placeholder="请输入场所类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="区域/地点名称" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入区域/地点名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="考核状态" prop="inspectionStatus">
        <el-input
          v-model="queryParams.inspectionStatus"
          placeholder="请输入考核状态"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
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
    <!-- 绑定sort-change事件，使用Element Plus内置排序触发逻辑 -->
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="statistics-table"
      @sort-change="handleTableSort"
    >
      <el-table-column label="考核日期" align="center" prop="inspectionDate" width="140" />
      <el-table-column label="场所类型" align="center" prop="areaType" />
      <el-table-column label="区域/地点名称" align="center" prop="areaName" />
      <!--      <el-table-column label="最终得分" align="center" prop="scoreWeighted" width="100">-->
      <!--        <template #default="scope">-->
      <!--          <span class="score-weighted">{{ scope.row.scoreWeighted }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="总得分" align="center" prop="totalScore" width="100">
        <template #default="scope">
          <span class="score-weighted">{{ scope.row.totalScore }}</span>
        </template>
      </el-table-column>
      <el-table-column label="样本数" align="center" prop="sampleCount" width="80" />
      <el-table-column label="考核状态" align="center" prop="inspectionStatus" width="120">
        <template #default="scope">
          <el-tag
            :type="scope.row.inspectionStatus === '已完成' ? 'success' : 'warning'"
            effect="light"
          >
            {{ scope.row.inspectionStatus }}
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
      <!-- 修复：绑定prop、启用内置排序、修正样式类名 -->
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTime"
        :formatter="dateFormatter"
        width="180px"
        sortable="custom"
        class="sortable-column"
      />

      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 查看</el-button>
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
  <InspectionStatisticsForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <InspectionStatisticsDetail ref="detailRef" v-model="detailVisible" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  InspectionStatisticsApi,
  InspectionStatisticsVO
} from '@/api/dataHub/comprehensiveEval/inspectionstatistics'
import InspectionStatisticsForm from './InspectionStatisticsForm.vue'
import InspectionStatisticsDetail from './InspectionStatisticsDetail.vue'
import { ref, computed, onMounted, reactive } from 'vue'

/** 环卫考核统计结果 列表 */
defineOptions({ name: 'InspectionStatistics' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectionStatisticsVO[]>([]) // 原始列表数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  inspectionDate: [],
  areaType: undefined,
  areaName: undefined,
  inspectionStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const detailVisible = ref(false) // 详情抽屉显示状态
const detailRef = ref() // 详情抽屉引用

// 排序状态：默认按更新时间降序排列
const sortKey = ref<string>('updateTime')
const sortOrder = ref<'asc' | 'desc'>('desc')

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectionStatisticsApi.getInspectionStatisticsPage(queryParams)
    // 修复：确保排序逻辑生效（即使数据updateTime相同，也执行排序）
    let resultList = data.list
    if (sortKey.value === 'updateTime') {
      resultList = [...resultList].sort((a, b) => {
        const timeA = a.updateTime ? new Date(a.updateTime).getTime() : 0
        const timeB = b.updateTime ? new Date(b.updateTime).getTime() : 0
        return sortOrder.value === 'asc' ? timeA - timeB : timeB - timeA
      })
    }
    list.value = resultList
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
  // 重置排序状态
  sortKey.value = ''
  sortOrder.value = 'asc'
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 查看详情 */
const openDetail = (row: InspectionStatisticsVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await InspectionStatisticsApi.deleteInspectionStatistics(id)
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
    const data = await InspectionStatisticsApi.exportInspectionStatistics(queryParams)
    download.excel(data, '环卫考核统计结果.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 修复：使用Element Plus内置排序事件，避免事件被覆盖 */
const handleTableSort = (sort: {
  column: any
  prop: string
  order: 'ascending' | 'descending' | null
}) => {
  if (!sort.prop) {
    sortKey.value = ''
    sortOrder.value = 'asc'
  } else if (sort.prop === 'updateTime') {
    sortKey.value = sort.prop
    sortOrder.value = sort.order === 'ascending' ? 'asc' : 'desc'
  }
  // 触发列表重新加载（执行排序）
  getList()
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.statistics-table {
  border-radius: 8px;
  overflow: hidden;
}

.score-weighted {
  color: #409eff;
  font-weight: 500;
}

.search-form {
  padding: 15px;
  background-color: #f9fafb;
  border-radius: 8px;
  margin-bottom: 15px !important;
}

/* 排序表头样式 */
.sortable-column {
  cursor: pointer;
}

.sort-header {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
}

/* 修复：修正样式类名笔误 */
.sort-icon {
  font-size: 14px;
  color: #409eff;
}
</style>
