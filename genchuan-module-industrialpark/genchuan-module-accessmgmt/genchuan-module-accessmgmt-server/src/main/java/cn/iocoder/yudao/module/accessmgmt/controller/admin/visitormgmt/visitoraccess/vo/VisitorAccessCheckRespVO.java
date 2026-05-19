package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 凭证核验 Response VO")
@Data
public class VisitorAccessCheckRespVO {

    @Schema(description = "核验结果，true为通过")
    private Boolean pass;

    @Schema(description = "核验结果描述")
    private String msg;

}
