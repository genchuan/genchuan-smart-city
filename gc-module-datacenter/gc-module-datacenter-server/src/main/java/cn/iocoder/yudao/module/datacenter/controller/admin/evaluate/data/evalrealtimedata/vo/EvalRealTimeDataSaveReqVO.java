package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 实时监测数据接入新增/修改 Request VO")
@Data
public class EvalRealTimeDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "数据ID")
    private String realTimeDataId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项ID不能为空")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项名称不能为空")
    private String idxItemName;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "数据值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据值不能为空")
    private String dataValue;

    @Schema(description = "数据时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数据时间不能为空")
    private LocalDateTime dataTime;

    @Schema(description = "接入状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "接入状态不能为空")
    private String accessStatus;

    @Schema(description = "失败原因")
    private String failReason;

    @Schema(description = "同步频率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "同步频率不能为空")
    private Integer syncFrequency;

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