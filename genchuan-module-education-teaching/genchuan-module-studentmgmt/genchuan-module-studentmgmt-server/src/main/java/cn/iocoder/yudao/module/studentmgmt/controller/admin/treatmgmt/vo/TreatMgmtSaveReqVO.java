package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 就诊管理新增/修改 Request VO")
@Data
public class TreatMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5311")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19989")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "就诊类型：门诊/急诊/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "就诊类型：门诊/急诊/其他不能为空")
    private String treatType;

    @Schema(description = "症状描述")
    private String symptom;

    @Schema(description = "就诊登记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "就诊登记时间不能为空")
    private LocalDateTime registerTime;

    @Schema(description = "就诊内容")
    private String treatContent;

    @Schema(description = "预约时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "家长反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "状态：待审核/已就诊", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/已就诊不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}