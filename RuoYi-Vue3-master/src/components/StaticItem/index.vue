<template>
  <el-col :offset="3" :span="9" class="">
    <el-form-item :label="props.label" prop="projectName">
      <el-input readonly :value="props.value" />
    </el-form-item>
  </el-col>
</template>
<script setup>
const props = defineProps({
  label: {
    type: String,
    default: "",
  },
  value: {
    type: Number,
    default: 0,
  },
});
</script>
