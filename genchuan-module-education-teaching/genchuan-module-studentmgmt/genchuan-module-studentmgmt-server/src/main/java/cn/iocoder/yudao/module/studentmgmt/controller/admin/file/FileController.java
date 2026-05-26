package cn.iocoder.yudao.module.studentmgmt.controller.admin.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.studentmgmt.api.FileApi;
import cn.iocoder.yudao.module.studentmgmt.framework.file.FileUploadService;
import cn.iocoder.yudao.module.studentmgmt.framework.file.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "学生管理 - 文件上传")
@RestController
@Validated
@RequestMapping("/studentmgmt/file")
@Slf4j
public class FileController implements FileApi {

    @Resource
    private FileUploadService fileUploadService;

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfig.MinioProperties minioProperties;

    @PostMapping("/upload-file")
    @Operation(summary = "上传文件")
    public CommonResult<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            String url = fileUploadService.uploadFile(file);
            return CommonResult.success(url);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return CommonResult.error(500, "文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 上传多张图片
     */
    @PostMapping(value = "/upload-multiple-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传多张图片")
    public CommonResult<List<String>> uploadMultipleImages(@RequestParam("files") MultipartFile[] files) {
        try {
            if (files == null || files.length == 0) {
                return CommonResult.error(400, "请选择要上传的图片");
            }
            if (files.length > 10) {
                return CommonResult.error(400, "最多只能上传10张图片");
            }

            List<String> urls = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                try {
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")) {
                        errors.add(String.format("第%d个文件不是图片类型", i + 1));
                        continue;
                    }
                    String url = fileUploadService.uploadFile(file);
                    urls.add(url);
                } catch (Exception e) {
                    log.error("上传第{}个图片失败", i + 1, e);
                    errors.add(String.format("第%d个图片上传失败: %s", i + 1, e.getMessage()));
                }
            }

            if (!errors.isEmpty() && !urls.isEmpty()) {
                log.warn("部分图片上传失败: {}", String.join("; ", errors));
                return CommonResult.success(urls);
            }
            if (urls.isEmpty() && !errors.isEmpty()) {
                return CommonResult.error(500, "所有图片上传失败: " + String.join("; ", errors));
            }
            return CommonResult.success(urls);

        } catch (Exception e) {
            log.error("上传多张图片失败", e);
            return CommonResult.error(500, "上传多张图片失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-file")
    @Operation(summary = "删除文件")
    public CommonResult<Boolean> deleteFile(@RequestParam("fileUrl") String fileUrl) {
        try {
            // 1. 空值判断
            if (fileUrl == null || fileUrl.isBlank()) {
                return CommonResult.error(400, "文件URL不能为空");
            }

            String bucketName = minioProperties.getBucket();
            String bucketPrefix = "/" + bucketName + "/";

            // 2. 校验URL是否合法
            if (!fileUrl.contains(bucketPrefix)) {
                log.error("文件URL格式错误，无法解析：{}", fileUrl);
                return CommonResult.error(400, "文件URL格式错误");
            }

            // 3. 解析文件名
            String fileName = fileUrl.substring(fileUrl.indexOf(bucketPrefix) + bucketPrefix.length());

            // 4. 执行删除
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );

            log.info("编辑功能删除文件成功 → URL：{}，文件名：{}", fileUrl, fileName);
            return CommonResult.success(true);

        } catch (Exception e) {
            log.error("编辑功能删除文件失败 URL：{}", fileUrl, e);
            return CommonResult.error(500, "删除文件失败：" + e.getMessage());
        }
    }
}