<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" multiple placeholder="请选择">
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName" :value="item.id"
            style="width: 240px"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌名称" prop="brandName">
        <el-input v-model="queryParams.brandName" placeholder="请输入品牌名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['business:brand:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:brand:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:brand:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:brand:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="brandList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类id" align="center" prop="categoryId" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:brand:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:brand:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改品牌对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="brandRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类id" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类id" />
        </el-form-item>
        <el-form-item label="品牌名称" prop="brandName">
          <el-input v-model="form.brandName" placeholder="请输入品牌名称" />
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

<script setup name="Brand">
import { listBrand, getBrand, delBrand, addBrand, updateBrand } from "@/api/business/brand"
import { listCategory } from "@/api/business/catrgory"

const { proxy } = getCurrentInstance()

const brandList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const categoryOptions = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryId: null,
    brandName: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询品牌列表 */
function getList() {
  loading.value = true
  listBrand(queryParams.value).then(response => {
    brandList.value = response.rows
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
    categoryId: null,
    brandName: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("brandRef")
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
  title.value = "添加品牌"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getBrand(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改品牌"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["brandRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateBrand(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addBrand(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除品牌编号为"' + _ids + '"的数据项？').then(function () {
    return delBrand(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/brand/export', {
    ...queryParams.value
  }, `brand_${new Date().getTime()}.xlsx`)
}

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
}

function getCategory() {
  listCategory().then(response => {
    categoryOptions.value = response.data
  })
}
onMounted(() => {
  getList()
  getCategory()
})
</script>
