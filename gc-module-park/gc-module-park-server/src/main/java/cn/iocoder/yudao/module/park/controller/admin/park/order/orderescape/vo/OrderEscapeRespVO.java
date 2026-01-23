package cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo;

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
public class OrderEscapeRespVO {

    @Schema(description = "[主键ID] 逃费订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "4447")
    @ExcelProperty("[主键ID] 逃费订单唯一标识")
    private Long id;

    @Schema(description = "[原临停订单ID] 关联原临停订单ID，park_order_temp.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20546")
    @ExcelProperty("[原临停订单ID] 关联原临停订单ID，park_order_temp.id")
    private Long originalOrderId;

    @Schema(description = "[车牌号码] 停车车辆车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[车牌号码] 停车车辆车牌号码")
    private String carNumber;

    @Schema(description = "[所属车场ID] 所属车场ID，关联 park_lot.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3182")
    @ExcelProperty("[所属车场ID] 所属车场ID，关联 park_lot.id")
    private Long lotId;

    @Schema(description = "[逃费金额] 逃费金额")
    @ExcelProperty("[逃费金额] 逃费金额")
    private BigDecimal escapeAmount;

    @Schema(description = "[逃费时间] 逃费发生时间")
    @ExcelProperty("[逃费时间] 逃费发生时间")
    private LocalDateTime escapeTime;

    @Schema(description = "[逃费类型] 如:未缴费离场/设备故障逃费/其他", example = "1")
    @ExcelProperty("[逃费类型] 如:未缴费离场/设备故障逃费/其他")
    private String escapeType;

    @Schema(description = "[逃费等级] 如:轻度/中度/重度")
    @ExcelProperty("[逃费等级] 如:轻度/中度/重度")
    private String escapeLevel;

    @Schema(description = "[黑名单状态] 如:未列入/已列入", example = "2")
    @ExcelProperty("[黑名单状态] 如:未列入/已列入")
    private String blacklistStatus;

    @Schema(description = "[追缴状态] 如:未追缴/追缴中/已追缴/无法追缴", example = "2")
    @ExcelProperty("[追缴状态] 如:未追缴/追缴中/已追缴/无法追缴")
    private String traceStatus;

    @Schema(description = "[追缴次数] 追缴次数", example = "8879")
    @ExcelProperty("[追缴次数] 追缴次数")
    private Integer traceCount;

    @Schema(description = "[上次追缴时间] 上次追缴时间")
    @ExcelProperty("[上次追缴时间] 上次追缴时间")
    private LocalDateTime lastTraceTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 逃费订单相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 逃费订单相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
