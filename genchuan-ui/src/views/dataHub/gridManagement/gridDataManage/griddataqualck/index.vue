<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px flex-wrap"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="网格类型">
        <el-select v-model="queryParams.gridType" placeholder="全部" clearable class="!w-200px">
          <el-option label="单元网格" value="UNIT_GRID" />
          <el-option label="管理网格" value="MANAGE_GRID" />
          <el-option label="评价网格" value="EVAL_GRID" />
        </el-select>
      </el-form-item>
      <el-form-item label="检查项">
        <el-select v-model="queryParams.ckItem" placeholder="全部" clearable class="!w-200px">
          <el-option label="边界完整性检查" value="边界完整性检查" />
          <el-option label="拓扑关系检查" value="拓扑关系检查" />
          <el-option label="数据一致性检查" value="数据一致性检查" />
          <el-option label="坐标精度检查" value="坐标精度检查" />
          <el-option label="属性完整性检查" value="属性完整性检查" />
          <el-option label="关联关系检查" value="关联关系检查" />
        </el-select>
      </el-form-item>
      <el-form-item label="检查结果">
        <el-select v-model="queryParams.ckResult" placeholder="全部" clearable class="!w-200px">
          <el-option label="合格" value="PASS" />
          <el-option label="不合格" value="FAIL" />
        </el-select>
      </el-form-item>
      <el-form-item label="整改状态">
        <el-select
          v-model="queryParams.rectifyStatus"
          placeholder="全部"
          clearable
          class="!w-200px"
        >
          <el-option label="未整改" value="0" />
          <el-option label="整改中" value="1" />
          <el-option label="已整改" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="handleQuery">搜索</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          >导出
        </el-button>
        <el-button type="warning" plain @click="startAutoCheck">自动检查</el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 检查进度 -->
  <el-progress
    v-if="checkProgress.show"
    :percentage="checkProgress.value"
    :indeterminate="checkProgress.value < 100"
    class="mb-3"
  />
  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" stripe :show-overflow-tooltip="true">
      <el-table-column label="质量检查ID" prop="qualCkId" align="center" />
      <el-table-column label="网格编码" prop="gridCode" align="center" />
      <el-table-column label="检查项" prop="ckItem" align="center" />
      <el-table-column label="检查结果" prop="ckResult" align="center">
        <template #default="scope">
          <el-tag :type="scope.row.ckResult === 'PASS' ? 'success' : 'danger'">
            {{ scope.row.ckResult === 'PASS' ? '合格' : '不合格' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="错误描述" prop="errorDesc" align="center">
        <template #default="scope">
          <el-button type="text" @click="openDetail(scope.row)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column label="整改状态" prop="rectifyStatus" align="center">
        <template #default="scope">
          <el-tooltip :content="rectifyStatusMap[scope.row.rectifyStatus]">
            <span>
              <Icon :icon="rectifyStatusIcon[scope.row.rectifyStatus]" />
            </span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button type="primary" link @click="markRectify(scope.row)">整改</el-button>
          <el-button type="success" link @click="recheck(scope.row)">重新检查</el-button>
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

  <!-- 详情抽屉 -->
  <GridDataQualCkDetailDrawer v-model="detailDrawerVisible" :record="detailRecord || {}" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import {
  GridDataQualCkApi,
  GridDataQualCkVO
} from '@/api/dataHub/gridManagement/gridDataManage/griddataqualck'
import GridDataQualCkDetailDrawer from './GridDataQualCkDetailDrawer.vue'
import { useMessage } from '@/hooks/web/useMessage'
import download from '@/utils/download'

const message = useMessage()
const loading = ref(false)
const exportLoading = ref(false)
const list = ref<GridDataQualCkVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 20,
  gridType: '',
  ckItem: '',
  ckResult: '',
  rectifyStatus: ''
})

const detailDrawerVisible = ref(false)
const detailRecord = ref<GridDataQualCkVO | null>(null)

// 进度条
const checkProgress = reactive({ show: false, value: 0 })

const rectifyStatusMap = {
  COMPLETED: '已整改',
  PENDING: '待处理',
  IN_PROGRESS: '整改中'
}
const rectifyStatusIcon = {
  PENDING: 'ep:warning',
  IN_PROGRESS: 'ep:loading',
  COMPLETED: 'ep:circle-check'
}

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await GridDataQualCkApi.getGridDataQualCkPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}
const resetQuery = () => {
  Object.assign(queryParams, { gridType: '', ckItem: '', ckResult: '', rectifyStatus: '' })
  getList()
}

/** 导出 */
const handleExport = async () => {
  try {
    exportLoading.value = true
    const data = await GridDataQualCkApi.exportGridDataQualCk(queryParams)
    download.excel(data, '网格数据质量检查.xls')
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情抽屉 */
const openDetail = (row: GridDataQualCkVO) => {
  detailRecord.value = row
  detailDrawerVisible.value = true
}

/** 标记整改 */
const markRectify = (row: GridDataQualCkVO) => {
  row.rectifyStatus = '1'
  row.rectifyUserId = '当前用户'
  row.rectifyTime = new Date()
  message.success('已标记为整改中')
}

/** 重新检查（仅模拟） */
const recheck = async (row: GridDataQualCkVO) => {
  checkProgress.show = true
  checkProgress.value = 0
  const timer = setInterval(() => {
    checkProgress.value += 10
    if (checkProgress.value >= 100) {
      clearInterval(timer)
      checkProgress.show = false
      row.ckResult = 'pass'
      row.errorDesc = ''
      row.rectifyStatus = '2'
      message.success('重新检查完成')
    }
  }, 200)
}

/** 自动检查（模拟） */
const startAutoCheck = () => {
  checkProgress.show = true
  checkProgress.value = 0
  const timer = setInterval(() => {
    checkProgress.value += 5
    if (checkProgress.value >= 100) {
      clearInterval(timer)
      checkProgress.show = false
      message.success('自动检查完成，合格项已存入表')
      getList()
    }
  }, 300)
}

onMounted(() => {
  getList()
})
</script>
