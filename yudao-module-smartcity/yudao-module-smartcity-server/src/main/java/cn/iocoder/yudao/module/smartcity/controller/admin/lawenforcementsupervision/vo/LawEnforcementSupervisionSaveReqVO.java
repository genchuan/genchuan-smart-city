package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 执法监督新增/修改 Request VO")
@Data
public class LawEnforcementSupervisionSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24171")
    private Long id;

    @Schema(description = "监督编号", example = "11753")
    private String supervisionId;

    @Schema(description = "执法事件编号")
    private String eventNumber;

    @Schema(description = "执法人员")
    private String officials;

    @Schema(description = "监督人员")
    private String personnel;

    @Schema(description = "监督时间")
    private String time;

    @Schema(description = "监督方式")
    private String method;

    @Schema(description = "证据采集完整性")
    private String integrityCollection;

    @Schema(description = "当事人满意度")
    private String satisfactionInvolved;

    @Schema(description = "监督结果评价")
    private String resultEvaluation;

    @Schema(description = "违规行为描述", example = "随便")
    private String violationDescription;

}