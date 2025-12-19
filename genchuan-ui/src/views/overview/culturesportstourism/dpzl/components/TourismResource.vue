<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/resource.png"
          alt="文旅资源图标"
          class="title-icon"
        />
        文旅资源专题
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 核心指标卡片区（顶部10%） -->
    <div class="stats-cards">
      <div class="stat-card">
        <div class="stat-title">资源总数（个）</div>
        <div class="stat-value" ref="totalCountRef">
          {{ totalSceneCount }}
        </div>
        <div class="stat-desc">截至 {{ formatDate(new Date()) }} 统计</div>
      </div>
      <div class="stat-card" :class="{ 'flash-card': operationRate < 80 && flashFlag }">
        <div class="stat-title">资源运营率（%）</div>
        <div class="stat-value" :class="operationRate >= 80 ? 'normal' : 'warning'" ref="rateRef">
          {{ operationRate.toFixed(1) }}
        </div>
        <div class="stat-desc">
          已使用: {{ usedCount }} 个 | 未使用: {{ totalSceneCount - usedCount }} 个
        </div>
      </div>
    </div>

    <!-- 主内容区域（左侧40% + 右侧60%） -->
    <div class="main-content">
      <!-- 左侧图表区（40%） -->
      <div class="left-section">
        <!-- 类型分布饼图（上50%） -->
        <div class="panel pie-panel" ref="piePanel">
          <div class="panel-header">
            <h2>资源类型分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('piePanel')">
                <el-icon color="#9333ea" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartPie
              :data="typeDistributionData"
              title="文旅资源类型占比"
              :baseFontScale="1"
              @chartClick="handlePieClick"
            />
          </div>
        </div>

        <!-- 状态分布柱状图（下50%） -->
        <div class="panel bar-panel" ref="barPanel">
          <div class="panel-header">
            <h2>资源状态分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('barPanel')">
                <el-icon color="#9333ea" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="statusDistributionData"
              :baseFontScale="1"
            />
          </div>
        </div>
      </div>

      <!-- 右侧闲置资源列表区（60%） -->
      <div class="right-section">
        <div class="panel list-panel" ref="listPanel">
          <div class="panel-header">
            <h2>闲置资源列表</h2>
            <div class="header-actions">
              <!-- 筛选器组 -->
              <el-select v-model="filterParams.typeCode" placeholder="资源类型" size="small">
                <el-option label="全部" value="" />
                <el-option
                  v-for="item in typeDistributionData.legend"
                  :key="item"
                  :label="item"
                  :value="getTypeCode(item)"
                />
              </el-select>
              <el-select v-model="filterParams.regionCode" placeholder="行政区域" size="small">
                <el-option label="全部" value="" />
                <el-option label="主城区" value="001" />
                <el-option label="东部新区" value="002" />
                <el-option label="西部文旅区" value="003" />
                <el-option label="南部乡村区" value="004" />
              </el-select>
              <el-select v-model="filterParams.idleDays" placeholder="闲置天数" size="small">
                <el-option label="全部" value="" />
                <el-option label="＞15天" value="15" />
                <el-option label="＞30天" value="30" />
              </el-select>
              <el-button size="small" type="primary" @click="resetFilter">重置</el-button>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('listPanel')">
                <el-icon color="#9333ea" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <!-- 列表滚动容器 -->
          <div class="panel-body table-scroll">
            <el-table
              :data="filteredIdleResources"
              border
              size="small"
              style="width: 100%"
              @row-dblclick="handleRowDblClick"
            >
              <el-table-column prop="app_scene_name" label="资源名称" />
              <el-table-column prop="idle_days" label="闲置天数（天）">
                <template #default="scope">
                  <span :class="getIdleDaysClass(scope.row.idle_days)">
                    {{ scope.row.idle_days }}
                  </span>
                </template>
              </el-table-column>
              <el-table-column prop="responsible_person" label="负责人" />
              <el-table-column prop="contact" label="联系方式" />
              <el-table-column label="操作">
                <template #default="scope">
                  <el-button size="small" link @click="handleDisposeResource(scope.row)">处置</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </div>

    <!-- 资源详情弹窗 -->
    <el-dialog
      v-model="resourceDetailVisible"
      :title="currentResource?.app_scene_name || '文旅资源详情'"
      width="800px"
    >
      <div class="resource-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="资源ID">{{ currentResource?.scene_id }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ currentResource?.minor_name }}</el-descriptions-item>
            <el-descriptions-item label="行政区域">{{ currentResource?.region_name }}</el-descriptions-item>
            <el-descriptions-item label="资源状态">{{ getStatusName(currentResource?.scene_status) }}</el-descriptions-item>
            <el-descriptions-item label="闲置天数">{{ currentResource?.idle_days }} 天</el-descriptions-item>
            <el-descriptions-item label="最后运营时间">{{ currentResource?.last_operation_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 运营数据区域 -->
        <div class="detail-section">
          <h3>运营数据</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="累计使用次数">{{ currentResource?.total_used_count }} 次</el-descriptions-item>
            <el-descriptions-item label="月均使用次数">{{ currentResource?.monthly_used_count }} 次</el-descriptions-item>
            <el-descriptions-item label="运营率">{{ currentResource?.scene_operation_rate }}%</el-descriptions-item>
            <el-descriptions-item label="异常记录数">{{ currentResource?.exception_count }} 条</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 操作按钮 -->
        <div class="detail-actions">
          <el-button @click="viewOperationRecord">查看运营记录</el-button>
          <el-button type="primary" @click="handleUpdateResourceStatus">更新资源状态</el-button>
        </div>
      </div>
    </el-dialog>

    <!-- 运营记录弹窗 -->
    <el-dialog
      v-model="operationRecordVisible"
      title="资源运营记录"
      width="900px"
    >
      <div class="operation-record">
        <div class="panel-body">
          <ChartLine
            :xAxis="operationRecordXAxis"
            :series="operationRecordSeries"
            yAxisName="使用次数"
          />
        </div>
        <el-table
          :data="operationRecordList"
          border
          size="small"
          style="width: 100%; margin-top: 16px"
        >
          <el-table-column prop="record_time" label="运营时间" />
          <el-table-column prop="used_count" label="使用次数" />
          <el-table-column prop="operation_type" label="运营类型" />
          <el-table-column prop="operator" label="操作人员" />
          <el-table-column prop="remark" label="备注" />
        </el-table>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance, watch, nextTick } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
import screenFull from 'screenfull';
import ChartPie from './ChartPie1.vue';
import ChartBar from './ChartBar1.vue';
import ChartLine from './ChartLine2.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入文旅资源API
import {
  fetchTourismResourceOverview,
  fetchResourceTypeDistribution,
  fetchResourceStatusDistribution,
  fetchIdleResourceList,
  fetchResourceOperationRecord,
  updateResourceStatus
} from '@/api/overview/culturesportstourism/SubdomainScenarioTopics.js';

// 初始化变量
const router = useRouter();
const instance = getCurrentInstance();

// 面板全屏切换
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

// 核心数据变量
const totalSceneCount = ref(0);
const usedCount = ref(0);
const operationRate = ref(0);
const typeDistributionData = ref({ legend: [], series: [] });
const statusDistributionData = ref({ xAxis: [], series: [] });
const idleResources = ref([]);
const currentResource = ref(null);
const resourceDetailVisible = ref(false);
const operationRecordVisible = ref(false);
const operationRecordXAxis = ref([]);
const operationRecordSeries = ref([]);
const operationRecordList = ref([]);

// 筛选参数
const filterParams = ref({
  typeCode: '',
  regionCode: '',
  idleDays: ''
});

// 动效相关
const flashFlag = ref(false);
const totalCountRef = ref(null);
const rateRef = ref(null);

// 格式化日期
const formatDate = (date) => {
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
};

// 资源类型编码映射
const getTypeCode = (typeName) => {
  const map = {
    '自然景区': 'type_1',
    '人文古迹': 'type_2',
    '文旅综合体': 'type_3',
    '乡村旅游点': 'type_4',
    '研学基地': 'type_5'
  };
  return map[typeName] || '';
};

// 资源状态名称映射
const getStatusName = (status) => {
  const map = {
    'normal': '正常运营',
    'idle': '闲置',
    'maintenance': '维护中',
    'pending': '待验收',
    'stopped': '已停用'
  };
  return map[status] || '未知状态';
};

// 闲置天数样式类
const getIdleDaysClass = (days) => {
  if (days > 30) return 'idle-over-30';
  if (days > 15) return 'idle-over-15';
  return '';
};

// 筛选后的闲置资源
const filteredIdleResources = computed(() => {
  return idleResources.value.filter(item => {
    // 类型筛选
    const matchType = !filterParams.value.typeCode || item.minor_code === filterParams.value.typeCode;
    // 区域筛选
    const matchRegion = !filterParams.value.regionCode || item.region_code === filterParams.value.regionCode;
    // 闲置天数筛选
    let matchIdleDays = true;
    if (filterParams.value.idleDays) {
      const threshold = Number(filterParams.value.idleDays);
      matchIdleDays = item.idle_days > threshold;
    }
    return matchType && matchRegion && matchIdleDays;
  });
});

// 数字滚动动效
const numberRoll = (el, target, isRate = false) => {
  if (!el) return;
  let current = 0;
  const step = target / 50;
  const timer = setInterval(() => {
    current += step;
    if (current >= target) {
      clearInterval(timer);
      current = target;
    }
    el.textContent = isRate ? `${current.toFixed(1)}` : `${Math.floor(current)}`;
  }, 16);
};

// 运营率卡片闪烁动效
const startFlash = () => {
  if (operationRate.value < 80) {
    flashFlag.value = true;
    setTimeout(() => {
      flashFlag.value = false;
      setTimeout(() => startFlash(), 2000);
    }, 500);
  }
};

// 饼图点击筛选
const handlePieClick = (params) => {
  const typeName = params.name;
  filterParams.value.typeCode = getTypeCode(typeName);
  ElMessage.info(`已筛选：${typeName} 类型闲置资源`);
};

// 列表行双击查看详情
const handleRowDblClick = async (row) => {
  currentResource.value = { ...row };
  resourceDetailVisible.value = true;
};

// 查看运营记录
const viewOperationRecord = async () => {
  const recordData = await fetchResourceOperationRecord(currentResource.value.scene_id);
  operationRecordXAxis.value = recordData.xAxis;
  operationRecordSeries.value = recordData.series;
  operationRecordList.value = recordData.recordList;
  operationRecordVisible.value = true;
};

// 更新资源状态
const handleUpdateResourceStatus = async () => {
  try {
    await ElMessageBox.confirm('确认更新该资源状态为「正常运营」？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });
    // 调用API更新状态
    await updateResourceStatus(currentResource.value.scene_id, 'normal');

    // 前端数据同步（柱状图平滑动效）
    currentResource.value.scene_status = 'normal';
    currentResource.value.idle_days = 0;
    const statusSeries = statusDistributionData.value.series[0].data;
    statusSeries[0] += 1; // 正常运营+1
    statusSeries[1] -= 1; // 闲置-1
    statusDistributionData.value = { ...statusDistributionData.value };

    // 移除闲置列表项（淡出效果）
    idleResources.value = idleResources.value.filter(item => item.scene_id !== currentResource.value.scene_id);

    resourceDetailVisible.value = false;
  } catch (error) {
    if (error !== 'cancel') {
      console.error('更新资源状态失败:', error);
    }
  }
};

// 处置闲置资源
const handleDisposeResource = async (row) => {
  try {
    await ElMessageBox.confirm(`确认处置「${row.app_scene_name}」？`, '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });
    // 移除列表项
    idleResources.value = idleResources.value.filter(item => item.scene_id !== row.scene_id);
    ElMessage.success('闲置资源处置成功');
  } catch (error) {
    if (error !== 'cancel') {
      console.error('处置闲置资源失败:', error);
    }
  }
};

// 重置筛选条件
const resetFilter = () => {
  filterParams.value = {
    typeCode: '',
    regionCode: '',
    idleDays: ''
  };
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('文旅资源数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/culturesportstourism/dpzl');
};

// 初始化数据
const initData = async () => {
  // 1. 获取概览数据
  const overviewData = await fetchTourismResourceOverview();
  totalSceneCount.value = overviewData.total_scene_count;
  usedCount.value = overviewData.used_count;
  operationRate.value = overviewData.operation_rate;

  // 数字滚动动效
  nextTick(() => {
    numberRoll(totalCountRef.value, overviewData.total_scene_count);
    numberRoll(rateRef.value, overviewData.operation_rate, true);
  });

  // 运营率闪烁动效
  startFlash();

  // 2. 获取类型分布数据
  const typeData = await fetchResourceTypeDistribution();
  typeDistributionData.value = typeData;

  // 3. 获取状态分布数据
  const statusData = await fetchResourceStatusDistribution();
  statusDistributionData.value = statusData;

  // 4. 获取闲置资源列表
  const idleData = await fetchIdleResourceList();
  idleResources.value = idleData;
};

// 监听运营率变化，触发闪烁
watch(operationRate, (newVal) => {
  if (newVal < 80) {
    startFlash();
  } else {
    flashFlag.value = false;
  }
});

// 组件挂载初始化
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
// 页面容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #555555;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;
}

// 全屏按钮
.panel-fullscreen-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 4px;
  transition: all 0.2s ease;
  &:hover {
    background: rgba(147, 51, 234, 0.2); // 紫色hover背景
  }
}

// 页头
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(147, 51, 234, 0.3);
  margin-bottom: 1.2vw;
  position: relative;
  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: linear-gradient(90deg, #9333ea, #a855f7); // 紫色渐变
  }
  h1 {
    margin: 0;
    font-size: 1.6vw;
    color: #d8b4fe; // 浅紫标题文字
    font-weight: 600;
    letter-spacing: 0.1vw;
    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
    }
  }
  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }
  .el-button {
    --el-button-text-color: #d8b4fe;
    --el-button-bg-color: rgba(147, 51, 234, 0.2); // 紫色按钮背景
    --el-button-border-color: #9333ea;
    --el-button-hover-bg-color: rgba(147, 51, 234, 0.4);
    --el-button-hover-border-color: #a855f7;
    --el-button-active-bg-color: rgba(147, 51, 234, 0.5);
    --el-button-active-border-color: #a855f7;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
  }
}

