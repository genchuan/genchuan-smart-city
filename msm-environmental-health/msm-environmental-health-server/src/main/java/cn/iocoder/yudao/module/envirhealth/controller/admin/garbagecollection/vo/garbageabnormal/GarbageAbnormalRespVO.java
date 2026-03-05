package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 垃圾异常记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageAbnormalRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14169")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "异常记录主键（UUID）", example = "11260")
    @ExcelProperty("异常记录主键（UUID）")
    private String abnormalId;

    @Schema(description = "关联garbage_collection.collection_id", example = "12102")
    @ExcelProperty("关联garbage_collection.collection_id")
    private String planId;

    @Schema(description = "关联sys_abnormal_type.id", example = "30798")
    @ExcelProperty("关联sys_abnormal_type.id")
    private String abnormalTypeId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "优先级：高/中/低")
    @ExcelProperty("优先级：高/中/低")
    private String priority;

    @Schema(description = "关联sys_user.id", example = "9865")
    @ExcelProperty("关联sys_user.id")
    private String handlerId;

    @Schema(description = "处置状态：待处置/处理中/已办结/退回", example = "1")
    @ExcelProperty("处置状态：待处置/处理中/已办结/退回")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "整改说明")
    @ExcelProperty("整改说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("整改照片URL，JSON")
    private String handlePhotoUrl;

    @Schema(description = "复核状态：待复核/通过/退回", example = "2")
    @ExcelProperty("复核状态：待复核/通过/退回")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reviewBy;

    @Schema(description = "复核时间")
    @ExcelProperty("复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}