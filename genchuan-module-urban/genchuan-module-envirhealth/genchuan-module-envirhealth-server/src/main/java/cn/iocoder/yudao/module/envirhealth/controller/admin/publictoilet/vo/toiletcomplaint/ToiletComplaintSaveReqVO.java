package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公厕投诉新增/修改 Request VO")
@Data
public class ToiletComplaintSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "投诉主键（UUID）", example = "31780")
    private String complaintId;

    @Schema(description = "关联public_toilet.toilet_id", example = "20776")
    private String toiletId;

    @Schema(description = "关联sys_complaint_type.id", example = "24484")
    private String complaintTypeId;

    @Schema(description = "投诉内容")
    private String content;

    @Schema(description = "投诉人", example = "李四")
    private String complaintName;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "投诉时间")
    private LocalDateTime complaintTime;

    @Schema(description = "派单状态：待派单/已派单/已处置", example = "2")
    private String dispatchStatus;

    @Schema(description = "关联sys_user.id", example = "17583")
    private String handlerId;

    @Schema(description = "是否超时：是/否")
    private String isTimeout;

    @Schema(description = "处置措施")
    private String handleMeasure;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "整改照片URL")
    private String reformPhoto;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}