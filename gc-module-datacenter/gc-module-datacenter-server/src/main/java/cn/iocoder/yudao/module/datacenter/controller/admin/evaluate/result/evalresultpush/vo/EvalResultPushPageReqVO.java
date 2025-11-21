package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结果关联推送分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalResultPushPageReqVO extends PageParam {

    @Schema(description = "推送ID")
    private String resultPushId;

    @Schema(description = "推送编号")
    private String resultPushCode;

    @Schema(description = "关联存档ID")
    private String resultArchId;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "推送目标")
    private String pushTarget;

    @Schema(description = "推送方式")
    private String pushMethod;

    @Schema(description = "推送地址")
    private String pushAddress;

    @Schema(description = "推送内容")
    private String pushContent;

    @Schema(description = "推送状态")
    private String pushStatus;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

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