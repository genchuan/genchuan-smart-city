package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 迎新推送新增/修改 Request VO")
@Data
public class NewPushSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5383")
    private Long id;

    @Schema(description = "推送任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "推送任务名称不能为空")
    private String taskName;

    @Schema(description = "推送内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送内容不能为空")
    private String pushContent;

    @Schema(description = "推送人数")
    private Integer pushNum;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "推送完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：未推送/已推送", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：未推送/已推送不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}