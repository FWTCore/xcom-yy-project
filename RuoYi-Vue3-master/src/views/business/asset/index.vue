<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="归属公司" prop="deptId">
        <el-tree-select v-model="queryParams.deptId" :data="enabledDeptOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }" value-key="id" placeholder="请选择归属公司"
          style="width: 240px" clearable @change="handleTreeNodeClick" />
      </el-form-item>
      <el-form-item prop="projectId">
         <template #label>
          <span>
            <el-tooltip content="需要先选择公司" placement="top">
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
      <el-form-item label="临时编码" prop="temporaryCode">
        <el-input v-model="queryParams.temporaryCode" placeholder="请输入临时编码" style="width: 240px" clearable  @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="原始编码" prop="originalCode">
        <el-input v-model="queryParams.originalCode" placeholder="请输入原始编码" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
         <el-select v-model="queryParams.categoryId" placeholder="请选择" style="width: 240px" clearable
          @change="handleNodeClick">
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
            :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌id" prop="brandId">
        <template #label>
          <span>
            <el-tooltip content="需要先选择分类" placement="top">
              <el-icon><question-filled /></el-icon>
            </el-tooltip>
            品牌
          </span>
        </template>
        <el-select v-model="queryParams.brandId" placeholder="请选择" style="width: 240px" clearable>
          <el-option v-for="item in brandOptions" :key="item.id" :label="item.brandName" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input v-model="queryParams.assetName" placeholder="请输入资产名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="规格型号" prop="specification">
        <el-input v-model="queryParams.specification" placeholder="请输入规格型号" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="地点名称" prop="locationName">
        <el-input v-model="queryParams.locationName" placeholder="请输入地点名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="管理部门" prop="managedDeptName">
        <el-input v-model="queryParams.managedDeptName" placeholder="请输入管理部门名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="使用部门" prop="usingDeptName">
        <el-input v-model="queryParams.usingDeptName" placeholder="请输入使用部门名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="管理员工" prop="managedEmpName">
        <el-input v-model="queryParams.managedEmpName" placeholder="请输入管理员工名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="使用员工" prop="usingEmpName">
        <el-input v-model="queryParams.usingEmpName" placeholder="请输入使用员工名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="采集人" prop="collectorUserName">
        <el-input v-model="queryParams.collectorUserName" placeholder="请输入采集人名称" style="width: 240px" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="采集时间" prop="collectorTime">
        <el-date-picker clearable v-model="queryParams.collectorTime" type="date" style="width: 240px" value-format="YYYY-MM-DD"
          placeholder="请选择采集时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:asset:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:asset:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:asset:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:asset:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属公司" align="center" prop="deptName" />
      <el-table-column label="项目" align="center" prop="projectName" />
      <el-table-column label="临时编码" align="center" prop="temporaryCode" />
      <el-table-column label="原始编码" align="center" prop="originalCode" />
      <el-table-column label="分类名称" align="center" prop="categoryName" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="生产时间" align="center" prop="productionTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.productionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地点名称" align="center" prop="locationName" />
      <el-table-column label="管理部门名称" align="center" prop="managedDeptName" />
      <el-table-column label="使用部门名称" align="center" prop="usingDeptName" />
      <el-table-column label="管理员工名称" align="center" prop="managedEmpName" />
      <el-table-column label="使用员工名称" align="center" prop="usingEmpName" />
      <el-table-column label="采集人名称" align="center" prop="collectorUserName" />
      <el-table-column label="采集时间" align="center" prop="collectorTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.collectorTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="主图片" align="center" prop="mainImageUrl" />
      <el-table-column label="主图片名称" align="center" prop="mainImageName" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:asset:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:asset:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改资产对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="assetRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属公司" prop="deptId">
              <el-tree-select v-model="form.deptId" :data="enabledDeptOptions"
                :props="{ value: 'id', label: 'label', children: 'children' }" value-key="id" placeholder="请选择归属公司"
                clearable check-strictly />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-select v-model="form.projectId" placeholder="请选择" style="width: 240px" clearable>
              <el-option v-for="item in projectOptions" :key="item.id" :label="item.projectName"
                :value="item.id"></el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="临时编码" prop="temporaryCode">
              <el-input v-model="form.temporaryCode" placeholder="请输入临时编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="原始编码" prop="originalCode">
              <el-input v-model="form.originalCode" placeholder="请输入原始编码" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="分类" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="请选择" style="width: 240px" clearable
                @change="handleNodeClickForForm">
                <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item prop="brandId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择分类" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  品牌
                </span>
              </template>
              <el-select v-model="form.brandId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in brandFormOptions" :key="item.id" :label="item.brandName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="资产名称" prop="assetName">
              <el-input v-model="form.assetName" placeholder="请输入资产名称" />
            </el-form-item>

          </el-col>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="specification">
              <el-input v-model="form.specification" placeholder="请输入规格型号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产时间" prop="productionTime">
              <el-date-picker clearable v-model="form.productionTime" type="date" value-format="YYYY-MM-DD"
                placeholder="请选择生产时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12"><el-form-item label="地点id" prop="locationId">
              <el-input v-model="form.locationId" placeholder="请输入地点id" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="管理部门id" prop="managedDeptId">
              <el-input v-model="form.managedDeptId" placeholder="请输入管理部门id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="使用部门id" prop="usingDeptId">
              <el-input v-model="form.usingDeptId" placeholder="请输入使用部门id" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="管理员工id" prop="managedEmpId">
              <el-input v-model="form.managedEmpId" placeholder="请输入管理员工id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="使用员工id" prop="usingEmpId">
              <el-input v-model="form.usingEmpId" placeholder="请输入使用员工id" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="主图片" prop="mainImageUrl">
              <el-input v-model="form.mainImageUrl" placeholder="请输入主图片" />
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="图片" prop="imageUrl">
              <el-input v-model="form.imageUrl" placeholder="请输入图片" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Asset">
