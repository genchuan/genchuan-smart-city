package cn.iocoder.yudao.module.envirhealth.service.image;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.envirhealth.framework.file.FileFeignClient;
import cn.iocoder.yudao.module.envirhealth.framework.util.convert.UrlConvert;
import cn.iocoder.yudao.module.envirhealth.controller.admin.image.vo.ImageUploadRespVO;
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

        // 空值保护
        if (files == null || files.length == 0) {
            log.warn("批量上传图片时，文件数组为空");
            return results;
        }

        log.info("开始批量上传图片，文件数量：{}", files.length);

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            log.info("处理第{}个文件，文件名：{}，文件大小：{}字节，ContentType：{}",
                    i + 1,
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType());

            try {
                // 校验文件合法性
                validateImageFile(file);
                log.info("文件校验通过：{}", file.getOriginalFilename());

                // 调用文件服务上传
                log.info("开始调用文件服务上传：{}", file.getOriginalFilename());
                CommonResult<String> uploadResult = fileFeignClient.uploadFile(file);

                // 打印FeignClient返回的完整结果
                log.info("文件服务返回结果 - success: {}, data: {}, msg: {}, code: {}",
                        uploadResult.isSuccess(),
                        uploadResult.getData(),
                        uploadResult.getMsg(),
                        uploadResult.getCode());

                if (uploadResult.isSuccess()) {
                    // 检查返回的data是否为null
                    if (uploadResult.getData() == null) {
                        log.error("文件服务返回成功但data为null，文件名：{}", file.getOriginalFilename());
                        continue;
                    }

                    // 转换为公共可访问的URL
                    String publicUrl = UrlConvert.toPublicUrl(uploadResult.getData());
                    log.info("URL转换 - 原始URL: {}, 转换后URL: {}", uploadResult.getData(), publicUrl);

                    // 构建返回结果
                    ImageUploadRespVO respVO = ImageUploadRespVO.builder()
                            .url(publicUrl)
                            .fileName(file.getOriginalFilename())
                            .fileSize(file.getSize())
                            .build();

                    log.info("构建响应VO成功: {}", respVO);
                    results.add(respVO);
                } else {
                    log.warn("文件上传失败: {}, 原因: {}, code: {}",
                            file.getOriginalFilename(),
                            uploadResult.getMsg(),
                            uploadResult.getCode());
                }
            } catch (IllegalArgumentException e) {
                log.error("文件校验失败: {}, 错误: {}", file.getOriginalFilename(), e.getMessage());
            } catch (Exception e) {
                log.error("处理文件异常: {}", file.getOriginalFilename(), e);
            }
        }

        log.info("批量上传完成，成功上传{}个文件，共{}个文件", results.size(), files.length);
        return results;
    }

    @Override
    public void validateImageFile(MultipartFile file) {
        // 1. 校验文件是否为空
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        // 2. 校验文件类型（MIME类型）
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("只支持图片文件上传");
        }

        // 3. 校验文件后缀名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            // 处理无后缀名的情况
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

        // 4. 校验文件大小（5MB）
        long maxSize = 5 * 1024 * 1024; // 5MB
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("图片大小不能超过5MB");
        }
    }
}