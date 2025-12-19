<template>
  <!-- 5.3 整体布局架构 - "1+8+1"架构 -->
  <div class="emergency-command-view">
    <!-- 头部操作栏 -->
    <div class="command-header">
      <div class="header-left">
        <div class="system-title">
          <h2>应急指挥可视化平台</h2>
        </div>
        <div class="current-event">
          <el-tag type="warning" size="large">防汛应急响应（三级）</el-tag>
          <span class="event-location">XX市XX区</span>
          <span class="event-time">2024-01-15 14:30:00</span>
        </div>
      </div>

      <div class="header-center">
        <el-button-group>
          <el-button type="primary" icon="VideoPlay" @click="startEmergencyPlan">启动方案</el-button>
          <el-button type="warning" icon="Phone" @click="startVideoMeeting">发起会议</el-button>
          <el-button type="success" icon="Camera" @click="saveSituationSnapshot">快照保存</el-button>
          <el-button type="info" icon="Setting" @click="openSettings">系统设置</el-button>
        </el-button-group>
      </div>

      <div class="header-right">
        <div class="time-display">
          <div class="current-time">{{ currentTime }}</div>
          <div class="current-date">{{ currentDate }}</div>
        </div>
        <div class="user-info">
          <el-avatar :size="36" src="@/assets/avatar.png" />
          <span class="username">指挥员：张三</span>
        </div>
      </div>
    </div>

    <!-- 主体内容：1+8+1架构 -->
    <div class="command-main">
      <!-- 5.3.1 核心态势区 (应急态势视图) -->
      <div class="core-situation-area">
        <!-- 事件核心信息栏 -->
        <div class="event-info-bar">
          <div class="info-item">
            <span class="label">事件等级：</span>
            <el-tag :type="eventLevel.tagType">{{ eventLevel.name }}</el-tag>
          </div>
          <div class="info-item">
            <span class="label">影响人数：</span>
            <span class="value highlight">{{ eventStats.affectedPeople }}人</span>
          </div>
          <div class="info-item">
            <span class="label">救援进度：</span>
            <div class="progress-container">
              <el-progress :percentage="eventStats.rescueProgress" :color="progressColor" />
              <span class="progress-text">{{ eventStats.rescueProgress }}%</span>
            </div>
          </div>
          <div class="info-item">
            <span class="label">当前阶段：</span>
            <span class="value">{{ eventStats.currentStage }}</span>
          </div>
          <div class="info-item">
            <span class="label">责任单位：</span>
            <span class="value">{{ eventStats.responsibleUnit }}</span>
          </div>
        </div>

        <!-- GIS地图区域 -->
        <div class="gis-container">
          <!-- 地图控制栏 -->
          <div class="map-controls">
            <el-button-group>
              <el-button size="small" icon="ZoomIn" @click="mapZoomIn">放大</el-button>
              <el-button size="small" icon="ZoomOut" @click="mapZoomOut">缩小</el-button>
              <el-button size="small" icon="Location" @click="locateEvent">定位</el-button>
              <el-button size="small" icon="Position" @click="startMeasure('distance')">测距</el-button>
              <el-button size="small" icon="Crop" @click="startMeasure('area')">测面</el-button>
              <el-button size="small" icon="CircleCheck" @click="drawCircle">圈选</el-button>
            </el-button-group>

            <div class="event-switcher">
              <el-select v-model="currentEventId" size="small" placeholder="切换事件">
                <el-option
                  v-for="event in parallelEvents"
                  :key="event.id"
                  :label="`${event.name}（${event.level}级）`"
                  :value="event.id"
                />
              </el-select>
            </div>
          </div>

          <!-- 地图主体 -->
          <div class="gis-map">
            <!-- 这里应该集成真实的地图组件 -->
            <div class="map-placeholder">
              <!-- 事件标记示例 -->
              <div class="event-marker level-1" style="left: 40%; top: 45%;">
                <div class="marker-icon">🔥</div>
                <div class="marker-label">主事件点</div>
                <div class="influence-area" style="width: 200px; height: 200px;"></div>
              </div>
            </div>
          </div>

          <!-- 右侧关联数据面板 -->
          <div class="related-data-panel">
            <div class="panel-header">
              <span class="panel-title">关联数据</span>
              <div class="panel-actions">
                <el-button size="small" icon="Refresh" circle @click="refreshData" />
                <el-button size="small" icon="Setting" circle @click="configPanel" />
              </div>
            </div>

            <div class="panel-content">
              <!-- 周边资源 -->
              <div class="data-section">
                <h4><el-icon><Location /></el-icon>周边资源</h4>
                <div class="resource-list">
                  <div v-for="resource in nearbyResources" :key="resource.id" class="resource-item">
                    <span class="resource-name">{{ resource.name }}</span>
                    <div class="resource-info">
                      <span class="resource-distance">{{ resource.distance }}km</span>
                      <el-tag size="small" :type="resource.statusType">{{ resource.status }}</el-tag>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 气象数据 -->
              <div class="data-section">
                <h4><el-icon><Sunny /></el-icon>气象数据</h4>
                <div class="weather-info">
                  <div class="weather-item">
                    <span class="weather-icon">🌧️</span>
                    <span class="weather-text">中雨 25℃</span>
                  </div>
                  <div class="weather-details">
                    <div class="detail-item">
                      <span class="label">湿度</span>
                      <span class="value">85%</span>
                    </div>
                    <div class="detail-item">
                      <span class="label">风速</span>
                      <span class="value">3级</span>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 交通路况 -->
              <div class="data-section">
                <h4><el-icon><Promotion /></el-icon>交通路况</h4>
                <div class="traffic-info">
                  <div class="traffic-status">
                    <el-tag type="danger" size="small">严重拥堵</el-tag>
                    <span class="traffic-detail">3条道路封闭</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 右侧功能模块区 -->
      <div class="function-modules-area">
        <!-- 5.3.2 标准化功能模块 (8个标签页) -->
        <div class="module-tabs">
          <el-tabs v-model="activeModule" type="border-card" @tab-click="handleTabClick">
            <!-- 5.1.2 应急资源分布 -->
            <el-tab-pane name="resource">
              <template #label>
                <span class="tab-label">
                  <el-icon><Location /></el-icon>
                  资源分布
                </span>
              </template>
              <div class="module-content">
                <div class="module-header">
                  <div class="search-section">
                    <el-input
                      v-model="resourceSearch"
                      placeholder="搜索资源..."
                      size="small"
                      prefix-icon="Search"
                      style="width: 200px"
                    />
                    <el-select v-model="resourceFilter.type" placeholder="资源类型" size="small">
                      <el-option label="全部类型" value="" />
                      <el-option label="救援车辆" value="vehicle" />
                      <el-option label="救援设备" value="equipment" />
                    </el-select>
                  </div>
                  <div class="action-section">
                    <el-button size="small" icon="Refresh">刷新</el-button>
                    <el-button size="small" icon="Download">导出</el-button>
                  </div>
                </div>
                <div class="module-body">
                  <!-- 资源分布内容 -->
                  <div class="placeholder-content">
                    应急资源分布模块 - 按行业配置资源类型与状态
                  </div>
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.3 指挥调度看板 -->
            <el-tab-pane name="dispatch">
              <template #label>
                <span class="tab-label">
                  <el-icon><Promotion /></el-icon>
                  指挥调度
                </span>
              </template>
              <div class="module-content">
                <!-- 指令下发区 -->
                <div class="command-send">
                  <el-input
                    v-model="commandContent"
                    type="textarea"
                    :rows="3"
                    placeholder="输入指令..."
                  />
                  <div class="command-actions">
                    <el-select v-model="commandType" placeholder="指令类型" size="small">
                      <el-option label="调度指令" value="dispatch" />
                      <el-option label="资源调配" value="resource" />
                    </el-select>
                    <el-button type="primary" icon="Promotion" @click="sendCommand">发送</el-button>
                    <el-button icon="Files" @click="batchDispatch">批量派单</el-button>
                  </div>
                </div>

                <!-- 任务看板 -->
                <div class="task-board">
                  <div class="placeholder-content">
                    指挥调度看板模块 - 集成指令下发、进度跟踪、多方通讯
                  </div>
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.4 应急处置进度 -->
            <el-tab-pane name="progress">
              <template #label>
                <span class="tab-label">
                  <el-icon><Timer /></el-icon>
                  处置进度
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  应急处置进度模块 - 甘特图展示各阶段时间分布
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.5 资源调度视图 -->
            <el-tab-pane name="schedule">
              <template #label>
                <span class="tab-label">
                  <el-icon><Truck /></el-icon>
                  资源调度
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  资源调度视图模块 - 调度轨迹可视化与在途异常预警
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.6 现场态势视图 -->
            <el-tab-pane name="site">
              <template #label>
                <span class="tab-label">
                  <el-icon><VideoCamera /></el-icon>
                  现场态势
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  现场态势视图模块 - 视频监控、物联网数据、现场上报融合
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.7 应急方案视图 -->
            <el-tab-pane name="plan">
              <template #label>
                <span class="tab-label">
                  <el-icon><Document /></el-icon>
                  应急方案
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  应急方案视图模块 - 预设方案、执行步骤、责任分工
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.8 跨域协同指挥 -->
            <el-tab-pane name="collaboration">
              <template #label>
                <span class="tab-label">
                  <el-icon><Connection /></el-icon>
                  跨域协同
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  跨域协同指挥模块 - 跨区域、跨部门、跨行业协同
                </div>
              </div>
            </el-tab-pane>

            <!-- 5.1.10 应急演练视图 -->
            <el-tab-pane name="drill">
              <template #label>
                <span class="tab-label">
                  <el-icon><Flag /></el-icon>
                  应急演练
                </span>
              </template>
              <div class="module-content">
                <div class="placeholder-content">
                  应急演练视图模块 - 演练场景模拟与成效评估
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 5.3.3 专项应急扩展区 -->
        <div class="special-expansion-area">
          <div class="expansion-header">
            <span class="title">专项应急场景</span>
            <div class="expansion-actions">
              <el-dropdown @command="handleSceneCommand">
                <el-button size="small">
                  快速切换<el-icon><ArrowDown /></el-icon>
                </el-button>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="flood">防汛应急视图</el-dropdown-item>
                    <el-dropdown-item command="traffic">交通事故视图</el-dropdown-item>
                    <el-dropdown-item command="fire">火灾救援视图</el-dropdown-item>
                    <el-dropdown-item command="medical">疫情防控视图</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
          <div class="expansion-content">
            <div
              v-for="scene in specialScenes"
              :key="scene.id"
              class="scene-card"
              :class="{ active: currentScene === scene.id }"
              @click="switchScene(scene)"
            >
              <div class="scene-icon">{{ getSceneIcon(scene.type) }}</div>
              <span class="scene-name">{{ scene.name }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 底部状态栏 -->
    <div class="command-footer">
      <div class="status-bar">
        <div class="status-item">
          <span class="label">数据更新：</span>
          <span class="value">1分钟前</span>
        </div>
        <div class="status-item">
          <span class="label">系统状态：</span>
          <span class="value status-normal">正常</span>
        </div>
        <div class="status-item">
          <span class="label">在线用户：</span>
          <span class="value">15人</span>
        </div>
        <div class="status-item">
          <span class="label">并行事件：</span>
          <span class="value">{{ parallelEvents.length }}个</span>
        </div>
      </div>
      <div class="quick-actions">
        <el-button size="small" icon="HelpFilled" text @click="showHelp">帮助</el-button>
        <el-button size="small" icon="Warning" text @click="showAlerts">告警(3)</el-button>
        <el-button size="small" icon="Bell" text @click="showNotifications">通知</el-button>
        <el-button size="small" icon="Monitor" text @click="switchDisplay">多屏切换</el-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted, onUnmounted } from 'vue'
