<template>
  <!-- 模板内容保持不变 -->
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">单元网格统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <el-card class="detail-card base-info-card">
        <template #header>
          <div class="card-header base-info-header">基础信息</div>
        </template>
        <div class="card-content">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ detailData.id }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计ID：</span>
            <span class="value primary">{{ detailData.unitStatId }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value primary">{{ detailData.statCycle }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ detailData.statCycleName }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计人：</span>
            <span class="value">{{ detailData.statUserId }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计时间：</span>
            <span class="value">{{ formatDate(detailData.statTime) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 行政区划信息卡片 -->
      <el-card class="detail-card region-info-card">
        <template #header>
          <div class="card-header region-info-header">行政区划信息</div>
        </template>
        <div class="card-content">
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value primary">{{ detailData.regionCode }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value">{{ detailData.regionName }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划级别：</span>
            <span class="value">{{ detailData.regionLevel }}</span>
          </div>
          <div class="info-item">
            <span class="label">比例尺：</span>
            <span class="value">{{ detailData.scale }}</span>
          </div>
        </div>
      </el-card>

      <!-- 网格数量信息卡片 -->
      <el-card class="detail-card count-info-card">
        <template #header>
          <div class="card-header count-info-header">网格数量信息</div>
        </template>
        <div class="card-content">
          <div class="info-item">
            <span class="label">网格总数(个)：</span>
            <span class="value primary">{{ detailData.totalCount }}</span>
          </div>
          <div class="info-item">
            <span class="label">在用网格数(个)：</span>
            <span class="value status-success">{{ detailData.inUseCount }}</span>
          </div>
          <div class="info-item">
            <span class="label">停用网格数(个)：</span>
            <span class="value status-danger">{{ detailData.stoppedCount }}</span>
          </div>
          <div class="info-item">
            <span class="label">新增网格数(个)：</span>
            <span class="value">{{ detailData.newCount }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新网格数(个)：</span>
            <span class="value">{{ detailData.updateCount }}</span>
          </div>
        </div>
      </el-card>

      <!-- 面积信息卡片 -->
      <el-card class="detail-card area-info-card">
        <template #header>
          <div class="card-header area-info-header">面积信息</div>
        </template>
        <div class="card-content">
          <div class="info-item">
            <span class="label">总面积(m²)：</span>
            <span class="value primary">{{ detailData.totalArea }}</span>
          </div>
          <div class="info-item">
            <span class="label">平均面积(m²)：</span>
            <span class="value">{{ detailData.avgArea }}</span>
          </div>
        </div>
      </el-card>

      <!-- 其他信息卡片 -->
      <el-card class="detail-card other-info-card">
        <template #header>
          <div class="card-header other-info-header">其他信息</div>
        </template>
        <div class="card-content">
          <div class="info-item">
            <span class="label">报表备注：</span>
            <span class="value">{{ detailData.rptRemark || '-' }}</span>
          </div>
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
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
// 脚本内容保持不变
import { ref, defineProps, defineEmits, toRefs } from 'vue'
import { UnitGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/unitgridrpt'


const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  detailData: {
    type: Object as () => UnitGridRptVO,
    default: () => ({})
  }
})

const { visible, detailData } = toRefs(props)
const emit = defineEmits(['update:visible'])
const isFullscreen = ref(false)

const handleClose = () => {
  emit('update:visible', false)
  isFullscreen.value = false
}

const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '—')
</script>

<style scoped>
/* 马克龙风格配色优化 */
.detail-drawer {
  --el-drawer-bg-color: #f9fafc; /* 超浅灰背景 */
}

.detail-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  padding: 20px;
}

.detail-card {
  transition: all 0.3s ease;
  border: none;
  border-radius: 8px; /* 稍大圆角更柔和 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05); /* 更淡的阴影 */
  background-color: #ffffff;
  overflow: hidden;
}

.detail-card:hover {
  transform: translateY(-3px); /* 轻微上浮 */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08); /* hover阴影增强但保持柔和 */
}

.card-header {
  padding: 12px 15px;
  font-weight: 600;
  color: #5a5a5a; /* 深灰文字更柔和 */
  border-radius: 8px 8px 0 0;
  background-color: #f0f7ff; /* 默认浅灰底 */
}

/* 马克龙浅色配色方案 - 低饱和度高亮度 */
.base-info-header {
  background-color: #e6f7ff; /* 浅蓝 */
}

.region-info-header {
  background-color: #e6fffa; /* 浅青 */
}

.count-info-header {
  background-color: #fff7e6; /* 浅橙 */
}

.area-info-header {
  background-color: #f6ffed; /* 浅绿 */
}

.other-info-header {
  background-color: #fff0f6; /* 浅粉 */
}

.card-content {
  padding: 18px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 18px;
}

.info-item {
  display: flex;
  margin-bottom: 10px;
  line-height: 1.6;
}

.label {
  color: #8c8c8c; /* 浅灰标签 */
  width: 120px;
  flex-shrink: 0;
  font-size: 14px;
}

.value {
  color: #434343; /* 主文字深灰 */
  flex-grow: 1;
  font-size: 14px;
}

/* 重点字段颜色调整为柔和蓝色 */
.primary {
  color: #69b1ff;
  font-weight: 600;
}

/* 状态颜色降低饱和度 */
.status-success {
  color: #52c41a; /* 柔和绿 */
  font-weight: 600;
}

.status-danger {
  color: #ff7a45; /* 柔和红 */
  font-weight: 600;
}

/* 响应式调整 */
@media screen and (max-width: 1024px) {
  .detail-container {
    grid-template-columns: 1fr;
  }

  .card-content {
    grid-template-columns: 1fr;
  }
}
</style>
