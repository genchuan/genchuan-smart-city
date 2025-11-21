<template>
  <el-drawer
    :title="null"
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备共享属性详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基础信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value font-medium text-blue-600">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">属性配置ID：</span>
              <span class="value">{{ data.shareAttrCfgId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备ID：</span>
              <span class="value font-medium text-blue-600">{{ data.deviceId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备名称：</span>
              <span class="value">{{ data.deviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备编码：</span>
              <span class="value">{{ data.deviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 属性信息卡片 -->
        <el-card class="info-card attr-info-card">
          <div class="card-header">属性信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">属性名称：</span>
              <span class="value">{{ data.attrName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">属性代码：</span>
              <span class="value">{{ data.attrCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">属性值：</span>
              <span class="value">{{ data.attrValue || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">数据类型：</span>
              <span class="value">{{ data.dataType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">值域范围：</span>
              <span class="value">{{ data.valueRange || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">是否必选：</span>
              <span
                class="value"
                :class="data.isRequired !== '1' ? 'text-green-500' : 'text-red-500'"
              >
                {{ data.isRequired === '1' ? '是' : '否' }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 维护信息卡片 -->
        <el-card class="info-card mnt-info-card">
          <div class="card-header">维护信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">维护单位代码：</span>
              <span class="value">{{ data.mntDeptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">维护单位名称：</span>
              <span class="value">{{ data.mntDeptName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">配置人：</span>
              <span class="value">{{ data.cfgUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">配置时间：</span>
              <span class="value">{{ formatDate(data.cfgTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间：</span>
              <span class="value">{{ formatDate(data.updateTimeSys) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
          <div class="card-header">扩展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">属性说明：</span>
              <span class="value">{{ data.attrDesc || '-' }}</span>
            </div>
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
import { DeviceShareAttrVO } from '@/api/dataHub/deviceManage/deviceDataManage/deviceshareattr'
import { watch, ref } from 'vue' // 显式导入必要的API

defineOptions({ name: 'DeviceShareAttrDetail' })

// 格式化日期 - 修复弃用符号问题
const formatDate = (val: any): string => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 接收父组件参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 向父组件发送事件
const emit = defineEmits(['close'])

// 内部状态管理抽屉显示（解决直接修改props问题）
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 内部状态
const isFullscreen = ref(false)
const data = ref<DeviceShareAttrVO>({
  id: 0,
  shareAttrCfgId: '',
  deviceId: '',
  deviceName: '',
  deviceCode: '',
  attrName: '',
  attrCode: '',
  attrValue: '',
  dataType: '',
  valueRange: '',
  isRequired: '',
  attrDesc: '',
  mntDeptCode: '',
  mntDeptName: '',
  cfgTime: '', // 修正为字符串类型，避免初始化为Date对象可能导致的问题
  cfgUser: '',
  updateTimeSys: '', // 修正为字符串类型
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 设置详情数据
const setData = (row: DeviceShareAttrVO) => {
  data.value = { ...row }
}

// 处理关闭事件
const handleClose = () => {
  emit('close')
}

// 暴露方法给父组件
defineExpose({ setData })
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

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 16px;
  font-size: 15px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 12px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.attr-info-card .card-header {
  background-color: #fff7e6;
  color: #fa8c16;
}

.mnt-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.ext-info-card .card-header {
  background-color: #fff0f6;
  color: #f5222d;
}

.card-body {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  line-height: 1.5;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #6b7280;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #374151;
  word-break: break-all;
}
</style>
