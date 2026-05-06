package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 升学管理选择 Request VO")
@Data
public class StudyUpSelectReqVO {
    @Schema(description = "主键 ID", example = "2")
    private Long id;
    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1428")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;
    @Schema(description = "目标院校名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "北京大学")
    @NotEmpty(message = "目标院校名称不能为空")
    private String schoolName;
    @Schema(description = "院校类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "public")
    @NotEmpty(message = "院校类型不能为空")
    private String schoolType;
    @Schema(description = "意向专业", requiredMode = Schema.RequiredMode.REQUIRED, example = "软件工程")
    @NotEmpty(message = "意向专业不能为空")
    private String major;


}