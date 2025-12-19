<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="left">
        <!-- 市政设施专题图表区域（left_top） -->
        <div class="panel left_top">
          <div class="chart-header">
            <h3>市政设施专题</h3>
            <el-button size="large" type="primary" @click="jumpToMunicipalFacility()">
              进入详情
            </el-button>
          </div>
          <div class="chart-content">
            <div class="chart-item pie-chart">
              <h4>故障类型分布</h4>
              <ChartPie2 :data="faultTypeDistribution" />
            </div>
            <div class="chart-item trend-chart">
              <h4>近7天故障趋势</h4>
              <ChartLine2 :xAxis="faultTrend.xAxis" :series="faultTrend.series" :yAxisName="''" />
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 市容秩序专题图表区域（left_bottom） -->
        <div class="panel left_bottom">
          <div class="chart-header">
            <h3>市容秩序专题</h3>
            <el-button size="large" type="primary" @click="jumpToCityscapeOrder()">
              进入详情
            </el-button>
          </div>
          <div class="chart-content">
            <div class="chart-item complete-rate-chart">
              <h4>近7天办结率趋势</h4>
              <ChartLine3 :xAxis="completeRateTrend.xAxis" :series="completeRateTrend.series" :yAxisName="''" />
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <div class="middle">
        <div class="middle_top">
          <el-button size="large" type="primary" @click="jumpToEnvironSanitation()">
            环境卫生专题
          </el-button>
        </div>
        <div class="middle_bottom">
          <el-button size="large" type="primary" @click="jumpToGardenGreening()">
            园林绿化专题
          </el-button>
        </div>
      </div>

      <div class="right">
        <div class="right_top">
          违法建设专题
        </div>
        <div class="right_bottom">
          建筑工地专题
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElButton, ElMessage } from 'element-plus';

// 导入图表组件
import ChartLine2 from './ChartLine2.vue';
import ChartPie2 from './ChartPie2.vue';
import ChartLine3 from './ChartLine3.vue';

// 导入API请求函数
import {
  fetchFaultTrend,
  fetchFaultTypeDistribution,
  fetchCompleteRateTrend
} from '@/api/overview/urbanmanagement/SubdomainScenarioTopics.js'; // 请根据实际路径调整

const router = useRouter();

// 跳转函数
const jumpToMunicipalFacility = () => {
  router.push('/overview/municipalfacility/dpzl');
};

const jumpToCityscapeOrder = () => {
  router.push('/overview/cityscapeorder/dpzl');
};

const jumpToEnvironSanitation = () => {
  router.push('/overview/environsanitation/dpzl');
};

const jumpToGardenGreening = () => {
  router.push('/overview/gardengreening/dpzl');
};


// 响应式数据 - 市政设施专题
const faultTrend = ref({ xAxis: [], series: [] });
const faultTypeDistribution = ref({ legend: [], series: [] });

// 响应式数据 - 市容秩序专题
const completeRateTrend = ref({ xAxis: [], series: [] });

// 初始化数据
const initData = async () => {
  try {
    // 请求市政设施图表数据
    faultTrend.value = await fetchFaultTrend();
    faultTypeDistribution.value = await fetchFaultTypeDistribution();

    // 请求市容秩序图表数据
    completeRateTrend.value = await fetchCompleteRateTrend();
  } catch (error) {
    ElMessage.error('图表数据加载失败');
    console.error('数据加载错误：', error);
  }
};

// 生命周期 - 挂载时加载数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
// 最外层容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  display: flex;
  box-sizing: border-box;
}

.mainbox {
  display: flex;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 88vh;
  box-sizing: border-box;
  gap: 0.6vw;
  width: 100%;
}

.panel {
  position: relative;
  height: 100%;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 1.5vh;
  height: 1.5vh;
  border-top: 0.5vh solid #02a6b5;
  border-left: 0.5vh solid #02a6b5;
}

