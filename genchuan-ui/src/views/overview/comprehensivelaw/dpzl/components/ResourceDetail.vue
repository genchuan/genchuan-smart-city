<template>
  <div class="resource-detail-container">
    <div class="detail-header">
      <h3>{{ getResourceTitle(resourceType) }}详情</h3>
      <div class="header-actions">
        <el-button type="primary" size="small" @click="exportData">
          <i class="el-icon-download"></i> 导出数据
        </el-button>
        <el-button size="small" @click="refresh">
          <i class="el-icon-refresh"></i> 刷新
        </el-button>
      </div>
    </div>

    <div class="detail-content">
      <!-- 基本信息 -->
      <div class="basic-info-section">
        <h4>基本信息</h4>
        <div class="info-grid">
          <div class="info-item">
            <label>资源类型：</label>
            <span>{{ resourceTypeText }}</span>
          </div>
          <div class="info-item">
            <label>资源总数：</label>
            <span class="highlight">{{ basicInfo.totalCount }}</span>
          </div>
          <div class="info-item">
            <label>在用数量：</label>
            <span class="highlight success">{{ basicInfo.inUseCount }}</span>
          </div>
          <div class="info-item">
            <label>闲置数量：</label>
            <span class="highlight warning">{{ basicInfo.idleCount }}</span>
          </div>
          <div class="info-item">
            <label>故障数量：</label>
            <span class="highlight danger">{{ basicInfo.faultCount }}</span>
          </div>
          <div class="info-item">
            <label>使用率：</label>
            <span class="highlight">{{ basicInfo.usageRate }}%</span>
          </div>
        </div>
      </div>

      <!-- 资源列表 -->
      <div class="resource-list-section">
        <div class="section-header">
          <h4>资源列表</h4>
          <div class="list-actions">
            <el-input
              v-model="searchKeyword"
              placeholder="搜索资源..."
              size="small"
              style="width: 200px; margin-right: 10px;"
              clearable
            >
              <template #prefix>
                <i class="el-icon-search"></i>
              </template>
            </el-input>
            <el-select v-model="statusFilter" placeholder="状态筛选" size="small" clearable>
              <el-option label="全部" value="" />
              <el-option label="在用" value="in_use" />
              <el-option label="闲置" value="idle" />
              <el-option label="故障" value="fault" />
            </el-select>
          </div>
        </div>

        <div class="resource-table">
          <el-table
            :data="filteredResources"
            height="300"
            style="width: 100%"
            border
            stripe
            size="small"
            @row-click="handleRowClick"
          >
            <el-table-column prop="resourceCode" label="资源编号" width="120" />
            <el-table-column prop="resourceName" label="资源名称" width="150" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="getStatusType(scope.row.status)" size="small">
                  {{ getStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="department" label="所属部门" width="150" />
            <el-table-column prop="location" label="当前位置" width="180" />
            <el-table-column prop="lastUseTime" label="最后使用时间" width="160" />
            <el-table-column prop="maintenanceDue" label="下次维护时间" width="160" />
            <el-table-column label="操作" width="120" fixed="right">
              <template #default="scope">
                <el-button
                  type="text"
                  size="small"
                  @click.stop="viewResourceDetail(scope.row)"
                >
                  详情
                </el-button>
                <el-button
                  v-if="scope.row.status === 'idle'"
                  type="text"
                  size="small"
                  @click.stop="dispatchResource(scope.row)"
                >
                  调度
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="pagination-wrapper">
            <el-pagination
              v-model:current-page="currentPage"
              v-model:page-size="pageSize"
              :total="totalCount"
              :page-sizes="[10, 20, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              small
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </div>
      </div>

      <!-- 使用趋势 -->
      <div class="trend-section">
        <h4>使用趋势</h4>
        <div class="trend-charts">
          <div class="chart-container">
            <div class="chart-header">
              <span>使用率趋势</span>
              <el-radio-group v-model="trendPeriod" size="small">
                <el-radio-button label="week">近一周</el-radio-button>
                <el-radio-button label="month">近一月</el-radio-button>
                <el-radio-button label="quarter">近一季度</el-radio-button>
              </el-radio-group>
            </div>
            <div class="chart-wrapper" ref="usageChartRef"></div>
          </div>

          <div class="chart-container">
            <div class="chart-header">
              <span>状态分布</span>
            </div>
            <div class="chart-wrapper" ref="statusChartRef"></div>
          </div>
        </div>
      </div>

      <!-- 预警信息 -->
      <div v-if="warnings.length" class="warning-section">
        <h4>预警信息</h4>
        <el-alert
          v-for="(warning, index) in warnings"
          :key="index"
          :title="warning.title"
          :type="warning.type"
          :description="warning.description"
          show-icon
          :closable="false"
          style="margin-bottom: 10px;"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import * as echarts from 'echarts'
import type { ECharts } from 'echarts'

const props = defineProps<{
  data: {
    type: string
  }
}>()

const resourceType = ref(props.data.type || 'staff')
const searchKeyword = ref('')
const statusFilter = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const totalCount = ref(0)
const trendPeriod = ref('month')

// 图表引用
const usageChartRef = ref<HTMLElement>()
const statusChartRef = ref<HTMLElement>()
let usageChart: ECharts | null = null
let statusChart: ECharts | null = null

// 数据
const basicInfo = reactive({
  totalCount: 120,
  inUseCount: 85,
  idleCount: 25,
  faultCount: 10,
  usageRate: 70.8
})

const resources = ref<any[]>([
  {
    id: 1,
    resourceCode: 'RES001',
    resourceName: '执法人员-张三',
    status: 'in_use',
    department: '执法一队',
    location: 'XX街道巡查',
    lastUseTime: '2024-01-15 08:30',
    maintenanceDue: '2024-02-01'
  },
  {
    id: 2,
    resourceCode: 'VEH001',
    resourceName: '执法车辆-京A12345',
    status: 'idle',
    department: '执法一队',
    location: '执法大队停车场',
    lastUseTime: '2024-01-14 17:00',
    maintenanceDue: '2024-03-01'
  },
  {
    id: 3,
    resourceCode: 'DEV001',
    resourceName: '执法记录仪-001',
    status: 'fault',
    department: '执法二队',
    location: '设备维修室',
    lastUseTime: '2024-01-10 14:00',
    maintenanceDue: '2024-01-20'
  }
])

const warnings = ref([
  {
    title: '设备完好率预警',
    type: 'warning',
    description: '设备完好率低于90%，请及时安排维护'
  },
  {
    title: '资源闲置预警',
    type: 'info',
    description: '有25台设备处于闲置状态，建议合理调配'
  }
])

// 计算属性
const resourceTypeText = computed(() => {
  const map: Record<string, string> = {
    staff: '执法人员',
    vehicle: '执法车辆',
    device: '执法设备',
    material: '应急物资'
  }
  return map[resourceType.value] || resourceType.value
})

const filteredResources = computed(() => {
  return resources.value.filter(item => {
    const keywordMatch = !searchKeyword.value ||
      item.resourceCode.includes(searchKeyword.value) ||
      item.resourceName.includes(searchKeyword.value) ||
      item.department.includes(searchKeyword.value)

    const statusMatch = !statusFilter.value || item.status === statusFilter.value

    return keywordMatch && statusMatch
  })
})

// 方法
const getResourceTitle = (type: string) => {
  const map: Record<string, string> = {
    staff: '执法人员在岗率',
    vehicle: '执法车辆使用率',
    device: '执法设备完好率',
    material: '应急物资库存'
  }
  return map[type] || type
}

const getStatusType = (status: string) => {
  const map: Record<string, string> = {
    in_use: 'success',
    idle: 'warning',
    fault: 'danger'
  }
  return map[status] || 'info'
}

const getStatusText = (status: string) => {
  const map: Record<string, string> = {
    in_use: '在用',
    idle: '闲置',
    fault: '故障'
  }
  return map[status] || status
}

const handleRowClick = (row: any) => {
  viewResourceDetail(row)
}

const viewResourceDetail = (resource: any) => {
  ElMessageBox.alert(
    `
    <div class="resource-detail-popup">
      <h3>${resource.resourceName}</h3>
      <div class="detail-grid">
        <div><strong>资源编号：</strong>${resource.resourceCode}</div>
        <div><strong>状态：</strong><span class="status-${resource.status}">${getStatusText(resource.status)}</span></div>
        <div><strong>所属部门：</strong>${resource.department}</div>
        <div><strong>当前位置：</strong>${resource.location}</div>
        <div><strong>最后使用时间：</strong>${resource.lastUseTime}</div>
        <div><strong>下次维护时间：</strong>${resource.maintenanceDue}</div>
      </div>
    </div>
    `,
    '资源详情',
    {
      dangerouslyUseHTMLString: true,
      customClass: 'resource-detail-dialog'
    }
  )
}

const dispatchResource = (resource: any) => {
  ElMessageBox.confirm(
    `确定要调度资源 ${resource.resourceName} 吗？`,
    '资源调度确认',
    {
      confirmButtonText: '确认调度',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    ElMessage.success('调度指令已发送')
    // 实际开发中调用调度API
  }).catch(() => {
    // 取消操作
  })
}

const exportData = () => {
  ElMessage.success('导出数据功能开发中')
}

const refresh = () => {
  loadResourceData()
}

const loadResourceData = async () => {
  try {
    // 实际开发中调用API获取数据
    // const response = await getResourceDetail(resourceType.value, {
    //   page: currentPage.value,
    //   size: pageSize.value
    // })
    // resources.value = response.data.list
    // totalCount.value = response.data.total

    // 更新图表
    updateCharts()
  } catch (error) {
    console.error('加载资源数据失败:', error)
    ElMessage.error('数据加载失败')
  }
}

const updateCharts = () => {
  // 更新使用率趋势图
  if (usageChartRef.value && usageChart) {
    const option = {
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top: '15%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        data: ['01-10', '01-11', '01-12', '01-13', '01-14', '01-15', '01-16']
      },
      yAxis: {
        type: 'value',
        min: 0,
        max: 100,
        axisLabel: {
          formatter: '{value}%'
        }
      },
      series: [
        {
          name: '使用率',
          type: 'line',
          data: [65, 68, 70, 72, 71, 73, 75],
          smooth: true,
          itemStyle: {
            color: '#3B82F6'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: 'rgba(59, 130, 246, 0.6)' },
              { offset: 1, color: 'rgba(59, 130, 246, 0.1)' }
            ])
          }
        }
      ]
    }
    usageChart.setOption(option)
  }

  // 更新状态分布图
  if (statusChartRef.value && statusChart) {
    const option = {
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        right: 10,
        top: 'center'
      },
      series: [
        {
          name: '状态分布',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: '16',
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: [
            { value: 85, name: '在用', itemStyle: { color: '#22C55E' } },
            { value: 25, name: '闲置', itemStyle: { color: '#F59E0B' } },
            { value: 10, name: '故障', itemStyle: { color: '#EF4444' } }
          ]
        }
      ]
    }
    statusChart.setOption(option)
  }
}

