<template>
  <div class="page-container">
    <!-- 加载遮罩 -->
    <div v-if="loading" class="loading-overlay">
      <div class="loading-content">
        <el-icon class="is-loading" color="#00ccff" size="32"><Loading /></el-icon>
        <div class="loading-text">数据加载中...</div>
      </div>
    </div>

    <div class="mainbox">
      <div class="top">
        <!-- 左侧：教育核心指标 -->
        <div class="top_left">
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanel">
            <div class="panel-header">
              <h2><el-icon><TrendCharts /></el-icon> 教育核心指标</h2>
              <div class="header-actions">
                <el-select v-model="eduDomainFilter" placeholder="指标类型" size="small"
                           @change="handleEduDomainChange">
                  <el-option label="全部" value="" />
                  <el-option label="入学类" value="入学类" />
                  <el-option label="师资类" value="师资类" />
                  <el-option label="学业类" value="学业类" />
                </el-select>
                <el-select v-model="indicatorTimeRange" placeholder="时间周期" size="small"
                           @change="handleIndicatorTimeRangeChange">
                  <el-option label="近7日" value="7days" />
                  <el-option label="近30日" value="30days" />
                </el-select>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen(coreIndicatorsPanel)">
                  <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <div v-if="filteredIndicators.length === 0" class="empty-state">
                <el-icon class="empty-icon"><DataBoard /></el-icon>
                <div class="empty-text">暂无指标数据</div>
              </div>
              <div v-else class="indicator-cards-grid">
                <div v-for="indicator in filteredIndicators" :key="indicator.indicator_id"
                     :class="['indicator-card-enhanced', indicator.warn_status === '1' ? 'warning' : 'normal']"
                     @click="showIndicatorDetail(indicator)">
                  <div class="indicator-header">
                    <div class="indicator-title">{{ indicator.indicator_name }}</div>
                    <div class="indicator-status">
                      <el-icon v-if="indicator.warn_status === '1'" color="#EF4444" class="warning-icon">
                        <Warning />
                      </el-icon>
                      <el-icon v-else color="#22C55E" class="normal-icon">
                        <CircleCheck />
                      </el-icon>
                    </div>
                  </div>
                  <div class="indicator-main">
                    <div class="indicator-value-section">
                      <div class="indicator-value">
                        <span class="animated-value" :data-value="indicator.real_value">
                          {{ indicator.real_value }}
                        </span>
                        <span class="indicator-unit">{{ indicator.unit }}</span>
                      </div>
                      <div class="indicator-compliance">
                        达标率: <span class="compliance-value">{{ indicator.compliance_rate }}%</span>
                      </div>
                    </div>
                    <div class="indicator-trend-section">
                      <div class="mini-chart-container">
                        <div class="mini-chart" :id="'miniChart-' + indicator.indicator_id"></div>
                      </div>
                    </div>
                  </div>
                  <div class="indicator-footer">
                    <div class="indicator-yoy" :class="indicator.year_on_year >= 0 ? 'positive' : 'negative'">
                      同比: {{ indicator.year_on_year >= 0 ? '+' : '' }}{{ indicator.year_on_year }}%
                    </div>
                    <div class="indicator-update">
                      {{ formatUpdateTime(indicator.update_time) }}
                    </div>
                  </div>
                  <div v-if="indicator.warn_status === '1'" class="warning-border"></div>
                  <div class="pulse-effect" :class="indicator.warn_status === '1' ? 'warning' : 'normal'"></div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>

        <!-- 中间：教育资源分布视图 -->
        <div class="top_middle">
          <div class="panel force-distribution-panel" ref="mapPanel">
            <div class="panel-header">
              <h2><el-icon><MapLocation /></el-icon> 教育资源分布视图</h2>
              <div class="header-actions">
                <el-select v-model="forceTypeFilter" placeholder="资源类型" size="small"
                           @change="handleForceTypeChange">
                  <el-option label="全部" value="" />
                  <el-option label="教师" value="teacher" />
                  <el-option label="校车" value="schoolbus" />
                  <el-option label="学校" value="school" />
                </el-select>
                <el-select v-model="eduTeamFilter" placeholder="教研组" size="small"
                           @change="handleEduTeamChange">
                  <el-option label="全部" value="" />
                  <el-option label="语文组" value="语文组" />
                  <el-option label="数学组" value="数学组" />
                  <el-option label="英语组" value="英语组" />
                </el-select>
                <el-button size="small" type="primary" @click="refreshForceDistribution">
                  <el-icon><Refresh /></el-icon>刷新
                </el-button>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen(mapPanel)">
                  <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body map-container">
              <div class="map-wrapper">
                <MapCommon idName="chinaEcharts" :geometriesArray="filteredForceGeometries"
                           @marker-click="handleMarkerClick" style="height:100%" />
              </div>
              <div class="force-stats-overlay">
                <div class="force-stats-cards">
                  <div class="force-stat-card" @click="handleStatCardClick('teacher')">
                    <div class="stat-icon teacher"><el-icon><User /></el-icon></div>
                    <div class="stat-content">
                      <div class="stat-title">专任教师总数</div>
                      <div class="stat-value">{{ eduForceStats.total_teacher_count || 0 }}</div>
                      <div class="stat-sub">在线: {{ eduForceStats.online_teacher_count || 0 }}</div>
                    </div>
                    <div class="stat-trend positive"><el-icon><Top /></el-icon></div>
                  </div>
                  <div class="force-stat-card" @click="handleStatCardClick('schoolbus')">
                    <div class="stat-icon schoolbus"><el-icon><Van /></el-icon></div>
                    <div class="stat-content">
                      <div class="stat-title">校车总数</div>
                      <div class="stat-value">{{ eduForceStats.total_schoolbus_count || 0 }}</div>
                      <div class="stat-sub">活跃: {{ eduForceStats.active_schoolbus_count || 0 }}</div>
                    </div>
                    <div class="stat-trend positive"><el-icon><Top /></el-icon></div>
                  </div>
                  <div class="force-stat-card" @click="handleStatCardClick('school')">
                    <div class="stat-icon school"><el-icon><OfficeBuilding /></el-icon></div>
                    <div class="stat-content">
                      <div class="stat-title">学校总数</div>
                      <div class="stat-value">{{ eduForceStats.total_school_count || 0 }}</div>
                      <div class="stat-sub">所</div>
                    </div>
                    <div class="stat-trend normal"><el-icon><Minus /></el-icon></div>
                  </div>
                </div>
                <div class="region-force-distribution">
                  <h4>区域资源分布</h4>
                  <div class="region-list">
                    <div v-for="region in regionForceDistribution" :key="region.region_name" class="region-item"
                         @click="handleRegionClick(region.region_name)">
                      <div class="region-name">{{ region.region_name }}</div>
                      <div class="region-stats">
                        <span class="stat-badge teacher">{{ region.teacher_count }}师</span>
                        <span class="stat-badge schoolbus">{{ region.schoolbus_count }}车</span>
                        <span class="stat-badge school">{{ region.school_count }}校</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>

        <!-- 右侧：教育全域数据预览 -->
        <div class="top_right">
          <div class="panel" ref="regionPatternPanel" style="height: 100%;">
            <div class="panel-header">
              <h2><el-icon><DataAnalysis /></el-icon> 教育全域数据</h2>
              <div class="header-actions compact-actions">
                <el-select v-model="overviewTimeRange" placeholder="时间范围" size="small" class="compact-filter"
                           @change="handleTimeRangeChange">
                  <el-option label="今日" value="today" />
                  <el-option label="本周" value="week" />
                  <el-option label="本月" value="month" />
                </el-select>

                <!-- 行政区划筛选框 -->
                <el-select v-model="areaFilter" placeholder="行政区划" size="small" class="compact-filter"
                           @change="handleAreaChange">
                  <el-option label="全部" value="" />
                  <el-option label="鼓楼区" value="gulou" />
                  <el-option label="台江区" value="taijiang" />
                  <el-option label="仓山区" value="cangshan" />
                  <el-option label="马尾区" value="mawei" />
                  <el-option label="晋安区" value="jinan" />
                </el-select>

                <button class="panel-fullscreen-btn compact-btn" @click="togglePanelFullscreen(regionPatternPanel)">
                  <el-icon color="#00ccff" size="16"><FullScreen /></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body" style="flex: 1; overflow: hidden; padding: 0;">
              <!-- 统一为网格卡片 -->
              <div class="indicator-cards-grid">
                <div v-for="stat in eduOverviewStats" :key="stat.id"
                     :class="['indicator-card-enhanced', stat.warning ? 'warning' : 'normal', stat.id === 3 ? 'white-bg' : '']"
                     @click="showStatDetail(stat)" @mouseenter="showTooltip(stat.calculation)"
                     @mouseleave="hideTooltip">

                  <div class="indicator-header">
                    <div class="indicator-title">{{ stat.title }}</div>
                    <div class="indicator-status">
                      <el-icon v-if="stat.warning" color="#EF4444" class="warning-icon">
                        <Warning />
                      </el-icon>
                      <el-icon v-else color="#22C55E" class="normal-icon">
                        <CircleCheck />
                      </el-icon>
                    </div>
                  </div>

                  <div class="indicator-main">
                    <div class="indicator-value-section">
                      <div class="indicator-value">
                        <span class="animated-value" :data-value="stat.value">{{ stat.value }}</span>
                        <span class="indicator-unit">{{ stat.unit }}</span>
                      </div>
                      <div class="indicator-compliance">
                        趋势: <span class="compliance-value">{{ stat.rateText }}</span>
                      </div>
                    </div>
                  </div>

                  <div class="indicator-footer">
                    <div class="indicator-yoy" :class="stat.rate >= 0 ? 'positive' : 'negative'">
                      同比: {{ stat.rate >= 0 ? '+' : '' }}{{ stat.rate }}%
                    </div>
                  </div>

                  <div v-if="stat.hasPulse" class="pulse-effect" :class="stat.warning ? 'warning' : 'normal'"></div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>

      <!-- 底部：教育服务总览 -->
      <div class="bottom">
        <div class="bottom_left">
          <div class="panel service-overview-panel" ref="serviceOverviewPanel">
            <div class="panel-header">
              <h2>
                <el-icon>
                  <List />
                </el-icon>
                教育服务总览
              </h2>
              <div class="header-actions">
                <el-select v-model="serviceTypeFilter" placeholder="服务类型" size="small"
                           @change="handleServiceTypeChange">
                  <el-option label="全部" value="" />
                  <el-option label="教学类" value="教学类" />
                  <el-option label="后勤类" value="后勤类" />
                  <el-option label="安全类" value="安全类" />
                  <el-option label="信息化类" value="信息化类" />
                </el-select>
                <el-select v-model="handleDeptFilter" placeholder="办理部门" size="small"
                           @change="handleHandleDeptChange">
                  <el-option label="全部" value="" />
                  <el-option label="教学设备科" value="教学设备科" />
                  <el-option label="后勤保障科" value="后勤保障科" />
                  <el-option label="安全管理科" value="安全管理科" />
                  <el-option label="信息化科" value="信息化科" />
                </el-select>
                <el-select v-model="serviceTimeRange" placeholder="时间周期" size="small"
                           @change="handleServiceTimeRangeChange">
                  <el-option label="今日" value="today" />
                  <el-option label="本周" value="week" />
                  <el-option label="本月" value="month" />
                </el-select>
                <el-button size="small" type="primary" @click="exportServiceData">
                  <el-icon>
                    <Download />
                  </el-icon>导出
                </el-button>
                <el-button size="small" type="primary" @click="showServiceList">
                  <el-icon>
                    <Menu />
                  </el-icon>服务列表
                </el-button>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen(serviceOverviewPanel)">
                  <el-icon color="#00ccff" size="16">
                    <FullScreen />
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 超期服务预警条 -->
              <div class="overdue-warning-section" v-if="serviceOverview && serviceOverview.overdue_service_count > 0">
                <div class="overdue-warning-bar">
                  <div class="warning-content">
                    <el-icon color="#EF4444" size="16">
                      <Warning />
                    </el-icon>
                    <span class="warning-text">超期未办结服务：{{ serviceOverview.overdue_service_count }}件</span>
                    <span class="overdue-top3" @mouseenter="showOverdueTooltip"
                          @mouseleave="hideOverdueTooltip" @click="showOverdueServices">
                      TOP3: {{ getOverdueTop3() }}
                    </span>
                    <el-button size="small" type="danger" text @click="handleUrgeOverdue">催办</el-button>
                  </div>
                </div>
              </div>

              <div class="service-charts-container">
                <!-- 区域服务分布 -->
                <div class="chart-section">
                  <div class="chart-header">
                    <h3>区域服务分布</h3>
                    <el-button size="small" text @click="handleChartAction('region', 'drill')">下钻</el-button>
                  </div>
                  <div class="chart-container">
                    <ChartBar :xAxis="regionServiceXAxis" :series="regionServiceSeries" unit="件" :title="''"
                              height="100%" />
                  </div>
                </div>
                <!-- 服务类型分布 -->
                <div class="chart-section">
                  <div class="chart-header">
                    <h3>服务类型分布</h3>
                    <el-button size="small" text @click="handleChartAction('type', 'drill')">下钻</el-button>
                  </div>
                  <div class="chart-container">
                    <ChartPie3 :data="serviceTypePieData" :title="''" height="100%" />
                  </div>
                </div>
                <!-- 服务来源分布 -->
                <div class="chart-section">
                  <div class="chart-header">
                    <h3>服务来源分布</h3>
                    <el-button size="small" text @click="handleChartAction('source', 'drill')">下钻</el-button>
                  </div>
                  <div class="chart-container">
                    <ChartPie :data="serviceSourceData" :title="''" height="100%" />
                  </div>
                </div>

                <!-- 服务办理进度 -->
                <div class="chart-section">
                  <div class="chart-header">
                    <h3>服务办理进度</h3>
                    <el-button size="small" text @click="handleChartAction('progress', 'drill')">下钻</el-button>
                  </div>
                  <div class="chart-container">
                    <ChartBar :xAxis="progressXAxis" :series="progressSeries" unit="件" :title="''"
                              height="100%" />
                  </div>
                </div>
              </div>

            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 指标详情弹窗 -->
    <el-dialog v-model="indicatorDetailVisible" :title="currentIndicator?.indicator_name || '指标详情'" width="700px">
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="实时值">
              <span class="current-value">{{ currentIndicator?.real_value }}{{ currentIndicator?.unit }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="阈值范围">
              {{ currentIndicator?.threshold_min }} - {{ currentIndicator?.threshold_max }}{{ currentIndicator?.unit }}
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentIndicator?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentIndicator?.warn_status === '1' ? '预警' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="异常原因" v-if="currentIndicator?.warn_status === '1'">
              <el-input type="textarea" v-model="currentIndicator.abnormal_reason" rows="3"
                        @change="handleReasonChange" />
            </el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentIndicator?.update_time }}</el-descriptions-item>
            <el-descriptions-item label="数据来源">{{ currentIndicator?.ext1 }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>近7天趋势</h3>
          <div class="chart-container">
            <ChartLine3 :xAxis="indicatorTrendData.xAxis" :series="indicatorTrendData.series"
                        :unit="currentIndicator?.unit" :title="currentIndicator?.indicator_name" />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="showIndicatorConfig">修改阈值</el-button>
      </template>
    </el-dialog>
    <!-- 指标配置弹窗 -->
    <el-dialog v-model="indicatorConfigVisible" title="修改指标阈值" width="500px">
      <el-form :model="indicatorConfigForm" ref="indicatorConfigRef" label-width="100px">
        <el-form-item label="指标名称">
          <el-input v-model="indicatorConfigForm.indicator_name" disabled />
        </el-form-item>
        <el-form-item label="最小值">
          <el-input-number v-model="indicatorConfigForm.threshold_min" :min="0" />
        </el-form-item>
        <el-form-item label="最大值">
          <el-input-number v-model="indicatorConfigForm.threshold_max"
                           :min="indicatorConfigForm.threshold_min" />
        </el-form-item>
        <el-form-item label="单位">
          <el-input v-model="indicatorConfigForm.unit" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="indicatorConfigVisible = false">取消</el-button>
        <el-button type="primary" @click="saveIndicatorConfig">保存</el-button>
      </template>
    </el-dialog>
    <!-- 统计项详情弹窗 -->
    <el-dialog v-model="statDetailVisible" :title="currentStatDetail?.title || '统计详情'" width="600px">
      <div class="stat-detail-content" v-if="currentStatDetail">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="统计项">{{ currentStatDetail.title }}</el-descriptions-item>
          <el-descriptions-item label="当前值">
            <span class="current-value">{{ currentStatDetail.value }}{{ currentStatDetail.unit }}</span>
          </el-descriptions-item>
          <el-descriptions-item label="变化趋势">
            <span :class="getRateClass(currentStatDetail.rate)">
              {{ currentStatDetail.rateText }}
            </span>
          </el-descriptions-item>
          <el-descriptions-item label="计算方式">{{ currentStatDetail.calculation }}</el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag :type="currentStatDetail.warning ? 'danger' : 'success'">
              {{ currentStatDetail.warning ? '预警' : '正常' }}
            </el-tag>
          </el-descriptions-item>
        </el-descriptions>
        <div class="stat-analysis" v-if="currentStatDetail.analysis">
          <h3>分析说明</h3>
          <p>{{ currentStatDetail.analysis }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="statDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
    <!-- 服务详情弹窗 -->
    <el-dialog v-model="serviceDetailVisible" :title="currentServiceDetail?.service_title || '服务详情'" width="1200px">
      <div class="service-detail-content" v-if="currentServiceDetail">
        <!-- 基本信息 -->
        <el-descriptions title="基本信息" :column="3" border>
          <el-descriptions-item label="服务编号">{{ currentServiceDetail.service_id || '-' }}</el-descriptions-item>
          <el-descriptions-item label="服务类型">
            <el-tag :type="getServiceTypeTag(currentServiceDetail.service_type)">
              {{ currentServiceDetail.service_type || '-' }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="优先级">
            <el-tag :type="getPriorityTag(currentServiceDetail.priority)">
              {{ currentServiceDetail.priority || '-' }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="服务来源">{{ currentServiceDetail.service_source || '-' }}</el-descriptions-item>
          <el-descriptions-item label="受理时间">{{ currentServiceDetail.apply_time || '-' }}</el-descriptions-item>
          <el-descriptions-item label="办理时限">{{ currentServiceDetail.due_time || '-' }}</el-descriptions-item>
          <el-descriptions-item label="当前进度">
            <el-progress :percentage="getProgressPercentage(currentServiceDetail.current_progress)"
                         :status="getProgressStatus(currentServiceDetail.current_progress)" style="width: 120px" />
            {{ currentServiceDetail.current_progress || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="办理部门">{{ currentServiceDetail.handle_dept || '-' }}</el-descriptions-item>
          <el-descriptions-item label="承办人员">{{ currentServiceDetail.handle_staff || '-' }}</el-descriptions-item>
          <el-descriptions-item label="服务状态">
            <el-tag :type="getStatusTag(currentServiceDetail.status)">
              {{ currentServiceDetail.status || '处理中' }}
            </el-tag>
          </el-descriptions-item>
        </el-descriptions>

        <!-- 服务位置 -->
        <div class="detail-section" v-if="currentServiceDetail.location">
          <h3>服务位置</h3>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="详细地址">{{ currentServiceDetail.location.address || '-' }}</el-descriptions-item>
            <el-descriptions-item label="所在区域">{{ currentServiceDetail.location.district || '-' }}</el-descriptions-item>
            <el-descriptions-item label="坐标位置">{{ currentServiceDetail.location.coord || '-' }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 服务描述 -->
        <div class="detail-section">
          <h3>服务描述</h3>
          <div class="service-description">
            <p>{{ currentServiceDetail.service_description || '暂无详细描述' }}</p>
          </div>
        </div>

        <!-- 相关当事人 -->
        <div class="detail-section" v-if="currentServiceDetail.involved_parties && currentServiceDetail.involved_parties.length > 0">
          <h3>相关当事人</h3>
          <el-table :data="currentServiceDetail.involved_parties" border>
            <el-table-column prop="name" label="姓名" width="100" />
            <el-table-column prop="type" label="类型" width="100" />
            <el-table-column prop="contact" label="联系方式" width="120" />
            <el-table-column prop="identity" label="身份信息" />
          </el-table>
        </div>

        <!-- 证据材料 -->
        <div class="detail-section" v-if="currentServiceDetail.evidence_materials && currentServiceDetail.evidence_materials.length > 0">
          <h3>证据材料</h3>
          <el-table :data="currentServiceDetail.evidence_materials" border>
            <el-table-column prop="type" label="类型" width="100" />
            <el-table-column prop="name" label="文件名称" />
            <el-table-column prop="time" label="上传时间" width="150" />
            <el-table-column prop="uploader" label="上传人" width="100" />
            <el-table-column label="操作" width="80">
              <template #default="{ row }">
                <el-button size="small" text @click="viewEvidence(row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 办理记录 -->
        <div class="detail-section" v-if="currentServiceDetail.progress_records && currentServiceDetail.progress_records.length > 0">
          <h3>办理记录</h3>
          <el-timeline>
            <el-timeline-item v-for="(record, index) in currentServiceDetail.progress_records" :key="index"
                              :timestamp="record.time" :type="getTimelineType(record.action)" placement="top">
              <div class="timeline-content">
                <div class="action-header">
                  <strong>{{ record.action || '操作' }}</strong>
                  <span class="operator"> - {{ record.operator || '系统' }}</span>
                </div>
                <p class="action-description">{{ record.description || '暂无描述' }}</p>
                <div v-if="record.attachments && record.attachments.length > 0" class="attachments">
                  <el-tag v-for="attachment in record.attachments" :key="attachment" size="small" type="info"
                          class="attachment-tag">
                    {{ attachment }}
                  </el-tag>
                </div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>

        <!-- 下一步行动 -->
        <div class="detail-section" v-if="currentServiceDetail.next_actions && currentServiceDetail.next_actions.length > 0">
          <h3>下一步行动</h3>
          <el-table :data="currentServiceDetail.next_actions" border>
            <el-table-column prop="action" label="行动内容" />
            <el-table-column prop="deadline" label="完成时限" width="150" />
            <el-table-column prop="responsible" label="负责人" width="100" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="{ row }">
                <el-tag :type="getActionStatusTag(row.status)" size="small">
                  {{ row.status || '待完成' }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 服务统计 -->
        <div class="detail-section" v-if="currentServiceDetail.statistics">
          <h3>服务统计</h3>
          <div class="statistics-grid">
            <div class="stat-item">
              <div class="stat-label">已处理天数</div>
              <div class="stat-value">{{ currentServiceDetail.statistics.handle_days || 0 }}天</div>
            </div>
            <div class="stat-item">
              <div class="stat-label">超期天数</div>
              <div class="stat-value" :class="{ 'warning-text': (currentServiceDetail.statistics.overdue_days || 0) > 0 }">
                {{ currentServiceDetail.statistics.overdue_days || 0 }}天
              </div>
            </div>
            <div class="stat-item">
              <div class="stat-label">现场走访次数</div>
              <div class="stat-value">{{ currentServiceDetail.statistics.visit_times || 0 }}次</div>
            </div>
            <div class="stat-item">
              <div class="stat-label">证据材料数量</div>
              <div class="stat-value">{{ currentServiceDetail.statistics.evidence_count || 0 }}个</div>
            </div>
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="serviceDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
    <!-- 标记点详情弹窗 -->
    <el-dialog v-model="markerDetailVisible" :title="currentMarkerDetail?.title || '标记点详情'" width="500px">
      <div class="marker-detail-content" v-if="currentMarkerDetail">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="名称">{{ currentMarkerDetail.title }}</el-descriptions-item>
          <el-descriptions-item label="类型">
            <el-tag :type="getMarkerTypeTag(currentMarkerDetail.dataType)">
              {{ getMarkerTypeName(currentMarkerDetail.dataType) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag :type="getMarkerStatusTag(currentMarkerDetail.status)">
              {{ getMarkerStatusName(currentMarkerDetail.status) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item v-if="currentMarkerDetail.team" label="所属教研组">
            {{ currentMarkerDetail.team }}
          </el-descriptions-item>
          <el-descriptions-item v-if="currentMarkerDetail.type" label="车辆类型">
            {{ currentMarkerDetail.type }}
          </el-descriptions-item>
          <el-descriptions-item label="位置坐标">
            {{ currentMarkerDetail.position?.lat?.toFixed(6) }}, {{ currentMarkerDetail.position?.lng?.toFixed(6) }}
          </el-descriptions-item>
        </el-descriptions>

        <!-- 详细信息区域 -->
        <div class="marker-extra-info" v-if="currentMarkerDetail.originalData">
          <h3>详细信息</h3>
          <div class="info-grid">
            <div class="info-item" v-if="currentMarkerDetail.originalData.school">
              <span class="info-label">所属学校:</span>
              <span class="info-value">{{ currentMarkerDetail.originalData.school }}</span>
            </div>
            <div class="info-item" v-if="currentMarkerDetail.originalData.route">
              <span class="info-label">运行路线:</span>
              <span class="info-value">{{ currentMarkerDetail.originalData.route }}</span>
            </div>
          </div>
        </div>

        <!-- 操作记录 -->
        <div class="marker-actions" v-if="currentMarkerDetail.dataType === 'teacher'">
          <h3>今日任务</h3>
          <el-timeline>
            <el-timeline-item v-for="(task, index) in getTodayTasks(currentMarkerDetail)" :key="index"
                              :timestamp="task.time" :type="task.type">
              {{ task.content }}
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <template #footer>
        <el-button @click="markerDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="handleMarkerOperation('contact')"
                   v-if="currentMarkerDetail?.dataType === 'teacher'">
          联系教师
        </el-button>
        <el-button type="primary" @click="handleMarkerOperation('dispatch')"
                   v-if="currentMarkerDetail?.dataType === 'schoolbus'">
          调度校车
        </el-button>
        <el-button type="primary" @click="handleMarkerOperation('navigate')">
          导航至此
        </el-button>
      </template>
    </el-dialog>
    <!-- 工具提示 -->
    <el-tooltip v-model="tooltipVisible" :content="tooltipContent" placement="top" manual />
    <!-- 超期服务提示 -->
    <el-tooltip v-model="overdueTooltipVisible" placement="top" content="超期服务TOP3提示" manual>
      <div class="overdue-tooltip-content" v-if="overdueTooltipVisible">
        <div v-for="serviceItem in overdueTop3Services" :key="serviceItem.service_id" class="overdue-service-item">
          <span>服务{{ serviceItem.service_id }} - 超期{{ serviceItem.overdue_days }}天</span>
        </div>
      </div>
    </el-tooltip>
    <!-- 分析报告弹窗 -->
    <el-dialog v-model="reportVisible" :title="currentReport?.title || '分析报告'" width="800px">
      <div class="report-content" v-if="currentReport">
        <el-descriptions column="1" border>
          <el-descriptions-item label="统计时间">{{ currentReport.stat_time }}</el-descriptions-item>
          <el-descriptions-item label="创建人">{{ currentReport.create_user }}</el-descriptions-item>
        </el-descriptions>
        <div class="report-text">
          <h3>报告内容</h3>
          <p>{{ currentReport.content }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="reportVisible = false">关闭</el-button>
      </template>
    </el-dialog>
    <!-- 服务列表弹窗 -->
    <el-dialog v-model="serviceListVisible" title="服务列表" width="900px">
      <div class="service-list-content">
        <el-table :data="filteredServices" border style="width: 100%">
          <el-table-column prop="service_id" label="服务编号" width="120" />
          <el-table-column prop="service_type" label="服务类型" width="100" />
          <el-table-column prop="service_title" label="服务标题" min-width="180" />
          <el-table-column prop="apply_time" label="受理时间" width="150" />
          <el-table-column prop="overdue_days" label="超期天数" width="80">
            <template #default="{ row }">
              <el-tag v-if="row.overdue_days > 0" type="danger">{{ row.overdue_days }}天</el-tag>
              <span v-else>-</span>
            </template>
          </el-table-column>
          <el-table-column prop="current_progress" label="当前进度" width="80" />
          <el-table-column prop="handle_dept" label="办理部门" width="120" />
          <el-table-column label="操作" width="80">
            <template #default="{ row }">
              <el-button size="small" type="primary" text @click="viewServiceDetail(row.service_id)">详情</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <template #footer>
        <el-button @click="serviceListVisible = false">关闭</el-button>
      </template>
    </el-dialog>
    <!-- 服务类型下钻 -->
    <el-dialog v-model="chartDrillVisible.type" title="服务类型分布详情" width="800px">
      <div class="chart-drill-content">
        <div class="drill-header">
          <h3>服务类型详细统计</h3>
          <el-button size="small" @click="exportChartData('type')">
            <el-icon><Download /></el-icon>导出数据
          </el-button>
        </div>
        <el-table :data="chartDrillData.type" border style="width: 100%">
          <el-table-column prop="service_type" label="服务类型" width="120" />
          <el-table-column prop="service_count" label="服务数量" width="100" />
          <el-table-column prop="percentage" label="占比" width="100">
            <template #default="{ row }">
              {{ row.percentage }}%
            </template>
          </el-table-column>
          <el-table-column prop="trend" label="趋势" width="100">
            <template #default="{ row }">
              <el-tag :type="row.trend >= 0 ? 'success' : 'danger'" size="small">
                {{ row.trend >= 0 ? '+' : '' }}{{ row.trend }}%
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="avg_handle_time" label="平均办理时长" width="120" />
          <el-table-column prop="complete_rate" label="办结率" width="100">
            <template #default="{ row }">
              <span :class="row.complete_rate < 80 ? 'warning-text' : ''">
                {{ row.complete_rate }}%
              </span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="80">
            <template #default="{ row }">
              <el-button size="small" text @click="viewServiceListByType(row.service_type)">
                查看服务
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="drill-chart">
          <h4>近30天趋势</h4>
          <ChartLine3 :xAxis="typeTrendData.xAxis" :series="typeTrendData.series" height="200px" />
        </div>
      </div>
    </el-dialog>
    <!-- 区域分布下钻 -->
    <el-dialog v-model="chartDrillVisible.region" title="区域服务分布详情" width="900px">
      <div class="chart-drill-content">
        <div class="drill-header">
          <h3>各区域服务统计</h3>
          <div class="header-actions">
            <el-select v-model="regionTimeRange" size="small" @change="refreshRegionDrillData">
              <el-option label="近7天" value="7days" />
              <el-option label="近30天" value="30days" />
              <el-option label="近90天" value="90days" />
            </el-select>
            <el-button size="small" @click="exportChartData('region')">
              <el-icon><Download /></el-icon>导出数据
            </el-button>
          </div>
        </div>
        <el-table :data="chartDrillData.region" border style="width: 100%">
          <el-table-column prop="region_name" label="区域名称" width="120" />
          <el-table-column prop="total_services" label="服务总数" width="100" />
          <el-table-column prop="new_services" label="新增服务" width="100" />
          <el-table-column prop="overdue_services" label="超期服务" width="100">
            <template #default="{ row }">
              <el-tag v-if="row.overdue_services > 0" type="danger" size="small">
                {{ row.overdue_services }}
              </el-tag>
              <span v-else>{{ row.overdue_services }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="complete_rate" label="办结率" width="100" />
          <el-table-column prop="avg_handle_time" label="平均时长" width="100" />
          <el-table-column prop="teacher_count" label="教师数量" width="100" />
          <el-table-column label="操作" width="80">
            <template #default="{ row }">
              <el-button size="small" text @click="viewServiceListByRegion(row.region_name)">
                查看服务
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
    <!-- 来源分布下钻 -->
    <el-dialog v-model="chartDrillVisible.source" title="服务来源分布详情" width="700px">
      <div class="chart-drill-content">
        <div class="drill-header">
          <h3>服务来源分析</h3>
          <el-button size="small" @click="exportChartData('source')">
            <el-icon><Download /></el-icon>导出数据
          </el-button>
        </div>
        <el-table :data="chartDrillData.source" border style="width: 100%">
          <el-table-column prop="service_source" label="来源渠道" width="120" />
          <el-table-column prop="service_count" label="服务数量" width="100" />
          <el-table-column prop="percentage" label="占比" width="100" />
          <el-table-column prop="response_time" label="平均响应时间" width="120" />
          <el-table-column prop="satisfaction_rate" label="满意度" width="100" />
          <el-table-column prop="trend" label="趋势" width="100">
            <template #default="{ row }">
              <el-tag :type="row.trend >= 0 ? 'success' : 'danger'" size="small">
                {{ row.trend >= 0 ? '+' : '' }}{{ row.trend }}%
              </el-tag>
            </template>
          </el-table-column>
        </el-table>
        <div class="drill-chart">
          <h4>各来源渠道趋势对比</h4>
          <ChartLine3 :xAxis="sourceTrendData.xAxis" :series="sourceTrendData.series" height="200px" />
        </div>
      </div>
    </el-dialog>

    <!-- 办理进度下钻弹窗 -->
    <el-dialog v-model="chartDrillVisible.progress" title="服务办理进度详情" width="800px">
      <div class="chart-drill-content">
        <div class="drill-header">
          <h3>服务办理进度分析</h3>
          <el-button size="small" @click="exportChartData('progress')">
            <el-icon><Download /></el-icon>导出数据
          </el-button>
        </div>

        <!-- 简化的表格，移除异常内容 -->
        <el-table :data="chartDrillData.progress" border style="width: 100%">
          <el-table-column prop="progress_stage" label="办理阶段" width="100" />
          <el-table-column prop="service_count" label="服务数量" width="100" />
          <el-table-column prop="percentage" label="占比" width="80">
            <template #default="{ row }">
              {{ row.percentage }}%
            </template>
          </el-table-column>
          <el-table-column prop="avg_stay_time" label="平均停留时间" width="120" />
          <el-table-column prop="next_stage_rate" label="进入下一阶段比例" width="140">
            <template #default="{ row }">
              {{ row.next_stage_rate }}%
            </template>
          </el-table-column>
          <el-table-column prop="overdue_rate" label="超期率" width="100">
            <template #default="{ row }">
              <el-tag v-if="row.overdue_rate > 10" type="danger" size="small">
                {{ row.overdue_rate }}%
              </el-tag>
              <span v-else>{{ row.overdue_rate }}%</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="100">
            <template #default="{ row }">
              <el-button size="small" text @click="viewServiceListByProgress(row.progress_stage)">
                查看服务
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>

    <!-- 导出进度弹窗 -->
    <el-dialog v-model="exportProgressVisible" title="数据导出" width="400px" :close-on-click-modal="false"
               :show-close="false">
      <div class="export-progress">
        <el-progress :percentage="exportProgress" :status="exportStatus" :stroke-width="8" />
        <p class="progress-text">{{ exportMessage }}</p>
      </div>
      <template #footer>
        <el-button v-if="exportStatus === 'success'" type="primary" @click="exportProgressVisible = false">
          完成
        </el-button>
        <el-button v-else-if="exportStatus === 'exception'" @click="retryExport">
          重试
        </el-button>
        <el-button v-else :disabled="true">
          导出中...
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
// 导入Vue核心API
import {ref, computed, onMounted, onUnmounted, nextTick} from 'vue';
// 导入Element Plus组件及图标
import {
  ElSelect, ElOption, ElButton, ElTag, ElInput, ElDialog,
  ElDescriptions, ElDescriptionsItem, ElForm, ElFormItem,
  ElTable, ElTableColumn,  ElTooltip, ElTimeline, ElTimelineItem, ElProgress
} from 'element-plus';
import {
  FullScreen, Warning, CircleCheck, User, Van, OfficeBuilding,
  Minus, Refresh, Download, Menu, List, DataAnalysis, MapLocation,
  TrendCharts, Loading, DataBoard, Top
} from "@element-plus/icons-vue";
import screenFull from 'screenfull';
import * as echarts from 'echarts';
import MapCommon from "./MapCommon.vue";
import ChartLine3 from './ChartLine3.vue';
import ChartBar from './ChartBar.vue';
import ChartPie from './ChartPie.vue';
import ChartPie3 from './ChartPie3.vue';

// 导入API方法
import {
  fetchEduGlobalOverview,
  fetchEduCoreIndicators,
  fetchEduForceDistribution,
  fetchServiceOverview,
  exportData,
  updateIndicatorReason,
  updateIndicatorThreshold,
  viewReport,
  fetchRealTimeLocations,
  filterServices,
  fetchServiceDetail
} from '@/api/overview/educationmanagement/GlobalSituationOverview.js';

// 路由实例
import { useRouter } from 'vue-router';

const router = useRouter();

// 使用 ref 直接引用DOM元素
const coreIndicatorsPanel = ref(null);
const mapPanel = ref(null);
const regionPatternPanel = ref(null);
const serviceOverviewPanel = ref(null);

// 响应式数据定义
const eduOverviewStats = ref([]);
const overviewChartData = ref({
  serviceTypeDistribution: [],
  regionServiceDistribution: []
});

const eduForceGeometries = ref([]);
const eduForceStats = ref({});
const regionForceDistribution = ref([]);

const coreIndicators = ref([]);
const serviceOverview = ref({});

// 筛选条件
const eduDomainFilter = ref('');
const indicatorTimeRange = ref('7days');
const forceTypeFilter = ref('');
const eduTeamFilter = ref('');
const overviewTimeRange = ref('today');
const areaFilter = ref([]);
const serviceTypeFilter = ref('');
const handleDeptFilter = ref('');
const serviceTimeRange = ref('today');

// 弹窗控制
const indicatorDetailVisible = ref(false);
const indicatorConfigVisible = ref(false);
const reportVisible = ref(false);
const serviceListVisible = ref(false);
const serviceDetailVisible = ref(false);
const statDetailVisible = ref(false);
const serviceStatDetailVisible = ref(false);
const markerDetailVisible = ref(false);

// 图表下钻弹窗控制
const chartDrillVisible = ref({
  type: false,
  region: false,
  source: false,
  progress: false
});

const chartDrillData = ref({
  type: [],
  region: [],
  source: [],
  progress: []
});

const regionTimeRange = ref('7days');

// 导出相关状态
const exportProgressVisible = ref(false);
const exportProgress = ref(0);
const exportStatus = ref('');
const exportMessage = ref('');
const currentExportParams = ref(null);

// 当前选中数据
const currentIndicator = ref(null);
const currentReport = ref(null);
const currentServiceDetail = ref(null);
const currentStatDetail = ref(null);
const currentServiceStatDetail = ref(null);
const currentMarkerDetail = ref(null);

// 趋势数据
const indicatorTrendData = ref({ xAxis: [], series: [] });
const typeTrendData = ref({ xAxis: [], series: [] });
const sourceTrendData = ref({ xAxis: [], series: [] });

// 进度流转数据
const progressFlow = ref([
  { stage: '受理', count: 156, next: true },
  { stage: '处理', count: 234, next: true },
  { stage: '审核', count: 345, next: true },
  { stage: '办结', count: 521, next: false }
]);

// 工具提示
const tooltipVisible = ref(false);
const tooltipContent = ref('');

// 超期服务相关
const overdueTooltipVisible = ref(false);
const overdueTop3Services = ref([]);
const filteredServices = ref([]);

// 指标配置表单
const indicatorConfigForm = ref({ indicator_name: '', threshold_min: 0, threshold_max: 0, unit: '' });
const indicatorConfigRef = ref(null);

// 图表加载状态
const chartLoaded = ref(false);

// 加载状态
const loading = ref(false);

// 行政区划选项
const areaOptions = ref([
  {
    value: 'fujian',
    label: '福建省',
    children: [
      {
        value: 'fuzhou',
        label: '福州市',
        children: [
          { value: 'gulou', label: '鼓楼区' },
          { value: 'taijiang', label: '台江区' },
          { value: 'cangshan', label: '仓山区' },
          { value: 'mawei', label: '马尾区' },
          { value: 'jinan', label: '晋安区' }
        ]
      }
    ]
  }
]);

// 计算属性 - 修复可选链操作符问题
const filteredIndicators = computed(() => {
  let filtered = coreIndicators.value;

  if (eduDomainFilter.value) {
    filtered = filtered.filter(indicator => {
      const indicatorName = indicator.indicator_name;
      if (eduDomainFilter.value === '入学类') {
        return indicatorName.includes('入园率') || indicatorName.includes('巩固率');
      } else if (eduDomainFilter.value === '师资类') {
        return indicatorName.includes('师生比') || indicatorName.includes('学历占比');
      } else if (eduDomainFilter.value === '学业类') {
        return indicatorName.includes('辍学率');
      }
      return true;
    });
  }

  return filtered;
});

const filteredForceGeometries = computed(() => {
  let filtered = eduForceGeometries.value;

  if (forceTypeFilter.value) {
    filtered = filtered.filter(item => item.dataType === forceTypeFilter.value);
  }

  if (eduTeamFilter.value) {
    filtered = filtered.filter(item => {
      if (eduTeamFilter.value === '语文组') return item.team === '语文组';
      if (eduTeamFilter.value === '数学组') return item.team === '数学组';
      if (eduTeamFilter.value === '英语组') return item.team === '英语组';
      return true;
    });
  }

  return filtered;
});

// 添加计算属性来简化模板中的条件判断
const serviceSourceData = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.source_distribution) {
    return { legend: [], series: [] };
  }
  return {
    legend: serviceOverview.value.source_distribution.map(item => item.service_source),
    series: [{
      name: '服务数量',
      data: serviceOverview.value.source_distribution.map(item => item.service_count)
    }]
  };
});

const serviceProgressData = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.progress_distribution) {
    return { xAxis: [], series: [] };
  }
  return {
    xAxis: serviceOverview.value.progress_distribution.map(item => item.progress_stage),
    series: [{
      name: '服务数量',
      data: serviceOverview.value.progress_distribution.map(item => item.service_count)
    }]
  };
});

const recentTrendData = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.recent_service_trend) {
    return { xAxis: [], series: [] };
  }
  return {
    xAxis: serviceOverview.value.recent_service_trend.map(item => item.date),
    series: [
      {
        name: '新增服务',
        data: serviceOverview.value.recent_service_trend.map(item => item.new_services)
      },
      {
        name: '办结服务',
        data: serviceOverview.value.recent_service_trend.map(item => item.complete_services)
      }
    ]
  };
});

// 方法定义
const handleEduDomainChange = () => {
  refreshCoreIndicators();
};

const handleIndicatorTimeRangeChange = () => {
  refreshCoreIndicators();
};

const handleForceTypeChange = () => {
  refreshForceDistribution();
};

const handleEduTeamChange = () => {
  refreshForceDistribution();
};

const handleTimeRangeChange = () => {
  refreshData();
};

const handleAreaChange = () => {
  refreshData();
};

const handleServiceTypeChange = () => {
  refreshServiceOverview();
};

const handleHandleDeptChange = () => {
  refreshServiceOverview();
};

const handleServiceTimeRangeChange = () => {
  refreshServiceOverview();
};

// 数据刷新函数
const refreshData = async () => {
  loading.value = true;
  try {
    const params = {
      timeRange: overviewTimeRange.value,
      area: areaFilter.value
    };

    const overviewData = await fetchEduGlobalOverview(params);
    eduOverviewStats.value = overviewData.stats || [];
    overviewChartData.value = {
      serviceTypeDistribution: overviewData.school_type_distribution || [],
      regionServiceDistribution: overviewData.region_school_distribution || []
    };

    await refreshCoreIndicators();
    await refreshForceDistribution();
    await refreshServiceOverview();

    initValueAnimations();
  } catch (error) {
    console.error('数据刷新失败:', error);
  } finally {
    loading.value = false;
  }
};

const refreshCoreIndicators = async () => {
  try {
    const params = {
      eduDomain: eduDomainFilter.value,
      timeRange: indicatorTimeRange.value
    };

    coreIndicators.value = await fetchEduCoreIndicators(params);

    nextTick(() => {
      initMiniCharts();
    });

  } catch (error) {
    console.error('核心指标刷新失败:', error);
  }
};

const refreshForceDistribution = async () => {
  try {
    const params = {
      forceType: forceTypeFilter.value,
      eduTeam: eduTeamFilter.value
    };

    const forceData = await fetchEduForceDistribution(params);
    eduForceGeometries.value = forceData.geometries || [];
    eduForceStats.value = forceData.stats || {};
    regionForceDistribution.value = forceData.region_force_distribution || [];

  } catch (error) {
    console.error('资源分布刷新失败:', error);
  }
};

const refreshServiceOverview = async () => {
  try {
    const params = {
      serviceType: serviceTypeFilter.value,
      handleDept: handleDeptFilter.value,
      timeRange: serviceTimeRange.value
    };

    serviceOverview.value = await fetchServiceOverview(params);

    if (serviceOverview.value && serviceOverview.value.overdue_services) {
      overdueTop3Services.value = serviceOverview.value.overdue_services.slice(0, 3);
    }

    initChartAnimations();

  } catch (error) {
    console.error('服务总览刷新失败:', error);
  }
};
// 全屏功能
const togglePanelFullscreen = (panelElement) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }

  if (!panelElement) {
    ElMessage.error('未找到面板元素');
    return;
  }

  if (screenFull.isFullscreen && document.fullscreenElement === panelElement) {
    screenFull.exit();
  } else {
    screenFull.request(panelElement);
  }
};

// 统计卡片点击事件
const handleStatCardClick = (type) => {
  forceTypeFilter.value = type;
  refreshForceDistribution();
};

const getForceTypeName = (type) => {
  const typeMap = {
    teacher: '教师',
    schoolbus: '校车',
    school: '学校'
  };
  return typeMap[type] || type;
};

// 区域点击事件
const handleRegionClick = (regionName) => {
};

// 全域数据概览点击事件 - 修改为弹出弹窗
const showStatDetail = (stat) => {
  currentStatDetail.value = { ...stat };
  // 添加分析说明
  currentStatDetail.value.analysis = getStatAnalysis(stat.id);
  statDetailVisible.value = true;
};

const getStatAnalysis = (statId) => {
  const analysisMap = {
    1: '当前学校总数反映了辖区内的教育机构总量，较上年增长2%，表明教育基础设施不断完善。',
    2: '在园在校学生数量持续增长，反映了人口增长和教育普及程度的提高。',
    3: '专任教师数量稳步增加，师资队伍建设成效显著。',
    4: '教育资源配置率低于85%阈值，需重点关注资源配置不均衡问题。',
    5: '教育服务办结率保持较高水平，服务质量持续优化。'
  };
  return analysisMap[statId] || '暂无详细分析数据。';
};

// 服务统计详情弹窗
const showServiceStatDetail = (type) => {
  currentServiceStatDetail.value = {
    type,
    title: getServiceStatTitle(type)
  };
  serviceStatDetailVisible.value = true;
};

const getServiceStatTitle = (type) => {
  const titleMap = {
    total: '服务总数',
    new: '今日新增服务',
    rate: '服务办结率',
    overdue: '超期未办结服务'
  };
  return titleMap[type] || '服务统计';
};

const getServiceStatValue = (type) => {
  const valueMap = {
    total: `${serviceOverview.value && serviceOverview.value.total_service_count ? serviceOverview.value.total_service_count : 0}件`,
    new: `${serviceOverview.value && serviceOverview.value.new_service_today ? serviceOverview.value.new_service_today : 0}件`,
    rate: `${serviceOverview.value && serviceOverview.value.service_complete_rate ? serviceOverview.value.service_complete_rate : 0}%`,
    overdue: `${serviceOverview.value && serviceOverview.value.overdue_service_count ? serviceOverview.value.overdue_service_count : 0}件`
  };
  return valueMap[type] || '-';
};

const getServiceStatTrendText = (type) => {
  const trendMap = {
    total: '较上月 +8%',
    new: '较昨日 +15%',
    rate: (serviceOverview.value && serviceOverview.value.service_complete_rate && serviceOverview.value.service_complete_rate < 90) ? '较上月 -2%' : '较上月 +2%',
    overdue: '较上周 +5%'
  };
  return trendMap[type] || '';
};

const getServiceStatTrendClass = (type) => {
  if (type === 'rate' && serviceOverview.value && serviceOverview.value.service_complete_rate && serviceOverview.value.service_complete_rate < 90) {
    return 'danger';
  }
  if (type === 'overdue') {
    return 'danger';
  }
  return 'positive';
};

const getServiceStatStatus = (type) => {
  if (type === 'rate' && serviceOverview.value && serviceOverview.value.service_complete_rate && serviceOverview.value.service_complete_rate < 90) {
    return 'danger';
  }
  if (type === 'overdue' && serviceOverview.value && serviceOverview.value.overdue_service_count && serviceOverview.value.overdue_service_count > 0) {
    return 'danger';
  }
  return 'success';
};

const getServiceStatStatusText = (type) => {
  if (type === 'rate' && serviceOverview.value && serviceOverview.value.service_complete_rate && serviceOverview.value.service_complete_rate < 90) {
    return '预警';
  }
  if (type === 'overdue' && serviceOverview.value && serviceOverview.value.overdue_service_count && serviceOverview.value.overdue_service_count > 0) {
    return '预警';
  }
  return '正常';
};

const getServiceStatAnalysis = (type) => {
  const analysisMap = {
    total: '服务总数反映了辖区内的整体教育服务工作量，需要合理分配服务资源。',
    new: '今日新增服务数量变化反映了日常教育服务需求，需关注重点时段和区域。',
    rate: '服务办结率是衡量教育服务效率的重要指标，低于90%需要重点关注处理效率。',
    overdue: '超期未办结服务会影响教育服务质量，需要及时催办和跟踪处理。'
  };
  return analysisMap[type] || '暂无详细分析数据。';
};

// 比率样式处理
const getRateClass = (value) => {
  if (value < 0) return 'danger';
  if (value === 0) return 'normal';
  return 'positive';
};

// 工具提示功能
const showTooltip = (content) => {
  tooltipContent.value = content;
  tooltipVisible.value = true;
};

const hideTooltip = () => {
  tooltipVisible.value = false;
};

// 指标详情相关
const showIndicatorDetail = (indicator) => {
  currentIndicator.value = { ...indicator };
  indicatorDetailVisible.value = true;

  if (indicator.trend_7days) {
    try {
      nextTick(() => {
        const trendData = JSON.parse(indicator.trend_7days);
        indicatorTrendData.value = {
          xAxis: trendData.xAxis,
          series: [{ name: indicator.indicator_name, data: trendData.series, type: 'line' }]
        };
      });
    } catch (e) {
      console.error('解析趋势数据失败:', e);
    }
  }
};

// 异常原因更新
const handleReasonChange = async () => {
  if (!currentIndicator.value) return;
  try {
    await updateIndicatorReason({
      indicator_id: currentIndicator.value.indicator_id,
      abnormal_reason: currentIndicator.value.abnormal_reason
    });
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) coreIndicators.value[index].abnormal_reason = currentIndicator.value.abnormal_reason;
    ElMessage.success('异常原因已更新');
  } catch (error) {
    ElMessage.error('更新失败: ' + (error.message || '未知错误'));
  }
};

// 指标配置
const showIndicatorConfig = () => {
  if (!currentIndicator.value) return;
  indicatorConfigForm.value = {
    indicator_name: currentIndicator.value.indicator_name,
    threshold_min: Number(currentIndicator.value.threshold_min),
    threshold_max: Number(currentIndicator.value.threshold_max),
    unit: currentIndicator.value.unit
  };
  indicatorConfigVisible.value = true;
};

const saveIndicatorConfig = async () => {
  if (!currentIndicator.value) return;
  try {
    await updateIndicatorThreshold({
      indicator_id: currentIndicator.value.indicator_id,
      threshold_min: indicatorConfigForm.value.threshold_min,
      threshold_max: indicatorConfigForm.value.threshold_max,
      unit: indicatorConfigForm.value.unit
    });
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) {
      coreIndicators.value[index].threshold_min = indicatorConfigForm.value.threshold_min;
      coreIndicators.value[index].threshold_max = indicatorConfigForm.value.threshold_max;
      coreIndicators.value[index].unit = indicatorConfigForm.value.unit;
    }
    currentIndicator.value.threshold_min = indicatorConfigForm.value.threshold_min;
    currentIndicator.value.threshold_max = indicatorConfigForm.value.threshold_max;
    currentIndicator.value.unit = indicatorConfigForm.value.unit;
    ElMessage.success('指标配置已保存');
    indicatorConfigVisible.value = false;
  } catch (error) {
    ElMessage.error('保存失败: ' + (error.message || '未知错误'));
  }
};

// 图表操作处理
const handleChartAction = (chartType, action) => {
  if (action === 'drill') {
    // 打开对应的下钻弹窗
    chartDrillVisible.value[chartType] = true;
    // 加载下钻数据
    loadChartDrillData(chartType);
  }
};

// 加载下钻数据
const loadChartDrillData = async (chartType) => {
  try {
    // 模拟API调用，实际项目中替换为真实接口
    const mockData = {
      type: [
        {
          service_type: '教学服务',
          service_count: 456,
          percentage: 36.3,
          trend: 5,
          avg_handle_time: '2.3天',
          complete_rate: 92
        },
        {
          service_type: '后勤服务',
          service_count: 342,
          percentage: 27.2,
          trend: -2,
          avg_handle_time: '3.1天',
          complete_rate: 88
        },
        {
          service_type: '安全服务',
          service_count: 240,
          percentage: 19.1,
          trend: 8,
          avg_handle_time: '4.2天',
          complete_rate: 85
        },
        {
          service_type: '信息化服务',
          service_count: 150,
          percentage: 11.9,
          trend: 12,
          avg_handle_time: '5.5天',
          complete_rate: 90
        },
        {
          service_type: '其他',
          service_count: 68,
          percentage: 5.4,
          trend: -3,
          avg_handle_time: '3.8天',
          complete_rate: 95
        }
      ],
      region: [
        {
          region_name: '高新区',
          total_services: 320,
          new_services: 45,
          overdue_services: 12,
          complete_rate: 92,
          avg_handle_time: '2.8天',
          teacher_count: 850
        },
        {
          region_name: '经开区',
          total_services: 280,
          new_services: 38,
          overdue_services: 8,
          complete_rate: 94,
          avg_handle_time: '2.5天',
          teacher_count: 720
        },
        {
          region_name: '城东区',
          total_services: 210,
          new_services: 28,
          overdue_services: 15,
          complete_rate: 85,
          avg_handle_time: '4.2天',
          teacher_count: 680
        }
      ],
      source: [
        {
          service_source: '学校上报',
          service_count: 623,
          percentage: 49.6,
          response_time: '2.1小时',
          satisfaction_rate: 88,
          trend: 5
        },
        {
          service_source: '家长反馈',
          service_count: 432,
          percentage: 34.4,
          response_time: '1.2小时',
          satisfaction_rate: 92,
          trend: 8
        },
        {
          service_source: '上级交办',
          service_count: 201,
          percentage: 16.0,
          response_time: '0.5小时',
          satisfaction_rate: 95,
          trend: -2
        }
      ],
      progress: [
        {
          progress_stage: '受理',
          service_count: 156,
          percentage: 12.4,
          avg_stay_time: '0.5天',
          next_stage_rate: 95,
          overdue_rate: 2
        },
        {
          progress_stage: '处理',
          service_count: 234,
          percentage: 18.6,
          avg_stay_time: '2.1天',
          next_stage_rate: 78,
          overdue_rate: 15
        },
        {
          progress_stage: '审核',
          service_count: 345,
          percentage: 27.5,
          avg_stay_time: '1.8天',
          next_stage_rate: 85,
          overdue_rate: 8
        },
        {
          progress_stage: '办结',
          service_count: 521,
          percentage: 41.5,
          avg_stay_time: '0天',
          next_stage_rate: 100,
          overdue_rate: 0
        }
      ]
    };

    chartDrillData.value[chartType] = mockData[chartType];

    // 设置趋势数据
    if (chartType === 'type') {
      typeTrendData.value = {
        xAxis: ['10-01', '10-05', '10-10', '10-15', '10-20', '10-25', '10-30'],
        series: [
          { name: '教学服务', data: [120, 132, 101, 134, 90, 230, 210] },
          { name: '后勤服务', data: [150, 232, 201, 154, 190, 330, 410] },
          { name: '安全服务', data: [320, 332, 301, 334, 390, 330, 320] }
        ]
      };
    }

    if (chartType === 'source') {
      sourceTrendData.value = {
        xAxis: ['10-01', '10-05', '10-10', '10-15', '10-20', '10-25', '10-30'],
        series: [
          { name: '学校上报', data: [120, 132, 101, 134, 90, 230, 210] },
          { name: '家长反馈', data: [150, 232, 201, 154, 190, 330, 410] },
          { name: '上级交办', data: [320, 332, 301, 334, 390, 330, 320] }
        ]
      };
    }
  } catch (error) {
    console.error(`加载${chartType}下钻数据失败:`, error);
  }
};

// 导出图表数据
const exportChartData = async (chartType) => {
  try {
    const data = chartDrillData.value[chartType];
    const fileName = `服务${getChartTypeName(chartType)}统计_${new Date().toISOString().split('T')[0]}.xlsx`;

    // 实际项目中调用导出API
    await exportData(chartType, { data: data });
    ElMessage.success(`导出成功: ${fileName}`);
  } catch (error) {
    ElMessage.error('导出失败: ' + error.message);
  }
};

const getChartTypeName = (chartType) => {
  const nameMap = {
    type: '类型分布',
    region: '区域分布',
    source: '来源分布',
    progress: '办理进度'
  };
  return nameMap[chartType] || chartType;
};

// 查看特定类型服务列表
const viewServiceListByType = (serviceType) => {
  serviceTypeFilter.value = serviceType;
  serviceListVisible.value = true;
  // 加载对应类型的服务
  loadFilteredServices();
};

// 查看特定区域服务列表
const viewServiceListByRegion = (regionName) => {
  // 设置区域筛选并打开服务列表
  areaFilter.value = [regionName];
  serviceListVisible.value = true;
  loadFilteredServices();
};

// 查看特定进度服务列表
const viewServiceListByProgress = (progressStage) => {
  // 设置进度筛选并打开服务列表
  serviceListVisible.value = true;
  // 这里需要根据进度阶段筛选服务
  loadFilteredServicesByProgress(progressStage);
};

// 刷新区域下钻数据
const refreshRegionDrillData = () => {
  loadChartDrillData('region');
};

// 超期服务相关
const showOverdueTooltip = () => {
  overdueTooltipVisible.value = true;
};

const hideOverdueTooltip = () => {
  overdueTooltipVisible.value = false;
};

const getOverdueTop3 = () => {
  if (!overdueTop3Services.value.length) return '';
  return overdueTop3Services.value
    .slice(0, 3)
    .map(item => `服务${item.service_id}`)
    .join('、');
};

const showOverdueServices = () => {
  if (serviceOverview.value && serviceOverview.value.overdue_services && serviceOverview.value.overdue_services.length > 0) {
    serviceListVisible.value = true;
    filteredServices.value = serviceOverview.value.overdue_services;
    ElMessage.info('显示超期服务列表');
  }
};

const handleUrgeOverdue = () => {
  if (serviceOverview.value && serviceOverview.value.overdue_service_count && serviceOverview.value.overdue_service_count > 0) {
    ElMessage.warning(`已对${serviceOverview.value.overdue_service_count}件超期服务发送催办通知`);
  }
};

// 服务图表数据计算属性
const serviceTypePieData = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.type_distribution) {
    return { legend: [], series: [] };
  }
  return {
    legend: serviceOverview.value.type_distribution.map(item => item.service_type),
    series: [{
      name: '服务数量',
      data: serviceOverview.value.type_distribution.map(item => ({
        value: item.service_count,
        name: item.service_type,
        itemStyle: { color: item.color || getDefaultColor(item.service_type) }
      }))
    }]
  };
});

