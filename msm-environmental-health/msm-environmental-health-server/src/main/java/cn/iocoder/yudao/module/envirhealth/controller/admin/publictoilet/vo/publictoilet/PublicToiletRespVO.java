package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 公厕 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicToiletRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22794")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "公厕主键（UUID）", example = "495")
    @ExcelProperty("公厕主键（UUID）")
    private String toiletId;

    @Schema(description = "公厕名称", example = "李四")
    @ExcelProperty("公厕名称")
    private String name;

    @Schema(description = "公厕位置")
    @ExcelProperty("公厕位置")
    private String location;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "开放时段")
    @ExcelProperty("开放时段")
    private String openHours;

    @Schema(description = "蹲位数量", example = "16057")
    @ExcelProperty("蹲位数量")
    private Integer stallCount;

    @Schema(description = "关联sys_operation_status.id", example = "6154")
    @ExcelProperty("关联sys_operation_status.id")
    private String operationStatusId;

    @Schema(description = "关联sys_user.id", example = "27436")
    @ExcelProperty("关联sys_user.id")
    private String managerId;

    @Schema(description = "保洁达标率")
    @ExcelProperty("保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "投诉办结率")
    @ExcelProperty("投诉办结率")
    private BigDecimal complaintRate;

    @Schema(description = "耗材库存预警数", example = "5094")
    @ExcelProperty("耗材库存预警数")
    private Integer warningCount;

    @Schema(description = "设施完好率")
    @ExcelProperty("设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁内容")
    @ExcelProperty("保洁内容")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON")
    @ExcelProperty("保洁人员IDs，JSON")
    private String cleanerIds;

    @Schema(description = "耗材库存")
    @ExcelProperty("耗材库存")
    private String consumableStock;

    @Schema(description = "预警阈值")
    @ExcelProperty("预警阈值")
    private Integer consumableThreshold;

    @Schema(description = "缺口数量")
    @ExcelProperty("缺口数量")
    private Integer consumableGap;

    @Schema(description = "上次补充时间")
    @ExcelProperty("上次补充时间")
    private LocalDateTime lastSupplyTime;

    @Schema(description = "补充周期")
    @ExcelProperty("补充周期")
    private String supplyCycle;

    @JsonIgnore
    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @JsonIgnore
    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @JsonIgnore
    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @JsonIgnore
    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}