package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.PaAccountDO;
import com.ruoyi.business.domain.model.PaAccount;

import java.util.List;

/**
 * PA平台账号;PA平台账号Service接口
 *
 * @author xcom
 * @date 2026/1/2
 */

public interface PaAccountService {
    /**
     * 查询PA平台账号;PA平台账号
     *
     * @param id PA平台账号;PA平台账号主键
     * @return PA平台账号;PA平台账号
     */
    PaAccountDO selectPaAccountById(Long id);

    /**
     * 查询PA平台账号;PA平台账号列表
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return PA平台账号;PA平台账号集合
     */
    List<PaAccountDO> selectPaAccountList(PaAccount paAccount);

    /**
     * 新增PA平台账号;PA平台账号
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return 结果
     */
    int insertPaAccount(PaAccount paAccount);

    /**
     * 修改PA平台账号;PA平台账号
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return 结果
     */
    int updatePaAccount(PaAccount paAccount);

    /**
     * 批量删除PA平台账号;PA平台账号
     *
     * @param ids 需要删除的PA平台账号;PA平台账号主键集合
     * @return 结果
     */
    int deletePaAccountByIds(Long[] ids);

    /**
     * 删除PA平台账号;PA平台账号信息
     *
     * @param id PA平台账号;PA平台账号主键
     * @return 结果
     */
    int deletePaAccountById(Long id);
}
