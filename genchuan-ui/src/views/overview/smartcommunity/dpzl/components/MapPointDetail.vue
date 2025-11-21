<template>
  <div class="map-point-detail">
    <div class="detail-header">
      <h2>{{ detail.title }}</h2>
      <div class="header-actions">
        <el-button @click="$emit('close')">关闭</el-button>
        <el-button type="primary" @click="handleEdit" v-if="detail.allowEdit">编辑信息</el-button>
        <el-button @click="handleNavigate" v-if="detail.coordinates">导航到此</el-button>
      </div>
    </div>

    <div class="detail-content">
      <!-- 基本信息 -->
      <div class="section">
        <h3>基本信息</h3>
        <div class="basic-info">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="名称">{{ detail.name || '--' }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ detail.type || '--' }}</el-descriptions-item>
            <el-descriptions-item label="位置">{{ detail.location || '--' }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag :type="getStatusType(detail.status)">
                {{ getStatusText(detail.status) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="所属网格">{{ detail.gridName || '--' }}</el-descriptions-item>
            <el-descriptions-item label="负责人">{{ detail.manager || '--' }}</el-descriptions-item>
            <el-descriptions-item label="联系电话">{{ detail.phone || '--' }}</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{ detail.createTime || '--' }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <!-- 坐标信息 -->
      <div class="section" v-if="detail.coordinates">
        <h3>坐标信息</h3>
        <div class="coordinate-info">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="经度">{{ detail.coordinates[0] }}</el-descriptions-item>
            <el-descriptions-item label="纬度">{{ detail.coordinates[1] }}</el-descriptions-item>
            <el-descriptions-item label="坐标系">WGS84</el-descriptions-item>
            <el-descriptions-item label="精度">±5米</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>

      <!-- 详细信息 -->
      <div class="section" v-if="detail.desc || detail.remark">
        <h3>详细信息</h3>
        <div class="detail-info">
          <div class="info-item" v-if="detail.desc">
            <label>描述:</label>
            <span>{{ detail.desc }}</span>
          </div>
          <div class="info-item" v-if="detail.remark">
            <label>备注:</label>
            <span>{{ detail.remark }}</span>
          </div>
        </div>
      </div>

      <!-- 操作记录 -->
      <div class="section" v-if="operationRecords.length > 0">
        <h3>操作记录</h3>
        <div class="operation-records">
          <el-timeline>
            <el-timeline-item
              v-for="record in operationRecords"
              :key="record.id"
              :timestamp="record.time"
              :type="getRecordType(record.action)"
            >
              <div class="record-content">
                <div class="record-action">{{ record.action }}</div>
                <div class="record-operator">操作人: {{ record.operator }}</div>
                <div class="record-desc" v-if="record.desc">{{ record.desc }}</div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <!-- 快速操作 -->
      <div class="section">
        <h3>快速操作</h3>
        <div class="quick-actions">
          <el-button-group>
            <el-button @click="handleRepair" v-if="detail.type === '设施故障' || detail.status === 'offline'">
              <el-icon><Tools /></el-icon>
              报修
            </el-button>
            <el-button @click="handleMaintenance" v-if="detail.type === '资源点位'">
              <el-icon><Setting /></el-icon>
              维护记录
            </el-button>
            <el-button @click="handleShare">
              <el-icon><Share /></el-icon>
              分享位置
            </el-button>
            <el-button @click="handlePrint">
              <el-icon><Printer /></el-icon>
              打印信息
            </el-button>
          </el-button-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { Tools, Setting, Share, Printer } from '@element-plus/icons-vue'
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js'

const props = defineProps({
  detail: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['close', 'edit', 'navigate', 'repair'])

// 操作记录
const operationRecords = ref([])

// 加载点位详情
const loadPointDetail = async () => {
  try {
    // 根据点位类型调用不同的API
    if (props.detail.type === '资源点位') {
      const resourcesData = await GlobalSituationOverviewAPI.getMngComponents({
        communityId: 'comm1001'
      });
      if (resourcesData.data && resourcesData.data.length > 0) {
        // 找到对应的资源
        const resourceDetail = resourcesData.data.find(r => r.id === props.detail.id?.replace('resource_', ''));
        if (resourceDetail) {
          Object.assign(props.detail, {
            name: resourceDetail.comp_name,
            type: resourceDetail.comp_type,
            location: resourceDetail.location,
            status: resourceDetail.status === 1 ? 'normal' : 'offline',
            gridName: resourceDetail.grid_name,
            createTime: resourceDetail.install_time,
            desc: resourceDetail.remark
          });
        }
      }
    } else if (props.detail.type === '异常事件') {
      const eventsData = await GlobalSituationOverviewAPI.getMonitorEvents({
        communityId: 'comm1001'
      });
      if (eventsData.data && eventsData.data.length > 0) {
        const eventDetail = eventsData.data.find(e => e.id === props.detail.id?.replace('event_', ''));
        if (eventDetail) {
          Object.assign(props.detail, {
            name: eventDetail.evt_name,
            type: eventDetail.evt_cat_name,
            location: eventDetail.location,
            status: eventDetail.evt_status === 0 ? 'warning' : eventDetail.evt_status === 1 ? 'handling' : 'resolved',
            gridName: eventDetail.grid_name,
            createTime: eventDetail.occur_time,
            desc: eventDetail.evt_desc
          });
        }
      }
    }
  } catch (error) {
    console.error('加载点位详情失败:', error);
    ElMessage.error('点位详情加载失败');
  }
}

// 计算属性
const getStatusType = (status) => {
  const typeMap = {
    normal: 'success',
    warning: 'warning',
    offline: 'danger',
    pending: 'warning',
    handling: 'info',
    resolved: 'success'
  }
  return typeMap[status] || 'info'
}

const getStatusText = (status) => {
  const textMap = {
    normal: '正常',
    warning: '告警',
    offline: '离线',
    pending: '待处理',
    handling: '处理中',
    resolved: '已解决'
  }
  return textMap[status] || '未知'
}

const getRecordType = (action) => {
  const typeMap = {
    '创建': 'primary',
    '编辑': 'warning',
    '修复': 'success',
    '报修': 'danger',
    '维护': 'info'
  }
  return typeMap[action] || 'info'
}

// 方法
const handleEdit = () => {
  emit('edit', props.detail)
}

const handleNavigate = () => {
  emit('navigate', props.detail)
  ElMessage.success('开始导航到该位置')
}

const handleRepair = () => {
  emit('repair', props.detail)
  ElMessage.info('发起报修请求')
}

const handleMaintenance = () => {
  ElMessage.info('查看维护记录')
}

const handleShare = () => {
  ElMessage.success('位置信息已复制到剪贴板')
}

const handlePrint = () => {
  window.print()
}

// 初始化操作记录
const initOperationRecords = () => {
  operationRecords.value = [
    {
      id: 1,
      action: '创建',
      operator: '系统',
      time: '2024-01-15 10:30:00',
      desc: '点位自动创建'
    },
    {
      id: 2,
      action: '编辑',
      operator: '管理员',
      time: '2024-01-15 11:20:00',
      desc: '更新位置信息'
    },
    {
      id: 3,
      action: '维护',
      operator: '维护组',
      time: '2024-01-15 14:15:00',
      desc: '定期维护检查'
    }
  ]
}

onMounted(async () => {
  await loadPointDetail();
  initOperationRecords();
})
</script>

<style lang="scss" scoped>
.map-point-detail {
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
      font-size: 20px;
    }
  }

  .detail-content {
    max-height: 60vh;
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

  .basic-info,
  .coordinate-info {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;

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

  .detail-info {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;

    .info-item {
      display: flex;
      margin-bottom: 10px;

      label {
        color: #9fbdff;
        min-width: 60px;
        margin-right: 10px;
      }

      span {
        color: #fff;
        flex: 1;
      }

      &:last-child {
        margin-bottom: 0;
      }
    }
  }

  .operation-records {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;

    :deep(.el-timeline) {
      .el-timeline-item {
        .el-timeline-item__timestamp {
          color: #9fbdff;
          font-size: 12px;
        }

        .el-timeline-item__node {
          background-color: #00ccff;
        }

        .el-timeline-item__wrapper {
          .record-content {
            .record-action {
              color: #fff;
              font-weight: 500;
              margin-bottom: 4px;
            }

            .record-operator {
              color: #9fbdff;
              font-size: 12px;
              margin-bottom: 2px;
            }

            .record-desc {
              color: #ccc;
              font-size: 12px;
            }
          }
        }
      }
    }
  }

  .quick-actions {
    background: rgba(0, 30, 60, 0.4);
    border-radius: 8px;
    padding: 15px;

    :deep(.el-button-group) {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;

      .el-button {
        flex: 1;
        min-width: 120px;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 5px;
      }
    }
  }
}

// 响应式设计
@media (max-width: 768px) {
  .detail-header {
    flex-direction: column;
    gap: 10px;
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

  .quick-actions {
    :deep(.el-button-group) {
      flex-direction: column;

      .el-button {
        min-width: auto;
      }
    }
  }
}
</style>
