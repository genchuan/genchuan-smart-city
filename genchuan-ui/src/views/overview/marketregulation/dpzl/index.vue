<template>
  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />

      <!-- 使用动态组件和 keep-alive 避免重复销毁创建 -->
      <keep-alive>
        <component
          :is="currentComponent"
          :key="userStore.headerTopActive"
        />
      </keep-alive>
    </div>
  </dv-full-screen-container>
</template>

<script lang="ts" setup name="test">
import { onMounted, watch, computed, shallowRef, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import HeaderTop from '@/views/overview/marketregulation/dpzl/components/HeaderTop.vue'
import GlobalSituationOverview from '@/views/overview/marketregulation/dpzl/components/GlobalSituationOverview.vue'
import SubdomainScenarioTopics from '@/views/overview/marketregulation/dpzl/components/SubdomainScenarioTopics.vue'
import EventEarlyWarningTracking from '@/views/overview/marketregulation/dpzl/components/EventEarlyWarningTracking.vue'
import MunicipalUtilities from '@/views/overview/marketregulation/dpzl/components/MunicipalUtilities.vue'
import EmergencyCommandView from '@/views/overview/marketregulation/dpzl/components/EmergencyCommandView.vue'
import CrossDomainScenarioCollaboration from '@/views/overview/marketregulation/dpzl/components/CrossDomainScenarioCollaboration.vue'

import { useUserStore } from '@/store/modules/user';

const userStore = useUserStore();
const route = useRoute();

// 修复：使用 shallowRef 避免不必要的响应式更新
const componentMap = shallowRef({
  'home': GlobalSituationOverview,
  '1202': SubdomainScenarioTopics,
  '1203': MunicipalUtilities,
  '1204': EventEarlyWarningTracking,
  '1205': EmergencyCommandView,
  '1206': CrossDomainScenarioCollaboration
});

const currentComponent = computed(() => {
  return componentMap.value[userStore.headerTopActive] || GlobalSituationOverview;
});

// 修复：简化的路由监听
watch(
  () => route.query.active,
  (newActive) => {
    if (newActive && newActive !== userStore.headerTopActive) {
      console.log('路由变化:', newActive);
      userStore.headerTopActive = newActive;

      // 延迟确保 DOM 更新
      nextTick(() => {
        console.log('当前组件:', userStore.headerTopActive);
      });
    }
  },
  { immediate: true }
);

onMounted(() => {
  console.log('页面初始化完成');
});
</script>

<style lang="scss">
@use "@/assets/chart/css/dataV";
</style>
