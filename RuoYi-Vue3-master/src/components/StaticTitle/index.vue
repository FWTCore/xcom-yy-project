<template>
  <div class="title">
    <p class="title-text">{{ props.title }}</p>
  </div>
</template>
<script setup name="StaticTitle">
import { defineProps } from "vue";

const props = defineProps({
  title: {
    type: String,
    default: "",
  },
});
</script>
<style>
.title {
  display: flex;
  border-bottom: 1px solid #e4e7ed;
  margin: 0 10px;
  padding: 0;
}

.title > .title-text {
  flex: 1;
  font-size: 14px;
  font-weight: 700;
  color: var(--el-text-color-regular);
}
</style>
