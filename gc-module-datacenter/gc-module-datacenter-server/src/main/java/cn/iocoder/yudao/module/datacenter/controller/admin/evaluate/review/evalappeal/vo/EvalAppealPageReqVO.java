package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 申诉管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalAppealPageReqVO extends PageParam {

    @Schema(description = "申诉ID")
    private String appealId;

    @Schema(description = "申诉编号")
    private String appealCode;

    @Schema(description = "关联公示ID")
    private String resultPublicId;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "申诉人ID")
    private String appealUserId;

    @Schema(description = "申诉人姓名")
    private String appealUserName;

    @Schema(description = "申诉理由")
    private String appealReason;

    @Schema(description = "证明材料")
    private String proofMats;

    @Schema(description = "申诉状态", example = "2")
    private String appealStatus;

    @Schema(description = "提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

    @Schema(description = "撤销时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

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