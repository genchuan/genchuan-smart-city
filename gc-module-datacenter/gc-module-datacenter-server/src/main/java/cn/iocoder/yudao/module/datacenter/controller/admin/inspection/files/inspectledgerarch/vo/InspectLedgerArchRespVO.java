package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检台账档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectLedgerArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案ID")
    private String ledgerArchId;

    @Schema(description = "档案编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案编码")
    private String ledgerArchCode;

    @Schema(description = "档案名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案名称")
    private String ledgerArchName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "台账周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("台账周期")
    private String ledgerCycle;

    @Schema(description = "核心指标JSON", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("核心指标JSON")
    private String coreIdxJson;

    @Schema(description = "包含档案类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("包含档案类型")
    private String includeArchTypes;

    @Schema(description = "台账附件URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("台账附件URL")
    private String ledgerAttachUrl;

    @Schema(description = "归档人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档人")
    private String ledgerArchUser;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档时间")
    private LocalDateTime ledgerArchTime;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案状态")
    private String ledgerArchStatus;

    @Schema(description = "销毁时间")
    @ExcelProperty("销毁时间")
    private LocalDateTime ledgerDestroyTime;

    @Schema(description = "归档说明")
    @ExcelProperty("归档说明")
    private String ledgerArchDesc;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}