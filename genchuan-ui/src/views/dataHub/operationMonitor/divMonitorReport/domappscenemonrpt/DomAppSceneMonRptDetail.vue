<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">应用场景分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card" hoverable>
          <div class="card-header">基础信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表ID：</span>
              <span class="value font-bold text-blue">{{ data.rptId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value">{{ data.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期名称：</span>
              <span class="value">{{ data.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表生成时间：</span>
              <span class="value">{{ formatDate(data.rptCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表生成人：</span>
              <span class="value">{{ data.rptCreateUser || '-' }}</span>
            </div>
          </div>
        </el-card>
        <!-- 行政区划信息 -->
        <el-card class="info-card region-info-card" hoverable>
          <div class="card-header">行政区划信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value">{{ data.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value font-bold text-blue">{{ data.adminName || '-' }}</span>
            </div>
          </div>
        </el-card>
        <!-- 应用场景信息 -->
        <el-card class="info-card scene-info-card" hoverable>
          <div class="card-header">应用场景信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">应用场景大类ID：</span>
              <span class="value">{{ data.appSceneMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景大类名称：</span>
              <span class="value">{{ data.appSceneMajorName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景中类ID：</span>
              <span class="value">{{ data.appSceneMidId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景中类名称：</span>
              <span class="value">{{ data.appSceneMidName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景小类ID：</span>
              <span class="value">{{ data.appSceneMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景小类名称：</span>
              <span class="value">{{ data.appSceneMinorName || '-' }}</span>
            </div>
          </div>
        </el-card>
        <!-- 分域信息 -->
        <el-card class="info-card domain-info-card" hoverable>
          <div class="card-header">分域信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">分域ID：</span>
              <span class="value">{{ data.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域名称：</span>
              <span class="value font-bold text-blue">{{ data.domName || '-' }}</span>
            </div>
          </div>
        </el-card>
        <!-- 设备统计信息 -->
        <el-card class="info-card device-info-card" hoverable>
          <div class="card-header">设备统计信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">关联设备总数：</span>
              <span class="value">{{ data.totalDeviceCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备在线率：</span>
              <span
                class="value"
                :class="data.deviceOnlineRate >= 90 ? 'text-green' : 'text-orange'"
              >
                {{ data.deviceOnlineRate ? data.deviceOnlineRate + '%' : '-' }}
              </span>
            </div>
          </div>
        </el-card>
        <!-- 场景事件信息 -->
        <el-card class="info-card event-info-card" hoverable>
          <div class="card-header">场景事件信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">场景事件总数：</span>
              <span class="value">{{ data.sceneEvtCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事件处置率：</span>
              <span class="value" :class="data.evtHandleRate >= 90 ? 'text-green' : 'text-orange'">
                {{ data.evtHandleRate ? data.evtHandleRate + '%' : '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">资源调度次数：</span>
              <span class="value">{{ data.resDispatchCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">场景健康度：</span>
              <span
                class="value"
                :class="
                  data.sceneHealthRate >= 80
                    ? 'text-green'
                    : data.sceneHealthRate >= 60
                      ? 'text-blue'
                      : 'text-red'
                "
              >
                {{ data.sceneHealthRate ? data.sceneHealthRate + '%' : '-' }}
              </span>
            </div>
          </div>
        </el-card>
        <!-- 备注信息 -->
        <el-card class="info-card remark-info-card" hoverable :span="2">
          <div class="card-header">备注信息</div>
          <div class="card-content">
            <div class="info-item full-width">
              <span class="label">报表备注：</span>
              <span class="value">{{ data.rptRemark || '-' }}</span>
            </div>
          </div>
        </el-card>
        <!-- 扩展字段信息 -->
        <el-card class="info-card ext-info-card" hoverable :span="2">
          <div class="card-header">扩展字段信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { DomAppSceneMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/domappscenemonrpt'

const { t } = useI18n()
const emit = defineEmits(['close'])

// 修复核心：解构 props，避免直接使用 props 变量
const props = defineProps<{
  visible: boolean
}>()

// 本地状态管理抽屉显示/隐藏（直接访问解构后的 props.visible）
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 全屏状态
const isFullscreen = ref(false)
// 标题
const title = ref('应用场景分域监测报表详情')
// 详情数据
const data = ref<DomAppSceneMonRptVO>({} as DomAppSceneMonRptVO)

// 设置详情数据
const setData = (row: DomAppSceneMonRptVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

defineExpose({ setData })
</script>
<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  width: 100%;
}

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  font-weight: 600;
  color: #666;
  border-bottom: 1px solid #f0f0f0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.region-info-card .card-header {
  background-color: #fff2e8;
}

.scene-info-card .card-header {
  background-color: #f6ffed;
}

.domain-info-card .card-header {
  background-color: #fff0f6;
}

.device-info-card .card-header {
  background-color: #f0f2ff;
}

.event-info-card .card-header {
  background-color: #fffbe6;
}

.remark-info-card .card-header {
  background-color: #e8f4ff;
}

.ext-info-card .card-header {
  background-color: #f9f0ff;
}

.card-content {
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.remark-info-card .card-content,
.ext-info-card .card-content {
  grid-template-columns: repeat(4, 1fr);
}

.info-item {
  display: flex;
  flex-wrap: wrap;
}

.full-width {
  grid-column: 1 / -1;
}

.label {
  color: #666;
  margin-right: 8px;
  white-space: nowrap;
}

.value {
  color: #333;
  flex: 1;
  word-break: break-all;
}

.font-bold {
  font-weight: 600;
}

.text-blue {
  color: #4096ff;
}

.text-green {
  color: #52c41a;
}

.text-orange {
  color: #fa8c16;
}

.text-red {
  color: #f5222d;
}

@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .remark-info-card .card-content,
  .ext-info-card .card-content {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
