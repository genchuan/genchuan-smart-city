package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 逃费订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkOrderEscapeRespVO {

    @Schema(description = "主键ID，自增，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10900")
    @ExcelProperty("主键ID，自增，记录唯一标识")
    private Long id;

    @Schema(description = "该订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("该订单编号")
    private String orderNo;

    @Schema(description = "逃费订单ID / 原订单ID，关联 park_order_temp.order_id 或 park_order_period.order_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19527")
    @ExcelProperty("逃费订单ID / 原订单ID，关联 park_order_temp.order_id 或 park_order_period.order_id")
    private Long escapeOrderId;

    @Schema(description = "车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车辆车牌号")
    private String carNumber;

    @Schema(description = "用户ID，关联 sys_user.id，可为空", example = "2390")
    @ExcelProperty("用户ID，关联 sys_user.id，可为空")
    private Long userId;

    @Schema(description = "车场ID，关联 park_lot.lot_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17870")
    @ExcelProperty("车场ID，关联 park_lot.lot_id")
    private Long lotId;

    @Schema(description = "发生逃费的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发生逃费的时间")
    private LocalDateTime escapeTime;

    @Schema(description = "逃费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("逃费金额")
    private BigDecimal escapeAmount;

    @Schema(description = "逃费原因，包括：未缴费离场/设备故障/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @ExcelProperty("逃费原因，包括：未缴费离场/设备故障/其他")
    private String escapeReason;

    @Schema(description = "追缴状态，包括：未追缴/追缴中/已追缴/无法追缴", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("追缴状态，包括：未追缴/追缴中/已追缴/无法追缴")
    private String traceStatus;

    @Schema(description = "追缴次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("追缴次数")
    private Integer traceTimes;

    @Schema(description = "上次追缴时间")
    @ExcelProperty("上次追缴时间")
    private LocalDateTime lastTraceTime;

    @Schema(description = "追缴方式，包括：短信提醒/电话通知/系统限制/法律途径")
    @ExcelProperty("追缴方式，包括：短信提醒/电话通知/系统限制/法律途径")
    private String traceWay;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "备注信息", example = "随便")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

}
