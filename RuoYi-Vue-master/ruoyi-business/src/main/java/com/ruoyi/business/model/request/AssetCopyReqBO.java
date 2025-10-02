package com.ruoyi.business.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 资产复制请求模型
 *
 * @author xcom
 * @date 2025/9/21
 */
@Data
public class AssetCopyReqBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 数据id
     */
    private Long              id;
    /**
     * 系统公司id,;
     */
    private Long              deptId;

    /**
     * 项目id,;
     */
    private Long              projectId;
    /**
     * 复制数量
     */
    private Integer           copyNum;

}
