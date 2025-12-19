<template>
  <div class="analysis-mng-comp-page">
    <!-- 搜索区域 -->
    <ContentWrap class="search-container">
      <div class="search-content">
        <el-form
          class="search-form"
          :model="queryParams"
          ref="queryFormRef"
          :inline="true"
          label-width="100px"
        >
          <div class="form-row">
            <el-form-item label="统计ID" prop="statAnalysisId">
              <el-input
                v-model="queryParams.statAnalysisId"
                placeholder="请输入统计ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-160px"
              />
            </el-form-item>

            <el-form-item label="统计周期" prop="statCycle">
              <el-input
                v-model="queryParams.statCycle"
                placeholder="请输入统计周期"
                clearable
                @keyup.enter="handleQuery"
                class="!w-160px"
              />
            </el-form-item>

            <el-form-item label="部件大类名称" prop="compMajorName">
              <el-input
                v-model="queryParams.compMajorName"
                placeholder="请输入部件大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-160px"
              />
            </el-form-item>

            <el-form-item label="部件小类名称" prop="compMinorName">
              <el-input
                v-model="queryParams.compMinorName"
                placeholder="请输入部件小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-160px"
              />
            </el-form-item>

            <el-form-item label="创建时间" prop="createTimeSys">
              <el-date-picker
                v-model="queryParams.createTimeSys"
                value-format="YYYY-MM-DD"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                class="!w-220px"
              />
            </el-form-item>

            <!-- 按钮组与搜索字段同行 -->
            <div class="search-buttons-group">
              <el-button type="primary" @click="handleQuery">
                <Icon icon="ep:search" class="mr-1" /> 搜索
              </el-button>
              <el-button @click="resetQuery">
                <Icon icon="ep:refresh" class="mr-1" /> 重置
              </el-button>
              <el-button
                type="primary"
                plain
                @click="openForm('create')"
                v-hasPermi="['datacenter:analysis-mng-comp:create']"
              >
                <Icon icon="ep:plus" class="mr-1" /> 新增
              </el-button>
              <el-button
                type="success"
                plain
                @click="handleExport"
                :loading="exportLoading"
                v-hasPermi="['datacenter:analysis-mng-comp:export']"
              >
                <Icon icon="ep:download" class="mr-1" /> 导出
              </el-button>
            </div>
          </div>
        </el-form>
      </div>
    </ContentWrap>

    <!-- 数据列表 -->
    <ContentWrap class="list-container">
      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        class="list-table"
        :header-cell-style="{
          'background-color': '#f5f7fa',
          'font-weight': '600',
          'color': '#606266',
          'padding': '12px 8px',
          'white-space': 'nowrap'
        }"
        :cell-style="{
          'vertical-align': 'middle',
          'padding': '8px'
        }"
      >
        <el-table-column label="主键ID" align="center" prop="id" width="100" />
        <el-table-column label="统计ID" align="center" prop="statAnalysisId" min-width="140" />
        <el-table-column label="统计周期" align="center" prop="statCycle" min-width="120" />
        <el-table-column label="部件大类名称" align="center" prop="compMajorName" min-width="160" />
        <el-table-column label="部件小类名称" align="center" prop="compMinorName" min-width="160" />
        <el-table-column label="部件总存量" align="center" prop="totalCompStock" min-width="120" />
        <el-table-column label="损坏部件数" align="center" prop="damagedCompCount" min-width="120" />
        <el-table-column label="部件损坏率" align="center" prop="compDamageRate" min-width="120" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTimeSys"
          :formatter="dateFormatter"
          width="180"
        />
        <!-- 操作列 -->
        <el-table-column
          label="操作"
          align="center"
          width="180"
          fixed="right"
        >
          <template #default="scope">
            <div class="operation-btn-group">
              <el-button
                link
                type="primary"
                size="small"
                @click="openDetail(scope.row)"
                v-hasPermi="['datacenter:analysis-mng-comp:detail']"
                class="operation-btn"
              >
                详情
              </el-button>
              <el-button
                link
                type="primary"
                size="small"
                @click="openForm('update', scope.row.id)"
                v-hasPermi="['datacenter:analysis-mng-comp:update']"
                class="operation-btn"
              >
                编辑
              </el-button>
              <el-button
                link
                type="danger"
                size="small"
                @click="handleDelete(scope.row.id)"
                v-hasPermi="['datacenter:analysis-mng-comp:delete']"
                class="operation-btn"
              >
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 - 居中显示 -->
      <div class="pagination-container">
        <div class="pagination-content">
          <div class="page-info">
            共 <span class="text-primary font-medium">{{ total }}</span> 条
          </div>
          <Pagination
            :total="total"
            v-model:page="queryParams.pageNo"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
            layout="sizes, prev, pager, next, jumper"
            :page-sizes="[10, 20, 50, 100]"
          />
        </div>
      </div>
    </ContentWrap>

    <!-- 详情抽屉 -->
    <div
      v-if="isDetailShow"
      class="detail-mask"
      @click="closeDetail"
    ></div>
    <div
      v-if="isDetailShow"
      class="detail-drawer"
      :class="{ 'full-screen': isFullScreen }"
      @click.stop
    >
      <div class="detail-header flex justify-between items-center p-4 border-b">
        <h3 class="text-lg font-semibold">数据详情</h3>
        <div class="detail-header-btns">
          <el-tooltip
            :content="isFullScreen ? '退出全屏' : '全屏显示'"
            placement="bottom"
          >
            <el-button
              text
              size="small"
              @click="toggleFullScreen"
              class="fullscreen-btn"
            >
              <!-- 减号放大镜图标（退出全屏） -->
              <svg
                v-if="isFullScreen"
                class="zoom-out-icon"
                viewBox="0 0 24 24"
                width="18"
                height="18"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              >
                <circle cx="11" cy="11" r="8" />
                <line x1="21" y1="21" x2="16.65" y2="16.65" />
                <line x1="8" y1="11" x2="14" y2="11" />
              </svg>
              <!-- 加号放大镜图标（进入全屏） -->
              <svg
                v-else
                class="zoom-in-icon"
                viewBox="0 0 24 24"
                width="18"
                height="18"
                fill="none"
                stroke="currentColor"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              >
                <circle cx="11" cy="11" r="8" />
                <line x1="21" y1="21" x2="16.65" y2="16.65" />
                <line x1="11" y1="8" x2="11" y2="14" />
                <line x1="8" y1="11" x2="14" y2="11" />
              </svg>
            </el-button>
          </el-tooltip>
          <el-tooltip content="关闭" placement="bottom">
            <el-button
              text
              size="small"
              @click="closeDetail"
              class="close-btn"
            >
              <Icon icon="ep:close" />
            </el-button>
          </el-tooltip>
        </div>
      </div>

      <div class="detail-content p-6">
        <el-descriptions
          title=""
          :column="1"
          border
          :label-style="{ 'width': '120px', 'font-weight': '500', 'text-align': 'left' }"
          :content-style="{ 'flex': '1', 'text-align': 'left' }"
        >
          <el-descriptions-item label="主键ID">{{ selectedRow?.id || '-' }}</el-descriptions-item>
          <el-descriptions-item label="统计ID">{{ selectedRow?.statAnalysisId || '-' }}</el-descriptions-item>
          <el-descriptions-item label="统计周期">{{ selectedRow?.statCycle || '-' }}</el-descriptions-item>
          <el-descriptions-item label="统计周期名称">{{ selectedRow?.statCycleName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件大类ID">{{ selectedRow?.compMajorId || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件大类名称">{{ selectedRow?.compMajorName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件小类ID">{{ selectedRow?.compMinorId || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件小类名称">{{ selectedRow?.compMinorName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件总存量">{{ selectedRow?.totalCompStock || '-' }}</el-descriptions-item>
          <el-descriptions-item label="损坏部件数">{{ selectedRow?.damagedCompCount || '-' }}</el-descriptions-item>
          <el-descriptions-item label="部件损坏率">{{ selectedRow?.compDamageRate || '-' }}</el-descriptions-item>
          <el-descriptions-item label="系统创建时间">{{ dateFormatter(selectedRow?.createTimeSys) || '-' }}</el-descriptions-item>
          <el-descriptions-item label="系统更新时间">{{ dateFormatter(selectedRow?.updateTimeSys) || '-' }}</el-descriptions-item>
          <el-descriptions-item label="分类扩展字段1">{{ selectedRow?.extCat1 || '-' }}</el-descriptions-item>
          <el-descriptions-item label="分类扩展字段2">{{ selectedRow?.extCat2 || '-' }}</el-descriptions-item>
          <el-descriptions-item label="通用扩展字段1">{{ selectedRow?.extCommon1 || '-' }}</el-descriptions-item>
          <el-descriptions-item label="通用扩展字段2">{{ selectedRow?.extCommon2 || '-' }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </div>

    <!-- 表单弹窗：添加/修改 -->
    <AnalysisMngCompForm ref="formRef" @success="getList" />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, nextTick, onMounted, onUnmounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AnalysisMngCompApi, AnalysisMngCompVO } from '@/api/dataHub/analysisDecision/analysismngcomp'
import AnalysisMngCompForm from './AnalysisMngCompForm.vue'

/** 按管理部件分析研判统计 列表 */
defineOptions({ name: 'AnalysisMngComp' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AnalysisMngCompVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const exportLoading = ref(false) // 导出的加载中

// 详情伪抽屉相关变量
const isDetailShow = ref(false)
const selectedRow = ref<AnalysisMngCompVO | null>(null)
const isFullScreen = ref(false)

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statAnalysisId: undefined,
  statCycle: undefined,
  compMajorName: undefined,
  compMinorName: undefined,
  createTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const formRef = ref() // 表单弹窗

/** 打开详情伪抽屉 */
const openDetail = (row: AnalysisMngCompVO) => {
  selectedRow.value = row
  isDetailShow.value = true
  isFullScreen.value = false
  document.body.style.overflow = 'hidden'
}

/** 关闭详情伪抽屉 */
const closeDetail = () => {
  isDetailShow.value = false
  selectedRow.value = null
  isFullScreen.value = false
  document.body.style.overflow = ''
}

/** 切换全屏/缩小 */
const toggleFullScreen = () => {
  isFullScreen.value = !isFullScreen.value
  nextTick(() => {
    const iconEl = document.querySelector('.fullscreen-btn')
    if (iconEl) {
      iconEl.classList.add('btn-fade')
      setTimeout(() => iconEl.classList.remove('btn-fade'), 300)
    }
  })
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AnalysisMngCompApi.getAnalysisMngCompPage(queryParams)
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
  queryFormRef.value?.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await AnalysisMngCompApi.deleteAnalysisMngComp(id)
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
    const data = await AnalysisMngCompApi.exportAnalysisMngComp(queryParams)
    download.excel(data, '按管理部件分析研判统计.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})

onUnmounted(() => {
  document.body.style.overflow = ''
  isFullScreen.value = false
})
</script>

<style scoped lang="scss">
.analysis-mng-comp-page {
  padding: 8px;
  height: 100vh;
  box-sizing: border-box;
  background-color: #f9fafb;
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  gap: 1px;
}

/* 搜索栏样式 - 更紧凑 */
.search-container {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  padding: 12px 16px;
  flex-shrink: 0;
}

.search-content {
  width: 100%;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  align-items: center;
  width: 100%;
}

.search-form {
  width: 100%;
}

.search-form ::v-deep(.el-form-item) {
  margin-bottom: 0;
  flex-shrink: 0;
}

.search-form ::v-deep(.el-form-item__label) {
  font-size: 13px;
  color: #333;
  text-align: right;
  padding-right: 6px;
}

/* 按钮组与搜索字段同行 */
.search-buttons-group {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-left: auto;
  flex-shrink: 0;
  white-space: nowrap;
}

/* 列表区域样式 */
.list-container {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  padding: 16px;
  flex: 1;
  display: flex;
  flex-direction: column;
  min-height: 0;
  overflow: hidden;
}

.list-table {
  flex: 1;
  overflow: auto;

  /* 禁止列拖动 */
  ::v-deep(.el-table) {
    table-layout: fixed;
    border: none;
  }

  /* 去掉表格边框 */
  ::v-deep(.el-table__header),
  ::v-deep(.el-table__body),
  ::v-deep(.el-table__row) {
    border: none;
  }

  ::v-deep(.el-table th),
  ::v-deep(.el-table td) {
    border: none;
  }

  /* 添加行分隔线 */
  ::v-deep(.el-table__row) {
    border-bottom: 1px solid #f0f0f0;
  }

  /* 禁止列宽调整 */
  ::v-deep(.el-table__header-wrapper .el-table__header) {
    .el-table__column-resize-proxy {
      display: none !important;
    }
  }

  /* 隐藏列拖动指示器 */
  ::v-deep(.el-table .caret-wrapper) {
    cursor: default !important;
  }

  ::v-deep(.el-table__body tr:hover > td) {
    background-color: #f0f9ff !important;
  }

  ::v-deep(.el-table__row--striped > td) {
    background-color: #fafafa !important;
  }

  ::v-deep(.el-table__fixed-right) {
    z-index: 10;
    background-color: #fff !important;
  }
}

/* 操作按钮组 - 紧凑排列 */
.operation-btn-group {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;
  flex-wrap: nowrap;
  width: 100%;
}

.operation-btn {
  padding: 2px 6px !important;
  font-size: 12px !important;
  min-width: auto !important;
  height: 24px !important;
}

/* 分页样式 - 居中显示 */
.pagination-container {
  padding: 12px 0;
  flex-shrink: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pagination-content {
  display: flex;
  align-items: center;
  gap: 16px;
}

.page-info {
  color: #606266;
  font-size: 14px;
}

/* 伪抽屉核心样式 */
.detail-mask {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
  transition: opacity 0.3s ease;
}

.detail-drawer {
  position: fixed;
  top: 0;
  right: 0;
  width: calc(100% / 3);
  height: 100vh;
  background-color: #fff;
  box-shadow: -2px 0 12px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  transition: width 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
  transform: translateX(0);
  overflow: hidden;
}

.detail-drawer.full-screen {
  width: 100%;
  box-shadow: none;
}

/* 抽屉头部 */
.detail-header {
  background-color: #f8f9fa;
  border-bottom: 1px solid #eee;
  position: relative;
  z-index: 1001;
}

.detail-header-btns {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* 全屏按钮样式 - 无背景 */
.fullscreen-btn {
  width: 32px;
  height: 32px;
  border: none;
  background-color: transparent;
  color: #606266;
  transition: all 0.3s ease;

  &:hover {
    color: #409eff;
    background-color: rgba(64, 158, 255, 0.1);
    transform: scale(1.05);
  }
}

/* 关闭按钮样式 - 无背景 */
.close-btn {
  width: 32px;
  height: 32px;
  border: none;
  background-color: transparent;
  color: #606266;
  transition: all 0.3s ease;

  &:hover {
    color: #f56c6c;
    background-color: rgba(245, 108, 108, 0.1);
    transform: scale(1.05);
  }
}

/* 按钮动画 */
.btn-fade {
  animation: btnFade 0.3s ease;
}

@keyframes btnFade {
  0% { opacity: 0.5; transform: scale(0.9); }
  100% { opacity: 1; transform: scale(1); }
}

/* 抽屉内容区域 */
.detail-content {
  height: calc(100vh - 60px);
  overflow-y: auto;
}

/* 详情描述组件样式 - 全部左对齐 */
::v-deep(.el-descriptions) {
  --el-descriptions-item-padding: 16px 12px;
}

::v-deep(.el-descriptions__border .el-descriptions-item) {
  border-bottom: 1px solid #f0f0f0;
}

::v-deep(.el-descriptions__label) {
  text-align: left !important;
  color: #666;
  justify-content: flex-start !important;
}

::v-deep(.el-descriptions__content) {
  text-align: left !important;
  color: #333;
  word-break: break-all;
  justify-content: flex-start !important;
}

::v-deep(.el-descriptions__cell) {
  text-align: left !important;
  justify-content: flex-start !important;
}

/* 响应式适配 */
@media (max-width: 1440px) {
  .search-buttons-group {
    min-width: auto;
  }

  ::v-deep(.el-button) {
    padding: 6px 10px;
    font-size: 12px;
  }

  .detail-drawer {
    width: 40%;
  }
}

@media (max-width: 1200px) {
  .search-form .el-input,
  .search-form .el-select {
    width: 140px !important;
  }

  .detail-drawer {
    width: 50%;
  }
}

@media (max-width: 992px) {
  .form-row {
    flex-wrap: wrap;
    gap: 8px;
  }

  .search-buttons-group {
    width: 100%;
    justify-content: flex-start;
    margin-left: 0;
    margin-top: 8px;
  }

  .detail-drawer {
    width: 70%;
  }
}

@media (max-width: 768px) {
  .search-buttons-group {
    flex-wrap: wrap;
    justify-content: center;
  }

  .detail-drawer {
    width: 100%;
  }

  .operation-btn-group {
    flex-wrap: wrap;
  }

  .pagination-content {
    flex-direction: column;
    gap: 8px;
  }
}

/* 滚动条优化 */
::-webkit-scrollbar {
  height: 6px;
  width: 6px;
}

::-webkit-scrollbar-thumb {
  background: #ddd;
  border-radius: 3px;
}

::-webkit-scrollbar-track {
  background: #f5f5f5;
  border-radius: 3px;
}
</style>
