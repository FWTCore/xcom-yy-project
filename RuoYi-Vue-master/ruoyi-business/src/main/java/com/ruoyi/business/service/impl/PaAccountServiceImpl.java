package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaAccountDO;
import com.ruoyi.business.domain.model.PaAccount;
import com.ruoyi.business.mapper.PaAccountMapper;
import com.ruoyi.business.service.PaAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台账号;PA平台账号Service业务层处理
 *
 * @author xcom
 * @date 2026/1/2
 */

@Service
public class PaAccountServiceImpl implements PaAccountService {
    @Resource
    private PaAccountMapper paAccountMapper;

    /**
     * 查询PA平台账号;PA平台账号
     *
     * @param id PA平台账号;PA平台账号主键
     * @return PA平台账号;PA平台账号
     */
    @Override
    public PaAccountDO selectPaAccountById(Long id) {
        return paAccountMapper.selectPaAccountById(id);
    }

    /**
     * 查询PA平台账号;PA平台账号列表
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return PA平台账号;PA平台账号
     */
    @Override
    public List<PaAccountDO> selectPaAccountList(PaAccount paAccount) {
        return paAccountMapper.selectPaAccountList(paAccount);
    }

    /**
     * 新增PA平台账号;PA平台账号
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return 结果
     */
    @Override
    public int insertPaAccount(PaAccount paAccount) {
        return paAccountMapper.insertPaAccount(paAccount);
    }

    /**
     * 修改PA平台账号;PA平台账号
     *
     * @param paAccount PA平台账号;PA平台账号
     * @return 结果
     */
    @Override
    public int updatePaAccount(PaAccount paAccount) {
        return paAccountMapper.updatePaAccount(paAccount);
    }

    /**
     * 批量删除PA平台账号;PA平台账号
     *
     * @param ids 需要删除的PA平台账号;PA平台账号主键
     * @return 结果
     */
    @Override
    public int deletePaAccountByIds(Long[] ids) {
        return paAccountMapper.deletePaAccountByIds(ids);
    }

    /**
     * 删除PA平台账号;PA平台账号信息
     *
     * @param id PA平台账号;PA平台账号主键
     * @return 结果
     */
    @Override
    public int deletePaAccountById(Long id) {
        return paAccountMapper.deletePaAccountById(id);
    }
}
