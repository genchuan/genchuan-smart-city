<template>

  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--生态环保-全局态势总览-->
      <global-situation-overview v-if="userStore.headerTopActive=='home'" />
      <!--生态环保-分域场景专题-->
      <subdomain-scenario-topics v-if="userStore.headerTopActive=='0302'" />
      <!--生态环保-核心业务指标-->
      <municipal-utilities v-if="userStore.headerTopActive=='0303'" />
      <!--生态环保-事件预警追踪-->
      <event-early-warning-tracking v-if="userStore.headerTopActive=='0304'" />
      <!--生态环保-应急指挥视图-->
      <emergency-command-view v-if="userStore.headerTopActive=='0305'"/>
      <!--生态环保-跨域场景协同-->
      <cross-domain-scenario-collaboration v-if="userStore.headerTopActive=='0306'" />
    </div>

  </dv-full-screen-container>

</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
// import {ref, reactive, onMounted} from 'vue'
import HeaderTop from '@/views/overview/ecologicalprotection/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/ecologicalprotection/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/ecologicalprotection/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/ecologicalprotection/dpzl/components/EventEarlyWarningTracking.vue'
import MunicipalUtilities from '@/views/overview/ecologicalprotection/dpzl/components/MunicipalUtilities.vue'
import EmergencyCommandView from '@/views/overview/ecologicalprotection/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/ecologicalprotection/dpzl/components/CrossDomainScenarioCollaboration.vue'

import { useUserStore } from '@/store/modules/user';
const userStore = useUserStore();
// 挂载完毕
// onMounted(() => {
//   console.log('挂载完毕')
// })
</script>
// import "@/assets/chart/css/dataV";
<style lang="scss">
@use "@/assets/chart/css/dataV";
</style>
