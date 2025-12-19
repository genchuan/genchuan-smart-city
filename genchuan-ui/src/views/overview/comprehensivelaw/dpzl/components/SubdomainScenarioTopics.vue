<template>
  <div class="page-container subdomain-scenario-themes">
    <!-- 顶部筛选栏 -->
    <div class="theme-filters">
      <div class="filter-group">
        <span class="filter-label">时间周期：</span>
        <el-select v-model="timePeriod" placeholder="请选择" size="small" @change="loadAllData">
          <el-option label="今日" value="today" />
          <el-option label="本周" value="week" />
          <el-option label="本月" value="month" />
        </el-select>
      </div>

      <div class="filter-group">
        <span class="filter-label">区域：</span>
        <el-select
          v-model="areaId"
          placeholder="请选择"
          size="small"
          clearable
          @change="loadAllData"
        >
          <el-option
            v-for="area in areaOptions"
            :key="area.value"
            :label="area.label"
            :value="area.value"
          />
        </el-select>
      </div>

      <div class="filter-group">
        <span class="filter-label">部门：</span>
        <el-select
          v-model="deptId"
          placeholder="请选择"
          size="small"
          clearable
          @change="loadAllData"
        >
          <el-option
            v-for="dept in deptOptions"
            :key="dept.value"
            :label="dept.label"
            :value="dept.value"
          />
        </el-select>
      </div>

      <div class="filter-group">
        <el-button type="primary" size="small" @click="loadAllData">
          <i class="el-icon-refresh"></i> 刷新
        </el-button>
        <el-button size="small" @click="resetFilters">重置</el-button>
      </div>
    </div>

    <!-- 三部分布局 -->
    <div class="three-part-layout">
      <!-- 顶部区域：执法资源专题 -->
      <div class="top-section resource-section">
        <div class="panel theme-panel">
          <div class="panel-header">
            <h2><i class="el-icon-s-cooperation"></i> 执法资源专题</h2>
            <div class="header-actions">
              <el-select
                v-model="resourceFilter.status"
                placeholder="资源状态"
                size="small"
                clearable
                @change="loadResourceData"
              >
                <el-option label="全部" value="" />
                <el-option label="在用" value="1" />
                <el-option label="闲置" value="0" />
              </el-select>
              <el-button type="text" size="small" @click="gotoResourceDispatch">
                <i class="el-icon-sort"></i> 资源调度
              </el-button>
            </div>
          </div>
          <div class="panel-body">
            <!-- 资源卡片网格 - 4列1行 -->
            <div class="resource-cards-grid">
              <!-- 执法人员在岗率卡片 -->
              <div
                class="resource-card staff-card"
                :class="{'warning': resources.staff.warning}"
                @click="showResourceDetail('staff')"
              >
                <div class="card-breathe-effect" v-if="resources.staff.warning"></div>
                <div class="card-content">
                  <div class="card-header">
                    <div class="resource-title">
                      <i class="el-icon-user"></i>
                      执法人员在岗率
                    </div>
                    <div class="resource-status" :class="getStatusClass(resources.staff)">
                      {{ getStatusText(resources.staff) }}
                    </div>
                  </div>
                  <div class="resource-value">
                    <span class="animated-value">{{ resources.staff.value }}%</span>
                  </div>
                  <div class="resource-progress">
                    <div class="progress-track">
                      <div
                        class="progress-fill"
                        :style="{ width: resources.staff.value + '%' }"
                        :class="{'warning': resources.staff.warning}"
                      ></div>
                    </div>
                  </div>
                  <div class="resource-details">
                    <div class="detail-item">
                      <span>在岗人员：</span>
                      <span class="detail-value">{{ resources.staff.onDutyCount }}人</span>
                    </div>
                    <div class="detail-item">
                      <span>总人数：</span>
                      <span class="detail-value">{{ resources.staff.totalCount }}人</span>
                    </div>
                    <div v-if="resources.staff.top3Idle?.length" class="detail-list">
                      <div class="list-title">TOP3在岗人员：</div>
                      <div
                        v-for="(item, index) in resources.staff.top3Idle"
                        :key="index"
                        class="list-item"
                        @mouseenter="showResourceTooltip(item, 'staff')"
                        @mouseleave="hideResourceTooltip"
                      >
                        <span>{{ item.name }}</span>
                        <span class="list-action" @click.stop="dispatchResource(item, 'staff')">调度</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 执法车辆使用率卡片 -->
              <div
                class="resource-card vehicle-card"
                :class="{'warning': resources.vehicle.warning}"
                @click="showResourceDetail('vehicle')"
              >
                <div class="card-breathe-effect" v-if="resources.vehicle.warning"></div>
                <div class="card-content">
                  <div class="card-header">
                    <div class="resource-title">
                      <i class="el-icon-truck"></i>
                      执法车辆使用率
                    </div>
                    <div class="resource-status" :class="getStatusClass(resources.vehicle)">
                      {{ getStatusText(resources.vehicle) }}
                    </div>
                  </div>
                  <div class="resource-value">
                    <span class="animated-value">{{ resources.vehicle.value }}%</span>
                  </div>
                  <div class="resource-progress">
                    <div class="progress-track">
                      <div
                        class="progress-fill"
                        :style="{ width: resources.vehicle.value + '%' }"
                        :class="{'warning': resources.vehicle.warning}"
                      ></div>
                    </div>
                  </div>
                  <div class="resource-details">
                    <div class="detail-item">
                      <span>在用车辆：</span>
                      <span class="detail-value">{{ resources.vehicle.inUseCount }}辆</span>
                    </div>
                    <div class="detail-item">
                      <span>闲置车辆：</span>
                      <span class="detail-value warning">{{ resources.vehicle.idleCount }}辆</span>
                    </div>
                    <div v-if="resources.vehicle.top3Idle?.length" class="detail-list">
                      <div class="list-title">TOP3闲置车辆：</div>
                      <div
                        v-for="(item, index) in resources.vehicle.top3Idle"
                        :key="index"
                        class="list-item"
                        @mouseenter="showResourceTooltip(item, 'vehicle')"
                        @mouseleave="hideResourceTooltip"
                      >
                        <span>{{ item.code }}</span>
                        <span class="list-action" @click.stop="dispatchResource(item, 'vehicle')">调度</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 执法设备完好率卡片 -->
              <div
                class="resource-card device-card"
                :class="{'warning': resources.device.warning}"
                @click="showResourceDetail('device')"
              >
                <div class="card-breathe-effect" v-if="resources.device.warning"></div>
                <div class="card-content">
                  <div class="card-header">
                    <div class="resource-title">
                      <i class="el-icon-camera"></i>
                      执法设备完好率
                    </div>
                    <div class="resource-status" :class="getStatusClass(resources.device)">
                      {{ getStatusText(resources.device) }}
                    </div>
                  </div>
                  <div class="resource-value">
                    <span class="animated-value">{{ resources.device.value }}%</span>
                  </div>
                  <div class="resource-progress">
                    <div class="progress-track">
                      <div
                        class="progress-fill"
                        :style="{ width: resources.device.value + '%' }"
                        :class="{'warning': resources.device.warning}"
                      ></div>
                    </div>
                  </div>
                  <div class="resource-details">
                    <div class="detail-item">
                      <span>完好设备：</span>
                      <span class="detail-value">{{ resources.device.goodCount }}台</span>
                    </div>
                    <div class="detail-item">
                      <span>故障设备：</span>
                      <span class="detail-value warning">{{ resources.device.faultCount }}台</span>
                    </div>
                    <div v-if="resources.device.top3Fault?.length" class="detail-list">
                      <div class="list-title">TOP3故障设备：</div>
                      <div
                        v-for="(item, index) in resources.device.top3Fault"
                        :key="index"
                        class="list-item"
                        @mouseenter="showResourceTooltip(item, 'device')"
                        @mouseleave="hideResourceTooltip"
                      >
                        <span>{{ item.name }}</span>
                        <span class="list-action" @click.stop="showRepairDialog(item)">报修</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 应急物资库存卡片 -->
              <div
                class="resource-card material-card"
                :class="{'warning': resources.material.warning}"
                @click="showResourceDetail('material')"
              >
                <div class="card-breathe-effect" v-if="resources.material.warning"></div>
                <div class="card-content">
                  <div class="card-header">
                    <div class="resource-title">
                      <i class="el-icon-box"></i>
                      应急物资库存
                    </div>
                    <div class="resource-status" :class="getStatusClass(resources.material)">
                      {{ getStatusText(resources.material) }}
                    </div>
                  </div>
                  <div class="resource-value">
                    <span class="animated-value">{{ resources.material.value }}</span>
                    <span class="unit">件</span>
                  </div>
                  <div class="resource-progress">
                    <div class="progress-track">
                      <div
                        class="progress-fill"
                        :style="{ width: (resources.material.value / resources.material.safeStock * 100) + '%' }"
                        :class="{'warning': resources.material.warning}"
                      ></div>
                    </div>
                  </div>
                  <div class="resource-details">
                    <div class="detail-item">
                      <span>安全库存：</span>
                      <span class="detail-value">{{ resources.material.safeStock }}件</span>
                    </div>
                    <div class="detail-item">
                      <span>库存状态：</span>
                      <span class="detail-value" :class="getStatusClass(resources.material)">
                        {{ resources.material.stockStatus }}
                      </span>
                    </div>
                    <div v-if="resources.material.lowStock?.length" class="detail-list">
                      <div class="list-title">紧缺物资：</div>
                      <div
                        v-for="(item, index) in resources.material.lowStock"
                        :key="index"
                        class="list-item warning"
                        @mouseenter="showResourceTooltip(item, 'material')"
                        @mouseleave="hideResourceTooltip"
                      >
                        <span>{{ item.name }}</span>
                        <span class="list-action" @click.stop="replenishMaterial(item)">补货</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 中部区域：左检查右案件 -->
      <div class="middle-section">
        <!-- 左侧：执法检查专题 -->
        <div class="left-section check-section">
          <div class="panel theme-panel">
            <div class="panel-header">
              <h2><i class="el-icon-document-checked"></i> 执法检查专题</h2>
              <div class="header-actions">
                <el-select
                  v-model="checkFilter.type"
                  placeholder="检查类型"
                  size="small"
                  @change="loadCheckData"
                >
                  <el-option label="全部" value="" />
                  <el-option label="日常检查" value="daily" />
                  <el-option label="专项检查" value="special" />
                </el-select>
                <el-select
                  v-model="checkFilter.trendPeriod"
                  placeholder="时间周期"
                  size="small"
                  @change="loadCheckData"
                >
                  <el-option label="近15日" value="15days" />
                  <el-option label="近30日" value="30days" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="check-content">
                <!-- 检查指标卡片 - 2列2行 -->
                <div class="check-indicators-grid">
                  <div
                    class="check-card"
                    @click="showCheckDetail('plan')"
                  >
                    <div class="check-title">检查计划完成率</div>
                    <div class="check-value" :class="{'warning': checks.planCompleteRate < 90}">
                      {{ checks.planCompleteRate }}%
                    </div>
                    <div class="check-trend" :class="getTrendClass(checks.planTrend)">
                      {{ checks.planTrend > 0 ? '+' : '' }}{{ checks.planTrend }}%
                    </div>
                  </div>
                  <div
                    class="check-card"
                    @click="showCheckDetail('problem')"
                  >
                    <div class="check-title">检查发现问题数</div>
                    <div class="check-value">{{ checks.problemCount }}</div>
                    <div class="check-trend" :class="getTrendClass(checks.problemTrend)">
                      {{ checks.problemTrend > 0 ? '+' : '' }}{{ checks.problemTrend }}%
                    </div>
                  </div>
                  <div
                    class="check-card"
                    :class="{'warning': checks.rectifyRate < 80}"
                    @click="showCheckDetail('rectify')"
                  >
                    <div class="check-title">问题整改率</div>
                    <div class="check-value" :class="{'warning': checks.rectifyRate < 80}">
                      {{ checks.rectifyRate }}%
                    </div>
                    <div class="check-trend" :class="getTrendClass(checks.rectifyTrend)">
                      {{ checks.rectifyTrend > 0 ? '+' : '' }}{{ checks.rectifyTrend }}%
                    </div>
                    <div v-if="checks.rectifyRate < 80" class="warning-mark">⚠</div>
                  </div>
                  <div
                    class="check-card"
                    @click="showCheckDetail('coverage')"
                  >
                    <div class="check-title">重点场所覆盖率</div>
                    <div class="check-value">{{ checks.coverageRate }}%</div>
                    <div class="check-trend" :class="getTrendClass(checks.coverageTrend)">
                      {{ checks.coverageTrend > 0 ? '+' : '' }}{{ checks.coverageTrend }}%
                    </div>
                  </div>
                </div>

                <!-- 检查进度条和趋势图 -->
                <div class="check-charts-row">
                  <!-- 检查计划执行进度 -->
                  <div class="progress-container" @click="showUncompletedPlans">
                    <div class="progress-header">
                      <div class="progress-title">检查计划执行进度</div>
                      <div class="progress-rate">{{ checks.progressRate }}%</div>
                    </div>
                    <div class="progress-bar">
                      <div
                        class="progress-fill"
                        :style="{ width: checks.progressRate + '%' }"
                        :class="{'animated': true}"
                      ></div>
                      <div
                        class="uncompleted-part"
                        :style="{ width: (100 - checks.progressRate) + '%' }"
                      ></div>
                    </div>
                    <div class="progress-stats">
                      <span>已完成：{{ checks.completedCount }}</span>
                      <span>未完成：{{ checks.uncompletedCount }}</span>
                    </div>
                  </div>

                  <!-- 整改趋势折线图 -->
                  <div class="trend-chart-container">
                    <div class="chart-header">
                      <h3>问题整改趋势</h3>
                      <div class="chart-legend">
                        <span class="legend-item problem">
                          <i class="dot"></i>发现问题
                        </span>
                        <span class="legend-item rectify">
                          <i class="dot"></i>已整改
                        </span>
                      </div>
                    </div>
                    <div class="chart-wrapper" ref="rectifyChartRef"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 右侧：案件管理专题 -->
        <div class="right-section case-section">
          <div class="panel theme-panel">
            <div class="panel-header">
              <h2><i class="el-icon-files"></i> 案件管理专题</h2>
              <div class="header-actions">
                <el-select
                  v-model="caseFilter.type"
                  placeholder="案件类型"
                  size="small"
                  multiple
                  clearable
                  @change="loadCaseData"
                >
                  <el-option label="违法建设" value="illegal_construction" />
                  <el-option label="噪音扰民" value="noise_pollution" />
                  <el-option label="占道经营" value="occupying_road" />
                </el-select>
                <el-select
                  v-model="caseFilter.department"
                  placeholder="办理部门"
                  size="small"
                  clearable
                  @change="loadCaseData"
                >
                  <el-option label="执法一队" value="dept1" />
                  <el-option label="执法二队" value="dept2" />
                  <el-option label="执法三队" value="dept3" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="case-content">
                <!-- 案件办理进度分布（柱状图） -->
                <div class="stage-chart-container">
                  <div class="chart-header">
                    <h3>案件办理进度分布</h3>
                  </div>
                  <div class="chart-wrapper" ref="stageChartRef"></div>
                </div>

                <!-- 案件来源TOP3（饼图） -->
                <div class="source-chart-container">
                  <div class="chart-header">
                    <h3>案件来源分布 TOP3</h3>
                  </div>
                  <div class="chart-wrapper" ref="sourceChartRef"></div>
                </div>

                <!-- 案件处罚金额统计 -->
                <div
                  class="amount-card"
                  @click="showPunishmentDetail"
                >
                  <div class="amount-title">案件处罚金额</div>
                  <div class="amount-value">
                    <span class="animated-value">¥{{ formatAmount(cases.punishmentAmount) }}</span>
                  </div>
                  <div class="amount-trend" :class="getTrendClass(cases.punishmentTrend)">
                    环比增长 {{ cases.punishmentTrend > 0 ? '+' : '' }}{{ cases.punishmentTrend }}%
                  </div>
                </div>

                <!-- 超期案件TOP5 -->
                <div class="overdue-section">
                  <div class="section-header">
                    <h4>超期案件 TOP5</h4>
                    <el-button type="text" size="small" @click="showAllOverdueCases">
                      查看全部
                    </el-button>
                  </div>
                  <div class="overdue-list">
                    <div
                      v-for="(caseItem, index) in cases.overdueList"
                      :key="index"
                      class="overdue-item"
                      :class="{'shake': caseItem.shake}"
                      @dblclick="showCaseDetail(caseItem)"
                      @click="toggleCaseSelection(caseItem)"
                    >
                      <div class="case-info">
                        <span class="case-id">{{ caseItem.caseId }}</span>
                        <span class="overdue-days">
                          超期{{ caseItem.overdueDays }}天
                        </span>
                      </div>
                      <div class="case-desc">{{ caseItem.description }}</div>
                      <div class="case-actions">
                        <el-button
                          size="mini"
                          type="text"
                          @click.stop="dispatchCase(caseItem)"
                        >
                          派单
                        </el-button>
                        <el-button
                          v-if="caseItem.selected"
                          size="mini"
                          type="text"
                          @click.stop="showUrgentDialog(caseItem)"
                        >
                          催办
                        </el-button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 底部区域：执法监督专题 -->
      <div class="bottom-section supervision-section">
        <div class="panel theme-panel">
          <div class="panel-header">
            <h2><i class="el-icon-monitor"></i> 执法监督专题</h2>
            <div class="header-actions">
              <el-select
                v-model="supervisionFilter.type"
                placeholder="监督类型"
                size="small"
                @change="loadSupervisionData"
              >
                <el-option label="全部" value="" />
                <el-option label="合规检查" value="compliance" />
                <el-option label="投诉处理" value="complaint" />
              </el-select>
              <el-select
                v-model="supervisionFilter.period"
                placeholder="监督时间"
                size="small"
                @change="loadSupervisionData"
              >
                <el-option label="近7日" value="7days" />
                <el-option label="近30日" value="30days" />
              </el-select>
            </div>
          </div>
          <div class="panel-body">
            <div class="supervision-content">
              <!-- 监督指标卡片 - 2列2行 -->
              <div class="supervision-indicators-grid">
                <div
                  class="supervision-card"
                  @click="showSupervisionDetail('compliance')"
                >
                  <div class="card-header">
                    <div class="supervision-title">执法合规检查合格率</div>
                    <div class="supervision-value" :class="getStatusClassByRate(supervision.complianceRate)">
                      {{ supervision.complianceRate }}%
                    </div>
                  </div>
                  <div class="supervision-trend" :class="getTrendClass(supervision.complianceTrend)">
                    {{ supervision.complianceTrend > 0 ? '+' : '' }}{{ supervision.complianceTrend }}%
                  </div>
                  <div class="supervision-note">
                    检查次数：{{ supervision.checkCount }}
                  </div>
                </div>
                <div
                  class="supervision-card"
                  @click="showSupervisionDetail('complaint')"
                >
                  <div class="card-header">
                    <div class="supervision-title">投诉举报处理率</div>
                    <div class="supervision-value" :class="getStatusClassByRate(supervision.complaintHandleRate)">
                      {{ supervision.complaintHandleRate }}%
                    </div>
                  </div>
                  <div class="supervision-trend" :class="getTrendClass(supervision.complaintTrend)">
                    {{ supervision.complaintTrend > 0 ? '+' : '' }}{{ supervision.complaintTrend }}%
                  </div>
                  <div class="supervision-note">
                    投诉数：{{ supervision.complaintCount }}
                  </div>
                </div>
                <div
                  class="supervision-card warning"
                  @click="showSupervisionDetail('illegal')"
                >
                  <div class="card-header">
                    <div class="supervision-title">违规执法次数</div>
                    <div class="supervision-value warning">{{ supervision.illegalCount }}次</div>
                  </div>
                  <div class="supervision-trend warning">
                    {{ supervision.illegalTrend > 0 ? '+' : '' }}{{ supervision.illegalTrend }}次
                  </div>
                  <div class="pulse-effect" v-if="supervision.illegalCount > 3"></div>
                </div>
                <div
                  class="supervision-card"
                  @click="showSupervisionDetail('credit')"
                >
                  <div class="card-header">
                    <div class="supervision-title">执法人员信用评分均值</div>
                    <div class="supervision-value">{{ supervision.creditScore }}</div>
                  </div>
                  <div class="supervision-trend" :class="getTrendClass(supervision.creditTrend)">
                    {{ supervision.creditTrend > 0 ? '+' : '' }}{{ supervision.creditTrend }}
                  </div>
                  <div class="credit-stars">
                    <i
                      v-for="n in 5"
                      :key="n"
                      :class="[
                        'star',
                        n <= Math.floor(supervision.creditScore) ? 'el-icon-star-on' : 'el-icon-star-off',
                        { 'active': n <= supervision.creditScore }
                      ]"
                    ></i>
                  </div>
                </div>
              </div>

              <!-- 合规趋势图和投诉处理列表 -->
              <div class="supervision-charts-row">
                <!-- 合规检查趋势图 -->
                <div class="compliance-trend-chart">
                  <div class="chart-header">
                    <h3>合规检查趋势</h3>
                  </div>
                  <div class="chart-wrapper" ref="complianceChartRef"></div>
                </div>

                <!-- 投诉举报处理列表 -->
                <div class="complaint-handling-list">
                  <div class="list-header">
                    <h3>投诉举报处理</h3>
                    <el-button type="text" size="small" @click="showAllComplaints">
                      查看全部
                    </el-button>
                  </div>
                  <div class="complaint-list">
                    <div
                      v-for="(complaint, index) in supervision.complaintList"
                      :key="index"
                      class="complaint-item"
                      @click="showComplaintDetail(complaint)"
                    >
                      <div class="item-header">
                        <span class="complaint-title">{{ complaint.title }}</span>
                        <i
                          :class="[
                            'status-icon',
                            complaint.processed ? 'el-icon-check success' : 'el-icon-close warning'
                          ]"
                        ></i>
                      </div>
                      <div class="item-content">{{ complaint.content }}</div>
                      <div class="item-footer">
                        <span class="complaint-time">{{ formatTime(complaint.time) }}</span>
                        <span
                          class="complaint-status"
                          :class="complaint.processed ? 'success' : 'warning'"
                        >
                          {{ complaint.status }}
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 资源信息工具提示 -->
    <div
      v-if="resourceTooltip.visible"
      class="resource-tooltip"
      :style="{ left: resourceTooltip.x + 'px', top: resourceTooltip.y + 'px' }"
    >
      <div class="tooltip-content">
        <div class="tooltip-title">{{ resourceTooltip.title }}</div>
        <div class="tooltip-details">
          <div v-for="(value, key) in resourceTooltip.details" :key="key" class="detail-row">
            <span class="detail-label">{{ key }}：</span>
            <span class="detail-value">{{ value }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import * as echarts from 'echarts'
import type { ECharts } from 'echarts'

// API导入
import {
  getAreaOptions,
  getDepartmentOptions,
  getResourceData,
  getCheckData,
  getCaseData,
  getSupervisionData
} from './SubdomainScenarioTopics.js'

// 全局筛选条件
const timePeriod = ref('today')
const areaId = ref('')
const deptId = ref('')
const areaOptions = ref<any[]>([])
const deptOptions = ref<any[]>([])

// 资源专题筛选
const resourceFilter = reactive({
  status: ''
})

// 检查专题筛选
const checkFilter = reactive({
  type: '',
  trendPeriod: '15days'
})

// 案件专题筛选
const caseFilter = reactive({
  type: [],
  department: ''
})

// 监督专题筛选
const supervisionFilter = reactive({
  type: '',
  period: '7days'
})

// 资源专题数据
const resources = reactive({
  staff: {
    value: 95.8,
    onDutyCount: 85,
    totalCount: 89,
    warning: false,
    top3Idle: [] as any[]
  },
  vehicle: {
    value: 72.5,
    inUseCount: 29,
    idleCount: 11,
    warning: true,
    top3Idle: [] as any[]
  },
  device: {
    value: 87.3,
    goodCount: 145,
    faultCount: 21,
    warning: true,
    top3Fault: [] as any[]
  },
  material: {
    value: 156,
    safeStock: 200,
    stockStatus: '充足',
    warning: false,
    lowStock: [] as any[]
  }
})

// 检查专题数据
const checks = reactive({
  planCompleteRate: 85.2,
  planTrend: 2.3,
  problemCount: 156,
  problemTrend: -8.5,
  rectifyRate: 78.5,
  rectifyTrend: 5.2,
  coverageRate: 92.8,
  coverageTrend: 1.7,
  progressRate: 85.2,
  completedCount: 120,
  uncompletedCount: 18,
  trendData: {
    dates: [] as string[],
    problems: [] as number[],
    rectifies: [] as number[]
  }
})

// 案件专题数据
const cases = reactive({
  stageData: [] as any[],
  sourceData: [] as any[],
  punishmentAmount: 1250000,
  punishmentTrend: 12.5,
  overdueList: [] as any[],
  stageChart: null as ECharts | null,
  sourceChart: null as ECharts | null
})

// 监督专题数据
const supervision = reactive({
  complianceRate: 96.5,
  complianceTrend: 0.8,
  checkCount: 120,
  complaintHandleRate: 94.2,
  complaintTrend: 1.2,
  complaintCount: 85,
  illegalCount: 5,
  illegalTrend: 2,
  creditScore: 4.2,
  creditTrend: 0.3,
  complaintList: [] as any[],
  trendData: {
    dates: [] as string[],
    rates: [] as number[]
  }
})

// 图表引用
const rectifyChartRef = ref<HTMLElement>()
const stageChartRef = ref<HTMLElement>()
const sourceChartRef = ref<HTMLElement>()
const complianceChartRef = ref<HTMLElement>()

let rectifyChart: ECharts | null = null
let stageChart: ECharts | null = null
let sourceChart: ECharts | null = null
let complianceChart: ECharts | null = null

// 资源工具提示
const resourceTooltip = reactive({
  visible: false,
  x: 0,
  y: 0,
  title: '',
  details: {} as Record<string, any>
})

// 初始化
onMounted(() => {
  loadAllData()
  initAreaOptions()
  initDeptOptions()
})

// 加载所有数据
const loadAllData = async () => {
  try {
    await Promise.all([
      loadResourceData(),
      loadCheckData(),
      loadCaseData(),
      loadSupervisionData()
    ])
    ElMessage.success('数据刷新成功')
  } catch (error) {
    console.error('加载数据失败:', error)
    ElMessage.error('数据加载失败')
  }
}

// 加载资源数据
const loadResourceData = async () => {
  try {
    const params = {
      timePeriod: timePeriod.value,
      areaId: areaId.value,
      deptId: deptId.value,
      status: resourceFilter.status
    }

    // 模拟API调用
    // const data = await getResourceData(params)
    const mockData = {
      staff: {
        value: 95.8,
        onDutyCount: 85,
        totalCount: 89,
        warning: 95.8 < 95, // 阈值95%
        top3Idle: [
          { id: 1, name: '张三', code: 'S001', position: '指挥中心', department: '办公室' },
          { id: 2, name: '李四', code: 'S002', position: '资料室', department: '档案科' },
          { id: 3, name: '王五', code: 'S003', position: '接待室', department: '信访科' }
        ]
      },
      vehicle: {
        value: 72.5,
        inUseCount: 29,
        idleCount: 11,
        warning: 72.5 < 85, // 阈值85%
        top3Idle: [
          { id: 1, code: 'V001', name: '执法车A', location: '停车场A区', status: '闲置' },
          { id: 2, code: 'V002', name: '执法车B', location: '停车场B区', status: '闲置' },
          { id: 3, code: 'V003', name: '执法车C', location: '维修站', status: '保养中' }
        ]
      },
      device: {
        value: 87.3,
        goodCount: 145,
        faultCount: 21,
        warning: 87.3 < 90, // 阈值90%
        top3Fault: [
          { id: 1, code: 'D001', name: '执法记录仪001', fault: '电池故障', reportTime: '2024-01-15' },
          { id: 2, code: 'D002', name: '对讲机002', fault: '信号异常', reportTime: '2024-01-14' },
          { id: 3, code: 'D003', name: '摄像机003', fault: '镜头模糊', reportTime: '2024-01-13' }
        ]
      },
      material: {
        value: 156,
        safeStock: 200,
        stockStatus: 156 < 200 ? '不足' : '充足',
        warning: 156 < 200,
        lowStock: [
          { id: 1, name: '防暴盾牌', current: 15, safe: 30 },
          { id: 2, name: '急救包', current: 8, safe: 20 },
          { id: 3, name: '反光背心', current: 25, safe: 50 }
        ]
      }
    }

    Object.assign(resources, mockData)

    // 添加动效
    resources.staff.top3Idle.forEach(item => {
      item.scrolling = true
    })
  } catch (error) {
    console.error('加载资源数据失败:', error)
  }
}

// 加载检查数据
const loadCheckData = async () => {
  try {
    const params = {
      type: checkFilter.type,
      trendPeriod: checkFilter.trendPeriod,
      areaId: areaId.value,
      deptId: deptId.value
    }

    // 模拟API调用
    const mockData = {
      planCompleteRate: 85.2,
      planTrend: 2.3,
      problemCount: 156,
      problemTrend: -8.5,
      rectifyRate: 78.5,
      rectifyTrend: 5.2,
      coverageRate: 92.8,
      coverageTrend: 1.7,
      progressRate: 85.2,
      completedCount: 120,
      uncompletedCount: 18,
      trendData: {
        dates: ['01-10', '01-11', '01-12', '01-13', '01-14', '01-15', '01-16'],
        problems: [8, 12, 10, 15, 9, 14, 11],
        rectifies: [5, 8, 7, 12, 6, 10, 9]
      }
    }

    Object.assign(checks, mockData)

    // 初始化或更新趋势图
    if (rectifyChartRef.value) {
      if (!rectifyChart) {
        rectifyChart = echarts.init(rectifyChartRef.value)
      }
      initRectifyChart()
    }
  } catch (error) {
    console.error('加载检查数据失败:', error)
  }
}

// 加载案件数据
const loadCaseData = async () => {
  try {
    const params = {
      type: caseFilter.type,
      department: caseFilter.department,
      areaId: areaId.value
    }

    // 模拟API调用
    const mockData = {
      stageData: [
        { stage: '受理', count: 45, color: '#3B82F6' },
        { stage: '调查', count: 38, color: '#1D4ED8' },
        { stage: '处罚', count: 28, color: '#60A5FA' },
        { stage: '超期', count: 12, color: '#EF4444' },
        { stage: '办结', count: 52, color: '#93C5FD' }
      ],
      sourceData: [
        { name: '热线举报', value: 85, color: '#F59E0B' },
        { name: '巡查发现', value: 56, color: '#3B82F6' },
        { name: '上级交办', value: 42, color: '#10B981' },
        { name: '其他', value: 38, color: '#8B5CF6' }
      ],
      punishmentAmount: 1250000,
      punishmentTrend: 12.5,
      overdueList: [
        {
          caseId: 'CASE20240115001',
          overdueDays: 3,
          description: 'XX小区违法建设案，调查阶段超期',
          selected: false,
          shake: false
        },
        {
          caseId: 'CASE20240112003',
          overdueDays: 5,
          description: 'XX工地夜间施工噪音扰民，处罚决定超期',
          selected: false,
          shake: true
        },
        {
          caseId: 'CASE20240110005',
          overdueDays: 7,
          description: 'XX街道占道经营，办结阶段超期',
          selected: false,
          shake: true
        },
        {
          caseId: 'CASE20240108002',
          overdueDays: 2,
          description: 'XX餐饮店油烟污染，受理阶段超期',
          selected: false,
          shake: false
        },
        {
          caseId: 'CASE20240105004',
          overdueDays: 4,
          description: 'XX公司违规排放，调查阶段超期',
          selected: false,
          shake: true
        }
      ]
    }

    Object.assign(cases, mockData)

    // 初始化图表
    nextTick(() => {
      initStageChart()
      initSourceChart()
    })
  } catch (error) {
    console.error('加载案件数据失败:', error)
  }
}

// 加载监督数据
const loadSupervisionData = async () => {
  try {
    const params = {
      type: supervisionFilter.type,
      period: supervisionFilter.period,
      areaId: areaId.value,
      deptId: deptId.value
    }

    // 模拟API调用
    const mockData = {
      complianceRate: 96.5,
      complianceTrend: 0.8,
      checkCount: 120,
      complaintHandleRate: 94.2,
      complaintTrend: 1.2,
      complaintCount: 85,
      illegalCount: 5,
      illegalTrend: 2,
      creditScore: 4.2,
      creditTrend: 0.3,
      complaintList: [
        {
          id: 1,
          title: '夜间施工噪音扰民',
          content: 'XX工地夜间10点后仍在施工，噪音严重影响周边居民休息...',
          time: '2024-01-15 20:30:00',
          status: '已处理',
          processed: true
        },
        {
          id: 2,
          title: '占道经营影响交通',
          content: 'XX路口占道经营严重，影响车辆通行...',
          time: '2024-01-14 09:15:00',
          status: '处理中',
          processed: false
        },
        {
          id: 3,
          title: '油烟污染严重',
          content: 'XX餐饮店油烟直排，严重影响周边环境...',
          time: '2024-01-13 18:45:00',
          status: '已处理',
          processed: true
        }
      ],
      trendData: {
        dates: ['01-10', '01-11', '01-12', '01-13', '01-14', '01-15', '01-16'],
        rates: [95, 96, 94, 97, 95, 98, 96]
      }
    }

    Object.assign(supervision, mockData)

    // 初始化图表
    if (complianceChartRef.value) {
      if (!complianceChart) {
        complianceChart = echarts.init(complianceChartRef.value)
      }
      initComplianceChart()
    }
  } catch (error) {
    console.error('加载监督数据失败:', error)
  }
}

// 初始化区域选项
const initAreaOptions = async () => {
  try {
    // const data = await getAreaOptions()
    areaOptions.value = [
      { label: 'XX市', value: 'city1' },
      { label: 'XX区', value: 'district1' },
      { label: 'XX街道', value: 'street1' }
    ]
  } catch (error) {
    console.error('加载区域选项失败:', error)
  }
}

// 初始化部门选项
const initDeptOptions = async () => {
  try {
    // const data = await getDepartmentOptions()
    deptOptions.value = [
      { label: '执法一队', value: 'dept1' },
      { label: '执法二队', value: 'dept2' },
      { label: '执法三队', value: 'dept3' }
    ]
  } catch (error) {
    console.error('加载部门选项失败:', error)
  }
}

// 初始化整改趋势图
const initRectifyChart = () => {
  if (!rectifyChart) return

  const option = {
    tooltip: {
      trigger: 'axis',
      formatter: (params: any) => {
        let html = `<div style="padding: 5px;">${params[0].axisValue}</div>`
        params.forEach((item: any) => {
          const color = item.seriesName === '发现问题' ? '#3B82F6' : '#22C55E'
          const icon = item.seriesName === '发现问题' ? '●' : '●'
          html += `<div>
            <span style="color: ${color};">${icon}</span>
            <span style="margin-left: 5px;">${item.seriesName}: ${item.value}</span>
          </div>`

          // 添加预警标注
          if (item.seriesName === '发现问题' && item.value > 10) {
            html += `<div style="color: #EF4444; font-size: 12px;">⚠ 问题较多</div>`
          }
        })
        return html
      }
    },
    legend: {
      data: ['发现问题', '已整改'],
      textStyle: { color: '#333' },
      top: 0
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: '15%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: checks.trendData.dates,
      axisLabel: { color: '#666' }
    },
    yAxis: {
      type: 'value',
      axisLabel: { color: '#666' }
    },
    series: [
      {
        name: '发现问题',
        type: 'line',
        data: checks.trendData.problems,
        smooth: true,
        itemStyle: {
          color: '#3B82F6'
        },
        lineStyle: {
          width: 3
        },
        symbolSize: 8,
        markPoint: {
          data: checks.trendData.problems.map((value, index) => ({
            name: '预警',
            value: value,
            xAxis: index,
            yAxis: value,
            symbol: 'triangle',
            symbolSize: 10,
            itemStyle: {
              color: value > 10 ? '#EF4444' : 'transparent'
            }
          }))
        }
      },
      {
        name: '已整改',
        type: 'line',
        data: checks.trendData.rectifies,
        smooth: true,
        itemStyle: {
          color: '#22C55E'
        },
        lineStyle: {
          width: 3
        },
        symbolSize: 8
      }
    ]
  }

  rectifyChart.setOption(option)
}

// 初始化案件阶段分布图
const initStageChart = () => {
  if (!stageChartRef.value) return

  if (!stageChart) {
    stageChart = echarts.init(stageChartRef.value)
  }

  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: '10%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: cases.stageData.map(item => item.stage),
      axisLabel: {
        color: '#666',
        interval: 0,
        rotate: 45
      }
    },
    yAxis: {
      type: 'value',
      axisLabel: { color: '#666' }
    },
    series: [{
      type: 'bar',
      data: cases.stageData.map(item => ({
        value: item.count,
        itemStyle: {
          color: item.stage === '超期' ? '#EF4444' : item.color
        }
      })),
      barWidth: '60%',
      itemStyle: {
        borderRadius: [4, 4, 0, 0]
      }
    }]
  }

  stageChart.setOption(option)

  // 添加闪烁效果
  if (cases.overdueList.some(item => item.shake)) {
    stageChart.dispatchAction({
      type: 'highlight',
      seriesIndex: 0,
      dataIndex: 3 // 超期阶段索引
    })

    setTimeout(() => {
      stageChart?.dispatchAction({
        type: 'downplay',
        seriesIndex: 0,
        dataIndex: 3
      })
    }, 1000)
  }
}

