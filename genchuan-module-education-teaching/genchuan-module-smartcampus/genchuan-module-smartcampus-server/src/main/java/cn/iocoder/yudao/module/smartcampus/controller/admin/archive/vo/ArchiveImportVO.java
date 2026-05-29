package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Schema(description = "学生学籍档案 Excel 导入 VO")
@Data
public class ArchiveImportVO {

    @Schema(description = "学生编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学生编号不能为空")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Schema(description = "班级ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "班级ID不能为空")
    private Long classId;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "专业不能为空")
    private String major;

    @Schema(description = "层次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "层次不能为空")
    private String level;

    @Schema(description = "学习形式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学习形式不能为空")
    private String studyType;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "身份证号不能为空")
    @Length(min = 18, max = 18, message = "身份证号长度必须为18位")
    private String idCard;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "家长电话")
    private String parentPhone;

    @Schema(description = "学籍状态：0在籍 1休学 2退学 3异动", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学籍状态不能为空")
    private String status;

    @Schema(description = "建档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "建档时间不能为空")
    private LocalDateTime archiveTime;

    @Schema(description = "流程状态：0待审核 1正常 2已归档", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "流程状态不能为空")
    private String processStatus;

    @Schema(description = "异动原因")
    private String changeReason;

    @Schema(description = "备注")
    private String remark;
}