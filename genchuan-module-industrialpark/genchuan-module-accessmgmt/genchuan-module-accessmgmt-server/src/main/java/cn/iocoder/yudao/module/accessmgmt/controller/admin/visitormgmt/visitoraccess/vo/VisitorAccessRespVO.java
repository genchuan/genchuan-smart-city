package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 访客通行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VisitorAccessRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预约ID")
    @ExcelProperty("预约ID")
    private Long appointId;

    @Schema(description = "访客姓名")
    @ExcelProperty("访客姓名")
    private String visitorName;

    @Schema(description = "通行区域")
    @ExcelProperty("通行区域")
    private String accessArea;

    @Schema(description = "通行时间")
    @ExcelProperty("通行时间")
    private LocalDateTime accessTime;

    @Schema(description = "凭证状态")
    @ExcelProperty("凭证状态")
    private String ticketStatus;

    @Schema(description = "通行状态")
    @ExcelProperty("通行状态")
    private String accessStatus;

    @Schema(description = "核查结果")
    @ExcelProperty("核查结果")
    private String checkResult;

    @Schema(description = "授权有效期")
    @ExcelProperty("授权有效期")
    private LocalDateTime authValidity;

    @Schema(description = "经办人")
    @ExcelProperty("经办人")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
