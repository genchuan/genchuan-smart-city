package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 奖助勤贷新增/修改 Request VO")
@Data
public class AidWorkSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19452")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10682")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "资助类型：奖学金/助学金/助学贷款/勤工俭学", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资助类型：奖学金/助学金/助学贷款/勤工俭学不能为空")
    private String aidType;

    @Schema(description = "申请金额")
    private BigDecimal applyAmount;

    @Schema(description = "申报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申报时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "流程状态：跟进中/已完成", example = "2")
    private String processStatus;

    @Schema(description = "状态：待审核/已通过/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待审核/已通过/已完成不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}