package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件统计报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompRptRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6378")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "唯一编码，采用UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "5519")
    @ExcelProperty("唯一编码，采用UUID生成")
    private String statId;

    @Schema(description = "统计周期类型，格式:YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期类型，格式:YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期的中文描述，如“2025年Q3”“2025年09月”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("统计周期的中文描述，如“2025年Q3”“2025年09月”")
    private String statCycleName;

    @Schema(description = "符合GB/T 2260，统计区域的行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("符合GB/T 2260，统计区域的行政区划代码")
    private String regionCode;

    @Schema(description = "与行政区划代码关联，自动同步区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("与行政区划代码关联，自动同步区域名称")
    private String regionName;

    @Schema(description = "关联监测部件分类配置表的大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3841")
    @ExcelProperty("关联监测部件分类配置表的大类ID")
    private String compMajorId;

    @Schema(description = "与部件大类ID关联，自动同步名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("与部件大类ID关联，自动同步名称")
    private String compMajorName;

    @Schema(description = "关联监测部件分类配置表的小类ID(钻取统计时必填)", example = "24416")
    @ExcelProperty("关联监测部件分类配置表的小类ID(钻取统计时必填)")
    private String compMinorId;

    @Schema(description = "与部件小类ID关联，自动同步名称", example = "王五")
    @ExcelProperty("与部件小类ID关联，自动同步名称")
    private String compMinorName;

    @Schema(description = "该维度下监测部件总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30917")
    @ExcelProperty("该维度下监测部件总数量")
    private Integer totalCompCount;

    @Schema(description = "状态为“正常”的部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30465")
    @ExcelProperty("状态为“正常”的部件数量")
    private Integer normalCompCount;

    @Schema(description = "状态为“异常”的部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "17888")
    @ExcelProperty("状态为“异常”的部件数量")
    private Integer abnCompCount;

    @Schema(description = "状态为“维护”的部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "4404")
    @ExcelProperty("状态为“维护”的部件数量")
    private Integer mntCompCount;

    @Schema(description = "状态为“废弃”的部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "21300")
    @ExcelProperty("状态为“废弃”的部件数量")
    private Integer discardCompCount;

    @Schema(description = "统计周期内新增的部件数量（创建时间在周期内）", example = "2387")
    @ExcelProperty("统计周期内新增的部件数量（创建时间在周期内）")
    private Integer newCompCount;

    @Schema(description = "统计周期内更新的部件数量（更新时间在周期内）", example = "18955")
    @ExcelProperty("统计周期内更新的部件数量（更新时间在周期内）")
    private Integer updateCompCount;

    @Schema(description = "生成报表的用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成报表的用户ID")
    private String statUser;

    @Schema(description = "报表生成时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表生成时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTime;

    @Schema(description = "报表说明，如“统计范围：XX市建成区监测部件”", example = "你猜")
    @ExcelProperty("报表说明，如“统计范围：XX市建成区监测部件”")
    private String rptRemark;

    @Schema(description = "预留字段，存储统计额外维度（如“统计范围”）")
    @ExcelProperty("预留字段，存储统计额外维度（如“统计范围”）")
    private String extCat1;

    @Schema(description = "预留字段，存储统计额外维度（如“统计范围”）")
    @ExcelProperty("预留字段，存储统计额外维度（如“统计范围”）")
    private String extCat2;

    @Schema(description = "预留通用字段，存储额外统计信息")
    @ExcelProperty("预留通用字段，存储额外统计信息")
    private String extCommon1;

    @Schema(description = "预留通用字段，存储额外统计信息")
    @ExcelProperty("预留通用字段，存储额外统计信息")
    private String extCommon2;

}