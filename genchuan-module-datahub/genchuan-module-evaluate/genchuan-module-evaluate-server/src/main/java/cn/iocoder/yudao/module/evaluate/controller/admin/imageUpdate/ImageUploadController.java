package cn.iocoder.yudao.module.evaluate.controller.admin.imageUpdate;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.imageUpdate.vo.ImageUploadRespVO;
import cn.iocoder.yudao.module.evaluate.service.image.ImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "管理后台 - 图片上传")
@RestController
@RequestMapping("/evaluate/patrol-inspection/image")
@Slf4j
public class ImageUploadController {

    @Resource
    private ImageUploadService imageUploadService;

    @PostMapping(value = "/upload/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "批量上传图片")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:create')")
    public CommonResult<List<ImageUploadRespVO>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<ImageUploadRespVO> results = imageUploadService.batchUploadImages(files);
        return success(results);
    }

    private static <T> CommonResult<T> success(T data) {
        return CommonResult.success(data);
    }
}
