package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 垃圾异常记录新增/修改 Request VO")
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

    @Schema(description = "异常描述")
    private String abnormalDesc;

    @Schema(description = "异常照片")
    private String abnormalPhotoUrl;

    @Schema(description = "整改说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "[\"https://www.iocoder.cn/photo1.jpg\",\"https://www.iocoder.cn/photo2.jpg\"]")
    private String handlePhotoUrl;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "复核状态：待复核/通过/退回", example = "2")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    private String reviewBy;

    @Schema(description = "复核意见", example = "整改通过")
    private String reviewDesc;

    @Schema(description = "复核时间")
    private LocalDateTime reviewTime;

    public void setHandlePhotoUrl(String handlePhotoUrl) {
        this.handlePhotoUrl = (handlePhotoUrl == null || handlePhotoUrl.trim().isEmpty()) ? "[]" : handlePhotoUrl;
    }

    public void setAbnormalPhotoUrl(String abnormalPhotoUrl) {
        this.abnormalPhotoUrl = (abnormalPhotoUrl == null || abnormalPhotoUrl.trim().isEmpty()) ? "[]" : abnormalPhotoUrl;
    }

}