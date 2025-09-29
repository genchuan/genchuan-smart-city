package cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 巡查路线新增/修改 Request VO")
@Data
public class PatrolRouteSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "路线名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路线名称不能为空")
    private String routeName;

    @Schema(description = "路线编码")
    private String routeCode;

    @Schema(description = "所属区域ID")
    private String areaId;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "路线类型")
    private String routeType;

    @Schema(description = "关联点位IDs")
    private String relatedPointIds;

    @Schema(description = "点位名称列表")
    private String pointNameList;

    @Schema(description = "路线长度")
    private Double routeLength;

    @Schema(description = "预计耗时(分钟)")
    private Integer estimatedTime;

    @Schema(description = "路线描述")
    private String routeDescription;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Boolean enabledStatus;

}