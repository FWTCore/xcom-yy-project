<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="归属单位" prop="deptId">
        <el-tree-select
          v-model="queryParams.deptId"
          :data="enabledDeptOptions"
          :props="{ value: 'id', label: 'label', children: 'children' }"
          value-key="id"
          placeholder="请选择归属单位"
          style="width: 240px"
          clearable
          @change="handleTreeNodeClick"
          filterable
        />
      </el-form-item>
      <el-form-item prop="projectId">
        <template #label>
          <span>
            <el-tooltip content="需要先选择单位" placement="top">
              <el-icon><question-filled /></el-icon>
            </el-tooltip>
            项目
          </span>
        </template>
        <el-select
          v-model="queryParams.projectId"
          placeholder="请选择"
          style="width: 240px"
          clearable
        >
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="临时编码" prop="searchTemporaryCode">
        <el-input
          v-model="queryParams.searchTemporaryCode"
          placeholder="请输入临时编码"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原始编码" prop="searchOriginalCode">
        <el-input
          v-model="queryParams.searchOriginalCode"
          placeholder="请输入原始编码"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门类" prop="categoryId">
        <el-select
          v-model="queryParams.categoryId"
          placeholder="请选择"
          style="width: 240px"
          clearable
          @change="handleNodeClick"
        >
          <el-option
            v-for="item in categoryOptions"
            :key="item.id"
            :label="item.categoryName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌id" prop="brandId">
        <template #label>
          <span>
            <el-tooltip content="需要先选择门类" placement="top">
              <el-icon><question-filled /></el-icon>
            </el-tooltip>
            品牌
          </span>
        </template>
        <el-select
          v-model="queryParams.brandId"
          placeholder="请选择"
          style="width: 240px"
          clearable
          filterable
        >
          <el-option
            v-for="item in brandOptions"
            :key="item.id"
            :label="item.brandName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="资产名称" prop="searchAssetName">
        <el-input
          v-model="queryParams.searchAssetName"
          placeholder="请输入资产名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="searchSpecification">
        <el-input
          v-model="queryParams.searchSpecification"
          placeholder="请输入规格型号"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select
          v-model="queryParams.assetStatus"
          placeholder="资产状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in asset_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="地点名称" prop="searchLocationName">
        <el-input
          v-model="queryParams.searchLocationName"
          placeholder="请输入地点名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理部门" prop="searchManagedDeptName">
        <el-input
          v-model="queryParams.searchManagedDeptName"
          placeholder="请输入管理部门名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用部门" prop="searchUsingDeptName">
        <el-input
          v-model="queryParams.searchUsingDeptName"
          placeholder="请输入使用部门名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员工" prop="searchManagedEmpName">
        <el-input
          v-model="queryParams.searchManagedEmpName"
          placeholder="请输入管理员工名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用员工" prop="searchUsingEmpName">
        <el-input
          v-model="queryParams.searchUsingEmpName"
          placeholder="请输入使用员工名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集人" prop="searchCollectorUserName">
        <el-input
          v-model="queryParams.searchCollectorUserName"
          placeholder="请输入采集人名称"
          style="width: 240px"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集时间" style="width: 308px">
        <el-date-picker
          v-model="dateRange"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="打印状态" prop="printStatus">
        <el-select
          v-model="queryParams.printStatus"
          placeholder="打印状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in asset_print_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
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
          v-hasPermi="['business:asset:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:asset:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Edit"
          @click="handleBatchUpdate"
          v-hasPermi="['business:asset:edit']"
          >批量编辑</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:asset:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:asset:export']"
          >导出</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          :disabled="multiple"
          @click="handleDisassociate"
          v-hasPermi="['business:asset:disassociate']"
          >取消关联</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="assetList"
      :height="tableHeight"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="50">
        <template #default="scope">
          <span>{{ calculateIndex(scope.$index) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属单位" align="center" prop="deptName" />
      <el-table-column label="项目" align="center" prop="projectName" />
      <el-table-column label="临时编码" align="center" prop="temporaryCode" />
      <el-table-column label="原始编码" align="center" prop="originalCode" />
      <el-table-column
        label="原始子编码"
        align="center"
        prop="originalSubCode"
      />
      <el-table-column label="门类名称" align="center" prop="categoryName" />
      <el-table-column label="品牌名称" align="center" prop="brandName" />
      <el-table-column label="资产名称" align="center" prop="assetName" />
      <el-table-column label="规格型号" align="center" prop="specification" />
      <el-table-column
        label="生产时间"
        align="center"
        prop="productionTime"
        width="180"
      >
        <template #default="scope">
          <span>{{ parseTime(scope.row.productionTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资产状态" align="center" prop="assetStatus">
        <template #default="scope">
          <dict-tag :options="asset_status" :value="scope.row.assetStatus" />
        </template>
      </el-table-column>
      <el-table-column label="地点名称" align="center" prop="locationName" />
      <el-table-column label="管理部门" align="center" prop="managedDeptName" />
      <el-table-column label="使用部门" align="center" prop="usingDeptName" />
      <el-table-column label="管理员工" align="center" prop="managedEmpName" />
      <el-table-column label="使用员工" align="center" prop="usingEmpName" />
      <el-table-column label="采集人" align="center" prop="collectorUserName" />
      <el-table-column
        label="采集时间"
        align="center"
        prop="collectorTime"
        width="180"
      >
        <template #default="scope">
          <span>{{
            parseTime(scope.row.collectorTime, "{y}-{m}-{d} {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="匹配状态" align="center" prop="matchStatus">
        <template #default="scope">
          <span v-if="scope.row.matchStatus === 1" class="status-tag status-yes"
            >是</span
          >
          <span v-else class="status-tag status-no">否</span>
        </template>
      </el-table-column>
      <el-table-column label="打印状态" align="center" prop="printStatus">
        <template #default="scope">
          <dict-tag
            :options="asset_print_status"
            :value="scope.row.printStatus"
          />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="主图片" align="center" prop="mainImageUrl">
        <template #default="scope">
          <div v-if="scope.row.mainImageUrl">
            <el-image
              :src="normalizeImageUrl(scope.row.mainImageUrl)"
              :alt="scope.row.mainImageName"
              :preview-src-list="[normalizeImageUrl(scope.row.mainImageUrl)]"
              preview-teleported
              style="
                width: 50px;
                height: 50px;
                object-fit: cover;
                cursor: pointer;
              "
              hide-on-click-modal
            />
          </div>
          <div v-else>无</div>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:asset:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:asset:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资产对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="assetRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属单位" prop="deptId">
              <el-tree-select
                v-model="form.deptId"
                :data="enabledDeptOptions"
                :props="{ value: 'id', label: 'label', children: 'children' }"
                value-key="id"
                placeholder="请选择归属单位"
                check-strictly
                @change="handleTreeNodeFormClick"
                filterable
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="projectId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  项目
                </span>
              </template>
              <el-select
                v-model="form.projectId"
                placeholder="请选择"
                style="width: 240px"
                clearable
              >
                <el-option
                  v-for="item in projectFormOptions"
                  :key="item.id"
                  :label="item.projectName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="临时编码" prop="temporaryCode">
              <el-input
                v-model="form.temporaryCode"
                placeholder="请输入临时编码"
                :disabled="!!form.id"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="原始子编码" prop="originalSubCode">
              <el-input
                v-model="form.originalSubCode"
                placeholder="请输入原始子编码"
                :disabled="!!form.id"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="门类" prop="categoryId">
              <el-select
                v-model="form.categoryId"
                placeholder="请选择"
                style="width: 240px"
                @change="handleNodeClickForForm"
                clearable
              >
                <el-option
                  v-for="item in categoryOptions"
                  :key="item.id"
                  :label="item.categoryName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="brandId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择门类" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  品牌
                </span>
              </template>
              <el-select
                v-model="form.brandId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in brandFormOptions"
                  :key="item.id"
                  :label="item.brandName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="资产名称" prop="assetName">
              <el-input
                v-model="form.assetName"
                placeholder="请输入资产名称"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="specification">
              <el-input
                v-model="form.specification"
                placeholder="请输入规格型号"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产时间" prop="productionTime">
              <el-date-picker
                v-model="form.productionTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择生产时间"
                clearable
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资产状态" prop="assetStatus">
              <el-select
                v-model="form.assetStatus"
                placeholder="资产状态"
                value-key="value"
                clearable
              >
                <el-option
                  v-for="dict in asset_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item prop="locationId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  地点
                </span>
              </template>
              <el-select
                v-model="form.locationId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in locationFormOptions"
                  :key="item.id"
                  :label="item.locationName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理部门
                </span>
              </template>
              <el-select
                v-model="form.managedDeptId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in deptFormOptions"
                  :key="item.id"
                  :label="item.departmentName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用部门
                </span>
              </template>
              <el-select
                v-model="form.usingDeptId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in deptFormOptions"
                  :key="item.id"
                  :label="item.departmentName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理员工
                </span>
              </template>
              <el-select
                v-model="form.managedEmpId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in empFormOptions"
                  :key="item.id"
                  :label="item.employeeName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用员工
                </span>
              </template>
              <el-select
                v-model="form.usingEmpId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in empFormOptions"
                  :key="item.id"
                  :label="item.employeeName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="主图片" prop="mainImageUrl">
              <upload-image
                v-model="form.mainImageUrl"
                :defaultImage="form.imageUrl"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="图片" prop="imageUrl">
              <upload-image
                v-model="form.imageUrl"
                :defaultImage="form.imageUrl"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input
                v-model="form.remark"
                type="textarea"
                placeholder="请输入内容"
                clearable
              ></el-input>
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

    <!-- 批量修改 -->
    <el-dialog
      :title="batchTitle"
      v-model="batchOpen"
      width="800px"
      append-to-body
    >
      <el-form ref="batchAssetRef" :model="batchForm" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="资产名称" prop="assetName">
              <el-input
                v-model="batchForm.assetName"
                placeholder="请输入资产名称"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="specification">
              <el-input
                v-model="batchForm.specification"
                placeholder="请输入规格型号"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生产时间" prop="productionTime">
              <el-date-picker
                v-model="batchForm.productionTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择生产时间"
                clearable
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资产状态" prop="assetStatus">
              <el-select
                v-model="batchForm.assetStatus"
                placeholder="资产状态"
                value-key="value"
                clearable
              >
                <el-option
                  v-for="dict in asset_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="门类" prop="categoryId">
              <el-select
                v-model="batchForm.categoryId"
                placeholder="请选择"
                style="width: 240px"
                @change="handleNodeClickForForm"
                clearable
              >
                <el-option
                  v-for="item in categoryOptions"
                  :key="item.id"
                  :label="item.categoryName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="brandId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择门类" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  品牌
                </span>
              </template>
              <el-select
                v-model="batchForm.brandId"
                placeholder="请选择"
                style="width: 240px"
                filterable
                clearable
              >
                <el-option
                  v-for="item in brandFormOptions"
                  :key="item.id"
                  :label="item.brandName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item prop="locationId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  地点
                </span>
              </template>
              <el-select
                v-model="batchForm.locationId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in locationFormOptions"
                  :key="item.id"
                  :label="item.locationName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理部门
                </span>
              </template>
              <el-select
                v-model="batchForm.managedDeptId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in deptFormOptions"
                  :key="item.id"
                  :label="item.departmentName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingDeptId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用部门
                </span>
              </template>
              <el-select
                v-model="batchForm.usingDeptId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in deptFormOptions"
                  :key="item.id"
                  :label="item.departmentName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="managedEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  管理员工
                </span>
              </template>
              <el-select
                v-model="batchForm.managedEmpId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in empFormOptions"
                  :key="item.id"
                  :label="item.employeeName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="usingEmpId">
              <template #label>
                <span>
                  <el-tooltip content="需要先选择单位" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  使用员工
                </span>
              </template>
              <el-select
                v-model="batchForm.usingEmpId"
                placeholder="请选择"
                style="width: 240px"
                clearable
                filterable
              >
                <el-option
                  v-for="item in empFormOptions"
                  :key="item.id"
                  :label="item.employeeName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitBatchForm">确 定</el-button>
          <el-button @click="cancelBatch">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Asset">
import {
  listAsset,
  getAsset,
  delAsset,
  addAsset,
  updateAsset,
  updateAssetBatch,
  disassociateAsset,
} from "@/api/business/asset";
import { deptTreeNoAuthSelect } from "@/api/system/user";
import { listAllCategory } from "@/api/business/category";
import { listAllBrand } from "@/api/business/brand";
import { listAllProject } from "@/api/business/project";
import { listAllLocation } from "@/api/business/location";
import { listAllDepartment } from "@/api/business/department";
import { listAllEmployee } from "@/api/business/employee";
import uploadImage from "@/views/common/uploadImage.vue";

const { proxy } = getCurrentInstance();
const { asset_status } = proxy.useDict("asset_status");
const { asset_print_status } = proxy.useDict("asset_print_status");

const assetList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const batchTitle = ref("");
const batchOpen = ref(false);
const batchProjectIds = ref([]);
const batchDeptId = ref(0);
const enabledDeptOptions = ref(undefined);
const categoryOptions = ref([]);
const brandOptions = ref([]);
const projectOptions = ref([]);
const projectFormOptions = ref([]);
const brandFormOptions = ref([]);
const locationFormOptions = ref([]);
const deptFormOptions = ref([]);
const empFormOptions = ref([]);
const dateRange = ref([]);
const tableHeight = ref("400px");

// 响应式高度计算
const calculateTableHeight = () => {
  const windowHeight = window.innerHeight;
  // 减去页面其他元素高度（头部、搜索栏、分页等）
  const otherElementsHeight = 400; // 根据实际情况调整
  tableHeight.value = `${windowHeight - otherElementsHeight}px`;
};

const data = reactive({
  form: {
    assetStatus: "1",
    printStatus: "0",
  },
  batchForm: {
    assetStatus: "1",
    printStatus: "0",
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    deptId: null,
    projectId: null,
    searchTemporaryCode: null,
    searchOriginalCode: null,
    categoryId: null,
    brandId: null,
    searchAssetName: null,
    searchSpecification: null,
    searchLocationName: null,
    searchManagedDeptName: null,
    searchUsingDeptName: null,
    searchManagedEmpName: null,
    searchUsingEmpName: null,
    searchCollectorUserName: null,
    assetStatus: null,
    printStatus: null,
  },
  rules: {
    deptId: [{ required: true, message: "单位不能为空", trigger: "blur" }],
    projectId: [{ required: true, message: "项目不能为空", trigger: "blur" }],
    categoryId: [{ required: true, message: "门类不能为空", trigger: "blur" }],
    assetName: [
      { required: true, message: "资产名称不能为空", trigger: "blur" },
    ],
    deleteFlag: [
      { required: true, message: "是否删除不能为空", trigger: "blur" },
    ],
  },
});

const { queryParams, form, rules, batchForm } = toRefs(data);
/**
 * 标准化图片URL
 * @param url 原始URL
 * @returns 处理后的URL
 */
function normalizeImageUrl(url) {
  if (!url) return undefined;
  // 如果已经是完整URL或base64数据，直接返回
  if (url.startsWith("http") || url.startsWith("data:")) {
    return url;
  }
  // 处理相对路径
  if (!url.startsWith("/api")) {
    url = import.meta.env.VITE_APP_BASE_API + url;
  }
  return url;
}
/** 查询资产列表 */
function getList() {
  loading.value = true;
  listAsset(proxy.addDateRange(queryParams.value, dateRange.value)).then(
    (response) => {
      assetList.value = response.rows;
      total.value = response.total;
      loading.value = false;
    }
  );
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    originalSubCode: null,
    categoryId: null,
    categoryName: null,
    brandId: null,
    brandName: null,
    materialId: null,
    assetName: null,
    specification: null,
    assetStatus: "1",
    printStatus: "0",
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
    updatedTime: null,
  };
  proxy.resetForm("assetRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
  batchProjectIds.value = [...new Set(selection.map((item) => item.projectId))];
  batchDeptId.value = selection.length > 0 ? selection[0].deptId : null;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加资产";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getAsset(_id).then((response) => {
    form.value = {
      ...response.data,
      assetStatus: String(response.data.assetStatus || "1"),
    };
    open.value = true;
    title.value = "修改资产";
    handleTreeNodeFormClick(form.value.deptId);
    getFormBrand(form.value.categoryId);
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["assetRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateAsset(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAsset(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认删除资产编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delAsset(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "/asset/export",
    {
      ...queryParams.value,
    },
    `盘点资产_${new Date().getTime()}.xlsx`
  );
}

/** 取消关联按钮操作 */
function handleDisassociate(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认取消关联资产编号为"' + _ids + '"的数据项？')
    .then(function () {
      return disassociateAsset(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("取消关联成功");
    })
    .catch(() => {});
}

/** 查询单位下拉树结构 */
function getDeptTree() {
  deptTreeNoAuthSelect().then((response) => {
    enabledDeptOptions.value = filterDisabledDept(
      JSON.parse(JSON.stringify(response.data))
    );
  });
}

/** 过滤禁用的单位 */
function filterDisabledDept(deptList) {
  return deptList.filter((dept) => {
    if (dept.disabled) {
      return false;
    }
    if (dept.children && dept.children.length) {
      dept.children = filterDisabledDept(dept.children);
    }
    return true;
  });
}

// 计算序号（考虑分页）
const calculateIndex = (index) => {
  return (
    (queryParams.value.pageNum - 1) * queryParams.value.pageSize + index + 1
  );
};

function getCategory() {
  listAllCategory().then((response) => {
    categoryOptions.value = response.data;
  });
}
function handleNodeClick(data) {
  getBrand(data);
}

function getBrand(categoryId) {
  if (
    categoryId === null ||
    categoryId === undefined ||
    categoryId === "" ||
    Number.isNaN(categoryId)
  ) {
    brandOptions.value = []; // 清空品牌选项
    queryParams.brandId = null; // 重置已选品牌
    return;
  }
  listAllBrand(categoryId).then((response) => {
    brandOptions.value = response.data;
    queryParams.brandId = null;
  });
}

function handleNodeClickForForm(data) {
  getFormBrand(data);
}

function getFormBrand(categoryId) {
  if (
    categoryId === null ||
    categoryId === undefined ||
    categoryId === "" ||
    Number.isNaN(categoryId)
  ) {
    brandFormOptions.value = []; // 清空品牌选项
    form.brandId = null; // 重置已选品牌
    return;
  }
  listAllBrand(categoryId).then((response) => {
    brandFormOptions.value = response.data;
    form.brandId = null;
  });
}

/** 节点单击事件 */
function handleTreeNodeClick(data) {
  getProject(data);
}
function getProject(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    projectOptions.value = []; // 清空选项
    queryParams.projectId = null; // 重置
    return;
  }
  listAllProject(deptId).then((response) => {
    projectOptions.value = response.data;
    queryParams.projectId = null;
  });
}

/** 节点单击事件 */
function handleTreeNodeFormClick(data) {
  getFormProject(data);
  getLocation(data);
  getDepartment(data);
  getEmployee(data);
}
function getFormProject(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    projectFormOptions.value = []; // 清空选项
    form.projectId = null; // 重置
    return;
  }
  listAllProject(deptId).then((response) => {
    projectFormOptions.value = response.data;
    form.projectId = null;
  });
}
function getLocation(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    locationFormOptions.value = []; // 清空选项
    form.locationId = null; // 重置
    return;
  }
  listAllLocation(deptId).then((response) => {
    locationFormOptions.value = response.data;
    form.locationId = null;
  });
}
function getDepartment(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    deptFormOptions.value = []; // 清空选项
    form.managedDeptId = null; // 重置
    form.usingDeptId = null; // 重置
    return;
  }
  listAllDepartment(deptId).then((response) => {
    deptFormOptions.value = response.data;
    form.managedDeptId = null;
    form.usingDeptId = null;
  });
}
function getEmployee(deptId) {
  if (
    deptId === null ||
    deptId === undefined ||
    deptId === "" ||
    Number.isNaN(deptId)
  ) {
    empFormOptions.value = []; // 清空选项
    form.managedEmpId = null; // 重置
    form.usingEmpId = null; // 重置
    return;
  }
  listAllEmployee(deptId).then((response) => {
    empFormOptions.value = response.data;
    form.managedEmpId = null;
    form.usingEmpId = null;
  });
}

/** 修改按钮操作 */
function handleBatchUpdate() {
  resetBatch();
  // 检查项目选择
  if (!batchProjectIds.value.length || batchProjectIds.value.length > 1) {
    proxy.$modal.msgWarning("批量修改的数据项只能是同一个项目");
    return;
  }
  // 检查数据项选择 - 修复点
  if (!ids.value || ids.value.length === 0) {
    proxy.$modal.msgWarning("请选择需要批量修改的数据项");
    return;
  }
  batchForm.value = { id: ids.value };
  batchOpen.value = true;
  batchTitle.value = "批量修改资产（共" + ids.value.length + "项）";
  handleTreeNodeBatchClick(batchDeptId.value);
}

/** 节点单击事件 */
function handleTreeNodeBatchClick(data) {
  getDepartment(data);
  getEmployee(data);
  getLocation(data);
}
/** 提交按钮 */
function submitBatchForm() {
  proxy.$refs["batchAssetRef"].validate((valid) => {
    if (valid) {
      if (batchForm.value.id == null || batchForm.value.id.length === 0) {
        proxy.$modal.msgWarning("请选择需要批量修改的数据项");
        return;
      }
      const { id, ...rest } = batchForm.value;
      const batchData = { ...rest, ids: id };
      updateAssetBatch(batchData).then((response) => {
        proxy.$modal.msgSuccess("批量修改成功");
        batchOpen.value = false;
        getList();
      });
    }
  });
}
// 取消按钮
function cancelBatch() {
  batchOpen.value = false;
  resetBatch();
}
// 表单重置
function resetBatch() {
  batchForm.value = {
    id: null,
    deptId: null,
    projectId: null,
    temporaryCode: null,
    originalCode: null,
    originalSubCode: null,
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
    updatedTime: null,
  };
  proxy.resetForm("batchAssetRef");
}

onMounted(() => {
  getDeptTree();
  getCategory();
  getList();
  calculateTableHeight();
  window.addEventListener("resize", calculateTableHeight);
});

onUnmounted(() => {
  window.removeEventListener("resize", calculateTableHeight);
});
</script>
