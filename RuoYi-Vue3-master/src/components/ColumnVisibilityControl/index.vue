<template>
  <el-tooltip
    effect="dark"
    :content="props.title"
    placement="top"
    v-if="Object.keys(columns).length > 0"
  >
    <el-button
      icon="Menu"
      size="small"
      @click="showColumn()"
      v-if="showColumnsType == 'transfer'"
      style="margin: 0; padding: 0"
    />
    <el-dropdown
      trigger="click"
      :hide-on-click="false"
      :disabled="props.disabled"
      style="display: inline-block; margin: 0"
      v-if="showColumnsType == 'checkbox'"
    >
      <slot name="trigger">
        <el-button size="small">
          <el-icon><Grid /></el-icon>
          <el-icon><CaretBottom /></el-icon>
        </el-button>
      </slot>
      <!-- <el-button circle icon="Menu" style="margin: 0; padding: 0" /> -->
      <template #dropdown>
        <el-dropdown-menu>
          <!-- 全选/反选 按钮 -->
          <el-dropdown-item>
            <el-checkbox
              :indeterminate="isIndeterminate"
              v-model="isChecked"
              @change="toggleCheckAll"
            >
              {{ props.selectTitle }}
            </el-checkbox>
          </el-dropdown-item>
          <div class="check-line"></div>
          <template v-for="(item, key) in columns" :key="item.key">
            <el-dropdown-item>
              <el-checkbox
                v-model="item.visible"
                @change="checkboxChange($event, key)"
                :label="item.label"
                :value="item.value"
              />
            </el-dropdown-item>
          </template>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </el-tooltip>
  <el-dialog :title="title" v-model="open" append-to-body>
    <el-transfer
      :titles="['显示', '隐藏']"
      v-model="value"
      :data="transferData"
      @change="dataChange"
    ></el-transfer>
  </el-dialog>
</template>

<script setup>
import { ref, computed, watch } from "vue";

// 定义事件
const emit = defineEmits(["change"]);

const props = defineProps({
  /* 显隐列信息（数组格式、对象格式） */
  columns: {
    type: [Array, Object],
    default: () => ({}),
  },
  /* 显隐列类型（transfer穿梭框、checkbox复选框） */
  showColumnsType: {
    type: String,
    default: "checkbox",
  },
  /* 选择标题 */
  selectTitle: {
    type: String,
    default: "全选",
  },
  /* 弹出层标题 */
  title: {
    type: String,
    default: "",
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  key: {
    type: String,
    default: "key",
  }
});

// 显隐数据
const value = ref([]);
// 弹出层标题
const title = ref("显示/隐藏");
// 是否显示弹出层
const open = ref(false);

// 是否全选/半选 状态
const isChecked = computed({
  get: () =>
    Array.isArray(props.columns)
      ? props.columns.every((col) => col.visible)
      : Object.values(props.columns).every((col) => col.visible),
  set: () => {},
});

const isIndeterminate = computed(() =>
  Array.isArray(props.columns)
    ? props.columns.some((col) => col.visible) && !isChecked.value
    : Object.values(props.columns).some((col) => col.visible) &&
      !isChecked.value
);

const transferData = computed(() =>
  Array.isArray(props.columns)
    ? props.columns.map((item, index) => ({ key: index, label: item.label }))
    : Object.keys(props.columns).map((key, index) => ({
        key: index,
        label: props.columns[key].label,
      }))
);

// 打开显隐列dialog
function showColumn() {
  if (props.disabled) {
    return;
  }
  open.value = true;
}

// 右侧列表元素变化
// function dataChange(data) {
//   if (Array.isArray(props.columns)) {
//     for (let item in props.columns) {
//       const key = props.columns[item].key;
//       props.columns[item].visible = !data.includes(key);
//     }
//   } else {
//     Object.keys(props.columns).forEach((key, index) => {
//       props.columns[key].visible = !data.includes(index);
//     });
//   }
// }

// 单勾选
function checkboxChange(event, key) {
  if (Array.isArray(props.columns)) {
    props.columns.filter((item) => item.key == key)[0].visible = event;
  } else {
    props.columns[key].visible = event;
  }
  // 触发change事件
  emitChangeEvent();
}

// 切换全选/反选
function toggleCheckAll() {
  const newValue = !isChecked.value;
  if (Array.isArray(props.columns)) {
    props.columns.forEach((col) => (col.visible = newValue));
  } else {
    Object.values(props.columns).forEach((col) => (col.visible = newValue));
  }
  // 触发change事件
  emitChangeEvent();
}

// 触发change事件的辅助函数
function emitChangeEvent() {
  // 构建当前状态对象
  const currentState = Array.isArray(props.columns)
    ? props.columns.reduce((acc, item) => {
        acc[item.key] = item.visible;
        return acc;
      }, {})
    : Object.keys(props.columns).reduce((acc, key) => {
        acc[key] = props.columns[key].visible;
        return acc;
      }, {});

  // 触发事件，传递当前状态
  emit("change", currentState);
}

// 监听 columns 变化，初始化 transfer 模式下的值
watch(
  () => props.columns,
  (newColumns) => {
    if (props.showColumnsType == "transfer" && newColumns) {
      value.value = [];
      if (Array.isArray(newColumns)) {
        for (let item in newColumns) {
          if (newColumns[item].visible === false) {
            value.value.push(parseInt(item));
          }
        }
      } else {
        Object.keys(newColumns).forEach((key, index) => {
          if (newColumns[key].visible === false) {
            value.value.push(index);
          }
        });
      }
    }
  },
  { deep: true, immediate: true }
);

// 为transfer模式也添加change事件触发
function dataChange(data) {
  if (Array.isArray(props.columns)) {
    for (let item in props.columns) {
      const key = props.columns[item].key;
      props.columns[item].visible = !data.includes(key);
    }
  } else {
    Object.keys(props.columns).forEach((key, index) => {
      props.columns[key].visible = !data.includes(index);
    });
  }
  // 触发change事件
  emitChangeEvent();
}
</script>

<style lang="scss" scoped>
// 重置组件根元素样式
:host {
  display: inline-block;
}

// 确保按钮在按钮组中显示正确的边框
:deep(.el-button) {
  margin: 0;
  // 确保按钮有完整的边框
  //   border-left-width: 1px !important;
  //   border-right-width: 1px !important;
  // border-left-color: #dcdfe6 !important;
  // border-radius: 0;
  // border: 1px solid #dcdfe6;
}

// 确保下拉菜单按钮在按钮组中正确显示
:deep(.el-dropdown) {
  margin: 0;
}

// 调整transfer按钮样式
:deep(.el-transfer__button) {
  display: block;
  margin-left: 0px;
}
:deep(.el-transfer__button:first-child) {
  margin-bottom: 10px;
}

// 下拉菜单样式
:deep(.el-dropdown-menu__item) {
  line-height: 30px;
  padding: 0 17px;
}

// 分隔线样式
.check-line {
  width: 90%;
  height: 1px;
  background-color: #ccc;
  margin: 3px auto;
}
</style>