const regionServiceXAxis = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.region_distribution) {
    return ['高新区', '经开区', '城东区', '城西区', '城南区', '城北区'];
  }
  return serviceOverview.value.region_distribution.map(item => item.region_name);
});

const regionServiceSeries = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.region_distribution) {
    return [{
      name: '服务数量',
      type: 'bar',
      data: [320, 280, 210, 180, 150, 116]
    }];
  }
  return [{
    name: '服务数量',
    type: 'bar',
    data: serviceOverview.value.region_distribution.map(item => item.service_count),
    itemStyle: {
      color: '#3B82F6'
    }
  }];
});

const progressXAxis = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.progress_distribution) {
    return ['受理', '处理', '审核', '办结'];
  }
  return serviceOverview.value.progress_distribution.map(item => item.progress_stage);
});

const progressSeries = computed(() => {
  if (!serviceOverview.value || !serviceOverview.value.progress_distribution) {
    return [{
      name: '服务数量',
      type: 'bar',
      data: [156, 234, 345, 521]
    }];
  }
  return [{
    name: '服务数量',
    type: 'bar',
    data: serviceOverview.value.progress_distribution.map(item => item.service_count),
    itemStyle: {
      color: '#10B981'
    }
  }];
});

const getDefaultColor = (type) => {
  const colorMap = {
    '教学服务': '#3B82F6',
    '后勤服务': '#10B981',
    '安全服务': '#F59E0B',
    '信息化服务': '#EF4444',
    '其他': '#8B5CF6'
  };
  return colorMap[type] || '#6B7280';
};

