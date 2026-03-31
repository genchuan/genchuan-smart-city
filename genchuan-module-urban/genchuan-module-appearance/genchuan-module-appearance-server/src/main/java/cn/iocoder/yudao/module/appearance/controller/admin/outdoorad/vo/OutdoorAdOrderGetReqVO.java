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
public class OutdoorAdOrderGetReqVO {

    @Schema(description = "工单主键ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "工单ID不能为空")
    private String id;

}
