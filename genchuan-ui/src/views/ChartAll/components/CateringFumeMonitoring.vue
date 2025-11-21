<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">餐饮油烟监测设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">餐饮油烟监测质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">餐饮油烟监测事件清单</div>
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
        <div class="title">餐饮油烟监测项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">餐饮油烟浓度变化情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">不同区域餐饮油烟排放占比</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">监测设备运行状况</div>
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
  {x:26.855227,y:117.650114},
  {x:26.783227,y:117.720114},
  {x:26.7333227,y:117.650114},
  {x:26.823227,y:117.8220114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '监测站点数量', num: '20', unit: '个' },
    { title: '维护车辆', num: '2', unit: '辆' },
    { title: '日超标商户数', num: '3', unit: '家' },
    { title: '月超标商户总数', num: '60', unit: '家' }
  ],
  picData: [
    { value: 22, name: '商业区餐饮油烟' },
    { value: 30, name: '居民区餐饮油烟' },
    { value: 28, name: '美食街餐饮油烟' },
    { value: 20, name: '其他区域餐饮油烟' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '正常运行设备数', num: '180', unit: '台' },
    { title: '维护人员数量', num: '8', unit: '人' },
    { title: '日设备故障数', num: '1', unit: '台' },
    { title: '月设备故障总数', num: '30', unit: '台' }
  ],
  lineSeriesData: [1, 0, 1, 0, 1, 2, 1],
  CCDvScrollBoardConfig: {
    header: ['商户名称', '问题', '时间', '状态'],
    data: [
      ['美味餐厅', '油烟净化设备故障', '07:20', '处理中'],
      ['香满楼饭店', '油烟排放超标', '08:15', '处理中'],
      ['小吃一条街某店', '监测数据异常', '08:40', '已解决'],
      ['家常菜馆', '设备通信中断', '09:10', '已解决'],
      ['火锅店', '油烟监测设备损坏', '09:30', '处理中'],
      ['西餐厅', '传感器精度下降', '10:10', '已解决'],
      ['烧烤摊', '数据传输延迟', '10:35', '处理中'],
      ['快餐店', '设备电源故障', '11:20', '已解决'],
      ['海鲜酒楼', '监测数据不准确', '11:45', '处理中'],
      ['面馆', '设备安装松动', '12:00', '已解决'],
      ['甜品店', '设备老化严重', '13:15', '处理中'],
      ['烤肉店', '设备软件故障', '13:40', '已解决'],
      ['包子铺', '油烟数据异常波动', '14:05', '处理中'],
      ['咖啡店', '设备被遮挡', '14:30', '已解决'],
      ['饺子馆', '设备防雷设施损坏', '15:00', '处理中']
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
        name: '市区餐饮油烟综合监测项目',
        process: '激光散射法 + 无线传输',
        processingScale: '60 平方公里',
        theAmountOfWaterReturned: '4000 条/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl:  new URL('@/assets/chart/images/imgs/88.jpeg', import.meta.url).href,
            name: '激光散射监测设备'
          },
          {
            imgUrl:  new URL('@/assets/chart/images/imgs/88.png', import.meta.url).href,
            name: '无线传输'
          }
        ]
      },
      /*{
        name: '城乡餐饮油烟一体化监测项目',
        process: '电化学传感器法 + 无线传输',
        processingScale: '100 平方公里',
        theAmountOfWaterReturned: '7000 条/日',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
            name: '电化学传感器监测设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
            name: '无线传输模块'
          }
        ]
      },
      {
        name: '重点区域餐饮油烟精准监测项目',
        process: '光散射法 + 智能分析',
        processingScale: '40 平方公里',
        theAmountOfWaterReturned: '2500 条/日',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '光散射监测设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '智能分析软件界面'
          }
        ]
      },
      {
        name: '城市餐饮油烟动态监测与预警项目',
        process: '多传感器融合 + 实时预警',
        processingScale: '80 平方公里',
        theAmountOfWaterReturned: '5000 条/日',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '多传感器融合设备'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '实时预警系统界面'
          }
        ]
      },
      {
        name: '餐饮油烟监测与治理效果评估项目',
        process: '卫星遥感（辅助） + 地面监测',
        processingScale: '120 平方公里',
        theAmountOfWaterReturned: '9000 条/日',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '卫星遥感数据展示（辅助）'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '地面监测站点'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '餐饮油烟监测设备总数', num: '500', unit: '台' },
    { name: '激光散射仪', num: '100', unit: '台' },
    { name: '电化学传感器', num: '120', unit: '个' },
    { name: '光散射仪', num: '150', unit: '台' },
    { name: '多参数传感器', num: '130', unit: '个' }
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
        data: [20, 25, 30, 35, 40, 32, 45, 42, 50, 55, 52, 50]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [18, 22, 28, 32, 36, 30, 42, 40, 48, 52, 50, 48]
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '设备数据准确率', value: 92 },
      { name: '数据传输及时率', value: 95 },
      { name: '超标预警准确率', value: 90 },
      { name: '设备运行稳定性', value: 93 },
      { name: '监测数据完整性', value: 91 }
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