import {
  Location,
  Sunny,
  Promotion,
  Document,
  Timer,
  VideoCamera,
  Connection,
  Flag,
  ArrowDown,
  HelpFilled,
  Warning,
  Bell,
  Monitor
} from '@element-plus/icons-vue'

// 响应式数据
const currentTime = ref('')
const currentDate = ref('')
const activeModule = ref('resource')
const currentEventId = ref('1')
const currentScene = ref('flood')

// 核心态势数据
const eventLevel = reactive({
  level: '3',
  name: '三级',
  tagType: 'warning'
})

const eventStats = reactive({
  affectedPeople: 1250,
  rescueProgress: 65,
  currentStage: '处置中',
  responsibleUnit: 'XX应急管理局'
})

const progressColor = computed(() => {
  if (eventStats.rescueProgress >= 80) return '#67C23A'
  if (eventStats.rescueProgress >= 60) return '#E6A23C'
  if (eventStats.rescueProgress >= 40) return '#409EFF'
  return '#F56C6C'
})

const parallelEvents = ref([
  { id: '1', name: '防汛应急响应', level: '3' },
  { id: '2', name: '交通事故', level: '2' },
  { id: '3', name: '疫情防控', level: '1' }
])

const nearbyResources = ref([
  { id: '1', name: '应急物资库', distance: '3.2', status: '可用', statusType: 'success' },
  { id: '2', name: '医疗救援队', distance: '5.1', status: '可用', statusType: 'success' },
  { id: '3', name: '消防车辆', distance: '2.5', status: '占用', statusType: 'warning' }
])

