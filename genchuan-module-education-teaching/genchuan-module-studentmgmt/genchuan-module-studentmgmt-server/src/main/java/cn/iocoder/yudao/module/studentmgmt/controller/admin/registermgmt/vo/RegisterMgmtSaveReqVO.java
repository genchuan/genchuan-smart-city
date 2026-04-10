package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报名管理新增/修改 Request VO")
@Data
public class RegisterMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17725")
    private Long id;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "学生姓名不能为空")
    private String studentName;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "身份证号不能为空")
    private String idCard;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "意向专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "意向专业不能为空")
    private String major;

    @Schema(description = "报名时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报名时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "录取确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "状态：待审核/已录取", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/已录取不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}