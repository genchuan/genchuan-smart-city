<template>
  <div class="dashboard-public-service-css">
    公众服务
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
.dashboard-public-service-css{
  background: #64ffd0;
  text-align: center;
  width: calc(100% / 4);
  height: 100%;
}
</style>
