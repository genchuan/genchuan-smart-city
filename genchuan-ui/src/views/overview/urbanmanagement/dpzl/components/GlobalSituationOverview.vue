<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="panel top_left" style="min-width: 2vw;" ref="facilityDistributionPanel">
          <!-- 城管设施分布 -->
          <div class="panel-header">
            <h2>城管设施分布</h2>
            <div class="header-actions">
              <el-select v-model="facilityStatusFilter" placeholder="设施状态" size="small" @change="updateFacilityChartsData">
                <el-option label="全部" value="" />
                <el-option label="正常" value="0" />
                <el-option label="故障" value="1" />
                <el-option label="待维护" value="2" />
              </el-select>
              <el-button size="small" type="primary" @click="toggleFacilityView">
                {{ currentFacilityView === 'chart' ? '显示列表' : '显示饼图' }}
              </el-button>
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('facilityDistributionPanel')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <div class="facility-stats">
              <div class="facility-stat-item">
                <div class="stat-number">{{ totalFacilities }}</div>
                <div class="stat-label">设施总数</div>
              </div>
              <div class="facility-stat-item">
                <div class="stat-number">{{ normalFacilities }}</div>
                <div class="stat-label">正常设施</div>
              </div>
              <div class="facility-stat-item">
                <div class="stat-number">{{ faultyFacilities }}</div>
                <div class="stat-label">故障设施</div>
              </div>
              <div class="facility-stat-item">
                <div class="stat-number">{{ pendingFacilities }}</div>
                <div class="stat-label">待维护设施</div>
              </div>
            </div>

            <!-- 饼图视图 -->
            <div class="chart-container" v-if="currentFacilityView === 'chart'">
              <ChartPie
                :data="facilityTypePieData"
                :nameField="'类型'"
                :valueField="'数量'"
              />
            </div>

            <!-- 列表视图 -->
            <div style="width: 100%; height: 100%;" v-else>
              <el-table
                :data="filteredFacilities"
                border
                size="small"
                height="74%"
              >
                <el-table-column prop="mng_comp_id" label="设施ID" />
                <el-table-column prop="comp_name" label="设施名称" />
                <el-table-column prop="minor_name" label="设施类型" />
                <el-table-column prop="region" label="区域" />
                <el-table-column prop="facility_status" label="状态">
                  <template #default="scope">
                    <el-tag :type="getFacilityStatusType(scope.row.facility_status)">
                      {{ getFacilityStatusText(scope.row.facility_status) }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button size="small" type="text" @click="showFacilityDetail(scope.row)">详情</el-button>
                    <el-button size="small" type="text" v-if="scope.row.facility_status !== '0'" @click="createRepairOrder(scope.row)">维修</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel top_middle" style="min-width: 3vw;" ref="map">
          <div class="header-actions">
            <p>城管全域数据地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <div class="panel top_right" style="min-width: 2vw;" ref="warningsPanel">
          <!-- 近期预警信息 -->
          <div class="panel-header">
            <h2>近期预警信息</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('warningsPanel')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <div class="warning-scroll-container">
              <div class="warning-scroll-header">
                <div class="warning-header-item">区域</div>
                <div class="warning-header-item">预警类型</div>
                <div class="warning-header-item">时间</div>
                <div class="warning-header-item">状态</div>
              </div>
              <div class="warning-scroll-content" ref="warningScrollContent">
                <div
                  class="warning-item"
                  v-for="(item, index) in warningScrollConfig.data"
                  :key="index"
                  @click="handleWarningClick(item)"
                >
                  <div class="warning-item-col">{{ item[0] }}</div>
                  <div class="warning-item-col">{{ item[1] }}</div>
                  <div class="warning-item-col">{{ item[2] }}</div>
                  <div class="warning-item-col">
                    <el-tag :type="item[3] === '未处理' ? 'danger' : 'success'">
                      {{ item[3] }}
                    </el-tag>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>

      </div>
      <div class="bottom">
        <div class="panel bottom_left" style="min-width: 2vw;">
          <div class="core-indicators-panel" ref="coreIndicatorsPanelLeft">
            <div class="panel-body">
              <div class="indicator-cards">
                <!-- 循环渲染指标卡片 -->
                <div
                  v-for="indicator in leftIndicators"
                  :key="indicator.index_id"
                  :class="['indicator-card', getIndicatorStatusClass(indicator.index_status)]"
                  @click="showIndicatorDetail(indicator)"
                >
                  <div class="indicator-title">{{ indicator.index_name }}</div>
                  <div class="indicator-value">
                <span :data-value="indicator.current_value" class="number-animate">
                  {{ indicator.current_value }}
                  <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                  <template v-if="indicator.index_id === 'ind1006'">分钟</template>
                </span>
                  </div>
                  <div class="indicator-total">总数: {{ indicator.total_count }} (达标: {{ getQualifiedCount(indicator) }})</div>
                  <div class="indicator-threshold">
                    <template v-if="indicator.target_value">
                      目标值: {{ indicator.target_value }}
                      <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                      <template v-if="indicator.index_id === 'ind1006'">分钟</template>
                    </template>
                    <template v-if="indicator.warn_threshold">
                      <br/>
                      阈值: {{ indicator.warn_threshold }}
                      <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                      <template v-if="indicator.index_id === 'ind1006' || indicator.index_id === 'ind1005'">分钟</template>
                    </template>
                  </div>
                  <div class="indicator-trends">
                <span class="trend-item" :class="indicator.yoy >= 0 ? 'up' : 'down'">
                  同比: {{ indicator.yoy > 0 ? '+' : '' }}{{ indicator.yoy }}%
                </span>
                    <span class="trend-item" :class="indicator.mom >= 0 ? 'up' : 'down'">
                  环比: {{ indicator.mom > 0 ? '+' : '' }}{{ indicator.mom }}%
                </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel bottom_middle" style="min-width: 3vw;" ref="list">
          <div class="panel-header">
            <h2>部件列表</h2>
            <!-- 筛选组件（适配表格样式） -->
            <div class="header-filters">
              <div class="filter-group">
                <label class="filter-label"></label>
                <el-checkbox-group v-model="selectedTypes">
                  <el-checkbox label="市政" />
                  <el-checkbox label="市容" />
                  <el-checkbox label="环卫" />
                </el-checkbox-group>
              </div>
              <div class="filter-group">
                <label class="filter-label"></label>
                <el-checkbox-group v-model="selectedStatuses" size="small">
                  <el-checkbox label="完好" />
                  <el-checkbox label="破损" />
                  <el-checkbox label="维护" />
                </el-checkbox-group>
              </div>
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('list')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body" style="width: 100%; height: 100%;">
            <!-- 部件列表表格（复用现有表格样式） -->
            <el-table
              :data="filteredParts"
              border
              size="small"
              height="96%"
              @row-click="showPartDetail"
            >
              <el-table-column prop="part_id" label="部件ID" />
              <el-table-column prop="part_name" label="部件名称" />
              <el-table-column prop="part_type" label="部件类型" />
              <el-table-column prop="status" label="状态">
                <template #default="scope">
                  <!-- 状态标签复用现有.el-tag样式 -->
                  <el-tag :type="getStatusTagType(scope.row.status)">
                    {{ scope.row.status }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="manager" label="负责人" />
              <el-table-column prop="related_event" label="关联事件">
                <template #default="scope">
                  <span v-if="scope.row.related_event !== '无'" class="event-link">
                    {{ scope.row.related_event }}
                  </span>
                  <span v-else>无</span>
                </template>
              </el-table-column>
              <el-table-column prop="location" label="位置" />
              <el-table-column label="操作">
                <template #default="scope">
                  <!-- 操作按钮复用.el-button--text样式 -->
                  <el-button
                    size="small"
                    type="text"
                    @click="showPartDetail(scope.row)"
                  >
                    详情
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel bottom_right" style="min-width: 2vw;">
          <div class="core-indicators-panel" ref="coreIndicatorsPanelRight">
            <div class="panel-body">
              <div class="indicator-cards">
                <!-- 循环渲染指标卡片 -->
                <div
                  v-for="indicator in rightIndicators"
                  :key="indicator.index_id"
                  :class="['indicator-card', getIndicatorStatusClass(indicator.index_status)]"
                  @click="showIndicatorDetail(indicator)"
                >
                  <div class="indicator-title">{{ indicator.index_name }}</div>
                  <div class="indicator-value">
                <span :data-value="indicator.current_value" class="number-animate">
                  {{ indicator.current_value }}
                  <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                  <template v-if="indicator.index_id === 'ind1006'">分钟</template>
                </span>
                  </div>
                  <div class="indicator-total">总数: {{ indicator.total_count }} (达标: {{ getQualifiedCount(indicator) }})</div>
                  <div class="indicator-threshold">
                    <template v-if="indicator.target_value">
                      目标值: {{ indicator.target_value }}
                      <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                      <template v-if="indicator.index_id === 'ind1006'">分钟</template>
                    </template>
                    <template v-if="indicator.warn_threshold">
                      <br/>
                      阈值: {{ indicator.warn_threshold }}
                      <template v-if="['ind1002', 'ind1004'].includes(indicator.index_id)">%</template>
                      <template v-if="indicator.index_id === 'ind1006' || indicator.index_id === 'ind1005'">分钟</template>
                    </template>
                  </div>
                  <div class="indicator-trends">
                <span class="trend-item" :class="indicator.yoy >= 0 ? 'up' : 'down'">
                  同比: {{ indicator.yoy > 0 ? '+' : '' }}{{ indicator.yoy }}%
                </span>
                    <span class="trend-item" :class="indicator.mom >= 0 ? 'up' : 'down'">
                  环比: {{ indicator.mom > 0 ? '+' : '' }}{{ indicator.mom }}%
                </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      当前时间：{{currentTime}}
    </div>

    <!-- 部件详情弹窗 -->
    <el-dialog
      v-model="partDetailVisible"
      :title="currentPart?.part_name || '部件详情'"
      width="420px"
      :border="false"
      :before-close="() => partDetailVisible = false"
    >
      <!-- 弹窗内容区样式 -->
      <div class="part-detail-container">
        <el-descriptions
          column="1"
          border
          :style="{
        backgroundColor: 'rgba(0, 54, 108, 0.6)',
        borderColor: 'rgba(0, 204, 255, 0.2)'
      }"
        >
          <!-- 部件ID -->
          <el-descriptions-item
            label="部件ID"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.part_id }}
          </el-descriptions-item>

          <!-- 部件名称 -->
          <el-descriptions-item
            label="部件名称"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.part_name }}
          </el-descriptions-item>

          <!-- 部件类型 -->
          <el-descriptions-item
            label="部件类型"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.part_type }}
          </el-descriptions-item>

          <!-- 状态（带标签） -->
          <el-descriptions-item
            label="状态"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ padding: '10px 12px' }"
          >
            <el-tag
              :type="getStatusTagType(currentPart?.status)"
              style="font-size: 13px"
            >
              {{ currentPart?.status }}
            </el-tag>
          </el-descriptions-item>

          <!-- 负责人 -->
          <el-descriptions-item
            label="负责人"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.manager }}
          </el-descriptions-item>

          <!-- 关联事件 -->
          <el-descriptions-item
            label="关联事件"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
        <span v-if="currentPart?.related_event !== '无'" class="event-link">
          {{ currentPart?.related_event }}
        </span>
            <span v-else>无</span>
          </el-descriptions-item>

          <!-- 位置 -->
          <el-descriptions-item
            label="位置"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.location }}
          </el-descriptions-item>

          <!-- 安装时间 -->
          <el-descriptions-item
            label="安装时间"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.install_time }}
          </el-descriptions-item>

          <!-- 最后检查时间 -->
          <el-descriptions-item
            label="最后检查"
            :label-style="{ color: '#00ffd0', fontWeight: 500 }"
            :content-style="{ color: '#afc2ff' }"
          >
            {{ currentPart?.last_check }}
          </el-descriptions-item>
        </el-descriptions>
      </div>

      <!-- 弹窗底部按钮 -->
      <template #footer>
        <el-button
          @click="partDetailVisible = false"
          :style="{
        backgroundColor: 'transparent',
        borderColor: 'rgba(0, 204, 255, 0.3)',
        color: '#00ccff'
      }"
          hover-class="el-button--text"
        >
          关闭
        </el-button>

        <!-- 仅在部件状态为“破损”或“维护”时显示维修按钮 -->
        <el-button
          type="primary"
          @click="createRepairOrder(currentPart)"
          v-if="['破损', '维护'].includes(currentPart?.status)"
          :style="{
        backgroundColor: 'rgba(0, 204, 255, 0.2)',
        borderColor: 'rgba(0, 204, 255, 0.5)',
        color: '#00ffd0'
      }"
          hover-style="{
        backgroundColor: 'rgba(0, 204, 255, 0.3)',
        borderColor: '#00ccff'
      }"
        >
          维修处理
        </el-button>
      </template>
    </el-dialog>

    <!-- 核心指标详情 -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="currentIndicator?.index_name || '指标详情'"
      width="600px"
    >
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="指标类别">{{ currentIndicator?.index_category }}</el-descriptions-item>
            <el-descriptions-item label="指标编码">{{ currentIndicator?.index_code }}</el-descriptions-item>
            <el-descriptions-item label="统计周期">{{ getStatCycleText(currentIndicator?.stat_cycle) }}</el-descriptions-item>
            <el-descriptions-item label="总量统计">
              总数: {{ currentIndicator?.total_count }} | 达标数: {{ getQualifiedCount(currentIndicator) }}
            </el-descriptions-item>
            <el-descriptions-item label="当前值">
              <span class="current-value">{{ currentIndicator?.current_value }}%</span>
            </el-descriptions-item>
            <el-descriptions-item label="趋势数据">
              同比: {{ currentIndicator?.yoy > 0 ? '+' : '' }}{{ currentIndicator?.yoy }}% |
              环比: {{ currentIndicator?.mom > 0 ? '+' : '' }}{{ currentIndicator?.mom }}%
            </el-descriptions-item>
            <el-descriptions-item label="目标值">{{ currentIndicator?.target_value }}%</el-descriptions-item>
            <el-descriptions-item label="阈值范围">
              提醒: {{ currentIndicator?.remind_threshold }}% | 预警: {{ currentIndicator?.warn_threshold }}%
            </el-descriptions-item>
            <el-descriptions-item label="指标状态">
              <el-tag :type="getIndicatorStatusTagType(currentIndicator?.index_status)">
                {{ getIndicatorStatusText(currentIndicator?.index_status) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="数据来源">{{ currentIndicator?.ext2 }}</el-descriptions-item>
            <el-descriptions-item label="计算方式">{{ currentIndicator?.ext1 }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>近7天趋势</h3>
          <div class="chart-container" style="height: 300px;">
            <ChartLine
              :xAxis="indicatorTrendData.xAxis"
              :series="indicatorTrendData.series"
              :yAxisName="'数值(%)'"
            />
          </div>
        </div>

        <template v-if="currentIndicator?.index_id === 'ind1005'">
          <div class="detail-section">
            <h3>未处置事件明细</h3>
            <el-table :data="unhandledEvents" border size="small">
              <el-table-column prop="event_name" label="事件名称" />
              <el-table-column prop="region" label="区域" />
              <el-table-column prop="occur_time" label="发生时间" />
              <el-table-column prop="level" label="级别" />
            </el-table>
          </div>
        </template>

        <template v-if="currentIndicator?.index_status !== '0'">
          <div class="supervise-section">
            <el-form>
              <el-form-item label="负责人" required>
                <el-select v-model="superviseLeaderId" placeholder="选择负责人">
                  <el-option label="赵六（城东区负责人）" value="user3001" />
                  <el-option label="钱七（南郊区负责人）" value="user3002" />
                  <el-option label="孙八（主城区负责人）" value="user3003" />
                </el-select>
              </el-form-item>
              <el-form-item label="督办内容" required>
                <el-input type="textarea" v-model="superviseContent" rows="3" />
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleCreateSuperviseOrder"
          v-if="currentIndicator?.index_status !== '0'"
        >
          生成督办工单
        </el-button>
        <!--        <el-button-->
        <!--          type="primary"-->
        <!--          @click="modifyIndicatorConfig"-->
        <!--        >-->
        <!--          修改指标配置-->
        <!--        </el-button>-->
      </template>
    </el-dialog>

    <!-- 事件处理 -->
    <el-dialog
      v-model="eventDetailVisible"
      :title="currentEvent?.event_name || '事件详情'"
      width="500px"
    >
      <el-form :model="eventForm" ref="eventFormRef" label-width="80px">
        <el-form-item label="事件类型" disabled>
          <el-input v-model="eventForm.eventType" />
        </el-form-item>
        <el-form-item label="发生区域" disabled>
          <el-input v-model="eventForm.region" />
        </el-form-item>
        <el-form-item label="发生时间" disabled>
          <el-input v-model="eventForm.occurTime" />
        </el-form-item>
        <el-form-item label="事件级别" disabled>
          <el-input v-model="eventForm.level" />
        </el-form-item>
        <el-form-item label="当前状态" disabled>
          <el-input v-model="eventForm.status" />
        </el-form-item>
        <template v-if="currentEvent?.status !== '2'">
          <el-form-item label="处理状态" required>
            <el-select v-model="eventForm.handleStatus">
              <el-option label="待处置" value="0" v-if="currentEvent?.status === '0'" />
              <el-option label="处置中" value="1" />
              <el-option label="已完成" value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="处理内容" required>
            <el-input type="textarea" v-model="eventForm.content" rows="4" />
          </el-form-item>
        </template>
        <template v-if="currentEvent?.status === '2'">
          <el-form-item label="完成时间" disabled>
            <el-input v-model="eventForm.finishTime" />
          </el-form-item>
          <el-form-item label="处理结果" disabled>
            <el-input type="textarea" v-model="eventForm.content" rows="4" />
          </el-form-item>
        </template>
      </el-form>
      <template #footer>
        <el-button @click="eventDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleSubmitEvent"
          v-if="currentEvent?.status !== '2'"
        >
          提交处理结果
        </el-button>
      </template>
    </el-dialog>

    <!-- 设施处理 -->
    <el-dialog
      v-model="facilityDetailVisible"
      :title="currentFacility?.comp_name || '设施详情'"
      width="500px"
    >
      <el-form :model="facilityForm" ref="facilityFormRef" label-width="80px">
        <el-form-item label="设施ID" disabled>
          <el-input v-model="facilityForm.facilityId" />
        </el-form-item>
        <el-form-item label="设施名称" disabled>
          <el-input v-model="facilityForm.compName" />
        </el-form-item>
        <el-form-item label="设施类型" disabled>
          <el-input v-model="facilityForm.minorName" />
        </el-form-item>
        <el-form-item label="所在区域" disabled>
          <el-input v-model="facilityForm.region" />
        </el-form-item>
        <el-form-item label="安装时间" disabled>
          <el-input v-model="facilityForm.installTime" />
        </el-form-item>
        <el-form-item label="当前状态" disabled>
          <el-input v-model="facilityForm.status" />
        </el-form-item>
        <el-form-item label="坐标位置" disabled>
          <el-input v-model="facilityForm.coord" />
        </el-form-item>
        <el-form-item label="扩展信息" disabled>
          <el-input v-model="facilityForm.extInfo" />
        </el-form-item>
        <template v-if="currentFacility?.facility_status !== '0'">
          <el-form-item label="维修人员" required>
            <el-select v-model="facilityForm.maintainUserId">
              <el-option label="周九（照明维修）" value="user2001" />
              <el-option label="吴十（环卫维修）" value="user2002" />
              <el-option label="郑十一（市政维修）" value="user2003" />
            </el-select>
          </el-form-item>
          <el-form-item label="维修内容" required>
            <el-input type="textarea" v-model="facilityForm.repairContent" rows="4" />
          </el-form-item>
        </template>
      </el-form>
      <template #footer>
        <el-button @click="facilityDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleCreateRepairOrder"
          v-if="currentFacility?.facility_status !== '0'"
        >
          生成维修工单
        </el-button>
      </template>
    </el-dialog>

    <!-- 新预警通知 -->
    <el-dialog
      v-model="newWarningVisible"
      title="新预警通知"
      width="400px"
      :show-close="false"
    >
      <div class="warning-notification">
        <div class="warning-icon">警告</div>
        <div class="warning-content">
          <p><strong>{{ newWarning?.name }}</strong> 出现异常</p>
          <p>{{ newWarning?.warning_reason }}</p>
          <p class="warning-time">发生时间: {{ newWarning?.time }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="ignoreWarning">忽略</el-button>
        <el-button type="primary" @click="handleNewWarning">查看详情</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, computed, onMounted, onUnmounted, getCurrentInstance, nextTick } from 'vue';
import MapCommon from './MapCommon.vue';
import { FullScreen } from "@element-plus/icons-vue";
import screenFull from 'screenfull';
import ChartPie from './ChartPie.vue';
import ChartLine from './ChartLine.vue';
import { ElMessage } from 'element-plus';

// 导入API方法
import {
  fetchUrbanDataMap,
  fetchUrbanFacilityDistribution,
  fetchUrbanCoreIndicators,
  fetchIndicatorTrend,
  fetchEventDisposalStatus,
  fetchWarningData,
  dispatchEvent,
  handleEvent,
  fetchPartsList,
  createSuperviseOrder,
  createRepairOrder as apiCreateRepairOrder
} from '@/api/overview/urbanmanagement/GlobalSituationOverview.js';

const geometriesArray = ref([]);

const currentTime = ref('');

// 获取当前组件实例，用于访问ref
const instance = getCurrentInstance();

// 工具函数
const formatTime = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return `${year}年${month}月${day}日 ${hours}时${minutes}分${seconds}秒`;
};

// 数字增长动画函数
const animateValue = (element, start, end, duration) => {
  let startTimestamp = null;
  // 判断原始指标是否为整数
  const isInteger = Number.isInteger(end);

  const step = (timestamp) => {
    if (!startTimestamp) startTimestamp = timestamp;
    const progress = Math.min((timestamp - startTimestamp) / duration, 1);
    const currentValue = progress * (end - start) + start;

    // 整数用 toFixed(0)，小数用 toFixed(1)
    element.textContent = isInteger
      ? currentValue.toFixed(0)  // 整数：不带小数位
      : currentValue.toFixed(1); // 小数：保留一位小数

    if (progress < 1) {
      window.requestAnimationFrame(step);
    }
  };
  window.requestAnimationFrame(step);
};

// 初始化数字动画
const initNumberAnimations = () => {
  nextTick(() => {
    const elements = document.querySelectorAll('.number-animate');
    elements.forEach(el => {
      const value = parseFloat(el.getAttribute('data-value'));
      animateValue(el, 0, value, 1500);
    });
  });
};

// 全屏相关功能
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

  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit();
  } else {
    screenFull.request(panel);
  }
};

