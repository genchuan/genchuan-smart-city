<template>
  <div class="dashboard-comprehensive-evaluation-css">综合评价</div>
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
.dashboard-comprehensive-evaluation-css{
  background: #9d27d9;
  text-align: center;
  width: calc(100% / 4);
  height: 100%;
}
</style>