// 导出服务数据
const exportServiceData = async () => {
  exportProgressVisible.value = true;
  exportProgress.value = 0;
  exportStatus.value = '';
  exportMessage.value = '准备导出数据...';

  try {
    // 构建导出参数
    const params = {
      serviceType: serviceTypeFilter.value,
      handleDept: handleDeptFilter.value,
      timeRange: serviceTimeRange.value,
      exportTime: new Date().toISOString()
    };
    currentExportParams.value = params;
    // 模拟进度更新
    const progressInterval = setInterval(() => {
      if (exportProgress.value < 90) {
        exportProgress.value += 10;
        exportMessage.value = `正在导出数据... ${exportProgress.value}%`;
      }
    }, 200);
    // 执行导出
    const result = await exportData('service', params);

    clearInterval(progressInterval);
    exportProgress.value = 100;
    exportStatus.value = 'success';
    exportMessage.value = `导出成功: ${result.fileName}`;

    ElMessage.success('服务数据导出成功');

  } catch (error) {
    exportProgress.value = 0;
    exportStatus.value = 'exception';
    exportMessage.value = `导出失败: ${error.message}`;
    ElMessage.error('导出失败: ' + error.message);
  }
};

// 重试导出
const retryExport = () => {
  if (currentExportParams.value) {
    exportServiceData();
  }
};

