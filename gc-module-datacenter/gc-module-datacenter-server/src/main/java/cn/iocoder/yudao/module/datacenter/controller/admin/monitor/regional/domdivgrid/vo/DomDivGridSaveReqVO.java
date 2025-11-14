package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 网格分域新增/修改 Request VO")
@Data
public class DomDivGridSaveReqVO {

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

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格类型不能为空")
    private String gridType;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格ID不能为空")
    private String gridId;

    @Schema(description = "网格编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格编码不能为空")
    private String gridCode;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格名称不能为空")
    private String gridName;

    @Schema(description = "所属行政区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属行政区域代码不能为空")
    private String adminCode;

    @Schema(description = "所属行政区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属行政区域名称不能为空")
    private String adminName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "网格员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "网格员ID不能为空")
    private Long gridUserId;

    @Schema(description = "网格员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格员姓名不能为空")
    private String gridUserName;

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