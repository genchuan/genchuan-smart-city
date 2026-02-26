package cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 城中村分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UrbanVillagePageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "18483")
    private String urbanVillageId;

    @Schema(description = "城中村名称", example = "王五")
    private String name;

    @Schema(description = "城中村地址")
    private String address;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "责任区域数量")
    private Integer responsibilityAreas;

    @Schema(description = "责任区域划分规则（含划分依据、区域边界、责任人等）")
    private String areaDivideRule;

    @Schema(description = "道路保洁频次（可选值：每小时/每日2次/每日1次/隔日1次/每周2次）")
    private String roadCleaningFrequency;

    @Schema(description = "垃圾收集时段（如：07:00-08:00/19:00-20:00）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] wasteCollectionTime;

    @Schema(description = "负责人（关联sys_user.id）", example = "32682")
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

    @Schema(description = "考核得分（0.00-100.00）")
    private BigDecimal assessmentScore;

    @Schema(description = "问题上报照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String problemPhotoUrl;

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