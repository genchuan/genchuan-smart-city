<template>
  <div class="cityscape-order-container">
    <!-- 页面头部 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/cityscape-order.png"
          alt="市容秩序图标"
          class="title-icon"
        />
        市容秩序专题
      </h1>
      <div class="header-actions">
        <el-button size="small" type="primary" @click="openHighIncidenceAnalysis">
          高发区域分析
        </el-button>
        <el-button size="small" @click="refreshData">刷新数据</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主体内容 -->
    <div class="main-content">
      <!-- 左侧50%：指标卡片（上）+事件地图区域（下） -->
      <div class="left-area" style="min-width: 5vw;">
        <!-- 上半部分：指标卡片（完全保留，无任何改动） -->
        <div class="stat-card-container">
          <div class="stat-card total-card">
            <div class="stat-title">事件总数</div>
            <div class="stat-value">{{ overviewData.totalRptCount }}</div>
            <div class="stat-desc">超时未办结：{{ overviewData.timeoutCount }} 件</div>
          </div>
          <div class="stat-card rate-card" :class="overviewData.completeRate < 80 ? 'danger-rate' : 'normal-rate'">
            <div class="stat-title">办结率</div>
            <div class="stat-value">{{ overviewData.completeRate }}%</div>
            <div class="stat-desc">近7天平均：{{ avgCompleteRate }}%</div>
          </div>
          <div class="stat-card high-area-card">
            <div class="stat-title">高发区域TOP3</div>
            <div class="high-area-list">
              <div
                class="high-area-item"
                v-for="(area, index) in overviewData.highIncidenceAreas"
                :key="index"
                @click="handleTypeDrill(area.mainType)"
                :style="{ borderLeftColor: getTypeColor(area.mainType) }"
              >
                <span class="area-rank">{{ index + 1 }}</span>
                <span class="area-name">{{ area.areaName }}</span>
                <span class="area-count">{{ area.incidentCount }}件</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 下半部分：事件地图区域（布满整个面板） -->
        <div class="map-container panel" ref="mapPanel">
          <div class="panel-header">
            <h3>市容秩序事件地图</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('mapPanel')">
                <el-icon color="#00c6ff" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body map-body">
            <CityscapeOrderMap
              ref="mapComponent"
              idName="municipalMap"
              :geometriesArray="filteredOrderList"
            />
          </div>
        </div>
      </div>

      <!-- 右侧50%：办结率趋势图表（上） + 筛选栏 + 列表区域 （下）-->
      <div class="right-area" style="min-width: 5vw;">
        <!-- 上半部分：办结率趋势图表 -->
        <div class="trend-chart-panel panel" ref="trendPanel">
          <div class="panel-header">
            <h3>近7天办结率趋势</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#14b8a6" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="completeRateTrend.xAxis"
              :series="completeRateTrend.series"
              :yAxisName="''"
            />
          </div>
        </div>

        <!-- 下半部分：筛选栏（单行） + 列表区域 -->
        <div class="filter-list-container">
          <!-- 筛选栏（严格对齐参考代码布局） -->
          <div class="panel filter-bar list-filter-bar">
            <el-select
              v-model="filterParams.matterType"
              placeholder="事件类型"
              size="small"
              multiple
              collapse-tags
            >
              <el-option
                v-for="type in orderTypeDict"
                :key="type.value"
                :label="type.label"
                :value="type.value"
                :style="{ color: type.color }"
              />
            </el-select>
            <el-cascader
              v-model="filterParams.area"
              placeholder="选择区域"
              size="small"
              :options="areaDict"
              separator="/"
              collapse-tags
            />
            <el-select
              v-model="filterParams.matterStatus"
              placeholder="处置状态"
              size="small"
            >
              <el-option label="全部" value="" />
              <el-option label="未处置" value="未处置" />
              <el-option label="处置中" value="处置中" />
              <el-option label="已办结" value="已办结" />
            </el-select>
            <el-select
              v-model="filterParams.timeRange"
              placeholder="上报时间"
              size="small"
            >
              <el-option label="全部" value="" />
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <el-button
              size="small"
              type="success"
              style="margin-left: 3vw"
              @click="resetFilter"
            >
              重 置
            </el-button>
          </div>

          <!-- 未办结事件列表 -->
          <div class="task-list-container panel" ref="taskTablePanel">
            <div class="panel-header">
              <h3>未办结事件列表</h3>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('taskTablePanel')">
                  <el-icon color="#ec4899" size="16">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body table-scroll">
              <transition-group name="task-list">
                <el-table
                  ref="taskTable"
                  :data="unCompletedOrderList"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick"
                  key="task-table"
                >
                  <el-table-column prop="mng_matter_id" label="事件ID" />
                  <el-table-column prop="matter_name" label="事件名称" />
