package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 迎新推送 Response VO")
@Data
@ExcelIgnoreUnannotated
public class NewPushRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5383")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "推送任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("推送任务名称")
    private String taskName;

    @Schema(description = "推送内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送内容")
    private String pushContent;

    @Schema(description = "推送人数")
    @ExcelProperty("推送人数")
    private Integer pushNum;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "推送完成率")
    @ExcelProperty("推送完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：未推送/已推送", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：未推送/已推送")
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