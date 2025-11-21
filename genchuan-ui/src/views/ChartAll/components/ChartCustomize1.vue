<template>
  <div class="chart-customize1-css">
    {{ paramsData.name }}<span v-for="(item, key) in paramsData.runningDays" :key="key">{{ item }}</span>{{ paramsData.tail }}
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  paramsData: {
    type: Object,
    default: () => {
      return {
        name: '',
        runningDays: '',
        tail: ''
      };
    }
  }
});
</script>

<style lang="scss" scoped>
.chart-customize1-css {
  /*position: absolute;*/
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  top: 0;
  text-align: center;
  height: 40px;
  line-height: 40px;
  color: #ffffff;
  font-size: 28px;
  font-weight: bold;

  span {
    background: #0069df;
    padding: 3px 5px !important;
    margin-right: 8px !important;
    font-size: 38px;
  }
}
</style>
