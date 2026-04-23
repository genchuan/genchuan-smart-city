package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 车牌识别修正 Request VO")
public class IdentifyCorrectReqVO {

    @NotNull(message = "记录ID不能为空")
    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @NotBlank(message = "修正后车牌不能为空")
    @Schema(description = "修正后车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;

    @NotBlank(message = "修正后车牌颜色不能为空")
    @Schema(description = "修正后车牌颜色", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateColor;

    @Schema(description = "修正后置信度")
    private BigDecimal confidence;

    @Schema(description = "修正后抓拍图片地址")
    private String imageUrl;

    @NotBlank(message = "修正后识别状态不能为空")
    @Schema(description = "修正后识别状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @NotNull(message = "场站ID不能为空")
    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;

    @Schema(description = "修正备注")
    private String remark;

    @NotNull(message = "修正标记不能为空")
    @Schema(description = "修正记录标记（固定为1）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCorrected;

}