package cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 风险管控 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RiskControlRespVO {

    @Schema(description = "风险的唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29447")
    @ExcelProperty("风险的唯一标识")
    private Integer id;

    @Schema(description = "风险名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("风险名称")
    private String riskName;

    @Schema(description = "风险的详细描述", example = "你猜")
    @ExcelProperty("风险的详细描述")
    private String riskDescription;

    @Schema(description = "风险等级")
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "风险状态", example = "2")
    @ExcelProperty("风险状态")
    private String riskStatus;

    @Schema(description = "记录的创建时间")
    @ExcelProperty("记录的创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "记录的上次更新时间")
    @ExcelProperty("记录的上次更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "是否删除")
    @ExcelProperty("是否删除")
    private String deleted;

    @Schema(description = "租户编号", example = "12252")
    @ExcelProperty("租户编号")
    private Integer tenantId;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}