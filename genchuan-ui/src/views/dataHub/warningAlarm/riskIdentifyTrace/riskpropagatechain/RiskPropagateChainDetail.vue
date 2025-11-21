<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">风险传播链详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="content">
        <div class="grid-layout">
          <!-- 基本信息 -->
          <el-card class="info-card basic-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主键：</span>
                <span class="value">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">传播链ID：</span>
                <span class="value key-field">{{ data.propagateChainId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">传播链编号：</span>
                <span class="value key-field">{{ data.propagateChainNo || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">源头预警ID：</span>
                <span class="value">{{ data.sourceWarnId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">源头预警名称：</span>
                <span class="value">{{ data.sourceWarnName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 节点信息 -->
          <el-card class="info-card node-info-card">
            <div class="card-header">节点信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">传播节点序号：</span>
                <span class="value">{{ data.nodeSeq || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">节点类型：</span>
                <span class="value status-field">
                  <span :class="getNodeTypeClass">{{ data.nodeType || '-' }}</span>
                </span>
              </div>
              <div class="info-item">
                <span class="label">发生时间：</span>
                <span class="value">{{ formatDate(data.occurTime) }}</span>
              </div>
              <div class="info-item">
                <span class="label">影响范围：</span>
                <span class="value">{{ data.impactRange || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 设备信息 -->
          <el-card class="info-card device-info-card">
            <div class="card-header">设备信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">关联设备ID：</span>
                <span class="value">{{ data.deviceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">关联设备名称：</span>
                <span class="value">{{ data.deviceName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 记录信息 -->
          <el-card class="info-card record-info-card">
            <div class="card-header">记录信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">记录人ID：</span>
                <span class="value">{{ data.recUserId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">记录人姓名：</span>
                <span class="value">{{ data.recUserName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">创建时间：</span>
                <span class="value">{{ formatDate(data.createTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 传播内容 -->
          <el-card class="info-card content-info-card" :span="2">
            <div class="card-header">传播节点内容</div>
            <div class="card-body content-field">
              {{ data.nodeContent || '-' }}
            </div>
          </el-card>

          <!-- 扩展信息 -->
          <el-card class="info-card extend-info-card" :span="2">
            <div class="card-header">扩展信息</div>
            <div class="card-body">
              <div class="grid grid-cols-2 gap-4">
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
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { RiskPropagateChainVO } from '@/api/dataHub/warningAlarm/riskIdentifyTrace/riskpropagatechain'

defineOptions({ name: 'RiskPropagateChainDetail' })

// 定义props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 定义emit
const emit = defineEmits(['close'])

// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
// 全屏状态
const isFullscreen = ref(false)
const data = ref<RiskPropagateChainVO>({} as RiskPropagateChainVO)

// 设置详情数据
const setData = (row: RiskPropagateChainVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 节点类型样式
const getNodeTypeClass = computed(() => {
  // 根据实际节点类型值设置不同样式
  const type = data.value.nodeType
  if (!type) return ''

  // 示例：这里可以根据实际业务逻辑调整
  switch (type) {
    case 'high':
      return 'status-high'
    case 'medium':
      return 'status-medium'
    case 'low':
      return 'status-low'
    default:
      return 'status-normal'
  }
})

// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 0;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 16px 20px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.grid-layout > .info-card[span='2'] {
  grid-column: span 2;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.node-info-card .card-header {
  background-color: #fff2e8;
}

.device-info-card .card-header {
  background-color: #f6ffed;
}

.record-info-card .card-header {
  background-color: #fff0f6;
}

.content-info-card .card-header {
  background-color: #f0f2ff;
}

.extend-info-card .card-header {
  background-color: #f5f5f5;
}

.card-body {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 110px;
  color: #6b7280;
}

.value {
  color: #1f2937;
}

.key-field {
  color: #2563eb;
  font-weight: 500;
}

.content-field {
  padding: 10px;
  background-color: #f9fafb;
  border-radius: 4px;
  min-height: 100px;
  line-height: 1.8;
}

.status-field {
  font-weight: 500;
}

.status-high {
  color: #dc2626;
}

.status-medium {
  color: #ea580c;
}

.status-low {
  color: #059669;
}

.status-normal {
  color: #4f46e5;
}

.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.gap-4 {
  gap: 16px;
}
</style>
