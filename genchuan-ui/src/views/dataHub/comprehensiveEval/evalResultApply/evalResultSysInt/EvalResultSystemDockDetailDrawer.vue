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
        <span class="text-lg font-bold text-gray-800">结果系统对接详情</span>
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
              <span class="label">对接ID：</span>
              <span class="value font-bold text-blue-600">{{ formData.resultDockId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">对接编号：</span>
              <span class="value font-bold text-blue-600">{{
                formData.resultDockCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联存档ID：</span>
              <span class="value">{{ formData.resultArchId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 外部系统信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-700">外部系统信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">外部系统ID：</span>
              <span class="value">{{ formData.externalSystemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">外部系统名称：</span>
              <span class="value font-bold text-pink-600">{{
                formData.externalSystemName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 对接配置信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-green-50 text-green-700">对接配置信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">对接方式：</span>
              <span class="value font-bold text-green-600">{{ formData.dockMethod || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">对接频率：</span>
              <span class="value font-bold text-green-600">{{
                formData.dockFrequency || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">数据映射规则ID：</span>
              <span class="value">{{ formData.mapRuleId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">对接时间：</span>
              <span class="value font-bold text-green-600">{{
                formatDate(formData.dockTime)
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 对接状态信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-700">对接状态信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">对接状态：</span>
              <span class="value">
                <el-tag
                  :type="
                    formData.dockStatus === 'SUCCESS'
                      ? 'success'
                      : formData.dockStatus === 'PENDING'
                        ? 'warning'
                        : 'danger'
                  "
                >
                  {{
                    formData.dockStatus === 'SUCCESS'
                      ? '成功'
                      : formData.dockStatus === 'PENDING'
                        ? '对接中'
                        : '失败'
                  }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="label">失败原因：</span>
              <span class="value">{{ formData.failReason || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 对接数据卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow" span="2">
          <template #header>
            <div class="card-header bg-purple-50 text-purple-700">对接数据详情</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">对接数据：</span>
              <span class="value whitespace-pre-line">{{ formData.dockData || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 操作信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow" span="2">
          <template #header>
            <div class="card-header bg-teal-50 text-teal-700">操作信息</div>
          </template>
          <div class="card-content grid grid-cols-2 gap-4">
            <div class="info-item">
              <span class="label">业务创建人：</span>
              <span class="value">{{ formData.createUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">业务创建时间：</span>
              <span class="value font-bold text-teal-600">{{
                formatDate(formData.createTimeBiz)
              }}</span>
            </div>
          </div>
        </el-card>
      </div>
      <div class="grid gap-4">
        <!-- 扩展字段卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
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
import { EvalResultSystemDockVO } from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultSysInt'

/** 结果系统对接详情抽屉 */
defineOptions({ name: 'EvalResultSystemDockDetailDrawer' })

// 格式化时间
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 响应式变量
const dialogVisible = ref(false) // 控制抽屉显示隐藏（避免修改props的visible）
const isFullscreen = ref(false) // 全屏状态
const hoverShadow = ref('hover') // 卡片hover阴影
const formData = ref<EvalResultSystemDockVO>({
  id: 0,
  resultDockId: '',
  resultDockCode: '',
  resultArchId: '',
  externalSystemId: '',
  externalSystemName: '',
  dockMethod: '',
  dockFrequency: '',
  mapRuleId: '',
  dockStatus: '',
  dockTime: undefined,
  dockData: '',
  failReason: '',
  createUserBiz: '',
  createTimeBiz: undefined,
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

/** 打开抽屉 */
const open = (row: EvalResultSystemDockVO) => {
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

.bg-purple-50 {
  background-color: #f9f0ff;
}

.text-purple-700 {
  color: #722ed1;
}

.bg-teal-50 {
  background-color: #e6fffa;
}

.text-teal-700 {
  color: #13c2c2;
}

.bg-orange-50 {
  background-color: #fff7e6;
}

.text-orange-700 {
  color: #fa8c16;
}
</style>
