<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="queryParams.categoryName" placeholder="请输入分类名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="品牌" prop="hasBrand">
        <el-select v-model="queryParams.hasBrand" placeholder="请选择" style="width: 240px" clearable>
          <el-option key="1" label="必填" value="1"></el-option>
          <el-option key="0" label="非必填" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物资名称" prop="hasMaterialName">
        <el-select v-model="queryParams.hasMaterialName" placeholder="请选择" style="width: 240px" clearable>
          <el-option key="1" label="必填" value="1"></el-option>
          <el-option key="0" label="非必填" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="规格型号" prop="hasSpecification">
        <el-select v-model="queryParams.hasSpecification" placeholder="请选择" style="width: 240px" clearable>
          <el-option key="1" label="必填" value="1"></el-option>
          <el-option key="0" label="非必填" value="0"></el-option>
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
          v-hasPermi="['business:category:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:category:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:category:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:category:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoryList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <!-- 序号列优化 -->
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类名称" align="center" prop="categoryName" />
      <el-table-column label="是否填写品牌" align="center" prop="hasBrand">
        <template #default="scope">
          <el-switch v-model="scope.row.hasBrand" :active-value="true" :inactive-value="false" disabled>
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否填写物资名称" align="center" prop="hasMaterialName">
        <template #default="scope">
          <el-switch v-model="scope.row.hasMaterialName" :active-value="true" :inactive-value="false" disabled>
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否填写规格型号" align="center" prop="hasSpecification">
        <template #default="scope">
          <el-switch v-model="scope.row.hasSpecification" :active-value="true" :inactive-value="false" disabled>
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:category:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:category:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="categoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="是否填写品牌" prop="hasBrand">
          <el-switch v-model="form.hasBrand" :active-value="true" :inactive-value="false" active-text="是"
            inactive-text="否">
          </el-switch>
        </el-form-item>
        <el-form-item label="是否填写物资名称" prop="hasMaterialName">
          <el-switch v-model="form.hasMaterialName" :active-value="true" :inactive-value="false" active-text="是"
            inactive-text="否">
          </el-switch>
        </el-form-item>
        <el-form-item label="是否填写规格型号" prop="hasSpecification">
          <el-switch v-model="form.hasSpecification" :active-value="true" :inactive-value="false" active-text="是"
            inactive-text="否">
          </el-switch>
        </el-form-item>
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

<script setup name="Category">
import { listCategory, getCategory, delCategory, addCategory, updateCategory } from "@/api/business/category"

const { proxy } = getCurrentInstance()

const categoryList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryName: null,
    hasBrand: null,
    hasMaterialName: null,
    hasSpecification: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询分类列表 */
function getList() {
  loading.value = true
  listCategory(queryParams.value).then(response => {
    categoryList.value = response.rows
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
    categoryName: null,
    hasBrand: null,
    hasMaterialName: null,
    hasSpecification: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("categoryRef")
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
  title.value = "添加分类"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getCategory(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改分类"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["categoryRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCategory(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCategory(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除分类编号为"' + _ids + '"的数据项？').then(function () {
    return delCategory(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/category/export', {
    ...queryParams.value
  }, `category_${new Date().getTime()}.xlsx`)
}

getList()

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
}
</script>
