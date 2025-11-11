package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基础监测指标新增/修改 Request VO")
@Data
public class BasicMonIdxSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "指标ID")
    private String idxId;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标名称不能为空")
    private String idxName;

    @Schema(description = "指标编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标编码不能为空")
    private String idxCode;

    @Schema(description = "指标单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标单位不能为空")
    private String idxUnit;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "数据长度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据长度不能为空")
    private String dataLength;

    @Schema(description = "关联点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联点位类型不能为空")
    private String relPtType;

    @Schema(description = "预警阈值上限")
    private String warnThresholdMax;

    @Schema(description = "预警阈值下限")
    private String warnThresholdMin;

    @Schema(description = "指标说明")
    private String idxDesc;

    @Schema(description = "指标状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "指标状态不能为空")
    private Boolean idxStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间不能为空")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}