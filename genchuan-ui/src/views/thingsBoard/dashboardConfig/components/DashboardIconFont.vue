<template>
  <!--图标和字-->
  <div :class="['icon-font-css',className]">
    <div class="content1">
      <div class="s1">
        <img :src="iconUrl"/>
        <span>{{ title }}</span>
      </div>
      <div class="s2">
        <i>{{ total }}</i>
        <img src="@/assets/thingsBoard/dashboard/images/img2.png"/>
      </div>
    </div>
  </div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import { ref, reactive, onMounted} from 'vue'
const props = defineProps({
  // 资产数量
  title: {
    type: String,
    default: ''
  },
  total: {
    type: Number,
    default: 0
  },
  iconUrl:{
    type: String,
    required: true,
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

<style lang="scss" scoped></style>
