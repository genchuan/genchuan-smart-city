<template>
  <div class="garden-greening-container">
    <!-- 完全复用参考代码的page-header结构 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/garden-greening.png"
          alt="园林绿化图标"
          class="title-icon"
        />
        园林绿化专题
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新数据</el-button>
        <el-button size="small" type="primary" @click="handleDiseaseReport" v-if="userRole !== '养护人员'">
          病害上报
        </el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 核心：完全复用参考代码的main-content布局逻辑 -->
    <div class="main-content">
      <div class="map-area" style="min-width: 5vw;">
        <div class="map-top-stats">
          <div class="stats-cards">
            <div class="stat-card cover-card" :class="overviewData.coverRate >= 90 ? 'normal-rate' : 'warning-rate'">
              <div class="stat-title">绿化覆盖面积</div>
              <div class="stat-value">
                <span>{{ overviewData.coverArea }}</span>
                <span class="unit">㎡</span>
              </div>
              <div class="stat-desc">覆盖率：{{ overviewData.coverRate }}%</div>
            </div>
            <div class="stat-card disease-card">
              <div class="stat-title">病害处置率</div>
              <div class="stat-value">{{ overviewData.diseaseHandlingRate }}%</div>
              <div class="stat-desc">已处置/总病害：{{ overviewData.handledDiseaseCount }}/{{ overviewData.diseaseCount }}</div>
            </div>
            <div class="stat-card survival-card">
              <div class="stat-title">绿化存活率</div>
              <div class="stat-value">{{ overviewData.survivalRate }}%</div>
              <div class="stat-desc">待养护：{{ overviewData.toMaintainCount }} 处</div>
            </div>
          </div>
        </div>

        <div class="panel map-container" ref="mapPanel">
          <div class="panel-header">
            <h3>园林绿化分布地图</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('mapPanel')">
                <el-icon color="#2e7d32" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <MapComponentGarden
              ref="mapComponent"
              idName="gardenMap"
              :geometriesArray="filteredGardenList"
              :newDiseasePoints="newDiseasePoints"
              @markerClick="handleMarkerClick"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <div class="right-area" style="min-width: 5vw;">
        <div class="right-list-area">
          <!-- 完全复用参考代码的filter-bar结构 -->
          <div class="panel filter-bar list-filter-bar">
            <el-select v-model="filterParams.gardenType" placeholder="绿化类型" size="small" multiple collapse-tags>
              <el-option
                v-for="type in gardenTypeDict"
                :key="type.value"
                :label="type.label"
                :value="type.value"
              />
            </el-select>
            <el-select v-model="filterParams.maintainStatus" placeholder="养护状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="待养护" value="待养护" />
              <el-option label="养护中" value="养护中" />
              <el-option label="已养护" value="已养护" />
            </el-select>
            <el-select v-model="filterParams.diseaseStatus" placeholder="病害状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="未处置" value="未处置" />
              <el-option label="处置中" value="处置中" />
              <el-option label="已处置" value="已处置" />
              <el-option label="无病害" value="无病害" />
            </el-select>
            <el-select v-model="filterParams.area" placeholder="所属区域" size="small">
              <el-option label="全部" value="" />
              <el-option
                v-for="area in gardenAreaDict"
                :key="area.value"
                :label="area.label"
                :value="area.value"
              />
            </el-select>
            <el-button size="small" type="success" style="margin-left: 3vw" @click="resetFilter">重 置</el-button>
          </div>

          <!-- 完全复用参考代码的task-list-container面板结构 -->
          <div class="panel task-list-container" ref="taskTablePanel">
            <div class="panel-header">
              <h3>待办任务列表</h3>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('taskTablePanel')">
                  <el-icon color="#ec4899" size="16">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <!-- 参考代码：table-scroll仅flex:1 + overflow，无硬编码高度 -->
            <div class="panel-body table-scroll">
              <transition-group name="task-list">
                <el-table
                  ref="taskTable"
                  :data="filteredGardenList"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick"
                  key="task-table"
                >
                  <el-table-column prop="mng_comp_id" label="绿化ID" />
                  <el-table-column prop="comp_name" label="绿化名称" />
                  <el-table-column prop="minor_name" label="绿化类型">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.minor_name === '树木' ? 'success' : scope.row.minor_name === '绿地' ? 'info' : 'purple'"
                      >
                        {{ scope.row.minor_name }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="maintain_status" label="养护状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.maintain_status === '待养护' ? 'danger' : scope.row.maintain_status === '养护中' ? 'warning' : 'success'"
                      >
                        {{ scope.row.maintain_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="disease_status" label="病害状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.disease_status === '未处置' ? 'danger' : scope.row.disease_status === '处置中' ? 'warning' : scope.row.disease_status === '无病害' ? 'info' : 'success'"
                        :class="{ 'disease-tag': scope.row.disease_status !== '无病害' && scope.row.disease_status !== '已处置' }"
                      >
                        {{ scope.row.disease_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="area" label="所属区域" />
                  <el-table-column label="操作" width="200">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="handleDispatch(scope.row)"
                        v-if="scope.row.maintain_status !== '已养护' && userRole === '主管'"
                      >
                        养护派单
                      </el-button>
                      <el-button
                        size="small"
                        type="success"
                        @click.stop="handleCompleteMaintain(scope.row)"
                        v-if="scope.row.maintain_status === '养护中'"
                      >
                        标记完成
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </transition-group>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 弹窗部分：完全复用参考代码的样式结构 -->
    <el-dialog
      v-model="gardenDetailVisible"
      :title="currentGarden?.comp_name || '园林绿化详情'"
      width="700px"
      class="work-order-dialog"
    >
      <div class="work-order-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="绿化ID">{{ currentGarden?.mng_comp_id }}</el-descriptions-item>
            <el-descriptions-item label="绿化名称">{{ currentGarden?.comp_name }}</el-descriptions-item>
            <el-descriptions-item label="绿化类型">{{ currentGarden?.minor_name }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentGarden?.area }}</el-descriptions-item>
            <el-descriptions-item label="坐标位置">
              ({{ currentGarden?.coord_x?.toFixed(6) }}, {{ currentGarden?.coord_y?.toFixed(6) }})
            </el-descriptions-item>
            <el-descriptions-item label="养护次数">{{ currentGarden?.maintain_count || 0 }} 次</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>养护信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="养护状态">{{ currentGarden?.maintain_status }}</el-descriptions-item>
            <el-descriptions-item label="责任人">{{ currentGarden?.responsible_person || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="养护要求" :span="2">
              <div v-if="currentGarden?.minor_name === '树木'">
                <p>• 浇水频率：每3天一次，每次浇透至根部湿润</p>
                <p>• 修剪标准：剪除病枝、枯枝，保持树形美观</p>
                <p>• 施肥周期：每月一次，使用有机肥</p>
              </div>
              <div v-if="currentGarden?.minor_name === '绿地'">
                <p>• 浇水频率：每日一次，早晚各半小时</p>
                <p>• 除草周期：每周一次，杂草高度不超过5cm</p>
                <p>• 施肥周期：每季度一次，复合肥撒施</p>
              </div>
              <div v-if="currentGarden?.minor_name === '花坛'">
                <p>• 浇水频率：每日两次，早晚各一次</p>
                <p>• 修剪标准：花期后及时修剪残花</p>
                <p>• 施肥周期：每半月一次，磷钾肥为主</p>
              </div>
              <div v-else>
                <p>• 请根据绿化类型执行对应养护标准</p>
              </div>
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section" v-if="currentGarden?.disease_status !== '无病害'">
          <h3>病害信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="病害状态">{{ currentGarden?.disease_status }}</el-descriptions-item>
            <el-descriptions-item label="病害原因">{{ currentGarden?.disease_reason || '无' }}</el-descriptions-item>
            <el-descriptions-item label="处置建议" :span="2">
              <div v-if="currentGarden?.disease_reason.includes('叶斑病')">
                • 及时剪除病叶，喷洒多菌灵杀菌剂，每周一次，连续3周
              </div>
              <div v-if="currentGarden?.disease_reason.includes('虫害')">
                • 喷洒专用杀虫剂，根部埋施防虫颗粒，加强通风
              </div>
              <div v-if="currentGarden?.disease_reason.includes('杂草')">
                • 人工除草结合除草剂喷洒，后续定期巡检
              </div>
              <div v-else>
                • 请根据实际病害情况采取对应防治措施
              </div>
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section" v-if="workOrderId">
          <h3>工单信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="工单ID">{{ workOrderId }}</el-descriptions-item>
            <el-descriptions-item label="派单时间">{{ dispatchTime }}</el-descriptions-item>
            <el-descriptions-item label="派单状态">已派单</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="gardenDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleDispatch(currentGarden)"
          v-if="currentGarden?.maintain_status !== '已养护' && userRole === '主管'"
        >
          养护派单
        </el-button>
      </template>
    </el-dialog>

    <el-dialog
      v-model="dispatchVisible"
      title="绿化养护派单"
      width="600px"
      class="dispatch-dialog"
      :close-on-click-modal="false"
    >
      <el-form :model="dispatchForm" label-width="120px" :rules="dispatchRules" ref="dispatchFormRef">
        <el-form-item label="养护人员" prop="handler">
          <el-select v-model="dispatchForm.handler" placeholder="选择养护人员">
            <el-option label="张三" value="张三" />
            <el-option label="李四" value="李四" />
            <el-option label="王五" value="王五" />
            <el-option label="赵六" value="赵六" />
          </el-select>
        </el-form-item>
        <el-form-item label="预计完成时间" prop="expectTime">
          <el-date-picker
            v-model="dispatchForm.expectTime"
            type="datetime"
            placeholder="选择预计完成时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="养护要求" prop="remark">
          <el-input
            v-model="dispatchForm.remark"
            type="textarea"
            rows="3"
            placeholder="输入养护要求（选填）"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dispatchVisible = false">取消</el-button>
        <el-button type="primary" @click="submitDispatch">确认派单</el-button>
      </template>
    </el-dialog>

    <el-dialog
      v-model="diseaseReportVisible"
      title="园林绿化病害上报"
      width="600px"
      class="dispatch-dialog"
      :close-on-click-modal="false"
    >
      <el-form :model="diseaseReportForm" label-width="120px" :rules="diseaseReportRules" ref="diseaseReportFormRef">
        <el-form-item label="绿化名称" prop="compName">
          <el-select v-model="diseaseReportForm.compName" placeholder="选择绿化">
            <el-option
              v-for="item in gardenList"
              :key="item.mng_comp_id"
              :label="item.comp_name"
              :value="item.mng_comp_id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="病害类型" prop="diseaseType">
          <el-select v-model="diseaseReportForm.diseaseType" placeholder="选择病害类型">
            <el-option label="叶斑病" value="叶斑病" />
            <el-option label="虫害" value="虫害" />
            <el-option label="枯萎病" value="枯萎病" />
            <el-option label="杂草丛生" value="杂草丛生" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item label="病害描述" prop="diseaseDesc">
          <el-input
            v-model="diseaseReportForm.diseaseDesc"
            type="textarea"
            rows="3"
            placeholder="详细描述病害情况"
          />
        </el-form-item>
        <el-form-item label="上报人" prop="reporter">
          <el-input v-model="diseaseReportForm.reporter" placeholder="输入上报人姓名" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="diseaseReportVisible = false">取消</el-button>
        <el-button type="primary" @click="submitDiseaseReport">确认上报</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
// 完全复用参考代码的script结构 + 原有业务逻辑
import { computed, getCurrentInstance, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
import screenFull from 'screenfull';
import { FullScreen } from "@element-plus/icons-vue";
import MapComponentGarden from './GardenGreeningMap.vue';

import {
  createGardenWorkOrder,
  fetchGardenOverview,
  fetchGardenTypeDict,
  fetchGardenSpatial,
  fetchGardenAreaDict,
  markGardenHandled,
  reportGardenDisease
} from '@/api/overview/urbanmanagement/SubdomainScenarioTopics.js';

const router = useRouter();
const instance = getCurrentInstance();

const userRole = ref('主管');

// 完全复用参考代码的全屏逻辑
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

// 业务数据：保留原有逻辑
const overviewData = ref({
  coverArea: 0,
  coverRate: 0,
  diseaseCount: 0,
  handledDiseaseCount: 0,
  diseaseHandlingRate: 0,
  survivalRate: 0,
  toMaintainCount: 0
});
const originOverviewData = ref({});
const gardenList = ref([]);
const gardenTypeDict = ref([]);
const gardenAreaDict = ref([]);
const newDiseasePoints = ref([]);

// 筛选参数：保留原有逻辑
const filterParams = ref({
  gardenType: [],
  maintainStatus: '',
  diseaseStatus: '',
  area: ''
});

// 弹窗相关：保留原有逻辑
const gardenDetailVisible = ref(false);
const dispatchVisible = ref(false);
const diseaseReportVisible = ref(false);
const currentGarden = ref(null);
const workOrderId = ref('');
const dispatchTime = ref('');

// 表单相关：保留原有逻辑
const dispatchForm = ref({
  handler: '',
  expectTime: '',
  remark: ''
});
const diseaseReportForm = ref({
  compName: '',
  diseaseType: '',
  diseaseDesc: '',
  reporter: ''
});
const dispatchRules = ref({
  handler: [{required: true, message: '请选择养护人员', trigger: 'change'}],
  expectTime: [{required: true, message: '请选择预计完成时间', trigger: 'change'}]
});
const diseaseReportRules = ref({
  compName: [{required: true, message: '请选择绿化', trigger: 'change'}],
  diseaseType: [{required: true, message: '请选择病害类型', trigger: 'change'}],
  diseaseDesc: [{required: true, message: '请描述病害情况', trigger: 'blur'}],
  reporter: [{required: true, message: '请输入上报人姓名', trigger: 'blur'}]
});
const dispatchFormRef = ref(null);
const diseaseReportFormRef = ref(null);

// 筛选逻辑：保留原有逻辑
const filteredGardenList = computed(() => {
  return gardenList.value.filter(item => {
    const matchType = filterParams.value.gardenType.length === 0 || filterParams.value.gardenType.includes(item.minor_name);
    const matchMaintain = !filterParams.value.maintainStatus || item.maintain_status === filterParams.value.maintainStatus;
    const matchDisease = !filterParams.value.diseaseStatus || item.disease_status === filterParams.value.diseaseStatus;
    const matchArea = !filterParams.value.area || item.area === filterParams.value.area;

    return matchType && matchMaintain && matchDisease && matchArea;
  });
});

// 页面操作方法：保留原有逻辑
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('园林绿化数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

const handleBack = () => {
  router.push('/overview/urbanmanagement/dpzl');
};

const handleCompleteMaintain = async (row) => {
  try {
    await ElMessageBox.confirm('确认该绿化养护已完成？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });
    const res = await markGardenHandled(row.mng_comp_id);
    if (res.code === 200) {
      const gardenIndex = gardenList.value.findIndex(item => item.mng_comp_id === row.mng_comp_id);
      if (gardenIndex > -1) {
        gardenList.value[gardenIndex].maintain_status = '已养护';
        gardenList.value[gardenIndex].disease_status = '已处置';
        gardenList.value[gardenIndex].update_time = new Date().toLocaleString();
      }
      ElMessage.success('养护完成！');
      await fetchOverviewData();

      if (instance.refs.mapComponent) {
        instance.refs.mapComponent.refreshMap();
      }
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('标记完成失败：', error);
      ElMessage.error('标记完成失败，请重试！');
    }
  }
};

const handleDiseaseReport = () => {
  diseaseReportForm.value = {
    compName: '',
    diseaseType: '',
    diseaseDesc: '',
    reporter: ''
  };
  diseaseReportVisible.value = true;
};

const submitDiseaseReport = async () => {
  if (!diseaseReportFormRef.value) return;
  try {
    await diseaseReportFormRef.value.validate();

    const gardenItem = gardenList.value.find(item => item.mng_comp_id === diseaseReportForm.value.compName);
    if (!gardenItem) {
      ElMessage.error('未找到对应的绿化信息');
      return;
    }

    const res = await reportGardenDisease({
      mng_comp_id: diseaseReportForm.value.compName,
      disease_type: diseaseReportForm.value.diseaseType,
      disease_desc: diseaseReportForm.value.diseaseDesc,
      reporter: diseaseReportForm.value.reporter,
      report_time: new Date().toLocaleString()
    });

    if (res.code === 200) {
      const gardenIndex = gardenList.value.findIndex(item => item.mng_comp_id === diseaseReportForm.value.compName);
      if (gardenIndex > -1) {
        gardenList.value[gardenIndex].disease_status = '未处置';
        gardenList.value[gardenIndex].disease_reason = diseaseReportForm.value.diseaseDesc;
        gardenList.value[gardenIndex].is_new_disease = true;

        newDiseasePoints.value = [gardenList.value[gardenIndex]];
      }

      ElMessage.success('病害上报成功！');
      diseaseReportVisible.value = false;
      await fetchOverviewData();

      if (instance.refs.mapComponent) {
        instance.refs.mapComponent.refreshMap();
      }
    }
  } catch (error) {
    console.error('病害上报失败：', error);
    ElMessage.error('病害上报失败，请重试！');
  }
};

const handleMarkerClick = (properties) => {
  currentGarden.value = properties;
  workOrderId.value = '';
  dispatchTime.value = '';
  gardenDetailVisible.value = true;
};

const handleTaskRowClick = (row) => {
  currentGarden.value = row;
  workOrderId.value = '';
  dispatchTime.value = '';
  gardenDetailVisible.value = true;
};

const handleDispatch = (row) => {
  currentGarden.value = row;
  dispatchForm.value = {handler: '', expectTime: '', remark: ''};
  dispatchVisible.value = true;
};

const submitDispatch = async () => {
  if (!dispatchFormRef.value) return;
  try {
    await dispatchFormRef.value.validate();
    const res = await createGardenWorkOrder({
      mng_comp_id: currentGarden.value.mng_comp_id,
      handler: dispatchForm.value.handler,
      expect_time: dispatchForm.value.expectTime,
      remark: dispatchForm.value.remark
    });

    if (res.code === 200) {
      workOrderId.value = res.data.wo_id;
      dispatchTime.value = new Date().toLocaleString();
      ElMessage.success('养护派单成功！');
      dispatchVisible.value = false;

      const gardenIndex = gardenList.value.findIndex(item => item.mng_comp_id === currentGarden.value.mng_comp_id);
      if (gardenIndex > -1) {
        gardenList.value[gardenIndex].maintain_status = '养护中';
        gardenList.value[gardenIndex].responsible_person = dispatchForm.value.handler;
        gardenList.value[gardenIndex].update_time = new Date().toLocaleString();
      }
      await fetchOverviewData();

      if (instance.refs.mapComponent) {
        instance.refs.mapComponent.refreshMap();
      }
    }
  } catch (error) {
    console.error('派单失败：', error);
    ElMessage.error('派单失败，请重试！');
  }
};

// 重置筛选：保留原有逻辑
const resetFilter = () => {
  filterParams.value.gardenType = [];
  filterParams.value.maintainStatus = '';
  filterParams.value.diseaseStatus = '';
  filterParams.value.area = '';
  overviewData.value = {...originOverviewData.value};
};

// 数据请求：保留原有逻辑
const fetchOverviewData = async () => {
  const data = await fetchGardenOverview();
  overviewData.value = data;
  originOverviewData.value = {...data};
};

const fetchGardenData = async () => {
  gardenList.value = await fetchGardenSpatial();
  newDiseasePoints.value = gardenList.value.filter(item => item.is_new_disease);
};

const fetchTypeDict = async () => {
  gardenTypeDict.value = await fetchGardenTypeDict();
};

const fetchAreaDict = async () => {
  gardenAreaDict.value = await fetchGardenAreaDict();
};

const initData = async () => {
  await fetchOverviewData();
  await fetchGardenData();
  await fetchTypeDict();
  await fetchAreaDict();
};

// 筛选监听：保留原有逻辑
watch(filterParams, (newVal, oldVal) => {
  const isTypeSame = JSON.stringify(newVal.gardenType) === JSON.stringify(oldVal.gardenType);
  const isMaintainSame = newVal.maintainStatus === oldVal.maintainStatus;
  const isDiseaseSame = newVal.diseaseStatus === oldVal.diseaseStatus;
  const isAreaSame = newVal.area === oldVal.area;

  if (isTypeSame && isMaintainSame && isDiseaseSame && isAreaSame) return;

  const filtered = filteredGardenList.value;
  const diseaseCount = filtered.filter(item => item.disease_status !== '无病害').length;
  const handledDiseaseCount = filtered.filter(item => item.disease_status === '已处置').length;

  let diseaseHandlingRate = 0;
  if (diseaseCount > 0) {
    diseaseHandlingRate = Math.round((handledDiseaseCount / diseaseCount) * 100 * 10) / 10;
  }

  overviewData.value = {
    ...overviewData.value,
    diseaseCount,
    handledDiseaseCount,
    diseaseHandlingRate,
    toMaintainCount: filtered.filter(item => item.maintain_status === '待养护').length
  };
}, {deep: true});

// 生命周期：保留原有逻辑
onMounted(async () => {
  await initData();
  setTimeout(() => {
    if (instance.refs.mapComponent) {
      instance.refs.mapComponent.refreshMap();
    }
  }, 1000);
});
</script>

<style lang="scss" scoped>
// 100% 复刻参考代码的样式结构 + 仅替换配色/文字，布局逻辑完全一致
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

// 完全复用参考代码的panel样式（仅替换主题色）
.panel {
  position: relative;
  border: 1px solid rgba(46, 125, 50, 0.3);
  background: rgba(255, 204, 211, 0.05);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0; // 参考代码核心：Flex兜底，防止溢出
  box-shadow: 0 0 1vw rgba(46, 125, 50, 0.2);
  overflow: hidden;

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(46, 125, 50, 0.3);
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
      text-shadow: 0 0 5px rgba(46, 125, 50, 0.3);
    }

    .header-actions {
      display: flex;
      align-items: center;
    }
  }

  .panel-body {
    flex: 1; // 参考代码核心：占满剩余空间，流式布局
    padding: 0.5vw;
    overflow: hidden;
    position: relative;
    z-index: 2;
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(46, 125, 50, 0.3);
    position: relative;
    z-index: 2;
  }

  // 参考代码：不同面板的渐变顶部边框
  &.map-container {
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 3px;
      background: linear-gradient(90deg, #2e7d32, rgba(46, 125, 50, 0.6));
      z-index: 1;
    }
    .panel-header h3 { color: #2e7d32; }
    .panel-body {
      width: 100%;
      height: 100%;
      padding: 0;
    }
  }

  &.filter-bar {
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
    .panel-header h3 { color: #f7931e; }
  }

  &.task-list-container {
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
    .panel-header h3 { color: #ec4899; }
  }

  &.pie-panel {
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 3px;
      background: linear-gradient(90deg, #4f46e5, rgba(79, 70, 229, 0.6));
      z-index: 1;
    }
    .panel-header h3 { color: #4f46e5; }
  }
}

// 完全复用参考代码的page-header样式
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(46, 125, 50, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: linear-gradient(90deg, #facc15, #2e7d32, #ec4899);
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    background: linear-gradient(90deg, #facc15, #2e7d32, #ec4899);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
    font-weight: 700;
    letter-spacing: 0.1vw;
    text-shadow: 0 0 8px rgba(46, 125, 50, 0.2);

    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
      margin-right: 0.5vw;
      filter: drop-shadow(0 0 3px rgba(46, 125, 50, 0.5));
    }
  }

  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }

  .el-button {
    --el-button-text-color: #ffffff;
    --el-button-bg-color: rgba(46, 125, 50, 0.2);
    --el-button-border-color: #2e7d32;
    --el-button-hover-bg-color: rgba(46, 125, 50, 0.4);
    --el-button-hover-border-color: #ec4899;
    --el-button-active-bg-color: rgba(236, 72, 153, 0.3);
    --el-button-active-border-color: #ec4899;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
    transition: all 0.3s ease;

    &.el-button--primary {
      --el-button-bg-color: #2e7d32;
      --el-button-hover-bg-color: #4caf50;
      --el-button-active-bg-color: #1b5e20;
    }
  }
}

.main-content {
  display: flex;
  gap: 1.2vw;
  height: calc(100vh - 12vh); // 参考代码：仅此处用固定vh计算

  .map-area {
    flex: 1;
    height: 100%;
    display: flex;
    flex-direction: column;
    gap: 1vw;

    .map-top-stats {
      width: 100%;

      .stats-cards {
        display: flex;
        gap: 1vw;
        width: 100%;
        box-sizing: border-box;

        .stat-card {
          flex: 1;
          background: rgba(0, 68, 102, 0.8);
          border-radius: 8px;
          padding: 0.8vw 1vw;
          border: 1px solid rgba(46, 125, 50, 0.3);
          box-shadow: 0 0 1vw rgba(46, 125, 50, 0.2);
          cursor: default;
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
            background: linear-gradient(45deg, transparent, rgba(46, 125, 50, 0.1));
            z-index: 0;
          }

          &:hover {
            box-shadow: 0 0 1vw rgba(46, 125, 50, 0.2);
            border-color: rgba(46, 125, 50, 0.3);
          }

          &.cover-card {
            &.normal-rate {
              background: rgba(255, 204, 211, 0.1);
              .stat-value {
                color: #38b000;
                background: linear-gradient(90deg, #38b000, #4caf50);
                -webkit-background-clip: text;
                background-clip: text;
              }
              .stat-title, .stat-desc { color: #c8e6c9; }
            }
            &.warning-rate {
              background: rgba(255, 179, 186, 0.2);
              .stat-value {
                color: #f7931e;
                background: linear-gradient(90deg, #f7931e, #fbb866);
                -webkit-background-clip: text;
                background-clip: text;
              }
              .stat-title, .stat-desc { color: #ffe0b2; }
            }
          }

          &.disease-card {
            .stat-title::before { background: #ec4899; }
            .stat-title { color: #f074b3; }
            .stat-value {
              color: #ec4899;
              background: linear-gradient(90deg, #ec4899, #f074b3);
              -webkit-background-clip: text;
              background-clip: text;
            }
          }

          &.survival-card {
            .stat-title::before { background: #facc15; }
            .stat-title { color: #fce473; }
            .stat-value {
              color: #facc15;
              background: linear-gradient(90deg, #facc15, #fce473);
              -webkit-background-clip: text;
              background-clip: text;
            }
          }

          .stat-title {
            font-size: 1vw;
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
            text-shadow: 0 0 8px rgba(46, 125, 50, 0.2);

            .unit {
              font-size: 1vw;
              margin-left: 0.5vw;
            }
          }

          .stat-desc {
            font-size: 0.7vw;
            line-height: 1.4;
            position: relative;
            z-index: 1;
          }
        }
      }
    }

    .map-container {
      width: 100%;
      flex: 1; // 参考代码：占满剩余空间
      position: relative;
      padding: 0;
    }
  }

  .right-area {
    flex: 1;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    .right-list-area {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 1vw;
      height: 100%;

      .filter-bar.list-filter-bar {
        display: flex;
        gap: 0.8vw;
        align-items: center;
        padding: 0.8vw 1.2vw;
        background: rgba(0, 68, 102, 0.5);
        border-radius: 8px;
        border: 1px solid rgba(46, 125, 50, 0.3);
        width: 100%;
        flex-direction: row;

        .el-select, .el-button {
          font-size: 0.8vw;
        }

        .el-select {
          width: calc(20% - 0.8vw);
          max-width: 12vw;
          --el-select-text-color: #ffffff;
          --el-select-border-color: #f7931e;
          --el-select-hover-border-color: #ec4899;
          --el-select-dropdown-bg-color: rgba(0, 68, 102, 0.5);
          --el-select-option-hover-bg-color: rgba(247, 147, 30, 0.2);
        }

        .el-button--success {
          --el-button-bg-color: rgba(76, 175, 80, 0.2);
          --el-button-border-color: #4caf50;
          --el-button-text-color: #e8f5e9;
          --el-button-hover-bg-color: #4caf50;
        }
      }

      .task-list-container {
        flex: 1; // 参考代码：占满剩余空间
        background: rgba(0, 47, 75, 0.4);
        border-radius: 8px;
        border: 1px solid rgba(46, 125, 50, 0.3);
        overflow-y: auto;
        position: relative;

        .table-scroll {
          flex: 1; // 参考代码：核心！流式滚动区域，无硬编码高度
          overflow-y: auto;

          &::-webkit-scrollbar {
            width: 6px;
            height: 6px;
          }
          &::-webkit-scrollbar-track {
            background: rgba(0, 68, 102, 0.8);
            border-radius: 3px;
          }
          &::-webkit-scrollbar-thumb {
            background: linear-gradient(180deg, #2e7d32, #ec4899);
            border-radius: 3px;
          }
          &::-webkit-scrollbar-thumb:hover {
            background: linear-gradient(180deg, #ec4899, #7e57c2);
          }
        }

        .task-list-enter-active, .task-list-leave-active {
          transition: all 0.5s ease;
        }
        .task-list-enter-from, .task-list-leave-to {
          opacity: 0;
          transform: translateY(30px);
        }

        .el-table {
          --el-table-bg-color: transparent;
          --el-table-text-color: #ffffff;
          --el-table-border-color: rgba(46, 125, 50, 0.3);
          --el-table-header-text-color: #facc15;
          --el-table-header-bg-color: rgba(0, 68, 102, 0.5);
          --el-table-row-hover-bg-color: rgba(46, 125, 50, 0.1);
          --el-table-row-stripes-bg-color: rgba(0, 68, 102, 0.3);

          .el-table__cell {
            padding: 1vh 0.8vw !important;
            font-size: 0.8vw;
          }

          .el-tag {
            font-size: 0.75vw;
            padding: 0.2vh 0.6vw;
            border-radius: 12px;
            font-weight: 500;
            &.disease-tag {
              animation: pulse 2s infinite;
            }
          }

          .el-button {
            padding: 0.2vh 0.6vw;
            font-size: 0.7vw;
          }
        }
      }
    }
  }
}

// 完全复用参考代码的弹窗样式
.work-order-dialog, .dispatch-dialog {
  --el-dialog-bg-color: rgba(0, 47, 75, 0.4);
  --el-dialog-border-color: rgba(46, 125, 50, 0.3);
  --el-dialog-title-color: #facc15;
  --el-dialog-text-color: #ffffff;
  --el-dialog-header-border-color: rgba(46, 125, 50, 0.3);
  --el-dialog-footer-border-color: rgba(46, 125, 50, 0.3);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(46, 125, 50, 0.3), 0 0 40px rgba(236, 72, 153, 0.1);

  .el-dialog__header {
    border-bottom: 1px solid rgba(46, 125, 50, 0.3);
    position: relative;
    &::after {
      content: '';
      position: absolute;
      bottom: -1px;
      left: 0;
      width: 100%;
      height: 1px;
      background: linear-gradient(90deg, #2e7d32, #ec4899, #f7931e, transparent);
    }
  }

  .work-order-detail {
    .detail-section {
      margin-bottom: 24px;
      h3 {
        font-size: 16px;
        color: #facc15;
        margin-bottom: 12px;
        padding-bottom: 6px;
        border-bottom: 1px solid rgba(46, 125, 50, 0.3);
        font-weight: 600;
        background: linear-gradient(90deg, #facc15, #f7931e);
        -webkit-background-clip: text;
        background-clip: text;
      }
    }

    .el-descriptions {
      --el-descriptions-bg-color: transparent;
      --el-descriptions-border-color: rgba(46, 125, 50, 0.3);
      --el-descriptions-content-color: #ffffff;
      font-size: 0.9vw;

      .el-descriptions__label {
        color: #2e7d32;
        font-weight: 500;
      }
    }
  }

  .el-form {
    .el-form-item {
      label { color: #ffffff; }
      .el-select { --el-select-border-color: #ec4899; --el-select-hover-border-color: #7e57c2; }
      .el-date-picker { --el-input-border-color: #14b8a6; --el-input-hover-border-color: #2e7d32; }
      .el-input { --el-input-border-color: #4f46e5; --el-input-hover-border-color: #ec4899; }
    }
  }

  .el-dialog__footer {
    border-top: 1px solid rgba(46, 125, 50, 0.3);
    padding-top: 15px;
  }
}

// 标签样式：复用参考代码逻辑
.el-tag--success {
  --el-tag-bg-color: rgba(46, 125, 50, 0.2);
  --el-tag-border-color: #2e7d32;
  --el-tag-text-color: #2e7d32;
}
.el-tag--info {
  --el-tag-bg-color: rgba(0, 198, 255, 0.2);
  --el-tag-border-color: #00c6ff;
  --el-tag-text-color: #00c6ff;
}
.el-tag--purple {
  --el-tag-bg-color: rgba(156, 39, 176, 0.2);
  --el-tag-border-color: #9c27b0;
  --el-tag-text-color: #9c27b0;
}
.el-tag--danger {
  --el-tag-bg-color: rgba(236, 72, 153, 0.2);
  --el-tag-border-color: #ec4899;
  --el-tag-text-color: #ec4899;
}
.el-tag--warning {
  --el-tag-bg-color: rgba(247, 147, 30, 0.2);
  --el-tag-border-color: #f7931e;
  --el-tag-text-color: #f7931e;
}

// 动画：复用参考代码
@keyframes pulse {
  0% { box-shadow: 0 0 0 0 rgba(236, 72, 153, 0.4); }
  70% { box-shadow: 0 0 0 6px rgba(236, 72, 153, 0); }
  100% { box-shadow: 0 0 0 0 rgba(236, 72, 153, 0); }
}

// 表格深度样式：完全复用参考代码（仅替换主题色）
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(0, 47, 75, 0.4);
  border-color: rgba(46, 125, 50, 0.3);

  th {
    background-color: rgba(0, 68, 102, 0.5);
    color: #facc15;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(46, 125, 50, 0.3);
    font-weight: 500;
    background: linear-gradient(90deg, rgba(0, 68, 102, 0.5), rgba(0, 47, 75, 0.5));
  }

  td {
    background-color: transparent;
    color: #ffffff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(46, 125, 50, 0.3);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .el-table__row {
    background-color: rgba(0, 47, 75, 0.3);
    &:nth-child(even) { background-color: rgba(0, 68, 102, 0.3); }
    &:hover > td { background-color: rgba(46, 125, 50, 0.1) !important; color: #facc15; }
  }

  .el-table__row--current > td {
    background-color: rgba(236, 72, 153, 0.1) !important;
    color: #ec4899;
  }

  .el-table__empty-text {
    color: #c8e6c9;
    background-color: rgba(0, 47, 75, 0.4);
  }

  &::before { background-color: rgba(46, 125, 50, 0.3); }
  .el-table__fixed-right::before, .el-table__fixed::before { background-color: transparent; }
}

// 容器基础样式：完全复用参考代码（仅替换主题色）
.garden-greening-container {
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
    background: linear-gradient(135deg, rgba(0, 47, 75, 0.5) 0%, rgba(0, 102, 153, 0.4) 100%);
    z-index: -1;
  }
}

// 响应式：参考代码仅微调字体，不改变布局结构
@media screen and (max-width: 1440px) {
  .page-header h1 {
    font-size: 1.8vw;
    .title-icon { width: 2vw; height: 2vw; }
  }
  .stat-card {
    .stat-title { font-size: 0.9vw; }
    .stat-value { font-size: 1.6vw; .unit { font-size: 0.9vw; } }
    .stat-desc { font-size: 0.85vw; }
  }
  :deep(.el-table .el-tag) { font-size: 0.8vw; }
  :deep(.el-dialog) {
    .el-dialog__title { font-size: 1.1vw; }
    .detail-section h3 { font-size: 1vw; }
    .el-button { font-size: 0.9vw; }
  }
}

// 参考代码无「小屏幕改列布局」逻辑，仅微调字体
@media screen and (max-width: 1024px) {
  .page-header h1 {
    font-size: 2.2vw;
    .title-icon { width: 2.4vw; height: 2.4vw; }
  }
  .stat-card {
    .stat-title { font-size: 1vw; }
    .stat-value { font-size: 1.8vw; }
  }
  :deep(.el-dialog) {
    width: 80% !important;
    .el-dialog__body { max-height: 60vh; overflow-y: auto; }
  }
}
</style>
