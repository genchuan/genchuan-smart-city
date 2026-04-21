package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位定位新增/修改 Request VO")
@Data
public class SpaceLocationSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @NotBlank(message = "车牌号码不能为空")
    private String plateNo;

    @Schema(description = "查询时间（创建时由后端默认为当前时间）")
    private LocalDateTime queryTime;

    @Schema(description = "车位 ID")
    private Long spaceId;

    @Schema(description = "定位结果", example = "成功")
    private String locationResult;

    @Schema(description = "响应时长（毫秒）")
    private Integer responseDuration;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