// 核心指标卡片
.stats-cards {
  display: flex;
  gap: 1.2vw;
  height: 10vh; // 保持高度不变
  margin-bottom: 1.2vw;
  .stat-card {
    flex: 1;
    background: rgba(255, 192, 203, 0.2);
    border-radius: 8px;
    padding: 0 1.2vw; // 调整内边距（上下0，左右保留）
    border: 1px solid rgba(147, 51, 234, 0.3);
    box-shadow: 0 0 1vw rgba(147, 51, 234, 0.15);
    transition: all 0.3s ease;
    display: flex;
    align-items: center; // 垂直居中
    justify-content: space-between; // 元素之间均匀分布
    gap: 1vw; // 元素之间的间距

    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 0 20px rgba(147, 51, 234, 0.25);
      border-color: #a855f7;
    }
    &.flash-card {
      animation: flash 1s infinite alternate;
    }

    .stat-title {
      font-size: 1.2vw !important;
      color: #e9d5ff;
      white-space: nowrap; // 强制不换行
      flex-shrink: 0; // 不被挤压
      display: flex;
      align-items: center;
      &::before {
        content: '';
        display: inline-block;
        width: 0.7vw;
        height: 1.4vh;
        background: #9333ea;
        margin-right: 0.8vw;
        border-radius: 50%;
      }
    }

    .stat-value {
      font-size: 1.6vw !important;
      color: #97e2f8;
      margin-left: 3vw;
      font-weight: 700;
      line-height: 1; // 行高重置
      white-space: nowrap; // 强制不换行
      flex-shrink: 0; // 不被挤压
      &.normal {
        color: #13ce66;
        text-shadow: 0 0 8px rgba(19, 206, 102, 0.3);
      }
      &.warning {
        color: #ffb800;
        text-shadow: 0 0 8px rgba(255, 184, 0, 0.3);
      }
    }

    .stat-desc {
      font-size: 0.8vw !important;
      color: #f0ecf5;
      line-height: 1; // 行高重置
      white-space: nowrap; // 强制不换行
      text-overflow: ellipsis; // 超出显示省略号
      overflow: hidden; // 隐藏超出内容
      flex: 1; // 占剩余空间（保证标题/数值不被挤压）
      text-align: right; // 描述文字右对齐（可选）
    }
  }
}

