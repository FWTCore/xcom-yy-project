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
      <el-form-item label="原始编码" prop="searchOriginalCode">
        <el-input v-model="queryParams.searchOriginalCode" placeholder="请输入原始编码" clearable @keyup.enter="handleQuery"
          style="width: 240px" />
      </el-form-item>
      <el-form-item label="门类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择" style="width: 240px" clearable>
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
            :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌名称" prop="searchBrandName">
        <el-input v-model="queryParams.searchBrandName" placeholder="请输入品牌名称" clearable @keyup.enter="handleQuery"
          style="width: 240px" />
      </el-form-item>
      <el-form-item label="资产名称" prop="searchAssetName">
        <el-input v-model="queryParams.searchAssetName" placeholder="请输入资产名称" clearable @keyup.enter="handleQuery"
          style="width: 240px" />
      </el-form-item>
      <el-form-item label="规格型号" prop="searchSpecification">
        <el-input v-model="queryParams.searchSpecification" placeholder="请输入规格型号" clearable @keyup.enter="handleQuery"
          style="width: 240px" />
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
        <el-input v-model="queryParams.searchUsingEmpName" placeholder="请输入使用员工名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="关联状态" prop="matchStatus">
        <el-select v-model="queryParams.matchStatus" placeholder="关联状态" clearable style="width: 240px">
          <el-option v-for="dict in original_asset_match_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['business:original_asset:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:original_asset:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:original_asset:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:original_asset:export']">导出</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport"
          v-hasPermi="['business:original_asset:import']">导入</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="originalAssetList" :height="tableHeight"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属单位" align="center" prop="deptName" />
      <el-table-column label="项目" align="center" prop="projectName" />
      <el-table-column label="原始编码" align="center" prop="originalCode" />
      <el-table-column label="门类名称" align="center" prop="categoryName" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="获得时间" align="center" prop="obtainTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.obtainTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="价值" align="center" prop="productPrice" />
      <el-table-column label="地点名称" align="center" prop="locationName" />
      <el-table-column label="管理部门名称" align="center" prop="managedDeptName" />
      <el-table-column label="使用部门名称" align="center" prop="usingDeptName" />
      <el-table-column label="管理员工名称" align="center" prop="managedEmpName" />
      <el-table-column label="使用员工名称" align="center" prop="usingEmpName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="关联状态" align="center" prop="matchStatus">
        <template #default="scope">
          <dict-tag :options="original_asset_match_status" :value="scope.row.matchStatus" />
        </template>
      </el-table-column>
      <el-table-column label="匹配数量" align="center" prop="matchCount" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:original_asset:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:original_asset:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" :pageSizes="[10, 20, 30, 50, 500]"/>

    <!-- 添加或修改原始资产对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="originalAssetRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属单位" prop="deptId">
              <el-tree-select v-model="form.deptId" :data="enabledDeptOptions"
                :props="{ value: 'id', label: 'label', children: 'children' }" value-key="id" placeholder="请选择归属单位"
                check-strictly @change="handleTreeNodeFormClick" filterable clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="projectId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  项目
                </span>
              </template>
              <el-select v-model="form.projectId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in projectFormOptions" :key="item.id" :label="item.projectName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="原始编码" prop="originalCode">
              <el-input v-model="form.originalCode" placeholder="请输入原始编码" :disabled="!!form.id" clearable />
            </el-form-item>
          </el-col>

          <el-col :span="12">

            <el-form-item label="资产名称" prop="assetName">
              <el-input v-model="form.assetName" placeholder="请输入资产名称" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="门类" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品牌名称" prop="brandName">
              <el-input v-model="form.brandName" placeholder="请输入品牌名称" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="specification">
              <el-input v-model="form.specification" placeholder="请输入规格型号" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="获得时间" prop="obtainTime">
              <el-date-picker v-model="form.obtainTime" type="date" value-format="YYYY-MM-DD" placeholder="请选择获得时间"
                clearable>
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="价值" prop="productPrice">
              <el-input-number v-model="form.productPrice" placeholder="请输入价值" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="locationId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  地点
                </span>
              </template>
              <el-select v-model="form.locationId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in locationFormOptions" :key="item.id" :label="item.locationName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理部门
                </span>
              </template>
              <el-select v-model="form.managedDeptId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in deptFormOptions" :key="item.id" :label="item.departmentName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用部门
                </span>
              </template>
              <el-select v-model="form.usingDeptId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in deptFormOptions" :key="item.id" :label="item.departmentName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理员工
                </span>
              </template>
              <el-select v-model="form.managedEmpId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in empFormOptions" :key="item.id" :label="item.employeeName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用员工
                </span>
              </template>
              <el-select v-model="form.usingEmpId" placeholder="请选择" style="width: 240px" clearable>
                <el-option v-for="item in empFormOptions" :key="item.id" :label="item.employeeName"
                  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" clearable></el-input>
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

    <!-- 导入对话框 -->
    <el-dialog :title="upload.title" v-model="upload.open" width="400px" append-to-body>
      <el-upload ref="uploadRef" :limit="1" accept=".xlsx, .xls" :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :on-change="handleFileChange"
        :on-remove="handleFileRemove" :auto-upload="false" drag>
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <template #tip>
          <div class="el-upload__tip text-center">
            <div class="el-upload__tip">
              <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的数据
            </div>
            <span>仅允许导入xls、xlsx格式文件。</span>
            <el-link type="primary" :underline="false" style="font-size: 12px; vertical-align: baseline"
              @click="importTemplate">下载模板</el-link>
          </div>
        </template>
      </el-upload>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitFileForm">确 定</el-button>
          <el-button @click="upload.open = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="OriginalAsset">
