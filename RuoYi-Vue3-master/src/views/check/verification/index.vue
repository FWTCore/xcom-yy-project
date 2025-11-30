<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="归属单位" prop="deptId">
        <el-tree-select
          v-model="queryParams.deptId"
          :data="enabledDeptOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }"
          value-key="id"
          placeholder="请选择归属单位"
          style="width: 240px"
          clearable
          @change="handleTreeNodeClick"
          filterable
        />
      </el-form-item>
      <el-form-item prop="projectId">
        <template #label>
          <span>
            <el-tooltip content="需要先选择单位" placement="top">
              <el-icon><question-filled /></el-icon>
            </el-tooltip>
            项目
          </span>
        </template>
        <el-select
          v-model="queryParams.projectId"
          placeholder="请选择"
          style="width: 240px"
          clearable
        >
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        <el-button
          type="primary"
          icon="Plus"
          :disabled="
            physicalSelected.length === 0 || ledgerSelected.length === 0
          "
          plain
          v-hasPermi="['check:verification:relational']"
          @click="handleRelation"
          >关联</el-button
        >
      </el-form-item>
    </el-form>
    <StaticTitle title="账实核对" />
    <el-row class="statistic-container">
      <PhysicalTable
        type="physical"
        :tableHeight="tableHeight"
        :filter="filter"
        :sortFiels="physicalSortFields"
        title="实物资产列表"
        enableGlobalOrder
        :handleGlobalOrderChange="handleGlobalOrderChange"
        :globalOrder="globalOrder"
        :fields="physicalFields"
        :loadDataHandler="getPhysicalList"
        :handleOrderChange="handlePhysicalOrderChange"
        @selection-change="handlePhysicalSelectionChange"
        @order-change="handlePhysicalOrderChange"
      />
      <PhysicalTable
        type="ledger"
        :tableHeight="tableHeight"
        :filter="filter"
        title="财务资产列表"
        :sortFields="ledgerSortFields"
        :enableGlobalOrder="false"
        :globalOrder="globalOrder"
        :globalFields="globalFields"
        :fields="ledgerFields"
        :loadDataHandler="ledgerList"
        :singleSelect="true"
        @selection-change="handleLedgerSelectionChange"
      />
    </el-row>
  </div>
</template>

<script setup name="Project">
import { listAllProject } from "@/api/business/project";
import PhysicalTable from "@/views/check/verification/PhysicalTable.vue";
import StaticTitle from "@/components/StaticTitle/index.vue";
import { deptTreeNoAuthSelect } from "@/api/system/user";
import { getPhysicalList } from "@/api/statistic/physical";
import { ledgerList } from "@/api/statistic/ledger";
import { relation } from "@/api/statistic/metrics";
import { ElMessage } from "element-plus";

const { proxy } = getCurrentInstance();
const projectOptions = ref([]);
const showSearch = ref(true);
const enabledDeptOptions = ref(undefined);
const tableHeight = ref("400px");
const filter = ref({});

