<template>

  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--应急安全-全局态势总览-->
      <global-situation-overview v-if="userStore.headerTopActive=='home'" />
      <!--应急安全-分域场景专题-->
      <subdomain-scenario-topics v-if="userStore.headerTopActive=='0602'" />
      <!--应急安全-核心业务指标-->
      <municipal-utilities v-if="userStore.headerTopActive=='0603'" />
      <!--应急安全-事件预警追踪-->
      <event-early-warning-tracking v-if="userStore.headerTopActive=='0604'" />
      <!--应急安全-应急指挥视图-->
      <emergency-command-view v-if="userStore.headerTopActive=='0605'"/>
      <!--应急安全-跨域场景协同-->
      <cross-domain-scenario-collaboration v-if="userStore.headerTopActive=='0606'" />
    </div>

  </dv-full-screen-container>

</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
// import {ref, reactive, onMounted} from 'vue'
import HeaderTop from '@/views/overview/hygienehealth/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/hygienehealth/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/hygienehealth/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/hygienehealth/dpzl/components/EventEarlyWarningTracking.vue'
import MunicipalUtilities from '@/views/overview/hygienehealth/dpzl/components/MunicipalUtilities.vue'
import EmergencyCommandView from '@/views/overview/hygienehealth/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/hygienehealth/dpzl/components/CrossDomainScenarioCollaboration.vue'

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
