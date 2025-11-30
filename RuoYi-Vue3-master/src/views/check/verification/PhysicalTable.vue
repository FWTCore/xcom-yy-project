<template>
  <el-col :span="12" class="statistic-col">
    <el-card>
      <template #header>
        <el-row style="display: flex">
          <div style="flex: 1">
            <Collection
              style="width: 1em; height: 1em; vertical-align: middle"
            />
            <span style="vertical-align: middle">{{ props.title }}</span>
            <span class="global-order" v-if="props.enableGlobalOrder">
              <span>全局排序</span>
              <el-switch
                :default-value="props.globalOrder"
                v-model="globalOrder"
                @change="props.handleGlobalOrderChange"
                size="small"
              />
            </span>
          </div>
          <el-button-group style="">
            <el-button size="small" icon="Refresh" @click="loadData" />
            <ColumnVisibilityControl
              :columns="orderColumns"
              :show-columns-type="'checkbox'"
              select-title="全选"
              :disabled="!props.enableGlobalOrder && props.globalOrder"
              title="排序字段"
              @change="handleOrderChange"
            >
              <template #trigger>
                <el-button size="small">
                  <el-icon><Memo /></el-icon>
                  <el-icon><CaretBottom /></el-icon>
                  <el-badge
                    v-if="showBadge"
                    :value="selectedCount"
                    class="custom-badge"
                    :max="99"
                  />
                </el-button>
              </template>
            </ColumnVisibilityControl>
            <ColumnVisibilityControl
              :columns="physicalColumns"
              :show-columns-type="'checkbox'"
              select-title="全选"
              title="显示字段"
            />
          </el-button-group>
        </el-row>

        <!-- <el-button
              link
              type="primary"
              icon="Refresh"
              @click="loadData()"
            ></el-button> -->
      </template>
      <el-table
        v-loading="physicalLoading"
        :data="physicalList"
        :height="tableHeight"
        @selection-change="handlePhysicalSelectionChange"
      >
        <el-table-column
          v-if="!props.singleSelect"
          type="selection"
          width="50"
          align="center"
        />
        <el-table-column v-else width="50" align="center">
          <template #default="scope">
            <el-radio
              v-model="radioId"
              :value="scope.row"
              @change="handleRadioSelectionChange"
            />
          </template>
        </el-table-column>
        <el-table-column label="序号" align="center" width="50">
          <template #default="scope">
            <span>{{ calculateIndex(scope.$index) }}</span>
          </template>
        </el-table-column>
        <template v-for="field in props.fields">
          <el-table-column
            :label="field.label"
            align="center"
            :key="field.prop"
            :prop="field.prop"
            v-if="physicalColumns[field.prop]?.visible"
            :show-overflow-tooltip="true"
          >
            <template #header v-if="!!field.filter">
              <div class="custom-header">
                <span>{{ field.label }}</span>
                <AsyncTableFilter
                  :type="props.type"
                  v-model="physicalQueryParams[field.filter.name]"
                  :metricsType="field.filter.metricsType"
                  :filter="props.filter"
                  :load-data="props.loadDataOptions"
                  @confirm="() => loadData(true)"
                  @reset="() => loadData(true)"
                />
              </div>
            </template>
          </el-table-column>
        </template>
      </el-table>
      <Pagination
        v-show="physicalTotal > 0"
        :total="physicalTotal"
        v-model:page="physicalQueryParams.pageNum"
        v-model:limit="physicalQueryParams.pageSize"
        :pageSizes="[10, 20, 30, 50, 500]"
        @pagination="() => loadData(false)"
      />
    </el-card>
  </el-col>
</template>

<script setup>
import { onMounted, ref, watch, computed } from "vue";
import AsyncTableFilter from "@/components/AsyncTableFilter/index.vue";
import { Refresh } from "@element-plus/icons-vue";
import { Grid } from "@element-plus/icons-vue";
import { Memo } from "@element-plus/icons-vue";
import { CaretBottom } from "@element-plus/icons-vue";
import ColumnVisibilityControl from "@/components/ColumnVisibilityControl/index.vue";
import Pagination from "@/components/Pagination/index.vue";
// import { getPhysicalList } from "@/api/statistic/physical";

const physicalList = ref([]);
const physicalTotal = ref(0);
const physicalLoading = ref(false);
const physicalQueryParams = ref({
  pageNum: 1,
  pageSize: 500,
  sortFields: [],
  catcategoryIds: [],
  brandNames: [],
  assetNames: [],
  sepcifications: [],
  obtainTimeDays: [],
  managedDeptIds: [],
  usingDeptIds: [],
  managedEmpIds: [],
  usingEmpIds: [],
});

const calculateIndex = (index) => {
  return (
    (physicalQueryParams.value.pageNum - 1) *
      physicalQueryParams.value.pageSize +
    index +
    1
  );
};
const globalOrder = ref(false);
const radioId = ref();
const selectedCount = computed(() => {
  return physicalQueryParams.value.sortFields.length || 0;
});

// 是否显示徽章
const showBadge = computed(() => {
  return selectedCount.value > 0;
});

const physicalColumns = ref({});

const orderColumns = ref({});