<!--                  <el-table-column prop="matter_type" label="事件类型">-->
<!--                    <template #default="scope">-->
<!--                      <el-tag :style="{ background: getTypeColor(scope.row.matter_type), color: '#fff' }">-->
<!--                        {{ scope.row.matter_type }}-->
<!--                      </el-tag>-->
<!--                    </template>-->
<!--                  </el-table-column>-->
                  <el-table-column prop="incident_location" label="发生位置" />
                  <el-table-column prop="report_time" label="上报时间">
                    <template #default="scope">
                      <span :class="{ timeout: scope.row.is_timeout }">{{ scope.row.report_time }}</span>
                    </template>
                  </el-table-column>
<!--                  <el-table-column prop="matter_status" label="处置状态">-->
<!--                    <template #default="scope">-->
<!--                      <el-tag-->
<!--                        :type="scope.row.matter_status === '未处置' ? 'danger' : scope.row.matter_status === '处置中' ? 'info' : 'success'"-->
<!--                      >-->
<!--                        {{ scope.row.matter_status }}-->
<!--                      </el-tag>-->
<!--                    </template>-->
<!--                  </el-table-column>-->
                  <el-table-column prop="responsible_person" label="责任人" />
                  <el-table-column label="操作">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="handleDispatch(scope.row)"
                        v-if="scope.row.matter_status !== '已办结'"
                      >
                        派单执法
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </transition-group>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 事件详情弹窗 -->
    <el-dialog
      v-model="orderDetailVisible"
      :title="currentOrder?.matter_name || '市容秩序事件详情'"
      width="800px"
      class="order-detail-dialog"
    >
      <div class="order-detail">
        <div class="detail-section">
          <h3>事件基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="事件ID">{{ currentOrder?.mng_matter_id }}</el-descriptions-item>
            <el-descriptions-item label="事件名称">{{ currentOrder?.matter_name }}</el-descriptions-item>
            <el-descriptions-item label="事件类型">{{ currentOrder?.matter_type }}</el-descriptions-item>
            <el-descriptions-item label="发生位置">{{ currentOrder?.incident_location }}</el-descriptions-item>
            <el-descriptions-item label="上报时间">{{ currentOrder?.report_time }}</el-descriptions-item>
            <el-descriptions-item label="处置状态">{{ currentOrder?.matter_status }}</el-descriptions-item>
            <el-descriptions-item label="责任人">{{ currentOrder?.responsible_person || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentOrder?.update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>违规详情</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="违规描述">{{ currentOrder?.violation_desc }}</el-descriptions-item>
            <el-descriptions-item label="关联部件">{{ currentOrder?.related_parts }}</el-descriptions-item>
          </el-descriptions>
          <div class="scene-img-container" v-if="currentOrder?.scene_img">
            <h4>现场图片</h4>
            <img :src="currentOrder.scene_img" alt="现场图片" class="scene-img" />
          </div>
        </div>

        <div class="detail-section" v-if="enforcementOrderId">
          <h3>执法工单信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="工单ID">{{ enforcementOrderId }}</el-descriptions-item>
            <el-descriptions-item label="派单时间">{{ dispatchTime }}</el-descriptions-item>
            <el-descriptions-item label="派单状态">已派单</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="orderDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleCompleteOrder"
          v-if="currentOrder?.matter_status === '处置中'"
        >
          标记办结
        </el-button>
      </template>
    </el-dialog>

    <!-- 派单执法弹窗 -->
    <el-dialog
      v-model="dispatchVisible"
      title="执法工单派单"
      width="600px"
      class="dispatch-dialog"
      :close-on-click-modal="false"
    >
      <el-form :model="dispatchForm" label-width="120px" :rules="dispatchRules" ref="dispatchFormRef">
        <el-form-item label="执法人员" prop="enforcer">
          <el-select v-model="dispatchForm.enforcer" placeholder="选择执法人员">
            <el-option label="李执法" value="李执法" />
            <el-option label="王执法" value="王执法" />
            <el-option label="张执法" value="张执法" />
            <el-option label="赵执法" value="赵执法" />
          </el-select>
        </el-form-item>
        <el-form-item label="预计办结时间" prop="expectTime">
          <el-date-picker
            v-model="dispatchForm.expectTime"
            type="datetime"
            placeholder="选择预计办结时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="执法要求" prop="requirement">
          <el-input
            v-model="dispatchForm.requirement"
            type="textarea"
            rows="3"
            placeholder="输入执法要求（如：限时2小时内处置）"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dispatchVisible = false">取消</el-button>
        <el-button type="primary" @click="submitDispatch">确认派单</el-button>
      </template>
    </el-dialog>

    <!-- 高发区域分析弹窗 -->
    <el-dialog
      v-model="highIncidenceAnalysisVisible"
      :title="currentHighArea || '高发区域分析'"
      width="700px"
      class="high-incidence-dialog"
    >
      <div class="analysis-content">
        <div class="analysis-section">
          <h3>高发原因分析</h3>
          <div class="analysis-text">{{ highIncidenceData.reason }}</div>
        </div>
        <div class="analysis-section">
          <h3>整改建议</h3>
          <div class="analysis-text">{{ highIncidenceData.suggestion }}</div>
        </div>
      </div>
      <template #footer>
        <el-button @click="highIncidenceAnalysisVisible = false">关闭</el-button>
        <el-button type="primary" @click="exportAnalysisReport">导出分析报告</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {computed, getCurrentInstance, onMounted, onUnmounted, ref, watch} from 'vue';
import {useRouter} from 'vue-router';
import {ElMessage, ElMessageBox} from 'element-plus';
import screenFull from 'screenfull';
import ChartLine from './ChartLine3.vue';
import {FullScreen} from "@element-plus/icons-vue";
import CityscapeOrderMap from './CityscapeOrderMap.vue';
import {
  createEnforcementWorkOrder,
  fetchAreaDict,
  fetchCompleteRateTrend,
  fetchHighIncidenceAnalysis,
  fetchOrderOverview,
  fetchOrderSpatial,
  fetchOrderTypeDict,
  markOrderCompleted
} from '@/api/overview/urbanmanagement/SubdomainScenarioTopics.js';

const router = useRouter();
const instance = getCurrentInstance();

// 定时刷新定时器
let refreshTimer = null;

// 全屏切换功能
const toggleFullscreen = (panelRefName) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }

  const panel = instance.refs[panelRefName];
  if (!panel) {
    ElMessage.error('未找到面板元素');
    return;
  }

  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit();
  } else {
    screenFull.request(panel);
  }
};