// 部件列表核心数据与筛选逻辑
const parts = ref([]); // 原始部件数据
const selectedTypes = ref([]); // 选中的部件类型（多勾选）
const selectedStatuses = ref([]); // 状态筛选值

// 加载部件数据（调用修改后的接口）
const loadPartsData = async () => {
  try {
    const data = await fetchPartsList(); // 替换为新接口
    parts.value = data;
  } catch (error) {
    console.error('加载部件数据失败:', error);
  }
};

// 筛选逻辑
const filteredParts = computed(() => {
  return parts.value.filter(part => {
    // 类型筛选：未勾选任何类型则不过滤，否则匹配选中类型
    const typeMatch = selectedTypes.value.length === 0
      ? true
      : selectedTypes.value.includes(part.part_type);

    // 状态筛选：未选择状态则不过滤，否则精确匹配
    const statusMatch = selectedStatuses.value.length === 0
      ? true
      : selectedStatuses.value.includes(part.status);

    return typeMatch && statusMatch;
  });
});

// 状态标签类型映射（与现有.el-tag样式对应）
const getStatusTagType = (status) => {
  switch (status) {
    case '完好': return 'success'; // 绿色标签
    case '破损': return 'danger';  // 红色标签
    case '维护': return 'warning'; // 黄色标签
    default: return 'info';
  }
};

