<template>
  <div class="dashboard-risk-warning">风险预警</div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import { ref, reactive, onMounted} from 'vue'

// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
})
</script>

<style lang="scss" scoped>
.dashboard-risk-warning{
  height: calc(100% / 3);
  text-align: center;
  background: #2bb673;
}
</style>