// 初始化案件来源分布图
const initSourceChart = () => {
  if (!sourceChartRef.value) return

  if (!sourceChart) {
    sourceChart = echarts.init(sourceChartRef.value)
  }

  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: 10,
      top: 'center',
      textStyle: { color: '#666' }
    },
    series: [{
      name: '案件来源',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '16',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: cases.sourceData.map(item => ({
        value: item.value,
        name: item.name,
        itemStyle: { color: item.color }
      }))
    }]
  }

  sourceChart.setOption(option)
}

// 初始化合规趋势图
const initComplianceChart = () => {
  if (!complianceChart) return

  const option = {
    tooltip: {
      trigger: 'axis',
      formatter: (params: any) => {
        const value = params[0].value
        let html = `<div style="padding: 5px;">${params[0].axisValue}</div>`
        html += `<div>
          <span style="color: #3B82F6;">●</span>
          <span style="margin-left: 5px;">合规率: ${value}%</span>
        </div>`

        if (value < 95) {
          html += `<div style="color: #EF4444; font-size: 12px;">⚠ 低于阈值</div>`
        }
        return html
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: '15%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: supervision.trendData.dates,
      axisLabel: { color: '#666' }
    },
    yAxis: {
      type: 'value',
      min: 0,
      max: 100,
      axisLabel: {
        color: '#666',
        formatter: '{value}%'
      }
    },
    series: [{
      name: '合规率',
      type: 'line',
      data: supervision.trendData.rates,
      smooth: true,
      itemStyle: {
        color: '#3B82F6'
      },
      lineStyle: {
        width: 3
      },
      symbolSize: 8,
      markLine: {
        data: [{
          yAxis: 95,
          lineStyle: {
            color: '#EF4444',
            type: 'dashed'
          },
          label: {
            formatter: '阈值 95%',
            position: 'end'
          }
        }],
        symbol: 'none'
      },
      markPoint: {
        data: supervision.trendData.rates.map((value, index) => ({
          name: '预警',
          value: value,
          xAxis: index,
          yAxis: value,
          symbol: 'triangle',
          symbolSize: 10,
          itemStyle: {
            color: value < 95 ? '#EF4444' : 'transparent'
          }
        }))
      }
    }]
  }

  complianceChart.setOption(option)
}

// 工具方法
const getStatusClass = (resource: any) => {
  if (resource.warning) return 'warning'
  return 'normal'
}

const getStatusText = (resource: any) => {
  if (resource.warning) return '异常'
  return '正常'
}

const getStatusClassByRate = (rate: number) => {
  if (rate < 80) return 'warning'
  if (rate < 95) return 'normal'
  return 'success'
}

const getTrendClass = (trend: number) => {
  if (trend > 0) return 'positive'
  if (trend < 0) return 'negative'
  return 'normal'
}

const formatAmount = (amount: number) => {
  return amount.toLocaleString('zh-CN')
}

const formatTime = (time: string) => {
  if (!time) return ''
  return time.substring(5, 16).replace(' ', ' ')
}

// 交互方法
const showResourceDetail = (type: string) => {
  // 跳转到资源详情页面
  window.open(`/law/enforcement/resource/${type}`, '_blank')
}

const showResourceTooltip = (item: any, type: string) => {
  let details: Record<string, any> = {}
  let title = ''

  switch (type) {
    case 'staff':
      title = '执法人员信息'
      details = {
        '姓名': item.name,
        '编号': item.code,
        '岗位': item.position,
        '部门': item.department
      }
      break
    case 'vehicle':
      title = '执法车辆信息'
      details = {
        '车牌号': item.code,
        '名称': item.name,
        '位置': item.location,
        '状态': item.status
      }
      break
    case 'device':
      title = '执法设备信息'
      details = {
        '设备编号': item.code,
        '设备名称': item.name,
        '故障描述': item.fault,
        '报修时间': item.reportTime
      }
      break
    case 'material':
      title = '应急物资信息'
      details = {
        '物资名称': item.name,
        '当前库存': item.current,
        '安全库存': item.safe,
        '缺口数量': item.safe - item.current
      }
      break
  }

  resourceTooltip.visible = true
  resourceTooltip.title = title
  resourceTooltip.details = details
  resourceTooltip.x = event?.clientX || 0
  resourceTooltip.y = event?.clientY || 0
}

const hideResourceTooltip = () => {
  resourceTooltip.visible = false
}

const dispatchResource = (item: any, type: string) => {
  ElMessageBox.confirm(
    `确定要调度${type === 'staff' ? '人员' : type === 'vehicle' ? '车辆' : '设备'} ${item.name || item.code} 吗？`,
    '资源调度确认',
    {
      confirmButtonText: '确认调度',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    ElMessage.success('调度指令已发送')
    // 跳转到调度页面
    window.open('/law/enforcement/resource-dispatch', '_blank')
  }).catch(() => {})
}

const showRepairDialog = (item: any) => {
  ElMessageBox.prompt('请输入维修说明', `报修设备：${item.name}`, {
    confirmButtonText: '提交',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入维修说明...'
  }).then(({ value }) => {
    ElMessage.success('报修申请已提交')
  }).catch(() => {})
}

const replenishMaterial = (item: any) => {
  ElMessageBox.prompt('请输入补货数量', `补货物资：${item.name}`, {
    confirmButtonText: '提交',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入补货数量...',
    inputPattern: /^[1-9]\d*$/,
    inputErrorMessage: '请输入正确的数量'
  }).then(({ value }) => {
    ElMessage.success('补货申请已提交')
  }).catch(() => {})
}

const showCheckDetail = (type: string) => {
  // 跳转到检查详情页面
  window.open(`/law/enforcement/check/${type}`, '_blank')
}

const showUncompletedPlans = () => {
  window.open('/law/enforcement/uncompleted-plans', '_blank')
}

const showPunishmentDetail = () => {
  window.open('/law/enforcement/punishment-statistics', '_blank')
}

const toggleCaseSelection = (caseItem: any) => {
  caseItem.selected = !caseItem.selected
}

const showCaseDetail = (caseItem: any) => {
  // 弹出案件详情对话框
  ElMessageBox.alert(
    `
    <div class="case-detail-popup">
      <h3>${caseItem.caseId}</h3>
      <div class="detail-grid">
        <div><strong>超期天数：</strong>${caseItem.overdueDays}天</div>
        <div><strong>案件描述：</strong>${caseItem.description}</div>
      </div>
    </div>
    `,
    '案件详情',
    {
      dangerouslyUseHTMLString: true
    }
  )
}

const dispatchCase = (caseItem: any) => {
  ElMessageBox.prompt('请输入派单备注', `派单案件：${caseItem.caseId}`, {
    confirmButtonText: '确认派单',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入派单备注...'
  }).then(({ value }) => {
    ElMessage.success('派单成功')
  }).catch(() => {})
}

const showUrgentDialog = (caseItem: any) => {
  ElMessageBox.prompt('请输入催办内容', `催办案件：${caseItem.caseId}`, {
    confirmButtonText: '发送',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入催办内容...'
  }).then(({ value }) => {
    ElMessage.success('催办通知已发送')
  }).catch(() => {})
}

const showAllOverdueCases = () => {
  window.open('/law/enforcement/overdue-cases', '_blank')
}

const showSupervisionDetail = (type: string) => {
  // 跳转到监督详情页面
  window.open(`/law/enforcement/supervision/${type}`, '_blank')
}

const showComplaintDetail = (complaint: any) => {
  // 弹出投诉详情对话框
  ElMessageBox.alert(
    `
    <div class="complaint-detail-popup">
      <h3>${complaint.title}</h3>
      <div class="detail-grid">
        <div><strong>投诉时间：</strong>${complaint.time}</div>
        <div><strong>处理状态：</strong>${complaint.status}</div>
        <div><strong>投诉内容：</strong>${complaint.content}</div>
      </div>
    </div>
    `,
    '投诉详情',
    {
      dangerouslyUseHTMLString: true
    }
  )
}

const showAllComplaints = () => {
  window.open('/law/enforcement/complaints', '_blank')
}

const gotoResourceDispatch = () => {
  window.open('/law/enforcement/resource-dispatch', '_blank')
}

const resetFilters = () => {
  timePeriod.value = 'today'
  areaId.value = ''
  deptId.value = ''
  resourceFilter.status = ''
  checkFilter.type = ''
  checkFilter.trendPeriod = '15days'
  caseFilter.type = []
  caseFilter.department = ''
  supervisionFilter.type = ''
  supervisionFilter.period = '7days'

  loadAllData()
}

// 窗口大小变化监听
window.addEventListener('resize', () => {
  rectifyChart?.resize()
  stageChart?.resize()
  sourceChart?.resize()
  complianceChart?.resize()
})
</script>

<style lang="scss" scoped>
@import './common-styles.scss';
@import './Subdom.scss';
.page-container {
  background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
  color: #333;
  height: 100vh;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.subdomain-scenario-themes {
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 0 20px;
  box-sizing: border-box;
}

// 顶部筛选栏
.theme-filters {
  display: flex;
  align-items: center;
  gap: 15px;
  padding: 12px 0;
  border-bottom: 1px solid rgba(0, 204, 255, 0.2);
  background: rgba(30, 41, 59, 0.8);
  margin-bottom: 15px;

  .filter-group {
    display: flex;
    align-items: center;
    gap: 8px;

    .filter-label {
      color: #00ccff;
      font-size: 14px;
      white-space: nowrap;
    }

    :deep(.el-select) {
      width: 180px;
    }
  }
}

// 三部分布局
.three-part-layout {
  display: flex;
  flex-direction: column;
  flex: 1;
  gap: 15px;
  height: calc(100vh - 100px);
  box-sizing: border-box;
  overflow: hidden;
}

// 顶部区域：执法资源专题
.top-section.resource-section {
  height: 300px;
  min-height: 300px;

  .panel {
    height: 100%;

    .panel-body {
      padding: 0;
      height: calc(100% - 50px);
    }
  }
}

// 资源卡片网格
.resource-cards-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 15px;
  height: 100%;
  padding: 15px;
  box-sizing: border-box;

  .resource-card {
    background: #ECFEFF; // 浅青色背景
    border-radius: 8px;
    padding: 15px;
    position: relative;
    overflow: hidden;
    transition: all 0.3s ease;
    cursor: pointer;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);

    &.warning {
      animation: breatheWarning 1s infinite alternate;
    }

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
    }

    .card-breathe-effect {
      position: absolute;
      top: -2px;
      left: -2px;
      right: -2px;
      bottom: -2px;
      border: 2px solid #EF4444;
      border-radius: 8px;
      animation: breatheWarning 1s infinite alternate;
      pointer-events: none;
    }

    .card-content {
      position: relative;
      z-index: 1;
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 15px;

      .resource-title {
        font-size: 16px;
        font-weight: 600;
        color: #0891B2; // 深蓝色标题
        display: flex;
        align-items: center;
        gap: 8px;

        i {
          font-size: 18px;
        }
      }

      .resource-status {
        font-size: 12px;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 500;

        &.normal {
          background: rgba(34, 197, 94, 0.1);
          color: #22C55E; // 正常绿色
        }

        &.warning {
          background: rgba(239, 68, 68, 0.1);
          color: #EF4444; // 异常红色
        }
      }
    }

    .resource-value {
      font-size: 32px;
      font-weight: bold;
      color: #000; // 黑色粗体
      margin-bottom: 15px;
      display: flex;
      align-items: baseline;

      .unit {
        font-size: 16px;
        margin-left: 4px;
        color: #666;
      }

      .animated-value {
        transition: all 0.5s ease-in-out;
      }
    }

    .resource-progress {
      margin-bottom: 15px;

      .progress-track {
        height: 8px;
        background: #E2E8F0;
        border-radius: 4px;
        overflow: hidden;

        .progress-fill {
          height: 100%;
          border-radius: 4px;
          background: linear-gradient(90deg, #22C55E, #10B981);
          transition: width 0.5s ease;

          &.warning {
            background: linear-gradient(90deg, #EF4444, #DC2626);
          }
        }
      }
    }

    .resource-details {
      .detail-item {
        display: flex;
        justify-content: space-between;
        margin-bottom: 6px;
        font-size: 14px;

        .detail-value {
          font-weight: 500;

          &.warning {
            color: #EF4444;
          }
        }
      }

      .detail-list {
        margin-top: 10px;
        padding-top: 10px;
        border-top: 1px solid rgba(0, 0, 0, 0.1);

        .list-title {
          font-size: 12px;
          color: #666;
          margin-bottom: 5px;
        }

        .list-item {
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding: 4px 8px;
          margin-bottom: 4px;
          background: rgba(255, 255, 255, 0.5);
          border-radius: 4px;
          font-size: 12px;
          cursor: pointer;
          transition: all 0.2s ease;
          position: relative;
          overflow: hidden;

          &.warning {
            background: rgba(239, 68, 68, 0.1);
          }

          &:hover {
            background: rgba(0, 0, 0, 0.05);
            transform: translateX(3px);
          }

          .list-action {
            color: #0891B2;
            cursor: pointer;
            padding: 2px 6px;
            border-radius: 3px;
            background: rgba(8, 145, 178, 0.1);
            transition: all 0.2s ease;

            &:hover {
              background: rgba(8, 145, 178, 0.2);
            }
          }

          &.scrolling {
            animation: scrollUp 10s linear infinite;
          }
        }
      }
    }
  }
}

// 中部区域：左检查右案件
.middle-section {
  display: flex;
  flex: 1;
  gap: 15px;
  min-height: 400px;

  .left-section.check-section,
  .right-section.case-section {
    flex: 1;
    min-width: 0;
    min-height: 0;
    display: flex;
    flex-direction: column;
  }

  .panel {
    flex: 1;
    display: flex;
    flex-direction: column;
  }
}

// 检查专题样式
.check-section {
  .check-content {
    display: flex;
    flex-direction: column;
    gap: 15px;
    height: 100%;
  }

  .check-indicators-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);
    gap: 10px;

    .check-card {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 12px;
      cursor: pointer;
      transition: all 0.3s ease;
      position: relative;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

      &:hover {
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
      }

      &.warning {
        animation: breatheWarning 1s infinite alternate;
      }

      .check-title {
        font-size: 12px;
        color: #666;
        margin-bottom: 8px;
      }

      .check-value {
        font-size: 24px;
        font-weight: bold;
        color: #333;
        margin-bottom: 4px;

        &.warning {
          color: #EF4444;
        }
      }

      .check-trend {
        font-size: 12px;

        &.positive {
          color: #22C55E;
        }

        &.negative {
          color: #EF4444;
        }

        &.normal {
          color: #666;
        }
      }

      .warning-mark {
        position: absolute;
        top: 5px;
        right: 5px;
        color: #EF4444;
        animation: blinkWarning 1s infinite;
      }
    }
  }

  .check-charts-row {
    display: flex;
    flex: 1;
    gap: 15px;
    min-height: 200px;

    .progress-container {
      flex: 0.4;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      cursor: pointer;
      transition: all 0.3s ease;

      &:hover {
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
      }

      .progress-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;

        .progress-title {
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }

        .progress-rate {
          font-size: 18px;
          font-weight: bold;
          color: #0891B2;
        }
      }

      .progress-bar {
        height: 12px;
        background: #94A3B8; // 灰色未完成
        border-radius: 6px;
        overflow: hidden;
        display: flex;

        .progress-fill {
          height: 100%;
          background: linear-gradient(90deg, #22C55E, #10B981); // 绿色完成
          transition: width 1s ease;

          &.animated {
            animation: fillProgress 1s ease-out;
          }
        }

        .uncompleted-part {
          height: 100%;
          background: rgba(148, 163, 184, 0.5);
        }
      }

      .progress-stats {
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
        font-size: 12px;
        color: #666;
      }
    }

    .trend-chart-container {
      flex: 0.6;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      display: flex;
      flex-direction: column;

      .chart-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;

        h3 {
          margin: 0;
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }

        .chart-legend {
          display: flex;
          gap: 15px;
          font-size: 12px;

          .legend-item {
            display: flex;
            align-items: center;
            gap: 4px;

            .dot {
              width: 8px;
              height: 8px;
              border-radius: 50%;
            }

            &.problem .dot {
              background: #3B82F6;
            }

            &.rectify .dot {
              background: #22C55E;
            }
          }
        }
      }

      .chart-wrapper {
        flex: 1;
        min-height: 150px;
      }
    }
  }
}