// 基础数据
const overviewData = ref({
  totalRptCount: 0,
  completeRate: 0,
  timeoutCount: 0,
  highIncidenceAreas: []
});
const orderList = ref([]);
const orderTypeDict = ref([]);
const areaDict = ref([]);
const completeRateTrend = ref({xAxis: [], series: []});
const highIncidenceData = ref({reason: '', suggestion: ''});

// 筛选参数
const filterParams = ref({
  matterType: [],
  area: [],
  matterStatus: '',
  timeRange: ''
});

// 弹窗相关
const orderDetailVisible = ref(false);
const dispatchVisible = ref(false);
const highIncidenceAnalysisVisible = ref(false);
const currentOrder = ref(null);
const currentHighArea = ref('');
const enforcementOrderId = ref('');
const dispatchTime = ref('');

// 派单表单
const dispatchForm = ref({
  enforcer: '',
  expectTime: '',
  requirement: ''
});

// 表单校验
const dispatchRules = ref({
  enforcer: [{required: true, message: '请选择执法人员', trigger: 'change'}],
  expectTime: [{required: true, message: '请选择预计办结时间', trigger: 'change'}]
});
const dispatchFormRef = ref(null);

// 计算属性
// 平均办结率
const avgCompleteRate = computed(() => {
  const rates = completeRateTrend.value.series.find(item => item.name === '办结率(%)')?.data || [];
  const validRates = rates.filter(r => r > 0);
  return validRates.length > 0
    ? Math.round((validRates.reduce((a, b) => a + b, 0) / validRates.length) * 10) / 10
    : 0;
});

