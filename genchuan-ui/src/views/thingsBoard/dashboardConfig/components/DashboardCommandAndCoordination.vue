<template>
  <div class="dashboard-command-and-coordination-css">指挥协调</div>
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
.dashboard-command-and-coordination-css{
  background: #4fa2f8;
  text-align: center;
  width: calc(100% / 3);
  height: 100%;
}
</style>
