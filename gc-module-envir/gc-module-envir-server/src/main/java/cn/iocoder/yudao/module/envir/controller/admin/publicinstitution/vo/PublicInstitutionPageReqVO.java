package cn.iocoder.yudao.module.envir.controller.admin.publicinstitution.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公共机构分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicInstitutionPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "10826")
    private String publicInstitutionId;

    @Schema(description = "机构名称", example = "芋艿")
    private String name;

    @Schema(description = "机构类型（关联sys_institution_type.sys_institution_type_id）", example = "28786")
    private String institutionTypeId;

    @Schema(description = "机构地址")
    private String address;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "保洁标准（如：一级保洁/二级保洁/三级保洁）")
    private String cleaningStandard;

    @Schema(description = "保洁频次（可选值：每小时/每日/每周/每月/不定期）")
    private String cleaningFrequency;

    @Schema(description = "垃圾收集时段（如：08:00-09:00/18:00-19:00）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] collectionTime;

    @Schema(description = "负责人（关联sys_user.id）", example = "17918")
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

    @Schema(description = "问题处置完成率（0.00-100.00）")
    private BigDecimal problemRate;

    @Schema(description = "垃圾清运量（单位：立方米）")
    private BigDecimal wasteVolume;

    @Schema(description = "问题上报位置信息（含经纬度、具体地址）")
    private String problemLocation;

    @Schema(description = "保洁对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String cleaningPhotoUrl;

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