// 主内容区域（左侧40% + 右侧60%）
.main-content {
  display: flex;
  gap: 1.2vw;
  height: 76vh;
  .left-section {
    width: 40%;
    display: flex;
    flex-direction: column;
    gap: 1.2vw;
    .pie-panel {
      height: 50%;
    }
    .bar-panel {
      height: 50%;
    }
  }
  .right-section {
    width: 60%;
    .list-panel {
      height: 100%;
    }
  }
}

// 通用面板样式
.panel {
  position: relative;
  border: 1px solid rgba(147, 51, 234, 0.3);
  background: rgba(10, 16, 100, 0.6);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(147, 51, 234, 0.15);
  overflow: hidden;
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, #9333ea, rgba(147, 51, 234, 0.3)); // 紫色渐变顶边
  }
  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(147, 51, 234, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
    h2 {
      margin: 0;
      font-size: 1.1vw;
      color: #a855f7;
      font-weight: 600;
      display: flex;
      align-items: center;
    }
    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
      .el-select, .el-input {
        --el-select-text-color: white;
        --el-select-placeholder-color: #ccc;
        --el-select-dropdown-bg-color: rgba(16, 32, 80, 0.5);
        --el-select-dropdown-border-color: rgba(147, 51, 234, 0.3);
        --el-select-dropdown-item-hover-bg-color: rgba(147, 51, 234, 0.2); // 紫色hover
        --el-select-dropdown-item-text-color: white;
        --el-input-bg-color: rgba(16, 32, 64, 0.5);
        --el-input-border-color: rgba(147, 51, 234, 0.3);
        --el-input-text-color: white;
        --el-input-placeholder-color: #ccc;
        font-size: 0.7vw;
        width: 8vw;
      }
      .el-button--primary {
        --el-button-text-color: #fff;
        --el-button-bg-color: #9333ea;
        --el-button-border-color: #9333ea;
        --el-button-hover-bg-color: #a855f7;
        --el-button-hover-border-color: #a855f7;
        --el-button-active-bg-color: #7e22ce;
        --el-button-active-border-color: #7e22ce;
      }
    }
  }
  .panel-body {
    flex: 1;
    padding: 1.2vw;
    overflow: hidden;
    &.table-scroll {
      overflow-y: auto;
      &::-webkit-scrollbar {
        width: 6px;
        height: 6px;
      }
      &::-webkit-scrollbar-track {
        background: rgba(16, 32, 64, 0.5);
        border-radius: 3px;
      }
      &::-webkit-scrollbar-thumb {
        background: #9333ea; // 紫色滚动条
        border-radius: 3px;
      }
      &::-webkit-scrollbar-thumb:hover {
        background: #a855f7;
      }
    }
  }
  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(147, 51, 234, 0.1);
  }
}

