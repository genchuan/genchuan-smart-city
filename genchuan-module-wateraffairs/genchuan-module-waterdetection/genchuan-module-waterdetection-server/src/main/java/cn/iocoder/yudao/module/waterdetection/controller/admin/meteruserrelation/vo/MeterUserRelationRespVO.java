package cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 户表关联及变更管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MeterUserRelationRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "户表编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("户表编号")
    private String meterCode;

    @Schema(description = "原用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原用户编号")
    private String oldUserCode;

    @Schema(description = "新用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("新用户编号")
    private String newUserCode;

    @Schema(description = "变更原因")
    @ExcelProperty("变更原因")
    private String changeReason;

    @Schema(description = "变更时间")
    @ExcelProperty("变更时间")
    private LocalDateTime changeTime;

    @Schema(description = "经办人")
    @ExcelProperty("经办人")
    private String operator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}