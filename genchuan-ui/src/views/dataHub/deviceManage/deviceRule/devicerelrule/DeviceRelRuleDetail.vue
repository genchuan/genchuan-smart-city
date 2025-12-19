<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">{{ title }}</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <el-card class="info-card base-info-card">
        <div class="card-header">基础信息</div>
        <div class="grid-content">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-bold">{{ formData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联规则ID：</span>
            <span class="value">{{ formData.relRuleId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备分类ID：</span>
            <span class="value">{{ formData.deviceCatId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备分类名称：</span>
            <span class="value primary">{{ formData.deviceCatName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 关联对象信息卡片 -->
      <el-card class="info-card rel-info-card">
        <div class="card-header">关联对象信息</div>
        <div class="grid-content">
          <div class="info-item">
            <span class="label">关联对象类型：</span>
            <span class="value">{{ formData.relObjType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联对象ID：</span>
            <span class="value">{{ formData.relObjId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联对象名称：</span>
            <span class="value primary">{{ formData.relObjName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联必选标识：</span>
            <span class="value">
              <span :class="formData.isRequired === '1' ? 'required' : 'not-required'">
                {{
                  formData.isRequired === '1' ? '必填' : formData.isRequired === '0' ? '可选' : '-'
                }}
              </span>
            </span>
          </div>
        </div>
      </el-card>

      <!-- 规则信息卡片 -->
      <el-card class="info-card rule-info-card">
        <div class="card-header">规则信息</div>
        <div class="grid-content">
          <div class="info-item">
            <span class="label">关联校验规则：</span>
            <span class="value">{{ formData.relCkRule || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">启用状态：</span>
            <span class="value">
              <span :class="formData.enableStatus === '1' ? 'enabled' : 'disabled'">
                {{
                  formData.enableStatus === '1'
                    ? '启用'
                    : formData.enableStatus === '0'
                      ? '禁用'
                      : '-'
                }}
              </span>
            </span>
          </div>
        </div>
      </el-card>

      <!-- 时间信息卡片 -->
      <el-card class="info-card time-info-card">
        <div class="card-header">时间信息</div>
        <div class="grid-content">
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(formData.createTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(formData.updateTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="label">系统创建时间：</span>
            <span class="value">{{ formatDate(formData.createTimeSys) }}</span>
          </div>
        </div>
      </el-card>
    </div>
    <!-- 扩展信息卡片 -->
    <el-card class="info-card ext-info-card">
      <div class="card-header">扩展信息</div>
      <div class="grid-content">
        <div class="info-item">
          <span class="label">分类扩展字段1：</span>
          <span class="value">{{ formData.extCat1 || '-' }}</span>
        </div>
        <div class="info-item">
          <span class="label">分类扩展字段2：</span>
          <span class="value">{{ formData.extCat2 || '-' }}</span>
        </div>
        <div class="info-item">
          <span class="label">通用扩展字段1：</span>
          <span class="value">{{ formData.extCommon1 || '-' }}</span>
        </div>
        <div class="info-item">
          <span class="label">通用扩展字段2：</span>
          <span class="value">{{ formData.extCommon2 || '-' }}</span>
        </div>
      </div>
    </el-card>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceRelRuleVO } from '@/api/dataHub/deviceManage/deviceRule/devicerelrule'

defineOptions({ name: 'DeviceRelRuleDetail' })

const visible = ref(false)
const title = ref('设备关联规则详情')
const isFullscreen = ref(false)
const formData = ref<Partial<DeviceRelRuleVO>>({})

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DeviceRelRuleVO) => {
  formData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
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
  padding: 12px 16px;
  font-weight: 600;
  color: #666;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.rel-info-card .card-header {
  background-color: #f0f2ff;
}

.rule-info-card .card-header {
  background-color: #fff7e6;
}

.time-info-card .card-header {
  background-color: #f6ffed;
}

.ext-info-card .card-header {
  background-color: #fff0f6;
}

.grid-content {
  padding: 16px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-item {
  display: flex;
  margin-bottom: 8px;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  color: #666;
  min-width: 100px;
  font-size: 13px;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex: 1;
  font-size: 13px;
  word-break: break-all;
}

.value.primary {
  color: #1890ff;
  font-weight: 500;
}

.required {
  color: #1890ff;
  background-color: #e8f3ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.not-required {
  color: #86909c;
  background-color: #f2f3f5;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.enabled {
  color: #00b42a;
  background-color: #e6f7ee;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.disabled {
  color: #f53f3f;
  background-color: #fff2f0;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .detail-container {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .grid-content {
    grid-template-columns: 1fr;
  }
}
</style>
