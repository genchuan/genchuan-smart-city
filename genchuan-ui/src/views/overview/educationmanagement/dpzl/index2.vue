<template>
  <div></div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import { ref, reactive, onMounted} from 'vue'

// 挂载完毕
onMounted(() => {
  console.log()
  const  url = window.location.origin+'/overview/educationmanagement/dpzl';
  window.open(url,"_self");
  console.log('挂载完毕')
})
</script>

<style lang="scss" scoped></style>
