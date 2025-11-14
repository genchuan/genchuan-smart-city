package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 基础监测指标 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BasicMonIdxRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标ID")
    @ExcelProperty("指标ID")
    private String idxId;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标名称")
    private String idxName;

    @Schema(description = "指标编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标编码")
    private String idxCode;

    @Schema(description = "指标单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标单位")
    private String idxUnit;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "数据长度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据长度")
    private String dataLength;

    @Schema(description = "关联点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联点位类型")
    private String relPtType;

    @Schema(description = "预警阈值上限")
    @ExcelProperty("预警阈值上限")
    private String warnThresholdMax;

    @Schema(description = "预警阈值下限")
    @ExcelProperty("预警阈值下限")
    private String warnThresholdMin;

    @Schema(description = "指标说明")
    @ExcelProperty("指标说明")
    private String idxDesc;

    @Schema(description = "指标状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标状态")
    private Boolean idxStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
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