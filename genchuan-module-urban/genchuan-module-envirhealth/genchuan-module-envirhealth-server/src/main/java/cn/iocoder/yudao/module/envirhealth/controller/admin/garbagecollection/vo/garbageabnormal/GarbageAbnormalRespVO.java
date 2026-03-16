package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 垃圾异常记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageAbnormalRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14169")
    @ExcelIgnore
    private Long id;

    @Schema(description = "异常记录主键（UUID）", example = "11260")
    @ExcelProperty("异常记录主键")
    private String abnormalId;

    @Schema(description = "关联garbage_collection.collection_id", example = "12102")
    @ExcelProperty("计划编号")
    private String planId;

    @Schema(description = "关联sys_abnormal_type.id", example = "30798")
    @ExcelProperty("垃圾异常编号")
    private String abnormalTypeId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("报告人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "优先级：高/中/低")
    @ExcelProperty("优先级")
    private String priority;

    @Schema(description = "关联sys_user.id", example = "9865")
    @ExcelProperty("处理人员")
    private String handlerId;

    @Schema(description = "处置状态：待处置/处理中/已办结/退回", example = "1")
    @ExcelProperty("处置状态")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String isTimeout;

    @Schema(description = "异常描述")
    @ExcelProperty("异常描述")
    private String abnormalDesc;

    @Schema(description = "异常照片")
    @ExcelProperty("异常照片")
    private String abnormalPhotoUrl;

    @Schema(description = "整改说明")
    @ExcelProperty("整改说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("整改照片")
    private String handlePhotoUrl;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "复核状态：待复核/通过/退回", example = "2")
    @ExcelProperty("复核状态")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("复核人员")
    private String reviewBy;

    @Schema(description = "复核意见", example = "整改通过")
    @ExcelProperty("复核意见")
    private String reviewDesc;

    @Schema(description = "复核时间")
    @ExcelProperty("复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}