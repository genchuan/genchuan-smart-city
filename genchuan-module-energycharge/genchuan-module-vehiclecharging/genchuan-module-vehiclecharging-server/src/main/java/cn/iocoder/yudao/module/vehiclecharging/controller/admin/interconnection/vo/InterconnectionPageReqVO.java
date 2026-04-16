package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 互联互通表分页 Request VO")
@Data
public class InterconnectionPageReqVO extends PageParam {

    @Schema(description = "对接编号")
    private String connectCode;

    @Schema(description = "第三方平台名称")
    private String thirdPlatform;

    @Schema(description = "对接类型", example = "1")
    private String connectType;

    @Schema(description = "API参数")
    private String apiParam;

    @Schema(description = "同步频率，单位：分钟")
    private Integer syncFreq;

    @Schema(description = "同步成功率，单位：%")
    private BigDecimal syncSuccessRate;

    @Schema(description = "对接状态：未申请/审核中/已开通/已关闭", example = "1")
    private String connectStatus;

    @Schema(description = "审核人员")
    private String auditUser;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "审核备注", example = "你说的对")
    private String auditRemark;

    @Schema(description = "关闭原因", example = "不喜欢")
    private String closeReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}