// 案件专题样式
.case-section {
  .case-content {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: auto auto 1fr;
    gap: 15px;
    height: 100%;
    box-sizing: border-box;

    .stage-chart-container,
    .source-chart-container {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      display: flex;
      flex-direction: column;

      .chart-header {
        margin-bottom: 10px;

        h3 {
          margin: 0;
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }
      }

      .chart-wrapper {
        flex: 1;
        min-height: 150px;
      }
    }

    .stage-chart-container {
      grid-column: 1;
      grid-row: 1;
    }

    .source-chart-container {
      grid-column: 2;
      grid-row: 1;
    }

    .amount-card {
      grid-column: 1 / span 2;
      grid-row: 2;
      background: linear-gradient(135deg, #D97706, #FBBF24); // 金色渐变
      border-radius: 6px;
      padding: 20px;
      cursor: pointer;
      transition: all 0.3s ease;
      text-align: center;
      position: relative;
      overflow: hidden;

      &:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 25px rgba(217, 119, 6, 0.3);
      }

      .amount-title {
        font-size: 16px;
        color: rgba(255, 255, 255, 0.9);
        margin-bottom: 10px;
      }

      .amount-value {
        font-size: 32px;
        font-weight: bold;
        color: white;
        margin-bottom: 8px;
        text-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);

        .animated-value {
          transition: all 0.5s ease-in-out;
        }
      }

      .amount-trend {
        font-size: 14px;
        color: rgba(255, 255, 255, 0.8);

        &.positive {
          color: #22C55E;
        }

        &.negative {
          color: #EF4444;
        }
      }
    }

    .overdue-section {
      grid-column: 1 / span 2;
      grid-row: 3;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      display: flex;
      flex-direction: column;
      min-height: 0;

      .section-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;

        h4 {
          margin: 0;
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }
      }

      .overdue-list {
        flex: 1;
        overflow-y: auto;
        @include custom-scrollbar;

        .overdue-item {
          padding: 10px;
          margin-bottom: 8px;
          background: #FEF2F2; // 红色背景
          border: 1px solid #FECACA;
          border-radius: 4px;
          cursor: pointer;
          transition: all 0.3s ease;
          position: relative;

          &.shake {
            animation: shakeWarning 2s infinite;
          }

          &:hover {
            background: #FEE2E2;
            transform: translateX(3px);
          }

          &.selected {
            background: #FEE2E2;
            border-color: #EF4444;
          }

          .case-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 4px;

            .case-id {
              font-size: 12px;
              font-weight: 500;
              color: #333;
            }

            .overdue-days {
              font-size: 11px;
              color: #EF4444;
              background: rgba(239, 68, 68, 0.1);
              padding: 2px 8px;
              border-radius: 12px;
              font-weight: 500;
            }
          }

          .case-desc {
            font-size: 12px;
            color: #666;
            margin-bottom: 8px;
            line-height: 1.4;
          }

          .case-actions {
            display: flex;
            gap: 10px;
            justify-content: flex-end;

            :deep(.el-button) {
              font-size: 12px;
              padding: 2px 8px;
              height: auto;
            }
          }
        }
      }
    }
  }
}

