package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.LocationDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 存放地点 明细
 *
 * @author xcom
 * @date 2025/10/5
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LocationDetailVO extends LocationDO {
    private String deptName;
}
