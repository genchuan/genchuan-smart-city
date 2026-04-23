package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考评管理发布 Request VO")
@Data
public class AssessMgmtPublishReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,4")
    private Long[] ids;


}