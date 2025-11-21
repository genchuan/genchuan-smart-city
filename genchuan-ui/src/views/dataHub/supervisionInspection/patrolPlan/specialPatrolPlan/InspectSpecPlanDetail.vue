<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">专项巡查计划详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-blue-50">
              <span class="text-blue-600 font-medium">基本信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">计划ID：</span>
            <span class="value font-semibold text-blue-600">{{ data.specPlanId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">计划名称：</span>
            <span class="value">{{ data.specPlanName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">计划编码：</span>
            <span class="value">{{ data.specPlanCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">专项主题：</span>
            <span class="value">{{ data.specTopic || '-' }}</span>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-green-50">
              <span class="text-green-600 font-medium">区域信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">所属区域代码：</span>
            <span class="value">{{ data.areaCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属区域名称：</span>
            <span class="value">{{ data.areaName || '-' }}</span>
          </div>
        </el-card>

        <!-- 标准信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-purple-50">
              <span class="text-purple-600 font-medium">标准信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">关联标准ID：</span>
            <span class="value">{{ data.stdId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联标准名称：</span>
            <span class="value">{{ data.stdName || '-' }}</span>
          </div>
        </el-card>

        <!-- 负责人信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-yellow-50">
              <span class="text-yellow-600 font-medium">负责人信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">负责人员IDs：</span>
            <span class="value">{{ data.personIds || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">负责人员Names：</span>
            <span class="value">{{ data.personNames || '-' }}</span>
          </div>
        </el-card>

        <!-- 执行时间信息卡片 -->
        <el-card class="card-hover md:col-span-2">
          <template #header>
            <div class="card-header bg-pink-50">
              <span class="text-pink-600 font-medium">执行时间信息</span>
            </div>
          </template>
          <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div class="info-item">
              <span class="label">执行开始日期：</span>
              <span class="value">{{ formatDate(data.executeStartDate) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行结束日期：</span>
              <span class="value">{{ formatDate(data.executeEndDate) }}</span>
            </div>
            <div class="info-item">
              <span class="label">每日执行时间：</span>
              <span class="value">{{ data.dailyExecuteTime || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-orange-50">
              <span class="text-orange-600 font-medium">状态信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">计划状态：</span>
            <span
              class="value status-label"
              :class="data.planStatus ? 'status-active' : 'status-inactive'"
            >
              {{ data.planStatus ? '启用' : '禁用' }}
            </span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-teal-50">
              <span class="text-teal-600 font-medium">扩展信息</span>
            </div>
          </template>
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

        <!-- 操作记录卡片 -->
        <el-card class="card-hover md:col-span-2">
          <template #header>
            <div class="card-header bg-indigo-50">
              <span class="text-indigo-600 font-medium">操作记录</span>
            </div>
          </template>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
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
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits } from 'vue'
import { InspectSpecPlanVO } from '@/api/dataHub/supervisionInspection/patrolPlan/specialPatrolPlan'

// 定义属性
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => InspectSpecPlanVO,
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
// 定义事件
const emit = defineEmits(['update:visible', 'close'])

// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('update:visible', false)
  emit('close')
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}
</script>

<style scoped>
.detail-container {
  padding: 16px;
  min-height: calc(100% - 55px);
}

.card-header {
  padding: 12px 16px;
  border-radius: 4px 4px 0 0;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.info-item {
  padding: 10px 0;
  border-bottom: 1px solid #f5f5f5;
}

.info-item:last-child {
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

.status-label {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-active {
  background-color: #e6f7ee;
  color: #00b42a;
}

.status-inactive {
  background-color: #fff2f0;
  color: #f53f3f;
}
</style>
