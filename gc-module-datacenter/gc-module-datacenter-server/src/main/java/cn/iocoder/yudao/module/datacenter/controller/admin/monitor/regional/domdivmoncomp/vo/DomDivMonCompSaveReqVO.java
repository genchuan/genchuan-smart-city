package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件分域新增/修改 Request VO")
@Data
public class DomDivMonCompSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "分极编码")
    private String domCode;

    @Schema(description = "监测部件大类ID")
    private String monCompMajorId;

    @Schema(description = "监测部件大类名称")
    private String monCompMajorName;

    @Schema(description = "监测部件小类ID")
    private String monCompMinorId;

    @Schema(description = "监测部件小类名称")
    private String monCompMinorName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "设备在线率(%)")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "维护部门代码")
    private String deptCode;

    @Schema(description = "维护部门名称")
    private String deptName;

    @Schema(description = "分域状态")
    private Boolean domStatus;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新时间(业务)")
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