<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2 py-4 border-b">
        <span class="text-lg font-bold text-gray-800">应用场景分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 内容区 -->
      <div class="grid-layout">
        <!-- 基本信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ data?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域ID：</span>
              <span class="value">{{ data?.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域名称：</span>
              <span class="value font-semibold text-blue-600">{{ data?.domName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域编码：</span>
              <span class="value font-semibold text-blue-600">{{ data?.domCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域状态：</span>
              <span :class="data?.domStatus ? 'status-active' : 'status-inactive'">
                {{ data?.domStatus ? '启用' : '停用' }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 应用场景信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">应用场景信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">应用场景大类ID：</span>
              <span class="value">{{ data?.appSceneMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景大类名称：</span>
              <span class="value font-semibold text-purple-600">{{
                data?.appSceneMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景小类ID：</span>
              <span class="value">{{ data?.appSceneMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景小类名称：</span>
              <span class="value font-semibold text-purple-600">{{
                data?.appSceneMinorName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联数量信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">关联数量信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">关联点位数量：</span>
              <span class="value font-semibold text-green-600">{{ data?.relPtCount || 0 }} 个</span>
            </div>
            <div class="info-item">
              <span class="label">关联设备数量：</span>
              <span class="value font-semibold text-green-600"
                >{{ data?.relDeviceCount || 0 }} 个</span
              >
            </div>
            <div class="info-item">
              <span class="label">关联事件数量：</span>
              <span class="value font-semibold text-green-600"
                >{{ data?.relEvtCount || 0 }} 件</span
              >
            </div>
          </div>
        </el-card>

        <!-- 负责人与部门信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">负责人与部门信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">场景负责人ID：</span>
              <span class="value">{{ data?.sceneMngrId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">场景负责人姓名：</span>
              <span class="value font-semibold text-orange-600">{{
                data?.sceneMngrName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">主管部门代码：</span>
              <span class="value">{{ data?.deptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主管部门名称：</span>
              <span class="value font-semibold text-orange-600">{{ data?.deptName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-teal-50">
            <span class="text-teal-600 font-medium">时间信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">创建时间(业务)：</span>
              <span class="value">{{ formatDate(data?.createTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间(业务)：</span>
              <span class="value">{{ formatDate(data?.updateTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value">{{ formatDate(data?.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">扩展信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivAppSceneVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivappscene'
import { watch, ref } from 'vue'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DomDivAppSceneVO | null,
    default: null
  }
})

const emit = defineEmits(['update:visible']) // 定义更新visible的事件

// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
const isFullscreen = ref(false)

// 监听props.visible变化，同步到本地状态
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
    if (!newVal) {
      isFullscreen.value = false // 关闭时重置全屏状态
    }
  },
  { immediate: true } // 初始时立即执行
)

// 监听本地状态变化，通知父组件
watch(
  () => drawerVisible.value,
  (newVal) => {
    emit('update:visible', newVal)
  }
)

// 处理抽屉关闭事件
const handleClose = () => {
  emit('update:visible', false)
}

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}
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

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  padding: 20px;
  overflow-y: auto;
  flex: 1;
}

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 0;
}

.card-body {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;

  &:last-child {
    border-bottom: none;
  }
}

.label {
  width: 130px;
  color: #666;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex: 1;
  word-break: break-all;
}

.status-active {
  color: #4caf50;
  background-color: rgba(76, 175, 80, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-inactive {
  color: #f44336;
  background-color: rgba(244, 67, 54, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

/* 马克龙色系背景 */
.bg-blue-50 {
  background-color: #e6f4ff;
}

.text-blue-600 {
  color: #165dff;
}

.bg-purple-50 {
  background-color: #f9f0ff;
}

.text-purple-600 {
  color: #722ed1;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-600 {
  color: #00b42a;
}

.bg-orange-50 {
  background-color: #fff7e6;
}

.text-orange-600 {
  color: #ff7d00;
}

.bg-teal-50 {
  background-color: #e6fffa;
}

.text-teal-600 {
  color: #00b29e;
}

.bg-gray-50 {
  background-color: #f2f3f5;
}

.text-gray-600 {
  color: #4e5969;
}
</style>
