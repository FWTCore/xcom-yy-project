package com.ruoyi.business.repository.impl;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.repository.AssetDataRepository;
import com.ruoyi.common.repository.AbstractMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 资产数据mongodb Repository
 *
 * @author xcom
 * @date 2025/11/18
 */

@Repository
@Slf4j
public class AssetDataRepositoryImpl extends AbstractMongoRepository<AssetDataDO> implements AssetDataRepository {
}
