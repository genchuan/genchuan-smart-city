package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 德育资源上架/下架 Request VO")
@Data
public class MoralResourceOnlineReqVO {

        @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long[] ids;

}