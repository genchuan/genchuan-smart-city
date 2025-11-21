<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联应用场景详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <template #default>
      <div class="detail-container">
        <!-- 内容区域 -->
        <div class="detail-content">
          <!-- 基础信息卡片 -->
          <div class="grid-layout">
            <!-- 基础信息 -->
            <el-card class="info-card base-info-card">
              <div class="card-header">基础信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">主键ID：</span>
                  <span class="info-value">{{ data.id || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">关联ID：</span>
                  <span class="info-value text-blue-600 font-bold">{{
                    data.deviceRelAppSceneId || '-'
                  }}</span>
                </div>
              </div>
            </el-card>

            <!-- 设备信息 -->
            <el-card class="info-card device-info-card">
              <div class="card-header">设备信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">设备ID：</span>
                  <span class="info-value text-blue-600 font-bold">{{ data.deviceId || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">设备名称：</span>
                  <span class="info-value">{{ data.deviceName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">设备编码：</span>
                  <span class="info-value">{{ data.deviceCode || '-' }}</span>
                </div>
              </div>
            </el-card>

            <!-- 应用场景信息 -->
            <el-card class="info-card scene-info-card">
              <div class="card-header">应用场景信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">应用场景ID：</span>
                  <span class="info-value text-blue-600 font-bold">{{
                    data.appSceneId || '-'
                  }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">应用场景名称：</span>
                  <span class="info-value">{{ data.appSceneName || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">应用场景编码：</span>
                  <span class="info-value">{{ data.appSceneCode || '-' }}</span>
                </div>
              </div>
            </el-card>

            <!-- 关联信息 -->
            <el-card class="info-card relation-info-card">
              <div class="card-header">关联信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">关联时间：</span>
                  <span class="info-value">{{ formatDate(data.relTime) }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">关联人：</span>
                  <span class="info-value">{{ data.relUser || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">关联状态：</span>
                  <span :class="data.relStatus === '1' ? 'status-active' : 'status-inactive'">
                    {{ data.relStatus === '1' ? '已关联' : '未关联' }}
                  </span>
                </div>
              </div>
            </el-card>

            <!-- 调整信息 -->
            <el-card class="info-card adjust-info-card" :span="2">
              <div class="card-header">调整信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">调整时间：</span>
                  <span class="info-value">{{ formatDate(data.adjustTime) }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">调整人：</span>
                  <span class="info-value">{{ data.adjustUser || '-' }}</span>
                </div>
                <div class="info-item full-width">
                  <span class="info-label">调整原因：</span>
                  <span class="info-value">{{ data.adjustReason || '-' }}</span>
                </div>
              </div>
            </el-card>

            <!-- 扩展信息 -->
            <el-card class="info-card ext-info-card" :span="2">
              <div class="card-header">扩展信息</div>
              <div class="card-body">
                <div class="info-item">
                  <span class="info-label">分类扩展字段1：</span>
                  <span class="info-value">{{ data.extCat1 || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">分类扩展字段2：</span>
                  <span class="info-value">{{ data.extCat2 || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">通用扩展字段1：</span>
                  <span class="info-value">{{ data.extCommon1 || '-' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">通用扩展字段2：</span>
                  <span class="info-value">{{ data.extCommon2 || '-' }}</span>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </template>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceAppSceneVO } from '@/api/dataHub/deviceManage/deviceOpManage/deviceappscene'

defineOptions({ name: 'DeviceAppSceneDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 抽屉状态
const visible = ref(false)
const title = ref('设备关联应用场景详情')
const data = ref<DeviceAppSceneVO>({} as DeviceAppSceneVO)
const isFullscreen = ref(false)

// 打开抽屉
const open = (row: DeviceAppSceneVO) => {
  data.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 暴露方法
defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.detail-header {
  padding: 16px 20px;
  border-bottom: 1px solid #e5e7eb;
}

.detail-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-size: 14px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 12px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.device-info-card .card-header {
  background-color: #fff7e6;
  color: #fa8c16;
}

.scene-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.relation-info-card .card-header {
  background-color: #fff0f0;
  color: #f5222d;
}

.adjust-info-card .card-header {
  background-color: #f0f5ff;
  color: #40a9ff;
}

.ext-info-card .card-header {
  background-color: #f9f0ff;
  color: #722ed1;
}

.card-body {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  padding: 6px 0;
  border-bottom: 1px dashed #f0f2f5;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  width: 100px;
  color: #666;
  flex-shrink: 0;
}

.info-value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.full-width {
  grid-column: span 2;
}

.status-active {
  color: #52c41a;
  font-weight: 500;
}

.status-inactive {
  color: #f5222d;
  font-weight: 500;
}

.text-blue-600 {
  color: #1890ff;
}

/* 适配全屏模式 */
:deep(.el-drawer__container) {
  .el-drawer.is-fullscreen {
    height: 100% !important;
    max-height: 100vh !important;
  }
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .full-width {
    grid-column: span 1;
  }
}
</style>
