package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检验收档案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectAcceptArchPageReqVO extends PageParam {

    @Schema(description = "档案ID")
    private String acceptArchId;

    @Schema(description = "档案编码")
    private String acceptArchCode;

    @Schema(description = "档案名称")
    private String acceptArchName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "归档周期")
    private String acceptArchCycle;

    @Schema(description = "包含验收记录数")
    private Integer includeAcceptCount;

    @Schema(description = "验收合格率")
    private BigDecimal acceptQualifiedRate;

    @Schema(description = "档案附件URLs")
    private String acceptAttachUrls;

    @Schema(description = "归档人")
    private String acceptArchUser;

    @Schema(description = "归档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] acceptArchTime;

    @Schema(description = "档案状态")
    private String acceptArchStatus;

    @Schema(description = "销毁时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] acceptDestroyTime;

    @Schema(description = "归档说明")
    private String acceptArchDesc;

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