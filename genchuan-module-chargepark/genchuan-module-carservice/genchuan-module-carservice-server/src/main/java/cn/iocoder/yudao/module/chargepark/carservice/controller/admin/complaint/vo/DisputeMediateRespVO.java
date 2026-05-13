package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 纠纷调解 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DisputeMediateRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "商户 ID")
    @ExcelProperty("商户 ID")
    private Long merchantId;

    @Schema(description = "纠纷内容")
    @ExcelProperty("纠纷内容")
    private String content;

    @Schema(description = "发起时间")
    @ExcelProperty("发起时间")
    private LocalDateTime submitTime;

    @Schema(description = "调解状态,关联字典 dispute_mediate_status")
    @ExcelProperty("调解状态")
    private String status;

    @Schema(description = "调解人 ID")
    @ExcelProperty("调解人 ID")
    private Long mediateUserId;

    @Schema(description = "调解人名（关联 system_user.nickname）")
    @ExcelProperty("调解人名")
    private String mediateUserName;

    @Schema(description = "调解进度")
    @ExcelProperty("调解进度")
    private String progress;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "调解确认结果")
    @ExcelProperty("确认结果")
    private String confirmResult;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
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