import { getToken } from "@/utils/auth"
import { listOriginalAsset, getOriginalAsset, delOriginalAsset, addOriginalAsset, updateOriginalAsset } from "@/api/business/originalAsset"
import { deptTreeNoAuthSelect } from "@/api/system/user"
import { listAllCategory } from "@/api/business/category"
import { listAllProject } from "@/api/business/project"
import { listAllLocation } from "@/api/business/location"
import { listAllDepartment } from "@/api/business/department"
import { listAllEmployee } from "@/api/business/employee"

const { proxy } = getCurrentInstance()
const { original_asset_match_status } = proxy.useDict("original_asset_match_status");

const originalAssetList = ref([])
const open = ref(false)
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const tableHeight = ref('400px')

// 响应式高度计算
const calculateTableHeight = () => {
  const windowHeight = window.innerHeight
  // 减去页面其他元素高度（头部、搜索栏、分页等）
  const otherElementsHeight = 400 // 根据实际情况调整
  tableHeight.value = `${windowHeight - otherElementsHeight}px`
}


const enabledDeptOptions = ref(undefined)
const categoryOptions = ref([])
const projectOptions = ref([])
const projectFormOptions = ref([])
const locationFormOptions = ref([])
const deptFormOptions = ref([])
const empFormOptions = ref([])
/*** 导入参数 */
const upload = reactive({
  // 是否显示弹出层（导入）
  open: false,
  // 弹出层标题（导入）
  title: "",
  // 是否禁用上传
  isUploading: false,
  // 是否更新已经存在的数据
  updateSupport: 0,
  // 设置上传的请求头部
  headers: { Authorization: "Bearer " + getToken() },
  // 上传的地址
  url: import.meta.env.VITE_APP_BASE_API + "/originalAsset/importData"
})

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 500,
    deptId: null,
    projectId: null,
    searchOriginalCode: null,
    categoryId: null,
    searchBrandName: null,
    searchAssetName: null,
    searchSpecification: null,
    searchLocationName: null,
    searchManagedDeptName: null,
    searchUsingDeptName: null,
    searchManagedEmpName: null,
    searchUsingEmpName: null,
    matchStatus: null,
  },
  rules: {
    deptId: [{ required: true, message: "单位不能为空", trigger: "blur" }],
    projectId: [{ required: true, message: "项目不能为空", trigger: "blur" }],
    originalCode: [{ required: true, message: "原始编码不能为空", trigger: "blur" }],
    categoryId: [{ required: true, message: "门类不能为空", trigger: "blur" }],
    assetName: [{ required: true, message: "资产名称不能为空", trigger: "blur" }],
    obtainTime: [{ required: true, message: "获得时间不能为空", trigger: "blur" }],
    productPrice: [{ required: true, message: "价值不能为空", trigger: "blur" }],
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询原始资产列表 */
function getList() {
  loading.value = true
  listOriginalAsset(queryParams.value).then(response => {
    originalAssetList.value = response.rows
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
    originalCode: null,
    categoryId: null,
    categoryName: null,
    brandName: null,
    assetName: null,
    specification: null,
    obtainTime: null,
    productPrice: null,
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
    remark: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("originalAssetRef")
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
  title.value = "添加原始资产"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getOriginalAsset(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改原始资产"
    handleTreeNodeFormClick(form.value.deptId)
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["originalAssetRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOriginalAsset(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOriginalAsset(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除原始资产编号为"' + _ids + '"的数据项？').then(function () {
    return delOriginalAsset(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/OriginalAsset/export', {
    ...queryParams.value
  }, `originalAsset_${new Date().getTime()}.xlsx`)
}


/** 查询单位下拉树结构 */
function getDeptTree() {
  deptTreeNoAuthSelect().then(response => {
    enabledDeptOptions.value = filterDisabledDept(JSON.parse(JSON.stringify(response.data)))
  })
}

/** 过滤禁用的单位 */
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

/** 节点单击事件 */
function handleTreeNodeClick(data) {
  getProject(data)
}
function getProject(deptId) {
  if (deptId === null || deptId === undefined || deptId === '' || Number.isNaN(deptId)) {
    projectOptions.value = [] // 清空选项
    queryParams.projectId = null // 重置
    return
  }
  listAllProject(deptId).then(response => {
    projectOptions.value = response.data
    queryParams.projectId = null
  })
}

/** 节点单击事件 */
function handleTreeNodeFormClick(data) {
  getFormProject(data)
  getLocation(data)
  getDepartment(data)
  getEmployee(data)
}

function getFormProject(deptId) {
  if (deptId === null || deptId === undefined || deptId === '' || Number.isNaN(deptId)) {
    projectFormOptions.value = [] // 清空选项
    form.projectId = null // 重置
    return
  }
  listAllProject(deptId).then(response => {
    projectFormOptions.value = response.data
    form.projectId = null
  })
}
function getLocation(deptId) {
  if (deptId === null || deptId === undefined || deptId === '' || Number.isNaN(deptId)) {
    locationFormOptions.value = [] // 清空选项
    form.locationId = null // 重置
    return
  }
  listAllLocation(deptId).then(response => {
    locationFormOptions.value = response.data
    form.locationId = null
  })
}
function getDepartment(deptId) {
  if (deptId === null || deptId === undefined || deptId === '' || Number.isNaN(deptId)) {
    deptFormOptions.value = [] // 清空选项
    form.managedDeptId = null // 重置
    form.usingDeptId = null // 重置
    return
  }
  listAllDepartment(deptId).then(response => {
    deptFormOptions.value = response.data
    form.managedDeptId = null
    form.usingDeptId = null
  })
}
function getEmployee(deptId) {
  if (deptId === null || deptId === undefined || deptId === '' || Number.isNaN(deptId)) {
    empFormOptions.value = [] // 清空选项
    form.managedEmpId = null // 重置
    form.usingEmpId = null // 重置
    return
  }
  listAllEmployee(deptId).then(response => {
    empFormOptions.value = response.data
    form.managedEmpId = null
    form.usingEmpId = null
  })
}

/** 导入按钮操作 */
function handleImport() {
  upload.title = "原始资产导入"
  upload.open = true
  upload.selectedFile = null
}

/** 下载模板操作 */
function importTemplate() {
  proxy.download("/originalAsset/importTemplate", {
  }, `原始资产_template_${new Date().getTime()}.xlsx`)
}

/**文件上传中处理 */
const handleFileUploadProgress = (event, file, fileList) => {
  upload.isUploading = true
}

/** 文件选择处理 */
const handleFileChange = (file, fileList) => {
  upload.selectedFile = file
}

/** 文件删除处理 */
const handleFileRemove = (file, fileList) => {
  upload.selectedFile = null
}

/** 文件上传成功处理 */
const handleFileSuccess = (response, file, fileList) => {
  upload.open = false
  upload.isUploading = false
  proxy.$refs["uploadRef"].handleRemove(file)
  proxy.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true })
  getList()
}

/** 提交上传文件 */
function submitFileForm() {
  const file = upload.selectedFile
  if (!file || file.length === 0 || !file.name.toLowerCase().endsWith('.xls') && !file.name.toLowerCase().endsWith('.xlsx')) {
    proxy.$modal.msgError("请选择后缀为 “xls”或“xlsx”的文件。")
    return
  }
  proxy.$refs["uploadRef"].submit()
}

onMounted(() => {
  getDeptTree()
  getCategory()
  // getList()
  calculateTableHeight()
  window.addEventListener('resize', calculateTableHeight)
})

onUnmounted(() => {
  window.removeEventListener('resize', calculateTableHeight)
})
</script>
