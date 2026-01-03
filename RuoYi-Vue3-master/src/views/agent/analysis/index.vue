<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="解析名称" prop="analysisName">
        <el-input v-model="queryParams.analysisName" placeholder="请输入解析名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="解析类型" prop="analysisType">
        <el-select v-model="queryParams.analysisType" placeholder="解析类型" clearable style="width: 240px">
          <el-option v-for="dict in analysis_type" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="配置状态" prop="configStatus">
        <el-select v-model="queryParams.configStatus" placeholder="配置状态" clearable style="width: 240px">
          <el-option v-for="dict in config_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:config:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:config:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:config:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:config:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="解析名称" align="center" prop="analysisName" />
      <el-table-column label="解析类型" align="center" prop="analysisType">
        <template #default="scope">
          <dict-tag :options="analysis_type" :value="scope.row.analysisType" />
        </template>
      </el-table-column>
      <el-table-column label="配置状态" align="center" prop="configStatus">
        <template #default="scope">
          <dict-tag :options="config_status" :value="scope.row.configStatus" />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">

          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:config:edit']">修改</el-button>

          <el-tooltip content="添加配置明细" placement="top">
            <el-button link type="primary" icon="Plus" @click="handleDetail(scope.row)">添加配置明细</el-button>
          </el-tooltip>

          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:config:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改解析配置;解析配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="configRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="解析名称" prop="analysisName">
          <el-input v-model="form.analysisName" placeholder="请输入解析名称" />
        </el-form-item>
        <el-form-item label="解析类型" prop="analysisType">
          <el-select v-model="form.analysisType" placeholder="解析类型" value-key="value" clearable>
            <el-option v-for="dict in analysis_type" :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="配置状态" prop="configStatus">
          <el-select v-model="form.configStatus" placeholder="状态" value-key="value" clearable>
            <el-option v-for="dict in config_status" :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
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

<script setup name="pa_analysis_config">
import { listConfig, getConfig, delConfig, addConfig, updateConfig } from "@/api/agent/config"

const { proxy } = getCurrentInstance()
const { analysis_type } = proxy.useDict("analysis_type");
const { config_status } = proxy.useDict("config_status");

const configList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const router = useRouter()

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    analysisName: null,
    analysisType: null,
    configStatus: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (
    (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
  );
};

/** 查询解析配置;解析配置列表 */
function getList() {
  loading.value = true
  listConfig(queryParams.value).then(response => {
    configList.value = response.rows
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
    analysisName: null,
    analysisType: null,
    configStatus: null,
    remark: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("configRef")
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
  title.value = "添加解析配置"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getConfig(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改解析配置"
  })
}
/** 解析配置 */
function handleDetail(row) {
  router.push("/agent/analysis-config/detail/" + row.id)
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["configRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateConfig(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addConfig(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除解析配置;解析配置编号为"' + _ids + '"的数据项？').then(function () {
    return delConfig(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/config/export', {
    ...queryParams.value
  }, `config_${new Date().getTime()}.xlsx`)
}
onMounted(() => {
  getList()
})


</script>
