<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">渣土车监管设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">渣土车监管质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">渣土车监管事件清单</div>
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
        <div class="title">渣土车监管项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">渣土车行驶里程统计</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">不同区域渣土车活动占比</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">监管设备在线率情况</div>
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
  {x:26.825227,y:117.650114},
  {x:26.753227,y:117.720114},
  {x:26.7233227,y:117.650114},
  {x:26.843227,y:117.8220114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '监管站点数量', num: '10', unit: '个' },
    { title: '执法车辆', num: '5', unit: '辆' },
    { title: '日违规车辆数', num: '8', unit: '辆' },
    { title: '月违规车辆总数', num: '200', unit: '辆' }
  ],
  picData: [
    { value: 25, name: '工地周边渣土车' },
    { value: 35, name: '运输路线渣土车' },
    { value: 20, name: '消纳场周边渣土车' },
    { value: 20, name: '其他区域渣土车' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '在线监管设备数', num: '90', unit: '台' },
    { title: '监管人员数量', num: '15', unit: '人' },
    { title: '日设备离线数', num: '2', unit: '台' },
    { title: '月设备离线总数', num: '60', unit: '台' }
  ],
  lineSeriesData: [2, 1, 2, 1, 2, 3, 2],
  CCDvScrollBoardConfig: {
    header: ['车辆编号', '违规行为', '时间', '状态'],
    data: [
      ['001', '未覆盖篷布', '07:20', '处理中'],
      ['002', '超速行驶', '08:15', '处理中'],
      ['003', '随意倾倒渣土', '08:40', '已解决'],
      ['004', '偏离规定路线', '09:10', '已解决'],
      ['005', '未按规定时间行驶', '09:30', '处理中'],
      ['006', '号牌污损', '10:10', '已解决'],
      ['007', '超载运输', '10:35', '处理中'],
      ['008', '尾气排放超标', '11:20', '已解决'],
      ['009', '私自改装车辆', '11:45', '处理中'],
      ['010', '未安装定位设备', '12:00', '已解决'],
      ['011', '定位设备故障', '13:15', '处理中'],
      ['012', '闯红灯', '13:40', '已解决'],
      ['013', '违规掉头', '14:05', '处理中'],
      ['014', '不按规定车道行驶', '14:30', '已解决'],
      ['015', '拒绝检查', '15:00', '处理中']
    ],
    rowNum: 4,
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '系统连续稳定运行天数：',
    runningDays: '360',
    tail: '天'
  },
  proJectsList: {
    processName: '监管模式',
    processingScaleName: '监管范围',
    theAmountOfWaterReturnedName: '监管车辆数',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区渣土车综合监管项目',
        process: 'GPS 定位 + 视频监控',
        processingScale: '80 平方公里',
        theAmountOfWaterReturned: '500 辆/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/77.jpeg', import.meta.url).href,
            name: 'GPS 定位设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/77.jpg', import.meta.url).href,
            name: '视频监控画面'
          }
        ]
      },
      /*{
        name: '城乡渣土车一体化监管项目',
        process: '电子围栏 + 大数据分析',
        processingScale: '150 平方公里',
        theAmountOfWaterReturned: '1000 辆/日',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '电子围栏设置界面'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '大数据分析报表'
          }
        ]
      },
      {
        name: '重点区域渣土车精准监管项目',
        process: '车载传感器 + 智能预警',
        processingScale: '60 平方公里',
        theAmountOfWaterReturned: '300 辆/日',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '车载传感器设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '智能预警界面'
          }
        ]
      },
      {
        name: '渣土车运输全流程监管项目',
        process: '信息平台 + 执法联动',
        processingScale: '120 平方公里',
        theAmountOfWaterReturned: '800 辆/日',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '信息平台操作界面'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '执法联动场景'
          }
        ]
      },
      {
        name: '渣土车环保监管提升项目',
        process: '尾气监测 + 扬尘防控',
        processingScale: '100 平方公里',
        theAmountOfWaterReturned: '600 辆/日',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '尾气监测设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '扬尘防控措施'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '渣土车监管设备总数', num: '600', unit: '台' },
    { name: 'GPS 定位器', num: '200', unit: '台' },
    { name: '视频监控摄像头', num: '150', unit: '个' },
    { name: '车载传感器', num: '180', unit: '个' },
    { name: '电子围栏基站', num: '70', unit: '个' }
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
        data: [5000, 6000, 8000, 9000, 10000, 8500, 11000, 10500, 12000, 13000, 12500, 12000]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [4500, 5500, 7500, 8500, 9000, 8000, 10500, 10000, 11500, 12000, 11500, 11000]
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '设备在线率', value: 90 },
      { name: '违规预警准确率', value: 85 },
      { name: '数据传输及时率', value: 95 },
      { name: '车辆定位准确率', value: 92 },
      { name: '执法响应及时率', value: 88 }
    ],
    showValue: false
  }
});
</script>

<style lang="scss" scoped>
.CityAppearanceAndSanitation-center-css {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-items: center;
}
</style>
