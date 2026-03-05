package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公厕设施维修新增/修改 Request VO")
@Data
public class ToiletFacilityRepairSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "维修主键（UUID）", example = "10879")
    private String repairId;

    @Schema(description = "关联public_toilet.toilet_id", example = "11177")
    private String toiletId;

    @Schema(description = "关联sys_facility.id", example = "19156")
    private String facilityId;

    @Schema(description = "损坏情况")
    private String damageDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "现场照片URL", example = "https://www.iocoder.cn")
    private String photoUrl;

    @Schema(description = "关联sys_user.id")
    private String repairBy;

    @Schema(description = "维修状态：待维修/维修中/已完成/不合格", example = "2")
    private String repairStatus;

    @Schema(description = "预计完成时间")
    private LocalDateTime expectedCompleteTime;

    @Schema(description = "验收结果：合格/不合格")
    private String acceptResult;

    @Schema(description = "验收意见")
    private String acceptOpinion;

}