package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 城中村 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UrbanVillageRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "12009")
    @ExcelProperty("城中村编号")
    private String villageId;

    @Schema(description = "城中村名称", example = "芋艿")
    @ExcelProperty("城中村名称")
    private String name;

    @Schema(description = "城中村地址")
    @ExcelProperty("城中村地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "责任区域数")
    @ExcelProperty("责任区域数")
    private Integer responsibilityAreas;

    @Schema(description = "道路保洁频次")
    @ExcelProperty("道路保洁频次")
    private String roadCleaningFrequency;

    @Schema(description = "关联sys_user.id", example = "19621")
    @ExcelProperty("管理人员")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "3504")
    @ExcelProperty("运行状态编号")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    @ExcelProperty("保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "问题处置完成率")
    @ExcelProperty("问题处置完成率")
    private BigDecimal problemRate;

    @Schema(description = "复核通过率")
    @ExcelProperty("复核通过率")
    private BigDecimal reviewPassRate;

    @Schema(description = "考核得分（满分100）")
    @ExcelProperty("考核得分(满分100)")
    private BigDecimal assessmentScore;

    @Schema(description = "责任区域名称", example = "李四")
    @ExcelProperty("责任区域名称")
    private String responsibilityAreaName;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员")
    private String staffIds;

    @Schema(description = "问题位置")
    @ExcelProperty("问题位置")
    private String problemLocation;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("上报人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "现场照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("现场照片")
    private String problemPhotoUrl;

    @Schema(description = "关联sys_dept.id", example = "11465")
    @ExcelProperty("部门编号")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("处置人员")
    private String handleBy;

    @Schema(description = "派单时间")
    @ExcelProperty("派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "关联sys_handle_status.id", example = "9561")
    @ExcelProperty("处置状态编号")
    private String handleStatusId;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String isTimeout;

    @Schema(description = "处置说明")
    @ExcelProperty("处置说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("整改照片")
    private String reformPhotoUrl;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("复核人员")
    private String reviewBy;

    @Schema(description = "复核时间")
    @ExcelProperty("复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "关联sys_review_result.id", example = "13703")
    @ExcelProperty("复核结果编号")
    private String reviewResultId;

    @Schema(description = "复核意见")
    @ExcelProperty("复核意见")
    private String reviewOpinion;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}