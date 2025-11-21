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
        <span class="text-lg font-bold text-gray-800">监测事件分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container" v-loading="loading">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <!-- 基本信息 -->
        <el-card class="info-card basic-info-card">
          <div class="card-header">基本信息</div>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value primary-text">{{ detailData.domName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域编码：</span>
            <span class="value primary-text">{{ detailData.domCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value">{{ detailData.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域状态：</span>
            <span class="value">
              <el-tag :type="detailData.domStatus ? 'success' : 'danger'">
                {{ detailData.domStatus ? '启用' : '禁用' }}
              </el-tag>
            </span>
          </div>
        </el-card>

        <!-- 事件分类信息 -->
        <el-card class="info-card event-type-card">
          <div class="card-header">事件分类信息</div>
          <div class="info-item">
            <span class="label">监测事件大类ID：</span>
            <span class="value">{{ detailData.monEvtMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">监测事件大类名称：</span>
            <span class="value primary-text">{{ detailData.monEvtMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">监测事件小类ID：</span>
            <span class="value">{{ detailData.monEvtMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">监测事件小类名称：</span>
            <span class="value primary-text">{{ detailData.monEvtMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 关联统计信息 -->
        <el-card class="info-card stats-card">
          <div class="card-header">关联统计信息</div>
          <div class="info-item">
            <span class="label">关联点位数量：</span>
            <span class="value">{{ detailData.relPtCount || 0 }} 个</span>
          </div>
          <div class="info-item">
            <span class="label">关联事件数量：</span>
            <span class="value">{{ detailData.relEvtCount || 0 }} 件</span>
          </div>
          <div class="info-item">
            <span class="label">事件办结率：</span>
            <span class="value">{{ detailData.evtCompleteRate || 0 }}%</span>
          </div>
        </el-card>

        <!-- 管控部门信息 -->
        <el-card class="info-card dept-card">
          <div class="card-header">管控部门信息</div>
          <div class="info-item">
            <span class="label">管控部门代码：</span>
            <span class="value">{{ detailData.deptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管控部门名称：</span>
            <span class="value primary-text">{{ detailData.deptName || '-' }}</span>
          </div>
        </el-card>

        <!-- 时间信息 -->
        <el-card class="info-card time-card">
          <div class="card-header">时间信息</div>
          <div class="info-item">
            <span class="label">创建时间(业务)：</span>
            <span class="value">{{ formatDate(detailData.createTimeBiz) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间(业务)：</span>
            <span class="value">{{ formatDate(detailData.updateTimeBiz) }}</span>
          </div>
          <div class="info-item">
            <span class="label">系统创建时间：</span>
            <span class="value">{{ formatDate(detailData.createTime) }}</span>
          </div>
        </el-card>

        <!-- 扩展字段信息 -->
        <el-card class="info-card ext-card">
          <div class="card-header">扩展字段信息</div>
          <div class="info-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ detailData.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ detailData.extCat2 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段1：</span>
            <span class="value">{{ detailData.extCommon1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段2：</span>
            <span class="value">{{ detailData.extCommon2 || '-' }}</span>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import {
  DomDivMonEvtApi,
  DomDivMonEvtVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmonevt'

const message = useMessage() // 消息弹窗

defineOptions({ name: 'DomDivMonEvtDetail' })

// 时间格式化
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// Emits
const emit = defineEmits(['update:visible'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 状态
const loading = ref(false)
const isFullscreen = ref(false)
const detailData = ref<DomDivMonEvtVO>({} as DomDivMonEvtVO)

// 加载数据
const loadData = async (id: number) => {
  loading.value = true
  try {
    const data = await DomDivMonEvtApi.getDomDivMonEvt(id)
    detailData.value = data
  } catch (error) {
    message.error('加载详情失败')
    emit('update:visible', false)
  } finally {
    loading.value = false
  }
}

// 关闭抽屉
const handleClose = () => {
  emit('update:visible', false)
  isFullscreen.value = false
}

// 暴露方法
defineExpose({
  loadData
})
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
}

.info-card {
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  font-weight: 600;
  color: #606266;
  border-bottom: 1px solid #f0f2f5;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.event-type-card .card-header {
  background-color: #fff7e6;
}

.stats-card .card-header {
  background-color: #f6ffed;
}

.dept-card .card-header {
  background-color: #fff0f0;
}

.time-card .card-header {
  background-color: #f0f5ff;
}

.ext-card .card-header {
  background-color: #f9f0ff;
}

.el-card__body {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f2f5;

  &:last-child {
    border-bottom: none;
  }
}

.label {
  flex: 0 0 130px;
  color: #606266;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #303133;
  font-size: 14px;
  word-break: break-all;
}

.primary-text {
  color: #165dff;
  font-weight: 500;
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
