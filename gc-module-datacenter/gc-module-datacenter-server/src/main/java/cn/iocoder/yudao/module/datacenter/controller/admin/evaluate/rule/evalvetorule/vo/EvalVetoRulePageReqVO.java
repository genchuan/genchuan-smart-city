package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 否决项规则管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalVetoRulePageReqVO extends PageParam {

    @Schema(description = "否决项ID")
    private String vetoRuleId;

    @Schema(description = "否决项名称")
    private String vetoRuleName;

    @Schema(description = "否决项编码")
    private String vetoRuleCode;

    @Schema(description = "适用对象类型")
    private String applyObjectType;

    @Schema(description = "否决条件")
    private String vetoCondition;

    @Schema(description = "关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    private String idxItemName;

    @Schema(description = "指标阈值")
    private String idxThreshold;

    @Schema(description = "生效周期")
    private String validCycle;

    @Schema(description = "否决结果")
    private String vetoResult;

    @Schema(description = "否决描述")
    private String vetoDesc;

    @Schema(description = "启用状态")
    private String enableStatus;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}