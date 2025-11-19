package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检验收档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectAcceptArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案ID")
    private String acceptArchId;

    @Schema(description = "档案编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案编码")
    private String acceptArchCode;

    @Schema(description = "档案名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案名称")
    private String acceptArchName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "归档周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档周期")
    private String acceptArchCycle;

    @Schema(description = "包含验收记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("包含验收记录数")
    private Integer includeAcceptCount;

    @Schema(description = "验收合格率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收合格率")
    private BigDecimal acceptQualifiedRate;

    @Schema(description = "档案附件URLs")
    @ExcelProperty("档案附件URLs")
    private String acceptAttachUrls;

    @Schema(description = "归档人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档人")
    private String acceptArchUser;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档时间")
    private LocalDateTime acceptArchTime;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案状态")
    private String acceptArchStatus;

    @Schema(description = "销毁时间")
    @ExcelProperty("销毁时间")
    private LocalDateTime acceptDestroyTime;

    @Schema(description = "归档说明")
    @ExcelProperty("归档说明")
    private String acceptArchDesc;

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