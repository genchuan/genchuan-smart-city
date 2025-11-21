<template>

  <dv-full-screen-container class="dv-full-screen-container-main">
    <div class="leadershipCockpit-main">
      <header-top />
      <!--智慧城市一网统管-->
      <city-operation-management-service-platform v-if="userStore.headerTopActive=='home'" />
      <!--水利水务-->
      <water-conservancy-and-water-affairs v-if="userStore.headerTopActive=='1'" />
      <!--市政公用-->
      <municipal-utilities v-if="userStore.headerTopActive=='2'" />
      <!--市容环卫-->
      <city-appearance-and-sanitation v-if="userStore.headerTopActive=='3'" />
      <!--园林绿化-->
      <landscaping v-if="userStore.headerTopActive=='5'" />
      <!--智能井盖-->
      <intelligent-manhole-cover-management-system v-if="userStore.headerTopActive=='61'"/>
      <!--排水防涝-->
      <drainage-and-flood-control v-if="userStore.headerTopActive=='62'"/>
      <!--停车管理系统-->
      <parking-management v-if="userStore.headerTopActive=='63'"/>
      <!--扬尘监测系统-->
      <fugitive-dust-monitoring v-if="userStore.headerTopActive=='64'"/>
      <!--城市渣土车监管系统-->
      <supervision-of-urban-muck-trucks v-if="userStore.headerTopActive=='65'"/>
      <!--餐饮油烟监测系统-->
      <catering-fume-monitoring v-if="userStore.headerTopActive=='66'"/>
      <!--违建巡查管理系统-->
      <illegal-construction-inspection-management v-if="userStore.headerTopActive=='67'"/>
    </div>

  </dv-full-screen-container>

</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
// import {ref, reactive, onMounted} from 'vue'
import HeaderTop from './components/HeaderTop.vue'
import CityOperationManagementServicePlatform from './components/CityOperationManagementServicePlatform.vue'
import WaterConservancyAndWaterAffairs from './components/WaterConservancyAndWaterAffairs.vue'
import CityAppearanceAndSanitation from './components/CityAppearanceAndSanitation.vue'
import MunicipalUtilities from './components/MunicipalUtilities.vue'
import Landscaping from './components/Landscaping.vue'
import IntelligentManholeCoverManagementSystem from './components/IntelligentManholeCoverManagementSystem.vue'
import DrainageAndFloodControl from './components/DrainageAndFloodControl.vue'
import ParkingManagement from './components/ParkingManagement.vue'
import FugitiveDustMonitoring from './components/FugitiveDustMonitoring.vue'
import SupervisionOfUrbanMuckTrucks from './components/SupervisionOfUrbanMuckTrucks.vue'
import CateringFumeMonitoring from './components/CateringFumeMonitoring.vue'
import IllegalConstructionInspectionManagement from './components/IllegalConstructionInspectionManagement.vue'

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
