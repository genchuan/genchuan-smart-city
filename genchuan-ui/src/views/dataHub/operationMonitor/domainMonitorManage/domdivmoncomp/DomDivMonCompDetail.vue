<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">监测部件分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container" :style="{ backgroundColor: isFullscreen ? '#fff' : '#f5f7fa' }">
      <!-- 内容区域 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">主键ID：</span>
                <span class="info-value font-semibold text-blue-500">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分域ID：</span>
                <span class="info-value">{{ data.domId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分域名称：</span>
                <span class="info-value font-semibold">{{ data.domName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分极编码：</span>
                <span class="info-value">{{ data.domCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分域状态：</span>
                <span class="info-value">
                  <el-tag :type="data.domStatus ? 'success' : 'danger'">
                    {{ data.domStatus ? '启用' : '停用' }}
                  </el-tag>
                </span>
              </div>
            </div>
          </el-card>

          <!-- 监测部件信息卡片 -->
          <el-card class="info-card monitor-info-card">
            <div class="card-header">监测部件信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">监测部件大类ID：</span>
                <span class="info-value">{{ data.monCompMajorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">监测部件大类名称：</span>
                <span class="info-value">{{ data.monCompMajorName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">监测部件小类ID：</span>
                <span class="info-value">{{ data.monCompMinorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">监测部件小类名称：</span>
                <span class="info-value">{{ data.monCompMinorName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">关联点位数量：</span>
                <span class="info-value">{{ data.relPtCount || 0 }} 个</span>
              </div>
              <div class="info-item">
                <span class="info-label">关联设备数量：</span>
                <span class="info-value">{{ data.relDeviceCount || 0 }} 个</span>
              </div>
            </div>
          </el-card>

          <!-- 设备状态信息卡片 -->
          <el-card class="info-card device-info-card">
            <div class="card-header">设备状态信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">设备在线率：</span>
                <span class="info-value">{{ data.deviceOnlineRate || 0 }}%</span>
              </div>
              <div class="info-item">
                <span class="info-label">维护部门代码：</span>
                <span class="info-value">{{ data.deptCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">维护部门名称：</span>
                <span class="info-value">{{ data.deptName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">创建时间(业务)：</span>
                <span class="info-value">{{ formatDate(data.createTimeBiz) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">更新时间(业务)：</span>
                <span class="info-value">{{ formatDate(data.updateTimeBiz) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card extend-info-card">
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
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivMonCompVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmoncomp'

defineOptions({ name: 'DomDivMonCompDetail' })

// 接收父组件参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 向父组件传递事件
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 抽屉状态
const isFullscreen = ref(false)
const data = ref<Partial<DomDivMonCompVO>>({})

// 设置详情数据
const setData = (row: DomDivMonCompVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-container {
  height: 100%;
  padding: 16px;
  overflow: auto;
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
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 16px;
  color: #666;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border: 1px solid #b3d8ff;
}

.monitor-info-card .card-header {
  background-color: #fff2e8;
  border: 1px solid #ffd6b3;
}

.device-info-card .card-header {
  background-color: #f6ffed;
  border: 1px solid #b7eb8f;
}

.extend-info-card .card-header {
  background-color: #fff0f6;
  border: 1px solid #ffadd2;
}

.card-body {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-label {
  flex: 0 0 130px;
  color: #666;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #333;
  font-size: 14px;
}

.text-blue-500 {
  color: #4096ff;
}

@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