// 部件详情弹窗控制
const partDetailVisible = ref(false);
const currentPart = ref(null);
const showPartDetail = (part) => {
  currentPart.value = { ...part };
  partDetailVisible.value = true;
};


// 城管全域数据地图相关功能
const mapItems = ref([]);
const mapDataType = ref('');
const mapRegionFilter = ref('');

const filteredMapItems = computed(() => {
  return mapItems.value.filter(item => {
    const matchesType = !mapDataType.value || item.data_type === mapDataType.value;
    const matchesRegion = !mapRegionFilter.value || item.region.includes(mapRegionFilter.value);
    return matchesType && matchesRegion;
  });
});

const mapItemDetailVisible = ref(false);
const currentMapItem = ref(null);
const dispatchUserId = ref('');
const dispatchRemark = ref('');

const showMapItemDetail = (item) => {
  currentMapItem.value = {...item};
  dispatchUserId.value = '';
  dispatchRemark.value = '';
  mapItemDetailVisible.value = true;
};

const handleDispatchEvent = async () => {
  if (!currentMapItem.value || !dispatchUserId.value) return;

  try {
    const result = await dispatchEvent({
      eventId: currentMapItem.value.relate_id,
      handleUserId: dispatchUserId.value,
      remark: dispatchRemark.value
    });

    const index = mapItems.value.findIndex(item => item.map_data_id === currentMapItem.value.map_data_id);
    if (index !== -1) {
      mapItems.value[index].status = '1';
      mapItems.value[index].dispatch_order_id = result.data.dispatchOrderId;
      mapItems.value[index].handle_user_id = dispatchUserId.value;
      mapItems.value[index].update_time = formatTime(new Date());
    }

    currentMapItem.value.status = '1';
    currentMapItem.value.dispatch_order_id = result.data.dispatchOrderId;
    currentMapItem.value.handle_user_id = dispatchUserId.value;
    currentMapItem.value.update_time = formatTime(new Date());

    ElMessage.success('派单成功');
    mapItemDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('派单失败: ' + (error.message || '未知错误'));
  }
};

