package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 沟通管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CommunicateMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28666")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "消息标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("消息标题")
    private String title;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("消息内容")
    private String content;

    @Schema(description = "发布人")
    @ExcelProperty("发布人")
    private String sendUser;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime sendTime;

    @Schema(description = "家长反馈内容")
    @ExcelProperty("家长反馈内容")
    private String replyContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime replyTime;

    @Schema(description = "互动率")
    @ExcelProperty("互动率")
    private BigDecimal interactRate;

    @Schema(description = "状态：未发布/已发布", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：未发布/已发布")
    private String status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}