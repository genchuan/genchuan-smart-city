package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结果系统对接新增/修改 Request VO")
@Data
public class EvalResultSystemDockSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "对接ID")
    private String resultDockId;

    @Schema(description = "对接编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "对接编号不能为空")
    private String resultDockCode;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联存档ID不能为空")
    private String resultArchId;

    @Schema(description = "外部系统ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "外部系统ID不能为空")
    private String externalSystemId;

    @Schema(description = "外部系统名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "外部系统名称不能为空")
    private String externalSystemName;

    @Schema(description = "对接方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "对接方式不能为空")
    private String dockMethod;

    @Schema(description = "对接频率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "对接频率不能为空")
    private String dockFrequency;

    @Schema(description = "数据映射规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据映射规则ID不能为空")
    private String mapRuleId;

    @Schema(description = "对接状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "对接状态不能为空")
    private String dockStatus;

    @Schema(description = "对接时间")
    private LocalDateTime dockTime;

    @Schema(description = "对接数据")
    private String dockData;

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