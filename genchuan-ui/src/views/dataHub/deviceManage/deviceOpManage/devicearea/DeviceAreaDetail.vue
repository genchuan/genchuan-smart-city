<template>
  <el-drawer
    v-model="innerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联行政区划详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card basic-info-card">
          <div class="card-header">基本信息</div>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联ID：</span>
            <span class="value primary">{{ data.deviceRelRegionId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value primary">{{ data.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value">{{ data.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value">{{ data.deviceCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="info-card region-info-card">
          <div class="card-header">行政区划信息</div>
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value primary">{{ data.regionCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value">{{ data.regionName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划级别：</span>
            <span class="value">{{ data.regionLevel || '-' }}</span>
          </div>
        </el-card>

        <!-- 关联信息卡片 -->
        <el-card class="info-card relation-info-card">
          <div class="card-header">关联信息</div>
          <div class="info-item">
            <span class="label">关联时间：</span>
            <span class="value">{{ formatDate(data.relTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联人：</span>
            <span class="value">{{ data.relUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联状态：</span>
            <span class="value status">
              <el-tag :type="data.relStatus === '1' ? 'success' : 'danger'">
                {{ data.relStatus === '1' ? '已关联' : '未关联' }}
              </el-tag>
            </span>
          </div>
        </el-card>

        <!-- 调整信息卡片 -->
        <el-card class="info-card adjust-info-card">
          <div class="card-header">调整信息</div>
          <div class="info-item">
            <span class="label">调整时间：</span>
            <span class="value">{{ formatDate(data.adjustTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整人：</span>
            <span class="value">{{ data.adjustUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整原因：</span>
            <span class="value">{{ data.adjustReason || '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card" style="grid-column: span 2">
          <div class="card-header">扩展信息</div>
          <div class="grid-two-columns">
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
import { ref, defineProps, defineEmits, watch } from 'vue'
import { DeviceAreaVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicearea'

// 定义属性
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DeviceAreaVO,
    default: () => ({})
  }
})

// 定义事件
const emit = defineEmits(['update:visible', 'close'])

// 解决直接修改props的问题：使用内部状态
const innerVisible = ref(props.visible)
// 监听props变化同步到内部状态
watch(
  () => props.visible,
  (newVal) => {
    innerVisible.value = newVal
  }
)

// 全屏状态
const isFullscreen = ref(false)
const title = ref('设备关联行政区划详情')

// 处理关闭
const handleClose = () => {
  emit('update:visible', false)
  emit('close')
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}
</script>

<style scoped>
.detail-container {
  padding: 20px;
  min-height: calc(100% - 60px);
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
  font-size: 16px;
  font-weight: 600;
  color: #333;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 16px;
}

/* 马克龙色系标题背景 */
.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.region-info-card .card-header {
  background-color: #fff7e6;
}

.relation-info-card .card-header {
  background-color: #f6ffed;
}

.adjust-info-card .card-header {
  background-color: #fff0f0;
}

.ext-info-card .card-header {
  background-color: #f0f7ff;
}

.info-item {
  display: flex;
  padding: 0 16px 12px;
  line-height: 1.5;
}

.label {
  flex: 0 0 120px;
  color: #666;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #333;
  font-size: 14px;
}

.value.primary {
  color: #1890ff;
  font-weight: 600;
}

.grid-two-columns {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}

/* 替换 ::v-deep 写法 */
:deep(.el-drawer__body) {
  padding: 0;
  overflow: auto;
}
</style>
