package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 风险传播链新增/修改 Request VO")
@Data
public class RiskPropagateChainSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "传播链ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "传播链ID不能为空")
    private String propagateChainId;

    @Schema(description = "传播链编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "传播链编号不能为空")
    private String propagateChainNo;

    @Schema(description = "源头预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "源头预警ID不能为空")
    private String sourceWarnId;

    @Schema(description = "源头预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "源头预警名称不能为空")
    private String sourceWarnName;

    @Schema(description = "传播节点序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "传播节点序号不能为空")
    private Integer nodeSeq;

    @Schema(description = "传播节点内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "传播节点内容不能为空")
    private String nodeContent;

    @Schema(description = "节点类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "节点类型不能为空")
    private String nodeType;

    @Schema(description = "发生时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发生时间不能为空")
    private LocalDateTime occurTime;

    @Schema(description = "影响范围")
    private String impactRange;

    @Schema(description = "关联设备ID")
    private String deviceId;

    @Schema(description = "关联设备名称")
    private String deviceName;

    @Schema(description = "记录人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录人ID不能为空")
    private String recUserId;

    @Schema(description = "记录人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录人姓名不能为空")
    private String recUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}