import { listAsset, getAsset, delAsset, addAsset, updateAsset } from "@/api/business/asset"
import { deptTreeSelect } from "@/api/system/user"
import { listAllCategory } from "@/api/business/category"
import { listAllBrand } from "@/api/business/brand"
import { listAllProject } from "@/api/business/project"

const { proxy } = getCurrentInstance()

const assetList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const enabledDeptOptions = ref(undefined)
const categoryOptions = ref([])
const brandOptions = ref([])
const projectOptions = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    categoryId: null,
    brandId: null,
    assetName: null,
    specification: null,
    productionTime: null,
    locationName: null,
    managedDeptName: null,
    usingDeptName: null,
    managedEmpName: null,
    usingEmpName: null,
    collectorUserName: null,
    collectorTime: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询资产列表 */
function getList() {
  loading.value = true
  listAsset(queryParams.value).then(response => {
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
    brandId: null,
    brandName: null,
    materialId: null,
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
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
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

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加资产"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAsset(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改资产"
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
  proxy.$modal.confirm('是否确认删除资产编号为"' + _ids + '"的数据项？').then(function () {
    return delAsset(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/asset/export', {
    ...queryParams.value
  }, `asset_${new Date().getTime()}.xlsx`)
}


/** 查询公司下拉树结构 */
function getDeptTree() {
  deptTreeSelect().then(response => {
    enabledDeptOptions.value = filterDisabledDept(JSON.parse(JSON.stringify(response.data)))
  })
}

/** 过滤禁用的公司 */
function filterDisabledDept(deptList) {
  return deptList.filter(dept => {
    if (dept.disabled) {
      return false
    }
    if (dept.children && dept.children.length) {
      dept.children = filterDisabledDept(dept.children)
    }
    return true
  })
}

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
}

function getCategory() {
  listAllCategory().then(response => {
    categoryOptions.value = response.data
  })
}
function handleNodeClick(data) {
  getBrand(data)
}

function getBrand(categoryId) {
  if (categoryId === null || categoryId === undefined || categoryId === '' || Number.isNaN(categoryId)) {
    brandOptions.value = [] // 清空品牌选项
    queryParams.brandId = null // 重置已选品牌
    return
  }
  listAllBrand(categoryId).then(response => {
    brandOptions.value = response.data
    queryParams.brandId = null
  })
}

function handleNodeClickForForm(data) {
  getFormBrand(data)
}


function getFormBrand(categoryId) {
  if (categoryId === null || categoryId === undefined || categoryId === '' || Number.isNaN(categoryId)) {
    brandFormOptions.value = [] // 清空品牌选项
    form.brandId = null // 重置已选品牌
    return
  }
  listAllBrand(categoryId).then(response => {
    brandFormOptions.value = response.data
    form.brandId = null
  })
}

/** 节点单击事件 */
function handleTreeNodeClick(data) {
  getProject(data)
}
function getProject(projectId) {
   if (projectId === null || projectId === undefined || projectId === '' || Number.isNaN(projectId)) {
    projectOptions.value = [] // 清空选项
    queryParams.projectId = null // 重置
    return
  }
  listAllProject(projectId).then(response => {
    projectOptions.value = response.data
     queryParams.brandId = null
  })
}

onMounted(() => {
  getDeptTree()
  getCategory()
  getList()
})
</script>
