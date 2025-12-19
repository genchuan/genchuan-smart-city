<template>

  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--应用场景导航-->
      <subdomain-scenario-topics v-if="userStore.headerTopActive=='nav'" />
      <!--全局态势总览-->
      <global-situation-overview v-if="userStore.headerTopActive=='home'" />
      <!--核心业务指标-->
      <municipal-utilities v-if="userStore.headerTopActive=='0103'" />
      <!--事件预警追踪-->
      <event-early-warning-tracking v-if="userStore.headerTopActive=='0104'" />
      <!--应急指挥视图-->
      <emergency-command-view v-if="userStore.headerTopActive=='0105'"/>
      <!--跨域场景协同-->
      <cross-domain-scenario-collaboration v-if="userStore.headerTopActive=='0106'" />
    </div>

  </dv-full-screen-container>

</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import HeaderTop from '@/views/overview/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/dpzl/components/EventEarlyWarningTracking.vue'
import MunicipalUtilities from '@/views/overview/dpzl/components/MunicipalUtilities.vue'
import EmergencyCommandView from '@/views/overview/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/dpzl/components/CrossDomainScenarioCollaboration.vue'

import { useUserStore } from '@/store/modules/user';
const userStore = useUserStore();

</script>

<style lang="scss">
@use "@/assets/chart/css/dataV";

</style>
