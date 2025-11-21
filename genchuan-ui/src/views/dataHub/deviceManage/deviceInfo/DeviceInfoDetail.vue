<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="device-detail-drawer"
    title=""
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息 -->
        <el-card class="info-card basic-info-card">
          <div class="card-header">基本信息</div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">设备ID：</span>
              <span class="value font-semibold text-blue-600">{{
                deviceInfo?.deviceId || '-'
              }}</span>
            </div>
            <div class="info-item"
              ><span class="label">设备名称：</span
              ><span class="value">{{ deviceInfo?.deviceName || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">设备编码：</span
              ><span class="value">{{ deviceInfo?.deviceCode || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">设备类型：</span
              ><span class="value">{{ deviceInfo?.deviceType || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">设备型号：</span
              ><span class="value">{{ deviceInfo?.deviceModel || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">设备规格：</span
              ><span class="value">{{ deviceInfo?.deviceSpec || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">生产厂商：</span
              ><span class="value">{{ deviceInfo?.manufacturer || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">生产日期：</span
              ><span class="value">{{ formatDate(deviceInfo?.productionDate) }}</span></div
            >
          </div>
        </el-card>

        <!-- 状态信息 -->
        <el-card class="info-card status-info-card">
          <div class="card-header">状态信息</div>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">设备状态：</span>
              <span
                :class="deviceInfo?.deviceStatus === '01' ? 'status-normal' : 'status-abnormal'"
              >
                {{ deviceInfo?.deviceStatus || '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">运行状态：</span>
              <span
                :class="deviceInfo?.runStatus === 'online' ? 'status-running' : 'status-stopped'"
              >
                {{ deviceInfo?.runStatus || '-' }}
              </span>
            </div>
            <div class="info-item"
              ><span class="label">最后在线时间：</span
              ><span class="value">{{ formatDate(deviceInfo?.lastOnlineTime) }}</span></div
            >
            <div class="info-item"
              ><span class="label">质保期（月）：</span
              ><span class="value">{{ deviceInfo?.warrantyPeriod || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">电池电量：</span
              ><span class="value">{{
                deviceInfo?.batteryLevel ? `${deviceInfo.batteryLevel}%` : '-'
              }}</span></div
            >
            <div class="info-item"
              ><span class="label">信号强度：</span
              ><span class="value">{{ deviceInfo?.signalStrength || '-' }}</span></div
            >
          </div>
        </el-card>

        <!-- 安装信息 -->
        <el-card class="info-card install-info-card">
          <div class="card-header">安装信息</div>
          <div class="info-grid">
            <div class="info-item"
              ><span class="label">安装日期：</span
              ><span class="value">{{ formatDate(deviceInfo?.installDate) }}</span></div
            >
            <div class="info-item"
              ><span class="label">采购日期：</span
              ><span class="value">{{ formatDate(deviceInfo?.purchaseDate) }}</span></div
            >
            <div class="info-item"
              ><span class="label">安装位置：</span
              ><span class="value">{{ deviceInfo?.installLocation || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">坐标X（经度）：</span
              ><span class="value">{{ deviceInfo?.coordX || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">坐标Y（纬度）：</span
              ><span class="value">{{ deviceInfo?.coordY || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">高程：</span
              ><span class="value">{{ deviceInfo?.elevation || '-' }}</span></div
            >
          </div>
        </el-card>

        <!-- 网络信息 -->
        <el-card class="info-card network-info-card">
          <div class="card-header">网络信息</div>
          <div class="info-grid">
            <div class="info-item"
              ><span class="label">IP地址：</span
              ><span class="value">{{ deviceInfo?.ipAddress || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">MAC地址：</span
              ><span class="value">{{ deviceInfo?.macAddress || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">通信协议：</span
              ><span class="value">{{ deviceInfo?.communicationProtocol || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">数据上报间隔：</span
              ><span class="value">{{
                deviceInfo?.dataReportInterval ? `${deviceInfo.dataReportInterval}秒` : '-'
              }}</span>
            </div>
            <div class="info-item"
              ><span class="label">采样频率：</span
              ><span class="value">{{
                deviceInfo?.samplingFrequency ? `${deviceInfo.samplingFrequency}秒` : '-'
              }}</span></div
            >
          </div>
        </el-card>

        <!-- 维护信息 -->
        <el-card class="info-card maintenance-info-card">
          <div class="card-header">维护信息</div>
          <div class="info-grid">
            <div class="info-item"
              ><span class="label">维护单位：</span
              ><span class="value">{{ deviceInfo?.maintenanceOrg || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">维护人：</span
              ><span class="value">{{ deviceInfo?.maintenancePerson || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">维护电话：</span
              ><span class="value">{{ deviceInfo?.maintenancePhone || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">维护周期：</span
              ><span class="value">{{
                deviceInfo?.maintenanceCycle ? `${deviceInfo.maintenanceCycle}天` : '-'
              }}</span></div
            >
            <div class="info-item"
              ><span class="label">最后维护日期：</span
              ><span class="value">{{ formatDate(deviceInfo?.lastMaintenanceDate) }}</span></div
            >
            <div class="info-item"
              ><span class="label">下次维护日期：</span
              ><span class="value">{{ formatDate(deviceInfo?.nextMaintenanceDate) }}</span></div
            >
          </div>
        </el-card>

        <!-- 其他信息 -->
        <el-card class="info-card other-info-card">
          <div class="card-header">其他信息</div>
          <div class="info-grid">
            <div class="info-item"
              ><span class="label">序列号：</span
              ><span class="value">{{ deviceInfo?.serialNumber || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">固件版本：</span
              ><span class="value">{{ deviceInfo?.firmwareVersion || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">软件版本：</span
              ><span class="value">{{ deviceInfo?.softwareVersion || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">电压等级：</span
              ><span class="value">{{ deviceInfo?.voltageLevel || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">供电方式：</span
              ><span class="value">{{ deviceInfo?.powerSupply || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">备注：</span
              ><span class="value">{{ deviceInfo?.remark || '-' }}</span></div
            >
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceInfoVO } from '@/api/dataHub/deviceManage/deviceinfo'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  deviceInfo: {
    type: Object as () => DeviceInfoVO | null,
    default: null
  }
})

const emit = defineEmits(['close'])
const visible = ref(props.visible)
watch(
  () => props.visible,
  (val) => (visible.value = val)
)
watch(visible, (val) => {
  if (!val) emit('close')
})

const isFullscreen = ref(false)

const handleClose = () => {
  visible.value = false
  isFullscreen.value = false
}

const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}
</script>

<style scoped>
/* 保留原样式 */
.device-detail-drawer {
  background-color: #f5f7fa;
  padding: 15px;
}

.detail-container {
  margin-top: 10px;
}

.grid-layout {
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
  color: #333;
  border-bottom: 1px solid #f0f0f0;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.status-info-card .card-header {
  background-color: #fff7e6;
}

.install-info-card .card-header {
  background-color: #f6ffed;
}

.network-info-card .card-header {
  background-color: #fff0f6;
}

.maintenance-info-card .card-header {
  background-color: #f0f2ff;
}

.other-info-card .card-header {
  background-color: #fff;
  border-bottom: 1px dashed #f0f0f0;
}

.info-grid {
  padding: 16px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-item {
  display: flex;
  flex-wrap: wrap;
  padding: 4px 0;
}

.label {
  color: #666;
  min-width: 100px;
}

.value {
  color: #333;
  flex: 1;
}

.status-normal {
  color: #4caf50;
  background-color: rgba(76, 175, 80, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-abnormal {
  color: #f44336;
  background-color: rgba(244, 67, 54, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-running {
  color: #2196f3;
  background-color: rgba(33, 150, 243, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-stopped {
  color: #ff9800;
  background-color: rgba(255, 152, 0, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
