<template>
  <div class="page-container" ref="pageContainerRef">
    <div class="header-box">
      <button class="back-button" @click="handleBack">
        <el-icon color="#00ccff" :size="`${1.2}vw`">
          <ArrowLeft />
        </el-icon>
      </button>
      <span class="head-name">XXXX-全局态势总览</span>
      <button class="fullScreenBut" @click="clickFullscreen">
        <el-icon color="#00ccff" :size="`${1.2}vw`">
          <FullScreen />
        </el-icon>
      </button>
    </div>

    <div class="mainbox">
      <div class="left">
        <div class="panel left_top">
          <div class="header-actions">
            <div class="actions-left">
              <p>核心指标看板</p>
            </div>
            <div class="actions-right">
              <div class="filter-group">
                <el-select
                  v-model="coreFilterParams.stat_cycle"
                  size="small"
                  style="width: 5vw;"
                  class="filter-select"
                  @change="fetchCoreData"
                >
                  <el-option label="日统计" value="DAY"/>
                  <el-option label="周统计" value="WEEK"/>
                  <el-option label="月统计" value="MONTH"/>
                  <el-option label="季统计" value="QUARTER"/>
                  <el-option label="年统计" value="YEAR"/>
                </el-select>

                <el-select
                  v-model="coreFilterParams.region_code"
                  size="small"
                  style="width: 5vw;"
                  class="filter-select"
                  @change="fetchCoreData"
                  placeholder="请选择区域"
                >
                  <el-option
                    v-for="item in regionOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </div>
            </div>
          </div>

          <div class="indicator-cards">
            <div class="indicator-card card-total" :class="getGroupStatusClass('total_count')">
              <div class="indicator-title">设施总量</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ statData.total_count || 0 }}
                    <div class="unit">个</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="indicator-card card-abnormal" :class="getGroupStatusClass('abnormal_count')">
              <div class="indicator-title">异常数</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ statData.abnormal_count || 0 }}
                    <div class="unit">个</div>
                  </div>
                </div>
              </div>
            </div>

            <!-- 达标率卡片：脉冲动画持续到指标恢复 -->
            <div
              class="indicator-card card-qualify"
              :class="[getGroupStatusClass('qualify_rate'), { 'pulse-danger': qualifyWarnPulse }]"
            >
              <div class="indicator-title">达标率</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ (statData.qualify_rate * 100 || 0).toFixed(2) }}
                    <div class="unit">%</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="indicator-card card-close" :class="getGroupStatusClass('close_rate')">
              <div class="indicator-title">处置闭环率</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ (statData.close_rate * 100 || 0).toFixed(2) }}
                    <div class="unit">%</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="indicator-card card-yoy" :class="getGroupStatusClass('yoy_growth')">
              <div class="indicator-title">同比</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ (statData.yoy_growth * 100 || 0).toFixed(2) }}
                    <div class="unit">%</div>
                  </div>
                </div>
              </div>
            </div>

            <div class="indicator-card card-mom" :class="getGroupStatusClass('mom_growth')">
              <div class="indicator-title">环比</div>
              <div class="sub-indicators">
                <div class="sub-indicator-item">
                  <div class="sub-indicator-value">
                    {{ (statData.mom_growth * 100 || 0).toFixed(2) }}
                    <div class="unit">%</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel left_bottom" ref="object">
          <div class="header-actions">
            <div class="actions-left">
              <p>核心对象分布视图</p>
            </div>
            <div class="actions-right">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('object')">
                <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <div class="middle">
        <!-- 全域数据地图面板 -->
        <div class="panel middle_top" style="min-width: 3vw;" ref="map">
          <div class="header-actions">
            <div class="actions-left">
              <p>全域数据地图</p>
            </div>
            <div class="actions-right">
              <div class="map-filter-group">
                <el-select
                  v-model="mapFilterParams.region_code"
                  placeholder="行政区域"
                  size="small"
                  class="map-filter-select"
                  @change="fetchMapData"
                >
                  <el-option
                    v-for="item in regionOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>

                <el-select
                  v-model="mapFilterParams.facility_type"
                  placeholder="设施类型"
                  size="small"
                  class="map-filter-select"
                  @change="fetchMapData"
                >
                  <el-option label="道路设施" value="road"/>
                  <el-option label="桥梁设施" value="bridge"/>
                  <el-option label="排水设施" value="drainage"/>
                  <el-option label="照明设施" value="lighting"/>
                  <el-option label="环卫设施" value="sanitation"/>
                </el-select>

                <el-select
                  v-model="mapFilterParams.status"
                  placeholder="设施状态"
                  size="small"
                  class="map-filter-select"
                  @change="fetchMapData"
                >
                  <el-option label="正常" value="正常"/>
                  <el-option label="异常" value="异常"/>
                  <el-option label="维护" value="维护"/>
                </el-select>

                <button
                  class="map-filter-reset-btn"
                  @click="resetMapFilter"
                >
                  重置
                </button>
              </div>

              <!-- 环绕控制按钮 -->
              <button
                class="orbit-control-btn"
                @click="mapCommonRef?.toggleOrbitAnimation()"
              >
                {{ mapCommonRef?.orbitStatus?.playing ? '暂停环绕' : '开始环绕' }}
              </button>

              <!-- 面板全屏按钮 -->
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
                <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
              </button>
            </div>
          </div>

          <!-- 地图组件 -->
          <map-common
            ref="mapCommonRef"
            idName="chinaEcharts"
            :geometriesArray="geometriesArray"
          />
          <div class="panel-footer"></div>
        </div>

        <div class="panel middle_bottom" ref="trend">
          <div class="header-actions">
            <div class="actions-left">
              <p>全局态势趋势分析</p>
            </div>
            <div class="actions-right">
              <!-- 保留：区域筛选框 -->
              <el-select
                v-model="trendFilterParams.region_code"
                size="small"
                style="width: 5vw;"
                class="trend-filter-select"
                @change="fetchTrendData"
                placeholder="选择区域"
              >
                <el-option
                  v-for="item in regionOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>

              <button
                class="export-btn"
                @click="exportTrendExcel"
                :disabled="!trendData.xAxis.length"
              >
                导出Excel
              </button>

              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('trend')">
                <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
              </button>
            </div>
          </div>

          <!-- 折线图组件 -->
          <div class="trend-chart-box" style="width: 100%; height: calc(100% - 40px);">
            <chart-component
              :data="chartData"
              :yAxisName="''"
              :showGrid="true"
              :showArea="false"
              :baseFontScale="1"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <div class="right">
        <div class="panel right_top" ref="scene">
          <div class="header-actions">
            <div class="actions-left">
              <p>行业特色态势聚合</p>
            </div>
            <div class="actions-right">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('scene')">
                <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel right_bottom" ref="element">
          <div class="header-actions">
            <div class="actions-left">
              <p>核心要素运行监测</p>
            </div>
            <div class="actions-right">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('element')">
                <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </div>
    <div class="footer-box">帮助文档、异常反馈入口</div>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance, onMounted, watch, computed } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
