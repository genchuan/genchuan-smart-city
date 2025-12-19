<template>
  <!--<dv-full-screen-container  style="width: calc(100% - 150px); height: calc(100% - 85px); margin: 20px auto; background: #0f2438">-->
  <div class="thingsBoard-dashboard-css">
    <div class="content-one">
      <div class="left">
        <dashboard-icon-font :total="leftConfig.deviceTotal" title="设备管理" :icon-url="leftConfig.deviceIconUrl"/>
        <dashboard-icon-font :total="leftConfig.entityTotal" title="实体管理" :icon-url="leftConfig.entityIconUrl"/>
        <dashboard-icon-font :total="leftConfig.assetsTotal" title="资产管理" :icon-url="leftConfig.assetsIconUrl"/>
        <!--设备-->
        <!--<dashboard-device :deviceTotal="deviceTotal"/>-->
        <!--实体-->
        <!--<dashboard-entity :entityTotal="entityTotal"/>-->
        <!--资产-->
        <!--<dashboard-assets :assetsTotal="assetsTotal"/>-->
      </div>
      <div class="center">
        <dashboard-title :title="titleName"/>
        <dashboard-map/>
      </div>
      <div class="right">
        <dashboard-icon-font :total="leftConfig.deviceTotal" title="监测点位" :icon-url="leftConfig.monitorIconUrl"/>
        <dashboard-icon-font :total="leftConfig.riskTotal" title="风险预警" :icon-url="leftConfig.riskIconUrl"/>
        <dashboard-icon-font :total="leftConfig.alarmTotal" title="告警中心" :icon-url="leftConfig.alarmIconUrl"/>
        <!--监测点位-->
        <!--<dashboard-monitoring-points/>-->
        <!--风险预警-->
        <!--<dashboard-risk-warning/>-->
        <!--警告中心-->
        <!--<dashboard-warning-center/>-->
      </div>
    </div>
    <div class="content-two">
      <!--分域分析研判-->
      <dashboard-analysis-and-judgment/>
      <!--监督检查-->
      <dashboard-supervision-and-inspection/>
      <!--指挥协调-->
      <dashboard-command-and-coordination/>
    </div>
    <div class="content-three">
      <!--人工智能-->
      <dashboard-artificial-intelligence/>
      <!--业务指导-->
      <dashboard-business-guidance/>
      <!--公众服务-->
      <dashboard-public-service/>
      <!--综合评价-->
      <dashboard-comprehensive-evaluation/>
    </div>
  </div>
  <!--</dv-full-screen-container>-->
</template>

<!-- vue3写法 -->
<script lang="ts" setup>
import {ref, reactive, onMounted} from 'vue'
import dashboardDevice from "@/views/thingsBoard/dashboardConfig/components/DashboardDevice.vue";
import DashboardAnalysisAndJudgment from "@/views/thingsBoard/dashboardConfig/components/DashboardAnalysisAndJudgment.vue";
import DashboardArtificialIntelligence from "@/views/thingsBoard/dashboardConfig/components/DashboardArtificialIntelligence.vue";
import DashboardAssets from "@/views/thingsBoard/dashboardConfig/components/DashboardAssets.vue";
import DashboardBusinessGuidance from "@/views/thingsBoard/dashboardConfig/components/DashboardBusinessGuidance.vue";
import DashboardCommandAndCoordination from "@/views/thingsBoard/dashboardConfig/components/DashboardCommandAndCoordination.vue";
import DashboardComprehensiveEvaluation from "@/views/thingsBoard/dashboardConfig/components/DashboardComprehensiveEvaluation.vue";
import DashboardEntity from "@/views/thingsBoard/dashboardConfig/components/DashboardEntity.vue";
import DashboardMonitoringPoints from "@/views/thingsBoard/dashboardConfig/components/DashboardMonitoringPoints.vue";
import DashboardPublicService from "@/views/thingsBoard/dashboardConfig/components/DashboardPublicService.vue";
import DashboardRiskWarning from "@/views/thingsBoard/dashboardConfig/components/DashboardRiskWarning.vue";
import DashboardSupervisionAndInspection from "@/views/thingsBoard/dashboardConfig/components/DashboardSupervisionAndInspection.vue";
import DashboardTitle from "@/views/thingsBoard/dashboardConfig/components/DashboardTitle.vue";
import DashboardWarningCenter from "@/views/thingsBoard/dashboardConfig/components/DashboardWarningCenter.vue";
import DashboardMap from "@/views/thingsBoard/dashboardConfig/components/DashboardMap.vue";
import DashboardIconFont from "@/views/thingsBoard/dashboardConfig/components/DashboardIconFont.vue";
import {dashboardWsService} from "@/views/thingsBoard/profiles/dashboardWsService";
import {deviceProfileQueryList} from "@/api/genchuan/thingsBoard/profiles";
import {delay} from "@/utils/formatTime";
import deviceIcon from '@/assets/thingsBoard/dashboard/images/img1.png';
import entityIcon from '@/assets/thingsBoard/dashboard/images/img3.png';
import assetsIcon from '@/assets/thingsBoard/dashboard/images/img4.png';
import riskIcon from '@/assets/thingsBoard/dashboard/images/img5.png';
import alarmIcon from '@/assets/thingsBoard/dashboard/images/img6.png';
import monitorIcon from '@/assets/thingsBoard/dashboard/images/img7.png';

