package com.ruoyi.web.controller.business.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * App登录用户信息
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "App登录用户信息")
@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long              userId;
    /**
     * 用户名称,;
     */
    @ApiModelProperty(value = "用户名称")
    private String            userName;
    /**
     * 头像,;
     */
    @ApiModelProperty(value = "头像")
    private String            avatar;
    /**
     * 手机号,;
     */
    private String            mobilePhone;
    /**
     * 系统公司id,;
     */
    @ApiModelProperty(value = "系统公司id")
    private Long              companyId;
    /**
     * 公司名称,;
     */
    @ApiModelProperty(value = "公司名称")
    private String            companyName;

}