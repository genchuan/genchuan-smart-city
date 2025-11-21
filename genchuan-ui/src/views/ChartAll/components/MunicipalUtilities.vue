<template>
  <div class="content-main">
    <!--左边开始-->
    <div class="content-left">
      <!--第一块-->
      <div class="one bg">
        <div class="title">市政公用设备总数</div>
        <chart-customize2 :paramsData="rightData.customize2Params" />
      </div>
      <!--第二块-->
      <div class="two bg">
        <div class="title">市政公用质量指标</div>
        <dv-capsule-chart :config="rightData.rightTwo" style="width:100%;height:calc(100% - 35px)" />
      </div>
      <!--第三块-->
      <div class="three bg">
        <div class="title">市政公用事件清单</div>
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
        <div class="title">市政公用项目介绍</div>
        <chart-customize3 :proJectsList="centerData.proJectsList" />
      </div>
    </div>

    <!--右边开始-->
    <div class="content-right">
      <div class="s1 bg">
        <div class="title">市政公用垃圾清运情况</div>
        <chart-type-poly-lines
          :idName="rightData.rightOne.idName"
          :xAxisData="rightData.rightOne.xAxisData"
          :polyLinesData="rightData.rightOne.polyLinesData"
          :yAxisName="rightData.rightOne.yAxisName"
        />
      </div>

      <div class="s2 bg">
        <div class="title">市政公用垃圾处理情况</div>
        <chart-type-pie
          :idName="leftData.oneId"
          :rightData="leftData.picRightData"
          :resData="leftData.picData"
          :roseType="true"
        />
      </div>

      <div class="s3 bg">
        <div class="title">市政公用车辆运行情况</div>
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
  {x:26.623227,y:117.780114},
  {x:26.693227,y:117.760114},
  {x:26.823227,y:117.620114},
  {x:26.853227,y:117.650114},
  {x:26.803227,y:117.680114},
  {x:26.863227,y:117.6520114},
];

const leftData = ref({
  oneId: 'oneId',
  twoId: 'twoId',
  picRightData: [
    { title: '垃圾处理厂', num: '15', unit: '座' },
    { title: '市政车辆', num: '20', unit: '辆' },
    { title: '日垃圾处理量', num: '500', unit: '吨' },
    { title: '月垃圾处理量', num: '13000', unit: '吨' }
  ],
  picData: [
    { value: 20, name: '垃圾分类' },
    { value: 30, name: '垃圾清运' },
    { value: 42, name: '市政清扫' },
    { value: 32, name: '市政设施维护' }
  ],
  lineAxisData: ['2-21', '2-22', '2-23', '2-24', '2-25', '2-26', '2-27'],
  picRightData2: [
    { title: '市政站点数量', num: '50', unit: '个' },
    { title: '市政人员数量', num: '200', unit: '人' },
    { title: '日垃圾清运量', num: '150', unit: '吨' },
    { title: '月垃圾清运总量', num: '4500', unit: '吨' }
  ],
  lineSeriesData: [150, 145, 152, 148, 153, 143, 150],
  CCDvScrollBoardConfig: {
    header: ['区域', '问题', '时间', '状态'],
    data: [
      ['东街', '路灯损坏', '07:20', '处理中'],
      ['南街', '市政车辆故障', '08:15', '处理中'],
      ['西街', '道路维修不及时', '08:40', '已解决'],
      ['市中心广场', '排水管道堵塞', '09:10', '已解决'],
      ['城北街道', '市政设施损坏', '09:30', '处理中'],
      ['城西公园', '垃圾堆积', '10:10', '已解决'],
      ['城中商业区', '道路清扫质量差', '10:35', '处理中'],
      ['南部街道', '井盖丢失', '11:20', '已解决'],
      ['北部居民区', '污水外溢', '11:45', '处理中'],
      ['中部道路', '市政车辆违规停放', '12:00', '已解决'],
      ['东部广场', '市政设施未及时维护', '13:15', '处理中'],
      ['西部垃圾处理厂', '设备故障', '13:40', '已解决'],
      ['南郊街道', '垃圾清运不及时', '14:05', '处理中'],
      ['北郊公园', '市政清扫不到位', '14:30', '已解决'],
      ['城中老街', '市政人员不足', '15:00', '处理中']
    ],
    rowNum: 4, // 表行数
    align: ['center', 'center', 'center']
  }
});

