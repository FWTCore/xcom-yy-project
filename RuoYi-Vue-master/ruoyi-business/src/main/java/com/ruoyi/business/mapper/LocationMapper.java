package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Location;
import com.ruoyi.business.domain.model.response.LocationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 存放地点;(location)表数据库访问层
 *
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface LocationMapper {
    /**
     * 查询存放地点
     *
     * @param id 存放地点主键
     * @return 存放地点
     */
    LocationDO selectLocationById(Long id);

    /**
     * 查询存放地点
     *
     * @param ids 存放地点主键
     * @return 存放地点
     */
    List<LocationDO> selectLocationByIds(List<Long> ids);

    /**
     * 查询存放地点列表
     *
     * @param location 存放地点
     * @return 存放地点集合
     */
    List<LocationDO> selectLocationList(Location location);
    /**
     * 查询存放地点列表
     *
     * @param location 存放地点
     * @return 存放地点集合
     */
    List<LocationDetailVO> selectLocationDetailList(Location location);

    /**
     * 新增存放地点
     *
     * @param location 存放地点
     * @return 结果
     */
    int insertLocation(LocationDO location);

    /**
     * 修改存放地点
     *
     * @param location 存放地点
     * @return 结果
     */
    int updateLocation(LocationDO location);

    /**
     * 删除存放地点
     *
     * @param id 存放地点主键
     * @return 结果
     */
    int deleteLocationById(Long id);

    /**
     * 批量删除存放地点
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteLocationByIds(Long[] ids);
}