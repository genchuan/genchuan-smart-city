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
        <span class="text-lg font-bold text-gray-800">结果公示详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="p-4 bg-gray-50 min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4">
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
              <span class="label">公示ID：</span>
              <span class="value font-bold text-blue-600">{{
                formData.resultPublicId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联审核ID：</span>
              <span class="value">{{ formData.resultAuditId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value">{{ formData.evalTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务名称：</span>
              <span class="value font-bold text-blue-600">{{ formData.evalTaskName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 评价对象信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-700">评价对象信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">评价对象ID：</span>
              <span class="value">{{ formData.evalObjectId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">评价对象名称：</span>
              <span class="value font-bold text-pink-600">{{
                formData.evalObjectName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">评价等级：</span>
              <el-tag
                :type="
                  formData.evalLevel === 'A'
                    ? 'success'
                    : formData.evalLevel === 'C'
                      ? 'warning'
                      : 'primary'
                "
              >
                {{ formData.evalLevel || '-' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="label">评价得分：</span>
              <span
                class="font-bold"
                :class="{
                  'text-green-600': formData.evalLevel === 'A',
                  'text-blue-600': formData.evalLevel === 'B',
                  'text-orange-600': formData.evalLevel === 'C'
                }"
                >{{ formData.evalScore || 0 }}</span
              >
            </div>
          </div>
        </el-card>

        <!-- 公示配置信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-green-50 text-green-700">公示配置信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">公示范围：</span>
              <span class="value font-bold text-green-600">{{ formData.publicScope || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">公示开始时间：</span>
              <span class="value font-bold text-green-600">{{
                formatDate(formData.publicStartTime)
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">公示结束时间：</span>
              <span class="value font-bold text-green-600">{{
                formatDate(formData.publicEndTime)
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">公示状态：</span>
              <span class="value">
                <el-tag :type="formData.publicStatus === 'COMPLETED' ? 'success' : 'warning'">
                  {{ formData.publicStatus === 'COMPLETED' ? '已完成' : '进行中' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 公示附加信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-700">公示附加信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">公示链接：</span>
              <span class="value">{{ formData.publicUrl || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">异议数量：</span>
              <span class="value font-bold text-yellow-600">{{ formData.objectCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 操作信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow" span="2">
          <template #header>
            <div class="card-header bg-purple-50 text-purple-700">操作信息</div>
          </template>
          <div class="card-content grid grid-cols-2 gap-4">
            <div class="info-item">
              <span class="label">业务创建人：</span>
              <span class="value">{{ formData.createUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">业务创建时间：</span>
              <span class="value font-bold text-purple-600">{{
                formatDate(formData.createTimeBiz)
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展字段卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow" span="2">
          <template #header>
            <div class="card-header bg-teal-50 text-teal-700">扩展字段信息</div>
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
import { EvalResultPublicVO } from '@/api/dataHub/comprehensiveEval/evalResultMgmt/evalResultAnnounce'

/** 结果公示详情抽屉 */
defineOptions({ name: 'EvalResultPublicDetailDrawer' })

// 格式化时间
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 响应式变量
const dialogVisible = ref(false) // 控制抽屉显示隐藏（避免修改props的visible）
const isFullscreen = ref(false) // 全屏状态
const hoverShadow = ref('hover') // 卡片hover阴影
const formData = ref<EvalResultPublicVO>({
  id: 0,
  resultPublicId: '',
  resultAuditId: '',
  evalTaskId: '',
  evalTaskName: '',
  evalObjectId: '',
  evalObjectName: '',
  evalLevel: '',
  evalScore: 0,
  publicScope: '',
  publicStartTime: undefined,
  publicEndTime: undefined,
  publicStatus: '',
  publicUrl: '',
  objectCount: 0,
  createUserBiz: '',
  createTimeBiz: undefined,
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

/** 打开抽屉 */
const open = (row: EvalResultPublicVO) => {
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
</style>
