<template>
  <div class="app-container">
    <h4 class="form-header h4">基本信息</h4>
    <el-form :model="data.form" label-width="80px">
      <el-row>
        <el-col :span="8" :offset="2">
          <el-form-item label="解析名称" prop="analysisName">
            <el-input v-model="form.analysisName" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-form-item label="解析类型" prop="analysisTypeDesc">
            <el-input v-model="form.analysisTypeDesc" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <h4 class="form-header h4">配置信息</h4>
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="副名称" prop="subName">
        <el-input v-model="queryParams.subName" placeholder="请输入副名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:detail:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:detail:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:detail:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Close" @click="handleClose">关闭</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['system:detail:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="副名称" align="center" prop="subName" />
      <el-table-column label="文件模板" align="center" prop="fileTemplate" />
      <el-table-column label="解析格式" align="center" prop="analysisFormat" />
      <el-table-column label="解析数量" align="center" prop="analysisNumber" />
      <el-table-column label="解析说明" align="center" prop="analysisDescription" />
      <el-table-column label="解析字段Json" align="center" prop="analysisField" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:detail:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:detail:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改解析配置详情;解析配置详情对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="detailRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="副名称" prop="subName">
          <el-input v-model="form.subName" placeholder="请输入副名称" />
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
        <el-form-item label="解析结果" prop="analysisField">
          <el-input v-model="form.analysisField" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文件模板" prop="fileTemplate">
          <FileUpload :file-types="['.jpg', '.jpeg', '.png', '.pdf', '.doc', '.docx']" :max-file-size="20"
            :max-files="1" v-model="form.fileTemplateList" />
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

<script setup name="Detail">
import { listDetail, getDetail, delDetail, addDetail, updateDetail } from "@/api/agent/configDetail"
import { getConfig } from "@/api/agent/config"
import FileUpload from "@/views/common/UploadFile.vue";


const { proxy } = getCurrentInstance()
const { analysis_type } = proxy.useDict("analysis_type");
const route = useRoute()

const detailList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {
    configId: null,
    analysisName: null,
    analysisType: null,
    analysisTypeDesc: null
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    paAnalysisConfigId: route.params.configId,
    subName: null,
    fileTemplate: null,
    analysisFormat: null,
    analysisNumber: null,
    analysisDescription: null,
    analysisField: null
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


/** 查询解析配置详情;解析配置详情列表 */
function getList() {
  loading.value = true
  listDetail(queryParams.value).then(response => {
    detailList.value = response.rows
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
    paAnalysisConfigId: route.params.configId,
    analysisName: null,
    analysisType: null,
    subName: null,
    fileTemplate: null,
    analysisFormat: null,
    analysisNumber: null,
    analysisDescription: null,
    analysisField: null,
    fileTemplateList: null
  }
  proxy.resetForm("detailRef")
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
  title.value = "添加解析配置明细"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getDetail(_id).then(response => {
    form.value = { fileTemplateList: [response.data.fileTemplate], ...response.data }
    open.value = true
    title.value = "修改解析配置明细"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["detailRef"].validate(valid => {
    if (valid) {
      form.value.fileTemplate = Array.isArray(form.value?.fileTemplateList) &&
        form.value.fileTemplateList.length > 0
        ? form.value.fileTemplateList[0]
        : form.value.fileTemplateList
      if (form.value.id != null) {
        updateDetail(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addDetail(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除解析配置明细;解析配置明细编号为"' + _ids + '"的数据项？').then(function () {
    return delDetail(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/detail/export', {
    ...queryParams.value
  }, `detail_${new Date().getTime()}.xlsx`)
}

function getConfigInfo() {
  return getConfig(route.params.configId).then(res => {
    data.form.configId = res.data.id
    data.form.analysisName = res.data.analysisName
    data.form.analysisType = res.data.analysisType
    const found = analysis_type.value.find(item => item.value === String(res.data.analysisType))
    data.form.analysisTypeDesc = found?.label ?? ''
  })
}
/** 返回按钮操作 */
function handleClose() {
  const obj = { path: "/agent/analysisConfig" }
  proxy.$tab.closeOpenPage(obj)
}

onMounted(() => {
  getConfigInfo()
  getList()
})

</script>
