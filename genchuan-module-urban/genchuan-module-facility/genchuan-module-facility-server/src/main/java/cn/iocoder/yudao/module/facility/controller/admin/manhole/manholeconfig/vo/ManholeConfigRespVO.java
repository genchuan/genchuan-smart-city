package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖监测配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManholeConfigRespVO {

    @Schema(description = "主键（自增）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8741")
    @ExcelProperty("主键（自增）")
    private Long id;

    @Schema(description = "关联窨井盖表manhole_cover的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13424")
    @ExcelProperty("关联窨井盖表manhole_cover的id")
    private Long coverId;

    @Schema(description = "数据采集频率（单位：秒/分钟，根据业务定义）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据采集频率（单位：秒/分钟，根据业务定义）")
    private Integer collectFrequency;

    @Schema(description = "倾斜角度阈值（数值，单位：度）")
    @ExcelProperty("倾斜角度阈值（数值，单位：度）")
    private BigDecimal tiltAngleThreshold;

    @Schema(description = "[创建时间] 记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}