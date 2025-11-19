package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检台账档案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectLedgerArchPageReqVO extends PageParam {

    @Schema(description = "档案ID")
    private String ledgerArchId;

    @Schema(description = "档案编码")
    private String ledgerArchCode;

    @Schema(description = "档案名称")
    private String ledgerArchName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "台账周期")
    private String ledgerCycle;

    @Schema(description = "核心指标JSON")
    private String coreIdxJson;

    @Schema(description = "包含档案类型")
    private String includeArchTypes;

    @Schema(description = "台账附件URL")
    private String ledgerAttachUrl;

    @Schema(description = "归档人")
    private String ledgerArchUser;

    @Schema(description = "归档时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ledgerArchTime;

    @Schema(description = "档案状态")
    private String ledgerArchStatus;

    @Schema(description = "销毁时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ledgerDestroyTime;

    @Schema(description = "归档说明")
    private String ledgerArchDesc;

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