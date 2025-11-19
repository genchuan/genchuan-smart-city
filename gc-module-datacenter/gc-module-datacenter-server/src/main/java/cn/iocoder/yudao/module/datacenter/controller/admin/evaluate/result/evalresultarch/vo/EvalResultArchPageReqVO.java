package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结果存档管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalResultArchPageReqVO extends PageParam {

    @Schema(description = "存档ID")
    private String resultArchId;

    @Schema(description = "存档编号")
    private String archCode;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称")
    private String evalTaskName;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "最终评价等级")
    private String finalEvalLevel;

    @Schema(description = "最终评价得分")
    private BigDecimal finalEvalScore;

    @Schema(description = "存档原因")
    private String archReason;

    @Schema(description = "存档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archTime;

    @Schema(description = "存档附件")
    private String archAttachs;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

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