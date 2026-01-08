package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 欠费记录新增/修改 Request VO")
@Data
public class ParkArrearsSaveReqVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "6581")
    private Long id;

    @Schema(description = "欠费单号，欠费唯一单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "欠费单号，欠费唯一单号不能为空")
    private String arrearsNo;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "9554")
    private Long userId;

    @Schema(description = "车牌，车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌，车辆车牌号不能为空")
    private String carNumber;

    @Schema(description = "关联逃费订单ID列表，以逗号分隔")
    private String orderIds;

    @Schema(description = "总欠费金额，该用户总欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总欠费金额，该用户总欠费金额不能为空")
    private BigDecimal totalArrearsAmount;

    @Schema(description = "欠费状态：未追缴 / 追缴中 / 已结清 / 坏账", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "欠费状态：未追缴 / 追缴中 / 已结清 / 坏账不能为空")
    private String arrearsStatus;

    @Schema(description = "追缴方式，欠费追缴方式")
    private String traceWay;

    @Schema(description = "上次追缴时间")
    private LocalDateTime lastTraceTime;

    @Schema(description = "追缴记录，JSON 格式，不建议使用")
    private String traceRecord;

    @Schema(description = "备注，业务备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