// 闲置天数样式
.idle-over-30 {
  background-color: #ff4949;
  color: #fff;
  padding: 2px 8px;
  border-radius: 4px;
}
.idle-over-15 {
  background-color: #ffb800;
  color: #000;
  padding: 2px 8px;
  border-radius: 4px;
}

// 表格样式（紫色主题）
::v-deep .el-table {
  --el-table-bg-color: transparent;
  --el-table-text-color: white;
  --el-table-border-color: rgba(147, 51, 234, 0.1);
  --el-table-header-text-color: #a855f7; // 紫色表头
  --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
  --el-table-row-hover-bg-color: rgba(147, 51, 234, 0.1); // 紫色hover
  --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
  border-radius: 4px;
  overflow: hidden;
  .el-table__header {
    border-bottom: 1px solid rgba(147, 51, 234, 0.3);
  }
  .el-table__cell {
    border-bottom: 1px solid rgba(147, 51, 234, 0.1);
    padding: 1vh 0.8vw !important;
  }
  .el-tag {
    font-size: 0.75vw;
    padding: 0.2vh 0.6vw;
    border-radius: 12px;
  }
  table-layout: fixed;
  background-color: rgba(0, 30, 60, 0.4);
  border-color: rgba(147, 51, 234, 0.2);
  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #a855f7; // 紫色表头文字
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(147, 51, 234, 0.3);
    font-weight: 500;
  }
  td {
    background-color: transparent;
    color: #d8b4fe; // 浅紫单元格文字
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(147, 51, 234, 0.15);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .el-table__row {
    background-color: rgba(0, 30, 60, 0.5);
    &:nth-child(even) {
      background-color: rgba(0, 40, 80, 0.5);
    }
    &:hover > td {
      background-color: rgba(147, 51, 234, 0.3) !important; // 紫色hover
      color: #ffffff;
    }
  }
  .el-table__row--current > td {
    background-color: rgba(147, 51, 234, 0.4) !important;
    color: #ffffff;
  }
  .el-table__empty-text {
    color: #a855f7;
    background-color: rgba(0, 30, 60, 0.4);
  }
  &::before {
    background-color: rgba(147, 51, 234, 0.2);
  }
  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }
}

