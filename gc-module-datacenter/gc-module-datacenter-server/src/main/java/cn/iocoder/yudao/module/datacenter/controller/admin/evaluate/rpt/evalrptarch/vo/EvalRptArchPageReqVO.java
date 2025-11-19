package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 报告归档导出分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalRptArchPageReqVO extends PageParam {

    @Schema(description = "归档ID")
    private String rptArchId;

    @Schema(description = "归档编号")
    private String rptArchCode;

    @Schema(description = "关联报告ID")
    private String evalRptId;

    @Schema(description = "报告名称")
    private String evalRptName;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "归档位置")
    private String archLocation;

    @Schema(description = "归档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archTime;

    @Schema(description = "归档方式")
    private String archMethod;

    @Schema(description = "报告版本")
    private String rptVersion;

    @Schema(description = "归档状态")
    private String archStatus;

    @Schema(description = "失败原因")
    private String failReason;

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