package cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 执法公示新增/修改 Request VO")
@Data
public class EnforcementPublicitySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4786")
    private Long id;

    @Schema(description = "执法公示编号")
    private String publicityNumber;

    @Schema(description = "公示标题")
    private String publicityTitle;

    @Schema(description = "公示内容")
    private String publicityContent;

    @Schema(description = "公示类型", example = "2")
    private String publicityType;

    @Schema(description = "执法部门")
    private String enforcement;

    @Schema(description = "执法地点")
    private String enforcementLocation;

    @Schema(description = "执法依据")
    private String enforcementBasis;

    @Schema(description = "执法结果")
    private String enforcementResults;

    @Schema(description = "公示开始时间")
    private LocalDateTime announcementStartTime;

    @Schema(description = "公示截止时间")
    private LocalDateTime announcementDeadline;

}