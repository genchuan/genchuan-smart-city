<template>
  <div class="bi-mng-matter-container">
    <!-- 筛选区域 -->
    <div class="filter-section">
      <el-card shadow="never" class="filter-card">
        <template #header>
          <div class="filter-header">
            <span>筛选条件</span>
            <div class="header-actions">
              <el-button type="primary" @click="handleQuery">查询</el-button>
              <el-button @click="handleReset">重置</el-button>
              <el-button @click="handleRefresh">刷新报表</el-button>
              <el-button @click="handleExport('excel')">导出Excel</el-button>
              <el-button @click="handleExport('pdf')">导出PDF</el-button>
              <el-button type="success" @click="showCompareDialog = true">报表对比</el-button>
            </div>
          </div>
        </template>

        <el-form :model="queryParams" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="统计周期">
                <el-select v-model="queryParams.statCycle" placeholder="请选择统计周期" clearable>
                  <el-option
                    v-for="item in cycleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="行政区划">
                <el-cascader
                  v-model="queryParams.areaCode"
                  :options="areaOptions"
                  :props="areaProps"
                  clearable
                  placeholder="请选择行政区划"
                />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="部件大类">
                <el-select v-model="queryParams.compMajorId" placeholder="请选择部件大类" clearable>
                  <el-option
                    v-for="item in majorOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="部件小类">
                <el-select v-model="queryParams.compMinorId" placeholder="请选择部件小类" clearable>
                  <el-option
                    v-for="item in minorOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="统计时间">
                <el-date-picker
                  v-model="queryParams.statTimeRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="YYYY-MM-DD"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>

    <!-- 图表区域 -->
    <div class="chart-section">
      <el-card shadow="never" class="chart-card">
        <template #header>
          <div class="chart-header">
            <span>统计分析图表</span>
            <div class="chart-type-selector">
              <el-radio-group v-model="chartType" @change="handleChartTypeChange">
                <el-radio-button label="bar">柱状图</el-radio-button>
                <el-radio-button label="pie">饼图</el-radio-button>
                <el-radio-button label="line">折线图</el-radio-button>
              </el-radio-group>
            </div>
          </div>
        </template>

        <div ref="chartContainer" class="chart-container" style="height: 400px;"></div>
      </el-card>
    </div>

    <!-- 表格区域 -->
    <div class="table-section">
      <el-card shadow="never" class="table-card">
        <template #header>
          <div class="table-header">
            <span>统计明细数据</span>
            <div class="table-actions">
              <el-tag type="info">共 {{ tableData.length }} 条记录</el-tag>
            </div>
          </div>
        </template>

        <el-table
          :data="tableData"
          v-loading="loading"
          :row-class-name="tableRowClassName"
          @row-click="handleRowClick"
          style="width: 100%"
        >
          <el-table-column type="index" width="50" label="序号" />
          <el-table-column prop="statCycleName" label="统计周期" min-width="120" />
          <el-table-column prop="areaName" label="行政区划" min-width="120" />
          <el-table-column prop="compMajorName" label="部件大类" min-width="120" />
          <el-table-column prop="compMinorName" label="部件小类" min-width="120" />
          <el-table-column prop="totalCompCount" label="部件总数" width="100" />
          <el-table-column prop="normalCompCount" label="完好数量" width="100">
            <template #default="{ row }">
              <span class="status-normal">{{ row.normalCompCount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="damagedCompCount" label="破损数量" width="100">
            <template #default="{ row }">
              <span class="status-damaged">{{ row.damagedCompCount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="lostCompCount" label="丢失数量" width="100">
            <template #default="{ row }">
              <span class="status-lost">{{ row.lostCompCount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="discardedCompCount" label="废弃数量" width="100">
            <template #default="{ row }">
              <span class="status-discarded">{{ row.discardedCompCount }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="damagedRate" label="破损率" width="100">
            <template #default="{ row }">
              <el-tag
                :type="row.damagedRate > 0.1 ? 'danger' : 'success'"
                size="small"
              >
                {{ (row.damagedRate * 100).toFixed(1) }}%
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="statTime" label="统计时间" width="180" />
          <el-table-column label="操作" width="120" fixed="right">
            <template #default="{ row }">
              <el-button
                link
                type="primary"
                size="small"
                @click.stop="handleDrillDown(row)"
              >
                钻取
              </el-button>
              <el-button
                link
                type="warning"
                size="small"
                @click.stop="handleViewDetail(row)"
              >
                详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>

    <!-- 报表对比对话框 -->
    <el-dialog
      v-model="showCompareDialog"
      title="报表对比分析"
      width="90%"
      top="5vh"
    >
      <div class="compare-container">
        <el-form :model="compareParams" label-width="120px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="对比周期一">
                <el-select v-model="compareParams.cycle1" placeholder="请选择对比周期">
                  <el-option
                    v-for="item in cycleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="对比周期二">
                <el-select v-model="compareParams.cycle2" placeholder="请选择对比周期">
                  <el-option
                    v-for="item in cycleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <el-table :data="compareData" v-loading="compareLoading">
          <el-table-column prop="areaName" label="行政区划" fixed />
          <el-table-column prop="compMajorName" label="部件大类" />
          <el-table-column label="部件总数">
            <el-table-column label="周期一" prop="total1" width="100" />
            <el-table-column label="周期二" prop="total2" width="100" />
            <el-table-column label="增减率" width="100">
              <template #default="{ row }">
                <el-tag :type="getChangeRateType(row.totalRate)" size="small">
                  {{ (row.totalRate * 100).toFixed(1) }}%
                </el-tag>
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="完好数量">
            <el-table-column label="周期一" prop="normal1" width="100" />
            <el-table-column label="周期二" prop="normal2" width="100" />
            <el-table-column label="增减率" width="100">
              <template #default="{ row }">
                <el-tag :type="getChangeRateType(row.normalRate)" size="small">
                  {{ (row.normalRate * 100).toFixed(1) }}%
                </el-tag>
              </template>
            </el-table-column>
          </el-table-column>
        </el-table>
      </div>

      <template #footer>
        <el-button @click="showCompareDialog = false">取消</el-button>
        <el-button type="primary" @click="handleCompare">开始对比</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { ElMessage, ElMessageBox } from 'element-plus'

// 图表实例
let chartInstance = null

// 响应式数据
const loading = ref(false)
const showCompareDialog = ref(false)
const compareLoading = ref(false)
const chartType = ref('bar')
const chartContainer = ref(null)

// 查询参数
const queryParams = reactive({
  statCycle: '',
  areaCode: [],
  compMajorId: '',
  compMinorId: '',
  statTimeRange: []
})

// 对比参数
const compareParams = reactive({
  cycle1: '',
  cycle2: ''
})

// 模拟数据
const cycleOptions = ref([
  { value: '2024Q1', label: '2024年第一季度' },
  { value: '2024Q2', label: '2024年第二季度' },
  { value: '2024Q3', label: '2024年第三季度' },
  { value: '2024Q4', label: '2024年第四季度' },
  { value: '2025Q1', label: '2025年第一季度' }
])

const areaOptions = ref([
  {
    value: '110000',
    label: '北京市',
    children: [
      {
        value: '110100',
        label: '市辖区',
        children: [
          { value: '110101', label: '东城区' },
          { value: '110102', label: '西城区' },
          { value: '110105', label: '朝阳区' },
          { value: '110106', label: '丰台区' }
        ]
      }
    ]
  }
])

const majorOptions = ref([
  { value: '1', label: '道路交通设施' },
  { value: '2', label: '市容环境设施' },
  { value: '3', label: '园林绿化设施' },
  { value: '4', label: '公共设施' }
])

const minorOptions = ref([
  { value: '1-1', label: '交通信号灯' },
  { value: '1-2', label: '交通标志牌' },
  { value: '2-1', label: '垃圾箱' },
  { value: '2-2', label: '公共厕所' },
  { value: '3-1', label: '行道树' },
  { value: '3-2', label: '绿化带' }
])

const areaProps = {
  value: 'value',
  label: 'label',
  children: 'children'
}

// 表格数据
const tableData = ref([])
const compareData = ref([])

// 初始化图表
const initChart = () => {
  if (!chartContainer.value) return

  chartInstance = echarts.init(chartContainer.value)
  updateChart()
}

// 更新图表
const updateChart = () => {
  if (!chartInstance) return

  const option = {
    title: {
      text: '管理部件统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: []
  }

  if (chartType.value === 'bar') {
    option.series = [
      {
        name: '部件统计',
        type: 'bar',
        data: tableData.value.map(item => ({
          name: item.areaName + '-' + item.compMajorName,
          value: item.totalCompCount
        })),
        emphasis: {
          focus: 'series'
        }
      }
    ]
    option.xAxis = {
      type: 'category',
      data: tableData.value.map(item => item.areaName + '-' + item.compMajorName)
    }
    option.yAxis = {
      type: 'value'
    }
  } else if (chartType.value === 'pie') {
    option.series = [
      {
        name: '部件状态分布',
        type: 'pie',
        radius: '50%',
        data: [
          { name: '完好', value: tableData.value.reduce((sum, item) => sum + item.normalCompCount, 0) },
          { name: '破损', value: tableData.value.reduce((sum, item) => sum + item.damagedCompCount, 0) },
          { name: '丢失', value: tableData.value.reduce((sum, item) => sum + item.lostCompCount, 0) },
          { name: '废弃', value: tableData.value.reduce((sum, item) => sum + item.discardedCompCount, 0) }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  } else if (chartType.value === 'line') {
    option.series = [
      {
        name: '部件总数',
        type: 'line',
        data: tableData.value.map(item => item.totalCompCount),
        smooth: true
      },
      {
        name: '完好数量',
        type: 'line',
        data: tableData.value.map(item => item.normalCompCount),
        smooth: true
      }
    ]
    option.xAxis = {
      type: 'category',
      data: tableData.value.map(item => item.statCycleName)
    }
    option.yAxis = {
      type: 'value'
    }
  }

  chartInstance.setOption(option)
}

// 生成模拟数据
const generateMockData = () => {
  const mockData = []
  const areas = ['东城区', '西城区', '朝阳区', '丰台区']
  const majors = ['道路交通设施', '市容环境设施', '园林绿化设施', '公共设施']
  const minors = ['交通信号灯', '交通标志牌', '垃圾箱', '公共厕所', '行道树', '绿化带']
  const cycles = ['2024年第一季度', '2024年第二季度', '2024年第三季度', '2024年第四季度', '2025年第一季度']

  cycles.forEach(cycle => {
    areas.forEach(area => {
      majors.forEach(major => {
        const total = Math.floor(Math.random() * 500) + 100
        const normal = Math.floor(total * (0.7 + Math.random() * 0.2))
        const damaged = Math.floor(total * (0.05 + Math.random() * 0.1))
        const lost = Math.floor(total * (0.02 + Math.random() * 0.05))
        const discarded = Math.floor(total * (0.01 + Math.random() * 0.03))
        const remaining = total - normal - damaged - lost - discarded

        mockData.push({
          statMngCompId: generateUUID(),
          statCycle: cycle.replace('年', '').replace('第', 'Q').replace('季度', ''),
          statCycleName: cycle,
          areaCode: '110101',
          areaName: area,
          compMajorId: '1',
          compMajorName: major,
          compMinorId: '1-1',
          compMinorName: minors[Math.floor(Math.random() * minors.length)],
          totalCompCount: total,
          normalCompCount: normal + remaining, // 确保总数正确
          damagedCompCount: damaged,
          lostCompCount: lost,
          discardedCompCount: discarded,
          newCompCount: Math.floor(Math.random() * 50),
          updateCompCount: Math.floor(Math.random() * 30),
          statUser: 'admin',
          statTime: '2024-06-01 10:00:00',
          rptRemark: `统计范围：${area}建成区`,
          damagedRate: damaged / total
        })
      })
    })
  })

  return mockData
}

// 生成UUID
const generateUUID = () => {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    const r = Math.random() * 16 | 0
    const v = c === 'x' ? r : (r & 0x3 | 0x8)
    return v.toString(16)
  })
}

// 事件处理函数
const handleQuery = () => {
  loading.value = true
  // 模拟API调用
  setTimeout(() => {
    tableData.value = generateMockData().slice(0, 20) // 只取前20条用于演示
    loading.value = false
    updateChart()
    ElMessage.success('查询成功')
  }, 500)
}

const handleReset = () => {
  Object.assign(queryParams, {
    statCycle: '',
    areaCode: [],
    compMajorId: '',
    compMinorId: '',
    statTimeRange: []
  })
  ElMessage.info('已重置筛选条件')
}

const handleRefresh = () => {
  handleQuery()
  ElMessage.success('报表已刷新')
}

const handleExport = (type) => {
  ElMessage.success(`正在导出${type.toUpperCase()}文件...`)
  // 实际项目中这里会调用导出API
}

const handleDrillDown = (row) => {
  ElMessageBox.confirm(
    `是否要钻取查看【${row.areaName} - ${row.compMajorName}】的详细数据？`,
    '钻取确认',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    ElMessage.info('钻取功能开发中...')
    // 实际项目中这里会跳转到钻取页面或打开钻取对话框
  })
}

const handleViewDetail = (row) => {
  ElMessage.info(`查看详情：${row.statCycleName} - ${row.areaName} - ${row.compMajorName}`)
  // 实际项目中这里会打开详情对话框或跳转到详情页面
}

const handleRowClick = (row) => {
  console.log('点击行数据:', row)
  // 可以在这里实现行点击后的交互逻辑
}

const handleChartTypeChange = () => {
  updateChart()
}

const handleCompare = () => {
  if (!compareParams.cycle1 || !compareParams.cycle2) {
    ElMessage.warning('请选择两个对比周期')
    return
  }

  compareLoading.value = true
  // 模拟对比数据生成
  setTimeout(() => {
    compareData.value = [
      {
        areaName: '东城区',
        compMajorName: '道路交通设施',
        total1: 350,
        total2: 380,
        normal1: 280,
        normal2: 310,
        totalRate: (380 - 350) / 350,
        normalRate: (310 - 280) / 280
      },
      {
        areaName: '西城区',
        compMajorName: '市容环境设施',
        total1: 420,
        total2: 450,
        normal1: 350,
        normal2: 380,
        totalRate: (450 - 420) / 420,
        normalRate: (380 - 350) / 350
      }
    ]
    compareLoading.value = false
    ElMessage.success('对比分析完成')
  }, 1000)
}

const getChangeRateType = (rate) => {
  if (rate > 0) return 'success'
  if (rate < 0) return 'danger'
  return 'info'
}

const tableRowClassName = ({ row }) => {
  if (row.damagedRate > 0.1) {
    return 'warning-row'
  }
  return ''
}

// 生命周期
onMounted(() => {
  nextTick(() => {
    initChart()
    // 初始化时加载数据
    handleQuery()
  })
})

// 组件卸载时销毁图表实例
import { onUnmounted } from 'vue'
onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose()
  }
})
</script>

<style scoped>
.bi-mng-matter-container {
  padding: 20px;
  background-color: #f5f7fa;
}

.filter-section {
  margin-bottom: 20px;
}

.filter-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-actions {
  display: flex;
  gap: 10px;
}

.chart-section {
  margin-bottom: 20px;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-container {
  width: 100%;
}

.table-section {
  margin-bottom: 20px;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.compare-container {
  max-height: 60vh;
  overflow-y: auto;
}

/* 状态样式 */
.status-normal {
  color: #67c23a;
  font-weight: bold;
}

.status-damaged {
  color: #e6a23c;
  font-weight: bold;
}

.status-lost {
  color: #f56c6c;
  font-weight: bold;
}

.status-discarded {
  color: #909399;
  font-weight: bold;
}

/* 表格行样式 */
:deep(.warning-row) {
  background-color: #fef0f0;
}

:deep(.warning-row:hover > td) {
  background-color: #fde2e2 !important;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .bi-mng-matter-container {
    padding: 10px;
  }

  .header-actions {
    flex-wrap: wrap;
  }

  .chart-container {
    height: 300px !important;
  }
}
</style>
