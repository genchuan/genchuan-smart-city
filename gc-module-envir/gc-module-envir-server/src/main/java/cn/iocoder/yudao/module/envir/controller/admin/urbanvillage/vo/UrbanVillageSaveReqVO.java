package cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 城中村新增/修改 Request VO")
@Data
public class UrbanVillageSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23954")
    private Long id;

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
    private String wasteCollectionTime;

    @Schema(description = "负责人（关联sys_user.id）", example = "32682")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime abnormalUpdateTime;

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

}