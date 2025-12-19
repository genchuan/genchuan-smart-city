<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="top_left" style="min-width: 2vw;">
          <!-- 文旅资源分布 -->
          <div class="panel resource-distribution-panel">
            <div class="panel-header">
              <h2>文旅资源分布</h2>
              <div class="header-actions">
                <el-button type="success" round size="small" @click="toggleView">
                  {{ currentView === 'charts' ? '显示TOP5' : '显示统计图' }}
                </el-button>
                <el-button type="success" round size="small" @click="showAllResources">查看全部资源</el-button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 图表组合：类型分布柱状图 + 状态占比饼图 -->
              <div class="charts-container" v-if="currentView === 'charts'">
                <div class="chart-item">
                  <h4>资源类型分布</h4>
                  <ChartBar
                    :data="typeDistributionData"
                    :xAxisName="'资源类型'"
                    :yAxisName="'数量'"
                  />
                </div>
                <div class="chart-item">
                  <h4>资源状态占比</h4>
                  <ChartPie
                    :data="statusDistributionData"
                    :nameField="'状态'"
                    :valueField="'数量'"
                  />
                </div>
              </div>

              <!-- 重点资源TOP5列表 -->
              <div class="top5-container" v-else>
                <h4>重点资源TOP5</h4>
                <div class="top5-list">
                  <div
                    v-for="(item, index) in top5Resources"
                    :key="item.resource_id"
                    class="top5-item"
                    @click="showResourceDetail(item)"
                  >
                    <div class="top5-rank">{{ index + 1 }}</div>
                    <div class="top5-info">
                      <div class="top5-name">{{ item.resource_name }}</div>
                      <div class="top5-meta">
                        <span>类型：{{ item.resource_type }}</span>
                        <span>国家等级：{{ item.ext1 || '未评级' }}</span>
                        <span class="hot-degree">热度：<span class="red-hot">{{ item.hot_degree }}</span></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel top_middle" style="min-width: 3vw;" ref="map">
          <div class="header-actions">
            <p>文旅全域数据地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <div class="top_right" style="min-width: 2vw;">
          <!-- 文旅客流总览 -->
          <div class="panel tourist-flow-panel" style="width: 100%" ref="touristFlowPanel">
            <div class="panel-header">
              <h2>文旅客流总览</h2>
              <div class="header-actions">
                <el-select v-model="flowAreaTypeFilter" placeholder="区域类型" style="width: 5vw" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="景区" value="景区" />
                  <el-option label="商圈" value="商圈" />
                  <el-option label="活动" value="活动" />
                </el-select>
                <el-select v-model="flowWarnFilter" placeholder="预警状态" style="width: 5vw" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="无预警" value="0" />
                  <el-option label="已预警" value="1" />
                </el-select>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('touristFlowPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <el-table
                :data="filteredTouristFlow"
                border
                size="small"
              >
                <el-table-column prop="area_name" label="区域名称" />
                <el-table-column prop="area_type" label="区域类型" />
                <el-table-column label="客流情况" min-width="180px">
                  <template #default="scope">
                    <div>
                      <span>当前: {{ scope.row.real_time_flow }}人</span>
                      <div class="param-threshold">
                        (容量: {{ scope.row.area_capacity }}人，占比: {{ (scope.row.real_time_flow / scope.row.area_capacity * 100).toFixed(1) }}%)
                      </div>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="warn_status" label="预警状态">
                  <template #default="scope">
                    <el-tag :type="scope.row.warn_status === '0' ? 'success' : 'danger'">
                      {{ scope.row.warn_status === '0' ? '无预警' : '已预警' }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="操作" min-width="180px">
                  <template #default="scope">
                    <el-button size="small" type="text" @click="showFlowDetail(scope.row)">详情</el-button>
                    <el-button size="small" type="text" v-if="scope.row.warn_status === '1'" @click="handleFlowWarning(scope.row)">处理预警</el-button>
                  </template>
                </el-table-column>
              </el-table>

              <div class="table-actions">
                <el-button size="small" type="primary" @click="exportFlowData">导出数据</el-button>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottom_left" style="min-width: 2vw;">
          <!-- 近期预警信息 -->
          <div class="panel warnings-panel" ref="warningsPanel">
            <div class="panel-header">
              <h2>近期预警信息</h2>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('warningsPanel')">
                  <el-icon color="#00ccff" size="16">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <dv-scroll-board :config="warningScrollConfig" style="width: 100%; height: 100%;" />
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_middle" style="min-width: 3vw;">
          <!-- 文旅核心指标 -->
          <div class="panel core-indicators-panel">
            <div class="panel-header">
              <h2>文旅核心指标</h2>
              <div class="header-actions">
                <el-select v-model="indicatorTimeRange" placeholder="选择时间范围" style="width: 8vw" size="small">
                  <el-option label="今日" value="today" />
                  <el-option label="本周" value="week" />
                  <el-option label="本月" value="month" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="indicator-cards">
                <div
                  :class="['indicator-card', getIndicatorStatusClass((coreIndicators[0] || {}).warn_status)]"
                  @click="showIndicatorDetail(coreIndicators[0])"
                >
                  <div class="indicator-title">
                    {{ (coreIndicators[0] || {}).index_type }}
                    <br/>
                    （{{ getIndicatorUnit((coreIndicators[0] || {}).index_type) }}）
                  </div>
                  <div class="indicator-value">
                    <span :data-value="(coreIndicators[0] || {}).stat_value" class="number-animate">
                      {{ (coreIndicators[0] || {}).stat_value }}
                    </span>
                  </div>
                </div>
                <div
                  :class="['indicator-card', getIndicatorStatusClass((coreIndicators[1] || {}).warn_status)]"
                  @click="showIndicatorDetail(coreIndicators[1])"
                >
                  <div class="indicator-title">
                    {{ (coreIndicators[1] || {}).index_type }}
                    <br/>
                    （{{ getIndicatorUnit((coreIndicators[1] || {}).index_type) }}）
                  </div>
                  <div class="indicator-value">
                    <span :data-value="(coreIndicators[1] || {}).stat_value" class="number-animate">
                      {{ (coreIndicators[1] || {}).stat_value }}
                    </span>
                  </div>
                </div>
                <div
                  :class="['indicator-card', getIndicatorStatusClass((coreIndicators[2] || {}).warn_status)]"
                  @click="showIndicatorDetail(coreIndicators[2])"
                >
                  <div class="indicator-title">
                    {{ (coreIndicators[2] || {}).index_type }}
                    <br/>
                    （{{ getIndicatorUnit((coreIndicators[2] || {}).index_type) }}）
                  </div>
                  <div class="indicator-value">
                    <span :data-value="(coreIndicators[2] || {}).stat_value" class="number-animate">
                      {{ (coreIndicators[2] || {}).stat_value }}
                    </span>
                  </div>
                </div>
                <div
                  :class="['indicator-card', getIndicatorStatusClass((coreIndicators[3] || {}).warn_status)]"
                  @click="showIndicatorDetail(coreIndicators[3])"
                >
                  <div class="indicator-title">
                    {{ (coreIndicators[3] || {}).index_type }}
                    <br/>
                    （{{ getIndicatorUnit((coreIndicators[3] || {}).index_type) }}）
                  </div>
                  <div class="indicator-value">
                    <span :data-value="(coreIndicators[3] || {}).stat_value" class="number-animate">
                      {{ (coreIndicators[3] || {}).stat_value }}
                    </span>
                  </div>
                </div>
                <div
                  :class="['indicator-card', getIndicatorStatusClass((coreIndicators[4] || {}).warn_status)]"
                  @click="showIndicatorDetail(coreIndicators[4])"
                >
                  <div class="indicator-title">
                    {{ (coreIndicators[4] || {}).index_type }}
                    <br/>
                    （{{ getIndicatorUnit((coreIndicators[4] || {}).index_type) }}）
                  </div>
                  <div class="indicator-value">
                    <span :data-value="(coreIndicators[4] || {}).stat_value" class="number-animate">
                      {{ (coreIndicators[4] || {}).stat_value }}
                    </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_right" style="min-width: 2vw;">
          <!-- 指标趋势图 -->
          <div class="panel indicator-trend-panel" ref="indicatorTrendPanel">
            <div class="panel-header">
              <h2>核心指标趋势</h2>
              <div class="header-actions">
                <el-select v-model="trendIndicatorId" placeholder="选择指标" size="small" @change="changeTrendIndicator">
                  <el-option
                    v-for="indicator in coreIndicators"
                    :key="indicator.index_id"
                    :label="indicator.index_type"
                    :value="indicator.index_id"
                  />
                </el-select>
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('indicatorTrendPanel')">
                  <el-icon color="#00ccff" size="16">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <div class="chart-container">
                <ChartLine :data="indicatorTrendData" :yAxisName="getIndicatorUnit(currentTrendIndicator?.index_type || '')" />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      当前时间：{{currentTime}}
    </div>

    <!-- 全部资源明细弹窗 -->
    <el-dialog v-model="allResourcesVisible" title="全部资源明细" width="800px">
      <el-table
        :data="filteredResources"
        border
        size="small"
        style="width: 100%;"
      >
        <el-table-column prop="resource_name" label="资源名称" />
        <el-table-column prop="resource_type" label="资源类型" />
        <el-table-column prop="resource_pos" label="资源位置" />
        <el-table-column prop="resource_status" label="资源状态">
          <template #default="scope">
            <el-tag :type="scope.row.resource_status === '0' ? 'success' : 'danger'">
              {{ scope.row.resource_status === '0' ? '正常' : '异常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="warn_status" label="预警状态">
          <template #default="scope">
            <el-tag :type="scope.row.warn_status === '0' ? 'success' : 'danger'">
              {{ scope.row.warn_status === '0' ? '无预警' : '已预警' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" type="text" @click="showResourceDetail(scope.row)">详情</el-button>
            <el-button size="small" type="text" v-if="scope.row.warn_status === '1'" @click="handleResourceWarning(scope.row)">处理预警</el-button>
          </template>
        </el-table-column>
      </el-table>
      <template #footer>
        <el-button size="small" type="primary" @click="exportResourceData">导出数据</el-button>
        <el-button size="small" @click="allResourcesVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 地图资源详情弹窗 -->
    <el-dialog v-model="resourceOnMapDetailVisible" :title="currentMapResource?.resource_name || '资源详情'" width="600px">
      <div class="resource-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="资源ID">{{ currentMapResource?.resource_id }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ currentMapResource?.resource_type }}</el-descriptions-item>
            <el-descriptions-item label="位置">{{ currentMapResource?.resource_pos }}</el-descriptions-item>
            <el-descriptions-item label="经纬度">
              {{ currentMapResource?.longitude }}, {{ currentMapResource?.latitude }}
            </el-descriptions-item>
            <el-descriptions-item label="资源等级">{{ currentMapResource?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="传感器型号">{{ currentMapResource?.ext2 }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>客流信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="实时客流">{{ currentMapResource?.real_time_flow }}人</el-descriptions-item>
            <el-descriptions-item label="最大容量">{{ currentMapResource?.max_capacity }}人</el-descriptions-item>
            <el-descriptions-item label="客流占比">
              {{ (currentMapResource?.real_time_flow / currentMapResource?.max_capacity * 100).toFixed(1) }}%
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentMapResource?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentMapResource?.warn_status === '1' ? '已预警' : '无预警' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="调度工单ID" v-if="currentMapResource?.dispatch_order_id">
              {{ currentMapResource?.dispatch_order_id }}
            </el-descriptions-item>
            <el-descriptions-item label="管理员ID">{{ currentMapResource?.admin_user_id }}</el-descriptions-item>
            <el-descriptions-item label="核查结果" v-if="currentMapResource?.verify_result">
              {{ currentMapResource?.verify_result }} ({{ currentMapResource?.verify_time }})
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <template v-if="currentMapResource?.warn_status === '1'">
          <div class="handle-section">
            <h3>预警处置</h3>
            <el-form>
              <el-form-item label="处置内容" required>
                <el-input type="textarea" v-model="handleContent" rows="3" />
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      <template #footer>
        <el-button @click="resourceOnMapDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="handleMapResourceWarning" v-if="currentMapResource?.warn_status === '1'">
          处理预警
        </el-button>
      </template>
    </el-dialog>

    <!-- 核心指标详情弹窗 -->
    <el-dialog v-model="indicatorDetailVisible" :title="currentIndicator?.index_type || '指标详情'" width="750px">
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="指标ID">{{ currentIndicator?.index_id }}</el-descriptions-item>
            <el-descriptions-item label="统计周期">{{ currentIndicator?.stat_cycle }}</el-descriptions-item>
            <el-descriptions-item label="当前值">
              <span class="current-value">{{ currentIndicator?.stat_value }}{{ getIndicatorUnit(currentIndicator?.index_type) }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="基准值">
              {{ currentIndicator?.benchmark_value }}{{ getIndicatorUnit(currentIndicator?.index_type) }}
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="getIndicatorStatusTagType(currentIndicator?.warn_status)">
                {{ getIndicatorStatusText(currentIndicator?.warn_status) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="统计时间">{{ currentIndicator?.create_time }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="modifyIndicatorBenchmark">修改基准值</el-button>
      </template>
    </el-dialog>

    <!-- 客流详情弹窗 -->
    <el-dialog v-model="flowDetailVisible" :title="currentFlow?.area_name || '客流详情'" width="650px">
      <div class="flow-detail">
        <div class="detail-section">
          <h3>区域信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="区域ID">{{ currentFlow?.area_id }}</el-descriptions-item>
            <el-descriptions-item label="区域名称">{{ currentFlow?.area_name }}</el-descriptions-item>
            <el-descriptions-item label="区域类型">{{ currentFlow?.area_type }}</el-descriptions-item>
            <el-descriptions-item label="区域承载量">{{ currentFlow?.area_capacity }}人</el-descriptions-item>
            <el-descriptions-item label="疏导工具">{{ currentFlow?.ext2 }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>客流数据</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="实时客流">{{ currentFlow?.real_time_flow }}人</el-descriptions-item>
            <el-descriptions-item label="客流占比">
              {{ (currentFlow?.real_time_flow / currentFlow?.area_capacity * 100).toFixed(1) }}%
            </el-descriptions-item>
            <el-descriptions-item label="监测时段">{{ currentFlow?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentFlow?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentFlow?.warn_status === '1' ? '已预警' : '无预警' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="疏导工单ID" v-if="currentFlow?.guide_order_id">
              {{ currentFlow?.guide_order_id }}
            </el-descriptions-item>
            <el-descriptions-item label="调度员ID">{{ currentFlow?.dispatcher_id }}</el-descriptions-item>
            <el-descriptions-item label="处理措施" v-if="currentFlow?.handle_measure">
              {{ currentFlow?.handle_measure }} ({{ currentFlow?.handle_time }})
            </el-descriptions-item>
            <el-descriptions-item label="核查结果" v-if="currentFlow?.verify_result">
              {{ currentFlow?.verify_result }} ({{ currentFlow?.verify_time }})
            </el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>24小时客流趋势</h3>
          <div class="chart-container">
            <ChartLine3 :data="flowTrendData" :yAxisName="'人数'" />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="flowDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="handleCurrentFlowWarning" v-if="currentFlow?.warn_status === '1'">
          处理预警
        </el-button>
      </template>
    </el-dialog>

    <!-- 资源分布详情弹窗 -->
    <el-dialog v-model="resourceDetailVisible" :title="currentResource?.resource_name || '资源详情'" width="600px">
      <div class="resource-dist-detail">
        <div class="detail-section">
          <h3>资源信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="资源ID">{{ currentResource?.resource_id }}</el-descriptions-item>
            <el-descriptions-item label="资源名称">{{ currentResource?.resource_name }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ currentResource?.resource_type }}</el-descriptions-item>
            <el-descriptions-item label="资源位置">{{ currentResource?.resource_pos }}</el-descriptions-item>
            <el-descriptions-item label="资源等级">{{ currentResource?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="检修周期">{{ currentResource?.ext2 }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>状态信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="资源状态">
              <el-tag :type="currentResource?.resource_status === '1' ? 'danger' : 'success'">
                {{ currentResource?.resource_status === '1' ? '异常' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentResource?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentResource?.warn_status === '1' ? '已预警' : '无预警' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="检修工单ID" v-if="currentResource?.maintain_order_id">
              {{ currentResource?.maintain_order_id }}
            </el-descriptions-item>
            <el-descriptions-item label="运维员ID">{{ currentResource?.maintain_user_id }}</el-descriptions-item>
            <el-descriptions-item label="检修记录" v-if="currentResource?.maintain_measure">
              {{ currentResource?.maintain_measure }} ({{ currentResource?.maintain_time }})
            </el-descriptions-item>
            <el-descriptions-item label="核查结果" v-if="currentResource?.verify_result">
              {{ currentResource?.verify_result }} ({{ currentResource?.verify_time }})
            </el-descriptions-item>
          </el-descriptions>
        </div>
        <template v-if="currentResource?.warn_status === '1'">
          <div class="handle-section">
            <el-form>
              <el-form-item label="检修措施" required>
                <el-input type="textarea" v-model="maintainMeasure" rows="3" />
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      <template #footer>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="handleCurrentResourceWarning" v-if="currentResource?.warn_status === '1'">
          处理预警
        </el-button>
      </template>
    </el-dialog>

    <!-- 新预警通知弹窗 -->
    <el-dialog v-model="newWarningVisible" title="新预警通知" width="400px" :show-close="false">
      <div class="warning-notification">
        <div class="warning-icon">预警</div>
        <div class="warning-content">
          <p><strong>{{ newWarning?.resource_name }}</strong> 出现预警</p>
          <p>{{ newWarning?.warning_desc }}</p>
          <p class="warning-time">发生时间: {{ newWarning?.time }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="ignoreWarning">忽略</el-button>
        <el-button type="primary" @click="handleNewWarning">处理预警</el-button>
      </template>
    </el-dialog>

    <!-- 修改指标基准值弹窗 -->
    <el-dialog v-model="modifyBenchmarkVisible" title="修改指标基准值" width="400px">
      <el-form :model="benchmarkForm" ref="benchmarkFormRef" label-width="90px">
        <el-form-item label="指标名称" disabled>
          <el-input v-model="benchmarkForm.indicatorName" />
        </el-form-item>
        <el-form-item label="当前基准值" disabled>
          <el-input v-model="benchmarkForm.currentBenchmark" :suffix="getIndicatorUnit(currentIndicator?.index_type)" />
        </el-form-item>
        <el-form-item label="新基准值" required>
          <el-input
            v-model="benchmarkForm.newBenchmark"
            type="number"
            min="0"
            step="0.1"
            :suffix="getIndicatorUnit(currentIndicator?.index_type)"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="modifyBenchmarkVisible = false">取消</el-button>
        <el-button type="primary" @click="saveIndicatorBenchmark">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, computed, onMounted, onUnmounted, getCurrentInstance } from 'vue';
import {
  ElSelect,
  ElOption,
  ElButton,
  ElTable,
  ElTableColumn,
  ElTag,
  ElInput,
  ElDialog,
  ElDescriptions,
  ElDescriptionsItem,
  ElForm,
  ElFormItem,
  ElMessage
} from 'element-plus';
import screenFull from 'screenfull';
import { FullScreen } from "@element-plus/icons-vue";
import ChartLine from './ChartLine.vue';
import ChartLine3 from './ChartLine3.vue';
import ChartBar from './ChartBar.vue';
import ChartPie from './ChartPie.vue';
// 导入腾讯地图组件
import MapCommon from './MapCommon.vue';

// 导入API方法
import {
  fetchCulturalTourismGeometries,
  fetchResourceDistribution,
  fetchResourceTypeDistribution,
  fetchResourceStatusDistribution,
  fetchCoreIndicators,
  fetchTouristFlowOverview,
  handleWarning,
  refreshCulturalData,
  configureIndicatorBenchmark,
  submitVerifyResult,
  fetchIndicatorHistory,
  fetchResourceStatusTrend,
  fetchFlowHourlyTrend
} from '@/api/overview/culturesportstourism/GlobalSituationOverview.js';
import {useRouter} from "vue-router";

const geometriesArray = ref([]);
const currentTime = ref('');
const instance = getCurrentInstance();
const router = useRouter();

// 时间格式化
const formatTime = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return `${year}年${month}月${day}日 ${hours}时${minutes}分${seconds}秒`;
};

// 面板全屏切换功能
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

// 文旅全域数据地图相关数据
const mapResources = ref([]);
const mapResourceType = ref('');
const mapWarnFilter = ref('');
const filteredMapResources = computed(() => {
  return mapResources.value.filter(resource => {
    const matchesType = !mapResourceType.value || resource.resource_type === mapResourceType.value;
    const matchesStatus = !mapWarnFilter.value || resource.warn_status === mapWarnFilter.value;
    return matchesType && matchesStatus;
  });
});

// 地图资源详情弹窗
const resourceOnMapDetailVisible = ref(false);
const currentMapResource = ref(null);
const handleContent = ref('');
const showResourceOnMapDetail = (resource) => {
  currentMapResource.value = {...resource};
  handleContent.value = '';
  resourceOnMapDetailVisible.value = true;
};
const handleMapResourceWarning = async () => {
  if (!currentMapResource.value || !handleContent.value) return;
  try {
    await handleWarning({
      mapId: currentMapResource.value.map_id,
      handleContent: handleContent.value,
      handleUser: 'admin'
    });
    const index = mapResources.value.findIndex(f => f.map_id === currentMapResource.value.map_id);
    if (index !== -1) {
      mapResources.value[index].warn_status = '0';
      mapResources.value[index].dispatch_order_id = 'order_' + Date.now();
    }
    currentMapResource.value.warn_status = '0';
    currentMapResource.value.dispatch_order_id = 'order_' + Date.now();
    ElMessage.success('预警处理成功');
    resourceOnMapDetailVisible.value = false;
    refreshWarnings();
  } catch (error) {
    ElMessage.error('预警处理失败: ' + (error.message || '未知错误'));
  }
};

// 文旅资源分布相关数据
const resourceTypeData = ref([]); // 类型分布
const resourceStatusData = ref([]); // 状态占比
const resourceDistribution = ref([]);
const resourceTypeFilter = ref('');
const resourceStatusFilter = ref('');
const filteredResources = computed(() => {
  return resourceDistribution.value.filter(resource => {
    const matchesType = !resourceTypeFilter.value || resource.resource_type === resourceTypeFilter.value;
    const matchesStatus = !resourceStatusFilter.value || resource.resource_status === resourceStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 资源统计数据
const totalResources = computed(() => filteredResources.value.length);
const normalResources = computed(() => filteredResources.value.filter(item => item.resource_status === '0').length);
const abnormalResources = computed(() => filteredResources.value.filter(item => item.resource_status === '1').length);
const warningResources = computed(() => filteredResources.value.filter(item => item.warn_status === '1').length);

// 路由跳转方法
const jumpToTourismResourceEmergency = () => router.push('/overview/tourismresourceemergency/dpzl');

// 国家资源等级权重映射
const nationalLevelWeights = {
  '5A景区': 5,
  '4A景区': 4,
  '3A景区': 3,
  '全国重点文物保护单位': 5,
  '省级文物保护单位': 4,
  '市级文物保护单位': 3,
  '国家一级博物馆': 5,
  '国家二级博物馆': 4,
  '国家三级博物馆': 3,
  default: 2
};

// 重点资源TOP5
const top5Resources = computed(() => {
  return [...filteredResources.value]
    .sort((a, b) => {
      // 核心：按热度降序（优先级最高）
      if (a.hot_degree !== b.hot_degree) return b.hot_degree - a.hot_degree;
      // 兜底：原国家等级排序（热度相同时生效）
      const weightA = nationalLevelWeights[a.ext1] || nationalLevelWeights.default;
      const weightB = nationalLevelWeights[b.ext1] || nationalLevelWeights.default;
      if (weightA !== weightB) return weightB - weightA;
      if (a.warn_status !== b.warn_status) return a.warn_status - b.warn_status;
      return a.resource_status - b.resource_status;
    })
    .slice(0, 5);
});

// 全部资源弹窗控制
const allResourcesVisible = ref(false);
const showAllResources = () => {
  allResourcesVisible.value = true;
};

// 视图切换状态（图表/表格）
const currentView = ref('charts');
const toggleView = () => {
  currentView.value = currentView.value === 'charts' ? 'table' : 'charts';
};

// 图表数据（类型分布柱状图+状态占比饼图）
const typeDistributionData = ref({ xAxis: [], series: [{ name: '数量', data: [] }] });
const statusDistributionData = ref([]);

// 计算图表数据
const calculateChartsData = () => {
  // 资源类型分布（柱状图）
  const typeXAxis = resourceTypeData.value.map(item => item.type);
  const typeSeriesData = resourceTypeData.value.map(item => item.count);
  typeDistributionData.value = {
    xAxis: typeXAxis,
    series: [{
      name: '资源数量',
      data: typeSeriesData,
      itemStyle: {
        color: function (params) {
          const colors = {
            '景区': '#ff7d00',
            '场馆': '#13ce66',
            '活动': '#ffd100'
          };
          return colors[params.name] || '#722ed1';
        }
      }
    }]
  };

  // 资源状态占比（饼图）
  const total = resourceStatusData.value.reduce((sum, item) => sum + item.count, 0);
  statusDistributionData.value = resourceStatusData.value.map(item => ({
    状态: item.status === '0' ? '正常' : '异常',
    数量: item.count,
    占比: total > 0 ? `${(item.count / total * 100).toFixed(1)}%` : '0%',
    itemStyle: {
      color: item.status === '0' ? 'rgba(51,164,234,0.8)' : 'rgba(255,82,82,0.8)'
    }
  }));
};

// 筛选条件变化时更新图表
const updateChartsData = () => {
  calculateChartsData();
};

// 资源分布详情弹窗
const resourceDetailVisible = ref(false);
const currentResource = ref(null);
const maintainMeasure = ref('');
const resourceTrendData = ref({xAxis: [], series: []});
const showResourceDetail = async (resource) => {
  currentResource.value = {...resource};
  maintainMeasure.value = '';
  await generateResourceTrendData(resource.dist_id);
  resourceDetailVisible.value = true;
};
const generateResourceTrendData = async (distId) => {
  try {
    const trendData = await fetchResourceStatusTrend(distId);
    resourceTrendData.value = trendData;
  } catch (error) {
    console.error('获取资源状态趋势失败:', error);
    ElMessage.error('加载趋势数据失败');
  }
};
const handleResourceWarning = (resource) => {
  showResourceDetail(resource);
};
const handleCurrentResourceWarning = async () => {
  if (!currentResource.value || !maintainMeasure.value) return;
  try {
    await handleWarning({
      mapId: currentResource.value.dist_id,
      handleContent: maintainMeasure.value,
      handleUser: 'maintainer'
    });
    const index = resourceDistribution.value.findIndex(f => f.dist_id === currentResource.value.dist_id);
    if (index !== -1) {
      resourceDistribution.value[index].warn_status = '0';
      resourceDistribution.value[index].resource_status = '0';
      resourceDistribution.value[index].maintain_order_id = 'maintain_' + Date.now();
      resourceDistribution.value[index].maintain_time = formatTime(new Date());
      resourceDistribution.value[index].maintain_measure = maintainMeasure.value;
    }
    currentResource.value.warn_status = '0';
    currentResource.value.resource_status = '0';
    currentResource.value.maintain_order_id = 'maintain_' + Date.now();
    currentResource.value.maintain_time = formatTime(new Date());
    currentResource.value.maintain_measure = maintainMeasure.value;
    ElMessage.success('预警处理成功');
    resourceDetailVisible.value = false;
    refreshWarnings();
    calculateChartsData();
  } catch (error) {
    ElMessage.error('预警处理失败: ' + (error.message || '未知错误'));
  }
};

// 导出资源分布数据
const exportResourceData = () => {
  console.log('导出资源分布数据', filteredResources.value);
  ElMessage.success('资源分布数据导出成功');
};

// 文旅核心指标相关数据
const coreIndicators = ref([]);
const indicatorTimeRange = ref('today');
const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);
const indicatorHistoryData = ref({xAxis: [], series: []});

// 数字增长动画核心函数
const animateValue = (element, start, end, duration) => {
  let startTimestamp = null;
  // 判断原始指标是否为整数（控制小数位数）
  const isInteger = Number.isInteger(end);

  const step = (timestamp) => {
    if (!startTimestamp) startTimestamp = timestamp;
    // 计算动画进度（0-1）
    const progress = Math.min((timestamp - startTimestamp) / duration, 1);
    // 计算当前应显示的数值
    const currentValue = progress * (end - start) + start;

    // 格式化显示：整数无小数，小数保留1位
    element.textContent = isInteger
      ? currentValue.toFixed(0)
      : currentValue.toFixed(1);

    // 未完成则继续帧动画
    if (progress < 1) {
      window.requestAnimationFrame(step);
    }
  };
  window.requestAnimationFrame(step);
};

const initNumberAnimations = () => {
  // 等待DOM渲染完成后执行
  nextTick(() => {
    // 匹配所有带数字动画的元素（.number-animate类）
    const elements = document.querySelectorAll('.number-animate');
    elements.forEach(el => {
      // 从data-value属性获取目标值
      const value = parseFloat(el.getAttribute('data-value'));
      // 启动动画：从0到目标值，时长1500ms
      animateValue(el, 0, value, 1500);
    });
  });
};

const getIndicatorStatusClass = (status) => {
  switch (status) {
    case '0': return 'normal'; // 正常（无闪烁）
    case '1': return 'remind blink-animation'; // 提醒（带闪烁）
    case '2': return 'warning blink-animation'; // 预警（带闪烁）
    default: return ''; // 未知状态（无样式）
  }
};

const getIndicatorStatusTagType = (status) => {
  switch (status) {
    case '0': return 'success';
    case '1': return 'warning';
    case '2': return 'danger';
    default: return 'info';
  }
};

const getIndicatorStatusText = (status) => {
  switch (status) {
    case '0': return '正常';
    case '1': return '提醒';
    case '2': return '预警';
    default: return '未知';
  }
};

const showIndicatorDetail = async (indicator) => {
  currentIndicator.value = {...indicator};
  await generateIndicatorHistoryData(indicator.index_id);
  indicatorDetailVisible.value = true;
};
const generateIndicatorHistoryData = async (indicatorId) => {
  try {
    const trendData = await fetchIndicatorHistory(indicatorId);
    indicatorHistoryData.value = trendData;
  } catch (error) {
    console.error('获取指标历史趋势失败:', error);
    ElMessage.error('加载趋势数据失败');
  }
};

const getIndicatorUnit = (type) => {
  switch (type) {
    case '文旅资源总数':
    case '活动开展数':
      return '个';
    case '当日客流峰值':
      return '人';
    case '投诉办结率':
    case '设施完好率':
      return '%';
    default:
      return '';
  }
};

// 修改指标基准值相关
const modifyBenchmarkVisible = ref(false);
const benchmarkForm = ref({indicatorName: '', currentBenchmark: '', newBenchmark: ''});
const benchmarkFormRef = ref(null);
const modifyIndicatorBenchmark = () => {
  if (!currentIndicator.value) return;
  benchmarkForm.value.indicatorName = currentIndicator.value.index_type;
  benchmarkForm.value.currentBenchmark = currentIndicator.value.benchmark_value;
  benchmarkForm.value.newBenchmark = '';
  modifyBenchmarkVisible.value = true;
};
const saveIndicatorBenchmark = async () => {
  if (!currentIndicator.value || !benchmarkForm.value.newBenchmark) return;
  try {
    await configureIndicatorBenchmark({
      indexId: currentIndicator.value.index_id,
      benchmarkValue: parseFloat(benchmarkForm.value.newBenchmark)
    });
    const index = coreIndicators.value.findIndex(i => i.index_id === currentIndicator.value.index_id);
    if (index !== -1) {
      coreIndicators.value[index].benchmark_value = parseFloat(benchmarkForm.value.newBenchmark);
      coreIndicators.value[index].warn_status =
        (coreIndicators.value[index].index_type === '投诉处理率' ||
          coreIndicators.value[index].index_type === '预警处理及时率')
          ? (coreIndicators.value[index].stat_value < coreIndicators.value[index].benchmark_value ? '1' : '0')
          : (coreIndicators.value[index].stat_value > coreIndicators.value[index].benchmark_value ? '1' : '0');
    }
    currentIndicator.value.benchmark_value = parseFloat(benchmarkForm.value.newBenchmark);
    currentIndicator.value.warn_status =
      (currentIndicator.value.index_type === '投诉处理率' ||
        currentIndicator.value.index_type === '预警处理及时率')
        ? (currentIndicator.value.stat_value < currentIndicator.value.benchmark_value ? '1' : '0')
        : (currentIndicator.value.stat_value > currentIndicator.value.benchmark_value ? '1' : '0');
    ElMessage.success('基准值配置成功');
    modifyBenchmarkVisible.value = false;
    generateIndicatorHistoryData(currentIndicator.value.index_id);
    refreshWarnings();
  } catch (error) {
    ElMessage.error('配置失败: ' + (error.message || '未知错误'));
  }
};

// 指标趋势图相关
const trendIndicatorId = ref('');
const currentTrendIndicator = ref(null);
const indicatorTrendData = ref({xAxis: [], series: []});
const changeTrendIndicator = (indicatorId) => {
  if (!indicatorId) return;
  const indicator = coreIndicators.value.find(i => i.index_id === indicatorId);
  if (!indicator) return;
  currentTrendIndicator.value = indicator;
  const xAxis = [];
  const data = [];
  const now = new Date();
  for (let i = 11; i >= 0; i--) {
    const month = new Date(now);
    month.setMonth(now.getMonth() - i);
    xAxis.push(`${month.getFullYear()}-${String(month.getMonth() + 1).padStart(2, '0')}`);
    const baseValue = indicator.stat_value;
    const fluctuation = (Math.random() - 0.5) * (indicator.index_type === '资源总数' ? 15 : 20);
    data.push(parseFloat((baseValue + fluctuation).toFixed(2)));
  }
  indicatorTrendData.value = {
    xAxis,
    series: [{name: indicator.index_type, data}]
  };
};

const togglePanelFullscreen = (panelRefName) => {
  toggleFullscreen(panelRefName);
};

// 文旅客流总览相关数据
const touristFlowOverview = ref([]);
const flowAreaTypeFilter = ref('');
const flowWarnFilter = ref('');
const filteredTouristFlow = computed(() => {
  return touristFlowOverview.value.filter(flow => {
    const matchesType = !flowAreaTypeFilter.value || flow.area_type === flowAreaTypeFilter.value;
    const matchesStatus = !flowWarnFilter.value || flow.warn_status === flowWarnFilter.value;
    return matchesType && matchesStatus;
  });
});

// 流量详情弹窗
const flowDetailVisible = ref(false);
const currentFlow = ref(null);
const flowTrendData = ref({xAxis: [], series: []});
const showFlowDetail = async (flow) => {
  currentFlow.value = {...flow};
  await generateFlowTrendData(flow.flow_id);
  flowDetailVisible.value = true;
};
const generateFlowTrendData = async (flowId) => {
  try {
    const trendData = await fetchFlowHourlyTrend(flowId);
    flowTrendData.value = trendData;
  } catch (error) {
    console.error('获取客流小时趋势失败:', error);
    ElMessage.error('加载趋势数据失败');
  }
};
const handleFlowWarning = (flow) => {
  showFlowDetail(flow);
};
const handleCurrentFlowWarning = async () => {
  if (!currentFlow.value) return;
  const handleMeasure = "已增派工作人员进行客流疏导";
  try {
    await handleWarning({
      mapId: currentFlow.value.flow_id,
      handleContent: handleMeasure,
      handleUser: 'dispatcher'
    });
    const index = touristFlowOverview.value.findIndex(f => f.flow_id === currentFlow.value.flow_id);
    if (index !== -1) {
      touristFlowOverview.value[index].warn_status = '0';
      touristFlowOverview.value[index].handle_time = formatTime(new Date());
      touristFlowOverview.value[index].handle_measure = handleMeasure;
    }
    currentFlow.value.warn_status = '0';
    currentFlow.value.handle_time = formatTime(new Date());
    currentFlow.value.handle_measure = handleMeasure;
    ElMessage.success('预警处理成功');
    flowDetailVisible.value = false;
    refreshWarnings();
  } catch (error) {
    ElMessage.error('预警处理失败: ' + (error.message || '未知错误'));
  }
};

// 导出流量数据
const exportFlowData = () => {
  console.log('导出文旅客流数据', filteredTouristFlow.value);
  ElMessage.success('文旅客流数据导出成功');
};

// 异常信息滚动配置
const warningScrollConfig = ref({
  header: ['资源名称', '预警类型', '时间'],
  data: [],
  rowNum: 5,
  align: ['center', 'center', 'center'],
  columnWidth: [125, 125, 180],
  minWidth: 430,
});
const refreshWarnings = () => {
  const warnings = [];
  mapResources.value.forEach(res => {
    if (res.warn_status === '1') {
      warnings.push([
        res.resource_name,
        `客流超${(res.real_time_flow / res.max_capacity * 100).toFixed(1)}%`,
        res.create_time
      ]);
    }
  });
  resourceDistribution.value.forEach(res => {
    if (res.warn_status === '1') {
      warnings.push([
        res.resource_name,
        res.resource_status === '1' ? '资源异常' : '其他预警',
        res.create_time
      ]);
    }
  });
  touristFlowOverview.value.forEach(flow => {
    if (flow.warn_status === '1') {
      warnings.push([
        flow.area_name,
        `客流超${(flow.real_time_flow / flow.area_capacity * 100).toFixed(1)}%`,
        flow.create_time
      ]);
    }
  });
  coreIndicators.value.forEach(ind => {
    if (ind.warn_status === '1') {
      warnings.push([
        ind.index_type,
        '指标异常',
        ind.create_time
      ]);
    }
  });
  warningScrollConfig.value.data = warnings;
};

// 新预警通知
const newWarningVisible = ref(false);
const newWarning = ref(null);
const simulateNewWarning = () => {
  const warningResources = [];
  mapResources.value.forEach(res => {
    if (res.warn_status === '1') {
      warningResources.push({
        resource_name: res.resource_name,
        warning_desc: `客流超${(res.real_time_flow / res.max_capacity * 100).toFixed(1)}%`,
        type: 'map',
        id: res.map_id
      });
    }
  });
  resourceDistribution.value.forEach(res => {
    if (res.warn_status === '1') {
      warningResources.push({
        resource_name: res.resource_name,
        warning_desc: res.resource_status === '1' ? '资源异常' : '其他预警',
        type: 'resource',
        id: res.dist_id
      });
    }
  });
  if (warningResources.length === 0) return;
  const randomIndex = Math.floor(Math.random() * warningResources.length);
  const warning = warningResources[randomIndex];
  newWarning.value = {...warning, time: formatTime(new Date())};
  newWarningVisible.value = true;
};
const ignoreWarning = () => {
  newWarningVisible.value = false;
};
const handleNewWarning = () => {
  if (!newWarning.value) return;
  if (newWarning.value.type === 'map') {
    const resource = mapResources.value.find(f => f.map_id === newWarning.value.id);
    if (resource) showResourceOnMapDetail(resource);
  } else if (newWarning.value.type === 'resource') {
    const resource = resourceDistribution.value.find(f => f.dist_id === newWarning.value.id);
    if (resource) showResourceDetail(resource);
  }
  newWarningVisible.value = false;
};

onMounted(() => {
  // 初始化时间
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  // 初始化
  const initData = async () => {
    try {
      const [
        geometriesData,
        resources,
        typeDistData, // 类型分布
        statusDistData, // 状态占比
        indicators,
        flowData
      ] = await Promise.all([
        fetchCulturalTourismGeometries(),
        fetchResourceDistribution(),
        fetchResourceTypeDistribution(),
        fetchResourceStatusDistribution(),
        fetchCoreIndicators('today'),
        fetchTouristFlowOverview()
      ]);

      // 赋值原有数据
      geometriesArray.value = geometriesData;
      resourceDistribution.value = resources;
      coreIndicators.value = indicators;
      touristFlowOverview.value = flowData;

      // 赋值新增的类型/状态数据
      resourceTypeData.value = typeDistData;
      resourceStatusData.value = statusDistData;

      // 刷新预警、计算图表、初始化动画
      refreshWarnings();
      calculateChartsData();
      initNumberAnimations();

      if (coreIndicators.value.length > 0) {
        trendIndicatorId.value = coreIndicators.value[0].index_id;
        changeTrendIndicator(trendIndicatorId.value);
      }
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };

  initData();

  // 定时模拟新预警
  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) simulateNewWarning();
  }, 30000);
  onUnmounted(() => clearInterval(warningTimer));
});
</script>
<style lang="scss" scoped>

@import url('./common-styles.scss');

// 最外层容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  box-sizing: border-box;
}

// 主体内容区
.mainbox {
  width: 100%;
  height: 90vh;
  padding: 0.6vw 0;
  box-sizing: border-box;
  position: relative;
}

// 面板样式
.panel {
  position: relative;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.top {
  display: flex;
  gap: 0.6vw;
  height: 60%;
}

.top_left {
  flex: 2;
}

.top_middle {
  flex: 3;
}

.top_right {
  flex: 2;
}

.bottom {
  display: flex;
  gap: 0.6vw;
  height: 36%;
  margin-top: 1vh;
}

.bottom_left {
  flex: 2;
}

.bottom_middle {
  flex: 3;
}

.bottom_right {
  flex: 2;
}

</style>
