<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="归属单位" prop="deptId">
        <el-tree-select v-model="queryParams.deptId" :data="enabledDeptOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }" value-key="id" placeholder="请选择归属单位"
          style="width: 240px" clearable @change="handleTreeNodeClick" filterable />
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
        <el-select v-model="queryParams.projectId" placeholder="请选择" style="width: 240px" clearable>
          <el-option v-for="item in projectOptions" :key="item.id" :label="item.projectName"
            :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="临时编码" prop="searchTemporaryCode">
        <el-input v-model="queryParams.searchTemporaryCode" placeholder="请输入临时编码" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="原始编码" prop="searchOriginalCode">
        <el-input v-model="queryParams.searchOriginalCode" placeholder="请输入原始编码" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="门类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择" style="width: 240px" clearable >
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
            :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌名称" prop="searchBrandName">
        <el-input v-model="queryParams.searchBrandName" placeholder="请输入品牌名称" clearable @keyup.enter="handleQuery"
          style="width: 240px" />
      </el-form-item>
      <el-form-item label="资产名称" prop="searchAssetName">
        <el-input v-model="queryParams.searchAssetName" placeholder="请输入资产名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="规格型号" prop="searchSpecification">
        <el-input v-model="queryParams.searchSpecification" placeholder="请输入规格型号" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select v-model="queryParams.assetStatus" placeholder="资产状态" clearable style="width: 240px">
          <el-option v-for="dict in asset_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="地点名称" prop="searchLocationName">
        <el-input v-model="queryParams.searchLocationName" placeholder="请输入地点名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="管理部门" prop="searchManagedDeptName">
        <el-input v-model="queryParams.searchManagedDeptName" placeholder="请输入管理部门名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="使用部门" prop="searchUsingDeptName">
        <el-input v-model="queryParams.searchUsingDeptName" placeholder="请输入使用部门名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="管理员工" prop="searchManagedEmpName">
        <el-input v-model="queryParams.searchManagedEmpName" placeholder="请输入管理员工名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="使用员工" prop="searchUsingEmpName">
        <div style="display: flex; align-items: center; gap: 12px">
          <el-input v-model="queryParams.searchUsingEmpName" placeholder="请输入使用员工名称" style="width: 190px" clearable
            @keyup.enter="handleQuery" />
          <el-checkbox v-model="queryParams.searchUsingEmpNameEmpty">
            空
          </el-checkbox>
        </div>
      </el-form-item>
      <el-form-item label="采集人" prop="searchCollectorUserName">
        <el-input v-model="queryParams.searchCollectorUserName" placeholder="请输入采集人名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="采集时间" style="width: 340px">
        <el-date-picker v-model="dateRange" value-format="YYYY-MM-DD" type="daterange" range-separator="-"
          start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="打印状态" prop="printStatus">
        <el-select v-model="queryParams.printStatus" placeholder="打印状态" clearable style="width: 240px">
          <el-option v-for="dict in asset_print_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联状态" prop="relationalStatus">
        <el-select v-model="queryParams.relationalStatus" placeholder="关联状态" clearable style="width: 240px">
          <el-option v-for="dict in relational_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:asset:remove']">删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:verify:edit']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetList" @selection-change="handleSelectionChange" border resizable>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属单位" align="center" prop="deptName" />
      <el-table-column label="项目" align="center" prop="projectName" />
      <el-table-column label="临时编码" align="center" prop="temporaryCode" />
      <el-table-column label="原始编码" align="center" prop="originalCode" />
      <el-table-column label="原始子编码" align="center" prop="originalSubCode" />
      <el-table-column label="分类名称" align="center" prop="categoryName" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="生产时间" align="center" prop="productionTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.productionTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地点名称" align="center" prop="locationName" />
      <el-table-column label="管理部门名称" align="center" prop="managedDeptName" />
      <el-table-column label="使用部门名称" align="center" prop="usingDeptName" />
      <el-table-column label="管理员工名称" align="center" prop="managedEmpName" />
      <el-table-column label="使用员工名称" align="center" prop="usingEmpName" />
      <el-table-column label="采集人名称" align="center" prop="collectorUserName" />
      <el-table-column label="采集时间" align="center" prop="collectorTime">
        <template #default="scope">
          <span>{{
            parseTime(scope.row.collectorTime, "{y}-{m}-{d} {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="主图片" align="center" prop="mainImageUrl">
        <template #default="scope">
          <div v-if="scope.row.mainImageUrl">
            <el-image :src="normalizeImageUrl(scope.row.mainImageUrl)" :alt="scope.row.mainImageName"
              :preview-src-list="[normalizeImageUrl(scope.row.mainImageUrl)]" preview-teleported style="
                width: 50px;
                height: 50px;
                object-fit: cover;
                cursor: pointer;
              " hide-on-click-modal />
          </div>
          <div v-else>无</div>
        </template>
      </el-table-column>
      <el-table-column label="资产状态" align="center" prop="assetStatus">
        <template #default="scope">
          <dict-tag :options="asset_status" :value="scope.row.assetStatus" />
        </template>
      </el-table-column>
      <el-table-column label="打印状态" align="center" prop="printStatus">
        <template #default="scope">
          <dict-tag :options="asset_print_status" :value="scope.row.printStatus" />
        </template>
      </el-table-column>
      <el-table-column label="获得时间" align="center" prop="obtainTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.obtainTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品价格" align="center" prop="productPrice" />
      <el-table-column label="获得时间" align="center" prop="obtainTimeDay" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="累计折旧" align="center" prop="accumulatedDepreciation" />
      <el-table-column label="净值" align="center" prop="carryingValue" />
      <el-table-column label="关联状态" align="center" prop="relationalStatus">
        <template #default="scope">
          <dict-tag :options="relational_status" :value="scope.row.relationalStatus" />
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedTime">
        <template #default="scope">
          <span>{{
            parseTime(scope.row.updatedTime, "{y}-{m}-{d} {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <!-- <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:asset:edit']">修改</el-button> -->
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:asset:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

  </div>
</template>

<script setup name="Asset">
import { listAsset, getAsset, delAsset, addAsset, updateAsset } from "@/api/business/verifyAsset"
import { deptTreeNoAuthSelect } from "@/api/system/user";
import { listAllProject } from "@/api/business/project";
import { listAllCategory } from "@/api/business/category";

const { proxy } = getCurrentInstance()
const { asset_status } = proxy.useDict("asset_status");
const { asset_print_status } = proxy.useDict("asset_print_status");
const { relational_status } = proxy.useDict("relational_status");

const assetList = ref([])
const open = ref(false)
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const dateRange = ref([]);
const tableHeight = ref("400px");
const enabledDeptOptions = ref(undefined);
const projectOptions = ref([]);
const categoryOptions = ref([]);

// 响应式高度计算
const calculateTableHeight = () => {
  const windowHeight = window.innerHeight;
  // 减去页面其他元素高度（头部、搜索栏、分页等）
  const otherElementsHeight = 400; // 根据实际情况调整
  tableHeight.value = `${windowHeight - otherElementsHeight}px`;
};

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    deptId: null,
    projectId: null,
    searchTemporaryCode: null,
    searchOriginalCode: null,
    categoryId: null,
    searchBrandName: null,
    searchAssetName: null,
    searchSpecification: null,
    assetStatus: null,
    searchLocationName: null,
    searchManagedDeptName: null,
    searchUsingDeptName: null,
    searchManagedEmpName: null,
    searchUsingEmpName: null,
    searchCollectorUserName: null,
    searchUsingEmpNameEmpty: null,
    printStatus: null,
    relationalStatus: null,
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询核实资产列表 */
function getList() {
  loading.value = true
  listAsset(proxy.addDateRange(queryParams.value, dateRange.value)).then(response => {
    assetList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    categoryId: null,
    categoryName: null,
    brandName: null,
    assetName: null,
    specification: null,
    productionTime: null,
    locationId: null,
    locationName: null,
    managedDeptId: null,
    managedDeptName: null,
    usingDeptId: null,
    usingDeptName: null,
    managedEmpId: null,
    managedEmpName: null,
    usingEmpId: null,
    usingEmpName: null,
    collectorUserId: null,
    collectorUserName: null,
    collectorTime: null,
    remark: null,
    mainImageUrl: null,
    mainImageName: null,
    imageUrl: null,
    imageUrlName: null,
    assetStatus: null,
    originalSubCode: null,
    printStatus: null,
    obtainTime: null,
    productPrice: null,
    obtainTimeDay: null,
    quantity: null,
    accumulatedDepreciation: null,
    carryingValue: null,
    relationalStatus: null
  }
  proxy.resetForm("assetRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 节点单击事件 */
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
    queryParams.projectId = null; // 重置
    return;
  }
  listAllProject(deptId).then((response) => {
    projectOptions.value = response.data;
    queryParams.projectId = null;
  });
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加核实资产"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAsset(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改核实资产"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["assetRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateAsset(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addAsset(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除核实资产编号为"' + _ids + '"的数据项？').then(function () {
    return delAsset(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('/verifyAsset/export', {
    ...queryParams.value
  }, `核实资产_${new Date().getTime()}.xlsx`)
}
/** 查询单位下拉树结构 */
function getDeptTree() {
  deptTreeNoAuthSelect().then((response) => {
    enabledDeptOptions.value = filterDisabledDept(
      JSON.parse(JSON.stringify(response.data))
    );
  });
}
function getCategory() {
  listAllCategory().then((response) => {
    categoryOptions.value = response.data;
  });
}

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (
    (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
  );
};

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


onMounted(() => {
  getDeptTree();
  getCategory();
  // getList();
  calculateTableHeight();
  window.addEventListener("resize", calculateTableHeight);
});

onUnmounted(() => {
  window.removeEventListener("resize", calculateTableHeight);
});
</script>
