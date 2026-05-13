package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报到管理新增/修改 Request VO")
@Data
public class CheckInSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26468")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8524")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "中考成绩")
    private BigDecimal examScore;

    @Schema(description = "补充信息")
    private String supplyInfo;

    @Schema(description = "报到确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "账号创建时间")
    private LocalDateTime accountCreateTime;

    @Schema(description = "账号状态：未创建/已创建", example = "2")
    private String accountStatus;

    @Schema(description = "状态：待确认/待审核/已报到", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待确认/待审核/已报到不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}