const getMapItemTypeText = (type) => {
  switch (type) {
    case '1': return '设施';
    case '2': return '事件';
    case '3': return '人员';
    default: return '未知';
  }
};

const getMapItemStatusText = (type, status) => {
  if (type === '1') {
    switch (status) {
      case '0': return '正常';
      case '1': return '故障';
      default: return '未知';
    }
  } else if (type === '2') {
    switch (status) {
      case '0': return '待处置';
      case '1': return '处置中';
      case '2': return '已完成';
      default: return '未知';
    }
  } else if (type === '3') {
    switch (status) {
      case '0': return '在岗';
      case '1': return '离线';
      default: return '未知';
    }
  }
  return '未知';
};

// 设施图层控制
const showLayers = ref({
  lighting: true,
  environment: true,
  municipal: true,
  appearance: true
});

const facilityMapDetailVisible = ref(false);
const currentMapFacility = ref(null);

const showFacilityMapDetail = (facility) => {
  currentMapFacility.value = {...facility};
  facilityMapDetailVisible.value = true;
};

const getMaintainUserName = (userId) => {
  const userMap = {
    'user2001': '周九（照明维修）',
    'user2002': '吴十（环卫维修）',
    'user2003': '郑十一（市政维修）',
    'user2004': '王十二（市容维护）'
  };
  return userMap[userId] || '未知人员';
};