import { ArrowLeft, FullScreen } from "@element-plus/icons-vue";
import screenFull from "screenfull";
import * as XLSX from 'xlsx';
import MapCommon from "@/views/industry/MapCommon.vue";
import ChartComponent from "@/views/industry/ChartLine.vue";
import {
  fetchUrbanManagementCoreIndicators,
  fetchUrbanConstructionGeometries,
  fetchRegionDict,
  fetchCoreIndicatorTrend,
  checkContinuousAbnormal
} from '@/api/industry/qjtszlmb.js';

const router = useRouter();
const pageContainerRef = ref(null);
const instance = getCurrentInstance();
const mapCommonRef = ref(null);
const geometriesArray = ref([]);
const statData = ref({});
// 新增：达标率预警脉冲动画控制
const qualifyWarnPulse = ref(false);

// 行政区域选项
const regionOptions = ref([]);

// 核心指标看板筛选参数
const coreFilterParams = ref({
  stat_cycle: 'DAY',
  region_code: ''
});

// 全域数据地图筛选参数
const mapFilterParams = ref({
  region_code: '',
  facility_type: '',
  status: ''
});

// 简化：趋势分析筛选参数（仅保留区域筛选）
const trendFilterParams = ref({
  region_code: '' // 移除indicators参数
});

// 趋势数据和图表数据
const trendData = ref({
  xAxis: [],
  series: [],
  abnormalPeriods: [],
  predictXAxis: []
});
const chartData = ref({
  xAxis: [],
  series: []
});