// 筛选后的事件列表
const filteredOrderList = computed(() => {
  return orderList.value.filter(item => {
    // 事件类型筛选
    const matchType = filterParams.value.matterType.length === 0 || filterParams.value.matterType.includes(item.matter_type);
    // 区域筛选（级联：省/市/区/街道）
    let matchArea = true;
    if (filterParams.value.area.length > 0) {
      const areaPath = filterParams.value.area.join('/');
      matchArea = item.incident_location.includes(areaPath) || item.grid_code.includes(areaPath.replace(/\//g, '-'));
    }
    // 状态筛选
    const matchStatus = !filterParams.value.matterStatus || item.matter_status === filterParams.value.matterStatus;
    // 时间筛选
    let matchTime = true;
    if (filterParams.value.timeRange) {
      const today = new Date();
      const reportDate = new Date(item.report_time);
      const todayStr = today.toLocaleDateString();
      const weekStart = new Date(today.setDate(today.getDate() - today.getDay() + 1)).toLocaleDateString();
      const monthStart = new Date(today.getFullYear(), today.getMonth(), 1).toLocaleDateString();

      switch (filterParams.value.timeRange) {
        case 'today':
          matchTime = reportDate.toLocaleDateString() === todayStr;
          break;
        case 'week':
          matchTime = reportDate.toLocaleDateString() >= weekStart;
          break;
        case 'month':
          matchTime = reportDate.toLocaleDateString() >= monthStart;
          break;
        default:
          matchTime = true;
      }
    }
    return matchType && matchArea && matchStatus && matchTime;
  });
});

// 未办结事件列表
const unCompletedOrderList = computed(() => {
  return filteredOrderList.value.filter(item => item.matter_status !== '已办结');
});

// 获取事件类型颜色
const getTypeColor = (type) => {
  const typeItem = orderTypeDict.value.find(item => item.value === type);
  return typeItem?.color || '#666';
};

// 页面操作方法
// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('市容秩序事件数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 返回总览
const handleBack = () => {
  router.push('/overview/urbanmanagement/dpzl');
};

// 打开高发区域分析
const openHighIncidenceAnalysis = async () => {
  currentHighArea.value = '高发区域整体分析';
  highIncidenceData.value = await fetchHighIncidenceAnalysis('默认');
  highIncidenceAnalysisVisible.value = true;
};

// 处理类型钻取
const handleTypeDrill = (type) => {
  filterParams.value.matterType = [type];
};

// 处理列表行点击
const handleTaskRowClick = (row) => {
  currentOrder.value = row;
  enforcementOrderId.value = '';
  dispatchTime.value = '';
  orderDetailVisible.value = true;
};

// 处理派单
const handleDispatch = (row) => {
  currentOrder.value = row;
  dispatchForm.value = {enforcer: '', expectTime: '', requirement: ''};
  dispatchVisible.value = true;
};

// 提交派单
const submitDispatch = async () => {
  if (!dispatchFormRef.value) return;
  try {
    await dispatchFormRef.value.validate();
    const res = await createEnforcementWorkOrder({
      mng_matter_id: currentOrder.value.mng_matter_id,
      enforcer: dispatchForm.value.enforcer,
      expect_time: dispatchForm.value.expectTime,
      requirement: dispatchForm.value.requirement
    });
    if (res.code === 200) {
      enforcementOrderId.value = res.data.eo_id;
      dispatchTime.value = new Date().toLocaleString();
      ElMessage.success('执法工单派单成功！');
      dispatchVisible.value = false;

      // 更新事件状态
      const orderIndex = orderList.value.findIndex(item => item.mng_matter_id === currentOrder.value.mng_matter_id);
      if (orderIndex > -1) {
        orderList.value[orderIndex].matter_status = '处置中';
        orderList.value[orderIndex].responsible_person = dispatchForm.value.enforcer;
        orderList.value[orderIndex].update_time = new Date().toLocaleString();
      }
      await fetchOverviewData();
    }
  } catch (error) {
    console.error('派单失败：', error);
    ElMessage.error('派单失败，请重试！');
  }
};

// 标记事件办结
const handleCompleteOrder = async () => {
  try {
    await ElMessageBox.confirm('确认该事件已办结？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });
    const res = await markOrderCompleted(currentOrder.value.mng_matter_id);
    if (res.code === 200) {
      const orderIndex = orderList.value.findIndex(item => item.mng_matter_id === currentOrder.value.mng_matter_id);
      if (orderIndex > -1) {
        // 办结动效：先标记状态，再触发渐隐动画
        orderList.value[orderIndex].matter_status = '已办结';
        orderList.value[orderIndex].update_time = new Date().toLocaleString();
        // 通知地图组件隐藏该标记
        if (instance.refs.mapComponent) {
          instance.refs.mapComponent.hideMarker(currentOrder.value.mng_matter_id);
        }
      }
      ElMessage.success('事件已办结！');
      orderDetailVisible.value = false;
      await fetchOverviewData();
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('标记办结失败：', error);
      ElMessage.error('标记办结失败，请重试！');
    }
  }
};

// 导出分析报告
const exportAnalysisReport = () => {
  ElMessage.success('分析报告导出中，请稍后...');

  // 模拟生成报告内容
  const reportContent = `
  高发区域分析报告
  ====================
  区域名称：${currentHighArea.value}
  分析时间：${new Date().toLocaleString()}
  ====================
  一、高发原因分析：
  ${highIncidenceData.value.reason || '暂无相关分析数据'}

  二、整改建议：
  ${highIncidenceData.value.suggestion || '暂无相关整改建议'}
  ====================
  导出时间：${new Date().toLocaleString()}
    `.trim();

  // 原生下载逻辑（替代ElDownload）
  setTimeout(() => {
    const blob = new Blob([reportContent], {type: 'text/plain;charset=utf-8'});
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `${currentHighArea.value}_分析报告_${new Date().getTime()}.txt`;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url); // 释放URL对象，避免内存泄漏

    ElMessage.success('分析报告导出成功！');
  }, 1000);
};

// 重置筛选
const resetFilter = () => {
  filterParams.value = {
    matterType: [],
    area: [],
    matterStatus: '',
    timeRange: ''
  };
};

// 数据请求方法
// 获取概览数据
const fetchOverviewData = async () => {
  overviewData.value = await fetchOrderOverview();
};

// 获取事件数据
const fetchOrderData = async () => {
  orderList.value = await fetchOrderSpatial();
};

// 获取类型字典
const fetchTypeDict = async () => {
  orderTypeDict.value = await fetchOrderTypeDict();
};

// 获取区域字典
const fetchAreaDictData = async () => {
  areaDict.value = await fetchAreaDict();
};

// 获取趋势数据
const fetchTrendData = async () => {
  completeRateTrend.value = await fetchCompleteRateTrend();
};

// 初始化数据
const initData = async () => {
  await fetchOverviewData();
  await fetchOrderData();
  await fetchTypeDict();
  await fetchAreaDictData();
  await fetchTrendData();
};

// 监听筛选参数变化
watch(filterParams, () => {
  // 筛选后更新概览数据
  const filtered = filteredOrderList.value;
  overviewData.value.totalRptCount = filtered.length;
  overviewData.value.completeRate = filtered.length > 0
    ? Math.round((filtered.filter(item => item.matter_status === '已办结').length / filtered.length) * 100 * 10) / 10
    : 0;
  overviewData.value.timeoutCount = filtered.filter(item => item.is_timeout).length;
}, {deep: true});

// 生命周期
onMounted(async () => {
  await initData();
  // 30分钟自动刷新数据
  refreshTimer = setInterval(refreshData, 30 * 60 * 1000);
});

onUnmounted(() => {
  // 清除定时器
  if (refreshTimer) clearInterval(refreshTimer);
});
</script>

<style lang="scss" scoped>
// 全局样式
.cityscape-order-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg") no-repeat center center;
  background-size: cover;
  color: #ffffff;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;

  &::after {
    content: '';
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(0, 47, 75, 0.6) 0%, rgba(0, 102, 153, 0.5) 100%);
    z-index: -1;
  }
}

