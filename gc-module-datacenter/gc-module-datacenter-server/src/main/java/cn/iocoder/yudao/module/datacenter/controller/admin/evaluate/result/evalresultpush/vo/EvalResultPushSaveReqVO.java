package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果关联推送新增/修改 Request VO")
@Data
public class EvalResultPushSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "推送ID")
    private String resultPushId;

    @Schema(description = "推送编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送编号不能为空")
    private String resultPushCode;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联存档ID不能为空")
    private String resultArchId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "推送目标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送目标不能为空")
    private String pushTarget;

    @Schema(description = "推送方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送方式不能为空")
    private String pushMethod;

    @Schema(description = "推送地址")
    private String pushAddress;

    @Schema(description = "推送内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送内容不能为空")
    private String pushContent;

    @Schema(description = "推送状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送状态不能为空")
    private String pushStatus;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "失败原因")
    private String failReason;

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