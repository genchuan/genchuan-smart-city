<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">排水防涝设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">排水防涝质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <!--<div class="title">排水防涝事件清单</div>-->
        <div class="title">积水点监测/防涝事件</div>
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
        <div class="title">排水防涝项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">排水防涝积水排除情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">排水防涝设施维护情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">排水防涝车辆运行情况</div>
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
  {x:26.805227,y:117.650114},
  {x:26.733227,y:117.940114},
  {x:26.7533227,y:117.680114},
  {x:26.763227,y:117.7220114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '排水泵站', num: '15', unit: '座' },
    { title: '排水车辆', num: '20', unit: '辆' },
    { title: '日排水量', num: '5000', unit: '立方米' },
    { title: '月排水量', num: '130000', unit: '立方米' }
  ],
  picData: [
    { value: 20, name: '管道疏通' },
    { value: 30, name: '积水排除' },
    { value: 42, name: '泵站维护' },
    { value: 32, name: '设备检修' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '排水站点数量', num: '50', unit: '个' },
    { title: '排水人员数量', num: '200', unit: '人' },
    { title: '日积水排除量', num: '1500', unit: '立方米' },
    { title: '月积水排除总量', num: '45000', unit: '立方米' }
  ],
  lineSeriesData: [1500, 1450, 1520, 1480, 1530, 1430, 1500],
  CCDvScrollBoardConfig: {
    header: ['区域', '问题', '时间', '状态'],
    data: [
      ['东街', '排水管道堵塞', '07:20', '处理中'],
      ['南街', '排水车辆故障', '08:15', '处理中'],
      ['西街', '泵站设备故障', '08:40', '已解决'],
      ['市中心广场', '道路积水严重', '09:10', '已解决'],
      ['城北街道', '排水设施损坏', '09:30', '处理中'],
      ['城西公园', '雨水箅子堵塞', '10:10', '已解决'],
      ['城中商业区', '排水不畅', '10:35', '处理中'],
      ['南部街道', '井盖丢失', '11:20', '已解决'],
      ['北部居民区', '污水外溢', '11:45', '处理中'],
      ['中部道路', '排水车辆违规停放', '12:00', '已解决'],
      ['东部广场', '排水设施未及时维护', '13:15', '处理中'],
      ['西部排水泵站', '设备故障', '13:40', '已解决'],
      ['南郊街道', '积水排除不及时', '14:05', '处理中'],
      ['北郊公园', '排水清扫不到位', '14:30', '已解决'],
      ['城中老街', '排水人员不足', '15:00', '处理中']
    ],
    rowNum: 4,
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '连续正常运行天数：',
    runningDays: '360',
    tail: '天'
  },
  proJectsList: {
    processName: '施工技术',
    processingScaleName: '处理规模',
    theAmountOfWaterReturnedName: '排水量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区排水管网升级项目',
        process: '管道更换 + 智能监测',
        processingScale: '500公里/年',
        theAmountOfWaterReturned: '3000立方米/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/33.jpg', import.meta.url).href,
            name: '管道更换施工'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/44.jpeg', import.meta.url).href,
            name: '智能监测设备安装'
          }
        ]
      },
      /*{
        name: '城乡排水一体化项目',
        process: '泵站建设 + 雨水收集',
        processingScale: '1200公里/年',
        theAmountOfWaterReturned: '8000立方米/日',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('../assets/images/2.png', import.meta.url).href,
            name: '泵站建设施工'
          },
          {
            imgUrl: new URL('../assets/images/1.png', import.meta.url).href,
            name: '雨水收集设施安装'
          }
        ]
      },
      {
        name: '重点区域排水保障项目',
        process: '积水点改造 + 应急排水',
        processingScale: '800公里/年',
        theAmountOfWaterReturned: '4000立方米/日',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('../assets/images/2.png', import.meta.url).href,
            name: '积水点改造施工'
          },
          {
            imgUrl: new URL('../assets/images/1.png', import.meta.url).href,
            name: '应急排水设备作业'
          }
        ]
      },
      {
        name: '城市排水防涝提升项目',
        process: '排水系统优化 + 海绵城市建设',
        processingScale: '1500公里/年',
        theAmountOfWaterReturned: '6000立方米/日',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('../assets/images/2.png', import.meta.url).href,
            name: '排水系统优化施工'
          },
          {
            imgUrl: new URL('../assets/images/1.png', import.meta.url).href,
            name: '海绵城市建设场景'
          }
        ]
      },
      {
        name: '排水清洁与雨水回收利用项目',
        process: '管道清洗 + 雨水回收系统',
        processingScale: '2000公里/年',
        theAmountOfWaterReturned: '10000立方米/日',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('../assets/images/2.png', import.meta.url).href,
            name: '管道清洗作业'
          },
          {
            imgUrl: new URL('../assets/images/1.png', import.meta.url).href,
            name: '雨水回收系统安装'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '排水设备总数', num: '852', unit: '台' },
    { name: '排水泵', num: '135', unit: '台' },
    { name: '排水车', num: '123', unit: '辆' },
    { name: '吸污车', num: '256', unit: '辆' },
    { name: '雨水箅子', num: '338', unit: '个' }
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
        data: [3800, 5100, 12000, 16000, 18000, 13500, 24000, 22000, 29100, 32000, 28000, 26000]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [16500, 18600, 23500, 26800, 22000, 23200, 28200, 26000, 22000, 20000, 18000, 14000]
      }
    ],
    yAxisName: '排水量（立方米）'
  },
  rightTwo: {
    data: [
      { name: '排水管道畅通率', value: 55 },
      { name: '泵站运行效率', value: 120 },
      { name: '雨水箅子完好率', value: 78 },
      { name: '排水设施完好率', value: 66 },
      { name: '积水排除及时率', value: 80 }
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
