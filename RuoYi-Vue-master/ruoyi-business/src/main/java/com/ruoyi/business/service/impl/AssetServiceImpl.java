package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.AssetDetailVO;
import com.ruoyi.business.domain.model.HomeAssetStatsVO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.OriginalAssetDetailVO;
import com.ruoyi.business.domain.model.ProjectDetailVO;
import com.ruoyi.business.mapper.AssetMapper;
import com.ruoyi.business.model.convert.AssetConvert;
import com.ruoyi.business.model.request.AssetCopyReqBO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.BrandService;
import com.ruoyi.business.service.CategoryService;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.business.service.EmployeeService;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Asset Service
 *
 * @author xcom
 * @date 2025/9/20
 */

@Service
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper          assetMapper;
    @Resource
    private CategoryService      categoryService;
    @Resource
    private BrandService         brandService;
    @Resource
    private MaterialService      materialService;
    @Resource
    private LocationService      locationService;
    @Resource
    private DepartmentService    departmentService;
    @Resource
    private EmployeeService      employeeService;
    @Resource
    private ProjectService       projectService;
    @Resource
    private OriginalAssetService originalAssetService;

    @Override
    public HomeAssetStatsVO getHomeAssetStats(Long projectId) {
        return assetMapper.getHomeAssetStats(projectId);
    }

    /**
     * 查询资产
     *
     * @param id 资产主键
     * @return 资产
     */
    @Override
    public AssetDO selectAssetById(Long id) {
        return assetMapper.selectAssetById(id);
    }

    /**
     * 查询资产
     *
     * @param ids 资产主键
     * @return 资产
     */
    @Override
    public List<AssetDO> selectAssetByIds(List<Long> ids) {
        return assetMapper.selectAssetByIds(ids);
    }

    /**
     * 查询资产列表
     *
     * @param asset 资产
     * @return 资产
     */
    @Override
    @DataScope(deptAlias = "a", projectAlias = "a")
    public List<AssetDO> selectAssetList(Asset asset) {
        return assetMapper.selectAssetList(asset);
    }

    @Override
    @DataScope(deptAlias = "a", projectAlias = "a")
    public List<AssetDetailVO> selectAssetDetailList(Asset asset) {
        return assetMapper.selectAssetDetailList(asset);
    }

    /**
     * 新增资产
     *
     * @param asset 资产
     * @return 结果
     */
    @Override
    public int insertAsset(AssetDO asset) {
        asset.setBaseFieldValue();
        return assetMapper.insertAsset(asset);
    }

    /**
     * 修改资产
     *
     * @param asset 资产
     * @return 结果
     */
    @Override
    public int updateAsset(AssetDO asset) {
        asset.setUpdatedFieldValue();
        return assetMapper.updateAsset(asset);
    }

    @Override
    public int upsetAsset(AssetDO asset) {
        if (Objects.isNull(asset.getProjectId()) || Objects.isNull(asset.getDeptId())) {
            throw new ServiceException("项目和单位数据异常");
        }
        if (StringUtils.isBlank(asset.getTemporaryCode()) && StringUtils.isBlank(asset.getOriginalCode())) {
            throw new ServiceException("临时编码和原始编码不能同时为空");
        }
        ProjectDetailVO projectDetailVO = projectService.selectProjectById(asset.getProjectId());
        if (ObjectUtils.isEmpty(projectDetailVO)) {
            throw new ServiceException("项目不存在");
        }
        if (!asset.getDeptId().equals(projectDetailVO.getDeptId())) {
            throw new ServiceException("项目所属单位不存在");
        }

        validAndSetField(asset);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        asset.setCollectorUserId(loginUser.getUserId());
        asset.setCollectorUserName(loginUser.getUser().getNickName());
        asset.setCollectorTime(LocalDateTime.now());
        if (ObjectUtils.isEmpty(asset.getId())) {
            return this.insertAsset(asset);
        } else {
            return this.updateAsset(asset);
        }
    }

    /**
     * 批量删除资产
     *
     * @param ids 需要删除的资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetByIds(Long[] ids) {
        return assetMapper.deleteAssetByIds(ids);
    }

    /**
     * 删除资产信息
     *
     * @param id 资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetById(Long id) {
        return assetMapper.deleteAssetById(id);
    }

    @Override
    public Boolean copyData(AssetCopyReqBO copyReqBO) {
        if (ObjectUtils.isEmpty(copyReqBO.getProjectId()) || ObjectUtils.isEmpty((copyReqBO.getDeptId()))) {
            throw new ServiceException("项目和单位数据异常");
        }
        AssetDO pureData = this.selectAssetById(copyReqBO.getId());
        if (ObjectUtils.isEmpty(pureData)) {
            throw new ServiceException("复制的资产不存在");
        }
        if (!copyReqBO.getDeptId().equals(pureData.getDeptId())
            || !copyReqBO.getProjectId().equals(pureData.getProjectId())) {
            throw new ServiceException("复制的资产不存在");
        }
        Asset searchAsset = new Asset();
        searchAsset.setDeptId(pureData.getDeptId());
        searchAsset.setProjectId(pureData.getProjectId());
        String temporaryCode = pureData.getTemporaryCode();
        String assetCodeSeparator = pureData.getTemporaryCode().substring(pureData.getTemporaryCode().length() - 2,
            pureData.getTemporaryCode().length() - 1);
        if (assetCodeSeparator.equals("-")) {
            temporaryCode = pureData.getTemporaryCode().substring(0, pureData.getTemporaryCode().length() - 2);
        }
        searchAsset.setLeftSearchTemporaryCode(temporaryCode);
        List<AssetDO> assetList = this.selectAssetList(searchAsset);
        if (CollectionUtils.isEmpty(assetList)) {
            throw new ServiceException("复制的资产不存在");
        }
        assetList.sort((o1, o2) -> {
            String[] parts1 = o1.getTemporaryCode().split("-");
            String[] parts2 = o2.getTemporaryCode().split("-");

            if (parts2.length != parts1.length) {
                return Integer.compare(parts2.length, parts1.length);
            }

            int num1 = Integer.parseInt(parts1[parts1.length - 1]);
            int num2 = Integer.parseInt(parts2[parts2.length - 1]);
            return Integer.compare(num2, num1);
        });

        String maxTemporaryCode = assetList.get(0).getTemporaryCode();
        List<String> codeList = generateAssetCode(maxTemporaryCode, copyReqBO.getCopyNum());
        if (CollectionUtils.isEmpty(codeList)) {
            throw new ServiceException("资产复制生成编号异常");
        }
        for (String code : codeList) {
            //清除id，用来新增
            pureData.setId(null);
            pureData.setTemporaryCode(code);
            pureData.setBaseFieldValue();
            LoginUser loginUser = SecurityUtils.getLoginUser();
            pureData.setCollectorUserId(loginUser.getUserId());
            pureData.setCollectorUserName(loginUser.getUser().getNickName());
            pureData.setCollectorTime(LocalDateTime.now());
            this.insertAsset(pureData);
        }
        return true;
    }

    @Override
    public AssetDetailVO selectAssetByCode(String code, Long projectId) {
        Asset assetQuery = new Asset();
        assetQuery.setProjectId(projectId);
        assetQuery.setMatchCode(code);
        List<AssetDetailVO> assetDetailList = this.selectAssetDetailList(assetQuery);
        if (CollectionUtils.isNotEmpty(assetDetailList)) {
            AssetDetailVO matchData = assetDetailList.stream().filter(e -> e.getTemporaryCode().equals(code))
                .findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(matchData)) {
                return matchData;
            }
            matchData = assetDetailList.stream().filter(e -> e.getOriginalCode().equals(code)).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(matchData)) {
                return matchData;
            }
        }
        OriginalAsset originalAssetQuery = new OriginalAsset();
        originalAssetQuery.setProjectId(projectId);
        originalAssetQuery.setOriginalCode(code);
        List<OriginalAssetDetailVO> originalAssetDetailList = originalAssetService
            .selectOriginalAssetDetailList(originalAssetQuery);
        if (CollectionUtils.isEmpty(originalAssetDetailList)) {
            AssetDetailVO resultData = new AssetDetailVO();
            resultData.setTemporaryCode(code);
            resultData.setOriginalCode(code);
            return resultData;
        }
        AssetDetailVO resultData = AssetConvert.INSTANCE.toAssetDetailVO(originalAssetDetailList.get(0));
        resultData.setId(null);
        return resultData;
    }

    /**
     * 检查并设置值
     * @param data
     */
    private void validAndSetField(AssetDO data) {
        Asset searchAsset = new Asset();
        searchAsset.setDeptId(data.getDeptId());
        searchAsset.setProjectId(data.getProjectId());
        if (StringUtils.isNotBlank(data.getTemporaryCode())) {
            searchAsset.setTemporaryCode(data.getTemporaryCode());
        } else {
            searchAsset.setOriginalCode(data.getOriginalCode());
        }
        List<AssetDO> assetList = this.selectAssetList(searchAsset);
        if (CollectionUtils.isNotEmpty(assetList)) {
            if (assetList.size() > 1) {
                throw new ServiceException("临时/原始编码存在多个，请核对数据");
            }
            AssetDO existAsset = assetList.get(0);
            data.setId(existAsset.getId());
        } else {
            data.setId(null);
        }
        CategoryDO categoryDO = categoryService.selectCategoryById(data.getCategoryId());
        if (ObjectUtils.isEmpty(categoryDO)) {
            throw new ServiceException("门类不存在");
        }
        data.setCategoryName(categoryDO.getCategoryName());

        if (BooleanUtils.isTrue(categoryDO.getHasBrand()) && StringUtils.isBlank(data.getBrandName())
            && ObjectUtils.isEmpty(data.getBrandId())) {
            throw new ServiceException(String.format("门类【%s】要求品牌必填", data.getCategoryName()));
        }
        if (StringUtils.isNotBlank(data.getBrandName())) {
            Long brandId = brandService.insertNotExistBrand(data.getCategoryId(), data.getBrandName());
            data.setBrandId(brandId);
        } else if (ObjectUtils.isNotEmpty(data.getBrandId())) {
            BrandDO brandDO = brandService.selectBrandById(data.getBrandId());
            if (ObjectUtils.isEmpty(brandDO)) {
                throw new ServiceException("品牌不存在必填");
            }
            data.setBrandName(brandDO.getBrandName());
        }

        if (BooleanUtils.isTrue(categoryDO.getHasMaterialName()) && StringUtils.isBlank(data.getAssetName())) {
            throw new ServiceException(String.format("门类【%s】要求资产名称必填", data.getCategoryName()));
        }
        if (BooleanUtils.isTrue(categoryDO.getHasSpecification()) && StringUtils.isBlank(data.getSpecification())) {
            throw new ServiceException(String.format("门类【%s】要求规格型号必填", data.getCategoryName()));
        }

        if (StringUtils.isNotBlank(data.getAssetName()) || StringUtils.isNotBlank(data.getSpecification())) {
            MaterialDO materialDO = new MaterialDO();
            materialDO.setCategoryId(data.getCategoryId());
            materialDO.setBrandId(data.getBrandId());
            materialDO.setMaterialName(data.getAssetName());
            materialDO.setSpecification(data.getSpecification());
            materialDO.setMainImageUrl(data.getMainImageUrl());
            materialDO.setMainImageName(data.getMainImageName());
            materialDO.setImageUrl(data.getImageUrl());
            materialDO.setImageUrlName(data.getImageUrlName());
            Long materialId = materialService.insertNotExistMaterial(materialDO);
            data.setMaterialId(materialId);
        }
        if (ObjectUtils.isNotEmpty(data.getLocationId())) {
            LocationDO locationPureData = locationService.selectLocationById(data.getLocationId());
            if (ObjectUtils.isEmpty(locationPureData) || !locationPureData.getDeptId().equals(data.getDeptId())) {
                throw new ServiceException("存放地点id错误");
            }
            data.setLocationName(locationPureData.getLocationName());
        }
        List<Long> departmentIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(data.getManagedDeptId())) {
            departmentIds.add(data.getManagedDeptId());
        }
        if (ObjectUtils.isNotEmpty(data.getUsingDeptId())) {
            departmentIds.add(data.getUsingDeptId());
        }
        if (CollectionUtils.isNotEmpty(departmentIds)) {
            List<DepartmentDO> deptPureDataList = departmentService.selectDepartmentByIds(departmentIds);
            if (CollectionUtils.isEmpty(deptPureDataList)) {
                throw new ServiceException("管理部门和使用部门id错误");
            }
            if (deptPureDataList.stream().anyMatch(e -> !e.getDeptId().equals(data.getDeptId()))) {
                throw new ServiceException("管理部门和使用部门存在不是当前所属部门的数据");
            }
            Map<Long, DepartmentDO> deptMap = deptPureDataList.stream()
                .collect(toMap(DepartmentDO::getId, Function.identity(), (k1, k2) -> k2));
            if (deptMap.containsKey(data.getManagedDeptId())) {
                data.setManagedDeptName(deptMap.get(data.getManagedDeptId()).getDepartmentName());
            }
            if (deptMap.containsKey(data.getUsingDeptId())) {
                data.setUsingDeptName(deptMap.get(data.getUsingDeptId()).getDepartmentName());
            }
        } else {
            throw new ServiceException("管理部门和使用部门必填1个");
        }
        List<Long> employeeIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(data.getManagedEmpId())) {
            employeeIds.add(data.getManagedEmpId());
        }
        if (ObjectUtils.isNotEmpty(data.getUsingEmpId())) {
            employeeIds.add(data.getUsingEmpId());
        }
        if (CollectionUtils.isNotEmpty(employeeIds)) {
            List<EmployeeDO> empPureDataList = employeeService.selectEmployeeByIds(employeeIds);
            if (CollectionUtils.isEmpty(empPureDataList)) {
                throw new ServiceException("管理员工和使用员工id错误");
            }
            if (empPureDataList.stream().anyMatch(e -> !e.getDeptId().equals(data.getDeptId()))) {
                throw new ServiceException("管理员工和使用员工存在不是当前所属部门的数据");
            }
            Map<Long, EmployeeDO> empMap = empPureDataList.stream()
                .collect(toMap(EmployeeDO::getId, Function.identity(), (k1, k2) -> k2));
            if (empMap.containsKey(data.getManagedEmpId())) {
                data.setManagedEmpName(empMap.get(data.getManagedEmpId()).getEmployeeName());
            }
            if (empMap.containsKey(data.getUsingEmpId())) {
                data.setUsingEmpName(empMap.get(data.getUsingEmpId()).getEmployeeName());
            }
        } else {
            throw new ServiceException("管理员工和使用员工必须填写1个");
        }
    }

    /**
     * 生成资产code
     * @param temporaryCode
     * @param copyNum
     * @return
     */
    private List<String> generateAssetCode(String temporaryCode, Integer copyNum) {
        if (StringUtils.isBlank(temporaryCode)) {
            throw new ServiceException("复制编码不存在");
        }
        int startIndex = 0;
        String assetCodePrefix = temporaryCode;
        // 判断倒数第二位是否是 -
        if (temporaryCode.length() > 2) {
            String assetCodeSeparator = temporaryCode.substring(temporaryCode.length() - 2, temporaryCode.length() - 1);
            if (assetCodeSeparator.equals("-")) {
                String startStr = temporaryCode.substring(temporaryCode.length() - 1);
                try {
                    startIndex = Integer.parseInt(startStr);
                    if (startIndex > 0) {
                        assetCodePrefix = temporaryCode.substring(0, temporaryCode.length() - 2);
                    }
                } catch (Exception ignored) {

                }
            }
        }
        List<String> resultData = new ArrayList<>();
        for (int i = 1; i <= copyNum; i++) {
            resultData.add(String.format("%s-%s", assetCodePrefix, startIndex + i));
        }
        return resultData;
    }
}
