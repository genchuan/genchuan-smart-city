<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">应用场景分类详情</span>
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
        <el-card class="info-card basic-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ data?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景分类代码：</span>
              <span class="value key-field">{{ data?.sceneCatCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应用场景分类名称：</span>
              <span class="value">{{ data?.sceneCatName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类层级：</span>
              <span class="value">
                <span :class="levelClass(data?.catLevel)">{{
                  data?.catLevel === '1'
                    ? '大类'
                    : data?.catLevel === '2'
                      ? '中类'
                      : data?.catLevel === '3'
                        ? '小类'
                        : '-'
                }}</span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 父级信息卡片 -->
        <el-card class="info-card parent-info-card">
          <div class="card-header">父级信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">父级分类ID：</span>
              <span class="value">{{ data?.parentCatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">父级分类名称：</span>
              <span class="value">{{ data?.parentCatName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="info-card status-info-card">
          <div class="card-header">状态信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">是否扩展类：</span>
              <span class="value">
                <span :class="data?.isExt === '1' ? 'ext-tag' : 'standard-tag'">
                  {{ data?.isExt === '1' ? '扩展类' : '标准类' }}
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="label">启用状态：</span>
              <span class="value">
                <el-tag :type="data?.enableStatus === '1' ? 'success' : 'danger'">
                  {{ data?.enableStatus === '1' ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 关联信息卡片 -->
        <el-card class="info-card relation-info-card">
          <div class="card-header">关联信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">关联监测部件类型ID：</span>
              <span class="value">{{ data?.relCompCatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联监测部件类型名称：</span>
              <span class="value">{{ data?.relCompCatName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联监测事件类型ID：</span>
              <span class="value">{{ data?.relEvtCatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联监测事件类型名称：</span>
              <span class="value">{{ data?.relEvtCatName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 业务信息卡片 -->
        <el-card class="info-card business-info-card">
          <div class="card-header">业务信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">创建人(业务)：</span>
              <span class="value">{{ data?.createUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间(业务)：</span>
              <span class="value">{{ formatDate(data?.createTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新人(业务)：</span>
              <span class="value">{{ data?.updateUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间(业务)：</span>
              <span class="value">{{ formatDate(data?.updateTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(data?.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card">
          <div class="card-header">扩展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">应用场景分类说明：</span>
              <span class="value">{{ data?.sceneCatDesc || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { AppSceneCategoryVO } from '@/api/dataHub/sceneCategory/appCateInfo'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => AppSceneCategoryVO | null,
    default: null
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
const isFullscreen = ref(false)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 分类层级样式
const levelClass = (level?: string) => {
  switch (level) {
    case '1':
      return 'level-1'
    case '2':
      return 'level-2'
    case '3':
      return 'level-3'
    default:
      return ''
  }
}

// 监听visible变化，重置全屏状态
watch(
  () => props.visible,
  (newVal) => {
    if (!newVal) {
      isFullscreen.value = false
    }
  }
)
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 20px;
}

.drawer-content {
  height: 100%;
  overflow-y: auto;
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
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  font-size: 14px;
  margin-bottom: 10px;
  border-radius: 4px 4px 0 0;
}

.card-body {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  font-size: 13px;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #666;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

/* 不同卡片的标题背景色（马克龙色系） */
.basic-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.parent-info-card .card-header {
  background-color: #fff7e6;
  color: #faad14;
}

.status-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.relation-info-card .card-header {
  background-color: #fff0f6;
  color: #eb2f96;
}

.business-info-card .card-header {
  background-color: #f0f2ff;
  color: #722ed1;
}

.extend-info-card .card-header {
  background-color: #e8f3ff;
  color: #00b42a;
}

/* 状态标签样式 */
.level-1 {
  color: #4096ff;
  font-weight: 500;
}

.level-2 {
  color: #67c23a;
  font-weight: 500;
}

.level-3 {
  color: #faad14;
  font-weight: 500;
}

.ext-tag {
  color: #722ed1;
  background-color: #f9f0ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.standard-tag {
  color: #1890ff;
  background-color: #e6f7ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

/* 响应式调整 */
@media (max-width: 1024px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
