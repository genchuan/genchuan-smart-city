<template>
  <el-drawer
    :title="null"
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    @close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备服务器属性详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 内容区 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">
              <span class="card-title">基本信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">设备名称：</span>
                <span class="info-value font-semibold text-blue-500">{{
                  detailData?.deviceName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备编码：</span>
                <span class="info-value">{{ detailData?.deviceCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备ID：</span>
                <span class="info-value">{{ detailData?.deviceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">属性配置ID：</span>
                <span class="info-value">{{ detailData?.serverAttrCfgId || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 属性信息卡片 -->
          <el-card class="info-card attr-info-card">
            <div class="card-header">
              <span class="card-title">属性信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">属性名称：</span>
                <span class="info-value font-semibold text-blue-500">{{
                  detailData?.attrName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">属性代码：</span>
                <span class="info-value">{{ detailData?.attrCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">属性值：</span>
                <span class="info-value">{{ detailData?.attrValue || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">数据类型：</span>
                <span class="info-value">{{ detailData?.dataType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">是否必选：</span>
                <span class="info-value">
                  <span :class="detailData?.isRequired === '1' ? 'text-green-500' : 'text-red-500'">
                    {{ detailData?.isRequired === '1' ? '是' : '否' }}
                  </span>
                </span>
              </div>
              <div class="info-item">
                <span class="info-label">属性说明：</span>
                <span class="info-value">{{ detailData?.attrDesc || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 时间信息卡片 -->
          <el-card class="info-card time-info-card">
            <div class="card-header">
              <span class="card-title">时间信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">配置时间：</span>
                <span class="info-value">{{ formatDate(detailData?.cfgTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">更新时间：</span>
                <span class="info-value">{{ formatDate(detailData?.updateTimeSys) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 其他信息卡片 -->
          <el-card class="info-card other-info-card">
            <div class="card-header">
              <span class="card-title">其他信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">配置人：</span>
                <span class="info-value">{{ detailData?.cfgUser || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分类扩展字段1：</span>
                <span class="info-value">{{ detailData?.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分类扩展字段2：</span>
                <span class="info-value">{{ detailData?.extCat2 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">通用扩展字段1：</span>
                <span class="info-value">{{ detailData?.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">通用扩展字段2：</span>
                <span class="info-value">{{ detailData?.extCommon2 || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceServerAttrVO } from '@/api/dataHub/deviceManage/deviceDataManage/deviceserverattr'
import { watch, ref } from 'vue' // 引入必要的API

defineOptions({ name: 'DeviceServerAttrDetail' })

// 格式化日期
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
  detailData: {
    type: Object as () => DeviceServerAttrVO | null,
    default: null
  }
})

// Emits
const emit = defineEmits(['close'])

// 本地状态管理抽屉显示（解决直接修改props问题）
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
/* 样式保持不变 */
.detail-container {
  height: 100%;
  background-color: #f5f7fa;
  padding: 20px;
  overflow-y: auto;
}

.detail-header {
  margin-bottom: 20px;
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
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 15px;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 15px;
}

.card-title {
  font-weight: 500;
  font-size: 15px;
  padding-left: 8px;
  border-left: 3px solid;
}

.base-info-card .card-title {
  border-left-color: #8c9eff; /* 淡紫色 */
}

.attr-info-card .card-title {
  border-left-color: #8cd5ff; /* 淡蓝色 */
}

.time-info-card .card-title {
  border-left-color: #a1de93; /* 淡绿色 */
}

.other-info-card .card-title {
  border-left-color: #ffcf96; /* 淡橙色 */
}

.card-body {
  padding: 0 15px 15px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-label {
  color: #666;
  display: inline-block;
  width: 100px;
}

.info-value {
  color: #333;
}

@media (max-width: 1024px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
