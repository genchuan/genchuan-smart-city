package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 到访验证 Response VO")
@Data
public class VisitorAppointVerifyRespVO {

    @Schema(description = "验证结果，true为通过")
    private Boolean pass;

    @Schema(description = "访客姓名")
    private String visitorName;

    @Schema(description = "拜访企业")
    private String visitCompany;

    @Schema(description = "验证结果描述")
    private String msg;

}
