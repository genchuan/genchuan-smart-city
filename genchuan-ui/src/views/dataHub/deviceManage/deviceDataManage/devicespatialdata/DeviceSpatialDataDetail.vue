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
        <span class="text-lg font-bold text-gray-800">设备空间数据详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card hover-lift">
          <div class="card-header">基础信息</div>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">空间数据ID：</span>
            <span class="value">{{ data.deviceSpatialId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value primary">{{ data.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value primary">{{ data.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value">{{ data.deviceCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 空间坐标信息卡片 -->
        <el-card class="info-card spatial-info-card hover-lift">
          <div class="card-header">空间坐标信息</div>
          <div class="info-item">
            <span class="label">坐标系类型：</span>
            <span class="value">{{ data.coordSystem || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">坐标X(度)：</span>
            <span class="value primary">{{ data.coordX || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">坐标Y(度)：</span>
            <span class="value primary">{{ data.coordY || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">高程(米)：</span>
            <span class="value">{{ data.elevation || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">定位精度(米)：</span>
            <span class="value">{{ data.positionAccuracy || '-' }}</span>
          </div>
        </el-card>

        <!-- 数据信息卡片 -->
        <el-card class="info-card data-info-card hover-lift">
          <div class="card-header">数据信息</div>
          <div class="info-item">
            <span class="label">空间数据格式：</span>
            <span class="value">{{ data.spatialDataFormat || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">数据来源：</span>
            <span class="value">{{ data.dataSource || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ data.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ data.extCat2 || '-' }}</span>
          </div>
        </el-card>

        <!-- 操作信息卡片 -->
        <el-card class="info-card operation-info-card hover-lift">
          <div class="card-header">操作信息</div>
          <div class="info-item">
            <span class="label">录入人：</span>
            <span class="value">{{ data.inputUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">录入时间：</span>
            <span class="value">{{ formatDate(data.inputTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新人：</span>
            <span class="value">{{ data.updateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(data.updateTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段：</span>
            <span class="value">{{ data.extCommon1 || '-' }} / {{ data.extCommon2 || '-' }}</span>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceSpatialDataVO } from '@/api/dataHub/deviceManage/deviceDataManage/devicespatialdata'
import { watch } from 'vue' // 引入watch监听props变化
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏，避免直接修改props
const drawerVisible = ref(props.visible)
// 监听props变化同步到本地状态
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const data = ref<Partial<DeviceSpatialDataVO>>({})
const isFullscreen = ref(false)
const title = ref('设备空间数据详情')

// 设置详情数据
const setData = (row: DeviceSpatialDataVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 0;
}

.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.hover-lift:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-size: 15px;
  font-weight: 500;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 10px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.spatial-info-card .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.data-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.operation-info-card .card-header {
  background-color: #fff0f6;
  color: #f5222d;
}

.info-item {
  padding: 8px 16px;
  display: flex;
  line-height: 1.8;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
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

/* 响应式调整 */
@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
