<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">告警预警规则详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6 p-4 bg-gray-50">
        <!-- 基本信息 -->
        <el-card class="card-hover">
          <div class="card-header bg-blue-50 px-4 py-2 mb-4">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">规则ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">规则名称：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.ruleName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联对象类型：</span>
              <span class="value">{{ detailData.relatedObjectType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联对象范围：</span>
              <span class="value">{{ detailData.relatedObjectScope || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联对象分类ID：</span>
              <span class="value">{{ detailData.relatedObjectCategoryId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联对象ID列表：</span>
              <span class="value">{{ detailData.relatedObjectIdList || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警条件信息 -->
        <el-card class="card-hover">
          <div class="card-header bg-purple-50 px-4 py-2 mb-4">
            <span class="text-purple-600 font-medium">预警条件信息</span>
          </div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">预警触发条件：</span>
              <span class="value">{{ detailData.triggerCondition || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警指标编码：</span>
              <span class="value">{{ detailData.warningIndicatorCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警阈值上限：</span>
              <span class="value">{{ detailData.warningThresholdUpper || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警阈值下限：</span>
              <span class="value">{{ detailData.warningThresholdLower || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">状态判定值：</span>
              <span class="value">{{ detailData.statusDetermineValue || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">触发时长(分钟)：</span>
              <span class="value">{{ detailData.triggerDuration || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警级别与频率 -->
        <el-card class="card-hover">
          <div class="card-header bg-orange-50 px-4 py-2 mb-4">
            <span class="text-orange-600 font-medium">预警级别与频率</span>
          </div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">预警等级：</span>
              <span :class="getLevelClass(detailData.earlyWarningLevel)" class="value font-medium">
                {{ detailData.earlyWarningLevel || '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">触发频率(次/小时)：</span>
              <span class="value">{{ detailData.triggerFrequency || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">重复触发间隔(分钟)：</span>
              <span class="value">{{ detailData.repeatTriggerInterval || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">启用状态：</span>
              <span class="value">
                <el-tag :type="detailData.enableStatus === 1 ? 'success' : 'danger'">
                  {{ detailData.enableStatus === 1 ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息 -->
        <el-card class="card-hover">
          <div class="card-header bg-green-50 px-4 py-2 mb-4">
            <span class="text-green-600 font-medium">其他信息</span>
          </div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">规则描述：</span>
              <span class="value">{{ detailData.ruleDescription || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value">{{ detailData.createUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新人：</span>
              <span class="value">{{ detailData.updateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTimeSys) }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统更新时间：</span>
              <span class="value">{{ formatDate(detailData.updateTimeSys) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-pink-50 px-4 py-2 mb-4">
            <span class="text-pink-600 font-medium">扩展信息</span>
          </div>
          <div class="info-grid md:grid-cols-3">
            <div class="info-item">
              <span class="label">扩展分类字段1：</span>
              <span class="value">{{ detailData.extendCategory1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">扩展分类字段2：</span>
              <span class="value">{{ detailData.extendCategory2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">扩展分类字段3：</span>
              <span class="value">{{ detailData.extendCategory3 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { AlarmRuleApi, AlarmRuleVO } from '@/api/dataHub/warningAlarm/warnAlarmRuleMgr'
import { ref } from 'vue'

defineOptions({ name: 'AlarmRuleDetailDrawer' })

const message = useMessage()

// 抽屉显示状态
const visible = ref(false)
// 标题
const title = ref('预警规则详情')
// 是否全屏
const isFullscreen = ref(false)
// 详情数据
const detailData = ref<Partial<AlarmRuleVO>>({})
// 加载状态
const loading = ref(false)

/** 格式化日期 */
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

/** 获取预警等级样式 */
const getLevelClass = (level: number) => {
  const classes = [
    'text-pink-500', // 1级
    'text-red-500', // 2级
    'text-orange-500', // 3级
    'text-blue-500', // 4级
    'text-green-500' // 5级
  ]
  return level && level >= 1 && level <= 5 ? classes[level - 1] : ''
}

/** 打开抽屉 */
const open = async (id: number) => {
  visible.value = true
  isFullscreen.value = false
  loading.value = true
  try {
    const data = await AlarmRuleApi.getAlarmRule(id)
    detailData.value = data
  } catch (error) {
    message.error('获取详情失败')
    visible.value = false
  } finally {
    loading.value = false
  }
}

// 暴露方法
defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f9fafb;
}

.drawer-content {
  height: 100%;
  overflow-y: auto;
  padding-bottom: 20px;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  border-radius: 4px 4px 0 0;
  font-size: 14px;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 16px;
  padding: 0 8px;
}

.md\:grid-cols-3 {
  grid-template-columns: repeat(3, 1fr);
}

.info-item {
  display: flex;
  flex-wrap: wrap;
  padding: 4px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  color: #666;
  min-width: 100px;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex-grow: 1;
  word-break: break-all;
}

::v-deep .el-card__body {
  padding: 15px;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow: hidden;
}
</style>
