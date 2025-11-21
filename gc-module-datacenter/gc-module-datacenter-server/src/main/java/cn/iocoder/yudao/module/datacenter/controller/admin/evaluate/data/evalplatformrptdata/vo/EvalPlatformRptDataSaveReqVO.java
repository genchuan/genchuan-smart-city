package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 平台上报数据新增/修改 Request VO")
@Data
public class EvalPlatformRptDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "上报数据ID")
    private String platformRptDataId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项ID不能为空")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项名称不能为空")
    private String idxItemName;

    @Schema(description = "数据值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据值不能为空")
    private String dataValue;

    @Schema(description = "数据单位")
    private String dataUnit;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报时间不能为空")
    private LocalDateTime rptTime;

    @Schema(description = "上报人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报人ID不能为空")
    private String rptUserId;

    @Schema(description = "上报人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报人姓名不能为空")
    private String rptUserName;

    @Schema(description = "数据状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据状态不能为空")
    private String dataStatus;

    @Schema(description = "校验失败原因")
    private String verifyFailReason;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}