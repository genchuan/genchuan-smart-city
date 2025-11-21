<template>

  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--水利水务-全局态势总览-->
      <global-situation-overview v-if="userStore.headerTopActive=='home'" />
      <!--水利水务-分域场景专题-->
      <subdomain-scenario-topics v-if="userStore.headerTopActive=='0202'" />
      <!--水利水务-核心业务指标-->
      <municipal-utilities v-if="userStore.headerTopActive=='0203'" />
      <!--水利水务-事件预警追踪-->
      <event-early-warning-tracking v-if="userStore.headerTopActive=='0204'" />
      <!--水利水务-应急指挥视图-->
      <emergency-command-view v-if="userStore.headerTopActive=='0205'"/>
      <!--水利水务-跨域场景协同-->
      <cross-domain-scenario-collaboration v-if="userStore.headerTopActive=='0206'" />
    </div>

  </dv-full-screen-container>

</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
// import {ref, reactive, onMounted} from 'vue'
import HeaderTop from '@/views/overview/waterresourceswateraffairs/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/waterresourceswateraffairs/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/waterresourceswateraffairs/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/waterresourceswateraffairs/dpzl/components/EventEarlyWarningTracking.vue'
import MunicipalUtilities from '@/views/overview/waterresourceswateraffairs/dpzl/components/MunicipalUtilities.vue'
import EmergencyCommandView from '@/views/overview/waterresourceswateraffairs/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/waterresourceswateraffairs/dpzl/components/CrossDomainScenarioCollaboration.vue'

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
