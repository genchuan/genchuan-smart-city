package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 学籍档案新增/修改 Request VO")
@Data
public class ArchiveSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1190")
    private Long id;

    @Schema(description = "学生编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学生编号不能为空")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Schema(description = "班级ID，关联system_dept.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10143")
    @NotNull(message = "班级ID，关联system_dept.id不能为空")
    private Long classId;

    @Schema(description = "专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "专业不能为空")
    private String major;

    @Schema(description = "层次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "层次不能为空")
    private String level;

    @Schema(description = "学习形式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "学习形式不能为空")
    private String studyType;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "身份证号不能为空")
    private String idCard;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "家长电话")
    private String parentPhone;

    @Schema(description = "学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label不能为空")
    private String status;

    @Schema(description = "建档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "建档时间不能为空")
    private LocalDateTime archiveTime;

    @Schema(description = "流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label不能为空")
    private String processStatus;

    @Schema(description = "驳回原因", example = "不对")
    private String rejectReason;

    @Schema(description = "异动原因", example = "不对")
    private String changeReason;

    @Schema(description = "佐证材料", example = "https://www.iocoder.cn")
    private String evidenceUrl;

    @Schema(description = "处分有效期")
    private LocalDateTime punishValidTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "扩展字段，JSON格式（插入时需显式提供值）")
    private String extension;

}