<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <!-- 头部 -->
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">巡查巡检人员详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">基本信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">人员ID：</span>
            <span class="value">{{ data.personId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">人员姓名：</span>
            <span class="value font-semibold text-blue-600">{{ data.personName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">人员账号：</span>
            <span class="value">{{ data.personAccount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">联系电话：</span>
            <span class="value">{{ data.personPhone || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">入职日期：</span>
            <span class="value">{{ formatDate(data.entryDate) }}</span>
          </div>
          <div class="info-item">
            <span class="label">人员状态：</span>
            <el-tag :type="data.personStatus ? 'success' : 'danger'">
              {{ data.personStatus ? '启用' : '禁用' }}
            </el-tag>
          </div>
        </el-card>

        <!-- 部门信息卡片 -->
        <el-card class="info-card dept-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">部门信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">所属部门代码：</span>
            <span class="value">{{ data.deptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属部门名称：</span>
            <span class="value font-semibold text-blue-600">{{ data.deptName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">人员角色：</span>
            <span class="value">{{ data.personRole || '-' }}</span>
          </div>
        </el-card>

        <!-- 网格信息卡片 -->
        <el-card class="info-card grid-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">网格信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">负责网格ID：</span>
            <span class="value">{{ data.gridId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">负责网格名称：</span>
            <span class="value font-semibold text-blue-600">{{ data.gridName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备编码：</span>
            <span class="value">{{ data.deviceCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 操作信息卡片 -->
        <el-card class="info-card operation-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">操作信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">创建人：</span>
            <span class="value">{{ data.createUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间（业务）：</span>
            <span class="value">{{ formatDate(data.bizCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新人：</span>
            <span class="value">{{ data.updateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间（业务）：</span>
            <span class="value">{{ formatDate(data.bizUpdateTime) }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card" :span="2">
          <template #header>
            <div class="card-header">
              <span class="card-title">扩展信息</span>
            </div>
          </template>
          <div class="info-grid">
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
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectPersonInfoVO } from '@/api/dataHub/supervisionInspection/patrolResource/staffManage'

defineOptions({ name: 'InspectPersonInfoDetail' })

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 抽屉状态
const visible = ref(false)
const title = ref('巡查巡检人员详情')
const isFullscreen = ref(false)
const data = ref<InspectPersonInfoVO>({} as InspectPersonInfoVO)

// 打开抽屉
const open = (row: InspectPersonInfoVO) => {
  data.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 暴露方法
defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.drawer-content {
  padding: 20px;
  height: 100%;
  overflow-y: auto;
  box-sizing: border-box;
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.12);
}

.ext-info-card {
  grid-column: span 2;
}

.card-header {
  background-color: #f0f7ff;
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #f0f7ff;
}

.dept-info-card .card-header {
  background-color: #fff0f3;
}

.grid-info-card .card-header {
  background-color: #f0fff4;
}

.operation-info-card .card-header {
  background-color: #fff7e6;
}

.ext-info-card .card-header {
  background-color: #f9f0ff;
}

::v-deep .el-card__body {
  padding: 15px;
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
  width: 130px;
  color: #666;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex-grow: 1;
}

.status-tag {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-active {
  background-color: #e6fffa;
  color: #00b42a;
}

.status-inactive {
  background-color: #fff2f0;
  color: #f53f3f;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .ext-info-card {
    grid-column: span 1;
  }

  .info-grid {
    grid-template-columns: 1fr;
  }
}
</style>
