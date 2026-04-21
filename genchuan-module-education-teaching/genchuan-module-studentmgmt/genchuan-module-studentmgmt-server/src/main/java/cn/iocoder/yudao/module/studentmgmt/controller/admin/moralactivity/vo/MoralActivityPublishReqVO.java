package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 德育活动发布 Request VO")
@Data
public class MoralActivityPublishReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32555")
    private Long[] ids;


}