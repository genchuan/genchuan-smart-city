package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 申诉管理新增/修改 Request VO")
@Data
public class EvalAppealSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "申诉ID")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉编号不能为空")
    private String appealCode;

    @Schema(description = "关联公示ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联公示ID不能为空")
    private String resultPublicId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "申诉人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉人ID不能为空")
    private String appealUserId;

    @Schema(description = "申诉人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉人姓名不能为空")
    private String appealUserName;

    @Schema(description = "申诉理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉理由不能为空")
    private String appealReason;

    @Schema(description = "证明材料")
    private String proofMats;

    @Schema(description = "申诉状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "申诉状态不能为空")
    private String appealStatus;

    @Schema(description = "提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提交时间不能为空")
    private LocalDateTime submitTime;

    @Schema(description = "撤销时间")
    private LocalDateTime cancelTime;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}