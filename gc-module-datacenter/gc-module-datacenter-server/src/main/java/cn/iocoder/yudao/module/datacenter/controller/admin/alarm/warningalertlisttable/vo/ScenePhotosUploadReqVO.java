package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 现场照片上传请求VO
 */
@Data
@Schema(description = "现场照片上传请求VO")
public class ScenePhotosUploadReqVO {


    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警ID不能为空")
    private Long alertId;

    @Schema(description = "Base64图片数据列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图片数据不能为空")
    private List<String> base64Images;

    @Schema(description = "是否压缩图片", example = "true")
    private Boolean compress = true;

    @Schema(description = "压缩质量(0.1-1.0)", example = "0.8")
    @DecimalMin(value = "0.1", message = "压缩质量不能小于0.1")
    @DecimalMax(value = "1.0", message = "压缩质量不能大于1.0")
    private Float compressQuality = 0.8f;

}
