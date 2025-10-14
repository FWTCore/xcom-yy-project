package com.ruoyi.common.utils.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;

import javax.imageio.ImageIO;

/**
 * 图片处理工具类
 *
 * @author ruoyi
 */
public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath) {
        InputStream is = getFile(imagePath);
        try {
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error("图片加载异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath) {
        try {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        } catch (Exception e) {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     * 
     * @param url 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url) {
        InputStream in = null;
        try {
            if (url.startsWith("http")) {
                // 网络地址
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            } else {
                // 本机地址
                String localPath = RuoYiConfig.getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("获取文件路径异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 按原后缀压缩
     *
     * @param inputStream 原图流
     * @param fileName    原文件名（含后缀）
     * @param maxWidth    最大宽
     * @param maxHeight   最大高
     * @param quality     质量 0~1
     * @return 压缩后的字节数组
     */
    public static byte[] compressImage(InputStream inputStream, String fileName, int maxWidth, int maxHeight,
                                       float quality) throws IOException {
        // 取原后缀（不含点）
        // commons-io 工具类
        String ext = FilenameUtils.getExtension(fileName).toLowerCase();
        if (!Arrays.asList("jpg", "jpeg", "png", "webp").contains(ext)) {
            throw new IllegalArgumentException("不支持的图片格式：" + ext);
        }
        // 关键修复：复制输入流以便重复使用
        byte[] inputBytes = IOUtils.toByteArray(inputStream);
        InputStream copy1 = new ByteArrayInputStream(inputBytes);
        InputStream copy2 = new ByteArrayInputStream(inputBytes);
        // 计算缩放比例
        double scaleRatio = calculateScaleRatio(copy1, maxWidth, maxHeight);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用第二个流副本进行压缩
        Thumbnails.of(copy2).scale(scaleRatio).outputFormat(ext).outputQuality(quality).toOutputStream(out);
        return out.toByteArray();
    }

    /**
     * 计算保持宽高比的最大缩放比例
     */
    private static double calculateScaleRatio(InputStream inputStream, int maxWidth, int maxHeight) throws IOException {
        // 获取原始图片尺寸（不消耗流）
        BufferedImage srcImage = ImageIO.read(inputStream);
        if (srcImage == null) {
            throw new IOException("无法读取图片数据");
        }

        int origWidth = srcImage.getWidth();
        int origHeight = srcImage.getHeight();

        // 计算宽高缩放比例
        double widthRatio = (double) maxWidth / origWidth;
        double heightRatio = (double) maxHeight / origHeight;

        // 取最小值确保图片不超出边界
        return Math.min(widthRatio, heightRatio);
    }

}