// 底部区域：执法监督专题
.bottom-section.supervision-section {
  height: 300px;
  min-height: 300px;

  .panel {
    height: 100%;

    .panel-body {
      padding: 0;
      height: calc(100% - 50px);
    }
  }

  .supervision-content {
    display: flex;
    flex-direction: column;
    gap: 15px;
    height: 100%;
    padding: 15px;
    box-sizing: border-box;
  }

  .supervision-indicators-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);
    gap: 10px;

    .supervision-card {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 12px;
      cursor: pointer;
      transition: all 0.3s ease;
      position: relative;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

      &.warning {
        animation: breatheWarning 1s infinite alternate;
      }

      &:hover {
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
      }

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        margin-bottom: 8px;

        .supervision-title {
          font-size: 12px;
          color: #666;
          flex: 1;
        }

        .supervision-value {
          font-size: 20px;
          font-weight: bold;
          margin-left: 8px;

          &.warning {
            color: #EF4444;
          }

          &.success {
            color: #22C55E;
          }

          &.normal {
            color: #333;
          }
        }
      }

      .supervision-trend {
        font-size: 12px;
        margin-bottom: 6px;

        &.positive {
          color: #22C55E;
        }

        &.negative {
          color: #EF4444;
        }

        &.normal {
          color: #666;
        }

        &.warning {
          color: #EF4444;
        }
      }

      .supervision-note {
        font-size: 10px;
        color: #999;
      }

      .pulse-effect {
        position: absolute;
        top: -2px;
        left: -2px;
        right: -2px;
        bottom: -2px;
        border-radius: 6px;
        animation: pulseWarning 1s infinite;
        pointer-events: none;
        border: 2px solid #EF4444;
      }

      .credit-stars {
        display: flex;
        gap: 2px;
        margin-top: 6px;

        .star {
          font-size: 14px;
          color: #E5E7EB;
          transition: all 0.3s ease;

          &.active {
            color: #F59E0B;
            animation: starLight 0.5s ease forwards;
            animation-delay: calc(var(--star-index) * 0.1s);
          }

          &.el-icon-star-on {
            color: #F59E0B;
          }
        }
      }
    }
  }

  .supervision-charts-row {
    display: flex;
    flex: 1;
    gap: 15px;
    min-height: 150px;

    .compliance-trend-chart {
      flex: 0.6;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      display: flex;
      flex-direction: column;

      .chart-header {
        margin-bottom: 10px;

        h3 {
          margin: 0;
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }
      }

      .chart-wrapper {
        flex: 1;
        min-height: 120px;
      }
    }

    .complaint-handling-list {
      flex: 0.4;
      background: rgba(255, 255, 255, 0.9);
      border-radius: 6px;
      padding: 15px;
      display: flex;
      flex-direction: column;
      min-height: 0;

      .list-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;

        h3 {
          margin: 0;
          font-size: 14px;
          color: #333;
          font-weight: 500;
        }
      }

      .complaint-list {
        flex: 1;
        overflow-y: auto;
        @include custom-scrollbar;

        .complaint-item {
          padding: 10px;
          margin-bottom: 8px;
          background: rgba(255, 255, 255, 0.5);
          border-radius: 4px;
          cursor: pointer;
          transition: all 0.3s ease;

          &:hover {
            background: rgba(0, 0, 0, 0.05);
            transform: translateX(3px);
          }

          .item-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 6px;

            .complaint-title {
              font-size: 13px;
              font-weight: 500;
              color: #333;
              flex: 1;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }

            .status-icon {
              font-size: 14px;
              margin-left: 8px;

              &.success {
                color: #22C55E;
              }

              &.warning {
                color: #EF4444;
              }
            }
          }

          .item-content {
            font-size: 12px;
            color: #666;
            margin-bottom: 6px;
            line-height: 1.4;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
          }

          .item-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 10px;
            color: #999;

            .complaint-status {
              padding: 2px 6px;
              border-radius: 10px;
              font-size: 10px;

              &.success {
                background: rgba(34, 197, 94, 0.1);
                color: #22C55E;
              }

              &.warning {
                background: rgba(239, 68, 68, 0.1);
                color: #EF4444;
              }
            }
          }
        }
      }
    }
  }
}

