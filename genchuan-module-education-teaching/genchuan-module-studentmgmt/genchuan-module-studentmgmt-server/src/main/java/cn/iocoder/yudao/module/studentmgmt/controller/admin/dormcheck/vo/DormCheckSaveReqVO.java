package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍考勤新增/修改 Request VO")
@Data
public class DormCheckSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24531")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14725")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "考勤时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "考勤时间不能为空")
    private LocalDateTime checkTime;

    @Schema(description = "考勤状态：正常/迟到/未到", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "考勤状态：正常/迟到/未到不能为空")
    private String checkStatus;

    @Schema(description = "异常类型：无/晚归/未归", example = "1")
    private String abnormalType;

    @Schema(description = "补卡时间")
    private LocalDateTime repairTime;

    @Schema(description = "补卡人")
    private String repairUser;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "在寝率")
    private BigDecimal inRate;

    @Schema(description = "状态：正常/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：正常/异常不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}