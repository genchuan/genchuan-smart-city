package cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测结果录入新增/修改 Request VO")
@Data
public class TestResultSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "样本编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "样本编号不能为空")
    private String sampleCode;

    @Schema(description = "检测指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检测指标不能为空")
    private String testIndicator;

    @Schema(description = "检测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测值不能为空")
    private Double testValue;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位不能为空")
    private String unit;

    @Schema(description = "检测方法")
    private String testMethod;

    @Schema(description = "检测人员", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检测人员不能为空")
    private String testOperator;

    @Schema(description = "检测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测时间不能为空")
    private LocalDateTime testTime;

    @Schema(description = "设备编号")
    private String equipmentCode;

}