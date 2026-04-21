package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 德育活动记录 Request VO")
@Data
public class MoralActivityRecordReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;

    @Schema(description = "活动参与记录内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "活动参与记录内容abc")
    private String content;

    @Schema(description = "实际参与人数")
    private Integer joinNum;


}