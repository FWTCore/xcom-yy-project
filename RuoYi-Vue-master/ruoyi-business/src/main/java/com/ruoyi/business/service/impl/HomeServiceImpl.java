package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.vo.HomeAssetStatsVO;
import com.ruoyi.business.mapper.AssetMapper;
import com.ruoyi.business.service.HomeService;
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
        //        return assetMapper.getHomeAssetStats();
        return null;
    }
}
