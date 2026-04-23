package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 车牌识别手动录入 Request VO")
public class IdentifyCreateReqVO {

    @NotBlank(message = "车牌不能为空")
    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;

    @NotBlank(message = "车牌颜色不能为空")
    @Schema(description = "车牌颜色（蓝牌/黄牌/绿牌/其他）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateColor;

    @Schema(description = "置信度，默认0")
    private BigDecimal confidence;

    @Schema(description = "抓拍图片地址")
    private String imageUrl;

    @NotBlank(message = "识别状态不能为空")
    @Schema(description = "识别状态（识别成功/识别失败）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @NotNull(message = "场站ID不能为空")
    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;

    @Schema(description = "备注")
    private String remark;

}