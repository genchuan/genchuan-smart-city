<template>
  <div class="resource-detail">
    <div class="detail-header">
      <div class="header-main">
        <div class="resource-icon">
          <div class="custom-icon" :class="resource.type"></div>
        </div>
        <div class="header-info">
          <h2>{{ resource.name }}</h2>
          <div class="resource-meta">
            <el-tag :type="resource.status === 'online' ? 'success' : 'danger'">
              {{ resource.status === 'online' ? '在线' : '离线' }}
            </el-tag>
            <span class="resource-type">{{ resource.type }}</span>
            <span class="resource-id">ID: {{ resource.id }}</span>
          </div>
        </div>
      </div>
      <div class="header-actions">
        <el-button @click="$emit('close')">关闭</el-button>
        <el-button type="primary" @click="handleEdit">编辑信息</el-button>
        <el-button @click="handleMaintenance" v-if="resource.type !== '服务点'">
          <el-icon><Setting /></el-icon>
          维护记录
        </el-button>
      </div>
    </div>

    <div class="detail-content">
      <!-- 资源状态概览 -->
      <div class="section">
        <h3>资源状态概览</h3>
        <div class="status-overview">
          <div class="status-cards">
            <div class="status-card">
              <div class="card-icon online">
                <el-icon><Check /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ statusStats.onlineTime }}</div>
                <div class="card-label">在线时长</div>
              </div>
            </div>
            <div class="status-card">
              <div class="card-icon maintenance">
                <el-icon><Tools /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ statusStats.maintenanceCount }}</div>
                <div class="card-label">维护次数</div>
              </div>
            </div>
            <div class="status-card">
              <div class="card-icon uptime">
                <el-icon><TrendCharts /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ statusStats.uptimeRate }}</div>
                <div class="card-label">可用率</div>
              </div>
            </div>
            <div class="status-card">
              <div class="card-icon health">
                <el-icon><Monitor /></el-icon>
              </div>
              <div class="card-content">
                <div class="card-value">{{ statusStats.healthScore }}</div>
                <div class="card-label">健康评分</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 基本信息 -->
      <div class="section">
        <h3>基本信息</h3>
        <div class="basic-info-grid">
          <div class="info-group">
            <h4>位置信息</h4>
            <el-descriptions :column="1" border>
              <el-descriptions-item label="详细位置">{{ resource.location }}</el-descriptions-item>
              <el-descriptions-item label="所属网格">{{ resource.gridName }}</el-descriptions-item>
              <el-descriptions-item label="坐标">
                {{ resource.coordinates ? `${resource.coordinates[0]}, ${resource.coordinates[1]}` : '--' }}
              </el-descriptions-item>
            </el-descriptions>
          </div>
          <div class="info-group">
            <h4>设备信息</h4>
            <el-descriptions :column="1" border>
              <el-descriptions-item label="设备型号">{{ resource.model || '--' }}</el-descriptions-item>
              <el-descriptions-item label="序列号">{{ resource.serialNumber || '--' }}</el-descriptions-item>
              <el-descriptions-item label="安装时间">{{ resource.installTime || '--' }}</el-descriptions-item>
            </el-descriptions>
          </div>
          <div class="info-group">
            <h4>管理信息</h4>
            <el-descriptions :column="1" border>
              <el-descriptions-item label="负责人">{{ resource.manager || '--' }}</el-descriptions-item>
              <el-descriptions-item label="联系电话">{{ resource.phone || '--' }}</el-descriptions-item>
              <el-descriptions-item label="维护单位">{{ resource.maintenanceUnit || '--' }}</el-descriptions-item>
            </el-descriptions>
          </div>
        </div>
      </div>

      <!-- 实时监测数据 -->
      <div class="section" v-if="realtimeData && resource.type !== '服务点'">
        <h3>实时监测数据</h3>
        <div class="realtime-monitoring">
          <div class="monitoring-cards">
            <div class="monitoring-card" v-for="item in realtimeData" :key="item.name">
              <div class="monitoring-value">{{ item.value }}</div>
              <div class="monitoring-name">{{ item.name }}</div>
              <div class="monitoring-unit">{{ item.unit }}</div>
              <div class="monitoring-status" :class="item.status">
                {{ item.status === 'normal' ? '正常' : '异常' }}
              </div>
            </div>
          </div>
          <div class="monitoring-chart">
            <ChartLine :data="monitoringChartData" height="120" :showGrid="false" />
          </div>
        </div>
      </div>

      <!-- 维护记录 -->
      <div class="section" v-if="maintenanceRecords.length > 0">
        <h3>维护记录</h3>
        <div class="maintenance-records">
          <el-table :data="maintenanceRecords" style="width: 100%">
            <el-table-column prop="time" label="维护时间" width="150" />
            <el-table-column prop="type" label="维护类型" width="120" />
            <el-table-column prop="operator" label="维护人员" width="120" />
            <el-table-column prop="content" label="维护内容" />
            <el-table-column prop="result" label="维护结果" width="100">
              <template #default="{ row }">
                <el-tag :type="row.result === '正常' ? 'success' : 'warning'" size="small">
                  {{ row.result }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="80">
              <template #default="{ row }">
                <el-button size="small" link @click="viewMaintenanceDetail(row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>

      <!-- 告警历史 -->
      <div class="section" v-if="alertHistory.length > 0">
        <h3>告警历史</h3>
        <div class="alert-history">
          <el-timeline>
            <el-timeline-item
              v-for="alert in alertHistory"
              :key="alert.id"
              :timestamp="alert.time"
              :type="alert.level === 'high' ? 'danger' : 'warning'"
            >
              <div class="alert-content">
                <div class="alert-title">{{ alert.title }}</div>
                <div class="alert-desc">{{ alert.desc }}</div>
                <div class="alert-meta">
                  <span class="alert-level">级别: {{ getAlertLevelText(alert.level) }}</span>
                  <span class="alert-status">状态: {{ alert.resolved ? '已解决' : '未解决' }}</span>
                </div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <!-- 快速操作 -->
      <div class="section">
        <h3>快速操作</h3>
        <div class="quick-actions">
          <el-button
            type="danger"
            v-if="resource.status === 'offline'"
            @click="handleRepair"
          >
            <el-icon><Tools /></el-icon>
            立即报修
          </el-button>
          <el-button @click="handleReset" v-if="resource.type === '监控'">
            <el-icon><Refresh /></el-icon>
            重启设备
          </el-button>
          <el-button @click="handleTest" v-if="resource.type === '消防设施'">
            <el-icon><Check /></el-icon>
            功能测试
          </el-button>
          <el-button @click="handleShare">
            <el-icon><Share /></el-icon>
            分享信息
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import { ElMessage } from 'element-plus'
import {
  Sunny as Light, VideoCamera, Bell as Fire, Box, Setting, Check,
  Tools, TrendCharts, Monitor, Refresh, Share
} from '@element-plus/icons-vue'
import ChartLine from './ChartLine.vue'
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js'

const props = defineProps({
  resource: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['close', 'edit', 'maintenance'])

// 状态统计
const statusStats = reactive({
  onlineTime: '98.5%',
  maintenanceCount: '12次',
  uptimeRate: '99.2%',
  healthScore: '95分'
})

// 实时监测数据
const realtimeData = ref([])
const monitoringChartData = ref({
  xAxis: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00'],
  series: [{ name: '监测值', data: [] }]
})

// 维护记录
const maintenanceRecords = ref([])

// 告警历史
const alertHistory = ref([])

// 加载资源详情数据
const loadResourceDetail = async () => {
  try {
    // 如果有资源ID，从API加载详细数据
    if (props.resource.id) {
      const resourcesData = await GlobalSituationOverviewAPI.getMngComponents({
        communityId: 'comm1001'
      });

      if (resourcesData.data && resourcesData.data.length > 0) {
        // 找到对应的资源
        const resourceDetail = resourcesData.data.find(r => r.id === props.resource.id);
        if (resourceDetail) {
          // 更新资源详情信息
          Object.assign(props.resource, {
            name: resourceDetail.comp_name,
            type: resourceDetail.comp_type,
            location: resourceDetail.location,
            status: resourceDetail.status === 1 ? 'online' : 'offline',
            gridName: resourceDetail.grid_name,
            installTime: resourceDetail.install_time,
            remark: resourceDetail.remark
          });
        }
      }
    }
  } catch (error) {
    console.error('加载资源详情失败:', error);
    ElMessage.error('资源详情加载失败');
  }
}

// 方法
const handleEdit = () => {
  emit('edit', props.resource)
}

const handleMaintenance = () => {
  emit('maintenance', props.resource)
}

const handleRepair = () => {
  ElMessage.success('报修请求已提交')
}

const handleReset = () => {
  ElMessage.info('设备重启指令已发送')
}

const handleTest = () => {
  ElMessage.info('功能测试开始执行')
}

const handleShare = () => {
  ElMessage.success('资源信息已复制到剪贴板')
}

const viewMaintenanceDetail = (record) => {
  ElMessage.info(`查看维护记录: ${record.time}`)
}

const getAlertLevelText = (level) => {
  const levelMap = {
    high: '高',
    medium: '中',
    low: '低'
  }
  return levelMap[level] || '未知'
}

// 初始化数据
const initData = () => {
  // 根据资源类型初始化实时数据
  const realtimeConfig = {
    '路灯': [
      { name: '电压', value: '220', unit: 'V', status: 'normal' },
      { name: '功率', value: '150', unit: 'W', status: 'normal' },
      { name: '亮度', value: '85', unit: '%', status: 'normal' },
      { name: '温度', value: '45', unit: '°C', status: 'normal' }
    ],
    '监控': [
      { name: '信号强度', value: '92', unit: '%', status: 'normal' },
      { name: '存储使用', value: '65', unit: '%', status: 'normal' },
      { name: '网络延迟', value: '28', unit: 'ms', status: 'normal' },
      { name: '在线时长', value: '256', unit: '天', status: 'normal' }
    ],
    '消防设施': [
      { name: '水压', value: '0.8', unit: 'MPa', status: 'normal' },
      { name: '水位', value: '95', unit: '%', status: 'normal' },
      { name: '电池电量', value: '88', unit: '%', status: 'normal' },
      { name: '信号状态', value: '良好', unit: '', status: 'normal' }
    ]
  }

  realtimeData.value = realtimeConfig[props.resource.type] || []

  // 初始化图表数据
  const chartData = {
    '路灯': [218, 221, 219, 220, 219, 220],
    '监控': [90, 91, 92, 91, 92, 92],
    '消防设施': [0.75, 0.78, 0.80, 0.79, 0.80, 0.80]
  }

  monitoringChartData.value.series[0].data = chartData[props.resource.type] || []

  // 初始化维护记录
  maintenanceRecords.value = [
    {
      time: '2024-01-10 14:30',
      type: '日常检查',
      operator: '张三',
      content: '设备运行状态检查，各项参数正常',
      result: '正常'
    },
    {
      time: '2023-12-25 09:15',
      type: '定期维护',
      operator: '李四',
      content: '更换老化部件，清洁设备表面',
      result: '正常'
    },
    {
      time: '2023-11-20 16:45',
      type: '故障修复',
      operator: '王五',
      content: '修复电源线路故障',
      result: '已修复'
    }
  ]

  // 初始化告警历史
  alertHistory.value = [
    {
      id: 1,
      time: '2024-01-12 08:20',
      title: '设备离线告警',
      desc: '设备信号中断，检测不到在线状态',
      level: 'high',
      resolved: true
    },
    {
      id: 2,
      time: '2023-12-18 14:35',
      title: '参数异常',
      desc: '监测到电压波动异常',
      level: 'medium',
      resolved: true
    }
  ]
}

onMounted(async () => {
  await loadResourceDetail();
  initData();
})

// 监听资源ID变化
watch(() => props.resource.id, async () => {
  await loadResourceDetail();
})
</script>

<style lang="scss" scoped>
.resource-detail {
  .detail-header {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    .header-main {
      display: flex;
      align-items: center;
      gap: 15px;

      .resource-icon {
        font-size: 40px;
        color: #00ccff;
      }

      .header-info {
        h2 {
          margin: 0 0 8px 0;
          color: #00ccff;
          font-size: 24px;
        }

        .resource-meta {
          display: flex;
          align-items: center;
          gap: 10px;

          .resource-type {
            color: #9fbdff;
            font-size: 14px;
          }

          .resource-id {
            color: #666;
            font-size: 12px;
          }
        }
      }
    }
  }

  .detail-content {
    max-height: 70vh;
    overflow-y: auto;
  }

  .section {
    margin-bottom: 25px;

    h3 {
      color: #00ccff;
      margin-bottom: 15px;
      font-size: 16px;
      border-left: 3px solid #00ccff;
      padding-left: 8px;
    }
  }

  .status-overview {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 20px;
  }

  .status-cards {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;

    .status-card {
      background: rgba(0, 30, 60, 0.6);
      border-radius: 8px;
      padding: 15px;
      display: flex;
      align-items: center;
      gap: 12px;
      transition: all 0.3s;

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(0, 204, 255, 0.2);
      }

      .card-icon {
        width: 50px;
        height: 50px;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 24px;

        &.online {
          background: rgba(82, 196, 26, 0.2);
          color: #52c41a;
        }

        &.maintenance {
          background: rgba(24, 144, 255, 0.2);
          color: #1890ff;
        }

        &.uptime {
          background: rgba(250, 173, 20, 0.2);
          color: #faad14;
        }

        &.health {
          background: rgba(0, 204, 255, 0.2);
          color: #00ccff;
        }
      }

      .card-content {
        .card-value {
          font-size: 20px;
          font-weight: bold;
          color: #00ccff;
          margin-bottom: 4px;
        }

        .card-label {
          font-size: 12px;
          color: #9fbdff;
        }
      }
    }
  }

  .basic-info-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 15px;

    .info-group {
      background: rgba(0, 30, 60, 0.4);
      border-radius: 8px;
      padding: 15px;

      h4 {
        margin: 0 0 10px 0;
        color: #9fbdff;
        font-size: 14px;
      }

      :deep(.el-descriptions) {
        .el-descriptions__label {
          color: #9fbdff;
          background: rgba(0, 30, 60, 0.6);
        }

        .el-descriptions__content {
          color: #fff;
          background: rgba(0, 30, 60, 0.4);
        }
      }
    }
  }

  .realtime-monitoring {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 20px;

    .monitoring-cards {
      display: grid;
      grid-template-columns: repeat(4, 1fr);
      gap: 15px;
      margin-bottom: 15px;

      .monitoring-card {
        background: rgba(0, 30, 60, 0.6);
        border-radius: 6px;
        padding: 12px;
        text-align: center;
        position: relative;

        .monitoring-value {
          font-size: 24px;
          font-weight: bold;
          color: #00ccff;
          margin-bottom: 4px;
        }

        .monitoring-name {
          font-size: 12px;
          color: #9fbdff;
          margin-bottom: 2px;
        }

        .monitoring-unit {
          font-size: 11px;
          color: #666;
          margin-bottom: 6px;
        }

        .monitoring-status {
          position: absolute;
          top: 8px;
          right: 8px;
          font-size: 10px;
          padding: 2px 6px;
          border-radius: 10px;

          &.normal {
            background: rgba(82, 196, 26, 0.2);
            color: #52c41a;
          }

          &.abnormal {
            background: rgba(255, 77, 79, 0.2);
            color: #ff4d4f;
          }
        }
      }
    }
  }

  .maintenance-records,
  .alert-history {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;
  }

  .alert-history {
    :deep(.el-timeline) {
      .el-timeline-item {
        .el-timeline-item__timestamp {
          color: #9fbdff;
        }

        .alert-content {
          .alert-title {
            color: #fff;
            font-weight: 500;
            margin-bottom: 4px;
          }

          .alert-desc {
            color: #ccc;
            font-size: 12px;
            margin-bottom: 4px;
          }

          .alert-meta {
            display: flex;
            gap: 15px;
            font-size: 11px;
            color: #666;
          }
        }
      }
    }
  }

  .quick-actions {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;

    .el-button {
      margin-right: 10px;
      margin-bottom: 8px;
    }
  }
}
/* 资源详情页图标 */
.resource-icon .custom-icon {
  width: 60px;
  height: 60px;
  border-radius: 12px;
  background-size: 40px 40px;
  background-repeat: no-repeat;
  background-position: center;
  border: 3px solid;
}

