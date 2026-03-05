package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 积分规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPointsRulePageReqVO extends PageParam {

    @Schema(description = "[规则名称] 积分规则名称", example = "张三")
    private String ruleName;

    @Schema(description = "[触发类型] 如:停车消费/充值/分享/投诉反馈/会员任务", example = "1")
    private String triggerType;

    @Schema(description = "[固定积分值] 固定赠送的积分值")
    private BigDecimal pointsAmount;

    @Schema(description = "[积分比例] 积分计算比例，0~1 小数")
    private BigDecimal pointsRatio;

    @Schema(description = "[单日上限] 单条规则单日可获得的积分上限")
    private BigDecimal upperLimit;

    @Schema(description = "[状态] 如:禁用/启用", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 积分规则相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
