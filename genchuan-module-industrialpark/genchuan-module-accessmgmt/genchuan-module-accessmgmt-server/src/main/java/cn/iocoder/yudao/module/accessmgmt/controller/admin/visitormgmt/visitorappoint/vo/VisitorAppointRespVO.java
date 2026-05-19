package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 访客预约 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VisitorAppointRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "访客姓名")
    @ExcelProperty("访客姓名")
    private String visitorName;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idCard;

    @Schema(description = "拜访企业")
    @ExcelProperty("拜访企业")
    private String visitCompany;

    @Schema(description = "拜访时间")
    @ExcelProperty("拜访时间")
    private LocalDateTime visitTime;

    @Schema(description = "预约状态")
    @ExcelProperty("预约状态")
    private String appointStatus;

    @Schema(description = "通行凭证")
    @ExcelProperty("通行凭证")
    private String ticket;

    @Schema(description = "到访时间")
    @ExcelProperty("到访时间")
    private LocalDateTime arriveTime;

    @Schema(description = "离园时间")
    @ExcelProperty("离园时间")
    private LocalDateTime leaveTime;

    @Schema(description = "审核人账号")
    @ExcelProperty("审核人账号")
    private String checkUser;

    @Schema(description = "审核结果")
    @ExcelProperty("审核结果")
    private String checkResult;

    @Schema(description = "驳回原因")
    @ExcelProperty("驳回原因")
    private String rejectReason;

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