/* 使用相同的图标类名和样式，但尺寸更大 */
.resource-icon .custom-icon.路灯 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M511.6 3.4c-247.5 0-448.1 204-448.1 454.9 0 93.5 23 171.1 75.5 252.6 103.7 172.8 305.5 308.5 373 308.5 67.4 0 268.8-136.6 373-308.5 51.6-81.5 75.5-159.2 75.5-252.6-0.8-251-201.4-454.9-448.9-454.9z m0 821.8c-206.1 0-373.4-167.3-373.4-373.4 0-206.1 167.3-373.4 373.4-373.4C717.7 78.5 885 245.7 885 451.9c0 206.1-167.3 373.3-373.4 373.3z m0 0' fill='%2352c41a'/%3E%3Cpath d='M512 451.9m-373.4 0a373.4 373.4 0 1 0 746.8 0 373.4 373.4 0 1 0-746.8 0Z' fill='%23FFFFFF'/%3E%3Cpath d='M690 299.1v-19.2c0-26.9-21.8-48.6-48.6-48.6H381.9c-26.9 0-48.6 21.8-48.6 48.6v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0-20.6-13-38.9-32.4-45.7v-19.2c0-9 7.3-16.2 16.2-16.2h113.5v275.6h-6.8c-9 0-17.5 3.6-23.9 10-6.3 6.4-9.9 15-9.8 23.9l-7 95.8h-49.8c-8.7 0.4-15.5 7.5-15.5 16.2s6.8 15.8 15.5 16.2h243.2c9 0 16.2-7.3 16.2-16.2 0-9-7.3-16.2-16.2-16.2h-66l-7.1-97c0-17.5-15-32.7-33.7-32.7h-6.8V263.7h113.5c9 0 16.2 7.3 16.2 16.2v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0.2-20.6-12.8-38.9-32.2-45.7z m-340.5 61.8c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0 9-7.3 16.2-16.2 16.2z m186.3 212.3l7 95.8h-62.4l6.9-94.6c0-2 0.6-2.6 1.3-2.6h45.9c0.8-0.1 1.3 0.5 1.3 1.4z m137.9-212.3c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0.1 9-7.2 16.2-16.2 16.2z m0 0' fill='%2352c41a'/%3E%3C/svg%3E");
  background-color: rgba(82, 196, 26, 0.1);
  border-color: #52c41a;
}

