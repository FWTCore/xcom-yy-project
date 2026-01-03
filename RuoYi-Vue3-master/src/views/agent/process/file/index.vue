<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务编排id" prop="paBizOrchestrationId">
        <el-input v-model="queryParams.paBizOrchestrationId" placeholder="请输入业务编排id" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="业务办理步骤id" prop="paBizProcessStepsId">
        <el-input v-model="queryParams.paBizProcessStepsId" placeholder="请输入业务办理步骤id" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="解析名称" prop="analysisName">
        <el-input v-model="queryParams.analysisName" placeholder="请输入解析名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="副名称" prop="subName">
        <el-input v-model="queryParams.subName" placeholder="请输入副名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="解析格式" prop="analysisFormat">
        <el-input v-model="queryParams.analysisFormat" placeholder="请输入解析格式" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="解析数量" prop="analysisNumber">
        <el-input v-model="queryParams.analysisNumber" placeholder="请输入解析数量" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:file:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:file:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:file:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:file:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fileList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="业务编排id" align="center" prop="paBizOrchestrationId" />
      <el-table-column label="业务办理步骤id" align="center" prop="paBizProcessStepsId" />
      <el-table-column label="解析名称" align="center" prop="analysisName" />
      <el-table-column label="解析类型" align="center" prop="analysisType" />
      <el-table-column label="副名称" align="center" prop="subName" />
      <el-table-column label="文件模板" align="center" prop="fileTemplate" />
      <el-table-column label="解析格式" align="center" prop="analysisFormat" />
      <el-table-column label="解析数量" align="center" prop="analysisNumber" />
      <el-table-column label="解析说明" align="center" prop="analysisDescription" />
      <el-table-column label="解析字段Json" align="center" prop="analysisField" />
      <el-table-column label="文件地址" align="center" prop="fileUrl" />
      <el-table-column label="解析结果" align="center" prop="analysisResult" />
      <el-table-column label="确认结果" align="center" prop="confirmResult" />
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
            v-hasPermi="['system:file:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:file:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改业务办理步骤文件;业务办理步骤文件对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="fileRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务编排id" prop="paBizOrchestrationId">
          <el-input v-model="form.paBizOrchestrationId" placeholder="请输入业务编排id" />
        </el-form-item>
        <el-form-item label="业务办理步骤id" prop="paBizProcessStepsId">
          <el-input v-model="form.paBizProcessStepsId" placeholder="请输入业务办理步骤id" />
        </el-form-item>
        <el-form-item label="解析名称" prop="analysisName">
          <el-input v-model="form.analysisName" placeholder="请输入解析名称" />
        </el-form-item>
        <el-form-item label="副名称" prop="subName">
          <el-input v-model="form.subName" placeholder="请输入副名称" />
        </el-form-item>
        <el-form-item label="文件模板" prop="fileTemplate">
          <el-input v-model="form.fileTemplate" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="解析格式" prop="analysisFormat">
          <el-input v-model="form.analysisFormat" placeholder="请输入解析格式" />
        </el-form-item>
        <el-form-item label="解析数量" prop="analysisNumber">
          <el-input v-model="form.analysisNumber" placeholder="请输入解析数量" />
        </el-form-item>
        <el-form-item label="解析说明" prop="analysisDescription">
          <el-input v-model="form.analysisDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="解析字段Json" prop="analysisField">
          <el-input v-model="form.analysisField" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文件地址" prop="fileUrl">
          <el-input v-model="form.fileUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="解析结果" prop="analysisResult">
          <el-input v-model="form.analysisResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="确认结果" prop="confirmResult">
          <el-input v-model="form.confirmResult" type="textarea" placeholder="请输入内容" />
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

<script setup name="File">
import { listFile, getFile, delFile, addFile, updateFile } from "@/api/agent/stepsFile"

const { proxy } = getCurrentInstance()

const fileList = ref([])
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
    paBizOrchestrationId: null,
    paBizProcessStepsId: null,
    analysisName: null,
    analysisType: null,
    subName: null,
    fileTemplate: null,
    analysisFormat: null,
    analysisNumber: null,
    analysisDescription: null,
    analysisField: null,
    fileUrl: null,
    analysisResult: null,
    confirmResult: null,
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


/** 查询业务办理步骤文件;业务办理步骤文件列表 */
function getList() {
  loading.value = true
  listFile(queryParams.value).then(response => {
    fileList.value = response.rows
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
    paBizOrchestrationId: null,
    paBizProcessStepsId: null,
    analysisName: null,
    analysisType: null,
    subName: null,
    fileTemplate: null,
    analysisFormat: null,
    analysisNumber: null,
    analysisDescription: null,
    analysisField: null,
    fileUrl: null,
    analysisResult: null,
    confirmResult: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("fileRef")
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
  title.value = "添加业务办理步骤文件;业务办理步骤文件"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getFile(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改业务办理步骤文件;业务办理步骤文件"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["fileRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateFile(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addFile(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除业务办理步骤文件;业务办理步骤文件编号为"' + _ids + '"的数据项？').then(function () {
    return delFile(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/file/export', {
    ...queryParams.value
  }, `file_${new Date().getTime()}.xlsx`)
}

getList()
</script>
