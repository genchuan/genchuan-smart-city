package cn.iocoder.yudao.module.envir.controller.admin.commercialstreet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商业街新增/修改 Request VO")
@Data
public class CommercialStreetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18425")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "23230")
    private String commercialStreetId;

    @Schema(description = "商业街名称", example = "王五")
    private String name;

    @Schema(description = "商业街地址")
    private String address;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "保洁频次（可选值：每小时/每日/每周/定点保洁/实时保洁）")
    private String cleaningFrequency;

    @Schema(description = "巡回保洁间隔（单位：小时）")
    private Integer patrolInterval;

    @Schema(description = "垃圾收集点位数量")
    private Integer collectionPoints;

    @Schema(description = "垃圾清运间隔（单位：小时）")
    private Integer transferInterval;

    @Schema(description = "设施类型（关联sys_facility.sys_facility_id，多个用逗号分隔）")
    private String facilityIds;

    @Schema(description = "负责人（关联sys_user.id）", example = "23543")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "保洁覆盖率（0.00-100.00）")
    private BigDecimal cleaningCoverage;

    @Schema(description = "设施完好率（0.00-100.00）")
    private BigDecimal facilityRate;

    @Schema(description = "问题平均处置时长（单位：分钟）")
    private BigDecimal disposalDuration;

    @Schema(description = "问题处置对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String problemPhotoUrl;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}