package cn.iocoder.yudao.module.envirhealth.controller.admin.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.envirhealth.api.FileApi;
import cn.iocoder.yudao.module.envirhealth.framework.file.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "环境卫生管理 - 文件上传")
@RestController
@Validated
@RequestMapping("/envirhealth/file") // 这里定义了HTTP访问路径，与ApiConstants.PREFIX通常组合使用
@Slf4j
public class FileController implements FileApi { // 实现 FileApi 接口

    @Resource
    private FileUploadService fileUploadService;

    @Override
    @PostMapping("/upload-file") // 这里的路径与 FileApi 中 PREFIX 之后的部分拼接
    @Operation(summary = "上传文件")
    public CommonResult<String> uploadAvatar(@RequestPart("file") MultipartFile file) {
        try {
            String url = fileUploadService.uploadAvatar(file);
            return CommonResult.success(url);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            // 这里可以更精细地捕获异常，返回不同的错误码。
            // 例如，捕获文件类型不合法的异常，返回 CommonResult.error(400, "文件类型不支持");
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
            // 验证文件数组不为空
            if (files == null || files.length == 0) {
                return CommonResult.error(400, "请选择要上传的图片");
            }

            // 验证文件数量
            if (files.length > 10) {
                return CommonResult.error(400, "最多只能上传10张图片");
            }

            List<String> urls = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                try {
                    // 验证是否为图片
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")) {
                        errors.add(String.format("第%d个文件不是图片类型", i + 1));
                        continue;
                    }

                    // 上传图片
                    String url = fileUploadService.uploadAvatar(file);
                    urls.add(url);
                } catch (Exception e) {
                    log.error("上传第{}个图片失败", i + 1, e);
                    errors.add(String.format("第%d个图片上传失败: %s", i + 1, e.getMessage()));
                }
            }

            // 如果有错误但部分成功
            if (!errors.isEmpty() && !urls.isEmpty()) {
                log.warn("部分图片上传失败: {}", String.join("; ", errors));
                return CommonResult.success(urls);
            }

            // 全部失败
            if (urls.isEmpty() && !errors.isEmpty()) {
                return CommonResult.error(500, "所有图片上传失败: " + String.join("; ", errors));
            }

            // 全部成功
            return CommonResult.success(urls);

        } catch (Exception e) {
            log.error("上传多张图片失败", e);
            return CommonResult.error(500, "上传多张图片失败: " + e.getMessage());
        }
    }
}