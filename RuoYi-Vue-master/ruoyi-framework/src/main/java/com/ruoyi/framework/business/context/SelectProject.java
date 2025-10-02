package com.ruoyi.framework.business.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 选择项目
 *
 * @author xcom
 * @date 2025/10/2
 */
@Data
public class SelectProject implements Serializable {

    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 项目所属公司id
     */
    private Long projectCompanyId;
}
