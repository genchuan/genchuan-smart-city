package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基础监测点位报表新增/修改 Request VO")
@Data
public class BasicMonPtRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String adminName;

    @Schema(description = "点位类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "点位类型不能为空")
    private String ptType;

    @Schema(description = "点位总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "点位总数(个)不能为空")
    private Integer totalPtCount;

    @Schema(description = "启用点位数量(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "启用点位数量(个)不能为空")
    private Integer enablePtCount;

    @Schema(description = "停用点位数量(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停用点位数量(个)不能为空")
    private Integer disablePtCount;

    @Schema(description = "新增点位数量(个)")
    private Integer newPtCount;

    @Schema(description = "关联设备总数(个)")
    private Integer relDeviceCount;

    @Schema(description = "报表生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报表生成时间不能为空")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报表生成人不能为空")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}