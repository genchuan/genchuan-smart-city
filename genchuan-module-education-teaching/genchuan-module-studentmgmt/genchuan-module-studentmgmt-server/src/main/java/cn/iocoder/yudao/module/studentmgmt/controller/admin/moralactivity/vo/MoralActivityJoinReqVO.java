package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 德育活动报名 Request VO")
@Data
public class MoralActivityJoinReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;

   @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private Long studentId;


}