package com.ruoyi.business.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Category;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDetailVO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.domain.model.response.ProjectDetailVO;
import com.ruoyi.business.mapper.OriginalAssetMapper;
import com.ruoyi.business.service.CategoryService;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.business.service.EmployeeService;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static java.util.stream.Collectors.toMap;

/**
 * 原始资产Service业务层处理
 *
 * @author xcom
 * @date 2025-10-19
 */
@Slf4j
@Service
public class OriginalAssetServiceImpl implements OriginalAssetService {
    @Resource
    private OriginalAssetMapper originalAssetMapper;
    @Resource
    private CategoryService     categoryService;
    @Resource
    private LocationService     locationService;
    @Resource
    private DepartmentService   departmentService;
    @Resource
    private EmployeeService     employeeService;
    @Resource
    private ProjectService      projectService;
    @Resource
    private ISysDeptService     iSysDeptService;

    /**
     * 查询原始资产
     *
     * @param id 原始资产主键
     * @return 原始资产
     */
    @Override
    public OriginalAssetDO selectOriginalAssetById(Long id) {
        return originalAssetMapper.selectOriginalAssetById(id);
    }

    /**
     * 查询原始资产
     *
     * @param ids 原始资产主键
     * @return 原始资产
     */
    @Override
    public List<OriginalAssetDO> selectOriginalAssetByIds(List<Long> ids) {
        return originalAssetMapper.selectOriginalAssetByIds(ids);
    }

    /**
     * 查询原始资产列表
     *
     * @param originalAsset 原始资产
     * @return 原始资产
     */
    @Override
    @DataScope(deptAlias = "a", projectAlias = "a")
    public List<OriginalAssetDO> selectOriginalAssetList(OriginalAsset originalAsset) {
        return originalAssetMapper.selectOriginalAssetList(originalAsset);
    }

    @Override
    @DataScope(deptAlias = "a", projectAlias = "a")
    public List<OriginalAssetDetailVO> selectOriginalAssetDetailList(OriginalAsset originalAsset) {
        return originalAssetMapper.selectOriginalAssetDetailList(originalAsset);
    }

    /**
     * 新增原始资产
     *
     * @param originalAsset 原始资产
     * @return 结果
     */
    @Override
    public int insertOriginalAsset(OriginalAssetDO originalAsset) {
        validAndSetField(originalAsset);
        originalAsset.setBaseFieldValue();
        return originalAssetMapper.insertOriginalAsset(originalAsset);
    }

    /**
     * 修改原始资产
     *
     * @param originalAsset 原始资产
     * @return 结果
     */
    @Override
    public int updateOriginalAsset(OriginalAssetDO originalAsset) {
        validAndSetField(originalAsset);
        originalAsset.setUpdatedFieldValue();
        return originalAssetMapper.updateOriginalAsset(originalAsset);
    }

    /**
     * 批量删除原始资产
     *
     * @param ids 需要删除的原始资产主键
     * @return 结果
     */
    @Override
    public int deleteOriginalAssetByIds(Long[] ids) {
        return originalAssetMapper.deleteOriginalAssetByIds(ids);
    }

    /**
     * 删除原始资产信息
     *
     * @param id 原始资产主键
     * @return 结果
     */
    @Override
    public int deleteOriginalAssetById(Long id) {
        return originalAssetMapper.deleteOriginalAssetById(id);
    }

