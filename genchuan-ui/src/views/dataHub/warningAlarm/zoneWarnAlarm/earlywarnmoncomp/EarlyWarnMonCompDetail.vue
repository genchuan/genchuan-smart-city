<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按监测部件预警告警详情</span>
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
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value">{{ detailData.warnMonCompStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ detailData.statUserId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 部件信息卡片 -->
        <el-card class="info-card component-info-card">
          <div class="card-header">部件信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">部件大类ID：</span>
              <span class="value">{{ detailData.monMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">部件大类名称：</span>
              <span class="value font-semibold text-blue">{{
                detailData.monMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">部件小类ID：</span>
              <span class="value">{{ detailData.monMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">部件小类名称：</span>
              <span class="value font-semibold text-blue">{{
                detailData.monMinorName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card warning-info-card">
          <div class="card-header">预警信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value font-semibold text-orange">{{
                detailData.totalWarnCount || 0
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要触发原因：</span>
              <span class="value">{{ detailData.mainTriggerReason || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">涉及设备数：</span>
              <span class="value">{{ detailData.deviceCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 恢复信息卡片 -->
        <el-card class="info-card recovery-info-card">
          <div class="card-header">恢复信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">已恢复数：</span>
              <span class="value">{{ detailData.recoveredCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">恢复率：</span>
              <span class="value">
                <span :class="detailData.recoveryRate > 80 ? 'text-green' : 'text-orange'">
                  {{ detailData.recoveryRate || 0 }}%
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="label">恢复状态：</span>
              <span class="value">
                <el-tag :type="detailData.recoveryRate === 100 ? 'success' : 'warning'">
                  {{ detailData.recoveryRate === 100 ? '完全恢复' : '部分恢复' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extension-info-card" :span="2">
          <div class="card-header">扩展信息</div>
          <div class="card-content grid-2cols">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { EarlyWarnMonCompVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmoncomp'

/** 详情抽屉组件 */
defineOptions({ name: 'EarlyWarnMonCompDetail' })

const visible = ref(false)
const title = ref('预警告警统计详情')
const isFullscreen = ref(false)
const detailData = ref<EarlyWarnMonCompVO>({} as EarlyWarnMonCompVO)

/** 打开抽屉 */
const open = (data: EarlyWarnMonCompVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

/** 关闭抽屉 */
const handleClose = () => {
  visible.value = false
}

/** 格式化日期 */
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
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
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

.card-content {
  padding: 16px;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.component-info-card .card-header {
  background-color: #fff2e8;
}

.warning-info-card .card-header {
  background-color: #fff7e6;
}

.recovery-info-card .card-header {
  background-color: #f6ffed;
}

.extension-info-card .card-header {
  background-color: #f0f2f5;
}

.extension-info-card {
  grid-column: span 2;
}

.grid-2cols {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  color: #86909c;
  display: inline-block;
  width: 110px;
}

.value {
  color: #1d2129;
}

.text-blue {
  color: #4096ff;
}

.text-green {
  color: #52c41a;
}

.text-orange {
  color: #fa8c16;
}

.font-semibold {
  font-weight: 500;
}
</style>
