<template>
  <el-col :span="8">
    <el-card>
      <template #header>
        <Collection style="width: 1em; height: 1em; vertical-align: middle" />
        <span style="vertical-align: middle">{{ title }}</span>
        <el-button
          style="float: right; padding: 3px 0"
          link
          type="primary"
          icon="Refresh"
          @click="loadData()"
        ></el-button>
      </template>
      <el-table
        v-loading="loading"
        :data="data"
        :height="tableHeight"
        highlight-current-row
        style="width: 100%"
        @sort-change="handleSortChange"
      >
        <el-table-column label="序号" width="60" type="index"></el-table-column>
        <el-table-column
          :key="index"
          :label="props.name"
          align="center"
          prop="metricsName"
        ></el-table-column>
        <el-table-column
          :key="index"
          label="核对数据/总数"
          align="center"
          sortable
        >
          <template #default="scope">
            {{ scope.row.checkCount }}/{{ scope.row.totalCount }}
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </el-col>
</template>
<script setup>
import { onMounted, ref, watch } from "vue";

const props = defineProps({
  title: {
    type: String,
    default: "",
  },
  name: {
    type: String,
    default: "",
  },
  filter: {
    type: Object,
    default: () => {},
  },
  tableHeight: {
    type: String,
    default: "440px",
  },
  handler: {
    type: Function,
    default: () => {},
  },
});

const loading = ref(false);
const data = ref([]);
const descending = ref(null);

// 处理排序变化
const handleSortChange = (column) => {
  // 更新排序方向
  descending.value =
    column.order === null ? null : column.order === "ascending" ? false : true;
  // 重新加载数据
  loadData();
};

const loadData = () => {
  // 合并基础筛选条件和排序信息
  const requestParams = {
    ...props.filter,
    orderFlag: descending.value === null ? null : descending.value,
  };
  if (!requestParams.deptId || !requestParams.projectId) {
    data.value = [];
    return;
  }
  loading.value = true;
  if (!props.handler) {
    loading.value = false;
    return;
  }
  props
    .handler(requestParams)
    .then((res) => {
      loading.value = false;
      data.value = res.data || [];
    })
    .catch(() => {
      loading.value = false;
    });
};

onMounted(() => {
  loadData();
});

watch(
  () => props.filter,
  (newVal, oldVal) => {
    // if (JSON.stringify(newVal) !== JSON.stringify(oldVal)) {
    loadData();
    // }
  }
);

// 重置排序信息（可选，根据需要添加）
defineExpose({
  resetSort: () => {
    sortInfo.value = {};
    loadData();
  },
});
</script>
