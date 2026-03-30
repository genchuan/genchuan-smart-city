package cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 岗位职责划分管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PositionResponsibilityRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "岗位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("岗位名称")
    private String positionName;

    @Schema(description = "岗位职责描述")
    @ExcelProperty("岗位职责描述")
    private String responsibilityDesc;

    @Schema(description = "任职要求")
    @ExcelProperty("任职要求")
    private String qualificationReq;

    @Schema(description = "所属单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属单位")
    private String belongUnit;

    @Schema(description = "负责人")
    @ExcelProperty("负责人")
    private String manager;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}