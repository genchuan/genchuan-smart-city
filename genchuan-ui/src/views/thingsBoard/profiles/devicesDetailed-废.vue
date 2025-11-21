<template>
  <div>123123</div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import { ref, reactive, onMounted,onBeforeMount} from 'vue'
defineOptions({ name: 'DeviceTelemetryDetail' })
const route = useRoute()
route.meta.title = `${route.query.deviceName}-遥测数据详细`


// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
  // route.meta.activeMenu = '/tB/a/devices'
})
</script>

<style lang="scss" scoped></style>
