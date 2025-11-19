package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检整改档案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectRectifyArchPageReqVO extends PageParam {

    @Schema(description = "档案ID")
    private String rectifyArchId;

    @Schema(description = "档案编码")
    private String rectifyArchCode;

    @Schema(description = "档案名称")
    private String rectifyArchName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "归档周期")
    private String rectifyArchCycle;

    @Schema(description = "关联问题类型")
    private String problemTypeName;

    @Schema(description = "包含整改任务数")
    private Integer includeTaskCount;

    @Schema(description = "整改完成率")
    private BigDecimal rectifyCompleteRate;

    @Schema(description = "档案附件URLs")
    private String rectifyAttachUrls;

    @Schema(description = "归档人")
    private String rectifyArchUser;

    @Schema(description = "归档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyArchTime;

    @Schema(description = "档案状态")
    private String rectifyArchStatus;

    @Schema(description = "销毁时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyDestroyTime;

    @Schema(description = "归档说明")
    private String rectifyArchDesc;

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