// 城管设施分布相关功能
const facilityDistribution = ref([]);
const facilityStatusFilter = ref('');
const currentFacilityView = ref('chart');

const filteredFacilities = computed(() => {
  return facilityDistribution.value.filter(facility => {
    return !facilityStatusFilter.value || facility.facility_status === facilityStatusFilter.value;
  });
});

const totalFacilities = computed(() => filteredFacilities.value.length);
const normalFacilities = computed(() => filteredFacilities.value.filter(f => f.facility_status === '0').length);
const faultyFacilities = computed(() => filteredFacilities.value.filter(f => f.facility_status === '1').length);
const pendingFacilities = computed(() => filteredFacilities.value.filter(f => f.facility_status === '2').length);

const facilityTypePieData = ref([]);

const calculateFacilityChartsData = () => {
  const filtered = filteredFacilities.value;
  const typeCountMap = {};

  filtered.forEach(item => {
    const typeName = getFacilityTypeName(item.facility_type);
    typeCountMap[typeName] = (typeCountMap[typeName] || 0) + 1;
  });

  facilityTypePieData.value = Object.keys(typeCountMap).map(type => ({
    类型: type,
    数量: typeCountMap[type],
    占比: `${(typeCountMap[type] / filtered.length * 100).toFixed(1)}%`,
    itemStyle: {
      color: type === '照明设施' ? 'rgba(255,227,46,0.9)' :
        type === '环卫设施' ? 'rgba(0,224,20,0.9)' :
          type === '市政设施' ? 'rgba(238,137,255,0.9)' :
            'rgba(255,143,51,0.9)'
    }
  }));
};

