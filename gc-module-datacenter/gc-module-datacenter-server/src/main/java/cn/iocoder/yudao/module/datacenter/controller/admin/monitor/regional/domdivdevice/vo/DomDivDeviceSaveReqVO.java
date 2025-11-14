package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备分域新增/修改 Request VO")
@Data
public class DomDivDeviceSaveReqVO {

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

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类ID不能为空")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类名称不能为空")
    private String deviceMajorName;

    @Schema(description = "设备小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备小类ID不能为空")
    private String deviceMinorId;

    @Schema(description = "设备小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备小类名称不能为空")
    private String deviceMinorName;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "设备在线率(%)")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "设备管理部门代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备管理部门代码不能为空")
    private String deptCode;

    @Schema(description = "设备管理部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备管理部门名称不能为空")
    private String deptName;

    @Schema(description = "分域状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分域状态不能为空")
    private Boolean domStatus;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "更新时间(业务)不能为空")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}