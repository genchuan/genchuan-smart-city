<template>
  <div class="dashboard-artificial-intelligence-css">人工智能</div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import {ref, reactive, onMounted} from 'vue'

// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
})
</script>

<style lang="scss" scoped>
.dashboard-artificial-intelligence-css {
  background: #4fa2f8;
  text-align: center;
  width: calc(100% / 4);
  height: 100%;
}
</style>
