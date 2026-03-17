package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.urbanvillage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 城中村新增/修改 Request VO")
@Data
public class UrbanVillageSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "12009")
    private String villageId;

    @Schema(description = "城中村名称", example = "芋艿")
    private String name;

    @Schema(description = "城中村地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "责任区域数")
    private Integer responsibilityAreas;

    @Schema(description = "道路保洁频次")
    private String roadCleaningFrequency;

    @Schema(description = "关联sys_user.id", example = "19621")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "3504")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "问题处置完成率")
    private BigDecimal problemRate;

    @Schema(description = "复核通过率")
    private BigDecimal reviewPassRate;

    @Schema(description = "考核得分（满分100）")
    private BigDecimal assessmentScore;

    @Schema(description = "责任区域名称", example = "李四")
    private String responsibilityAreaName;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "问题位置")
    private String problemLocation;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "现场照片URL，JSON", example = "[\"https://www.iocoder.cn/photo1.jpg\", \"https://www.iocoder.cn/photo2.jpg\"]")
    private String problemPhotoUrl;

    @Schema(description = "关联sys_dept.id", example = "11465")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "关联sys_handle_status.id", example = "9561")
    private String handleStatusId;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "处置说明")
    private String handleDesc;

    @Schema(description = "整改照片URL，JSON", example = "[\"https://www.iocoder.cn/photo1.jpg\", \"https://www.iocoder.cn/photo2.jpg\"]")
    private String reformPhotoUrl;

    @Schema(description = "关联sys_user.id")
    private String reviewBy;

    @Schema(description = "复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "关联sys_review_result.id", example = "13703")
    private String reviewResultId;

    @Schema(description = "复核意见")
    private String reviewOpinion;

    public void setStaffIds(String staffIds) {
        this.staffIds = (staffIds == null || staffIds.trim().isEmpty()) ? "[]" : staffIds;
    }

    public void setProblemPhotoUrl(String problemPhotoUrl) {
        this.problemPhotoUrl = (problemPhotoUrl == null || problemPhotoUrl.trim().isEmpty()) ? "[]" : problemPhotoUrl;
    }

    public void setReformPhotoUrl(String reformPhotoUrl) {
        this.reformPhotoUrl = (reformPhotoUrl == null || reformPhotoUrl.trim().isEmpty()) ? "[]" : reformPhotoUrl;
    }
}