// 核心指标预警逻辑
const getGroupStatusClass = (field) => {
  if (!statData.value[field]) return 'normal';
  if (field === 'qualify_rate') {
    const warnThreshold = statData.value.qualify_warn_threshold || 0.9;
    return statData.value[field] < warnThreshold ? 'danger' : 'normal';
  }
  return 'normal';
};

// 计算属性：判断达标率是否异常（用于实时监听）
const isQualifyRateAbnormal = computed(() => {
  if (statData.value.qualify_rate === undefined || statData.value.qualify_warn_threshold === undefined) {
    return false;
  }
  return statData.value.qualify_rate < statData.value.qualify_warn_threshold;
});

// 核心指标数据请求（修改：移除定时关闭，改为实时判断）
const fetchCoreData = async () => {
  try {
    const data = await fetchUrbanManagementCoreIndicators(coreFilterParams.value);
    statData.value = data;
    // 直接根据指标状态设置动画（无定时关闭）
    qualifyWarnPulse.value = isQualifyRateAbnormal.value;
  } catch (error) {
    console.error('获取核心指标数据失败:', error);
    ElMessage.error('核心指标数据加载失败，请刷新页面重试');
  }
};

// 地图数据请求
const fetchMapData = async () => {
  try {
    const data = await fetchUrbanConstructionGeometries(mapFilterParams.value);
    geometriesArray.value = data;
  } catch (error) {
    console.error('获取地图点位数据失败:', error);
    ElMessage.error('地图数据加载失败，请刷新页面重试');
  }
};

// 重置地图筛选参数
const resetMapFilter = () => {
  mapFilterParams.value = {
    region_code: '',
    facility_type: '',
    status: ''
  };
  fetchMapData();
  ElMessage.success('地图筛选条件已重置');
};

// 获取趋势数据（无需传indicators，后端返回全部核心指标）
const fetchTrendData = async () => {
  if (!trendFilterParams.value.region_code) {
    ElMessage.warning('请先选择区域');
    return;
  }
  try {
    // 仅传区域参数，后端返回全部核心指标的趋势数据
    const data = await fetchCoreIndicatorTrend(trendFilterParams.value);
    trendData.value = data;

    // 组装图表数据
    const fullXAxis = [...data.xAxis, ...data.predictXAxis];
    const fullSeries = data.series.map(seriesItem => {
      const fullData = [...seriesItem.data, ...seriesItem.predictData];
      return {
        name: seriesItem.name,
        type: 'line',
        data: fullData,
        lineStyle: {
          ...seriesItem.lineStyle,
          type: Array(fullData.length).fill('solid').map((v, i) =>
            i >= data.xAxis.length ? 'dashed' : v
          )
        },
        markArea: {
          data: data.abnormalPeriods.map(period => [
            {
              name: period.reason,
              xAxis: period.start
            },
            {
              xAxis: period.end
            }
          ]),
          itemStyle: {
            color: 'rgba(255, 0, 0, 0.1)'
          },
          emphasis: {
            label: {
              show: true,
              formatter: (params) => params.name
            },
            itemStyle: {color: 'rgba(255, 0, 0, 0.2)'}
          }
        }
      };
    });

    chartData.value = {
      xAxis: fullXAxis,
      series: fullSeries
    };

    checkAbnormalWarning();
  } catch (error) {
    console.error('获取趋势数据失败:', error);
    ElMessage.error('趋势数据加载失败，请重试');
  }
};

// 检查连续异常预警
const checkAbnormalWarning = async () => {
  const region = trendFilterParams.value.region_code;
  if (!region) return;

  const abnormalWarn = await checkContinuousAbnormal(region, 'abnormal_count', 30, 3);
  if (abnormalWarn.isTrigger) {
    ElMessageBox.warning(
      `【${regionOptions.value.find(item => item.value === region)?.label}】异常数连续3天超过30，当前值：${abnormalWarn.lastNDays.join(', ')}`,
      '预警提醒',
      {
        confirmButtonText: '确认',
        type: 'warning'
      }
    );
  }

  const qualifyWarn = await checkContinuousAbnormal(region, 'qualify_rate', 90, 3);
  if (qualifyWarn.isTrigger) {
    ElMessageBox.warning(
      `【${regionOptions.value.find(item => item.value === region)?.label}】达标率连续3天低于90%，当前值：${qualifyWarn.lastNDays.join(', ')}%`,
      '预警提醒',
      {
        confirmButtonText: '确认',
        type: 'warning'
      }
    );
  }
};

