<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">问题分级记录详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="info-item">
            <span class="label">主键：</span>
            <span class="value">{{ data?.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分级ID：</span>
            <span class="value primary">{{ data?.problemLevelId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联问题ID：</span>
            <span class="value primary">{{ data?.problemId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联问题编码：</span>
            <span class="value">{{ data?.problemCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联问题名称：</span>
            <span class="value">{{ data?.problemName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">问题级别：</span>
            <span class="value">{{ data?.problemLevel || '-' }}</span>
          </div>
        </el-card>

        <!-- 分级信息卡片 -->
        <el-card class="info-card level-info-card">
          <div class="card-header">分级信息</div>
          <div class="info-item">
            <span class="label">分级人：</span>
            <span class="value">{{ data?.levelUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分级时间：</span>
            <span class="value">{{ formatDate(data?.levelTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">分级依据：</span>
            <span class="value">{{ data?.levelBasis || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">级别描述：</span>
            <span class="value">{{ data?.levelDesc || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置时限：</span>
            <span class="value">{{ data?.handleDeadline || '-' }}</span>
          </div>
        </el-card>

        <!-- 提醒信息卡片 -->
        <el-card class="info-card remind-info-card">
          <div class="card-header">提醒信息</div>
          <div class="info-item">
            <span class="label">是否推送提醒：</span>
            <span class="value status">
              <el-tag :type="data?.isPushRemind ? 'success' : 'info'">
                {{ data?.isPushRemind ? '是' : '否' }}
              </el-tag>
            </span>
          </div>
          <div class="info-item" v-if="data?.isPushRemind">
            <span class="label">提醒推送时间：</span>
            <span class="value">{{ formatDate(data?.pushRemindTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">问题状态变更：</span>
            <span class="value">{{ data?.problemStatusChange || '-' }}</span>
          </div>
        </el-card>

        <!-- 系统信息卡片 -->
        <el-card class="info-card system-info-card">
          <div class="card-header">系统信息</div>
          <div class="info-item">
            <span class="label">创建人：</span>
            <span class="value">{{ data?.bizCreateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data?.bizCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新人：</span>
            <span class="value">{{ data?.bizUpdateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(data?.bizUpdateTime) }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card" span="2">
          <div class="card-header">扩展信息</div>
          <div class="grid-sub-layout">
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
import { InspectProblemLevelRecVO } from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueClassificationManagement'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => InspectProblemLevelRecVO | null,
    default: null
  }
})

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
const isFullscreen = ref(false)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}
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

.grid-sub-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.span-2 {
  grid-column: span 2;
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
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.level-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.remind-info-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.system-info-card .card-header {
  background-color: #fff0f6;
  border-left: 4px solid #eb2f96;
}

.extend-info-card .card-header {
  background-color: #f0f2f5;
  border-left: 4px solid #8c8c8c;
}

::v-deep .el-card__body {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 120px;
  color: #6b7280;
  font-size: 13px;
  flex-shrink: 0;
}

.value {
  flex: 1;
  color: #1f2937;
  font-size: 13px;
  word-break: break-all;
}

.value.primary {
  color: #165dff;
  font-weight: 500;
}

::v-deep .el-tag {
  margin-right: 0;
}
</style>
