package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 欠费记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkArrearsRespVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "6581")
    @ExcelProperty("主键ID，自增")
    private Long id;

    @Schema(description = "欠费单号，欠费唯一单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("欠费单号，欠费唯一单号")
    private String arrearsNo;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "9554")
    @ExcelProperty("用户ID，关联 sys_user.id，可为 NULL")
    private Long userId;

    @Schema(description = "车牌，车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌，车辆车牌号")
    private String carNumber;

    @Schema(description = "关联逃费订单ID列表，以逗号分隔")
    @ExcelProperty("关联逃费订单ID列表，以逗号分隔")
    private String orderIds;

    @Schema(description = "总欠费金额，该用户总欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总欠费金额，该用户总欠费金额")
    private BigDecimal totalArrearsAmount;

    @Schema(description = "欠费状态：未追缴 / 追缴中 / 已结清 / 坏账", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("欠费状态：未追缴 / 追缴中 / 已结清 / 坏账")
    private String arrearsStatus;

    @Schema(description = "追缴方式，欠费追缴方式")
    @ExcelProperty("追缴方式，欠费追缴方式")
    private String traceWay;

    @Schema(description = "上次追缴时间")
    @ExcelProperty("上次追缴时间")
    private LocalDateTime lastTraceTime;

    @Schema(description = "追缴记录，JSON 格式，不建议使用")
    @ExcelProperty("追缴记录，JSON 格式，不建议使用")
    private String traceRecord;

    @Schema(description = "备注，业务备注说明", example = "你说的对")
    @ExcelProperty("备注，业务备注说明")
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

    @Schema(description = "创建时间，记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，记录创建时间")
    private LocalDateTime createTime;

}
