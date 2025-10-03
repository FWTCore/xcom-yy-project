<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="系统公司id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入系统公司id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目id" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="临时编码" prop="temporaryCode">
        <el-input
          v-model="queryParams.temporaryCode"
          placeholder="请输入临时编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原始编码" prop="originalCode">
        <el-input
          v-model="queryParams.originalCode"
          placeholder="请输入原始编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类id" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入分类id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类名称" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入分类名称"
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
      <el-form-item label="品牌名称" prop="brandName">
        <el-input
          v-model="queryParams.brandName"
          placeholder="请输入品牌名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物资id" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入物资id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input
          v-model="queryParams.assetName"
          placeholder="请输入资产名称"
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
      <el-form-item label="生产时间" prop="productionTime">
        <el-date-picker clearable
          v-model="queryParams.productionTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择生产时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="地点id" prop="locationId">
        <el-input
          v-model="queryParams.locationId"
          placeholder="请输入地点id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地点名称" prop="locationName">
        <el-input
          v-model="queryParams.locationName"
          placeholder="请输入地点名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理部门id" prop="managedDeptId">
        <el-input
          v-model="queryParams.managedDeptId"
          placeholder="请输入管理部门id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理部门名称" prop="managedDeptName">
        <el-input
          v-model="queryParams.managedDeptName"
          placeholder="请输入管理部门名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用部门id" prop="usingDeptId">
        <el-input
          v-model="queryParams.usingDeptId"
          placeholder="请输入使用部门id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用部门名称" prop="usingDeptName">
        <el-input
          v-model="queryParams.usingDeptName"
          placeholder="请输入使用部门名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员工id" prop="managedEmpId">
        <el-input
          v-model="queryParams.managedEmpId"
          placeholder="请输入管理员工id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员工名称" prop="managedEmpName">
        <el-input
          v-model="queryParams.managedEmpName"
          placeholder="请输入管理员工名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用员工id" prop="usingEmpId">
        <el-input
          v-model="queryParams.usingEmpId"
          placeholder="请输入使用员工id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用员工名称" prop="usingEmpName">
        <el-input
          v-model="queryParams.usingEmpName"
          placeholder="请输入使用员工名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集人id" prop="collectorUserId">
        <el-input
          v-model="queryParams.collectorUserId"
          placeholder="请输入采集人id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集人名称" prop="collectorUserName">
        <el-input
          v-model="queryParams.collectorUserName"
          placeholder="请输入采集人名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集时间" prop="collectorTime">
        <el-date-picker clearable
          v-model="queryParams.collectorTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择采集时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="主图片" prop="mainImageUrl">
        <el-input
          v-model="queryParams.mainImageUrl"
          placeholder="请输入主图片"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主图片名称" prop="mainImageName">
        <el-input
          v-model="queryParams.mainImageName"
          placeholder="请输入主图片名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片" prop="imageUrl">
        <el-input
          v-model="queryParams.imageUrl"
          placeholder="请输入图片"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片名称" prop="imageUrlName">
        <el-input
          v-model="queryParams.imageUrlName"
          placeholder="请输入图片名称"
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
          v-hasPermi="['system:asset:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:asset:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:asset:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['system:asset:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id;主键id" align="center" prop="id" />
      <el-table-column label="系统公司id" align="center" prop="deptId" />
      <el-table-column label="项目id" align="center" prop="projectId" />
      <el-table-column label="临时编码" align="center" prop="temporaryCode" />
      <el-table-column label="原始编码" align="center" prop="originalCode" />
      <el-table-column label="分类id" align="center" prop="categoryId" />
      <el-table-column label="分类名称" align="center" prop="categoryName" />
      <el-table-column label="品牌id" align="center" prop="brandId" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="物资id" align="center" prop="materialId" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column label="生产时间" align="center" prop="productionTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.productionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地点id" align="center" prop="locationId" />
      <el-table-column label="地点名称" align="center" prop="locationName" />
      <el-table-column label="管理部门id" align="center" prop="managedDeptId" />
      <el-table-column label="管理部门名称" align="center" prop="managedDeptName" />
      <el-table-column label="使用部门id" align="center" prop="usingDeptId" />
      <el-table-column label="使用部门名称" align="center" prop="usingDeptName" />
      <el-table-column label="管理员工id" align="center" prop="managedEmpId" />
      <el-table-column label="管理员工名称" align="center" prop="managedEmpName" />
      <el-table-column label="使用员工id" align="center" prop="usingEmpId" />
      <el-table-column label="使用员工名称" align="center" prop="usingEmpName" />
      <el-table-column label="采集人id" align="center" prop="collectorUserId" />
      <el-table-column label="采集人名称" align="center" prop="collectorUserName" />
      <el-table-column label="采集时间" align="center" prop="collectorTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.collectorTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="主图片" align="center" prop="mainImageUrl" />
      <el-table-column label="主图片名称" align="center" prop="mainImageName" />
      <el-table-column label="图片" align="center" prop="imageUrl" />
      <el-table-column label="图片名称" align="center" prop="imageUrlName" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:asset:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:asset:remove']">删除</el-button>
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

    <!-- 添加或修改资产对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="assetRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="系统公司id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入系统公司id" />
        </el-form-item>
        <el-form-item label="项目id" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="临时编码" prop="temporaryCode">
          <el-input v-model="form.temporaryCode" placeholder="请输入临时编码" />
        </el-form-item>
        <el-form-item label="原始编码" prop="originalCode">
          <el-input v-model="form.originalCode" placeholder="请输入原始编码" />
        </el-form-item>
        <el-form-item label="分类id" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类id" />
        </el-form-item>
        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="品牌id" prop="brandId">
          <el-input v-model="form.brandId" placeholder="请输入品牌id" />
        </el-form-item>
        <el-form-item label="品牌名称" prop="brandName">
          <el-input v-model="form.brandName" placeholder="请输入品牌名称" />
        </el-form-item>
        <el-form-item label="物资id" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入物资id" />
        </el-form-item>
        <el-form-item label="资产名称" prop="assetName">
          <el-input v-model="form.assetName" placeholder="请输入资产名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="specification">
          <el-input v-model="form.specification" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="生产时间" prop="productionTime">
          <el-date-picker clearable
            v-model="form.productionTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择生产时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地点id" prop="locationId">
          <el-input v-model="form.locationId" placeholder="请输入地点id" />
        </el-form-item>
        <el-form-item label="地点名称" prop="locationName">
          <el-input v-model="form.locationName" placeholder="请输入地点名称" />
        </el-form-item>
        <el-form-item label="管理部门id" prop="managedDeptId">
          <el-input v-model="form.managedDeptId" placeholder="请输入管理部门id" />
        </el-form-item>
        <el-form-item label="管理部门名称" prop="managedDeptName">
          <el-input v-model="form.managedDeptName" placeholder="请输入管理部门名称" />
        </el-form-item>
        <el-form-item label="使用部门id" prop="usingDeptId">
          <el-input v-model="form.usingDeptId" placeholder="请输入使用部门id" />
        </el-form-item>
        <el-form-item label="使用部门名称" prop="usingDeptName">
          <el-input v-model="form.usingDeptName" placeholder="请输入使用部门名称" />
        </el-form-item>
        <el-form-item label="管理员工id" prop="managedEmpId">
          <el-input v-model="form.managedEmpId" placeholder="请输入管理员工id" />
        </el-form-item>
        <el-form-item label="管理员工名称" prop="managedEmpName">
          <el-input v-model="form.managedEmpName" placeholder="请输入管理员工名称" />
        </el-form-item>
        <el-form-item label="使用员工id" prop="usingEmpId">
          <el-input v-model="form.usingEmpId" placeholder="请输入使用员工id" />
        </el-form-item>
        <el-form-item label="使用员工名称" prop="usingEmpName">
          <el-input v-model="form.usingEmpName" placeholder="请输入使用员工名称" />
        </el-form-item>
        <el-form-item label="采集人id" prop="collectorUserId">
          <el-input v-model="form.collectorUserId" placeholder="请输入采集人id" />
        </el-form-item>
        <el-form-item label="采集人名称" prop="collectorUserName">
          <el-input v-model="form.collectorUserName" placeholder="请输入采集人名称" />
        </el-form-item>
        <el-form-item label="采集时间" prop="collectorTime">
          <el-date-picker clearable
            v-model="form.collectorTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择采集时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="主图片" prop="mainImageUrl">
          <el-input v-model="form.mainImageUrl" placeholder="请输入主图片" />
        </el-form-item>
        <el-form-item label="主图片名称" prop="mainImageName">
          <el-input v-model="form.mainImageName" placeholder="请输入主图片名称" />
        </el-form-item>
        <el-form-item label="图片" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入图片" />
        </el-form-item>
        <el-form-item label="图片名称" prop="imageUrlName">
          <el-input v-model="form.imageUrlName" placeholder="请输入图片名称" />
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