.resource-icon .custom-icon.监控 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M512 320.2c-80.3 0-145.7 69.5-145.7 154.8 0 41.4 15.2 80.3 42.7 109.5 27.5 29.2 64.1 45.4 103 45.4 38.8 0 75.4-16.1 102.9-45.4 27.5-29.3 42.7-68.2 42.7-109.5 0.1-85.3-65.3-154.8-145.6-154.8z m-0.5 223.7c-18.3 0-35.5-7.6-48.4-21.5-12.9-13.9-20.1-32.3-20.1-52 0-40.5 30.7-73.5 68.5-73.5s68.5 33 68.5 73.5-30.7 73.5-68.5 73.5z' fill='%231890ff'/%3E%3Cpath d='M881 421.2v-5.4c-0.8-95-40.3-184-111.2-250.7C699.5 98.9 606.6 62.5 509 63.9c-203.4 1.6-367.6 161.9-366 357.4v5.9c1.4 150.1 100.9 281.9 248.5 330.1l-48.7 125.1h-97.2c-18.5 0-36.1 11.5-40.5 29.4-6.3 25.5 13.8 48.3 39.3 48.3h534.2c18.5 0 36.1-11.5 40.5-29.5 6.2-25.5-13.8-48.2-39.3-48.2h-98.5l-48.7-125c53.7-17.5 103.1-47.3 143.3-86.6 68.7-67 106-155.7 105.1-249.6zM429 882.4l44.6-118.8c14.3 1.5 27.3 2.3 39.6 2.3h9.3c11.6-0.1 23.8-0.8 37.2-2.2l44.6 118.7H429z m237.3-233.6c-45.2 26.6-98.3 39.3-151.7 39.4H509c-157.6 0-286.9-119-288.3-265.2v-5.8C219.4 268 349 145.6 509.7 144.4h2.3c159.3 0 290 120.3 291.3 268.2v5.3c0.9 92.8-48.7 178.8-137 230.9z' fill='%231890ff'/%3E%3Cpath d='M512 280.4c21.4 0 38.8-17.4 38.8-38.8s-17.4-38.8-39-38.8c-6.6 0.2-13.3 2-19.3 5.3-11.8 6.9-19.3 19.8-19.4 33.5 0.1 21.3 17.5 38.8 38.9 38.8z' fill='%231890ff'/%3E%3C/svg%3E");
  background-color: rgba(24, 144, 255, 0.1);
  border-color: #1890ff;
}

