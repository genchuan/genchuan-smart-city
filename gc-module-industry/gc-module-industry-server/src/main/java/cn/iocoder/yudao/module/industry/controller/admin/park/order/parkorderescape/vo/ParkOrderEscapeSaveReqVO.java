package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 逃费订单新增/修改 Request VO")
@Data
public class ParkOrderEscapeSaveReqVO {

    @ExcelProperty(index = 0)
    @Schema(description = "主键ID，自增，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10900")
    private Long id;

    @ExcelProperty(index = 1)
    @Schema(description = "该订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "该订单编号不能为空")
    private String orderNo;

    @ExcelProperty(index = 2)
    @Schema(description = "逃费订单ID / 原订单ID，关联 park_order_temp.order_id 或 park_order_period.order_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19527")
    @NotNull(message = "逃费订单ID / 原订单ID不能为空")
    private Long escapeOrderId;

    @ExcelProperty(index = 3)
    @Schema(description = "车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆车牌号不能为空")
    private String carNumber;

    @ExcelProperty(index = 4)
    @Schema(description = "用户ID，关联 sys_user.id，可为空", example = "2390")
    private Long userId;

    @ExcelProperty(index = 5)
    @Schema(description = "车场ID，关联 park_lot.lot_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17870")
    @NotNull(message = "车场ID不能为空")
    private Long lotId;

    @ExcelProperty(index = 6)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发生逃费的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发生逃费的时间不能为空")
    private LocalDateTime escapeTime;

    @ExcelProperty(index = 7)
    @Schema(description = "逃费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "逃费金额不能为空")
    private BigDecimal escapeAmount;

    @ExcelProperty(index = 8)
    @Schema(description = "逃费原因，包括：未缴费离场/设备故障/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @NotEmpty(message = "逃费原因不能为空")
    private String escapeReason;

    @ExcelProperty(index = 9)
    @Schema(description = "追缴状态，包括：未追缴/追缴中/已追缴/无法追缴", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "追缴状态不能为空")
    private String traceStatus;

    @ExcelProperty(index = 10)
    @Schema(description = "追缴次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "追缴次数不能为空")
    private Integer traceTimes;

    @ExcelProperty(index = 11)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "上次追缴时间")
    private LocalDateTime lastTraceTime;

    @ExcelProperty(index = 12)
    @Schema(description = "追缴方式，包括：短信提醒/电话通知/系统限制/法律途径")
    private String traceWay;

    @ExcelProperty(index = 13)
    @Schema(description = "备注信息", example = "随便")
    private String remark;

    @ExcelProperty(index = 14)
    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @ExcelProperty(index = 15)
    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @ExcelProperty(index = 16)
    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @ExcelProperty(index = 17)
    @Schema(description = "通用扩展字段4")
    private String extCommon4;
}
