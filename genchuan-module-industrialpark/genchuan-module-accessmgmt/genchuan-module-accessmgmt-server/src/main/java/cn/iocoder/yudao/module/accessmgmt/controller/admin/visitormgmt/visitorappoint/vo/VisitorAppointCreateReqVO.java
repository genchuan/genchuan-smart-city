package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 访客预约新增 Request VO")
@Data
public class VisitorAppointCreateReqVO {

    @Schema(description = "访客姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "访客姓名不能为空")
    private String visitorName;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    @Schema(description = "拜访企业", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "拜访企业不能为空")
    private String visitCompany;

    @Schema(description = "拜访时间，格式时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "拜访时间不能为空")
    private String visitTime;

}
