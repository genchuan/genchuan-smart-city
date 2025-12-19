<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">智慧污水</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">智慧蓄水</div>
        <chart-type-line
          :idName="leftData.twoId"
          :rightData="leftData.picRightData2"
          :xAxisData="leftData.lineAxisData"
          :seriesData="leftData.lineSeriesData"
        />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">预警管理</div>
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
        <div class="title">智慧水务</div>
        <div class="WaterConservancyAndWaterAffairs-center-css">
          <dv-active-ring-chart :config="centerData.option2" style="width:50%;height:100%;" />
          <dv-water-level-pond :config="centerData.option4" style="width:200px;height:calc(100% - 35px); margin-right:90px" />
        </div>
        <!--<chart-type-line2
          :idName="centerData.centerId"
          :xAxisData="centerData.lineAxisData"
          :seriesData="centerData.lineSeriesData"
          lineWidth="100%"
        />-->
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <div class="s2 bg">
        <div class="title">处理水量情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>
      <div class="s3 bg">
        <div class="title">水质指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
        <!--        <chart-type-poly-lines
                  :idName="rightData.rightTwo.idName"
                  :xAxisData="rightData.rightTwo.xAxisData"
                  :polyLinesData="rightData.rightTwo.polyLinesData"
                  :yAxisName="rightData.rightTwo.yAxisName"
                />-->
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
  {x:26.653227,y:117.810114},
  {x:26.693227,y:117.860114},
  {x:26.823227,y:117.820114},
  {x:26.863227,y:117.920114},
  {x:26.863227,y:117.820114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '污水处理厂', num: '15', unit: '座' },
    { title: '雨水泵站', num: '20', unit: '座' },
    { title: '日处理污水量', num: '50000', unit: '' },
    { title: '月处理污水量', num: '1300000', unit: '' }
  ],
  picData: [
    { value: 20, name: '河流监测' },
    { value: 30, name: '水库监测' },
    { value: 42, name: '水位监测' },
    { value: 32, name: '水质监测' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '水库数量', num: '50', unit: '座' },
    { title: '水位监测点数量', num: '200', unit: '个' },
    { title: '日入库水量', num: '15000', unit: '' },
    { title: '月总蓄水量', num: '4500000', unit: '' }
  ],
  lineSeriesData: [15000, 14500, 15200, 14800, 15300, 14300, 15000],
  CCDvScrollBoardConfig: {
    header: ['地区', '问题', '时间', '状态'],
    data: [
      ['东郊河河段', '水位监测设备故障', '07:20', '处理中'],
      ['南郊水库', '大坝出现渗水', '08:15', '处理中'],
      ['西郊灌溉渠', '渠道漏水', '08:40', '已解决'],
      ['市中心湖泊', '水质富营养化', '09:10', '已解决'],
      ['城北排水管道', '排水管道堵塞', '09:30', '处理中'],
      ['城西水闸', '水闸开合异常', '10:10', '已解决'],
      ['城中泵站', '泵站设备故障', '10:35', '处理中'],
      ['南部河道', '河道垃圾堆积', '11:20', '已解决'],
      ['北部水源地', '水源地水质污染', '11:45', '处理中'],
      ['中部供水管道', '供水管道破裂', '12:00', '已解决'],
      ['东部防洪堤', '防洪堤出现裂缝', '13:15', '处理中'],
      ['西部污水处理厂', '污水处理设备故障', '13:40', '已解决'],
      ['南郊排涝沟', '排涝沟排水不畅', '14:05', '处理中'],
      ['北郊水塔', '水塔水位异常', '14:30', '已解决'],
      ['城中水利枢纽', '水利枢纽运行异常', '15:00', '处理中']
    ],
    rowNum: 4, //表行数
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '安全运行天数：',
    runningDays: '1000',
    tail: '天'
  },
  option2: {
    data: [
      { name: '泵站耗电', value: 120 },
      { name: '水源空气', value: 78 },
      { name: '出厂水质', value: 66 },
      { name: '管周土湿', value: 80 }
    ],
    radius: '75%',
    activeTimeGap: 1500
  },
  option4: {
    data: [66, 45],
    shape: 'roundRect'
  }
  // centerId: 'centerId',
  // lineAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
  // lineSeriesData: [
  //   { data: [535, 852, 920, 1010, 1100, 1205, 1350, 1405, 1685, 2010, 2105, 2250], name: '交通设备', type: 'bar', color: ['#02a8f1', '#0c1e65'] },
  //   { data: [505, 802, 900, 1000, 1000, 1105, 1250, 1305, 1485, 2000, 2005, 2200], name: '运行设备', type: 'bar', color: ['#ba97f9', '#4033f9'] }
  // ]
});

const rightData = ref({
  customize2Params: [
    { name: '设备总数', num: '8525', unit: '' },
    { name: '水位监测设备', num: '1352', unit: '' },
    { name: '水质监测设备', num: '1230', unit: '' },
    { name: '供水泵站设备', num: '2562', unit: '' },
    { name: '供水管网设备', num: '3381', unit: '' }
  ],
  rightOne: {
    idName: 'rightOne',
    xAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
    yAxisName: '',
    polyLinesData: [
      {
        lineColor: '#3a72d7', //线的颜色
        name: '今年', //提示的名称
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'], //渐变色0-1
        type: 'line',
        data: [388, 512, 1200, 1600, 1800, 1350, 2400, 2200, 2910, 3200, 2800, 2600] //纵坐标值
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [1650, 1860, 2350, 2680, 2200, 2320, 2820, 2600, 2200, 2000, 1800, 1400] //纵坐标值
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '酸碱度（pH 值）', value: 55 },
      { name: '溶解氧（DO）', value: 120 },
      { name: '化学需氧量（COD）', value: 78 },
      { name: '生化需氧量（BOD）', value: 66 },
      { name: '氨氮', value: 80 }
    ],
    showValue: false
  }
});
</script>

<style lang="scss" scoped>
.WaterConservancyAndWaterAffairs-center-css {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
  width: 100%;
  height: calc(100% - 35px);
}
</style>