// 资源工具提示
.resource-tooltip {
  position: fixed;
  background: rgba(30, 41, 59, 0.95);
  border: 1px solid $primary-color;
  border-radius: 8px;
  padding: 12px;
  color: $text-white;
  font-size: 12px;
  z-index: 10000;
  pointer-events: none;
  max-width: 300px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(10px);

  .tooltip-content {
    .tooltip-title {
      font-size: 14px;
      font-weight: 600;
      color: $primary-color;
      margin-bottom: 8px;
      padding-bottom: 4px;
      border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    }

    .tooltip-details {
      .detail-row {
        display: flex;
        margin-bottom: 4px;

        .detail-label {
          color: $text-light;
          flex: 0 0 80px;
        }

        .detail-value {
          color: $text-white;
          flex: 1;
          font-weight: 500;
        }
      }
    }
  }
}

// 动画定义
@keyframes breatheWarning {
  0% {
    box-shadow: 0 0 0 0 rgba(239, 68, 68, 0.4);
  }
  70% {
    box-shadow: 0 0 0 10px rgba(239, 68, 68, 0);
  }
  100% {
    box-shadow: 0 0 0 0 rgba(239, 68, 68, 0);
  }
}

@keyframes blinkWarning {
  0%, 100% {
    opacity: 1;
  }
  50% {
    opacity: 0.3;
  }
}

