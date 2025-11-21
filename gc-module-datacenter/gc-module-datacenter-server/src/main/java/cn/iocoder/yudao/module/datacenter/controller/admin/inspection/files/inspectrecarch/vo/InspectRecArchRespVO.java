package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检记录档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRecArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "档案ID")
    @ExcelProperty("档案ID")
    private String archId;

    @Schema(description = "档案编码")
    @ExcelProperty("档案编码")
    private String archCode;

    @Schema(description = "档案名称")
    @ExcelProperty("档案名称")
    private String archName;

    @Schema(description = "档案类型")
    @ExcelProperty("档案类型")
    private String archType;

    @Schema(description = "所属区域代码")
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "归档周期")
    @ExcelProperty("归档周期")
    private String archCycle;

    @Schema(description = "包含记录数")
    @ExcelProperty("包含记录数")
    private Integer includeRecCount;

    @Schema(description = "档案附件URLs")
    @ExcelProperty("档案附件URLs")
    private String archAttachUrls;

    @Schema(description = "归档人")
    @ExcelProperty("归档人")
    private String archUser;

    @Schema(description = "归档时间")
    @ExcelProperty("归档时间")
    private LocalDateTime archTime;

    @Schema(description = "档案状态")
    @ExcelProperty("档案状态")
    private String archStatus;

    @Schema(description = "销毁时间")
    @ExcelProperty("销毁时间")
    private LocalDateTime destroyTime;

    @Schema(description = "归档说明")
    @ExcelProperty("归档说明")
    private String archDesc;

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