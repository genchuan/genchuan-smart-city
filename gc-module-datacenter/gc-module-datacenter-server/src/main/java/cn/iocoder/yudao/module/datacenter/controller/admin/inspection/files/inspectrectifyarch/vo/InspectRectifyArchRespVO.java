package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检整改档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRectifyArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案ID")
    private String rectifyArchId;

    @Schema(description = "档案编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案编码")
    private String rectifyArchCode;

    @Schema(description = "档案名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案名称")
    private String rectifyArchName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "归档周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档周期")
    private String rectifyArchCycle;

    @Schema(description = "关联问题类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题类型")
    private String problemTypeName;

    @Schema(description = "包含整改任务数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("包含整改任务数")
    private Integer includeTaskCount;

    @Schema(description = "整改完成率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改完成率")
    private BigDecimal rectifyCompleteRate;

    @Schema(description = "档案附件URLs")
    @ExcelProperty("档案附件URLs")
    private String rectifyAttachUrls;

    @Schema(description = "归档人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档人")
    private String rectifyArchUser;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档时间")
    private LocalDateTime rectifyArchTime;

    @Schema(description = "档案状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("档案状态")
    private String rectifyArchStatus;

    @Schema(description = "销毁时间")
    @ExcelProperty("销毁时间")
    private LocalDateTime rectifyDestroyTime;

    @Schema(description = "归档说明")
    @ExcelProperty("归档说明")
    private String rectifyArchDesc;

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