// 模块数据
const resourceSearch = ref('')
const resourceFilter = reactive({
  type: '',
  status: ''
})

const commandContent = ref('')
const commandType = ref('dispatch')

// 专项应急场景
const specialScenes = ref([
  { id: 'flood', name: '防汛应急视图', type: 'water', priority: true },
  { id: 'traffic', name: '交通事故视图', type: 'car', priority: false },
  { id: 'fire', name: '火灾救援视图', type: 'fire', priority: false },
  { id: 'medical', name: '疫情防控视图', type: 'medical', priority: false }
])

// 工具函数
const getSceneIcon = (type: string) => {
  const icons: Record<string, string> = {
    water: '🌊',
    car: '🚗',
    fire: '🔥',
    medical: '🏥'
  }
  return icons[type] || '📱'
}

// 操作方法
const startEmergencyPlan = () => {
  console.log('启动应急方案')
  // 调用接口：POST /api/emergency/plan/start
}

const startVideoMeeting = () => {
  console.log('发起视频会议')
  // 集成视频会议系统
}

const saveSituationSnapshot = () => {
  console.log('保存态势快照')
  // 调用接口：POST /api/emergency/snapshot/save
}

const mapZoomIn = () => console.log('地图放大')
const mapZoomOut = () => console.log('地图缩小')
const locateEvent = () => console.log('定位事件')
const startMeasure = (type: string) => console.log('开始测量:', type)
const drawCircle = () => console.log('画圈选择')

