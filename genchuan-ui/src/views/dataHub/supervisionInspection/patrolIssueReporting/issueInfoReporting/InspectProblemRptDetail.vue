<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">问题上报详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container" :class="{ fullscreen: isFullscreen }">
      <!-- 内容区域 -->
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">问题ID：</span>
              <span class="value">{{ formData.problemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问题编码：</span>
              <span class="value">{{ formData.problemCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问题名称：</span>
              <span class="value font-bold text-blue-600">{{ formData.problemName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问题状态：</span>
              <el-tag
                :type="
                  formData.problemStatus === '已处理'
                    ? 'success'
                    : formData.problemStatus === '处理中'
                      ? 'warning'
                      : 'primary'
                "
              >
                {{ formData.problemStatus || '-' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="label">上报时间：</span>
              <span class="value">{{ formatDate(formData.rptTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value">{{ formData.bizCreateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(formData.bizCreateTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 问题类型信息卡片 -->
        <el-card class="info-card type-info-card">
          <div class="card-header">问题类型信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">问题类型ID：</span>
              <span class="value">{{ formData.matterTypeId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问题类型名称：</span>
              <span class="value">{{ formData.matterTypeName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ formData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ formData.extCat2 || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联任务信息卡片 -->
        <el-card class="info-card task-info-card">
          <div class="card-header">关联任务信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value">{{ formData.taskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务编码：</span>
              <span class="value">{{ formData.taskCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 上报人员信息卡片 -->
        <el-card class="info-card user-info-card">
          <div class="card-header">上报人员信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">上报人员ID：</span>
              <span class="value">{{ formData.rptUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">上报人员姓名：</span>
              <span class="value">{{ formData.rptUserName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 网格信息卡片 -->
        <el-card class="info-card grid-info-card">
          <div class="card-header">网格信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">所属网格ID：</span>
              <span class="value">{{ formData.gridId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">所属网格名称：</span>
              <span class="value">{{ formData.gridName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 位置信息卡片 -->
        <el-card class="info-card location-info-card">
          <div class="card-header">位置信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">位置坐标X：</span>
              <span class="value">{{ formData.problemLocX || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">位置坐标Y：</span>
              <span class="value">{{ formData.problemLocY || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">位置描述：</span>
              <span class="value">{{ formData.problemLocDesc || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 问题描述卡片 -->
        <el-card class="info-card desc-info-card" style="grid-column: span 2">
          <div class="card-header">问题描述</div>
          <div class="card-content">
            <div class="info-item full-width">
              <span class="label">问题详情：</span>
              <span class="value">{{ formData.problemDesc || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">驳回原因：</span>
              <span class="value">{{ formData.rejectReason || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ formData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ formData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 照片信息卡片 -->
        <el-card class="info-card photo-info-card" style="grid-column: span 2">
          <div class="card-header">问题照片</div>
          <div class="card-content">
            <div v-if="formData.problemPhotoUrls" class="photo-list">
              <el-image
                v-for="(url, index) in formData.problemPhotoUrls.split(',')"
                :key="index"
                :src="url"
                :preview-src-list="formData.problemPhotoUrls.split(',')"
                class="photo-item"
                fit="cover"
              />
            </div>
            <div v-else class="no-photo">无照片信息</div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectProblemRptVO } from '@/api/dataHub/supervisionInspection/patrolIssueReporting/issueInfoReporting'

defineOptions({ name: 'InspectProblemRptDetail' })

const visible = ref(false)
const isFullscreen = ref(false)
const formData = ref<InspectProblemRptVO>({
  id: 0,
  problemId: '',
  problemCode: '',
  problemName: '',
  matterTypeId: '',
  matterTypeName: '',
  taskId: '',
  taskCode: '',
  rptUserId: '',
  rptUserName: '',
  gridId: '',
  gridName: '',
  problemLocX: 0,
  problemLocY: 0,
  problemLocDesc: '',
  problemDesc: '',
  problemPhotoUrls: '',
  rptTime: new Date(),
  problemStatus: '',
  rejectReason: '',
  bizCreateUser: '',
  bizCreateTime: new Date(),
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 打开抽屉
const open = (data: InspectProblemRptVO) => {
  visible.value = true
  formData.value = { ...data }
  isFullscreen.value = false
}

// 关闭抽屉
const close = () => {
  visible.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 格式化状态
// const formatStatus = (status: string) => {
//   const statusMap: Record<string, string> = {
//     '1': '待处理',
//     '2': '处理中',
//     '3': '已解决',
//     '4': '已驳回'
//   }
//   return statusMap[status] || status || '-'
// }
//
// // 获取状态样式
// const getStatusClass = (status: string) => {
//   const classMap: Record<string, string> = {
//     '1': 'status-pending',
//     '2': 'status-processing',
//     '3': 'status-success',
//     '4': 'status-rejected'
//   }
//   return classMap[status] || ''
// }

// 暴露方法
defineExpose({
  open,
  close
})

// 关闭事件
const emit = defineEmits(['close'])
watch(visible, (newVal) => {
  if (!newVal) {
    emit('close')
  }
})
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  box-sizing: border-box;
}

.detail-container.fullscreen {
  padding: 10px;
}

.detail-header {
  margin-bottom: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 16px;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border: 1px solid #b3d8ff;
}

.type-info-card .card-header {
  background-color: #fff7e6;
  border: 1px solid #ffe8cc;
}

.task-info-card .card-header {
  background-color: #f6ffed;
  border: 1px solid #d9f7be;
}

.user-info-card .card-header {
  background-color: #fff0f6;
  border: 1px solid #ffadd2;
}

.grid-info-card .card-header {
  background-color: #f0f2ff;
  border: 1px solid #d6e4ff;
}

.location-info-card .card-header {
  background-color: #e8f4f8;
  border: 1px solid #b5e8f8;
}

.desc-info-card .card-header {
  background-color: #f9f0ff;
  border: 1px solid #efdbff;
}

.photo-info-card .card-header {
  background-color: #fffbf0;
  border: 1px solid #fff2cc;
}

.card-content {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item.full-width {
  flex-direction: column;
  align-items: flex-start;
}

.info-item.full-width .label {
  margin-bottom: 6px;
}

.label {
  width: 110px;
  flex-shrink: 0;
  color: #6b7280;
  font-size: 14px;
}

.value {
  flex-grow: 1;
  font-size: 14px;
  word-break: break-all;
}

.status-tag {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-pending {
  background-color: #fffbe6;
  color: #faad14;
}

.status-processing {
  background-color: #e6f7ff;
  color: #1890ff;
}

.status-success {
  background-color: #f6ffed;
  color: #52c41a;
}

.status-rejected {
  background-color: #fff2f0;
  color: #ff4d4f;
}

.photo-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 8px;
}

.photo-item {
  width: 120px;
  height: 120px;
  border-radius: 4px;
  cursor: pointer;
}

.no-photo {
  color: #9ca3af;
  padding: 20px;
  text-align: center;
  background-color: #f9fafb;
  border-radius: 4px;
}

::v-deep .el-drawer__body {
  padding: 0 !important;
  overflow: hidden;
}
</style>
