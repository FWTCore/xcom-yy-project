package com.ruoyi.business.utils;

import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AssetCodeUtil
 *
 * @author xcom
 * @date 2025/11/29
 */

public class AssetCodeUtil {
    /**
     * 生成资产code
     * @param code
     * @param copyNum
     * @return
     */
    public static List<String> generateAssetCode(String code, Integer copyNum) {
        if (StringUtils.isBlank(code)) {
            throw new ServiceException("编码不存在");
        }
        int startIndex = 0;
        String assetCodePrefix = code;
        if (code.contains("-")) {
            String[] data = code.split("-");
            if (data.length != 2) {
                throw new ServiceException("编码格式错误，包含多个-");
            }
            assetCodePrefix = data[0];
            startIndex = Integer.parseInt(data[1]);
        }
        List<String> resultData = new ArrayList<>();
        for (int i = 1; i <= copyNum; i++) {
            resultData.add(String.format("%s-%s", assetCodePrefix, startIndex + i));
        }
        return resultData;
    }

}
