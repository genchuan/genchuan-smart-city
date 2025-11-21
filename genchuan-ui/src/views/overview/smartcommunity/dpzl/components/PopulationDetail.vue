<template>
  <div class="population-detail">
    <div class="detail-header">
      <h2>{{ detailTitle }}</h2>
      <div class="header-actions">
        <el-button @click="$emit('close')">关闭</el-button>
        <el-button type="primary" @click="exportData">导出数据</el-button>
        <el-button @click="printReport">打印报告</el-button>
      </div>
    </div>
    <div class="detail-content">
      <!-- 人口概览 -->
      <div class="section">
        <h3>人口概览</h3>
        <div class="population-overview">
          <div class="overview-cards">
            <div class="overview-card total">
              <div class="card-icon">
                <el-icon><User /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ populationData.total }}</div>
                <div class="card-label">总人口</div>
                <div class="card-change" :class="populationData.totalChangeType">
                  {{ populationData.totalChange }}
                </div>
              </div>
            </div>
            <div class="overview-card permanent">
              <div class="card-icon">
                <el-icon><Home /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ populationData.permanent }}</div>
                <div class="card-label">常住人口</div>
                <div class="card-change" :class="populationData.permanentChangeType">
                  {{ populationData.permanentChange }}
                </div>
              </div>
            </div>
            <div class="overview-card float">
              <div class="card-icon">
                <el-icon><Location /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ populationData.float }}</div>
                <div class="card-label">流动人口</div>
                <div class="card-change" :class="populationData.floatChangeType">
                  {{ populationData.floatChange }}
                </div>
              </div>
            </div>
            <div class="overview-card density">
              <div class="card-icon">
                <el-icon><TrendCharts /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ populationData.density }}</div>
                <div class="card-label">人口密度</div>
                <div class="card-unit">人/平方公里</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 人口流动分析 -->
      <div class="section">
        <h3>人口流动分析</h3>
        <div class="migration-analysis">
          <div class="migration-charts">
            <div class="chart-container">
              <h4>月度流动趋势</h4>
              <ChartLine :data="migrationTrendData" height="200" />
            </div>
            <div class="chart-container">
              <h4>流动人口来源</h4>
              <ChartPie :data="migrationSourceData" :radius="['40%', '60%']" />
            </div>
          </div>
          <div class="migration-stats">
            <div class="stat-card inflow">
              <div class="stat-icon">
                <el-icon><Top /></el-icon>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ migrationStats.inflow }}</div>
                <div class="stat-label">本月流入</div>
                <div class="stat-change">同比 +12.5%</div>
              </div>
            </div>
            <div class="stat-card outflow">
              <div class="stat-icon">
                <el-icon><Bottom /></el-icon>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ migrationStats.outflow }}</div>
                <div class="stat-label">本月流出</div>
                <div class="stat-change">同比 +8.3%</div>
              </div>
            </div>
            <div class="stat-card net">
              <div class="stat-icon">
                <el-icon><TrendCharts /></el-icon>
              </div>
              <div class="stat-content">
                <div class="stat-value">{{ migrationStats.net }}</div>
                <div class="stat-label">净流入</div>
                <div class="stat-change">同比增长</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 数据分析报告 -->
      <div class="section">
        <h3>数据分析报告</h3>
        <div class="analysis-report">
          <div class="report-content">
            <div class="report-section">
              <h4>人口特征分析</h4>
              <p>{{ populationData.analysis.features }}</p>
            </div>
            <div class="report-section">
              <h4>发展趋势</h4>
              <p>{{ populationData.analysis.trends }}</p>
            </div>
            <div class="report-section">
              <h4>建议措施</h4>
              <p>{{ populationData.analysis.suggestions }}</p>
            </div>
          </div>
          <div class="report-meta">
            <el-descriptions :column="1" border>
              <el-descriptions-item label="数据更新时间">{{ populationData.updateTime }}</el-descriptions-item>
              <el-descriptions-item label="数据来源">sys_pop 人口管理表</el-descriptions-item>
              <el-descriptions-item label="统计周期">{{ populationData.period }}</el-descriptions-item>
              <el-descriptions-item label="数据质量">{{ populationData.quality }}</el-descriptions-item>
            </el-descriptions>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import {
  User, House as Home, Location, TrendCharts, Top, Bottom
} from '@element-plus/icons-vue'
import ChartPie from './ChartPie.vue'
import ChartLine from './ChartLine.vue'
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js'

