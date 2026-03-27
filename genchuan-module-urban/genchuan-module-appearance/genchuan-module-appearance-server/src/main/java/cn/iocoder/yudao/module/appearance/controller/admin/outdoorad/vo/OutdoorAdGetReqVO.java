package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * 管理后台 - 户外广告详情 Request VO
 *
 * @author 亘川智城
 */
@Data
@Schema(description = "管理后台 - 户外广告 Get Request VO")
public class OutdoorAdGetReqVO {

    @Schema(description = "户外广告主键ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "广告ID不能为空")
    private String id;

}