// 显示服务列表
const showServiceList = () => {
  serviceListVisible.value = true;
  filteredServices.value = (serviceOverview.value && serviceOverview.value.overdue_services) || [];
};

const viewServiceDetail = async (serviceId) => {
  try {
    console.log('正在获取服务详情，服务ID:', serviceId);
    const detail = await fetchServiceDetail(serviceId);
    console.log('获取到的服务详情:', detail);
    currentServiceDetail.value = detail;
    serviceDetailVisible.value = true;

    // 确保DOM更新后显示
    nextTick(() => {
      console.log('服务详情弹窗已打开');
    });
  } catch (error) {
    console.error('获取服务详情失败:', error);
    ElMessage.error('获取服务详情失败: ' + (error.message || '未知错误'));
  }
};
// 查看证据
const viewEvidence = (evidence) => {
};

// 分析报告
const viewAnalysisReport = async (reportId) => {
  try {
    currentReport.value = await viewReport(reportId);
    reportVisible.value = true;
  } catch (error) {
    ElMessage.error('查看报告失败: ' + (error.message || '未知错误'));
  }
};

// 格式化时间
const formatUpdateTime = (timeStr) => {
  if (!timeStr) return '';
  const date = new Date(timeStr);
  return `${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
};

// 动画相关
const initValueAnimations = () => {
  nextTick(() => {
    const valueElements = document.querySelectorAll('.animated-value');
    valueElements.forEach(element => {
      const currentValue = parseFloat(element.textContent) || 0;
      const targetValue = parseFloat(element.dataset.value) || 0;

      if (currentValue !== targetValue) {
        animateValue(element, currentValue, targetValue, 500);
      }
    });
  });
};

const animateValue = (element, start, end, duration) => {
  const startTime = performance.now();
  const updateValue = (currentTime) => {
    const elapsed = currentTime - startTime;
    const progress = Math.min(elapsed / duration, 1);

    const easeOutQuart = 1 - Math.pow(1 - progress, 4);
    const value = Math.floor(start + (end - start) * easeOutQuart);

    element.textContent = value.toLocaleString();

    if (progress < 1) {
      requestAnimationFrame(updateValue);
    }
  };
  requestAnimationFrame(updateValue);
};

// 小型折线图初始化
const initMiniCharts = () => {
  nextTick(() => {
    coreIndicators.value.forEach((indicator) => {
      const chartId = `miniChart-${indicator.indicator_id}`;
      const chartElement = document.getElementById(chartId);

      if (chartElement) {
        const chart = echarts.init(chartElement);

        if (indicator.trend_7days) {
          try {
            const trendData = JSON.parse(indicator.trend_7days);
            const isWarning = indicator.warn_status === '1';

            const option = {
              animation: false,
              grid: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2,
                containLabel: false
              },
              xAxis: {
                type: 'category',
                show: false,
                data: trendData.xAxis
              },
              yAxis: {
                type: 'value',
                show: false,
                min: Math.min(...trendData.series) * 0.9,
                max: Math.max(...trendData.series) * 1.1
              },
              series: [{
                type: 'line',
                data: trendData.series,
                smooth: true,
                symbol: 'none',
                lineStyle: {
                  width: 2,
                  color: isWarning ? '#EF4444' : '#22C55E'
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: isWarning ? 'rgba(239, 68, 68, 0.3)' : 'rgba(34, 197, 94, 0.3)' },
                    { offset: 1, color: isWarning ? 'rgba(239, 68, 68, 0)' : 'rgba(34, 197, 94, 0)' }
                  ])
                },
                emphasis: {
                  lineStyle: {
                    width: 3
                  }
                }
              }]
            };

            chart.setOption(option);

            chart.on('click', () => {
              showIndicatorDetail(indicator);
            });

          } catch (e) {
            console.error('初始化小型折线图失败:', e);
          }
        }
      }
    });
  });
};

// 图表动画初始化
const initChartAnimations = () => {
  nextTick(() => {
    const pieCharts = document.querySelectorAll('.chart-pie-container');
    pieCharts.forEach((chart, index) => {
      chart.style.animation = `pieSectorExpand 0.8s ease ${index * 0.1}s both`;
    });

    const barCharts = document.querySelectorAll('.chart-bar-container');
    barCharts.forEach((chart, index) => {
      chart.style.animation = `barExpand 0.6s ease ${index * 0.1}s both`;
    });
  });
};

// 实时位置更新
const refreshRealTimeLocations = async () => {
  try {
    await fetchRealTimeLocations();
  } catch (error) {
    console.error('实时位置更新失败:', error);
  }
};

// 标记点详情相关
// 处理地图标记点点击事件
const handleMarkerClick = (markerData) => {
  console.log('地图标记点被点击:', markerData);

  // 显示标记点详情弹窗
  currentMarkerDetail.value = {
    ...markerData,
    position: markerData.originalData ?
      { lat: markerData.originalData.x, lng: markerData.originalData.y } :
      null
  };
  // 使用 nextTick 确保 DOM 更新
  nextTick(() => {
    markerDetailVisible.value = true;
  });
};

// 获取标记点类型标签
const getMarkerTypeTag = (dataType) => {
  const tagMap = {
    'teacher': 'primary',
    'schoolbus': 'success',
    'school': 'warning'
  };
  return tagMap[dataType] || 'info';
};

// 获取标记点类型名称
const getMarkerTypeName = (dataType) => {
  const nameMap = {
    'teacher': '教师',
    'schoolbus': '校车',
    'school': '学校'
  };
  return nameMap[dataType] || dataType;
};

// 获取标记点状态标签
const getMarkerStatusTag = (status) => {
  const tagMap = {
    'online': 'success',
    'busy': 'warning',
    'offline': 'danger',
    'moving': 'primary',
    'parked': 'info',
    'normal': 'success'
  };
  return tagMap[status] || 'info';
};

// 获取标记点状态名称
const getMarkerStatusName = (status) => {
  const nameMap = {
    'online': '在线',
    'busy': '忙碌',
    'offline': '离线',
    'moving': '移动中',
    'parked': '已停放',
    'normal': '完好'
  };
  return nameMap[status] || status;
};

// 获取今日任务
const getTodayTasks = (marker) => {
  if (marker.dataType !== 'teacher') return [];

  // 模拟任务数据
  return [
    {
      time: '08:30',
      type: 'primary',
      content: '开始日常教学任务'
    },
    {
      time: '10:15',
      type: 'success',
      content: '处理教学服务申请'
    },
    {
      time: '14:20',
      type: 'warning',
      content: '参与教研活动'
    },
    {
      time: '16:45',
      type: 'info',
      content: '撰写教学报告'
    }
  ];
};

// 处理标记点操作
const handleMarkerOperation = (operation) => {
  if (!currentMarkerDetail.value) return;

  const operations = {
    'contact': `正在联系教师 ${currentMarkerDetail.value.title}...`,
    'dispatch': `正在调度校车 ${currentMarkerDetail.value.title}...`,
    'navigate': `正在导航至 ${currentMarkerDetail.value.title}...`
  };

  ElMessage.success(operations[operation] || '操作执行成功');

  // 关闭弹窗
  markerDetailVisible.value = false;
};

// 服务详情相关辅助函数
const getServiceTypeTag = (type) => {
  const typeMap = {
    '教学服务': 'primary',
    '后勤服务': 'success',
    '安全服务': 'warning',
    '信息化服务': 'danger'
  };
  return typeMap[type] || 'info';
};

const getPriorityTag = (priority) => {
  const priorityMap = {
    '高': 'danger',
    '中': 'warning',
    '低': 'success'
  };
  return priorityMap[priority] || 'info';
};

const getStatusTag = (status) => {
  const statusMap = {
    '处理中': 'primary',
    '已办结': 'success',
    '超期': 'danger',
    '暂停': 'warning'
  };
  return statusMap[status] || 'info';
};

const getProgressPercentage = (progress) => {
  const progressMap = {
    '受理': 25,
    '处理': 50,
    '审核': 75,
    '办结': 100
  };
  return progressMap[progress] || 0;
};

const getProgressStatus = (progress) => {
  return progress === '办结' ? 'success' : undefined;
};

const getTimelineType = (action) => {
  if (action.includes('受理')) return 'primary';
  if (action.includes('办结')) return 'success';
  if (action.includes('超期')) return 'danger';
  return 'info';
};

const getActionStatusTag = (status) => {
  const statusMap = {
    '待完成': 'warning',
    '进行中': 'primary',
    '已完成': 'success',
    '已取消': 'danger'
  };
  return statusMap[status] || 'info';
};

// 加载筛选后的服务
const loadFilteredServices = async () => {
  try {
    const filters = {
      serviceType: serviceTypeFilter.value,
      handleDept: handleDeptFilter.value,
      timeRange: serviceTimeRange.value
    };
    const result = await filterServices(filters);
    filteredServices.value = result.services || [];
  } catch (error) {
    console.error('加载筛选服务失败:', error);
    filteredServices.value = [];
  }
};

const loadFilteredServicesByProgress = async (progressStage) => {
  try {
    const filters = {
      progressStage: progressStage,
      timeRange: serviceTimeRange.value
    };
    const result = await filterServices(filters);
    filteredServices.value = result.services || [];
  } catch (error) {
    console.error('加载进度服务失败:', error);
    filteredServices.value = [];
  }
};

// 生命周期
onMounted(() => {
  refreshData();
  initMiniCharts();
  initValueAnimations();

  setTimeout(() => {
    chartLoaded.value = true;
  }, 100);

  const autoRefreshTimer = setInterval(() => {
    refreshData();
  }, 300000);

  const locationUpdateTimer = setInterval(() => {
    refreshRealTimeLocations();
  }, 60000);

  onUnmounted(() => {
    clearInterval(autoRefreshTimer);
    clearInterval(locationUpdateTimer);

    coreIndicators.value.forEach(indicator => {
      const chart = echarts.getInstanceByDom(document.getElementById(`miniChart-${indicator.indicator_id}`));
      if (chart) {
        chart.dispose();
      }
    });
  });
});
</script>

<style lang="scss" scoped>
@import url('./common-styles.scss');

.page-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: cover;
  background-position: center;
  color: #fff;
  padding: 0 20px;
  box-sizing: border-box;
  position: relative;

  &::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: rgba(0, 204, 255, 0.3);
    border-radius: 3px;
  }

  &::-webkit-scrollbar-track {
    background-color: rgba(0, 30, 60, 0.5);
  }
}

.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 15px 0;
  height: calc(100% - 30px);
  box-sizing: border-box;
  gap: 15px;
}

.top {
  display: flex;
  gap: 15px;
  height: 50%;

  .top_left {
    flex: 0.7;
    display: flex;
    flex-direction: column;
    min-width: 0;
  }

  .top_middle {
    flex: 1.6;
    display: flex;
    flex-direction: column;
    min-width: 0;
  }

  .top_right {
    flex: 0.7;
    display: flex;
    flex-direction: column;
    min-width: 0;

    .panel {
      width: 100%;
      min-width: 0;

      .panel-body {
        width: 100%;
        min-width: 0;

        .overview-stats-single-row {
          width: 100%;
          min-width: 0;
          flex-wrap: nowrap; /* 防止换行 */

          .overview-stat-item.single-row-item {
            min-width: 0;
            flex: 1;

            .stat-content.single-row-content {
              min-width: 0;
              width: 100%;

              .stat-title {
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                font-size: 14px; /* 减小字体 */
              }

              .stat-value-container .stat-value {
                font-size: 24px; /* 减小数值字体 */

                .stat-unit {
                  font-size: 14px; /* 减小单位字体 */
                }
              }

              .stat-rate {
                font-size: 12px; /* 减小比率字体 */
                padding: 4px 8px;
              }
            }
          }
        }
      }
    }
  }
}

.bottom {
  display: flex;
  gap: 15px;
  height: 48%; /* 缩小底部高度 */

  .bottom_left {
    flex: 1;
    display: flex;
    flex-direction: column;
  }
}

.panel-title-large {
  white-space: nowrap;
  overflow: visible;
  text-overflow: unset;
  flex: 1;
  min-width: 180px;
  font-size: 16px;
}

// 紧凑的操作区域样式 - 进一步优化以适应更窄的空间
.compact-actions {
  display: flex;
  align-items: center;
  gap: 4px;
  flex-wrap: nowrap;
  min-width: 0;

  // 进一步缩小筛选框
  .compact-filter {
    width: 80px !important; /* 进一步缩小宽度 */

    :deep(.el-input__wrapper) {
      width: 100%;
      padding: 1px 6px; /* 进一步减小内边距 */
    }

    :deep(.el-input__inner) {
      font-size: 11px; /* 进一步减小字体 */
    }
  }

  // 进一步缩小按钮
  .compact-btn {
    padding: 4px 6px !important; /* 进一步减小按钮内边距 */
    font-size: 11px !important; /* 进一步减小按钮字体 */
    min-width: auto;

    .el-icon {
      font-size: 11px !important; /* 进一步减小图标 */
    }

    // 隐藏按钮文字，只显示图标
    span:not(.el-icon) {
      display: none;
    }
  }

  // 全屏按钮特别调整
  .panel-fullscreen-btn.compact-btn {
    padding: 3px !important;
    width: 22px;
    height: 22px;

    .el-icon {
      font-size: 11px !important;
    }
  }
}

// 确保面板头部布局合理
.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  min-height: 40px;
  width: 100%;

  h2 {
    margin: 0;
    display: flex;
    align-items: center;
    gap: 6px;
    min-width: 0;
    flex: 1;
  }

  .header-actions {
    min-width: 0;
    flex-shrink: 1;
  }
}

// 响应式调整 - 确保在较窄宽度下正常显示
@media (max-width: 1920px) {
  .page-container {
    padding: 0 15px;
  }

  .mainbox {
    gap: 12px;
  }

  .top,
  .bottom {
    gap: 8px;
  }

  .top_right {
    .panel {
      .panel-body {
        .overview-stats-single-row {
          .overview-stat-item.single-row-item {
            .stat-content.single-row-content {
              .stat-title {
                font-size: 13px;
              }

              .stat-value-container .stat-value {
                font-size: 22px;

                .stat-unit {
                  font-size: 13px;
                }
              }

              .stat-rate {
                font-size: 11px;
              }
            }
          }
        }
      }
    }
  }
}

@media (max-width: 1600px) {
  .top {
    .top_left {
      flex: 0.6; /* 在更小屏幕上进一步缩小 */
    }

    .top_middle {
      flex: 1.8; /* 在更小屏幕上进一步扩大中间 */
    }

    .top_right {
      flex: 0.6; /* 在更小屏幕上进一步缩小 */

      .panel {
        .panel-body {
          .overview-stats-single-row {
            .overview-stat-item.single-row-item {
              .stat-content.single-row-content {
                .stat-title {
                  font-size: 12px;
                }

                .stat-value-container .stat-value {
                  font-size: 20px;

                  .stat-unit {
                    font-size: 12px;
                  }
                }

                .stat-rate {
                  font-size: 10px;
                  padding: 3px 6px;
                }
              }
            }
          }
        }
      }
    }
  }

  .compact-actions {
    gap: 3px;

    .compact-filter {
      width: 75px !important;
    }
  }
}

@media (max-width: 1400px) {
  .top {
    .top_left {
      flex: 0.5;
    }

    .top_middle {
      flex: 2.0;
    }

    .top_right {
      flex: 0.5;

      // 在超小屏幕上考虑换行显示统计项
      .panel-body {
        .overview-stats-single-row {
          flex-wrap: wrap;
          height: auto;
          min-height: 140px;

          .overview-stat-item.single-row-item {
            flex: 0 0 calc(50% - 5px);
            min-height: 65px;

            .stat-content.single-row-content {
              .stat-title {
                font-size: 11px;
                margin-bottom: 4px;
              }

              .stat-value-container .stat-value {
                font-size: 18px;

                .stat-unit {
                  font-size: 11px;
                }
              }

              .stat-rate {
                font-size: 9px;
                padding: 2px 4px;
              }
            }
          }
        }
      }
    }
  }

  // 在超小屏幕上隐藏部分操作按钮文字
  .compact-actions {
    .compact-btn {
      span:not(.el-icon) {
        display: none;
      }

      .el-icon {
        margin-right: 0 !important;
      }
    }
  }
}

// 调整指标卡片大小
.overview-stats-horizontal {
  .overview-stat-item {
    padding: 12px 15px;

    .stat-value {
      font-size: 22px;
    }

    .stat-title {
      font-size: 13px;
    }

    .stat-rate {
      font-size: 11px;
    }
  }
}

@media (max-width: 1920px) {
  .page-container {
    padding: 0 15px;
  }

  .mainbox {
    gap: 12px;
  }

  .top,
  .bottom {
    gap: 8px;
  }
}

@media (max-width: 1600px) {
  .overview-stats-horizontal {
    .overview-stat-item {
      .stat-value {
        font-size: 20px;
      }

      .stat-title {
        font-size: 12px;
      }
    }
  }

  .service-stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .service-charts-container {
    grid-template-columns: 1fr 1fr;

    .progress-chart {
      grid-column: 1 / span 2;
    }
  }
}

@media (max-width: 1400px) {
  .overview-stats-horizontal {
    flex-wrap: wrap;

    .overview-stat-item {
      flex: 0 0 calc(50% - 10px);
      margin-bottom: 10px;
    }
  }

  .service-charts-container {
    grid-template-columns: 1fr;

    .progress-chart {
      grid-column: 1;
    }
  }
}

/* 新增弹窗样式 */
.stat-detail-content,
.service-stat-detail-content,
.marker-detail-content {
  max-height: 500px;
  overflow-y: auto;
  padding-right: 10px;

  .stat-analysis,
  .service-stat-analysis,
  .marker-extra-info,
  .marker-actions {
    margin-top: 20px;
    padding: 15px;
    background: rgba(0, 30, 60, 0.3);
    border-radius: 6px;

    h3 {
      color: #00ccff;
      margin-bottom: 10px;
    }

    p {
      color: #ccc;
      line-height: 1.6;
      margin: 0;
    }
  }
}

.marker-extra-info {
  .info-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
    gap: 10px;

    .info-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 8px;
      background: rgba(255, 255, 255, 0.1);
      border-radius: 4px;

      .info-label {
        color: #94a3b8;
        font-size: 12px;
        margin-bottom: 4px;
      }

      .info-value {
        font-size: 13px;
        color: #e2e8f0;
        font-weight: 500;
      }
    }
  }
}

:deep(.stat-detail-content),
:deep(.service-stat-detail-content),
:deep(.marker-detail-content) {
  &::-webkit-scrollbar {
    width: 6px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: rgba(0, 204, 255, 0.3);
    border-radius: 3px;
  }

  &::-webkit-scrollbar-track {
    background-color: rgba(0, 30, 60, 0.5);
  }
}

// 确保右侧面板高度正确
.top_right {
  .panel {
    height: 100%;
    display: flex;
    flex-direction: column;

    .panel-body {
      flex: 1;
      overflow: hidden;
      padding: 0; // 移除内边距，由内部容器控制
    }
  }
}

// 确保统计项完全填充
.overview-stats-single-row {
  height: 100%;
  width: 100%;
}

// 服务详情样式
.detail-section {
  margin-bottom: 20px;
}

.detail-section h3 {
  color: #1E293B;
  margin-bottom: 10px;
  padding-bottom: 5px;
  border-bottom: 1px solid #e2e8f0;
}

.service-description {
  background: #f8fafc;
  padding: 15px;
  border-radius: 6px;
  line-height: 1.6;
}

.timeline-content {
  padding: 5px 0;
}

.action-header {
  margin-bottom: 5px;
}

.operator {
  color: #64748B;
  font-size: 0.9em;
}

.action-description {
  color: #475569;
  margin: 5px 0;
}

.attachments {
  margin-top: 8px;
}

.attachment-tag {
  margin-right: 5px;
}

.statistics-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 15px;
  margin-top: 15px;

  .stat-item {
    text-align: center;
    padding: 20px 15px;
    border-radius: 12px;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    border: 1px solid rgba(255, 255, 255, 0.1);

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
    }

    // 为不同的统计项设置不同的渐变颜色
    &:nth-child(1) {
      background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
    }

    &:nth-child(2) {
      background: linear-gradient(135deg, #ff6b6b 0%, #ff8e8e 100%);
    }

    &:nth-child(3) {
      background: linear-gradient(135deg, #a8e6cf 0%, #3edbf0 100%);
    }

    &:nth-child(4) {
      background: linear-gradient(135deg, #ffd93d 0%, #ff9c35 100%);
    }

    .stat-label {
      font-size: 14px;
      font-weight: 500;
      margin-bottom: 8px;
      opacity: 0.9;
      text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    }

    .stat-value {
      font-size: 24px;
      font-weight: bold;
      text-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);

      &.warning-text {
        color: #ffeaa7;
        font-weight: 700;
        animation: pulseWarning 2s infinite;
      }
    }
  }
}

// 图表下钻样式
.chart-drill-content {
  max-height: 600px;
  overflow-y: auto;
}

.drill-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #e2e8f0;
}

.drill-header h3 {
  margin: 0;
  color: #1E293B;
}

.drill-chart {
  margin-top: 20px;
  padding: 15px;
  background: #f8fafc;
  border-radius: 6px;
}

.drill-chart h4 {
  margin: 0 0 15px 0;
  color: #475569;
}

.progress-flow {
  margin-top: 20px;
}

.flow-diagram {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 15px;
  background: #f8fafc;
  border-radius: 6px;
}

.flow-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.stage-name {
  font-weight: 500;
  color: #475569;
}

.stage-count {
  background: #3B82F6;
  color: white;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 12px;
}

.flow-arrow {
  color: #94A3B8;
  font-weight: bold;
}

// 导出进度样式
.export-progress {
  text-align: center;
  padding: 20px;
}

.progress-text {
  margin-top: 15px;
  color: #64748B;
}

// 地图容器样式优化
.map-container {
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .map-wrapper {
    width: 100%;
    height: 100%;
    border-radius: 6px;
    overflow: hidden;
  }
}

// 力量统计悬浮层样式优化
.force-stats-overlay {
  position: absolute;
  top: 15px;
  left: 15px;
  z-index: 1000;
  display: flex;
  flex-direction: column;
  gap: 15px;
  max-width: 280px;
}

// 区域力量分布样式优化
.region-force-distribution {
  max-height: 180px;
  overflow-y: auto;
}

// 服务图表容器样式优化
.service-charts-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 12px;
  height: 140px; // 缩小高度
  margin-bottom: 8px;
}

// 超期服务预警条样式优化
.overdue-warning-section {
  margin-top: 8px;

  .overdue-warning-bar {
    padding: 6px 10px;

    .warning-content {
      font-size: 12px;
    }
  }
}
// 服务详情样式修复
.service-detail-content {
  max-height: 70vh;
  overflow-y: auto;
  padding: 10px;
  color: #333; // 确保文字可见

  // 调试信息样式
  .debug-info {
    background: #f8f9fa;
    border: 1px solid #e9ecef;
    border-radius: 4px;
    padding: 10px;
    margin-bottom: 15px;

    pre {
      margin: 0;
      font-family: 'Courier New', monospace;
      white-space: pre-wrap;
    }
  }

  .detail-section {
    margin-bottom: 25px;
    padding: 15px;
    background: #ffffff;
    border-radius: 6px;
    border: 1px solid #e1e5e9;

    h3 {
      color: #1a1a1a;
      margin: 0 0 15px 0;
      padding-bottom: 8px;
      border-bottom: 2px solid #409eff;
      font-size: 16px;
      font-weight: 600;
    }
  }

  .info-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 12px;

    .info-item {
      display: flex;
      align-items: center;
      padding: 8px 12px;
      background: #f8f9fa;
      border-radius: 4px;

      .info-label {
        color: #666;
        font-weight: 500;
        margin-right: 8px;
        min-width: 80px;
      }

      .info-value {
        color: #1a1a1a;
        font-weight: 500;
      }
    }
  }

  .service-description {
    background: #f8f9fa;
    padding: 15px;
    border-radius: 6px;
    line-height: 1.6;
    color: #333;
    border-left: 4px solid #409eff;
  }

  .timeline-content {
    padding: 8px 0;

    .action-header {
      margin-bottom: 6px;

      strong {
        color: #1a1a1a;
        font-size: 14px;
      }

      .operator {
        color: #666;
        font-size: 12px;
      }
    }

    .action-description {
      color: #555;
      margin: 5px 0;
      line-height: 1.5;
    }

    .attachments {
      margin-top: 8px;

      .attachment-tag {
        margin-right: 6px;
        margin-bottom: 4px;
      }
    }
  }

  .statistics-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
    gap: 15px;

    .stat-item {
      text-align: center;
      padding: 15px;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border-radius: 8px;
      color: white;

      .stat-label {
        font-size: 12px;
        margin-bottom: 8px;
        opacity: 0.9;
      }

      .stat-value {
        font-size: 18px;
        font-weight: bold;

        &.warning-text {
          color: #ff6b6b;
        }
      }
    }
  }
}

:deep(.service-detail-content) {
  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: #409eff;
    border-radius: 4px;
  }

  &::-webkit-scrollbar-track {
    background-color: #f1f1f1;
  }
}

// 修复弹窗背景色
:deep(.el-dialog) {
  .el-dialog__body {
    background: #f5f7fa;
    color: #333;
  }
}

// 修复表格样式
:deep(.el-table) {
  th {
    background-color: #f8f9fa !important;
    color: #333 !important;
  }

  td {
    background-color: #ffffff !important;
    color: #333 !important;
  }
}
</style>