const props = defineProps({
  grid: {
    type: Object,
    default: null
  },
  ageGroup: {
    type: String,
    default: ''
  },
  hukouType: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['close'])

// 人口数据
const populationData = reactive({
  total: '12,560',
  permanent: '10,048',
  float: '2,512',
  density: '8,372',
  totalChange: '+2.3%',
  totalChangeType: 'increase',
  permanentChange: '+1.8%',
  permanentChangeType: 'increase',
  floatChange: '+5.2%',
  floatChangeType: 'increase',
  updateTime: '2024-01-15 14:30:00',
  period: '2024年1月',
  quality: '优秀',
  analysis: {
    features: '本社区人口结构相对年轻，劳动力资源丰富，老龄化程度较低。常住人口占比80%，流动人口管理规范。',
    trends: '人口总量稳步增长，流动人口增速较快，人口密度适中，社区承载能力良好。',
    suggestions: '建议加强流动人口服务管理，优化公共服务资源配置，关注老年人口服务需求。'
  }
})

// 人口流动数据
const migrationStats = computed(() => {
  const communityId = props.communityId || 'comm1001';
  const data = {
    'comm1001': { inflow: '156', outflow: '89', net: '+67' },
    'comm1002': { inflow: '218', outflow: '125', net: '+93' },
    'comm1003': { inflow: '95', outflow: '62', net: '+33' }
  };
  return data[communityId];
});

const migrationTrendData = computed(() => {
  const communityId = props.communityId || 'comm1001';
  const data = {
    'comm1001': {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      series: [
        {name: '流入人口', data: [120, 135, 142, 148, 152, 156]},
        {name: '流出人口', data: [85, 82, 87, 86, 88, 89]}
      ]
    },
    'comm1002': {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      series: [
        {name: '流入人口', data: [170, 185, 192, 205, 210, 218]},
        {name: '流出人口', data: [100, 105, 110, 115, 120, 125]}
      ]
    },
    'comm1003': {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      series: [
        {name: '流入人口', data: [75, 80, 85, 90, 92, 95]},
        {name: '流出人口', data: [50, 55, 58, 60, 61, 62]}
      ]
    }
  };
  return data[communityId];
});

const migrationSourceData = ref({
  legend: ['省内其他市', '外省', '境外', '其他'],
  series: [65, 25, 5, 5]
})

// 加载人口详情数据
const loadPopulationDetail = async () => {
  try {
    const populationData = await GlobalSituationOverviewAPI.getPopulationDistribution({
      communityId: 'comm1001'
    });

    if (populationData) {
      // 更新人口统计数据
      populationData.total = populationData.total?.toLocaleString() || '12,560';
      populationData.permanent = (populationData.total - populationData.hukouRatio?.nonlocal)?.toLocaleString() || '10,048';
      populationData.float = populationData.hukouRatio?.nonlocal?.toLocaleString() || '2,512';
      populationData.updateTime = populationData.update_time || '2024-01-15 14:30:00';
    }
  } catch (error) {
    console.error('加载人口详情失败:', error);
  }
}

// 计算属性
const detailTitle = computed(() => {
  if (props.grid) {
    return `${props.grid.name}人口详情`
  } else if (props.ageGroup) {
    return `${props.ageGroup}人口分析`
  } else if (props.hukouType) {
    return `${props.hukouType}人口分析`
  } else {
    return '社区人口总览'
  }
})

// 方法
const exportData = () => {
  ElMessage.success('人口数据导出成功')
}

const printReport = () => {
  window.print()
}

// 初始化数据
const initData = () => {
  // 如果有网格参数，更新数据
  if (props.grid) {
    Object.assign(populationData, {
      total: props.grid.population.toString(),
      density: props.grid.density.toString(),
      analysis: {
        features: `${props.grid.name}人口分布相对集中，人口密度${props.grid.density}人/平方公里，属于${props.grid.density > 80 ? '高密度' : '中密度'}区域。`,
        trends: '该网格人口增长稳定，人口结构合理，公共服务需求适中。',
        suggestions: '建议关注人口密度变化，适时调整公共服务资源配置。'
      }
    })
  }

  // 如果有年龄组参数，更新数据
  if (props.ageGroup) {
    const ageConfig = {
      '0-18岁': { count: '1,884', analysis: '青少年人口占比较为合理，教育资源需求稳定。' },
      '19-60岁': { count: '7,536', analysis: '劳动力人口充足，就业服务需求较大。' },
      '60岁以上': { count: '3,140', analysis: '老年人口服务需求增长，需加强养老服务。' }
    }
    const config = ageConfig[props.ageGroup]
    if (config) {
      populationData.analysis.features = `${props.ageGroup}人口${config.count}人，${config.analysis}`
    }
  }

  // 如果有户籍类型参数，更新数据
  if (props.hukouType) {
    const hukouConfig = {
      '本地户籍': { count: '10,048', analysis: '本地户籍人口稳定，社区认同感强。' },
      '外地户籍': { count: '2,512', analysis: '流动人口服务管理需要加强，促进社区融合。' }
    }
    const config = hukouConfig[props.hukouType]
    if (config) {
      populationData.analysis.features = `${props.hukouType}人口${config.count}人，${config.analysis}`
    }
  }
}

onMounted(async () => {
  await loadPopulationDetail();
  initData();
})
</script>

<style lang="scss" scoped>
.population-detail {
  .detail-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    h2 {
      margin: 0;
      color: #00ccff;
      font-size: 24px;
    }
  }

  .detail-content {
    max-height: 80vh;
    overflow-y: auto;
  }

  .section {
    margin-bottom: 30px;

    h3 {
      color: #00ccff;
      margin-bottom: 15px;
      font-size: 18px;
      border-left: 4px solid #00ccff;
      padding-left: 10px;
    }
  }

  .population-overview {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 20px;
  }

  .overview-cards {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;

    .overview-card {
      background: rgba(0, 30, 60, 0.6);
      border-radius: 8px;
      padding: 20px;
      display: flex;
      align-items: center;
      gap: 15px;
      transition: all 0.3s;

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(0, 204, 255, 0.2);
      }

      .card-icon {
        width: 60px;
        height: 60px;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 28px;

        .total & {
          background: rgba(0, 204, 255, 0.2);
          color: #00ccff;
        }

        .permanent & {
          background: rgba(82, 196, 26, 0.2);
          color: #52c41a;
        }

        .float & {
          background: rgba(24, 144, 255, 0.2);
          color: #1890ff;
        }

        .density & {
          background: rgba(250, 173, 20, 0.2);
          color: #faad14;
        }
      }

      .card-content {
        flex: 1;

        .card-value {
          font-size: 28px;
          font-weight: bold;
          margin-bottom: 5px;

          .total & {
            color: #00ccff;
          }

          .permanent & {
            color: #52c41a;
          }

          .float & {
            color: #1890ff;
          }

          .density & {
            color: #faad14;
          }
        }

        .card-label {
          font-size: 14px;
          color: #9fbdff;
          margin-bottom: 4px;
        }

        .card-change {
          font-size: 12px;
          font-weight: 500;

          &.increase {
            color: #52c41a;
          }

          &.decrease {
            color: #ff4d4f;
          }
        }

        .card-unit {
          font-size: 11px;
          color: #666;
        }
      }
    }
  }

  .migration-analysis {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 20px;
  }

  .migration-charts {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 15px;

    .chart-container {
      background: rgba(0, 30, 60, 0.4);
      border-radius: 8px;
      padding: 15px;

      h4 {
        margin: 0 0 10px 0;
        color: #9fbdff;
        font-size: 14px;
        text-align: center;
      }
    }
  }

  .migration-stats {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }

  .stat-card {
    background: rgba(0, 30, 60, 0.6);
    border-radius: 8px;
    padding: 20px;
    display: flex;
    align-items: center;
    gap: 15px;

    .stat-icon {
      width: 50px;
      height: 50px;
      border-radius: 8px;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 24px;

      .inflow & {
        background: rgba(82, 196, 26, 0.2);
        color: #52c41a;
      }

      .outflow & {
        background: rgba(255, 77, 79, 0.2);
        color: #ff4d4f;
      }

      .net & {
        background: rgba(0, 204, 255, 0.2);
        color: #00ccff;
      }
    }

    .stat-content {
      .stat-value {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 5px;

        .inflow & {
          color: #52c41a;
        }

        .outflow & {
          color: #ff4d4f;
        }

        .net & {
          color: #00ccff;
        }
      }

      .stat-label {
        font-size: 14px;
        color: #9fbdff;
        margin-bottom: 4px;
      }

      .stat-change {
        font-size: 12px;
        color: #666;
      }
    }
  }

  .analysis-report {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 20px;
  }

  .report-content {
    margin-bottom: 20px;

    .report-section {
      margin-bottom: 15px;

      h4 {
        color: #9fbdff;
        margin: 0 0 8px 0;
        font-size: 14px;
      }

      p {
        color: #fff;
        line-height: 1.6;
        margin: 0;
        font-size: 14px;
      }

      &:last-child {
        margin-bottom: 0;
      }
    }
  }
}

// 响应式设计
@media (max-width: 1200px) {
  .overview-cards {
    grid-template-columns: repeat(2, 1fr) !important;
  }

  .migration-analysis {
    grid-template-columns: 1fr !important;
  }

  .migration-charts {
    grid-template-columns: 1fr !important;
  }
}

@media (max-width: 768px) {
  .detail-header {
    flex-direction: column;
    gap: 15px;
    align-items: flex-start !important;

    .header-actions {
      width: 100%;
      display: flex;
      gap: 8px;

      .el-button {
        flex: 1;
      }
    }
  }

  .overview-cards {
    grid-template-columns: 1fr !important;
  }
}
</style>
