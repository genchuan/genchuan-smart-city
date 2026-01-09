package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 欠费记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkArrearsPageReqVO extends PageParam {

    @Schema(description = "欠费单号，欠费唯一单号")
    private String arrearsNo;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "9554")
    private Long userId;

    @Schema(description = "车牌，车辆车牌号")
    private String carNumber;

    @Schema(description = "关联逃费订单ID列表，以逗号分隔")
    private String orderIds;

    @Schema(description = "总欠费金额，该用户总欠费金额")
    private BigDecimal totalArrearsAmount;

    @Schema(description = "欠费状态：未追缴 / 追缴中 / 已结清 / 坏账", example = "1")
    private String arrearsStatus;

    @Schema(description = "追缴方式，欠费追缴方式")
    private String traceWay;

    @Schema(description = "上次追缴时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastTraceTime;

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

    @Schema(description = "创建时间，记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
