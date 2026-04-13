package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 值班管理新增/修改 Request VO")
@Data
public class DutyMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22240")
    private Long id;

    @Schema(description = "值班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "值班日期不能为空")
    private LocalDate dutyDate;

    @Schema(description = "值班人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "值班人不能为空")
    private String dutyUser;

    @Schema(description = "打卡时间")
    private LocalDateTime checkInTime;

    @Schema(description = "打卡状态：未打卡/已打卡", example = "1")
    private String checkInStatus;

    @Schema(description = "调班原因", example = "不香")
    private String transferReason;

    @Schema(description = "调班替代人")
    private String transferUser;

    @Schema(description = "调班状态：无/待审批/已通过/已驳回", example = "1")
    private String transferStatus;

    @Schema(description = "出车事由", example = "不对")
    private String carReason;

    @Schema(description = "出车目的地")
    private String carDestination;

    @Schema(description = "出车状态：无/待审批/已通过", example = "2")
    private String carStatus;

    @Schema(description = "值班记录")
    private String recordContent;

    @Schema(description = "记录上传时间")
    private LocalDateTime recordUploadTime;

    @Schema(description = "状态：待打卡/待调班审批/待出车审批/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待打卡/待调班审批/待出车审批/已完成不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}