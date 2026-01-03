<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号id" prop="paAccountId">
        <el-input v-model="queryParams.paAccountId" placeholder="请输入账号id" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="智能体id" prop="paAgentId">
        <el-input v-model="queryParams.paAgentId" placeholder="请输入智能体id" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="业务名称" prop="bizName">
        <el-input v-model="queryParams.bizName" placeholder="请输入业务名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['system:orchestration:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:orchestration:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:orchestration:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:orchestration:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orchestrationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号id" align="center" prop="paAccountId" />
      <el-table-column label="智能体id" align="center" prop="paAgentId" />
      <el-table-column label="业务名称" align="center" prop="bizName" />
      <el-table-column label="业务描述" align="center" prop="bizDescription" />
      <el-table-column label="业务url地址" align="center" prop="bizUrl" />
      <el-table-column label="业务状态" align="center" prop="biaStatus" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建人" align="center" prop="createdByName" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedByName" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:orchestration:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:orchestration:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改业务编排;业务编排对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orchestrationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号id" prop="paAccountId">
          <el-input v-model="form.paAccountId" placeholder="请输入账号id" />
        </el-form-item>
        <el-form-item label="智能体id" prop="paAgentId">
          <el-input v-model="form.paAgentId" placeholder="请输入智能体id" />
        </el-form-item>
        <el-form-item label="业务名称" prop="bizName">
          <el-input v-model="form.bizName" placeholder="请输入业务名称" />
        </el-form-item>
        <el-form-item label="业务描述" prop="bizDescription">
          <el-input v-model="form.bizDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="业务url地址" prop="bizUrl">
          <el-input v-model="form.bizUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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

<script setup name="pa_biz_orchestration">
import { listOrchestration, getOrchestration, delOrchestration, addOrchestration, updateOrchestration } from "@/api/agent/orchestration"

const { proxy } = getCurrentInstance()

const orchestrationList = ref([])
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
    paAccountId: null,
    paAgentId: null,
    bizName: null,
    bizDescription: null,
    bizUrl: null,
    biaStatus: null,
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

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (
    (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
  );
};


/** 查询业务编排;业务编排列表 */
function getList() {
  loading.value = true
  listOrchestration(queryParams.value).then(response => {
    orchestrationList.value = response.rows
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
    paAccountId: null,
    paAgentId: null,
    bizName: null,
    bizDescription: null,
    bizUrl: null,
    biaStatus: null,
    remark: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("orchestrationRef")
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
  title.value = "添加业务编排;业务编排"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getOrchestration(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改业务编排;业务编排"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orchestrationRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOrchestration(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOrchestration(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除业务编排;业务编排编号为"' + _ids + '"的数据项？').then(function () {
    return delOrchestration(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/orchestration/export', {
    ...queryParams.value
  }, `orchestration_${new Date().getTime()}.xlsx`)
}

getList()
</script>
