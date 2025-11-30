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
      </el-form-item>
    </el-form>
    <StaticTitle title="概要信息" />

    <el-row class="statistic-container">
      <StaticItem label="总数" :value="statisticData.totalCount" />
      <StaticItem label="核对数" :value="statisticData.checkCount" />
    </el-row>

    <StaticTitle title="指标统计" />

    <el-row class="statistic-container" :gutter="10">
      <StaticTable
        title="资产名称统计(top10)"
        name="名称"
        :filter="{
          ...filter,
          metricsType: 1,
        }"
        :handler="ledgerMetrics"
        :tableHeight="tableHeight"
      />
      <StaticTable
        title="使用部门统计(top10)"
        name="使用部门"
        :filter="{
          ...filter,
          metricsType: 2,
        }"
        :handler="ledgerMetrics"
        :tableHeight="tableHeight"
      />
      <StaticTable
        title="存放地点统计(top10)"
        name="存放地点"
        :filter="{
          ...filter,
          metricsType: 3,
        }"
        :handler="ledgerMetrics"
        :tableHeight="tableHeight"
      />
    </el-row>
  </div>
</template>

<script setup name="Project">
import { listAllProject } from "@/api/business/project";
import StaticTitle from "@/components/StaticTitle/index.vue";
import StaticItem from "@/components/StaticItem/index.vue";
import StaticTable from "@/components/StaticTable/index.vue";
import { deptTreeNoAuthSelect } from "@/api/system/user";
import { ledgerMetrics, ledgerOverView } from "../../../api/statistic/ledger";

const { proxy } = getCurrentInstance();
const projectOptions = ref([]);
const showSearch = ref(true);
const enabledDeptOptions = ref(undefined);
const tableHeight = ref("400px");
const filter = ref({});
const statisticData = ref({
  totalCount: 0,
  checkCount: 0,
});

// 响应式高度计算
const calculateTableHeight = () => {
  const windowHeight = window.innerHeight;
  // 减去页面其他元素高度（头部、搜索栏、分页等）
  const otherElementsHeight = 484; // 根据实际情况调整
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
  queryParams.value.projectId = null;
}

function resetProject() {
  projectOptions.value = []; // 清空选项
  queryParams.value.projectId = null; // 重置
}
function getProject(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    resetProject(); // 重置
    return;
  }
  listAllProject(deptId).then((response) => {
    projectOptions.value = response.data;
    queryParams.value.projectId = null;
  });
}

const getOverview = () => {
  if (!queryParams.value.deptId || !queryParams.value.projectId) {
    statisticData.value = {
      totalCount: 0,
      checkCount: 0,
    };
    return;
  }
  ledgerOverView(queryParams.value).then((response) => {
    statisticData.value = response.data;
  });
};

watch(
  () => filter.value,
  (newVal, oldVal) => {
    getOverview();
  }
);

onMounted(() => {
  getDeptTree();
  getOverview();
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
</style>
