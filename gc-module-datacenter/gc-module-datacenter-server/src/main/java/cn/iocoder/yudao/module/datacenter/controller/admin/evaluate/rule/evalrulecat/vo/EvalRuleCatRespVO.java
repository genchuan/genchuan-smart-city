package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 规则分类管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalRuleCatRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则分类ID")
    @ExcelProperty("规则分类ID")
    private String ruleCatId;

    @Schema(description = "规则分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("规则分类名称")
    private String ruleCatName;

    @Schema(description = "分类编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类编码")
    private String ruleCatCode;

    @Schema(description = "适用指标体系ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用指标体系ID")
    private String idxSystemId;

    @Schema(description = "适用指标体系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用指标体系名称")
    private String idxSystemName;

    @Schema(description = "分类描述")
    @ExcelProperty("分类描述")
    private String catDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

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

    @Schema(description = "创建时间(系统)")
    @ExcelProperty("创建时间(系统)")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间(系统)")
    @ExcelProperty("更新时间(系统)")
    private LocalDateTime updateTimeSys;

}