.panel::after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  width: 1.5vh;
  height: 1.5vh;
  border-top: 0.5vh solid #02a6b5;
  border-right: 0.5vh solid #02a6b5;
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
  width: 1.5vh;
  height: 1.5vh;
  border-bottom: 0.5vh solid #02a6b5;
  border-left: 0.5vh solid #02a6b5;
}

.panel-footer::after {
  content: '';
  position: absolute;
  bottom: 0;
  right: 0;
  width: 1.5vh;
  height: 1.5vh;
  border-bottom: 0.5vh solid #02a6b5;
  border-right: 0.5vh solid #02a6b5;
}

// 左侧区域
.left {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.6vw;
  box-sizing: border-box;

  // 市政设施专题（left_top）
  .left_top {
    height: 50%;
    padding: 0.5vw;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;

    .chart-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 0.5vw;
      padding-bottom: 0.3vw;
      border-bottom: 1px solid rgba(0, 204, 255, 0.3);

      h3 {
        margin: 0;
        font-size: 1vw;
        color: #00c6ff;
        text-shadow: 0 0 5px rgba(0, 198, 255, 0.5);
      }

      .el-button {
        --el-button-text-color: #fff;
        --el-button-bg-color: #00c6ff;
        --el-button-border-color: #00c6ff;
        --el-button-hover-bg-color: #00a8e8;
        font-size: 0.8vw;
        padding: 0.4vw 1vw;
      }
    }

    .chart-content {
      flex: 1;
      display: flex;
      gap: 0.5vw;

      .chart-item {
        flex: 1;
        display: flex;
        flex-direction: column;

        h4 {
          margin: 0 0 0.3vw 0;
          font-size: 0.8vw;
          color: #67e8f9;
          text-align: center;
        }

        :deep(.chart-container) {
          width: 100%;
          height: calc(100% - 1.5vw);
        }
      }

      .pie-chart {
        flex: 4;
      }

      .trend-chart {
        flex: 6;
      }
    }
  }

  // 市容秩序专题（left_bottom）
  .left_bottom {
    height: 48%;
    padding: 0.5vw;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;

    .chart-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 0.5vw;
      padding-bottom: 0.3vw;
      border-bottom: 1px solid rgba(0, 204, 255, 0.3);

      h3 {
        margin: 0;
        font-size: 1vw;
        color: #ec4899;
        text-shadow: 0 0 5px rgba(236, 72, 153, 0.5);
      }

      .el-button {
        --el-button-text-color: #fff;
        --el-button-bg-color: #ec4899;
        --el-button-border-color: #ec4899;
        --el-button-hover-bg-color: #d13b82;
        font-size: 0.8vw;
        padding: 0.4vw 1vw;
      }
    }

    .chart-content {
      flex: 1;

      .chart-item {
        height: 100%;
        display: flex;
        flex-direction: column;

        h4 {
          margin: 0 0 0.3vw 0;
          font-size: 0.8vw;
          color: #14b8a6;
          text-align: center;
        }

        :deep(.chart-container) {
          width: 100%;
          height: calc(100% - 1.5vw);
        }
      }
    }
  }
}

// 中间区域
.middle {
  flex: 1;
  border: solid 2px black;
  display: flex;
  flex-direction: column;
  gap: 0.6vw;
  padding: 0.5vw;
  box-sizing: border-box;

  .middle_top {
    height: 50%;
    border: solid 2px black;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2vw;
    color: #facc15;
  }

  .middle_bottom {
    height: 48%;
    border: solid 2px black;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2vw;
    color: #10b981;
  }
}

// 右侧区域
.right {
  flex: 1;
  border: solid 2px black;
  display: flex;
  flex-direction: column;
  gap: 0.6vw;
  padding: 0.5vw;
  box-sizing: border-box;

  .right_top {
    height: 50%;
    border: solid 2px black;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2vw;
    color: #f97316;
  }

  .right_bottom {
    height: 48%;
    border: solid 2px black;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2vw;
    color: #8b5cf6;
  }
}

// 图表容器通用样式
:deep(.chart-container) {
  width: 100%;
  height: 100%;
}

:deep(.el-chart) {
  width: 100%;
  height: 100%;
}
</style>
