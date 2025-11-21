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
        <span class="text-lg font-bold text-gray-800">巡查巡检标准详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container" v-loading="loading">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">基本信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">标准ID：</span>
            <span class="value key-field">{{ detailData.stdId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">标准名称：</span>
            <span class="value key-field">{{ detailData.stdName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">标准编码：</span>
            <span class="value">{{ detailData.stdCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">标准类型：</span>
            <span class="value">{{ detailData.stdType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">标准状态：</span>
            <span class="value status-field">
              <el-tag :type="detailData.stdStatus ? 'success' : 'danger'">
                {{ detailData.stdStatus ? '启用' : '禁用' }}
              </el-tag>
            </span>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="info-card area-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">区域信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">适用区域代码：</span>
            <span class="value">{{ detailData.areaCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">适用区域名称：</span>
            <span class="value">{{ detailData.areaName || '-' }}</span>
          </div>
        </el-card>

        <!-- 检查标准卡片 -->
        <el-card class="info-card inspect-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">检查标准</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">检查项列表：</span>
            <span class="value">{{ detailData.ckItemList || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">评分规则：</span>
            <span class="value">{{ detailData.scoreRule || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">合格阈值：</span>
            <span class="value key-field">{{ detailData.qualifiedThreshold || '-' }}</span>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="info-card time-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">时间信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">生效时间：</span>
            <span class="value">{{ formatDate(detailData.effectiveTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">失效时间：</span>
            <span class="value">{{ formatDate(detailData.expireTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(detailData.bizCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(detailData.bizUpdateTime) }}</span>
          </div>
        </el-card>

        <!-- 操作人信息卡片 -->
        <el-card class="info-card operator-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">操作人信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">创建人：</span>
            <span class="value">{{ detailData.createUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新人：</span>
            <span class="value">{{ detailData.updateUser || '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">扩展信息</span>
            </div>
          </template>
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
  InspectStdInfoApi,
  InspectStdInfoVO
} from '@/api/dataHub/supervisionInspection/patrolStandardManage'

defineOptions({ name: 'InspectStdInfoDetail' })

const message = useMessage()

// 抽屉显示状态
const visible = ref(false)
// 标题
const title = ref('巡查巡检标准详情')
// 加载状态
const loading = ref(false)
// 详情数据
const detailData = ref<Partial<InspectStdInfoVO>>({})
// 全屏状态
const isFullscreen = ref(false)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = async (id: number) => {
  visible.value = true
  loading.value = true
  isFullscreen.value = false

  try {
    const data = await InspectStdInfoApi.getInspectStdInfo(id)
    detailData.value = data
  } catch (error) {
    message.error('获取详情失败')
    visible.value = false
  } finally {
    loading.value = false
  }
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  detailData.value = {}
}

// 暴露方法
defineExpose({
  open
})
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
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #f0f2f5;
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

/* 不同卡片标题条使用不同马克龙色*/
.base-info-card .card-header {
  background-color: #e6f7ff;
}

.area-info-card .card-header {
  background-color: #fff7e6;
}

.inspect-info-card .card-header {
  background-color: #f6ffed;
}

.time-info-card .card-header {
  background-color: #fff0f0;
}

.operator-info-card .card-header {
  background-color: #f0f5ff;
}

.ext-info-card .card-header {
  background-color: #f9f0ff;
}

.info-item {
  display: flex;
  padding: 10px 15px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 120px;
  color: #666;
  flex-shrink: 0;
}

.value {
  color: #333;
  word-break: break-all;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}

::v-deep .el-tag {
  margin-left: 5px;
}
</style>
