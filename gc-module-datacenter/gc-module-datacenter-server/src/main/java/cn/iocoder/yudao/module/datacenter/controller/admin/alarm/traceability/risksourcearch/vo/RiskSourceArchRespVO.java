package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 风险源档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RiskSourceArchRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "风险源ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险源ID")
    private String riskSourceId;

    @Schema(description = "风险源编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险源编号")
    private String riskSourceNo;

    @Schema(description = "风险源名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险源名称")
    private String riskSourceName;

    @Schema(description = "风险源类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险源类型")
    private String riskSourceType;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "所在行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所在行政区划代码")
    private String regionCode;

    @Schema(description = "所在行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所在行政区划名称")
    private String regionName;

    @Schema(description = "所在位置描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所在位置描述")
    private String locationDesc;

    @Schema(description = "关联资产ID")
    @ExcelProperty("关联资产ID")
    private String assetId;

    @Schema(description = "关联资产名称")
    @ExcelProperty("关联资产名称")
    private String assetName;

    @Schema(description = "责任部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门ID")
    private String deptId;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任部门名称")
    private String deptName;

    @Schema(description = "历史预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("历史预警次数")
    private Integer historyWarnCount;

    @Schema(description = "建档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("建档时间")
    private LocalDateTime createTime;

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

}