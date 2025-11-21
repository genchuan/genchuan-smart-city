<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">停车管理设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">停车管理质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">停车管理事件清单</div>
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
        <div class="title">停车管理项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">停车场车辆进出情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">停车场车位使用情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">停车管理车辆巡逻情况</div>
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
  {x:26.725227,y:117.750114},
  {x:26.753227,y:117.920114},
  {x:26.743227,y:117.860114},
  {x:26.765227,y:117.850114},
  {x:26.7233227,y:117.720114},
  {x:26.763227,y:117.8520114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '停车场数量', num: '15', unit: '个' },
    { title: '巡逻车辆', num: '5', unit: '辆' },
    { title: '日车辆进出量', num: '500', unit: '辆' },
    { title: '月车辆进出总量', num: '13000', unit: '辆' }
  ],
  picData: [
    { value: 20, name: '空闲车位' },
    { value: 30, name: '已占用车位' },
    { value: 42, name: '临时车位' },
    { value: 32, name: '专用车位' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '巡逻站点数量', num: '10', unit: '个' },
    { title: '巡逻人员数量', num: '20', unit: '人' },
    { title: '日巡逻次数', num: '15', unit: '次' },
    { title: '月巡逻总次数', num: '450', unit: '次' }
  ],
  lineSeriesData: [15, 14, 15, 14, 15, 13, 15],
  CCDvScrollBoardConfig: {
    header: ['区域', '问题', '时间', '状态'],
    data: [
      ['A区停车场', '道闸故障', '07:20', '处理中'],
      ['B区停车场', '巡逻车辆故障', '08:15', '处理中'],
      ['C区停车场', '车位引导系统故障', '08:40', '已解决'],
      ['D区停车场', '车辆违规停放', '09:10', '已解决'],
      ['E区停车场', '停车设备损坏', '09:30', '处理中'],
      ['F区停车场', '车位指示灯损坏', '10:10', '已解决'],
      ['G区停车场', '缴费系统故障', '10:35', '处理中'],
      ['H区停车场', '标识牌丢失', '11:20', '已解决'],
      ['I区停车场', '监控设备故障', '11:45', '处理中'],
      ['J区停车场', '巡逻车辆违规停放', '12:00', '已解决'],
      ['K区停车场', '停车设施未及时维护', '13:15', '处理中'],
      ['L区停车场', '收费亭设备故障', '13:40', '已解决'],
      ['M区停车场', '车辆进出记录异常', '14:05', '处理中'],
      ['N区停车场', '车位统计错误', '14:30', '已解决'],
      ['O区停车场', '巡逻人员不足', '15:00', '处理中']
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
    processName: '管理模式',
    processingScaleName: '管理规模',
    theAmountOfWaterReturnedName: '车辆处理量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区大型停车场智能化改造项目',
        process: '车牌识别 + 智能缴费',
        processingScale: '500个车位/年',
        theAmountOfWaterReturned: '3000辆/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/44.jpg', import.meta.url).href,
            name: '车牌识别设备安装'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/55.jpeg', import.meta.url).href,
            name: '智能缴费终端设置'
          }
        ]
      },
      /*{
        name: '城乡停车场一体化管理项目',
        process: '远程监控 + 车位预约',
        processingScale: '1200个车位/年',
        theAmountOfWaterReturned: '8000辆/日',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '远程监控设备安装'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '车位预约系统搭建'
          }
        ]
      },
      {
        name: '重点区域停车场优化项目',
        process: '车位引导 + 应急处理',
        processingScale: '800个车位/年',
        theAmountOfWaterReturned: '4000辆/日',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '车位引导系统调试'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '应急处理预案制定'
          }
        ]
      },
      {
        name: '城市停车场综合管理提升项目',
        process: '数据分析 + 服务优化',
        processingScale: '1500个车位/年',
        theAmountOfWaterReturned: '6000辆/日',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '数据分析软件部署'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '服务优化方案实施'
          }
        ]
      },
      {
        name: '停车场安全与环境改善项目',
        process: '安全设施升级 + 环境整治',
        processingScale: '2000个车位/年',
        theAmountOfWaterReturned: '10000辆/日',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '安全设施安装'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '环境整治施工'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '停车管理设备总数', num: '852', unit: '台' },
    { name: '道闸', num: '135', unit: '套' },
    { name: '车位检测器', num: '123', unit: '个' },
    { name: '监控摄像头', num: '256', unit: '个' },
    { name: '收费终端', num: '338', unit: '台' }
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
        data: [380, 510, 1200, 1600, 1800, 1350, 2400, 2200, 2910, 3200, 2800, 2600]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [1650, 1860, 2350, 2680, 2200, 2320, 2820, 2600, 2200, 2000, 1800, 1400]
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '道闸正常运行率', value: 55 },
      { name: '车位检测准确率', value: 120 },
      { name: '监控设备完好率', value: 78 },
      { name: '收费系统准确率', value: 66 },
      { name: '车辆引导及时率', value: 80 }
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