const toggleFacilityView = () => {
  currentFacilityView.value = currentFacilityView.value === 'chart' ? 'list' : 'chart';
};

const updateFacilityChartsData = () => {
  calculateFacilityChartsData();
};

const facilityDetailVisible = ref(false);
const currentFacility = ref(null);
const facilityForm = ref({
  facilityId: '',
  compName: '',
  minorName: '',
  region: '',
  installTime: '',
  status: '',
  coord: '',
  extInfo: '',
  maintainUserId: '',
  repairContent: ''
});
const facilityFormRef = ref(null);

const showFacilityDetail = (facility) => {
  currentFacility.value = {...facility};
  facilityDetailVisible.value = true;

  facilityForm.value.facilityId = facility.mng_comp_id;
  facilityForm.value.compName = facility.comp_name;
  facilityForm.value.minorName = facility.minor_name;
  facilityForm.value.region = facility.region;
  facilityForm.value.installTime = facility.install_time;
  facilityForm.value.status = getFacilityStatusText(facility.facility_status);
  facilityForm.value.coord = facility.map_coord;
  facilityForm.value.extInfo = `${facility.ext1} | ${facility.ext2}`;
  facilityForm.value.maintainUserId = facility.maintain_user_id || '';
  facilityForm.value.repairContent = '';
};

const createRepairOrder = (facility) => {
  currentFacility.value = {...facility};
  facilityDetailVisible.value = true;

  facilityForm.value.facilityType = getFacilityTypeName(facility.facility_type);
  facilityForm.value.region = facility.region;
  facilityForm.value.installTime = facility.install_time;
  facilityForm.value.status = getFacilityStatusText(facility.facility_status);
  facilityForm.value.coord = facility.map_coord;
  facilityForm.value.extInfo = `${facility.ext1} | ${facility.ext2}`;
  facilityForm.value.maintainUserId = facility.maintain_user_id || '';
  facilityForm.value.repairContent = '';
};

