<template>
  <div :class="['dashboard-title-css', className]">{{ title }}</div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import {ref, reactive, onMounted} from 'vue'

const props = defineProps({
  // 标题
  title: {
    type: String,
    default: '标题名称'
  },
  // class名称
  className: {
    type: String,
    default: ''
  }
})


// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
})
</script>

<style lang="scss" scoped>

</style>
