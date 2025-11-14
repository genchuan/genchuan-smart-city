package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 行政区划分域新增/修改 Request VO")
@Data
public class DomDivAdminSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分域名称不能为空")
    private String domName;

    @Schema(description = "分域编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分域编码不能为空")
    private String domCode;

    @Schema(description = "行政区域级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区域级别不能为空")
    private String adminLevel;

    @Schema(description = "行政区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区域代码不能为空")
    private String adminCode;

    @Schema(description = "行政区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区域名称不能为空")
    private String adminName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "分域负责人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分域负责人ID不能为空")
    private Long mngrId;

    @Schema(description = "分域负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分域负责人姓名不能为空")
    private String mngrName;

    @Schema(description = "分域状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分域状态不能为空")
    private Boolean domStatus;

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