    @Override
    public String importData(List<OriginalAsset> dataList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(dataList) || dataList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        List<String> deptNameList = dataList.stream().map(OriginalAsset::getDeptName).filter(StringUtils::isNotEmpty)
            .distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(deptNameList) || deptNameList.size() != 1) {
            throw new ServiceException("导入数据只能是同1个所属单位！");
        }
        List<String> projectNameList = dataList.stream().map(OriginalAsset::getProjectName)
            .filter(StringUtils::isNotEmpty).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(projectNameList) || projectNameList.size() != 1) {
            throw new ServiceException("导入数据只能是同1个项目！");
        }
        SysDept sysDept = new SysDept();
        sysDept.setAccurateDeptName(deptNameList.get(0));
        List<SysDept> sysDeptList = iSysDeptService.selectDeptList(sysDept);
        if (CollectionUtils.isEmpty(sysDeptList)) {
            throw new ServiceException("所属单位不存在");
        }
        SysDept existSysDept = sysDeptList.get(0);

        Project project = new Project();
        project.setProjectName(projectNameList.get(0));
        List<ProjectDO> projectList = projectService.selectProjectList(project);
        if (CollectionUtils.isEmpty(projectList)) {
            throw new ServiceException("项目不存在");
        }
        ProjectDO existProject = projectList.get(0);
        if (!existProject.getDeptId().equals(existSysDept.getDeptId())) {
            throw new ServiceException("项目所属单位不存在");
        }

        for (OriginalAsset data : dataList) {
            try {
                if (StringUtils.isEmpty(data.getOriginalCode())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号不能为空");
                    continue;
                }
                if (StringUtils.isEmpty(data.getDeptName())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的所属单位为空");
                    continue;
                }
                if (StringUtils.isEmpty(data.getProjectName())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的项目为空");
                    continue;
                }
                if (StringUtils.isEmpty(data.getCategoryName())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的分类名称为空");
                    continue;
                }
                if (StringUtils.isEmpty(data.getAssetName())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的资产名称为空");
                    continue;
                }
                if (ObjectUtils.isEmpty(data.getObtainTime())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的获得时间为空");
                    continue;
                }
                if (ObjectUtils.isEmpty(data.getProductPrice())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的价值为空");
                    continue;
                }

                OriginalAssetDO importData = new OriginalAssetDO();
                importData.setDeptId(existSysDept.getDeptId());
                importData.setProjectId(existProject.getId());
                importData.setMatchCount(0);
                importData.setMatchStatus(0);

                OriginalAsset originalAssetQuery = new OriginalAsset();
                originalAssetQuery.setProjectId(existProject.getId());
                originalAssetQuery.setOriginalCode(data.getOriginalCode());
                List<OriginalAssetDO> originalAssetList = this.selectOriginalAssetList(originalAssetQuery);
                if (CollectionUtils.isNotEmpty(originalAssetList)) {
                    if (originalAssetList.size() != 1) {
                        throw new ServiceException("原始编码存在多个，请核对后提交");
                    }
                    OriginalAssetDO originalAssetDO = originalAssetList.get(0);
                    importData.setId(originalAssetDO.getId());
                    importData.setMatchCount(originalAssetDO.getMatchCount());
                    importData.setMatchStatus(originalAssetDO.getMatchStatus());
                }
                if (ObjectUtils.isNotEmpty(importData.getId())) {
                    if (!isUpdateSupport) {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 已存在");
                        continue;
                    }
                }
                importData.setOriginalCode(data.getOriginalCode());

                Category category = new Category();
                category.setCategoryName(data.getCategoryName());
                List<CategoryDO> categoryList = categoryService.selectCategoryList(category);
                if (CollectionUtils.isEmpty(categoryList)) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的门类名称不存在");
                    continue;
                }
                if (categoryList.size() != 1) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 的门类名称数据存在多个");
                    continue;
                }
                CategoryDO categoryDO = categoryList.get(0);
                importData.setCategoryId(categoryDO.getId());
                importData.setCategoryName(categoryDO.getCategoryName());

                importData.setBrandName(data.getBrandName());
                importData.setAssetName(data.getAssetName());
                importData.setSpecification(data.getSpecification());
                importData.setObtainTime(data.getObtainTime());
                importData.setProductPrice(data.getProductPrice());

                if (StringUtils.isNotEmpty(data.getLocationName())) {
                    LocationDO locationDO = locationService.insertNotExistData(importData.getDeptId(),
                        data.getLocationName());
                    importData.setLocationId(locationDO.getId());
                    importData.setLocationName(locationDO.getLocationName());
                }

                HashSet<String> departmentNameList = new HashSet<>();
                if (StringUtils.isNotEmpty(data.getManagedDeptName())) {
                    departmentNameList.add(data.getManagedDeptName());
                }
                if (StringUtils.isNotEmpty(data.getUsingDeptName())) {
                    departmentNameList.add(data.getUsingDeptName());
                }
                if (!departmentNameList.isEmpty()) {
                    List<DepartmentDO> departmentList = departmentService.insertNotExistData(importData.getDeptId(),
                        new ArrayList<>(departmentNameList));
                    DepartmentDO departmentDO = departmentList.stream()
                        .filter(e -> e.getDepartmentName().equals(data.getManagedDeptName())).findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(departmentDO)) {
                        importData.setManagedDeptId(departmentDO.getId());
                        importData.setManagedDeptName(departmentDO.getDepartmentName());
                    }
                    departmentDO = departmentList.stream()
                        .filter(e -> e.getDepartmentName().equals(data.getUsingDeptName())).findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(departmentDO)) {
                        importData.setUsingDeptId(departmentDO.getId());
                        importData.setUsingDeptName(departmentDO.getDepartmentName());
                    }
                }

                HashSet<String> empNameList = new HashSet<>();
                if (StringUtils.isNotEmpty(data.getManagedEmpName())) {
                    empNameList.add(data.getManagedEmpName());
                }
                if (StringUtils.isNotEmpty(data.getUsingEmpName())) {
                    empNameList.add(data.getUsingEmpName());
                }
                if (CollectionUtils.isNotEmpty(empNameList)) {
                    List<EmployeeDO> employeeList = employeeService.insertNotExistData(importData.getDeptId(),
                        new ArrayList<>(empNameList));
                    EmployeeDO employeeDO = employeeList.stream()
                        .filter(e -> e.getEmployeeName().equals(data.getManagedEmpName())).findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(employeeDO)) {
                        importData.setManagedEmpId(employeeDO.getId());
                        importData.setManagedEmpName(employeeDO.getEmployeeName());
                    }
                    employeeDO = employeeList.stream().filter(e -> e.getEmployeeName().equals(data.getUsingEmpName()))
                        .findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(employeeDO)) {
                        importData.setUsingEmpId(employeeDO.getId());
                        importData.setUsingEmpName(employeeDO.getEmployeeName());
                    }
                }
                importData.setRemark(data.getRemark());

                if (ObjectUtils.isNotEmpty(importData.getId())) {
                    importData.setUpdatedFieldValue();
                    originalAssetMapper.updateOriginalAsset(importData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、原始编号 " + data.getOriginalCode() + " 更新成功");
                } else {
                    importData.setBaseFieldValue();
                    originalAssetMapper.insertOriginalAsset(importData);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、原始编号 " + data.getOriginalCode() + " 导入成功");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、原始编号 " + data.getOriginalCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public boolean updateMatchStatic(String originalCode) {
        originalAssetMapper.updateMatchStatic(originalCode);
        return true;
    }

    @Override
    public AssetMetricsVO getLedgerOverview(AssetBordReqBO reqBO) {
        return originalAssetMapper.getLedgerOverview(reqBO.getDeptId(), reqBO.getProjectId());
    }

    @Override
    public AssetMetricsVO getAssetNameMetrics(Long projectId, String assetName) {
        return originalAssetMapper.getAssetNameMetrics(projectId, assetName);
    }

    @Override
    public AssetMetricsVO getUsingDeptMetrics(Long projectId, Long usingDeptId) {
        return originalAssetMapper.getUsingDeptMetrics(projectId, usingDeptId);
    }

    @Override
    public AssetMetricsVO getLocationMetrics(Long projectId, Long locationId) {
        return originalAssetMapper.getLocationMetrics(projectId,locationId);
    }

    /**
     * 验证数据
     *
     * @param originalAsset
     */
    private void validAndSetField(OriginalAssetDO originalAsset) {
        if (Objects.isNull(originalAsset.getProjectId()) || Objects.isNull(originalAsset.getDeptId())) {
            throw new ServiceException("项目和单位数据异常");
        }
        ProjectDetailVO projectDetailVO = projectService.selectProjectById(originalAsset.getProjectId());
        if (ObjectUtils.isEmpty(projectDetailVO)) {
            throw new ServiceException("项目不存在");
        }
        if (!originalAsset.getDeptId().equals(projectDetailVO.getDeptId())) {
            throw new ServiceException("项目所属单位不存在");
        }
        OriginalAsset originalAssetQuery = new OriginalAsset();
        originalAssetQuery.setOriginalCode(originalAsset.getOriginalCode());
        List<OriginalAssetDO> originalAssetList = this.selectOriginalAssetList(originalAssetQuery);
        if (CollectionUtils.isNotEmpty(originalAssetList)) {
            if (originalAssetList.size() != 1) {
                throw new ServiceException("原始编码存在多个，请核对后提交");
            }
            OriginalAssetDO originalAssetDO = originalAssetList.get(0);
            if (ObjectUtils.isNotEmpty(originalAsset.getId())) {
                if (!originalAssetDO.getId().equals(originalAsset.getId())) {
                    throw new ServiceException("编辑数据id错误");
                }
            }
        }
        if (ObjectUtils.isNotEmpty(originalAsset.getId())) {
            OriginalAssetDO originalAssetDO = this.selectOriginalAssetById(originalAsset.getId());
            if (ObjectUtils.isEmpty(originalAssetDO)) {
                throw new ServiceException("编辑数据不存在");
            }
        }
        CategoryDO categoryDO = categoryService.selectCategoryById(originalAsset.getCategoryId());
        if (ObjectUtils.isEmpty(categoryDO)) {
            throw new ServiceException("分类数据不存在");
        }
        originalAsset.setCategoryName(categoryDO.getCategoryName());

        if (ObjectUtils.isNotEmpty(originalAsset.getLocationId())) {
            LocationDO locationPureData = locationService.selectLocationById(originalAsset.getLocationId());
            if (ObjectUtils.isEmpty(locationPureData)
                || !locationPureData.getDeptId().equals(originalAsset.getDeptId())) {
                throw new ServiceException("存放地点id错误");
            }
            originalAsset.setLocationName(locationPureData.getLocationName());
        }

        List<Long> departmentIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(originalAsset.getManagedDeptId())) {
            departmentIds.add(originalAsset.getManagedDeptId());
        }
        if (ObjectUtils.isNotEmpty(originalAsset.getUsingDeptId())) {
            departmentIds.add(originalAsset.getUsingDeptId());
        }
        if (CollectionUtils.isNotEmpty(departmentIds)) {
            List<DepartmentDO> deptPureDataList = departmentService.selectDepartmentByIds(departmentIds);
            if (CollectionUtils.isEmpty(deptPureDataList)) {
                throw new ServiceException("管理部门和使用部门id错误");
            }
            if (deptPureDataList.stream().anyMatch(e -> !e.getDeptId().equals(originalAsset.getDeptId()))) {
                throw new ServiceException("管理部门和使用部门存在不是当前所属部门的数据");
            }
            Map<Long, DepartmentDO> deptMap = deptPureDataList.stream()
                .collect(toMap(DepartmentDO::getId, Function.identity(), (k1, k2) -> k2));
            if (deptMap.containsKey(originalAsset.getManagedDeptId())) {
                originalAsset.setManagedDeptName(deptMap.get(originalAsset.getManagedDeptId()).getDepartmentName());
            }
            if (deptMap.containsKey(originalAsset.getUsingDeptId())) {
                originalAsset.setUsingDeptName(deptMap.get(originalAsset.getUsingDeptId()).getDepartmentName());
            }
        }

        List<Long> employeeIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(originalAsset.getManagedEmpId())) {
            employeeIds.add(originalAsset.getManagedEmpId());
        }
        if (ObjectUtils.isNotEmpty(originalAsset.getUsingEmpId())) {
            employeeIds.add(originalAsset.getUsingEmpId());
        }
        if (CollectionUtils.isNotEmpty(employeeIds)) {
            List<EmployeeDO> empPureDataList = employeeService.selectEmployeeByIds(employeeIds);
            if (CollectionUtils.isEmpty(empPureDataList)) {
                throw new ServiceException("管理员工和使用员工id错误");
            }
            if (empPureDataList.stream().anyMatch(e -> !e.getDeptId().equals(originalAsset.getDeptId()))) {
                throw new ServiceException("管理员工和使用员工存在不是当前所属部门的数据");
            }
            Map<Long, EmployeeDO> empMap = empPureDataList.stream()
                .collect(toMap(EmployeeDO::getId, Function.identity(), (k1, k2) -> k2));
            if (empMap.containsKey(originalAsset.getManagedEmpId())) {
                originalAsset.setManagedEmpName(empMap.get(originalAsset.getManagedEmpId()).getEmployeeName());
            }
            if (empMap.containsKey(originalAsset.getUsingEmpId())) {
                originalAsset.setUsingEmpName(empMap.get(originalAsset.getUsingEmpId()).getEmployeeName());
            }
        }

    }
}