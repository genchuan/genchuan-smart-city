package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 风险溯源路径分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RiskTracePathPageReqVO extends PageParam {

    @Schema(description = "路径ID")
    private String tracePathId;

    @Schema(description = "预警ID")
    private String earlyWarnId;

    @Schema(description = "预警名称")
    private String earlyWarnName;

    @Schema(description = "溯源时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] traceTime;

    @Schema(description = "路径节点1（直接现象）")
    private String node1Content;

    @Schema(description = "路径节点2（直接原因）")
    private String node2Content;

    @Schema(description = "路径节点3（根本原因）")
    private String node3Content;

    @Schema(description = "溯源结论")
    private String traceConclusion;

    @Schema(description = "分析人ID")
    private String analystUserId;

    @Schema(description = "分析人姓名")
    private String analystUserName;

    @Schema(description = "是否自动分析")
    private String isAutoAnalysis;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "create_time")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}