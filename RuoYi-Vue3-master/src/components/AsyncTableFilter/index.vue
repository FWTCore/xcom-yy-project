<!-- AsyncTableFilter.vue -->
<template>
  <el-popover
    v-model:visible="visible"
    placement="bottom"
    :width="popoverWidth"
    trigger="click"
    @show="handlePopoverShow"
  >
    <template #reference>
      <el-button
        :icon="Filter"
        link
        size="small"
        :class="{ 'is-filtered': hasActiveFilter }"
        @click.stop
      />
    </template>

    <div class="async-table-filter">
      <!-- 搜索框 -->
      <el-input
        v-if="searchable"
        v-model="keyword"
        :placeholder="searchPlaceholder"
        clearable
        size="small"
        @input="handleSearch"
      />

      <!-- 筛选列表 -->
      <div ref="listRef" class="filter-list" @scroll="handleScroll">
        <el-checkbox-group v-model="selectedValues">
          <el-checkbox
            v-for="(item, index) in options"
            :key="index"
            :label="item.metricsId"
            class="filter-item"
          >
            {{ item.metricsName }} ({{ item.checkCount }}/{{ item.totalCount }})
          </el-checkbox>
        </el-checkbox-group>

        <!-- 加载状态 -->
        <div v-if="loading" class="filter-status">
          <el-icon class="is-loading"><Loading /></el-icon>
          {{ loadingText }}
        </div>

        <!-- 无更多数据 -->
        <!-- <div v-if="noMore && options.length > 0" class="filter-status">
          {{ noMoreText }}
        </div> -->

        <!-- 空状态 -->
        <div v-if="!loading && options.length === 0" class="filter-status">
          {{ emptyText }}
        </div>
      </div>

      <!-- 操作按钮 -->
      <div class="filter-actions">
        <el-button size="small" @click="handleReset">
          {{ resetText }}
        </el-button>
        <el-button type="primary" size="small" @click="handleConfirm">
          {{ confirmText }}
        </el-button>
      </div>
    </div>
  </el-popover>
</template>

<script setup>
import { ref, computed, watch } from "vue";
import { Filter, Loading } from "@element-plus/icons-vue";
import { metricsList } from "@/api/statistic/metrics";

const props = defineProps({
  type: {
    type: String,
    default: "physical",
  },
  // 指标类型
  metricsType: {
    type: Number,
    default: 0,
  },
  filter: {
    type: Object,
    default: () => ({}),
  },
  // 当前选中的值
  modelValue: {
    type: Array,
    default: () => [],
  },
  // 选项的值字段名
  valueKey: {
    type: String,
    default: "value",
  },
  // 选项的标签字段名
  labelKey: {
    type: String,
    default: "label",
  },
  // 是否显示搜索框
  searchable: {
    type: Boolean,
    default: true,
  },
  // 搜索框占位符
  searchPlaceholder: {
    type: String,
    default: "搜索...",
  },
  // 每页大小
  pageSize: {
    type: Number,
    default: 20,
  },
  // 弹出框宽度
  popoverWidth: {
    type: Number,
    default: 280,
  },
  // 搜索防抖时间(ms)
  searchDebounce: {
    type: Number,
    default: 300,
  },
  // 滚动加载触发距离(px)
  scrollThreshold: {
    type: Number,
    default: 50,
  },
  // 文本配置
  loadingText: {
    type: String,
    default: "加载中...",
  },
  noMoreText: {
    type: String,
    default: "没有更多了",
  },
  emptyText: {
    type: String,
    default: "暂无数据",
  },
  resetText: {
    type: String,
    default: "重置",
  },
  confirmText: {
    type: String,
    default: "确定",
  },
});

const emit = defineEmits(["update:modelValue", "change", "confirm", "reset"]);

// 状态管理
const visible = ref(false);
const keyword = ref("");
const options = ref([]);
const selectedValues = ref([...props.modelValue]);
const loading = ref(false);
const noMore = ref(false);
const currentPage = ref(1);
const listRef = ref(null);

// 是否有激活的筛选
const hasActiveFilter = computed(() => selectedValues.value.length > 0);