const globalOrder = ref(false);
const globalFields = ref([]);
const physicalFields = ref([
  {
    label: "临时编码",
    prop: "temporaryCode",
  },
  {
    label: "门类名称",
    prop: "categoryName",
    orderable: true,
    orderPos: 1,
    filter: {
      metricsType: 0,
      value: "",
      name: "categoryIds",
    },
  },
  {
    label: "品牌名称",
    prop: "brandName",
    orderable: true,
    orderPos: 7,
    filter: {
      metricsType: 1,
      value: "",
      name: "brandNames",
    },
  },
  {
    label: "资产名称",
    prop: "assetName",
    orderable: true,
    orderPos: 2,
    filter: {
      metricsType: 2,
      value: "",
      name: "assetNames",
    },
  },
  {
    label: "规格型号",
    prop: "specification",
    orderable: true,
    orderPos: 8,
    filter: {
      metricsType: 3,
      value: "",
      name: "specifications",
    },
  },
  {
    label: "存放地点",
    prop: "locationName",
    orderable: true,
    orderPos: 5,
    filter: {
      metricsType: 5,
      value: "",
      name: "locationIds",
    },
  },
  {
    label: "管理部门",
    prop: "managedDeptName",
    filter: {
      metricsType: 6,
      value: "",
      name: "managedDeptIds",
    },
  },
  {
    label: "使用部门",
    prop: "usingDeptName",
    orderable: true,
    orderPos: 4,
    filter: {
      metricsType: 7,
      value: "",
      name: "usingDeptIds",
    },
  },
  {
    label: "管理员工",
    prop: "managedEmpName",
    filter: {
      metricsType: 8,
      value: "",
      name: "managedEmpIds",
    },
  },
  {
    label: "使用员工",
    prop: "usingEmpName",
    orderable: true,
    orderPos: 6,
    filter: {
      metricsType: 9,
      value: "",
      name: "usingEmpIds",
    },
  },
]);
const ledgerFields = ref([
  {
    label: "原始编码",
    prop: "originalCode",
  },
  {
    label: "门类名称",
    prop: "categoryName",
    orderable: true,
    orderPos: 1,
    filter: {
      metricsType: 0,
      value: "",
      name: "categoryIds",
    },
  },
  {
    label: "品牌名称",
    prop: "brandName",
    orderable: true,
    orderPos: 7,
    filter: {
      metricsType: 1,
      value: "",
      name: "brandNames",
    },
  },
  {
    label: "资产名称",
    prop: "assetName",
    orderable: true,
    orderPos: 2,
    filter: {
      metricsType: 2,
      value: "",
      name: "assetNames",
    },
  },
  {
    label: "规格型号",
    prop: "specification",
    orderable: true,
    orderPos: 8,
    filter: {
      metricsType: 3,
      value: "",
      name: "specifications",
    },
  },
  {
    label: "取得时间",
    prop: "obtainTime",
    orderable: true,
    orderPos: 3,
    filter: {
      metricsType: 4,
      value: "",
      name: "obtainTimeDays",
    },
  },
  {
    label: "存放地点",
    prop: "locationName",
    orderable: true,
    orderPos: 5,
    filter: {
      metricsType: 5,
      value: "",
      name: "locationIds",
    },
  },
  {
    label: "管理部门",
    prop: "managedDeptName",
    filter: {
      metricsType: 6,
      value: "",
      name: "managedDeptIds",
    },
  },
  {
    label: "使用部门",
    prop: "usingDeptName",
    orderable: true,
    orderPos: 4,
    filter: {
      metricsType: 7,
      value: "",
      name: "usingDeptIds",
    },
  },
  {
    label: "管理员工",
    prop: "managedEmpName",
    filter: {
      metricsType: 8,
      value: "",
      name: "managedEmpIds",
    },
  },
  {
    label: "使用员工",
    prop: "usingEmpName",
    orderable: true,
    orderPos: 6,
    filter: {
      metricsType: 9,
      value: "",
      name: "usingEmpIds",
    },
  },
]);
const physicalSelected = ref([]);
const ledgerSelected = ref([]);
const ledgerSortFields = ref([]);
const physicalSortFields = ref([]);

const handleLedgerSelectionChange = (selection) => {
  ledgerSelected.value = selection.map((item) => item.id);
};
const handlePhysicalSelectionChange = (selection) => {
  physicalSelected.value = selection.map((item) => item.id);
};

const handleRelation = () => {
  if (physicalSelected.value.length === 0) {
    ElMessage.error("请选择资产");
    return;
  }
  if (ledgerSelected.value.length === 0) {
    ElMessage.error("请选择台账");
    return;
  }
  relation(physicalSelected.value, ledgerSelected.value[0]).then((response) => {
    if (response.code === 200) {
      ElMessage.success("关联成功");
      filter.value = { ...filter.value };
      physicalSelected.value = [];
      ledgerSelected.value = [];
    } else {
      ElMessage.error(response.msg);
    }
  });
};

const handlePhysicalOrderChange = (order) => {
  // filter.value = {
  //   ...filter.value,
  //   sortFields: order,
  // };
  physicalSortFields.value = order;
  globalFields.value = order;
  if (globalOrder.value) {
    ledgerSortFields.value = order;
  }
  // globalFields.value = order;
};

// 响应式高度计算
const calculateTableHeight = () => {
  const windowHeight = window.innerHeight;
  // 减去页面其他元素高度（头部、搜索栏、分页等）
  const otherElementsHeight = 376; // 根据实际情况调整
  tableHeight.value = `${windowHeight - otherElementsHeight}px`;
};

const queryParams = ref({
  deptId: null,
  projectId: null,
});

/** 搜索按钮操作 */
function handleQuery() {
  filter.value = {
    ...queryParams.value,
  };
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 查询单位下拉树结构 */
function getDeptTree() {
  deptTreeNoAuthSelect().then((response) => {
    enabledDeptOptions.value = filterDisabledDept(
      JSON.parse(JSON.stringify(response.data))
    );
  });
}

/** 过滤禁用的单位 */
function filterDisabledDept(deptList) {
  return deptList.filter((dept) => {
    if (dept.disabled) {
      return false;
    }
    if (dept.children && dept.children.length) {
      dept.children = filterDisabledDept(dept.children);
    }
    return true;
  });
}

function handleTreeNodeClick(data) {
  getProject(data);
}
function getProject(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    projectOptions.value = []; // 清空选项
    queryParams.value.projectId = null; // 重置
    return;
  }
  listAllProject(deptId).then((response) => {
    projectOptions.value = response.data;
    queryParams.value.projectId = null;
  });
}

const handleGlobalOrderChange = (value) => {
  globalOrder.value = value;
  handlePhysicalOrderChange(globalFields.value);
};

onMounted(() => {
  getDeptTree();
  calculateTableHeight();
  window.addEventListener("resize", calculateTableHeight);
});
onUnmounted(() => {
  window.removeEventListener("resize", calculateTableHeight);
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
</style>
