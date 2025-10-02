package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Location;

import java.util.List;

/**
 * Location Service
 *
 * @author xcom
 * @date 2025/9/20
 */

public interface LocationService {

    /**
     * 查询存放地点
     *
     * @param id 存放地点主键
     * @return 存放地点
     */
    LocationDO selectLocationById(Long id);

    /**
     * 查询分类
     *
     * @param ids  存放地点主键
     * @return 分类
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
     * 批量删除存放地点
     *
     * @param ids 需要删除的存放地点主键集合
     * @return 结果
     */
    int deleteLocationByIds(Long[] ids);

    /**
     * 删除存放地点信息
     *
     * @param id 存放地点主键
     * @return 结果
     */
    int deleteLocationById(Long id);

    /**
     * 新增、更新数据
     * @param data
     * @return
     */
    Boolean upsetData(LocationDO data);
}