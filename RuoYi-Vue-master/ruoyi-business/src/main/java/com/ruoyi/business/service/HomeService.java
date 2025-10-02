package com.ruoyi.business.service;

import com.ruoyi.business.domain.model.HomeAssetStatsVO;

/**
 * HomeService
 *
 * @author xcom
 * @date 2025/10/2
 */

public interface HomeService {

    /**
     * 获取首页物资统计
     * @return
     */
    HomeAssetStatsVO getHomeAssetStats();
}
