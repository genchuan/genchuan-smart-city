package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件分域监测报表新增/修改 Request VO")
@Data
public class DomMngCompMonRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报表ID不能为空")
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

    @Schema(description = "管理部件大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理部件大类ID不能为空")
    private String mngCompMajorId;

    @Schema(description = "管理部件大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理部件大类名称不能为空")
    private String mngCompMajorName;

    @Schema(description = "管理部件小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理部件小类ID不能为空")
    private String mngCompMinorId;

    @Schema(description = "管理部件小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理部件小类名称不能为空")
    private String mngCompMinorName;

    @Schema(description = "分域ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分域ID不能为空")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分域名称不能为空")
    private String domName;

    @Schema(description = "关联点位数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联点位数量不能为空")
    private Integer relPtCount;

    @Schema(description = "关联设备数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联设备数量不能为空")
    private Integer relDeviceCount;

    @Schema(description = "设备在线率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备在线率不能为空")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "部件故障事件数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部件故障事件数不能为空")
    private Integer compFaultEvtCount;

    @Schema(description = "故障修复率")
    private BigDecimal faultRepairRate;

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