const centerData = ref({
  customizeParams: {
    name: '连续维护天数：',
    runningDays: '360',
    tail: '天'
  },
  proJectsList: {
    processName: '施工技术',
    processingScaleName: '处理规模',
    theAmountOfWaterReturnedName: '垃圾清运量',
    responsibleName: '负责人',
    contactNumberName: '联系电话',
    safetyOfficerName: '安全员',
    contactNumber2Name: '联系电话',
    listData: [
      {
        name: '市区市政设施维护项目',
        process: '人工维修 + 设备辅助',
        processingScale: '500公里/年',
        theAmountOfWaterReturned: '3000吨/日',
        responsible: '王磊',
        contactNumber: '138-xxxx-5484',
        safetyOfficer: '赵玲',
        contactNumber2: '139-xxxx-3468',
        realPictures: [
          {
            imgUrl: new URL('@/assets/chart/images/imgs/1.jpg', import.meta.url),
            name: '设备辅助维修'
          },
          {
            imgUrl: new URL('@/assets/chart/images/imgs/2.jpg', import.meta.url),
            name: '人工维修'
          }
        ]
      },
      // {
      //   name: '城乡市政一体化项目',
      //   process: '手动巡检 + 智能监控',
      //   processingScale: '1200公里/年',
      //   theAmountOfWaterReturned: '8000吨/日',
      //   responsible: '刘强',
      //   contactNumber: '138-xxxx-3658',
      //   safetyOfficer: '孙敏',
      //   contactNumber2: '139-xxxx-4857',
      //   realPictures: [
      //     {
      //       imgUrl: new URL('@/assets/chart/images/imgs/3.jpg', import.meta.url),
      //       name: '设备辅助维修'
      //     },
      //     {
      //       imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
      //       name: '人工维修'
      //     }
      //   ]
      // },
      // {
      //   name: '重点区域市政保障项目',
      //   process: '机械化作业 + 人工巡检',
      //   processingScale: '800公里/年',
      //   theAmountOfWaterReturned: '4000吨/日',
      //   responsible: '陈佳',
      //   contactNumber: '138-xxxx-4857',
      //   safetyOfficer: '周婷',
      //   contactNumber2: '139-xxxx-4524',
      //   realPictures: [
      //     {
      //       imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url),
      //       name: '设备辅助维修'
      //     },
      //     {
      //       imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url),
      //       name: '人工维修'
      //     }
      //   ]
      // },
      // {
      //   name: '城市垃圾处理与清运项目',
      //   process: '垃圾分类收集 + 智能垃圾桶',
      //   processingScale: '1500公里/年',
      //   theAmountOfWaterReturned: '6000吨/日',
      //   responsible: '李华',
      //   contactNumber: '138-xxxx-6875',
      //   safetyOfficer: '黄莉',
      //   contactNumber2: '139-xxxx-5867',
      //   realPictures: [
      //     {
      //       imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
      //       name: '设备辅助维修'
      //     },
      //     {
      //       imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
      //       name: '人工维修'
      //     }
      //   ]
      // },
      // {
      //   name: '市政清洁与废水回收利用项目',
      //   process: '机械清扫 + 雨水回收系统',
      //   processingScale: '2000公里/年',
      //   theAmountOfWaterReturned: '10000吨/日',
      //   responsible: '张涛',
      //   contactNumber: '138-xxxx-4587',
      //   safetyOfficer: '杨娜',
      //   contactNumber2: '139-xxxx-4587',
      //   realPictures: [
      //     {
      //       imgUrl: new URL('@/assets/chart/images/2.png', import.meta.url).href,
      //       name: '设备辅助维修'
      //     },
      //     {
      //       imgUrl: new URL('@/assets/chart/images/1.png', import.meta.url).href,
      //       name: '人工维修'
      //     }
      //   ]
      // }
    ]
  }
});

const rightData = ref({
  customize2Params: [
    { name: '市政设施总数', num: '1724', unit: '' },
    { name: '路灯数量', num: '1256', unit: '盏' },
    { name: '排水管道长度', num: '85', unit: '公里' },
    { name: '供水管道长度', num: '68', unit: '公里' },
    { name: '道路总里程', num: '156', unit: '公里' }
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
        data: [42, 58, 115, 150, 190, 145, 230, 210, 280, 310, 270, 250]
      },
      {
        lineColor: '#11e48a',
        name: '去年',
        color: ['rgba(9,114,95,1)', 'rgba(9,114,95,0.1)'],
        type: 'line',
        data: [150, 170, 220, 250, 210, 220, 270, 250, 210, 190, 170, 130]
      }
    ]
  },
  rightTwo: {
    data: [
      { name: '动态显示道路平整度', value: 65 },
      { name: '排水系统畅通率', value: 95 },
      { name: '路灯亮灯率', value: 98 },
      { name: '市政设施完成率', value: 99 },
      { name: '垃圾处理合规率', value: 85 }
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
