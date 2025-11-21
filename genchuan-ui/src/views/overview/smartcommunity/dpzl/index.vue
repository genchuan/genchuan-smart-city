<template>
  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--智慧社区-全局态势总览-->
      <global-situation-overview v-if="userStore.headerTopActive=='home'" />
      <!--智慧社区-分域场景专题-->
      <subdomain-scenario-topics v-if="userStore.headerTopActive=='1002'" />
      <!--智慧社区-核心业务指标-->
      <core-indicators v-if="userStore.headerTopActive=='1003'" />
      <!--智慧社区-事件预警追踪-->
      <event-early-warning-tracking v-if="userStore.headerTopActive=='1004'" />
      <!--智慧社区-应急指挥视图-->
      <emergency-command-view v-if="userStore.headerTopActive=='1005'"/>
      <!--智慧社区-跨域场景协同-->
      <cross-domain-scenario-collaboration v-if="userStore.headerTopActive=='1006'" />
    </div>
  </dv-full-screen-container>
</template>

<script lang="ts" setup name="test">
import HeaderTop from '@/views/overview/smartcommunity/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/smartcommunity/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/smartcommunity/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/smartcommunity/dpzl/components/EventEarlyWarningTracking.vue'
import CoreIndicators from '@/views/overview/smartcommunity/dpzl/components/CoreIndicators.vue'
import EmergencyCommandView from '@/views/overview/smartcommunity/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/smartcommunity/dpzl/components/CrossDomainScenarioCollaboration.vue'

import { useUserStore } from '@/store/modules/user';
const userStore = useUserStore();
</script>
// import "@/assets/chart/css/dataV";
<style lang="scss">
@use "@/assets/chart/css/dataV";
</style>
