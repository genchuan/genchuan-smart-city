<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">园林绿化设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">园林绿化质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">园林绿化事件清单</div>
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
        <div class="title">园林绿化项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">园林绿化垃圾清运情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">园林绿化垃圾处理情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">园林绿化车辆运行情况</div>
        <chart-type-line
          :idName="leftData.twoId"
          :rightData="leftData.picRightData2"
          :xAxisData="leftData.lineAxisData"
          :seriesData="leftData.lineSeriesData"
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
import ChartCustomize3 from './ChartCustomize3.vue';

const geometriesArray=[
  {x:26.925227,y:117.680114},
  {x:26.7833227,y:117.720114},
  {x:26.863227,y:117.8520114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '垃圾处理站', num: '10', unit: '座' },
    { title: '园林车辆', num: '30', unit: '辆' },
    { title: '日垃圾处理量', num: '300', unit: '吨' },
    { title: '月垃圾处理量', num: '8000', unit: '吨' }
  ],
  picData: [
    { value: 15, name: '园林垃圾收集' },
    { value: 25, name: '园林垃圾清运' },
    { value: 30, name: '园林修剪' },
    { value: 20, name: '园林灌溉' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '园林站点数量', num: '40', unit: '个' },
    { title: '园林人员数量', num: '150', unit: '人' },
    { title: '日园林垃圾清运量', num: '100', unit: '吨' },
    { title: '月园林垃圾清运总量', num: '3000', unit: '吨' }
  ],
  lineSeriesData: [120, 115, 122, 118, 123, 113, 120],
  CCDvScrollBoardConfig: {
    header: ['区域', '问题', '时间', '状态'],
    data: [
      ['公园', '树木病虫害', '07:20', '处理中'],
      ['植物园', '灌溉系统故障', '08:15', '处理中'],
      ['绿地', '草坪损坏', '08:40', '已解决'],
      ['广场', '绿化设施损坏', '09:10', '已解决'],
      ['街道', '树木倒伏', '09:30', '处理中'],
      ['花园', '杂草丛生', '10:10', '已解决'],
      ['商业街', '绿化维护不及时', '10:35', '处理中'],
      ['小区', '绿化缺失', '11:20', '已解决'],
      ['住宅区', '绿化浇水不足', '11:45', '处理中'],
      ['主干道', '绿化修剪不整齐', '12:00', '已解决'],
      ['公园入口', '绿化施肥不当', '13:15', '处理中'],
      ['苗圃', '苗木死亡', '13:40', '已解决'],
      ['街道拐角', '绿化垃圾堆积', '14:05', '处理中'],
      ['社区公园', '绿化造型不佳', '14:30', '已解决'],
      ['小区花园', '绿化病虫害严重', '15:00', '处理中']
    ],
    rowNum: 4, // 表行数
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '连续养护天数：',
    runningDays: '300',
    tail: '天'
  },
  proJectsList: {
    processName: '施工工艺',
    processingScaleName: '养护规模',
    theAmountOfWaterReturnedName: '园林垃圾清运量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '公园绿化养护项目',
        process: '人工修剪 + 机械辅助',
        processingScale: '300亩/年',
        theAmountOfWaterReturned: '2000吨/日',
        responsible: '李明',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '王丽',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/22.jpg', import.meta.url).href,
            name: '机械辅助修剪'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/11.jpeg', import.meta.url).href,
            name: '人工修剪'
          }
        ]
      },
    /*  {
        name: '城市绿地建设项目',
        process: '规划设计 + 种植施工',
        processingScale: '500亩/年',
        theAmountOfWaterReturned: '3000吨/日',
        responsible: '张强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙艳',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '机械辅助修剪'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '人工修剪'
          }
        ]
      },
      {
        name: '园林景观提升项目',
        process: '景观设计 + 改造施工',
        processingScale: '400亩/年',
        theAmountOfWaterReturned: '2500吨/日',
        responsible: '陈刚',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周慧',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '机械辅助修剪'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '人工修剪'
          }
        ]
      },
      {
        name: '园林花卉种植项目',
        process: '花卉培育 + 种植管理',
        processingScale: '600亩/年',
        theAmountOfWaterReturned: '3500吨/日',
        responsible: '李勇',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄静',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '机械辅助修剪'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '人工修剪'
          }
        ]
      },
      {
        name: '园林树木养护项目',
        process: '树木修剪 + 病虫害防治',
        processingScale: '700亩/年',
        theAmountOfWaterReturned: '4000吨/日',
        responsible: '张峰',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨敏',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '机械辅助修剪'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '人工修剪'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '园林设备总数', num: '700', unit: '台' },
    { name: '园林修剪机', num: '120', unit: '台' },
    { name: '园林洒水车', num: '100', unit: '辆' },
    { name: '园林喷雾器', num: '200', unit: '个' },
    { name: '园林垃圾桶', num: '280', unit: '个' }
  ],
  rightOne: {
    idName: 'rightOneId',
    xAxisData: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
    polyLinesData: [
      {
        lineColor: '#3a72d7',
        name: '今年',
        color: ['rgba(58,114,215,1)', 'rgba(58,114,215,0.1)'],
        type: 'line',
        data: [38, 51, 120, 160, 180, 135, 240, 220, 291, 320, 280, 260]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [165, 186, 235, 268, 220, 232, 282, 260, 220, 200, 180, 140]
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '绿地覆盖率', value: 60 },
      { name: '树木存活率', value: 95 },
      { name: '绿化设备完好率', value: 80 },
      { name: '病虫害防治率', value: 90 },
      { name: '绿化垃圾清运率', value: 85 }
    ],
    showValue: false
  }
});
</script>

<style lang="scss" scoped></style>
