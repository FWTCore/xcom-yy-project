<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号" prop="paAccount">
        <el-input v-model="queryParams.paAccount" placeholder="请输入账号" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="名称" prop="paUserName">
        <el-input v-model="queryParams.paUserName" placeholder="请输入名称" style="width: 240px" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="paStatus">
        <el-select v-model="queryParams.paStatus" placeholder="状态" clearable style="width: 240px">
          <el-option v-for="dict in pa_status" :key="dict.value" :label="dict.label" :value="dict.value" />
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
          v-hasPermi="['system:account:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:account:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:account:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:account:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号" align="center" prop="paAccount" />
      <el-table-column label="名称" align="center" prop="paUserName" />
      <el-table-column label="描述" align="center" prop="paAccountDescription" />
      <el-table-column label="AK" align="center" prop="paAccessKey" />
      <el-table-column label="SK" align="center" prop="paApiKey" />
      <el-table-column label="状态" align="center" prop="paStatus">
        <template #default="scope">
          <dict-tag :options="pa_status" :value="scope.row.paStatus" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:account:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:account:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改账号;账号对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="accountRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号" prop="paAccount">
          <el-input v-model="form.paAccount" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="名称" prop="paUserName">
          <el-input v-model="form.paUserName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="AK" prop="paAccessKey">
          <el-input v-model="form.paAccessKey" placeholder="请输入AK" />
        </el-form-item>
        <el-form-item label="SK" prop="paApiKey">
          <el-input v-model="form.paApiKey" placeholder="请输入SK" />
        </el-form-item>
        <el-form-item label="状态" prop="paStatus">
          <el-select v-model="form.paStatus" placeholder="状态" value-key="value" clearable>
            <el-option v-for="dict in pa_status" :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="paAccountDescription">
          <el-input v-model="form.paAccountDescription" type="textarea" placeholder="请输入描述" />
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

<script setup name="pa_account">
import { listAccount, getAccount, delAccount, addAccount, updateAccount } from "@/api/agent/account"

const { proxy } = getCurrentInstance()
const { pa_status } = proxy.useDict("pa_status");

const accountList = ref([])
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
    searAccount: null,
    searUserName: null,
    paStatus: null
  },
  rules: {
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询账号;账号列表 */
function getList() {
  loading.value = true
  listAccount(queryParams.value).then(response => {
    accountList.value = response.rows
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
    searAccount: null,
    paAccount: null,
    paUserName: null,
    searUserName: null,
    paAccountDescription: null,
    paAccessKey: null,
    paApiKey: null,
    paStatus: "0"
  }
  proxy.resetForm("accountRef")
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
// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (
    (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
  );
};

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加账号"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAccount(_id).then(response => {
    form.value = { paStatus: String(response.data.paStatus), ...response.data }
    open.value = true
    title.value = "修改账号"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["accountRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateAccount(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addAccount(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除账号;账号编号为"' + _ids + '"的数据项？').then(function () {
    return delAccount(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/account/export', {
    ...queryParams.value
  }, `account_${new Date().getTime()}.xlsx`)
}
onMounted(() => {
  getList()
})

</script>