const refreshData = () => {
  console.log('刷新数据')
  // 调用接口：GET /api/emergency/situation/data
}

const configPanel = () => console.log('配置面板')

const handleTabClick = (tab: any) => {
  console.log('切换到模块:', tab.props.name)
}

const sendCommand = () => {
  console.log('发送指令')
  // 调用接口：POST /api/emergency/command/send
}

const batchDispatch = () => {
  console.log('批量派单')
}

const handleSceneCommand = (command: string) => {
  currentScene.value = command
  console.log('切换到场景:', command)
}

const switchScene = (scene: any) => {
  currentScene.value = scene.id
  console.log('加载场景:', scene.name)
  // 调用接口：GET /api/emergency/special/switch
}

const showHelp = () => console.log('显示帮助')
const showAlerts = () => console.log('显示告警')
const showNotifications = () => console.log('显示通知')
const switchDisplay = () => console.log('多屏切换')
const openSettings = () => console.log('打开系统设置')

// 生命周期
onMounted(() => {
  const updateTime = () => {
    const now = new Date()
    currentTime.value = now.toLocaleTimeString('zh-CN', { hour12: false })
    currentDate.value = now.toLocaleDateString('zh-CN', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      weekday: 'long'
    })
  }
  updateTime()
  const timeInterval = setInterval(updateTime, 1000)

  // 数据轮询
  const dataInterval = setInterval(() => {
    // 模拟数据更新
    // 实际中应调用API: GET /api/emergency/situation/data
  }, 60000) // 1分钟

  return () => {
    clearInterval(timeInterval)
    clearInterval(dataInterval)
  }
})
</script>

