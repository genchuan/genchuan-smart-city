<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">违建巡查设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">违建巡查质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">违建事件清单</div>
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
        <div class="title">违建巡查项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">违建拆除进度情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">违建类型分布情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">违建巡查车辆运行情况</div>
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
  {x:26.825227,y:117.680114},
  {x:26.803227,y:117.750114},
  {x:26.7533227,y:117.680114},
  {x:26.7833227,y:117.680114},
  {x:26.813227,y:117.8020114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '违建拆除点', num: '15', unit: '个' },
    { title: '巡查车辆', num: '20', unit: '辆' },
    { title: '日违建拆除面积', num: '500', unit: '平方米' },
    { title: '月违建拆除总面积', num: '13000', unit: '平方米' }
  ],
  picData: [
    { value: 20, name: '违规搭建' },
    { value: 30, name: '超面积建设' },
    { value: 42, name: '未批先建' },
    { value: 32, name: '临时建筑超期' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '巡查站点数量', num: '50', unit: '个' },
    { title: '巡查人员数量', num: '200', unit: '人' },
    { title: '日巡查面积', num: '15000', unit: '平方米' },
    { title: '月巡查总面积', num: '450000', unit: '平方米' }
  ],
  lineSeriesData: [150, 145, 152, 148, 153, 143, 150],
  CCDvScrollBoardConfig: {
    header: ['区域', '违建类型', '发现时间', '处理状态'],
    data: [
      ['东街', '违规搭建', '07:20', '处理中'],
      ['南街', '超面积建设', '08:15', '处理中'],
      ['西街', '未批先建', '08:40', '已解决'],
      ['市中心广场', '临时建筑超期', '09:10', '已解决'],
      ['城北街道', '违规搭建', '09:30', '处理中'],
      ['城西公园', '超面积建设', '10:10', '已解决'],
      ['城中商业区', '未批先建', '10:35', '处理中'],
      ['南部街道', '临时建筑超期', '11:20', '已解决'],
      ['北部居民区', '违规搭建', '11:45', '处理中'],
      ['中部道路', '超面积建设', '12:00', '已解决'],
      ['东部广场', '未批先建', '13:15', '处理中'],
      ['西部拆除点', '设备故障', '13:40', '已解决'],
      ['南郊街道', '违建拆除不及时', '14:05', '处理中'],
      ['北郊公园', '巡查不到位', '14:30', '已解决'],
      ['城中老街', '巡查人员不足', '15:00', '处理中']
    ],
    rowNum: 4,
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '连续巡查天数：',
    runningDays: '360',
    tail: '天'
  },
  proJectsList: {
    processName: '巡查技术',
    processingScaleName: '巡查规模',
    theAmountOfWaterReturnedName: '违建拆除量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区违建巡查项目',
        process: '人工巡查 + 无人机辅助',
        processingScale: '500平方公里/年',
        theAmountOfWaterReturned: '3000平方米/月',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/99.jpeg', import.meta.url),
            name: '无人机辅助巡查'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/99.jpg', import.meta.url),
            name: '人工巡查'
          }
        ]
      },
      /*{
        name: '城乡违建一体化巡查项目',
        process: '手动巡检 + 智能监控',
        processingScale: '1200平方公里/年',
        theAmountOfWaterReturned: '8000平方米/月',
        responsible: '刘强',
        contactNumber: '138-xxxx-3658',
        safetyOfficer: '孙敏',
        contactNumber2: '139-xxxx-4857',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '无人机辅助巡查'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '人工巡查'
          }
        ]
      },
      {
        name: '重点区域违建保障巡查项目',
        process: '机械化作业 + 人工巡检',
        processingScale: '800平方公里/年',
        theAmountOfWaterReturned: '4000平方米/月',
        responsible: '陈佳',
        contactNumber: '138-xxxx-4857',
        safetyOfficer: '周婷',
        contactNumber2: '139-xxxx-4524',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '无人机辅助巡查'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '人工巡查'
          }
        ]
      },
      {
        name: '违建拆除与后续管理项目',
        process: '分类拆除 + 智能监控',
        processingScale: '1500平方公里/年',
        theAmountOfWaterReturned: '6000平方米/月',
        responsible: '李华',
        contactNumber: '138-xxxx-6875',
        safetyOfficer: '黄莉',
        contactNumber2: '139-xxxx-5867',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '无人机辅助巡查'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '人工巡查'
          }
        ]
      },
      {
        name: '违建区域清洁与环境恢复项目',
        process: '机械清理 + 生态修复系统',
        processingScale: '2000平方公里/年',
        theAmountOfWaterReturned: '10000平方米/月',
        responsible: '张涛',
        contactNumber: '138-xxxx-4587',
        safetyOfficer: '杨娜',
        contactNumber2: '139-xxxx-4587',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
            name: '无人机辅助巡查'
          },
          {
            imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
            name: '人工巡查'
          }
        ]
      }*/
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '巡查设备总数', num: '852', unit: '台' },
    { name: '无人机', num: '135', unit: '架' },
    { name: '巡查车辆', num: '123', unit: '辆' },
    { name: '监控设备', num: '256', unit: '套' },
    { name: '巡查终端', num: '338', unit: '个' }
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
    ],
    yAxisName: '违建拆除面积（平方米）'
  },
  rightTwo: {
    data: [
      { name: '巡查覆盖率', value: 55 },
      { name: '违建发现及时率', value: 120 },
      { name: '违建处理完成率', value: 78 },
      { name: '数据准确率', value: 66 },
      { name: '群众满意度', value: 80 }
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
