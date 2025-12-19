<template>
  <div class="dashboard-business-guidance-css">业务指导</div>
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
.dashboard-business-guidance-css{
  background: #a91f1f;
  text-align: center;
  width: calc(100% / 4);
  height: 100%;
}
</style>
