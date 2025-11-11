package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基础监测事件新增/修改 Request VO")
@Data
public class BasicMonEvtSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "事件ID")
    private String evtId;

    @Schema(description = "事件编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件编码不能为空")
    private String evtCode;

    @Schema(description = "事件类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件类型不能为空")
    private String evtType;

    @Schema(description = "关联点位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联点位ID不能为空")
    private String relPtId;

    @Schema(description = "关联点位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联点位名称不能为空")
    private String relPtName;

    @Schema(description = "关联指标ID")
    private String relIdxId;

    @Schema(description = "关联指标名称")
    private String relIdxName;

    @Schema(description = "关联设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联设备编码不能为空")
    private String relDeviceCode;

    @Schema(description = "关联设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联设备名称不能为空")
    private String relDeviceName;

    @Schema(description = "事件数值")
    private String evtValue;

    @Schema(description = "事发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事发时间不能为空")
    private LocalDateTime evtTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置状态不能为空")
    private String dealStatus;

    @Schema(description = "派单部门代码")
    private String deptCode;

    @Schema(description = "派单部门名称")
    private String deptName;

    @Schema(description = "处置人ID")
    private Long dealUserId;

    @Schema(description = "处置人姓名")
    private String dealUserName;

    @Schema(description = "处置结果")
    private String dealResult;

    @Schema(description = "处置时间")
    private LocalDateTime dealTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}