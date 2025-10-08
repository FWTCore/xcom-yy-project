package com.ruoyi.web.controller.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;

import javax.annotation.Resource;

/**
 * ImageUrl Util
 *
 * @author xcom
 * @date 2025/10/8
 */

public class ImageUrlUtil {

    @Resource
    private RuoYiConfig ruoYiConfig;

    /**
     * 补齐url
     * @param path
     * @return
     */
    public static String paddedImageUrl(String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (path.startsWith("http") || path.startsWith("data:")) {
            return path;
        }
        if (path.startsWith("/api")) {
            // (?i) 表示忽略大小写
            path = path.replaceFirst("(?i)/api", "");
        }
        return RuoYiConfig.getSystemPath() + path;
    }

}
