package cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 离场记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkCarExitRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13125")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "离场记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "22619")
    @ExcelProperty("离场记录ID（UUID）")
    private String exitId;

    @Schema(description = "入场记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11110")
    @ExcelProperty("入场记录ID")
    private String entryId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String carNumber;

    @Schema(description = "离场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("离场时间")
    private LocalDateTime exitTime;

    @Schema(description = "离场出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20426")
    @ExcelProperty("离场出入口ID")
    private String exitExitId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18494")
    @ExcelProperty("所属车场ID")
    private String lotId;

    @Schema(description = "停放时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停放时长（分钟）")
    private Integer parkingDuration;

    @Schema(description = "应缴费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("应缴费用")
    private BigDecimal feeAmount;

    @Schema(description = "实缴费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实缴费用")
    private BigDecimal actualPayAmount;

    @Schema(description = "缴费状态：未缴费/已缴费/部分缴费", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("缴费状态：未缴费/已缴费/部分缴费")
    private String payStatus;

    @Schema(description = "缴费记录ID", example = "18990")
    @ExcelProperty("缴费记录ID")
    private String paymentId;

    @Schema(description = "离场类型：正常/异常/特殊放行", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("离场类型：正常/异常/特殊放行")
    private String exitType;

    @Schema(description = "异常原因", example = "不香")
    @ExcelProperty("异常原因")
    private String abnormalReason;

    @Schema(description = "识别设备", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别设备")
    private String deviceCode;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime exitCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime exitUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String exitRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}