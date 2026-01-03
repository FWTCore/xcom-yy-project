<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号id" prop="paAccountId">
        <el-select v-model="queryParams.paAccountId" placeholder="请选择" style="width: 240px" clearable>
          <el-option v-for="item in accountOptions" :key="item.id" :label="item.paAccount" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="智能体id" prop="agentId">
        <el-input v-model="queryParams.agentId" placeholder="请输入智能体id" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="名称" prop="agentName">
        <el-input v-model="queryParams.agentName" placeholder="请输入智能体名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="agentStatus">
        <el-select v-model="queryParams.agentStatus" placeholder="状态" clearable style="width: 240px">
          <el-option v-for="dict in agent_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:agent:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:agent:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:agent:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:agent:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号" align="center" prop="paAccount" />
      <el-table-column label="名称" align="center" prop="paUserName" />
      <el-table-column label="智能体id" align="center" prop="agentId" />
      <el-table-column label="智能体名称" align="center" prop="agentName" />
      <el-table-column label="智能体描述" align="center" prop="agentDescription" />
      <el-table-column label="智能体状态" align="center" prop="agentStatus">
        <template #default="scope">
          <dict-tag :options="agent_status" :value="scope.row.agentStatus" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:agent:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:agent:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改智能体;智能体对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="agentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号id" prop="paAccountId">
          <el-select v-model="form.paAccountId" placeholder="请选择" style="width: 240px" clearable>
            <el-option v-for="item in accountOptions" :key="item.id" :label="item.paAccount"
              :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="智能体id" prop="agentId">
          <el-input v-model="form.agentId" placeholder="请输入智能体id" />
        </el-form-item>
        <el-form-item label="名称" prop="agentName">
          <el-input v-model="form.agentName" placeholder="请输入智能体名称" />
        </el-form-item>
        <el-form-item label="状态" prop="agentStatus">
          <el-select v-model="form.agentStatus" placeholder="状态" value-key="value" clearable>
            <el-option v-for="dict in agent_status" :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="agentDescription">
          <el-input v-model="form.agentDescription" type="textarea" placeholder="请输入智能体描述" />
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

<script setup name="pa_agent">
import { listAgent, getAgent, delAgent, addAgent, updateAgent } from "@/api/agent/agent"
import { listAllAccount } from "@/api/agent/account"

const { proxy } = getCurrentInstance()
const { agent_status } = proxy.useDict("agent_status");

const agentList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const accountOptions = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    paAccountId: null,
    agentId: null,
    agentName: null,
    agentDescription: null,
    agentStatus: null,
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


/** 查询智能体;智能体列表 */
function getList() {
  loading.value = true
  listAgent(queryParams.value).then(response => {
    agentList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}
function getAccount() {
  listAllAccount().then((response) => {
    accountOptions.value = response.data;
    queryParams.paAccountId = null;
  });
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
    agentId: null,
    agentName: null,
    agentDescription: null,
    agentStatus: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("agentRef")
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
  title.value = "添加智能体"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAgent(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改智能体"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["agentRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateAgent(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addAgent(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除智能体;智能体编号为"' + _ids + '"的数据项？').then(function () {
    return delAgent(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/agent/export', {
    ...queryParams.value
  }, `agent_${new Date().getTime()}.xlsx`)
}
onMounted(() => {
  getList()
  getAccount()
})

</script>
