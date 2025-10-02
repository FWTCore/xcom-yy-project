package com.ruoyi.web.controller.business;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.web.controller.business.response.LoginUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * App-账户管理
 *
 * @author xcom
 * @date 2025/9/20
 */

@Slf4j
@RestController
@Api(tags = "App-账户管理")
@RequestMapping("/m/account")
public class AppAccountController extends BaseController {

    @Resource
    private SysLoginService sysLoginService;

    @ApiOperation("App-账户登录")
    @PostMapping(value = "/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = sysLoginService.loginNoCaptcha(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("App-获取登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public AjaxResult getUserInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        LoginUserVO result = new LoginUserVO();
        result.setUserId(user.getUserId());
        result.setAvatar(user.getAvatar());
        result.setMobilePhone(user.getPhonenumber());
        result.setCompanyId(user.getDeptId());
        result.setCompanyName(user.getDept().getDeptName());
        return success(result);
    }

    // 退出 /logout
    // 修改密码 system/user/profile/updatePwd

}