const initData = () => {
  physicalColumns.value = [];
  orderColumns.value = [];
  let columns = {};
  let orderCols = [];
  props.fields.forEach((item) => {
    columns[item.prop] = {
      label: item.label,
      visible: true,
      value: item.label,
    };
    if (item.orderable) {
      orderCols[item.orderPos - 1] = {
        key: item.orderPos - 1,
        label: item.label,
        value: item.orderPos - 1,
        visible: false,
        prop: item.prop,
      };
    }
  });
  physicalColumns.value = columns;
  let orderColDict = {};
  orderCols.forEach((item) => {
    orderColDict[item.value] = item;
  });
  orderColumns.value = orderColDict;
};
const loadData = async (refresh = false) => {
  physicalLoading.value = true;
  try {
    if (refresh) {
      physicalQueryParams.value.pageNum = 1;
    }
    if (
      !physicalQueryParams.value.deptId ||
      !physicalQueryParams.value.projectId
    ) {
      physicalList.value = [];
      physicalTotal.value = 0;
      physicalLoading.value = false;
      return;
    }
    const response = await props.loadDataHandler(physicalQueryParams.value);
    physicalList.value = response.rows;
    physicalTotal.value = response.total;
    physicalLoading.value = false;
  } catch (error) {
    console.error("获取物理资产列表失败:", error);
  } finally {
    physicalLoading.value = false;
  }
};

const handleOrderChange = (data) => {
  // 从data对象中提取所有值为true的属性名，放入sortFields数组
  const sortFields = Object.keys(data).filter((key) => data[key] === true);
  // 这里可以使用sortFields进行后续操作
  physicalQueryParams.value.sortFields = sortFields;
  loadData(true);
  if (props.globalOrder && props.handleOrderChange) {
    props.handleOrderChange(sortFields);
    return;
  }
  emit("order-change", sortFields);
};

const emit = defineEmits(["selection-change", "order-change"]);

const physicalSelected = ref([]);

const handleRadioSelectionChange = (id) => {
  emit("selection-change", [radioId.value]);
};
const handlePhysicalSelectionChange = (selection) => {
  // 根据单选/多选属性处理选择逻辑
  // 多选模式：保留所有选中项
  // 向父级组件通知当前选中的元素
  emit("selection-change", selection);
};

const props = defineProps({
  type: {
    type: String,
    default: "physical",
  },
  title: {
    type: String,
    default: "",
  },
  columns: {
    type: Object,
    default: () => {},
  },
  sortFields: {
    type: Array,
    default: () => [],
  },
  filter: {
    type: Object,
    default: () => {},
  },
  enableGlobalOrder: {
    type: Boolean,
    default: false,
  },
  handleGlobalOrderChange: {
    type: Function,
    default: () => {},
  },
  globalOrder: {
    type: Boolean,
    default: false,
  },
  globalFields: {
    type: Array,
    default: () => [],
  },
  fields: {
    type: Array,
    default: () => [],
  },
  handleOrderChange: {
    type: Function,
    default: null,
  },
  loadDataHandler: {
    type: Function,
    default: () => {},
  },
  loadDataOptions: {
    type: Function,
    default: () => {},
  },
  singleSelect: {
    type: Boolean,
    default: false,
  },
  tableHeight: {
    type: Number,
    default: 400,
  },
});

// watch(
//   () => props.globalFields,
//   (newVal, oldVal) => {
//     if (props.globalOrder) {
//       console.log("globalFiels ", props.globalFields);
//       Object.keys(orderColumns.value).forEach((key) => {
//         if (newVal.includes(key)) {
//           orderColumns.value[key].visible = true;
//         } else {
//           orderColumns.value[key].visible = false;
//         }
//       });
//     }
//   }
// );

watch(
  () => props.sortFields,
  (newVal, oldVal) => {
    physicalQueryParams.value = {
      ...physicalQueryParams.value,
      sortFields: newVal,
    };

    Object.keys(orderColumns.value).forEach((key) => {
      if (newVal.includes(key)) {
        orderColumns.value[key].visible = true;
      } else {
        orderColumns.value[key].visible = false;
      }
    });
    loadData();
  }
);

watch(
  () => props.filter,
  (newVal, oldVal) => {
    physicalQueryParams.value = {
      ...physicalQueryParams.value,
      ...newVal,
    };
    if (!newVal.deptId || !newVal.projectId) {
      physicalQueryParams.value = {
        pageNum: 1,
        pageSize: 500,
        sortFields: [],
        catcategoryIds: [],
        brandNames: [],
        assetNames: [],
        sepcifications: [],
        obtainTimeDays: [],
        managedDeptIds: [],
        usingDeptIds: [],
        managedEmpIds: [],
        usingEmpIds: [],
      };
    }
    loadData();
  }
);

onMounted(() => {
  initData();
  loadData();
});
</script>
<style>
.statistic-container {
  margin-top: 20px;
  margin-left: 10px;
  margin-right: 10px;
}
.statistic-col {
  padding-right: 10px;
}
.cell {
  padding: 0 !important;
}
.custom-badge {
  position: absolute;
  top: -5px;
  right: -5px;
  transform: scale(0.75);
}
.global-order {
  margin-left: 20px;
}
.global-order span {
  font-size: 12px;
}

.global-order .el-switch {
  margin-left: 5px;
}
</style>
