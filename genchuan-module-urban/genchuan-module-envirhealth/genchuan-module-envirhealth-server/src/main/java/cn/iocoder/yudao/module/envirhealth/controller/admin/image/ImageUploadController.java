package cn.iocoder.yudao.module.envirhealth.controller.admin.image;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.envirhealth.service.image.ImageUploadService;
import cn.iocoder.yudao.module.envirhealth.util.vo.ImageUploadRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "环境卫生管理 - 图片上传")
@RestController
@RequestMapping("/envirhealth/image")
@Slf4j
public class ImageUploadController {

    @Resource
    private ImageUploadService imageUploadService;

    /**
     * 批量上传图片
     */
    @PostMapping(value = "/upload/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "批量上传图片")
    public CommonResult<List<ImageUploadRespVO>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        // 调用Service层处理核心逻辑
        List<ImageUploadRespVO> results = imageUploadService.batchUploadImages(files);
        return CommonResult.success(results);
    }
}