<style lang="scss" scoped>
// 应急指挥视图主容器
.emergency-command-view {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: linear-gradient(135deg, #0a1a2a 0%, #051525 100%);
  color: #fff;
  display: flex;
  flex-direction: column;
  font-family: "Microsoft YaHei", "PingFang SC", Arial, sans-serif;

  // 头部操作栏
  .command-header {
    height: 70px;
    background: linear-gradient(90deg, rgba(0, 30, 60, 0.95) 0%, rgba(0, 40, 80, 0.9) 100%);
    backdrop-filter: blur(10px);
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
    border-bottom: 1px solid rgba(0, 150, 255, 0.3);
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
    z-index: 100;

    .header-left {
      display: flex;
      align-items: center;
      gap: 30px;

      .system-title h2 {
        margin: 0;
        font-size: 20px;
        font-weight: bold;
        background: linear-gradient(90deg, #00a8ff, #4fc3f7);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
        text-shadow: 0 0 20px rgba(0, 168, 255, 0.3);
      }

      .current-event {
        display: flex;
        align-items: center;
        gap: 15px;

        .event-location,
        .event-time {
          color: #b0e0ff;
          font-size: 14px;
        }
      }
    }

    .header-center {
      .el-button-group .el-button {
        background: rgba(0, 80, 160, 0.6);
        border-color: rgba(0, 150, 255, 0.3);
        color: #e3f2fd;
        font-weight: 500;

        &:hover {
          background: rgba(0, 100, 200, 0.8);
          border-color: rgba(0, 200, 255, 0.5);
        }
      }
    }

    .header-right {
      display: flex;
      align-items: center;
      gap: 20px;

      .time-display {
        text-align: right;

        .current-time {
          font-size: 20px;
          font-weight: bold;
          color: #4fc3f7;
          text-shadow: 0 0 10px rgba(79, 195, 247, 0.5);
        }

        .current-date {
          font-size: 12px;
          color: #90caf9;
        }
      }

      .user-info {
        display: flex;
        align-items: center;
        gap: 10px;

        .username {
          color: #e3f2fd;
          font-size: 14px;
        }
      }
    }
  }

  // 主体内容
  .command-main {
    flex: 1;
    display: flex;
    overflow: hidden;
    padding: 15px;
    gap: 15px;

    // 5.3.1 核心态势区 (40%-50%)
    .core-situation-area {
      flex: 5; // 占50%宽度
      display: flex;
      flex-direction: column;
      background: linear-gradient(135deg, rgba(5, 25, 45, 0.9) 0%, rgba(8, 35, 60, 0.85) 100%);
      backdrop-filter: blur(5px);
      border-radius: 12px;
      border: 1px solid rgba(0, 150, 255, 0.2);
      overflow: hidden;
      box-shadow: 0 5px 20px rgba(0, 0, 0, 0.3);

      // 事件核心信息栏
      .event-info-bar {
        height: 70px;
        background: linear-gradient(90deg, rgba(0, 30, 60, 0.95) 0%, rgba(0, 40, 80, 0.9) 100%);
        display: flex;
        align-items: center;
        padding: 0 25px;
        border-bottom: 1px solid rgba(0, 150, 255, 0.2);
        gap: 30px;

        .info-item {
          display: flex;
          align-items: center;
          gap: 10px;

          .label {
            color: #90a4ae;
            font-size: 14px;
            white-space: nowrap;
          }

          .value {
            font-weight: bold;
            font-size: 16px;
            color: #e3f2fd;

            &.highlight {
              color: #ff9800;
              text-shadow: 0 0 10px rgba(255, 152, 0, 0.3);
            }
          }

          .progress-container {
            display: flex;
            align-items: center;
            gap: 10px;
            width: 120px;

            .el-progress {
              flex: 1;
            }

            .progress-text {
              color: #4fc3f7;
              font-weight: bold;
              min-width: 40px;
            }
          }
        }
      }

      // GIS容器
      .gis-container {
        flex: 1;
        display: flex;
        position: relative;
        min-height: 0;

        // 地图控制栏
        .map-controls {
          position: absolute;
          top: 15px;
          left: 15px;
          z-index: 100;
          display: flex;
          align-items: center;
          gap: 10px;
          background: rgba(0, 30, 60, 0.9);
          padding: 8px 12px;
          border-radius: 6px;
          border: 1px solid rgba(0, 150, 255, 0.2);

          .el-button-group .el-button {
            background: linear-gradient(135deg, rgba(0, 40, 80, 0.9), rgba(0, 60, 120, 0.8));
            border-color: rgba(0, 150, 255, 0.3);
            color: #b0e0ff;

            &:hover {
              background: linear-gradient(135deg, rgba(0, 60, 120, 0.9), rgba(0, 80, 160, 0.8));
              border-color: rgba(0, 200, 255, 0.5);
            }
          }

          .event-switcher .el-select {
            width: 150px;

            :deep(.el-input__inner) {
              background: rgba(0, 40, 80, 0.9);
              border-color: rgba(0, 150, 255, 0.3);
              color: #e3f2fd;
            }
          }
        }

        // 地图主体
        .gis-map {
          flex: 1;
          position: relative;
          background: linear-gradient(135deg, #0a2a4a 0%, #051e38 100%);

          .map-placeholder {
            width: 100%;
            height: 100%;
            position: relative;
            background-image:
              linear-gradient(rgba(0, 150, 255, 0.1) 1px, transparent 1px),
              linear-gradient(90deg, rgba(0, 150, 255, 0.1) 1px, transparent 1px);
            background-size: 50px 50px;

            .event-marker {
              position: absolute;
              transform: translate(-50%, -50%);
              cursor: pointer;
              z-index: 50;

              &.level-1 .marker-icon {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                background: linear-gradient(135deg, rgba(255, 0, 0, 0.9), rgba(255, 100, 100, 0.8));
                border: 2px solid #ff4444;
                box-shadow: 0 0 30px rgba(255, 0, 0, 0.6);
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 24px;
                animation: pulse 2s infinite;
              }

              .marker-label {
                position: absolute;
                top: 100%;
                left: 50%;
                transform: translateX(-50%);
                background: rgba(0, 30, 60, 0.9);
                color: #e3f2fd;
                padding: 2px 8px;
                border-radius: 4px;
                font-size: 12px;
                white-space: nowrap;
                margin-top: 5px;
                border: 1px solid rgba(0, 150, 255, 0.2);
              }

              .influence-area {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                border-radius: 50%;
                border: 1px solid rgba(255, 255, 255, 0.3);
                background: rgba(255, 0, 0, 0.1);
                opacity: 0.3;
                animation: ripple 3s infinite;
              }
            }
          }
        }

        // 右侧关联数据面板
        .related-data-panel {
          width: 320px;
          background: linear-gradient(135deg, rgba(10, 35, 60, 0.95), rgba(15, 45, 75, 0.9));
          border-left: 1px solid rgba(0, 150, 255, 0.2);
          display: flex;
          flex-direction: column;
          box-shadow: -5px 0 15px rgba(0, 0, 0, 0.2);

          .panel-header {
            height: 55px;
            padding: 0 18px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            border-bottom: 1px solid rgba(0, 150, 255, 0.2);
            background: rgba(0, 30, 60, 0.7);

            .panel-title {
              font-size: 16px;
              font-weight: bold;
              color: #4fc3f7;
            }
          }

          .panel-content {
            flex: 1;
            padding: 18px;
            overflow-y: auto;

            .data-section {
              margin-bottom: 24px;
              background: rgba(0, 30, 60, 0.4);
              border-radius: 8px;
              padding: 15px;
              border: 1px solid rgba(0, 150, 255, 0.1);

              h4 {
                color: #90caf9;
                margin-bottom: 12px;
                font-size: 14px;
                display: flex;
                align-items: center;
                gap: 8px;
              }

              .resource-list .resource-item {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 12px;
                background: rgba(0, 40, 80, 0.5);
                margin-bottom: 6px;
                border-radius: 6px;
                cursor: pointer;
                transition: all 0.3s;

                &:hover {
                  background: rgba(0, 80, 160, 0.6);
                  transform: translateX(5px);
                }

                .resource-name {
                  color: #e3f2fd;
                  font-size: 14px;
                }

                .resource-info {
                  display: flex;
                  align-items: center;
                  gap: 10px;

                  .resource-distance {
                    color: #4fc3f7;
                    font-size: 12px;
                    font-weight: bold;
                  }
                }
              }

              .weather-info .weather-item {
                display: flex;
                align-items: center;
                gap: 12px;
                margin-bottom: 12px;

                .weather-icon {
                  font-size: 28px;
                }

                .weather-text {
                  font-size: 16px;
                  color: #e3f2fd;
                  font-weight: 500;
                }
              }
            }
          }
        }
      }
    }

    // 右侧功能模块区 (40%-50%)
    .function-modules-area {
      flex: 5; // 占50%宽度
      display: flex;
      flex-direction: column;
      gap: 15px;

      // 5.3.2 标准化功能模块标签页
      .module-tabs {
        flex: 9; // 占90%高度
        background: linear-gradient(135deg, rgba(5, 25, 45, 0.9), rgba(8, 35, 60, 0.85));
        backdrop-filter: blur(5px);
        border-radius: 12px;
        border: 1px solid rgba(0, 150, 255, 0.2);
        overflow: hidden;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.3);

        :deep(.el-tabs) {
          height: 100%;

          .el-tabs__header {
            background: linear-gradient(90deg, rgba(0, 30, 60, 0.95), rgba(0, 40, 80, 0.9));
            margin: 0;
            border-bottom: 1px solid rgba(0, 150, 255, 0.2);

            .el-tabs__item {
              color: #b0bec5;
              font-size: 12px;
              padding: 0 15px;
              height: 42px;
              line-height: 42px;
              transition: all 0.3s;

              &:hover {
                color: #4fc3f7;
                background: rgba(0, 100, 200, 0.1);
              }

              &.is-active {
                color: #00a8ff;
                background: linear-gradient(90deg, rgba(0, 100, 200, 0.3), rgba(0, 150, 255, 0.2));
                font-weight: bold;
              }

              .tab-label {
                display: flex;
                align-items: center;
                gap: 6px;
              }
            }
          }

          .el-tabs__content {
            height: calc(100% - 42px);
            padding: 0;
          }
        }

        .module-content {
          height: 100%;
          padding: 18px;
          overflow-y: auto;
          background: rgba(0, 20, 40, 0.2);

          .module-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 18px;
            padding: 15px;
            background: rgba(0, 30, 60, 0.6);
            border-radius: 8px;
            border: 1px solid rgba(0, 150, 255, 0.2);
          }

          .command-send {
            background: rgba(0, 30, 60, 0.6);
            border-radius: 8px;
            padding: 15px;
            border: 1px solid rgba(0, 150, 255, 0.2);
            margin-bottom: 18px;

            .command-actions {
              display: flex;
              gap: 10px;
              align-items: center;
              margin-top: 12px;
            }
          }

          .placeholder-content {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 300px;
            color: #90a4ae;
            font-size: 16px;
            background: rgba(0, 30, 60, 0.4);
            border-radius: 8px;
            border: 1px dashed rgba(0, 150, 255, 0.3);
          }
        }
      }

      // 5.3.3 专项应急扩展区
      .special-expansion-area {
        flex: 1; // 占10%高度
        background: linear-gradient(135deg, rgba(5, 25, 45, 0.9), rgba(8, 35, 60, 0.85));
        backdrop-filter: blur(5px);
        border-radius: 12px;
        border: 1px solid rgba(0, 150, 255, 0.2);
        padding: 12px 15px;
        display: flex;
        flex-direction: column;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);

        .expansion-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 12px;

          .title {
            color: #4fc3f7;
            font-weight: bold;
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 8px;

            &::before {
              content: '📱';
              font-size: 16px;
            }
          }
        }

        .expansion-content {
          flex: 1;
          display: flex;
          gap: 12px;
          overflow-x: auto;

          .scene-card {
            min-width: 140px;
            background: linear-gradient(135deg, rgba(0, 40, 80, 0.6), rgba(0, 60, 120, 0.5));
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 15px 10px;
            transition: all 0.3s;
            cursor: pointer;
            border: 1px solid rgba(0, 150, 255, 0.2);

            &:hover {
              background: linear-gradient(135deg, rgba(0, 60, 120, 0.7), rgba(0, 80, 160, 0.6));
              transform: translateY(-3px);
              box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
            }

            &.active {
              background: linear-gradient(135deg, rgba(0, 100, 200, 0.8), rgba(0, 150, 255, 0.7));
              border-color: #4fc3f7;
              box-shadow: 0 0 20px rgba(79, 195, 247, 0.4);
            }

            .scene-icon {
              font-size: 36px;
              margin-bottom: 10px;
            }

            .scene-name {
              font-size: 13px;
              color: #b0e0ff;
              text-align: center;
            }
          }
        }
      }
    }
  }

  // 底部状态栏
  .command-footer {
    height: 45px;
    background: linear-gradient(90deg, rgba(0, 20, 40, 0.9), rgba(0, 30, 60, 0.8));
    backdrop-filter: blur(10px);
    border-top: 1px solid rgba(0, 150, 255, 0.3);
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
    box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.2);

    .status-bar {
      display: flex;
      gap: 30px;
      align-items: center;

      .status-item {
        font-size: 13px;
        display: flex;
        align-items: center;
        gap: 5px;

        .label {
          color: #90a4ae;
        }

        .value {
          color: #b0e0ff;
          font-weight: 500;

          &.status-normal {
            color: #4caf50;
            text-shadow: 0 0 10px rgba(76, 175, 80, 0.3);
          }
        }
      }
    }

    .quick-actions {
      display: flex;
      gap: 20px;
      align-items: center;

      .el-button {
        color: #b0e0ff;

        &:hover {
          color: #4fc3f7;
        }
      }
    }
  }
}

// 动画定义
@keyframes pulse {
  0% {
    transform: translate(-50%, -50%) scale(1);
    opacity: 1;
  }
  50% {
    transform: translate(-50%, -50%) scale(1.1);
    opacity: 0.8;
  }
  100% {
    transform: translate(-50%, -50%) scale(1);
    opacity: 1;
  }
}

@keyframes ripple {
  0% {
    transform: translate(-50%, -50%) scale(0.8);
    opacity: 0.3;
  }
  100% {
    transform: translate(-50%, -50%) scale(1.2);
    opacity: 0;
  }
}

// 滚动条样式
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: rgba(0, 30, 60, 0.3);
  border-radius: 3px;
}

::-webkit-scrollbar-thumb {
  background: linear-gradient(135deg, rgba(0, 150, 255, 0.6), rgba(79, 195, 247, 0.5));
  border-radius: 3px;
}
</style>
