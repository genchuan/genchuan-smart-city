package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检整改结果验收新增/修改 Request VO")
@Data
public class InspectRectifyAcceptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "验收ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "验收ID不能为空")
    private String acceptId;

    @Schema(description = "验收编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "验收编码不能为空")
    private String acceptCode;

    @Schema(description = "关联整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联整改任务ID不能为空")
    private String rectifyTaskId;

    @Schema(description = "关联整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联整改任务编码不能为空")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题ID不能为空")
    private String problemId;

    @Schema(description = "整改结果描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改结果描述不能为空")
    private String rectifyResultDesc;

    @Schema(description = "整改后照片URLs", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改后照片URLs不能为空")
    private String rectifyAfterPhotoUrls;

    @Schema(description = "整改前照片URLs", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改前照片URLs不能为空")
    private String rectifyBeforePhotoUrls;

    @Schema(description = "验收结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "验收结果不能为空")
    private String acceptResult;

    @Schema(description = "验收意见")
    private String acceptOpinion;

    @Schema(description = "验收人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "验收人不能为空")
    private String acceptUser;

    @Schema(description = "验收时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "验收时间不能为空")
    private LocalDateTime acceptTime;

    @Schema(description = "整改提交人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改提交人不能为空")
    private String rectifySubmitUser;

    @Schema(description = "整改提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改提交时间不能为空")
    private LocalDateTime rectifySubmitTime;

    @Schema(description = "任务状态变更", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务状态变更不能为空")
    private String taskStatusChange;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}