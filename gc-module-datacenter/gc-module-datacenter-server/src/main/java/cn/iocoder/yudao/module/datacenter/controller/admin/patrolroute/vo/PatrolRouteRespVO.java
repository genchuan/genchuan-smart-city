package cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查路线 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PatrolRouteRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "路线名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("路线名称")
    private String routeName;

    @Schema(description = "路线编码")
    @ExcelProperty("路线编码")
    private String routeCode;

    @Schema(description = "所属区域ID")
    @ExcelProperty("所属区域ID")
    private String areaId;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "路线类型")
    @ExcelProperty("路线类型")
    private String routeType;

    @Schema(description = "关联点位IDs")
    @ExcelProperty("关联点位IDs")
    private String relatedPointIds;

    @Schema(description = "点位名称列表")
    @ExcelProperty("点位名称列表")
    private String pointNameList;

    @Schema(description = "路线长度")
    @ExcelProperty("路线长度")
    private Double routeLength;

    @Schema(description = "预计耗时(分钟)")
    @ExcelProperty("预计耗时(分钟)")
    private Integer estimatedTime;

    @Schema(description = "路线描述")
    @ExcelProperty("路线描述")
    private String routeDescription;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Boolean enabledStatus;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}