// 引入WebSocket服务
const {
  isConnected,
  connectionError,
  receivedTelemetry,
  wsResults,
  connect,
  disconnect,
  sendTelemetry,
  subscribeToDeviceTelemetry
} = dashboardWsService();


// ws传过去的值
const authMessageData = {
  "authCmd": {
    "cmdId": 0,
    "token": window.localStorage.getItem('thingsBoardJwt_token')
  },
  cmds: [
    {
      "type": "ENTITY_COUNT",
      "query": {
        "entityFilter": {
          "type": "deviceType",
          "resolveMultiple": true,
          "deviceTypes": [
            "顺昌排口"
          ],
          "deviceNameFilter": "",
        }
      },
      "cmdId": 2
    },
    {
      "type": "ENTITY_COUNT",
      "query": {
        "entityFilter": {
          "type": "entityType",
          "resolveMultiple": true,
          "entityType": "ENTITY_VIEW"
        }
      },
      "cmdId": 3
    },
    {
      "type": "ENTITY_COUNT",
      "query": {
        "entityFilter": {
          "type": "assetType",
          "resolveMultiple": true,
          "assetTypes": [
            "顺昌排口"
          ],

        }
      },
      "cmdId": 4
    },
    {
      "type": "ALARM_COUNT",
      "query": {
        "severityList": [
          "MINOR",
          "INDETERMINATE"
        ],
        "statusList": [
          "ACTIVE",
          "CLEARED",
          "ACK",
          "UNACK"
        ],
        "searchPropagatedAlarms": false,
        "assigneeId": null
      },
      "cmdId": 5
    },
    {
      "type": "ALARM_COUNT",
      "query": {
        "entityFilter": {
          "type": "entityList",
          "resolveMultiple": true,
          "entityType": "DEVICE",
          "entityList": [
            "ea4671f0-3acc-11f0-9dfc-efca985232a9"
          ]
        },
        "severityList": [],
        "statusList": [],
        "searchPropagatedAlarms": false,
        "assigneeId": null
      },
      "cmdId": 6
    }
  ]
}


//标题
const titleName = '顺昌智慧排口监测系统';
//左边配置
const leftConfig = ref({
  //设备数量
  deviceTotal: 0,
  deviceIconUrl: deviceIcon,
  //实体数量
  entityTotal: 0,
  entityIconUrl: entityIcon,
  //资产数量
  assetsTotal: 0,
  assetsIconUrl: assetsIcon,
  //风险预警
  riskTotal: 0,
  riskIconUrl: riskIcon,
  //告警中心
  alarmTotal: 0,
  alarmIconUrl: alarmIcon,
  //监测点位
  monitorIconUrl: monitorIcon
})


// 定义要添加到 body 的 class 名称
const customClass = 'custom-body-class';


// 监听返回的值
watch(wsResults, (newVal, oldVal) => {
  // 设备数量
  if (newVal.cmdId == 2) {
    leftConfig.value.deviceTotal = newVal.count;
  }
  // 实体数量
  if (newVal.cmdId == 3) {
    leftConfig.value.entityTotal = newVal.count;
  }
  // 资产数量
  if (newVal.cmdId == 4) {
    leftConfig.value.assetsTotal = newVal.count;
  }
  // 风险预警
  if (newVal.cmdId == 5) {
    leftConfig.value.riskTotal = newVal.count;
  }
  // 告警中心
  if (newVal.cmdId == 6) {
    leftConfig.value.alarmTotal = newVal.count;
  }


})

// 数据初始化
const initData = async () => {
  let params = {
    pageSize: 10,
    page: 1,
    sortProperty: 'createdTime',
    textSearch: '为了方式token过期拉下接口',
    sortOrder: 'DESC'
  }
  await deviceProfileQueryList(params);
  await delay(1000);

  await connect(authMessageData);
  if (isConnected) {
    console.log('ws11 连接连接上')
  } else {
    console.log('ws11 连接没连接上')
  }

};

const addClassToBody = () => {
  document.body.classList.add(customClass);
};
const removeClassFromBody = () => {
  document.body.classList.remove(customClass);
};


// 挂载完毕
onMounted(() => {
  initData();//数据初始化
  console.log('挂载完毕')
  addClassToBody();
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody();
  disconnect(); //关闭ws
});
</script>


<style lang="scss">
@use "@/assets/thingsBoard/dashboard/css/style";
</style>
