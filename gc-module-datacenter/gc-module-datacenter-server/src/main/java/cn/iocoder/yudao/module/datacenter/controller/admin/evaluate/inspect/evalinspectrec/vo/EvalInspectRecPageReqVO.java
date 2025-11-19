package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 考察记录数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalInspectRecPageReqVO extends PageParam {

    @Schema(description = "记录ID")
    private String inspectRecId;

    @Schema(description = "关联计划ID")
    private String inspectPlanId;

    @Schema(description = "关联计划名称")
    private String inspectPlanName;

    @Schema(description = "考察对象ID")
    private String evalObjectId;

    @Schema(description = "考察对象名称")
    private String evalObjectName;

    @Schema(description = "关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    private String idxItemName;

    @Schema(description = "考察得分")
    private BigDecimal inspectScore;

    @Schema(description = "考察等级")
    private String inspectLevel;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "现场照片")
    private String onSitePhotos;

    @Schema(description = "考察时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectTime;

    @Schema(description = "考察人员ID")
    private String inspectorId;

    @Schema(description = "考察人员姓名")
    private String inspectorName;

    @Schema(description = "记录状态")
    private String recStatus;

    @Schema(description = "审核人ID")
    private String auditorId;

    @Schema(description = "审核人姓名")
    private String auditorName;

    @Schema(description = "审核意见")
    private String auditOpinion;

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