package cn.iocoder.yudao.module.evaluate.service.image;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.imageUpdate.vo.ImageUploadRespVO;
import cn.iocoder.yudao.module.evaluate.file.FileFeignClient;
import cn.iocoder.yudao.module.evaluate.util.convert.UrlConvert;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图片上传 Service 实现类
 */
@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {

    @Resource
    private FileFeignClient fileFeignClient;

    @Override
    public List<ImageUploadRespVO> batchUploadImages(MultipartFile[] files) {
        List<ImageUploadRespVO> results = new ArrayList<>();

        if (files == null || files.length == 0) {
            log.warn("批量上传图片时，文件数组为空");
            return results;
        }

        for (MultipartFile file : files) {
            try {
                validateImageFile(file);

                CommonResult<String> uploadResult = fileFeignClient.uploadFile(file);

                if (uploadResult.isSuccess()) {
                    String publicUrl = UrlConvert.toPublicUrl(uploadResult.getData());
                    results.add(ImageUploadRespVO.builder()
                            .url(publicUrl)
                            .fileName(file.getOriginalFilename())
                            .fileSize(file.getSize())
                            .build());
                } else {
                    log.warn("文件上传失败: {}, 原因: {}", file.getOriginalFilename(), uploadResult.getMsg());
                }
            } catch (Exception e) {
                log.error("处理文件异常: {}", file.getOriginalFilename(), e);
            }
        }

        return results;
    }

    @Override
    public void validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("只支持图片文件上传");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            int lastDotIndex = originalFilename.lastIndexOf(".");
            if (lastDotIndex == -1) {
                throw new IllegalArgumentException("图片文件必须包含合法的后缀名");
            }
            String extension = originalFilename.substring(lastDotIndex + 1).toLowerCase();
            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp");
            if (!allowedExtensions.contains(extension)) {
                throw new IllegalArgumentException("不支持的图片格式，仅支持: " + allowedExtensions);
            }
        } else {
            throw new IllegalArgumentException("无法获取文件名，无法校验图片格式");
        }

        long maxSize = 5 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("图片大小不能超过5MB");
        }
    }
}
