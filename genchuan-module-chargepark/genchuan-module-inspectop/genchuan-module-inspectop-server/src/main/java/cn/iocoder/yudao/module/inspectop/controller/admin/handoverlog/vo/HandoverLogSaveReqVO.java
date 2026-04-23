package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 交接日志新增/修改 Request VO")
@Data
public class HandoverLogSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "交接人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交接人员ID不能为空")
    private Long userId;

    @Schema(description = "交接日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交接日期不能为空")
    private LocalDateTime handoverDate;

    @Schema(description = "交接内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "交接内容不能为空")
    private String content;

    @Schema(description = "日志状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "日志状态不能为空")
    private String status;

    @Schema(description = "确认人ID")
    private Long confirmUserId;

    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}