package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 欠费追缴分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkArrearsRecoveryPageReqVO extends PageParam {

    @Schema(description = "欠费追缴唯一标识", example = "3125")
    private String recoveryCode;

    @Schema(description = "关联订单ID", example = "25273")
    private Long woId;

    @Schema(description = "订单编号")
    private String woNo;

    @Schema(description = "用户唯一标识", example = "28135")
    private Long userId;

    @Schema(description = "用户姓名", example = "张三")
    private String userName;

    @Schema(description = "用户手机号")
    private String userPhone;

    @Schema(description = "欠费发生时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] arrearsTime;

    @Schema(description = "欠费结清时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] settleTime;

    @Schema(description = "欠费金额")
    private BigDecimal arrearsAmount;

    @Schema(description = "追缴工单唯一标识", example = "12189")
    private Long recoveryWoId;

    @Schema(description = "执行追缴的人员ID", example = "1816")
    private Long recoveryUserId;

    @Schema(description = "追缴处理状态", example = "2")
    private String recoveryStatus;

    @Schema(description = "追缴方式")
    private String recoveryMethod;

    @Schema(description = "对用户采取的限制措施")
    private String limitMeasure;

    @Schema(description = "所属行政区划名称", example = "赵六")
    private String regionName;

    @Schema(description = "所属网格名称", example = "张三")
    private String gridName;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