@keyframes fillProgress {
  0% {
    width: 0%;
  }
  100% {
    width: var(--progress-width);
  }
}

@keyframes scrollUp {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-100%);
  }
}

@keyframes shakeWarning {
  0%, 100% {
    transform: translateX(0);
  }
  10%, 30%, 50%, 70%, 90% {
    transform: translateX(-3px);
  }
  20%, 40%, 60%, 80% {
    transform: translateX(3px);
  }
}

@keyframes pulseWarning {
  0% {
    opacity: 0.5;
    transform: scale(1);
  }
  50% {
    opacity: 1;
    transform: scale(1.02);
  }
  100% {
    opacity: 0.5;
    transform: scale(1);
  }
}

@keyframes starLight {
  0% {
    transform: scale(0.5);
    opacity: 0;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

// 响应式调整
@media (max-width: 1600px) {
  .theme-filters .filter-group :deep(.el-select) {
    width: 160px !important;
  }

  .resource-cards-grid {
    gap: 12px;
    padding: 12px;

    .resource-card {
      padding: 12px;

      .resource-value {
        font-size: 28px;
      }
    }
  }

  .check-charts-row {
    .progress-container {
      flex: 0.5;
    }

    .trend-chart-container {
      flex: 0.5;
    }
  }

  .supervision-indicators-grid .supervision-card {
    padding: 10px;

    .supervision-value {
      font-size: 18px;
    }
  }
}

@media (max-width: 1400px) {
  .middle-section {
    flex-direction: column;
  }

  .resource-cards-grid {
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);
  }

  .case-content {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(5, auto);
    gap: 10px;

    .stage-chart-container {
      grid-column: 1;
      grid-row: 1;
    }

    .source-chart-container {
      grid-column: 1;
      grid-row: 2;
    }

    .amount-card {
      grid-column: 1;
      grid-row: 3;
    }

    .overdue-section {
      grid-column: 1;
      grid-row: 4;
    }
  }

  .supervision-charts-row {
    flex-direction: column;

    .compliance-trend-chart,
    .complaint-handling-list {
      flex: 1;
    }
  }
}

@media (max-width: 768px) {
  .theme-filters {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;

    .filter-group {
      width: 100%;

      :deep(.el-select) {
        width: 100% !important;
      }
    }
  }

  .resource-cards-grid {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(4, 1fr);
    gap: 10px;
  }

  .check-indicators-grid {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(4, 1fr);
  }

  .supervision-indicators-grid {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(4, 1fr);
  }
}

</style>
