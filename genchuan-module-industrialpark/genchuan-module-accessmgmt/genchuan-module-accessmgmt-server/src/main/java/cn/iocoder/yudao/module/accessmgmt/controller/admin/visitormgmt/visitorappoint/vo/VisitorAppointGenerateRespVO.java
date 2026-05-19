package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 凭证生成 Response VO")
@Data
public class VisitorAppointGenerateRespVO {

    @Schema(description = "操作结果")
    private Boolean success;

    @Schema(description = "生成的通行凭证")
    private String ticket;

}