.resource-icon .custom-icon.消防设施 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M491.054545 0c1.070545 0 2.048 0.186182 3.072 0.325818h43.287273C538.530909 0.186182 539.461818 0 540.532364 0h10.612363a26.996364 26.996364 0 0 1 26.624 27.415273l-0.046545 0.651636c77.730909 16.663273 141.032727 73.541818 168.820363 148.48h44.125091c25.134545 0 45.428364 18.664727 45.428364 41.751273 0 23.04-20.293818 41.704727-45.428364 41.704727h-27.229091V439.854545c16.011636-3.258182 32.581818 1.024 45.149091 11.68291 12.613818 10.658909 19.921455 26.530909 19.921455 43.287272v125.253818c0 16.756364-7.307636 32.674909-19.874909 43.333819-12.613818 10.658909-29.184 14.941091-45.149091 11.682909v241.105454H837.818182V1024H193.815273v-107.845818H268.101818v-241.105455c-15.965091 3.258182-32.581818-1.024-45.149091-11.636363a56.738909 56.738909 0 0 1-19.921454-43.380364v-125.207273c0-16.756364 7.307636-32.674909 19.921454-43.333818 12.567273-10.658909 29.184-14.894545 45.149091-11.682909V260.002909H231.610182C206.568727 260.002909 186.181818 241.338182 186.181818 218.298182c0-23.086545 20.340364-41.751273 45.428364-41.751273h53.527273C312.878545 101.608727 376.180364 44.683636 453.911273 28.066909L453.864727 27.415273A26.996364 26.996364 0 0 1 480.488727 0zM512 445.067636c-84.759273 0-153.413818 70.656-153.413818 157.789091 0 87.226182 68.654545 157.882182 153.367273 157.882182 84.712727 0 153.413818-70.656 153.413818-157.835636S596.666182 445.067636 511.953455 445.067636z m-49.570909 69.352728a96.488727 96.488727 0 0 1 99.095273 0 102.353455 102.353455 0 0 1 49.338181 88.436363 102.353455 102.353455 0 0 1-49.338181 88.436364 96.488727 96.488727 0 0 1-99.141819 0 102.353455 102.353455 0 0 1-49.338181-88.436364 102.353455 102.353455 0 0 1 49.338181-88.436363z' fill='%23ff4d4f'/%3E%3C/svg%3E");
  background-color: rgba(255, 77, 79, 0.1);
  border-color: #ff4d4f;
}