<script setup name="Asset">
import { listAsset, getAsset, delAsset, addAsset, updateAsset } from "@/api/system/asset"

const { proxy } = getCurrentInstance()

const assetList = ref([])
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
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    categoryId: null,
    categoryName: null,
    brandId: null,
    brandName: null,
    materialId: null,
    assetName: null,
    specification: null,
    productionTime: null,
    locationId: null,
    locationName: null,
    managedDeptId: null,
    managedDeptName: null,
    usingDeptId: null,
    usingDeptName: null,
    managedEmpId: null,
    managedEmpName: null,
    usingEmpId: null,
    usingEmpName: null,
    collectorUserId: null,
    collectorUserName: null,
    collectorTime: null,
    mainImageUrl: null,
    mainImageName: null,
    imageUrl: null,
    imageUrlName: null,
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

/** 查询资产列表 */
function getList() {
  loading.value = true
  listAsset(queryParams.value).then(response => {
    assetList.value = response.rows
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
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    categoryId: null,
    categoryName: null,
    brandId: null,
    brandName: null,
    materialId: null,
    assetName: null,
    specification: null,
    productionTime: null,
    locationId: null,
    locationName: null,
    managedDeptId: null,
    managedDeptName: null,
    usingDeptId: null,
    usingDeptName: null,
    managedEmpId: null,
    managedEmpName: null,
    usingEmpId: null,
    usingEmpName: null,
    collectorUserId: null,
    collectorUserName: null,
    collectorTime: null,
    remark: null,
    mainImageUrl: null,
    mainImageName: null,
    imageUrl: null,
    imageUrlName: null,
    deleteFlag: null,
    createdById: null,
    createdByName: null,
    createdTime: null,
    updatedById: null,
    updatedByName: null,
    updatedTime: null
  }
  proxy.resetForm("assetRef")
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
  title.value = "添加资产"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAsset(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改资产"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["assetRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateAsset(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addAsset(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除资产编号为"' + _ids + '"的数据项？').then(function() {
    return delAsset(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/asset/export', {
    ...queryParams.value
  }, `asset_${new Date().getTime()}.xlsx`)
}

getList()
</script>