// 导出Excel
const exportTrendExcel = () => {
  if (!trendData.value.xAxis.length) return;

  const header = ['日期', ...trendData.value.series.map(s => s.name)];
  const rows = trendData.value.xAxis.map((date, idx) => {
    const row = [date];
    trendData.value.series.forEach(series => {
      row.push(series.data[idx]);
    });
    return row;
  });

  trendData.value.predictXAxis.forEach((date, idx) => {
    const row = [date + '(预判)'];
    trendData.value.series.forEach(series => {
      row.push(series.predictData[idx]);
    });
    rows.push(row);
  });

  const ws = XLSX.utils.aoa_to_sheet([header, ...rows]);
  const wb = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, '核心指标趋势');
  XLSX.writeFile(wb, `核心指标趋势_${new Date().toLocaleDateString()}.xlsx`);

  ElMessage.success('Excel导出成功');
};

// 全屏切换（页面级）
const clickFullscreen = () => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }
  const targetEl = pageContainerRef.value;
  screenFull.isFullscreen ? screenFull.exit() : screenFull.request(targetEl);
};

// 返回首页
const handleBack = () => {
  router.push('/');
};

// 面板全屏切换
const togglePanelFullscreen = (panelRefName) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }
  const panel = instance.refs[panelRefName];
  if (!panel) {
    ElMessage.error('未找到面板元素');
    return;
  }
  screenFull.isFullscreen ? screenFull.exit() : screenFull.request(panel);
};

// 监听达标率状态变化，实时控制动画（核心修改）
watch(isQualifyRateAbnormal, (newVal) => {
  qualifyWarnPulse.value = newVal;
}, { immediate: true, deep: true });

// 监听区域选项变化，加载趋势数据
watch(regionOptions, (newVal) => {
  if (newVal.length > 0) {
    trendFilterParams.value.region_code = newVal[0].value;
    fetchTrendData();
  }
}, { immediate: true });

// 页面挂载
onMounted(async () => {
  // 1. 获取行政区域字典
  const regionData = await fetchRegionDict();
  regionOptions.value = regionData;

  // 2. 设置核心指标区域默认值
  coreFilterParams.value.region_code = regionOptions.value[0]?.value || '';

  // 3. 加载核心指标和地图数据
  await Promise.all([
    fetchCoreData(),
    fetchMapData()
  ]);

  // 4. 加载趋势数据
  if (regionOptions.value.length > 0) {
    trendFilterParams.value.region_code = regionOptions.value[0].value;
    fetchTrendData();
  }

  // 模拟实时数据刷新（可选：每30秒刷新一次核心指标，模拟真实场景）
  setInterval(() => {
    fetchCoreData();
  }, 30000);
});
</script>

<style lang="scss" scoped>
// 原有样式完全保留，调整动画循环方式
.page-container {
  width: 100%;
  height: 100vh;
  overflow-x: auto;
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  box-sizing: border-box;
}

.header-box {
  width: 100%;
  height: 10vh;
  position: relative;
  background: url("@/assets/chart/images/head_bg.png") no-repeat;
  background-size: 100% 100%;
  color: #00ccff;
  font-size: 2.1vw;
  font-weight: bold;

  .head-name {
    display: inline-block;
    line-height: 9vh;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    white-space: nowrap;
  }
}

.panel {
  position: relative;
  height: 100%;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
  padding: 0.5vw;
}

.panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 1.2vh;
  height: 1.2vh;
  border-top: 0.3vh solid #02a6b5;
  border-left: 0.3vh solid #02a6b5;
}

.panel::after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  width: 1.2vh;
  height: 1.2vh;
  border-top: 0.3vh solid #02a6b5;
  border-right: 0.3vh solid #02a6b5;
}

.panel-footer {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 1vh;
}

.panel-footer::before {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 1.2vh;
  height: 1.2vh;
  border-bottom: 0.3vh solid #02a6b5;
  border-left: 0.3vh solid #02a6b5;
}

.panel-footer::after {
  content: '';
  position: absolute;
  bottom: 0;
  right: 0;
  width: 1.2vh;
  height: 1.2vh;
  border-bottom: 0.3vh solid #02a6b5;
  border-right: 0.3vh solid #02a6b5;
}

