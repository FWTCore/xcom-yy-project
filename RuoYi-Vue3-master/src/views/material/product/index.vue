<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择" style="width: 240px" clearable
          @change="handleNodeClick">
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName"
            :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="brandId">
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
      <el-form-item label="物资名称" prop="searchMaterialName">
        <el-input v-model="queryParams.searchMaterialName" placeholder="请输入物资名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="规格型号" prop="searchSpecification">
        <el-input v-model="queryParams.searchSpecification" placeholder="请输入规格型号" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['business:material:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:material:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:material:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['business:material:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="categoryName" />
      <el-table-column label="品牌" align="center" prop="brandName" />
      <el-table-column label="物资名称" align="center" prop="materialName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="物资图片" align="center" prop="mainImageUrl">
        <template #default="scope">
          <div v-if="scope.row.mainImageUrl">
            <img :src="scope.row.mainImageUrl" :alt="scope.row.mainImageName"
              style="width: 50px; height: 50px; object-fit: cover;" />
          </div>
          <div v-else>无</div>
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
            v-hasPermi="['business:material:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:material:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改物资对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="materialRef" :model="form" :rules="rules" label-width="80px">
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
            <el-form-item label="物资名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入物资名称" />
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
            <el-form-item label="物资图片" prop="mainImageUrl">
              <template>
                <uploadImage v-model="form.mainImageUrl" :default-image="form.mainImageUrl" />
              </template>
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

<script setup name="Material">
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial } from "@/api/business/material"
import { listAllCategory } from "@/api/business/category"
import { listAllBrand } from "@/api/business/brand"
import uploadImage from "@/views/common/uploadImage"

const { proxy } = getCurrentInstance()

const materialList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const categoryOptions = ref([])
const brandOptions = ref([])
const brandFormOptions = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryId: null,
    brandId: null,
    searchMaterialName: null,
    searchSpecification: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询物资列表 */
function getList() {
  loading.value = true
  listMaterial(queryParams.value).then(response => {
    materialList.value = response.rows
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
    brandId: null,
    materialName: null,
    specification: null,
    mainImageUrl: null,
    imageUrl: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  brandFormOptions.value = []
  proxy.resetForm("materialRef")
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
  title.value = "添加物资"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getMaterial(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改物资"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["materialRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMaterial(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addMaterial(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除物资编号为"' + _ids + '"的数据项？').then(function () {
    return delMaterial(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/material/export', {
    ...queryParams.value
  }, `material_${new Date().getTime()}.xlsx`)
}


// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
}

function handleNodeClick(data) {
  getBrand(data)
}

function getCategory() {
  listAllCategory().then(response => {
    categoryOptions.value = response.data
  })
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


onMounted(() => {
  getList()
  getCategory()
})
</script>