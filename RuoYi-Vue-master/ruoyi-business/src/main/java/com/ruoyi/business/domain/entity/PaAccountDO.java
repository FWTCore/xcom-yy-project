package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台账号;pa_account数据表的DO对象
 *
 * @author xcom
 * @date 2026/1/2
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaAccountDO extends BaseEntityDO {

    /**
     * PA平台账号
     */
    private String paAccount;

    /**
     * PA平台名称
     */
    private String paUserName;

    /**
     * PA平台描述
     */
    private String paAccountDescription;

    /**
     * PA平台AK
     */
    private String paAccessKey;

    /**
     * PA平台SK
     */
    private String paApiKey;

    /**
     * 状态
     */
    private Integer paStatus;

}