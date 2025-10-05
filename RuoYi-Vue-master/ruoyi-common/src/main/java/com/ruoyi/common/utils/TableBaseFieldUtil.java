package com.ruoyi.common.utils;

import com.ruoyi.common.core.domain.BaseEntityDO;
import com.ruoyi.common.core.domain.model.LoginUser;

import java.time.LocalDateTime;

/**
 * 表基础字段
 *
 * @author xcom
 * @date 2025/10/3
 */

public class TableBaseFieldUtil {

    /**
     * 设置全部基础字段
     * @param data
     * @param <T>
     */
    public static <T extends BaseEntityDO> void setBaseFieldValue(T data) {
        setCreatedFieldValue(data);
        setUpdatedFieldValue(data);
    }

    /**
     * 设置创建基础字段
     * @param data
     * @param <T>
     */
    public static <T extends BaseEntityDO> void setCreatedFieldValue(T data) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        data.setCreatedById(loginUser.getUserId());
        data.setCreatedByName(loginUser.getUser().getNickName());
        data.setCreatedTime(LocalDateTime.now());
    }

    /**
     * 设置更新基础字段
     * @param data
     * @param <T>
     */
    public static <T extends BaseEntityDO> void setUpdatedFieldValue(T data) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        data.setUpdatedById(loginUser.getUserId());
        data.setUpdatedByName(loginUser.getUser().getNickName());
        data.setUpdatedTime(LocalDateTime.now());
    }
}
