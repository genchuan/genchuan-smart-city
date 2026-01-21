package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收费异常分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChargeAbnormalPageReqVO extends PageParam {

    @Schema(description = "[异常订单编号]")
    private String orderNo;

    @Schema(description = "[车牌号码]")
    private String carNumber;

    @Schema(description = "[区域ID] 关联sys_area.id", example = "16943")
    private Long areaId;

    @Schema(description = "[车场ID] 关联park_lot.id", example = "20676")
    private Long lotId;

    @Schema(description = "[异常时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalTime;

    @Schema(description = "[异常金额]")
    private BigDecimal abnormalAmount;

    @Schema(description = "[异常类型] 如:金额错误/计费缺失/重复收费/其他", example = "2")
    private String abnormalType;

    @Schema(description = "[异常原因]", example = "不香")
    private String abnormalReason;

    @Schema(description = "[处置状态] 如:未处置/处理中/已处置", example = "1")
    private String disposalStatus;

    @Schema(description = "[处置结果] 如:已纠错/无法纠错")
    private String disposalResult;

    @Schema(description = "[处置人ID] 关联park_user.id")
    private Long disposalBy;

    @Schema(description = "[处置时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] disposalTime;

    @Schema(description = "[备注]", example = "你猜")
    private String remark;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
