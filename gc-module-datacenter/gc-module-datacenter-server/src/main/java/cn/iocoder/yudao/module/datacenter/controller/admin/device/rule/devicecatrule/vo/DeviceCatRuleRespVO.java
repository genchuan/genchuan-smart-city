package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备分类规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceCatRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类规则ID")
    @ExcelProperty("分类规则ID")
    private String catRuleId;

    @Schema(description = "父类规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("父类规则ID")
    private String parentCatRuleId;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级")
    private String catLevel;

    @Schema(description = "分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类代码")
    private String catCode;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类名称")
    private String catName;

    @Schema(description = "分类说明")
    @ExcelProperty("分类说明")
    private String catDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTimeSys;

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