<template>
  <el-drawer
    :model-value="props.visible"
    title="预警详情"
    size="75%"
    :before-close="handleBeforeClose"
    @update:model-value="(val) => emit('update:visible', val)"
  >
    <el-descriptions border class="mt-4" size="small">
      <el-descriptions-item label="预警ID">{{ props.detailData.alarmId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="预警编号">{{ props.detailData.alarmCode || '-' }}</el-descriptions-item>
      <el-descriptions-item label="风险类型ID">{{ props.detailData.riskTypeId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="风险类型名称">{{ props.detailData.riskTypeName || '-' }}</el-descriptions-item>
      <el-descriptions-item label="预警等级">
        <span
          class="alarm-level-tag"
          :class="`level-${props.levelClass(props.detailData.alarmLevel)}`"
          v-if="props.detailData.alarmLevel"
        >
          {{ props.levelName(props.detailData.alarmLevel) }}
        </span>
        <span v-else>-</span>
      </el-descriptions-item>
      <el-descriptions-item label="所属分域ID">{{ props.detailData.domainId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="所属分域名称">{{ props.detailData.domainName || '-' }}</el-descriptions-item>
      <el-descriptions-item label="发生区域">{{ props.detailData.occurRegion || '-' }}</el-descriptions-item>
      <el-descriptions-item label="GPS坐标">{{ props.detailData.gpsCoordinate || '-' }}</el-descriptions-item>
      <el-descriptions-item label="触发时间">
        {{ formatTimestamp(props.detailData.triggerTime ? new Date(props.detailData.triggerTime).getTime() : undefined, 'YYYY-MM-DD') }}
      </el-descriptions-item>
      <el-descriptions-item label="预警状态">
        <span
          class="status-tag"
          :class="`status-${props.statusClass(props.detailData.alarmStatus)}`"
          v-if="props.detailData.alarmStatus"
        >
          {{ props.detailData.alarmStatus }}
        </span>
        <span v-else>-</span>
      </el-descriptions-item>
      <el-descriptions-item label="触发原因">{{ props.detailData.triggerReason || '-' }}</el-descriptions-item>
      <el-descriptions-item label="关联指标ID">{{ props.detailData.indicatorId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="处置责任人ID">{{ props.detailData.handlerId || '-' }}</el-descriptions-item>
      <el-descriptions-item label="处置责任人姓名">{{ props.detailData.handlerName || '未分配' }}</el-descriptions-item>
      <el-descriptions-item label="最后更新时间">
        {{ formatTimestamp(props.detailData.lastUpdateTime ? new Date(props.detailData.lastUpdateTime).getTime() : undefined, 'YYYY-MM-DD') }}
      </el-descriptions-item>
    </el-descriptions>
  </el-drawer>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
import dayjs from 'dayjs'

// 定义Props：接收主组件传递的数据和方法
const props = defineProps({
  // 控制抽屉显示/隐藏（双向绑定）
  visible: {
    type: Boolean,
    default: false
  },
  // 详情数据
  detailData: {
    type: Object,
    default: () => ({})
  },
  // 获取预警等级样式类的方法
  levelClass: {
    type: Function,
    required: true
  },
  // 获取预警等级名称的方法
  levelName: {
    type: Function,
    required: true
  },
  // 获取状态样式类的方法
  statusClass: {
    type: Function,
    required: true
  },
  // 时间格式化方法
  formatTime: {
    type: Function,
    required: true
  }
})

// 时间戳格式化函数
const formatTimestamp = (timestamp: number | string | undefined, format: string = 'YYYY-MM-DD') => {
  if (!timestamp) return '-'

  let ts = Number(timestamp)
  // 处理10位时间戳（秒）转13位（毫秒）
  if (ts.toString().length === 10) {
    ts = ts * 1000
  }

  return dayjs(ts).format(format)
}

// 定义事件
const emit = defineEmits<{
  (e: 'update:visible', value: boolean): void
  (e: 'close'): void
}>()

/** 抽屉关闭前的钩子 */
const handleBeforeClose = () => {
  emit('close')
  emit('update:visible', false)
}
</script>

<style scoped>
/* 样式部分保持不变 */
.alarm-level-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  color: #fff;
  font-size: 12px;
  font-weight: 500;
}
.alarm-level-tag.level-blue {
  background-color: #1E90FF;
}
.alarm-level-tag.level-yellow {
  background-color: #FFC107;
}
.alarm-level-tag.level-orange {
  background-color: #FF7F50;
}
.alarm-level-tag.level-red {
  background-color: #DC143C;
}

.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  color: #fff;
  font-size: 12px;
  font-weight: 500;
}
.status-tag.status-orange {
  background-color: #FF9800;
}
.status-tag.status-blue {
  background-color: #2196F3;
}
.status-tag.status-green {
  background-color: #4CAF50;
}
.status-tag.status-gray {
  background-color: #9E9E9E;
}
.status-tag.status-default {
  background-color: #607D8B;
}
</style>
