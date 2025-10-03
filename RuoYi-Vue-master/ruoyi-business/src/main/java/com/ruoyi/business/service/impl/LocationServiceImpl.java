package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Location;
import com.ruoyi.business.mapper.LocationMapper;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Location Service
 *
 * @author xcom
 * @date 2025/9/20
 */

@Service
public class LocationServiceImpl implements LocationService {

    @Resource
    private LocationMapper locationMapper;

    /**
     * 查询存放地点
     *
     * @param id 存放地点主键
     * @return 存放地点
     */
    @Override
    public LocationDO selectLocationById(Long id) {
        return locationMapper.selectLocationById(id);
    }

    /**
     * 查询存放地点
     *
     * @param ids 存放地点主键
     * @return 存放地点
     */
    @Override
    public List<LocationDO> selectLocationByIds(List<Long> ids) {
        return locationMapper.selectLocationByIds(ids);
    }

    /**
     * 查询存放地点列表
     *
     * @param location 存放地点
     * @return 存放地点
     */
    @Override
    public List<LocationDO> selectLocationList(Location location) {
        return locationMapper.selectLocationList(location);
    }

    /**
     * 新增存放地点
     *
     * @param location 存放地点
     * @return 结果
     */
    @Override
    public int insertLocation(LocationDO location) {
        location.setBaseFieldValue();
        return locationMapper.insertLocation(location);
    }

    /**
     * 修改存放地点
     *
     * @param location 存放地点
     * @return 结果
     */
    @Override
    public int updateLocation(LocationDO location) {
        location.setUpdatedFieldValue();
        return locationMapper.updateLocation(location);
    }

    /**
     * 批量删除存放地点
     *
     * @param ids 需要删除的存放地点主键
     * @return 结果
     */
    @Override
    public int deleteLocationByIds(Long[] ids) {
        return locationMapper.deleteLocationByIds(ids);
    }

    /**
     * 删除存放地点信息
     *
     * @param id 存放地点主键
     * @return 结果
     */
    @Override
    public int deleteLocationById(Long id) {
        return locationMapper.deleteLocationById(id);
    }

    @Override
    public Boolean upsetData(LocationDO data) {
        Location location = new Location();
        location.setDeptId(data.getDeptId());
        location.setLocationName(data.getLocationName());
        List<LocationDO> existNameData = this.selectLocationList(location);
        if (ObjectUtils.isEmpty(data.getId())) {
            if (CollectionUtils.isNotEmpty(existNameData)) {
                throw new ServiceException("存放地点名称已存在");
            }
            this.insertLocation(data);
        } else {
            if (CollectionUtils.isNotEmpty(existNameData)) {
                if (existNameData.size() > 1) {
                    throw new ServiceException("存放地点名称已存在");
                }
                LocationDO existNameObj = existNameData.get(0);
                if (!existNameObj.getId().equals(data.getId())) {
                    throw new ServiceException("存放地点名称已存在");
                }
            }
            LocationDO existNameObj = this.selectLocationById(data.getId());
            if (ObjectUtils.isEmpty(existNameObj)) {
                throw new ServiceException("编辑的存放地点不存在");
            }
            this.updateLocation(data);
        }
        return true;
    }
}