// 加载数据
const loadOptions = async (isRefresh = false) => {
  if (loading.value) return;

  loading.value = true;
  if (!props.filter.deptId || !props.filter.projectId) {
    options.value = [];
    noMore.value = true;
    loading.value = false;
    return;
  }
  try {
    // 调用外部传入的 loadData 函数，传递当前的搜索关键词
    const result = await metricsList(props.type, {
      pageNum: isRefresh ? 1 : currentPage.value,
      pageSize: props.pageSize,
      searchName: keyword.value,
      metricsType: props.metricsType,
      ...props.filter,
    });

    if (isRefresh) {
      options.value = result.rows || [];
      currentPage.value = 1;
      // 重置滚动位置
      if (listRef.value) {
        listRef.value.scrollTop = 0;
      }
    } else {
      options.value = [...options.value, ...(result.rows || [])];
    }

    // 判断是否有更多数据
    noMore.value = !hasMore(result.total);
    if (hasMore(result.total)) {
      currentPage.value++;
    }
  } catch (error) {
    console.error("加载筛选数据失败:", error);
    options.value = [];
    noMore.value = true;
  } finally {
    loading.value = false;
  }
};

const hasMore = (total) => total > options.value.length;

// 搜索防抖 - 搜索时重新请求服务端
let searchTimer = null;
const handleSearch = () => {
  clearTimeout(searchTimer);
  searchTimer = setTimeout(() => {
    // 搜索时重置分页，重新请求第一页数据
    currentPage.value = 1;
    noMore.value = false;
    loadOptions(true);
  }, props.searchDebounce);
};

// 滚动加载 - 加载下一页
const handleScroll = (e) => {
  const { scrollTop, scrollHeight, clientHeight } = e.target;

  if (
    scrollHeight - scrollTop - clientHeight < props.scrollThreshold &&
    !loading.value &&
    !noMore.value
  ) {
    loadOptions();
  }
};

// 确认筛选
const handleConfirm = () => {
  visible.value = false;
  emit("update:modelValue", selectedValues.value);
  emit("change", selectedValues.value);
  emit("confirm", selectedValues.value);
};

// 重置筛选
const handleReset = () => {
  // visible.value = false;
  selectedValues.value = [];
  keyword.value = "";
  currentPage.value = 1;
  noMore.value = false;
  emit("update:modelValue", selectedValues.value);
  emit("reset");
  loadOptions(true);
};

// 弹出框显示时的处理
const handlePopoverShow = () => {
  // 如果没有数据，首次加载
  if (options.value.length === 0) {
    loadOptions(true);
  }
};

// 监听外部值变化
watch(
  () => props.modelValue,
  (newVal) => {
    selectedValues.value = [...newVal];
  },
  { deep: true }
);

watch(
  () => props.filter,
  (newVal) => {
    if (JSON.stringify(newVal) !== JSON.stringify(props.filter)) {
      loadOptions(true);
    }
  },
  { deep: true }
);
</script>

<style scoped>
.async-table-filter {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.filter-list {
  max-height: 300px;
  overflow-y: auto;
  padding: 4px 0;
}

.filter-list::-webkit-scrollbar {
  width: 6px;
}

.filter-list::-webkit-scrollbar-thumb {
  background-color: var(--el-border-color);
  border-radius: 3px;
}

.filter-list::-webkit-scrollbar-thumb:hover {
  background-color: var(--el-border-color-dark);
}

.filter-item {
  display: flex;
  width: 100%;
  padding: 8px 12px;
  margin: 0;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.filter-item:hover {
  background-color: var(--el-fill-color-light);
}

:deep(.filter-item .el-checkbox__label) {
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.filter-status {
  text-align: center;
  padding: 12px;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.filter-actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  padding-top: 8px;
  border-top: 1px solid var(--el-border-color-light);
}

.is-filtered {
  color: var(--el-color-primary);
}

.is-filtered:hover {
  color: var(--el-color-primary-light-3);
}

.is-loading {
  animation: rotating 2s linear infinite;
}

@keyframes rotating {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