// 页头样式
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(0, 198, 255, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444);
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
    font-weight: 700;
    letter-spacing: 0.1vw;
    text-shadow: 0 0 8px rgba(0, 198, 255, 0.2);

    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
      margin-right: 0.5vw;
      filter: drop-shadow(0 0 3px rgba(0, 198, 255, 0.5));
    }
  }

  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }

  .el-button {
    --el-button-text-color: #ffffff;
    --el-button-bg-color: rgba(0, 198, 255, 0.2);
    --el-button-border-color: #9333EA;
    --el-button-hover-bg-color: rgba(147, 51, 234, 0.4);
    --el-button-hover-border-color: #FACC15;
    --el-button-active-bg-color: rgba(250, 204, 21, 0.3);
    --el-button-active-border-color: #FACC15;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
    transition: all 0.3s ease;
  }
}

// 主体内容
.main-content {
  display: flex;
  gap: 1.2vw;
  height: calc(100vh - 12vh);

  // 左侧50%：指标卡片（上）+地图（下）
  .left-area {
    flex: 5;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    // 指标卡片容器（完全保留，无任何改动）
    .stat-card-container {
      display: flex;
      gap: 1vw;
      height: 20%;

      .stat-card {
        flex: 1;
        background: rgba(0, 68, 102, 0.8);
        border-radius: 8px;
        padding: 0.8vw 1vw;
        border: 1px solid rgba(0, 198, 255, 0.3);
        box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
        transition: all 0.3s ease;
        box-sizing: border-box;
        position: relative;
        overflow: hidden;

        &::after {
          content: '';
          position: absolute;
          top: 0;
          right: 0;
          width: 40%;
          height: 100%;
          background: linear-gradient(45deg, transparent, rgba(0, 198, 255, 0.1));
          z-index: 0;
        }

        &:hover {
          transform: translateY(-3px);
          box-shadow: 0 0 20px rgba(147, 51, 234, 0.3);
          border-color: transparent;
        }

        .stat-title {
          font-size: 1vw;
          color: #67e8f9;
          margin-bottom: 0.5vw;
          display: flex;
          align-items: center;
          position: relative;
          z-index: 1;

          &::before {
            content: '';
            display: inline-block;
            width: 0.7vw;
            height: 1.4vh;
            border-radius: 50%;
            margin-right: 0.8vw;
          }
        }

        .stat-value {
          font-size: 1.8vw;
          font-weight: 700;
          margin-bottom: 0.5vh;
          line-height: 1.2;
          position: relative;
          z-index: 1;
          text-shadow: 0 0 8px rgba(0, 198, 255, 0.2);
        }

        .stat-desc {
          font-size: 0.7vw;
          color: #67e8f9;
          line-height: 1.4;
          position: relative;
          z-index: 1;
        }

        // 不同卡片配色
        &.total-card {
          .stat-title::before {
            background: #FACC15;
          }

          .stat-title {
            color: #fbb866;
          }

          .stat-value {
            background: linear-gradient(90deg, #FACC15, #fbb866);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
        }

        &.rate-card {
          .stat-title::before {
            background: #9333EA;
          }

          .stat-title {
            color: #c4b5fd;
          }

          .stat-value {
            background: linear-gradient(90deg, #9333EA, #c4b5fd);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }

          &.danger-rate {
            .stat-value {
              background: linear-gradient(90deg, #EF4444, #fca5a5);
              -webkit-background-clip: text;
              background-clip: text;
              color: transparent;
            }
          }
        }

        &.high-area-card {
          .stat-title::before {
            background: #EF4444;
          }

          .stat-title {
            color: #fca5a5;
          }

          .high-area-list {
            position: relative;
            z-index: 1;
            margin-top: 0.5vw;

            .high-area-item {
              display: flex;
              align-items: center;
              padding: 0.3vw 0;
              border-left: 3px solid #666;
              padding-left: 0.5vw;
              margin-bottom: 0.2vw;
              cursor: pointer;
              transition: all 0.2s ease;

              &:hover {
                background: rgba(255, 255, 255, 0.1);
                border-radius: 4px;
              }

              .area-rank {
                font-size: 0.8vw;
                font-weight: bold;
                color: #FACC15;
                width: 1vw;
              }

              .area-name {
                flex: 1;
                font-size: 0.8vw;
              }

              .area-count {
                font-size: 0.8vw;
                color: #EF4444;
                font-weight: bold;
              }
            }
          }
        }
      }
    }

    // 地图容器（布满面板）
    .map-container {
      flex: 1;
      position: relative;
      padding: 0;
      height: 100%;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #00c6ff, rgba(0, 198, 255, 0.6));
        z-index: 1;
      }

      .panel-header {
        padding: 1vh 1.2vw;
        border-bottom: 1px solid rgba(0, 198, 255, 0.3);
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;
        z-index: 2;
        height: 6%;
        box-sizing: border-box;

        h3 {
          margin: 0;
          font-size: 0.9vw;
          font-weight: 600;
          color: #00c6ff;
          text-shadow: 0 0 5px rgba(0, 198, 255, 0.3);
        }
      }

      // 地图主体（100%高度）
      .map-body {
        width: 100%;
        height: 94%;
        padding: 0 !important;
        margin: 0 !important;
        overflow: hidden;

        // 确保地图组件填满容器
        #municipalMap {
          width: 100% !important;
          height: 100% !important;
        }
      }
    }
  }

  // 右侧50%：趋势图表（上）+筛选+列表（下）
  .right-area {
    flex: 5;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    // 趋势图表
    .trend-chart-panel {
      height: 25%;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #14b8a6, rgba(20, 184, 166, 0.6));
        z-index: 1;
      }

      .panel-header h3 {
        color: #14b8a6;
      }

      .panel-body {
        height: calc(100% - 2vh);
      }
    }

    // 筛选+列表容器
    .filter-list-container {
      display: flex;
      flex-direction: column;
      gap: 1vw;
      height: 73%;

      // 筛选栏（严格对齐参考代码样式）
      .filter-bar.list-filter-bar {
        display: flex;
        gap: 0.8vw;
        align-items: center;
        padding: 0.8vw 1.2vw;
        background: rgba(0, 68, 102, 0.5);
        border-radius: 8px;
        border: 1px solid rgba(0, 198, 255, 0.3);
        width: 100%;
        flex-direction: row;
        flex-wrap: nowrap;
        overflow: hidden;

        &::before {
          content: '';
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 3px;
          background: linear-gradient(90deg, #f7931e, rgba(247, 147, 30, 0.6));
          z-index: 1;
        }

        .el-select, .el-cascader {
          font-size: 0.8vw;
          width: calc(20% - 0.8vw);
          max-width: 12vw;
          --el-select-text-color: #ffffff;
          --el-select-border-color: #f7931e;
          --el-select-hover-border-color: #ec4899;
          --el-select-dropdown-bg-color: rgba(0, 68, 102, 0.5);
          --el-select-option-hover-bg-color: rgba(247, 147, 30, 0.2);
        }

        .el-button {
          font-size: 0.8vw;
        }
      }

      // 列表容器
      .task-list-container {
        flex: 1;
        background: rgba(0, 47, 75, 0.4);
        border-radius: 8px;
        border: 1px solid rgba(0, 198, 255, 0.3);
        overflow-y: auto;
        position: relative;
        height: 83%;

        &::before {
          content: '';
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 3px;
          background: linear-gradient(90deg, #ec4899, rgba(236, 72, 153, 0.6));
          z-index: 1;
        }

        .panel-header h3 {
          color: #ec4899;
        }

        .table-scroll {
          overflow-y: auto;
          height: calc(100% - 2vh - 1vh);

          &::-webkit-scrollbar {
            width: 6px;
            height: 6px;
          }

          &::-webkit-scrollbar-track {
            background: rgba(0, 68, 102, 0.8);
            border-radius: 3px;
          }

          &::-webkit-scrollbar-thumb {
            background: linear-gradient(180deg, #9333EA, #FACC15);
            border-radius: 3px;
          }

          &::-webkit-scrollbar-thumb:hover {
            background: linear-gradient(180deg, #EF4444, #9333EA);
          }
        }

        .timeout {
          color: #EF4444 !important;
          font-weight: 500;
          text-shadow: 0 0 3px rgba(239, 68, 68, 0.3);
        }

        .task-list-enter-active, .task-list-leave-active {
          transition: all 0.5s ease;
        }

        .task-list-enter-from, .task-list-leave-to {
          opacity: 0;
          transform: translateY(30px);
        }
      }
    }
  }
}

// 通用面板样式
.panel {
  position: relative;
  border: 1px solid rgba(0, 198, 255, 0.3);
  background: rgba(255, 204, 211, 0.05);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
  overflow: hidden;

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(0, 198, 255, 0.3);
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    z-index: 2;

    h3 {
      margin: 0;
      font-size: 0.9vw;
      font-weight: 600;
      display: flex;
      align-items: center;
      text-shadow: 0 0 5px rgba(0, 198, 255, 0.3);
    }

    .header-actions {
      display: flex;
      align-items: center;
    }
  }

  .panel-body {
    flex: 1;
    padding: 0.5vw;
    overflow: hidden;
    position: relative;
    z-index: 2;
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(0, 198, 255, 0.3);
    position: relative;
    z-index: 2;
  }
}

// 全屏按钮样式
.panel-fullscreen-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 4px;
  transition: all 0.2s ease;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background: rgba(255, 255, 255, 0.1);
    transform: scale(1.1);
  }
}

// 表格样式（替换为参考代码结构 + 新配色）
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(26, 16, 46, 0.4);
  border-color: rgba(147, 51, 234, 0.3);

  // 表头样式
  th {
    background: linear-gradient(90deg, rgba(26, 16, 46, 0.8), rgba(41, 24, 71, 0.8)) !important;
    color: #FACC15;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(147, 51, 234, 0.3);
    font-weight: 500;
  }

  // 单元格样式
  td {
    background-color: transparent;
    color: #C8B69C; // 非白色，符合之前要求
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(147, 51, 234, 0.3);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  // 行样式
  .el-table__row {
    background-color: rgba(26, 16, 46, 0.3);
    &:nth-child(even) {
      background-color: rgba(41, 24, 71, 0.3);
    }
    // 行悬浮效果
    &:hover > td {
      background-color: rgba(147, 51, 234, 0.15) !important;
      color: #FACC15;
    }
  }

  // 当前行样式
  .el-table__row--current > td {
    background-color: rgba(236, 72, 153, 0.1) !important;
    color: #ec4899;
  }

  // 空表格文本
  .el-table__empty-text {
    color: #c4b5fd;
    background-color: rgba(26, 16, 46, 0.4);
  }

  // 表格边框伪元素
  &::before {
    background-color: rgba(147, 51, 234, 0.3);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }

  // 单元格内边距
  .el-table__cell {
    padding: 1vh 0.8vw !important;
    font-size: 0.8vw;
  }

  // 标签样式
  .el-tag {
    font-size: 0.75vw;
    padding: 0.2vh 0.6vw;
    border-radius: 12px;
    font-weight: 500;
  }
}

// 弹窗样式
.order-detail-dialog, .dispatch-dialog, .high-incidence-dialog {
  --el-dialog-bg-color: rgba(0, 47, 75, 0.8);
  --el-dialog-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-title-color: #FACC15;
  --el-dialog-text-color: #ffffff;
  --el-dialog-header-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-footer-border-color: rgba(0, 198, 255, 0.3);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 198, 255, 0.3), 0 0 40px rgba(147, 51, 234, 0.1);

  .el-dialog__header {
    border-bottom: 1px solid rgba(0, 198, 255, 0.3);
    position: relative;

    &::after {
      content: '';
      position: absolute;
      bottom: -1px;
      left: 0;
      width: 100%;
      height: 1px;
      background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444, transparent);
    }
  }

  .order-detail {
    .detail-section {
      margin-bottom: 24px;

      h3 {
        font-size: 16px;
        color: #FACC15;
        margin-bottom: 12px;
        padding-bottom: 6px;
        border-bottom: 1px solid rgba(0, 198, 255, 0.3);
        font-weight: 600;
        background: linear-gradient(90deg, #FACC15, #9333EA);
        -webkit-background-clip: text;
        background-clip: text;
      }

      h4 {
        font-size: 14px;
        color: #9333EA;
        margin: 12px 0 8px;
      }
    }

    .scene-img-container {
      margin-top: 12px;

      .scene-img {
        width: 100%;
        max-height: 300px;
        object-fit: contain;
        border-radius: 8px;
        border: 1px solid rgba(0, 198, 255, 0.3);
      }
    }

    .el-descriptions {
      --el-descriptions-bg-color: transparent;
      --el-descriptions-border-color: rgba(0, 198, 255, 0.3);
      --el-descriptions-content-color: #ffffff;
      font-size: 0.9vw;

      .el-descriptions__label {
        color: #00c6ff;
        font-weight: 500;
      }
    }
  }

  .analysis-content {
    .analysis-section {
      margin-bottom: 20px;

      h3 {
        font-size: 16px;
        color: #EF4444;
        margin-bottom: 10px;
        padding-bottom: 5px;
        border-bottom: 1px solid rgba(239, 68, 68, 0.3);
      }

      .analysis-text {
        font-size: 14px;
        line-height: 1.6;
        color: #000000;
        white-space: pre-line;
      }
    }
  }

  .el-form {
    .el-form-item {
      label {
        color: #ffffff;
      }

      .el-select {
        --el-select-border-color: #9333EA;
        --el-select-hover-border-color: #FACC15;
      }

      .el-date-picker {
        --el-input-border-color: #14b8a6;
        --el-input-hover-border-color: #00c6ff;
      }
    }
  }

  .el-dialog__footer {
    border-top: 1px solid rgba(0, 198, 255, 0.3);
    padding-top: 15px;
  }
}

// 标签样式
.el-tag--danger {
  --el-tag-bg-color: rgba(239, 68, 68, 0.2);
  --el-tag-border-color: #EF4444;
  --el-tag-text-color: #EF4444;
}

.el-tag--info {
  --el-tag-bg-color: rgba(0, 198, 255, 0.2);
  --el-tag-border-color: #00c6ff;
  --el-tag-text-color: #00c6ff;
}

.el-tag--success {
  --el-tag-bg-color: rgba(56, 176, 0, 0.2);
  --el-tag-border-color: #38b000;
  --el-tag-text-color: #38b000;
}
</style>
