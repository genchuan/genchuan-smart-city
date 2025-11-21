<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">智慧环卫</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
        />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">智慧市政</div>
        <chart-type-line
          :idName="leftData.twoId"
          :rightData="leftData.picRightData2"
          :xAxisData="leftData.lineAxisData"
          :seriesData="leftData.lineSeriesData"
        />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">事件清单</div>
        <dv-scroll-board
          :config="leftData.CCDvScrollBoardConfig"
          style="padding: 15px 25px 25px;  height: calc(100% - 35px - 40px);"
        />
      </div>
    </div>

    <!--中间开始-->
    <div class="content-center">
      <div class="s1">
        <chart-customize1 :paramsData="centerData.customizeParams" />
        <map-common idName="chinaEcharts" :geometriesArray="geometriesArray" />
      </div>
      <div class="s2 bg">
        <div class="title">智慧交通</div>
        <chart-type-line2
          :idName="centerData.centerId"
          :xAxisData="centerData.lineAxisData"
          :seriesData="centerData.lineSeriesData"
          lineWidth="100%"
        />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>

      <div class="s2 bg">
        <div class="title">处置事件</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s3 bg">
        <div class="title">智慧供水</div>
        <chart-type-poly-lines
          :idName="rightData.rightTwo.idName"
          :xAxisData="rightData.rightTwo.xAxisData"
          :polyLinesData="rightData.rightTwo.polyLinesData"
          :yAxisName="rightData.rightTwo.yAxisName"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import MapCommon from './MapCommon.vue';
import ChartTypePie from './ChartTypePie.vue';
import ChartTypeLine from './ChartTypeLine.vue';
import ChartTypeLine2 from './ChartTypeLine2.vue';
import ChartCustomize1 from './ChartCustomize1.vue';
import ChartCustomize2 from './ChartCustomize2.vue';
import ChartTypePolyLines from './ChartTypePolyLines.vue';

const geometriesArray=[
  {x:26.793227,y:117.810114},
  {x:26.893227,y:117.860114},
  {x:26.823227,y:117.850114},
  {x:26.863227,y:117.920114},
];

// 使用 ref 创建响应式数据
const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '中转站', num: '42', unit: '' },
    { title: '填埋场', num: '48', unit: '' },
    { title: '日处理垃圾', num: '32', unit: '' },
    { title: '月处理垃圾', num: '780', unit: '' }
  ],
  picData: [
    { value: 16, name: '街道' },
    { value: 51, name: '社区' },
    { value: 164, name: '责任网格' },
    { value: 320, name: '单元网格' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '灯杆数量', num: '1246', unit: '' },
    { title: '站点数量', num: '248', unit: '' },
    { title: '日电数量', num: '1562', unit: '' },
    { title: '月用点数量', num: '54216', unit: '' }
  ],
  lineSeriesData: [1562, 1480, 1580, 1520, 1540, 1490, 1540],
  CCDvScrollBoardConfig: {
    header: ['地区', '问题', '时间', '状态'],
    data: [
      ['朝阳大街', '路灯损坏', '07:20', '处理中'],
      ['北环路', '垃圾堆积', '08:15', '处理中'],
      ['和平西路', '道路坑洼', '08:40', '已解决'],
      ['中心广场', '喷泉故障', '09:10', '已解决'],
      ['学府路', '下水管道堵塞', '09:30', '处理中'],
      ['友谊东路', '树木倾斜', '10:10', '已解决'],
      ['光明巷', '井盖缺失', '10:35', '处理中'],
      ['公园南路', '交通标识不清', '11:20', '已解决'],
      ['幸福大街', '电线杆倾斜', '11:45', '处理中'],
      ['胜利西路', '公交站台损坏', '12:00', '已解决'],
      ['健康路', '路面结冰', '13:15', '处理中'],
      ['文化街', '广告牌掉落', '13:40', '已解决'],
      ['前进路', '电缆外露', '14:05', '处理中'],
      ['民主北路', '窨井溢水', '14:30', '已解决'],
      ['团结路', '消防栓漏水', '15:00', '处理中']
    ],
    rowNum: 4, // 表行数
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '安全运行天数：',
    runningDays: '1000',
    tail: '天'
  },
  centerId: 'centerId',
  lineAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
  lineSeriesData: [
    { data: [535, 852, 920, 1010, 1100, 1205, 1350, 1405, 1685, 2010, 2105, 2250], name: '交通设备', type: 'bar', color: ['#02a8f1', '#0c1e65'] },
    { data: [505, 802, 900, 1000, 1000, 1105, 1250, 1305, 1485, 2000, 2005, 2200], name: '运行设备', type: 'bar', color: ['#ba97f9', '#4033f9'] }
  ]
});

const rightData = ref({
  customize2Params: [
    { name: '设备总数', num: '25626', unit: '' },
    { name: '视频摄像头', num: '14260', unit: '' },
    { name: '园林绿化类', num: '1423', unit: '' },
    { name: '市政施工类', num: '6562', unit: '' },
    { name: '环卫监管类', num: '3381', unit: '' }
  ],
  rightOne: {
    idName: 'rightOne',
    xAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
    yAxisName: '',
    polyLinesData: [
      {
        lineColor: '#3a72d7', // 线的颜色
        name: '今年', // 提示的名称
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'], // 渐变色0 - 1
        type: 'line',
        data: [1650, 1860, 2350, 2680, 2200, 2320, 2820, 2600, 2200, 2000, 1800, 1400] // 纵坐标值
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [388, 512, 1200, 1600, 1800, 1350, 2400, 2200, 2910, 3200, 2800, 2600] // 纵坐标值
      }
    ]
  },
  rightTwo: {
    idName: 'rightTwo',
    xAxisData: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
    yAxisName: 'm³',
    polyLinesData: [
      {
        lineColor: '#3a72d7', // 线的颜色
        name: '智慧供水', // 提示的名称
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'], // 渐变色0 - 1
        type: 'line',
        data: [12, 16, 20, 19, 22, 24, 22, 20, 18, 12, 20, 18, 16, 23] // 纵坐标值
      }
    ]
  }
});
</script>

<style lang="scss" scoped></style>
