package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.PaAccountDO;
import com.ruoyi.business.domain.model.PaAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PA平台账号;PA平台账号Mapper接口
 *
 * @author xcom
 * @date 2026/1/2
 */

@Mapper
public interface PaAccountMapper {
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
     * 删除PA平台账号;PA平台账号
     *
     * @param id PA平台账号;PA平台账号主键
     * @return 结果
     */
    int deletePaAccountById(Long id);

    /**
     * 批量删除PA平台账号;PA平台账号
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletePaAccountByIds(Long[] ids);

}
