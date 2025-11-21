<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">智能井盖状态分布</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
        />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">智能井盖异常事件趋势</div>
        <chart-type-line
          :idName="leftData.twoId"
          :rightData="leftData.picRightData2"
          :xAxisData="leftData.lineAxisData"
          :seriesData="leftData.lineSeriesData"
        />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">智能井盖异常事件清单</div>
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
        <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
      </div>
      <div class="s2 bg">
        <div class="title">智能井盖在线率趋势</div>
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
        <div class="title">智能井盖设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>

      <div class="s2 bg">
        <div class="title">智能井盖异常处置情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s3 bg">
        <div class="title">智能井盖倾斜角度监测</div>
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
  {x:26.855227,y:117.680114},
  {x:26.825227,y:117.720114},
  {x:26.723227,y:117.920114},
  {x:26.753227,y:117.890114},
  {x:26.7233227,y:117.720114},
  {x:26.823227,y:117.8020114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '正常井盖数量', num: '1200', unit: '' },
    { title: '异常井盖数量', num: '20', unit: '' },
    { title: '在线井盖数量', num: '1180', unit: '' },
    { title: '离线井盖数量', num: '40', unit: '' }
  ],
  picData: [
    { value: 1180, name: '在线正常' },
    { value: 20, name: '在线异常' },
    { value: 20, name: '离线异常' },
    { value: 20, name: '离线正常' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '累计异常事件', num: '150', unit: '' },
    { title: '今日异常事件', num: '5', unit: '' },
    { title: '本周异常事件', num: '20', unit: '' },
    { title: '本月异常事件', num: '60', unit: '' }
  ],
  lineSeriesData: [5, 3, 7, 4, 6, 2, 8],
  CCDvScrollBoardConfig: {
    header: ['井盖位置', '异常类型', '发现时间', '处理状态'],
    data: [
      ['朝阳大街路口', '井盖移位', '07:20', '处理中'],
      ['北环路中段', '井盖松动', '08:15', '处理中'],
      ['和平西路小区门口', '井盖损坏', '08:40', '已解决'],
      ['中心广场角落', '井盖异常倾斜', '09:10', '已解决'],
      ['学府路公交站旁', '井盖积水', '09:30', '处理中'],
      ['友谊东路商场前', '井盖信号丢失', '10:10', '已解决'],
      ['光明巷居民楼前', '井盖震动异常', '10:35', '处理中'],
      ['公园南路停车场内', '井盖温度异常', '11:20', '已解决'],
      ['幸福大街医院附近', '井盖被遮挡', '11:45', '处理中'],
      ['胜利西路学校门口', '井盖电量低', '12:00', '已解决'],
      ['健康路超市旁', '井盖周边有异常声音', '13:15', '处理中'],
      ['文化街书店前', '井盖数据传输异常', '13:40', '已解决'],
      ['前进路工厂附近', '井盖周边有异味', '14:05', '处理中'],
      ['民主北路菜市场旁', '井盖周边有异物', '14:30', '已解决'],
      ['团结路酒店门口', '井盖异常开启', '15:00', '处理中']
    ],
    rowNum: 4, // 表行数
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '智能井盖安全运行天数：',
    runningDays: '365',
    tail: '天'
  },
  centerId: 'centerId',
  lineAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
  lineSeriesData: [
    { data: [90, 92, 95, 96, 98, 99, 97, 96, 98, 99, 98, 99], name: '在线率', type: 'bar', color: ['#02a8f1', '#0c1e65'] },
    { data: [85, 87, 89, 90, 92, 93, 91, 90, 92, 93, 92, 93], name: '正常率', type: 'bar', color: ['#ba97f9', '#4033f9'] }
  ]
});

const rightData = ref({
  customize2Params: [
    { name: '智能井盖设备总数', num: '1500', unit: '' },
    { name: '蓝牙井盖数量', num: '300', unit: '' },
    { name: 'NB - IoT井盖数量', num: '1200', unit: '' },
    { name: '带倾角传感器井盖数量', num: '800', unit: '' },
    { name: '带水位传感器井盖数量', num: '500', unit: '' }
  ],
  rightOne: {
    idName: 'rightOne',
    xAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
    yAxisName: '',
    polyLinesData: [
      {
        lineColor: '#3a72d7',
        name: '今年',
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'],
        type: 'line',
        data: [10, 12, 15, 18, 20, 22, 25, 28, 26, 24, 22, 20]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30]
      }
    ]
  },
  rightTwo: {
    idName: 'rightTwo',
    xAxisData: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
    yAxisName: '度',
    polyLinesData: [
      {
        lineColor: '#3a72d7',
        name: '平均倾斜角度',
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'],
        type: 'line',
        data: [0.5, 0.8, 1.2, 1.0, 1.5, 1.3, 1.1, 0.9, 0.8, 0.6, 0.7, 0.9]
      }
    ]
  }
});
</script>

<style lang="scss" scoped></style>
