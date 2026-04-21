package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 空位推送 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SpacePushRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "场站 ID")
    @ExcelProperty("场站 ID")
    private Long stationId;

    @Schema(description = "空位信息")
    @ExcelProperty("空位信息")
    private String spaceInfo;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "推送状态")
    @ExcelProperty("推送状态")
    private String status;

    @Schema(description = "推送结果")
    @ExcelProperty("推送结果")
    private String pushResult;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

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
