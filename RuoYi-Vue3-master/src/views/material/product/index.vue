<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类id" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入分类id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌id" prop="brandId">
        <el-input
          v-model="queryParams.brandId"
          placeholder="请输入品牌id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物资名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入物资名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="specification">
        <el-input
          v-model="queryParams.specification"
          placeholder="请输入规格型号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物资主图片" prop="mainImageUrl">
        <el-input
          v-model="queryParams.mainImageUrl"
          placeholder="请输入物资主图片"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物资图片" prop="imageUrl">
        <el-input
          v-model="queryParams.imageUrl"
          placeholder="请输入物资图片"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入是否删除"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人id" prop="createdById">
        <el-input
          v-model="queryParams.createdById"
          placeholder="请输入创建人id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createdByName">
        <el-input
          v-model="queryParams.createdByName"
          placeholder="请输入创建人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新人id" prop="updatedById">
        <el-input
          v-model="queryParams.updatedById"
          placeholder="请输入更新人id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updatedByName">
        <el-input
          v-model="queryParams.updatedByName"
          placeholder="请输入更新人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable
          v-model="queryParams.updatedTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['business:material:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:material:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:material:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:material:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id;主键id" align="center" prop="id" />
      <el-table-column label="分类id" align="center" prop="categoryId" />
      <el-table-column label="品牌id" align="center" prop="brandId" />
      <el-table-column label="物资名称" align="center" prop="materialName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="物资主图片" align="center" prop="mainImageUrl" />
      <el-table-column label="物资图片" align="center" prop="imageUrl" />
      <el-table-column label="是否删除" align="center" prop="deleteFlag" />
      <el-table-column label="创建人id" align="center" prop="createdById" />
      <el-table-column label="创建人" align="center" prop="createdByName" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人id" align="center" prop="updatedById" />
      <el-table-column label="更新人" align="center" prop="updatedByName" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['business:material:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['business:material:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物资对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="materialRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类id" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类id" />
        </el-form-item>
        <el-form-item label="品牌id" prop="brandId">
          <el-input v-model="form.brandId" placeholder="请输入品牌id" />
        </el-form-item>
        <el-form-item label="物资名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物资名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="specification">
          <el-input v-model="form.specification" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="物资主图片" prop="mainImageUrl">
          <el-input v-model="form.mainImageUrl" placeholder="请输入物资主图片" />
        </el-form-item>
        <el-form-item label="物资图片" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入物资图片" />
        </el-form-item>
        <el-form-item label="是否删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入是否删除" />
        </el-form-item>
        <el-form-item label="创建人id" prop="createdById">
          <el-input v-model="form.createdById" placeholder="请输入创建人id" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdByName">
          <el-input v-model="form.createdByName" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人id" prop="updatedById">
          <el-input v-model="form.updatedById" placeholder="请输入更新人id" />
        </el-form-item>
        <el-form-item label="更新人" prop="updatedByName">
          <el-input v-model="form.updatedByName" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable
            v-model="form.updatedTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择更新时间">
          </el-date-picker>
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

<script setup name="Material">
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial } from "@/api/business/material"

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

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
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
  proxy.$modal.confirm('是否确认删除物资编号为"' + _ids + '"的数据项？').then(function() {
    return delMaterial(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/material/export', {
    ...queryParams.value
  }, `material_${new Date().getTime()}.xlsx`)
}

getList()
</script>
