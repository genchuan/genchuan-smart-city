package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 数据更新周期配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingUpdatecyclePageReqVO extends PageParam {

    @Schema(description = "更新周期ID")
    private String updateCycleId;

    @Schema(description = "更新对象类型")
    private String updateObjectType;

    @Schema(description = "常规更新周期")
    private String regularCycle;

    @Schema(description = "特殊更新触发条件")
    private String specialTriggerCondition;

    @Schema(description = "更新责任部门")
    private String updateDept;

    @Schema(description = "更新流程说明")
    private String updateProcessDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}