.fullScreenBut, .back-button {
  position: absolute;
  top: 0.5vw;
  cursor: pointer;
  transition: transform 0.2s;
  padding: 5px;
  background: none;
  border: none;
  display: flex;
  align-items: center;
  z-index: 1000;

  &:hover {
    transform: scale(1.05);
    background: rgba(0, 30, 60, 0.8);
    border-radius: 4px;
  }
}

.back-button {
  left: 1vw;
}

.fullScreenBut {
  right: 1vw;
}

.header-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  border-bottom: 1px solid rgba(0, 204, 255, 0.1);
  box-sizing: border-box;
  gap: 1vw;
  margin-bottom: 0.5vw;

  .actions-left p {
    margin: 0;
    color: #9fbdff;
    font-size: 0.85vw;
    font-weight: 600;
  }

  .actions-right {
    display: flex;
    align-items: center;
    gap: 0.8vw;
  }
}

.panel-fullscreen-btn {
  cursor: pointer;
  transition: transform 0.2s;
  padding: 5px;
  background: none;
  border: none;
  display: flex;
  align-items: center;
  z-index: 1000;

  &:hover {
    transform: scale(1.05);
    background: rgba(0, 30, 60, 0.8);
    border-radius: 4px;
  }
}

.mainbox {
  display: flex;
  margin: 0 auto;
  height: 84vh;
  box-sizing: border-box;
  gap: 0.6vw;
  width: 100%;
}

.left {
  flex: 2;
  display: flex;
  flex-direction: column;
  gap: 2%;
}

.left_top {
  height: 40%;
}

.left_bottom {
  height: 58%;
}

.middle {
  flex: 5;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  height: 100%;
  min-height: 0;
}

.middle_top {
  flex: 1;
  margin-bottom: 1%;
  overflow: visible !important;
  position: relative;
  padding: 0.2vw !important;
  height: 69%;
}

.middle_bottom {
  height: 30%;
}

.right {
  flex: 2;
  display: flex;
  flex-direction: column;
  gap: 2%;
}

.right_top {
  height: 40%;
}

.right_bottom {
  height: 58%;
}

.footer-box {
  height: 4vh;
  border: solid black 2px;
  box-sizing: border-box;
  margin-top: 1vh;
}

.filter-group {
  display: flex;
  align-items: center;
  gap: 0.5vw;

  .filter-select {
    color: #fff;
    background: rgba(0, 30, 60, 0.5);

    :deep(.el-input__wrapper) {
      background: transparent;
      border: none;
      box-shadow: none;
    }

    :deep(.el-input__placeholder) {
      color: #ccefff;
    }

    :deep(.el-select-dropdown) {
      background: rgba(0, 30, 60, 0.8);
      border: 1px solid #00ccff;

      .el-option {
        color: #fff;

        &:hover {
          background: rgba(0, 204, 255, 0.2);
        }

        &.selected {
          background: rgba(0, 204, 255, 0.3);
        }
      }
    }
  }
}

.indicator-cards {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 0.6vw;
  padding: 1vh 0.5vw;
  flex: 1;
}

.indicator-card {
  height: 100%;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
  position: relative;
  overflow: hidden;
  border: 1px solid transparent;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.25);
    border-color: rgba(255, 255, 255, 0.3);
  }

  .indicator-title {
    font-size: 0.8vw;
    font-weight: 600;
    margin-bottom: 1vh;
    color: #90ffc4;
    text-align: center;
    text-shadow: 0 1px 2px rgba(0, 0, 0, 0.3);
  }

  .sub-indicator-value {
    font-size: 1vw;
    font-weight: 700;
    color: #f0f9ff;
    line-height: 1.3;

    .unit {
      font-size: 0.7vw;
      color: #ccefff;
      margin-top: 1vh;
      text-align: right;
    }
  }
}

