package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检周期统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectCycleRptPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String cycleStatId;

    @Schema(description = "统计维度")
    private String statDimension;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "周期列表")
    private String cycleList;

    @Schema(description = "巡查次数列表")
    private String inspectCountList;

    @Schema(description = "问题数列表")
    private String problemCountList;

    @Schema(description = "整改率列表")
    private String rectifyRateList;

    @Schema(description = "平均问题发现率列表")
    private String avgFoundRateList;

    @Schema(description = "统计人")
    private String statUser;

    @Schema(description = "统计时间(业务)")
    private LocalDateTime statTimeBiz;

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