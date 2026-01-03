<template>
  <div class="app-container">
    <h4 class="form-header h4">基本信息</h4>
    <el-form :model="data.form" label-width="80px">
      <el-row>
        <el-col :span="8" :offset="2">
          <el-form-item label="业务名称" prop="bizName">
            <el-input v-model="form.bizName" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <h4 class="form-header h4">配置信息</h4>
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="步骤名称" prop="stepsName">
        <el-input v-model="queryParams.stepsName" placeholder="请输入步骤名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:steps:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:steps:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:steps:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Close" @click="handleClose">关闭</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:steps:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stepsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="步骤名称" align="center" prop="stepsName" />
      <el-table-column label="步骤描述" align="center" prop="stepsDescription" />
      <el-table-column label="步骤属性" align="center" prop="stepsAttribute" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:steps:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:steps:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改业务办理步骤;业务办理步骤对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="stepsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父级id" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父级id" />
        </el-form-item>
        <el-form-item label="步骤名称" prop="stepsName">
          <el-input v-model="form.stepsName" placeholder="请输入步骤名称" />
        </el-form-item>
        <el-form-item label="步骤描述" prop="stepsDescription">
          <el-input v-model="form.stepsDescription" type="textarea" placeholder="请输入内容" />
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

<script setup name="Steps">
import { listSteps, getSteps, delSteps, addSteps, updateSteps } from "@/api/agent/steps"
import { getOrchestration} from "@/api/agent/orchestration"


const { proxy } = getCurrentInstance()

const stepsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const route = useRoute()

const data = reactive({
  form: {
    bizName: null,
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    paBizOrchestrationId: route.params.bizId,
    stepsName: null
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


/** 查询业务办理步骤;业务办理步骤列表 */
function getList() {
  loading.value = true
  listSteps(queryParams.value).then(response => {
    stepsList.value = response.rows
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
    paBizOrchestrationId: route.params.bizId,
    parentId: null,
    stepsName: null,
    stepsDescription: null,
    stepsAttribute: null
  }
  proxy.resetForm("stepsRef")
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
  title.value = "添加业务办理步骤"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getSteps(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改业务办理步骤"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["stepsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSteps(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addSteps(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除业务办理步骤;业务办理步骤编号为"' + _ids + '"的数据项？').then(function () {
    return delSteps(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}
/** 返回按钮操作 */
function handleClose() {
  const obj = { path: "/agent/orchestration" }
  proxy.$tab.closeOpenPage(obj)
}


/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/steps/export', {
    ...queryParams.value
  }, `steps_${new Date().getTime()}.xlsx`)
}
function getOrchestrationInfo() {
  return getOrchestration(route.params.bizId).then(res => {
    data.form.bizName = res.data.bizName
  })
}
onMounted(() => {
  getOrchestrationInfo()
  getList()
})
</script>