// 详情弹窗样式
.resource-detail, .operation-record {
  .detail-section {
    margin-bottom: 24px;
    h3 {
      font-size: 16px;
      color: #a855f7;
      margin-bottom: 12px;
      padding-bottom: 6px;
      border-bottom: 1px solid rgba(147, 51, 234, 0.3);
      font-weight: 600;
    }
  }
  .detail-actions {
    margin-top: 16px;
    text-align: right;
  }
  .el-descriptions {
    --el-descriptions-bg-color: transparent;
    --el-descriptions-border-color: rgba(147, 51, 234, 0.1);
    --el-descriptions-title-color: white;
    --el-descriptions-content-color: #d8b4fe; // 浅紫内容
    font-size: 0.9vw;
    .el-descriptions__label {
      color: #a855f7;
      font-weight: 500;
      width: 110px !important;
      text-align: left !important;
    }
    .el-descriptions__content {
      color: #d8b4fe;
      padding: 10px 0;
    }
    .el-descriptions__item {
      border-bottom: 1px solid rgba(147, 51, 234, 0.1);
      &:last-child {
        border-bottom: none;
      }
    }
  }
}

// 弹窗样式（紫色主题）
::v-deep .el-dialog {
  --el-dialog-bg-color: rgba(10, 16, 100, 0.6);
  --el-dialog-border-color: rgba(147, 51, 234, 0.3);
  --el-dialog-title-color: #a855f7;
  --el-dialog-text-color: white;
  --el-dialog-header-border-color: rgba(147, 51, 234, 0.1);
  --el-dialog-footer-border-color: rgba(147, 51, 234, 0.1);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(147, 51, 234, 0.2); // 紫色阴影
  .el-dialog__title {
    font-size: 18px;
    font-weight: 600;
  }
  .el-dialog__header {
    padding: 16px 20px;
    border-bottom: 1px solid rgba(147, 51, 234, 0.1);
  }
  .el-dialog__body {
    padding: 20px;
    color: #d8b4fe; // 浅紫文字
  }
  .el-dialog__footer {
    padding: 12px 20px;
    border-top: 1px solid rgba(147, 51, 234, 0.1);
  }
}

// 动效
@keyframes flash {
  from {
    box-shadow: 0 0 10px rgba(147, 51, 234, 0.3);
    border-color: #9333ea;
  }
  to {
    box-shadow: 0 0 20px rgba(147, 51, 234, 0.6);
    border-color: #a855f7;
  }
}

// 列表淡入淡出动效
::v-deep .el-table__row {
  animation: fadeIn 0.5s ease-in-out;
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
