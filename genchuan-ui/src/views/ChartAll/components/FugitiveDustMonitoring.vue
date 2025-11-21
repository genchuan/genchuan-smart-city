<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">扬尘监测设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">扬尘监测质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">扬尘监测事件清单</div>
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
        <div class="title">扬尘监测项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">扬尘浓度变化情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">不同区域扬尘占比情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">监测设备运行状态情况</div>
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
  {x:26.825227,y:117.620114},
  {x:26.753227,y:117.760114},
  {x:26.7233227,y:117.600114},
  {x:26.7233227,y:117.650114},
  {x:26.843227,y:117.9220114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '监测站点数量', num: '15', unit: '个' },
    { title: '维护车辆', num: '3', unit: '辆' },
    { title: '日超标次数', num: '5', unit: '次' },
    { title: '月超标总次数', num: '120', unit: '次' }
  ],
  picData: [
    { value: 20, name: '工业区扬尘' },
    { value: 30, name: '建筑工地扬尘' },
    { value: 42, name: '道路扬尘' },
    { value: 32, name: '其他区域扬尘' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '正常运行设备数', num: '80', unit: '台' },
    { title: '维护人员数量', num: '10', unit: '人' },
    { title: '日设备故障数', num: '2', unit: '台' },
    { title: '月设备故障总数', num: '50', unit: '台' }
  ],
  lineSeriesData: [2, 1, 2, 1, 2, 3, 2],
  CCDvScrollBoardConfig: {
    header: ['区域', '问题', '时间', '状态'],
    data: [
      ['A区工地', '监测设备故障', '07:20', '处理中'],
      ['B区工厂', '扬尘超标预警', '08:15', '处理中'],
      ['C区道路', '监测数据异常', '08:40', '已解决'],
      ['D区广场', '设备通信中断', '09:10', '已解决'],
      ['E区居民区', '监测设备损坏', '09:30', '处理中'],
      ['F区公园', '传感器精度下降', '10:10', '已解决'],
      ['G区商业区', '数据传输延迟', '10:35', '处理中'],
      ['H区学校', '设备电源故障', '11:20', '已解决'],
      ['I区医院', '监测数据不准确', '11:45', '处理中'],
      ['J区车站', '设备安装松动', '12:00', '已解决'],
      ['K区仓库', '设备老化严重', '13:15', '处理中'],
      ['L区码头', '设备软件故障', '13:40', '已解决'],
      ['M区景区', '扬尘数据异常波动', '14:05', '处理中'],
      ['N区农田', '设备被遮挡', '14:30', '已解决'],
      ['O区矿区', '设备防雷设施损坏', '15:00', '处理中']
    ],
    rowNum: 4,
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '系统连续正常运行天数：',
    runningDays: '360',
    tail: '天'
  },
  proJectsList: {
    processName: '监测技术',
    processingScaleName: '监测范围',
    theAmountOfWaterReturnedName: '数据采集量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区扬尘综合监测项目',
        process: '激光散射法 + 无线传输模块',
        processingScale: '50 平方公里',
        theAmountOfWaterReturned: '3000 条/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/66.png', import.meta.url).href,
            name: '激光散射监测设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/66.jpg', import.meta.url).href,
            name: '无线传输模块'
          }
        ]
      },
     /* {
        name: '城乡扬尘一体化监测项目',
        process: 'β射线法 + 无线传输',
        processingScale: '120 平方公里',
        theAmountOfWaterReturned: '8000 条/日',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: 'β射线监测设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '无线传输模块'
          }
        ]
      },
      {
        name: '重点区域扬尘精准监测项目',
        process: '光学粒子计数器 + 智能分析',
        processingScale: '80 平方公里',
        theAmountOfWaterReturned: '4000 条/日',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '光学粒子计数器'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '智能分析软件界面'
          }
        ]
      },
      {
        name: '城市扬尘动态监测与预警项目',
        process: '多传感器融合 + 实时预警',
        processingScale: '150 平方公里',
        theAmountOfWaterReturned: '6000 条/日',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '多传感器融合设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '实时预警系统界面'
          }
        ]
      },
      {
        name: '扬尘监测与治理效果评估项目',
        process: '卫星遥感 + 地面监测',
        processingScale: '200 平方公里',
        theAmountOfWaterReturned: '10000 条/日',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '卫星遥感数据展示'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '地面监测站点'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '扬尘监测设备总数', num: '852', unit: '台' },
    { name: '激光散射仪', num: '135', unit: '台' },
    { name: 'β射线仪', num: '123', unit: '台' },
    { name: '光学粒子计数器', num: '256', unit: '台' },
    { name: '多参数传感器', num: '338', unit: '个' }
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
      { name: '设备数据准确率', value: 55 },
      { name: '数据传输及时率', value: 120 },
      { name: '超标预警准确率', value: 78 },
      { name: '设备运行稳定性', value: 66 },
      { name: '监测数据完整性', value: 80 }
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