const handleSizeChange = (size: number) => {
  pageSize.value = size
  currentPage.value = 1
  loadResourceData()
}

const handleCurrentChange = (page: number) => {
  currentPage.value = page
  loadResourceData()
}

// 生命周期
onMounted(() => {
  if (usageChartRef.value) {
    usageChart = echarts.init(usageChartRef.value)
  }
  if (statusChartRef.value) {
    statusChart = echarts.init(statusChartRef.value)
  }

  loadResourceData()

  // 监听窗口大小变化
  window.addEventListener('resize', () => {
    usageChart?.resize()
    statusChart?.resize()
  })
})

// 清理
import { onUnmounted } from 'vue'
onUnmounted(() => {
  usageChart?.dispose()
  statusChart?.dispose()
  window.removeEventListener('resize', () => {})
})
</script>

<style lang="scss" scoped>
.resource-detail-container {
  .detail-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid #E2E8F0;

    h3 {
      margin: 0;
      color: #0891B2;
      font-size: 18px;
      font-weight: 600;
    }
  }

  .detail-content {
    > div {
      margin-bottom: 25px;
    }

    h4 {
      margin: 0 0 15px 0;
      color: #1E293B;
      font-size: 16px;
      font-weight: 500;
      padding-left: 8px;
      border-left: 4px solid #0891B2;
    }
  }

  .basic-info-section {
    .info-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 15px;

      @media (max-width: 1200px) {
        grid-template-columns: repeat(2, 1fr);
      }

      @media (max-width: 768px) {
        grid-template-columns: 1fr;
      }

      .info-item {
        background: #F8FAFC;
        border-radius: 8px;
        padding: 15px;
        border: 1px solid #E2E8F0;

        label {
          color: #64748B;
          font-size: 14px;
          margin-right: 8px;
        }

        .highlight {
          font-size: 16px;
          font-weight: bold;

          &.success {
            color: #22C55E;
          }

          &.warning {
            color: #F59E0B;
          }

          &.danger {
            color: #EF4444;
          }
        }
      }
    }
  }

  .resource-list-section {
    .section-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 15px;
    }

    .pagination-wrapper {
      display: flex;
      justify-content: flex-end;
      margin-top: 15px;
      padding: 10px;
      background: #F8FAFC;
      border-radius: 0 0 6px 6px;
      border: 1px solid #E2E8F0;
      border-top: none;
    }
  }

  .trend-section {
    .trend-charts {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px;

      @media (max-width: 1200px) {
        grid-template-columns: 1fr;
      }

      .chart-container {
        background: #FFFFFF;
        border-radius: 8px;
        padding: 15px;
        border: 1px solid #E2E8F0;

        .chart-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 15px;

          span {
            font-size: 14px;
            font-weight: 500;
            color: #1E293B;
          }
        }

        .chart-wrapper {
          width: 100%;
          height: 250px;
        }
      }
    }
  }

  .warning-section {
    h4 {
      color: #EF4444 !important;
      border-left-color: #EF4444;
    }
  }
}

// 弹窗样式
:deep(.resource-detail-dialog) {
  .resource-detail-popup {
    h3 {
      margin-top: 0;
      color: #0891B2;
    }

    .detail-grid {
      display: grid;
      gap: 10px;

      div {
        padding: 8px 0;
        border-bottom: 1px solid #F1F5F9;

        &:last-child {
          border-bottom: none;
        }

        strong {
          color: #64748B;
          margin-right: 8px;
        }
      }
    }
  }
}
</style>
