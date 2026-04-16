package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 互联互通表审核 Request VO")
@Data
public class InterconnectionAuditReqVO {

    @Schema(description = "对接申请主键 ID", example = "4002")
    private Integer id;

    @Schema(description = "审核备注", example = "审核通过，已开通对接")
    private String auditRemark;

    @Schema(description = "是否审核通过，true - 通过，false - 驳回", example = "true")
    private Boolean pass;

}