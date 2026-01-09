package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 城市管理执法新增/修改 Request VO")
@Data
public class LawEnforcementSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7475")
    private Long id;

    @Schema(description = "执法案件编号")
    private String lawEnforcementNumber;

    @Schema(description = "案件发生地点")
    private String locationTheCase;

    @Schema(description = "发生时间")
    private String occurrenceTime;

    @Schema(description = "涉事主体信息")
    private String informationInvolved;

    @Schema(description = "案件详情描述", example = "随便")
    private String caseDetailsDescription;

    @Schema(description = "案件处理时长")
    private String durationOfCaseHandling;

    @Schema(description = "投诉举报次数")
    private String numberComplaintsReports;

    @Schema(description = "投诉内容")
    private String complaintContent;

}