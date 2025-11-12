package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 基础监测点位报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BasicMonPtRptPageReqVO extends PageParam {

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "点位类型")
    private String ptType;

    @Schema(description = "点位总数(个)")
    private Integer totalPtCount;

    @Schema(description = "启用点位数量(个)")
    private Integer enablePtCount;

    @Schema(description = "停用点位数量(个)")
    private Integer disablePtCount;

    @Schema(description = "新增点位数量(个)")
    private Integer newPtCount;

    @Schema(description = "关联设备总数(个)")
    private Integer relDeviceCount;

    @Schema(description = "报表生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rptCreateTime;

    @Schema(description = "报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

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