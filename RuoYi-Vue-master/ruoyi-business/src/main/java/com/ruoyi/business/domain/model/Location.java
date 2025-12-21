package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.LocationDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 存放地点;location数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Location extends LocationDO {

    private String searchName;

}