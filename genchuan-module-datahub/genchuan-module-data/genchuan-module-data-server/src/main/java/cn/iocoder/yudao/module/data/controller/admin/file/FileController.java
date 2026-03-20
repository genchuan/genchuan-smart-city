package cn.iocoder.yudao.module.data.controller.admin.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.data.api.FileApi;
import cn.iocoder.yudao.module.data.framework.file.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 文件")
@RestController
@Validated
@RequestMapping("/data/file") // 这里定义了HTTP访问路径，与ApiConstants.PREFIX通常组合使用
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
}