<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">日常巡查计划详情</span>
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
            <span class="label">计划ID：</span>
            <span class="value primary">{{ data.dailyPlanId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">计划名称：</span>
            <span class="value primary">{{ data.dailyPlanName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">计划编码：</span>
            <span class="value">{{ data.dailyPlanCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="info-card area-info-card">
          <div class="card-header">区域信息</div>
          <div class="info-item">
            <span class="label">所属区域代码：</span>
            <span class="value">{{ data.areaCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属区域名称：</span>
            <span class="value primary">{{ data.areaName || '-' }}</span>
          </div>
        </el-card>

        <!-- 路线与标准信息卡片 -->
        <el-card class="info-card route-info-card">
          <div class="card-header">路线与标准信息</div>
          <div class="info-item">
            <span class="label">关联路线ID：</span>
            <span class="value">{{ data.routeId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联路线名称：</span>
            <span class="value primary">{{ data.routeName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联标准ID：</span>
            <span class="value">{{ data.stdId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联标准名称：</span>
            <span class="value primary">{{ data.stdName || '-' }}</span>
          </div>
        </el-card>

        <!-- 负责人信息卡片 -->
        <el-card class="info-card person-info-card">
          <div class="card-header">负责人信息</div>
          <div class="info-item">
            <span class="label">负责人员ID：</span>
            <span class="value">{{ data.personId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">负责人员姓名：</span>
            <span class="value primary">{{ data.personName || '-' }}</span>
          </div>
        </el-card>

        <!-- 计划时间信息卡片 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">计划时间信息</div>
          <div class="info-item">
            <span class="label">计划周期：</span>
            <span class="value">{{ data.planCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">执行开始时间：</span>
            <span class="value">{{ formatDate(data.executeStartTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">执行结束时间：</span>
            <span class="value">{{ formatDate(data.executeEndTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">开始日期：</span>
            <span class="value">{{ formatDate(data.startDate) }}</span>
          </div>
          <div class="info-item">
            <span class="label">结束日期：</span>
            <span class="value">{{ formatDate(data.endDate) }}</span>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="info-card status-info-card">
          <div class="card-header">状态信息</div>
          <div class="info-item">
            <span class="label">计划状态：</span>
            <span
              class="value status"
              :class="data.planStatus ? 'status-active' : 'status-inactive'"
            >
              {{ data.planStatus ? '启用' : '禁用' }}
            </span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
          <div class="card-header">扩展信息</div>
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
        </el-card>

        <!-- 操作信息卡片 -->
        <el-card class="info-card operation-info-card">
          <div class="card-header">操作信息</div>
          <div class="info-item">
            <span class="label">创建人：</span>
            <span class="value">{{ data.createUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data.bizCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新人：</span>
            <span class="value">{{ data.updateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(data.bizUpdateTime) }}</span>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue'
import { InspectDailyPlanVO } from '@/api/dataHub/supervisionInspection/patrolPlan/dailyPatrolPlan'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => InspectDailyPlanVO,
    default: () => ({})
  }
})
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
const emit = defineEmits(['update:visible', 'close'])

const isFullscreen = ref(false)
const title = ref('日常巡查计划详情')

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 处理关闭事件
const handleClose = () => {
  emit('update:visible', false)
  emit('close')
}

// 监听数据变化
watch(
  () => props.data,
  (newVal) => {
    // 数据更新时的处理
  }
)
</script>

<style scoped>
.detail-container {
  padding: 20px;
  min-height: calc(100% - 55px);
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #333;
}

/* 马克龙色系标题背景 */
.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.area-info-card .card-header {
  background-color: #fff2e8;
}

.route-info-card .card-header {
  background-color: #f6ffed;
}

.person-info-card .card-header {
  background-color: #fff0f6;
}

.time-info-card .card-header {
  background-color: #f0f2ff;
}

.status-info-card .card-header {
  background-color: #fffbe6;
}

.ext-info-card .card-header {
  background-color: #e8f3ff;
}

.operation-info-card .card-header {
  background-color: #fef2e4;
}

.el-card__body {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.5;
  padding: 4px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  margin-bottom: 0;
  border-bottom: none;
}

.label {
  display: inline-block;
  width: 120px;
  color: #666;
  font-size: 14px;
}

.value {
  color: #333;
  font-size: 14px;
}

.value.primary {
  color: #165dff;
  font-weight: 500;
}

.status {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-active {
  background-color: #f0f9eb;
  color: #52c41a;
}

.status-inactive {
  background-color: #fff1f0;
  color: #f5222d;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