const handleCreateRepairOrder = async () => {
  if (!currentFacility.value || !facilityForm.value.maintainUserId || !facilityForm.value.repairContent) return;

  try {
    const result = await apiCreateRepairOrder({
      facilityId: currentFacility.value.facility_id,
      maintainUserId: facilityForm.value.maintainUserId,
      content: facilityForm.value.repairContent
    });

    const index = facilityDistribution.value.findIndex(f => f.dist_id === currentFacility.value.dist_id);
    if (index !== -1) {
      facilityDistribution.value[index].facility_status = '2';
      facilityDistribution.value[index].repair_order_id = result.data.repairOrderId;
    }

    currentFacility.value.facility_status = '2';
    currentFacility.value.repair_order_id = result.data.repairOrderId;

    ElMessage.success('维修工单生成成功');
    facilityDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('生成维修工单失败: ' + (error.message || '未知错误'));
  }
};

const getFacilityTypeName = (type) => {
  switch (type) {
    case '1': return '照明设施';
    case '2': return '环卫设施';
    case '3': return '市政设施';
    case '4': return '市容设施';
    default: return '未知类型';
  }
};

const getFacilityStatusText = (status) => {
  switch (status) {
    case '0': return '正常';
    case '1': return '故障';
    case '2': return '待维护';
    default: return '未知状态';
  }
};

const getFacilityStatusType = (status) => {
  switch (status) {
    case '0': return 'success';
    case '1': return 'danger';
    case '2': return 'warning';
    default: return 'info';
  }
};

// 核心指标数据
const coreIndicators = ref([]);

const leftIndicators = computed(() => {
  return coreIndicators.value.slice(0, 3);
});

const rightIndicators = computed(() => {
  return coreIndicators.value.slice(3, 6);
});

const indicatorTimeRange = ref('day');

const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);
const superviseLeaderId = ref('');
const superviseContent = ref('');
const indicatorTrendData = ref({
  xAxis: [],
  series: []
});

const getIndicatorTrend = async (indexId) => {
  try {
    const data = await fetchIndicatorTrend(indexId);
    indicatorTrendData.value = data;
  } catch (error) {
    console.error('获取近7天趋势数据失败:', error);
    ElMessage.error('加载趋势数据失败');
  }
};

const showIndicatorDetail = (indicator) => {
  currentIndicator.value = {...indicator};
  superviseLeaderId.value = indicator.leader_id || '';
  superviseContent.value = '';
  getIndicatorTrend(indicator.index_id);
  indicatorDetailVisible.value = true;
};

