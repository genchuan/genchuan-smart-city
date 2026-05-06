package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 升学管理查询 Request VO")
@Data
public class StudyUpQueryReqVO {

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1428")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;


}