.resource-icon .custom-icon.垃圾点 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1215 1024'%3E%3Cpath d='M962.757223 434.927522a252.237202 252.237202 0 0 0-252.237203 252.237202c0 210.197669 252.237202 336.835276 252.237203 336.835276s252.237202-126.118601 252.237202-336.835276a252.237202 252.237202 0 0 0-252.237202-252.237202z m0 336.835276a84.079067 84.079067 0 1 1 84.079067-84.079068 84.079067 84.079067 0 0 1-84.079067 84.079068z' fill='%23faad14'/%3E%3Cpath d='M684.569691 687.164724a278.187532 278.187532 0 0 1 205.007602-267.288394V323.341105H80.965028v646.68221h756.1926a381.988849 381.988849 0 0 1-152.587937-282.858591z m-280.263558 120.928535H323.341105V485.271161h80.965028z m242.376077 0H565.717182V485.271161h80.965028zM970.023315 80.965028h-323.341105V0H323.341105v80.965028H0v161.411049h970.023315V80.965028z' fill='%23faad14'/%3E%3C/svg%3E");
  background-color: rgba(250, 173, 20, 0.1);
  border-color: #faad14;
}
// 响应式设计
@media (max-width: 1200px) {
  .status-cards {
    grid-template-columns: repeat(2, 1fr) !important;
  }

  .basic-info-grid {
    grid-template-columns: 1fr !important;
  }

  .monitoring-cards {
    grid-template-columns: repeat(2, 1fr) !important;
  }
}

@media (max-width: 768px) {
  .detail-header {
    flex-direction: column;
    gap: 15px;

    .header-main {
      flex-direction: column;
      text-align: center;
    }
  }

  .status-cards {
    grid-template-columns: 1fr !important;
  }

  .monitoring-cards {
    grid-template-columns: 1fr !important;
  }
}
</style>