const refreshIndicators = async () => {
  try {
    const newData = await fetchUrbanCoreIndicators(indicatorTimeRange.value);
    coreIndicators.value = newData;
    initNumberAnimations();
    ElMessage.success('指标数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

const handleCreateSuperviseOrder = async () => {
  if (!currentIndicator.value || !superviseLeaderId.value || !superviseContent.value) return;

  try {
    const result = await createSuperviseOrder({
      indicatorId: currentIndicator.value.index_id,
      leaderId: superviseLeaderId.value,
      content: superviseContent.value
    });

    const index = coreIndicators.value.findIndex(i => i.index_id === currentIndicator.value.index_id);
    if (index !== -1) {
      coreIndicators.value[index].supervise_order_id = result.data.superviseOrderId;
      coreIndicators.value[index].leader_id = superviseLeaderId.value;
    }

    currentIndicator.value.supervise_order_id = result.data.superviseOrderId;
    currentIndicator.value.leader_id = superviseLeaderId.value;

    ElMessage.success('督办工单生成成功');
  } catch (error) {
    ElMessage.error('生成督办工单失败: ' + (error.message || '未知错误'));
  }
};

const modifyIndicatorConfig = () => {
  ElMessage.info('指标配置修改功能待实现');
};

const getQualifiedCount = (indicator) => {
  if (indicator.index_id === 'ind1001') return '-';
  if (indicator.index_id === 'ind1002') return indicator.intact_count || 0;
  if (indicator.index_id === 'ind1003') return '-';
  if (indicator.index_id === 'ind1004') return indicator.completed_count || 0;
  if (indicator.index_id === 'ind1005') return (indicator.total_count - indicator.pend_count) || 0;
  if (indicator.index_id === 'ind1006') return indicator.total_disposed_count || 0;
  return 0;
};

const getIndicatorStatusClass = (status) => {
  switch (status) {
    case '0': return 'normal';
    case '1': return 'remind blink-animation';
    case '2': return 'warning blink-animation';
    default: return '';
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

const getIndicatorStatusTagType = (status) => {
  switch (status) {
    case '0': return 'success';
    case '1': return 'warning';
    case '2': return 'danger';
    default: return 'info';
  }
};

const getStatCycleText = (cycle) => {
  switch (cycle) {
    case '1': return '今日';
    case '2': return '本周';
    case '3': return '本月';
    default: return '未知';
  }
};

// 事件处置状态相关功能
const eventDisposalData = ref([]);

const unhandledEvents = computed(() => {
  return eventDisposalData.value.filter(event => event.status === '0');
});

const eventDetailVisible = ref(false);
const currentEvent = ref(null);
const eventForm = ref({
  eventType: '',
  region: '',
  occurTime: '',
  level: '',
  status: '',
  handleStatus: '',
  content: '',
  finishTime: ''
});
const eventFormRef = ref(null);

const handleSubmitEvent = async () => {
  if (!currentEvent.value || !eventForm.value.handleStatus || !eventForm.value.content) return;

  try {
    const result = await handleEvent({
      eventId: currentEvent.value.event_id,
      status: eventForm.value.handleStatus,
      content: eventForm.value.content,
      handleUserId: currentEvent.value.handle_user_id || 'user1001'
    });

    const index = eventDisposalData.value.findIndex(e => e.event_id === currentEvent.value.event_id);
    if (index !== -1) {
      eventDisposalData.value[index].status = eventForm.value.handleStatus;
      eventDisposalData.value[index].handle_user_id = currentEvent.value.handle_user_id || 'user1001';
      eventDisposalData.value[index].handle_user_name = eventForm.value.handleStatus !== '0' ? '张三' : '';

      if (eventForm.value.handleStatus === '1') {
        eventDisposalData.value[index].actual_handle_time = result.data.handleTime;
      } else if (eventForm.value.handleStatus === '2') {
        eventDisposalData.value[index].finish_time = result.data.handleTime;
      }
    }

    const mapIndex = mapItems.value.findIndex(item => item.data_type === '2' && item.relate_id === currentEvent.value.event_id);
    if (mapIndex !== -1) {
      mapItems.value[mapIndex].status = eventForm.value.handleStatus;
      mapItems.value[mapIndex].update_time = result.data.handleTime;
    }

    currentEvent.value.status = eventForm.value.handleStatus;
    ElMessage.success('事件处理成功');
    eventDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('事件处理失败: ' + (error.message || '未知错误'));
  }
};

const getEventStatusText = (status) => {
  switch (status) {
    case '0': return '待处置';
    case '1': return '处置中';
    case '2': return '已完成';
    default: return '未知';
  }
};

// 预警信息相关功能
const warningScrollConfig = ref({
  header: ['区域', '预警类型', '时间', '状态'],
  data: [],
  rowNum: 5
});

const warningScrollContent = ref(null);
let scrollInterval = null;

const startWarningScroll = () => {
  if (scrollInterval) clearInterval(scrollInterval);

  if (warningScrollConfig.value.data.length <= warningScrollConfig.value.rowNum) return;

  const contentEl = warningScrollContent.value;
  if (!contentEl) return;

  scrollInterval = setInterval(() => {
    contentEl.scrollBy({ top: 36, behavior: 'smooth' });

    if (contentEl.scrollTop + contentEl.clientHeight >= contentEl.scrollHeight - 10) {
      setTimeout(() => {
        contentEl.scrollTop = 0;
      }, 500);
    }
  }, 3000);
};

const handleWarningClick = (item) => {
  const targetItem = mapItems.value.find(
    mapItem => mapItem.region === item[0] && mapItem.name.includes(item[1])
  );

  if (targetItem) {
    showMapItemDetail(targetItem);
  } else {
    ElMessage.info('未找到相关数据');
  }
};

const newWarningVisible = ref(false);
const newWarning = ref(null);

const simulateNewWarning = () => {
  const warningItems = mapItems.value.filter(item => {
    if (item.data_type === '1' && item.status === '1') return true;
    if (item.data_type === '2' && item.status === '0') return true;
    return false;
  });

  if (warningItems.length === 0) return;

  const randomIndex = Math.floor(Math.random() * warningItems.length);
  const item = warningItems[randomIndex];

  let warningReason = '';
  if (item.data_type === '1') {
    warningReason = `${getFacilityTypeName(item.ext2 === '照明设施' ? '1' : item.ext2 === '环卫设施' ? '2' : '3')}发生故障`;
  } else if (item.data_type === '2') {
    warningReason = `${item.name}，请及时处置`;
  }

  newWarning.value = {
    name: item.name,
    warning_reason: warningReason,
    time: formatTime(new Date())
  };

  newWarningVisible.value = true;
};

const ignoreWarning = () => {
  newWarningVisible.value = false;
};

const handleNewWarning = () => {
  if (newWarning.value) {
    const item = mapItems.value.find(i => i.name === newWarning.value.name);
    if (item) {
      showMapItemDetail(item);
    }
  }
  newWarningVisible.value = false;
};


onMounted(() => {
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

// 初始化与生命周期
  const initData = async () => {
    try {
      const [mapData, facilities, indicators, events, warningData] = await Promise.all([
        fetchUrbanDataMap(),
        fetchUrbanFacilityDistribution(),
        fetchUrbanCoreIndicators('day'),
        fetchEventDisposalStatus(),
        fetchWarningData()
      ]);

      geometriesArray.value = mapData;
      facilityDistribution.value = facilities;
      coreIndicators.value = indicators;
      eventDisposalData.value = events;
      warningScrollConfig.value.data = warningData;

      calculateFacilityChartsData();
      initNumberAnimations();

      nextTick(() => {
        startWarningScroll();
      });
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };

  initData();
  loadPartsData();

  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) {
      simulateNewWarning();
    }
  }, 30000);

  const warningDataTimer = setInterval(() => {
    fetchWarningData().then(data => {
      warningScrollConfig.value.data = data;
      startWarningScroll();
    }).catch(err => {
      console.warn('刷新预警信息失败:', err);
    });
  }, 60000);

  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(warningTimer);
    clearInterval(warningDataTimer);
    if (scrollInterval) clearInterval(scrollInterval);
  });
});
</script>
<style lang="scss" scoped>

@import url('./common-styles.scss');

// 页面容器
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

// 主体内容区
.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 90vh;
  box-sizing: border-box;
  gap: 0.6vw;
}

// 公共面板样式
.panel {
  position: relative;
  height: 100%;
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
