<template>
  <el-drawer
    v-model="dialogVisible"
    :title="''"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    class="detail-drawer"
  >
    <!-- 抽屉头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">监测事件分类详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="p-4 bg-gray-50 min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4 mb-4">
        <!-- 基础信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-blue-50 text-blue-700">基础信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value font-bold text-blue-600">{{ formData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类ID：</span>
              <span class="value font-bold text-blue-600">{{ formData.monEvtCatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">父类ID：</span>
              <span class="value">{{ formData.parentEvtId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类层级：</span>
              <span class="value font-bold text-blue-600">{{ formData.evtCatLevel || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类代码：</span>
              <span class="value font-bold text-blue-600">{{ formData.evtCatCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 分类名称信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-700">分类名称信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">分类名称：</span>
              <span class="value font-bold text-pink-600">{{ formData.evtCatName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类说明：</span>
              <span class="value whitespace-pre-line">{{ formData.evtCatDesc || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">启用状态：</span>
              <span class="value">
                <el-tag :type="['1'].includes(formData.enableStatus) ? 'success' : 'danger'">
                  {{ formData.enableStatus === '1' ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 操作人信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-green-50 text-green-700">操作人信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value font-bold text-green-600">{{ formData.createUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新人：</span>
              <span class="value font-bold text-green-600">{{ formData.updateUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 系统时间信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-700">系统时间信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value font-bold text-yellow-600">{{
                formatDate(formData.createTimeSys)
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统更新时间：</span>
              <span class="value font-bold text-yellow-600">{{
                formatDate(formData.updateTimeSys)
              }}</span>
            </div>
          </div>
        </el-card>
      </div>
      <div class="grid gap-4">
        <!-- 扩展字段卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow" span="2">
          <template #header>
            <div class="card-header bg-orange-50 text-orange-700">扩展字段信息</div>
          </template>
          <div class="card-content grid grid-cols-2 gap-4">
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
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { MonEvtCatVO } from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'

/** 监测事件分类详情抽屉 */
defineOptions({ name: 'MonEvtCatDetailDrawer' })

// 格式化时间
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 响应式变量
const dialogVisible = ref(false) // 控制抽屉显示隐藏（避免修改props的visible）
const isFullscreen = ref(false) // 全屏状态
const hoverShadow = ref('hover') // 卡片hover阴影
const formData = ref<MonEvtCatVO>({
  id: 0,
  monEvtCatId: '',
  parentEvtId: '',
  evtCatLevel: '',
  evtCatCode: '',
  evtCatName: '',
  evtCatDesc: '',
  enableStatus: '',
  createUser: '',
  updateUser: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: '',
  createTimeSys: undefined,
  updateTimeSys: undefined
})

/** 打开抽屉 */
const open = (row: MonEvtCatVO) => {
  formData.value = { ...row }
  dialogVisible.value = true
  isFullscreen.value = false // 重置全屏状态
}

// 暴露方法给父组件
defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f8f9fa;
}

.card-beautify {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
}

.card-beautify:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08) !important;
}

.card-header {
  padding: 8px 16px;
  border-radius: 4px 4px 0 0;
  font-weight: 600;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 12px;
  font-size: 14px;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  width: 130px;
  color: #666;
  font-weight: 500;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex: 1;
  word-break: break-word;
}

/* 马克龙配色优化 */
.bg-blue-50 {
  background-color: #e6f7ff;
}

.text-blue-700 {
  color: #1890ff;
}

.text-blue-600 {
  color: #096dd9;
}

.bg-pink-50 {
  background-color: #fff0f6;
}

.text-pink-700 {
  color: #ff4d94;
}

.text-pink-600 {
  color: #e53e3e;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-700 {
  color: #52c41a;
}

.text-green-600 {
  color: #389e0d;
}

.bg-yellow-50 {
  background-color: #fffbe6;
}

.text-yellow-700 {
  color: #faad14;
}

.text-yellow-600 {
  color: #fa8c16;
}

.bg-orange-50 {
  background-color: #fff7e6;
}

.text-orange-700 {
  color: #fa8c16;
}
</style>
