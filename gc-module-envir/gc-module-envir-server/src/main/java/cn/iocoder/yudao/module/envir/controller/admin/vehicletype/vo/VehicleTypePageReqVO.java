package cn.iocoder.yudao.module.envir.controller.admin.vehicletype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆类型字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehicleTypePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "969")
    private String sysVehicleTypeId;

    @Schema(description = "类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）", example = "王五")
    private String name;

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    private Integer status;

    @Schema(description = "类型描述", example = "随便")
    private String description;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}