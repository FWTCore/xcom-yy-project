package com.ruoyi.web.controller.business.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传
 *
 * @author xcom
 * @date 2025/10/6
 */

@ApiModel(value = "文件上传返回")
@Data
public class UploadFileVO implements Serializable {
    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "文件名称")
    private String            fileName;
    @ApiModelProperty(value = "文件地址")
    private String            fileUrl;
    @ApiModelProperty(value = "文件地址")
    private String            fileFullUrl;
}
