<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">监测事件分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="detail-content">
        <el-row :gutter="20">
          <!-- 基本信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card base-info-card">
              <div class="card-header">基本信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="label">主键：</span>
                  <span class="value">{{ data?.id || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">报表ID：</span>
                  <span class="value primary">{{ data?.rptId || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">统计周期：</span>
                  <span class="value">{{ data?.statCycle || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">统计周期名称：</span>
                  <span class="value">{{ data?.statCycleName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">报表生成时间：</span>
                  <span class="value">{{ formatDate(data?.rptCreateTime) }}</span>
                </div>
                <div class="info-item">
                  <span class="label">报表生成人：</span>
                  <span class="value">{{ data?.rptCreateUser || '-' }}</span>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 区域信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card area-info-card">
              <div class="card-header">区域信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="label">行政区划代码：</span>
                  <span class="value">{{ data?.adminCode || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">行政区划名称：</span>
                  <span class="value primary">{{ data?.adminName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">分域ID：</span>
                  <span class="value">{{ data?.domId || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">分域名称：</span>
                  <span class="value">{{ data?.domName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">高发区域代码：</span>
                  <span class="value">{{ data?.highIncidenceCode || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">高发区域名称：</span>
                  <span class="value">{{ data?.highIncidenceName || '-' }}</span>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 事件分类卡片 -->
          <el-col :span="12">
            <el-card class="info-card event-type-card">
              <div class="card-header">事件分类信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="label">监测事件大类ID：</span>
                  <span class="value">{{ data?.monEvtMajorId || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">监测事件大类名称：</span>
                  <span class="value primary">{{ data?.monEvtMajorName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">监测事件小类ID：</span>
                  <span class="value">{{ data?.monEvtMinorId || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">监测事件小类名称：</span>
                  <span class="value">{{ data?.monEvtMinorName || '-' }}</span>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 统计数据卡片 -->
          <el-col :span="12">
            <el-card class="info-card stats-card">
              <div class="card-header">统计数据</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="label">事件总数：</span>
                  <span class="value primary">{{ data?.totalEvtCount || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="label">高发区域事件占比：</span>
                  <span class="value">{{
                    data?.highIncidenceRatio ? data.highIncidenceRatio + '%' : '-'
                  }}</span>
                </div>
                <div class="info-item">
                  <span class="label">事件办结率：</span>
                  <span class="value status-good">{{
                    data?.evtCompleteRate ? data.evtCompleteRate + '%' : '-'
                  }}</span>
                </div>
                <div class="info-item">
                  <span class="label">平均响应时长：</span>
                  <span class="value">{{ data?.avgRespEndure || '-' }} 分钟</span>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 扩展信息卡片 -->
          <el-col :span="24">
            <el-card class="info-card extra-info-card">
              <div class="card-header">扩展信息</div>
              <div class="card-body">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <div class="info-item">
                      <span class="label">报表备注：</span>
                      <span class="value">{{ data?.rptRemark || '-' }}</span>
                    </div>
                    <div class="info-item">
                      <span class="label">分类扩展字段1：</span>
                      <span class="value">{{ data?.extCat1 || '-' }}</span>
                    </div>
                    <div class="info-item">
                      <span class="label">分类扩展字段2：</span>
                      <span class="value">{{ data?.extCat2 || '-' }}</span>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="info-item">
                      <span class="label">通用扩展字段1：</span>
                      <span class="value">{{ data?.extCommon1 || '-' }}</span>
                    </div>
                    <div class="info-item">
                      <span class="label">通用扩展字段2：</span>
                      <span class="value">{{ data?.extCommon2 || '-' }}</span>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomMonEvtMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/dommonevtmonrpt'

defineOptions({ name: 'DomMonEvtMonRptDetail' })

// 时间格式化
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DomMonEvtMonRptVO | null,
    default: null
  }
})

// Emits
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}
</script>

<style scoped>
.detail-drawer {
  ::v-deep .el-drawer__body {
    padding: 0;
    background-color: #f5f7fa;
    overflow-y: auto;
  }
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 16px 24px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.detail-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.info-card {
  margin-bottom: 20px;
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-size: 15px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 10px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.area-info-card .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.event-type-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.stats-card .card-header {
  background-color: #fff0f6;
  color: #f5222d;
}

.extra-info-card .card-header {
  background-color: #f0f5ff;
  color: #40a9ff;
}

.card-body {
  padding: 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 130px;
  color: #666;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.value.primary {
  color: #1890ff;
  font-weight: 500;
}

.value.status-good {
  color: #52c41a;
  font-weight: 500;
}

.value.status-bad {
  color: #f5222d;
  font-weight: 500;
}
</style>
