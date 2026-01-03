package com.ruoyi.web.controller.common;

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
import com.ruoyi.web.controller.utils.ImageUrlUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            String fileUrl = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file, MimeTypeUtils.IMAGE_EXTENSION,
                true, true);
            UploadFileVO resultData = new UploadFileVO();
            resultData.setFileName(file.getOriginalFilename());
            resultData.setFileUrl(fileUrl);
            resultData.setFileFullUrl(ImageUrlUtil.paddedImageUrl(fileUrl));
            return success(resultData);
        }
        return error("上传图片异常，请联系管理员");
    }

    /**
     * 文件上传
     */
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("files") List<MultipartFile> files) throws Exception {
        if (CollectionUtils.isNotEmpty(files)) {
            List<UploadFileVO> resultData = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileUrl = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file,
                    MimeTypeUtils.DEFAULT_AGENT_EXTENSION, false, false);
                UploadFileVO tempData = new UploadFileVO();
                tempData.setFileName(file.getOriginalFilename());
                tempData.setFileUrl(fileUrl);
                tempData.setFileFullUrl(ImageUrlUtil.paddedImageUrl(fileUrl));
                resultData.add(tempData);
                return success(resultData);
            }
        }
        return error("上传文件异常，请联系管理员");
    }
}