.card-total.normal {
  background: linear-gradient(135deg, rgba(0, 168, 255, 0.3) 30%, #00528a 100%);
}

.card-total.danger {
  background: linear-gradient(135deg, #ff4d4d 30%, #00528a 100%);
  border-top-color: #ff4d4d;
}

.card-abnormal.normal {
  background: linear-gradient(135deg, rgba(255, 153, 0, 0.3) 30%, #8a4400 100%);
}

.card-abnormal.danger {
  background: linear-gradient(135deg, #ff4d4d 30%, #8a4400 100%);
  border-top-color: #ff4d4d;
}

.card-qualify.normal {
  background: linear-gradient(135deg, rgba(139, 92, 246, 0.3) 30%, #4a2394 100%);
}

.card-qualify.danger {
  background: linear-gradient(135deg, #ff4d4d 30%, #4a2394 100%);
  border-top-color: #ff4d4d;
}

.card-close.normal {
  background: linear-gradient(135deg, rgba(255, 107, 107, 0.3) 30%, #8a2323 100%);
}

.card-close.danger {
  background: linear-gradient(135deg, #ff3333 30%, #8a2323 100%);
  border-top-color: #ff3333;
}

.card-yoy.normal {
  background: linear-gradient(135deg, rgba(0, 255, 213, 0.3) 30%, #008a78 100%);
}

.card-yoy.danger {
  background: linear-gradient(135deg, #ff4d4d 30%, #008a78 100%);
  border-top-color: #ff4d4d;
}

.card-mom.normal {
  background: linear-gradient(135deg, rgba(52, 211, 153, 0.3) 30%, #1a7848 100%);
}

.card-mom.danger {
  background: linear-gradient(135deg, #ff4d4d 30%, #1a7848 100%);
  border-top-color: #ff4d4d;
}

.map-filter-group {
  display: flex;
  align-items: center;
  gap: 0.5vw;

  .map-filter-select {
    width: 6vw;
    color: #fff;
    background: rgba(0, 30, 60, 0.5);

    :deep(.el-input__wrapper) {
      background: transparent;
      border: none;
      box-shadow: none;
    }

    :deep(.el-input__placeholder) {
      color: #ccefff;
    }

    :deep(.el-select-dropdown) {
      background: rgba(0, 30, 60, 0.8);
      border: 1px solid #00ccff;

      .el-option {
        color: #fff;

        &:hover {
          background: rgba(0, 204, 255, 0.2);
        }

        &.selected {
          background: rgba(0, 204, 255, 0.3);
        }
      }
    }
  }

  .map-filter-reset-btn {
    padding: 0.2vw 0.6vw;
    margin-right: 5px;
    line-height: 1.5vh;
    background: rgba(0, 30, 60, 0.5);
    color: #fff;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.8vw;
    transition: all 0.2s;

    &:hover {
      background: rgba(0, 40, 80, 0.9);
      transform: scale(1.05);
    }
  }
}

.orbit-control-btn {
  cursor: pointer;
  transition: transform 0.2s;
  padding: 0.2vw 0.6vw;
  line-height: 1.5vh;
  background: rgba(0, 30, 60, 0.8);
  border: 1px solid #00ccff;
  color: #00ccff;
  font-size: 0.8vw;
  border-radius: 4px;
  display: flex;
  align-items: center;
  z-index: 1000;

  &:hover {
    transform: scale(1.1);
    background: rgba(0, 40, 80, 0.9);
  }
}

.trend-filter-select {
  color: #fff;
  background: rgba(0, 30, 60, 0.5);

  :deep(.el-input__wrapper) {
    background: transparent;
    border: none;
    box-shadow: none;
  }

  :deep(.el-input__placeholder) {
    color: #ccefff;
  }

  :deep(.el-select-dropdown) {
    background: rgba(0, 30, 60, 0.8);
    border: 1px solid #00ccff;

    .el-option {
      color: #fff;

      &:hover {
        background: rgba(0, 204, 255, 0.2);
      }

      &.selected {
        background: rgba(0, 204, 255, 0.3);
      }
    }
  }
}

.export-btn {
  padding: 0.2vw 0.6vw;
  line-height: 1.5vh;
  background: rgba(0, 30, 60, 0.8);
  color: #00ccff;
  border: 1px solid #00ccff;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.8vw;
  transition: all 0.2s;

  &:hover {
    background: rgba(0, 40, 80, 0.9);
    transform: scale(1.05);
  }

  &:disabled {
    opacity: 0.5;
    cursor: not-allowed;
    transform: none;
  }
}

// 修改：动画改为持续循环（移除alternate，保留infinite）
@keyframes pulse-danger {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 77, 77, 0.7);
  }
  100% {
    box-shadow: 0 0 0 15px rgba(255, 77, 77, 0);
  }
}

.pulse-danger {
  animation: pulse-danger 1s infinite; /* 持续循环，无交替 */
}
</style>
