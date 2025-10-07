package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.web.controller.business.response.UploadFileVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileController
 *
 * @author xcom
 * @date 2025/10/6
 */

@Slf4j
@RestController
@Api(tags = "文件管理")
@RequestMapping("/file")
public class FileController extends BaseController {

    /**
     * 图片上传
     */
    @Log(title = "图片上传", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadImage")
    public AjaxResult avatar(@RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            LoginUser loginUser = getLoginUser();
            String fileUrl = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.IMAGE_EXTENSION,
                true);
            UploadFileVO resultData = new UploadFileVO();
            resultData.setFileUrl(fileUrl);
            resultData.setFileName(file.getOriginalFilename());
            return success(resultData);
        }
        return error("上传图片异常，请联系管理员");
    }
}
