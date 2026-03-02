package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 公厕设施维修 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ToiletFacilityRepairRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25662")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "维修主键（UUID）", example = "10879")
    @ExcelProperty("维修主键（UUID）")
    private String repairId;

    @Schema(description = "关联public_toilet.toilet_id", example = "11177")
    @ExcelProperty("关联public_toilet.toilet_id")
    private String toiletId;

    @Schema(description = "关联sys_facility.id", example = "19156")
    @ExcelProperty("关联sys_facility.id")
    private String facilityId;

    @Schema(description = "损坏情况")
    @ExcelProperty("损坏情况")
    private String damageDesc;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "现场照片URL", example = "https://www.iocoder.cn")
    @ExcelProperty("现场照片URL")
    private String photoUrl;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String repairBy;

    @Schema(description = "维修状态：待维修/维修中/已完成/不合格", example = "2")
    @ExcelProperty("维修状态：待维修/维修中/已完成/不合格")
    private String repairStatus;

    @Schema(description = "预计完成时间")
    @ExcelProperty("预计完成时间")
    private LocalDateTime expectedCompleteTime;

    @Schema(description = "验收结果：合格/不合格")
    @ExcelProperty("验收结果：合格/不合格")
    private String acceptResult;

    @Schema(description = "验收意见")
    @ExcelProperty("验收意见")
    private String acceptOpinion;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}