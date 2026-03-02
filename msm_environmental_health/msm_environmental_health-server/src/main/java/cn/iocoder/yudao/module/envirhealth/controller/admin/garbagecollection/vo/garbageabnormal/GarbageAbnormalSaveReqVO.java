package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 垃圾异常记录新增/修改 Request VO")
@Data
public class GarbageAbnormalSaveReqVO {

    @Schema(description = "主键ID", example = "15")
    private Long id;

    @Schema(description = "异常记录主键（UUID）", example = "11260")
    private String abnormalId;

    @Schema(description = "关联garbage_collection.collection_id", example = "12102")
    private String planId;

    @Schema(description = "关联sys_abnormal_type.id", example = "30798")
    private String abnormalTypeId;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "优先级：高/中/低")
    private String priority;

    @Schema(description = "关联sys_user.id", example = "9865")
    private String handlerId;

    @Schema(description = "处置状态：待处置/处理中/已办结/退回", example = "1")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "整改说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "[\"https://www.iocoder.cn/photo1.jpg\",\"https://www.iocoder.cn/photo2.jpg\"]")
    @Pattern(regexp = "^$|^\\[.*\\]$", message = "handlePhotoUrl必须为JSON数组格式（如[]）")
    private String handlePhotoUrl;

    @Schema(description = "复核状态：待复核/通过/退回", example = "2")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    private String reviewBy;

    @Schema(description = "复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    public void setHandlePhotoUrl(String handlePhotoUrl) {
        this.handlePhotoUrl = (handlePhotoUrl == null || handlePhotoUrl.trim().isEmpty()) ? "[]" : handlePhotoUrl;
    }

}