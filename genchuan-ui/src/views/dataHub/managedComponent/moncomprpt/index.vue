<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">查询条件</span>
          <el-button
            type="text"
            @click="toggleSearchForm"
            class="toggle-btn"
          >
            {{ showFullSearch ? '简化搜索' : '展开搜索' }}
            <Icon :icon="showFullSearch ? 'ep:arrow-up' : 'ep:arrow-down'" class="ml-2" />
          </el-button>
        </div>
      </template>
      <el-form
        class="search-form"
        :model="queryParams"
        ref="queryFormRef"
        :inline="true"
        label-width="140px"
      >
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">基础信息</div>
          <div class="form-row">
            <el-form-item label="唯一编码" prop="statId">
              <el-input
                v-model="queryParams.statId"
                placeholder="请输入唯一编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="统计周期类型" prop="statCycle">
              <el-select
                v-model="queryParams.statCycle"
                placeholder="请选择统计周期类型"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="日" value="day" />
                <el-option label="周" value="week" />
                <el-option label="月" value="month" />
                <el-option label="季" value="quarter" />
                <el-option label="年" value="year" />
              </el-select>
            </el-form-item>
            <el-form-item label="统计周期描述" prop="statCycleName">
              <el-input
                v-model="queryParams.statCycleName"
                placeholder="请输入统计周期描述"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 区域维度 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">区域维度</div>
          <div class="form-row">
            <el-form-item label="行政区划代码" prop="regionCode">
              <el-input
                v-model="queryParams.regionCode"
                placeholder="请输入行政区划代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="区域名称" prop="regionName">
              <el-input
                v-model="queryParams.regionName"
                placeholder="请输入区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 部件分类维度 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">部件分类维度</div>
          <div class="form-row">
            <el-form-item label="部件大类ID" prop="compMajorId">
              <el-input
                v-model="queryParams.compMajorId"
                placeholder="请输入部件大类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件大类名称" prop="compMajorName">
              <el-input
                v-model="queryParams.compMajorName"
                placeholder="请输入部件大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件小类ID" prop="compMinorId">
              <el-input
                v-model="queryParams.compMinorId"
                placeholder="请输入部件小类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="部件小类名称" prop="compMinorName">
              <el-input
                v-model="queryParams.compMinorName"
                placeholder="请输入部件小类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 数量统计 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">数量统计</div>
          <div class="form-row">
            <el-form-item label="总数量" prop="totalCompCount">
              <el-input-number
                v-model="queryParams.totalCompCount"
                placeholder="总数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="正常数量" prop="normalCompCount">
              <el-input-number
                v-model="queryParams.normalCompCount"
                placeholder="正常数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="异常数量" prop="abnCompCount">
              <el-input-number
                v-model="queryParams.abnCompCount"
                placeholder="异常数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="维护数量" prop="mntCompCount">
              <el-input-number
                v-model="queryParams.mntCompCount"
                placeholder="维护数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="废弃数量" prop="discardCompCount">
              <el-input-number
                v-model="queryParams.discardCompCount"
                placeholder="废弃数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="新增数量" prop="newCompCount">
              <el-input-number
                v-model="queryParams.newCompCount"
                placeholder="新增数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="更新数量" prop="updateCompCount">
              <el-input-number
                v-model="queryParams.updateCompCount"
                placeholder="更新数量"
                :min="0"
                controls-position="right"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 其他信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">其他信息</div>
          <div class="form-row">
            <el-form-item label="生成用户ID" prop="statUser">
              <el-input
                v-model="queryParams.statUser"
                placeholder="请输入生成用户ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="报表生成时间" prop="statTime">
              <el-date-picker
                v-model="queryParams.statTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-240px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="报表说明" prop="rptRemark">
              <el-input
                v-model="queryParams.rptRemark"
                placeholder="请输入报表说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 扩展字段 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">扩展字段</div>
          <div class="form-row">
            <el-form-item label="扩展字段1" prop="extCat1">
              <el-input
                v-model="queryParams.extCat1"
                placeholder="请输入扩展字段1"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="扩展字段2" prop="extCat2">
              <el-input
                v-model="queryParams.extCat2"
                placeholder="请输入扩展字段2"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="通用扩展1" prop="extCommon1">
              <el-input
                v-model="queryParams.extCommon1"
                placeholder="请输入通用扩展1"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="通用扩展2" prop="extCommon2">
              <el-input
                v-model="queryParams.extCommon2"
                placeholder="请输入通用扩展2"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <div class="form-actions">
          <el-button type="primary" @click="handleQuery">
            <Icon icon="ep:search" class="mr-5px" /> 搜索
          </el-button>
          <el-button @click="resetQuery">
            <Icon icon="ep:refresh" class="mr-5px" /> 重置
          </el-button>
          <el-button
            type="success"
            @click="openForm('create')"
            v-hasPermi="['datacenter:mon-comp-rpt:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:mon-comp-rpt:export']"
          >
            <Icon icon="ep:download" class="mr-5px" /> 导出
          </el-button>
        </div>
      </el-form>
    </el-card>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">监测部件统计报表列表</span>
          <div class="table-info">
            共 <span class="info-highlight">{{ total }}</span> 条记录
          </div>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        style="width: 100%"
        :header-cell-style="{
          background: '#f5f7fa',
          color: '#606266',
          fontWeight: '600'
        }"
        @sort-change="handleSortChange"
      >
        <el-table-column label="ID" align="center" prop="id" width="70" sortable="custom" />
        <el-table-column label="唯一编码" align="center" prop="statId" width="120" sortable="custom" />
        <el-table-column label="统计周期" align="center" width="140">
          <template #default="scope">
            <div class="stat-cycle">
              <div class="cycle-type">{{ scope.row.statCycle }}</div>
              <div class="cycle-name">{{ scope.row.statCycleName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="区域维度" align="center" width="160">
          <template #default="scope">
            <div class="region-info">
              <div class="region-code">{{ scope.row.regionCode }}</div>
              <div class="region-name">{{ scope.row.regionName }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="部件分类" align="center" width="180">
          <template #default="scope">
            <el-popover
              placement="left"
              title="部件分类信息"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看分类</el-button>
              </template>
              <div class="component-category">
                <div class="category-item">
                  <span class="category-label">部件大类:</span>
                  <div class="category-detail">
                    <span class="major-id">ID: {{ scope.row.compMajorId }}</span>
                    <span class="major-name">{{ scope.row.compMajorName }}</span>
                  </div>
                </div>
                <div v-if="scope.row.compMinorId" class="category-item">
                  <span class="category-label">部件小类:</span>
                  <div class="category-detail">
                    <span class="minor-id">ID: {{ scope.row.compMinorId }}</span>
                    <span class="minor-name">{{ scope.row.compMinorName }}</span>
                  </div>
                </div>
                <div v-else class="category-item">
                  <span class="category-label">部件小类:</span>
                  <span class="no-minor">无小类信息</span>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="数量统计" align="center" width="220">
          <template #default="scope">
            <el-popover
              placement="left"
              title="详细数量统计"
              :width="320"
              trigger="click"
            >
              <template #reference>
                <div class="count-summary">
                  <div class="total-count">总计: {{ scope.row.totalCompCount || 0 }}</div>
                  <div class="status-counts">
                    <el-tag size="small" type="success">{{ scope.row.normalCompCount || 0 }}正常</el-tag>
                    <el-tag size="small" type="danger">{{ scope.row.abnCompCount || 0 }}异常</el-tag>
                  </div>
                </div>
              </template>
              <div class="count-detail">
                <div class="count-item">
                  <span class="count-label">总数量:</span>
                  <span class="count-value total">{{ scope.row.totalCompCount || 0 }}</span>
                </div>
                <div class="count-row">
                  <div class="count-cell">
                    <span class="count-label">正常:</span>
                    <span class="count-value normal">{{ scope.row.normalCompCount || 0 }}</span>
                  </div>
                  <div class="count-cell">
                    <span class="count-label">异常:</span>
                    <span class="count-value abnormal">{{ scope.row.abnCompCount || 0 }}</span>
                  </div>
                </div>
                <div class="count-row">
                  <div class="count-cell">
                    <span class="count-label">维护:</span>
                    <span class="count-value maintenance">{{ scope.row.mntCompCount || 0 }}</span>
                  </div>
                  <div class="count-cell">
                    <span class="count-label">废弃:</span>
                    <span class="count-value discard">{{ scope.row.discardCompCount || 0 }}</span>
                  </div>
                </div>
                <div class="count-row">
                  <div class="count-cell">
                    <span class="count-label">新增:</span>
                    <span class="count-value new">{{ scope.row.newCompCount || 0 }}</span>
                  </div>
                  <div class="count-cell">
                    <span class="count-label">更新:</span>
                    <span class="count-value update">{{ scope.row.updateCompCount || 0 }}</span>
                  </div>
                </div>
                <div class="count-chart">
                  <div class="chart-bar" v-if="scope.row.totalCompCount > 0">
                    <div
                      class="bar-segment normal"
                      :style="{ width: calculatePercentage(scope.row.normalCompCount, scope.row.totalCompCount) }"
                      :title="`正常: ${scope.row.normalCompCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment abnormal"
                      :style="{ width: calculatePercentage(scope.row.abnCompCount, scope.row.totalCompCount) }"
                      :title="`异常: ${scope.row.abnCompCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment maintenance"
                      :style="{ width: calculatePercentage(scope.row.mntCompCount, scope.row.totalCompCount) }"
                      :title="`维护: ${scope.row.mntCompCount || 0}`"
                    ></div>
                    <div
                      class="bar-segment discard"
                      :style="{ width: calculatePercentage(scope.row.discardCompCount, scope.row.totalCompCount) }"
                      :title="`废弃: ${scope.row.discardCompCount || 0}`"
                    ></div>
                  </div>
                  <div v-else class="no-data-chart">无数据</div>
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="生成信息" align="center" width="160">
          <template #default="scope">
            <div class="generate-info">
              <div class="generate-user">用户: {{ scope.row.statUser || '系统' }}</div>
              <div class="generate-time">{{ dateFormatter(scope.row, scope.column, scope.row.statTime) }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="报表说明" align="center" min-width="180" show-overflow-tooltip>
          <template #default="scope">
            <span v-if="scope.row.rptRemark" class="remark-text">{{ scope.row.rptRemark }}</span>
            <span v-else class="no-remark">无说明</span>
          </template>
        </el-table-column>
        <el-table-column label="扩展字段" align="center" width="100">
          <template #default="scope">
            <el-popover
              placement="left"
              title="扩展字段信息"
              :width="280"
              trigger="click"
            >
              <template #reference>
                <el-button link type="primary" size="small">查看</el-button>
              </template>
              <div class="ext-fields">
                <div v-if="scope.row.extCat1" class="ext-field">
                  <span class="field-label">扩展字段1:</span>
                  <span class="field-value">{{ scope.row.extCat1 }}</span>
                </div>
                <div v-if="scope.row.extCat2" class="ext-field">
                  <span class="field-label">扩展字段2:</span>
                  <span class="field-value">{{ scope.row.extCat2 }}</span>
                </div>
                <div v-if="scope.row.extCommon1" class="ext-field">
                  <span class="field-label">通用扩展1:</span>
                  <span class="field-value">{{ scope.row.extCommon1 }}</span>
                </div>
                <div v-if="scope.row.extCommon2" class="ext-field">
                  <span class="field-label">通用扩展2:</span>
                  <span class="field-value">{{ scope.row.extCommon2 }}</span>
                </div>
                <div v-if="!scope.row.extCat1 && !scope.row.extCat2 && !scope.row.extCommon1 && !scope.row.extCommon2" class="no-ext">
                  无扩展字段数据
                </div>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:mon-comp-rpt:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:mon-comp-rpt:delete']"
            >
              删除
            </el-button>
            <el-button
              size="small"
              type="success"
              link
              @click="handleViewChart(scope.row)"
              v-hasPermi="['datacenter:mon-comp-rpt:query']"
            >
              图表
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination-wrapper">
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <MonCompRptForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MonCompRptApi, MonCompRptVO } from '@/api/dataHub/managedComponent/moncomprpt'
import MonCompRptForm from './MonCompRptForm.vue'

/** 监测部件统计报 列表 */
defineOptions({ name: 'MonCompRpt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MonCompRptVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  statId: undefined,
  statCycle: undefined,
  statCycleName: undefined,
  regionCode: undefined,
  regionName: undefined,
  compMajorId: undefined,
  compMajorName: undefined,
  compMinorId: undefined,
  compMinorName: undefined,
  totalCompCount: undefined,
  normalCompCount: undefined,
  abnCompCount: undefined,
  mntCompCount: undefined,
  discardCompCount: undefined,
  newCompCount: undefined,
  updateCompCount: undefined,
  statUser: undefined,
  statTime: [],
  rptRemark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  sortField: undefined,
  sortOrder: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 计算百分比 */
const calculatePercentage = (part: number, total: number) => {
  if (!total) return '0%'
  return `${((part || 0) / total * 100).toFixed(1)}%`
}

/** 查看图表 */
const handleViewChart = (row: any) => {
  // 这里可以打开图表弹窗或跳转到图表页面
  message.success(`查看统计图表: ${row.statCycleName}`)
  // 实际项目中可以调用图表弹窗组件
  // chartRef.value.open(row.id)
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonCompRptApi.getMonCompRptPage(queryParams)
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
    await MonCompRptApi.deleteMonCompRpt(id)
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
    const data = await MonCompRptApi.exportMonCompRpt(queryParams)
    download.excel(data, '监测部件统计报.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 切换搜索表单显示 */
const toggleSearchForm = () => {
  showFullSearch.value = !showFullSearch.value
}

/** 排序处理 */
const handleSortChange = (column: any) => {
  if (column.prop) {
    queryParams.sortField = column.prop
    queryParams.sortOrder = column.order === 'ascending' ? 'asc' :
      column.order === 'descending' ? 'desc' : undefined
  } else {
    queryParams.sortField = undefined
    queryParams.sortOrder = undefined
  }
  getList()
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
  border-radius: 8px;
}

.table-card {
  border-radius: 8px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.toggle-btn {
  color: #409eff;
  font-size: 13px;
}

.table-info {
  font-size: 14px;
  color: #606266;
}

.info-highlight {
  color: #409eff;
  font-weight: 600;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-section {
  border: 1px solid #ebeef5;
  border-radius: 6px;
  padding: 16px;
  background: #fafbfc;
}

.section-title {
  font-size: 14px;
  font-weight: 600;
  color: #409eff;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #e1e4e8;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

/* 表格内容样式 */
.stat-cycle {
  line-height: 1.4;
  font-size: 12px;
}

.cycle-type {
  font-weight: 500;
  color: #303133;
  text-transform: capitalize;
}

.cycle-name {
  color: #909399;
  margin-top: 2px;
}

.region-info {
  line-height: 1.4;
  font-size: 12px;
}

.region-code {
  color: #909399;
  font-size: 11px;
}

.region-name {
  font-weight: 500;
  color: #303133;
  margin-top: 2px;
}

.component-category {
  font-size: 13px;
  line-height: 1.6;
}

.category-item {
  margin-bottom: 10px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #f0f0f0;
}

.category-label {
  font-weight: 500;
  color: #606266;
  display: block;
  margin-bottom: 4px;
}

.category-detail {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.major-id,
.minor-id {
  color: #909399;
  font-size: 11px;
}

.major-name,
.minor-name {
  color: #303133;
  font-weight: 500;
}

.no-minor {
  color: #c0c4cc;
  font-style: italic;
}

.count-summary {
  line-height: 1.4;
  text-align: center;
}

.total-count {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.status-counts {
  display: flex;
  justify-content: center;
  gap: 4px;
  margin-top: 4px;
}

.count-detail {
  font-size: 13px;
}

.count-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
  padding-bottom: 6px;
  border-bottom: 1px solid #e1e4e8;
}

.count-row {
  display: flex;
  gap: 16px;
  margin-bottom: 8px;
}

.count-cell {
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.count-label {
  font-weight: 500;
  color: #606266;
}

.count-value {
  font-weight: 600;
  padding: 2px 6px;
  border-radius: 3px;
}

.count-value.total {
  color: #409eff;
  background: #ecf5ff;
}

.count-value.normal {
  color: #67c23a;
  background: #f0f9eb;
}

.count-value.abnormal {
  color: #f56c6c;
  background: #fef0f0;
}

.count-value.maintenance {
  color: #e6a23c;
  background: #fdf6ec;
}

.count-value.discard {
  color: #909399;
  background: #f4f4f5;
}

.count-value.new {
  color: #409eff;
  background: #ecf5ff;
}

.count-value.update {
  color: #67c23a;
  background: #f0f9eb;
}

.count-chart {
  margin-top: 12px;
  padding-top: 8px;
  border-top: 1px dashed #e1e4e8;
}

.chart-bar {
  height: 20px;
  background: #f5f7fa;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
}

.bar-segment {
  height: 100%;
  transition: all 0.3s ease;
}

.bar-segment.normal {
  background: #67c23a;
}

.bar-segment.abnormal {
  background: #f56c6c;
}

.bar-segment.maintenance {
  background: #e6a23c;
}

.bar-segment.discard {
  background: #909399;
}

.no-data-chart {
  text-align: center;
  color: #c0c4cc;
  font-style: italic;
  font-size: 12px;
}

.generate-info {
  line-height: 1.4;
  font-size: 12px;
}

.generate-user {
  color: #909399;
}

.generate-time {
  color: #303133;
  margin-top: 2px;
}

.remark-text {
  color: #606266;
  font-style: italic;
}

.no-remark {
  color: #c0c4cc;
  font-style: italic;
}

.ext-fields {
  font-size: 13px;
  line-height: 1.6;
}

.ext-field {
  display: flex;
  margin-bottom: 6px;
}

.field-label {
  font-weight: 500;
  color: #606266;
  min-width: 80px;
  text-align: right;
  margin-right: 8px;
}

.field-value {
  color: #303133;
  word-break: break-all;
}

.no-ext {
  color: #c0c4cc;
  font-style: italic;
  text-align: center;
  padding: 8px 0;
}

:deep(.el-card__header) {
  padding: 12px 20px;
  border-bottom: 1px solid #ebeef5;
}

:deep(.el-table .cell) {
  padding: 8px 12px;
}

:deep(.el-table th) {
  font-weight: 600;
}

:deep(.el-table .el-table__row:hover) {
  background-color: #f5f7fa;
}

:deep(.el-input-number) {
  width: 100%;
}
</style>
