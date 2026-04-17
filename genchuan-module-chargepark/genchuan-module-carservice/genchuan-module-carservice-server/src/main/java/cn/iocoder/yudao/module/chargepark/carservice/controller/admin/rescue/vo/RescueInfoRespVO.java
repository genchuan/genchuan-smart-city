package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 救援信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RescueInfoRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "救援位置")
    @ExcelProperty("救援位置")
    private String location;

    @Schema(description = "救援类型")
    @ExcelProperty("救援类型")
    private String rescueType;

    @Schema(description = "派发时间")
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "救援状态")
    @ExcelProperty("救援状态")
    private String status;

    @Schema(description = "救援人员 ID")
    @ExcelProperty("救援人员 ID")
    private Long rescueUserId;

    @Schema(description = "救援人员名（关联 system_user.nickname）")
    @ExcelProperty("救援人员名")
    private String rescueUserName;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "处理时长（秒）")
    @ExcelProperty("处理时长（秒）")
    private Integer handleDuration;

    @Schema(description = "评价得分")
    @ExcelProperty("评价得分")
    private Integer score;

    @Schema(description = "归档状态")
    @ExcelProperty("归档状态")
    private String archiveStatus;

    @Schema(description = "派发备注")
    @ExcelProperty("派发备注")
    private String dispatchRemark;

    @Schema(description = "转派理由")
    @ExcelProperty("转派理由")
    private String transferReason;

    @Schema(description = "救援进度")
    @ExcelProperty("救援进度")
    private String progress;

    @Schema(description = "现场照片")
    @ExcelProperty("现场照片")
    private String photo;

    @Schema(description = "评价内容")
    @ExcelProperty("评价内容")
    private String evaluateContent;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
