package com.ruoyi.web.core.interceptor;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户上下文处理
 * 拦截器
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@Component
public class ProjectContextInterceptor implements HandlerInterceptor {

    @Resource
    private TokenService   tokenService;

    @Resource
    private ProjectService projectService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String projectIdStr = request.getHeader(Constants.HEADER_APP_PROJECT_ID);
        if (StringUtils.isBlank(projectIdStr)) {
            return true;
        }
        try {
            long projectId = Long.parseLong(projectIdStr);
            LoginUser loginUser = SecurityUtils.getLoginUser();
            Project project = new Project();
            project.setId(projectId);
            List<ProjectDO> projectList = projectService.selectProjectList(project);
            if (CollectionUtils.isEmpty(projectList)) {
                throw new ServiceException("项目访问非法", HttpStatus.BAD_REQUEST);
            }
            ProjectDO projectDO = projectList.get(0);
            loginUser.setProjectId(projectId);
            loginUser.setProjectCompanyId(projectDO.getDeptId());
            tokenService.refreshToken(loginUser);

        } catch (Exception ignored) {
            throw new ServiceException("项目访问非法", HttpStatus.BAD_REQUEST);
        }
        return true;
    }

}
