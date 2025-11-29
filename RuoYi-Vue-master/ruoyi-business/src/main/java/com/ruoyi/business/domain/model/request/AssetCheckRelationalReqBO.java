package com.ruoyi.business.domain.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * AssetCheckRelationalReqBO
 *
 * @author xcom
 * @date 2025/11/29
 */
@Data
public class AssetCheckRelationalReqBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 实物资产id
     */
    private List<Long>        physicalIds;
    /**
     * 账务资产id
     */
    private Long              ledgerId;
}
