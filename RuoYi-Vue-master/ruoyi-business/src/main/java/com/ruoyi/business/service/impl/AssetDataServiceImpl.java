package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.repository.AssetDataRepository;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.common.core.domain.AggregationMongodbQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * 资产数据mongodb service
 *
 * @author xcom
 * @date 2025/11/18
 */
@Service
public class AssetDataServiceImpl implements AssetDataService {

    @Resource
    private AssetDataRepository assetDataRepository;

    @Resource
    private DepartmentService   departmentService;

    @Resource
    private LocationService     locationService;

    @Override
    public AssetDataDO getAssetDataByAssetId(Long assetId) {
        return assetDataRepository.getOne(p -> {
            p.addCriteria(Criteria.where("asset_id").is(assetId));
        });
    }

    @Override
    public boolean addAssetData(AssetDataDO dataDO) {
        if (ObjectUtils.isEmpty(dataDO) || ObjectUtils.isEmpty(dataDO.getAssetId())) {
            return false;
        }
        assetDataRepository.insert(dataDO);
        return true;
    }

    @Override
    public boolean updateAssetNameMetrics(Long projectId, String assetName, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || StringUtils.isEmpty(assetName)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("asset_name_count", totalCount);
            update.set("asset_name_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("asset_name").is(assetName);
            criteria.and("delete_flag").is(false);
            query.addCriteria(criteria);
        });
        return true;
    }

    @Override
    public boolean updateLocationMetrics(Long projectId, Long locationId, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || ObjectUtils.isEmpty(locationId)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("location_count", totalCount);
            update.set("location_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("location_id").is(locationId);
            criteria.and("delete_flag").is(false);
            query.addCriteria(criteria);
        });
        return true;
    }

    @Override
    public boolean updateUsingDeptMetrics(Long projectId, Long usingDeptId, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || ObjectUtils.isEmpty(usingDeptId)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("using_dept_count", totalCount);
            update.set("using_dept_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("using_dept_id").is(usingDeptId);
            criteria.and("delete_flag").is(false);
            query.addCriteria(criteria);
        });
        return true;
    }

    @Override
    public List<AssetMetricsVO> listPhysicalBord(AssetBordReqBO assetBordReqBO) {
        if (assetBordReqBO.getMetricsType().compareTo(1) == 0) {
            return listAssetNameMetrics(assetBordReqBO);
        } else if (assetBordReqBO.getMetricsType().compareTo(2) == 0) {
            return listUsingDepMetrics(assetBordReqBO);
        } else {
            return listLocationMetrics(assetBordReqBO);
        }
    }

    /**
     * 查询名称指标
     *
     * @param assetBordReqBO
     * @return
     */
    private List<AssetMetricsVO> listAssetNameMetrics(AssetBordReqBO assetBordReqBO) {
        AggregationMongodbQuery<AssetMetricsVO> query = new AggregationMongodbQuery<>(AssetMetricsVO.class);
        Criteria criteria = Criteria.where("delete_flag").is(false).and("dept_id").is(assetBordReqBO.getDeptId())
            .and("project_id").is(assetBordReqBO.getProjectId());
        query.setCriteria(criteria);

        List<String> fields = new ArrayList<>();
        fields.add("asset_name");
        fields.add("asset_name_count");
        fields.add("asset_name_check_count");
        query.setQueryFields(fields);

        ProjectionOperation projectStage = Aggregation.project().and("_id.asset_name").as("metricsName")
            .and("_id.asset_name_count").as("totalCount").and("_id.asset_name_check_count").as("checkCount")
            .andExclude("_id");
        query.setProjectionOperation(projectStage);

        SortOperation sortStage;
        if (BooleanUtils.isFalse(assetBordReqBO.getOrderFlag())) {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.ASC, "asset_name_check_count"));
        } else {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "asset_name_check_count"));
        }
        query.setSortOperation(sortStage);
        return assetDataRepository.listAggregationData(query);
    }

    /**
     * 查询使用部门指标
     *
     * @param assetBordReqBO
     * @return
     */
    private List<AssetMetricsVO> listUsingDepMetrics(AssetBordReqBO assetBordReqBO) {
        AggregationMongodbQuery<AssetMetricsVO> query = new AggregationMongodbQuery<>(AssetMetricsVO.class);
        Criteria criteria = Criteria.where("delete_flag").is(false).and("dept_id").is(assetBordReqBO.getDeptId())
            .and("project_id").is(assetBordReqBO.getProjectId());
        query.setCriteria(criteria);

        List<String> fields = new ArrayList<>();
        fields.add("using_dept_id");
        fields.add("using_dept_count");
        fields.add("using_dept_check_count");
        query.setQueryFields(fields);

        ProjectionOperation projectStage = Aggregation.project().and("_id.using_dept_id").as("metricsId")
            .and("_id.using_dept_count").as("totalCount").and("_id.using_dept_check_count").as("checkCount")
            .andExclude("_id");
        query.setProjectionOperation(projectStage);

        SortOperation sortStage;
        if (BooleanUtils.isFalse(assetBordReqBO.getOrderFlag())) {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.ASC, "using_dept_check_count"));
        } else {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "using_dept_check_count"));
        }
        query.setSortOperation(sortStage);
        List<AssetMetricsVO> resultData = assetDataRepository.listAggregationData(query);
        if (CollectionUtils.isNotEmpty(resultData)) {
            List<Long> usingDeptIds = resultData.stream().map(AssetMetricsVO::getMetricsId)
                .filter(ObjectUtils::isNotEmpty).distinct().collect(Collectors.toList());
            List<DepartmentDO> departmentDOList = departmentService.selectDepartmentByIds(usingDeptIds);
            if (CollectionUtils.isNotEmpty(departmentDOList)) {
                Map<Long, DepartmentDO> departmentMap = departmentDOList.stream()
                    .collect(toMap(DepartmentDO::getId, Function.identity(), (k1, k2) -> k2));
                for (AssetMetricsVO resultDatum : resultData) {
                    if (!departmentMap.containsKey(resultDatum.getMetricsId())) {
                        continue;
                    }
                    DepartmentDO departmentDO = departmentMap.get(resultDatum.getMetricsId());
                    resultDatum.setMetricsName(departmentDO.getDepartmentName());
                }
            }
        }

        return resultData;
    }

    /**
     * 查询存放地点指标
     *
     * @param assetBordReqBO
     * @return
     */
    private List<AssetMetricsVO> listLocationMetrics(AssetBordReqBO assetBordReqBO) {
        AggregationMongodbQuery<AssetMetricsVO> query = new AggregationMongodbQuery<>(AssetMetricsVO.class);
        Criteria criteria = Criteria.where("delete_flag").is(false).and("dept_id").is(assetBordReqBO.getDeptId())
            .and("project_id").is(assetBordReqBO.getProjectId());
        query.setCriteria(criteria);

        List<String> fields = new ArrayList<>();
        fields.add("location_id");
        fields.add("location_count");
        fields.add("location_check_count");
        query.setQueryFields(fields);

        ProjectionOperation projectStage = Aggregation.project().and("_id.location_id").as("metricsId")
            .and("_id.location_count").as("totalCount").and("_id.location_check_count").as("checkCount")
            .andExclude("_id");
        query.setProjectionOperation(projectStage);

        SortOperation sortStage;
        if (BooleanUtils.isFalse(assetBordReqBO.getOrderFlag())) {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.ASC, "location_check_count"));
        } else {
            sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "location_check_count"));
        }
        query.setSortOperation(sortStage);
        List<AssetMetricsVO> resultData = assetDataRepository.listAggregationData(query);
        if (CollectionUtils.isNotEmpty(resultData)) {
            List<Long> locationIds = resultData.stream().map(AssetMetricsVO::getMetricsId)
                .filter(ObjectUtils::isNotEmpty).distinct().collect(Collectors.toList());
            List<LocationDO> locationDOS = locationService.selectLocationByIds(locationIds);
            if (CollectionUtils.isNotEmpty(locationDOS)) {
                Map<Long, LocationDO> locationMap = locationDOS.stream()
                    .collect(toMap(LocationDO::getId, Function.identity(), (k1, k2) -> k2));
                for (AssetMetricsVO resultDatum : resultData) {
                    if (!locationMap.containsKey(resultDatum.getMetricsId())) {
                        continue;
                    }
                    LocationDO locationDO = locationMap.get(resultDatum.getMetricsId());
                    resultDatum.setMetricsName(locationDO.getLocationName());
                }
            }
        }
        return resultData;
    }
}
