<template>
  <el-card shadow="never" class="mt-8px">
    <el-skeleton :loading="loading" animated>
      <el-row :gutter="20" justify="space-between">
        <el-col :xl="10" :lg="10" :md="24" :sm="24" :xs="24">
          <el-card shadow="hover" class="mb-8px">
            <el-skeleton :loading="loading" animated>
              <Echart :options="pieOptionsData" :height="280" />
            </el-skeleton>
          </el-card>
        </el-col>
        <el-col :xl="14" :lg="14" :md="24" :sm="24" :xs="24">
          <el-card shadow="hover" class="mb-8px">
            <el-skeleton :loading="loading" animated>
              <Echart :options="barOptionsData" :height="280" />
            </el-skeleton>
          </el-card>
        </el-col>
      </el-row>
    </el-skeleton>
  </el-card>
</template>


<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { EChartsOption, LegendComponentOption, XAXisComponentOption, SeriesOption } from 'echarts'
import { AlarmStatisticsApi } from '@/api/dataHub/warningAlarm/statistics'
import { set } from 'lodash-es' // 引入lodash的set方法
// const { t } = useI18n() // 国际化
// 定义饼图配置项
const pieOptionsData: EChartsOption = {
  title: {
    text: '告警级别统计',
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left',
    data: []
  },
  series: [
    {
      name: '告警数量',
      type: 'pie',
      radius: '55%',
      center: ['50%', '60%'],
      data: [],
      label: {
        formatter: '{b}: {c} ({d}%)'
      }
    }
  ]
};

// 定义柱状图配置项
const barOptionsData: EChartsOption = {
  title: {
    text: '告警状态统计',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  grid: {
    left: 50,
    right: 20,
    bottom: 20
  },
  xAxis: {
    type: 'category',
    data: [],
    axisTick: {
      alignWithLabel: true
    }
  } as XAXisComponentOption,
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '告警数量',
      type: 'bar',
      data: []
    } as SeriesOption
  ]
};

const loading = ref(true);

/** 获取饼图统计数据 */
const getPieStatistics = async (id: number) => {
  try {
    loading.value = true;
    const response = await AlarmStatisticsApi.getAlarmLevelStatistics(id);
    const data = response || [];
    // 处理数据：将alarmLevel映射为name，alarmCount映射为value
    const pieData = data.map(item => ({
      name: item.category,
      value: item.count
    }));

    // 提取图例数据（告警级别名称）
    const legendData = data.map(item => item.category);

    // 类型安全地更新图例数据
    if (pieOptionsData.legend) {
      (pieOptionsData.legend as LegendComponentOption).data = legendData;
    }

    // 类型安全地更新系列数据
    if (pieOptionsData.series && pieOptionsData.series[0]) {
      pieOptionsData.series[0].data = pieData;
    }
  } catch (error) {
    console.error('获取饼图统计数据失败:', error);
  }
};

/** 获取柱状图统计数据 */
const getBarStatistics = async (id: number) => {
  try {
    const response = await AlarmStatisticsApi.getAlarmStatusStatistics(id);
    const data = response || [];
    // 处理数据：提取alarmStatus作为x轴类目，alarmCount作为系列数据
    const xAxisData = data.map(item => item.category);
    const seriesData = data.map(item => item.count);

    // 使用set方法设置x轴数据
    set(barOptionsData, 'xAxis.data', xAxisData);

    // 使用set方法设置系列数据
    set(barOptionsData, 'series', [
      {
        name: '告警数量',
        data: seriesData,
        type: 'bar'
      }
    ]);
  } catch (error) {
    console.error('获取柱状图统计数据失败:', error);
  } finally {
    loading.value = false; // 数据获取完成后关闭加载
  }
};

/** 初始化加载 */
onMounted(() => {
  getPieStatistics(1);
  getBarStatistics(1);
});
</script>

<style scoped lang="scss">
</style>
