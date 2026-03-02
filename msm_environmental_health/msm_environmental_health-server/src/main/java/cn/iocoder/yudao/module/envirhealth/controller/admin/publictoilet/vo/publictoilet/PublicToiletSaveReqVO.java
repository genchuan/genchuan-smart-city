package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公厕新增/修改 Request VO")
@Data
public class PublicToiletSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22794")
    private Long id;

    @Schema(description = "公厕主键（UUID）", example = "495")
    private String toiletId;

    @Schema(description = "公厕名称", example = "李四")
    private String name;

    @Schema(description = "公厕位置")
    private String location;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "开放时段")
    private String openHours;

    @Schema(description = "蹲位数量", example = "16057")
    private Integer stallCount;

    @Schema(description = "关联sys_operation_status.id", example = "6154")
    private String operationStatusId;

    @Schema(description = "关联sys_user.id", example = "27436")
    private String managerId;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "投诉办结率")
    private BigDecimal complaintRate;

    @Schema(description = "耗材库存预警数", example = "5094")
    private Integer warningCount;

    @Schema(description = "设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁内容")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON")
    private String cleanerIds;

    @Schema(description = "耗材库存")
    private String consumableStock;

    @Schema(description = "预警阈值")
    private Integer consumableThreshold;

    @Schema(description = "缺口数量")
    private Integer consumableGap;

    @Schema(description = "上次补充时间")
    private LocalDateTime lastSupplyTime;

    @Schema(description = "补充周期")
    private String supplyCycle;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}