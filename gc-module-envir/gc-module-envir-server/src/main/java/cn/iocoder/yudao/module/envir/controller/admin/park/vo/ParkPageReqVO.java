package cn.iocoder.yudao.module.envir.controller.admin.park.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公园分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "16089")
    private String parkId;

    @Schema(description = "公园名称", example = "王五")
    private String name;

    @Schema(description = "公园地址")
    private String address;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "保洁频次（可选值：每小时/每日/每周/定点保洁/节假日加密）")
    private String cleaningFrequency;

    @Schema(description = "绿化品类字典表ID（多个用逗号分隔）")
    private String greenTypeIds;

    @Schema(description = "绿化养护周期（单位：天）")
    private Integer greenMaintenanceCycle;

    @Schema(description = "设施检查周期（单位：天）")
    private Integer facilityCheckCycle;

    @Schema(description = "垃圾清运频次（可选值：每2小时/每日3次/每日2次/每日1次）")
    private String wasteTransferFrequency;

    @Schema(description = "负责人（关联sys_user.id）", example = "2749")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalUpdateTime;

    @Schema(description = "保洁达标率（0.00-100.00）")
    private BigDecimal cleaningRate;

    @Schema(description = "绿化存活率（0.00-100.00）")
    private BigDecimal greenSurvivalRate;

    @Schema(description = "设施完好率（0.00-100.00）")
    private BigDecimal facilityRate;

    @Schema(description = "绿化养护对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String greenPhotoUrl;

    @Schema(description = "设施维护对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String facilityPhotoUrl;

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