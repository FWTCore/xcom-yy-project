package com.ruoyi.business.service.impl;

import com.ruoyi.business.model.response.HomeAssetStatsVO;
import com.ruoyi.business.mapper.AssetMapper;
import com.ruoyi.business.service.HomeService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Home Service
 *
 * @author xcom
 * @date 2025/10/2
 */

@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private AssetMapper assetMapper;

    @Override
    public HomeAssetStatsVO getHomeAssetStats() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (ObjectUtils.isEmpty(loginUser.getProjectCompanyId())) {
            throw new ServiceException("没有权限访问项目数据！");
        }
        return assetMapper.getHomeAssetStats(loginUser.getProjectCompanyId());
    }
}
