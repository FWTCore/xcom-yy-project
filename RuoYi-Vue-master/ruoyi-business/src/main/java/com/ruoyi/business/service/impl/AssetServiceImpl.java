package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.HomeAssetStatsVO;
import com.ruoyi.business.mapper.AssetMapper;
import com.ruoyi.business.model.request.AssetCopyReqBO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.BrandService;
import com.ruoyi.business.service.CategoryService;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.business.service.EmployeeService;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private AssetMapper       assetMapper;
    @Resource
    private CategoryService   categoryService;
    @Resource
    private BrandService      brandService;
    @Resource
    private MaterialService   materialService;
    @Resource
    private LocationService   locationService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService   employeeService;

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
    public List<AssetDO> selectAssetList(Asset asset) {
        return assetMapper.selectAssetList(asset);
    }

    /**
     * 新增资产
     *
     * @param asset 资产
     * @return 结果
     */
    @Override
    public int insertAsset(AssetDO asset) {
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
        if (Objects.isNull(asset.getProjectId()) || Objects.isNull(asset.getDeptId())) {
            throw new ServiceException("项目和公司数据异常");
        }
        validAndSetField(asset);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        asset.setCollectorUserId(loginUser.getUserId());
        asset.setCollectorUserName(loginUser.getUsername());
        asset.setCollectorTime(LocalDateTime.now());
        if (ObjectUtils.isEmpty(asset.getId())) {
            return assetMapper.insertAsset(asset);
        } else {
            return assetMapper.updateAsset(asset);
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
            throw new ServiceException("项目和公司数据异常");
        }
        AssetDO pureData = this.selectAssetById(copyReqBO.getId());
        if (ObjectUtils.isEmpty(pureData)) {
            throw new ServiceException("复制的资产不存在");
        }
        if (!copyReqBO.getDeptId().equals(pureData.getDeptId())
            || !copyReqBO.getProjectId().equals(pureData.getProjectId())) {
            throw new ServiceException("复制的资产不存在");
        }
        List<String> codeList = generateAssetCode(pureData.getTemporaryCode(), copyReqBO.getCopyNum());
        if (CollectionUtils.isEmpty(codeList)) {
            throw new ServiceException("资产复制生成编号异常");
        }
        for (String code : codeList) {
            //清除id，用来新增
            pureData.setId(null);
            pureData.setTemporaryCode(code);
            this.insertAsset(pureData);
        }
        return true;
    }

    /**
     * 检查并设置值
     * @param data
     */
    private void validAndSetField(AssetDO data) {

        Asset searchAsset = new Asset();
        searchAsset.setDeptId(data.getDeptId());
        searchAsset.setProjectId(data.getProjectId());
        searchAsset.setTemporaryCode(data.getTemporaryCode());
        List<AssetDO> assetList = this.selectAssetList(searchAsset);
        if (CollectionUtils.isNotEmpty(assetList)) {
            if (assetList.size() > 1) {
                throw new ServiceException("临时编码存在多个，数据异常");
            }
            AssetDO existAsset = assetList.get(0);
            if (!existAsset.getId().equals(data.getId())) {
                throw new ServiceException("编辑数据id错误");
            }
        }
        CategoryDO categoryDO = categoryService.selectCategoryById(data.getCategoryId());
        if (ObjectUtils.isEmpty(categoryDO)) {
            throw new ServiceException("分类不存在");
        }
        data.setCategoryName(categoryDO.getCategoryName());
        if (BooleanUtils.isTrue(categoryDO.getHasBrand()) && StringUtils.isBlank(data.getBrandName())) {
            throw new ServiceException(String.format("分类【%s】要求品牌必填", data.getCategoryName()));
        }

        if (StringUtils.isNotBlank(data.getBrandName())) {
            Long brandId = brandService.insertNotExistBrand(data.getCategoryId(), data.getBrandName());
            if (ObjectUtils.isEmpty(data.getBrandId())) {
                data.setBrandId(brandId);
            } else {
                if (!brandId.equals(data.getBrandId())) {
                    throw new ServiceException("品牌id错误");
                }
            }
        } else {
            data.setBrandId(null);
        }

        if (BooleanUtils.isTrue(categoryDO.getHasMaterialName()) && StringUtils.isBlank(data.getAssetName())) {
            throw new ServiceException(String.format("分类【%s】要求资产名称必填", data.getCategoryName()));
        }
        if (BooleanUtils.isTrue(categoryDO.getHasSpecification()) && StringUtils.isBlank(data.getSpecification())) {
            throw new ServiceException(String.format("分类【%s】要求规格型号必填", data.getCategoryName()));
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
            if (ObjectUtils.isEmpty(data.getMaterialId())) {
                data.setMaterialId(materialId);
            } else {
                if (!materialId.equals(data.getMaterialId())) {
                    throw new ServiceException("物资商品id错误");
                }
            }
        }

        LocationDO locationPureData = locationService.selectLocationById(data.getLocationId());
        if (ObjectUtils.isEmpty(locationPureData) || !locationPureData.getDeptId().equals(data.getDeptId())) {
            throw new ServiceException("存放地点id错误");
        }
        data.setLocationName(locationPureData.getLocationName());

        List<Long> departmentIds = Arrays.asList(data.getManagedDeptId(), data.getUsingDeptId());
        List<DepartmentDO> deptPureDataList = departmentService.selectDepartmentByIds(departmentIds);
        if (CollectionUtils.isEmpty(deptPureDataList)) {
            throw new ServiceException("管理部门和使用部门id错误");
        }
        Map<Long, DepartmentDO> deptMap = deptPureDataList.stream()
            .collect(toMap(DepartmentDO::getId, Function.identity(), (k1, k2) -> k2));
        if (!deptMap.containsKey(data.getManagedDeptId())) {
            throw new ServiceException("管理部门id错误");
        }
        data.setManagedDeptName(deptMap.get(data.getManagedDeptId()).getDepartmentName());
        if (!deptMap.containsKey(data.getUsingDeptId())) {
            throw new ServiceException("使用部门id错误");
        }
        data.setUsingDeptName(deptMap.get(data.getUsingDeptId()).getDepartmentName());

        List<Long> employeeIds = Arrays.asList(data.getManagedEmpId(), data.getUsingEmpId());
        List<EmployeeDO> empPureDataList = employeeService.selectEmployeeByIds(employeeIds);
        if (CollectionUtils.isEmpty(empPureDataList)) {
            throw new ServiceException("管理员工和使用员工id错误");
        }
        Map<Long, EmployeeDO> empMap = empPureDataList.stream()
            .collect(toMap(EmployeeDO::getId, Function.identity(), (k1, k2) -> k2));

        if (!deptMap.containsKey(data.getManagedEmpId())) {
            throw new ServiceException("管理员工id错误");
        }
        data.setManagedEmpName(empMap.get(data.getManagedEmpId()).getEmployeeName());
        if (!deptMap.containsKey(data.getUsingEmpId())) {
            throw new ServiceException("使用员工id错误");
        }
        data.setUsingEmpName(empMap.get(data.getUsingEmpId()).getEmployeeName());

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
