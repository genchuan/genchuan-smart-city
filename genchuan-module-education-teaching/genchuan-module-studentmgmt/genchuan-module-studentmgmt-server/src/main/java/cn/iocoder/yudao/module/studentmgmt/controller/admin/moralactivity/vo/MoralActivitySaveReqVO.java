package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 德育活动新增/修改 Request VO")
@Data
public class MoralActivitySaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32555")
    private Long id;

    @Schema(description = "活动名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "活动名称不能为空")
    private String activityName;

    @Schema(description = "活动类型：党团活动/志愿活动/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "活动类型：党团活动/志愿活动/其他不能为空")
    private String activityType;

    @Schema(description = "主办部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主办部门不能为空")
    private Long hostDept;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "参与人数")
    private Integer joinNum;

    @Schema(description = "活动照片地址")
    private String photo;

    @Schema(description = "活动详情")
    private String content;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "状态：未发布/进行中/已结束", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：未发布/进行中/已结束不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}