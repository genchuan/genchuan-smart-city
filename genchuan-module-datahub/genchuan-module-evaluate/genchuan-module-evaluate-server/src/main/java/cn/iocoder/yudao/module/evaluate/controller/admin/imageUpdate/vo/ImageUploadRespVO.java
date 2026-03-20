package cn.iocoder.yudao.module.evaluate.controller.admin.imageUpdate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片上传响应 VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "图片上传响应")
public class ImageUploadRespVO {

    @Schema(description = "图片公网访问地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://112.47.127.21:59000/2023/01/01/test.jpg")
    private String url;

    @Schema(description = "图片文件名", example = "test.jpg")
    private String fileName;

    @Schema(description = "图片大小(字节)", example = "1024")
    private Long fileSize;
}
