<template>
  <div class="dashboard-analysis-and-judgment-css">
    分析研判
  </div>
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
.dashboard-analysis-and-judgment-css{
  background: #e0e0e0;
  text-align: center;
  width: calc(100% / 3);
  height: 100%;
}
</style>
